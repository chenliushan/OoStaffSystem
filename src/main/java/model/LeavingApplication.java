package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */
public class LeavingApplication {
    public static int PROCESSING = 0;
    public static int SUCCESS = 1;
    public static int FAIL = -1;
    private int Aid;
    private int applicantId;
    private Date startDate;
    private Date endDate;
    private String note;
    private List<Integer> approverId;
    private int opponentId;
    private int status;

    public LeavingApplication(int applicantId, Date endDate, String note, Date startDate) {
        this.applicantId = applicantId;
        this.endDate = endDate;
        this.note = note;
        this.startDate = startDate;
        this.status = PROCESSING;
        this.approverId=new ArrayList<Integer>();
    }

    /**
     * this function should be invoked when supervisor handle the application and endorse it.
     * @param personnel
     * @return true means the application is success, false means it needs further processing.
     */
    public boolean endorse(Personnel personnel){
        approverId.add(personnel.getId());
        if(personnel.getClass()==Director.class){
            this.status=SUCCESS;
            return true;
        }else{
            return false;
        }
    }

    public void decline(Personnel personnel){
        opponentId=personnel.getId();
        this.status=FAIL;
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
}
