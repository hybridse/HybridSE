package gov.nasa.jpf.symbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import antlr4.smtlib.SMTLIBv2Lexer;
import antlr4.smtlib.SMTLIBv2Parser;
import antlr4.smtlib.SMTLIBv2Parser.HexadecimalContext;
import antlr4.smtlib.SMTLIBv2Parser.Qual_identiferContext;
import antlr4.smtlib.SMTLIBv2Parser.TermContext;
import gov.nasa.jpf.symbc.numeric.BinaryLinearIntegerExpression;
import gov.nasa.jpf.symbc.numeric.Comparator;
import gov.nasa.jpf.symbc.numeric.Constraint;
import gov.nasa.jpf.symbc.numeric.IntegerConstant;
import gov.nasa.jpf.symbc.numeric.IntegerExpression;
import gov.nasa.jpf.symbc.numeric.LinearIntegerConstraint;
import gov.nasa.jpf.symbc.numeric.LogicalORLinearIntegerConstraints;
import gov.nasa.jpf.symbc.numeric.Operator;
import gov.nasa.jpf.symbc.numeric.SymbolicInteger;
import main.corana.external.connector.ArithmeticUtils;
import main.corana.pojos.BitVec;

public class BV2LIAConverter {
	
	public static IntegerExpression toIntegerExpression(BitVec smtBitVec) {
		SMTLIBv2Lexer lexer = new SMTLIBv2Lexer(CharStreams.fromString(smtBitVec.getSym()));
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    SMTLIBv2Parser parser = new SMTLIBv2Parser(tokens);
	    ParseTree tree = parser.start().general_response().specific_success_response().get_assertions_response();
	    IntegerExpression constraint = null;
	    if (tree != null) {
	    //printTree(tree);	
	    List<SMTLIBv2Parser.TermContext> childTerms = new ArrayList<>();
	    for (int i = 0; i < tree.getChildCount(); i++) {
	    	if (tree.getChild(i) instanceof SMTLIBv2Parser.TermContext) 
	    		childTerms.add((TermContext) tree.getChild(i));
	    }
	    List<Constraint> constraintList = new ArrayList<>();
	    
	    if (childTerms.size() == 3) { // operation 
	    	//System.err.print(toOperator(childTerms.get(0)));
	    	Object op = toOperator(childTerms.get(0));
	    	if (op instanceof Operator) {
	    		Object childLeft = toExpression(childTerms.get(1));
	    		Object childRight = toExpression(childTerms.get(2));
	    		constraint = new BinaryLinearIntegerExpression((IntegerExpression) childLeft, (Operator) op, (IntegerExpression) childRight);
	    	} 
	    	}
		return constraint; 
	    } else {
	    	// only bitVec 
	    	if (smtBitVec.getSym().contains("SYM")) {
	    	constraint = new SymbolicInteger(smtBitVec.getSym());	
	    	} else 
	    	constraint = new IntegerConstant(ArithmeticUtils.BitVecToInteger(smtBitVec));
	    return constraint;
	    }
	}
	
