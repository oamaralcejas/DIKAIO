
/* First created by JCasGen Tue Nov 08 16:40:29 CET 2022 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Nov 08 16:40:30 CET 2022
 * @generated */
public class PredictedMetadata_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = PredictedMetadata.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
 
  /** @generated */
  final Feature casFeat_metadata1;
  /** @generated */
  final int     casFeatCode_metadata1;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMetadata1(int addr) {
        if (featOkTst && casFeat_metadata1 == null)
      jcas.throwFeatMissing("metadata1", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_metadata1);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMetadata1(int addr, String v) {
        if (featOkTst && casFeat_metadata1 == null)
      jcas.throwFeatMissing("metadata1", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    ll_cas.ll_setStringValue(addr, casFeatCode_metadata1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_metadata2;
  /** @generated */
  final int     casFeatCode_metadata2;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMetadata2(int addr) {
        if (featOkTst && casFeat_metadata2 == null)
      jcas.throwFeatMissing("metadata2", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_metadata2);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMetadata2(int addr, String v) {
        if (featOkTst && casFeat_metadata2 == null)
      jcas.throwFeatMissing("metadata2", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    ll_cas.ll_setStringValue(addr, casFeatCode_metadata2, v);}
    
  
 
  /** @generated */
  final Feature casFeat_metadata3;
  /** @generated */
  final int     casFeatCode_metadata3;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMetadata3(int addr) {
        if (featOkTst && casFeat_metadata3 == null)
      jcas.throwFeatMissing("metadata3", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_metadata3);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMetadata3(int addr, String v) {
        if (featOkTst && casFeat_metadata3 == null)
      jcas.throwFeatMissing("metadata3", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    ll_cas.ll_setStringValue(addr, casFeatCode_metadata3, v);}
    
  
 
  /** @generated */
  final Feature casFeat_metadata4;
  /** @generated */
  final int     casFeatCode_metadata4;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMetadata4(int addr) {
        if (featOkTst && casFeat_metadata4 == null)
      jcas.throwFeatMissing("metadata4", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_metadata4);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMetadata4(int addr, String v) {
        if (featOkTst && casFeat_metadata4 == null)
      jcas.throwFeatMissing("metadata4", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    ll_cas.ll_setStringValue(addr, casFeatCode_metadata4, v);}
    
  
 
  /** @generated */
  final Feature casFeat_keywords;
  /** @generated */
  final int     casFeatCode_keywords;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getKeywords(int addr) {
        if (featOkTst && casFeat_keywords == null)
      jcas.throwFeatMissing("keywords", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_keywords);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setKeywords(int addr, String v) {
        if (featOkTst && casFeat_keywords == null)
      jcas.throwFeatMissing("keywords", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    ll_cas.ll_setStringValue(addr, casFeatCode_keywords, v);}
    
  
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "lu.snt.svv.oac.ll.dpas.ml.solution.type.PredictedMetadata");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public PredictedMetadata_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_metadata1 = jcas.getRequiredFeatureDE(casType, "metadata1", "uima.cas.String", featOkTst);
    casFeatCode_metadata1  = (null == casFeat_metadata1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_metadata1).getCode();

 
    casFeat_metadata2 = jcas.getRequiredFeatureDE(casType, "metadata2", "uima.cas.String", featOkTst);
    casFeatCode_metadata2  = (null == casFeat_metadata2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_metadata2).getCode();

 
    casFeat_metadata3 = jcas.getRequiredFeatureDE(casType, "metadata3", "uima.cas.String", featOkTst);
    casFeatCode_metadata3  = (null == casFeat_metadata3) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_metadata3).getCode();

 
    casFeat_metadata4 = jcas.getRequiredFeatureDE(casType, "metadata4", "uima.cas.String", featOkTst);
    casFeatCode_metadata4  = (null == casFeat_metadata4) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_metadata4).getCode();

 
    casFeat_keywords = jcas.getRequiredFeatureDE(casType, "keywords", "uima.cas.String", featOkTst);
    casFeatCode_keywords  = (null == casFeat_keywords) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_keywords).getCode();

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

  }
}



    