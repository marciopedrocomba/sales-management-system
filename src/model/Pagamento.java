package model;

public class Pagamento {
	
	private int id;
	private TipoPagamento tipoPagamento;
	private float valorPagar;
	private float valorDinheiro;
	private float valorCartao;
	
	public Pagamento(int id, TipoPagamento tipoPagamento, float valorPagar, float valorDinheiro, float valorCartao) {
		super();
		this.id = id;
		this.tipoPagamento = tipoPagamento;
		this.valorPagar = valorPagar;
		this.valorDinheiro = valorDinheiro;
		this.valorCartao = valorCartao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public float getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(float valorPagar) {
		this.valorPagar = valorPagar;
	}

	public float getValorDinheiro() {
		return valorDinheiro;
	}

	public void setValorDinheiro(float valorDinheiro) {
		this.valorDinheiro = valorDinheiro;
	}

	public float getValorCartao() {
		return valorCartao;
	}

	public void setValorCartao(float valorCartao) {
		this.valorCartao = valorCartao;
	}
	
	
	
}
