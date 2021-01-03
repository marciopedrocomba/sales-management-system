package ctrls;

import java.sql.*;

public class Vender {
	public static void vender(int idTipoPagamento, float valorDinheiro, float valorCartao, int idCliente, String funcionario) {
		try {
			
			Connection conn = Dbh.connect();
			CallableStatement stmt = conn.prepareCall("call vender(?, ?, ?, ?, ?)");
			stmt.setInt(1, idTipoPagamento);
			stmt.setFloat(2, valorDinheiro);
			stmt.setFloat(3, valorCartao);
			stmt.setInt(4, idCliente);
			stmt.setString(5, funcionario);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void produtosVendidos(String categoria, String quantidade) {
		String[] categoriaArray = categoria.split("%20%");
		String[] quantidadeArray = quantidade.split("%20%");
		for (int i = 0; i < categoriaArray.length; i++) {
			int id = ctrls.Produto.SelectCategoriaId(categoriaArray[i]);
			categoriaArray[i] = Integer.toString(id);
		}
		
		Object[][] o = new Object[categoriaArray.length][2];
		
		for (int i = 0; i < categoriaArray.length; i++) {
			o[i][0] = Integer.parseInt(categoriaArray[i]);
			o[i][1] = Integer.parseInt(quantidadeArray[i]);
		}
		
		/**/
		
		try {
			produtosSelecionados(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void produtosSelecionados(Object[][] o)throws Exception {
		Connection conn = Dbh.connect();
		CallableStatement stmt = conn.prepareCall("call produtosVendidos(?, ?)");
		
		DatabaseMetaData db = conn.getMetaData();
		if (db.supportsBatchUpdates()) {
			for (int i = 0; i < o.length; i++) {
				stmt.setInt(1, (int) o[i][0]);
				stmt.setInt(2, (int) o[i][1]);
				stmt.addBatch();
			}
			
			stmt.executeBatch();
		}
		
	}
	
	
}
