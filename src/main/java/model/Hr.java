package model;

/**
 * Created by liushanchen on 16/3/28.
 */
public interface Hr {
     int createStaff(Personnel personnel, String name, String password, double salary);

     boolean deleteStaff(Personnel staff);

     Personnel viewStaffInfo(int sid);
}
