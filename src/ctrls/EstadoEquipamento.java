package ctrls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EstadoEquipamento {
	
	public static void Insert(String Nome)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO estado_equipamento(Nome) VALUES (?)");
            
            ps.setString(1, Nome);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item Adicionado Com Sucesso");
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
	
	public static void Update(int id, String nome)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE estado_equipamento SET  Nome=? WHERE ID=?" );
            ps.setString(1, nome);
            ps.setInt(2, id);
            
            
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
            ps = con.prepareStatement("Delete FROM estado_equipamento  WHERE ID=?" );
            ps.setInt(1, ID);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "estado de Equipamento Removido Com Sucesso");
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
	
	public static void SelectAllEstado(ArrayList<String> list)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM estado_equipamento");
            
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
	
	 public static int SelectEstadoId(String value)
	    {
	        Connection con = Dbh.connect();
	        PreparedStatement ps;
	        int id = 0;
	        
	        try {
	            ps = con.prepareStatement("SELECT ID FROM estado_equipamento where Nome=?");
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
	            ps = con.prepareStatement("SELECT ID FROM estado_equipamento");
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
