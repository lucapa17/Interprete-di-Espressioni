
public class PrintStmt implements Statement {
	public NumExpr numExpr;
	
	public PrintStmt(NumExpr numExpr) {
		this.numExpr = numExpr;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