	public static List<Constraint> preToInorder(String smtLibString) {
		String inStr = "";
		// remove () 
	    SMTLIBv2Lexer lexer = new SMTLIBv2Lexer(CharStreams.fromString(smtLibString));
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    SMTLIBv2Parser parser = new SMTLIBv2Parser(tokens);
	    ParseTree tree = parser.start().general_response().specific_success_response().get_assertions_response();
	    //printTree(tree);
	    List<SMTLIBv2Parser.TermContext> childTerms = new ArrayList<>();
	    for (int i = 0; i < tree.getChildCount(); i++) {
	    	if (tree.getChild(i) instanceof SMTLIBv2Parser.TermContext) 
	    		childTerms.add((TermContext) tree.getChild(i));
	    }
	    List<Constraint> constraintList = new ArrayList<>();
	    Object constraint ;
	    if (childTerms.size() == 3) {	 // operation 
	    	//System.err.print(toOperator(childTerms.get(0)));
	    	Object op = toOperator(childTerms.get(0));
	    	if (op instanceof Comparator) {
	    		Object childLeft = toExpression(childTerms.get(1));
	    		Object childRight = toExpression(childTerms.get(2));
	    		if ((childLeft instanceof IntegerExpression) && (childRight instanceof IntegerExpression)) {
	    			constraint = new LinearIntegerConstraint((IntegerExpression) childLeft, (Comparator) op, (IntegerExpression) childRight);
	    			constraintList.add((Constraint) constraint); 
	    		}
	    			else {
	    				if (childLeft instanceof ArrayList) {
			    			constraintList.addAll((Collection<? extends Constraint>) childLeft);
			    			} else constraintList.add((Constraint) childLeft);
	    				if (childRight instanceof ArrayList) {
	    					constraintList.addAll((Collection<? extends Constraint>) childRight);
	    				} else 	constraintList.add((Constraint) childRight);
		    	}
	    	} else if (op instanceof Operator) {
	    		Object childLeft = toExpression(childTerms.get(1));
	    		Object childRight = toExpression(childTerms.get(2));
	    		if (childLeft instanceof ArrayList) {
	    			constraintList.addAll((Collection<? extends Constraint>) childLeft);
	    			} else constraintList.add((Constraint) childLeft);
				if (childRight instanceof ArrayList) {
					constraintList.addAll((Collection<? extends Constraint>) childRight);
				} else 	constraintList.add((Constraint) childRight);
	    		
	    	}
	    	} else if (childTerms.size() == 2 && childTerms.get(0).getText().equals("not")) {
		    	constraint = toNotExpression( childTerms.get(1));
		    	constraintList.add((Constraint) constraint);
		    }
//	    for (Constraint c: constraintList) {
//	    	System.out.println(c.toString());
//	    }
	    
		return constraintList;
	}
	private static void printTree(ParseTree tree) {
		System.err.println(tree.getClass().getSimpleName() + " " + tree.getText() );
		List<SMTLIBv2Parser.TermContext> childTerms = new ArrayList<>();
	    for (int i = 0; i < tree.getChildCount(); i++) {
	    	if (tree.getChild(i) instanceof SMTLIBv2Parser.TermContext) 
	    		childTerms.add((TermContext) tree.getChild(i));
	    		System.err.println(tree.getChild(i).getText() + " " + tree.getChild(i).getClass().getSimpleName());
	    }
	    for (ParseTree child : childTerms) {
	    	printTree(child);
	    }   
	}
	
	private static Object toOperator(ParserRuleContext parserRuleContext) {
		ParseTree simpleSym;
		if (parserRuleContext instanceof TermContext)
			simpleSym = ((TermContext) parserRuleContext).getChild(0).getChild(0).getChild(0);
		else 
			simpleSym = parserRuleContext.getChild(0).getChild(0);
		//assert(simpleSym instanceof SMTLIBv2Parser.SymbolContext);
		SMTLIBv2Parser.SimpleSymbolContext simpleSymCtx = ((SMTLIBv2Parser.SymbolContext) simpleSym).simpleSymbol();
		switch (simpleSymCtx.getText()) {
		// Operator
		case "bvsub":
			return Operator.MINUS;
		case "bvadd":
			return Operator.PLUS;
		case "bvmul":
			return Operator.MUL;
		case "or":
			return Operator.OR;
		// Comparator
		case "and":
			return Operator.AND;
		case "=":
			return Comparator.EQ;
		case "bvuge":
			return Comparator.GE;
		case "bvugt":
			return Comparator.GT;
		case "bvsle":
			return Comparator.LE;
		case "bvslt":
			return Comparator.LT;
		case "bvsgt":
			return Comparator.GT;
		case "not":
			return Comparator.NE;
		default:
			System.err.println("Undefined: " + simpleSymCtx.getText());
			return Comparator.EQ;
		}
	}
	private static Object toNegOperator(ParserRuleContext parserRuleContext) {
		String simpleSym;
		simpleSym = parserRuleContext.getText();
		//assert(simpleSym instanceof SMTLIBv2Parser.SymbolContext);
		switch (simpleSym) {
		// Operator
		case "=":
			return Comparator.NE;
		case "bvuge":
			return Comparator.LT;
		case "bvugt":
			return Comparator.LE;
		case "bvsle":
			return Comparator.GT;
		case "bvslt":
			return Comparator.GE;
		case "not":
			return Comparator.EQ;
		default:
			//System.err.println("Undefined: " + simpleSym);
			return Comparator.EQ;
	}
	}
	private static Comparator getNeg(Comparator op) {
		switch (op) {
			case NE:
				return Comparator.EQ;
			case GE:
				return Comparator.LT;
			case GT:
				return Comparator.LE;
			case LE:
				return Comparator.GT;
			case LT:
				return Comparator.GE;
			case EQ:
				return Comparator.NE;
			default:
				System.err.println("Undefined: " + op);
				return Comparator.EQ;
		}
	}
		
