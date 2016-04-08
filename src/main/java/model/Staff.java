package model;

import Data.CommonConstant;
import utils.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */
public class Staff extends Personnel {

    private List<LeavingApplication> myApplicationResults = null;

    public Staff(Personnel personnel, String name, String password, double salary) {
        super(personnel, name, password, salary, CommonConstant.STAFF_TITLE);
        myApplicationResults = new ArrayList<LeavingApplication>();
    }

    public Staff(Personnel personnel, String name, String password, double salary, String title) {
        super(personnel, name, password, salary, title);
        myApplicationResults = new ArrayList<LeavingApplication>();
    }

    public void receiveApplicationResult(LeavingApplication la) {
        myApplicationResults.add(la);
    }

    public List<LeavingApplication> getMyApplicationResults() {
        return myApplicationResults;
    }

    public boolean applyALeave(String startDate, String endDate, String note) {
        Date sd = CommonUtils.getDate(startDate);
        Date ed = CommonUtils.getDate(endDate);
        if (sd == null || ed == null) {
            return false;
        }
        if (startDate != null && endDate != null) {
            LeavingApplication la = new LeavingApplication(getId(), sd, ed, note);
            notifySupervisor(la);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        if (getSupervisor() instanceof Personnel) {
            Personnel p = (Personnel) getSupervisor();
            return "Staff{" +
                    "id=" + super.getId() +
                    ", name='" + super.getName() + '\'' +
                    ", salary=" + super.getSalary() +
                    ", password='" + super.getPassword() + '\'' +
                    ", title='" + super.getTitle() + '\'' +
                    ", supervisor='" + p.getId() + '\'' +
                    '}' + "\n";
        }
        return "Staff{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", salary=" + super.getSalary() +
                ", password='" + super.getPassword() + '\'' +
                ", title='" + super.getTitle() + '\'' +

                ", supervisor='" + super.getSupervisor() + '\'' +
                '}' + "\n";
    }


    public static class StaffStr {
        public static final String SUPERVISOR = "Supervisor";

    }
}
