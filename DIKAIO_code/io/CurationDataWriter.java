package lu.snt.svv.oac.ll.dpas.ml.solution.io;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasConsumer_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.PPTuple;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.FileHandler;

/*
* @author Orlando AC
*
*/
public class CurationDataWriter extends JCasConsumer_ImplBase {

  public static final String PARAM_CURATION_PATH = "curationPath";
  @ConfigurationParameter(name = PARAM_CURATION_PATH, mandatory = true,
      description = "path to write intermediary files for curation",
      defaultValue = "src/main/resources/")
  private String curationPath;
  
  private Map<String, Set<String>> perMetadataContent;
  Map<String, List<String>> invalid;
  Map<String, Integer> invalidResults;

  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    perMetadataContent = new HashMap<String, Set<String>>();
    invalid = new HashMap<String, List<String>>();
    invalidResults = new HashMap<String, Integer>();
  }
  
  

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
//		String docName = "Document Name";
    String docName = DocumentMetaData.get(aJCas.getCas()).getDocumentTitle();
    for (PPTuple tuple : select(aJCas, PPTuple.class)) {
      
        Set<String> m1Content = new HashSet<String>();        
        Set<String> m1OContent = new HashSet<String>();

        Set<String> m2Content = new HashSet<String>();
        Set<String> m3Content = new HashSet<String>();
        Set<String> m4Content = new HashSet<String>();

        String m1 = tuple.getMetadata1();
        String m2 = tuple.getMetadata2();
        String m3 = tuple.getMetadata3();
        String m4 = tuple.getMetadata4();

        try {
			if(!checkValidity(m1,m2,m3,m4)) {
			  if(invalidResults.get(m1+","+m2+","+m3+","+m4)==null) 
			    invalidResults.put(m1+","+m2+","+m3+","+m4, 1);
			  else 
			    invalidResults.put(m1+","+m2+","+m3+","+m4, invalidResults.get(m1+","+m2+","+m3+","+m4)+1);
			  
			  List<String> tuples = new ArrayList<String>();
			  if(invalid.get(docName)==null) {
			    tuples.add(tuple.getCoveredText());
			    invalid.put(docName, tuples);
			  } else {
			    tuples.addAll(invalid.get(docName));
			    tuples.add(tuple.getCoveredText());
			    invalid.put(docName, tuples);
			  }
			  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Metadata1
        if(perMetadataContent.get(m1)==null) {
          m1Content.add(tuple.getText());
          perMetadataContent.put(m1, m1Content);
        }else {
          m1Content.addAll(perMetadataContent.get(m1));
          m1Content.add(tuple.getText());
          perMetadataContent.put(m1, m1Content);
        }
        // Metadata1-only
        if(!m1.equals("N/A") && m2.equalsIgnoreCase("x") && m3.equalsIgnoreCase("x") && m4.equalsIgnoreCase("x")) {
          String m1Only = m1+"_only";
        if(perMetadataContent.get(m1Only)==null) {
          m1OContent.add(tuple.getText());
          perMetadataContent.put(m1Only, m1OContent);
        }else {
          m1OContent.addAll(perMetadataContent.get(m1Only ));
          m1OContent.add(tuple.getText());
          perMetadataContent.put(m1Only, m1OContent);
        }
        }
        // Metadata2
        if(!m2.equalsIgnoreCase("x")) {
          if(perMetadataContent.get(m2)==null) {
            m2Content.add(tuple.getText());
          perMetadataContent.put(m2, m2Content);
        }else {
          m2Content.addAll(perMetadataContent.get(m2));
          m2Content.add(tuple.getText());
          perMetadataContent.put(m2, m2Content);
        }
        }
        // Metadata3
        if(!m3.equalsIgnoreCase("x")) {
        if(perMetadataContent.get(m3)==null) {
          m3Content.add(tuple.getText());
          perMetadataContent.put(m3, m3Content);
        }else {
          m3Content.addAll(perMetadataContent.get(m3));
          m3Content.add(tuple.getText());
          perMetadataContent.put(m3, m3Content);
        }
        }
        // Metadata4
        if(!m4.equalsIgnoreCase("x")) {
        if(perMetadataContent.get(m4)==null) {
          m4Content.add(tuple.getText());
          perMetadataContent.put(m4, m4Content);
        }else {
          m4Content.addAll(perMetadataContent.get(m4));
          m4Content.add(tuple.getText());
          perMetadataContent.put(m4, m4Content);
        }
      }
    }
  }

  private boolean checkValidity(String m1, String m2, String m3, String m4) throws IOException {
	String dataset = "testing/";	//training, testing
    String combination = m1+";"+m2+";"+m3+";"+m4;
    //Current Path
    List<String> valid = FileHandler.readFromFile(curationPath.replaceFirst("curation/" + dataset, "valid/validannotations"));
    if(!valid.contains(combination)) {
      return false;
      }
    return true;
  }

  @Override
  public void collectionProcessComplete() {
    // writing the multiple curation content into files
    StringBuilder misclassifiedContent = new StringBuilder();
    
    for(Entry<String, List<String>> entry : invalid.entrySet()) {
      misclassifiedContent.append("File: " + entry.getKey());
      misclassifiedContent.append("\n");
      List<String> sentences = new ArrayList<String>(entry.getValue());
      for(String sent: sentences) 
      {  
        misclassifiedContent.append("\t" + sent);      
        misclassifiedContent.append("\n");
      }
      misclassifiedContent.append("\n");
    }
    FileHandler.writeToFile(curationPath + "missed.txt", misclassifiedContent.toString());
    for(Entry<String, Set<String>> entry : perMetadataContent.entrySet()) {
      StringBuilder curationContent = new StringBuilder();
      List<String> sentences = new ArrayList<String>(entry.getValue());
      for(String sent: sentences) 
      {  
        curationContent.append(sent);
        curationContent.append("\n");
      }
      String metadata = entry.getKey();
      if(metadata.equals("N/A")) {
        metadata = "NA";
        continue;
      }
      FileHandler.writeToFile(curationPath + metadata, curationContent.toString());
    }
    for(Entry<String, Set<String>> entry : perMetadataContent.entrySet()) {
      System.out.println(entry.getKey() + " = " + entry.getValue().size());
    }
    System.out.println("FINISHED");
  }


}
