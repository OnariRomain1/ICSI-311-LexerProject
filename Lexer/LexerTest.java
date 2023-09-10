
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;


public class LexerTest {
    
     Lexer lexer;
     StringHandler awkFile;

    @Test
    public void LexTest(){
    
        Lexer lexer = new Lexer(".");

        // Call Lex to tokenize the input
        lexer.Lex();

        // Get the list of tokens from the lexer
        LinkedList<Token> tokens = lexer.GetLinkedListTokens();

        // Assert that the tokens were correctly identified
        assertEquals(1, tokens.size());

        // Check the first token
        Token firstToken = tokens.get(0);
        assertEquals(TokenType.FOR, firstToken.getTokenType());
        assertEquals(1, firstToken.getLineNumber());
        assertEquals(1, firstToken.getCharPosition());
        assertNull(firstToken.getTokenValue());

        // Check the second token
        Token secondToken = tokens.get(1);
        assertEquals(TokenType.IF, secondToken.getTokenType());
        assertEquals(1, secondToken.getLineNumber());
        assertEquals(5, secondToken.getCharPosition());
        assertNull(secondToken.getTokenValue());
        // Check the third token
        Token thirdToken = tokens.get(2);
        assertEquals(TokenType.WHILE, thirdToken.getTokenType());
        assertEquals(1, thirdToken.getLineNumber());
        assertEquals(8, thirdToken.getCharPosition());
        assertNull(thirdToken.getTokenValue());

    }

    @Test
    public void MakeHashTokenTest(){

        lexer = new Lexer("");
        lexer.Lex(); 

        assertTrue( lexer.GetHashMapTokens().containsKey("while"));
        assertTrue( lexer.GetHashMapTokens().containsKey("if"));
        assertTrue( lexer.GetHashMapTokens().containsKey("do"));

       
        assertTrue(lexer.GetHashMapTokens().containsValue(TokenType.WHILE));
        assertTrue(lexer.GetHashMapTokens().containsValue(TokenType.IF));
        assertTrue(lexer.GetHashMapTokens().containsValue(TokenType.DO));
       


    }


    @Test 
    public void LetterORUnderScoreTest(){

        lexer = new Lexer("");
        lexer.Lex();
    
        assertTrue(lexer.LetterORUnderScore('a'));
        assertTrue(lexer.LetterORUnderScore('_'));
        assertTrue(lexer.LetterORUnderScore('c'));
    

    }

    @Test 
    public void DigitOrPeriodTest(){

        lexer = new Lexer("");
        lexer.Lex();
        

        assertTrue(lexer.DigitOrPeriod('7'));
        assertTrue(lexer.DigitOrPeriod('8'));
        assertTrue(lexer.DigitOrPeriod('.'));
    

    }
    @Test
    public void IsValidCharacterTest(){

        lexer = new Lexer("");

        assertTrue(lexer.IsValidCharacter('.'));
        assertTrue(lexer.IsValidCharacter('1'));
        assertTrue(lexer.IsValidCharacter('a'));
        assertTrue(lexer.IsValidCharacter('_'));
        assertTrue(lexer.IsValidCharacter(' '));
        assertTrue(lexer.IsValidCharacter('\r'));
        assertTrue(lexer.IsValidCharacter('\t'));
        assertTrue(lexer.IsValidCharacter('\n'));
        
        

    }


    /*
     * ProcessWord Works :)
     */
    @Test
    public void ProccesWordTest(){

        Lexer lexer = new Lexer("");
        lexer.MakeHashMapTokens(); // Initialize the HashMapTokens

        Token result = lexer.ProcessWord("unkown/");

        // Ensure that the returned token is of the WORD TokenType
        assertEquals(TokenType.WORD, result.getTokenType());
        // Ensure that the charPosition is correctly set
        assertEquals(6, result.getCharPosition());
        // Ensure that the lineNumber is correctly set
        assertEquals(1, result.getLineNumber());
        // Ensure that the word is correctly set
        assertEquals("unkown", result.getTokenValue());
    }

    @Test
    public void ProcessKeyWordTest(){

        Lexer lexer = new Lexer("");
        lexer.MakeHashMapTokens(); // Initialize the HashMapTokens

        assertTrue(lexer.GetHashMapTokens().containsKey("while"));
        Token result = lexer.ProcessWord("while");

        // Ensure that the returned token is of the correct TokenType
        assertEquals(TokenType.WHILE, result.getTokenType());
        // Ensure that the charPosition is correctly set
        assertEquals(5, result.getCharPosition());
        // Ensure that the lineNumber is correctly set
        assertEquals(1, result.getLineNumber());
        // Ensure that the word is correctly set
        assertNull(result.getTokenValue());


    }

    @Test
    public void  ProccesNumberTest(){

        Lexer lexer = new Lexer("");
        Token numerResultToken = lexer.ProcessNumber("1.23", 0);
        assertNotNull(numerResultToken);
        assertEquals(TokenType.NUMBER, numerResultToken.getTokenType());
        assertEquals("1.23", numerResultToken.getTokenValue());

        Token numerResultToken2 = lexer.ProcessNumber(".2356", 0);
        assertNotNull(numerResultToken2);
        assertEquals(TokenType.NUMBER, numerResultToken2.getTokenType());
        assertEquals(".2356", numerResultToken2.getTokenValue());

    }
    @Test
    public void HandleStringLiteralTest(){

        lexer.HandleStringLiteral();

        lexer.GetLinkedListTokens().getFirst();

        assertEquals(lexer.GetLinkedListTokens().size(), 1);


    }

    public static void main(String[] args) {
        
    }

}
