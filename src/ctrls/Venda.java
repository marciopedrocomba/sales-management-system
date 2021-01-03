package ctrls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fanuel
 */
public class Venda {
	
     public static void Insert(int Pagamento, int Cliente, String Funcionario, String Data)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Venda (Pagamento, Cliente, Funcionario, Data) VALUES (?,?,?,?)");
            
            ps.setDouble(1, Pagamento);
            ps.setInt(2, Cliente);
            ps.setString(3, Funcionario);
            ps.setString(4, Data);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda Efectuada Com Susseço");
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
    
    
 
    
     public static void Update(int ID, int Pagamento, int Cliente, String Funcionario, String Data)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Venda SET Pagamento=?, Cliente=?, Funcionario=?, Data=? WHERE ID=?" );
            ps.setDouble(1, Pagamento);
            ps.setInt(2, Cliente);
            ps.setString(3, Funcionario);
            ps.setString(4, Data);
            ps.setInt(5, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda Atualizada");
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
            ps = con.prepareStatement("Delete FROM Venda WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda Removido Com Susseço");
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
     
     public static int SelectLastVendaId()
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT ID FROM venda");
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
     
     public static int selectVendasTotal()
	    {
	        Connection con = Dbh.connect();
	        CallableStatement stmt;
	        int total = 0;
	        
	        try {
	            stmt = con.prepareCall("CALL totalVendas(?)");
	            stmt.registerOutParameter(1, Types.INTEGER);
	            stmt.execute();
	            total = stmt.getInt(1);
	            
	        } catch (SQLException ex) {
	            System.out.println(ex);
	        }finally {
	        	try { 
	        		Dbh.close();
				} catch (Exception e) {
					System.out.println(e);
				}
	        }
	        
	        return total;
	        
	    }
}

