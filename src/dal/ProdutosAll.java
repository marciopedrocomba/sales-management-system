package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Categoria;
import model.TipoProduto;

public class ProdutosAll extends AbstractTableModel{
	
private List<model.Produto> list = new ArrayList<>();
	
	public ProdutosAll() {
		try {
			
			String sql = "select * from produto inner join categoria on produto.Categoria=categoria.ID "
					+ " inner join tipo_produto on produto.Tipo=tipo_produto.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Produto(res.getInt("produto.ID"), res.getFloat("produto.Preco"), 
						res.getInt("produto.Quantidade"), new Categoria(res.getInt("categoria.ID"), 
								res.getString("categoria.Nome")), new TipoProduto(res.getInt("tipo_produto.ID"), 
										res.getString("tipo_produto.Nome"))));
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
			return "Preco";
		case 2: 
			return "Quantidade";
		case 3:
			return "Categoria";
		default:
			return "Tipo";
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
			return list.get(row).getPreco()+"kz";
		case 2: 
			return list.get(row).getQuantidade();
		case 3:
			return list.get(row).getCategoria().getNome();
		default:
			return list.get(row).getTipoProduto().getNome();
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
			 list.get(row).setPreco((float)value);
		case 2: 
			 list.get(row).setQuantidade((int)value);
		case 3:
			 list.get(row).setCategoria((model.Categoria)value);
		default:
			 list.get(row).setTipoProduto((model.TipoProduto)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void appendValue(int id, int quantidade) {		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.get(i).setQuantidade(list.get(i).getQuantidade() + quantidade);
				break;
			}
		}
		
		/*if (Collections.binarySearch(list, list, null) == id) {
			list.get(id).setQuantidade(list.get(id).getQuantidade() + quantidade);
		}**/
	}
	
	public void appendValueFromEncomenda(int id, int quantidade) {		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.get(i).setQuantidade(list.get(i).getQuantidade() + quantidade);
				break;
			}
		}
	}
	
	public void decreValue(int id, int quantidade) {		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.get(i).setQuantidade(list.get(i).getQuantidade() - quantidade);
				break;
			}
		}
	}
	
	public void addRow(int id, double Preco, int Quantidade, int Categoria, String CategoriaNome,
			int Tipo, String TipoNome) {
		list.add(new model.Produto(id, (float) Preco, 
				Quantidade, new Categoria(Categoria, 
						CategoriaNome), new TipoProduto(Tipo, 
								TipoNome)));
	}
}
