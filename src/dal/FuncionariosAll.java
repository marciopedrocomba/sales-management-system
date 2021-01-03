package dal;


import javax.swing.table.*;
import java.util.*;

import ctrls.*;

import java.sql.*;

public class FuncionariosAll extends AbstractTableModel{
	
	private List<model.Funcionario> list = new ArrayList<>();
	
	public FuncionariosAll() {
		try {
			
			String sql = "select * from Funcionario inner join Funcao "
					+ "on Funcionario.Funcao=Funcao.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Funcionario(res.getString("Funcionario.BI"), res.getString("Funcionario.Nome"), 
						res.getString("Funcionario.Sobrenome"), res.getString("Funcionario.Data_Nascimento"), 
						res.getInt("Funcionario.Telefone"), res.getString("Funcionario.Cidade"), res.getString("Funcionario.Municipio"),
						res.getString("Funcionario.Bairro"), res.getString("Funcionario.Rua"), res.getString("Funcionario.Sexo"), 
						res.getString("Funcionario.Email"), new model.Funcao(res.getInt("Funcao.ID"),
								res.getString("Funcao.Nome"))));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				Dbh.close();
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 12;
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "BI";
		case 1:
			return "Nome";
		case 2: 
			return "Sobrenome";
		case 3:
			return "Data_Nascimento";
		case 4:
			return "Telefone";
		case 5:
			return "Cidade";
		case 6:
			return "Municipio";
		case 7:
			return "Bairro";
		case 8:
			return "Rua";
		case 9:
			return "Sexo";
		case 10:
			return "E-mail";
		default:
			return "Função";
		}
	}
	
	

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Object.class;
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return list.get(row).getBI();
		case 1:
			return list.get(row).getNome();
		case 2: 
			return list.get(row).getSobrenome();
		case 3:
			return list.get(row).getData_nascimento();
		case 4:
			return list.get(row).getTelefone();
		case 5:
			return list.get(row).getCidade();
		case 6:
			return list.get(row).getMunicipio();
		case 7:
			return list.get(row).getBairro();
		case 8:
			return list.get(row).getRua();
		case 9:
			return list.get(row).getSexo();
		case 10:
			return list.get(row).getEmail();
		default:
			return list.get(row).getFuncao().getNome();
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		switch (col) {
		case 0:
			 list.get(row).setBI((String)value);
		case 1:
			 list.get(row).setNome((String)value);
		case 2: 
			 list.get(row).setSobrenome((String)value);
		case 3:
			 list.get(row).setData_nascimento((String)value);
		case 4:
			 list.get(row).setTelefone((int)value);
		case 5:
			 list.get(row).setCidade((String)value);
		case 6:
			 list.get(row).setMunicipio((String)value);
		case 7:
			 list.get(row).setBairro((String)value);
		case 8:
			 list.get(row).setRua((String)value);
		case 9:
			 list.get(row).setSexo((String)value);
		case 10:
			 list.get(row).setEmail((String)value);	
		default:
			 list.get(row).setFuncao((model.Funcao)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(String BI, String Nome, String Sobrenome, String Data_Nascimento, 
			int Telefone, String Cidade, String Municipio, String Bairro, String Rua, 
			String Sexo, String Email, int Funcao, String funcaoNome) {
		list.add(new model.Funcionario(BI, Nome, Sobrenome, Data_Nascimento, Telefone,
				Cidade, Municipio, Bairro, Rua, Sexo, Email, new model.Funcao(Funcao, funcaoNome)));
	}
	
}
