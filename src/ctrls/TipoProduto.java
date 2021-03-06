package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class TipoProduto {
	
	public static void Insert(String Nome)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO tipo_produto (nome) VALUES (?)" );
            
            ps.setString(1, Nome);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Tipo de produto Adicionada Com Sucesso");
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
	
	 public static void Update(int id, String nome)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("UPDATE tipo_produto SET  Nome=? WHERE ID=?" );
	            ps.setString(1, nome);
	            ps.setInt(2, id);
	            
	            
	            if (ps.executeUpdate() > 0) {
	                JOptionPane.showMessageDialog(null, "Registro Atualizado");
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
	
	public static void Delete(int ID)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("Delete FROM tipo_produto WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "tipo de produto Removido Com sucesso");
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
	
	
	public static int SelectLastId()
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        int id = 0;
        
        try {
            ps = con.prepareStatement("SELECT ID FROM tipo_produto");
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
