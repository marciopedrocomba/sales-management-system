package dal;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Categoria;
import model.Cliente;
import model.Pagamento;
import model.ProdutoSaida;
import model.TipoProduto;

public class ProdutoSaidaAll extends AbstractTableModel{
private List<model.ProdutoSaida> list = new ArrayList<>();
private Object[] appendList = new Object[3];
	
	public ProdutoSaidaAll() {
		try {
			
			String sql = "select * from produtoSaida";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new ProdutoSaida(res.getString("produtoSaida.produto"), res.getInt("produtoSaida.total de saida")));
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
			return "Produto";
		default:
			return "Quantidade";
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
			return list.get(row).getProduto();
		default:
			return list.get(row).getTotal();
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
			 list.get(row).setProduto((String)value);
		default:
			 list.get(row).setTotal((int)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void appendValue(String categoria, int quantidade) {
		if (this.appendList[1].equals(categoria)) {
			int id = (int) this.appendList[0];
			list.get(id).setTotal(list.get(id).getTotal() + quantidade);
		}
	}
	
	public boolean exists(String categoria, int quantidade) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProduto().equals(categoria)) {
				this.appendList[0] = i;
				this.appendList[1] = categoria;
				this.appendList[2] = quantidade;
				return true;
			}
		}
		
		return false;
	}
	
	public void addRow(String categoria, int quantidade) {
		list.add(new ProdutoSaida(categoria, quantidade));
	}
}
