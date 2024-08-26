package lu.snt.svv.oac.ll.dpas.ml.solution.pipelines;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.uimafit.factory.AnalysisEngineFactory.createPrimitiveDescription;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.uimafit.component.xwriter.CASDumpWriter;

import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;
import lu.snt.svv.oac.ll.dpas.ml.solution.algorithms.MetadataPredictorV4;
import lu.snt.svv.oac.ll.dpas.ml.solution.algorithms.MultiBinaryClassifier;
import lu.snt.svv.oac.ll.dpas.ml.solution.algorithms.Similarity;
import lu.snt.svv.oac.ll.dpas.ml.solution.algorithms.SimilarityBasedClassification;
import lu.snt.svv.oac.ll.dpas.ml.solution.analysis.DataPreprocessor;
import lu.snt.svv.oac.ll.dpas.ml.solution.analysis.KeywordsContainer;
import lu.snt.svv.oac.ll.dpas.ml.solution.analysis.Vectorizer;
import lu.snt.svv.oac.ll.dpas.ml.solution.evaluation.Evaluator;
import lu.snt.svv.oac.ll.dpas.ml.solution.io.POIReader;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.FileHandler;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.MSWordHandler;

/*
* @author Orlando AC
*/

public class SolutionPipeline {
	
		  static final boolean GENERALIZE = true;
		  static final int NUM_CONTAINED_WORDS = 1;
		  static final boolean MAXIMIZE = true;
		  static final float DIST_THRESHOLD = (float) 0.037;
		  static String DPA_NAME = "DPA.docx";
		  
		  public static void main(String[] args) throws UIMAException, IOException {
			String currentPath = new java.io.File(".").getCanonicalPath() + "/src/main/resources/"; 	// + "/" or + "/src/main/resources/"
			Map<String, String> arguments = new HashMap<String, String>();
			int j = 0;
			while (j < args.length) {
				String arg = args[j];
				if (arg.startsWith("-")) {
					arguments.put(arg, args[j + 1]);
				}
				j++;
			}
			if (args.length > 0) {
				if (arguments.get("-dpaName") != null)
					DPA_NAME = arguments.get("-dpaName");
			}
			else {
				DPA_NAME = "Aucuba.docx";	// "DEMO.docx"
			}
			String embeddingsLocation = currentPath + "vector_models/";	// + "resources/vector_models/" or + "vector_models/"
			String metadataModel = currentPath + "metadata_model/";	// + "resources/metadata_model/" or + "metadata_model/"
			// Keywords location for lexical containment
			String kwsLocation = currentPath + "keywords/";	// + "resources/keywords/" or + "keywords/"
			String trainingClustering = currentPath + "training_pipeline/clustering/";	// + "training_pipeline/clustering/" or + "resources/clustering/"
			String trainingClassification = currentPath + "training_pipeline/classification/";	// + "training_pipeline/classification/" or + "resources/classification/"
			String testOutput = currentPath + "testing_pipeline/output/";	// + "testing_pipeline/output/" or + "output/"
			String gsLocation = currentPath + "testing_pipeline/goldstandard/";	// + "testing_pipeline/goldstandard/" or + "resources/goldstandard/"
//			List<String> fileNames = new ArrayList<String>(FileHandler.getFileNames(currentPath + "input/", ".docx"));
//			Collections.sort(fileNames);
//			String documentName = fileNames.get(0);
			System.out.println("currently processing ... " + DPA_NAME);
			MetadataExtraction(currentPath, DPA_NAME, embeddingsLocation, kwsLocation, trainingClustering,
					trainingClassification, metadataModel, testOutput, gsLocation);
	  }
	  
