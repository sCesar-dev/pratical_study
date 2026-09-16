package listarevisaoprova;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class Compras {
	
	private Map cProduto = new HashMap();
	private Map cFornecedor = new HashMap();
	private List cEstoque = new ArrayList();
	
	public boolean adicionaProduto(Produto p){
		
		if (!this.cProduto.containsKey(p.getCodigo())){
			this.cProduto.put(p.getCodigo(),p);
			return true;
		}else{
			return false;
		}
		
	}
	public boolean adicionaFornecedor(Fornecedor f){
		
		if (!this.cFornecedor.containsKey(f.getCodigo())){
			this.cFornecedor.put(f.getCodigo(),f);
			return true;
		}else{
			return false;
		}
		
	}
	
	public void compra(String codigoProduto, String codigoFornecedor,int qtd){
		   Produto p = (Produto)this.cProduto.get(codigoProduto);
		   Fornecedor f = (Fornecedor)this.cFornecedor.get(codigoFornecedor);
		   Estoque e = new Estoque();
		   e.setFornecedor(f);
		   e.setProduto(p);
		   
		   if (this.cEstoque.contains(e)){
		   		int i = this.cEstoque.indexOf(e);
		   		Estoque e1 = (Estoque)this.cEstoque.get(i);
		   		e1.setQuantidade(e1.getQuantidade()+qtd);
		   }else{
		   	   e.setQuantidade(qtd);
		   	   this.cEstoque.add(e);
		   	
		   }
		
	}
	
	
	
	public String totalPorProduto(){
		Iterator it = this.cProduto.values().iterator();
		String temp = "Total Produto \n";

		while(it.hasNext()){
			 Produto p = (Produto)it.next();
			 temp=temp+"Produto:"+p.getDescricao()+"\n";
			 Iterator it1 = this.cEstoque.iterator();
			 double total=0;
			 while (it1.hasNext()){
			 	Estoque e =(Estoque)it1.next();
			 	
			 	if (e.getProduto().getCodigo().equals(p.getCodigo())){
			 	    total = total +e.getProduto().getPreco()*e.getQuantidade();
			 	   
			 	}
			 	
			 }
			 temp=temp+"Total:"+total+"\n";
		}
		return temp;
		
	}
	public String fornecedorProdutos(){
		Iterator it = this.cFornecedor.values().iterator();
		String temp = "Lista de Fornecedores e seus produtos \n";
		while(it.hasNext()){
			 Fornecedor f = (Fornecedor)it.next();
			 temp=temp+"Fornecedor:"+f.getDescricao()+"\n";
			 Iterator it1 = this.cEstoque.iterator();
			 while (it1.hasNext()){
			 	Estoque e =(Estoque)it1.next();
			 	if (e.getFornecedor().getCodigo().equals(f.getCodigo())){
			 		temp=temp+"Produto:"+e.getProduto().getDescricao()+"\n";
			 	}
			 }
			 
			 
			
			
		}
		return temp;
		
	}
public static void main(String args[]){
	Compras c = new Compras();
	
	Produto[] p = new Produto[3];
	p[0]= new Produto();
	p[0].setCodigo("P-1");
	p[0].setDescricao("CD");
	p[0].setPreco(1.20);
	c.adicionaProduto(p[0]);
	
	p[1]= new Produto();
	p[1].setCodigo("P-30");
	p[1].setDescricao("Papel");
	p[1].setPreco(15);
	c.adicionaProduto(p[1]);
	
	p[2]= new Produto();
	p[2].setCodigo("P-3");
	p[2].setDescricao("Cola");
	p[2].setPreco(3.25);
	c.adicionaProduto(p[2]);
	
	Fornecedor[] f = new Fornecedor[2];
	f[0] = new Fornecedor();
	f[0].setCodigo("F-1");
	f[0].setDescricao("Empresa XO");
	c.adicionaFornecedor(f[0]);
	
	f[1] = new Fornecedor();
	f[1].setCodigo("F-2");
	f[1].setDescricao("Empresa ABC");
	c.adicionaFornecedor(f[1]);
	
	c.compra("P-3","F-1",10);
	c.compra("P-3","F-1",20);
	c.compra("P-30","F-1",5);
	c.compra("P-30","F-1",50);
	c.compra("P-1","F-1",20);
	c.compra("P-1","F-2",30);
	System.out.println(c.totalPorProduto());
	System.out.println(c.fornecedorProdutos());
	
}
}
