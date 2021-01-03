package model;

public class Equipamento {
	
	private int id;
	private String nome;
	private EstadoEquipamento estado;
	
	public Equipamento(int id, String nome, EstadoEquipamento estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoEquipamento getEstado() {
		return estado;
	}

	public void setEstado(EstadoEquipamento estado) {
		this.estado = estado;
	}
	
	
}
