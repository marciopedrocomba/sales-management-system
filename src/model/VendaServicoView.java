package model;

public class VendaServicoView {
	
	private int id;
	private String funcionario;
	private float total;
	private String data;
	
	public VendaServicoView(int id, String funcionario, float total, String data) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.total = total;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
