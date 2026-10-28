# 🐢 Little Language (DSL) & Padrões de Projeto: Interpreter + Memento

Este módulo da disciplina de **Padrões de Arquitetura de Software (PAS)** explora o conceito de **Little Languages** (Linguagens de Domínio Específico — DSL) através da implementação prática de dois importantes padrões de projeto comportamentais do GoF:
- **Interpreter Pattern** (para representação gramatical e execução de comandos/expressões)
- **Memento Pattern** (para captura, snapshot e restauração de estado sem violação de encapsulamento)

---

## 🗂️ Estrutura do Diretório

```
little_language/
├── README.md                          <-- Guia completo do módulo
│
├── Exemplo_Interpreter/               <-- Exemplo introdutório do padrão Interpreter
│   └── calculadoraInterpreterJava.java (Avaliador de expressões matemáticas via AST)
│
└── trabalhoLANG/                      <-- Atividade prática (Turtle Graphics + Memento)
    └── Exercicio.java                 (Mini-linguagem Logo com tartaruga animada em Swing)
```

---

## 📚 Conceitos e Padrões de Projeto Utilizados

### 1. O que é uma *Little Language* (DSL)?
Uma **Little Language** (pequena linguagem) é uma linguagem enxuta criada especificamente para resolver problemas em um domínio delimitado (Domain-Specific Language). Em vez de programar fluxos complexos diretamente na linguagem hospedeira (Java), criamos uma sintaxe ou estrutura de comandos declarativa (ex.: "ande 100", "gire 90°", "baixe a caneta", "some X e Y").

---

### 2. Padrão Interpreter (GoF - Comportamental)

#### O que é e qual problema resolve?
O **Interpreter** define uma representação gramatical para uma linguagem juntamente com um interpretador que usa essa representação para interpretar sentenças da linguagem.

Ele modela a gramática da linguagem como uma **Árvore Sintática Abstrata (AST - Abstract Syntax Tree)**, onde cada nó da árvore é uma instância de uma classe que implementa uma interface comum com o método `interpret(Context)`.

#### Papéis do Padrão Interpreter:
| Papel GoF | Na Calculadora (`Exemplo_Interpreter`) | Na Tartaruga (`trabalhoLANG`) | Função |
| :--- | :--- | :--- | :--- |
| **Context** | `Context` (armazena variáveis `Map<String, Integer>`) | `TurtleContext` (armazena `x`, `y`, `heading`, `penDown`) | Guarda o estado global e o ambiente de execução da linguagem. |
| **AbstractExpression** | `Expression` (`int interpret(Context)`) | `Command` (`void interpret(TurtleContext)`) | Define a interface comum para todos os nós da árvore gramatical. |
| **TerminalExpression** | `NumberExpression`, `VariableExpression` | `FrenteCommand`, `EsquerdaCommand`, `CanetaCommand`, `SalvarPosicaoCommand` | Folhas da árvore: operações atômicas que não possuem nós filhos. |
| **NonterminalExpression** | `AddExpression`, `SubtractExpression` | `SequenceCommand` (contém uma lista de `Command`) | Nós compostos: combinam uma ou mais subexpressões/comandos. |
| **Client / AST** | `Main` montando `(x + 5) - (y + 2)` | `Exercicio` montando `scriptAst1` e `scriptAst2` | Constrói a árvore de nós e dispara a execução via `interpret()`. |

---

### 3. Padrão Memento (GoF - Comportamental)

#### O que é e por que é usado no `trabalhoLANG`?
O **Memento** permite capturar e externalizar o estado interno de um objeto (snapshot) para que ele possa ser restaurado posteriormente, **sem violar o princípio de encapsulamento** (sem expor as variáveis privadas do objeto para quem gerencia o histórico).

#### Papéis do Padrão Memento no `Exercicio.java`:
- **Originator (`TurtleContext`):** É o objeto cujo estado precisa ser salvo. Ele cria o memento através de `saveToMemento()` e sabe como restaurar seu estado interno recebendo um memento em `restoreFromMemento(TurtleMemento)`.
- **Memento (`TurtleMemento`):** Objeto imutável que guarda os dados de estado da tartaruga (`x`, `y`, `heading`, `penDown`). Ninguém de fora deve modificar este objeto.
- **Caretaker (`Caretaker`):** Responsável por guardar a referência do memento (`setMemento()` e `getMemento()`), funcionando como um repositório temporário ou pilha de checkpoints.

---

## 🎨 Detalhes da Atividade: Turtle Graphics (`trabalhoLANG`)

