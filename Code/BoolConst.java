
public class BoolConst implements BoolExpr {
	public boolean value;

	public BoolConst(boolean value) {
		this.value = value;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
