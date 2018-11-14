import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TestMSWordTextFinder {

    public static void main(String[] args) {

        String path = "C:/Users/BITSKO/Desktop/sampledocument.docx";

        ArrayList<String> words = new ArrayList<String>();
        words.add("face");
        words.add("storms");
        words.add("wiping");
        words.add("Froissart");
        words.add("victories");
        words.add("war");
        words.add("terrible ");
        words.add("March");
        words.add("Ioanna");

        Map<String,Boolean> results = MSWordTextFinder.findTextInWordDocument(path, words);

        Iterator<Map.Entry<String, Boolean>> entries = results.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String, Boolean> entry = entries.next();
            System.out.println("Word = " + entry.getKey() + ", Found = " + entry.getValue());
        }


    }
}
