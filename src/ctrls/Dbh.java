package ctrls;

import java.sql.*;

import javax.swing.JOptionPane;

public class Dbh {
	
private static Connection conn = null;
	
	//metodo para abrir a conecao com a base de dados
	public static Connection connect() {
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Maseda?useSSL=false", "root", "");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return conn; 
	}
	
	//metodo para fechar a conecao com a base de dados
	public static void close() throws Exception{
		if(conn != null) conn.close();
	}
	
	
	public static boolean login(String username, String password)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from userFuncionario where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            
            if (ps.executeQuery().next()) {
                return true;
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
        
        return false;
        
    }
	
	public static boolean login(String username, String password, String funcao)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select * from userFuncionario where username=? and password=? and funcao=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, funcao);
            
            if (ps.executeQuery().next()) {
                return true;
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
        
        return false;
        
    }
}
