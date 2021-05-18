package se.kth.iv1350.seminar4.discount;

import java.util.ArrayList;

import se.kth.iv1350.seminar4.dto.DiscountDTO;
import se.kth.iv1350.seminar4.dto.SaleDTO;
import se.kth.iv1350.seminar4.model.Item;

public class ItemDiscount implements DiscountFinder{

    
    /** 
     * Finds an eligeble discount for a specific item in the sale.
     * 
     * @param saleDTO details about the sale, specifically the items are used
     * @param availableDiscounts All discounts in DChandler
     * @return ArrayList<DiscountDTO> The list of discounts that are appliable to the sale
     */
    @Override
    public ArrayList<DiscountDTO> findDiscount(SaleDTO saleDTO, ArrayList<DiscountDTO> availableDiscounts) {
        ArrayList<DiscountDTO> foundDiscounts = new ArrayList<DiscountDTO>();
        for(DiscountDTO discount : availableDiscounts){
            for(Item item : saleDTO.getAddedItems()){
                if(!(discount.getType().equals("item"))) continue;
                if(item.getIdentifier().equals(discount.getIDOfDiscountedItem())){
                    foundDiscounts.add(discount);
                }
            }
        }
        return foundDiscounts;
    }
    
}
