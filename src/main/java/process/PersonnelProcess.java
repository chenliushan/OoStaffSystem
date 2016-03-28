package process;

import Data.RuntimeData;
import exception.DuplicatedException;
import exception.IllegalOperationException;
import exception.UninitializedException;
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
public class PersonnelProcess {
    private static Logger logger = LogManager.getLogger(Personnel.class.getName());
    private static List<Personnel> PERSONNEL_LIST = null;
    private static int NUM_OF_NORMAL_STAFF = 0;
    private static boolean HAS_DIRECTOR = false;
    private static boolean HAS_HR = false;

    public PersonnelProcess() {
        PERSONNEL_LIST = new ArrayList<Personnel>();
        createDirector(RuntimeData.DirectorInfo.NAME, RuntimeData.DirectorInfo.PASSWORD, RuntimeData.DirectorInfo.SALARY);
        createHrStaff(RuntimeData.HrInfo.NAME, RuntimeData.HrInfo.PASSWORD, RuntimeData.HrInfo.SALARY);
    }

    public int getNextPersonnelId() throws UninitializedException {
        if (PERSONNEL_LIST != null) {
            return PERSONNEL_LIST.size() + 1;
        } else {
            throw new UninitializedException();
        }
    }

    public int addPersonnel(Personnel p) throws DuplicatedException, IllegalOperationException, UninitializedException {
        int id = -1;
        if (p != null) {
            int pid = p.getId();
            if (pid != -1 && PERSONNEL_LIST.size() > pid) {
                if (PERSONNEL_LIST.get(pid).equals(p)) {
                    throw new IllegalOperationException("the same personnel has exist! p:" + p.toString());
                }
            } else {
                id = getNextPersonnelId();
                p.setId(id);
                if (p.getClass().getName() == Staff.class.getName()) {
                    PERSONNEL_LIST.add(p);
                    NUM_OF_NORMAL_STAFF++;
                    return id;
                } else if (p.getClass().getSuperclass().getName() == Staff.class.getName()) {
                    if (!HAS_HR) {
                        PERSONNEL_LIST.add(p);
                        HAS_HR = true;
                        return id;
                    } else {
                        throw new DuplicatedException();
                    }
                } else if (p.getClass().getName() == Director.class.getName()) {
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

    public void deletePersonnel(Personnel p) throws IllegalOperationException {
        if (p != null && NUM_OF_NORMAL_STAFF > 2) {
            if (p.getClass().getName() == Staff.class.getName()) {
                PERSONNEL_LIST.remove(p);
                NUM_OF_NORMAL_STAFF--;
            } else {
                logger.error("p.getClass: " + p.getClass());
            }
        } else {
            logger.error("p==null or !NumOfStaff>2");
        }
        PERSONNEL_LIST.remove(p);
        throw new IllegalOperationException("deletePersonnel() p=" + p.toString());
    }

    public static Personnel searchById(int id) throws IllegalOperationException {
        if (PERSONNEL_LIST == null || id > PERSONNEL_LIST.size()) {
            logger.warn("searchById( ID is lager than list size)");
            return null;
        }
        Personnel personnel = PERSONNEL_LIST.get(id-1);
        if (personnel.getId() == id) {
            return personnel;
        } else {
            for (Personnel p : PERSONNEL_LIST) {
                if (p.getId() == id) {
                    return p;
                }
            }
        }
        return null;
    }

    private void createDirector(String name, String password, double salary) {
        if (!HAS_DIRECTOR) {
            try {
                Director director = new Director(name, password, salary);
                addPersonnel(director);
            } catch (UninitializedException e) {
                e.printStackTrace();
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
        if (!HAS_HR) {
            try {
                HrStaff hrStaff = new HrStaff(name, password, salary);
                addPersonnel(hrStaff);
            } catch (UninitializedException e) {
                e.printStackTrace();
            } catch (IllegalOperationException e) {
                e.printStackTrace();
            } catch (DuplicatedException e) {
                e.printStackTrace();
            }
        } else {
            logger.warn("HR has exist");
        }
    }

}
