
public class SetStmt implements Statement {
	public Variable variable;
	public NumExpr numExpr;
	
	public SetStmt(Variable variable, NumExpr numExpr) {
		this.variable = variable;
		this.numExpr = numExpr;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
