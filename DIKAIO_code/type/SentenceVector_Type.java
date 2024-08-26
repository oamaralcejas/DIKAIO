
/* First created by JCasGen Wed Oct 05 14:05:02 CEST 2022 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Nov 08 11:29:36 CET 2022
 * @generated */
public class SentenceVector_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SentenceVector.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
 
  /** @generated */
  final Feature casFeat_transformPreporcessed;
  /** @generated */
  final int     casFeatCode_transformPreporcessed;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getTransformPreporcessed(int addr) {
        if (featOkTst && casFeat_transformPreporcessed == null)
      jcas.throwFeatMissing("transformPreporcessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_transformPreporcessed);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTransformPreporcessed(int addr, boolean v) {
        if (featOkTst && casFeat_transformPreporcessed == null)
      jcas.throwFeatMissing("transformPreporcessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_transformPreporcessed, v);}
    
  
 
  /** @generated */
  final Feature casFeat_reasonNotTransformed;
  /** @generated */
  final int     casFeatCode_reasonNotTransformed;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getReasonNotTransformed(int addr) {
        if (featOkTst && casFeat_reasonNotTransformed == null)
      jcas.throwFeatMissing("reasonNotTransformed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return ll_cas.ll_getStringValue(addr, casFeatCode_reasonNotTransformed);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setReasonNotTransformed(int addr, String v) {
        if (featOkTst && casFeat_reasonNotTransformed == null)
      jcas.throwFeatMissing("reasonNotTransformed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    ll_cas.ll_setStringValue(addr, casFeatCode_reasonNotTransformed, v);}
    
  
 
  /** @generated */
  final Feature casFeat_vector;
  /** @generated */
  final int     casFeatCode_vector;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getVector(int addr) {
        if (featOkTst && casFeat_vector == null)
      jcas.throwFeatMissing("vector", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return ll_cas.ll_getStringValue(addr, casFeatCode_vector);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVector(int addr, String v) {
        if (featOkTst && casFeat_vector == null)
      jcas.throwFeatMissing("vector", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    ll_cas.ll_setStringValue(addr, casFeatCode_vector, v);}
    
  
 
  /** @generated */
  final Feature casFeat_vectorDimension;
  /** @generated */
  final int     casFeatCode_vectorDimension;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVectorDimension(int addr) {
        if (featOkTst && casFeat_vectorDimension == null)
      jcas.throwFeatMissing("vectorDimension", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    return ll_cas.ll_getIntValue(addr, casFeatCode_vectorDimension);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVectorDimension(int addr, int v) {
        if (featOkTst && casFeat_vectorDimension == null)
      jcas.throwFeatMissing("vectorDimension", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SentenceVector");
    ll_cas.ll_setIntValue(addr, casFeatCode_vectorDimension, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SentenceVector_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_transformPreporcessed = jcas.getRequiredFeatureDE(casType, "transformPreporcessed", "uima.cas.Boolean", featOkTst);
    casFeatCode_transformPreporcessed  = (null == casFeat_transformPreporcessed) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_transformPreporcessed).getCode();

 
    casFeat_reasonNotTransformed = jcas.getRequiredFeatureDE(casType, "reasonNotTransformed", "uima.cas.String", featOkTst);
    casFeatCode_reasonNotTransformed  = (null == casFeat_reasonNotTransformed) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_reasonNotTransformed).getCode();

 
    casFeat_vector = jcas.getRequiredFeatureDE(casType, "vector", "uima.cas.String", featOkTst);
    casFeatCode_vector  = (null == casFeat_vector) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_vector).getCode();

 
    casFeat_vectorDimension = jcas.getRequiredFeatureDE(casType, "vectorDimension", "uima.cas.Integer", featOkTst);
    casFeatCode_vectorDimension  = (null == casFeat_vectorDimension) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_vectorDimension).getCode();

  }
}



    