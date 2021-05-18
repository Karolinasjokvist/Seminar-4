package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;

import se.kth.iv1350.seminar4.discount.DiscountFinder;
import se.kth.iv1350.seminar4.dto.*;

/**
 * Discount Catalogue Handler, which handles an external database of discounts.
 */
public class DChandler {

    ArrayList<DiscountDTO> availableDiscounts = new ArrayList<DiscountDTO>();

    /**
     * This function creates a new instance of the Discount catalog handler 
     */
    public DChandler(){
        availableDiscounts.add(new DiscountDTO("1234", 0.1));
        availableDiscounts.add(new DiscountDTO(10, 50));
        availableDiscounts.add(new DiscountDTO("1235",0.5));  
    }

    
    /** 
     * Finds a discount that is appliable, by using the DiscountFinder method 
     * @param sale Information about the sale that the discount should be added to
     * @param finder The intended DiscountFinder method
     * @return ArrayList<DiscountDTO> a list of valid available discounts
     */
    public ArrayList<DiscountDTO> findDiscount(SaleDTO sale, DiscountFinder finder){
        return finder.findDiscounts(sale, availableDiscounts);
    }
    
}
