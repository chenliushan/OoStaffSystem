package exception;

/**
 * Created by liushanchen on 16/3/28.
 */
public class IllegalOperationException extends Exception{
    public IllegalOperationException(String functionName) {
        super("Error: process"+functionName +" like this is illegal.");
    }
}
