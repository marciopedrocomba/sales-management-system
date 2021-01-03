package dal;

import java.sql.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;
import ctrls.Dbh;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;

public class VendaAll extends AbstractTableModel{
private List<model.Venda> list = new ArrayList<>();
	
	public VendaAll() {
		try {
			
			String sql = "select * from venda inner join pagamento on venda.pagamento = pagamento.ID"
					+ " inner join tipo_pagamento on pagamento.Tipo=tipo_pagamento.ID"
					+ " inner join cliente on venda.cliente=cliente.ID inner join funcionario on "
					+ " venda.funcionario=funcionario.BI inner join funcao on funcionario.funcao=funcao.ID order by venda.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Venda(res.getInt("venda.ID"), new Pagamento(res.getInt("pagamento.ID"), 
						new model.TipoPagamento(res.getInt("tipo_pagamento.ID"), res.getString("tipo_pagamento.Nome")), 
						res.getFloat("pagamento.Valor_apagar"), res.getFloat("pagamento.Valor_dinheiro"), res.getFloat("pagamento.Valor_cartao")), new Cliente(res.getInt("cliente.ID"), res.getString("cliente.Nome"), 
								res.getString("cliente.cidade"), res.getString("cliente.municipio"), res.getString("cliente.bairro"), 
								res.getString("cliente.rua"), res.getString("cliente.pais"), res.getString("cliente.sexo"), res.getInt("cliente.telefone")), 
						new model.Funcionario(res.getString("Funcionario.BI"), res.getString("Funcionario.Nome"), 
								res.getString("Funcionario.Sobrenome"), res.getString("Funcionario.Data_Nascimento"), 
								res.getInt("Funcionario.Telefone"), res.getString("Funcionario.Cidade"), res.getString("Funcionario.Municipio"),
								res.getString("Funcionario.Bairro"), res.getString("Funcionario.Rua"), res.getString("Funcionario.Sexo"), 
								res.getString("Funcionario.Email"), new model.Funcao(res.getInt("Funcao.ID"),
										res.getString("Funcao.Nome"))), res.getString("venda.Data")));
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
			return "Funcionario";
		case 2: 
			return "Cliente";
		case 3:
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
			return list.get(row).getFuncionario().getNome();
		case 2: 
			return list.get(row).getCliente().getNome();
		case 3:
			return list.get(row).getPagamento().getValorPagar()+"kz";
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
			 list.get(row).setFuncionario((model.Funcionario)value);
		case 2: 
			 list.get(row).setCliente((model.Cliente)value);
		case 3:
			 list.get(row).setPagamento((model.Pagamento)value);
		default:
			 list.get(row).setData((String)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void removeRow(int index) {
		list.remove(index);
	}
	
	public void addRow(int id, String funcionario, String cliente, float valor, String data) {
		list.add(new model.Venda(id, new Pagamento(0, 
				new model.TipoPagamento(0, ""), 
				valor, 0, 0), new Cliente(0, cliente, 
						"", "", "", 
						"", "", "", 0), 
				new model.Funcionario("", funcionario, 
						"", "", 
						0, "", "",
						"", "", "", 
						"", null), data));
	}
}
