package process;

import exception.IllegalOperationException;
import model.Personnel;
import utils.CommonUtils;

/**
 * Created by liushanchen on 16/3/28.
 */
public class LoginProcess {




    /**
     * since there can be staffs share the same name, staff ID is used to login.
     * @param staffId
     * @param password
     * @return
     */
    public boolean check(String staffId,String password){
        if(CommonUtils.isInteger(staffId)){
            int id=Integer.valueOf(staffId);
            try {
                Personnel personnel=PersonnelProcess.searchById(id);
                if(personnel.getPassword().equals(password)){
                    return true;
                }
            } catch (IllegalOperationException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
