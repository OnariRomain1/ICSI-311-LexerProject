import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;
public class   StringHandlerTest {
    
    @Test
    public void peekTest(){

        StringHandler peekHandler = new StringHandler();
        peekHandler.Peek(2);
        assertEquals(peekHandler.Peek(2),2);
        assertEquals(peekHandler.Peek(0),0);

    }

    public void PeekStringTest(){
        
    }

}