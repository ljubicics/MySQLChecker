package controller;

import app.AppCore;
import app.Main;
import database.DatabaseImplementation;
import database.MYSQLrepository;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportAction extends AbstractDBAction {
    public ExportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, new ImageIcon("src/main/java/controller/icons/export.png"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String selected = MainFrame.getInstance().getJTree().getLastSelectedPathComponent().toString();
        AppCore appCore = MainFrame.getInstance().getAppCore();
        DatabaseImplementation database = (DatabaseImplementation) appCore.getDatabase();
        MYSQLrepository mysqLrepository = (MYSQLrepository) database.getRepository();
        mysqLrepository.resultSetExport(selected);
    }
}
