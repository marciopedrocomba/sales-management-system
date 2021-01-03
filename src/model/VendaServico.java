package model;

import java.util.*;

public class VendaServico {
	
	private int id;
	private Encomenda encomenda;
	private Pagamento pagamento;
	private Date data;
	
	public VendaServico(int id, Encomenda encomenda, Pagamento pagamento, Date data) {
		super();
		this.id = id;
		this.encomenda = encomenda;
		this.pagamento = pagamento;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Encomenda getEncomenda() {
		return encomenda;
	}

	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
