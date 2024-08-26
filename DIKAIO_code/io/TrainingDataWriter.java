package lu.snt.svv.oac.ll.dpas.ml.solution.io;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasConsumer_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;

import lu.snt.svv.oac.ll.dpas.ml.solution.type.PPTuple;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.FileHandler;

/*
* @author Orlando AC
*
*/
public class TrainingDataWriter extends JCasConsumer_ImplBase {

  public static final String PARAM_CLASSIFICATION_PATH = "classificationPath";
  @ConfigurationParameter(name = PARAM_CLASSIFICATION_PATH, mandatory = true,
      description = "path to write the classification arff files",
      defaultValue = "src/main/resources/")
  private String classificationPath;
  
  public static final String PARAM_CLUSTERING_PATH = "clusteringPath";
  @ConfigurationParameter(name = PARAM_CLUSTERING_PATH, mandatory = true,
      description = "path to write the training examples for clustering",
      defaultValue = "src/main/resources/")
  private String clusteringPath;

  public static final String PARAM_ADD_CLUSTERING = "addClustering";
  @ConfigurationParameter(name = PARAM_ADD_CLUSTERING, mandatory = true,
      description = "whether to include clustering training data",
      defaultValue = "true")
  private boolean addClustering;
  
  public static final String PARAM_ADD_CLASSIFICATION = "addClassification";
  @ConfigurationParameter(name = PARAM_ADD_CLASSIFICATION, mandatory = true,
      description = "whether to include classification training data",
      defaultValue = "true")
  private boolean addClassification;
  
  public static final String PARAM_STRATIFY = "stratify";
  @ConfigurationParameter(name = PARAM_STRATIFY, mandatory = true,
      description = "whether to stratify over the negative examples for the multi-binary classification",
      defaultValue = "true")
  private boolean stratify;
  
  public static final String PARAM_IGNORE_NA = "ignoreNA";
  @ConfigurationParameter(name = PARAM_IGNORE_NA, mandatory = true,
      description = "whether to ignore NA for the multi-binary classification",
      defaultValue = "true")
  private boolean ignoreNA;

