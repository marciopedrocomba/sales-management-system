package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioDialog extends JDialog{
	
	private JDateChooser dateChooserDe = new JDateChooser();
	private JDateChooser dateChooserAte = new JDateChooser();
	private JButton btnImprimir = new JButton("Imprimir");
	
	public RelatorioDialog() {
		super(null, "Impremir relátorio", Dialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		setSize(340, 160);
		this.addListeners();
		this.setLocationRelativeTo(null);
		this.createWindow();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void createWindow() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Tools.white);
		panel.add(this.windowContent());
		add(panel);
	}
	
	private JPanel windowContent() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		addComponent(panel, Tools.createLabel("De:", null, Tools.paragraph), 10, 10, 100, 30);
		addComponent(panel, dateChooserDe, 10, 40, 100, 30);
		addComponent(panel, Tools.createLabel("Ate:", null, Tools.paragraph), 210, 10, 100, 30);
		addComponent(panel, dateChooserAte, 210, 40, 100, 30);
		addComponent(panel, this.btnImprimir, 110, 80, 100, 30);
		return panel;
	}
	
	private void addComponent(Container container, JComponent component, int x, int y,
			int width, int height) {
		component.setBounds(x, y, width, height);
		container.add(component);
	}
	
	private void addListeners() {
		btnImprimir.addActionListener((e) -> {
		 Connection con = (Connection) ctrls.Dbh.connect();
	        
	        int resposta = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste documento?", "DANGER!", JOptionPane.YES_NO_OPTION);
	        
	        if(resposta == JOptionPane.YES_OPTION){
	            try {
	                JasperPrint print = JasperFillManager.fillReport("H:/reports/Relatorio_de_Venda.jasper",null,con);
	                
	                JasperViewer.viewReport(print, false);
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, ex);
	            }
	        }
		});
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new RelatorioDialog().setVisible(true);
		});	
	}
}
