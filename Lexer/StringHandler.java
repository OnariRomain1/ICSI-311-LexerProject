
public class StringHandler {

	public String document;
	private int indexPos = 0;
	
	/*
	 * looks “i” characters ahead and returns that character; 
	 * doesn’t move the index
	 */

	
	 StringHandler(String AwkFile){
		document = AwkFile;
	 }

	public char Peek(int charAhead) {

		int newPosition = indexPos + charAhead;
		char characters;
		
		if (newPosition < document.length()){

			characters = document.charAt(newPosition);
			return characters;

		} else {
			return  '\0';
		}

		}
		
		
	
	
	/* 
	 * returns a string of the next “i” 
	 * characters but doesn’t move the index
	 */

	public String PeekString(int charAhead) {

		int newPosition = indexPos + charAhead;

		if (newPosition < document.length()){
			return document.substring(indexPos ,newPosition);

		} else {

			return document.substring(indexPos);

		}
	}
	

	
	
	/*
	 * returns the next character and moves the index
	 */
	
	public char GetChar() {
		
		char nextChar;

		if (indexPos < document.length()){

			nextChar = document.charAt(indexPos);
			indexPos++;

			return nextChar;

		} else {

			return '\0';
		}



		
	}
	
	/*
	 * moves the index ahead “i” positions
	 */

	public void Swallow(int moveIndexNum) {

		indexPos = Math.min(indexPos + moveIndexNum, document.length());

	}
	/*
	 * returns true if we are at the end of the document
	 */
	public boolean isDone() {

		if (indexPos == document.length() ){
			return true;
		}	
		
			return false;
		
	}
	
	/*
	 * returns the rest of the document as a string
	 */
	public String Remainder() {

		String remainder;

		if (indexPos < document.length()){

			remainder = document.substring(indexPos);
			indexPos = document.length();

			return remainder;
		}



		return "";

	}
	
}
