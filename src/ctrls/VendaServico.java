package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaServico {
	
	public static int SelectLastVendaServicoId()
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        int id = 0;
        
        try {
            ps = con.prepareStatement("SELECT ID FROM venda_servico");
            ResultSet rs = ps.executeQuery();
            if (rs.last()) {
				id = rs.getInt("ID");
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
        
        return id;
        
    }
}
