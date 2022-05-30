package gui;

import javax.swing.*;

public class ToolBar extends JToolBar{

    public ToolBar() {
        super(SwingConstants.HORIZONTAL);
        setFloatable(false);
        this.add(MainFrame.getInstance().getActionManager().getImportAction());
        this.add(MainFrame.getInstance().getActionManager().getExportAction());
        this.add(MainFrame.getInstance().getActionManager().getPrettyAction());
        this.add(MainFrame.getInstance().getActionManager().getRunAction());
    }
}
