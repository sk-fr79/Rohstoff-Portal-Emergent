package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;


public class OldVisitorTest {
	public interface IConditionVisitor {
		void visit(ICondition c);
		void visit(AndCondition c);
		void visit(TermCondition c);
//		void visit(ICondition c);
//		void visit(ICondition c);
	}
	
	
	/*
	public interface IConditionEvaluator extends IConditionVisitor {
		void visit(ICondition c);
		void visit(AndCondition c);
		void visit(TermCondition c);
	}
	*/

	public interface IBinaryConditionVisitor {
		void visit(ICondition left, ICondition right);
	}
	
	public interface ICondition {
		public void accept(IConditionVisitor v);
		//Object eval(IConditionVisitor v);
		//boolean match();
	}
	
	public abstract class ACondition implements ICondition {
/*		public boolean accept(IConditionEvaluator v) {
			return v.visit(this);
		}*/
		//String stringRepresentation();
	}
	
	public interface IVariable extends ICondition {} 

	public abstract class ABinaryCondition extends ACondition {
		protected ICondition left;
		protected ICondition right;
	}
	
	/** Multiple child conditions */
	public abstract class AMultiCondition extends ACondition implements ICondition {
		protected ArrayList<ICondition> children = new ArrayList<ICondition>();
		protected String opString;
		
		public AMultiCondition(final ICondition... c) {
			for (int i = 0, j = c.length; i < j; i++ ) {
				addCondition(c[i]);
			}
		}

		public ICondition addCondition(ICondition c) {
			children.add(c);
			return c;
		}
		
		public ArrayList<ICondition> getChildren() {
			return children;
		}
	
		
		public String toString() {
			StringBuilder b = new StringBuilder();
			b.append("(");
			boolean first = true;
			for (ICondition c: children) {
				if (!first) {
					b.append(" ");
					b.append(this.opString);
					b.append(" ");
				}
				b.append(c.toString());
				first = false;
			}
			b.append(")");
			return b.toString();
		}
		
		public void accept(IConditionVisitor v) {
			for (ICondition c: children) {
				c.accept(v);
			}
			v.visit(this);
		}
	}
	
	
	public class AndCondition extends AMultiCondition {
		public AndCondition(ICondition... c) { 
			super(c);
			opString = "&&";
		}

		

		
//		public boolean match() {
//			for (ICondition c: children) {
//			//	if (c.match() == false) return false;
//			}
//			return true;
//		}
	}

	public class OrCondition extends AMultiCondition {
		public OrCondition(ICondition... c) { 
			super(c);
			opString = "||";
		}

//		public boolean match() {
//			for (ICondition c: children) {
//			///	if (c.match() == true) return true;
//			}
//			return false;
//		}

	}
	/*
	public class XOrCondition extends ABinaryCondition implements ICondition {
		public XOrCondition(final ICondition left, final ICondition right) {
			this.left = left;
			this.right = right; 
		}

		@Override
		public void accept(IConditionVisitor v) {
			left.accept(v);
			v.visit(this);
			right.accept(v);
		}
	}
	*/
	
	
	public class TermCondition extends ACondition implements ICondition {
		protected String left;
		protected String op;
		protected String right;

		public TermCondition(IVariable variable) {
			
		}
		public TermCondition(final String l, final String op, final String r) {
			this.left = l;
			this.op = op;
			this.right = r;
		}
/*		public TermCondition(Constant constant) {
			
		} */
	
		public String getLeft() {
			return left;
		}
		public String getRight() {
			return right;
		}
		public String getOP() {
			return op;
		}
		
		public String toString() {
			return left.toString()+" "+op+" "+(right != null ? right.toString() : "NULL");
		}
		
		public void accept(IConditionVisitor v) {
			v.visit(this);
		}

	}
	
	public class EqualsCondition extends TermCondition {

		public EqualsCondition(String l, String r) {
			super(l, "EQ", r);
		}
		
//		public 
		
	}
	public class NotNullCondition extends TermCondition {

		public NotNullCondition(String l) {
			super(l, "NN", null);
		}
		
//		public 
		
	}
	
	public static class TermConditionFactory {
		public static TermCondition createFromDBEntry(HashMap<String, Object> e) {
			String op = (String)e.get("CONDITION_OP");
			OldVisitorTest aft = new OldVisitorTest();
			if (op.equals("EQ")) {
				return aft.new EqualsCondition(
						(String)e.get("CONDITION_LEFT"), 
						(String)e.get("CONDITION_RIGHT")
				);
			}
			if (op.equals("NN")) {
				return aft.new NotNullCondition(
						(String)e.get("CONDITION_LEFT")
				);
			}
			return null;
		}
	}

	public class StringVariable implements IVariable {
		private String s; 
		public StringVariable(String s) {
			this.s = s;
		}

		@Override
		public void accept(IConditionVisitor v) {
			v.visit(this);
		}

	}
	
	
	public class XStringer implements IConditionVisitor {

		@Override
		public void visit(ICondition c) {
//	        System.out.println("Visiting " + c.stringRepresentation() + "!");
		}

		@Override
		public void visit(AndCondition c) {
			// TODO Auto-generated method stub
	/*		StringBuilder b = new StringBuilder();
			b.append("(");
			boolean first = true;
			for (ICondition c: children) {
				if (!first) {
					b.append(" ");
					b.append(this.opString);
					b.append(" ");
				}
				b.append(c.stringRepresentation());
				first = false;
			}
			b.append(")"); */
		//	return b.toString();

			
		}

