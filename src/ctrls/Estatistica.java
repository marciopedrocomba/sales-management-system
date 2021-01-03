package ctrls;

import java.sql.*;

public class Estatistica {
	
	public static int caixa() {
		
		 Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("SELECT SUM(Valor_apagar) as total FROM Pagamento WHERE (ID = (SELECT Pagamento FROM Venda WHERE `data` = CURDATE())) and (ID = (SELECT Pagamento FROM Venda_servico WHERE `data` = CURDATE()));" );
	            ResultSet res = ps.executeQuery();
	            
	            if (res.next()) {
	                return res.getInt("total");
	            }
	           
	        } catch (SQLException ex) {
	            System.out.println(ex);
	        }finally {
	        	try {
	        		Dbh.close();
				} catch (Exception e) {
					System.out.println(e);
				}
	        }
	        
	        return -1;

	}

}
