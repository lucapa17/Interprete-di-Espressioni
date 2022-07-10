
public interface ProgramVisitor {
	public void visit(Block block);	
	public void visit(PrintStmt printStmt);
	public void visit(SetStmt setStmt);
	public void visit(InputStmt inputStmt);
	public void visit(WhileStmt whileStmt);
	public void visit(IfStmt ifStmt);
	public void visit(Number number);
	public void visit(Operator operator);
	public void visit(Variable variable);
	public void visit(RelOp relOp);
	public void visit(BoolConst boolConst);
	public void visit(BoolOp boolOp);
}
