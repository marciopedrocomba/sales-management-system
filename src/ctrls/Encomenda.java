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
public class Encomenda {
    public static void Insert(int Servico, int Estado, int Cliente, String Data)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Encomenda (Servico, Estado, Cliente, Data) VALUES (?,?,?,?)");
            
            ps.setInt(1, Servico);
            ps.setInt(2, Estado);
            ps.setInt(3, Cliente);
            ps.setString(4, Data);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Encomenda Adicionada Com Susseço");
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
    
    
    
    
     public static void Update(int ID, int Servico, int Estado, int Cliente,  String Data)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Encomenda SET Servico=?, Estado=?, Cliente=?, Data=? WHERE Produto=? and Venda=?" );
           
            ps.setInt(1, Servico);
            ps.setInt(2, Estado);
            ps.setInt(3, Cliente);
            ps.setString(4, Data);
            ps.setInt(5, ID);
            
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Encomenda Atualizados");
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
     
     public static void Delete(int idEncomenda, int idCategoria)
    {
        Connection con = Dbh.connect();
        CallableStatement ps;
        
        try {
            ps = con.prepareCall("call removeEncomenda(?, ?)");
            ps.setInt(1, idEncomenda);
            ps.setInt(2, idCategoria);
            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Encomenda Removida Com Susseço");
           
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
     
     public static boolean insertEncomenda(int servico, int estado, int cliente, int categoria, int quantidade, String data)
     {
         Connection con = Dbh.connect();
         CallableStatement stmt;
         
         try {
        	 stmt = con.prepareCall("call encomedaAdicionar(?, ?, ?, ?, ?, ?, ?)");
        	 stmt.setInt(1, servico);
        	 stmt.setInt(2, estado);
        	 stmt.setInt(3, cliente);
        	 stmt.setInt(4, categoria);
        	 stmt.setInt(5, quantidade);
        	 stmt.setString(6, data);
        	 stmt.registerOutParameter(7, Types.INTEGER);
        	 
        	 stmt.execute();
        	 
        	 if (stmt.getInt(7) > 0) {
				return false;
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
         
         return true;
     }
     
     public static int SelectLastId()
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM encomenda");
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
     
     public static Object[][] SelectTotalProdutoCategoriaQuantidade(int clienteId)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int rowCount = 0;
	        Object[][] o = null;
	        
	        
	        try {
	            ps = con.prepareStatement("select encomenda.ID, Categoria.Nome, sum(encomenda.quantidade) as total, produto.Preco from encomenda inner join estado_encomenda\r\n" + 
	            		"on encomenda.estado = estado_encomenda.ID inner join produto on encomenda.produto = produto.ID\r\n" + 
	            		"inner join categoria on produto.categoria = categoria.ID where (encomenda.cliente = ? and estado_encomenda.Nome != ?)\r\n" + 
	            		"group by Categoria.Nome;");
	            ps.setInt(1, clienteId);
	            ps.setString(2, "efectuada");
	            ResultSet rs = ps.executeQuery(); 
	           while (rs.next()) {
					rowCount++;
				}
	            
	            o = new Object[rowCount][4];
	            
	            rs = ps.executeQuery();
	            
	            for (int i = 0; rs.next(); i++) {
	            	o[i][0] = rs.getString("Categoria.Nome"); 
	            	o[i][1] = rs.getInt("total"); 
	            	o[i][2] = rs.getInt("produto.Preco"); 
	            	o[i][3] = rs.getInt("ID"); 
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
	        
	        return o;
	        
	    }
     
     public static int countEncomendaEmProcesso()
     {
         Connection con = Dbh.connect();
         PreparedStatement ps;
         
         try {
             ps = con.prepareStatement("select count(*) as total from encomenda where encomenda.estado = (select ID from estado_encomenda where estado_encomenda.Nome like '%em processo%');");
             
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
