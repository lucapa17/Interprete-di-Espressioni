import java.util.HashMap;

public class Context {
	
	private HashMap<String, Long> symbolTable;
	
	public Context() {
		symbolTable = new HashMap<>();
	}
	
	public void setVariable(String id, long value) {
		symbolTable.put(id, value);
	}
	
	public long getVariable(String id) {
		return symbolTable.get(id);
	}
	
	public boolean hasVariable(String id) {
		if(symbolTable.get(id) == null)
			return false;
		else
			return true;
	}

}