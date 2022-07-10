import java.util.ArrayList;

public class Parser {
	
	private Program program;
	private int i;
	
	public Parser() {
		this.program = new Block();
		this.i = 0;
	}
	
	public Program doParse(ArrayList<Token> tokens) {
		Statement statementProgram = doRecursiveParse(tokens, (Block) program);
		
		if(((Block) program).statementList.size() == 0) {
			((Block) program).statementList.add(statementProgram);
		}
		if (i < tokens.size() - 1) {
			throw new SyntaxError("(ERROR in parser: Garbage at the end of text!)");
		}
		return program;
	}
	
	private Statement doRecursiveParse(ArrayList<Token> tokens, Block block)  {
		if(i == tokens.size()) {
			throw new SyntaxError("(ERROR in parser: Premature end of input)");
		}
		if(tokens.get(i).type != Token.Type.OPEN_PAR) {
			throw new SyntaxError("(ERROR in parser: Missing left parenthesis at position: " + i + " )");
		}
		if(++i == tokens.size()) {
			throw new SyntaxError("(ERROR in parser: Premature end of input)");
		}
		if(tokens.get(i).type != Token.Type.KEY_WORD) {
			throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
		}
		
    	switch(tokens.get(i).value.toString()) {
			
    		case "BLOCK":
    			do {
    				if(++i == tokens.size()) {
    					throw new SyntaxError("(ERROR in parser: Premature end of input)");
    				}
					Statement statement = doRecursiveParse(tokens, block);
					block.statementList.add(statement);
				}
				while((i < tokens.size()-1) && (tokens.get(i+1).type == Token.Type.OPEN_PAR) );
    			
    			if(++i == tokens.size()) {
    				throw new SyntaxError("(ERROR in parser: Premature end of input)");
    			}
				if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
	            break;
	            
			case "SET":
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				if(tokens.get(i).type != Token.Type.VARIABLE) {
					throw new SyntaxError("(ERROR in parser: Missing variable at token: " + i + " )");
				}
				Variable variableSet = new Variable(tokens.get(i).value.toString());
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				NumExpr numExprSet = doParseNumExpr(tokens);
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
				SetStmt setStatement = new SetStmt(variableSet, numExprSet);
				return setStatement;
				
			case "PRINT":
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				NumExpr numExprPrint = doParseNumExpr(tokens);
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
				PrintStmt printStatement = new PrintStmt(numExprPrint);
				return printStatement;
				
	    	case "INPUT":
	    		if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				if(tokens.get(i).type != Token.Type.VARIABLE) {
					throw new SyntaxError("(ERROR in parser: Missing variable at token: " + i + " )");
				}
				Variable variableInput = new Variable(tokens.get(i).value.toString());
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
		    	InputStmt inputStatement = new InputStmt(variableInput);
				return inputStatement;
				
			case "IF":
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				BoolExpr boolExprIf = doParseBoolExpr(tokens);
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				Block blockIf = new Block();
				Statement statementIf = doRecursiveParse(tokens, blockIf);
				if(blockIf.statementList.size() == 0) {
					blockIf.statementList.add(statementIf);
				}
				
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				Block blockElse = new Block();
				Statement statementElse = doRecursiveParse(tokens, blockElse);
				if(blockElse.statementList.size() == 0) {
					blockElse.statementList.add(statementElse);
				}
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
		    	IfStmt ifStatement = new IfStmt(boolExprIf, blockIf, blockElse);
				return ifStatement;
				
			case "WHILE":
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				BoolExpr boolExprWhile = doParseBoolExpr(tokens);
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
				Block blockWhile = new Block();
				Statement statementWhile = doRecursiveParse(tokens, blockWhile);
				if(blockWhile.statementList.size() == 0) {
					blockWhile.statementList.add(statementWhile);
				}
				if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
		    	WhileStmt whileStatement = new WhileStmt(boolExprWhile, blockWhile);
				return whileStatement;
			
			default:
				throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
    	}
		return null;
	
	}
	
	
	private NumExpr doParseNumExpr(ArrayList<Token> tokens)  {
		if(tokens.get(i).type == Token.Type.OPEN_PAR) { 
			if(++i == tokens.size()) {
				throw new SyntaxError("(ERROR in parser: Premature end of input)");
			}
			if(tokens.get(i).type != Token.Type.KEY_WORD) {
				throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
			}
			Operator.OpCode op;
	    	switch(tokens.get(i).value.toString()) {
				case "ADD":
					op = Operator.OpCode.ADD;
					break;
				case "SUB":
					op = Operator.OpCode.SUB;
					break;
				case "MUL":
					op = Operator.OpCode.MUL;
					break;
		    	case "DIV":
		    		op = Operator.OpCode.DIV;
					break;
				default:
					throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
	    	}
	    	if(++i == tokens.size()) {
				throw new SyntaxError("(ERROR in parser: Premature end of input)");
			}
	    	NumExpr left = doParseNumExpr(tokens);
	    	if(++i == tokens.size()) {
				throw new SyntaxError("(ERROR in parser: Premature end of input)");
			}
	    	NumExpr right = doParseNumExpr(tokens);
	    	if(++i == tokens.size()) {
				throw new SyntaxError("(ERROR in parser: Premature end of input)");
			}
	    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
	    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
	    	}
	    	Operator operator = new Operator(op, left, right);
	    	return operator;	
		}
		
