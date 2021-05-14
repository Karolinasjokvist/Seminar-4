package seminar3.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seminar3.dto.ItemDTO;


public class SaleTest {
    private Sale instance;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp(){
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);

        instance = new Sale();
    }

    @After
    public void tearDown(){
        printoutBuffer = null;
        System.setOut(originalSysOut);

    }

    @Test
    public void testSale()
    {
        String printout = printoutBuffer.toString();
        String expectedOutput = "success";
        assertTrue("Sale did not start correctly.", printout.contains(expectedOutput));
    }

    @Test
    public void testAddItem(){
        ItemDTO itemInfo = new ItemDTO(null, null, 0, 0, "123");
        instance.addItem(itemInfo);
        
        assertEquals("Item was not added",
                     itemInfo.getIdentifier(), 
                     instance.getAddedItems().get(0).getIdentifier()
                     );
    }

    @Test
    public void testUpdateRunningTotal(){
        double runningTotalBefore = instance.getTotalPrice();
        ItemDTO itemInfo = new ItemDTO(null, null, 100, 0, null);
        instance.addItem(itemInfo);
        
        assertEquals("RunningTotal was not updated",
                     (itemInfo.getPrice() + runningTotalBefore), 
                     instance.getTotalPrice(),
                     0.01
                     );
    }

    @Test
    public void testCheckForDuplicate(){
        ItemDTO itemInfo = new ItemDTO(null, null, 0, 0, "1234");
        instance.addItem(itemInfo);
        assertEquals("The function did not reckognize item", true, 
                    instance.checkForDuplicate(itemInfo.getIdentifier()));
    }

    @Test
    public void testAddDuplicate(){
        ItemDTO itemInfo = new ItemDTO(null, null, 0, 0, "1234");
        instance.addItem(itemInfo);
        double quantityBefore = instance.getAddedItems().get(0).getQuantity();
        instance.addDuplicateItem(itemInfo.getIdentifier());

        assertEquals("The duplicate item was not added", quantityBefore + 1,
                    instance.getAddedItems().get(0).getQuantity(),0.01);
    }
}
