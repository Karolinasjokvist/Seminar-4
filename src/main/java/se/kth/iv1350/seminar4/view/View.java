package se.kth.iv1350.seminar4.view;

import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.dto.SaleInfoDTO;
import se.kth.iv1350.seminar4.integration.ItemNotFoundException;
import se.kth.iv1350.seminar4.integration.ServerDownException;

import java.lang.Math;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private String[] identifier = {("1234"),("1235"),("1236")};
    private double amountPaid = 250;
    private String currency;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        contr.startSale();
        SaleInfoDTO saleInfo = null;
        double totalPrice;
        System.out.println("A new sale has been started");
        for(String identifiers : identifier){
            try {
                saleInfo = contr.identifyItem(identifiers);
                totalPrice = saleInfo.getRunningTotal();
                System.out.println();
                System.out.println(saleInfo.getItemName() + " was added"); 
                System.out.println("total price is: "+ totalPrice);
            } catch (ItemNotFoundException | ServerDownException e) {
                System.err.println("Could not find item");
            }
        }

        totalPrice = contr.addDiscounts();
        System.out.println();
        System.out.println("All fitting discounts were applied, new price is: "+ totalPrice);
        System.out.println();
        double change = contr.pay(amountPaid, currency);
        System.out.println();
        System.out.println("The sale is completed, " + amountPaid + " kr was paid");
        
        System.out.println();
        System.out.println("Change: " + ((double)Math.round(change * 100)) / 100);
    }
}
