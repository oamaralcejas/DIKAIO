

/* First created by JCasGen Thu Feb 09 16:10:19 CET 2023 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Feb 09 16:21:55 CET 2023
 * XML source: /Users/orlando.amaralcejas/eclipse-workspace/LinkLater/lu.snt.svv.oac.ll.dpas.ml.solution/src/main/resources/desc/type/SimilarityResults.xml
 * @generated */
public class SimilarityResults extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SimilarityResults.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SimilarityResults() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SimilarityResults(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SimilarityResults(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SimilarityResults(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Begin

  /** getter for Begin - gets whether we could transform the preprocessed part of the sentence or not
   * @generated
   * @return value of the feature 
   */
  public int getBegin() {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_Begin == null)
      jcasType.jcas.throwFeatMissing("Begin", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_Begin);}
    
  /** setter for Begin - sets whether we could transform the preprocessed part of the sentence or not 
   * @generated
   * @param v value to set into the feature 
   */
  public void setBegin(int v) {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_Begin == null)
      jcasType.jcas.throwFeatMissing("Begin", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    jcasType.ll_cas.ll_setIntValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_Begin, v);}    
   
    
  //*--------------*
  //* Feature: End

  /** getter for End - gets the reason for not transforming the preprocessed sentence : notInVocab or isEmpty
   * @generated
   * @return value of the feature 
   */
  public int getEnd() {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_End == null)
      jcasType.jcas.throwFeatMissing("End", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_End);}
    
  /** setter for End - sets the reason for not transforming the preprocessed sentence : notInVocab or isEmpty 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEnd(int v) {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_End == null)
      jcasType.jcas.throwFeatMissing("End", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    jcasType.ll_cas.ll_setIntValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_End, v);}    
   
    
  //*--------------*
  //* Feature: Requirement

  /** getter for Requirement - gets the vector representation of the preprocessed or generalized sentence
   * @generated
   * @return value of the feature 
   */
  public String getRequirement() {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_Requirement == null)
      jcasType.jcas.throwFeatMissing("Requirement", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_Requirement);}
    
  /** setter for Requirement - sets the vector representation of the preprocessed or generalized sentence 
   * @generated
   * @param v value to set into the feature 
   */
  public void setRequirement(String v) {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_Requirement == null)
      jcasType.jcas.throwFeatMissing("Requirement", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    jcasType.ll_cas.ll_setStringValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_Requirement, v);}    
   
    
  //*--------------*
  //* Feature: Similarity

  /** getter for Similarity - gets 
   * @generated
   * @return value of the feature 
   */
  public double getSimilarity() {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_Similarity == null)
      jcasType.jcas.throwFeatMissing("Similarity", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_Similarity);}
    
  /** setter for Similarity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSimilarity(double v) {
    if (SimilarityResults_Type.featOkTst && ((SimilarityResults_Type)jcasType).casFeat_Similarity == null)
      jcasType.jcas.throwFeatMissing("Similarity", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((SimilarityResults_Type)jcasType).casFeatCode_Similarity, v);}    
  }

    