import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringHandlerTest {
    private StringHandler stringHandler;

    @BeforeEach
    public void setUp() {
        // Initialize a new StringHandler object with a sample document
        stringHandler = new StringHandler("Hello, World!");
    }

    @Test
    public void testPeek() {
        
        // Test peeking at characters
        assertEquals('H', stringHandler.Peek(0));
        assertEquals('e', stringHandler.Peek(1));
        assertEquals('l', stringHandler.Peek(2));
        assertEquals('o', stringHandler.Peek(4));
        assertEquals('\0', stringHandler.Peek(13)); 
    }

    @Test
    public void testPeekString() {
        // Test peeking at strings
        assertEquals("Hello", stringHandler.PeekString(5));
        assertEquals("Hello, World!", stringHandler.PeekString(13)); 
        assertEquals("Hello, World!", stringHandler.PeekString(14)); 
    }

    @Test
    public void testGetChar() {
      
        assertEquals('H', stringHandler.GetChar());
        assertEquals('e', stringHandler.GetChar());
        assertEquals('l', stringHandler.GetChar());
        assertEquals('l', stringHandler.GetChar());
        assertEquals('o', stringHandler.GetChar());
        assertEquals(',', stringHandler.GetChar());
        assertEquals(' ', stringHandler.GetChar());
        assertEquals('W', stringHandler.GetChar());
        assertEquals('o', stringHandler.GetChar());
        assertEquals('r', stringHandler.GetChar());
        assertEquals('l', stringHandler.GetChar());
        assertEquals('d', stringHandler.GetChar());
        assertEquals('!', stringHandler.GetChar());
        assertEquals('\0', stringHandler.GetChar()); 
    }

    @Test
    public void testSwallow() {
       
        stringHandler.Swallow(13);
        stringHandler.Swallow(10);
        assertEquals(true, stringHandler.isDone());

    }

    @Test
    public void testIsDone() {
        assertFalse(stringHandler.isDone()); // Not done yet
        stringHandler.Swallow(13); // Move to the end
        assertTrue(stringHandler.isDone()); // Done
    }

    @Test
    public void testRemainder() {
        stringHandler.Swallow(6);
        assertEquals(" World!", stringHandler.Remainder());

    }
}