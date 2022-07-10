
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Input file not provided to the program");
			System.exit(0);
		}
		try  {
			FileReader reader = new FileReader(args[0]);
			Tokenizer tokenizer = new Tokenizer();
			ArrayList<Token> tokens = tokenizer.lexical_analysis(reader);
			//tokenizer.printTokens();
			Parser parser = new Parser();
			Program program = parser.doParse(tokens);
			EvaluationVisitor ev = new EvaluationVisitor();
			program.accept(ev);
			ev.reset();
		}
		catch (FileNotFoundException e) {
			System.out.println("File " + args[0] + " was not found! Exiting...");
		} 
		catch (IOException e) {
			System.out.println("Unexpected problem with file " + args[0] + "! Exiting...");
		}
		catch(Error e) {
			System.out.println(e);
		}
		
		
	}
}

