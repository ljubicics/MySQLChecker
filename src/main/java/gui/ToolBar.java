package gui;

import javax.swing.*;

public class ToolBar extends JToolBar{

    public ToolBar() {
        super(SwingConstants.HORIZONTAL);
        setFloatable(false);
        this.add(MainFrame.getInstance().getActionManager().getImportAction());
    }
}
