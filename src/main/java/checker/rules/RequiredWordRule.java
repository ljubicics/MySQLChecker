package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;

import java.util.HashMap;

public class RequiredWordRule implements Rule {

    private HashMap<String, String> pairs = new HashMap();

    public RequiredWordRule() {
        pairs.put("SELECT", "FROM");
        pairs.put("INSERT", "INTO");
        pairs.put("DELETE", "FROM");
        pairs.put("UPDATE", "SET");
    }

    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        String key = reci[0];
        String value = this.pairs.get(key.toUpperCase());

        for(int i = 1; i < reci.length; i++) {
            if(KeywordsLoader.getInstance().getKeywordsList().contains(reci[i].toUpperCase())) {
                if(reci[i].equalsIgnoreCase(value)) {
                    return "null";
                }
            }
        }
        return "REQUIRED_WORD";
    }
}
