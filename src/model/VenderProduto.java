package model;

public class VenderProduto {
	private int quantidade;
	private String categoria;
	private float preco;
	
	public VenderProduto(int quantidade, String categoria, float preco) {
		super();
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
	
	
}
