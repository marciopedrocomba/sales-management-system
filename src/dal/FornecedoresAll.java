package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.*;

import ctrls.Dbh;

public class FornecedoresAll extends AbstractTableModel{
private List<model.Fornecedore> list = new ArrayList<>();
	
	public FornecedoresAll() {
		try {
			
			String sql = "select * from fornecedor";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Fornecedore(res.getInt("ID"), res.getString("Nome"), 
						res.getString("Cidade"), res.getString("Municipio"), res.getString("Bairro"), 
						res.getString("Rua"), res.getString("Pais"), res.getInt("Telefone")));
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
		return 8;
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		case 1:
			return "Nome";
		case 2: 
			return "Cidade";
		case 3:
			return "Municipio";
		case 4:
			return "Bairro";
		case 5:
			return "Pais";
		case 6:
			return "Rua";
		default:
			return "Telefone";
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
			return list.get(row).getId();
		case 1:
			return list.get(row).getNome();
		case 2: 
			return list.get(row).getCidade();
		case 3:
			return list.get(row).getMunicipio();
		case 4:
			return list.get(row).getBairro();
		case 5:
			return list.get(row).getPais();
		case 6:
			return list.get(row).getRua();
		default:
			return list.get(row).getTelefone();
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
			 list.get(row).setId((int)value);
		case 1:
			 list.get(row).setNome((String)value);
		case 2: 
			 list.get(row).setCidade((String)value);
		case 3:
			 list.get(row).setMunicipio((String)value);
		case 4:
			 list.get(row).setBairro((String)value);
		case 5:
			 list.get(row).setPais((String)value);
		case 6:
			 list.get(row).setRua((String)value);
		default:
			 list.get(row).setTelfone((int)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(int id, String Nome, String Cidade, String Municipio, String Bairro, 
			String Rua, String Pais, int Telefone) {
		list.add(new model.Fornecedore(id, Nome, 
				Cidade, Municipio, Bairro, 
				Rua, Pais, Telefone));
	
	}
}
