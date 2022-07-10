
public class RelOp implements BoolExpr {
	public enum OpCode { LT, GT, EQ } 
	
	public OpCode operator;
	public NumExpr left;
	public NumExpr right;
		
	public RelOp(OpCode operator, NumExpr left, NumExpr right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
