import java.util.HashMap;
import java.util.LinkedList;

public class Lexer {


    private StringHandler awkFile;
    private LinkedList<Token> tokensLinkedList;
    private Token token;
    private HashMap<String, TokenType> HashMapTokens;
    private int lineNumber = 1;
    private int charPosition = 0;

    public Lexer(String _awkFile){

        awkFile = new StringHandler(_awkFile);
        tokensLinkedList = new LinkedList<Token>();
        HashMapTokens = new HashMap<String, TokenType>();
        MakeHashMapTokens();

    }
    
     /*
      * My accessor methods
      */
    public LinkedList<Token> GetLinkedListTokens(){
       return tokensLinkedList;
    }

    public Token getToken(){
        return token;
    }
    public HashMap<String, TokenType> GetHashMapTokens(){
        return HashMapTokens;
    }

    public StringHandler getAwkFile(){
        return awkFile;
    }
    

    public void Lex(){

        int currentCharacterPosition; 
        currentCharacterPosition = charPosition;

        try {

        // while stringHandler is not true
        while(!awkFile.isDone()){
        // peek at the first charachter  
        char currentChar = awkFile.Peek(currentCharacterPosition);
        
        if (!IsValidCharacter(currentChar)){
            currentCharacterPosition++;
            throw new IllegalArgumentException("Unrecognizable Character");
        }
         // loops to the end of the line 
        if (currentChar == '#'){

            awkFile.Swallow(currentCharacterPosition);


        }

        // if current char is a space or tab increment current character
        else if (currentChar == ' ' || currentChar == '\t' ){
            currentCharacterPosition++;
        }
        // if current character is a seperator create a seperator token; add the seperator token to the tokens linkedList, then increment currentchar and linenumber.
        else if(currentChar == '\n'){

            Token seperatorToken = new Token(TokenType.SEPERATOR,lineNumber,currentCharacterPosition);
            tokensLinkedList.add(seperatorToken);
            currentCharacterPosition++; 
            lineNumber++;

        }
        // If the character is a carriage return (\r), we will ignore it.
        // increment lineNumber because it is a new line
        //increment current character 
        else if (currentChar == '\r'){
            currentCharacterPosition++;
            lineNumber++;
        }

        // check if the current character is a letter or underscore  then add the lettr
       else if (LetterORUnderScore(currentChar)){

            Token isLetter = ProcessWord(awkFile.PeekString(currentCharacterPosition));
            tokensLinkedList.add(isLetter);
            currentCharacterPosition++;

        }

       else if (DigitOrPeriod(awkFile.Peek(currentCharacterPosition))){

            Token isDigit = ProcessNumber(awkFile.PeekString(currentCharacterPosition), lineNumber);
            tokensLinkedList.add(isDigit);
            currentCharacterPosition++;
        } 
        
    //    currentCharacterPosition++;

    }} catch(IndexOutOfBoundsException IOFBE){
      
        System.out.println("Error :" +IOFBE.getMessage());
    }

       
        


        
    
}

/*WORKS:
 * Checks if the character is a word or underscore 
 */
    boolean LetterORUnderScore (char c){

    if (Character.isLetter(c) ||  c == '_' ){
        return true;
    }

    return false;

}
/*WORKS:
 * Checks if the character is a digit or period
 *  
 */
    boolean DigitOrPeriod (char c){

    if (Character.isDigit(c) || c == '.'){
        return true;
    }
    return false;
    
}

    boolean IsValidCharacter(char c) {

        if(LetterORUnderScore(c) || DigitOrPeriod(c) ||  c == '\n' || c== '#' || c == '\r' || c == '\t' || c== ' '){
            return true;
        }
        return false;
    }


