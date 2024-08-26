package lu.snt.svv.oac.ll.dpas.ml.solution.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
public class CSVParserAll extends JCasCollectionReader_ImplBase {

  public static final String PARAM_INPUT_DIR_PATH = "inputDirPath";
  @ConfigurationParameter(name = PARAM_INPUT_DIR_PATH, mandatory = true,
      description = "Input directory for the files")
  private String inputDirPath;
  private File directory;

  private List<String> documentTexts;
  private int nextIndex;
  private List<String> fileNames;

  final static int SENT_INDEX = 0;
  final static int SENTENCE = 1;
  final static int M1_INDEX = 2;
  final static int M2_INDEX = 3;
  final static int M3_INDEX = 4;
  final static int M4_INDEX = 5;

  public void initialize(final UimaContext context) throws ResourceInitializationException {
    super.initialize(context);
    this.documentTexts = new ArrayList<String>();
    this.fileNames = new ArrayList<String>();
    this.directory = new File(inputDirPath);
    if (!this.directory.isDirectory())
      throw new ResourceInitializationException(
          new IOException("Dataset path " + this.directory + " is expected to be a directory!"));

    final List<String> files = FileHandler.getFileNames(inputDirPath, ".txt");
    for (final String filename : files) {
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
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
//    System.out.println(directory.getAbsolutePath() + "/" + this.fileNames.get(nextIndex));
//    System.out.println(inputDirPath + this.fileNames.get(nextIndex));
    int offset = 0;
    String sCurrentLine;
    StringBuilder documentText = new StringBuilder();
    while ((sCurrentLine = br.readLine()) != null) {
      // create tuple from each line
      if(sCurrentLine.contains("MDL1"))
        continue;
      if (!sCurrentLine.trim().isEmpty()) {
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
    String docName = this.fileNames.get(nextIndex).substring(0, this.fileNames.get(nextIndex).indexOf("."));
    setDocumentMetadata(nextCas, docName, documentText.toString());
    System.out.println("Processing the document: " + docName);
    ++this.nextIndex;
  }

  private void newPPTuple(JCas jCas, int offset, String line) throws Exception {
    List<String> cells = getCells(line);
    if (cells.size()!=6) {
    	throw new Exception("Error parsing the following sentence: \"" + line + "\"");
    }
    PPTuple tuple = new PPTuple(jCas);
    tuple.setBegin(offset);
    tuple.setEnd(offset + line.length());
    tuple.setText(cells.get(SENTENCE));
    tuple.setMetadata1(cells.get(M1_INDEX));
    tuple.setMetadata2(cells.get(M2_INDEX));
    tuple.setMetadata3(cells.get(M3_INDEX));
    tuple.setMetadata4(cells.get(M4_INDEX));
    if (cells.get(M1_INDEX).equalsIgnoreCase("x"))
    	throw new Exception("MDL1 is not set for the following sentence: \"" + line + "\"");
    tuple.addToIndexes();
  }

  private void setDocumentMetadata(JCas nextCas, String title, String text)
      throws CollectionException {
    DocumentMetaData d = DocumentMetaData.create(nextCas);
    String language = "en";
    // String title = inputPath.substring(inputPath.lastIndexOf("/") + 1, inputPath.indexOf("."));

    d.setDocumentTitle(title);
    d.addToIndexes();

    nextCas.setDocumentLanguage(language);
    nextCas.setDocumentText(text);
  }

  private static List<String> getCells(String line) {
    List<String> cells = new ArrayList<String>();
    String[] cols = line.split("\t");
    for (String c : cols) {
      if(c.trim().isEmpty())
        break;
      if (c.startsWith("\"") && c.endsWith("\"") && c.length()>1) { 
        c = c.substring(1, c.length() - 1);
      }
      cells.add(c.trim().replaceAll("\\s+", " "));
    }
    return cells;
  }



}
