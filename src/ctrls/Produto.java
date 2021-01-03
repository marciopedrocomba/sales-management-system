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
public class Produto {
    public static void Insert(double Preco, int Quantidade, int Categoria, int Tipo)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO Produto (Preco, Quantidade, Categoria, Tipo) VALUES (?,?,?,?)");
            
            ps.setDouble(1, Preco);
            ps.setInt(2, Quantidade);
            ps.setInt(3, Categoria);
            ps.setInt(4, Tipo);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto Adicionado Com Sucesso");
            }
           
        } catch (SQLException ex) {
        	if (ex.getMessage().contains("Duplicate")) {
        		JOptionPane.showMessageDialog(null, "O produto a ser adicionado ja existe");
			}
        	
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
            ps = con.prepareStatement("SELECT * FROM Produto");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getDouble(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getInt(5);
                
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
    
     public static void Update(int ID, double Preco, int Quantidade, int Categoria, int Tipo)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Produto SET Preco=?, Quantidade=?, Categoria=?, Tipo=? WHERE ID=?" );
            ps.setDouble(1, Preco);
            ps.setInt(2, Quantidade);
            ps.setInt(3, Categoria);
            ps.setInt(4, Tipo);
            ps.setInt(5, ID);
            
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
            ps = con.prepareStatement("Delete FROM Produto WHERE ID=?" );
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
     
     public static void SelectAllCategoria(ArrayList<String> list)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM categoria order by Nome asc");
	            
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
     
     public static void SelectAllCategoriaExistingOnProduto(ArrayList<String> list)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("select * from categoria where ID IN (select categoria from produto);");
	            
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
     
     
     
     public static int SelectCategoriaId(String value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM categoria where Nome=?");
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
     
     public static int SelectProdutoId(int value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM produto where categoria=?");
	            ps.setInt(1, value);
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
     
     public static void SelectAllTipo(ArrayList<String> list)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM tipo_produto");
	            
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
     
     public static int SelectTipoId(String value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT * FROM tipo_produto where Nome=?");
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
     
     public static int SelectLastId()
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT ID FROM produto");
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
     

	 
	 public static int selectQuantidade(String nome) {
		 	Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("select Preco, Quantidade from produto where categoria = (select ID from categoria where Nome = ?)");
	            ps.setString(1, nome);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	            	return rs.getInt("Quantidade");
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
	 
	 public static float selectPreco(String nome) {
		 	Connection con = Dbh.connect();
	        PreparedStatement ps;
	        
	        try {
	            ps = con.prepareStatement("select Preco, Quantidade from produto where categoria = (select ID from categoria where Nome = ?)");
	            ps.setString(1, nome);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	            	return rs.getFloat("Preco");
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