  private Set<String> multiClassArff;
  private Map<String, Set<String>> multipleBinaryContent;
  private Map<String, Set<String>> clusteringContent;
  private int vectorDim;

  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    multiClassArff = new HashSet<String>();
    multipleBinaryContent = new HashMap<String, Set<String>>();
    clusteringContent = new HashMap<String, Set<String>>();
    vectorDim = 0;
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    Set<String> multiClassUniqueContent = new HashSet<String>();
    for (PPTuple tuple : select(aJCas, PPTuple.class)) {
      for (UoA uoa : selectCovered(UoA.class, tuple)) {
        Set<String> content = new HashSet<String>();
        // Get M1
        if(clusteringContent.get(tuple.getMetadata1())==null) {
          content.add((uoa.getPreprocessed().isEmpty()?
              uoa.getGeneralized():uoa.getPreprocessed()));
         
          clusteringContent.put(tuple.getMetadata1(), content);
        }else {
          content.addAll(clusteringContent.get(tuple.getMetadata1()));
          content.add((uoa.getPreprocessed().isEmpty()?
              uoa.getGeneralized():uoa.getPreprocessed()));
          clusteringContent.put(tuple.getMetadata1(), content);
        }
        // Get M2
        if(!tuple.getMetadata2().equalsIgnoreCase("x")) {
          content = new HashSet<String>();
          if(clusteringContent.get(tuple.getMetadata2())==null) {
            content.add((uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()));          
            clusteringContent.put(tuple.getMetadata2(), content);
          }else {
            content.addAll(clusteringContent.get(tuple.getMetadata2()));
            content.add((uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()));
            clusteringContent.put(tuple.getMetadata2(), content);
          }
        }
        // Get M3
        if(!tuple.getMetadata3().equalsIgnoreCase("x")) {
          content = new HashSet<String>();
          if(clusteringContent.get(tuple.getMetadata3())==null) {
            content.add((uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()));           
            clusteringContent.put(tuple.getMetadata3(), content);
          }else {
            content.addAll(clusteringContent.get(tuple.getMetadata3()));
            content.add((uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()));
            clusteringContent.put(tuple.getMetadata3(), content);
          }
        }
        // Get M4
        if(!tuple.getMetadata4().equalsIgnoreCase("x")) {
          content = new HashSet<String>();
          if(clusteringContent.get(tuple.getMetadata4())==null) {
            content.add((uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()));          
            clusteringContent.put(tuple.getMetadata4(), content);
          }else {
            content.addAll(clusteringContent.get(tuple.getMetadata4()));
            content.add((uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()));
            clusteringContent.put(tuple.getMetadata4(), content);
          }
        }
      for (SentenceVector sv : selectCovered(SentenceVector.class, uoa)) {
        if (vectorDim == 0) {
          vectorDim = sv.getVectorDimension();
        }
        if(sv.getVector().isEmpty()) {
          continue;
        }
        String lineM1 = String.format("\"%s\", %s, %s", 
            (uoa.getPreprocessed().isEmpty()?
            uoa.getGeneralized():uoa.getPreprocessed()), 
            sv.getVector(), tuple.getMetadata1());     // TODO 
        String lineM2 = String.format("\"%s\", %s, %s", 
            (uoa.getPreprocessed().isEmpty()?
            uoa.getGeneralized():uoa.getPreprocessed()), 
            sv.getVector(), tuple.getMetadata2()); 
        String lineM3 = String.format("\"%s\", %s, %s", 
            (uoa.getPreprocessed().isEmpty()?
            uoa.getGeneralized():uoa.getPreprocessed()), 
            sv.getVector(), tuple.getMetadata3());
        String lineM4 = String.format("\"%s\", %s, %s", 
                (uoa.getPreprocessed().isEmpty()?
                uoa.getGeneralized():uoa.getPreprocessed()), 
                sv.getVector(), tuple.getMetadata4()); 
        multiClassUniqueContent.add(lineM1);
        // Get M1
        content = new HashSet<String>();
        if(multipleBinaryContent.get(tuple.getMetadata1())==null) {
          content.add(lineM1);
          multipleBinaryContent.put(tuple.getMetadata1(), content);
        }else {
          content.addAll(multipleBinaryContent.get(tuple.getMetadata1()));
          content.add(lineM1);
          multipleBinaryContent.put(tuple.getMetadata1(), 
              content);
        }
        // Get M2
        if(!tuple.getMetadata2().equalsIgnoreCase("x")) {
          content = new HashSet<String>();
          if(multipleBinaryContent.get(tuple.getMetadata2())==null) {
          content.add(lineM2);
          multipleBinaryContent.put(tuple.getMetadata2(), content);
        }else {
          content.addAll(multipleBinaryContent.get(tuple.getMetadata2()));
          content.add(lineM2);
          multipleBinaryContent.put(tuple.getMetadata2(), 
              content);
        }
        }
        // Get M3
        if(!tuple.getMetadata3().equalsIgnoreCase("x")) {
          content = new HashSet<String>();
          if(multipleBinaryContent.get(tuple.getMetadata3())==null) {
          content.add(lineM3);
          multipleBinaryContent.put(tuple.getMetadata3(), content);
        }else {
          content.addAll(multipleBinaryContent.get(tuple.getMetadata3()));
          content.add(lineM3);
          multipleBinaryContent.put(tuple.getMetadata3(), 
              content);
        }
        }
        // Get M4
        if(!tuple.getMetadata4().equalsIgnoreCase("x")) {
          content = new HashSet<String>();
          if(multipleBinaryContent.get(tuple.getMetadata4())==null) {
          content.add(lineM4);
          multipleBinaryContent.put(tuple.getMetadata4(), content);
        }else {
          content.addAll(multipleBinaryContent.get(tuple.getMetadata4()));
          content.add(lineM4);
          multipleBinaryContent.put(tuple.getMetadata4(), 
              content);
        }
        }
      }
      }
    }
    multiClassArff.addAll(multiClassUniqueContent); 
  }

  @Override
  public void collectionProcessComplete() {
    // writing the multiclass content on one arff file
    if(addClassification) {
      StringBuilder multiClassContent = new StringBuilder();
      for (String line : multiClassArff) {
        multiClassContent.append(line + "\n");
      }
      // Change here for training and testing dataset
      FileHandler.writeToFile(classificationPath + "_multiclassTraining.arff", //_multiclassTraining.arff, _multiclassTesting.arff
    		  				  FileHandler.getArffHeaderMultiClass("Training", vectorDim) + multiClassContent.toString());	// Training, Testing
      // writing multiple binary class files according to metadata
      createBinaryArffFiles();
    }
    // writing the preprocessed sentences for clustering
    if(addClustering) {
      for(Entry<String, Set<String>> metadataRelated: clusteringContent.entrySet()) {
        String metadata = metadataRelated.getKey();
        if(!metadata.equals("N/A")) {
          // write to files
          StringBuilder metadataContent = new StringBuilder();
          for (String line : metadataRelated.getValue()) {
//            metadataContent.append(line + "\n");	//original version (only the sentences)
            metadataContent.append("\"" + line + "\"" + ",\n");	//version to be used in Python to obtain the embeddings from SBERT
            }  
          FileHandler.writeToFile(clusteringPath + metadata, metadataContent.toString());
        }
      }
    }
    System.out.println("FINISHED");
  }

  private void createBinaryArffFiles() {
    for(Entry<String, Set<String>> metadataRelated: multipleBinaryContent.entrySet()) {
      String metadata = metadataRelated.getKey();
      if(metadata.equals("N/A"))
        metadata = "NOT_APPLICABLE";
      String header = FileHandler.getArffHeaderBinaryClass(metadata, vectorDim, metadata);
      header = header.replace("NOT_NOT_APPLICABLE", "APPLICABLE");
      StringBuilder metadataContent = new StringBuilder();
      for (String line : metadataRelated.getValue()) {
        if(line.contains("N/A"))
          line = line.replace("N/A", "NOT_APPLICABLE");
        metadataContent.append(line + "\n");
      }
      // Stratify the non-relevant examples: 
      int positive = metadataRelated.getValue().size();
      int negativeStratified = 0;
      int negativeCount = 0;
      for(String key: multipleBinaryContent.keySet()) {
        if(!key.equalsIgnoreCase(metadata) && !key.equals("N/A")) {
            negativeCount++;
        }
      }
      negativeStratified = (int) Math.round((double) positive / negativeCount);
      StringBuilder notMetadataContent = new StringBuilder();
      for(String key: multipleBinaryContent.keySet()) {
        if((!key.equalsIgnoreCase(metadata) && ignoreNA) || (!ignoreNA && key.equals("N/A"))) { //if the NA are ignored, the imbalance is reduced
          Set<String> notRelevant = multipleBinaryContent.get(key);
          int i=0;
          for(String line: notRelevant) {
            line=line.replace(key, "NOT_"+metadata);
            if(line.contains("NOT_NOT_APPLICABLE"))
              line=line.replace("NOT_NOT_APPLICABLE", "APPLICABLE");
            notMetadataContent.append(line+"\n");
            ++i;
            if((stratify && i >= negativeStratified) || i >= notRelevant.size())
              break;
          }
        }
      }
      FileHandler.writeToFile(classificationPath + metadata + ".arff", header + metadataContent.toString() + notMetadataContent.toString());
    }
    
  }

}
