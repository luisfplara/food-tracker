/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class GenerateBarcodes {
 
 
 public void createPDF (String DataE, String Cod, int qtdSelos){
  
  Document doc = new Document();
  PdfWriter docWriter = null;
  int row=2;
  try {
   String path = "C:\\PDFsRastreador\\" + Cod+".pdf";
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
   doc.addAuthor("RTRDR");
   doc.addCreationDate();
   doc.addProducer();
   doc.addTitle("Selos");
   doc.setPageSize(PageSize.LETTER);
   
   doc.open();
   PdfContentByte cb = docWriter.getDirectContent();
    
   String myString = "http://rastreadordebatata.pe.hu/index.php?cod="+Cod;
            boolean aux = false;
          int x = 20;
          int y = 630;
     
          if(qtdSelos%2==0){
              qtdSelos=qtdSelos/2;
          }
          else {
              qtdSelos++;
              qtdSelos=qtdSelos/2;
               aux = true;
          }
     
          for (int i =0; i<qtdSelos; i++){
             x = 20;
             
             if(i%5==0){
                 doc.newPage();
                 y=630;
             }
         
             if(i==qtdSelos-1&&aux){
                 row=1;
             }
               for (int u =0; u<row; u++){
                    drawRectangle(cb, 280, 130, x, y);
                    qrGenerator(myString, doc,x+190,y+20);
                    
                    PlaceChunck(cb,"BATATA DOCE",x+55, y+50,2,15);
                    PlaceChunck(cb,"CAIXA PLASTICA - 20KG",x+39, y+35,2,12);
                    PlaceChunck(cb,"DATA DE ENTREGA: ",x+5, y+15,1,9);
                    PlaceChunck(cb,DataE,x+95, y+15,1,9);
                    PlaceChunck(cb,"CODIGO DE RASTREAMENTO: ",x+5, y+5,1,9);
                    PlaceChunck(cb,Cod,x+140, y+5,1,9);
                    PlaceChunck(cb,"Manoel Pinto",x+5, y+110,2,12);
                    PlaceChunck(cb,"ESTRADA GERAL COSTA DA LAGOA",x+5, y+100,1,9);
                    PlaceChunck(cb,"SOMBRIO - SC",x+5, y+90,1,9);
                    PlaceChunck(cb,"TELEFONE: ",x+5, y+80,1,9);
                    PlaceChunck(cb,"(48) 99966-7648 ",x+56, y+80,1,9);
                    
                    x+=290;
               }
             y -= 140;  
             row=2;
             
          }
          
       
         Desktop.getDesktop().open( new File( "C:\\PDFsRastreador\\"+Cod+".pdf" ) );
          
  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
  }
  finally
  {
   if (doc != null)
   {
    doc.close();
   }
   if (docWriter != null)
   {
    docWriter.close();
   }
  }
 }
 
 public static void drawRectangle(PdfContentByte content, float width, float height, float x, float y) {
    content.saveState();
    PdfGState state = new PdfGState();
    state.setFillOpacity(0f);
    content.setGState(state);
    content.setRGBColorFill(0xFF, 0xFF, 0xFF);
    content.setLineWidth(1);
    content.rectangle(x, y, width, height);
    content.fillStroke();
    content.restoreState();
}
 public void qrGenerator (String link, Document doc, int x, int y) throws DocumentException{
     BarcodeQRCode qrcode = new BarcodeQRCode(link.trim(), 1, 1, null);
         Image qrcodeImage;
     try {
         qrcodeImage = qrcode.getImage();
         qrcodeImage.setAbsolutePosition(x,y);
         qrcodeImage.scalePercent(220);
         doc.add(qrcodeImage);
     } catch (BadElementException ex) {
         Logger.getLogger(GenerateBarcodes.class.getName()).log(Level.SEVERE, null, ex);
     }
        
         
 }
 private void PlaceChunck(PdfContentByte content,String text, int x, int y, int tip, int size) {    

     try {
          BaseFont bf = null;
         if (tip == 1){
             bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
         }
         if(tip == 2){
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); 
         }
        content.saveState();
        content.beginText();
        content.moveText(x, y);
        content.setFontAndSize(bf, size);
        content.showText(text);
        content.endText();
        content.restoreState();
     } catch (DocumentException ex) {
         Logger.getLogger(GenerateBarcodes.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(GenerateBarcodes.class.getName()).log(Level.SEVERE, null, ex);
     }

    }
}
