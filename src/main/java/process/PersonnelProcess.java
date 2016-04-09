package process;

import Data.CommonConstant;
import Data.RuntimeData;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CommonUtils;
import view.MenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */

/**
 * Should change to Singleton design patten
 */
public class PersonnelProcess {
    private static PersonnelProcess thisInstance = null;
    private static Logger logger = LogManager.getLogger(Personnel.class.getName());
    private static List<Personnel> PERSONNEL_LIST = null;
    private static int NUM_OF_NORMAL_STAFF = 0;
    private static boolean HAS_DIRECTOR = false;
    private static boolean HAS_HR = false;

    private PersonnelProcess() {
        PERSONNEL_LIST = new ArrayList<Personnel>();
        createDirector(RuntimeData.DirectorInfo.NAME, RuntimeData.DirectorInfo.PASSWORD, RuntimeData.DirectorInfo.SALARY);
        createHrStaff(RuntimeData.HrInfo.NAME, RuntimeData.HrInfo.PASSWORD, RuntimeData.HrInfo.SALARY);

    }

    public static PersonnelProcess getInstance() {
        if (thisInstance == null) {
            thisInstance = new PersonnelProcess();
        }
        return thisInstance;
    }

    /**
     * the  ID will start from 1
     * the last obj of the list always own the biggest ID.
     * and the next id = largest id+1
     *
     * @return the new Id of new obj
     */
    private int getNextPersonnelId() {
        int id = PERSONNEL_LIST.size() + 1;
        if (PERSONNEL_LIST.size() > 0) {
            int biggestId = PERSONNEL_LIST.get(PERSONNEL_LIST.size() - 1).getId();
            if (biggestId >= id) {
                id = biggestId + 1;
            }
        }
        return id;
    }

    public int addPersonnel(Staff p) {
        int id = -1;
        //A constraint that all the staff have to create with a not null supervisor is implemented.
        if (p != null && p.getSupervisor() != null) {
            id = getNextPersonnelId();
            p.setId(id);
            if (p instanceof HrStaff && !HAS_HR) {
                PERSONNEL_LIST.add(p);
                HAS_HR = true;
            } else if (p instanceof Staff) {
                PERSONNEL_LIST.add(p);
                NUM_OF_NORMAL_STAFF++;
            } else {
                logger.error("p.getClass: " + p.getClass());
            }
        } else {
            logger.error("p==null");
        }
        return id;
    }

    public int addPersonnel(Director director) {
        int id = -1;
        if (director != null && !HAS_DIRECTOR) {
            id = getNextPersonnelId();
            director.setId(id);
            PERSONNEL_LIST.add(director);
            HAS_DIRECTOR = true;
            return id;
        }
        return id;
    }

    private boolean ifIsSupervisor(int pid) {
        for (Personnel p : PERSONNEL_LIST) {
            Handler h = p.getSupervisor();
            if (h != null && h instanceof Personnel) {
                Personnel ps = (Personnel) h;
                if (ps.getId() == pid) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deletePersonnel(Personnel p) {
        if(p instanceof Director ||p instanceof HrStaff){
            return false;
        }
        if (p != null && NUM_OF_NORMAL_STAFF > 0 && !ifIsSupervisor(p.getId())) {
            if (p.getClass().getName() == Staff.class.getName()) {
                PERSONNEL_LIST.remove(p);
                NUM_OF_NORMAL_STAFF--;
                return true;
            } else {
                logger.error("p.getClass: " + p.getClass());
            }
        }
        return false;
    }

    public Personnel searchById(int id) {
        for (Personnel p : PERSONNEL_LIST) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void createDirector(String name, String password, double salary) {
        if (!HAS_DIRECTOR) {
            Director director = new Director(name, password, salary);
            addPersonnel(director);
        } else {
            logger.warn("Director has exist");
        }
    }

    private void createHrStaff(String name, String password, double salary) {
        if (!HAS_HR && HAS_DIRECTOR) {
            HrStaff hrStaff = new HrStaff(searchById(CommonConstant.DIRECTOR_ID), name, password, salary);
            addPersonnel(hrStaff);
        } else {
            logger.warn("HR has exist");
        }
    }

    public String printInfo() {
        return PERSONNEL_LIST.toString();
    }

    /**
     * since staffs can share the same name.
     * Staff ID is used to login.
     *
     * @param staffId
     * @param password
     * @return
     */
    public boolean check(String staffId, String password) {
        if (CommonUtils.isInteger(staffId)) {
            int id = Integer.valueOf(staffId);
            Personnel personnel =searchById(id);
            if (personnel != null && personnel.getPassword().equals(password)) {
                new MenuView(personnel);
                return true;
            }
        }
        return false;
    }
}
