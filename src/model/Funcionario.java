package model;

import java.util.*;

public class Funcionario {
	
	private String BI; 
	private String nome;
	private String sobrenome;
	private String data_nascimento;
	private int telefone;
	private String cidade;
	private String municipio;
	private String bairro;
	private String rua;
	private String sexo;
	private String email;
	private Funcao funcao;
	
	public Funcionario(String bI, String nome, String sobrenome, String data_nascimento, 
			int telefone, String cidade, String municipio, String bairro, String rua, 
			String sexo, String email, Funcao funcao) {
		
		super();
		BI = bI;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.data_nascimento = data_nascimento;
		this.telefone = telefone;
		this.cidade = cidade;
		this.municipio = municipio;
		this.bairro = bairro;
		this.rua = rua;
		this.sexo = sexo;
		this.email = email;
		this.funcao = funcao;
	}

	public String getBI() {
		return BI;
	}

	public void setBI(String bI) {
		BI = bI;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
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

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
}
