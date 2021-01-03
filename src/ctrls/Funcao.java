package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

public class Funcao {
	
	 public static void SelectAllFuncao(ArrayList<String> list)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM funcao");
	            
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
					list.add(rs.getString("Nome"));
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
	        
	    }
	 
	 public static int SelectFuncaoId(String value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM funcao where Nome=?");
	            ps.setString(1, value);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
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
