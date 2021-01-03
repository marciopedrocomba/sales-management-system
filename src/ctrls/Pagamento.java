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
public class Pagamento {
    public void Insert(int Tipo, double Valor)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Pagamento (Tipo, Valor_apagar) VALUES (?,?)" );
            
            ps.setInt(1, Tipo);
            ps.setDouble(2, Valor);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Pagamento Efectuado Com Susseço");
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
            ps = con.prepareStatement("SELECT * FROM Pagamento");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getDouble(3);
               
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
    
     public void Update(int Tipo, double Valor)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE Pagamento SET Tipo=?, Valor_apagar=? WHERE ID=?" );
           
            ps.setInt(1, Tipo);
            ps.setDouble(2, Valor);
            
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
     
     public void Delete(String BI)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("Delete FROM Pagamento WHERE ID=?" );
            ps.setString(1, BI);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Pagamento Removido Com Susseço");
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
     
     public static int SelectLastPagamentoId()
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("select ID from pagamento order by ID;");
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

