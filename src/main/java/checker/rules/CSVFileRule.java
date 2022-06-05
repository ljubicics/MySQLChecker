package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;

public class CSVFileRule implements Rule {
    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        String[] columns = new String[100];
        int br = 0;
        for(int i = 1; i > reci.length; i++) {
            if(!KeywordsLoader.getInstance().getKeywordsList().contains(reci[i].toUpperCase())) {
                columns[br] = reci[i];
                br++;
            } else {
                break;
            }
        }
        return null;
    }
}
