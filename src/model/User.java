package model;

public class User {
	
	private Funcionario funcionario;
	private String username;
	private String password;
	
	public User(Funcionario funcionario, String username, String password) {
		super();
		this.funcionario = funcionario;
		this.username = username;
		this.password = password;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
