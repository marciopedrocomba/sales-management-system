package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.ProdutoSaida;

public class ProdutoEntradaAll extends AbstractTableModel{
	
	private List<model.ProdutoEntradaView> list = new ArrayList<>();
	private Object[] appendList = new Object[5];
	
	public ProdutoEntradaAll() {
		try {

			String sql = "select * from produtoEntrada order by ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {
				list.add(new model.ProdutoEntradaView(res.getInt("produtoEntrada.ID"), res.getString("produtoEntrada.Fornecedor"), res.getString("produtoEntrada.Produto"), 
						res.getInt("produtoEntrada.Quantidade"), res.getString("produtoEntrada.Data")));
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
		return 5;
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		case 1:
			return "Fornecedor";
		case 2:
			return "Produto";
		case 3: 
			return "Quantidade";
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
			return list.get(row).getFornecedor();
		case 2:
			return list.get(row).getProduto();
		case 3:
			return list.get(row).getQuantidade();
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
			list.get(row).setFornecedor((String)value);
		case 2:
			list.get(row).setProduto((String)value);
		case 3: 
			list.get(row).setQuantidade((int)value);
		default:
			list.get(row).setData((String)value);
			fireTableCellUpdated(row, col);
		}
	}

	public void removeRow(int index) {
		list.remove(index);
	}

	public void append(String fornecedor, String produto, String data, int quantidade) {
		if (this.appendList[1].equals(fornecedor) && this.appendList[2].equals(produto) && this.appendList[3].equals(data)) {
			int id = (int) this.appendList[0];
			list.get(id).setQuantidade(list.get(id).getQuantidade() + quantidade);
		}
	}
	
	public boolean exists(String fornecedor, String produto, String data, int quantidade) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFornecedor().equals(fornecedor) && list.get(i).getProduto().equals(produto)
					&& list.get(i).getData().equals(data)) {
				this.appendList[0] = i;
				this.appendList[1] = fornecedor;
				this.appendList[2] = produto;
				this.appendList[3] = data;
				this.appendList[4] = quantidade;
				return true;
			}
		}

		return false;
	}

	public void addRow(int id, String data, int quantidade, String fornecedor, String produto) {
		list.add(new model.ProdutoEntradaView(id, fornecedor, produto, 
				quantidade, data));
	}
}
