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


public class Funcionario {
    
    public static void Insert(String BI, String Nome, String Sobrenome, String Data_Nascimento, int Telefone, String Cidade, String Municipio, String Bairro, String Rua, String Sexo, String Email, int Funcao)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
        	ps = con.prepareStatement("SELECT BI FROM funcionario where BI=?");
        	ps.setString(1, BI);
        	ResultSet rs = ps.executeQuery();
        	
        	String confirm = null;
        	
        	while(rs.next()) {
        		confirm = rs.getString("BI");
        	}
        	
        	if (BI.equals(confirm)) {
        		JOptionPane.showMessageDialog(null, "Esse BI Está associado a um Funcionario");
        	} else {
        		
        		ps = con.prepareStatement("INSERT INTO Funcionario (BI, Nome, Sobrenome, Data_nascimento, Telefone, Cidade, Municipio, Bairro, Rua, Sexo, Email, Funcao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, BI);
                ps.setString(2, Nome);
                ps.setString(3, Sobrenome);
                ps.setString(4, Data_Nascimento);
                ps.setInt(5, Telefone);
                ps.setString(6, Cidade);
                ps.setString(7, Municipio);
                ps.setString(8, Bairro);
                ps.setString(9, Rua);
                ps.setString(10, Sexo);
                ps.setString(11, Email);
                ps.setInt(12, Funcao);
                
                if (ps.executeUpdate() > 0) {
                	JOptionPane.showMessageDialog(null, "<html><p color='green'>funcionario cadastrado com sucesso</p></html>", "Alerta!", JOptionPane.INFORMATION_MESSAGE);
                }
        		
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
            ps = con.prepareStatement("SELECT * FROM Funcionario");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[12];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getInt(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getInt(12);
                
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
    
     public static void Update(String BI, String Nome, String Sobrenome, String Data_Nascimento, int Telefone, String Cidade, String Municipio, String Bairro, String Rua, String Sexo, String Email, int Funcao)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE Funcionario SET Nome=?, Sobrenome=?, data_nascimento=?, telefone=?, Cidade=?, Municipio=?, Bairro=?, Rua=?, Sexo=?, Email=?, Funcao=? WHERE BI=?" );
            ps.setString(1, Nome);
            ps.setString(2, Sobrenome);
            ps.setString(3, Data_Nascimento);
            ps.setInt(4, Telefone);
            ps.setString(5, Cidade);
            ps.setString(6, Municipio);
            ps.setString(7, Bairro);
            ps.setString(8, Rua);
            ps.setString(9, Sexo);
            ps.setString(10, Email);
            ps.setInt(11, Funcao);
            ps.setString(12, BI);
            
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
            ps = con.prepareStatement("Delete FROM Funcionario WHERE BI=?" );
            ps.setString(1, BI);
            ps.executeUpdate();
            /*if ( > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario Eliminado");
            }*/
           
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
     
    public void EncontrarFuncionario(JTable table, String valueToSearch)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Funcionario where  CONCAT('BI', 'nome', 'Sobrenome', 'Cidade', 'Municipio', 'Bairro', 'Nº_Contribuente', 'Salario')  like ? ");
            ps.setString(1, "'%"+valueToSearch+"%'");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[7];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                
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
    
    
    public static void SelectAllFuncionario(ArrayList<String> list)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM funcionario");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
				list.add(rs.getString("BI"));
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
    
    public static int countFuncionario()
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("select count(BI) as total from funcionario;");
            
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
    
    public static String selectFuncionarioNome(String bi)
    {
        Connection con = Dbh.connect();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("select * from funcionario where BI = ?;");
            ps.setString(1, bi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
				return rs.getString("Nome");
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

