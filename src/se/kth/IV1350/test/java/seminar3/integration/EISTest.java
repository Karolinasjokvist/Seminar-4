package seminar3.integration;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seminar3.dto.ItemDTO;

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
        ItemDTO item = new ItemDTO("apple", "green apple", 3, 0.25, "1234");
        assertEquals("The correct item was not returned",instance.findItem("1234").getName(), item.getName());
        assertEquals("The correct item was not returned",instance.findItem("1234").getDescription(), item.getDescription());
        assertEquals("The correct item was not returned",instance.findItem("1234").getPrice(), item.getPrice(), 0.01);
        assertEquals("The correct item was not returned",instance.findItem("1234").getVAT(), item.getVAT(), 0.01);
        assertEquals("The correct item was not returned",instance.findItem("1234").getIdentifier(), item.getIdentifier());

        assertEquals("The null item was not returned", instance.findItem("536").getName(), "noItem");
    }
}