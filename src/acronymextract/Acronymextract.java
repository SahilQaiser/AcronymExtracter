package acronymextract;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Acronymextract {

    static StringBuffer pdfText=new StringBuffer();
    public static void main(String[] args) throws IOException
    {
        try {
            PdfReader reader = new PdfReader("2016.pdf");
            int numberOfpages=reader.getNumberOfPages();
            for (int i=1;i<=numberOfpages;i++)
            {
                pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                pdfText.append("\n");
//                 pdfText =pdfText+ PdfTextExtractor.getTextFromPage(reader, i);//  i indicates the page number, 
//                 pdfText+='\n'; //separates the text of pages
            }         
            //System.out.println("File contents are: ");
            //System.out.println(pdfText);
                               
            System.out.print("The Acronyms in the file are: ");
            ArrayList<String> list=Helper.getAcronymList(pdfText.toString());
            System.out.println(list.size());
            for(String s:list)
            {
                System.out.println(s);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

