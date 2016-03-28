package Data;

import model.LeavingApplication;
import model.Personnel;

import java.util.List;

/**
 * Created by liushanchen on 16/3/28.
 */
public class RuntimeData {
    //    public static int NumOfApplication;


    public static List<LeavingApplication> leavingApplicationList = null;

    public static class DirectorInfo{
        public static String NAME = "Jana";
        public static String PASSWORD = "Jana";
        public static double SALARY = 10000000;
    }
    public static class HrInfo{
        public static String NAME = "Jack";
        public static String PASSWORD = "Jack";
        public static double SALARY = 100000;
    }
}
