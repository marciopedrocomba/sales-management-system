package ctrls;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Fanuel
 */
public class ProdutoEntrada {
    
    public static void Insert(String Data, int Quantidade, int Fornecedor, int Produto)
    {
        Connection con = Dbh.connect();
        CallableStatement stmt;
        
        try {
            stmt = con.prepareCall("call produtoEntradaAdicionar(?, ?, ?, ?)");
            
            stmt.setString(1, Data);
            stmt.setInt(2, Quantidade);
            stmt.setInt(3, Fornecedor);
            stmt.setInt(4, Produto);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto Adicionado Com Sucesso");
           
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
    
    
   
     public static void Update(int ID, String Data, int Quantidade, int Fornecedor, int Produto)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Produto_Entrada SET SET Data=?, Quantidade=?, Fornecedor=?, Produto=? WHERE ID=?" );
            ps.setString(1, Data);
            ps.setInt(2, Quantidade);
            ps.setInt(3, Fornecedor);
            ps.setInt(4, Produto);
            ps.setInt(9, ID);
            
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
            ps = con.prepareStatement("Delete FROM Produto_Entrada WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto Removido Com Susseço");
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
	            ps = con.prepareStatement("SELECT ID FROM produtoEntrada order by ID");
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

