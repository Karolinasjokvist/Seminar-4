package se.kth.iv1350.seminar4.integration;
/**
 * The exception when an identifier does not match with an item in EIShandler. 
 */
public class ItemNotFoundException extends Exception{
    /**
     * This function creates a new instance of the ItemNotFoundExeption
     * @param message the message to be used for debugging purposes
     */
    public ItemNotFoundException(String message){
        super(message);
    }
    
}
