package model;

import utils.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */
public class LeavingApplication {

    public static final int PROCESSING = 0;
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;

    private int applicantId = -1;
    private Date startDate;
    private Date endDate;
    private String note;
    private List<Integer> approverId = null;
    private int opponentId = -1;
    private int status;



    public LeavingApplication(int applicantId, Date startDate, Date endDate, String note) {
        this.applicantId = applicantId;
        this.endDate = endDate;
        this.note = note;
        this.startDate = startDate;
        this.status = PROCESSING;
        this.approverId = new ArrayList<Integer>();
    }

    /**
     * @param handler
     * @return false means operation fail.
     */
    public boolean endorsed(Handler handler) {
        if (handler instanceof Staff) {
            Staff staff = (Staff) handler;
            approverId.add(staff.getId());
            return true;
        } else if (handler instanceof Director) {
            Director d = (Director) handler;
            approverId.add(d.getId());
            status = SUCCESS;
            return true;
        }
        return false;
    }


    public void declined(Handler handler) {
        if (handler instanceof Personnel) {
            Personnel p = (Personnel) handler;
            opponentId = p.getId();
            this.status = FAIL;
        }
    }


    public int getApplicantId() {
        return applicantId;
    }

    public List<Integer> getApproverId() {
        return approverId;
    }


    public int getOpponentId() {
        return opponentId;
    }


    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LeavingApplication{" +
//                "#" + Aid +
                " | applicant:" + applicantId +
                "\n | startDate:" + CommonUtils.dispDate(startDate) +
                " | endDate:" + CommonUtils.dispDate(endDate) +
                " | status:" + displayStatus(status) +
                "\n | note :'" + note + '\'' +
                "\n | approverId :" + approverId +
                " | opponentId :" + opponentId +
                "}\n";
    }

    public String toDisplayStr() {
        return "LeavingApplication{" +
//                "#" + Aid +
                " | applicant:" + applicantId +
                " | startDate:" + CommonUtils.dispDate(startDate) +
                " | endDate:" + CommonUtils.dispDate(endDate) +
                " | status:" + displayStatus(status) +
                "}\n";
    }

    private String displayStatus(int s) {
        switch (s) {
            case 1:
                return "SUCCESS";
            case -1:
                return "FAIL";
            default:
                return "PROCESSING";
        }
    }


    public static class LAStr {
        public static final String APPLICANTID = "Applicant ID";
        public static final String STARTDATE = "Start Date";
        public static final String ENDDATE = "End Date";
        public static final String NOTE = "Note";
        public static final String APPROVERID = "Approvers";
        public static final String OPPONENTID = "Opponent";
        public static final String STATUS = "LeavingApplication Status";
        public static final String NEXTHANDLER = "Next Handler";

    }
}
