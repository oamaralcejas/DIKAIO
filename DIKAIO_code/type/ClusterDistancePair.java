

/* First created by JCasGen Thu Feb 09 16:35:05 CET 2023 */
package lu.snt.svv.oac.ll.dpas.ml.solution.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Feb 09 16:35:06 CET 2023
 * XML source: /Users/orlando.amaralcejas/eclipse-workspace/LinkLater/lu.snt.svv.oac.ll.dpas.ml.solution/src/main/resources/desc/type/ClusterDistancePair.xml
 * @generated */
public class ClusterDistancePair extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ClusterDistancePair.class);
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
  protected ClusterDistancePair() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ClusterDistancePair(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ClusterDistancePair(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ClusterDistancePair(JCas jcas, int begin, int end) {
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
  //* Feature: clusterLabel

  /** getter for clusterLabel - gets the label of the cluster
   * @generated
   * @return value of the feature 
   */
  public String getClusterLabel() {
    if (ClusterDistancePair_Type.featOkTst && ((ClusterDistancePair_Type)jcasType).casFeat_clusterLabel == null)
      jcasType.jcas.throwFeatMissing("clusterLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ClusterDistancePair_Type)jcasType).casFeatCode_clusterLabel);}
    
  /** setter for clusterLabel - sets the label of the cluster 
   * @generated
   * @param v value to set into the feature 
   */
  public void setClusterLabel(String v) {
    if (ClusterDistancePair_Type.featOkTst && ((ClusterDistancePair_Type)jcasType).casFeat_clusterLabel == null)
      jcasType.jcas.throwFeatMissing("clusterLabel", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    jcasType.ll_cas.ll_setStringValue(addr, ((ClusterDistancePair_Type)jcasType).casFeatCode_clusterLabel, v);}    
   
    
  //*--------------*
  //* Feature: distance

  /** getter for distance - gets the distance to the center of the cluster
   * @generated
   * @return value of the feature 
   */
  public double getDistance() {
    if (ClusterDistancePair_Type.featOkTst && ((ClusterDistancePair_Type)jcasType).casFeat_distance == null)
      jcasType.jcas.throwFeatMissing("distance", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((ClusterDistancePair_Type)jcasType).casFeatCode_distance);}
    
  /** setter for distance - sets the distance to the center of the cluster 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDistance(double v) {
    if (ClusterDistancePair_Type.featOkTst && ((ClusterDistancePair_Type)jcasType).casFeat_distance == null)
      jcasType.jcas.throwFeatMissing("distance", "lu.snt.svv.oac.ll.dpas.ml.solution.type.ClusterDistancePair");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((ClusterDistancePair_Type)jcasType).casFeatCode_distance, v);}    
  }

    