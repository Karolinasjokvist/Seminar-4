package seminar3.dto;

import seminar3.model.Item;

/*
* Sale data transfer object, which stores the information about the sale, and is sent to the view during the sale
*/
public class SaleInfoDTO {
    private double runningTotal;
    private String itemDescription;
    private double itemPrice;
    private String itemName;

    /**
     * This function creates a new instance of a sale information data transfer object
     * @param runningTotal the runnng total of the sale, including VAT
     * @param addedItem the last added item
     */
    public SaleInfoDTO(double runningTotal, Item addedItem) {
        this.runningTotal = runningTotal;
        this.itemDescription = addedItem.getDescription();
        this.itemPrice = addedItem.getPrice();
        this.itemName = addedItem.getName();
    }

    
    /** 
     * Returns the running total of the sale
     * 
     * @return double the runningTotal
     */
    public double getRunningTotal() {
        return runningTotal;
    }

    
    /** 
     * Returns the item description, of the last added item
     * 
     * @return String the item description
     */
    public String getItemDescription() {
        return itemDescription;
    }

    
    /** 
     * Returns the price of the last added item
     * 
     * @return double the price
     */
    public double getItemPrice() {
        return itemPrice;
    }

    
    /** 
     * Returns the name of the last added item
     * 
     * @return String the name
     */
    public String getItemName() {
        return itemName;
    }
    
}