	private static Object toNotExpression (ParserRuleContext smtTree) {
		Object constraint = null;
		if (smtTree instanceof TermContext) {
			List<ParserRuleContext> childTerms = new ArrayList<>();
		    for (int i = 0; i < smtTree.getChildCount(); i++) {
		    	if (!(smtTree.getChild(i) instanceof TerminalNodeImpl)) 
		    		childTerms.add((ParserRuleContext) smtTree.getChild(i));
		    }
		    if (childTerms.size() == 3) { // operation 
		    	//System.err.print(toOperator(childTerms.get(0)));
		    	Object op = toNegOperator(childTerms.get(0));
		    	Object childLeft = toExpression(childTerms.get(1));
	    		Object childRight = toExpression(childTerms.get(2));
	    		if (op instanceof Comparator) {
		    		if (childLeft instanceof IntegerExpression && childRight instanceof IntegerExpression) {
		    		constraint = new LinearIntegerConstraint((IntegerExpression) childLeft, getNeg((Comparator) op), (IntegerExpression) childRight);
		    		} else if (childLeft instanceof LogicalORLinearIntegerConstraints || childRight instanceof LogicalORLinearIntegerConstraints ) {
		    			// We only choose one
		    			constraint = (childLeft instanceof LogicalORLinearIntegerConstraints) ? childRight: childLeft;
		    		}
		    	} else {
		    		if (op == Operator.OR) {
		    			//LogicalORLinearIntegerConstraints 
		    			List<LinearIntegerConstraint> cList = new ArrayList<>();
		    			cList.add( (LinearIntegerConstraint)childLeft);
		    			cList.add( (LinearIntegerConstraint)childRight);
		    			constraint = new LogicalORLinearIntegerConstraints(cList);
		    			//constraint = new BinaryNonLinearIntegerExpression((IntegerExpression) childLeft, (Operator) op, (IntegerExpression) childRight);
		    		} else if (op == Operator.AND) {
		    			List<LinearIntegerConstraint> cList = new ArrayList<>();
		    			cList.add( (LinearIntegerConstraint)childLeft);
		    			cList.add( (LinearIntegerConstraint)childRight);
		    		} else {
		    			// op instance of Operator
			    		//System.err.println(childTerms.get(1).getClass().getSimpleName());
			    		constraint = new BinaryLinearIntegerExpression((IntegerExpression) childLeft, (Operator) op, (IntegerExpression) childRight);
		    		}
		    	}
		    } else if (childTerms.size() == 1) {
		    	constraint = toExpression(childTerms.get(0));
		    } else if (childTerms.size() == 2 && childTerms.get(0).getText().equals("not")) {
		    	constraint = toNegOperator(childTerms.get(1));
		    	
		    }
		      
		} else if (smtTree instanceof SMTLIBv2Parser.Qual_identiferContext) {
			//System.err.println(smtTree.getText());
			constraint = new SymbolicInteger(((Qual_identiferContext) smtTree).getText());
		} else if (smtTree instanceof SMTLIBv2Parser.Spec_constantContext) {
			//System.err.println(smtTree.getText());
			if (smtTree.getChild(0) instanceof HexadecimalContext) {
				
				constraint = new IntegerConstant(ArithmeticUtils.BitVecToInteger(new BitVec(smtTree.getChild(0).getText())));
			}
			//constraint = new IntegerConstraint();
		}
		if (constraint == null) {}
			//System.out.println(smtTree.getText() + smtTree.getClass().getSimpleName());
		return constraint;
	}
	
	
	private static Object toExpression(ParserRuleContext smtTree) {
		Object constraint = null;
		//System.err.println(smtTree.getText());
		if (smtTree instanceof TermContext) {
			List<ParserRuleContext> childTerms = new ArrayList<>();
		    for (int i = 0; i < smtTree.getChildCount(); i++) {
		    	if (!(smtTree.getChild(i) instanceof TerminalNodeImpl)) 
		    		childTerms.add((ParserRuleContext) smtTree.getChild(i));
		    }
		    if (childTerms.size() == 3) { // operation 
		    	//System.err.print(toOperator(childTerms.get(0)));
		    	Object op = toOperator(childTerms.get(0));
		    	Object childLeft = toExpression(childTerms.get(1));
	    		Object childRight = toExpression(childTerms.get(2));
	    		if (op instanceof Comparator) {
		    		if (childLeft instanceof IntegerExpression && childRight instanceof IntegerExpression) {
		    			constraint = new LinearIntegerConstraint((IntegerExpression) childLeft, (Comparator) op, (IntegerExpression) childRight);
		    		} else if (childLeft instanceof LinearIntegerConstraint && childRight instanceof LogicalORLinearIntegerConstraints) {
		    			List<Constraint> cList = new ArrayList<>();
		    			cList.add( (Constraint) childLeft);
		    			cList.add( (Constraint) childRight);
		    			constraint = cList;
		    		}
		    		
		    	} else {
		    		if (op == Operator.OR) {
		    			//LogicalORLinearIntegerConstraints 
		    			List<LinearIntegerConstraint> cList = new ArrayList<>();
		    			cList.add( (LinearIntegerConstraint)childLeft);
		    			cList.add( (LinearIntegerConstraint)childRight);
		    			constraint = new LogicalORLinearIntegerConstraints(cList);
		    			//constraint = new BinaryNonLinearIntegerExpression((IntegerExpression) childLeft, (Operator) op, (IntegerExpression) childRight);
		    		} else if (op == Operator.AND) {
		    			List<LinearIntegerConstraint> cList = new ArrayList<>();
		    			cList.add( (LinearIntegerConstraint)childLeft);
		    			cList.add( (LinearIntegerConstraint)childRight);
		    		} else {
		    			// op instance of Operator
			    		//System.err.println(childTerms.get(1).getClass().getSimpleName());
			    		constraint = new BinaryLinearIntegerExpression((IntegerExpression) childLeft, (Operator) op, (IntegerExpression) childRight);
		    		}
		    	}
		    } else if (childTerms.size() == 1) {
		    	constraint = toExpression(childTerms.get(0));
		    } else if (childTerms.size() == 2 && childTerms.get(0).getText().equals("not")) {
		    	constraint = toNotExpression(childTerms.get(1));
		    	
		    }
		      
		} else if (smtTree instanceof SMTLIBv2Parser.Qual_identiferContext) {
			//System.err.println(smtTree.getText());
			constraint = new SymbolicInteger(((Qual_identiferContext) smtTree).getText());
		} else if (smtTree instanceof SMTLIBv2Parser.Spec_constantContext) {
			//System.err.println(smtTree.getText());
			if (smtTree.getChild(0) instanceof HexadecimalContext) {
				
				constraint = new IntegerConstant(ArithmeticUtils.BitVecToInteger(new BitVec(smtTree.getChild(0).getText())));
			}
			//constraint = new IntegerConstraint();
		}
		if (constraint == null)
			System.out.println(smtTree.getText() + smtTree.getClass().getSimpleName());
		return constraint;
	}
}