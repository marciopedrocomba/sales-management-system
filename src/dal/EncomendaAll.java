package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ctrls.Dbh;
import model.Categoria;
import model.Cliente;
import model.EstadoEncomenda;
import model.Produto;
import model.TipoProduto;

public class EncomendaAll extends AbstractTableModel{
	
private List<model.Encomenda> list = new ArrayList<>();
private String[] appendList = new String[4];
	
	public EncomendaAll() {
		try {
			
			String sql = "select * from encomenda inner join servico on encomenda.Servico=servico.ID " + 
					"inner join estado_encomenda on encomenda.Estado=estado_encomenda.ID inner join " + 
					"cliente on encomenda.Cliente=cliente.ID inner join produto on encomenda.produto=produto.ID"
					+ " inner join categoria on produto.Categoria=categoria.ID inner join tipo_produto on produto.Tipo=tipo_produto.ID order by encomenda.ID";
			PreparedStatement stmt = Dbh.connect().prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				list.add(new model.Encomenda(res.getInt("encomenda.ID"), new model.Servico(res.getInt("Servico.ID"), res.getString("Servico.Nome")), 
						new model.EstadoEncomenda(res.getInt("estado_encomenda.ID"), res.getString("estado_encomenda.Nome")), 
						new model.Cliente(res.getInt("cliente.ID"), res.getString("cliente.Nome"), res.getString("cliente.Cidade"), 
								res.getString("cliente.Municipio"), res.getString("cliente.Bairro"), res.getString("cliente.Rua"), 
								res.getString("Pais"), res.getString("cliente.Sexo"), res.getInt("cliente.Telefone")), 
						new model.Produto(res.getInt("produto.ID"), res.getFloat("produto.preco"), res.getInt("produto.quantidade"), 
								new model.Categoria(res.getInt("categoria.ID"), res.getString("categoria.Nome")), 
								new model.TipoProduto(res.getInt("tipo_produto.ID"), res.getString("tipo_produto.Nome"))), res.getInt("encomenda.quantidade"), res.getString("encomenda.Data")));
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
		return 7;
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		case 1:
			return "Serviço";
		case 2: 
			return "Estado";
		case 3:
			return "Cliente";
		case 4:
			return "Produto";
		case 5:
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
			return list.get(row).getServico().getNome();
		case 2: 
			return list.get(row).getEstado().getNome();
		case 3:
			return list.get(row).getCliente().getNome();
		case 4:
			return list.get(row).getProduto().getCategoria().getNome();
		case 5:
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
			 list.get(row).setServico((model.Servico)value);;
		case 2: 
			 list.get(row).setEstado((model.EstadoEncomenda)value);
		case 3:
			 list.get(row).setCliente((model.Cliente)value);
		case 4:
			list.get(row).setProduto((model.Produto)value);
		case 5:
			list.get(row).setQuantidade((int)value);
		default:
			 list.get(row).setData((String)value);
			 fireTableCellUpdated(row, col);
		}
	}
	
	public void appendValue(String categoria, String cliente, String estado, int quantidade) {
		if (this.appendList[1].equals(categoria) && this.appendList[2].equals(cliente) && this.appendList[3].equals(estado)) {
			int id = Integer.parseInt(this.appendList[0]);
			list.get(id).setQuantidade(list.get(id).getQuantidade() + quantidade);
		}
	}
	
	public boolean exists(String categoria, String cliente, String estado) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProduto().getCategoria().getNome().equals(categoria) && list.get(i).getCliente().getNome().equals(cliente)
					&& list.get(i).getEstado().getNome().equals(estado)) {
				this.appendList[0] = Integer.toString(i);
				this.appendList[1] = categoria;
				this.appendList[2] = cliente;
				this.appendList[3] = estado;
				return true;
			}
		}
		
		return false;
	}
	
	public int count() {
		int c = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getEstado().getNome().equalsIgnoreCase("em processo")) {
				c++;
			}
		}
		return c;
	}
	
	public void updateEncomendaEstado(String categoria, String cliente, String estado) {
		if (this.appendList[1].equals(categoria) && this.appendList[2].equals(cliente) && this.appendList[3].equals(estado)) {
			int id = Integer.parseInt(this.appendList[0]);
			list.get(id).setEstado(new model.EstadoEncomenda(0, "efectuada"));
		}
	}
	
	public void removeRow(int index) {
		list.remove(index); 
	}
	
	
	public void addRow(int id, String servico, String estado, String cliente, String produtoCategoria, 
			int quantidadeValue, String date) {
		list.add(new model.Encomenda(id, new model.Servico(0, servico), 
				new model.EstadoEncomenda(0, estado), 
				new model.Cliente(0, cliente, "", 
						"", "", "", 
						"","", 0), 
				new model.Produto(0,0, 0, 
						new model.Categoria(0, produtoCategoria), 
						new model.TipoProduto(0, "")), quantidadeValue, date));
	
	}
	
}
