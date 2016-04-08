package model;

import Data.CommonConstant;
import process.PersonnelProcess;

/**
 * Created by liushanchen on 16/3/28.
 */
public class HrStaff extends Staff implements Hr {
    public HrStaff(Personnel personnel, String name, String password, double salary) {
        super(personnel, name, password, salary, CommonConstant.HR_TITLE);
    }

    @Override
    public int createStaff(Personnel personnel, String name, String password, double salary) {
        Staff staff = new Staff(personnel, name, password, salary);
        return PersonnelProcess.getInstance().addPersonnel(staff);

    }


    public boolean deleteStaff(Personnel staff) {
        return PersonnelProcess.getInstance().deletePersonnel(staff);
    }

    @Override
    public Personnel viewStaffInfo(int sid) {
        return PersonnelProcess.getInstance().searchById(sid);
    }

}
