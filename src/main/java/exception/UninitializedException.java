package exception;

/**
 * Created by liushanchen on 16/3/28.
 */
public class UninitializedException extends Exception{
    public UninitializedException() {
        super("Error: the system is not well initialized.");
    }
}
