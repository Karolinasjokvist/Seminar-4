package se.kth.iv1350.seminar4.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.seminar4.model.SaleObserver;

/**
 * TotalRevenueFileOutput This class writes the total income for the cash register, since the program started
 */
public class TotalRevenueFileOutput implements SaleObserver{
    private PrintWriter logFile;
    private double totalRevenue;

    /**
     * Genreates a new instance of the TotalRevenueFileOutput class
     */
    TotalRevenueFileOutput() {
        totalRevenue = 0;
        try {
            logFile = new PrintWriter(new FileWriter("total-revenue.txt"), true);
        } catch (IOException ex) {
            System.out.println("Could not create logger.");
            ex.printStackTrace();
        }
    }

    
    /** 
     * Logs the total revenue in the log file
     * @param priceOfPurchase The price paid for the current sale
     */
    @Override
    public void newSale(double priceOfPurchase) {
        totalRevenue += priceOfPurchase;
        logFile.println("Total revenue: " + totalRevenue);
    }
}
