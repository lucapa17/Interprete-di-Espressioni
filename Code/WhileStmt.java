
public class WhileStmt implements Statement{
	public BoolExpr boolExpr;
	public Block block;
	
	public WhileStmt(BoolExpr boolExpr, Block block) {
		this.boolExpr = boolExpr;
		this.block = block;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
