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
public abstract class MyChainHandler {

    MyChainHandler supervisor = null;
    List<LeavingApplication> shouldBeHandle=null;


    public MyChainHandler(MyChainHandler supervisor) {
        this.supervisor = supervisor;
        shouldBeHandle=new ArrayList<LeavingApplication>();
    }

    private void notifyHandler(LeavingApplication la){
        if(decide()){
            supervisor.notifyHandler(la);
        }else{
            notifyApplicant(la);
        }
    }

    /**
     *
     * @return true if endorse
     */
    public abstract boolean decide();

    public abstract void notifyApplicant(LeavingApplication la);
    public MyChainHandler getSupervisor() {
        return supervisor;
    }

    public List<LeavingApplication> getShouldBeHandle() {
        return shouldBeHandle;
    }

    public void setShouldBeHandle(List<LeavingApplication> shouldBeHandle) {
        this.shouldBeHandle = shouldBeHandle;
    }
}
