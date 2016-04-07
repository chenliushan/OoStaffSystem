package process;

import Data.RuntimeData;
import exception.IllegalOperationException;
import exception.UninitializedException;
import model.LeavingApplication;
import model.Personnel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liushanchen on 16/4/7.
 */
public class LaProcess {
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

    public LeavingApplication createLa(Date startDate, Date endDate, String note) {
        int laId = getNextId();
        LeavingApplication la = new LeavingApplication(laId, startDate, endDate, note);
        laList.add(la);
        return la;
    }

    /**
     * the LeavingApplication ID will start from 1
     * the last obj of the list always own the biggest ID.
     * and the next id = largest id+1
     * @return the new Id of new obj
     */
    private int getNextId() {
        int id=laList.size() + 1;
        if(laList.size()>0){
            int biggestId=laList.get(laList.size()-1).getAid();
            if(biggestId>=id){
                id=biggestId+1;
            }
        }
        return id;
    }

    /**
     * general speaking, the Aid = position+1
     *
     * @return
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
}
