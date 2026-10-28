import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// --- 1. MEMENTO, CONTEXTO E CARETAKER ---

class TurtleMemento {
    private final double x;
    private final double y;
    private final double heading;
    private final boolean penDown;

    public TurtleMemento(double x, double y, double heading, boolean penDown) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.penDown = penDown;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getHeading() { return heading; }
    public boolean isPenDown() { return penDown; }
}

/** Representa um segmento de linha desenhado na tela */
class LineSegment {
    double x1, y1, x2, y2;
    boolean visible;

    public LineSegment(double x1, double y1, double x2, double y2, boolean visible) {
        this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
        this.visible = visible;
    }
}

/** O Painel Gráfico que simula a tela do Turtle em Java */
class TurtleCanvas extends JPanel {
    private final List<LineSegment> segments = new ArrayList<>();
    public double turtleX = 0;
    public double turtleY = 0;
    public double turtleHeading = 0; // em graus

    // 'synchronized' evita conflito de concorrência com a Thread de animação
    public synchronized void addSegment(double x1, double y1, double x2, double y2, boolean visible) {
        segments.add(new LineSegment(x1, y1, x2, y2, visible));
        repaint(); // Atualiza a tela visualmente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Centraliza o plano cartesiano no meio da janela
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        // Desenha as linhas acumuladas
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        synchronized (this) {
            for (LineSegment seg : segments) {
                if (seg.visible) {
                    g2d.drawLine(cx + (int)seg.x1, cy - (int)seg.y1, cx + (int)seg.x2, cy - (int)seg.y2);
                }
            }
        }

        // Desenha a tartaruga (seta indicando a direção)
        int tx = cx + (int) turtleX;
        int ty = cy - (int) turtleY;
        
        AffineTransform old = g2d.getTransform();
        g2d.translate(tx, ty);
        g2d.rotate(Math.toRadians(-turtleHeading));
        
        // Desenho da seta apontando para a frente (direção do eixo X / 0°)
        g2d.setColor(Color.RED);
        int[] xPoints = {12, -8, -8};
        int[] yPoints = {0, -7, 7};
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setTransform(old);
    }
}

class TurtleContext {
    private double x = 0.0;
    private double y = 0.0;
    private double heading = 0.0; 
    private boolean penDown = true;
    private final TurtleCanvas canvas;

    public TurtleContext(TurtleCanvas canvas) {
        this.canvas = canvas;
        this.canvas.turtleX = this.x;
        this.canvas.turtleY = this.y;
        this.canvas.turtleHeading = this.heading;
    }

