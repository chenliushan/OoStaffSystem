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
        LoginView login = new LoginView();
        init();
    }

    private static void init() {
        RuntimeData.leavingApplicationList = new ArrayList<LeavingApplication>();
        PersonnelProcess personnelProcess = new PersonnelProcess();
    }

}
