package lu.snt.svv.oac.ll.dpas.ml.solution.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* @author Orlando AC
*
*/
public class PatternRecognizer {

  private static Pattern EMAIL_ADDRESS;
  private static Pattern PHONE;
  private static Pattern WEB_URL;
  private static Pattern ADDRESS;
  private static Pattern OTHER;
  private static Pattern OTHER2;
  //private static Pattern OTHER3;

  public PatternRecognizer() {
    EMAIL_ADDRESS = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" 
    							+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." 
    							+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

    PHONE = Pattern.compile("[\\(]*[+|00][0-9]+[\\)]*[0-9\\s]{7,10}[[-][0-9]]*");
    
    WEB_URL = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    ADDRESS = Pattern.compile("[_A-Z]+" + "[,’|'\\s]" + "[0-9]+-{1,3}");
    
    OTHER = Pattern.compile("[-a-zA-Z]+" + "[0-9]+-{1,3}" + "[\\s]" + "[0-9]+-{1,3}");
    
    OTHER2 = Pattern.compile("[-a-zA-Z]+" + "[\\s]" + "[0-9]+-{2,3}");
    
    //OTHER3 = Pattern.compile("[0-9]+-{1,3}" + "[\\s]" + "[-a-zA-Z]+");
  }

  public String generalize(String sentence) {
    sentence = generalizeWebsite(sentence);
    sentence = generalizeEmail(sentence);
    sentence = generalizePhone(sentence);
    sentence = generalizeAddress(sentence);
    sentence = generalizeOther(sentence);
    sentence = generalizeOther2(sentence);
   // sentence = generalizeOther3(sentence);
    return sentence;
  }

  private String generalizeWebsite(String sentence) {
    Matcher matcher = WEB_URL.matcher(sentence);
    List<String> websites = new ArrayList<String>();
    while (matcher.find())
      websites.add(sentence.substring(matcher.start(), matcher.end()));
    for (String w : websites)
      sentence = sentence.replace(w, " _WEBSITE ");
    return sentence;
  }

  private String generalizeEmail(String sentence) {
    Matcher matcher = EMAIL_ADDRESS.matcher(sentence);
    List<String> emails = new ArrayList<String>();
    while (matcher.find())
      emails.add(sentence.substring(matcher.start(), matcher.end()));
    for (String w : emails)
      sentence = sentence.replace(w, " _EMAIL ");
    return sentence;
  }

  private String generalizePhone(String sentence) {
    Matcher matcher = PHONE.matcher(sentence);
    List<String> phones = new ArrayList<String>();
    while (matcher.find())
      phones.add(sentence.substring(matcher.start(), matcher.end()));
    for (String w : phones) {
      sentence = sentence.replace(w, " _PHONE ");
    }
    return sentence;
  }
  
  private String generalizeAddress(String sentence) {
    Matcher matcher = ADDRESS.matcher(sentence);
    List<String> addresses = new ArrayList<String>();
    while (matcher.find())
      addresses.add(sentence.substring(matcher.start(), matcher.end()));
    for (String w : addresses)
      sentence = sentence.replace(w, " _ADDRESS ");
    return sentence;
  }
  
  private String generalizeOther(String sentence) {
	 Matcher matcher = OTHER.matcher(sentence);
	 List<String> addresses = new ArrayList<String>();
	 while (matcher.find())
	   addresses.add(sentence.substring(matcher.start(), matcher.end()));
	 for (String w : addresses)
	   sentence = sentence.replace(w, " _ADDRESS ");
	 return sentence;
  }

  private String generalizeOther2(String sentence) {
	 Matcher matcher = OTHER2.matcher(sentence);
	 List<String> addresses = new ArrayList<String>();
	 while (matcher.find())
	   addresses.add(sentence.substring(matcher.start(), matcher.end()));
	 for (String w : addresses)
	   sentence = sentence.replace(w, " _ADDRESS ");
	 return sentence;
  }
  
  /*private String generalizeOther3(String sentence) {
		 Matcher matcher = OTHER3.matcher(sentence);
		 List<String> addresses = new ArrayList<String>();
		 while (matcher.find())
		   addresses.add(sentence.substring(matcher.start(), matcher.end()));
		 for (String w : addresses)
		   sentence = sentence.replace(w, " _ADDRESS ");
		 return sentence;
  }*/

}
