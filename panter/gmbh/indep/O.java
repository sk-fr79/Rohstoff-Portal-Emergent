/**
 * 
 */
package panter.gmbh.indep;

import java.util.Collection;

import panter.gmbh.BasicInterfaces.IF__Reflections;

/**
 * @author martin
 *
 */
public class O implements IF__Reflections {
	
	public static boolean isAllNull(Object...  objs) {
		boolean ret = true;
		
		for (Object o: objs) {
			if (o!=null) {
				ret =false;
			}
		}
		return ret;
	}

	
	public static boolean isOneNull(Object...  objs) {
		boolean ret = false;
		
		for (Object o: objs) {
			if (o==null) {
				return true;
			}
		}
		return ret;
	}
	
	public static boolean isNoOneNull(Object...  objs) {
		boolean ret = true;
		
		for (Object o: objs) {
			if (o==null) {
				ret =false;
			}
		}
		return ret;
	}

	
	public static <T> T NN(T test, T ersatz) {
		if (test == null) {
			return ersatz;
		}
		
		return test;
	}


	public static boolean isNull(Object o) {
		return (o==null);
	}

	public static boolean isNotNull(Object o) {
		return (o!=null);
	}

	
	/**
	 * 
	 * @param testCollection
	 * @return true when col is null or empty
	 */
	public static <T extends Collection<V>,V> boolean isEmpty(T testCollection) {
		if ( testCollection == null || ((Collection<V>) testCollection).size()==0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param testCollection
	 * @return false when col is ( !! null and  size()>0)
	 */
	public static <T extends Collection<V>,V> boolean isFull(T testCollection) {
		return !O.isEmpty(testCollection);
	}
	

	
	/**
	 * 
	 * @param testCollection
	 * @return singlar member, when size of collection is exact 1, in all other variants null 
	 */
	public static <T extends Collection<V>,V> V getSingleMember(T testCollection) {
		V ret = null;
		if (testCollection!=null && testCollection.size()==1) {
			for (V v: testCollection) {
				ret=v;
			}
		}
		return ret;
	}

	
	public static  <T extends Comparable<T>> boolean equals(T t1, T t2) {
		if (t1==null && t2 == null) {
			return true;
		} else if (t1 != null && t2==null) {
			return false;
		} else if (t2 != null && t1==null) {
			return false;
		} else {
			return t1.equals(t2);
		}
	}
	
	
	public static  <T extends Comparable<T>> boolean notEquals(T t1, T t2) {
		return !O.equals(t1, t2);
	}
	
	
	
	
	@SafeVarargs
	public static <T> T coalesce(T... firstNotNull) {
		for (T ob: firstNotNull) {
			if (ob!=null) {
				return ob;
			}
		}
		return (T)null;
	}

	
}
