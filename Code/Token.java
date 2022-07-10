
public class Token {
	public enum Type { KEY_WORD, OPEN_PAR, CLOSE_PAR, NUMBER, VARIABLE }
	
	public Type type;
	public Object value;
	
	public Token(Object o, Type t) {
		this.type = t;
		this.value = o;
	}
}
