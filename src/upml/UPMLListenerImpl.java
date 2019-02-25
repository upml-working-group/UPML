package upml;




import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import upml.nodes.*;
import upml.parser.*;
import upml.parser.UPMLParser.*;
 

public class UPMLListenerImpl extends UPMLBaseListener {
	StringBuilder cmds;
	String tabs = "";
	
	static private Set<String> bivarOperators;
	static private Set<String> univarDistirbutions;
	static private Set<String> bivarDistirbutions;
	static private Set<String> trivarDistirbutions;
	
	
	static public Properties mapNameToPython;
	
	
	final static String PROPERTIES_FILE = "upml/map.properties";
	
	static public void initNameMap() {
		if (mapNameToPython == null) {
			try {
				mapNameToPython = new Properties();
				InputStream in = UPMLListenerImpl.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
				if (in != null) {
					mapNameToPython.load(in);
				} else {
					System.err.println("Could not read file " + PROPERTIES_FILE + " in class path");
				}			
			} catch (IOException e) {
				System.err.println("Failed to read file " + PROPERTIES_FILE + ": " + e.getMessage());
			}		
		}
	}
	
	public UPMLListenerImpl(StringBuilder cmds) {
		this.cmds = cmds;
	}
	
	// we want to return String and String[] -- so make it a visitor of Object and cast to expected type
	public class UPMLASTVisitor extends UPMLBaseVisitor<Object> {
		List<Distribution> distributions = new ArrayList<>();
		
		Map<String, Integer> iteratorValue = new HashMap<>();
		Map<String, Integer> iteratorDimension = new HashMap<>();
		
		public UPMLASTVisitor() {			
			bivarOperators = new HashSet<>();
			for (String s : new String[]{"+","-","*","/","**","&&","||","<=","<",">=",">","%",":","^","!=","==","&","|","<<",">>",">>>"}) {
				bivarOperators.add(s);
			}
			
			univarDistirbutions = new HashSet<>();
			bivarDistirbutions = new HashSet<>();
			trivarDistirbutions = new HashSet<>();
			for (String s : new String[]{"dchisq" ,"dexp" ,"dpois" ,"dgeom","ddirich"}){
				univarDistirbutions.add(s);
			}
			for (String s : new String[]{"dnorm" ,"dlnorm" ,"dbeta" ,"dnchisq" ,"dnt" ,"dbinom" ,"dnbinom" ,"dnbinom_mu" ,"dcauchy" ,"df" ,"dgamma" ,"dunif" ,"dweibull" ,"dlogis" ,"dsignrank"}){
				bivarDistirbutions.add(s);
			}
			for (String s : new String[]{"dnbeta" ,"dnf" ,"dhyper" ,"dwilcox"}){
				trivarDistirbutions.add(s);			
			}
			
			initNameMap();
		}
		
		@Override
		public Object visitVar_stmt(Var_stmtContext ctx) {			
			return visit(ctx.getChild(1));
		}
		
		
		@Override
		public Object visitDim_list(Dim_listContext ctx) {
			int dim = (ctx.getChildCount() + 1) / 2;
			String [] range = new String[dim];
			for (int i = 0; i < dim; i++) {
				range[i] = (String) visit(ctx.getChild(i*2));
			}
			return range;
		}

		
		@Override
		public Object visitNode_dec(Node_decContext ctx) {
			String id = ctx.getText();
			if (id.indexOf('[') > -1) {
				id = ctx.getChild(0).getText();								
				String fs = (String) visit(ctx.getChild(2));				
				return id + "[" + fs + "]";								
			} else {
				return id;
			}
		}
				
		@Override
		public Object visitConstant(UPMLParser.ConstantContext ctx) {
			String text = ctx.getText();
			// make sure that the constant is double, long or boolean
			double d = 0;
			try {
				d = Double.parseDouble(text);
			} catch (NumberFormatException e) {
				try {
					d = Long.parseLong(text);
				} catch (NumberFormatException e2) {
					d = Boolean.parseBoolean(text) ? 1.0 : 0.0;
				}
			}
			//Constant c = Constant.createConstant(new double[]{d});
			//System.out.println("value = " + text + " = " + d);
			return text;
		}
	
