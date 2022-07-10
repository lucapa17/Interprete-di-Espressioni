import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Tokenizer {
	private ArrayList<Token> tokens ;
	
	public Tokenizer() {
		tokens = new ArrayList<Token>();
	}
	
	public ArrayList<Token> lexical_analysis(FileReader reader) throws LexicalError, IOException{
		BufferedReader bufferread = new BufferedReader(reader);
        StreamTokenizer st = new StreamTokenizer(bufferread);
        st.ordinaryChars('0', '9');
        st.ordinaryChar('-');
        st.wordChars('0', '9'); 
        st.wordChars('-', '-');
		String[] key_word = {"BLOCK", "SET", "PRINT", "INPUT", "IF", "WHILE", "ADD", "SUB", "MUL",
				"DIV", "GT","LT","EQ","AND","OR","NOT","TRUE","FALSE"};
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
        	switch(st.ttype) {
        		case '(':
                    tokens.add(new Token('(', Token.Type.OPEN_PAR));
                    break;
        		case ')':
                    tokens.add(new Token(')', Token.Type.CLOSE_PAR));
                    break;
        		case StreamTokenizer.TT_WORD:
        			boolean isKeyword = false;
        			for(int i = 0; i < key_word.length; i++)
        				if(key_word[i].equals(st.sval)) {
        					tokens.add(new Token(st.sval, Token.Type.KEY_WORD));
        					isKeyword = true;
        					break;
        				}
        			if(!isKeyword)
        				if(Pattern.matches("[a-zA-Z]+", st.sval))
        					tokens.add(new Token(st.sval, Token.Type.VARIABLE));
        				else if(Pattern.matches("0|[-]?[1-9][0-9]*", st.sval))
        					tokens.add(new Token(Long.parseLong(st.sval), Token.Type.NUMBER));
        				else
        					throw new LexicalError("(ERROR in tokenizer: '"+st.sval+"' invalid token)");
        			break;
        		default:
        			throw new LexicalError("(ERROR in tokenizer: stray character "+ st.toString() +")");
        	}
    	}
        return tokens; 
	}
	
	public void printTokens() {
		int i = 0;
        while(i < tokens.size()) {
        	System.out.println(i + ") " + tokens.get(i).type + ": " + tokens.get(i).value);
        	i++;
        }
	}
}
