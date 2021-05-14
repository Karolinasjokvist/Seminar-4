package seminar3.controller;


import java.lang.String;

import seminar3.dto.*;
import seminar3.integration.*;
import seminar3.model.*;



/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private EIShandler eis;
    private EAShandler eas;
    private DChandler dc;
    private Printer printer;
    private CashRegister cashRegister;

    /**
     * Creates an instance of a controller with the external system classes
     * 
     * @param eas 
     * @param eis
     * @param dc
     * @param printer
     */
    public Controller(EAShandler eas, EIShandler eis, DChandler dc, Printer printer){
        this.eas = eas;
        this.eis = eis;
        this.dc = dc;
        this.printer = printer;

        this.cashRegister = new CashRegister();

        System.out.println("Controller has started successfully!");
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale. 
     */
    public void startSale() {
        this.sale = new Sale();
        System.out.println("Sale has started successfully!");
    }

    
    /** 
     * Identifies the item presented
     * 
     * @param identifier
     * @return SaleInfoDTO 
     */
    public SaleInfoDTO identifyItem(String identifier){
        if(sale.checkForDuplicate(identifier)){
           return sale.addDuplicateItem(identifier);
        }else{
           ItemDTO itemInfo = eis.findItem(identifier);
    
        return sale.addItem(itemInfo);
        }
    }

    
    /** 
     * Adds a fitting discount to the sale
     * 
     * @param costumerID
     * @return double
     */
    public double addDiscount(String costumerID){
        SaleDTO saleDTO = new SaleDTO(sale.getSaleTime(), sale.getAddedItems(), 
                                      sale.getTotalPrice(), sale.getTotalVAT());

        DiscountDTO foundDiscount = dc.findDiscount(saleDTO, costumerID);
        sale.applyDiscount(foundDiscount);
        return sale.getTotalPrice();
    }

    
    /** 
     * Executes the payment and calculates the change. Also updates the 
     * external systems.
     * 
     * @param amountPaid
     * @param currency
     * @return double change
     */
    public double pay(double amountPaid, String currency){

        PaymentDTO payment = new PaymentDTO(amountPaid, currency);
        SaleDTO saleDTO = sale.convertToDTO();
        Receipt receipt = sale.complete(payment, saleDTO);

        printer.print(receipt);
        eas.registerPayment(saleDTO, payment);
        eis.updateInventory(saleDTO);
        cashRegister.updateAmount(amountPaid);
        double change = amountPaid - sale.getTotalPrice();
        return change;
    }
}




