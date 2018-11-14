import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class DocumentFinderFinal {

    public static void main(String[] args) {

        System.out.println("Give file path for document: ");
        Scanner scannerPath = new Scanner(System.in);
        String path = scannerPath.nextLine();
        String extension ="";
        int k = path.lastIndexOf('.');
        if (k > 0) {
            extension = path.substring(k+1);
        }

        System.out.println("Give a list of words for search in document: ");
        Scanner scannerWords = new Scanner(System.in);
        String words = scannerWords.nextLine();

        String[] arrayOfWords = words.split("\\s+");
        ArrayList<String> listOfWords = new ArrayList<String>();
        for (int i = 0; i < arrayOfWords.length; i++){
            listOfWords.add(arrayOfWords[i]);
        }

        if(extension.equals("pdf")){
            System.out.println("\nYou gave a pdf document!\n");
            Map<String,Boolean> results = PDFTextFinder.findTextInPDFDocument(path, listOfWords);
            Iterator<Map.Entry<String, Boolean>> entries = results.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<String, Boolean> entry = entries.next();
                System.out.println("Word = " + entry.getKey() + ", Found = " + entry.getValue());
            }
        }else if(extension.equals("docx")){
            System.out.println("\nYou gave a MSWord document!\n");
            Map<String,Boolean> results = MSWordTextFinder.findTextInWordDocument(path,listOfWords);
            Iterator<Map.Entry<String, Boolean>> entries = results.entrySet().iterator();
            while (entries.hasNext()){
                Map.Entry<String, Boolean> entry = entries.next();
                System.out.println("Word = " + entry.getKey() + ", Found = " + entry.getValue());
            }
        }else{
            System.out.println("\nYou gave wrong document format! The extension of documement must be pdf or docx\n");
        }

    }
}
