import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;

public class Lexer {


    private StringHandler stringHandler;
    public LinkedList<Token> tokens;
    public HashMap<String, TokenType> hashtokens;
    private int linePos = 1;
    private int charPos = 0;

    public Lexer(String value){

        stringHandler = new StringHandler(value);
        tokens = new LinkedList<Token>();
        hashtokens = new HashMap<String, TokenType>();
        Maketoken();

    }

    /*
     * i think this should be in the token class but its whatever for noww 
     * my main focus atm is trying to get access to the data thats coming from the lex method 
     * but that requires me to be able to look into the linkedList of tokens and see whats being added.
     * Im still unsure if my lex method even works and ive been stuck mostly because i dont
     * know how to test my lex method properly.
     * Also i need to do better at organizing my classes 
     */
    public Token getToken(int index){

        return tokens.get(index);

    }




    
    /*
     * This is my first test of the helper method for the constructor 
     * this is two parts in one 
     * I think that this might be correct then i have modify the processWord methods and check my hashmap 
     * basically we are making hashmap ids the key is the currentString which looks for the string of characters 'f''o''r' so thats our key
     * Then for the value it is the tokenType which is FOR
     * I reallly need to do the testing for this lexer method 
     */
    public void Maketoken(){

        int currentChar = charPos;
        Token token;
        TokenType tokenType;
        String currentString = stringHandler.PeekString(currentChar);

        if (currentString.equals("for")){
            tokenType = TokenType.FOR;
            token = new Token(tokenType, linePos, currentChar);
            hashtokens.put(currentString, tokenType);
        }

    }

    public void Lex(){

        int currentChar = charPos;
        TokenType seperator;

        try {

        // while stringHandler is not done
        while(!stringHandler.isDone()){
        // peek at the first line 
        stringHandler.Peek(currentChar);

        // if current char is a space or tab currentChar++
        if (stringHandler.Peek(currentChar) == ' ' || stringHandler.Peek(currentChar) == '\t' ){
            currentChar++;
        }
        // if current char is '\n' add a token of seperator to the linkedList of tokens 
        else if(stringHandler.Peek(currentChar) == '\n'){

            seperator = TokenType.SEPERATOR;
            Token seperatorToken = new Token(seperator,linePos,currentChar);
            tokens.add(seperatorToken);
            currentChar++; 
            linePos = 0;

        }
        // If the character is a carriage return (\r), we will ignore it.
        //Im assuming that means you increment the currentChar.
        else if (stringHandler.Peek(currentChar) == '\r'){
            currentChar++;
            linePos = 0;
        }

       else if (isWordORUnderScore(stringHandler.Peek(currentChar) )){
            tokens.add(ProccesWord(stringHandler.PeekString(currentChar), linePos));
            
        }

       else if (isDigitOrPeriod(stringHandler.Peek(currentChar))){
            tokens.add(ProccesNumber(stringHandler.PeekString(currentChar), linePos));
        } 


    }} catch(IllegalArgumentException fnfe){
        System.out.println("Unrecognizable Character.");
    }

       
        


        
    
}

/*
 * Checks if the character is a word or underscore 
 */
boolean isWordORUnderScore (char c){

    if (Character.isLetterOrDigit(c) ||  c == '_'){
        return true;
    }

    return false;

}
/*
 * Checks if the character is a digit or period 
 */
boolean isDigitOrPeriod (char c){

    if (Character.isDigit(c) || c == '.'){
        return true;
    }
    return false;
    
}


   public Token ProccesWord(String input, int position){

         TokenType word = TokenType.WORD;

        // wordResults is used to add the characters to a string
        StringBuilder  Wordresults = new StringBuilder ();

        //while position is less than the length of the specified string and is a letter, Digit or underscore
        while (position < input.length() && isWordORUnderScore(input.charAt(position))){
            Wordresults.append(input.charAt(position));
            position++;
        }
        // create the token then return it.
        Token wordToken = new Token(word,linePos,position,Wordresults.toString());
        charPos = position;
        return wordToken;
       

    }

     public Token ProccesNumber(String input, int position){
         TokenType number = TokenType.NUMBER;
        
        // wordResults is used to add the characters to a string
        StringBuilder  numberResults = new StringBuilder ();

        //while position is less than the length of the specified string and is a  Digit 
        while (position < input.length() && isDigitOrPeriod(input.charAt(position))){
            numberResults.append(input.charAt(position));
            position++;
        }
        // create the token then return it.
        Token numberToken = new Token(number,linePos,position,numberResults.toString());
        charPos = position;
        return numberToken;
       

    }

    // make more methods that help access the things i need to test keep all fields private and just make methods that allow me to access the data.

}

