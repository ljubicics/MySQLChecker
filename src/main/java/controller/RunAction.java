package controller;

import app.AppCore;
import checker.Checker;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RunAction extends AbstractDBAction{
    public RunAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, new ImageIcon("src/main/java/controller/icons/run.png"));
        putValue(NAME, "Run");
        putValue(SHORT_DESCRIPTION, "Run");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String query = MainFrame.getInstance().getTextPane().getText();
        AppCore appCore = MainFrame.getInstance().getAppCore();
        Checker checker = appCore.getChecker();
        checker.check(query);
    }
}
