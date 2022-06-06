package checker.rules;

import app.AppCore;
import checker.MapaVaznostLoader;
import checker.Rule;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import controller.KeywordsLoader;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import gui.MainFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PriorityRule implements Rule {

    @Override
    public String check(String query) {
        String[] reci = query.split(" ");
        List<Integer> nmbrList = new ArrayList<>();
        HashMap<String, Integer> mapa = MapaVaznostLoader.getInstance().getMapaVaznosti();

        for(int i = 0; i < reci.length; i++) {
            if(mapa.containsKey(reci[i].toUpperCase())) {
                nmbrList.add(mapa.get(reci[i].toUpperCase()));
            }
        }
        
        //Integer nmbrBefore = 0;
        for(int i = 0; i < nmbrList.size() - 1; i++) {
            if(nmbrList.get(i) < nmbrList.get(i + 1)) {
                continue;
            } else {
                return "PRIORITY";
            }
        }
        return "null";
    }
}
