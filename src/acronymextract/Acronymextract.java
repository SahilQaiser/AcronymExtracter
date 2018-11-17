package acronymextract;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Acronymextract {

    static StringBuilder pdfText=new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        try {
            PdfReader reader = new PdfReader(args[2]);
            int numberOfpages=reader.getNumberOfPages();
            for (int i=1;i<=numberOfpages;i++)
            {
                pdfText.append(PdfTextExtractor.getTextFromPage(reader, i));
                pdfText.append("\n");
            }         
            //String text="Aam Admi Party (AAP) All india Radio (AIR) this is just a normal aam aadmi party (AAP) it can also mean Aasan Aasan Project (AAP) All India Reports (AIR) juts a sample it was ";
            System.out.print("The Acronyms in the file are: \n");
            HashMap<String,String> list=Helper.getAcronymList(pdfText.toString());
            System.out.println("No. of Acronyms Found : "+list.size());
            FileWriter fw = new FileWriter("AcronymsExtracted.csv");
            fw.write("File Name : "+args[0]+"\n"+"No. of Acronyms Found : "+list.size()+"\n");
            fw.write("Acronyms, FullForms\n");
            for(Map.Entry m:list.entrySet())
            {
                String word=m.getKey().toString();
                String def=m.getValue().toString();
                System.out.println(word+" : "+def);
                fw.write(word+","+def+"\n");
            }
            fw.flush();
            fw.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

