package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */
public class LeavingApplication {
    private static Logger logger = LogManager.getLogger(LeavingApplication.class.getName());

    public static final int PROCESSING = 0;
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;

    private int Aid = -1;
    private int applicantId = -1;
    private Date startDate;
    private Date endDate;
    private String note;
    private List<Integer> approverId = null;
    private int opponentId = -1;
    private int status;
    private int nextHandler = -1;

    public LeavingApplication(int aid, int applicantId, int nextHandler, Date startDate, Date endDate, String note) {
        this.Aid = aid;
        this.applicantId = applicantId;
        this.endDate = endDate;
        this.note = note;
        this.startDate = startDate;
        this.status = PROCESSING;
        this.approverId = new ArrayList<Integer>();
        this.nextHandler = nextHandler;
    }

    /**
     *  this function should be invoked when supervisor handle the application and endorse it.
     * @param staff
     * @return true means the application is success, false means it needs further processing.
     */
    public boolean endorse(Staff staff) {
        approverId.add(staff.getId());
        nextHandler = staff.getSupervisorId();
        return false;

    }

    public boolean endorse(Director d) {
        approverId.add(d.getId());
        status = SUCCESS;
        nextHandler = -1;
        return true;

    }

    public void decline(Personnel personnel) {
        opponentId = personnel.getId();
        this.status = FAIL;
        nextHandler = -1;
    }

    public int getAid() {
        return Aid;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public List<Integer> getApproverId() {
        return approverId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getNote() {
        return note;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getStatus() {
        return status;
    }

    public int getNextHandler() {
        return nextHandler;
    }

    @Override
    public String toString() {
        return "LeavingApplication{" +
                "Aid=" + Aid +
                ", applicantId=" + applicantId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", note='" + note + '\'' +
                ", approverId=" + approverId +
                ", opponentId=" + opponentId +
                ", status=" + status +
                ", nextHandler=" + nextHandler +
                "}\n";
    }

    public static class LAStr{
        public static final String AID = "LeavingApplication ID";
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
