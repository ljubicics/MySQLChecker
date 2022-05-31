package controller;

import gui.MainFrame;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;

@Getter
@Setter

public class KeywordsLoader {

    private static KeywordsLoader instance = null;
    ArrayList<String> keywordsList = new ArrayList<>();
    private KeywordsLoader() {

    }

    public static KeywordsLoader getInstance() {
        if (instance==null){
            instance=new KeywordsLoader();
            instance.initialise();
        }
        return instance;
    }

    private void initialise() {
        try {
            File file = new File("src/main/libraries/reserved_words.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) !=null){
                keywordsList.add(line);
            }
            bufferedReader.close();
        } catch (IOException ex) {
        throw new RuntimeException(ex);
        }
    }
}
