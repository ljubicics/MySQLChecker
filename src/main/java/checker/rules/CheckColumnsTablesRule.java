package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import gui.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class CheckColumnsTablesRule implements Rule {
    @Override
    public String check(String query) {

        String[] reci = query.split(" ");
        String ans = "";

        if(reci[0].equalsIgnoreCase("insert")) {
            DatabaseImplementation databaseImplementation = (DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase();
            MYSQLrepository mysqLrepository = (MYSQLrepository) databaseImplementation.getRepository();
            String table = reci[2];
            List<String> columns = new ArrayList<>();

            if(reci[3].equalsIgnoreCase("values")) {
                ans = mysqLrepository.checkTable(table);
                return ans;
            } else {
                for(int i = 4; i < reci.length; i++) {
                    if(!KeywordsLoader.getInstance().getKeywordsList().contains(reci[i])) {
                        if(((Character)reci[i].charAt(0)).equals('(')) {
                            String rec = reci[i].replaceAll("\\(", "");
                            String rec2 = rec.replaceAll(",", "");
                            columns.add(rec2);
                            continue;
                        } else if(((Character)reci[i].charAt(reci[i].length()-1)).equals(')')) {
                            String rec = reci[i].replaceAll("\\)", "");
                            columns.add(rec);
                            continue;
                        } else if(((Character)reci[i].charAt(reci[i].length()-1)).equals(',')) {
                            String rec = reci[i].replaceAll(",", "");
                            columns.add(rec);
                            continue;
                        }
                    }
                }
            }
            ans = mysqLrepository.checkColumnsAndTables(table, columns);
            System.out.println(ans);
            return ans;
        }

        return "null";
    }
}