   //WORKS:
   public Token ProcessWord(String input){

         Token token;
         StringBuilder wordBuilder;
         int currentPosition;
       
         
        currentPosition = charPosition;
        
        wordBuilder = new StringBuilder();
        
        while (currentPosition < input.length() && LetterORUnderScore(input.charAt(currentPosition))){

            //adding the characters at the currentPosition to the wordBuilder
            wordBuilder.append(input.charAt(currentPosition));
            
            //incrementing the current position
            currentPosition++;
            
        }
        // increment the lineNumber 
        
        //checking the hashmap for any key words that are in the wordBuilder 
        if (HashMapTokens.containsKey(wordBuilder.toString())) {
            token = new Token(HashMapTokens.get(wordBuilder.toString()), lineNumber, currentPosition);
        } else {
            token = new Token(TokenType.WORD, lineNumber, currentPosition, wordBuilder.toString());
        }

        charPosition = currentPosition;
        lineNumber++;

    
        return token;
        
       
    }

/*
 * WORKS:
 */
     public Token ProcessNumber(String input, int position){

        Token numberToken;
        StringBuilder numberBuilder = new StringBuilder ();
        int currentPosition;

        currentPosition = position;

        while (currentPosition < input.length() && DigitOrPeriod(input.charAt(currentPosition))){
            //adds the numbers to the number builder 
            numberBuilder.append(input.charAt(currentPosition));
            currentPosition++;
        }
        // creates the token then returns it.
        numberToken = new Token(TokenType.NUMBER,lineNumber,currentPosition,numberBuilder.toString());
        charPosition = currentPosition;
        lineNumber++;
        return numberToken;
       
    }



    /*
     * Works
     */
    public void MakeHashMapTokens(){
        

        HashMapTokens.put("while", TokenType.WHILE);
        HashMapTokens.put("if", TokenType.IF);
        HashMapTokens.put("do", TokenType.DO);
        HashMapTokens.put("for", TokenType.FOR);
        HashMapTokens.put("continue", TokenType.CONTINUE);
        HashMapTokens.put("break", TokenType.BREAK);
        HashMapTokens.put("else", TokenType.ELSE);
        HashMapTokens.put("return", TokenType.RETURN);
        HashMapTokens.put("BEGIN", TokenType.BEGIN);
        HashMapTokens.put("END", TokenType.END);
        HashMapTokens.put("print", TokenType.PRINT);
        HashMapTokens.put("printf", TokenType.PRINTF);
        HashMapTokens.put("next", TokenType.NEXT);
        HashMapTokens.put("in", TokenType.IN);
        HashMapTokens.put("delete", TokenType.DELETE);
        HashMapTokens.put("getline", TokenType.GETLINE);
        HashMapTokens.put("exit", TokenType.EXIT);
        HashMapTokens.put("nextfile", TokenType.NEXTFILE);
        HashMapTokens.put("function", TokenType.FUNCTION);        

       
    }

    public void HandleStringLiteral(){

        int currentPosition = charPosition;
        int StringLiteralEnd;
        int lineNumber = 0;
        Token stringLiteralToken ;
        String quoteString ="";
    
        try{
            
        while (!awkFile.isDone()){

            // look at the first character
            awkFile.Peek(currentPosition);
            //increment to go through the characters 
             currentPosition++;
            // if the first character is "
            if (awkFile.Peek(currentPosition) == '"'){

                // increment the current position 
                currentPosition++;
                // then check if theres another "
                if (awkFile.Peek(currentPosition) == '"'){
                    // if so set the StringLiteral End to the current position
                    StringLiteralEnd = currentPosition;
                    /* add the characters in bewtween the two ""  then create a token
                    then add it to the linked list of tokens then increment currentPositin
                    in order to ensure the String is done
                     */
                    quoteString.substring(awkFile.Peek(currentPosition), StringLiteralEnd);
                    stringLiteralToken = new Token(TokenType.STRINGLITERAL,lineNumber, currentPosition, quoteString);
                    tokensLinkedList.add(stringLiteralToken);
                   // currentPosition++;
                }
               
                  
            }

        }
    } catch(IndexOutOfBoundsException e) {}
        System.out.println("Error: IndexOutOfBoundsException" );
    }

    public static void main(String[] args) {
        
    }

}

