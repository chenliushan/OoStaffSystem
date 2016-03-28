package exception;

/**
 * Created by liushanchen on 16/3/28.
 */
public class DuplicatedException extends Exception{
    public DuplicatedException() {
        super("Error: Cannot create more than one director or HrStaff.");
    }
}
