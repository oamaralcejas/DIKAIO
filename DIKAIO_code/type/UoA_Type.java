
/* First created by JCasGen Wed Oct 05 14:04:57 CEST 2022 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Nov 08 11:29:56 CET 2022
 * @generated */
public class UoA_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = UoA.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
 
  /** @generated */
  final Feature casFeat_generalized;
  /** @generated */
  final int     casFeatCode_generalized;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getGeneralized(int addr) {
        if (featOkTst && casFeat_generalized == null)
      jcas.throwFeatMissing("generalized", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getStringValue(addr, casFeatCode_generalized);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGeneralized(int addr, String v) {
        if (featOkTst && casFeat_generalized == null)
      jcas.throwFeatMissing("generalized", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setStringValue(addr, casFeatCode_generalized, v);}
    
  
 
  /** @generated */
  final Feature casFeat_preprocessed;
  /** @generated */
  final int     casFeatCode_preprocessed;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPreprocessed(int addr) {
        if (featOkTst && casFeat_preprocessed == null)
      jcas.throwFeatMissing("preprocessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getStringValue(addr, casFeatCode_preprocessed);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPreprocessed(int addr, String v) {
        if (featOkTst && casFeat_preprocessed == null)
      jcas.throwFeatMissing("preprocessed", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setStringValue(addr, casFeatCode_preprocessed, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hasNE;
  /** @generated */
  final int     casFeatCode_hasNE;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getHasNE(int addr) {
        if (featOkTst && casFeat_hasNE == null)
      jcas.throwFeatMissing("hasNE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_hasNE);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHasNE(int addr, boolean v) {
        if (featOkTst && casFeat_hasNE == null)
      jcas.throwFeatMissing("hasNE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_hasNE, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hasPHONE;
  /** @generated */
  final int     casFeatCode_hasPHONE;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getHasPHONE(int addr) {
        if (featOkTst && casFeat_hasPHONE == null)
      jcas.throwFeatMissing("hasPHONE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_hasPHONE);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHasPHONE(int addr, boolean v) {
        if (featOkTst && casFeat_hasPHONE == null)
      jcas.throwFeatMissing("hasPHONE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_hasPHONE, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hasEMAIL;
  /** @generated */
  final int     casFeatCode_hasEMAIL;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getHasEMAIL(int addr) {
        if (featOkTst && casFeat_hasEMAIL == null)
      jcas.throwFeatMissing("hasEMAIL", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_hasEMAIL);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHasEMAIL(int addr, boolean v) {
        if (featOkTst && casFeat_hasEMAIL == null)
      jcas.throwFeatMissing("hasEMAIL", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_hasEMAIL, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hasWEBSITE;
  /** @generated */
  final int     casFeatCode_hasWEBSITE;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getHasWEBSITE(int addr) {
        if (featOkTst && casFeat_hasWEBSITE == null)
      jcas.throwFeatMissing("hasWEBSITE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_hasWEBSITE);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHasWEBSITE(int addr, boolean v) {
        if (featOkTst && casFeat_hasWEBSITE == null)
      jcas.throwFeatMissing("hasWEBSITE", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_hasWEBSITE, v);}
    
  
 
  /** @generated */
  final Feature casFeat_hasADDRESS;
  /** @generated */
  final int     casFeatCode_hasADDRESS;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getHasADDRESS(int addr) {
        if (featOkTst && casFeat_hasADDRESS == null)
      jcas.throwFeatMissing("hasADDRESS", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_hasADDRESS);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHasADDRESS(int addr, boolean v) {
        if (featOkTst && casFeat_hasADDRESS == null)
      jcas.throwFeatMissing("hasADDRESS", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_hasADDRESS, v);}
    
  
 
  /** @generated */
  final Feature casFeat_containedKWs;
  /** @generated */
  final int     casFeatCode_containedKWs;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getContainedKWs(int addr) {
        if (featOkTst && casFeat_containedKWs == null)
      jcas.throwFeatMissing("containedKWs", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    return ll_cas.ll_getStringValue(addr, casFeatCode_containedKWs);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainedKWs(int addr, String v) {
        if (featOkTst && casFeat_containedKWs == null)
      jcas.throwFeatMissing("containedKWs", "lu.snt.svv.oac.ll.dpas.ml.solution.type.UoA");
    ll_cas.ll_setStringValue(addr, casFeatCode_containedKWs, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public UoA_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_generalized = jcas.getRequiredFeatureDE(casType, "generalized", "uima.cas.String", featOkTst);
    casFeatCode_generalized  = (null == casFeat_generalized) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_generalized).getCode();

 
    casFeat_preprocessed = jcas.getRequiredFeatureDE(casType, "preprocessed", "uima.cas.String", featOkTst);
    casFeatCode_preprocessed  = (null == casFeat_preprocessed) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_preprocessed).getCode();

 
    casFeat_hasNE = jcas.getRequiredFeatureDE(casType, "hasNE", "uima.cas.Boolean", featOkTst);
    casFeatCode_hasNE  = (null == casFeat_hasNE) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hasNE).getCode();

 
    casFeat_hasPHONE = jcas.getRequiredFeatureDE(casType, "hasPHONE", "uima.cas.Boolean", featOkTst);
    casFeatCode_hasPHONE  = (null == casFeat_hasPHONE) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hasPHONE).getCode();

 
    casFeat_hasEMAIL = jcas.getRequiredFeatureDE(casType, "hasEMAIL", "uima.cas.Boolean", featOkTst);
    casFeatCode_hasEMAIL  = (null == casFeat_hasEMAIL) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hasEMAIL).getCode();

 
    casFeat_hasWEBSITE = jcas.getRequiredFeatureDE(casType, "hasWEBSITE", "uima.cas.Boolean", featOkTst);
    casFeatCode_hasWEBSITE  = (null == casFeat_hasWEBSITE) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hasWEBSITE).getCode();

 
    casFeat_hasADDRESS = jcas.getRequiredFeatureDE(casType, "hasADDRESS", "uima.cas.Boolean", featOkTst);
    casFeatCode_hasADDRESS  = (null == casFeat_hasADDRESS) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_hasADDRESS).getCode();

 
    casFeat_containedKWs = jcas.getRequiredFeatureDE(casType, "containedKWs", "uima.cas.String", featOkTst);
    casFeatCode_containedKWs  = (null == casFeat_containedKWs) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containedKWs).getCode();

  }
}



    