# ⚔️ Recrutamento da Guilda

Este projeto é uma atividade prática de **Padrões de Arquitetura de Software (PAS)** em Java. O cenário simula o sistema de seleção e recrutamento de aventureiros de uma Guilda de RPG para missões perigosas.

O sistema combina dois padrões de projeto:
1. **Decorator** (já implementado) para equipar heróis e calcular seus atributos dinamicamente.
2. **Filter / Criteria** (foco da sua atividade) para filtrar heróis com base em requisitos simples e compostos.

---

## 📚 Padrões de Projeto Utilizados

### 1. Padrão Decorator (GoF - Estrutural)
> **Status:** ✅ *Já implementado no pacote `guilda.decorator`.*

#### O que é e por que foi usado?
O **Decorator** permite adicionar comportamentos ou responsabilidades a objetos individuais dinamicamente, sem a necessidade de criar uma explosão de subclasses para cada combinação possível de itens (ex.: não precisamos de `GuerreiroComEspadaEEscudo`, `GuerreiroComArmadura`, etc.).

#### Como funciona no código:
- **`Heroi` (Interface / Component):** Define o contrato de um aventureiro com métodos para obter `nome`, `classe`, `nivel`, `ataque` e `defesa`.
- **`HeroiBase` (Concrete Component):** É o aventureiro "base" (com seus atributos nativos, sem equipamentos).
- **`EquipamentoDecorator` (Decorator Abstrato):** Implementa `Heroi` e contém uma referência para outro `Heroi` interno, repassando as chamadas por padrão.
- **Equipamentos Concretos (`EspadaFlamejante`, `EscudoDeCarvalho`, `ArmaduraPesada`):** Sobrescrevem apenas os métodos que alteram atributos:
  - `EspadaFlamejante`: adiciona `+8` ao ataque.
  - `EscudoDeCarvalho`: adiciona `+5` à defesa.
  - `ArmaduraPesada`: adiciona `+9` à defesa e penaliza `-2` no ataque.

```text
[EscudoDeCarvalho (+5 DEF)]
    └── [EspadaFlamejante (+8 ATK)]
            └── [HeroiBase: Thorin (ATK 10, DEF 6)]
  ==> Resultado final calculado: ATK = 18, DEF = 11
```

---

### 2. Padrão Filter / Criteria (Estrutural / Core J2EE)
> **Status:** 🎯 *Seu objetivo na atividade (pacote `guilda.filtro`).*

#### O que é e por que foi usado?
O **Filter** (ou **Criteria**) desacopla os critérios de busca/filtragem dos dados e das regras de negócio. Ele permite expressar regras de filtragem como objetos independentes que podem ser encadeados e combinados por meio de operadores lógicos (**E**, **OU**, **NÃO**), seguindo o princípio de *Responsabilidade Única* (SRP) e o princípio de *Aberto/Fechado* (OCP).

#### Como funciona no código:
- **`Criterio` (Interface):**
  ```java
  public interface Criterio {
      List<Heroi> filtrar(List<Heroi> herois);
  }
  ```
  Toda classe de critério recebe uma lista e retorna uma **nova lista** contendo apenas os heróis que atendem à regra, sem alterar a lista original.

- **Critérios Simples (Critérios Atômicos):**
  - Avaliam uma única propriedade do herói (ex.: classe, ataque mínimo, defesa mínima).
- **Critérios Compostos (Operadores Lógicos):**
  - Combinam dois ou mais critérios (`CriterioE`, `CriterioOu`) ou invertem um critério (`CriterioNao`).
  - Permite montar expressões como: `(Classe == "Guerreiro") OU (Defesa >= 12)`.

---

## 🗂️ Estrutura do Projeto

```
codigo-inicial/
├── README.md
└── guilda/
    ├── Main.java                          <-- Ponto de entrada (Completar Missões 1 e 2)
    │
    ├── decorator/                         <-- [JÁ PRONTO]
    │   ├── Heroi.java                     (Interface)
    │   ├── HeroiBase.java                 (Componente concreto)
    │   ├── EquipamentoDecorator.java      (Decorator base)
    │   ├── EspadaFlamejante.java          (Decorator concreto: +8 ATK)
    │   ├── EscudoDeCarvalho.java          (Decorator concreto: +5 DEF)
    │   └── ArmaduraPesada.java            (Decorator concreto: -2 ATK, +9 DEF)
    │
    └── filtro/                            <-- [SEU FOCO]
        ├── Criterio.java                  (Interface do filtro - PRONTO)
        ├── CriterioDefesaMinimo.java      (Exemplo pronto de critério simples)
        ├── CriterioOu.java                (Exemplo pronto de critério composto)
        ├── CriterioClasse.java            (TODO: implementar)
        ├── CriterioAtaqueMinimo.java      (TODO: implementar)
        ├── CriterioE.java                 (TODO: implementar)
        └── CriterioNao.java               (TODO: implementar)
```

---

