package model;

import exception.DuplicatedException;
import exception.IllegalOperationException;
import exception.UninitializedException;
import process.PersonnelProcess;

/**
 * Created by liushanchen on 16/3/28.
 */
public class HrStaff extends Staff implements Hr {
    public HrStaff(String name, String password, double salary) {
        super(name, password, salary);
    }

    public int createStaff() {
      return 0;
    }

    public boolean deleteStaff() {
        return false;
    }
}
