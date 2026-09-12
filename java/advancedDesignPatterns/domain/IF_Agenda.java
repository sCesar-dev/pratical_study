import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface IF_Agenda {
    IF_Contato getContato(String telefone);
    boolean adicionaContato(IF_Contato contato);
    boolean removeContato(String telefone);
    Collection<IF_Contato> getListaAgenda();
}

class AgendaList implements IF_Agenda {
    private List<IF_Contato> listaContato = new ArrayList<>();
    
    private int localizaContato(String telefone) {
        IF_Contato contato = new Contato();
        contato.setTelefone(telefone);
        return listaContato.indexOf(contato);
    }

    @Override
    public IF_Contato getContato(String telefone) {
        int index = localizaContato(telefone);
        // if (index != -1) {
        //     return listaContato.get(index);
        // }
        // return null;
        return (localizaContato(telefone) != -1) ? listaContato.get(index) : null;
    }

    @Override
    public boolean adicionaContato(IF_Contato contato) {
        int index = localizaContato(contato.getTelefone());
        if (index == -1) {
            listaContato.add(contato);
            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean removeContato(String telefone) {
        int index = localizaContato(telefone);
        if (index != -1) {
            listaContato.remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        // ArrayList<String> dados = new ArrayList<>();
        // for (int i = 0; i < listaContato.size(); i++) {
        //     dados.add(listaContato.get(i).toString() + "\n");
        // }
        String resultado = "";
        Iterator<IF_Contato> it = listaContato.iterator();
        while (it.hasNext()) {
            IF_Contato contato = it.next();
            resultado += contato.getNome() + contato.getTelefone() + "\n";
        }
        return resultado;
    }

    @Override
    public Collection<IF_Contato> getListaAgenda() {
        return listaContato;
    }

    public static void main(String[] args) {
        AgendaList agenda = new AgendaList();
        IF_Contato[] contatos = new IF_Contato[3];

        contatos[0] = new Contato();
        contatos[0].setNome("A");
        contatos[0].setTelefone("345-2455");
        System.out.println("Adicionando " + contatos[0]);
        agenda.adicionaContato(contatos[0]);
        
        contatos[1] = new Contato();    
        contatos[1].setNome("X");
        contatos[1].setTelefone("234-9085");
        System.out.println("Adicionando " + contatos[1]);
        agenda.adicionaContato(contatos[1]);

        contatos[2] = new Contato();
        contatos[2].setNome("Y");
        contatos[2].setTelefone("8890-19085");
        System.out.println("Adicionando " + contatos[2]);
        agenda.adicionaContato(contatos[2]);

        System.out.println(agenda);
        System.out.println("Localizando 345-2455");
        System.out.println(agenda.getContato("345-2455"));
        System.out.println("Removendo 234-9085");
        agenda.removeContato("234-9085");
        System.out.println(agenda);
    }
}
