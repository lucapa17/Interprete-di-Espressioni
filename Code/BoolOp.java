
public class BoolOp implements BoolExpr {
	public enum OpCode { AND, OR, NOT } 
	
	public OpCode operator;
	public BoolExpr left;
	public BoolExpr right;
		
	public BoolOp(OpCode operator, BoolExpr left, BoolExpr right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
