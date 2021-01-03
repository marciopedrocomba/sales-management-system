package model;

public class ItemVendido {
	
	private Produto produto;
	private Venda venda;
	private int quantidade;
	
	public ItemVendido(Produto produto, Venda venda, int quantidade) {
		super();
		this.produto = produto;
		this.venda = venda;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
