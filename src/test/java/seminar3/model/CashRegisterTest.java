package seminar3.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;

import se.kth.iv1350.seminar4.model.CashRegister;


public class CashRegisterTest {
    private CashRegister instance;

    @Before
    public void setUp(){
        instance = CashRegister.getInstance();
    }

    @After
    public void tearDown(){
        instance = null;

    }


    public void testUpdateAmount(){
        double testedAmount = 50;
        double amountBefore = instance.getAmount();
        instance.updateAmount(testedAmount);
        
        assertEquals(
            "UpdateAmount did not update", 
            amountBefore + testedAmount, 
            instance.getAmount(),
            0.01
            );
    }
}