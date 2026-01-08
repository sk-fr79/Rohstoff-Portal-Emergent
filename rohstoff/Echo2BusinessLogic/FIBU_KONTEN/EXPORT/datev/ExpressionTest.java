package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;


public class ExpressionTest {
/*
 Produce an array of simple token objects from a string.
 A simple token object contains these members:
	      type: 'name', 'string', 'number', 'operator'
	      value: string or number value of the token
	      from: index of first character of the token
	      to: index of the last character + 1
 Comments of the // type are ignored.
 Operators are by default single characters. Multicharacter
 operators can be made by supplying a string of prefix and
 suffix characters.
 characters. For example,
	      '<>+-&', '=>&:'
 will match any of these:
	      <=  >>  >>>  <>  >=  +: -: &: &&: &&
	      */
	public class Token {
		public String type;
		public String value;
		public int from;
		public int to;
		
		public Token set(String type, String value, int from, int to) {
			this.type = type;
			this.value = value;
			this.from = from;
			this.to = to;
			return this;
			
		}
		public void error(String message) {
			
		}
		public void error(String message, Token token) {
			
		}
		public String toString() {
			return "[Token "+type+": "+value+"]";
		}
	}
	
	public class Tokenizer {
		private String input;
		/** The current character */
		private char c;
		/** The current character's index */
		private int index = 0;
		/** The index of the start of the token. */
		private int from; 
		/** Length of the input string */
		private int length = 0;
		/** The string value */
		private String str;
		/** The quote character */
		private char q;
		
		/** The results */
		private ArrayList<Token> result = new ArrayList<Token>();
		
		private String prefix = "=<>!+-*&|/%^";
		private String suffix = "=<>&|";
		
		public Tokenizer(String input) {
			this.input = input;
		}
		
		private Token make(String type, String value) {
			return new Token().set(type, value, this.from, this.index);
		}
		
