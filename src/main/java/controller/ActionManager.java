package controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ImportAction importAction;

    public ActionManager() {
        initialiseActions();
    }

    public void initialiseActions() {
        importAction = new ImportAction();
    }

}
