package seminar3.view;

import seminar3.controller.Controller;
import seminar3.dto.SaleInfoDTO;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private Controller contr;
    private String[] identifier = {("1234"),("1235")};
    private int itemsLeft = 2;
    private double amountPaid = 100;
    private String currency;
    private int currentItem = 0;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        contr.startSale();
        System.out.println("A new sale has been started");
        SaleInfoDTO saleInfo = contr.identifyItem(identifier[currentItem]);
        System.out.println("An item has been added with identifier");
        System.out.println("Item: "+saleInfo.getItemName());
        System.out.println("price: "+saleInfo.getItemPrice());
        System.out.println("Running total: "+saleInfo.getRunningTotal());
        currentItem++;
        itemsLeft--;

        while(itemsLeft > 0){
            System.out.println("Another item has been added");
            saleInfo = contr.identifyItem(identifier[currentItem]);
            System.out.println("Item: "+saleInfo.getItemName());
            System.out.println("price: "+saleInfo.getItemPrice());
            System.out.println("Running total: "+saleInfo.getRunningTotal());

            currentItem++;
            itemsLeft--;
        }
        System.out.println("Total: "+ saleInfo.getRunningTotal());
        double change = contr.pay(amountPaid, currency);
        System.out.println("The sale is completed, 100 kr was paid");
        System.out.println("Change: "+ change);
    }
}
