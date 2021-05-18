package se.kth.iv1350.seminar4.model;

import se.kth.iv1350.seminar4.dto.DiscountDTO;
import se.kth.iv1350.seminar4.dto.ItemDTO;

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
     * Applies the discount that is valid for this item, and updates the price for the item
     * @param discount the discount
     */
    public void applyItemDiscount(DiscountDTO discount){
        if(discount.getAmount() < 1)
            price *= 1 - discount.getAmount();
        else
            price -= discount.getAmount();
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