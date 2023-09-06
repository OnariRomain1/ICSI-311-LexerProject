
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main (String[] args) {

        Path myPath;
        String document;
        Lexer lexer;

            try {
            
            myPath = Paths.get(args[0]);
            document = new String(Files.readAllBytes(myPath)); 
            lexer = new Lexer(document);

            lexer.Lex();

            for (Token token : lexer.tokens) {
                System.out.println(token.toString());
            }
             
             
    
        } catch (Exception e) {
    
            e.printStackTrace();
        
    
        }
        

    }

}