O arquivo [trabalhoLANG/Exercicio.java](file:///home/s-cesar/codes/pratical_study/java/pas/little_language/trabalhoLANG/Exercicio.java) implementa uma versão interativa da famosa **Turtle Graphics** (popularizada pela linguagem Logo) com interface visual em Java Swing.

### 🧭 Sistema de Coordenadas e Geometria da Tartaruga
O plano cartesiano é centralizado no meio da janela Swing `(cx, cy)`:
- Posição inicial: `x = 0, y = 0` (centro da tela).
- Eixo horizontal: `+X` para a **Direita**, `-X` para a **Esquerda**.
- Eixo vertical: `+Y` para **Cima**, `-Y` para **Baixo**.
- Orientação angular (`heading`):
  - `0°`: apontando para a **Direita** (`+X`)
  - `90°`: apontando para **Cima** (`+Y`)
  - `180°`: apontando para a **Esquerda** (`-X`)
  - `270°`: apontando para **Baixo** (`-Y`)
- Rotação: o comando `EsquerdaCommand(degrees)` incrementa o `heading` em sentido anti-horário (`(heading + degrees) % 360`).

---

### 📜 Comandos Disponíveis na DSL
| Comando | O que faz |
| :--- | :--- |
| `new FrenteCommand(steps)` | Move a tartaruga para a frente na direção atual por `steps` pixels (com animação passo a passo). |
| `new EsquerdaCommand(degrees)` | Gira a tartaruga no sentido anti-horário pelo número de graus informado (ex.: `90`). |
| `new CanetaCommand(boolean)` | Se `true`, a caneta desenha linhas pretas ao se mover. Se `false`, a tartaruga se move sem desenhar. |
| `new SalvarPosicaoCommand(caretaker)` | Captura o estado atual da tartaruga (`x`, `y`, `heading`, `penDown`) e armazena no `caretaker`. |
| `new SequenceCommand(List<Command>)` | Executa uma lista ordenada de comandos em sequência. |

---

### 🔍 O que o Script 1 (`scriptAst1`) faz:
1. Inicia em `(0, 0)` com `heading = 0°` (Direita).
2. Liga a caneta (`CanetaCommand(true)`).
3. Gira 90° e depois mais 90° -> `heading = 180°` (apontando para a Esquerda).
4. Anda 100 pixels -> vai de `(0, 0)` para `(-100, 0)`.
5. Gira 90° -> `heading = 270°` (apontando para Baixo).
6. Anda 100 pixels -> vai de `(-100, 0)` para `(-100, -100)`.
7. Gira 90° -> `heading = 0°` (apontando para a Direita).
8. Anda 100 pixels -> vai de `(-100, -100)` para `(0, -100)` (**canto inferior direito do 1º quadrado**).
9. Gira 90° -> `heading = 90°` (apontando para Cima).
10. **`SalvarPosicaoCommand(caretaker)`**: Salva o Memento nesta posição exata: `(0, -100)` com `heading = 90°`.
11. Anda 100 pixels -> vai de `(0, -100)` para `(0, 0)`, fechando o 1º quadrado.

```text
       (-100, 0) ─────── (0, 0) [Início do Script 1]
           │                │
           │  1º QUADRADO   │
           │                │
     (-100, -100) ────── (0, -100) [MEMENTO SALVO AQUI: apontando para CIMA]
```

---

### 🎯 O Desafio / Objetivo do Exercício:
No final do arquivo `Exercicio.java`:
```java
// ####################################################################################################################
// #OBJETIVO: GERAR UM QUADRADO AO LADO DIREITO DO PRIMEIRO, COMEÇANDO DO PONTO INFERIOR DIREITO DO PRIMEIRO QUADRADO 
// ####################################################################################################################
```

A tartaruga é restaurada do Memento:
- Posição restaurada: `x = 0, y = -100`
- Orientação: `heading = 90°` (apontando para **CIMA**).
- O 1º quadrado ocupa o espaço de `X: [-100, 0]` e `Y: [-100, 0]`.
- O 2º quadrado deve ficar ao lado direito do primeiro, ocupando o espaço de `X: [0, 100]` e `Y: [-100, 0]`.

```text
       (-100, 0) ─────── (0, 0) ─────── (100, 0)
           │                │                │
           │  1º QUADRADO   │  2º QUADRADO   │
           │                │                │
     (-100, -100) ────── (0, -100) ──── (100, -100)
                         [Ponto de partida do Script 2]
```

#### Dica para montar o `scriptAst2`:
Partindo de `(0, -100)` apontando para Cima (`heading = 90°`):
- Se subir 100 pixels (`FrenteCommand(100)`), ela percorre a linha divisória até `(0, 0)`.
- Como `EsquerdaCommand` gira no sentido anti-horário, girar 270° no sentido anti-horário equivale a girar 90° para a direita (ou você pode encadear giros de 90°).
- Ao contornar os vértices `(0, 0) -> (100, 0) -> (100, -100) -> (0, -100)`, o segundo quadrado estará perfeitamente desenhado e fechado ao lado direito do primeiro!

---

## 🛠️ Como Compilar e Executar

Abra o terminal na pasta correspondente:

### 1. Executando o Exemplo da Calculadora (`Exemplo_Interpreter`):
```bash
cd /home/s-cesar/codes/pratical_study/java/pas/little_language/Exemplo_Interpreter
javac calculadoraInterpreterJava.java
java Main
```
**Saída esperada no console:**
```text
Expressão avaliada: (x + 5) - (y + 2)
Com x = 10 e y = 5
Resultado final: 8
```

---

### 2. Executando a Tartaruga Gráfica (`trabalhoLANG`):
```bash
cd /home/s-cesar/codes/pratical_study/java/pas/little_language/trabalhoLANG
javac Exercicio.java
java Exercicio
```
Uma janela gráfica Swing de `600x600` se abrirá, animando o traçado da tartaruga em tempo real e executando o Script 1, a restauração pelo Memento e em seguida o Script 2!

---

## 💡 Boas Práticas e Arquitetura
1. **Thread Separada para Animação:** Os comandos rodam em uma `Thread` à parte de propósito para que o `Thread.sleep` (efeito de animação) não congele a interface gráfica do Swing (EDT - Event Dispatch Thread).
2. **Sincronização (`synchronized`):** O método `canvas.addSegment(...)` e o `paintComponent` são sincronizados para evitar `ConcurrentModificationException` na lista de segmentos enquanto a tela está sendo redesenhada.
3. **Encapsulamento do Memento:** O `TurtleMemento` não possui setters, apenas getters para valores primitivos, garantindo a integridade dos dados históricos salvos.
