/**
 * 
 */
package lu.snt.svv.oac.ll.dpas.ml.solution.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.FileHandler;

 /*
 * @author Orlando AC
 *
 */

public class EmbeddingsHandler extends JCasAnnotator_ImplBase {
  // embeddings location
  public static final String PARAM_EMBEDDINGS_LOCATION = "embeddingsLocation";
  @ConfigurationParameter(name = PARAM_EMBEDDINGS_LOCATION, mandatory = true,
      description = "the location of the embeddings")
  private String embeddingsLocation;
   
  List<String> documentVocab;
  List<String> embeddingsSharedVocab;
  List<String> embeddingsVocab;
  
  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    documentVocab = new ArrayList<String>();
    embeddingsSharedVocab = new ArrayList<String>();
    embeddingsVocab = new ArrayList<String>();
  }
  
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    for (String word: aJCas.getDocumentText().split(" "))
      documentVocab.add(word);
    
    try {  
      String line = null;

    FileReader fileReader = new FileReader(embeddingsLocation);
    @SuppressWarnings("resource")
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    while ((line = bufferedReader.readLine()) != null) {
      embeddingsSharedVocab.add(line.split(" ")[0]);
      embeddingsVocab.add(line);
    }
    } catch (IOException e) {
      e.printStackTrace();
    }
    embeddingsSharedVocab.retainAll(documentVocab);
    StringBuilder optimizedEmbeddings = new StringBuilder();
    for(String line:embeddingsVocab)
      if(embeddingsSharedVocab.contains(line.split(" ")[0])) {
        optimizedEmbeddings.append(line);
        optimizedEmbeddings.append("\n");
      }
    FileHandler.writeToFile(embeddingsLocation + "optimizedEmbeddings.txt", optimizedEmbeddings.toString());
  }

}
