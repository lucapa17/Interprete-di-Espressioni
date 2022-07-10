
public class Number implements NumExpr {
	public long value;

	public Number(long value) {
		this.value = value;
	}
	
	public void accept(ProgramVisitor visitor) {
		visitor.visit(this);
	}
}
