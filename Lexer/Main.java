
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws IOException {

        Path myPath;
       
        String file;
        Lexer lexer;
        Scanner input = new Scanner(System.in);


         
            try {
            
            /*
             * Constanly gives me a NoSuchFileException im not sure why even if the 
             * file is in the same folder -_-
             */
            myPath = Paths.get(input.next());
            file = new String(Files.readAllBytes(myPath)); 
            lexer = new Lexer(file);


            lexer.Lex();

            for (Token token : lexer.GetLinkedListTokens()) {
                System.out.println(token.toString());
            }
             
             
    
        } catch (IOException e) {
    
            e.printStackTrace();
        
    
        }
        

    }

}
