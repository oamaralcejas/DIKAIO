
/* First created by JCasGen Thu Feb 09 16:34:11 CET 2023 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Feb 09 16:34:12 CET 2023
 * @generated */
public class ClassProbabilityPair_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ClassProbabilityPair.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
 
  /** @generated */
  final Feature casFeat_classLabel;
  /** @generated */
  final int     casFeatCode_classLabel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getClassLabel(int addr) {
        if (featOkTst && casFeat_classLabel == null)
      jcas.throwFeatMissing("classLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    return ll_cas.ll_getStringValue(addr, casFeatCode_classLabel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setClassLabel(int addr, String v) {
        if (featOkTst && casFeat_classLabel == null)
      jcas.throwFeatMissing("classLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    ll_cas.ll_setStringValue(addr, casFeatCode_classLabel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_probability;
  /** @generated */
  final int     casFeatCode_probability;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getProbability(int addr) {
        if (featOkTst && casFeat_probability == null)
      jcas.throwFeatMissing("probability", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_probability);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setProbability(int addr, double v) {
        if (featOkTst && casFeat_probability == null)
      jcas.throwFeatMissing("probability", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClassProbabilityPair");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_probability, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ClassProbabilityPair_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_classLabel = jcas.getRequiredFeatureDE(casType, "classLabel", "uima.cas.String", featOkTst);
    casFeatCode_classLabel  = (null == casFeat_classLabel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_classLabel).getCode();

 
    casFeat_probability = jcas.getRequiredFeatureDE(casType, "probability", "uima.cas.Double", featOkTst);
    casFeatCode_probability  = (null == casFeat_probability) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_probability).getCode();

  }
}



    