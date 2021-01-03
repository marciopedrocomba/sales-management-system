package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fanuel
 */
public class Equipamento {
   public static void Insert(String Nome, int Estado)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Equipamento (Nome, Estado) VALUES (?,?)");
            
            ps.setString(1, Nome);
            ps.setInt(2, Estado);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item Adicionado Com Susseço");
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
    
    
    public static void Select(JTable table)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Equipamento");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getInt(3);
                
                model.addRow(row);
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
    
     public static void Update(int ID, int Estado, String Nome)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Equipamento SET Nome=?, Estado=? WHERE ID=?" );
           
            ps.setString(1, Nome);
            ps.setInt(2, Estado);
            ps.setInt(3, ID);
            
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Equipamento Atualizados");
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
            ps = con.prepareStatement("Delete FROM Equipamento WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Equipamento Removido Com Susseço");
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
	            ps = con.prepareStatement("SELECT ID FROM equipamento");
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

