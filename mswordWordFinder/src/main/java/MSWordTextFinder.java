import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MSWordTextFinder {

    private XWPFDocument docx1;

    public MSWordTextFinder(String path) throws IOException, InvalidFormatException {

        docx1 = new XWPFDocument(OPCPackage.open(new FileInputStream(path)));

    }

    public boolean findTextInDocument(String word) throws IOException {
        XWPFWordExtractor extractor = new XWPFWordExtractor(docx1);
        String text = extractor.getText();

        if(text.contains(word)){
            return true;
        }else{
            return false;
        }

    }

    public void CloseDocument() throws IOException {

        if(docx1 != null){
            docx1.close();
        }
    }

    public static Map<String,Boolean> findTextInWordDocument(String path, ArrayList<String> words){

        MSWordTextFinder newMSWTextFinder = null;

        try {
            newMSWTextFinder = new MSWordTextFinder(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can not open file" + path);
            return new HashMap<String, Boolean>();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Map<String,Boolean> newMap = new HashMap<String, Boolean>();

        try {
            for(int i = 0; i < words.size(); i++){
                newMap.put(words.get(i),(newMSWTextFinder.findTextInDocument(words.get(i))));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can not find word");
            return new HashMap<String, Boolean>();
        }
        try {
            newMSWTextFinder.CloseDocument();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can not close file");
            return new HashMap<String, Boolean>();

        }
        return newMap;
    }


}