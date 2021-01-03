package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.VendaServicoView;

public class VendaServicoAll extends AbstractTableModel{
	
private List<model.VendaServicoView> list = new ArrayList<>();
	
	public VendaServicoAll() {
		try {
			
			String sql = "select * from vendaServico order by ID;";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new VendaServicoView(res.getInt("ID"), res.getString("funcionario"), res.getFloat("total"), res.getString("data")));
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
		return 4;
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		case 1:
			return "Funcionario";
		case 2:
			return "Valor Total";
		default:
			return "Data";
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
			return list.get(row).getFuncionario();
		case 2:
			return list.get(row).getTotal() +"kz";
		default:
			return list.get(row).getData();
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
			 list.get(row).setFuncionario((String)value);
		case 2:
			 list.get(row).setTotal((float)value);
		default:
			 list.get(row).setData((String)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(int id, String funcionario, float total, String data) {
		list.add(new VendaServicoView(id, funcionario, total, data));
	}
	
}
