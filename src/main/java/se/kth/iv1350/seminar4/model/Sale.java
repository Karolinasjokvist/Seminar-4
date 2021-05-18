package se.kth.iv1350.seminar4.model;

import java.time.LocalTime;
import java.util.ArrayList;


import se.kth.iv1350.seminar4.dto.*;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {
    private LocalTime saleTime;
    private ArrayList<Item> addedItems = new ArrayList<Item>();
    private double totalPrice;
    private double totalVAT;
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();
    
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
     * Completes the sale and notifies observers
     * @param payment the payment made by the customer
     * @param sale the sale 
     * @return Receipt the generated receipt
     */
    public Receipt complete(PaymentDTO payment, SaleDTO sale) {
        notifyObservers();
        return new Receipt(sale, payment);
    }

    private void notifyObservers() {
        for(SaleObserver obs : saleObservers)
            obs.newSale(totalPrice);
    }

    /**
     * Adds an observer. The observer will be notified when sale is completed
     * 
     * @param obs The observer to notify. 
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }

    /** 
     * Applies the discounts that are valid based on the total price. Depending on the amount, 
     * either as a percentual discount or just an amount that is subtracted.
     * @param discounts the discounts found
     */
    public void applySaleDiscount(ArrayList<DiscountDTO> discounts){
        for(DiscountDTO discount : discounts){
            if(discount.getAmount() < 1)
                totalPrice *= 1 - discount.getAmount();
            else
                totalPrice -= discount.getAmount();
            }
        }


    
    /** 
     * Applies the discounts that are valid based on the items in the sale.
     * @param discounts the discounts found
     */
    public void applyItemDiscount(ArrayList<DiscountDTO> discounts){
        for(DiscountDTO discount : discounts){
            for(Item item : addedItems){
                if(item.getIdentifier().equals(discount.getIDOfDiscountedItem())){
                    item.applyItemDiscount(discount);
                }
            }
        }
        updateRunningTotal();
    }
}
