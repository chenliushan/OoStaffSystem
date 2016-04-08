package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import process.PersonnelProcess;

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

    public void notifySupervisor(LeavingApplication la) {
        supervisor.shouldBeHandle.add(la);
    }


    public void endorse(LeavingApplication la) {
        shouldBeHandle.remove(la);
        la.endorsed(this);
        if (supervisor != null) {
            notifySupervisor(la);
        } else {
            notifyApplicant(la);
        }
    }

    private void notifyApplicant(LeavingApplication la) {
        Personnel applicant = PersonnelProcess.getInstance().searchById(la.getApplicantId());
        if (applicant instanceof Staff) {
            Staff s = (Staff) applicant;
            s.receiveApplicationResult(la);
        }
    }

    public void decline(LeavingApplication la) {
        shouldBeHandle.remove(la);
        la.declined(this);
        notifyApplicant(la);
    }

    public Handler getSupervisor() {
        return supervisor;
    }

    public List<LeavingApplication> getAllShouldBeHandle() {
        return shouldBeHandle;
    }


}
