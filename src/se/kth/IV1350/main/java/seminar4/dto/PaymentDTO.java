package seminar3.dto;

/*
 * Payment data transfer object, which stores information about the payment
 */
public class PaymentDTO {
    private double amount;
    private String currency;

    /**
     * This function creates a new instance of a payment data transfer object
     * @param amount the amount the customer has paid
     * @param currency the currenct the customer paid in
     */
    public PaymentDTO(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    
    /** 
     * Returns the amount that amount paid
     * 
     * @return double the amount
     */
    public double getAmount() {
        return amount;
    }

    
    /** 
     * Returns the currency that was used in the payment
     * 
     * @return String the currency
     */
    public String getCurrency() {
        return currency;
    }
    
}
