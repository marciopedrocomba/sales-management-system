package model;

public class Fornecedore {
	
	private int id;
	private String nome;
	private String cidade;
	private String municipio;
	private String bairro;
	private String pais;
	private String rua;
	private int telefone;
	
	public Fornecedore(int id, String nome, String cidade, String municipio, String bairro, 
			String pais, String rua, int telefone) {
		
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.municipio = municipio;
		this.bairro = bairro;
		this.pais = pais;
		this.rua = rua;
		this.telefone = telefone;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelfone(int telefone) {
		this.telefone = telefone;
	}
	
}
