package lu.snt.svv.oac.ll.dpas.ml.solution.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.EnglishStopLemmatizer;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.PatternRecognizer;

/*
* @author Orlando AC
*
*/

public class DataPreprocessor extends JCasAnnotator_ImplBase {

  public static final String PARAM_GENERALIZE = "generalize";
  @ConfigurationParameter(name = PARAM_GENERALIZE, mandatory = true,
      description = "whether to generalize the text or not",
      defaultValue = "true")
  private boolean generalize;

  private PatternRecognizer pr;
  private EnglishStopLemmatizer esl;

  private long tic;
  private Logger logger = Logger.getLogger(getClass());

  @Override
  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    pr = new PatternRecognizer();
    esl = new EnglishStopLemmatizer();
    tic = System.currentTimeMillis();

  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // loop over sentences
    // ignore sentences with no words
    // replace the named entities by their type
    // get lemmas, with no stopwords

    for (Sentence sent : select(aJCas, Sentence.class)) { 
    //for (PPTuple tuple : select(aJCas, PPTuple.class)) {
      String sentText = sent.getCoveredText();
      if (numTokens(sentText) < 4)
        continue;
      boolean hasNE = false;
      for (NamedEntity ne : selectCovered(NamedEntity.class, sent)) {
        //System.out.println(ne.getCoveredText()+ "_" + ne.getValue());
        if(ne.getCoveredText().length()>1 && (ne.getValue().equalsIgnoreCase("ORGANIZATION") || ne.getValue().equalsIgnoreCase("LOCATION"))) {
          hasNE = selectCovered(NamedEntity.class, sent).size()>0;
          sentText = sentText.replace(ne.getCoveredText(), "_" + ne.getValue());
          }
      }

      if (generalize)
        sentText = pr.generalize(sentText);
      StringBuilder preprocessedSent = new StringBuilder();
      // Lemmatize and generalize -lrb- -rrb-
      List<String> lemmas = esl.tokenize(sentText);
      for (String l : lemmas) {
        if (!(l.equals("-lrb-") || l.equalsIgnoreCase("-rrb-")) && l.length() > 1) {
          String str = l;
          if (str.startsWith("_"))
            str = str.replace("_", "").toUpperCase();
          preprocessedSent.append(str + " ");
        }
      }
      
      /*int begin = sent.getBegin();
      if(sent.getCoveredText().startsWith("\""))
        begin++;*/
      newUoA(aJCas, sent.getBegin(), sent.getEnd(), //begin + sent.getCoveredText().length(), 
          sentText, preprocessedSent.toString(),
          hasNE);
    }
    long toc = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-tic);
    logger.info(String.format("%s: %d sec", "Time for preprocessing and generalization", 
        toc));

  }

  private void newUoA(JCas jcas, int begin, int end, String generalSent, String preprocessedSent,
      boolean hasNE) {
    UoA unitOfAnalysis = new UoA(jcas);

    unitOfAnalysis.setBegin(begin);
    unitOfAnalysis.setEnd(end);

    unitOfAnalysis.setPreprocessed(preprocessedSent.trim());
    unitOfAnalysis.setGeneralized(generalSent.trim());
    unitOfAnalysis.setHasNE(hasNE);
    unitOfAnalysis.setHasPHONE(generalSent.contains("PHONE"));
    unitOfAnalysis.setHasEMAIL(generalSent.contains("EMAIL"));
    unitOfAnalysis.setHasWEBSITE(generalSent.contains("WEBSITE"));
    unitOfAnalysis.setHasADDRESS(generalSent.contains("ADDRESS"));
    
    /*System.out.println(generalSent);
    try {
      System.in.read();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/
    unitOfAnalysis.addToIndexes();
  }

  private int numTokens(String sentence) {
    sentence = sentence.trim();
    return sentence.split(" ").length;
  }

}
