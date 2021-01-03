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

public class Users {
    public static void Insert(String BI, String username, String password)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO User (Funcionario, Username, Senha) VALUES (?,?,?)" );
            
            ps.setString(1, BI);
            ps.setString(2, username);
            ps.setString(3, password);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Adicionado Com Sucesso");
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
            ps = con.prepareStatement("SELECT * FROM User");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[3];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
               
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
    
     public static void Update(String BI, String username, String password)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE User SET Username=?, Senha=? WHERE Funcionario=?" );
           
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, BI);
            
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
     
     public static void Delete(String BI)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("Delete FROM User WHERE Funcionario=?" );
            ps.setString(1, BI);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Removido Com Susseço");
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
     
    public static String getUsername(String username, String password) {
    	 Connection con = Dbh.connect();
         PreparedStatement ps;
         try {
             ps = con.prepareStatement("select Username from user where Username = ? and Senha = ?" );
             ps.setString(1, username);
             ps.setString(2, password);
             ResultSet rs = ps.executeQuery();
             
             if (rs.next()) {
                 return rs.getString("Username");
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
         
         return null;
    }
    
    public static String getUserBI(String username) {
   	 Connection con = Dbh.connect();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("select Funcionario from user where Username = ?" );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString("Funcionario");
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
        
        return null;
   }
     
}

