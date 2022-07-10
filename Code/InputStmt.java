
public class InputStmt implements Statement{
	public Variable variable;
	
	public InputStmt(Variable variable) {
		this.variable = variable;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