    /**
     * Avança a seta passo a passo (interpolação) para criar a animação do traçado
     */
    public void forward(double steps) {
        double rad = Math.toRadians(heading);
        double dx = Math.cos(rad);
        double dy = Math.sin(rad);

        double stepIncrement = 2.0; // avança de 2 em 2 pixels
        double moved = 0.0;

        while (moved < steps) {
            double currentStep = Math.min(stepIncrement, steps - moved);
            double nextX = this.x + currentStep * dx;
            double nextY = this.y + currentStep * dy;

            canvas.addSegment(this.x, this.y, nextX, nextY, penDown);
            
            this.x = nextX;
            this.y = nextY;
            canvas.turtleX = this.x;
            canvas.turtleY = this.y;
            moved += currentStep;

            try {
                Thread.sleep(15); // Pausa a cada 2 pixels (ajusta a velocidade do traço)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void right(double degrees) {
        this.heading = (this.heading + degrees) % 360;
        canvas.turtleHeading = this.heading;
        canvas.repaint();
        // Pausa para dar tempo de visualizar a seta mudando de rotação
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void setPenDown(boolean status) {
        this.penDown = status;
    }

    public TurtleMemento saveToMemento() {
        return new TurtleMemento(this.x, this.y, this.heading, this.penDown);
    }

    public void restoreFromMemento(TurtleMemento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
        this.heading = memento.getHeading();
        this.penDown = memento.isPenDown();
        
        canvas.turtleX = this.x;
        canvas.turtleY = this.y;
        canvas.turtleHeading = this.heading;
        canvas.repaint();
        // Pausa para ver a seta voltando ao ponto salvo pelo Memento
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Caretaker {
    private TurtleMemento memento;
    public void setMemento(TurtleMemento memento) { this.memento = memento; }
    public TurtleMemento getMemento() { return memento; }
}


// --- 2. EXPRESSÃO ABSTRATA ---
interface Command {
    void interpret(TurtleContext context);
}


// --- 3. EXPRESSÕES CONCRETAS ---
class FrenteCommand implements Command {
    private final double steps;
    public FrenteCommand(double steps) { this.steps = steps; }
    @Override public void interpret(TurtleContext context) { context.forward(steps); }
}

class EsquerdaCommand implements Command {
    private final double degrees;
    public EsquerdaCommand(double degrees) { this.degrees = degrees; }
    @Override public void interpret(TurtleContext context) { context.right(degrees); }
}

class CanetaCommand implements Command {
    private final boolean status;
    public CanetaCommand(boolean status) { this.status = status; }
    @Override public void interpret(TurtleContext context) { context.setPenDown(status); }
}

class SalvarPosicaoCommand implements Command {
    private final Caretaker caretaker;
    public SalvarPosicaoCommand(Caretaker caretaker) { this.caretaker = caretaker; }
    @Override public void interpret(TurtleContext context) {
        caretaker.setMemento(context.saveToMemento());
    }
}


// --- 4. EXPRESSÃO NÃO-TERMINAL ---
class SequenceCommand implements Command {
    private final List<Command> commands;
    public SequenceCommand(List<Command> commands) { this.commands = commands; }
    @Override public void interpret(TurtleContext context) {
        for (Command cmd : commands) { cmd.interpret(context); }
    }
}


// --- 5. TESTANDO A JANELA E O DESENHO EM THREAD ---
public class Exercicio {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini-Turtle Gráfica com Memento - Java");
        TurtleCanvas canvas = new TurtleCanvas();
        frame.add(canvas);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Caretaker caretaker = new Caretaker();

        // Executamos a lógica de comandos em uma Thread separada para não travar a interface gráfica
        new Thread(() -> {
            // Pausa inicial para esperar a janela Swing carregar e abrir completamente
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                return;
            }

            // --- 1ª Execução ---
            TurtleContext ctx1 = new TurtleContext(canvas);
            Command scriptAst1 = new SequenceCommand(List.of(
                new CanetaCommand(true), //liga ou desliga o rastro que a seta vai deixar
                new EsquerdaCommand(90),
                new EsquerdaCommand(90),
                new FrenteCommand(100), //movimenta para a frente (o parametro é o quanto ele vai andar)
                new EsquerdaCommand(90), //ele muda a direção da seta, sem andar, apenas gira pra a direita(parametro padrão 90º)
                new FrenteCommand(100),
                new EsquerdaCommand(90),
                new FrenteCommand(100),
                new EsquerdaCommand(90),
                new SalvarPosicaoCommand(caretaker), // Salva o estado da seta atual (quando rodar de novo ele vai começar daqui)
                new FrenteCommand(100)
            ));
            scriptAst1.interpret(ctx1);

            try { Thread.sleep(1000); } catch (InterruptedException e) {}

            // --- 2ª Execução (Retomando do Caretaker) ---
            TurtleMemento mementoSalvo = caretaker.getMemento();
            if (mementoSalvo != null) {
                TurtleContext ctx2 = new TurtleContext(canvas);
                ctx2.restoreFromMemento(mementoSalvo); // Restaura posição e ângulo salvos
                
                //    ####################################################################################################################
                //    #OBJETIVO: GERAR UM QUADRADO AO LADO DIREITO DO PRIMEIRO, COMEÇANDO DO PONTO INFERIOR DIREITO DO PRIMEIRO QUADRADO 
                //    ####################################################################################################################
                Command scriptAst2 = new SequenceCommand(List.of(
                    new CanetaCommand(true),
                    new EsquerdaCommand(90),
                    new FrenteCommand(100),
                    new EsquerdaCommand(90),
                    new FrenteCommand(50),
                    new EsquerdaCommand(90),
                    new FrenteCommand(50)
                ));
                scriptAst2.interpret(ctx2);
            }
        }).start();
    }
}