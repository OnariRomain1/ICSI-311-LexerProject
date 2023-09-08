
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;


public class LexerTest {
    
     Lexer lexer;
    HashMap<String, TokenType> hashtokens;
    StringHandler stringhandler;
    LinkedList<Token> tokens;
  

    @Before
    public void Initialize(){

         lexer = new Lexer("\"Hello!\"");
         hashtokens = lexer.hashtokens;
         stringhandler = new StringHandler("Hello world");
       

    }

    @Test
    public void LexTest(){

        lexer.Lex();
        
        assertEquals(4, lexer.tokens.size());
        
    //    lexer.tokens.get(0), 
        

     //   assertTrue(lexerTokens.contains(TokenType.WORD));
        

    }

    @Test
    public void MakeHashtokensTest(){

       

        assertTrue( hashtokens.containsKey("while"));
        assertTrue( hashtokens.containsKey("if"));
        assertTrue( hashtokens.containsKey("do"));

       
        assertTrue(hashtokens.containsValue(TokenType.WHILE));
        assertTrue(hashtokens.containsValue(TokenType.IF));
        assertTrue(hashtokens.containsValue(TokenType.DO));
       


    }


    @Test 
    public void isWordORUnderScoreTest(){

        assertTrue(lexer.isWordORUnderScore('a'));
        assertTrue(lexer.isWordORUnderScore('_'));
        assertTrue(lexer.isWordORUnderScore('c'));
    

    }

    @Test 
    public void isDigitOrPeriodTest(){

        char period = '.';
        assertTrue(lexer.isWordORUnderScore('7'));
        assertTrue(lexer.isWordORUnderScore('8'));
        assertTrue(lexer.isDigitOrPeriod('.'));
    

    }


    @Test
    public void ProccesWordTest(){

        lexer.ProccesWord("Hello }", 1);
        tokens.getFirst().equals(TokenType.WORD);



    } 

    @Test
    public void HandleStringLiteralTest(){

        lexer.HandleStringLiteral();

        lexer.tokens.getFirst();

        assertEquals(lexer.tokens.size(), 1);


    }
}
