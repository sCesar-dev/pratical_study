# Guia Auxiliar: Conceitos, Comandos e Funções Fundamentais

Este documento reúne comandos de terminal, boas práticas de concorrência com Pyro5, detalhes matemáticos de relógios vetoriais e armadilhas comuns para o desenvolvimento da **Atividade 04 (Monitoramento Distribuído com Relógios Vetoriais)**.

---

## 1. Comandos de Terminal (Pyro5 CLI)

| Comando | Descrição | Importância / Observação |
| :--- | :--- | :--- |
| `pyro5-ns` | Inicia o Servidor de Nomes | **Obrigatório.** Deve ser executado em um terminal antes de qualquer processo. |
| `python3 -m Pyro5.nsc list` | Inspeciona serviços registrados | Lista todos os objetos/processos registrados no Name Server com suas respectivas URIs. Essencial para verificar se $P_1, P_2, P_3, P_4$ subiram com sucesso. |
| `python3 -m Pyro5.nsc ping` | Testa a conectividade | Verifica se o Name Server está ativo e respondendo na porta padrão (geralmente `9090`). |

> [!TIP]
> **Automação de Inicialização:** Em vez de abrir 4 abas de terminal para rodar cada processo manualmente, você pode criar um script `launcher.py` usando `subprocess.Popen([sys.executable, "processo.py", "P1"])` para inicializar todos de uma só vez.

---

## 2. Peculiaridades Críticas do Pyro5

### A. O `daemon.requestLoop()` é Bloqueante
O loop de escuta do Pyro5 (`daemon.requestLoop()`) trava a thread onde é chamado. Se for executado na thread principal, o processo não conseguirá gerar seus próprios eventos locais em paralelo.
* **Solução:** Execute o loop do Pyro em uma **thread separada em modo daemon**:
```python
import threading
import Pyro5.api

daemon = Pyro5.api.Daemon()
uri = daemon.register(meu_processo)
ns = Pyro5.api.locate_ns()
ns.register("processo.P1", uri)

# Inicia o servidor Pyro em segundo plano
thread_servidor = threading.Thread(target=daemon.requestLoop, daemon=True)
thread_servidor.start()

# A thread principal permanece livre para rodar o loop de geração de eventos
```

### B. Serialização e Tipos Primitivos
O Pyro5 utiliza serialização nativa (`serpent`). Evite transferir instâncias de classes complexas criadas por você.
* Envie mensagens remotas utilizando estruturas primitivas (dicionários e listas):
```python
mensagem = {
    "remetente": "P1",
    "evento_id": 1,
    "relogio": [1, 0, 0, 0],
    "timestamp_local": time.time()
}
```

---

## 3. Concorrência e Threads em Python

### A. Condição de Corrida no Relógio Vetorial (`threading.Lock`)
Como mensagens remotas chegam pela thread do Pyro ao mesmo tempo em que a thread principal gera eventos locais, o relógio vetorial é uma **região crítica compartilhada**. Sem sincronização, o vetor sofrerá inconsistência.
```python
import threading

class Processo:
    def __init__(self, id_proc, total_proc):
        self.id_proc = id_proc
        self.relogio = [0] * total_proc
        self.lock = threading.Lock()

    def evento_local(self):
        with self.lock:
            self.relogio[self.id_proc] += 1
            # Operação protegida
```

### B. Efeito do "Relógio do Futuro" (Snapshots com `copy()`)
Ao disseminar mensagens com atrasos aleatórios (`time.sleep`), **nunca passe a lista direta do relógio para a thread de envio**. Se um novo evento ocorrer durante o atraso, a thread enviará o relógio atualizado indevidamente.
```python
# Incorreto: passa a referência direta mutável
# threading.Thread(target=self._enviar, args=(destinatario, self.relogio)).start()

# Correto: cria uma cópia congelada (snapshot) no exato instante do evento
vetor_snapshot = self.relogio.copy()
threading.Thread(target=self._enviar_com_delay, args=(destinatario, vetor_snapshot)).start()
```

---

## 4. Algoritmo de Relógio Vetorial

### A. Regras de Atualização (Fidge / Mattern)
1. **Ao gerar um evento local:**
   $$V[i] = V[i] + 1$$
2. **Ao receber uma mensagem com vetor $V_{msg}$:**
   $$V[k] = \max(V[k], V_{msg}[k]), \quad \forall k \neq i$$
   $$V[i] = V[i] + 1$$ *(o recebimento também conta como evento local do processo)*

Implementação em Python:
```python
def atualizar_no_recebimento(self, vetor_msg):
    with self.lock:
        for k in range(len(self.relogio)):
            self.relogio[k] = max(self.relogio[k], vetor_msg[k])
        self.relogio[self.id_proc] += 1
```

### B. Comparação Causal e Identificação de Concorrência
Para verificar as relações causais entre dois eventos $A$ e $B$ com relógios $V_A$ e $V_B$:
* $A \rightarrow B$ ($A$ causou $B$) se $\forall k, V_A[k] \le V_B[k]$ e $\exists k, V_A[k] < V_B[k]$
* $B \rightarrow A$ ($B$ causou $A$) se $\forall k, V_B[k] \le V_A[k]$ e $\exists k, V_B[k] < V_A[k]$
* $A \parallel B$ ($A$ concorrente com $B$) caso contrário.

```python
def comparar_relogios(va, vb):
    menor_igual_ab = all(a <= b for a, b in zip(va, vb))
    estritamente_menor_ab = any(a < b for a, b in zip(va, vb))
    
    menor_igual_ba = all(b <= a for a, b in zip(va, vb))
    estritamente_menor_ba = any(b < a for a, b in zip(va, vb))
    
    if menor_igual_ab and estritamente_menor_ab:
        return "A causou B (A -> B)"
    elif menor_igual_ba and estritamente_menor_ba:
        return "B causou A (B -> A)"
    else:
        return "Concorrentes (A || B)"
```

---

## 5. Diagrama da Arquitetura do Processo

```
                  ┌──────────────────────────────────────┐
                  │              PROCESSO Pi             │
                  │                                      │
                  │  ┌────────────────────────────────┐  │
Remoto chama ────►│  │ Servidor Pyro (Daemon Thread)  │  │
receber_msg()     │  │  - Recebe chamada RPC          │  │
                  │  │  - Atualiza relógio com Lock   │  │
                  │  └────────────────────────────────┘  │
                  │                                      │
                  │  ┌────────────────────────────────┐  │
Loop principal ──►│  │ Gerador de Eventos Locais      │  │
                  │  │  - time.sleep(random)          │  │
                  │  │  - Atualiza relógio com Lock   │  │
                  │  │  - Dispara threads de envio    │  │
                  │  └───────────────┬────────────────┘  │
                  │                  │                   │
                  │                  ▼                   │
                  │     Disparo com atraso aleatório     │
                  │     threading.Thread(envio_delay)    │
                  └──────────────────┬───────────────────┘
                                     │
                                     ▼ Pyro RPC
                        Demais Processos (Pj, Pk, ...)
```
