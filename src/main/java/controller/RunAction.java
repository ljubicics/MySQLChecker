package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RunAction extends AbstractDBAction{
    public RunAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("//"));
        putValue(NAME, "Run");
        putValue(SHORT_DESCRIPTION, "Run");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
