package guilda.decorator;

/** Equipamento de exemplo (PRONTO): +8 de ataque. Use como modelo. */
public class EspadaFlamejante extends EquipamentoDecorator {
    public EspadaFlamejante(Heroi heroi) {
        super(heroi);
    }

    @Override
    public int getAtaque() {
        return super.getAtaque() + 8;
    }
}
