import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class testy {
    public static void main(String[] args) throws IOException, DocumentException {
        PdfReader pdfReader=new PdfReader("C:\\Users\\mike\\Documents\\Masterarbeit_Michal KaczkowskiWynik.pdf");
        PdfStamper pdfStamper = new PdfStamper(pdfReader,new FileOutputStream("C:\\Users\\mike\\Documents\\masterarbeit.pdf"));

        BaseFont baseFont=BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.CP1250,BaseFont.EMBEDDED);


        com.itextpdf.text.Font font=new com.itextpdf.text.Font(baseFont,14,Font.NORMAL,BaseColor.RED);
        Phrase znakWodny = new Phrase("testy",font);
        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            PdfContentByte overContent = pdfStamper.getOverContent(i);
            overContent.saveState();
            ColumnText.showTextAligned(overContent,Element.ALIGN_LEFT,znakWodny,100,50,0);
            overContent.restoreState();

        }
    pdfReader.close();
        pdfStamper.close();
    }
}
