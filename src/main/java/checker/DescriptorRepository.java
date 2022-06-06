package checker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class DescriptorRepository {

    private ArrayList<JSONObject> jsonObjectsAll = new ArrayList<>();
    private ArrayList<JSONObject> jsonObjects = new ArrayList<>();

    public ArrayList read(Stack<String> stack) {

        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray array = (JSONArray) jsonParser.parse(new FileReader("src/main/libraries/rules.json"));

            for (Object o : array) {
                JSONObject object = (JSONObject) o;
                jsonObjectsAll.add(object);
            }

            while (!stack.empty()) {
                String s = stack.peek();
                stack.pop();
                for(JSONObject j : jsonObjectsAll) {
                    if(s.equalsIgnoreCase((String) j.get("name")) && !jsonObjects.contains(j)) {
                        jsonObjects.add(j);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObjects;
    }
}
