import java.util.Objects;

public interface IF_Contato {
String getNome();
String getTelefone();

void setNome(String nome);
void setTelefone(String telefone);
}
class Contato implements IF_Contato{

private String nome;
private String telefone;

@Override
public String getNome() {
    return this.nome;
}

@Override
public String getTelefone() {
    return this.telefone;
}

@Override
public void setNome(String nome) {
    this.nome = nome;
}

@Override
public void setTelefone(String telefone) {
    this.telefone = telefone;
}

@Override
public boolean equals(Object o) {
    if (o == null || o.getClass() != getClass()) {
        return false;
    }
    if (this == o) {
        return true;
    }
    Contato outroContato = (Contato) o;
    if (this.telefone == null) {
        return outroContato.telefone == null;
    }
    return this.telefone.equals(outroContato.telefone);
}

@Override
public int hashCode() {
    return Objects.hash(telefone);
}

@Override
public String toString() {
    return "Nome: " + this.nome + ", Telefone: " + this.telefone;
}
}

class MainTesteContato {
public static void main(String[] args) {
    IF_Contato c = new Contato();
    c.setNome("Eduardo");
    c.setTelefone("71999999999");
    System.out.println(c);
}
}