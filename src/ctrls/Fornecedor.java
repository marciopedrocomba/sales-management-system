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
public class Fornecedor {
    
    public static void Insert(String Nome, String Cidade, String Municipio, String Bairro, String Pais, String Rua, int Telefone)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Fornecedor (Nome, Cidade, Municipio, Bairro, Rua, Pais, Telefone) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, Nome);
            ps.setString(2, Cidade);
            ps.setString(3, Municipio);
            ps.setString(4, Bairro);
            ps.setString(5, Rua);
            ps.setString(6, Pais);
            ps.setInt(7, Telefone);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor Adicionado Com Sucesso!");
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
    
    
    public static void Select(JTable table, String valueToSearch)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Fornecedor");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[5] = rs.getString(7);
                
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
    
     public static void Update(int ID, String Nome, String Cidade, String Municipio, String Bairro, String Rua, String Pais, int telefone)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Fornecedor SET Nome=?, Cidade=?, Municipio=?, Bairro=?, Rua=?, Pais=?, Telefone=? WHERE ID=?" );
            ps.setString(1, Nome);
            ps.setString(2, Cidade);
            ps.setString(3, Municipio);
            ps.setString(4, Bairro);
            ps.setString(5, Rua);
            ps.setString(6, Pais);
            ps.setInt(7, telefone);
            ps.setInt(8, ID);
            
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
            ps = con.prepareStatement("Delete FROM fornecedor WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor Removido Com Eliminado");
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
	            ps = con.prepareStatement("SELECT ID FROM fornecedor");
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
     
     public static void SelectAllFornecedor(ArrayList<String> list)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM fornecedor");
	            
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
  
     public static int SelectfornecedorId(String value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM fornecedor where Nome=?");
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
     
     public static int countFornecedor()
     {
         Connection con = Dbh.connect();
         PreparedStatement ps;
         
         try {
             ps = con.prepareStatement("select count(ID) as total from fornecedor;");
             
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
 				return rs.getInt("total");
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
         
         return 0;
         
     }
}

