import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class testy6 {
    public static void main(String[] args) {
        Document doc=new Document();
        try {
            PdfCopy copy=new PdfCopy(doc,new FileOutputStream("C:\\Users\\mike\\Documents\\testowanieKopi.pdf"));
            copy.addPage(PageSize.A4,0);
            copy.close();
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
