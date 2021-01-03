package model;

public class Produto implements Comparable{
	
	private int id;
	private float preco;
	private int quantidade;
	private Categoria categoria;
	private TipoProduto tipoProduto;
	
	public Produto(int id, float preco, int quantidade, Categoria categoria,
			TipoProduto tipoProduto) {
		
		super();
		this.id = id;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.tipoProduto = tipoProduto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
}
