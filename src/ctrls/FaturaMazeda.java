/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrls;
import ctrls.ItemVenda;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author we
 */
public class FaturaMazeda {

    /**
     * @param args the command line arguments
     */
    
    private static String nome;
    private static String tel;
    private static String nVenda;
    private static int qtdItens;
    private static ArrayList<ItemVenda> itens = new ArrayList<ItemVenda>();

    public static int getQtdItens() {
        return qtdItens;
    }

    public static void setQtdItens(int qtdItens) {
        FaturaMazeda.qtdItens = qtdItens;
    }
    public static void setNVenda(String valor)
    {
        FaturaMazeda.nVenda = valor;
    }

    public String getNome() {
        return nome;
    }
    public void addItens( int itens, Object[][] matriz)
    {	
        for(int i = 0; i <= itens-1; i++)
        {

           this.itens.add(new ItemVenda(matriz[i][0].toString(), Integer.parseInt(matriz[i][1].toString()), (float)Double.parseDouble(matriz[i][2].toString())));
        }
    }
    
    public static void setNome(String nome) {
        FaturaMazeda.nome = nome;
    }
    
       
    public static void fatura(String cliente, String tipoVenda, int vendaNumber, float total, float paid, float change) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        // TODO code application logic here
        //Abrindo e criando ficheiro /home/we/Desktop/fatura.pdf
      
        Rectangle pageSize = new Rectangle(28f, 520f);

        Document document = new Document(PageSize.A7, 0,0,10,30);
       
        PdfWriter writer = PdfWriter.getInstance( document, new FileOutputStream("fatura.pdf"));
        document.open();
        //Adicionando imagem do logotipo no centro na escala de 10%
         
         //Adicionando informaÃ§Ãµes de Mazeda
         Font font = new Font(FontFamily.HELVETICA, 2.3f);
         Font fontHeader = new Font(FontFamily.HELVETICA, 4f);
         Paragraph p2 = new Paragraph("Publicidade Maseda\n", fontHeader);
         p2.setIndentationLeft(5);
         p2.setSpacingAfter(10);
         document.add(p2);
         setNome(cliente);
         FaturaMazeda.tel = "938877612";
         Paragraph p1, p3, p4, p5, p6, p7;
         p1 = new Paragraph("Morada: Kilamba", font);
         p1.setSpacingAfter(5);
         
         p2 = new Paragraph("NIF: 5000172499", font);
         p2.setSpacingBefore(-5);
         p2.setSpacingAfter(5);
         
         p3 = new Paragraph("Telefone: "+tel+" / 926487850", font);
         p3.setSpacingBefore(-5);
         p3.setSpacingAfter(5);
         
         p4 = new Paragraph("Factura - Original", font);
         p4.setSpacingBefore(-5);
         p4.setSpacingAfter(5);
                
         p5 = new Paragraph("Cliente: "+ nome, font);
         p5.setSpacingBefore(-5);
         p5.setSpacingAfter(5);
         
         //document.add(p1);
         document.add(p2);
         document.add(p3);
         document.add(p4);
         document.add(p5);

         //Adicionando numero da venda e data a tabela

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(22);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        Paragraph p = new Paragraph("Venda a " + tipoVenda, font);
        p.setAlignment(Element.ALIGN_CENTER);
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(2);
        table.addCell(cell);
        FaturaMazeda.nVenda = "" + vendaNumber;
        p = new Paragraph("Venda nº:"+FaturaMazeda.nVenda, font);
        table.addCell(p);
        Calendar dataActual = Calendar.getInstance();
        
        int day = dataActual.get(dataActual.DAY_OF_MONTH);
        String dayEl = "";
        
        if(day > 0 && day < 10) {
        	dayEl += "0" + day;
        }else {
        	dayEl += day;
        }
        
        int month = dataActual.get(dataActual.MONTH);
        String monthEl = "";
        
        if(month > 0 && month < 10) {
        	monthEl += "0" + month;
        }else {
        	monthEl += month;
        }
        
        p = new Paragraph("Data "+dayEl+"-"
                +monthEl+"-"
                +dataActual.get(dataActual.YEAR)+"\n"+
                "Hora "+String.format("%02d",dataActual.get(dataActual.HOUR_OF_DAY))+
                ":"+String.format("%02d",dataActual.get(dataActual.MINUTE))+
                ":"+String.format("%02d",dataActual.get(dataActual.SECOND)), font);
        
        table.addCell(p);

        document.add(table);
        document.add(Chunk.NEWLINE);
        
        
        
        PdfPTable table1 = new PdfPTable(3);
        table1.setWidthPercentage(22);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table1.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        p = new Paragraph("Produto", font);
        table1.addCell(p);
        p = new Paragraph("Qtd.", font);
        table1.addCell(p);
        p = new Paragraph("Preço unit", font);
        table1.addCell(p);
        
        
        for (ItemVenda iten : itens) {
        	p = new Paragraph("" + iten.getDescricao(), font);
            table1.addCell(p);
            p = new Paragraph("" + iten.getQuantidade(), font);
            table1.addCell(p);
            p = new Paragraph("" + String.format("%.2fkz", (iten.getPreco() / iten.getQuantidade())), font);
            table1.addCell(p);
        }
        
        
        document.add(table1);
        
        document.add(Chunk.NEWLINE);
        
        p = new Paragraph("Total: " + String.format("%.2fkz", total), font);
        p5.setSpacingBefore(5);
        p5.setSpacingAfter(5);
        
        document.add(p);
        
        p = new Paragraph("Pago: " + String.format("%.2fkz", paid), font);
        p5.setSpacingBefore(5);
        p5.setSpacingAfter(5);
        
        document.add(p);
        
        p = new Paragraph("Troco: " + String.format("%.2fkz", change), font);
        p5.setSpacingBefore(5);
        p5.setSpacingAfter(5);
        
        document.add(p);
        

        itens.clear();
        
        document.close();
    }
    
}

