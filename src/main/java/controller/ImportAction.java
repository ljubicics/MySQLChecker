package controller;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
        System.out.println("CAOCAOCAOCAO");
    }

}
