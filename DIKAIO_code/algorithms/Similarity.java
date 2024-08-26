package lu.snt.svv.oac.ll.dpas.ml.solution.algorithms;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;

import lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA;

/*
* @author Orlando AC
*
*/

public class Similarity extends JCasAnnotator_ImplBase {

	public static final String PARAM_VECTORS_LOCATION = "vectorsLocation";
	@ConfigurationParameter(name = PARAM_VECTORS_LOCATION, mandatory = true, 
			description = "where are the requirements with the corresponding vectors")
	private String vectorsLocation;
	
	private long tic;
	private Logger logger = Logger.getLogger(getClass());
	
	public void initialize(final UimaContext context) throws ResourceInitializationException {
		super.initialize(context);
		tic = System.currentTimeMillis();
	}
	
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// for each uoa predict the metadata using cosine distance
		int dim = 768;
		Double sim;
		String line;
		Double[] sent_vector = new Double[dim];
		Double[] req_vector = new Double[dim];
		for (int i=0; i<req_vector.length; i++) {
			req_vector[i] = 0.0;
			sent_vector[i] = 0.0;
		}
		for (UoA uoa : select(aJCas, UoA.class)) {
			int lineCounter = 0;
			try {
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(vectorsLocation));
				while ((line = br.readLine()) != null) {
					lineCounter++;
					String[] array = line.split("#");
//					String requirement = array[0];
					String[] requirement_vector = array[1].split(",");
					for(int i = 0 ; i< requirement_vector.length ; i++) {
						req_vector[i] = Double.valueOf(requirement_vector[i]);
					}
					for (SentenceVector sv : selectCovered(SentenceVector.class, uoa)) {
						String[] sentence_vector = sv.getVector().split(",");
						for(int i=0; i<sentence_vector.length; i++) {
							sent_vector[i] = Double.valueOf(sentence_vector[i]);
						}
					}
					sim = cosineSimilarity(req_vector, sent_vector);
					if(sim>0.5) {
						newSimilarityResult(aJCas, uoa.getBegin(), uoa.getEnd(), sim, "R" + String.valueOf(lineCounter));
					}
//					System.out.println("\nSentence: " + uoa.getCoveredText());
//					System.out.println("Requirement: " + "R" + String.valueOf(lineCounter) + " -> " + requirement);
//					System.out.println("Similarity: " + sim);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long toc = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - tic);
		logger.info(String.format("%s: %d sec", "Time for similarity + ml", toc));
	}
	
	private static double cosineSimilarity(Double[] req_vector, Double[] sent_vector) {
        double dotProduct = dotProduct(req_vector, sent_vector);
        double euclideanDist = euclideanDistance(req_vector) * euclideanDistance(sent_vector);
        return dotProduct / euclideanDist;
    }
	
	private static double dotProduct(Double[] vector1, Double[] vector2) {
        double result = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            result += vector1[i] * vector2[i];
        }
        return result;
    }

    private static double euclideanDistance(Double[] vector) {
        double result = 0.0;
        for (double aVector : vector) {
            result += aVector * aVector;
        }
        return Math.sqrt(result);
    }
	
	private void newSimilarityResult(JCas aJCas, int begin, int end, Double similariry, String requirement) {
		SimilarityResults similarityResults = new SimilarityResults(aJCas);
		similarityResults.setBegin(begin);
		similarityResults.setEnd(end);
		similarityResults.setSimilarity(similariry);
		similarityResults.setRequirement(requirement);
		similarityResults.addToIndexes();
	}

}
