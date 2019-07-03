import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;

import javax.swing.*;
import java.io.IOException;

public class testy5 {
    public static void main(String[] args) {
        for (int i = 1; i <10 ; i++) {
            if(i%2==0){
                continue;
            }
            System.out.println(i);
        }

        try {
            PdfReader pdfReader=new PdfReader("C:\\Users\\mike\\Documents\\art%253A10.1023%252FA%253A1017948330363.pdf.pdf");

        }catch (InvalidPdfException x){
            JOptionPane.showMessageDialog(null,"Błąd odczytu");
        }

        catch (IOException e) {
            System.out.println("drugi blad");
            e.printStackTrace();
        }
        System.out.println("OK");
    }
}
