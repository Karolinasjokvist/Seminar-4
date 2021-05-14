package seminar3.integration;

import seminar3.dto.DiscountDTO;
import seminar3.dto.SaleDTO;

public class DChandler {

    public DiscountDTO findDiscount(SaleDTO saleDTO, String costumerID){
        DiscountDTO foundDiscount = new DiscountDTO(costumerID, saleDTO);
        return foundDiscount;
    }
    
}
