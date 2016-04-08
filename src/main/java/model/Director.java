package model;

import Data.CommonConstant;
import process.LaProcess;
import process.PersonnelProcess;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Director extends Personnel {

    public Director(String name, String password, double salary) {
        super(null,name, password, salary, CommonConstant.DIRECTOR_TITLE);
    }


    public String showAllStaff() {
        return PersonnelProcess.getInstance().printInfo();
    }
//    public String showAllLa() {
//        return LaProcess.getLaProcess().printInfo();
//    }
    public boolean HandleLeaveApplication(boolean decision) {
        return false;
    }


}
