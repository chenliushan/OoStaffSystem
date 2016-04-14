package model;

import Data.CommonConstant;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Director extends Personnel {

    public Director(String name, String password, double salary) {
        super(null, name, password, salary, CommonConstant.DIRECTOR_TITLE);
    }

    public String showAllStaff() {
        return PersonnelProcess.getInstance().printInfo();
    }

}
