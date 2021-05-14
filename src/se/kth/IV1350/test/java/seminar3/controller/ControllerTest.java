package seminar3.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seminar3.dto.ItemDTO;
import seminar3.dto.SaleInfoDTO;
import seminar3.integration.*;

public class ControllerTest {
    private Controller instance;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp(){
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);

        EIShandler eis = new EIShandler();
        EAShandler eas = new EAShandler();
        DChandler dc = new DChandler();
        Printer printer = new Printer();

        instance = new Controller(eas, eis, dc, printer);
    }

    @After
    public void tearDown(){
        printoutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testUIHasStarted()
    {
        String printout = printoutBuffer.toString();
        String expectedOutput = "success";
        assertTrue("Controller did not start correctly.", printout.contains(expectedOutput));
    }

    @Test
    public void testStartSale(){

        instance.startSale();
        String printout = printoutBuffer.toString();
        String expectedOutput = "success";
        assertTrue("Controller did not start correctly.", printout.contains(expectedOutput));

    }

    @Test
    public void testIdentifyItem(){

        instance.startSale();
        ItemDTO itemInfo = new ItemDTO(null, "green apple", 0, 0, "1234");
        SaleInfoDTO saleInfoDTO = instance.identifyItem(itemInfo.getIdentifier());

        assertEquals("Item was not Identified", itemInfo.getDescription(), 
                    saleInfoDTO.getItemDescription());


        itemInfo = new ItemDTO(null, "description", 0, 0, "0000");
        saleInfoDTO = instance.identifyItem(itemInfo.getIdentifier());
            
        assertEquals("Item was not Identified", itemInfo.getDescription(), 
                    saleInfoDTO.getItemDescription());
    }


    @Test
    public void testPay(){
        double amountPaid = 100;
        instance.startSale();
        SaleInfoDTO saleInfo =instance.identifyItem("1234");
        double totalPrice = saleInfo.getRunningTotal();
        double calculatedChange = instance.pay(amountPaid, null);

        assertEquals("Change was not correctly calculated", 
                    (amountPaid - totalPrice), calculatedChange, 0.01);
    }
}