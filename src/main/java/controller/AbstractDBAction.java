package controller;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractDBAction extends AbstractAction{

    public ImageIcon loadIcon(String fileName) {

        URL imageURL = getClass().getResource(fileName);
        ImageIcon icon = null;

        if(imageURL != null) {
            icon = new ImageIcon(imageURL);
        } else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }
}
