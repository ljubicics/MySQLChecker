package controller;

import gui.MainFrame;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
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

        JTextPane textPane = MainFrame.getInstance().getTextPane();
        StyledDocument styledDocument = textPane.getStyledDocument();
        String finalText = "";
        try {
            String text = styledDocument.getText(0, styledDocument.getLength());
            System.out.println(text);
            String[] reci = text.split("[ \n]");
            textPane.setText("");
            Style blueStyle = textPane.addStyle("plavi", null);
            Style blackStyle = textPane.addStyle("crni", null);
            for (int i = 0; i < reci.length; i++) {
                if(reci[i] == "\n")
                    continue;
                if (KeywordsLoader.getInstance().getKeywordsList().contains(reci[i].toUpperCase())) {
                    System.out.println(reci[i]);
                   StyleConstants.setForeground(blueStyle, Color.BLUE);
                    if (finalText == "") {
                        styledDocument.insertString(styledDocument.getLength(), reci[i].toUpperCase() + " ", blueStyle);
                        finalText += reci[i].toUpperCase() + " ";
                    } else {
                        styledDocument.insertString(styledDocument.getLength(), "\n", blueStyle);
                        styledDocument.insertString(styledDocument.getLength(), reci[i].toUpperCase() + " ", blueStyle);
                        finalText += "\n" + " ";
                        finalText += reci[i].toUpperCase() + " ";
                    }
                } else {
                    StyleConstants.setForeground(blackStyle, Color.black);
                    styledDocument.insertString(styledDocument.getLength(), reci[i] + " ", blackStyle);
                    finalText += reci[i] + " ";
                }
            }
            textPane.revalidate();
            textPane.repaint();
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
