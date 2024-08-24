# DIKAIO

DPA complIance checKing using AI technolOgies (DIKAIO) is a tool,
associated with the paper titled: "ML-based Compliance Verification
of Data Processing Agreements against GDPR". DIKAIO leverages Machine
Learning (ML) and Natural Language Processing (NLP) to analyze Data
Processing Agreements (DPAs) to verify its compliance against 
the General Data Protection Regulation (GDPR).

This README file contains the details on how to use DIKAIO, where to
find it, and how to interpret the results obtained by DIKAIO.

## What is released?
- ./DIKAIO.zip: is a compressed version of the Maven project, including 
an executable jar file (DIKAIO.jar) for running our implementation over a DPA.
- ./SourceCode.zip: contains the source code of the Maven project.
Instructions about using DIKAIO are provided below.
- ./TrainingSet.zip and ./EvaluationSet.zip: contain the non-proprietary DPAs which we use in our work. We provide both 
the original DPAs as well as the annotated ones.

## How to use DIKAIO?

- Download and extract the DIKAIO.zip file
- Navigate to the ./DIKAIO folder on your local machine
```
cd path/to/DIKAIO/
```

### Running DIKAIO using DIKAIO.jar file.

- For a **simple run** (running DIKAIO on *DEMO.docx*), use the following command: 
```
java -jar DIKAIO.jar -dpaName DEMO.docx 
```

- For a **customized run**, use the following command (*Note*:This command might require few minutes.)
```
java -jar DIKAIO.jar -dpaName DPA_NAME.docx
```
The DPA that you wish to analyze must be first placed in the "/input" folder and
then its name is passed to the "-dpaName" parameter like we did for "DEMO.docx".  


*Note** that the name of the DPA should contain the extension, otherwise DIKAIO
will not function properly.

## Output of DIKAIO

Once the execution is successfully completed, you will be asked to press any button to continue. 
The output is then generated and placed in "/testing_pipeline/output" folder.  
DIKAIO generates two output files, as follows: 
- "DPA_NAME.txt" contains the evaluation of the DPA according to the available ground truth. 
*Note* this file is not generated if there is no corresponding ground truth file for the DPA. 
Specifically, DIKAIO will report the total number of true positives (TPs), true negatives (TNs),
false positives (FPs), and false negatives (FNs) across all information types identified in the analyzed DPA. 
Using these values, DIKAIO computes Precision (Pre) and Recall (Rec). For example, the respective output file "DEMO.txt" 
after successfully running DIKAIO on "DEMO.docx" contains the following:  

RESULTS:

| TP |  FP | FN | TN | Pre (\%) | Rec (\%) |
| --- | --- | --- | ---  | --- | --- |
| 20 | 27 | 4 | 20 | 42.0 | 83.0 |

- "CC\_DPA_NAME.txt" contains the recommendations with respect to the compliance checking analysis performed 
 by DIKAIO. Specifically, DIKAIO lists the violations and warnings, related to
any missing information type that was not identified by the tool in the DPA. 
For example, the respective output file "CC\_DEMO.txt" 
after successfully running DIKAIO on "DEMO.docx" contains the following:  

| ID | Classification | Info |
|  ---	|	---	|	---	|
[C4] | Violation | DS_TYPE is not identified. 
[C5] | Violation | ACQUIRE_ACCEPTANCE is not identified. 
[C9] | Violation | ENSURE_CONFIDENTIALITY is not identified. 
[C16] | Violation | PD_REMOVAL is not identified. 
[C21] | Violation | ASSESS_RISK is not identified.
[C29] | Warning | DOCUMENT_DATA_BREACH is not identified.
[C31] | Warning | SEEK_ADVICE_DPO is not identified. 
[C32] | Warning | SEEK_VIEWS_DS is not identified. 
[C33] | Warning | CARRY_OUT_REVIEW is not identified.

Decision: DIKAIO finds this DPA not GDPR compliant, it is suggested to
verify the missing information types.