		else if(tokens.get(i).type == Token.Type.NUMBER) {
			Number number = new Number ((long) tokens.get(i).value);
			return number;
		}
		
		else if(tokens.get(i).type == Token.Type.VARIABLE) {
			Variable variable = new Variable (tokens.get(i).value.toString());
			return variable;
		}
		else {
			throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
		}	
	}
	
	private BoolExpr doParseBoolExpr(ArrayList<Token> tokens)  {
		if(tokens.get(i).type == Token.Type.OPEN_PAR) { 
			if(++i == tokens.size()) {
				throw new SyntaxError("(ERROR in parser: Premature end of input)");
			}
			if(tokens.get(i).type != Token.Type.KEY_WORD) {
				throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
			}
			String keyword = tokens.get(i).value.toString();
			
			if(keyword.equals("LT") || keyword.equals("GT") || keyword.equals("EQ")) {
				RelOp.OpCode op;
		    	switch(keyword) {
					case "LT":
						op = RelOp.OpCode.LT;
						break;
					case "GT":
						op = RelOp.OpCode.GT;
						break;
					case "EQ":
						op = RelOp.OpCode.EQ;
						break;
					default:
						throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
		    	}
		    	if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	NumExpr left = doParseNumExpr(tokens);
		    	if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	NumExpr right = doParseNumExpr(tokens);
		    	if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
		    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
		    	}
		    	RelOp relOp = new RelOp(op, left, right);
		    	return relOp;
			}
			
			else if(keyword.equals("AND") || keyword.equals("OR") ||  keyword.equals("NOT")) {
				BoolOp.OpCode op;
		    	switch(keyword) {
					case "AND":
						op = BoolOp.OpCode.AND;
						break;
					case "OR":
						op = BoolOp.OpCode.OR;
						break;
					case "NOT":
						op = BoolOp.OpCode.NOT;
						break;
					default:
						throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
		    	}
		    	if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	BoolExpr left = doParseBoolExpr(tokens);
		    	if(++i == tokens.size()) {
					throw new SyntaxError("(ERROR in parser: Premature end of input)");
				}
		    	
		    	/* 
		    	in caso di NOT il terzo attributo della classe BoolOp (ossia BoolExpr.right)
		    	non ha alcun significato e mettiamo di default valore TRUE
		    	*/
		    	if(keyword.equals("NOT")) {
		    		BoolConst right = new BoolConst(true);
		    		BoolOp boolOp = new BoolOp(op, left, right);
			    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
			    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
			    	}
			    	return boolOp;
		    	}
		    	else {
		    		BoolExpr right = doParseBoolExpr(tokens);
		    		BoolOp boolOp = new BoolOp(op, left, right);
		    		if(++i == tokens.size()) {
						throw new SyntaxError("(ERROR in parser: Premature end of input)");
					}
			    	if(tokens.get(i).type != Token.Type.CLOSE_PAR) {
			    		throw new SyntaxError("(ERROR in parser: Missing right parenthesis at token: " + i + " )");
			    	}
			    	return boolOp;
		    	}	
			}
			else {
				throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");
			}
		}
		
		else if(tokens.get(i).value.toString().equals( "TRUE")) {
			BoolConst boolConst = new BoolConst(true);
			return boolConst;
		}
		
		else if(tokens.get(i).value.toString().equals("FALSE")) {
			BoolConst boolConst = new BoolConst(false);
			return boolConst;
		}
		else {
			throw new SyntaxError("(ERROR in parser: Illegal token '" + tokens.get(i).value + "' at position: " + i + " )");

		}
	}
	
}

