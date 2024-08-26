package lu.snt.svv.oac.ll.dpas.ml.solution.algorithms;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.uimafit.component.JCasAnnotator_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassificationResult;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.PPTuple;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.FileHandler;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.PARAMETERS_UTILITIES;

/*
* @author Orlando AC
*
*/

public class MetadataPredictorV4 extends JCasAnnotator_ImplBase implements PARAMETERS_UTILITIES {
  
  // Expected path of the models
  public static final String METADATA_MODEL = "metadataModel";
  @ConfigurationParameter(name = METADATA_MODEL, mandatory = true,
	  description = "metadata model location", defaultValue = "source/model/")
	  private String metadataModel;
  
  //Expected path of the keywords
  public static final String KEYWORDS_PATH = "keywords";
  @ConfigurationParameter(name = KEYWORDS_PATH, mandatory = true,
	  description = "keywords location", defaultValue = "source/keywords/")
	  private String keywordsPath;
  
  //Expected path of the output
  public static final String OUTPUT_PATH = "output";
  @ConfigurationParameter(name = OUTPUT_PATH, mandatory = true,
	  description = "output path", defaultValue = "source/output/")
	  private String testOutput;
	  
  // Whether to maximize similarity to cluster centers o.w. minimize distance
  public static final String PARAM_MAXIMIZE = "maximize";
  @ConfigurationParameter(name = PARAM_MAXIMIZE, mandatory = true,
      description = "if to maximize the distance(sim) from instance to cluster center",
      defaultValue = "true")
  private boolean maximize;

  // distance/similarity to cluster center threshold
  public static final String PARAM_DISTANCE_THRESHOLD = "distThreshold";
  @ConfigurationParameter(name = PARAM_DISTANCE_THRESHOLD, mandatory = true,
      description = "threshold to control distance to cluster centers", defaultValue = "0.1")
  private double distThreshold;

  // top-k considered for soft clustering ~3 for generic metadata
  public static final String PARAM_TOP_K = "topK";
  @ConfigurationParameter(name = PARAM_TOP_K, mandatory = true,
      description = "threshold to control distance to cluster centers", defaultValue = "6")
  private int topK;

  // probability threshold of class distribution
  public static final String PARAM_PROBABILITY_THRESHOLD = "probThreshold";
  @ConfigurationParameter(name = PARAM_PROBABILITY_THRESHOLD, mandatory = true,
      description = "threshold to control distance to cluster centers", defaultValue = "0.5")
  private double probThreshold;
  
  private long tic;
  private Logger logger = Logger.getLogger(getClass());

//  private Map<Double, String> metadataDists;
//  private List<String> metadataL1;
//  List<String> metadataL2;
//  List<String> metadataL3;
//  List<String> metadataL4;
  Map<String, String> metadataIndex;

  // Results
  Map<String, List<String>> classifierLabelsPerTuple;
//  Map<String, List<String>> clustererLabelsPerTuple;
//  Map<String, List<String>> KWsPerTuple;
//  List<UoA> uoaTuples;
  private double Threshold = 0.5;
  StringBuilder log;
  boolean flag = false, AD_Flag = false, EM_Flag = false, PH_Flag = false, NE_Flag = false;
  boolean DSR_PP_Flag = false; //Still not considering well the Post-processing
  
  @Override
  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    
//    this.metadataDists = new TreeMap<Double, String>();// Collections.reverseOrder());
//    metadataL1 = FileHandler.readFromFile(metadataModel + "L1");
//    metadataL2 = FileHandler.readFromFile(metadataModel + "L2");
//    metadataL3 = FileHandler.readFromFile(metadataModel + "L3");
//    metadataL4 = FileHandler.readFromFile(metadataModel + "L4");
    metadataIndex = FileHandler.readIndex(metadataModel + "index");
    

    classifierLabelsPerTuple = new HashMap<String, List<String>>();
//    clustererLabelsPerTuple = new HashMap<String, List<String>>();
//    KWsPerTuple = new HashMap<String, List<String>>();
    log = new StringBuilder();
    
