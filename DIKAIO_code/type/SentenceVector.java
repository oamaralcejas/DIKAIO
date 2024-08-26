

/* First created by JCasGen Wed Oct 05 14:05:02 CEST 2022 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Nov 08 11:29:36 CET 2022
 * XML source: /Users/orlando.amaralcejas/eclipse-workspace/LinkLater/lu.snt.svv.oac.ll.dpas.ml.solution/src/main/resources/desc/type/SentenceVector.xml
 * @generated */
public class SentenceVector extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SentenceVector.class);
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
  protected SentenceVector() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SentenceVector(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SentenceVector(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SentenceVector(JCas jcas, int begin, int end) {
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
  //* Feature: transformPreporcessed

  /** getter for transformPreporcessed - gets whether we could transform the preprocessed part of the sentence or not
   * @generated
   * @return value of the feature 
   */
  public boolean getTransformPreporcessed() {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_transformPreporcessed == null)
      jcasType.jcas.throwFeatMissing("transformPreporcessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_transformPreporcessed);}
    
  /** setter for transformPreporcessed - sets whether we could transform the preprocessed part of the sentence or not 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTransformPreporcessed(boolean v) {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_transformPreporcessed == null)
      jcasType.jcas.throwFeatMissing("transformPreporcessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_transformPreporcessed, v);}    
   
    
  //*--------------*
  //* Feature: reasonNotTransformed

  /** getter for reasonNotTransformed - gets the reason for not transforming the preprocessed sentence : notInVocab or isEmpty
   * @generated
   * @return value of the feature 
   */
  public String getReasonNotTransformed() {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_reasonNotTransformed == null)
      jcasType.jcas.throwFeatMissing("reasonNotTransformed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_reasonNotTransformed);}
    
  /** setter for reasonNotTransformed - sets the reason for not transforming the preprocessed sentence : notInVocab or isEmpty 
   * @generated
   * @param v value to set into the feature 
   */
  public void setReasonNotTransformed(String v) {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_reasonNotTransformed == null)
      jcasType.jcas.throwFeatMissing("reasonNotTransformed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    jcasType.ll_cas.ll_setStringValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_reasonNotTransformed, v);}    
   
    
  //*--------------*
  //* Feature: vector

  /** getter for vector - gets the vector representation of the preprocessed or generalized sentence
   * @generated
   * @return value of the feature 
   */
  public String getVector() {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_vector == null)
      jcasType.jcas.throwFeatMissing("vector", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_vector);}
    
  /** setter for vector - sets the vector representation of the preprocessed or generalized sentence 
   * @generated
   * @param v value to set into the feature 
   */
  public void setVector(String v) {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_vector == null)
      jcasType.jcas.throwFeatMissing("vector", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    jcasType.ll_cas.ll_setStringValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_vector, v);}    
   
    
  //*--------------*
  //* Feature: vectorDimension

  /** getter for vectorDimension - gets the dimension of the sentence vector
   * @generated
   * @return value of the feature 
   */
  public int getVectorDimension() {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_vectorDimension == null)
      jcasType.jcas.throwFeatMissing("vectorDimension", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_vectorDimension);}
    
  /** setter for vectorDimension - sets the dimension of the sentence vector 
   * @generated
   * @param v value to set into the feature 
   */
  public void setVectorDimension(int v) {
    if (SentenceVector_Type.featOkTst && ((SentenceVector_Type)jcasType).casFeat_vectorDimension == null)
      jcasType.jcas.throwFeatMissing("vectorDimension", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    jcasType.ll_cas.ll_setIntValue(addr, ((SentenceVector_Type)jcasType).casFeatCode_vectorDimension, v);}    
  }

    