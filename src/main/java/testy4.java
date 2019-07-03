import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class testy4 {
    public static void main(String[] args) {
        try {
            PdfReader reader=new PdfReader("C:\\Users\\mike\\Documents\\Leistungsübersicht.pdf");
            System.out.println(reader.isEncrypted());
            if (reader.isEncrypted()){
                int odp = JOptionPane.showConfirmDialog(null, "Plik chroniony.Odblokowac?", "Plik chroniony", JOptionPane.YES_NO_OPTION);
                if(odp==JOptionPane.YES_OPTION){
                    PdfReader.unethicalreading=true;
                    PdfStamper stamper=new PdfStamper(reader,new FileOutputStream("C:\\Users\\mike\\Documents\\Leistungsübersicht2.pdf"));
                    stamper.close();
                    reader.close();
                }else {
                    reader.close();
                    return;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }



}
