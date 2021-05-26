package seminar4.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import se.kth.iv1350.seminar4.dto.ItemDTO;
import se.kth.iv1350.seminar4.model.Item;

public class ItemTest {
    private Item instance;

    @Before
    public void setUp(){
        ItemDTO item = new ItemDTO(null, null, 0, 0, null);
        instance = new Item(item);
    }

    @After
    public void tearDown(){
        instance = null;
    }

    @Test
    public void testIncreaseQuantity()
    {
        int quantityBefore = instance.getQuantity();
        instance.increaseQuantity();

        assertEquals(
            "Increased quantity did not give the expected result", 
            quantityBefore + 1, 
            instance.getQuantity()
            );
    }
}

