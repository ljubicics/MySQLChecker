package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PrettyAction extends AbstractDBAction{
    public PrettyAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("//"));
        putValue(NAME, "Pretty");
        putValue(SHORT_DESCRIPTION, "Pretty");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
