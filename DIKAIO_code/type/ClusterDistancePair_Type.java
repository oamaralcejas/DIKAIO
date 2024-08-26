
/* First created by JCasGen Thu Feb 09 16:35:05 CET 2023 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Feb 09 16:35:06 CET 2023
 * @generated */
public class ClusterDistancePair_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ClusterDistancePair.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
 
  /** @generated */
  final Feature casFeat_clusterLabel;
  /** @generated */
  final int     casFeatCode_clusterLabel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getClusterLabel(int addr) {
        if (featOkTst && casFeat_clusterLabel == null)
      jcas.throwFeatMissing("clusterLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    return ll_cas.ll_getStringValue(addr, casFeatCode_clusterLabel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setClusterLabel(int addr, String v) {
        if (featOkTst && casFeat_clusterLabel == null)
      jcas.throwFeatMissing("clusterLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    ll_cas.ll_setStringValue(addr, casFeatCode_clusterLabel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_distance;
  /** @generated */
  final int     casFeatCode_distance;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getDistance(int addr) {
        if (featOkTst && casFeat_distance == null)
      jcas.throwFeatMissing("distance", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_distance);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDistance(int addr, double v) {
        if (featOkTst && casFeat_distance == null)
      jcas.throwFeatMissing("distance", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_distance, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ClusterDistancePair_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_clusterLabel = jcas.getRequiredFeatureDE(casType, "clusterLabel", "uima.cas.String", featOkTst);
    casFeatCode_clusterLabel  = (null == casFeat_clusterLabel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_clusterLabel).getCode();

 
    casFeat_distance = jcas.getRequiredFeatureDE(casType, "distance", "uima.cas.Double", featOkTst);
    casFeatCode_distance  = (null == casFeat_distance) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_distance).getCode();

  }
}



    