package se.kth.iv1350.seminar4.model;

/*
* The cash register
*/
public class CashRegister {
    private double amount;
    private static CashRegister CASH_REGISTER = new CashRegister();

    private CashRegister() {
        this.amount = 1000;
    }
    
    /** 
     * Creates an instance of a cash register, that is used instead of a constructor
     * because there should only be one instance of a CashRegister
     */
    public static CashRegister getInstance(){
        return CASH_REGISTER;
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