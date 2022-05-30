package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportAction extends AbstractDBAction {
    public ExportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("//"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
