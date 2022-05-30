package controller;

import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

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
        boolean flag = false;
        File file = new File("src/main/libraries/reserved_words.txt");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> keywordsList = new ArrayList<String>();
            String line;
            while ((line = bufferedReader.readLine()) !=null){
                keywordsList.add(line);
            }
            bufferedReader.close();

            for (int i = 0; i < reci.length; i++) {
                if(keywordsList.contains(reci[i].toUpperCase())) {
                    System.out.println(reci[i].toUpperCase());
                    MainFrame.getInstance().getTextArea().setText(reci[i].toUpperCase());
                }
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
