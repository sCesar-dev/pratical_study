package guilda.decorator;

/** Equipamento (PRONTO): +7 de defesa e -2 de ataque. */
public class ArmaduraPesada extends EquipamentoDecorator {
    public ArmaduraPesada(Heroi heroi) {
        super(heroi);
    }

    @Override
    public int getAtaque() {
        return super.getAtaque() - 2;
    }

    @Override
    public int getDefesa() {
        return super.getDefesa() + 7;
    }
}
