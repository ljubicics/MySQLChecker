package gui;

import app.AppCore;
import app.Main;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;
import tree.implementation.SelectionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Vector;

@Data
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JTable jTable;
    private JScrollPane jsp;
    private JTree jTree;
    private JPanel panel;
    private JTextArea textArea;
    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(200, 100));
        jTable.setFillsViewportHeight(true);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(jTable), BorderLayout.SOUTH);
        textArea = new JTextArea();
        this.add(textArea, BorderLayout.CENTER);
        //proba
        //proba2
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
        initialiseTree();
    }

    private void initialiseTree() {
        DefaultTreeModel defaultTreeModel = appCore.loadResource();
        jTree = new JTree(defaultTreeModel);
        jTree.addTreeSelectionListener(new SelectionListener());
        jsp = new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        panel = new JPanel(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        this.add(panel, BorderLayout.WEST);
        pack();
    }


    @Override
    public void update(Notification notification) {


    }
}