		@Override
		public Object visitDeterm_relation(UPMLParser.Determ_relationContext ctx) {
			String f = (String) visit(ctx.getChild(2));
			String id = ctx.children.get(0).getText();
			if (id.indexOf('[') >= 0) {
				id = ctx.getChild(0).getChild(0).getText();
				String range = (String) visit(ctx.getChild(0).getChild(2));
				return tabs + id + "[" + range + "] = " + f + "\n";
			}

			return tabs + id + " = " + f + "\n";
		}
		
		@Override
		public Object visitStoch_relation(UPMLParser.Stoch_relationContext ctx) {
			System.out.println(2);
			String distr = (String) visit(ctx.getChild(2));
			String id = ctx.getChild(0).getText();
			distr = distr.replaceAll("\\$\\(id\\)", id);

			if (id.indexOf('[') != -1) {
				id = ctx.getChild(0).getChild(0).getText() + '[';
				for (int i = 2; i < ctx.getChild(0).getChildCount() -1; i++) {
					id += (String) visit(ctx.getChild(0).getChild(i));
					if (i < ctx.getChild(0).getChildCount() -2) {
						id += ',';
					}
				}
				id += ']';
			}
			
			String distribution = tabs + id + "~" + distr + "\n";
			return distribution;
		}
		
		@Override
		protected Object aggregateResult(Object aggregate, Object nextResult) {
			if (nextResult != null) {
				return nextResult;
			}
			return aggregate;
		}
		
		@Override
		public Object visitVar(VarContext ctx) {
			String id = ctx.getChild(0).getText();
			if (ctx.getChildCount() == 1) {
				// variable not indexed
				return id;
			}
			String index = (String) visit(ctx.getChild(2));
			String element = id + "[" + index + "]";
			return element;
		}
		
		
		@Override
		public Object visitExpression(UPMLParser.ExpressionContext ctx) {
			if (ctx.getChildCount() == 1) {
				// should be constant, variable or iterator reference
				String expr = (String) visit(ctx.getChild(0));
				return expr;
			}
			String expr = null;
			if (ctx.getChildCount() >= 2) {
				String s = ctx.getChild(1).getText();
				if (bivarOperators.contains(s)) {
					String f1 = (String) visit(ctx.getChild(0));
					String f2 = (String) visit(ctx.getChild(ctx.getChildCount() - 1));


					switch (s) {
					case "<": 
						switch (ctx.getChildCount()) {
						case 3:
							expr = f1 + " < " + f2; break;
						case 4:
							expr = f1 + " << " + f2; break;
						} 
						break;
					case ">":
						switch (ctx.getChildCount()) {
						case 3:
							expr = f1 + " > " + f2; break;
						case 4:
							expr = f1 + " >> " + f2; break;
						case 5:
							expr = f1 + " >>> " + f2; break;
						} 
						break;
					case "&&": expr = f1 + " and " + f2; break;
					case "||": expr = f1 + " or " + f2; break;
//					case "!=": expr = f1 + " <> " + f2; break;
					default:
						expr = f1 + " " + s + " " + f2; 
					}
				} else if (s.equals("!")) {
					String f1 = (String) visit(ctx.getChild(2));
					expr = "!" + f1;
				} else if (s.equals("~")) {
					String f1 = (String) visit(ctx.getChild(2));
					expr = "~" + f1;
				} else if (s.equals("[")) {
					String var = (String) visit(ctx.getChild(0));
					String f1 = (String) visit(ctx.getChild(2));
					expr = var + '[' +  f1 + ']';					
				}
			}
			return expr; 
		}
		
		@Override
		public Object visitDistribution(UPMLParser.DistributionContext ctx) {
			super.visitDistribution(ctx);
			
			String name = ctx.getChild(0).getText();			
			String [] f = (String[]) visit(ctx.getChild(2));
			
			
			if (mapNameToPython.containsKey(name)) {
				String className = mapNameToPython.getProperty(name);
				for (int i = 0; i < f.length; i++) {
					className = className.replaceAll("\\$\\(v"+(i+1)+"\\)", f[i]);
				}
				return className;
			}

			throw new IllegalArgumentException("Distributions not implemented yet. "
					+ "Choose one of " + Arrays.toString(mapNameToPython.keySet().toArray(new String[]{})));
		}
		

		@Override // for_loop: counter relations 
		public Object visitFor_loop(UPMLParser.For_loopContext ctx) {
			ParseTree counter = ctx.getChild(0);
			// counter: FOR '(' NAME IN range_element ')'
			String name = counter.getChild(2).getText();
			String range = (String) visit(counter.getChild(4));
			StringBuilder loop = new StringBuilder();
			loop.append(tabs + "for " + name + " in " + range + "\n");
			tabs += "\t";
			String inner = (String) visit(ctx.getChild(1));
			loop.append(inner);
			tabs = tabs.substring(0, tabs.length() - 2);						
			return null;
		}
		
