package seminar3.model;

import seminar3.dto.ItemDTO;


/*
* Represents an item that can be bought
*/
public class Item {
    private String name;
    private String description;
    private double price;
    private double VAT;
    private int quantity;
    private String identifier;

    /** 
     * Creates an instance if an item 
     * @param itemInfo The information about the item from the EIS
     */
    public Item(ItemDTO itemInfo) {
        this.quantity = 1;
        this.name = itemInfo.getName();
        this.description = itemInfo.getDescription();
        this.price = itemInfo.getPrice();
        this.VAT = itemInfo.getVAT();
        this.identifier = itemInfo.getIdentifier();

    }

    /** 
     * Increases the quantity of the item by 1
     */
    public void increaseQuantity(){
        quantity++;
    }

    
    /** 
     * Returns the name of the item
     * 
     * @return String the name
     */
    public String getName() {
        return name;
    }

    
    /** 
     * Returns the description for the item
     * 
     * @return String the description
     */
    public String getDescription() {
        return description;
    }

    
    /** 
     * Returns the price of the item, including VAT
     * 
     * @return double the price
     */
    public double getPrice() {
        return price;
    }

    
    /** 
     * Returns the VAT rate of the item in percent
     * 
     * @return double the VAT
     */
    public double getVAT() {
        return VAT;
    }

    
    /** 
     * Returns the quantity of the item in the sale
     * 
     * @return int the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    
    /** 
     * Returns the identifier of the item
     * 
     * @return String the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

}