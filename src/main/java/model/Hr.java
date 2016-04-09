package model;

/**
 * Created by liushanchen on 16/3/28.
 */
public interface Hr {
    public int createStaff(Personnel personnel, String name, String password, double salary);

    public boolean deleteStaff(Personnel staff);

    public Personnel viewStaffInfo(int sid);
}
