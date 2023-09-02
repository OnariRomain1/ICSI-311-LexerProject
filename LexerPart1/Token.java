/*
 * Skeleton of Token Class: Complete? : No
 */

public class Token {
	
	public enum TokenType{
		
		Word,Number,Seperator
		
	}
	
	public TokenType token;
	public String tokenValue;
	
	/*
	 * Constructor for TokenType, line number and position
	 */
	public Token(TokenType t, int lineNum, int position) {
		
	}
	
	/*
	 * constructor for the token value 
	 */
	public Token(String tokenValue) {
		
		
	}
	/*
	 * ToString Method
	 */
	public String ToString() {
		
		return "";
	}
}
