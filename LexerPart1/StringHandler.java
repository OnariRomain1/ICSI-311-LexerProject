import java.nio.file.Files;
import java.nio.file.Path;

public class StringHandler {

	//Path myPath = Paths.get(“someFile.awk”);
	//private String documentHandler = new String(Files.readAllBytes (myPath));;
	private int indexPos;
	
	/*
	 * ) -looks “i” characters ahead and returns that character; 
	 * doesn’t move the index
	 */
	public char Peek(int charAhead) {
		
		char current = 0;

		if(charAhead == 0){
			return current;
		}
		
		for (char i = 0; i <= charAhead;i++){

			if (i == charAhead){
				current = i;
			}

		} 
		// documentHandler.charAt(current)
			return current;

		
	}
	
	/* 
	 * returns a string of the next “i” 
	 * characters but doesn’t move the index
	 */
	public String PeekString() {

		
		return "";
	}
	
	/*
	 * returns the next character and moves the index
	 */
	
	public char GetChar() {
		return 0;
	}
	
	/*
	 * moves the index ahead “i” positions
	 */
	public void Swallow() {
		
	}
	/*
	 * returns true if we are at the end of the document
	 */
	public boolean isDone() {
		
		return true;
	}
	
	/*
	 * returns the rest of the document as a string
	 */
	public String Remainder() {
		return "";
	}
	
}
