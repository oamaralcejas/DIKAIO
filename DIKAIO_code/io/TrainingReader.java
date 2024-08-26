package lu.snt.svv.oac.ll.dpas.ml.solution.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.uimafit.component.JCasCollectionReader_ImplBase;
import org.uimafit.descriptor.ConfigurationParameter;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import lu.snt.svv.oac.ll.dpas.ml.solution.type.PPTuple;
import lu.snt.svv.oac.ll.dpas.ml.solution.utils.FileHandler;

/*
* @author Orlando AC
*
*/
public class TrainingReader extends JCasCollectionReader_ImplBase {

  public static final String PARAM_INPUT_DIR_PATH = "inputDirPath";
  @ConfigurationParameter(name = PARAM_INPUT_DIR_PATH, mandatory = true,
      description = "Input directory for the training files")
  private String inputDirPath;
  
  public static final String PARAM_METADATA_PATH = "metadataPath";
  @ConfigurationParameter(name = PARAM_METADATA_PATH, mandatory = true,
      description = "Input path to the metadata model")
  private String metadataPath;
  
  private File directory;
  private List<String> documentTexts;
  private int nextIndex;
  private List<String> fileNames;

  final static int SENT_INDEX = 0;
  final static int M1_INDEX = 1;
  final static int M2_INDEX = 2;
  final static int M3_INDEX = 3;
  final static int M4_INDEX = 4;
  final static int KW_INDEX = 5;
  final static int V_INDEX = 6;

  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    this.documentTexts = new ArrayList<String>();
    this.fileNames = new ArrayList<String>();
    this.directory = new File(inputDirPath);
    if (!this.directory.isDirectory())
      throw new ResourceInitializationException(
          new IOException("Dataset path " + this.directory + " is expected to be a directory!"));

    final List<String> files = FileHandler.getFileNames(inputDirPath, "");
    for (final String filename : files) {
    	if(!filename.contains("only") && !filename.contains("missed")) {
    		final File file = new File(this.directory, filename);
    	      try {
    	        @SuppressWarnings("deprecation")
				final String documentText = FileUtils.readFileToString(file);
    	        this.documentTexts.add(documentText);
    	        this.fileNames.add(filename);
    	      } catch (final IOException e) {
    	        throw new ResourceInitializationException(
    	            new IOException("Could not read file: " + filename + ". Skipping."));
    	      }
    	}
    }
  }

  public int getNumDocuments() {
    return this.documentTexts.size();
  }

  public boolean hasNext() throws IOException, CollectionException {
    return this.nextIndex < this.getNumDocuments();
  }

  public Progress[] getProgress() {
    return new Progress[] {
        new ProgressImpl(this.nextIndex, this.getNumDocuments(), Progress.ENTITIES)};
  }

  @Override
  public void close() throws IOException {
    // nothing
  }

  @SuppressWarnings("resource")
  @Override
  public void getNext(JCas nextCas) throws IOException, CollectionException {

    File file = new File(directory.getAbsolutePath() + "/" + this.fileNames.get(nextIndex));
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    //System.out.println(this.fileNames.get(nextIndex));
    String docName = this.fileNames.get(nextIndex);

    int offset = 0;
    String sCurrentLine;
    StringBuilder documentText = new StringBuilder();
    while ((sCurrentLine = br.readLine()) != null) {
      // create tuple from each line
      if (!sCurrentLine.trim().isEmpty()) {
//    	System.out.println("Before: " + sCurrentLine);
    	sCurrentLine = sCurrentLine.replaceAll("\"", "");
//    	System.out.println("After: " + sCurrentLine);
        sCurrentLine = sCurrentLine.trim();
        
        try {
          newPPTuple(nextCas, offset, sCurrentLine);
        } catch (Exception e) {
          e.printStackTrace();
        }
        documentText.append(sCurrentLine + " ");
        offset += sCurrentLine.length() + 1;
      }
    }

    setDocumentMetadata(nextCas, docName, documentText.toString());
    ++this.nextIndex;
  }

  private void newPPTuple(JCas jCas, int offset, String line) throws Exception {    
    PPTuple tuple = new PPTuple(jCas);
    String title = this.fileNames.get(nextIndex);
    Map<String,String> metadata = FileHandler.readIndex(metadataPath + "index");
    /*if(metadata.get(title)==null) {
      System.out.println(title);
      System.in.read();
    }*/
    String[] tupleMetadata = metadata.get(title).split(";"); 
    tuple.setBegin(offset);
    tuple.setEnd(offset + line.length());
    tuple.setText(line);
    tuple.setMetadata1(tupleMetadata[3]);
    tuple.setMetadata2(tupleMetadata[2]);
    tuple.setMetadata3(tupleMetadata[1]);
    tuple.setMetadata4(tupleMetadata[0]);

    tuple.addToIndexes();
  }

  private void setDocumentMetadata(JCas nextCas, String title, String text)
      throws CollectionException {
    DocumentMetaData d = DocumentMetaData.create(nextCas);
    String language = "en";

    d.setDocumentTitle(title);
    d.addToIndexes();

    nextCas.setDocumentLanguage(language);
    nextCas.setDocumentText(text);
  }

}
