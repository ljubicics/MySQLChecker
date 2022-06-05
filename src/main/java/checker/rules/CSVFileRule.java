package checker.rules;

import checker.Rule;
import controller.KeywordsLoader;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import gui.MainFrame;

import java.sql.PreparedStatement;

public class CSVFileRule implements Rule {
    @Override
    public String check(String columns) {
        String[] reci = columns.split(" ");
        String selected = MainFrame.getInstance().getJTree().getLastSelectedPathComponent().toString();

        String query2 = "SELECT * FROM " + selected;

        DatabaseImplementation databaseImplementation = (DatabaseImplementation) MainFrame.getInstance().getAppCore().getDatabase();
        MYSQLrepository mysqLrepository = (MYSQLrepository) databaseImplementation.getRepository();

        String[] columnsFromTable = mysqLrepository.bulkImportChecker(query2);

        for(int i = 0; i < reci.length; i++) {
            if(reci[i].equalsIgnoreCase(columnsFromTable[i])) {
                continue;
            } else {
                return "CSVFILE NIJE U SKLADU SA TABELOM";
            }
        }

        return "null";
    }
}
