package acronymextract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper 
{    
    public static ArrayList<String> getAcronymList(String pdfText)
    {
        ArrayList<String> acro_list=new ArrayList<>();
        String acro="";
        String regEX2="\\b[A-Za-z]{2,20} [A-Za-z]{2,20} [(][A-Z]{2,2}[s]?[)] \\b" ; // for acronyms of length 2
        String regEX3="\\b[A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [(][A-Z]{3,3}[s]?[)] \\b"; // for acronyms of length 3...
        String regEX4="\\b[A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [(][A-Z]{4,4}[s]?[)] \\b"; // for acronyms of length 4...
        String regEX5="\\b[A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [(][A-Z]{5,5}[s]?[)] \\b"; // for acronyms of length 4...
        String regEX6="\\b[A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [A-Za-z]{2,20} [(][A-Z]{6,6}[s]?[)] \\b"; // for acronyms of length 4...
        
//          String regEX="\\s*[A-Z]{3,}"
//                    + "[\\s]?"
//                    + "[(]"
//                    + "\\w{3,}\\s*\\w{3,}\\s*\\w{3,}\\s*[)]\\s*" ;
        
        Pattern pattern=Pattern.compile(regEX2+"|"+regEX3+"|"+regEX4+"|"+regEX5+"|"+regEX6);  
        Matcher matcher=pattern.matcher(pdfText);
        while(matcher.find())
        {
           acro=acro+matcher.group()+",";
        }
//        pattern=Pattern.compile(regEX3);
//        matcher=pattern.matcher(pdfText);
//        while(matcher.find())
//        {
//           acro=acro+matcher.group()+",";
//        }
//        pattern=Pattern.compile(regEX4);
//        matcher=pattern.matcher(pdfText);
//        while(matcher.find())
//        {
//           acro=acro+matcher.group()+",";
//        }
//        pattern=Pattern.compile(regEX5);
//        matcher=pattern.matcher(pdfText);
//        while(matcher.find())
//        {
//           acro=acro+matcher.group()+",";
//        }
//        pattern=Pattern.compile(regEX6);
//        matcher=pattern.matcher(pdfText);
//        while(matcher.find())
//        {
//           acro=acro+matcher.group()+",";
//        }
        ArrayList<String> uninqueAcroList= getIndividualTokens(acro,acro_list);// get invidual word, remove the duplicates
                                                                               //and save it into acrolist
        return uninqueAcroList;
    }

    private static ArrayList<String> getIndividualTokens(String acro,ArrayList<String> acro_list)
    {
        String [] acro_tokens=acro.split(",");//split on the base of space b/w the words
        for (String token : acro_tokens) 
        {
            if (!acro_list.contains(token))  //only add when the token is not present in the list, i.e if the acronym is 
                                             //already present in the list ,it means duplication, so we don't add it
            {
                acro_list.add(token);
            }
        }
        return  acro_list;
    }
}
