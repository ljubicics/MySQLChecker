package controller;


import app.AppCore;
import checker.Checker;
import checker.rules.CSVFileRule;
import com.opencsv.CSVReader;
import database.Database;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import database.Repository;
import gui.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.sql.Statement;
import java.util.List;

@Getter
@Setter
public class ImportAction extends AbstractDBAction{

    public ImportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, new ImageIcon("src/main/java/controller/icons/import.png"));
        putValue(NAME, "Import");
        putValue(SHORT_DESCRIPTION, "Import");
    }


    public void actionPerformed(ActionEvent e) {
        String selected = MainFrame.getInstance().getJTree().getLastSelectedPathComponent().toString();
        JFileChooser jfileChooser = new JFileChooser();

        if(jfileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try {
                File file = jfileChooser.getSelectedFile();

                CSVReader reader = new CSVReader(new FileReader(file));
                List<String[]> csvFajl = reader.readAll();

                int size = csvFajl.get(0).length;
                String[] firstLine = csvFajl.get(0);
                String columns = "";
                for(int i = 0; i < size - 1; i++) {
                    columns += firstLine[i];
                }
                columns += firstLine[size - 1];

                String importQueryCheck = "SELECT " + columns + " FROM " + selected;

                AppCore appCore = MainFrame.getInstance().getAppCore();
                DatabaseImplementation database = (DatabaseImplementation) appCore.getDatabase();
                MYSQLrepository mysqLrepository = (MYSQLrepository) database.getRepository();

                mysqLrepository.bulkImport(csvFajl, selected);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}





