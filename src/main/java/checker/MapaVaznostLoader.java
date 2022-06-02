package checker;

import controller.KeywordsLoader;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

@Getter
@Setter
public class MapaVaznostLoader {
    private static MapaVaznostLoader instance = null;

    private HashMap<String, Integer> mapaVaznosti = new HashMap<>();

    private MapaVaznostLoader() {

    }

    public static MapaVaznostLoader getInstance() {
        if (instance==null){
            instance=new MapaVaznostLoader();
            instance.initialise();
        }
        return instance;
    }

    private void initialise() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/libraries/mapaVaznosti.txt"));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(",");
                mapaVaznosti.put(splitLine[0], Integer.parseInt(splitLine[1]));
                //System.out.println(mapaVaznosti.get(splitLine[0]));
            }
        } catch (Exception ex) {
        }
    }
}
