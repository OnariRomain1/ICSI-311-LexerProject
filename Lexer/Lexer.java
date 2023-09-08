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
        MakeHashtokens();

    }


    
    /*
     * This is my first test of the helper method for the constructor 
     * this is two parts in one 
     * I think that this might be correct then i have modify the processWord methods and check my hashmap 
     * basically we are making hashmap ids the key is the currentString which looks for the string of characters 'f''o''r' so thats our key
     * Then for the value it is the tokenType which is FOR
     * I reallly need to do the testing for this lexer method 
     */

     /*
      *  int currentChar = charPos;
        Token token;
        TokenType tokenType;
        String currentString = stringHandler.PeekString(currentChar);

        
        if (currentString.equals("for")){
            tokenType = TokenType.FOR;
            token = new Token(tokenType, linePos, currentChar);
            hashtokens.put(currentString, tokenType);
        }

        System.out.println("contents:" + hashtokens);

      */
    
    

    public void Lex(){

        int currentChar = charPos;
        TokenType seperator;

        try {

        // while stringHandler is not done
        while(!stringHandler.isDone()){
        // peek at the first line 
        stringHandler.Peek(currentChar);

        /*
         * 
            In your loop in Lex, we need to deal with comments. 
            Comments in AWK start with # and go to the end of the line (like // comments in Java).
             When you encounter a #, loop to the end of the line. No need to update line number or line index, 
             because we aren’t going to output any tokens for comments.

         */
        if (stringHandler.Peek(currentChar) == '#'){
            stringHandler.Swallow(currentChar);
        }
        // if current char is a space or tab currentChar++
        else if (stringHandler.Peek(currentChar) == ' ' || stringHandler.Peek(currentChar) == '\t' ){
            currentChar++;
        }
        // if current char is '\n' add a token of seperator to the linkedList of tokens 
        else if(stringHandler.Peek(currentChar) == '\n'){

            seperator = TokenType.SEPERATOR;
            Token seperatorToken = new Token(seperator,linePos,currentChar);
            tokens.add(seperatorToken);
            currentChar++; 
            linePos++;

        }
        // If the character is a carriage return (\r), we will ignore it.
        //Im assuming that means you increment the currentChar.

        // or do i use stringHandler.swallow
        else if (stringHandler.Peek(currentChar) == '\r'){
            currentChar++;
            linePos++;
        }

       else if (isWordORUnderScore(stringHandler.Peek(currentChar) )){
            tokens.add(ProccesWord(stringHandler.PeekString(currentChar), linePos));
            currentChar++;
        }

       else if (isDigitOrPeriod(stringHandler.Peek(currentChar))){
            tokens.add(ProccesNumber(stringHandler.PeekString(currentChar), linePos));
            currentChar++;
        } else {
            throw new RuntimeException("Unrecognizable Character.");
        }



    }} catch(Exception e){
        System.out.println("Error: " + e.getMessage());
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

            /*
             * Modify “ProcessWord” so that it checks the hash map for known words and makes a token specific to the 
             * word with no value if the word is in the hash map, but WORD otherwise. 
             */
    

            position++;
        }
            
      //  if (stringHandler.PeekString(position).equals("while") && hashtokens.containsKey("while") ){

      //  }

   //   if(hashtokens.containsKey(stringHandler.PeekString(position))){
         
   //    }

    //    if(hashtokens.get("for").equals(Wordresults)){

       // }

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

    public void MakeHashtokens(){
        

        hashtokens.put("while", TokenType.WHILE);
        hashtokens.put("if", TokenType.IF);
        hashtokens.put("do", TokenType.DO);
        hashtokens.put("for", TokenType.FOR);
        hashtokens.put("continue", TokenType.CONTINUE);
        hashtokens.put("break", TokenType.BREAK);
        hashtokens.put("else", TokenType.ELSE);
        hashtokens.put("return", TokenType.RETURN);
        hashtokens.put("BEGIN", TokenType.BEGIN);
        hashtokens.put("END", TokenType.END);
        hashtokens.put("print", TokenType.PRINT);
        hashtokens.put("printf", TokenType.PRINTF);
        hashtokens.put("next", TokenType.NEXT);
        hashtokens.put("in", TokenType.IN);
        hashtokens.put("delete", TokenType.DELETE);
        hashtokens.put("getline", TokenType.GETLINE);
        hashtokens.put("exit", TokenType.EXIT);
        hashtokens.put("nextfile", TokenType.NEXTFILE);
        hashtokens.put("function", TokenType.FUNCTION);        

       
    }

    public void HandleStringLiteral(){

        int currentPosition = charPos;
        int StringLiteralEnd;
        int linePos = 0;
        Token stringLiteralToken ;
        String quoteString ="";
    
        try{
            
        while (!stringHandler.isDone()){

            // look at the first character
            stringHandler.Peek(currentPosition);
            //increment to go through the characters 
             currentPosition++;
            // if the first character is "
            if (stringHandler.Peek(currentPosition) == '"'){

                // increment the current position 
                currentPosition++;
                // then check if theres another "
                if (stringHandler.Peek(currentPosition) == '"'){
                    // if so set the StringLiteral End to the current position
                    StringLiteralEnd = currentPosition;
                    /* add the characters in bewtween the two ""  then create a token
                    then add it to the linked list of tokens then increment currentPositin
                    in order to ensure the String is done
                     */
                    quoteString.substring(stringHandler.Peek(currentPosition), StringLiteralEnd);
                    stringLiteralToken = new Token(TokenType.STRINGLITERAL,linePos, currentPosition, quoteString);
                    tokens.add(stringLiteralToken);
                   // currentPosition++;
                }
               
                  
            }

        }
    } catch(IndexOutOfBoundsException e) {}
        System.out.println("Error: IndexOutOfBoundsException" );
    }

}

