package model;

public class ProdutoSaida {
	private String produto;
	private int total;
	
	public ProdutoSaida(String produto, int total) {
		super();
		this.produto = produto;
		this.total = total;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
