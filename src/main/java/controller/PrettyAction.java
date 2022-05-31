package controller;

import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class PrettyAction extends AbstractDBAction{
    public PrettyAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, new ImageIcon("src/main/java/controller/icons/pretty.png"));
        putValue(NAME, "Pretty");
        putValue(SHORT_DESCRIPTION, "Pretty");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = MainFrame.getInstance().getTextArea().getText();
        String[] reci = text.split(" ");
        String finalText = "";

        for (int i = 0; i < reci.length; i++) {
            if (KeywordsLoader.getInstance().getKeywordsList().contains(reci[i].toUpperCase())) {
                if (finalText == "") {
                    finalText += reci[i].toUpperCase() + " ";
                } else {
                    finalText += "\n";
                    finalText += reci[i].toUpperCase() + " ";
                }
            }
            else {
                finalText += reci[i] + " ";
            }
        }

        MainFrame.getInstance().getTextArea().setText(finalText);
    }
}
