

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.*;


public class   StringHandlerTest {

 @Test
    public void testPeek() {
        StringHandler stringHandler = new StringHandler("Hello, World!");
        assertEquals('H', stringHandler.Peek(0));
        assertEquals('e', stringHandler.Peek(1));
        assertEquals('!', stringHandler.Peek(12));
        assertEquals('\0', stringHandler.Peek(13));
    }
}