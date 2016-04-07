package process;

import Data.CommonConstant;
import Data.RuntimeData;
import exception.DuplicatedException;
import exception.IllegalOperationException;
import model.Director;
import model.HrStaff;
import model.Personnel;
import model.Staff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


    public int addPersonnel(Personnel p) throws DuplicatedException, IllegalOperationException {
        int id = -1;
        if (p != null) {
            int pid = p.getId();
            if (pid != -1 && PERSONNEL_LIST.size() > pid) {
                if (PERSONNEL_LIST.get(pid).equals(p)) {
                    throw new IllegalOperationException("the same personnel has exist! p:" + p.toString());
                }
            } else if (pid == -1) {
                id = getNextPersonnelId();
                p.setId(id);
//                if (p.getClass().getName() == Staff.class.getName()) {
                if (p instanceof HrStaff) {
                    if (!HAS_HR) {
                        PERSONNEL_LIST.add(p);
                        HAS_HR = true;
                        return id;
                    } else {
                        throw new DuplicatedException();
                    }
//                } else if (p.getClass().getSuperclass().getName() == Staff.class.getName()) {
                } else if (p instanceof Staff) {
                    PERSONNEL_LIST.add(p);
                    NUM_OF_NORMAL_STAFF++;
                    return id;
                } else if (p instanceof Director) {
                    if (!HAS_DIRECTOR) {
                        PERSONNEL_LIST.add(p);
                        HAS_DIRECTOR = true;
                        return id;
                    } else {
                        throw new DuplicatedException();
                    }
                } else {
                    logger.error("p.getClass: " + p.getClass());
                }
            }
        } else {
            logger.error("p==null");
        }
        throw new IllegalOperationException("addPersonnel() p=" + p.toString());
    }

    public boolean assignSupervisor(int newStaffId, int spid) {
        if (newStaffId != spid) {
            if (searchById(spid) != null) {
                Staff staff = (Staff) searchById(newStaffId);
                int index = PERSONNEL_LIST.indexOf(staff);
                if (staff != null) {
                    staff.setSupervisorId(spid);
                    PERSONNEL_LIST.set(index, staff);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deletePersonnel(Personnel p)  {
        if (p != null && NUM_OF_NORMAL_STAFF > 0) {
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
            try {
                Director director = new Director(name, password, salary);
                addPersonnel(director);

            } catch (IllegalOperationException e) {
                e.printStackTrace();
            } catch (DuplicatedException e) {
                e.printStackTrace();
            }
        } else {
            logger.warn("Director has exist");
        }
    }

    private void createHrStaff(String name, String password, double salary) {
        if (!HAS_HR && HAS_DIRECTOR) {
            try {
                HrStaff hrStaff = new HrStaff(name, password, salary);
                assignSupervisor(addPersonnel(hrStaff), CommonConstant.DIRECTOR_ID);

            } catch (IllegalOperationException e) {
                e.printStackTrace();
            } catch (DuplicatedException e) {
                e.printStackTrace();
            }
        } else {
            logger.warn("HR has exist");
        }
    }

    public String printInfo() {
        logger.info("PERSONNEL_LIST:" + PERSONNEL_LIST);
        return PERSONNEL_LIST.toString();
    }

}
