import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class LexerTest {
    
     Lexer lexer;
    HashMap<String, TokenType> hashtokens;

    @Before
    public void Initialize(){
         lexer = new Lexer("for");
         hashtokens = lexer.hashtokens;
    }

    @Test
    public void makeTokenTest(){

       
        lexer.Maketoken();


        assertEquals(hashtokens.containsValue(TokenType.FOR), true);

        assertEquals(hashtokens.size(),1);

    }

    @Test
    public void LexTest(){

    }
}
