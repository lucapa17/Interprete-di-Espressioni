
import java.io.Console;
import java.util.Stack;
import java.util.regex.Pattern;

public class EvaluationVisitor implements ProgramVisitor{
	private Context context;
	private Stack<Object> accumulator;
	
	public EvaluationVisitor() {
		this.context = new Context();
		this.accumulator = new Stack<Object>();
	}
	
	@Override
	public void visit(Block block) {
		
		for(int i = 0; i < block.statementList.size(); i++) {
			block.statementList.get(i).accept(this);
		}		
	}
	
	@Override
	public void visit(SetStmt setStmt) {
		setStmt.numExpr.accept(this);
		long value = (long) this.getValue();	
		this.context.setVariable(setStmt.variable.name, value);
	}
	
	@Override
	public void visit(PrintStmt printStmt) {
		printStmt.numExpr.accept(this);
		long value = (long) this.getValue();	
		System.out.println(value);
	}
	
	@Override
	public void visit(InputStmt inputStmt) {
		Console console = System.console();
		if (console == null) {
			throw new SemanticError("(ERROR in evaluator: Unable to get a console to read input)");
		}
		String inputText = null;
		try {
			inputText = console.readLine();
		} 
		catch (Exception e) {
			throw new SemanticError("(ERROR in evaluator: Unable to read input from console)");
		}	
		long value = 0;
		try {
			value = Long.parseLong(inputText);
		} 
		catch (NumberFormatException e) {
			throw new SemanticError("(ERROR in evaluator: Expected integer number, got '" + inputText + "')");
		}
		if(Pattern.matches("0|[-]?[1-9][0-9]*", inputText)) {
			this.context.setVariable(inputStmt.variable.name, value);
		}
		else {
			throw new SemanticError("(ERROR in evaluator: Expected integer number, got '" + inputText + "')");
		}
		
	}
	
	@Override
	public void visit(IfStmt ifStmt) {
		ifStmt.boolExpr.accept(this);
		boolean value = (boolean) this.getValue();
		if(value) {
			for(int i = 0; i < ifStmt.block_if.statementList.size(); i++) {
				ifStmt.block_if.statementList.get(i).accept(this);
			}
		}
		else {
			for(int i = 0; i < ifStmt.block_else.statementList.size(); i++) {
				ifStmt.block_else.statementList.get(i).accept(this);
			}
		}
	}
	
	@Override
	public void visit(WhileStmt whileStmt) {
		whileStmt.boolExpr.accept(this);
		boolean value = (boolean) this.getValue();
		while(value) {
			for(int i = 0; i < whileStmt.block.statementList.size(); i++) {
				whileStmt.block.statementList.get(i).accept(this);
			}
			whileStmt.boolExpr.accept(this);
			value = (boolean) this.getValue();
		}
	}
	
	@Override
	public void visit(Number number) {
		this.accumulator.push(number.value);	
	}

	@Override
	public void visit(Variable variable) {
		if(this.context.hasVariable(variable.name)) {
			long value = this.context.getVariable(variable.name);
			this.accumulator.push(value);
		}
		else {
			throw new SemanticError("(ERROR in evaluator: Undefined variable "+ variable.name +")");
		}
	}

	@Override
	public void visit(Operator operator) {
		operator.left.accept(this);
		operator.right.accept(this);
		long r_value = (long) this.accumulator.pop();
		long l_value = (long) this.accumulator.pop();
		switch (operator.operator) {
		case ADD:
			this.accumulator.push(l_value + r_value);
			return;
		case SUB:
			this.accumulator.push(l_value - r_value);
			return;
		case MUL:
			this.accumulator.push(l_value * r_value);
			return;
		case DIV:
			if(r_value == 0)
				throw new SemanticError("(ERROR in evaluator: Division by zero)");
			this.accumulator.push(l_value / r_value);
			return;
		default:
			return;
		}
	}
	
	@Override
	public void visit(BoolConst boolConst) {
		this.accumulator.push(boolConst.value);	
	}

	@Override
	public void visit(BoolOp boolOp) {
		boolOp.left.accept(this);
		boolean l_value = (boolean) this.accumulator.pop();
		switch (boolOp.operator) {
		case AND:
			if(l_value) {
				boolOp.right.accept(this);
				boolean r_value = (boolean) this.accumulator.pop();
				this.accumulator.push(r_value);
			}
			else {
				this.accumulator.push(l_value);
			}
			return;
		case OR:
			if(l_value) {
				this.accumulator.push(l_value);
			}
			else {
				boolOp.right.accept(this);
				boolean r_value = (boolean) this.accumulator.pop();
				this.accumulator.push(r_value);
			}
			return;
		case NOT:
			this.accumulator.push(!l_value);
			return;
		default:
			return;
		}
	}
	
	@Override
	public void visit(RelOp relOp) {
		relOp.right.accept(this);
		long r_value = (long) this.getValue();
		relOp.left.accept(this);
		long l_value = (long) this.getValue();
		switch (relOp.operator) {
		case LT:
			this.accumulator.push(l_value < r_value);
			return;
		case GT:
			this.accumulator.push(l_value > r_value);
			return;
		case EQ:
			this.accumulator.push(l_value == r_value);
			return;
		default:
			return;
		}
	}
	
	
	public Object getValue() {
		return this.accumulator.lastElement();
	}
	
		public void reset() {
		this.accumulator.clear();
	}
}




