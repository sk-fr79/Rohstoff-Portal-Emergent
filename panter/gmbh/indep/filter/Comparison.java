package panter.gmbh.indep.filter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;

// import static panter.gmbh.indep.filter.Constants.*;
/**
 * This enumeration names and implements the comparison possibilities
 * for data given on the left with the right handside. It's string IDs
 * correspond to the possible values in the CONDITION_TYPE column of the
 * JT_FILTER_OR table, if an alternative ID (second argument to constructor)
 * is given, this is taken if a comparison is printed out to the user
 * The implemented {@code compare} methods are passed that data in 
 * the {@link ComparisonExpression} class upon being visited by
 * an appropriate visitor.
 *  
 * It is used for comparing for equality of <tt>left</tt> and 
 * <tt>right</tt> (they are equal if the method yields <tt>0</tt>), for
 * comparing order (lesser or greater, by yielding <tt>-1</tt> or 
 * <tt>1</tt> or for a containment of the left handside in the right 
 * argument (<tt>-1</tt> meaning no containment, anything else otherwise).
 *
 */
public enum Comparison implements Comparator<Object> {
	/** Equals */
	EQ(_FilterConstants.COMP.EQ) {
		public int compare(Object left, Object right) {
			if (left == null || right == null)
				return 1;
			
			right = rightHelper(left, right);
			//System.out.println("left="+left+", "+left.getClass());
			//System.out.println("right="+right+", "+right.getClass());

			// This test is to fix an equals-comparison where actually an IN 
			// operation should be there (easy error). If the left is a scalar
			// and the right is a Collection, it checks if left is in right.
			if (right instanceof Collection && !(left instanceof Collection)) {
				return ((Collection) right).contains(left) ? 0 : 1;
			}
			
			if (left instanceof Comparable && right instanceof Comparable) {
				return (((Comparable)left).compareTo((Comparable)right));
			}
			return (left.equals(right) ? 0 : 1);
		}
	},
	/** Greater-than */
	GT(_FilterConstants.COMP.GT) {
		public int compare(Object left, Object right) {
			if (left == right || left == null || left.equals(right)) {
				return 1;
			}
			right = rightHelper(left, right);
			return (((Comparable) left).compareTo(right) > 0 ? 0 : 1);
		}
	},
	/** Less than */
	LT(_FilterConstants.COMP.LT) {
		public int compare(Object left, Object right) {
			if (left == right || left == null || left.equals(right)) {
				return 1;
			}
			right = rightHelper(left, right);
			return (((Comparable) left).compareTo(right) < 0 ? 0 : 1);
		}
	},
	/** Greater-or-egals */
	GEQ(_FilterConstants.COMP.GEQ) {
		public int compare(Object left, Object right) {
			if (left == right || left == null || left.equals(right)) {
				return 1;
			}
			right = rightHelper(left, right);
			return (((Comparable) left).compareTo(right) >= 0 ? 0 : 1);
		}
	},
	/** Less-or-equals */
	LEQ(_FilterConstants.COMP.LEQ) {
		public int compare(Object left, Object right) {
			if (left == right || left == null || left.equals(right)) {
				return 1;
			}
			right = rightHelper(left, right);
			return (((Comparable) left).compareTo(right) <= 0 ? 0 : 1);
		}
	},
	/** Contains the string */
	CONTAINS(_FilterConstants.COMP.CONTAINS) {
		public int compare(Object left, Object right) {
			right = rightHelper(left, right);
			if (left instanceof String && right instanceof String) {
				String s = (String) left;
				return (s.contains((String) right) ? 0 : 1);
			}
			return 1;
		}
	},
	/** Starts with String */
	STARTSWITH(_FilterConstants.COMP.STARTSWITH) {
		public int compare(Object left, Object right) {
			right = rightHelper(left, right);
			if (left instanceof String && right instanceof String) {
				String l = (String) left;
				String r = (String) right;
				return (l.startsWith(r) ? 0 : 1);
			}
			return 1;
		}
	},
	/** Ends with String */
	ENDSWITH(_FilterConstants.COMP.ENDSWITH) {
		public int compare(Object left, Object right) {
			right = rightHelper(left, right);
			if (left instanceof String && right instanceof String) {
				String l = (String) left;
				String r = (String) right;
				return (l.endsWith(r) ? 0 : 1);
			}
			return 1;
		}
	},
	/** Is null */
	NULL(_FilterConstants.COMP.NULL) {
		public int compare(Object left, Object right) {
			return (left == null ? 0 : 1);
		}
	},
	/** Is in (a set) */
	IN(_FilterConstants.COMP.IN) {
		public int compare(Object left, Object right) {
			right = rightHelper(left, right);

			// This test is to fix an IN-comparison where actually an EQUALS 
			// operation should be there (easy error). If the left is a scalar
			// and the right is a no Collection, it checks if left equals right.
			if (!(right instanceof Collection) && left != null) {			
				if (left instanceof Comparable && right instanceof Comparable) {
					return (((Comparable)left).compareTo((Comparable)right));
				}
				return (left.equals(right) ? 0 : 1);
			}

			
			if (!(right instanceof Collection) || right == null)
				return 1;
			return ((Collection) right).contains(left) ? 0 : 1;
		}
	},
	
	EXISTS(_FilterConstants.COMP.EXISTS) {
		@Override
		public int compare(Object left, Object right) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	},
	IS(_FilterConstants.COMP.IS) {
		@Override
		public int compare(Object left, Object right) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	},
	LIKE(_FilterConstants.COMP.LIKE) {
		@Override
		public int compare(Object left, Object right) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	},	
	;

	private final String id;
	private String alternateId;

	Comparison(Enum id) {
		this.id = id.toString();
	}

	Comparison(String id) {
		this.id = id;
	}

	Comparison(_FilterConstants.COMP id) {
		this.id = id.toString();
		this.alternateId = id.getSqlId();
	}
	
	Comparison(String id, String altId) {
		this.id = id;
		this.alternateId = altId;
	}

	String getId() {
		return (alternateId != null ? alternateId : id);
	}

	public abstract int compare(Object left, Object right);

	/**
	 * Helps to adjust the right handside if:
	 * - it is a number
	 * - it has trailing and leading ' or " (commas) 
	 * @param left
	 * @param right
	 * @return
	 */
	private static Object rightHelper(Object left, Object right) {
		if (right instanceof String) {
			String s = (String)right;
			
			if (s.length() >= 2 && s.charAt(0) == (s.charAt(s.length()-1)) && (s.substring(0,1).equals("\"") || s.substring(0, 1).equals("'"))) {
				// Cut of leading/trailing " and '
				right = s.substring(1, s.length()-1);
			}
		}
		if (left instanceof Number && right instanceof String) {
			if (left instanceof BigDecimal) {
				right = BigDecimal.valueOf(Float.parseFloat((String)right));
			} else if (left instanceof BigInteger) {
				right = BigInteger.valueOf(Long.parseLong((String)right));
			} else {
				right = Float.parseFloat((String)right);
			}
		}
		return right;
	}

/*	private static Object expressionHelper(Object left, Object right) {

		return right;
	}
	*/
}
