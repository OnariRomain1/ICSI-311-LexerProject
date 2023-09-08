
public class Token {
	
	private TokenType tokenType;
	private String tokenValue;
	private int lineNum;
	private int charPosition;
	
	/*
	 * Constructor for TokenType, line number and charPosition
	 */
	public Token(TokenType tokenType, int lineNum, int charPosition) {
	
		this.tokenType = tokenType;
		this.lineNum = lineNum;
		this.charPosition = charPosition;

	}
	
	/*
	 * constructor for the token value 
	 */
	public Token(TokenType tokenType, int lineNum, int charPosition, String tokenValue) {

		this.tokenType = tokenType;
		this.lineNum = lineNum;
		this.charPosition = charPosition;
		this.tokenValue = tokenValue;
		
	}
	/*
	 * ToString Method
	 */
	public String toString() {

		if (tokenType != null){
			return tokenType + "(" + tokenValue + ")";
		}
		else{

			return tokenType.toString();

		}
		
	}
}
