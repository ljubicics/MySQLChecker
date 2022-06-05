package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;

public class GroupByRule implements Rule {

    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        if(reci[0].equalsIgnoreCase("select")) {
            int br = 0;
            for(int i = 1; i < reci.length; i++) {
                if(!KeywordsLoader.getInstance().getKeywordsList().contains(reci[i])) {
                    br++;
                }
            }

            boolean flag = false;
            if(br >= 2) {
                for(int i = 1; i <= br; i++) {
                    if(reci[i].contains("(")) {
                        flag = true;
                    }
                }
            }

            if(flag == true) {
                for(int i = 0; i < reci.length; i++) {
                    if(reci[i].equalsIgnoreCase("group")) {
                        System.out.println("NASAO");
                        if(reci[i+1].equalsIgnoreCase("by")) {
                            return "null";
                        }
                    }
                }
            }
        }
        return "QUERY NEMA GROUP BY";
    }
}
