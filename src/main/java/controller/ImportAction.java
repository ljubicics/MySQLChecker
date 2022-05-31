package controller;

import com.opencsv.CSVReader;
import gui.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;

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
                String[] line;

                while((line = reader.readNext()) != null) {

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}





