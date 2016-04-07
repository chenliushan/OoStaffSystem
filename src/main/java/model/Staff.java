package model;

import Data.CommonConstant;
import process.LaProcess;
import utils.CommonUtils;

import java.util.Date;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Staff extends Personnel {

    int supervisorId;


    public Staff(String name, String password, double salary) {
        super(name, password, salary, CommonConstant.STAFF_TITLE);
    }

    public Staff(String name, String password, double salary, String title) {
        super(name, password, salary, title);
    }


    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }


    public boolean applyALeave(String startDate, String endDate, String note) {
        Date sd = CommonUtils.date(startDate);
        Date ed = CommonUtils.date(endDate);
        if(sd==null||ed==null){
            return false;
        }
        if(LaProcess.getLaProcess().createLa(this, sd, ed, note)!=null){
            return true;
        }else{
            return false;
        }
    }

    public boolean HandleLeaveApplication(boolean decision) {
        return false;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", salary=" + super.getSalary() +
                ", password='" + super.getPassword() + '\'' +
                ", title='" + super.getTitle() + '\'' +
                ", supervisorId=" + supervisorId +
                '}' + "\n";
    }

    public static class StaffStr {
        public static final String SUPERVISOR = "Supervisor";

    }
}