		@Override
		public void visit(TermCondition c) {
			// TODO Auto-generated method stub
			System.out.println(c.getLeft()+" "+c.getOP()+" "+c.getRight());

			
		}
		

		
	}


	
	@Test
	public void testCondition() throws SQLException, myException {
		TermCondition t1 = new TermCondition("a", "=", "1");
		TermCondition t2 = new TermCondition("b", "=", "2");
		TermCondition t3 = new TermCondition("c", "=", "3");

		OrCondition a1 = new OrCondition(t1, t2);

		AndCondition a0 = new AndCondition(a1, t3);
		
		//a0.accept(new Stringer());
//		Evaluator e0 = new Evaluator(null);
//		System.out.println("EV?" +e0.evaluate(a0));

		
		
		// This is how the conditions are created; first, query the database
		ArrayList<HashMap<String, Object>> result = DBUtil.select(
				"SELECT o.id_filter_and, condition_left, condition_op, condition_right "
				+ "FROM jt_filter_or o JOIN jt_filter_and a ON (a.id_filter_and = o.id_filter_and) "
				+ "ORDER BY o.id_filter_and ASC"
		);

		AndCondition a = null;
		OrCondition o = null; 
		int andId = 0;
		for (HashMap<String, Object> r : result) {
			if (andId != ((BigDecimal)r.get("ID_FILTER_AND")).intValue()) {
				a = new AndCondition();
				a.addCondition(o);
				o = new OrCondition();
				andId = ((BigDecimal)r.get("ID_FILTER_AND")).intValue();
			}

			TermCondition ttmp = TermConditionFactory.createFromDBEntry(r);
			o.addCondition(ttmp);
		}
		if (a == null) {
			a = new AndCondition();
		} 
		if (o != null) {
			a.addCondition(o);
		}
		
//		System.out.println(a.toString());
		
//		a.accept(new Evaluator());

		
		ArrayList<HashMap<String, Object>> dataset = DBUtil.returnEntriesRaw(null, 0);
		for (HashMap<String, Object> d : dataset) {
			System.out.println(d);
			Evaluator e = new Evaluator(d);
			System.out.println("EVALUATOR? "+e.evaluate(a));
			return;
		}
		
	
		

	}
	
	public class Traverser {
		public boolean traverse(ICondition c) {
			if (c instanceof AndCondition) {
		        for (ICondition child : ((AndCondition)c).getChildren()) {
		        	this.traverse(child);
		        }
			}
			return false;
		}
	}
	
	public class Evaluator implements IConditionVisitor {
		private HashMap<String, Object> data;
		private HashMap<Object, Boolean> evaluated = new HashMap<Object, Boolean>();
		
		public Evaluator(HashMap<String, Object> d) {
			this.data = d;
		}
		
		public boolean evaluate(ICondition c) {
			c.accept(this);
			return evaluated.get(c);
		}
		
		public void visit(EqualsCondition c) {
	        System.out.println("Evaluating EqualsCondition " + c.toString() + "!"+ (c.hashCode()));
			this.evaluated.put(c, false);
			if (data.get(c.getLeft().toUpperCase()) != null) {
				// The specified key (left handside) exists in data
				if (data.get(c.getLeft().toUpperCase()).equals(c.getRight())) {
					// The specified data matches the right handside
					this.evaluated.put(c, true);
				} 
			}
			//return false;
		}

		public void visit(NotNullCondition c) {
	        System.out.println("Evaluating NotNullCondition " + c.toString() + "!"+ (c.hashCode()));
			this.evaluated.put(c, false);
			if (data.get(c.getLeft().toUpperCase()) != null) {
				// The specified key (left handside) exists in data
				if (data.get(c.getLeft().toUpperCase()).equals(c.getRight())) {
					// The specified data matches the right handside
					this.evaluated.put(c, true);
				} 
			}
			//return false;
		}

		public void visit(AndCondition c) {
	        System.out.println("Evaluating AND " + c.toString() + "!");
	        for (ICondition child : c.getChildren()) {
	        	System.out.println("  -> looking for child: "+child.toString());
	        	if (evaluated.get(child) == false) {
	        		evaluated.put(c, false);
	        		System.out.println(" => false");
	        		return;
	        	}
	        }
    		evaluated.put(c, true);
    		
		}

		public void visit(OrCondition c) {
	        System.out.println("Evaluating OR " + c.toString() + "!");
/*			if (data.get(c.getLeft().toUpperCase()) != null) {
				//op = data.get(c2.getLeft().toUpperCase()) != null
				if (data.get(c.getLeft().toUpperCase()).equals(c.getRight())) {
					System.out.println("Matches lefthandside");
				}
			} */
	        for (ICondition child : c.getChildren()) {
	        	if (evaluated.get(child) == true) {
	        		evaluated.put(c, true);
//	        		System.out.println("False!");
	        		return;
	        	}
	        }
//    		System.out.println("true!");
	        evaluated.put(c, false);
	        
		}

		@Override
		public void visit(ICondition c) {
			if (c instanceof OrCondition) {
				this.visit((OrCondition)c);
			} else if (c instanceof AndCondition) {
				this.visit((AndCondition)c);
			} else if (c instanceof EqualsCondition) {
				this.visit((EqualsCondition)c);
			} else if (c instanceof NotNullCondition) {
				this.visit((NotNullCondition)c);
			} else {
				System.out.println("Evaluating ICondition " + c.toString() + "!");
			}

			// TODO Auto-generated method stub
//	        System.out.println("Evaluating ICond " + c.stringRepresentation() + "!");

		}

		@Override
		public void visit(TermCondition c) {
			visit((ICondition)c);
			// TODO Auto-generated method stub
			//System.out.println("EEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRIIIIIR!");
		}


	}	
}
