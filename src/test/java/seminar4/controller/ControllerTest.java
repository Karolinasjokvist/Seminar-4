package seminar4.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.integration.*;
import se.kth.iv1350.seminar4.dto.SaleInfoDTO;
import se.kth.iv1350.seminar4.dto.ItemDTO;

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
        SaleInfoDTO saleInfoDTO;
        try {
            saleInfoDTO = instance.identifyItem(itemInfo.getIdentifier());
        } catch (ItemNotFoundException | ServerDownException e) {
            fail("Exception was thrown");
            return;
        }

        assertEquals("Item was not Identified", itemInfo.getDescription(), 
                    saleInfoDTO.getItemDescription());
    }


    @Test
    public void testPay(){
        double amountPaid = 100;
        instance.startSale();
        SaleInfoDTO saleInfo;
        try {
            saleInfo = instance.identifyItem("1234");
        } catch (ItemNotFoundException | ServerDownException e) {
            fail("Exception was thrown");
            return;
        }
        double totalPrice = saleInfo.getRunningTotal();
        double calculatedChange = instance.pay(amountPaid, null);

        assertEquals("Change was not correctly calculated", 
                    (amountPaid - totalPrice), calculatedChange, 0.01);
    }
}