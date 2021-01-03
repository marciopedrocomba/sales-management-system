package model;

public class ProdutoEntradaView {
	
	private int id;
	private String fornecedor;
	private String produto;
	private int quantidade;
	private String data;
	
	public ProdutoEntradaView(int id, String fornecedor, String produto, int quantidade, String data) {
		super();
		this.id = id;
		this.fornecedor = fornecedor;
		this.produto = produto;
		this.quantidade = quantidade;
		this.data = data;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	

}
