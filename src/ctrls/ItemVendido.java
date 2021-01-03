package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fanuel
 */
public class ItemVendido {
    public void Insert(int Produto, int Venda, int Quantidade)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Item_Vendido (Produto, Venda, Quantidade) VALUES (?,?,?)");
            
            ps.setDouble(1, Produto);
            ps.setInt(2, Venda);
            ps.setInt(3, Quantidade);
            
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
    
    
    public void Select(JTable table)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Item_Vendido");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
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
    
     public void Update(int Produto, int Venda, int Quantidade)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Item_Vendido SET Quantidade=? WHERE Produto=? and Venda=?" );
           
            ps.setInt(1, Quantidade);
            ps.setInt(2, Produto);
            ps.setInt(3, Venda);
            
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Itens Atualizados");
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
     
     public void Delete(int Produto, int Venda)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("Delete FROM Item_Vendido WHERE Produto=? and Venda=?" );
            ps.setInt(1, Produto);
            ps.setInt(2, Venda);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item Removido Com Susseço");
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
     
}