## 📋 O Que Deve Ser Feito (Passo a Passo)

### 🔹 Parte 1: Critérios Simples

1. **`guilda/filtro/CriterioClasse.java`**
   - **Objetivo:** Filtrar apenas os heróis cuja classe seja igual à classe informada no construtor.
   - **Dica:** Lembre-se de usar `equals` ou `equalsIgnoreCase` para comparar Strings (`h.getClasse().equalsIgnoreCase(this.classe)`).
   - **Exemplo de referência:** Veja como `CriterioDefesaMinimo.java` percorre a lista e monta a lista de resultado.

2. **`guilda/filtro/CriterioAtaqueMinimo.java`**
   - **Objetivo:** Filtrar apenas os heróis cujo ataque total seja maior ou igual ao mínimo informado (`h.getAtaque() >= this.minimo`).

---

### 🔹 Parte 2: Critérios Compostos

3. **`guilda/filtro/CriterioE.java`**
   - **Objetivo:** Filtrar heróis que atendem simultaneamente aos critérios `a` **E** `b`.
   - **Dica de Implementação:** Você pode filtrar a lista original com o critério `a`, e passar o resultado filtrado para o critério `b`:
     ```java
     List<Heroi> primeiraFiltragem = a.filtrar(herois);
     return b.filtrar(primeiraFiltragem);
     ```

4. **`guilda/filtro/CriterioNao.java`**
   - **Objetivo:** Inverter um critério (NOT lógico), retornando os heróis da lista original que **NÃO** atendem ao critério interno.
   - **Dica de Implementação:**
     1. Obtenha quem atende ao critério (`List<Heroi> atendem = criterio.filtrar(herois)`).
     2. Itere sobre a lista original `herois` e adicione ao resultado quem **não** está presente em `atendem` (`!atendem.contains(h)`).

---

### 🔹 Parte 3: Composição das Missões no `Main.java`

No arquivo `guilda/Main.java`, substitua os `TODO` pelas instâncias compostas de critérios:

5. **Missão 1 - Vanguarda:**
   - **Regra:** Heróis com ataque total >= 15 **E** defesa total >= 10.
   - **Composição:** Usar `CriterioE` combinando `CriterioAtaqueMinimo(15)` e `CriterioDefesaMinimo(10)`.
   - **Esperado:** `Thorin, Brunhilda, Elara`

6. **Missão 2 - Sem Conjuradores:**
   - **Regra:** Heróis aprovados na `vanguarda` **E** que **NÃO** sejam da classe `"Mago"`.
   - **Composição:** Usar `CriterioE` combinando `vanguarda` e `new CriterioNao(new CriterioClasse("Mago"))`.
   - **Esperado:** `Thorin, Brunhilda`

*(A Missão 3 já está implementada no `Main.java` como exemplo de uso do `CriterioOu` e passará a funcionar automaticamente assim que você concluir a Parte 1!)*

---

## 🛠️ Como Compilar e Executar

Abra o terminal dentro da pasta `java/pas/codigo-inicial` e execute:

### No Linux / macOS:
```bash
javac -d out $(find . -name "*.java")
java -cp out guilda.Main
```

### No Windows (PowerShell):
```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out guilda.Main
```

---

## 🎯 Saída Final Esperada no Console

Quando todos os critérios e missões estiverem implementados corretamente, a execução produzirá:

```text
=== Heróis equipados (Decorator pronto) ===
Thorin (Guerreiro) ATK 18 | DEF 11
Merlin (Mago) ATK 22 | DEF 3
Brunhilda (Guerreiro) ATK 20 | DEF 15
Lyra (Arqueiro) ATK 21 | DEF 4
Gorak (Guerreiro) ATK 9 | DEF 7
Elara (Mago) ATK 17 | DEF 10

=== PARTE 1: critérios simples ===
Guerreiros: Thorin, Brunhilda, Gorak
Ataque >= 15: Thorin, Merlin, Brunhilda, Lyra, Elara

=== PARTE 2: missões ===
Missão 1 - Vanguarda: Thorin, Brunhilda, Elara
Missão 2 - Sem conjuradores: Thorin, Brunhilda
Missão 3 - Bônus: Thorin, Brunhilda, Gorak
```

---

## 💡 Dicas Importantes
- **Nunca altere a lista recebida por parâmetro:** Sempre crie uma `new ArrayList<>()` para preencher e retornar.
- **Preserve a ordem original:** Mantenha os heróis na mesma ordem em que aparecem na lista de entrada.
- **Consulte os exemplos prontos:** Sempre que tiver dúvida sobre como estruturar a classe, olhe [CriterioDefesaMinimo.java](file:///home/s-cesar/codes/pratical_study/java/pas/codigo-inicial/guilda/filtro/CriterioDefesaMinimo.java) e [CriterioOu.java](file:///home/s-cesar/codes/pratical_study/java/pas/codigo-inicial/guilda/filtro/CriterioOu.java).