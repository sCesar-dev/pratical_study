package pas;

public class TesteObserverEstoque {
    public static void main(String[] args) {
        AbstractSubject subject = new Estoque(10);
        IF_Observer observerPapel = new Produto("Papel", 10, 10, subject);
        IF_Observer observerCaneta = new Produto("Caneta", 20, 10, subject);
        subject.attach(observerPapel);
        subject.attach(observerCaneta);
        ((Estoque) subject).incrementaEstoque(5);
        System.out.println(observerPapel.toString());
        System.out.println(observerCaneta.toString());
        ((Estoque) subject).incrementaEstoque(10);
        System.out.println(observerPapel.toString());
        System.out.println(observerCaneta.toString());
    }
}
