import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class FetchingDatafromPDF
{

    public static void main(String[] args) throws Throwable 
    {
		    	try
		    	{
			        File file = new File("./src/test/resources/Gracy KI_report.pdf");
			        PDDocument doc = PDDocument.load(file);
			        int pages = doc.getNumberOfPages();
			        System.out.println(pages);
			
			        PDFTextStripper pdfData = new PDFTextStripper();
			        String readData = pdfData.getText(doc);
			        System.out.println(readData);
			        
			        
			       // pdfData.setStartPage(2);
			      //  pdfData.setEndPage(2);
			       // String readData1 = pdfData.getText(doc);
			        // System.out.println(readData1);
			
			        pdfData.setStartPage(2);
			        
			        pdfData.setEndPage(4);
			        
			        String readData2 = pdfData.getText(doc);
			        System.out.println(readData2);
		    	}

    
    catch (Exception e)
    	{
           e.printStackTrace();
        }
    }
}
    