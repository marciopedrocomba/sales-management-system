package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Funcionario;
import model.User;

public class UsersAll extends AbstractTableModel{
	
private List<model.User> list = new ArrayList<>();
	
	public UsersAll() {
		try {
			
			String sql = "select * from user inner join funcionario "
					+ "on user.Funcionario=funcionario.BI inner join Funcao "
					+ "on Funcionario.Funcao=Funcao.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new User(new Funcionario(res.getString("Funcionario.BI"), res.getString("Funcionario.Nome"), 
						res.getString("Funcionario.Sobrenome"), res.getString("Funcionario.Data_Nascimento"), 
						res.getInt("Funcionario.Telefone"), res.getString("Funcionario.Cidade"), res.getString("Funcionario.Municipio"),
						res.getString("Funcionario.Bairro"), res.getString("Funcionario.Rua"), res.getString("Funcionario.Sexo"), 
						res.getString("Funcionario.Email"), new model.Funcao(res.getInt("Funcao.ID"),
								res.getString("Funcao.Nome"))), res.getString("user.Username"), 
						res.getString("user.Senha")));
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
		return 3;
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Funcionario";
		case 1:
			return "Nome do usuario";
		default:
			return "Senha";
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
			return list.get(row).getFuncionario().getBI();
		case 1:
			return list.get(row).getUsername();
		default:
			return list.get(row).getPassword();
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
			 list.get(row).setFuncionario((model.Funcionario)value);
		case 1:
			 list.get(row).setUsername((String)value);
		default:
			 list.get(row).setPassword((String)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(String bi, String username, String password) {
		list.add(new User(new Funcionario(bi, "", 
				"", "", 
				0, "", "",
				"", "", "", 
				"", new model.Funcao(0,
						"")), username, 
				password));
	}
}
