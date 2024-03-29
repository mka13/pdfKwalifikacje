import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class TestyPdfThread implements Runnable {

    Thread thread;

    JTable table;
    DefaultTableModel model;
    Map<String,plikPdf>listaPlikow;


    public TestyPdfThread(JTable table, DefaultTableModel model,Map<String,plikPdf>listaPlikow) {
        this.thread =new Thread(this,"pdf");
        this.table=table;
        this.model=model;
        this.listaPlikow=listaPlikow;
        thread.start();

    }

    @Override
    public void run() {
        JFrame frame=new JFrame();
        frame.setBounds(500,500,200,50);
        JProgressBar progressBar=new JProgressBar();
        frame.add(progressBar);
        progressBar.setBounds(0,0,200,50);
        progressBar.setMinimum(0);
        progressBar.setMaximum(table.getRowCount()*100);
        frame.setUndecorated(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.BLACK);
        progressBar.setForeground(Color.GREEN);
        frame.setVisible(true);


        for (int i = 0; i <table.getRowCount() ; i++) {
            if((boolean)(model.getValueAt(i,1))){
            if(!walidacja(table,i,model)){
                JOptionPane.showMessageDialog(null,"Za mało danych");
                break;
            }


            try {

                String linkOryginalny=(String) this.model.getValueAt(i,3);
                plikPdf plikPdf = listaPlikow.get(linkOryginalny);
                Set<Format> formaty = plikPdf.getFormaty();
                String linkDocelowy=linkOryginalny.substring(0,linkOryginalny.length()-4).concat("Wynik.pdf");
                Integer wielkoscCzionki= (Integer)this.model.getValueAt(i,6);

                BaseColor kolor=ustawienieKoloruCzcionki((String) model.getValueAt(i,5));
                BaseFont baseFont=BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.CP1250,BaseFont.EMBEDDED);
               String transparentnosc = String.valueOf( (Double) model.getValueAt(i,7));
                transparentnosc=transparentnosc.concat("f");
                String text=(String)model.getValueAt(i,4);

                com.itextpdf.text.Font font=new com.itextpdf.text.Font(baseFont,wielkoscCzionki,Font.NORMAL,kolor);
                Phrase znakWodny = null;
                PdfReader pdfReader=new PdfReader(linkOryginalny);
                PdfStamper pdfStamper=null;
                try {
                     pdfStamper=new PdfStamper(pdfReader,new FileOutputStream(linkDocelowy));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Zamknij plik ".concat(linkDocelowy));
                    frame.dispose();
                    return;
                }



                for (int j = 1; j <= pdfReader.getNumberOfPages(); j++) {



                    if((boolean)model.getValueAt(i,0)){
                        znakWodny=new Phrase(text + " str. "+ j,font);}
                    else{
                        znakWodny=new Phrase(text ,font);
                    }


                    String format = String.valueOf(pdfReader.getPageSize(j));
                    Format format1 = plikPdf.znajdzFormat(format);



                    PdfContentByte overContent = pdfStamper.getOverContent(j);
                    overContent.saveState();
                    PdfGState ustawienieWizualneStronyPdf = new PdfGState();

                    ustawienieWizualneStronyPdf.setFillOpacity(Float.valueOf(transparentnosc));
                    overContent.setGState(ustawienieWizualneStronyPdf);


                    ColumnText.showTextAligned(overContent, Element.ALIGN_LEFT,znakWodny,format1.getX(),format1.getY(),0);


                    overContent.restoreState();
                    try {
                        Thread.sleep(5);

                        progressBar.setValue(100*j/pdfReader.getNumberOfPages()+i*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                pdfReader.close();
                pdfStamper.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }


        }}
        frame.dispose();







    }
    private static BaseColor ustawienieKoloruCzcionki (String text){
        BaseColor bufor;
        switch (text){

            case "Czarny":
                bufor= BaseColor.BLACK;
                break;
            case "Niebieski":
                bufor= BaseColor.BLUE;
                break;
            case "Czerwony":
                bufor= BaseColor.RED;
                break;
            case "Szary":
                bufor= BaseColor.GRAY;
                break;
            default:
                bufor= BaseColor.BLACK;



        }
        return  bufor;



    }
    private static boolean walidacja(JTable table,int row,DefaultTableModel model) {
        for (int i = 0; i <table.getColumnCount() ; i++) {
            if(model.getValueAt(row,i)==null || model.getValueAt(row,i)==""){
                return false;
            }
        }
        return true;
    }


}
