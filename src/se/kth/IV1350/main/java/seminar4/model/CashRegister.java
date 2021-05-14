package seminar3.model;

/*
* The cash register
*/
public class CashRegister {
    private double amount;

    /** 
    * Creates an instance of a cash register and initiates the amount to 1000 kr
    */
    public CashRegister() {
        this.amount = 1000;
    }

    
    /** 
     * Returns the amount in the register
     * 
     * @return double the amount
     */
    public double getAmount(){
        return amount;
    }

    
    /** 
     * Updates the amount in the register
     * 
     * @param amount the amount that should update the amount
     */
    public void updateAmount(double amount){
        this.amount += amount;
    }
}