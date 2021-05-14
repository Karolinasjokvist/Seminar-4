package seminar3.main;

import org.junit.After;
import org.junit.Test;

import seminar3.setup.Main;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

import java.lang.String;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
public class MainTest {
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    @Before
    public void setUp() {
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }
    
    @After
    public void tearDown() {
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testUIHasStarted() {
        String[] args = null;
        Main.main(args);
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue("UI did not start correctly.", printout.contains(expectedOutput));
    } 
}
