import Data.RuntimeData;
import model.LeavingApplication;
import process.PersonnelProcess;
import view.LoginView;

import java.util.ArrayList;

/**
 * Created by liushanchen on 16/3/28.
 */
public class MyMainProcess {


    public static void main(String[] arg) {
        init();
        new LoginView();

    }

    private static void init() {

    }
    /**
     * the delete staff should be treat carefully, since the staff might be someone else's supervisor.
     * forbidden to delete someone who is a supervisor
     * or resign new supervisor to the related staff
     */

}
