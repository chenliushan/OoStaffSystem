package model;

import exception.IllegalOperationException;

/**
 * Created by liushanchen on 16/3/28.
 */
public interface Hr {
    public int createStaff(String name, String password, double salary);
    public boolean assignSupervisor(int newStaffId, int spid);
    public boolean deleteStaff(Personnel staff) throws IllegalOperationException;
    public Personnel viewStaffInfo(int sid);
}
