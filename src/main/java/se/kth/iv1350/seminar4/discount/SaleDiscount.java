package se.kth.iv1350.seminar4.discount;

import java.util.ArrayList;

import se.kth.iv1350.seminar4.dto.DiscountDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;

public class SaleDiscount implements DiscountFinder{

    
    /** 
     * Finds a discount that is appiable, depending on the total price.
     * 
     * @param saleDTO details about the sale, specifically the total price
     * @param availableDiscounts All discounts in DChandler
     * @return ArrayList<DiscountDTO> The list of discounts that are appliable to the sale
     */
    @Override
    public ArrayList<DiscountDTO> findDiscount(SaleDTO saleDTO, ArrayList<DiscountDTO> availableDiscounts) {
        ArrayList<DiscountDTO> foundDiscounts = new ArrayList<DiscountDTO>();
        for(DiscountDTO discount : availableDiscounts){
                if(!(discount.getType().equals("sale"))) continue;
                if(saleDTO.getTotalPrice() >= discount.getMinRequiredPrice()){
                    foundDiscounts.add(discount);
                }
            }
        return foundDiscounts;
    }
}
    