	  public static void MetadataExtraction(String currentPath, String documentName, String embeddingsLocation, String kwsLocation, String trainingClustering, 
			  String trainingClassification, String metadataModel, String testOutput, String gsLocation) throws UIMAException, IOException {
		    	    
		    // Read from MS-Word
		    CollectionReaderDescription reader = createReaderDescription(POIReader.class, POIReader.PARAM_INPUT_PATH, currentPath + "input/" + documentName);
		    
		    // Tokenize
		    AnalysisEngineDescription segmenter = createEngineDescription(OpenNlpSegmenter.class);
		    
		    // Create PPTuples
		    AnalysisEngineDescription handler = createEngineDescription(MSWordHandler.class);
		    
		    // NER
		    AnalysisEngineDescription ner = createEngineDescription(StanfordNamedEntityRecognizer.class);
		    
		    // Preprocessing annotations per sentence: Tokenize, lemmatize, remove stopwords, generalize if requested, create the UoAs
		    AnalysisEngineDescription preprocessor = createEngineDescription(DataPreprocessor.class, DataPreprocessor.PARAM_GENERALIZE, GENERALIZE); 

		    // Lexical Containment  (If needed, include KWs)
//		    AnalysisEngineDescription container = createEngineDescription(KeywordsContainer.class,
//		       KeywordsContainer.PARAM_KWS_LOCATION, kwsLocation,
//		       KeywordsContainer.PARAM_NUM_CONTAINED_KWS, NUM_CONTAINED_WORDS); 
		    
		    // Vectorizing using SBERT
		    AnalysisEngineDescription vectorizer = createEngineDescription(Vectorizer.class, Vectorizer.PARAM_VECTORS_LOCATION,
		    	embeddingsLocation + documentName.replaceAll(".docx", "") + "Vectors.txt");
		    
		    // Similarity using Cosine Similarity
		    AnalysisEngineDescription similarity = createEngineDescription(Similarity.class,
		    	Similarity.PARAM_VECTORS_LOCATION, embeddingsLocation + "RequirementsVectors.txt");
		   
		 // Clustering the UoAs using K-means (Define if it is needed and change it to use SBERT embeddings!)
//		    AnalysisEngineDescription similarity = createEngineDescription(SimilarityBasedClassification.class,
//		            SimilarityBasedClassification.PARAM_EMBEDDINGS_LOCATION, embeddingsLocation + documentName.replaceAll(".docx", "") + "Vectors.txt",
//		            SimilarityBasedClassification.PARAM_TRAINING_LOCATION, trainingClustering);
		    
		    // Classifying the UoAs using using multiple binary classifiers
		    AnalysisEngineDescription classifier = createEngineDescription(MultiBinaryClassifier.class,
		        MultiBinaryClassifier.PARAM_MODEL_LOCATION, trainingClassification);
		    
		    // Metadata Predictor
		    AnalysisEngineDescription predictor = createEngineDescription(MetadataPredictorV4.class,
		        MetadataPredictorV4.METADATA_MODEL, metadataModel,
		        MetadataPredictorV4.PARAM_MAXIMIZE, MAXIMIZE,
		        MetadataPredictorV4.PARAM_DISTANCE_THRESHOLD, DIST_THRESHOLD,
		        MetadataPredictorV4.OUTPUT_PATH, testOutput,
		        MetadataPredictorV4.KEYWORDS_PATH, kwsLocation);
		    
		    // Evaluator
		    AnalysisEngineDescription evaluator = createEngineDescription(Evaluator.class,
		    	Evaluator.PARAM_GS_LOCATION, gsLocation,
		    	Evaluator.METADATA_MODEL, metadataModel,
		    	Evaluator.OUTPUT_PATH, testOutput);
		    
		    // Dump Consumer
		    AnalysisEngineDescription dumpConsumer = createPrimitiveDescription(CASDumpWriter.class);
		    
		    // Pipeline
		    SimplePipeline.runPipeline(reader,segmenter,handler,ner,preprocessor,
		    	vectorizer,similarity,classifier,predictor,evaluator,dumpConsumer);
		    
	  }
	  
	}