package lu.snt.svv.oac.ll.dpas.ml.solution.analysis;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;

import lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA;

/*
* @author Orlando AC
*
*/

public class Vectorizer extends JCasAnnotator_ImplBase {

  public static final String PARAM_VECTORS_LOCATION = "vectorsLocation";
  @ConfigurationParameter(name = PARAM_VECTORS_LOCATION, mandatory = true,
      description = "where are the sentences with the corresponding vectors")
  private String vectorsLocation;

  private long tic;
  private Logger logger = Logger.getLogger(getClass());

  @Override
  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    tic = System.currentTimeMillis();
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
	  
		int dim = 768;
		// Loop over the UoAs and compare to the sentences
		for (UoA uoa : select(aJCas, UoA.class)) {
//			System.out.println("\nuoa.getCoveredText() -> " + uoa.getCoveredText());
			// Read all the sentences to be compared and the corresponding vectors to be added to the indexes
			try (BufferedReader br = new BufferedReader(new FileReader(vectorsLocation))) {
				String line;
				String avg_vector = "";
				String[] sum_vector = new String[dim];
				for (int i=0; i<sum_vector.length; i++) {
					sum_vector[i] = "0.0";
				}
				while ((line = br.readLine()) != null) {
					// process the line
					// System.out.println("Line: " + line);
					if(line.contains("#")) {
						String[] array = line.split("#");
						String sentence = array[0];
//						String vector = array[1];
//						if(!array[1].isBlank() && !array[1].isEmpty()) {
							String[] actual_vector = array[1].split(",");
							if(sentence.startsWith("1") || sentence.startsWith("2") || sentence.startsWith("3") || sentence.startsWith("4") || sentence.startsWith("5") 
														|| sentence.startsWith("6") || sentence.startsWith("7") || sentence.startsWith("8") || sentence.startsWith("9")) {
								sentence = sentence.replace(sentence.subSequence(0, 2), "");
							}
							if(sentence.startsWith(" ")){
								sentence = sentence.replaceFirst(sentence.substring(0, 1), "");
							}
							if (uoa.getCoveredText().toLowerCase().contains(sentence.toLowerCase())) {
								for (int i = 0; i < sum_vector.length; i++) {
									sum_vector[i] = String.valueOf(Double.valueOf(sum_vector[i]) + Double.valueOf(actual_vector[i]));
//									System.out.println("\nactual_vector[" + i + "]: " + actual_vector[i]);
//									System.out.println("sum_vector[" + i + "]: " + sum_vector[i]);
								}
//								System.out.println("\nuoa.getCoveredText(): " + uoa.getCoveredText());
//								System.out.println("Sentence: " + sentence);
//								System.out.println("Vector: " + array[1]);
							}
//						}
					}
				}
				avg_vector = sum_vector[0];
				for (int i=1; i<sum_vector.length; i++) {
					avg_vector += "," + sum_vector[i];
				}
				newSentenceVector(aJCas, uoa.getBegin(), uoa.getEnd(), avg_vector, dim);
//				System.out.println("\nsum_vector.length(): " + sum_vector.length);
//				System.out.println("avg_vector: " + avg_vector);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long toc = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - tic);
		logger.info(String.format("%s: %d sec", "Time for vectorization", toc));

	}

  private void newSentenceVector(JCas jcas, int begin, int end, String vector, int dim) {
    SentenceVector sentenceVector = new SentenceVector(jcas);
    sentenceVector.setBegin(begin);
    sentenceVector.setEnd(end);
    sentenceVector.setVector(vector);
    sentenceVector.setVectorDimension(dim);
//    sentenceVector.setTransformPreporcessed(transformedPreprocessed);
//    sentenceVector.setReasonNotTransformed(reason);   
    sentenceVector.addToIndexes();
  }

}
