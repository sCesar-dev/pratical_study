package listarevisaoprova;
/*
 * Created on 09/06/2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Administrador
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Produto {
	private String codigo;
	private String descricao;
	private double preco;

	/**
	 * @return Returns the preco.
	 */
	public double getPreco() {
		return preco;
	}
	/**
	 * @param preco The preco to set.
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString(){
		return "C�digo:"+this.codigo+"Descri��o:"+this.descricao;
	}
}
