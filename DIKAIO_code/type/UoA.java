

/* First created by JCasGen Wed Oct 05 14:04:57 CEST 2022 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Nov 08 11:29:56 CET 2022
 * XML source: /Users/orlando.amaralcejas/eclipse-workspace/LinkLater/lu.snt.svv.oac.ll.dpas.ml.solution/src/main/resources/desc/type/UoA.xml
 * @generated */
public class UoA extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(UoA.class);
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
  protected UoA() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public UoA(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public UoA(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public UoA(JCas jcas, int begin, int end) {
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
  //* Feature: generalized

  /** getter for generalized - gets the text after generalizing it with the NEs' types and
            symbols for phone numbers, e-mails and web-sites
   * @generated
   * @return value of the feature 
   */
  public String getGeneralized() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_generalized == null)
      jcasType.jcas.throwFeatMissing("generalized", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UoA_Type)jcasType).casFeatCode_generalized);}
    
  /** setter for generalized - sets the text after generalizing it with the NEs' types and
            symbols for phone numbers, e-mails and web-sites 
   * @generated
   * @param v value to set into the feature 
   */
  public void setGeneralized(String v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_generalized == null)
      jcasType.jcas.throwFeatMissing("generalized", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setStringValue(addr, ((UoA_Type)jcasType).casFeatCode_generalized, v);}    
   
    
  //*--------------*
  //* Feature: preprocessed

  /** getter for preprocessed - gets the text after being preprocessed: tokenized,
            lemmatized and stop-word removed
   * @generated
   * @return value of the feature 
   */
  public String getPreprocessed() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_preprocessed == null)
      jcasType.jcas.throwFeatMissing("preprocessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UoA_Type)jcasType).casFeatCode_preprocessed);}
    
  /** setter for preprocessed - sets the text after being preprocessed: tokenized,
            lemmatized and stop-word removed 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPreprocessed(String v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_preprocessed == null)
      jcasType.jcas.throwFeatMissing("preprocessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setStringValue(addr, ((UoA_Type)jcasType).casFeatCode_preprocessed, v);}    
   
    
  //*--------------*
  //* Feature: hasNE

  /** getter for hasNE - gets if the text contains a NE
   * @generated
   * @return value of the feature 
   */
  public boolean getHasNE() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasNE == null)
      jcasType.jcas.throwFeatMissing("hasNE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasNE);}
    
  /** setter for hasNE - sets if the text contains a NE 
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasNE(boolean v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasNE == null)
      jcasType.jcas.throwFeatMissing("hasNE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasNE, v);}    
   
    
  //*--------------*
  //* Feature: hasPHONE

  /** getter for hasPHONE - gets if the text contains a phone number
   * @generated
   * @return value of the feature 
   */
  public boolean getHasPHONE() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasPHONE == null)
      jcasType.jcas.throwFeatMissing("hasPHONE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasPHONE);}
    
  /** setter for hasPHONE - sets if the text contains a phone number 
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasPHONE(boolean v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasPHONE == null)
      jcasType.jcas.throwFeatMissing("hasPHONE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasPHONE, v);}    
   
    
  //*--------------*
  //* Feature: hasEMAIL

  /** getter for hasEMAIL - gets if the text contains an email
   * @generated
   * @return value of the feature 
   */
  public boolean getHasEMAIL() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasEMAIL == null)
      jcasType.jcas.throwFeatMissing("hasEMAIL", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasEMAIL);}
    
  /** setter for hasEMAIL - sets if the text contains an email 
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasEMAIL(boolean v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasEMAIL == null)
      jcasType.jcas.throwFeatMissing("hasEMAIL", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasEMAIL, v);}    
   
    
  //*--------------*
  //* Feature: hasWEBSITE

  /** getter for hasWEBSITE - gets if the text contains a website
   * @generated
   * @return value of the feature 
   */
  public boolean getHasWEBSITE() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasWEBSITE == null)
      jcasType.jcas.throwFeatMissing("hasWEBSITE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasWEBSITE);}
    
  /** setter for hasWEBSITE - sets if the text contains a website 
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasWEBSITE(boolean v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasWEBSITE == null)
      jcasType.jcas.throwFeatMissing("hasWEBSITE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasWEBSITE, v);}    
   
    
  //*--------------*
  //* Feature: hasADDRESS

  /** getter for hasADDRESS - gets if the text contains a postal address
   * @generated
   * @return value of the feature 
   */
  public boolean getHasADDRESS() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasADDRESS == null)
      jcasType.jcas.throwFeatMissing("hasADDRESS", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasADDRESS);}
    
  /** setter for hasADDRESS - sets if the text contains a postal address 
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasADDRESS(boolean v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_hasADDRESS == null)
      jcasType.jcas.throwFeatMissing("hasADDRESS", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((UoA_Type)jcasType).casFeatCode_hasADDRESS, v);}    
   
    
  //*--------------*
  //* Feature: containedKWs

  /** getter for containedKWs - gets the metadata that has a keyword fully contained 
                 in the UoA
   * @generated
   * @return value of the feature 
   */
  public String getContainedKWs() {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_containedKWs == null)
      jcasType.jcas.throwFeatMissing("containedKWs", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UoA_Type)jcasType).casFeatCode_containedKWs);}
    
  /** setter for containedKWs - sets the metadata that has a keyword fully contained 
                 in the UoA 
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainedKWs(String v) {
    if (UoA_Type.featOkTst && ((UoA_Type)jcasType).casFeat_containedKWs == null)
      jcasType.jcas.throwFeatMissing("containedKWs", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    jcasType.ll_cas.ll_setStringValue(addr, ((UoA_Type)jcasType).casFeatCode_containedKWs, v);}    
  }

    