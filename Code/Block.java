import java.util.ArrayList;

public class Block implements Program {
	public ArrayList<Statement> statementList;
	
	public Block() {
		this.statementList = new ArrayList<Statement>();
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}

