package app;

import checker.MapaVaznostLoader;
import controller.KeywordsLoader;
import gui.MainFrame;
import resource.data.Row;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AppCore appCore = new AppCore();
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setAppCore(appCore);
        KeywordsLoader.getInstance();
        MapaVaznostLoader.getInstance();
        // komentar proba
    }

}
