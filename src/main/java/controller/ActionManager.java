package controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ImportAction importAction;
    private ExportAction exportAction;
    private PrettyAction prettyAction;
    private RunAction runAction;

    public ActionManager() {
        initialiseActions();
    }

    public void initialiseActions() {
        importAction = new ImportAction();
        exportAction=new ExportAction();
        prettyAction=new PrettyAction();
        runAction=new RunAction();
    }

}
