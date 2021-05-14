package seminar3.dto;

public class DiscountDTO {
    private double discount = 0.8;

    public DiscountDTO(String costumerID, SaleDTO saleDTO) {

    }

    public double getDiscount(){
        return discount;
    }
    
}

