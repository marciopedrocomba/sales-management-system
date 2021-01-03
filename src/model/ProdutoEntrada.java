package model;

import java.util.*;

public class ProdutoEntrada {
	
	private int id;
	private Date data;
	private int quantidade;
	private Fornecedore fornecedore;
	private Produto produto;
	
	public ProdutoEntrada(int id, Date data, int quantidade, Fornecedore fornecedore, 
			Produto produto) {
		super();
		this.id = id;
		this.data = data;
		this.quantidade = quantidade;
		this.fornecedore = fornecedore;
		this.produto = produto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedore getFornecedore() {
		return fornecedore;
	}

	public void setFornecedore(Fornecedore fornecedore) {
		this.fornecedore = fornecedore;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
