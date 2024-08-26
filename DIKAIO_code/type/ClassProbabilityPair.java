

/* First created by JCasGen Thu Feb 09 16:34:11 CET 2023 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Feb 09 16:34:12 CET 2023
 * XML source: /Users/orlando.amaralcejas/eclipse-workspace/LinkLater/lu.snt.svv.oac.ll.dpas.ml.solution/src/main/resources/desc/type/ClassProbabilityPair.xml
 * @generated */
public class ClassProbabilityPair extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ClassProbabilityPair.class);
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
  protected ClassProbabilityPair() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ClassProbabilityPair(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ClassProbabilityPair(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ClassProbabilityPair(JCas jcas, int begin, int end) {
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
  //* Feature: classLabel

  /** getter for classLabel - gets the class label
   * @generated
   * @return value of the feature 
   */
  public String getClassLabel() {
    if (ClassProbabilityPair_Type.featOkTst && ((ClassProbabilityPair_Type)jcasType).casFeat_classLabel == null)
      jcasType.jcas.throwFeatMissing("classLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ClassProbabilityPair_Type)jcasType).casFeatCode_classLabel);}
    
  /** setter for classLabel - sets the class label 
   * @generated
   * @param v value to set into the feature 
   */
  public void setClassLabel(String v) {
    if (ClassProbabilityPair_Type.featOkTst && ((ClassProbabilityPair_Type)jcasType).casFeat_classLabel == null)
      jcasType.jcas.throwFeatMissing("classLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    jcasType.ll_cas.ll_setStringValue(addr, ((ClassProbabilityPair_Type)jcasType).casFeatCode_classLabel, v);}    
   
    
  //*--------------*
  //* Feature: probability

  /** getter for probability - gets the probability of the instance belonging to the class
   * @generated
   * @return value of the feature 
   */
  public double getProbability() {
    if (ClassProbabilityPair_Type.featOkTst && ((ClassProbabilityPair_Type)jcasType).casFeat_probability == null)
      jcasType.jcas.throwFeatMissing("probability", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((ClassProbabilityPair_Type)jcasType).casFeatCode_probability);}
    
  /** setter for probability - sets the probability of the instance belonging to the class 
   * @generated
   * @param v value to set into the feature 
   */
  public void setProbability(double v) {
    if (ClassProbabilityPair_Type.featOkTst && ((ClassProbabilityPair_Type)jcasType).casFeat_probability == null)
      jcasType.jcas.throwFeatMissing("probability", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((ClassProbabilityPair_Type)jcasType).casFeatCode_probability, v);}    
  }

    