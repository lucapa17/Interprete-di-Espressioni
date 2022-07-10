
public class Variable implements NumExpr {
	public String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
