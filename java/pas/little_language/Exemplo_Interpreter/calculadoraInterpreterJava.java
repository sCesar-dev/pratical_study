import java.util.HashMap;
import java.util.Map;

// ==========================================
// 1. CONTEXTO (Context)
// ==========================================
// Armazena informações globais necessárias para a interpretação,
// como o valor atual das variáveis da nossa linguagem.
class Context {
    private Map<String, Integer> variables = new HashMap<>();

    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    public int getVariable(String name) {
        return variables.getOrDefault(name, 0);
    }
}

// ==========================================
// 2. EXPRESSÃO ABSTRATA (AbstractExpression)
// ==========================================
// Declara a interface comum para todos os nós da árvore.
interface Expression {
    int interpret(Context context);
}

// ==========================================
// 3. EXPRESSÕES TERMINAIS (TerminalExpression)
// ==========================================
// Representam os elementos folha da árvore (números fixos ou variáveis).

class NumberExpression implements Expression {
    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret(Context context) {
        return value;
    }
}

class VariableExpression implements Expression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Context context) {
        return context.getVariable(name);
    }
}

// ==========================================
// 4. EXPRESSÕES NÃO-TERMINAIS (NonterminalExpression)
// ==========================================
// Representam as operações compostas (como soma e subtração) e contêm filhos.

class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        // Resolve recursivamente a esquerda e a direita, depois soma
        return left.interpret(context) + right.interpret(context);
    }
}

class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        // Resolve recursivamente a esquerda e a direita, depois subtrai
        return left.interpret(context) - right.interpret(context);
    }
}

// ==========================================
// 5. CLIENTE E EXECUÇÃO
// ==========================================
class Main {
    public static void main(String[] args) {
        // Passo A: Configurar o Contexto com valores para as variáveis
        Context context = new Context();
        context.setVariable("x", 10);
        context.setVariable("y", 5); // Em Java, ajustado para setVariable padrão camelCase

        // Passo B: Construir a Árvore Sintática Abstrata (AST)
        // Queremos calcular a expressão matemática: (x + 5) - (y + 2)
        //
        // Estrutura da Árvore:
        //           Subtract
        //          /        \
        //        Add        Add
        //       /   \      /   \
        //      x     5    y     2

        Expression ast = new SubtractExpression(
            new AddExpression(new VariableExpression("x"), new NumberExpression(5)),
            new AddExpression(new VariableExpression("y"), new NumberExpression(2))
        );

        // Passo C: Interpretar/Executar a árvore usando o contexto
        int resultado = ast.interpret(context);

        System.out.println("Expressão avaliada: (x + 5) - (y + 2)");
        System.out.println("Com x = 10 e y = 5");
        System.out.println("Resultado final: " + resultado); // Esperado: (10 + 5) - (5 + 2) = 15 - 7 = 8
    }
}