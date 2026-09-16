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
public class Estoque {
	private Produto produto;
	private Fornecedor fornecedor;
	private int quantidade;

	/**
	 * @return Returns the fornecedor.
	 */
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	/**
	 * @param fornecedor The fornecedor to set.
	 */
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	/**
	 * @return Returns the produto.
	 */
	public Produto getProduto() {
		return produto;
	}
	/**
	 * @param produto The produto to set.
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	/**
	 * @return Returns the quantidade.
	 */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade The quantidade to set.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public boolean equals(Object o){
		Estoque e = (Estoque)o;
		return this.getFornecedor().getCodigo().equals(e.getFornecedor().getCodigo())&&
		this.getProduto().getCodigo().equals(e.getProduto().getCodigo());
	}
}
