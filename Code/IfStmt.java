
public class IfStmt implements Statement{
	public BoolExpr boolExpr;
	public Block block_if;
	public Block block_else;
	
	
	public IfStmt(BoolExpr boolExpr, Block block_if, Block block_else) {
		this.boolExpr = boolExpr;
		this.block_if = block_if;
		this.block_else = block_else;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
