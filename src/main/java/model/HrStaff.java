package model;

import Data.CommonConstant;
import exception.DuplicatedException;
import exception.IllegalOperationException;
import exception.UninitializedException;
import process.PersonnelProcess;

/**
 * Created by liushanchen on 16/3/28.
 */
public class HrStaff extends Staff implements Hr {
    public HrStaff(String name, String password, double salary) {
        super(name, password, salary, CommonConstant.HR_TITLE);
    }

    public int createStaff(String name, String password, double salary) {
        Staff staff=new Staff(name, password, salary);

        try {
            return PersonnelProcess.addPersonnel(staff);
        } catch (DuplicatedException e) {
            e.printStackTrace();
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        } catch (UninitializedException e) {
            e.printStackTrace();
        }
        return staff.getId();
    }

    @Override
    public boolean assignSupervisor(int newStaffId, int spid) {
        return  PersonnelProcess.assignSupervisor(newStaffId,spid);
    }

    public boolean deleteStaff() {
        return false;
    }

}
