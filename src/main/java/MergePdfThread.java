import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MergePdfThread implements Runnable {


    Thread thread;
    JTable table;
    String link;
    DefaultTableModel model;
    public MergePdfThread(JTable table, String link,DefaultTableModel model) {
        this.thread = new Thread(this);
        this.table=table;
        this.link=link;
        this.model=model;
        this.thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i <table.getRowCount() ; i++) {
            String linkOryginalny=(String) this.model.getValueAt(i,3);
            String linkDocelowy=linkOryginalny.substring(0,linkOryginalny.length()-4).concat("Wynik.pdf");
            File file=new File(linkDocelowy);
            if (!file.exists() && !(boolean) this.model.getValueAt(i,2)){
                JOptionPane.showMessageDialog(null,"Nie istnieje plik ze znakiem wodnym dla " + linkOryginalny);
                return;
            }
        }


        Document document=new Document();
        PdfCopy copy= null;
        try {
            copy = new PdfCopy(document,new FileOutputStream(link));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        JFrame frame=new JFrame();
        frame.setBounds(500,550,200,50);
        JProgressBar progressBar=new JProgressBar();
        frame.add(progressBar);
        progressBar.setBounds(0,0,200,50);
        frame.setUndecorated(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(table.getRowCount()*100);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.BLACK);
        progressBar.setForeground(Color.GREEN);
        frame.setVisible(true);
        PdfReader pdfReader=null;
        for (int i = 0; i <table.getRowCount() ; i++) {
            String linkOryginalny=(String) this.model.getValueAt(i,3);
            String linkDocelowy=linkOryginalny.substring(0,linkOryginalny.length()-4).concat("Wynik.pdf");
            Integer minStrona;
            Integer maxStrona;
            try {
                if((boolean) model.getValueAt(i,2)){
                    linkDocelowy=linkOryginalny;
                }
                pdfReader=new PdfReader(linkDocelowy);
                if( model.getValueAt(i,8)!=null && (Integer) model.getValueAt(i,8)!=0 && (Integer) model.getValueAt(i,8)<=pdfReader.getNumberOfPages() ){
                    minStrona= (Integer) model.getValueAt(i,8);
                }else{
                    minStrona=1;
                }
                if(model.getValueAt(i,9)!=null && (Integer) model.getValueAt(i,9)!=0 && (Integer) model.getValueAt(i,9)<pdfReader.getNumberOfPages() ){
                    maxStrona=(Integer) model.getValueAt(i,9);
                }else{
                    maxStrona=pdfReader.getNumberOfPages();
                }


                for (int j = minStrona; j <=maxStrona ; j++) {
                    copy.addPage(copy.getImportedPage(pdfReader,j));

                    try {
                        Thread.sleep(5);

                        progressBar.setValue(100*j/maxStrona+i*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pdfReader.close();

            } catch (IOException e) {
               JOptionPane.showMessageDialog(null,"Plik " + linkDocelowy + " nie został wczytany");
                File file=new File(link);


                if (file.exists()){
                    file.delete();
                }
               frame.dispose();
               return;

            } catch (BadPdfFormatException e) {
                e.printStackTrace();
            }


        }
        frame.dispose();
        copy.close();
        document.close();





    }







}
