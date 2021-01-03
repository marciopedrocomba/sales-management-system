package model;

import java.util.*;

public class Encomenda {
	
	private int id;
	private Servico servico;
	private EstadoEncomenda estado;
	private Cliente cliente;
	private Produto produto;
	private int quantidade;
	private String data;
	
	public Encomenda(int id, Servico servico, EstadoEncomenda estado, Cliente cliente, Produto produto, int quantidade,
			String date) {
		super();
		this.id = id;
		this.servico = servico;
		this.estado = estado;
		this.cliente = cliente;
		this.produto = produto;
		this.quantidade = quantidade;
		this.data = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public EstadoEncomenda getEstado() {
		return estado;
	}

	public void setEstado(EstadoEncomenda estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
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
