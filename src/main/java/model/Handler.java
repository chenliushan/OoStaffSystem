package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushanchen on 16/4/8.
 */

/**
 * chain of responsibility implementation
 */
public abstract class Handler {
    private static Logger logger = LogManager.getLogger(Handler.class.getName());

    public static boolean ENDORSE = true;
    public static boolean DECLINE = false;

    private Handler supervisor = null;
    private List<LeavingApplication> shouldBeHandle = null;


    public Handler(Handler supervisor) {
        this.supervisor = supervisor;
        shouldBeHandle = new ArrayList<LeavingApplication>();
    }

    private void notifyApplicant(LeavingApplication la) {
        Personnel applicant = PersonnelProcess.getInstance().searchById(la.getApplicantId());
        if (applicant instanceof Staff) {
            Staff s = (Staff) applicant;
            s.receiveApplicationResult(la);
        }
    }
    protected void notifySupervisor(LeavingApplication la) {
        supervisor.shouldBeHandle.add(la);
    }



    protected void endorse(LeavingApplication la) {
        shouldBeHandle.remove(la);
        la.endorsed(this);
        if (supervisor != null) {
            notifySupervisor(la);
        } else {
            notifyApplicant(la);
        }
    }
    protected void decline(LeavingApplication la) {
        shouldBeHandle.remove(la);
        la.declined(this);
        notifyApplicant(la);
    }





    protected Handler getSupervisor() {
        return supervisor;
    }

    protected List<LeavingApplication> getAllShouldBeHandle() {
        return shouldBeHandle;
    }


}
