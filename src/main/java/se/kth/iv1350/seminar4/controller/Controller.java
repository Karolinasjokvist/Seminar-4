package se.kth.iv1350.seminar4.controller;

import java.lang.String;
import java.util.ArrayList;

import se.kth.iv1350.seminar4.discount.*;
import se.kth.iv1350.seminar4.dto.*;
import se.kth.iv1350.seminar4.integration.*;
import se.kth.iv1350.seminar4.model.*;


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
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();

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

        this.cashRegister = CashRegister.getInstance();

        System.out.println("Controller has started successfully!");
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     * The function also adds the sale observers that should be notified.
     */
    public void startSale() {
        this.sale = new Sale();
        System.out.println("Sale has started successfully!");
        for(SaleObserver obs : saleObservers)
            sale.addSaleObserver(obs);
    }

    
    /** 
     * Identifies the item presented
     * 
     * @param identifier the identifier of the intenden item
     * @return SaleInfoDTO the info about the sale
     * @throws ItemNotFoundException Exception when item is not found
     * @throws ServerDownException Exception when server is down
     * 
     */
    public SaleInfoDTO identifyItem(String identifier) throws ItemNotFoundException, ServerDownException{
        if(sale.checkForDuplicate(identifier)){
            return sale.addDuplicateItem(identifier);
        } else {
            try {
                ItemDTO itemInfo = eis.findItem(identifier);
                return sale.addItem(itemInfo);
            } catch(ItemNotFoundException | ServerDownException exception) {
                System.out.println("For developers: " + exception.getMessage());
                throw exception;
            }
        }
    }

    
    /** 
     * Adds a fitting discount to the sale, and returns the total price to the view
     * 
     * @return double the total price 
     */
    public double addDiscounts(){
        SaleDTO saleDTO = this.sale.convertToDTO();
        ArrayList<DiscountDTO> itemDiscounts = dc.findDiscount(saleDTO, new ItemDiscount());
        ArrayList<DiscountDTO> saleDiscounts = dc.findDiscount(saleDTO, new SaleDiscount());

        sale.applyItemDiscount(itemDiscounts);
        sale.applySaleDiscount(saleDiscounts);

        return sale.getTotalPrice();
    }

    
    /** 
     * Executes the payment and calculates the change. Also updates the 
     * external systems, and sets the sale to null.
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
        this.sale = null;
        return change;
    }

    /**
     * Adds the created sale observer that should be notified
     *
     * @param obs The observer to notify.
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }
}




