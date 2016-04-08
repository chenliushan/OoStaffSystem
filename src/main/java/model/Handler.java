package model;

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

    Handler supervisor = null;
    List<LeavingApplication> shouldBeHandle=null;


    public Handler(Handler supervisor) {
        this.supervisor = supervisor;
        shouldBeHandle=new ArrayList<LeavingApplication>();
    }

    private void notifyHandler(LeavingApplication la){
        shouldBeHandle.add(la);
    }


    public void endorse(LeavingApplication la){
        shouldBeHandle.remove(la);
        if(supervisor!=null){
            supervisor.notifyHandler(la);
        }else{
            notifyApplicant(la);
        }
    }
    private void notifyApplicant(LeavingApplication la){

    }
    public void decline(LeavingApplication la){
        notifyApplicant(la);
    }
    public Handler getSupervisor() {
        return supervisor;
    }
    public void checkShouldBeHandle(){
        for (LeavingApplication la:shouldBeHandle){

        }
    }

}
