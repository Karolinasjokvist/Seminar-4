package se.kth.iv1350.seminar4.integration;

/**
 * The exception when the server for EIShandler is unreachable. 
 */
public class ServerDownException extends Exception{
    /**
     * This function creates a new instance of the ServerDownException
     * @param message the message to be used for debugging purposes
     */
    public ServerDownException(String message) {
        super(message);
    }
}
