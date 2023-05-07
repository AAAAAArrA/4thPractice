package aruuke;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/File/Manas.docx";
        readDocxFile(path);

    }

    public static void readDocxFile(String fileName) {
        HashMap<Character, Integer> map = new HashMap<>();
        List<Character> list = Arrays.asList('а','б','в','г','д','е','ё','ж','з','и',
                                             'й','к','л','м','н','ң','о','ө','п','р',
                                             'с','т','у','ү','ф','х','ц','ч','ш','ъ',
                                             'ы','ь','э','ю','я');
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();

            System.out.println("Total no of paragraph "+paragraphs.size());
            for (XWPFParagraph para : paragraphs) {
                String tempText = para.getText().toLowerCase();
                for(char ch : tempText.toCharArray()){
                    if(list.contains(ch)){
                        map.put(ch, map.getOrDefault(ch, 0)+1);
                    }

                }
                System.out.println(para.getText());
            }
            System.out.println(map);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
