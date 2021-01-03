package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fanuel
 */
public class Cliente {
     public static void Insert(String Nome, String Cidade, String Municipio, String Bairro, String Rua, String Pais, String Sexo, int Telefone)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Cliente (Nome, Cidade, Municipio, Bairro, Rua, Pais, Sexo, Telefone) VALUES (?,?,?,?,?,?,?,?)" );
            
            ps.setString(1, Nome);
            ps.setString(2, Cidade);
            ps.setString(3, Municipio);
            ps.setString(4, Bairro);
            ps.setString(5, Rua);
            ps.setString(6, Pais);
            ps.setString(7, Sexo);
            ps.setInt(8, Telefone);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Adicionado Com Susseço");
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
            ps = con.prepareStatement("SELECT * FROM Cliente");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[8];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getInt(8);
                
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
    
     public static void Update(int ID, String Nome, String Cidade, String Municipio, String Bairro, String Rua, String Pais, String Sexo, int Telefone)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Cliente SET  Nome=?, Cidade=?, Municipio=?, Bairro=?, Rua=?, Pais=?, Sexo=?, Telefone=? WHERE ID=?" );
            ps.setString(1, Nome);
            ps.setString(2, Cidade);
            ps.setString(3, Municipio);
            ps.setString(4, Bairro);
            ps.setString(5, Rua);
            ps.setString(6, Pais);
            ps.setString(7, Sexo);
            ps.setInt(8, Telefone);
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
            ps = con.prepareStatement("Delete FROM Cliente WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente Removido Com Susseço");
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
	            ps = con.prepareStatement("SELECT ID FROM Cliente");
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
     
     public static void SelectAllClientes(ArrayList<String> list)
     {
         Connection con = Dbh.connect();
         PreparedStatement ps;
         
         try {
             ps = con.prepareStatement("SELECT * FROM cliente order by Nome asc");
             
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
     
     public static void SelectAllClientesEncomenda(ArrayList<String> list)
     {
         Connection con = Dbh.connect();
         PreparedStatement ps;
         
         try {
             ps = con.prepareStatement("select * from cliente where ID IN(select cliente from encomenda " + 
             		" where encomenda.estado = (select ID from estado_encomenda where estado_encomenda.Nome != 'efectuada')) order by cliente.Nome asc");
             
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
     
     
     
     public static int SelectClienteId(String value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM cliente where Nome=?");
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
     
     public static int countCliente()
     {
         Connection con = Dbh.connect();
         PreparedStatement ps;
         
         try {
             ps = con.prepareStatement("select count(ID) as total from cliente;");
             
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