    tic = System.currentTimeMillis();

  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException{
	  
    log.append("sentence,metadata");
    log.append("\n");
    String documentTitle = DocumentMetaData.get(aJCas).getDocumentTitle();
    String CC_DOCS_DIR = testOutput;
    Set<String> AllPDSs = new TreeSet<String>();
    Set<String> AllDSTs = new TreeSet<String>();
    Set<String> AllPDs = new TreeSet<String>();
    Set<String> AllPDTs = new TreeSet<String>();
    Set<String> AllDPIAs = new TreeSet<String>();
    Set<String> AllPPs = new TreeSet<String>();
    Set<String> AllPDCs = new TreeSet<String>();
    Set<String> AllPOs = new TreeSet<String>();
    Set<String> AllCRs = new TreeSet<String>();
    Set<String> AllCOs = new TreeSet<String>();
    Set<String> AllCs = new TreeSet<String>();
    Set<String> AllPs = new TreeSet<String>();

    for (PPTuple tuple : select(aJCas, PPTuple.class)) {
      // tupleIndex++;
    	
      // No Unit of Analysis
      if (selectCovered(UoA.class, tuple).size() == 0) {
        newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), "N/A", "X", "X", "X");
      }
      // UoA exists
      for (UoA uoa : selectCovered(UoA.class, tuple)) {
      
    	//Working with KWs
//  	    List<String> fileNames = null;
//		try {
//			fileNames = new ArrayList<String>(FileHandler.getFileNames(keywordsPath, ""));	//KWS_PATH is the path where the KWs are
//		} catch (ResourceInitializationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//  	    
//		String presentKWs = null;
//		
//  	    for (final String filename : fileNames){ 	    	
//  	    	String sCurrentLine;
//  	    	//System.out.println("Metadata: " + filename);	
//  	    	
//  			FileReader fr = null;
//  			try {
//				fr = new FileReader(keywordsPath + filename);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}    						
//			try {
//				BufferedReader br = new BufferedReader(fr);
//				while ((sCurrentLine = br.readLine()) != null) {
//					//System.out.println("Current line: " + sCurrentLine);
//					String[] Tokens = sCurrentLine.split(" ");
//					boolean present = true;
//					for (String token:Tokens){
//						if (!uoa.getPreprocessed().contains(token))
//							present = false;
//					}
//					if (present) {
//						presentKWs = presentKWs + ";" + filename;
//						break;
//					}
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				fr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//  	    	//System.out.println("KWs: " + presentKWs + "\n");
//			
//  	    	if (presentKWs != null)
//  	    		uoa.setContainedKWs(presentKWs);
//  	    }
      
        if (uoa.getHasNE())
        	NE_Flag=true;
        else
        	NE_Flag=false;
        if (uoa.getHasADDRESS())
         	AD_Flag=true;
        else
         	AD_Flag=false;
        if (uoa.getHasEMAIL())
         	EM_Flag=true;
        else
         	EM_Flag=false;
        if (uoa.getHasPHONE())
         	PH_Flag=true;
        else
         	PH_Flag=false;
        
        List<String> classifierLabels = new ArrayList<String>();
        List<String> similarityLabels = new ArrayList<String>();
        classifierLabels = new ArrayList<String>(getClassificationResults(uoa));
        similarityLabels = new ArrayList<String>(getSimilarityResults(uoa));
//        System.out.println("Chequea aqui:" + getSimilarityResults(uoa));
//----------------------------------------------------------------------------------------------------
// PD_SECURITY call
//----------------------------------------------------------------------------------------------------
//        List<String> KWs = new ArrayList<String>(Arrays.asList(uoa.getContainedKWs().split(";")));
        Set<String> PDSs = new TreeSet<String>();
        PDSs = predictPDSecurity(classifierLabels, similarityLabels);
        AllPDSs.addAll(PDSs);
//        System.out.println("PDSs: ");
        for (String cl : PDSs) {
//        	System.out.println("\t"+ cl);
        	String[] labels = metadataIndex.get(cl).split(";");
        	newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PD_SECURITY call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// DS_TYPE call
//----------------------------------------------------------------------------------------------------
        Set<String> DSTs = new TreeSet<String>();
        DSTs = predictDSType(classifierLabels, similarityLabels);
        AllDSTs.addAll(DSTs);
//        System.out.println("DSTs: ");
        for (String cl : DSTs) {
//          System.out.println("\t"+ cl);
          String[] labels = metadataIndex.get(cl).split(";");
          newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// DS_TYPE call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// PROCESSING_PURPOSES Call
//----------------------------------------------------------------------------------------------------
        Set<String> PPs= new TreeSet<String>();
        PPs = predictProcessingPurposes(classifierLabels, similarityLabels);
        AllPPs.addAll(PPs);
//          System.out.println("PPs: ");
        for (String cl : PPs) {
//        	System.out.println("\t"+ cl);
            String[] labels = metadataIndex.get(cl).split(";");
            newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PROCESSING_PURPOSES Call
//----------------------------------------------------------------------------------------------------
 
//----------------------------------------------------------------------------------------------------
// PROCESSING_DURATION Call
//----------------------------------------------------------------------------------------------------
        Set<String> PDs= new TreeSet<String>();
        PDs = predictProcessingDuration(classifierLabels, similarityLabels);
        AllPDs.addAll(PDs);
//        System.out.println("PDs: ");
        for (String cl : PDs) {
//        	System.out.println("\t"+ cl);
          	String[] labels = metadataIndex.get(cl).split(";");
          	newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PROCESSING_DURATION Call
//----------------------------------------------------------------------------------------------------
       
//----------------------------------------------------------------------------------------------------
// PD_CATEGORY Call
//----------------------------------------------------------------------------------------------------
        Set<String> PDCs= new TreeSet<String>();
        PDCs = predictPDCategory(classifierLabels, similarityLabels);
        AllPDCs.addAll(PDCs);
//          System.out.println("PDCs: ");
        for (String cl : PDCs) {
//        	System.out.println("\t"+ cl);
        	String[] labels = metadataIndex.get(cl).split(";");
        	newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PD_CATEGORY Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// PD_TRANSFER Call
//----------------------------------------------------------------------------------------------------
        Set<String> PDTs= new TreeSet<String>();
        PDTs = predictPDTransfer(classifierLabels, similarityLabels);
        AllPDTs.addAll(PDTs);
//        System.out.println("PDTs: ");
        for (String cl : PDTs) {
//        	System.out.println("\t"+ cl);
          	String[] labels = metadataIndex.get(cl).split(";");
          	newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PD_TRANSFER Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// DPIA Call
//----------------------------------------------------------------------------------------------------
        Set<String> DPIAs= new TreeSet<String>();
        DPIAs = predictDPIA(similarityLabels);
        AllDPIAs.addAll(DPIAs);
//        System.out.println("DPIAs: ");
        for (String cl : DPIAs) {
//            System.out.println("\t"+ cl);
            String[] labels = metadataIndex.get(cl).split(";");
            newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        } 
//----------------------------------------------------------------------------------------------------
// DPIA Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// PROCESSOR_OBLIGATION Call
//----------------------------------------------------------------------------------------------------
        Set<String> POs= new TreeSet<String>();
        POs = predictProcessorObligation(classifierLabels, similarityLabels);
        AllPOs.addAll(POs);
//        System.out.println("POs: ");
        for (String cl : POs) {
//          System.out.println("\t"+ cl);
          String[] labels = metadataIndex.get(cl).split(";");
          newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PROCESSOR_OBLIGATION Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// CONTROLLER_OBLIGATION Call
//----------------------------------------------------------------------------------------------------
        Set<String> COs= new TreeSet<String>();
        COs = predictControllerObligation(similarityLabels);
        AllCOs.addAll(COs);
//        System.out.println("COs: ");
        for (String cl : COs) {
//          System.out.println("\t"+ cl);
          String[] labels = metadataIndex.get(cl).split(";");
          newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// CONTROLLER_OBLIGATION Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// CONTROLLER_RIGHT Call
//----------------------------------------------------------------------------------------------------
        Set<String> CRs= new TreeSet<String>();
        CRs = predictControllerRight(similarityLabels);
        AllCRs.addAll(CRs);
//        System.out.println("CRs: ");
        for (String cl : CRs) {
//        	System.out.println("\t"+ cl);
            String[] labels = metadataIndex.get(cl).split(";");
            newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// CONTROLLER_RIGHT Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// CONTROLLER Call
//----------------------------------------------------------------------------------------------------
        Set<String> Cs= new TreeSet<String>();
        Cs = predictController(classifierLabels);
        AllCs.addAll(Cs);
//        System.out.println("Cs: ");
        for (String cl : Cs) {
//        	System.out.println("\t"+ cl);
            String[] labels = metadataIndex.get(cl).split(";");
            newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// CONTROLLER Call
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
// PROCESSOR Call
//----------------------------------------------------------------------------------------------------
        Set<String> Ps= new TreeSet<String>();
        Ps = predictProcessor(classifierLabels);
        AllPs.addAll(Ps);
//        System.out.println("Ps: ");
        for (String cl : Ps) {
//        	System.out.println("\t"+ cl);
            String[] labels = metadataIndex.get(cl).split(";");
            newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), labels[3], labels[2], labels[1], labels[0]);
        }
//----------------------------------------------------------------------------------------------------
// PROCESSOR Call
//----------------------------------------------------------------------------------------------------
        //filtering out the predictions out of context
//        postprocessing(aJCas);
        //Any metadata? || CRs.size()>0 || COs.size()>0
        if (PDSs.size()>0 || DSTs.size()>0 || PDTs.size()>0 || PDs.size()>0 || PPs.size()>0 || PDCs.size()>0 || POs.size()>0 || Cs.size()>0 || Ps.size()>0) {
          
          char ch='"';
          log.append(ch);
          log.append(uoa.getCoveredText());
//          System.out.println("Sentence to analyze: " + uoa.getCoveredText());
          log.append(ch);
          log.append(",");
          log.append(ch);
          
          //Adding PD_SECURITY instances
          if (PDSs.size()>0) {
            log.append(PD_SECURITY);
            log.append("|");
          }
          //Adding DS_TYPE instances
          if (DSTs.size()>0) {
            log.append(DS_TYPE);
            log.append("|");
          }
          //Adding PROCESSING_DURATION instances
          if (PDs.size()>0) {
        	  log.append(PROCESSING_DURATION);
        	  log.append("|");
          }
          //Adding PD_TRANSFER instances
          if (PDTs.size()>0) {
              log.append(PD_TRANSFER);
              log.append("|");
              if (PDTs.contains(CONTROLLER_CONSENT)) {
                  log.append(CONTROLLER_CONSENT);
                  log.append("|");
              }   
          }
          //Adding DPIAs instances
          if (!DPIAs.isEmpty()) {
              log.append(DPIA);
              log.append("|");
              if (DPIAs.contains(DESCRIPTION)) {
                  log.append(DESCRIPTION);
                  log.append("|");
              }
              if (DPIAs.contains(PROPORTIONALITY)) {
                  log.append(PROPORTIONALITY);
                  log.append("|");
              }
              if (DPIAs.contains(RISKS)) {
                  log.append(RISKS);
                  log.append("|");
              }
              if (DPIAs.contains(MEASURES)) {
                  log.append(MEASURES);
                  log.append("|");
              }  
          }
          //Adding PROCESSING_PURPOSES instances
          if (PPs.size()>0) {
              log.append(PROCESSING_PURPOSES);
              log.append("|");
          }
          //Adding PD_CATEGORY instances
          if (PDCs.size()>0) {
              if (PDCs.contains(PD_CATEGORY)) {
                log.append(PD_CATEGORY);
                log.append("|");
              }
//              if (PDCs.contains(SPECIAL)) {
//                log.append(SPECIAL);
//                log.append("|");
//              }
          } 
          //Adding PROCESSOR_OBLIGATION instances
          if (POs.size()>0) {
              log.append(PROCESSOR_OBLIGATION);
              log.append("|");
                if (POs.contains(ENSURE_SECURITY)) {
                  log.append(ENSURE_SECURITY);
                  log.append("|");
                }
                if (POs.contains(ASSESS_RISK)) {
                  log.append(ASSESS_RISK);
                  log.append("|");
                }
                if (POs.contains(SUB_CONTRACT)) {
                    log.append(SUB_CONTRACT);
                    log.append("|");
                  }
                if (POs.contains(PROCESS_ON_INSTRUCTIONS)) {
                    log.append(PROCESS_ON_INSTRUCTIONS);
                    log.append("|");
                  }
                if (POs.contains(REMAIN_LIABLE)) {
                    log.append(REMAIN_LIABLE);
                    log.append("|");
                  }
                if (POs.contains(DEMONSTRATE_COMPLIANCE)) {
                    log.append(DEMONSTRATE_COMPLIANCE);
                    log.append("|");
                  }
                if (POs.contains(ENSURE_CONFIDENTIALITY)) {
                    log.append(ENSURE_CONFIDENTIALITY);
                    log.append("|");
                  }
                if (POs.contains(ASSIST_CONTROLLER)) {
                    log.append(ASSIST_CONTROLLER);
                    log.append("|");
                  }
                if (POs.contains(PD_REMOVAL)) {
                    log.append(PD_REMOVAL);
                    log.append("|");
                  }
                if (POs.contains(DEMONSTRATE_GUARANTEES)) {
                    log.append(DEMONSTRATE_GUARANTEES);
                    log.append("|");
                  }
                if (POs.contains(INFORM_CONTROLLER)) {
                    log.append(INFORM_CONTROLLER);
                    log.append("|");
                  }
                if (POs.contains(ACQUIRE_ACCEPTANCE)) {
                    log.append(ACQUIRE_ACCEPTANCE);
                    log.append("|");
                  }
                if (POs.contains(ENSURE_SAME_OBLIGATIONS)) {
                    log.append(ENSURE_SAME_OBLIGATIONS);
                    log.append("|");
                  }
                if (POs.contains(APPLY_CHANGES)) {
                    log.append(APPLY_CHANGES);
                    log.append("|");
                  }
                if (POs.contains(AUDITS)) {
                    log.append(AUDITS);
                    log.append("|");
                  }
//                if (POs.contains(CONSULT_SA)) {
//                    log.append(CONSULT_SA);
//                    log.append("|");
//                  }
                if (POs.contains(NOTIFY_DATA_BREACH)) {
                    log.append(NOTIFY_DATA_BREACH);
                    log.append("|");
                  }
//                if (POs.contains(COMPLIANCE_DPIA)) {
//                    log.append(COMPLIANCE_DPIA);
//                    log.append("|");
//                  }
                if (POs.contains(FULFIL_OBLIGATIONS)) {
                    log.append(FULFIL_OBLIGATIONS);
                    log.append("|");
                  }
//                if (POs.contains(SECURITY)) {
//                    log.append(SECURITY);
//                    log.append("|");
//                  }
                if (POs.contains(SUBPROCESSOR_CHANGES)) {
                    log.append(SUBPROCESSOR_CHANGES);
                    log.append("|");
                  }
                if (POs.contains(DATA_BREACH)) {
                    log.append(DATA_BREACH);
                    log.append("|");
                  }
                if (POs.contains(INFRINGE_PROVISIONS)) {
                    log.append(INFRINGE_PROVISIONS);
                    log.append("|");
                  }
                if (POs.contains(PROCESS_WITHOUT_INSTRUCTIONS)) {
                    log.append(PROCESS_WITHOUT_INSTRUCTIONS);
                    log.append("|");
                  }
                if (POs.contains(EXPLANATION)) {
                    log.append(EXPLANATION);
                    log.append("|");
                  }
                if (POs.contains(ENTITY_DETAILS)) {
                    log.append(ENTITY_DETAILS);
                    log.append("|");
                  }
                if (POs.contains(CONSEQUENCES)) {
                    log.append(CONSEQUENCES);
                    log.append("|");
                  }
                if (POs.contains(TAKEN_MEASURES)) {
                    log.append(TAKEN_MEASURES);
                    log.append("|");
                  }
          }
          //Adding CONTROLLER_RIGHT instances
//          if (CRs.size()>0) {
//              log.append(CONTROLLER_RIGHT);
//              log.append("|");
//                if (CRs.contains(TERMINATE_DPA)) {
//                  log.append(TERMINATE_DPA);
//                  log.append("|");
//                }
//                if (CRs.contains(SUSPEND_PROCESSING)) {
//                  log.append(SUSPEND_PROCESSING);
//                  log.append("|");
//                }
//          }
          //Adding CONTROLLER_OBLIGATION instances
//          if (COs.size()>0) {
//              log.append(CONTROLLER_OBLIGATION);
//              log.append("|");
//              if (COs.contains(LIABLE)) {
//                log.append(LIABLE);
//                log.append("|");
//              }
//              if (COs.contains(CARRY_OUT_REVIEW)) {
//                log.append(CARRY_OUT_REVIEW);
//                log.append("|");
//              }
//              if (COs.contains(INFORM_DATA_BREACH)) {
//                log.append(INFORM_DATA_BREACH);
//                log.append("|");
//              }
//              if (COs.contains(CARRY_OUT_DPIA)) {
//                log.append(CARRY_OUT_DPIA);
//                log.append("|");
//              }
//              if (COs.contains(DOCUMENT_DATA_BREACH)) {
//                log.append(DOCUMENT_DATA_BREACH);
//                log.append("|");
//              }
//              if (COs.contains(SEEK_ADVICE_DPO)) {
//                log.append(SEEK_ADVICE_DPO);
//                log.append("|");
//              }
//              if (COs.contains(SEEK_VIEWS_DS)) {
//                log.append(SEEK_VIEWS_DS);
//                log.append("|");
//              }
//            }
          //Adding CONTROLLER instances
          if (Cs.size()>0) {
                log.append(CONTROLLER);
                log.append("|");
                if (Cs.contains(IDENTITY_C)) {
                  log.append(IDENTITY_C);
                  log.append("|");
                }
                if (Cs.contains(LEGAL_NAME_C)) {
                  log.append(LEGAL_NAME_C);
                  log.append("|");
                }
//                if (Cs.contains(SIGNATORY_NAME_C)) {
//                    log.append(SIGNATORY_NAME_C);
//                    log.append("|");
//                  } 
//                if (Cs.contains(REGISTER_NUMBER_C)) {
//                  log.append(REGISTER_NUMBER_C);
//                  log.append("|");
//                }
                if (Cs.contains(CONTACT_C)) {
                  log.append(CONTACT_C);
                  log.append("|");
                }
                if (Cs.contains(LEGAL_ADDRESS_C)) {
                  log.append(LEGAL_ADDRESS_C);
                  log.append("|");
                }
                if (Cs.contains(PHONE_NUMBER_C)) {
                  log.append(PHONE_NUMBER_C);
                  log.append("|");
                }
                if (Cs.contains(EMAIL_C)) {
                    log.append(EMAIL_C);
                    log.append("|");
                }
          }
          //Adding PROCESSOR instances
          if (Ps.size()>0) {
              log.append(PROCESSOR);
              log.append("|");
              if (Ps.contains(IDENTITY_P)) {
                log.append(IDENTITY_P);
                log.append("|");
              }
              if (Ps.contains(LEGAL_NAME_P)) {
                log.append(LEGAL_NAME_P);
                log.append("|");
              }
//              if (Ps.contains(SIGNATORY_NAME_P)) {
//                  log.append(SIGNATORY_NAME_P);
//                  log.append("|");
//                } 
//              if (Ps.contains(REGISTER_NUMBER_P)) {
//                log.append(REGISTER_NUMBER_P);
//                log.append("|");
//              }
              if (Ps.contains(CONTACT_P)) {
                log.append(CONTACT_C);
                log.append("|");
              }
              if (Ps.contains(LEGAL_ADDRESS_P)) {
                log.append(LEGAL_ADDRESS_P);
                log.append("|");
              }
              if (Ps.contains(PHONE_NUMBER_P)) {
                log.append(PHONE_NUMBER_P);
                log.append("|");
              }
              if (Ps.contains(EMAIL_P)) {
                  log.append(EMAIL_P);
                  log.append("|");
              }
          }
          
          log.deleteCharAt(log.length()-1);
          log.append(ch);
//          FileHandler.writeToFile(testOutput + "Predictions_"+ documentTitle + ".txt", log.toString());
          log.append("\n");
          
        }

        else {
          //log.append(String.format("%nPredicted as:%s%n", "N/A"));
          newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), "N/A", "X", "X", "X");
        }
        
      }
      
    }
    
    //Completeness_Checking Analysis
    try {
    	log = new StringBuilder();
    	boolean flag_CC_PDS = false, flag_CC_DST = false, flag_CC_PD = false, flag_CC_PDT = false, flag_CC_DPIA = false, flag_CC_PP = false, 
    			flag_CC_PDC = false, flag_CC_PO = false, flag_CC_CR = false, flag_CC_CO = false, flag_CC_C = false, flag_CC_P = false;
    	log.append("\nID\tClassification\tInfo\n");
    	flag_CC_PDS = CC_PDS(PD_SECURITY, AllPDSs, CC_DOCS_DIR, documentTitle);
    	flag_CC_DST = CC_DST(DS_TYPE, AllDSTs, CC_DOCS_DIR, documentTitle);
    	flag_CC_PD = CC_PD(PROCESSING_DURATION, AllPDs, CC_DOCS_DIR, documentTitle);
    	flag_CC_PP = CC_PP(PROCESSING_PURPOSES, AllPPs, CC_DOCS_DIR, documentTitle);
    	flag_CC_PDC = CC_PDC(PD_CATEGORY, AllPDCs, CC_DOCS_DIR, documentTitle);
    	flag_CC_DPIA = CC_DPIA(DPIA, AllDPIAs, CC_DOCS_DIR, documentTitle);
    	flag_CC_PDT = CC_PDT(PD_TRANSFER, AllPDTs, CC_DOCS_DIR, documentTitle);
    	flag_CC_PO = CC_PO(PROCESSOR_OBLIGATION, AllPOs, CC_DOCS_DIR, documentTitle);
    	flag_CC_CR = CC_CO(CONTROLLER_OBLIGATION, AllCOs, CC_DOCS_DIR, documentTitle);
    	flag_CC_CO = CC_CR(CONTROLLER_RIGHT, AllCRs, CC_DOCS_DIR, documentTitle);
//    	flag_CC_C = CC_C(CONTROLLER, AllCs, CC_DOCS_DIR, documentTitle);
//    	flag_CC_P = CC_P(PROCESSOR, AllPs, CC_DOCS_DIR, documentTitle);
    	if(!(flag_CC_PDS || flag_CC_DST || flag_CC_PD || flag_CC_PP || flag_CC_PDC || flag_CC_DPIA || flag_CC_PDT
    					 || flag_CC_PO || flag_CC_CO || flag_CC_CR)) {			//|| flag_CC_C || flag_CC_P
    		log.append("\nNO CRITERION VIOLATED\n");
    		log.append("Decision: NO CRITERION VIOLATED, DIKAIO finds this DPA GDPR compliant.\n");
    	}
    	else {
    		log.append("\nDecision: DIKAIO finds this DPA not GDPR compliant, it is suggested to verify the missing information types.\n");
    	}
    	
    	FileHandler.writeToFile(CC_DOCS_DIR + "CC_" + documentTitle + ".txt", log.toString());
        } catch (Exception e) {
        e.printStackTrace();
      }
    
    long toc = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - tic);
//	logger.info(String.format("%s: %d sec", "Time for prediction", toc));
    
  }

	// ----------------------------------------------------------------------------------------------------
	// PD_SECURITY Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictPDSecurity(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> pd_security = new ArrayList<String>(Arrays.asList(PD_SECURITY));
		classification.retainAll(pd_security);
		if (!classification.isEmpty() && similarityLabels.contains(R7)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PD_SECURITY Extraction
	// ----------------------------------------------------------------------------------------------------
	
	// ----------------------------------------------------------------------------------------------------
	// DS_TYPE Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictDSType(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> ds_type = new ArrayList<String>(Arrays.asList(DS_TYPE));
		classification.retainAll(ds_type);
		if (!classification.isEmpty() && similarityLabels.contains(R6)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// DS_TYPE Extraction
	// ----------------------------------------------------------------------------------------------------
	
	// ----------------------------------------------------------------------------------------------------
	// PROCESSING_DURATION Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictProcessingDuration(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> processing_duration = new ArrayList<String>(Arrays.asList(PROCESSING_DURATION));
		classification.retainAll(processing_duration);
//		if (!classification.isEmpty() && uoa.getCoveredText().contains("duration") && uoa.getCoveredText().contains("process")) {
		if (!classification.isEmpty() && similarityLabels.contains(R3)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PROCESSING_DURATION Extraction
	// ----------------------------------------------------------------------------------------------------

	// ----------------------------------------------------------------------------------------------------
	// PROCESSING_PURPOSES Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictProcessingPurposes(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> pp = new ArrayList<String>(Arrays.asList(PROCESSING_PURPOSES));
		classification.retainAll(pp);
		if (!classification.isEmpty() && similarityLabels.contains(R4)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PROCESSING_PURPOSES Extraction
	// ----------------------------------------------------------------------------------------------------

	// ----------------------------------------------------------------------------------------------------
	// PD_CATEGORY Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictPDCategory(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> pd_category = new ArrayList<String>(Arrays.asList(PD_CATEGORY));
		classification.retainAll(pd_category);
		if (!classification.isEmpty() && similarityLabels.contains(R5)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PD_CATEGORY Extraction
	// ----------------------------------------------------------------------------------------------------
	
	// ----------------------------------------------------------------------------------------------------
	// PD_TRANSFER Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictPDTransfer(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> pd_transfer = new ArrayList<String>(Arrays.asList(PD_TRANSFER, CONTROLLER_CONSENT));
		classification.retainAll(pd_transfer);
		if (!classification.isEmpty() && similarityLabels.contains(R29)) {
//		if (classification.contains(PD_TRANSFER) || classification.contains(CONTROLLER_CONSENT)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PD_TRANSFER Extraction
	// ----------------------------------------------------------------------------------------------------

	// ----------------------------------------------------------------------------------------------------
	// DPIA Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictDPIA(List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		if (similarityLabels.contains(R9)) {
			labels.add(DPIA);
			labels.add(PROPORTIONALITY);
			labels.add(RISKS);
			labels.add(DESCRIPTION);
			labels.add(MEASURES);
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// DPIA Extraction
	// ----------------------------------------------------------------------------------------------------
	
	// ----------------------------------------------------------------------------------------------------
	// PROCESSOR_OBLIGATION Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictProcessorObligation(List<String> classifierLabels, List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> processor_obligation = new ArrayList<String>(Arrays.asList(PROCESSOR_OBLIGATION, INFORM_CONTROLLER, ENSURE_CONFIDENTIALITY,
				SUB_CONTRACT, ASSIST_CONTROLLER, DEMONSTRATE_COMPLIANCE, PROCESS_ON_INSTRUCTIONS, ASSESS_RISK, ENSURE_SECURITY, DATA_BREACH,
				REMAIN_LIABLE, PD_REMOVAL, DEMONSTRATE_GUARANTEES, INFRINGE_PROVISIONS, SUBPROCESSOR_CHANGES, ACQUIRE_ACCEPTANCE, APPLY_CHANGES, 
				PROCESS_WITHOUT_INSTRUCTIONS, ENSURE_SAME_OBLIGATIONS, AUDITS, FULFIL_OBLIGATIONS));
		List<String> data_breach = new ArrayList<String>(Arrays.asList(EXPLANATION, ENTITY_DETAILS, CONSEQUENCES, TAKEN_MEASURES));
		classification.retainAll(processor_obligation);
		if (classification.contains(DATA_BREACH) && similarityLabels.contains(R8)) {
			classification.addAll(data_breach);
		}
		if (!classification.isEmpty()) {
			for (String cl : classification) {
				labels.add(cl);
			}
			if(!similarityLabels.contains(R10)) {
				labels.remove(ACQUIRE_ACCEPTANCE);
			}
			if(!similarityLabels.contains(R11)) {
				labels.remove(SUBPROCESSOR_CHANGES);
			}
			if(!similarityLabels.contains(R13)) {
				labels.remove(PROCESS_WITHOUT_INSTRUCTIONS);
			}
			if(!similarityLabels.contains(R14)) {
				labels.remove(ENSURE_CONFIDENTIALITY);
			}
			if(!similarityLabels.contains(R15)) {
				labels.remove(ENSURE_SECURITY);
			}
			if(!similarityLabels.contains(R16)) {
				labels.remove(FULFIL_OBLIGATIONS);
			}
			if(similarityLabels.contains(R17)) {
				labels.add(SECURITY);
				labels.add(ASSIST_CONTROLLER);
				labels.add(PROCESSOR_OBLIGATION);
			}
			if(similarityLabels.contains(R18)) {
				labels.add(CONSULT_SA);
				labels.add(ASSIST_CONTROLLER);
				labels.add(PROCESSOR_OBLIGATION);
			}
			if(similarityLabels.contains(R19) || similarityLabels.contains(R20)) {
				labels.add(NOTIFY_DATA_BREACH);
				labels.add(ASSIST_CONTROLLER);
				labels.add(PROCESSOR_OBLIGATION);
			}
			if(similarityLabels.contains(R21)) {
				labels.add(COMPLIANCE_DPIA);
				labels.add(ASSIST_CONTROLLER);
				labels.add(PROCESSOR_OBLIGATION);
			}
			if(!similarityLabels.contains(R22)) {
				labels.remove(PD_REMOVAL);
			}
			if(!similarityLabels.contains(R23)) {
				labels.remove(INFRINGE_PROVISIONS);
			}
			if(!similarityLabels.contains(R24)) {
				labels.remove(AUDITS);
			}
			if(!similarityLabels.contains(R25)) {
				labels.remove(DEMONSTRATE_COMPLIANCE);
			}
			if(!similarityLabels.contains(R26)) {
				labels.remove(ENSURE_SAME_OBLIGATIONS);
			}
			if(!similarityLabels.contains(R27)) {
				labels.remove(REMAIN_LIABLE);
			}
			if(!similarityLabels.contains(R28)) {
				labels.remove(ASSESS_RISK);
			}
			if(!similarityLabels.contains(R32)) {
				labels.remove(PROCESS_ON_INSTRUCTIONS);
			}
			if(!similarityLabels.contains(R35)) {
				labels.remove(APPLY_CHANGES);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PROCESSOR_OBLIGATION Extraction
	// ----------------------------------------------------------------------------------------------------	
	
	// ----------------------------------------------------------------------------------------------------
	// CONTROLLER_OBLIGATION Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictControllerObligation(List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		if(similarityLabels.contains(R38) || similarityLabels.contains(R40)) {
			labels.add(INFORM_DATA_BREACH);
			labels.add(CONTROLLER_OBLIGATION);
		}
		if(similarityLabels.contains(R41)) {
			labels.add(CARRY_OUT_DPIA);
			labels.add(CONTROLLER_OBLIGATION);
		}
		if(similarityLabels.contains(R45)) {
			labels.add(LIABLE);
			labels.add(CONTROLLER_OBLIGATION);
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// CONTROLLER_OBLIGATION Extraction
	// ----------------------------------------------------------------------------------------------------
	
	// ----------------------------------------------------------------------------------------------------
	// CONTROLLER_RIGHT Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictControllerRight(List<String> similarityLabels) {
		Set<String> labels = new TreeSet<String>();
		if(similarityLabels.contains(R36)) {
			labels.add(SUSPEND_PROCESSING);
			labels.add(CONTROLLER_RIGHT);
		}
		if(similarityLabels.contains(R37)) {
			labels.add(TERMINATE_DPA);
			labels.add(CONTROLLER_RIGHT);
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// CONTROLLER_RIGHT Extraction
	// ---------------------------------------------------------------------------------------------------- 
	
	// ----------------------------------------------------------------------------------------------------
	// CONTROLLER Extraction
	// ---------------------------------------------------------------------------------------------------- 
	private Set<String> predictController(List<String> classifierLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> controller = new ArrayList<String>(Arrays.asList(CONTROLLER, IDENTITY_C, CONTACT_C, LEGAL_NAME_C, LEGAL_ADDRESS_C));
		classification.retainAll(controller);
//		if(classification.contains(CONTACT_C) & AD_Flag)
//			labels.add(LEGAL_ADDRESS_C);
//		if(classification.contains(CONTROLLER) & NE_Flag)
//			labels.add(LEGAL_NAME_C);
//		if(EM_Flag)
//			labels.add(EMAIL_C);
//		if(PH_Flag)
//			labels.add(PHONE_NUMBER_C);
//		if (classifierLabels.contains(CONTROLLER) || classifierLabels.contains(IDENTITY_C)
//				|| classifierLabels.contains(CONTACT_C) || classifierLabels.contains(LEGAL_NAME_C)
//				|| classifierLabels.contains(SIGNATORY_NAME_C) || classifierLabels.contains(REGISTER_NUMBER_C)
//				|| classifierLabels.contains(PHONE_NUMBER_C) || classifierLabels.contains(EMAIL_C)
//				|| classifierLabels.contains(LEGAL_ADDRESS_C)) {
		if (!classification.isEmpty()) {
//		if (classification.contains(CONTROLLER) || classification.contains(IDENTITY_C) || classification.contains(CONTACT_C) 
//				|| classifierLabels.contains(LEGAL_NAME_C) || classifierLabels.contains(LEGAL_ADDRESS_C)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// CONTROLLER Extraction
	// ---------------------------------------------------------------------------------------------------- 
	
	// ----------------------------------------------------------------------------------------------------
	// PROCESSOR Extraction
	// ----------------------------------------------------------------------------------------------------
	private Set<String> predictProcessor(List<String> classifierLabels) {
		Set<String> labels = new TreeSet<String>();
		List<String> classification = new ArrayList<String>(classifierLabels);
		List<String> processor = new ArrayList<String>(Arrays.asList(PROCESSOR, IDENTITY_P, CONTACT_P, LEGAL_NAME_P, LEGAL_ADDRESS_P));
		classification.retainAll(processor);
//		if(AD_Flag)
//			labels.add(LEGAL_ADDRESS_P);
//		if(EM_Flag)
//			labels.add(EMAIL_P);
//		if(PH_Flag)
//			labels.add(PHONE_NUMBER_P);
//		if (classifierLabels.contains(PROCESSOR) || classifierLabels.contains(IDENTITY_P)
//				|| classifierLabels.contains(CONTACT_P) || classifierLabels.contains(LEGAL_NAME_P)
//				|| classifierLabels.contains(SIGNATORY_NAME_P) || classifierLabels.contains(REGISTER_NUMBER_P)
//				|| classifierLabels.contains(PHONE_NUMBER_P) || classifierLabels.contains(EMAIL_P)
//				|| classifierLabels.contains(LEGAL_ADDRESS_P)) {
		if (!classification.isEmpty()) {
//		if (classification.contains(PROCESSOR) || classification.contains(IDENTITY_P) || classification.contains(CONTACT_P) 
//				|| classification.contains(LEGAL_NAME_P) || classification.contains(LEGAL_ADDRESS_P)) {
			for (String cl : classification) {
				labels.add(cl);
			}
		}
		return labels;
	}
	// ----------------------------------------------------------------------------------------------------
	// PROCESSOR Extraction
	// ----------------------------------------------------------------------------------------------------

	private List<String> getClassificationResults(UoA uoa) {
		List<String> classifierLabels = new ArrayList<String>();
		for (ClassificationResult classification : selectCovered(ClassificationResult.class, uoa)) {
			FSArray probabilities = classification.getProbabilitiesOfClasses();
			ClassProbabilityPair prob;
			for (int i = 0; i < probabilities.size(); ++i) {
				prob = (ClassProbabilityPair) probabilities.get(i);
				if (prob.getProbability() >= probThreshold) {
					if (!prob.getClassLabel().startsWith("NOT")) {
						classifierLabels.add(prob.getClassLabel());
					} else if (prob.getClassLabel().equals("NOT_APPLICABLE")) {
						classifierLabels.add("N/A");
					}
				}
			}
		}
		return classifierLabels;
	}

	private void newPredictedMetadata(JCas aJCas, int begin, int end, String M1, String M2, String M3, String M4) {
		PredictedMetadata pm = new PredictedMetadata(aJCas);
		pm.setBegin(begin);
		pm.setEnd(end);
		pm.setMetadata1(M1);
		pm.setMetadata2(M2);
		pm.setMetadata3(M3);
		pm.setMetadata4(M4);
		pm.addToIndexes();
	}

	private List<String> getSimilarityResults(UoA uoa) {
		List<String> similarityLabels = new ArrayList<String>();
		for (SimilarityResults sr : selectCovered(SimilarityResults.class, uoa)) {
			similarityLabels.add(sr.getRequirement());
		}
		return similarityLabels;
	}
	
//  private void postprocessing(JCas aJCas) {
//	  List<PPTuple> tuples = new ArrayList<PPTuple>(select(aJCas, PPTuple.class));
//	    for (int i = 0; i < tuples.size(); ++i) {
//	      PPTuple tuple = tuples.get(i);
//	      int n = 5;
//
//	      boolean fromSame = true;
//	      if (selectCovered(PredictedMetadata.class, tuple).size() > 1) {
//	        String pm0 = selectCovered(PredictedMetadata.class, tuple).get(0).getMetadata1();
//	        for (int j = 1; j < selectCovered(PredictedMetadata.class, tuple).size(); ++j) {
//	          PredictedMetadata pm = selectCovered(PredictedMetadata.class, tuple).get(j);
//	          String pm1 = pm.getMetadata1();
//
//	          if (!pm1.equalsIgnoreCase(pm0)) {
//	            fromSame = false;
//	            break;
//	          }
//	        }
//	        if (fromSame) {
//	          continue;
//	        }
//	      }
//	      String m1 = "DATA_SUBJECT_RIGHT";
//	      for (PredictedMetadata pm : selectCovered(PredictedMetadata.class, tuple)) {
//	        if (pm.getMetadata1().equals(m1)) {
//	          if (pm.getMetadata2().contains("WITHDRAW_CONSENT")) {
//	            continue;
//	          }
//	          int fromIndex = (i - n < 0 ? 0 : i - n);
//	          int toIndex = (i + n >= tuples.size() ? tuples.size() - 1 : i + n);
//	          List<PPTuple> neighbors = tuples.subList(fromIndex, toIndex);
//	          neighbors.remove(tuple);
//	          if (!inContext(neighbors, m1, 4)) {
//	            pm.removeFromIndexes();
//	            newPredictedMetadata(aJCas, tuple.getBegin(), tuple.getEnd(), "N/A", "X", "X", "X");
//	            DSR_PP_Flag = true;
//	          }
//	        }
//	      }
//	    }
//	  }
//  
//  private boolean inContext(List<PPTuple> neighbors, String m1, int n) {
//	    boolean in = false;
//	    for (PPTuple tuple : neighbors) {
//	      for (PredictedMetadata pm : selectCovered(PredictedMetadata.class, tuple)) {
//	        if (pm.getMetadata1().equals(m1)) {
//	          in = true;
//	          break;
//	        }
//	      }
//	      if (in)
//	        break;
//	    }
//	    return in;
//	  }
  
	// ----------------------------------------------------------------------------------------------------
	// Completeness Checking
	// ----------------------------------------------------------------------------------------------------

	boolean CC_PD(String MetadataName, Set<String> PDs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (PDs.isEmpty()) {
			String criterionText = "\n[C1] Violation ";
			String criterion = PROCESSING_DURATION;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_PP(String MetadataName, Set<String> PPs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (PPs.isEmpty()) {
			String criterionText = "\n[C2] Violation ";
			String criterion = PROCESSING_PURPOSES;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_PDC(String MetadataName, Set<String> PDCs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (PDCs.isEmpty()) {
			String criterionText = "\n[C3] Violation ";
			String criterion = PD_CATEGORY;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_DST(String MetadataName, Set<String> DSTs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (DSTs.isEmpty()) {
			String criterionText = "\n[C4] Violation ";
			String criterion = DS_TYPE;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_PO(String MetadataName, Set<String> POs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (!POs.contains(ACQUIRE_ACCEPTANCE)) {
			String criterionText = "\n[C5] Violation ";
			String criterion = ACQUIRE_ACCEPTANCE;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(SUBPROCESSOR_CHANGES)) {
			String criterionText = "\n[C6] Violation ";
			String criterion = SUBPROCESSOR_CHANGES;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(PROCESS_ON_INSTRUCTIONS)) {
			String criterionText = "\n[C7] Violation ";
			String criterion = PROCESS_ON_INSTRUCTIONS;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(PROCESS_WITHOUT_INSTRUCTIONS)) {
			String criterionText = "\n[C8] Violation ";
			String criterion = PROCESS_WITHOUT_INSTRUCTIONS;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(ENSURE_CONFIDENTIALITY)) {
			String criterionText = "\n[C9] Violation ";
			String criterion = ENSURE_CONFIDENTIALITY;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(ENSURE_SECURITY)) {
			String criterionText = "\n[C10] Violation ";
			String criterion = SECURE_PROCESSING;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(FULFIL_OBLIGATIONS)) {
			String criterionText = "\n[C11] Violation ";
			String criterion = FULFIL_OBLIGATIONS;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(SECURITY)) {
			String criterionText = "\n[C12] Violation ";
			String criterion = ENSURE_SECURITY;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(DATA_BREACH)) {
			String criterionText = "\n[C13] Violation ";
			String criterion = DATA_BREACH;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(COMPLIANCE_DPIA)) {
			String criterionText = "\n[C14] Violation ";
			String criterion = COMPLIANCE_DPIA;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(CONSULT_SA)) {
			String criterionText = "\n[C15] Violation ";
			String criterion = CONSULT_SA;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(PD_REMOVAL)) {
			String criterionText = "\n[C16] Violation ";
			String criterion = PD_REMOVAL;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(INFRINGE_PROVISIONS)) {
			String criterionText = "\n[C17] Violation ";
			String criterion = INFRINGE_PROVISIONS;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(DEMONSTRATE_COMPLIANCE)) {
			String criterionText = "\n[C18] Violation ";
			String criterion = DEMONSTRATE_COMPLIANCE;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(ENSURE_SAME_OBLIGATIONS)) {
			String criterionText = "\n[C19] Violation ";
			String criterion = ENSURE_SAME_OBLIGATIONS;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(REMAIN_LIABLE)) {
			String criterionText = "\n[C20] Violation ";
			String criterion = REMAIN_LIABLE;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(ASSESS_RISK)) {
			String criterionText = "\n[C21] Violation ";
			String criterion = ASSESS_RISK;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(APPLY_CHANGES)) {
			String criterionText = "\n[C22] Violation ";
			String criterion = APPLY_CHANGES;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(NOTIFY_DATA_BREACH)) {
			String criterionText = "\n[C24] Warning ";
			String criterion = NOTIFY_DATA_BREACH;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!POs.contains(DEMONSTRATE_GUARANTEES)) {
			String criterionText = "\n[C27] Warning ";
			String criterion = DEMONSTRATE_GUARANTEES;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_PDS(String MetadataName, Set<String> PDSs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (PDSs.isEmpty()) {
			String criterionText = "\n[C23] Warning ";
			String criterion = PD_SECURITY;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}

	boolean CC_DPIA(String MetadataName, Set<String> DPIAs, String CC_DOCS_DIR, String documentTitle)
			throws IOException {
		boolean flag = false;
		if (DPIAs.isEmpty()) {
			String criterionText = "\n[C25] Warning ";
			String criterion = DPIA;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}

	boolean CC_PDT(String MetadataName, Set<String> PDTs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (PDTs.contains(PD_TRANSFER) && !PDTs.contains(CONTROLLER_CONSENT)) {
			String criterionText = "\n[C26] Warning ";
			String criterion = CONTROLLER_CONSENT;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_CO(String MetadataName, Set<String> COs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (!COs.contains(INFORM_DATA_BREACH)) {
			String criterionText = "\n[C28] Warning ";
			String criterion = INFORM_DATA_BREACH;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!COs.contains(DOCUMENT_DATA_BREACH)) {
			String criterionText = "\n[C29] Warning ";
			String criterion = DOCUMENT_DATA_BREACH;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!COs.contains(CARRY_OUT_DPIA)) {
			String criterionText = "\n[C30] Warning ";
			String criterion = CARRY_OUT_DPIA;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!COs.contains(SEEK_ADVICE_DPO)) {
			String criterionText = "\n[C31] Warning ";
			String criterion = SEEK_ADVICE_DPO;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!COs.contains(SEEK_VIEWS_DS)) {
			String criterionText = "\n[C32] Warning ";
			String criterion = SEEK_VIEWS_DS;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!COs.contains(CARRY_OUT_REVIEW)) {
			String criterionText = "\n[C33] Warning ";
			String criterion = CARRY_OUT_REVIEW;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!COs.contains(LIABLE)) {
			String criterionText = "\n[C34] Warning ";
			String criterion = ACCOUNTABLE;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
	boolean CC_CR(String MetadataName, Set<String> CRs, String CC_DOCS_DIR, String documentTitle) throws IOException {
		boolean flag = false;
		if (!CRs.contains(SUSPEND_PROCESSING)) {
			String criterionText = "\n[C35] Warning ";
			String criterion = SUSPEND_PROCESSING;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		if (!CRs.contains(TERMINATE_DPA)) {
			String criterionText = "\n[C36] Warning ";
			String criterion = TERMINATE_DPA;
			String violation = " is not identified.\n";
			criterionText += criterion + violation;
			log.append(criterionText);
			flag = true;
		}
		return flag;
	}
	
//	boolean CC_C(String MetadataName, Set<String> Cs, String CC_DOCS_DIR, String documentTitle) throws IOException {
//		boolean flag = false;
//		if (!Cs.contains(CONTROLLER) && !Cs.contains(IDENTITY_C) && !Cs.contains(CONTACT_C) && !Cs.contains(LEGAL_NAME_C)
//				&& !Cs.contains(SIGNATORY_NAME_C) && !Cs.contains(REGISTER_NUMBER_C) && !Cs.contains(PHONE_NUMBER_C)
//				&& !Cs.contains(EMAIL_C) && !Cs.contains(LEGAL_ADDRESS_C)) {
//			String criterionText = "\n[C11] Violation: ";
//			String criterion = CONTROLLER;
//			String violation = " is not identified.\n";
//			criterionText += criterion + violation;
//			log.append(criterionText);
//			flag = true;
//		}
//		return flag;
//	}
//	
//	boolean CC_P(String MetadataName, Set<String> Ps, String CC_DOCS_DIR, String documentTitle) throws IOException {
//		boolean flag = false;
//		if (!Ps.contains(PROCESSOR) && !Ps.contains(IDENTITY_P) && !Ps.contains(CONTACT_P) && !Ps.contains(LEGAL_NAME_P)
//				&& !Ps.contains(SIGNATORY_NAME_P) && !Ps.contains(REGISTER_NUMBER_P) && !Ps.contains(PHONE_NUMBER_P)
//				&& !Ps.contains(EMAIL_P) && !Ps.contains(LEGAL_ADDRESS_P)) {
//			String criterionText = "\n[C12] Violation: ";
//			String criterion = PROCESSOR;
//			String violation = " is not identified.\n";
//			criterionText += criterion + violation;
//			log.append(criterionText);
//			flag = true;
//		}
//		return flag;
//	}
  
	// ----------------------------------------------------------------------------------------------------
	// Completeness Checking
	// ----------------------------------------------------------------------------------------------------

}
