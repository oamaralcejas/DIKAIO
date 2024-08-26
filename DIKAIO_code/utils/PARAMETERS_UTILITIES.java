package lu.snt.svv.oac.ll.dpas.ml.solution.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface PARAMETERS_UTILITIES {
	
	// Metadata types' names
	
	// L1
	public static final String CONTROLLER = "CONTROLLER";
	public static final String PROCESSOR = "PROCESSOR";
	public static final String PROCESSOR_OBLIGATION = "PROCESSOR_OBLIGATION";
	public static final String CONTROLLER_RIGHT = "CONTROLLER_RIGHT";
	public static final String CONTROLLER_OBLIGATION = "CONTROLLER_OBLIGATION";
	public static final String PD_SECURITY = "PD_SECURITY";
	public static final String DS_TYPE = "DS_TYPE";
	public static final String PROCESSING_DURATION = "PROCESSING_DURATION";
	public static final String PD_TRANSFER = "PD_TRANSFER";
	public static final String DPIA = "DPIA";
	public static final String PROCESSING_PURPOSES = "PROCESSING_PURPOSES";
	public static final String PD_CATEGORY = "PD_CATEGORY";

	// L2
	public static final String IDENTITY_C = "IDENTITY_C";
	public static final String IDENTITY_P = "IDENTITY_P";
	public static final String CONTACT_C = "CONTACT_C";
	public static final String CONTACT_P = "CONTACT_P";
	public static final String ENSURE_SECURITY = "ENSURE_SECURITY";
	public static final String SECURE_PROCESSING = "SECURE_PROCESSING";
	public static final String ASSESS_RISK = "ASSESS_RISK";
	public static final String SUB_CONTRACT = "SUB_CONTRACT";
	public static final String PROCESS_ON_INSTRUCTIONS = "PROCESS_ON_INSTRUCTIONS";
	public static final String REMAIN_LIABLE = "REMAIN_LIABLE";
	public static final String DEMONSTRATE_COMPLIANCE = "DEMONSTRATE_COMPLIANCE";
	public static final String ENSURE_CONFIDENTIALITY = "ENSURE_CONFIDENTIALITY";
	public static final String ASSIST_CONTROLLER = "ASSIST_CONTROLLER";
	public static final String PD_REMOVAL = "PD_REMOVAL";
	public static final String DEMONSTRATE_GUARANTEES = "DEMONSTRATE_GUARANTEES";
	public static final String INFORM_CONTROLLER = "INFORM_CONTROLLER";
	public static final String TERMINATE_DPA = "TERMINATE_DPA";
	public static final String SUSPEND_PROCESSING = "SUSPEND_PROCESSING";
	public static final String CONTROLLER_CONSENT = "CONTROLLER_CONSENT";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String PROPORTIONALITY = "PROPORTIONALITY";
	public static final String RISKS = "RISKS";
	public static final String MEASURES = "MEASURES";
	public static final String SPECIAL = "SPECIAL";
	public static final String LIABLE = "LIABLE";
	public static final String ACCOUNTABLE = "ACCOUNTABLE";
	public static final String CARRY_OUT_REVIEW = "CARRY_OUT_REVIEW";
	public static final String INFORM_DATA_BREACH = "INFORM_DATA_BREACH";
	public static final String CARRY_OUT_DPIA = "CARRY_OUT_DPIA";
	public static final String DOCUMENT_DATA_BREACH = "DOCUMENT_DATA_BREACH";
	public static final String SEEK_ADVICE_DPO = "SEEK_ADVICE_DPO";
	public static final String SEEK_VIEWS_DS = "SEEK_VIEWS_DS";

	// L3
	public static final String LEGAL_NAME_C = "LEGAL_NAME_C";
	public static final String LEGAL_NAME_P = "LEGAL_NAME_P";
	public static final String SIGNATORY_NAME_C = "SIGNATORY_NAME_C";
	public static final String SIGNATORY_NAME_P = "SIGNATORY_NAME_P";
	public static final String REGISTER_NUMBER_C = "REGISTER_NUMBER_C";
	public static final String REGISTER_NUMBER_P = "REGISTER_NUMBER_P";
	public static final String LEGAL_ADDRESS_C = "LEGAL_ADDRESS_C";
	public static final String LEGAL_ADDRESS_P = "LEGAL_ADDRESS_P";
	public static final String PHONE_NUMBER_C = "PHONE_NUMBER_C";
	public static final String PHONE_NUMBER_P = "PHONE_NUMBER_P";
	public static final String EMAIL_C = "EMAIL_C";
	public static final String EMAIL_P = "EMAIL_P";
	public static final String ACQUIRE_ACCEPTANCE = "ACQUIRE_ACCEPTANCE";
	public static final String ENSURE_SAME_OBLIGATIONS = "ENSURE_SAME_OBLIGATIONS";
	public static final String APPLY_CHANGES = "APPLY_CHANGES";
	public static final String AUDITS = "AUDITS";
	public static final String CONSULT_SA = "CONSULT_SA";
	public static final String NOTIFY_DATA_BREACH = "NOTIFY_DATA_BREACH";
	public static final String COMPLIANCE_DPIA = "COMPLIANCE_DPIA";
	public static final String FULFIL_OBLIGATIONS = "FULFIL_OBLIGATIONS";
	public static final String SECURITY = "SECURITY";
	public static final String SUBPROCESSOR_CHANGES = "SUBPROCESSOR_CHANGES";
	public static final String DATA_BREACH = "DATA_BREACH";
	public static final String INFRINGE_PROVISIONS = "INFRINGE_PROVISIONS";
	public static final String PROCESS_WITHOUT_INSTRUCTIONS = "PROCESS_WITHOUT_INSTRUCTIONS";

	// L4
	public static final String EXPLANATION = "EXPLANATION";
	public static final String ENTITY_DETAILS = "ENTITY_DETAILS";
	public static final String CONSEQUENCES = "CONSEQUENCES";
	public static final String TAKEN_MEASURES = "TAKEN_MEASURES";

	// Criteria's name
	public static final String CRITERION_CONTROLLER_IDENTITY = "Controller Identity";
	public static final String CRITERION_CONTROLLER_CONTACT = "Controller Contact";
	public static final String CRITERION_CONTROLLER_REPRESENTATIVE_IDENTITY = "Controller Representative Identity";
	public static final String CRITERION_CONTROLLER_REPRESENTATIVE_CONTACT = "Controller Representative Contact";
	public static final String CRITERION_MANDATORY_RIGHTS = "Mandatory Rights";
	public static final String CRITERION_SUPERVISORY_AUTHORITY = "Supervisory Authority";
	public static final String CRITERION_PORTABILITY_RIGHT = "Portability Right";
	public static final String CRITERION_OBJECT_RIGHT = "Object Right";
	public static final String CRITERION_CONSENT_RELATED_RIGHTS = "Consent Related Rights";
	public static final String CRITERION_TRANSFER_OUTSIDE_EU = "Transfer outside EU";
	public static final String CRITERION_TRANSFER_OUTSIDE_EU_DETAILS = "Transfer outside EU details";
	public static final String CRITERION_EXPLICIT_CONSENT = "Explicit Consent";
	public static final String CRITERION_SAFEGUARDS_DETAILS = "Safeguard Details";
	public static final String CRITERION_ADEQUACY_DECISION_DETAILS = "Adequacy Decision Details";
	public static final String CRITERION_INDIRECT_DATA_COLLECTION = "Indirect Data Collection";
	public static final String CRITERION_INDIRECT_COLLECTION_DETAILS = "Indirect Collection Details";
	public static final String CRITERION_CATEGORY = "Category";
	public static final String CRITERION_CATEGORY_TYPE_COLLECTED_INDIRECTLY = "Category Type Collected Indirectly";
	public static final String CRITERION_RECIPIENTS = "Recipients";
	public static final String CRITERION_RETENTION_PERIOD = "Retention Period";
	public static final String CRITERION_FAILURE_TO_PROVIDE_DATA = "Failure to Provide Data";
	public static final String CRITERION_PROCESSING_PORPUSES = "Processing Purposes";
	public static final String CRITERION_DPO_CONTACT = "DPO Contact";

	//Requirements Numbers
	public static final String R1 = "R1";
	public static final String R2 = "R2";
	public static final String R3 = "R3";
	public static final String R4 = "R4";
	public static final String R5 = "R5";
	public static final String R6 = "R6";
	public static final String R7 = "R7";
	public static final String R8 = "R8";
	public static final String R9 = "R9";
	public static final String R10 = "R10";
	public static final String R11 = "R11";
	public static final String R12 = "R12";
	public static final String R13 = "R13";
	public static final String R14 = "R14";
	public static final String R15 = "R15";
	public static final String R16 = "R16";
	public static final String R17 = "R17";
	public static final String R18 = "R18";
	public static final String R19 = "R19";
	public static final String R20 = "R20";
	public static final String R21 = "R21";
	public static final String R22 = "R22";
	public static final String R23 = "R23";
	public static final String R24 = "R24";
	public static final String R25 = "R25";
	public static final String R26 = "R26";
	public static final String R27 = "R27";
	public static final String R28 = "R28";
	public static final String R29 = "R29";
	public static final String R30 = "R30";
	public static final String R31 = "R31";
	public static final String R32 = "R32";
	public static final String R33 = "R33";
	public static final String R34 = "R34";
	public static final String R35 = "R35";
	public static final String R36 = "R36";
	public static final String R37 = "R37";
	public static final String R38 = "R38";
	public static final String R39 = "R39";
	public static final String R40 = "R40";
	public static final String R41 = "R41";
	public static final String R42 = "R42";
	public static final String R43 = "R43";
	public static final String R44 = "R44";
	public static final String R45 = "R45";
	
}
