package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;

public class EquipamentosAll extends AbstractTableModel{
	
private List<model.Equipamento> list = new ArrayList<>();
	
	public EquipamentosAll() {
		try {
			
			String sql = "select * from equipamento inner join estado_equipamento "
					+ "on equipamento.Estado=estado_equipamento.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Equipamento(res.getInt("equipamento.ID"), res.getString("equipamento.Nome"), 
						new model.EstadoEquipamento(res.getInt("estado_equipamento.ID"), res.getString("estado_equipamento.Nome"))));
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
			return "ID";
		case 1:
			return "Nome";
		default:
			return "Estado";
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
		default:
			return list.get(row).getEstado().getNome();
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
		default:
			 list.get(row).setEstado((model.EstadoEquipamento)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(int id, String nome, String estado) {
		list.add(new model.Equipamento(id, nome, 
				new model.EstadoEquipamento(0, estado)));
	}
}
