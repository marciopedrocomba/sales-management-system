package views;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.*;
import javax.swing.table.*;

public class Tools {
	
	public static Font header = new Font("Arial", Font.BOLD, 30);
	public static Font header2 = new Font("Arial", Font.BOLD, 18);
	public static Font header3 = new Font("Arial", Font.BOLD, 16);
	public static Font paragraph = new Font("Arial", Font.PLAIN, 14);
	public static Font paragraph2 = new Font("Arial", Font.PLAIN, 22);
	public static Color darkBlue = new Color(46, 50, 77);
	public static Color red = new Color(176, 17, 22);
	public static Color white = new Color(255, 255, 255);
	public static Color whitesmoke = new Color(240, 240, 240);
	public static Color lightgrey = new Color(220, 220, 220);
	public static Color orange = new Color(245, 138, 8);
	public static Color black = new Color(1, 1, 1);
	public static Color blueA = new Color(51, 122, 183);
	public static Color redA = new Color(217, 83, 79);
	public static Color greenA = new Color(92, 184, 92);
	public static Color statisticColors[] = {
			new Color(255, 250, 230),
			new Color(234, 235, 236),
			new Color(248, 233, 221)
	};
	
	//metodo para poder criar icon
	public static ImageIcon createIcon(String path, int width) {
		java.net.URL url = Tools.class.getResource(path);
		
		if(url != null) {
			return new ImageIcon(
					new ImageIcon(url, "").getImage().getScaledInstance(width, width, Image.SCALE_SMOOTH), "");
		}
		
		return null;
	}
	
	public static ImageIcon createIcon(String path, int width, int height) {
		java.net.URL url = Tools.class.getResource(path);
		
		if(url != null) {
			return new ImageIcon(
					new ImageIcon(url, "").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH), "");
		}
		
		return null;
	}
	
	//metodo para criar botão com icon
	public static JButton createButton(String text, Icon icon, Color color, int width, int height, ActionListener listener) {
		JButton btn = new JButton();
		btn.setIcon(icon);
		btn.setBorder(null);
		btn.setToolTipText(text);
		btn.setBackground(color);
		btn.setPreferredSize(new Dimension(width, height));
		btn.addActionListener(listener);
		return btn;
	}
	
	public static JButton createButton(String text, Color color, int width, int height, ActionListener listener) {
		JButton btn = new JButton(text);
		btn.setBorder(null);
		btn.setBackground(color);
		btn.setPreferredSize(new Dimension(width, height));
		btn.addActionListener(listener);
		return btn;
	}
	
	public static JLabel createLabel(String text, Icon icon, Font font) {
		JLabel label = new JLabel(text);
		label.setIcon(icon);
		label.setFont(font);
		return label;
	}
	
	

    public static void searchTableContent(JTextField field, RowFilter filter, TableRowSorter trs){
        
        try {
            filter = RowFilter.regexFilter(field.getText().trim(), 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        trs.setRowFilter(filter);
    }
    
    public static void printTable(JTable table){
        MessageFormat format = new MessageFormat("");
			
        try {

                table.print(JTable.PrintMode.FIT_WIDTH, format, null);

        }catch(PrinterException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void alignRight(JLabel label) {
    	label.setHorizontalAlignment(JLabel.RIGHT);
    }
	
}
