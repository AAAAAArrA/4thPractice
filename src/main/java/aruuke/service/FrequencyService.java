package aruuke.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FrequencyService {

    private final String PATH = "src/main/resources/File/Manas.docx";

    private HashMap<Character, Integer> frequencyMap = new HashMap<>();


    private HashMap<Character, Double> percentageMap =  new HashMap<>();

    public FrequencyService(){
        readDocxFileAndFindFrequency();
    }

    private static List<Character> list = Arrays.asList('а','б','в','г','д','е','ё','ж','з','и',
            'й','к','л','м','н','ң','о','ө','п','р',
            'с','т','у','ү','ф','х','ц','ч','ш','ъ',
            'ы','ь','э','ю','я');

    public void readDocxFileAndFindFrequency() {

        try {
            File file = new File(PATH);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            System.out.println("Total no of paragraph "+paragraphs.size());
            for (XWPFParagraph para : paragraphs) {
                String tempText = para.getText().toLowerCase();
                for(char ch : tempText.toCharArray()){
                    if(list.contains(ch)){
                        frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0)+1);
                    }
                }
//                System.out.println(para.getText());
            }
//            System.out.println(frequencyMap);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findPercentageRatio(){
        double totalCharacters = 0;
        for(char key : frequencyMap.keySet()){
            totalCharacters += frequencyMap.get(key);
        }

        for(char key : frequencyMap.keySet()){
            double value = (float) ((frequencyMap.get(key)*100)/totalCharacters);
            double scale = Math.pow(10, 3);
            double percentage = Math.ceil(value * scale) / scale;
            System.out.println(key + " = " + percentage);

            percentageMap.put(key, percentage);
        }

        System.out.println(percentageMap);



    }
}
