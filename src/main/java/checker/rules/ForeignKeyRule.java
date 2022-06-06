package checker.rules;

import checker.Rule;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import gui.MainFrame;

import java.util.ArrayList;

public class ForeignKeyRule implements Rule {
    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        String table = "";

        if(reci[0].equalsIgnoreCase("join")) {
            for(int i = 0; i < reci.length; i++) {
                if(reci[i].equalsIgnoreCase("from")) {
                    table = reci[i+1];
                    break;
                }
            }
        }

        DatabaseImplementation databaseImplementation = (DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase();
        MYSQLrepository mysqLrepository = (MYSQLrepository) databaseImplementation.getRepository();
        ArrayList<String> foreignKeys =  mysqLrepository.foreignKeyList(table);

        if(foreignKeys.isEmpty()) {
            return "NEMA FOREIGN KEY";
        }
        String[] tmp = query.split("on");
        if(tmp[1] == null) {
            return "NEMA FOREIGN KEY";
        }

        String key = tmp[1].replaceAll("\\(", "").replaceAll("\\)","");
        String[] left = key.split("=");
        String check = left[0].replaceAll(" ", "");
        if(!foreignKeys.contains(check)) {
            return "NEMA FOREIGN KEY";
        }
        return "null";
    }
}