		@Override // counter: FOR '(' NAME IN range_element ')'
		public Object visitCounter(UPMLParser.CounterContext ctx) {
			return super.visitCounter(ctx);
		}

		@Override
		public Object visitRange_list(Range_listContext ctx) {
			String [] f = new String[ctx.getChildCount()/2+1];
			for (int i = 0; i < f.length; i++) {
				f[i] = (String) visit(ctx.getChild(i*2));
			}
			
			StringBuilder range = new StringBuilder();
			range.append("[");
			for (int i = 0; i < f.length; i++) {
				range.append(f[i]);
				if (i < f.length - 1) {
					range.append(", ");
				}
			}
			range.append("]");
			return range;
		}
		
		@Override // range_element: | expression 
		public Object visitRange_element(UPMLParser.Range_elementContext ctx) {
			if (ctx.getChildCount() == 0) {
				return null;
			}
			return visit(ctx.getChild(0));
		}
		
		@Override // expression_list : expression (',' expression)*
		public Object visitExpression_list(UPMLParser.Expression_listContext ctx) {
			JFunction [] f = new JFunction[ctx.getChildCount()/2+1];
			for (int i = 0; i < f.length; i++) {
				f[i] = (JFunction) visit(ctx.getChild(i*2));
			}
			return f;
		}
		
		@Override
		public Object visitMethodCall(UPMLParser.MethodCallContext ctx) {
			String methodCall = null;
			String functionName = ctx.children.get(0).getText();
			
			if (functionName.equals("c")) {
				String [] f= (String []) visit(ctx.getChild(2));				
				StringBuilder array = new StringBuilder();
				array.append("[");
				for (int i = 0; i < f.length; i++) {
					array.append(f[i]);
					if (i < f.length - 1) {
						array.append(", ");
					}
				}
				array.append("]");
				return array;
			}
			
			// process expression_list
			String [] f =  (String[]) visit(ctx.getChild(2));
			if (mapNameToPython.containsKey(functionName)) {
				String className = mapNameToPython.getProperty(functionName);
				StringBuilder array = new StringBuilder();
				array.append(className + "(");
				for (int i = 0; i < f.length; i++) {
					array.append(f[i]);
					if (i < f.length - 1) {
						array.append(", ");
					}
				}
				array.append(")");
				return array;
			}
			
			switch (functionName) {
				case "length": methodCall = "len(" + f[0] + ")";break;

				case "inprod": methodCall = "np.dot(" +f[0] + "," + f[1] +")"; break;
				case "prod":
				case "%*%": methodCall = "np.matmul(" +f[0] + "," + f[1] +")";break;

				case "min": methodCall = "min(" +f[0] + "," + f[1] +")";break;
				case "max": methodCall = "max(" +f[0] + "," + f[1] +")";break;
				case "sum": methodCall = "sum(" +f[0] + "," + f[1] +")";break;

				default:
					throw new IllegalArgumentException("Unknown function : " + functionName);
			}
			
			return methodCall;
		}
		
	}

	public Object parse(String CASentence) {
        // Custom parse/lexer error listener
        BaseErrorListener errorListener = new BaseErrorListener() {
        	@Override
        	public void syntaxError(Recognizer<?, ?> recognizer, 
        			Object offendingSymbol, int line, int charPositionInLine,
        			String msg, RecognitionException e) {
        		e.printStackTrace();
        	    if ( e instanceof NoViableAltException ) {
        	    	NoViableAltException nvae = (NoViableAltException)e;
        	    	System.out.println(nvae.getLocalizedMessage());
           }
           else {
           }
        	    throw new UPMLParsingException(msg, charPositionInLine, line);
        	}
        };

        // Get our lexer
        UPMLLexer lexer = new UPMLLexer(CharStreams.fromString(CASentence));        
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

	    // Get a list of matched tokens
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	 
	    // Pass the tokens to the parser
	    UPMLParser parser = new UPMLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
	 
        ParseTree parseTree = parser.input();

        // Traverse parse tree, constructing BEAST tree along the way
        UPMLASTVisitor visitor = new UPMLASTVisitor();

        return visitor.visit(parseTree);
	}
	
}
