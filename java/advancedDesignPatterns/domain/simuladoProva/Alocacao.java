package simuladoProva;

import listarevisaoprova.Estoque;

/*
3. Classe Alocacao (A Classe Associativa)

Faz o papel de ligação, similar ao Estoque.java do seu professor.

    Atributos (privados): Equipamento equipamento, Pesquisador pesquisador, int quantidade.

    Métodos:

        Getters e Setters para todos os atributos.

        Obrigatório: Sobrescrever o método equals(Object o). O método deve fazer o casting de Object para Alocacao e retornar true apenas se o código do equipamento e a matrícula do pesquisador forem iguais aos do objeto comparado.
         */
public class Alocacao {
    private Equipamento equipamento;
    private Pesquisador pesquisador;
    private int quantidade;

    
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean equals(Object o) {
        Alocacao e = (Alocacao)o;
        return this.getEquipamento().getCodigo().equals(e.getEquipamento().getCodigo())&&
        this.getPesquisador().getMatricula().equals(e.getPesquisador().getMatricula());
    }

}
