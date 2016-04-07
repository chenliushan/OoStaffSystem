package process;

import model.LeavingApplication;
import model.Personnel;
import model.Staff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liushanchen on 16/4/7.
 */
public class LaProcess {
    private static Logger logger = LogManager.getLogger(LaProcess.class.getName());
    private static LaProcess laInstance = null;
    private static List<LeavingApplication> laList = null;

    private LaProcess() {
        laList = new ArrayList<LeavingApplication>();
    }

    public static LaProcess getLaProcess() {
        if (laInstance == null) {
            laInstance = new LaProcess();
        }
        return laInstance;
    }

    /**
     * the LeavingApplication ID will start from 1
     * the last obj of the list always own the biggest ID.
     * and the next id = largest id+1
     *
     * @return the new Id of new obj
     */
    private int getNextId() {
        int id = laList.size() + 1;
        if (laList.size() > 0) {
            int biggestId = laList.get(laList.size() - 1).getAid();
            if (biggestId >= id) {
                id = biggestId + 1;
            }
        }
        return id;
    }

    /**
     * general speaking, the Aid = position+1
     *
     * @return the LeavingApplication with the specific id.
     */
    public LeavingApplication searchById(int id) {
        LeavingApplication la = laList.get(id - 1);
        if (la.getAid() == id) {
            return la;
        } else {
            for (LeavingApplication a : laList) {
                if (a.getAid() == id) {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * create a new LeavingApplication and insert it into the list
     *
     * @param startDate not null
     * @param endDate   not null
     * @param note      nullable
     * @return the new LeavingApplication
     */
    public LeavingApplication createLa(Staff s, Date startDate, Date endDate, String note) {
        if (startDate != null && endDate != null) {
            int laId = getNextId();
            LeavingApplication la = new LeavingApplication(laId, s.getId(), s.getSupervisorId(), startDate, endDate, note);
            laList.add(la);
            return la;
        } else {
            return null;
        }

    }

    /**
     * @param id
     * @return the LeavingApplication I have made
     */
    public List<LeavingApplication> getMyAllApplication(int id) {
        List<LeavingApplication> myApp = new ArrayList<LeavingApplication>();
        for (LeavingApplication la : laList) {
            if (la.getApplicantId() == id) {
                myApp.add(la);
            }
        }
        if (myApp.size() > 0) {
            return myApp;
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return the LeavingApplication I have made
     */
    public List<LeavingApplication> getMyAllApplicationWithStatus(int id, int status) {
        List<LeavingApplication> myApp = new ArrayList<LeavingApplication>();
        for (LeavingApplication la : laList) {
            if (la.getApplicantId() == id && la.getStatus() == status) {
                myApp.add(la);
            }
        }
        if (myApp.size() > 0) {
            return myApp;
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return the LeavingApplication I should handle
     */
    public List<LeavingApplication> getApplicationIShouldHandle(int id) {
        List<LeavingApplication> myApp = new ArrayList<LeavingApplication>();
        for (LeavingApplication la : laList) {
            if (la.getNextHandler() == id) {
                myApp.add(la);
            }
        }
        if (myApp.size() > 0) {
            return myApp;
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return the LeavingApplication I have handle
     */
    public List<LeavingApplication> getApplicationIHaveHandle(int id) {
        List<LeavingApplication> myApp = new ArrayList<LeavingApplication>();
        for (LeavingApplication la : laList) {
            if (la.getOpponentId() == id) {
                myApp.add(la);
                continue;
            }
            for (int approver : la.getApproverId()) {
                if (approver == id) {
                    myApp.add(la);
                    break;
                }
            }

        }
        if (myApp.size() > 0) {
            return myApp;
        } else {
            return null;
        }
    }
    public String printInfo() {
        logger.info("laList:" + laList);
        return laList.toString();
    }

}
