package ctrls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class VenderServico {
	
	public static void venderServico(int idTipoPagamento, float valorDinheiro, float valorCartao, int idEncomenda, String funcionario) {
		try {
			
			Connection conn = Dbh.connect();
			CallableStatement stmt = conn.prepareCall("call venderServico(?, ?, ?, ?)");
			stmt.setInt(1, idTipoPagamento);
			stmt.setFloat(2, valorDinheiro);
			stmt.setFloat(3, valorCartao);
			stmt.setString(4, funcionario);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/*public static void produtosEncomendados(String categoria, String quantidade) {
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
		
		/*try {
			produtosEncomendadosSelecionados(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	public static void produtosEncomendadosSelecionados(Object[][] o)throws Exception { 
		Connection conn = Dbh.connect();
		CallableStatement stmt = conn.prepareCall("call produtosVendidoEncomenda(?)");
		
		DatabaseMetaData db = conn.getMetaData();
		if (db.supportsBatchUpdates()) {
			for (int i = 0; i < o.length; i++) {
				stmt.setInt(1, (int) o[i][3]); 
				stmt.addBatch();
			}
			
			stmt.executeBatch();
		}
		
	}
}
