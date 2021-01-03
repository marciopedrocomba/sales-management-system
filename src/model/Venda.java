package model;

import java.util.*;

public class Venda {

	private int id;
	private Pagamento pagamento;
	private Cliente cliente;
	private Funcionario funcionario;
	private String data;
	
	public Venda(int id, Pagamento pagamento, Cliente cliente, Funcionario funcionario, String data) {
		super();
		this.id = id;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.data = data; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
