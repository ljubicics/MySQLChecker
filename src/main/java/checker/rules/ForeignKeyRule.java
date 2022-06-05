package checker.rules;

import checker.Rule;

public class ForeignKeyRule implements Rule {
    @Override
    public String check(String query) {
        String[] reci = query.split(" ");

        if(reci[0].equalsIgnoreCase("select")) {
            
        }
        return null;
    }
}
