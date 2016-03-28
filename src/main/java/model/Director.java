package model;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Director extends Personnel {

    public Director(String name, String password, double salary) {
        super(name, password, salary);
    }

    public boolean applyALeave(String startDate, String endDate) {
        return false;
    }

    public boolean HandleLeaveApplication(boolean decision) {
        return false;
    }
}
