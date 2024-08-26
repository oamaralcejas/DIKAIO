

/* First created by JCasGen Tue Nov 08 16:40:29 CET 2022 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Nov 08 16:40:30 CET 2022
 * XML source: /Users/orlando.amaralcejas/eclipse-workspace/LinkLater/lu.snt.svv.oac.ll.dpas.ml.solution/src/main/resources/desc/type/PredictedMetadata.xml
 * @generated */
public class PredictedMetadata extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(PredictedMetadata.class);
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
  protected PredictedMetadata() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public PredictedMetadata(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public PredictedMetadata(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public PredictedMetadata(JCas jcas, int begin, int end) {
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
  //* Feature: metadata1

  /** getter for metadata1 - gets the first metadata
   * @generated
   * @return value of the feature 
   */
  public String getMetadata1() {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata1 == null)
      jcasType.jcas.throwFeatMissing("metadata1", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata1);}
    
  /** setter for metadata1 - sets the first metadata 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetadata1(String v) {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata1 == null)
      jcasType.jcas.throwFeatMissing("metadata1", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    jcasType.ll_cas.ll_setStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata1, v);}    
   
    
  //*--------------*
  //* Feature: metadata2

  /** getter for metadata2 - gets the second metadata
   * @generated
   * @return value of the feature 
   */
  public String getMetadata2() {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata2 == null)
      jcasType.jcas.throwFeatMissing("metadata2", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata2);}
    
  /** setter for metadata2 - sets the second metadata 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetadata2(String v) {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata2 == null)
      jcasType.jcas.throwFeatMissing("metadata2", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    jcasType.ll_cas.ll_setStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata2, v);}    
   
    
  //*--------------*
  //* Feature: metadata3

  /** getter for metadata3 - gets the third metadata
   * @generated
   * @return value of the feature 
   */
  public String getMetadata3() {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata3 == null)
      jcasType.jcas.throwFeatMissing("metadata3", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata3);}
    
  /** setter for metadata3 - sets the third metadata 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetadata3(String v) {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata3 == null)
      jcasType.jcas.throwFeatMissing("metadata3", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    jcasType.ll_cas.ll_setStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata3, v);}    
   
    
  //*--------------*
  //* Feature: metadata4

  /** getter for metadata4 - gets the fourth metadata
   * @generated
   * @return value of the feature 
   */
  public String getMetadata4() {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata4 == null)
      jcasType.jcas.throwFeatMissing("metadata4", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata4);}
    
  /** setter for metadata4 - sets the fourth metadata 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMetadata4(String v) {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_metadata4 == null)
      jcasType.jcas.throwFeatMissing("metadata4", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    jcasType.ll_cas.ll_setStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_metadata4, v);}    
   
    
  //*--------------*
  //* Feature: keywords

  /** getter for keywords - gets the suggested keywords, if any
   * @generated
   * @return value of the feature 
   */
  public String getKeywords() {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_keywords == null)
      jcasType.jcas.throwFeatMissing("keywords", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_keywords);}
    
  /** setter for keywords - sets the suggested keywords, if any 
   * @generated
   * @param v value to set into the feature 
   */
  public void setKeywords(String v) {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_keywords == null)
      jcasType.jcas.throwFeatMissing("keywords", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    jcasType.ll_cas.ll_setStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_keywords, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets the value of the last metadata
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets the value of the last metadata 
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (PredictedMetadata_Type.featOkTst && ((PredictedMetadata_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    jcasType.ll_cas.ll_setStringValue(addr, ((PredictedMetadata_Type)jcasType).casFeatCode_value, v);}    
  }

    