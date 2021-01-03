package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Categoria;
import model.TipoProduto;

public class CategoriasAll extends AbstractTableModel{
	
private List<model.Categoria> list = new ArrayList<>();
	
	public CategoriasAll() {
		try {
			
			String sql = "select * from categoria";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Categoria(res.getInt("categoria.ID"), 
						res.getString("categoria.Nome")));
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
		return 2;
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		default:
			return "Nome";
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
		default:
			return list.get(row).getNome();
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
		default:
			 list.get(row).setNome((String)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(int id, String Nome) {
		list.add(new model.Categoria(id, Nome));
	}
}
