
/* First created by JCasGen Thu Feb 09 16:10:19 CET 2023 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Feb 09 16:21:55 CET 2023
 * @generated */
public class SimilarityResults_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SimilarityResults.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
 
  /** @generated */
  final Feature casFeat_Begin;
  /** @generated */
  final int     casFeatCode_Begin;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBegin(int addr) {
        if (featOkTst && casFeat_Begin == null)
      jcas.throwFeatMissing("Begin", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Begin);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBegin(int addr, int v) {
        if (featOkTst && casFeat_Begin == null)
      jcas.throwFeatMissing("Begin", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    ll_cas.ll_setIntValue(addr, casFeatCode_Begin, v);}
    
  
 
  /** @generated */
  final Feature casFeat_End;
  /** @generated */
  final int     casFeatCode_End;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEnd(int addr) {
        if (featOkTst && casFeat_End == null)
      jcas.throwFeatMissing("End", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return ll_cas.ll_getIntValue(addr, casFeatCode_End);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEnd(int addr, int v) {
        if (featOkTst && casFeat_End == null)
      jcas.throwFeatMissing("End", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    ll_cas.ll_setIntValue(addr, casFeatCode_End, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Requirement;
  /** @generated */
  final int     casFeatCode_Requirement;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getRequirement(int addr) {
        if (featOkTst && casFeat_Requirement == null)
      jcas.throwFeatMissing("Requirement", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Requirement);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRequirement(int addr, String v) {
        if (featOkTst && casFeat_Requirement == null)
      jcas.throwFeatMissing("Requirement", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    ll_cas.ll_setStringValue(addr, casFeatCode_Requirement, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Similarity;
  /** @generated */
  final int     casFeatCode_Similarity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getSimilarity(int addr) {
        if (featOkTst && casFeat_Similarity == null)
      jcas.throwFeatMissing("Similarity", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_Similarity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSimilarity(int addr, double v) {
        if (featOkTst && casFeat_Similarity == null)
      jcas.throwFeatMissing("Similarity", "lu.snt.svv.oac.ll.dpas.ml.solution.type.SimilarityResults");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_Similarity, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SimilarityResults_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Begin = jcas.getRequiredFeatureDE(casType, "Begin", "uima.cas.Integer", featOkTst);
    casFeatCode_Begin  = (null == casFeat_Begin) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Begin).getCode();

 
    casFeat_End = jcas.getRequiredFeatureDE(casType, "End", "uima.cas.Integer", featOkTst);
    casFeatCode_End  = (null == casFeat_End) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_End).getCode();

 
    casFeat_Requirement = jcas.getRequiredFeatureDE(casType, "Requirement", "uima.cas.String", featOkTst);
    casFeatCode_Requirement  = (null == casFeat_Requirement) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Requirement).getCode();

 
    casFeat_Similarity = jcas.getRequiredFeatureDE(casType, "Similarity", "uima.cas.Double", featOkTst);
    casFeatCode_Similarity  = (null == casFeat_Similarity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Similarity).getCode();

  }
}



    