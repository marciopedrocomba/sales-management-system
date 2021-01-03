package model;

public class EquipamentoServico {
	
	private Equipamento equipamento;
	private Servico servico;
	
	public EquipamentoServico(Equipamento equipamento, Servico servico) {
		super();
		this.equipamento = equipamento;
		this.servico = servico;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
}
