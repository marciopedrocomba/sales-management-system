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
public class EquipamentoServico {
    public void Insert(String Nome, int Estado)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Equipamento_Servico (Nome, Estado) VALUES (?,?)");
            
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
    
    
    public void Select(JTable table)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Equipamento_Servico");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[5];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                
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
    
     public void Update(int ID, int Estado, String Nome)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Equipamento SET Nome=?, Estado=? WHERE Equipamento=? and Servico=?" );
           
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
     
     public void Delete(int Equipamento, int Servico)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("Delete FROM Equipamento_Servico WHERE Equipamento=? and Servico=?" );
            ps.setInt(1, Equipamento);
            ps.setInt(2, Servico);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Equipamento Liberado");
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

