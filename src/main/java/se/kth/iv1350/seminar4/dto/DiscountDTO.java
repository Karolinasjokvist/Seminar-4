package se.kth.iv1350.seminar4.dto;
/** 
 * Discount data transer object, which stores the rules for the discount.
 */
public class DiscountDTO {
    private final String type;
    private String IDOfDiscountedItem;
    private double amount;
    private double minRequiredPrice;

    /** 
     * Creates an instance of a discount data transfer object, and sets the type to item
     * @param IDOfDiscountedItem the identifier of the item that has this discount
     * @param amount the amount for the discount, either percentual or actual amount
     */
    public DiscountDTO(String IDOfDiscountedItem, double amount) {
        this.type = "item";
        this.IDOfDiscountedItem = IDOfDiscountedItem;
        this.amount = amount;
    }

    /** 
     * Creates an instance of a discount data transfer object, and sets the type to sale
     * @param amount the amount for the discount, either percentual or actual amount
     * @param minRequiredPrice the minimal required price for the sale
     */
    public DiscountDTO(double amount, double minRequiredPrice){
        this.type = "sale";
        this.amount = amount;
        this.minRequiredPrice = minRequiredPrice;
    }

    
    /** 
     * Returns the type of the discount
     * @return String the type
     */
    public String getType(){
        return type;
    }
    
    
    /** 
     * Returns the identifier of the discounted item
     * @return String the identifier
     */
    public String getIDOfDiscountedItem(){
        return IDOfDiscountedItem;
    }

    
    /** 
     * Returns the amount for the discount
     * @return double the amount
     */
    public double getAmount(){
        return amount;
    }

    
    /** 
     * Returns the minimum total price for the discount
     * @return double the required minimal price
     */
    public double getMinRequiredPrice(){
        return minRequiredPrice;
    }
}

