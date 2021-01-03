package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Categoria;
import model.TipoProduto;

public class VenderProduto extends AbstractTableModel{
	
private List<model.VenderProduto> list;
	
	public VenderProduto() {
		list = new ArrayList<model.VenderProduto>();
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
			return "Categoria";
		case 1:
			return "Quantidade";
		default:
			return "Preço";
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
			return list.get(row).getCategoria();
		case 1:
			return list.get(row).getQuantidade();
		default:
			return list.get(row).getPreco();
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
			list.get(row).setCategoria((String)value);
		case 1: 
			 list.get(row).setQuantidade((int)value);
		default:
			 list.get(row).setPreco((float)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void removeAll() {
		list.removeAll(list);
	}
	
	public void appendValue(int index, String categoria, int quantidade, float preco) {
		//list.get(index).setQuantidade((int)quantidade);
		if (list.get(index).getCategoria().equals(categoria)) {
			list.get(index).setQuantidade(list.get(index).getQuantidade() + quantidade);
			list.get(index).setPreco(list.get(index).getPreco() + preco);
		}
	}
	
	public boolean exists(String categoria) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCategoria().equals(categoria)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int returnIfExists(String categoria) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getCategoria().equals(categoria)) {
				return list.get(i).getQuantidade();
			}
		}
		
		return 0;
	}
	
	public void addRow(int quantidade, String categoria, float preco) {
		list.add(new model.VenderProduto(quantidade, categoria, preco));
	}
	
	public String AllCategorias() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			s.append(list.get(i).getCategoria()).append("%20%");
		}
		
		return s.toString();
	}
	
	public String AllQuantidades() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			s.append(list.get(i).getQuantidade()).append("%20%");
		}
		
		return s.toString();
	}
}
