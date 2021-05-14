package seminar3.model;

import java.time.LocalTime;
import java.util.ArrayList;

import seminar3.dto.*;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {
    private LocalTime saleTime;
    private ArrayList<Item> addedItems = new ArrayList<Item>();
    private double totalPrice;
    private double totalVAT;
    
    /**
     * Creates a new instance and saves the time of the sale.
     */
    public Sale() {
        System.out.println("Sale has started successfully");
        saleTime = LocalTime.now();
    }
    
    
    /** 
     * Adds an item to the sale and returns information about the sale
     * 
     * @param itemInfo Information about the item
     * @return SaleInfoDTO the sale information
     */
    public SaleInfoDTO addItem(ItemDTO itemInfo){
        Item item = new Item(itemInfo);
        addedItems.add(item);
        updateRunningTotal();
        SaleInfoDTO saleInfo = new SaleInfoDTO(totalPrice, item);
        return saleInfo;
    }

    /**
     * Updates the running total in the sale
     */
    private void updateRunningTotal(){
        double runningTotal = 0;
        double totalVAT = 0;

        for(Item item : addedItems){
            runningTotal += item.getPrice() * item.getQuantity();
            totalVAT += item.getVAT() * item.getPrice() *item.getQuantity();
        }

        this.totalVAT = totalVAT;
        this.totalPrice = runningTotal;
    }
    

    
    /** 
     * Applies the discount
     * 
     * @param foundDiscount the discount found by the DChandler
     * @return double the running total after the discount
     */
    public double applyDiscount(DiscountDTO foundDiscount){
        totalPrice = totalPrice*foundDiscount.getDiscount();
        return totalPrice;
    }

    
    /** 
     * Returns the time of the sale
     * 
     * @return LocalTime the sale time
     */
    public LocalTime getSaleTime() {
        return saleTime;
    }

    
    /** 
     * Returns the items in the sale
     * 
     * @return ArrayList<Item> the items
     */
    public ArrayList<Item> getAddedItems() {
        return addedItems;
    }

    
    /** 
     * Returns the total price of all the items in the sale, including VAT
     * 
     * @return double the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    
    /** 
     * Returns the total VAT for the sale
     * 
     * @return double the VAT
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    
    /** 
     * Checks for a duplicate of the current item, and returns true or false
     * 
     * @param identifier the identifier of the current item
     * @return boolean
     */
    public boolean checkForDuplicate(String identifier){
        for(Item item : addedItems){
            if(identifier.equals(item.getIdentifier())){
                return true;
            }
        }
        return false;
    }

    
    /** 
     * Adds a duplicate of an item already added to the sale
     * 
     * @param identifier identifier of the current item
     * @return SaleInfoDTO the information about the sale
     */
    public SaleInfoDTO addDuplicateItem(String identifier){
        int indexOfDuplicateItem = 0;
        for(Item item : addedItems){
            if(identifier.equals(item.getIdentifier())){
                break;
            }
            indexOfDuplicateItem++;
        }
        addedItems.get(indexOfDuplicateItem).increaseQuantity();
        updateRunningTotal();
        SaleInfoDTO saleInfo = new SaleInfoDTO(totalPrice, addedItems.get(indexOfDuplicateItem));
        return saleInfo;
    }

    /** 
     * Converts the sale to a SaleDTO to be sent to external systems
     * @return SaleDTO the DTO version of the sale
     */
    public SaleDTO convertToDTO() {
        return new SaleDTO(this.saleTime, this.addedItems, this.totalPrice, this.totalVAT);
    }

    
    /** 
     * Completes the sale
     * @param payment the payment made by the customer
     * @param sale the sale 
     * @return Receipt the generated receipt
     */
    public Receipt complete(PaymentDTO payment, SaleDTO sale) {
        return new Receipt(sale, payment);
    }

}