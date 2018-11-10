package acronymextract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEst 
{
    public static void main(String[] args) 
    {
        String  acro="";
       String str="M AA Aaam Aadmi Party AAP Ckjsc Jammu Kashmir (JK) ckjb cjbj JAP jbsbj cbkjasj PDP vsjjs BJP Bhajpa "; 
       String regEX="\\b[A-Z][a-z]{2,20} [A-Z][a-z]{2,20} [(][A-Z]{2,2}[)] \\b"; //for acronyms of length 2
       Pattern pattern=Pattern.compile(regEX);  
        Matcher matcher=pattern.matcher(str);
        while(matcher.find())
        { 
             acro=acro+matcher.group();
        }
        String regEX2="\\b[A-Z][a-z]{2,20} [A-Z][a-z]{2,20} [A-Z][a-z]{2,20} [(][A-Z]{3,3}[)] \\b"; // for acronyms of length 3...
        pattern=Pattern.compile(regEX2);
        matcher=pattern.matcher(str);
        while(matcher.find())
        { 
              acro=acro+matcher.group();
        }
        System.out.println(acro.trim());
    }
    
}
