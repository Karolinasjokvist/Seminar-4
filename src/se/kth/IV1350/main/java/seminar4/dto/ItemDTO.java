package seminar3.dto;

/*
 * Item data transfer object, which stores information about the item
 */
public class ItemDTO {
    private String name;
    private String description;
    private double price;
    private double VAT;
    private String identifier;

    
    /** 
    * Creates an instance of an item data transfer object
    * 
    * @param name
    * @param description
    * @param price
    * @param VAT
    * @param identifier
    * @return String the name
    */
    public ItemDTO(String name, String description, double price, double VAT, String identifier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.VAT = VAT;
        this.identifier = identifier;
    }

    
    /** 
     * Returns the name of the item
     * 
     * @return String the name
     */
    public String getName() {
        return this.name;
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
     * Returns the price of the item, including the VAT
     * 
     * @return double the price
     */
    public double getPrice() {
        return price;
    }

    
    /** 
     * Returns the VAT rate in percent
     * 
     * @return double the VAT
     */
    public double getVAT() {
        return VAT;
    }

    
    /** 
     * Returns the identifier for the item
     * 
     * @return String the identifier
     */
    public String getIdentifier() {
        return identifier;
    }
}
