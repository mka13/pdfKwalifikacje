import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        for (int i = 0; i <table.getRowCount() ; i++) {
            String linkOryginalny=(String) this.model.getValueAt(i,1);
            String linkDocelowy=linkOryginalny.substring(0,linkOryginalny.length()-4).concat("Wynik.pdf");

            try {
                if((boolean) model.getValueAt(i,10)){
                    linkDocelowy=linkOryginalny;
                }
                PdfReader pdfReader=new PdfReader(linkDocelowy);
                for (int j = 1; j <=pdfReader.getNumberOfPages() ; j++) {
                    copy.addPage(copy.getImportedPage(pdfReader,j));

                    try {
                        Thread.sleep(5);

                        progressBar.setValue(100*j/pdfReader.getNumberOfPages()+i*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadPdfFormatException e) {
                e.printStackTrace();
            }


        }
        frame.dispose();
        document.close();
        copy.close();




    }







}
