# Monitoramento Distribuído de Eventos com Relógios Vetoriais

> **Prazo:** 12 de Outubro de 2026, 23:59  
> **Fechamento:** 19 de Outubro de 2026, 23:59  
> **Envios:** Múltiplas tentativas permitidas  

---

## 1. Objetivo

Desenvolver um sistema distribuído simples utilizando **Python** e **Pyro5**, no qual diferentes processos geram e compartilham eventos. O sistema deverá utilizar **relógios vetoriais** para registrar a ordem causal dos eventos e identificar eventos concorrentes.

---

## 2. Descrição da Atividade

O sistema deverá possuir pelo menos **quatro processos independentes** ($P_1, P_2, P_3, P_4$), executados concorrentemente.

Cada processo deverá gerar eventos sucessivos em instantes aleatórios. Um evento poderá representar, por exemplo:
- A conclusão de uma tarefa;
- Uma alteração de estado;
- A ocorrência de uma operação local.

### Ciclo de Vida de um Evento Local
Quando um processo gerar um evento, ele deverá:
1. **Atualizar seu relógio vetorial** (incrementar sua própria posição no vetor).
2. **Registrar o evento em um log local**, incluindo:
   - Identificador do processo ($P_i$);
   - Número/ID sequencial do evento;
   - Instante de ocorrência (timestamp local);
   - Estado atual do relógio vetorial.
3. **Enviar uma mensagem aos demais processos** informando sobre a ocorrência do evento e o relógio vetorial associado.

### Disseminação e Atrasos Simulados
- As mensagens destinadas aos diferentes processos deverão ser enviadas com uma pequena defasagem de tempo, utilizando atrasos simulados (`time.sleep`) aleatórios.
- Assim, cada destinatário poderá receber as mensagens em ordem diferente.

### Recepção de Mensagens
Ao receber uma mensagem de outro processo:
1. Atualizar seu relógio vetorial conforme as regras de relógios vetoriais:
   $$V_{local}[i] = \max(V_{local}[i], V_{msg}[i]) \quad \forall i \neq local$$
   $$V_{local}[local] = V_{local}[local] + 1$$
2. Registrar o recebimento no log local com o estado do relógio resultante.

> [!NOTE]
> O sistema deverá continuar gerando e disseminando eventos por um período definido (por exemplo, **30 segundos**).

---

## 3. Requisitos de Implementação

- [ ] **Middleware RPC:** Utilizar Python e **Pyro5** para a comunicação entre processos.
- [ ] **Múltiplos Processos:** Implementar pelo menos 4 processos independentes concorrentes ($P_1, P_2, P_3, P_4$).
- [ ] **Geração Assíncrona:** Gerar eventos em intervalos aleatórios.
- [ ] **Atrasos de Rede Simulados:** Disseminar eventos aos demais processos com delays e ordens distintas por destinatário.
- [ ] **Relógios Vetoriais:** Implementar rigorosamente as regras de atualização vetorial (evento local vs. evento de recepção).
- [ ] **Logging Completo:** Manter log rastreável de eventos locais e recepções.
- [ ] **Visualização:** Exibir os logs de forma que permita acompanhar e auditar a evolução causal e concorrência dos eventos.

---

## 4. Entrega

A equipe deverá entregar:
1. **Código-fonte completo:** Comentado em detalhes, explicando a arquitetura e a lógica causal.
2. **Instruções de execução:** Passo a passo para subir o Servidor de Nomes Pyro5 (`pyro5-ns`) e instanciar cada processo.
3. **Exemplos de Logs:** Amostras dos logs gerados durante uma execução real, destacando as relações causais.

---

## 5. Critérios de Avaliação

| Peso | Critério | Foco da Avaliação |
| :---: | :--- | :--- |
| **30%** | **Relógios Vetoriais** | Atualização correta nos eventos locais e no recebimento de mensagens. |
| **25%** | **Comunicação Distribuída** | Funcionamento robusto entre processos e disseminação via Pyro5. |
| **25%** | **Concorrência e Atrasos** | Geração independente de eventos e envio com delays simulados sem travamento. |
| **20%** | **Organização e Documentação** | Clareza do código, documentação de execução e qualidade dos logs. |

---

## 6. Observações & Diretrizes Técnicas

> [!IMPORTANT]
> **Distinção de Eventos:** É fundamental diferenciar o evento de **envio/local** do evento de **recebimento**. São eventos distintos, registrados em momentos e processos diferentes.

> [!WARNING]
> **Não bloqueie a recepção:** O atraso simulado (`time.sleep`) deve ocorrer entre os envios aos destinatários, **mas sem bloquear o tratamento de mensagens recebidas**. O uso de threads separadas (`threading.Thread`) é essencial para que o servidor Pyro continue respondendo enquanto threads em segundo plano disparam mensagens com atraso.

> [!TIP]
> **Objetivo Conceitual:** O principal resultado esperado é demonstrar que os relógios vetoriais identificam relações causais e concorrência que **não podem ser determinadas apenas pelo timestamp físico ou pela ordem de chegada das mensagens na rede**.