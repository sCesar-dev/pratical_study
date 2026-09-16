package simuladoProva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*

4. Classe GerenciadorLab (A Classe Principal de Controle)

Faz o papel de Compras.java.

    Atributos (privados e inicializados):

        Map cEquipamento = new HashMap();

        Map cPesquisador = new HashMap();

        List cAlocacao = new ArrayList();

    Métodos (Retorno e Assinaturas Exatas):

        public boolean adicionaEquipamento(Equipamento e): Verifica se o Map já contém a chave (código). Se não, insere no mapa e retorna true. Senão, retorna false.

        public boolean adicionaPesquisador(Pesquisador p): Mesma lógica, utilizando a matrícula como chave.

        public void emprestar(String codEquipamento, String matPesquisador, int qtd):

            Busca o equipamento e o pesquisador nos mapas (lembre-se de fazer o casting!).

            Cria uma nova instância de Alocacao.

            Se a lista cAlocacao já contiver (contains()) essa alocação (mesmo pesquisador e mesmo equipamento), você deve resgatar o objeto existente através do indexOf() e apenas somar a qtd.

            Caso contrário, adicione a nova alocação na lista.

        public String totalValorPorEquipamento(): Usa um Iterator no .values() do mapa de equipamentos. Para cada equipamento, percorre a lista de alocações (com outro Iterator) e soma o valor (preço * quantidade) de todas as alocações daquele equipamento específico. Retorna uma String formatada.

        public String pesquisadoresEItens(): Usa um Iterator no mapa de pesquisadores e, para cada um, lista quais equipamentos ele pegou emprestado buscando na lista de alocações.
        
*/
public class GerenciadorLab {
    private Map cEquipamento = new HashMap();
    private Map cPesquisador = new HashMap();
    private List cAlocacao = new ArrayList();

    public boolean adicionaEquipamento(Equipamento e) {
        if (!this.cEquipamento.containsKey(e.getCodigo())) {
            this.cEquipamento.put(e.getCodigo(), e);
            return true;
        }
        return false;
    }

    public boolean adicionaPesquisador(Pesquisador c) {
        if(!cPesquisador.containsKey(c.getMatricula())) {
            this.cPesquisador.put(c.getMatricula(), c);
            return true;
        }
        return false;
    }

    public void emprestar(String codEquipamento, String matPesquisador, int qtd) {
        Equipamento e = (Equipamento)this.cEquipamento.get(codEquipamento);
        Pesquisador p = (Pesquisador)this.cPesquisador.get(matPesquisador);
        Alocacao a = new Alocacao();
        a.setEquipamento(e);
        a.setPesquisador(p);

        if (this.cAlocacao.contains(a)) {
            int i = this.cAlocacao.indexOf(a);
            Alocacao a1 = (Alocacao)this.cAlocacao.get(i);
            a1.setQuantidade(a1.getQuantidade()+qtd);
        } else {
            a.setQuantidade(qtd);
            this.cAlocacao.add(e);
        }
    }

    public void totalValorPorEquipamento() {
        Iterator it = this.cEquipamento.values().iterator();
        String temp = "Total Equipamento \n";

        while(it.hasNext()) {
            Equipamento e = (Equipamento)it.next();
            temp = temp+"Produto: " + e.getNome();
            Iterator it1 = this.cAlocacao.iterator();
            double total = 0;
            while (it1.hasNext()) {
                Alocacao a = (Alocacao)it1.next();

                if(a.getEquipamento().getCodigo().equals(e.getCodigo())) {
                    total += a.getEquipamento().getValor() * a.getQuantidade();
                }
            }
            temp += "Total:"+total+"\n";
        }
    }
}
