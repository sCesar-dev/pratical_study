package guilda.decorator;

/** Equipamento (PRONTO): +5 de defesa. */
public class EscudoDeCarvalho extends EquipamentoDecorator {
    public EscudoDeCarvalho(Heroi heroi) {
        super(heroi);
    }

    @Override
    public int getDefesa() {
        return super.getDefesa() + 5;
    }
}
