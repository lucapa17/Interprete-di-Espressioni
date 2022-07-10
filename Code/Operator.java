
public class Operator implements NumExpr {
	public enum OpCode { ADD, SUB, MUL, DIV } 
	
	public OpCode operator;
	public NumExpr left;
	public NumExpr right;
		
	public Operator(OpCode operator, NumExpr left, NumExpr right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
