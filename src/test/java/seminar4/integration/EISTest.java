package seminar4.integration;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.integration.EIShandler;
import se.kth.iv1350.seminar4.integration.ItemNotFoundException;
import se.kth.iv1350.seminar4.integration.ServerDownException;

public class EISTest {
    private EIShandler instance;

    
    @Before
    public void setUp(){
        instance = new EIShandler();
    }


    @After
    public void tearDown(){
        instance = null;
    }


    @Test
    public void testFindItem(){
        try{
            ItemDTO item = new ItemDTO("apple", "green apple", 3, 0.25, "1234");
            assertEquals("The correct item was not returned",instance.findItem("1234").getName(), item.getName());
            assertEquals("The correct item was not returned",instance.findItem("1234").getDescription(), item.getDescription());
            assertEquals("The correct item was not returned",instance.findItem("1234").getPrice(), item.getPrice(), 0.01);
            assertEquals("The correct item was not returned",instance.findItem("1234").getVAT(), item.getVAT(), 0.01);
            assertEquals("The correct item was not returned",instance.findItem("1234").getIdentifier(), item.getIdentifier());
    
        } catch(Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    public void testFindItemWithInvalidIdentifier() {
        try {
            instance.findItem("This is an invalid identifier");
            fail("Exception was not thrown when it should have");
        } catch (ItemNotFoundException exc) {
            assertTrue("The exception message was wrong", exc.getMessage().contains("No item"));
        } catch (Exception exc) {
            fail("Wrong exception was thrown: " + exc.getMessage());
        }
    }

    @Test
    public void testServerDownException() {
        try {
            instance.findItem("ServerDownTest");
        } catch (ServerDownException exc) {
            assertTrue("The exception message was wrong", exc.getMessage().contains("server is down"));
        } catch (Exception exc) {
            fail("Wrong exception was thrown: " + exc.getMessage());
        }
    }
}