		/** The main loop 
		 * @return */
		public ArrayList<Token> tokenize() {
			length = this.input.length();
			while (index < length) {
				// Loop through this text, one character at a time.
				c = input.charAt(index);
				from = index;
			
		        if (c <= ' ') {
					// Ignore whitespace.
		            index++;
		            continue;
		            
		        } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
		        	// Names
		            str = String.valueOf(c);
		            index++;
		            for (;;) {
		            	try {
		            		c = input.charAt(index);
		            	} catch (IndexOutOfBoundsException e) {
		            		result.add(make("name", str));
		            		return result;
		            	}
		                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||
		                        (c >= '0' && c <= '9') || c == '_') {
		                    str += String.valueOf(c);
		                    index++;
		                } else {
		                    break;
		                }
		            }
		            result.add(make("name", str));
		            
    	        } else if (c >= '0' && c <= '9') {
    		    	// A number cannot start with a decimal point. It must start with a digit,
    		    	// possibly '0'.
    	            str = String.valueOf(c);
    	            index++;

    	            // Look for more digits.
    	            for (;;) {
    	            	try {
    	            		c = input.charAt(index);
		            	} catch (IndexOutOfBoundsException e) {
		            		result.add(make("number", str));
		            		return result;
		            	}
    	                
    	                if (c < '0' || c > '9') {
    	                    break;
    	                }
    	                index++;
    	                str += c;
    	            }
    	            
    	            // Look for a decimal fraction part.
    	            if (c == '.') {
    	                index++;
    	                str += c;
    	                for (;;) {
    	                	try {
    	                		c = input.charAt(index);
    		            	} catch (IndexOutOfBoundsException e) {
    		            		result.add(make("number", str));
    		            		return result;
    		            	}
    	                    if (c < '0' || c > '9') {
    	                        break;
    	                    }
    	                    index++;
    	                    str += c;
    	                }
    	            }
    	            // Look for an exponent part.
    	            if (c == 'e' || c == 'E') {
    	                index++;
    	                str += c;
    	                try {
    	                	c = input.charAt(index);
		            	} catch (IndexOutOfBoundsException e) {
		            		result.add(make("number", str));
		            		return result;
		            	}

    	                if (c == '-' || c == '+') {
    	                    index++;
    	                    str += c;
    	                    c = input.charAt(index);
    	                }
    	                if (c < '0' || c > '9') {
    	                    make("number", str).error("Bad exponent");
    	                }
    	                do {
    	                    index++;
    	                    str += c;
    	                    try {
    	                    	c = input.charAt(index);
    		            	} catch (IndexOutOfBoundsException e) {
    		            		result.add(make("number", str));
    		            		return result;
    		            	}

    	                } while (c >= '0' && c <= '9');
    	            }
    	            // Make sure the next character is not a letter.
    	            if (c >= 'a' && c <= 'z') {
    	                str += c;
    	                index++;
    	                make("number", str).error("Bad number");
    	            }
			    	// Convert the string value to a number. If it is finite, then it is a good
			    	// token.
	                result.add(make("number", str));
    	            /*} catch (Exception e) {
    	                make("number", str).error("Bad number");
    	            }*/

    	        } else if (c == '\'' || c == '"') {
		    	// strings
    	            str = "";
    	            q = c;
    	            index++;
    	            for (;;) {
    	            	try {
    	            		c = input.charAt(index);
		            	} catch (IndexOutOfBoundsException e) {
    	                    make("string", str).error("Unterminated string.");
    	                    return result;
		            	}
    	            	
    	                if (c < ' ') {
    	                    make("string", str).error(c == '\n' || c == '\r' ?
    	                        "Unterminated string." :
    	                        "Control character in string.", make("", str));
    	                }
    	                // Look for the closing quote.
    	                if (c == q) {
    	                    break;
    	                }
    	                // Look for escapement.
    	                if (c == '\\') {
    	                    index++;
    	                    if (index >= length) {
    	                        make("string", str).error("Unterminated string");
    	                    }
    	                    try {
    	                    	c = input.charAt(index);
    		            	} catch (IndexOutOfBoundsException e) {
    		            		//result.add(make("number", str));
        	                    return result;
    		            	}
    	                    switch (c) {
    	                    case 'b':
    	                        c = '\b';
    	                        break;
    	                    case 'f':
    	                        c = '\f';
    	                        break;
    	                    case 'n':
    	                        c = '\n';
    	                        break;
    	                    case 'r':
    	                        c = '\r';
    	                        break;
    	                    case 't':
    	                        c = '\t';
    	                        break;
    	                    case 'u':
    	                        if (index >= length) {
    	                            make("string", str).error("Unterminated string");
    	                        }
    	                        /*c = parseInt(this.substr(i + 1, 4), 16);
    	                        if (!isFinite(c) || c < 0) {
    	                            make("string", str).error("Unterminated string");
    	                        }
    	                        c = String.fromCharCode(c);
    	                        */
    	                        index += 4;
    	                        break;
    	                    }
    	                }
    	                str += c;
    	                index++;
    	            }
    	            index++;
    	            result.add(make("string", str));

    	        } else if (c == '/' && input.charAt(index + 1) == '/') {
    	        	// comment.
    	            index++;
    	            for (;;) {
    	            	try {
    	            		c = input.charAt(index);
    	            	} catch (IndexOutOfBoundsException e) {
    	            		break;
    	            	}
    	                if (c == '\n' || c == '\r') {
    	                    break;
    	                }
    	                index++;
    	            }

    	        } else if (prefix.indexOf(c) >= 0) {
			    	// combining
    	        	try {
    	        		str = String.valueOf(c);
	            	} catch (IndexOutOfBoundsException e) {
	                    return result;
	            	}
    	            index++;
    	            while (true) {
    	            	try {
    	            		c = input.charAt(index);
		            	} catch (IndexOutOfBoundsException e) {
		                    break;
		            	}

    	                if (index >= length || suffix.indexOf(c) < 0) {
    	                    break;
    	                }
    	                str += c;
    	                index++;
    	            }
    	            result.add(make("operator", str));


    	        } else {
			    	// single-character operator
    	            index++;
    	            result.add(make("operator", String.valueOf(c)));
    	        }
    	    }
			return result;
		}
	}
	
	@Test
	public void tokenTest() {
		Tokenizer t = new Tokenizer("hallo;'hier';1<2;2>>1");
		System.out.println(t.tokenize());
	}

	public class ParseException extends RuntimeException {
		ParseException(String message) {
			super(message);
		}
	}
	
	
	public enum Arity {
		UNARY, BINARY, TERNARY, NARY, NAME
	}
	
	public enum Type {
		LITERAL, CONSTANT, STRING, NUMBER, OPERATOR
	}
	
	/** 
	 * Symbol which is created (and saved in the symbol table) for 
	 * every token in the token stream. Every concrete symbol should
	 * inherit from this and override the specific fuctions.
	 * @author nils
	 *
	 */
	@SuppressWarnings("unused")
	public class Symbol implements Cloneable {
		/** left binding power */
		protected int leftBindingPower = 0;
		/** Reference to the parser which created the Symbol */
		protected Parser parser;
		/** The symbol id */
		protected String id;
		/** The symbol's inverse id */
		protected String inverse;
		/** The symbol value */
		protected String value;
		

		/** The symbol's arity. Unaray if null, which is default */
		protected Arity arity;
		
		public Arity getArity() {
			return arity;
		}

		/** Any sub-symbols */
//		protected Symbol first;
//		protected Symbol second;
		
		protected ArrayList<Symbol> children;
		protected ArrayList<Symbol> getChildren() {
			return children;
		}
		
		public Symbol addChild(Symbol child) {
			if (children == null) {
				children = new ArrayList<Symbol>();
			}
			
			if (child.getId().equals(this.getId())) {
				//
				if (child.getId().equals("&&") || child.getId().equals("||")) {
					children.addAll(child.getChildren());
					return this;
				}
			}
			children.add(child);
			return this;
		}
		
		/** Constructors */
		public Symbol(String id) {
			this.id = id;
			this.arity = Arity.UNARY;
		}
		public Symbol(String id, Arity arity) {
			this(id);
			this.arity = arity;
		}
		public Symbol(String id, String value) {
			this(id);
			this.value = value;
			this.arity = Arity.UNARY;
		}
		
		public Symbol(String id, int bindingPower) {
			this(id);
			System.out.println("Creating "+id+" ("+bindingPower+")");
			this.leftBindingPower = bindingPower;
		}
		
		public int getLeftBindingPower() {
			return this.leftBindingPower;
		}
		
		public void setLeftBindingPower(int bindingPower) {
			this.leftBindingPower = bindingPower;
		}
		
		public void setValue(String value) {
			this.value = value;
		}

		
		public String getId() {
			return this.id;
		}
		
		/** Should be true if std symbol is statement */ 
		public boolean isStatement() {
			return false;
		}
		public boolean isAssignment() {
			return false;
		}
		
		/** Sets the parser. Mandatory for each symbol to be used */
		public void setParser(Parser p) {
			this.parser = p;
		}
		
		/** Null denotation */
		public Symbol nud() {
			throw new ParseException("Undefined.");
		}
		
		/** Left denotation */
		public Symbol led(Symbol left) {
			throw new ParseException("Missing operator.");
		}
		
		/** Statement denotation */
		public Symbol std() {
			return null;
		}
		
		public String toString() {
			if (this.value != null) return this.value;
			return id;
		}
		
		public Symbol getCopy() {
			Symbol s = new Symbol(this.id);
			s.setParser(this.parser);
			s.setLeftBindingPower(this.leftBindingPower);
			return s;
			
		}
		
		public Symbol clone() throws CloneNotSupportedException {
			return (Symbol) super.clone();
		}

		public void setInverse(String inverse) {
			this.inverse = inverse;
		}
		
		public Symbol invert() {
			if (inverse == null) return null;
			String tmp1 = inverse;
			String tmp2 = id;
			id = tmp1;
			inverse = tmp2;
			return this;
		}
	}
	
	public class Parser {
		/** The stream of tokens */
		private ArrayList<Token> input;
		/** The current token index */
		private int tokenIndex = 0;
		/** The current token */
		private Symbol currentSymbol;
		/** The symbol table */
		private HashMap<String, Symbol> symbolTable = new HashMap<String, Symbol>();
		
		/** Constructor. Want's some tokens to be parsed to a tree. */
		public Parser(ArrayList<Token> input) {
			initLanguage();
			this.input = input;
		}
		
		protected void initLanguage() {
			defineSymbol(";");
		    defineSymbol("(end)");
		    defineSymbol("(name)");
		    defineSymbol(":");
		    defineSymbol(")");
		    defineSymbol("]");
		    defineSymbol("}");
		    defineSymbol(",");

		    defineConstant("true", true);
		    defineConstant("false", false);
		    defineConstant("null", null);
		    

		    //defineLiteral(); 
		    
		    //symbol("(literal)").nud = itself;
		    
		    //defineThis();
	/*
		    symbol("this").nud = function () {
		        scope.reserve(this);
		        this.arity = "this";
		        return this;
		    };
	*/
		    //assignment("=");
		    //assignment("+=");
		    //assignment("-=");
			
			defineInfix("&&", "||", 40);
			defineInfix("||", "&&", 35);

			
			defineInfixR("==", "!=", 45);
			defineInfixR("!=", "==", 45);
			defineInfixR("<", ">=", 45);
			defineInfixR(">", "<=", 45);
			defineInfixR(">=", "<", 45);
			defineInfixR("<=", ">", 45);

			defineInfix("+", 50);
			defineInfix("-", 50);


			defineInfix("*", 60);
			defineInfix("/", 60);
			
			definePrefix("!");
			
			defineLeftBracket();
		}
		
		
		public ArrayList<Symbol> parse() {
	        advance();
	        ArrayList<Symbol> s = statements();
	        advance("(end)");
	        return s;
		}
		

		
		/** 
		 * Retrieves a symbol for {@param id}, or creates it, if not yet in 
		 * existant in the symbol table. Each symbol thus get's a reference
		 * to the current parser instance. 
		 * @param id
		 * @return the symbol
		 */
		protected Symbol getSymbol(String id, String inverse, int bindingPower, Symbol ifAbsentCreate) {
			Symbol s = symbolTable.get(id);
			if (s == null) {
				if (ifAbsentCreate == null) {
					// If we intentionally provied null here, we don't want
					// to create it in case of absence => return null!
					return s;
				}
				// Otheriwse, create it! 
				s = ifAbsentCreate;
				s.setParser(this);
				if (inverse != null) {
					s.setInverse(inverse);
				}
				symbolTable.put(id, s);
			} 
			if (bindingPower >= s.getLeftBindingPower()) {
				s.setLeftBindingPower(bindingPower);
			}
			return s;
		}
		
		protected Symbol getSymbol(String id) {
			return getSymbol(id, null, 0, new Symbol(id){
				@Override
				public Symbol nud() {
					return this;
				}
			});
		}
		
		
		/** Define simple symbols: separators and brackets */
		protected void defineSymbol(String id) {
			getSymbol(id);
		}
		
		
		public void defineConstant(String id, Object value) {
			/* var x = symbol(s);
        x.nud = function () {
            scope.reserve(this);
            this.value = symbol_table[this.id].value;
            this.arity = "literal";
            return this;
        };
        x.value = v;
        return x; */
		}
		
		
		/** Infix operators: && and || */
		/*
		public void defineInfixOLD(final String id, final int leftBindingPower) {
			Symbol s = getSymbol(id, leftBindingPower, new Symbol(id){			
				@Override
				public Symbol led(Symbol left) {
					this.first = left;
					this.second = this.parser.expression(leftBindingPower);
					this.arity = Arity.BINARY;
					return this;
				}
				
				@Override
				public String toString() {
					if (this.first != this && this.second != this) {
						return "("+this.first.toString()+this.id+this.second.toString()+")";
					}
					return (this.first == this ? "!" : "_")+(this.second == this ? "!" : "_");
				}
			});
		}
		*/

		/** Infix operators: && and || */
		private Symbol defineInfix(final String id, final String inverse, final int leftBindingPower) {
			return getSymbol(id, inverse, leftBindingPower, createInfixSymbol(id, inverse));
		}
		private Symbol defineInfix(final String id, final int leftBindingPower) {
			return getSymbol(id, null, leftBindingPower, createInfixSymbol(id, null));
		}
		
		public Symbol createInfixSymbol(String id, String inverse) {
			Symbol s = new Symbol(id, Arity.NARY){			
				@Override
				public Symbol led(Symbol left) {
					Symbol right = this.parser.expression(leftBindingPower);
					children = new ArrayList<Symbol>();

					if (left.getId().equals(this.id)) {
						// Same type of operator, so we can actually merge 
						children.addAll(left.getChildren());
					} else {
						children.add(left);
					}
					
					if (right.getId().equals(this.id)) {
						// Same type of operator, so we can actually merge 
						children.addAll(right.getChildren());
					} else {
						children.add(right);
					}
					return this;
				}
				
				@Override
				public String toString() {
					StringBuilder sb = new StringBuilder();
					sb.append("(");
					boolean more = false;
					for (Symbol s : children) {
						if (more) {
							sb.append(" ");
							sb.append(this.id);
							sb.append(" ");
						}
						sb.append(s.toString());
						more = true;
					}
					sb.append(")");
					return sb.toString();
				}
			};
			s.setParser(this);
			s.setInverse(inverse);
			return s;
		}
		
		public Symbol newAnd(Symbol...symbols) {
			Symbol answer = createInfixSymbol("&&", "||");
			for (Symbol s: symbols) {
				answer.addChild(s);
			}
			return answer;
		}
		
		public Symbol newOr(Symbol...symbols) {
			Symbol answer = createInfixSymbol("||", "&&");
			for (Symbol s: symbols) {
				answer.addChild(s);
			}
			return answer;
		}
		
		public Symbol defineLeftBracket() {
			return getSymbol("(", null, 80, new Symbol("("){			
				@Override
				public Symbol led(Symbol left) {
					children = new ArrayList<Symbol>();
	
				    if (left.getId().equals(".") || left.getId().equals("[")) {
			            this.arity = Arity.TERNARY;
			            children.add(left.getChildren().get(0));  // first = left.first
			            children.add(left.getChildren().get(1));  // second = left.second
			            //this.third = a;
			        } else {
			            this.arity = Arity.BINARY;
		            	children.add(left);  // first = left.first
		            	
			            if ((left.arity == Arity.UNARY || !left.getId().equals("function")) &&
			                    left.arity != Arity.NAME && !left.getId().equals("(") &&
			                    !left.getId().equals("&&") && !left.getId().equals("||") && !left.getId().equals("?")) {
			                //left.error("Expected a variable name.");
			            }
			        }
			        if (!parser.currentSymbol.getId().equals(")")) {
			            while (true) {
			                children.add(expression(0));
			                if (!parser.currentSymbol.getId().equals(",")) {
			                    break;
			                }
			                parser.advance(",");
			            }
			        }
			        parser.advance(")");
			        return this;
				}
				
				/** This is only for organizing things with brackets */
				@Override
				public Symbol nud() {
			        Symbol s = expression(0);
			        advance(")");
			        return s;
				}
				
				@Override
				public String toString() {
					StringBuilder sb = new StringBuilder();
					int more = 0;
					for (Symbol s : children) {
						if (more == 0) {
							sb.append(s.toString());
							// First child = function id
							sb.append("(");
							more++;
							continue;
						}
						if (more > 1) {
							sb.append(", ");
						}
						sb.append(s.toString());
						more++;
					}
					sb.append(")");
					return sb.toString();
				}
			});
		}

		public Symbol definePrefix(String id) {
			return getSymbol(id, null, 0, createPrefixSymbol(id));
		}		

		private Symbol createPrefixSymbol(String id) {
			Symbol s = new Symbol(id, Arity.UNARY){			
				@Override
				public Symbol nud() {
					this.children = new ArrayList<Symbol>();
					this.children.add(this.parser.expression(70));
					return this;
				}
				
				@Override
				public String toString() {
					return this.id+this.children.get(0).toString();
				}
			};
			s.setParser(this);
			return s;
		}
		
		public Symbol newNot(Symbol... symbols){
			Symbol answer = createPrefixSymbol("!");
			for (Symbol s: symbols) {
				answer.addChild(s);
			}
			return answer;
		}

		/** All comparison operations, like >=, ==, ... */
		/*
		public void defineInfixROLD(final String id, final int leftBindingPower) {
			defineInfix(id, leftBindingPower-1);
		}
		*/
		public Symbol defineInfixR(final String id, final String inverse, final int leftBindingPower) {
			return getSymbol(id, inverse, leftBindingPower, createInfixRSymbol(id, inverse));
		}
		
		public Symbol createInfixRSymbol(String id, String inverse) {
			Symbol s = new Symbol(id, Arity.BINARY){			
				@Override
				public Symbol led(Symbol left) {
					Symbol right = this.parser.expression(leftBindingPower);
					children = new ArrayList<Symbol>();
					children.add(left);
					children.add(right);
					return this;
				}
				
				@Override
				public String toString() {
					// We only have two children on a comparison!
					return ""+this.children.get(0).toString()+this.id+this.children.get(1).toString()+"";
				}
			};
			s.setParser(this);
			s.setInverse(inverse);
			return s;
		}
		
		public Symbol newRel(Symbol l, String op, String inv, Symbol r) {
			Symbol answer = createInfixRSymbol(op, inv);
			answer.addChild(l);
			answer.addChild(r);
			return answer;
		}
		
		/** Retrieves the current statement and/or evaluates it's expression */
		public Symbol statement() {
			if (currentSymbol.isStatement()) {
				advance();
				return currentSymbol.std();
			}
			Symbol v;
			v = expression(0);
			
			if (!v.isAssignment() && !v.getId().equals("(")) {
				//throw new ParseException("Bad expression statement.");
		    }
		    advance(";");
		    return v;
		}

		/** Retrieves all statements parsed. */
		public ArrayList<Symbol> statements() {
			ArrayList<Symbol> answer = new ArrayList<Symbol>();
	        while (true) {
	            if (currentSymbol.getId().equals("}") || currentSymbol.getId().equals("(end)")) {
	                break;
	            }
	            Symbol s = statement();
	            if (s != null) {
	                answer.add(s);
	            }
	        }
			return answer;
		}
		
		/** Retrieves the expression from current stream */
		public Symbol expression(int rightBindingPower) {
			Symbol s = currentSymbol;
	        advance();
	        Symbol left = s.nud();
	        while (rightBindingPower < currentSymbol.getLeftBindingPower()) {
	            s = currentSymbol; 
	            advance();
	            left = s.led(left);
	        }
	        return left;
		}
		
		/** 
		 * Main part of the parser. Advances the token stream.
		 * Proceeds to the token sets the current symbol according to it. 
		 */
		public void advance(String id) {
			if (id != null && !currentSymbol.getId().equals(id)) {
				throw new ParseException("Expected '" + id + "', but have '"+currentSymbol.getId()+"'.");
    		}
			if (tokenIndex >= input.size()) {
				currentSymbol = getSymbol("(end)");
				return;
			}
			Token t = input.get(tokenIndex);
			tokenIndex++;
			
			if (t.type.equals("name")) {
				currentSymbol = getSymbol(t.type);
				currentSymbol.setValue(t.value);
			} else if (t.type.equals("operator")) {
				// In this case, we want to check whether operator is defined
				currentSymbol = getSymbol(t.value, null, 0, null);
				if (currentSymbol == null) {
					throw new ParseException("Unknown operator: '"+t.value+"'");
				}
			} else if (t.type.equals("string") || t.type.equals("number")) {
				currentSymbol = getSymbol("(literal)");
				currentSymbol.setValue(t.value);
			} else {
				throw new ParseException("Unexpected token: '"+t.type+", value: "+t.value);
			}

			// Finally, copy the currentSymbol!
			try {
				currentSymbol = (Symbol) currentSymbol.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void advance() {
			advance(null);
		}
		
		
		public ArrayList<Symbol> meldCNFIterated(ArrayList<ArrayList<Symbol>> atoms) {
			// Case: Nothing to iterate over
			ArrayList<Symbol> leftAndAtoms; 
			ArrayList<Symbol> rightAndAtoms; 

			// Default initializer, if the outer list has length 1
			rightAndAtoms = atoms.get(0);
			for (int i = 0; i < atoms.size()-1; i++) {
				leftAndAtoms = atoms.get(i); 
				rightAndAtoms = atoms.get(i+1); 
				Symbol answer = meldCNF(leftAndAtoms, rightAndAtoms);
				rightAndAtoms = answer.getChildren();
				atoms.set(i+1, rightAndAtoms);
			}
			return rightAndAtoms;
		}
		
		public Symbol meldCNF(ArrayList<Symbol> leftAndAtoms, ArrayList<Symbol> rightAndAtoms) {
			Symbol answer = newAnd();
			// "Jeder Kerl tanzt mit jeder Dame"
			for (Symbol l : leftAndAtoms) {
				for (Symbol r: rightAndAtoms) {
					// new disjoint
					Symbol d = newOr();
					d.addChild(l);
					d.addChild(r);
					answer.addChild(d);
				}
			}
			return answer;
		}
		
		public Symbol meldCNF(Symbol s) {
			//System.out.println("=>"+s.getArity()+":"+s.toString());
			String id = s.getId();

			if (s.getArity() == Arity.UNARY && id.equals("!")) {
				if (s.getChildren().get(0).getArity() == Arity.UNARY && s.getChildren().get(0).getId().equals("!")) {
					// Rule of absorbtion: !!a ~ a
					return meldCNF(s.getChildren().get(0).getChildren().get(0));
				}
				// Otherwise: Move ! inwards, flipping && and ||
				// if (s.getChildren().get())
				Symbol answer = s.getChildren().get(0).invert();
				
				if (answer != null) {
					return meldCNF(answer);
				} else {
					return newAnd(newOr(s));
				}
			}

			if (s.getArity() == Arity.BINARY || s.getArity() == Arity.UNARY) {
				// CNF'ize atoms
				return newAnd(newOr(s));
			}
			
			if (s.getArity() == Arity.NARY) {
				if (s.getId().equals("&&")) {
					// Check if all Children are ORs
				}
			}
			
			
			
			if (s.getId().equals("||")) {
				// First case, see helper
				ArrayList<ArrayList<Symbol>> atoms = new ArrayList<ArrayList<Symbol>>();
				for (Symbol child : s.getChildren()) {
					child = meldCNF(child);
					atoms.add(child.getChildren());
				}
				Symbol answer = newAnd();
				answer.children = meldCNFIterated(atoms);
				return answer;
//				return meldCNF(s.getChildren().get(0).getChildren(), s.getChildren().get(1).getChildren());
			} else if (s.getId().equals("&&")) {
				
			}
			return s;
		}

		
		public Symbol transform(Symbol input) {
			Symbol answer = null; 
			// Move all ! inbound
			String id = input.getId();
			if (id.equals("&&")) {
				// 2. Fall => 
				answer = defineInfix("&&", 35);
				ArrayList<Symbol> newChildren = new ArrayList<Symbol>();
				for (Symbol ch: input.getChildren()) {
					// Nach i.V.
					answer.addChild(transform(ch));
				}
				return answer;
			}
			if (id.equals("||")) {
				// 1. Fall => 
				answer = defineInfix("&&", 35);
				ArrayList<Symbol> newChildren = new ArrayList<Symbol>();
				for (Symbol ch: input.getChildren()) {
					// Nach i.V.
					answer.addChild(transform(ch));
				}
				return answer;
			}
			if (id.equals("!")) {
				// 3. Fall 
				answer = defineInfix("&&", 35);
				ArrayList<Symbol> newChildren = new ArrayList<Symbol>();
				for (Symbol ch: input.getChildren()) {
					// Nach i.V.
					answer.addChild(transform(ch));
				}
				return answer;
			}
						
			
			
			if (input.getId().equals("!")) {
				if (input.getChildren().get(0).getId().equals("&&")) {
					answer = defineInfix("||", 35);
					// All child symbols
					ArrayList<Symbol> children = input.getChildren().get(0).getChildren();
					for (Symbol ch: children) {
						Symbol not = definePrefix("!");
						not.addChild(ch);
						answer.addChild(not);
					}
				}
			} else if (input.getId().equals("&&")) {
				return input;
				//return transform(input);
			} else if (input.getId().equals("||")) {
				answer = defineInfix("&&", 35);
				//answer.addChild(new Symbol("s"));
				//return transform(input);
				return answer;
			}
			return answer;
		}
	}
	

	@Test
	public void parserTest() {
//		ArrayList<Token> tokens = new Tokenizer("q < 0 && !(a == 0) && b >= 10 || d < 30").tokenize();
//		ArrayList<Token> tokens = new Tokenizer("!a == 0").tokenize();
//		ArrayList<Token> tokens = new Tokenizer("f(a,1) == 0").tokenize();
//		ArrayList<Token> tokens = new Tokenizer("f() == 0").tokenize();
		ArrayList<Token> tokens = new Tokenizer("a==1&&b==2||c==3").tokenize();
		System.out.println(tokens);
		
		Parser p = new Parser(tokens);
		

		
        //ArrayList<Symbol> s = p.parse();
        p.advance();
        System.out.println(p.expression(0));
        //scope.pop();

        
        Symbol s = p.defineInfix("&&", 60);
        s.addChild(new Symbol("a"));
        s.addChild(new Symbol("b"));
        s.addChild(new Symbol("c"));
        
        Symbol not = p.definePrefix("!");
        not.addChild(s);

        Symbol s1 = p.defineInfix("||", 60);
        s1.addChild(not);
        s1.addChild(new Symbol("d"));

        System.out.println("ORG: "+s1.toString());
        // System.out.println("TRA: "+p.transform(s1).toString());

	
	
	
	
        Symbol o = p.newOr();
        
        Symbol a1 = p.newAnd(new Symbol("a1"), new Symbol("a2"));
        Symbol a2 = p.newAnd(new Symbol("c"));
        Symbol a3 = p.newAnd(new Symbol("d"));
//        Symbol a3 = p.getAnd(new Symbol("c1"), new Symbol("c2"));

        o.addChild(a1);
        o.addChild(a2);
        o.addChild(a3);

        //        a.addChild(new Symbol("b"));
//        a.addChild(new Symbol("c"));
        System.out.println(o.toString());
        Symbol result = p.meldCNF(o);
        
        System.out.println(result.toString());

        System.out.println("IA1:" +p.meldCNF((new Symbol("x"))).toString());
        System.out.println("IA2:" +p.meldCNF(p.newNot(new Symbol("x"))).toString());
        System.out.println("IA3:" +p.meldCNF(p.newNot(p.newNot(new Symbol("x")))).toString());
        System.out.println("IA4:" +p.meldCNF(p.newNot(p.newNot(p.newNot(new Symbol("x"))))).toString());
        System.out.println("IA5:" +p.meldCNF(p.newAnd(p.newAnd(new Symbol("x")))).toString());
        System.out.println("IA6:" +p.meldCNF(p.newRel(new Symbol("x"), "==", "!=", new Symbol("y"))).toString());
        System.out.println("IA7:" +p.meldCNF(p.newNot(p.newRel(new Symbol("x"), "==", "!=", new Symbol("y")))).toString());
        System.out.println("IA8:" +p.meldCNF(p.newNot(p.newAnd(new Symbol("a"), new Symbol("b")))).toString());
	

        
        
        Symbol o2 = p.newOr();
        
        Symbol oa1 = p.newAnd(new Symbol("dgn"), new Symbol("dke"));
        Symbol oa2 = p.newAnd(new Symbol("agn"), new Symbol("ake"));
//        Symbol a3 = p.getAnd(new Symbol("c1"), new Symbol("c2"));

        o2.addChild(oa1);
        o2.addChild(oa2);

        //        a.addChild(new Symbol("b"));
//        a.addChild(new Symbol("c"));
        System.out.println("My: "+o2.toString());
        Symbol result2 = p.meldCNF(o2);
        System.out.println("MyCNF: "+result2.toString());
        

	}
	




	
	
	

}
