package acronymextract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper 
{    
    public static HashMap<String,String> getAcronymList(String pdfText)
    {
        ArrayList<String> acro_list=new ArrayList<>();
        String acro="";
        String regEX2="\\b[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[(][A-Z]{2,2}[s]?[)][.,]?\\s*\\b" ; // for acronyms of length 2
        String regEX3="\\b[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[(][A-Z]{3,3}[s]?[)][.,]?\\s*\\b"; // for acronyms of length 3...
        String regEX4="\\b[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[(][A-Z]{4,4}[s]?[)][.,]?\\s*\\b"; // for acronyms of length 4...
        String regEX5="\\b[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[(][A-Z]{5,5}[s]?[)][.,]?\\s*\\b"; // for acronyms of length 5...
        String regEX6="\\b[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[(][A-Z]{6,6}[s]?[)][.,]?\\s*\\b"; // for acronyms of length 6...
        String regEX7="\\b[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[A-Za-z]{2,20}\\s*-*[(][A-Z]{7,7}[s]?[)][.,]?\\s*\\b"; // for acronyms of length 7...
        
        Pattern pattern=Pattern.compile(regEX2+"|"+regEX3+"|"+regEX4+"|"+regEX5+"|"+regEX6+"|"+regEX7);  
        Matcher matcher=pattern.matcher(pdfText);
        while(matcher.find())
        {
           acro=acro+matcher.group().trim()+",";
           //System.out.println(matcher.group());
        }
        
        HashMap<String,String> uniqueAcroList= getIndividualTokens(acro,acro_list);// get invidual word, remove the duplicates
                                                                               //and save it into acrolist
        return uniqueAcroList;
    }

    private static HashMap<String,String> getIndividualTokens(String acro,ArrayList<String> acro_list)
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
        HashMap<String,String> hm=getHashMap(acro_list);
        return  hm;
    }
    private static HashMap<String,String> getHashMap(ArrayList<String> acro_list)
    {
        HashMap<String,String> acroList = new HashMap<>();
        for(String s:acro_list)
        {
            String[] words=s.split("[(]");
            if(words.length>1)
            {
                words[1]=words[1].replace(')', ' ');
            
                if(acroList.containsKey(words[1]))
                {
                    String val=words[0]+","+acroList.get(words[1]);
                    words[0]=val;
                }
                acroList.put(words[1], words[0]);
            }
        }        
        return acroList;
    }
}
