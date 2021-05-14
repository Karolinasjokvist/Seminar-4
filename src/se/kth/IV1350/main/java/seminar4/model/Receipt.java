package seminar3.model;

import java.time.LocalTime;
import java.util.ArrayList;

import seminar3.dto.*;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt {
    private String storeInformation;
    private LocalTime timeOfSale;
    private ArrayList<Item> addedItems = new ArrayList<Item>();
    private double totalPrice;
    private double totalVAT;
    private double paidAmount;
    private String currency;

    /** 
     * Creates an instance of a receipt
     * @param sale Information about the sale
     * @param payment Information about the payment
     */
    public Receipt(SaleDTO sale, PaymentDTO payment) {
        this.timeOfSale = sale.getTimeOfSale();
        this.addedItems = sale.getAddedItems();
        this.totalPrice = sale.getTotalPrice();
        this.totalVAT = sale.getTotalVAT();
        this.paidAmount = payment.getAmount();
        this.currency = payment.getCurrency();
    }

    
    
    /** 
     * Returns the store information
     * 
     * @return String the storeInformation
     */
    public String getStoreInformation() {
        return storeInformation;
    }


    
    /** 
     * Returns the time time when the sale was started
     * 
     * @return LocalTime the timeOfSale
     */
    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }


    
    /** 
     * Returns all items bought
     * 
     * @return ArrayList<Item> the addedItems
     */
    public ArrayList<Item> getAddedItems() {
        return addedItems;
    }


    
    /** 
     * Returns the total price for all items, including VAT
     * 
     * @return double the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }


    
    /** 
     * Returns the total amount of VAT paid 
     * 
     * @return double the totalVAT
     */
    public double getTotalVAT() {
        return totalVAT;
    }


    
    /** 
     * Returns the amount paid
     * 
     * @return double paidAmount
     */
    public double getPaidAmount() {
        return paidAmount;
    }

    
    /** 
     * Returns the currency that was used to pay
     * 
     * @return String currency
     */
    public String getCurrency(){
        return currency;
    }

}