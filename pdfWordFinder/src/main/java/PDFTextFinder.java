import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PDFTextFinder {

    private PDDocument pdfFile = null;

    public PDFTextFinder(String path) throws IOException {
        pdfFile = PDDocument.load(new File(path));
    }

    public ArrayList<Integer> findTextInPages(String word) throws IOException {

        if(pdfFile == null){
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> newArrayList = new ArrayList<Integer>();
        int numOfPages = pdfFile.getNumberOfPages();

        PDFTextStripper pdfStripper = new PDFTextStripper();

        for(int i = 1; i <= numOfPages; i++){
            pdfStripper.setStartPage(i);
            pdfStripper.setEndPage(i);
            String text = pdfStripper.getText(pdfFile);

            if(text.contains(word)){
                newArrayList.add(i);
            }

        }
        return newArrayList;
    }

    public boolean findTextInDocument(String word) throws IOException {
        return !findTextInPages(word).isEmpty();
    }

    public void CloseDocument() throws IOException {

        if(pdfFile != null){
            pdfFile.close();
        }
    }

    public static Map<String,Boolean> findTextInPDFDocument(String path, ArrayList<String> words){

        PDFTextFinder newPDFTextFinder = null;

        try {
            newPDFTextFinder = new PDFTextFinder(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can not open file" + path);

        }
        Map<String,Boolean> newMap = new HashMap<String, Boolean>();

        try {
            for(int i = 0; i < words.size(); i++){
                newMap.put(words.get(i), newPDFTextFinder.findTextInDocument(words.get(i)));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can not find word");
            return new HashMap<String, Boolean>();
        }

        try {
            newPDFTextFinder.CloseDocument();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Can not close file");
            return new HashMap<String, Boolean>();

        }
        return newMap;
    }

}
