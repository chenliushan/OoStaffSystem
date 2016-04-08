package model;

import java.util.List;

/**
 * Created by liushanchen on 16/4/8.
 */


public interface HandleRequest {
    public List<LeavingApplication> getMyToBeHandleLa();

    /**
     * like post
     */
    public void endorse();

    /**
     * like handle
     */
    public void decline();
}
