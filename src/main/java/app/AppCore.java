package app;

import checker.Checker;
import database.Database;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.MainFrame;
import gui.table.TableModel;
import lombok.Getter;
import lombok.Setter;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import org.json.simple.JSONObject;
import resource.implementation.InformationResource;
import tree.Tree;
import tree.implementation.TreeImplementation;
import utils.Constants;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

@Getter
@Setter
public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private DefaultTreeModel defaultTreeModel;
    private Tree tree;
    private Checker checker;

    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImplementation(new MYSQLrepository(this.settings));
        this.tableModel = new TableModel();
        this.tree = new TreeImplementation();
        this.checker = new Checker();
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mysql_ip", Constants.MYSQL_IP);
        settingsImplementation.addParameter("mysql_database", Constants.MYSQL_DATABASE);
        settingsImplementation.addParameter("mysql_username", Constants.MYSQL_USERNAME);
        settingsImplementation.addParameter("mysql_password", Constants.MYSQL_PASSWORD);
        return settingsImplementation;
    }


    public DefaultTreeModel loadResource(){
        InformationResource ir = (InformationResource) this.database.loadResource();
        return this.tree.generateTree(ir);
    }

    public void readDataFromTable(String fromTable){

        tableModel.setRows(this.database.readDataFromTable(fromTable));

        //Zasto ova linija moze da ostane zakomentarisana?
        this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }


    public void run() {
        String query = MainFrame.getInstance().getTextPane().getText().replaceAll("\\r|\\n", "");
        System.out.println(query);

        Stack<String> mistake = checker.check(query);
        DatabaseImplementation databaseImplementation = (DatabaseImplementation) database;

        if(!mistake.isEmpty()) {
            ArrayList<JSONObject> jsonObjects = checker.getDescriptorRepository().read(mistake);

            for(JSONObject j : jsonObjects) {
                JOptionPane.showMessageDialog(null,j.get("desc").toString() + "\n" + j.get("sugg").toString() + "\n" + j.get("name").toString());
            }
        } else {
            this.tableModel.setRows(databaseImplementation.readDataForQuery(query));
        }
    }

}
