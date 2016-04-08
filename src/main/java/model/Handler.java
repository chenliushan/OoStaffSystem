package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushanchen on 16/4/8.
 */

/**
 * 这里要写清楚 chain of responsibility 会使用的方法: post,handle
 * 然后用一个abstract 把统一的post/handle实现了
 * 然后具体的class再实现独特的剩下那个方法
 */
public abstract class Handler {
    private static Logger logger = LogManager.getLogger(Handler.class.getName());

    public static boolean ENDORSE = true;
    public static boolean DECLINE = false;

    private Handler supervisor = null;
    private  List<LeavingApplication> shouldBeHandle = null;


    public Handler(Handler supervisor) {
        this.supervisor = supervisor;
        shouldBeHandle = new ArrayList<LeavingApplication>();
    }

    public void notifySupervisor(LeavingApplication la) {
        supervisor.shouldBeHandle.add(la);
        logger.info("LeavingApplicationS:"+la);
        logger.info("LeavingApplicationS:"+shouldBeHandle);
    }


    public void endorse(LeavingApplication la) {
        shouldBeHandle.remove(la);
        if (supervisor != null) {
            notifySupervisor(la);
        } else {
            notifyApplicant(la);
        }
    }

    private void notifyApplicant(LeavingApplication la) {

    }

    public void decline(LeavingApplication la) {
        notifyApplicant(la);
    }

    public Handler getSupervisor() {
        return supervisor;
    }

    public List<LeavingApplication> getAllShouldBeHandle() {
        logger.info("LeavingApplicationS:"+shouldBeHandle);

        return shouldBeHandle;
    }

    public LeavingApplication getOneShouldBeHandle(int index) {
        return shouldBeHandle.get(index);
    }

    public void checkShouldBeHandle() {
        for (LeavingApplication la : shouldBeHandle) {

        }
    }


}
