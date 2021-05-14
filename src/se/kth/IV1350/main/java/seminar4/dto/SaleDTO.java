package seminar3.dto;

import java.time.LocalTime;
import java.util.ArrayList;

import seminar3.model.Item;

/*
 * Sale data transfer object, which stores the final information about the sale
 */
public class SaleDTO {
    private LocalTime timeOfSale;
    private ArrayList<Item> addedItems = new ArrayList<Item>();
    private double totalPrice;
    private double totalVAT;
    private double runningTotal;

    /**
     * This function creates a new instance of a sale data transfer object
     * @param timeofSale
     * @param addedItems all the added items
     * @param totalPrice the total price of all the items, including VAT
     * @param totalVAT the total amount of VAT
     */
    public SaleDTO(LocalTime timeOfSale, ArrayList<Item> addedItems, double totalPrice, double totalVAT) {
        this.timeOfSale = timeOfSale;
        this.addedItems = addedItems;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
    }


    /** 
     * Returns the time time when the sale was started
     * 
     * @return LocalTime timeOfSale
     */
    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }

    
    /** 
     * Returns all the items
     * 
     * @return ArrayList<Item> the added items
     */
    public ArrayList<Item> getAddedItems() {
        return addedItems;
    }

    
    /** 
     * Returns the total price for all the items, including VAT
     * 
     * @return double the total Price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    
    /** 
     * Returns the total VAT of the sale
     * 
     * @return double the VAT
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    
    /** 
     * @return double
     */
    public double getRunningTotal() {
        return runningTotal;
    }
    
}
