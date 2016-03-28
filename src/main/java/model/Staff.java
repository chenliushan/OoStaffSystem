package model;

import Data.CommonConstant;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Staff extends Personnel {

    public Staff(String name, String password, double salary) {
        super(name, password, salary, CommonConstant.STAFF_TITLE);
    }
    public Staff(String name, String password, double salary,String title) {
        super(name, password, salary, title);
    }

    int supervisorId;

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public boolean applyALeave(String startDate, String endDate) {

        return false;
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
                '}'+"\n";
    }
}
