package model;

import Data.CommonConstant;
import process.PersonnelProcess;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Director extends Personnel {

    public Director(String name, String password, double salary) {
        super(name, password, salary, CommonConstant.DIRECTOR_TITLE);
    }

    public boolean applyALeave(String startDate, String endDate) {
        return false;
    }

    public String showAllStaff() {
        return PersonnelProcess.printInfo();
    }
    public boolean HandleLeaveApplication(boolean decision) {
        return false;
    }
}
