package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Funcionario;
import model.TipoPagamento;
import model.User;

public class PagamentosAll extends AbstractTableModel{
	
private List<model.Pagamento> list = new ArrayList<>();
	
	public PagamentosAll() {
		try {
			
			String sql = "select * from pagamento inner join tipo_pagamento "
					+ "on pagamento.Tipo=tipo_pagamento.ID order by pagamento.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Pagamento(res.getInt("pagamento.ID"), 
						new model.TipoPagamento(res.getInt("tipo_pagamento.ID"), res.getString("tipo_pagamento.Nome")), 
						res.getFloat("pagamento.Valor_apagar"),  res.getFloat("pagamento.Valor_dinheiro"), res.getFloat("pagamento.Valor_cartao")));
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
			return "Tipo";
		case 2:
			return "Valor Total";
		case 3:
			return "Valor Dinheiro";
		default:
			return "Valor Cartão";
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
			return list.get(row).getTipoPagamento().getNome();
		case 2:
			return list.get(row).getValorPagar()+"kz";
		case 3:
			return list.get(row).getValorDinheiro() + "kz";
		default:
			return list.get(row).getValorCartao() + "kz";
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
			 list.get(row).setTipoPagamento((model.TipoPagamento)value);
		case 2:
			list.get(row).setValorPagar((float)value);
		case 3:
			list.get(row).setValorDinheiro((float)value);
		default:
			 list.get(row).setValorCartao((float)value);
			 fireTableCellUpdated(row, col);
		} 
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(int id, String nomePagamento, float total, float dinheiro, float cartao) {
		list.add(new model.Pagamento(id, 
				new model.TipoPagamento(0, nomePagamento), 
				total,  dinheiro, cartao));
	}
}
