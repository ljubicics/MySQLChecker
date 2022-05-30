package gui;

import app.AppCore;
import app.Main;
import controller.ActionManager;
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
    private JPanel treePanel;
    private JPanel right;
    private JPanel toolbarPanel;
    private JTextArea textArea;
    private JToolBar toolBar;
    private ActionManager actionManager;
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

        this.actionManager = new ActionManager();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 500));

        jTable = new JTable();
        right = new JPanel();
        textArea = new JTextArea();
        toolbarPanel = new JPanel();
        toolBar = new ToolBar();
        jTable.setPreferredScrollableViewportSize(new Dimension(100, 200));
        jTable.setFillsViewportHeight(true);
        right.setPreferredSize(new Dimension(100, 100));
        toolbarPanel.setPreferredSize(new Dimension(100, 50));
        toolbarPanel.add(toolBar);   // Ovde dodajes toolbar kad napravis, pre toga ga moras inicijalizovati!!!


        //dodavanje buttona u toolbar
        /*JButton buttonImport=new JButton("import");
        JButton buttonExport=new JButton("export");
        JButton buttonPretty=new JButton("pretty");
        JButton buttonRun=new JButton("run");
        toolBar.add(buttonImport);
        toolBar.add(buttonExport);
        toolBar.add(buttonPretty);
        toolBar.add(buttonRun);*/

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(jTable), BorderLayout.SOUTH);
        this.add(textArea, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
        this.add(toolbarPanel, BorderLayout.NORTH);

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
        treePanel = new JPanel(new BorderLayout());
        treePanel.add(jsp, BorderLayout.CENTER);
        this.add(treePanel, BorderLayout.WEST);
        pack();
    }


    @Override
    public void update(Notification notification) {


    }
}
