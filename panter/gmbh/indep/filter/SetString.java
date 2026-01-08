package panter.gmbh.indep.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import panter.gmbh.indep.dataTools.query.Query;

/**
 * Creates a Collection of Objects from a String separated with ',' by splitting
 * it up and trimming the parts
 * 
 * @author nils
 * 
 */
public class SetString extends ArrayList<Object> {
	protected String orig;
	
	public String getOrig() {
		return orig;
	}

	public SetString(String s) {
		this.orig = s;
		construct(s, ",");
	}

	public SetString() {

	}

	public SetString(Object[] array) {
		this.clear();
		for (int i = 0, j = array.length; i < j; i++) {
			this.add(array[i]);
		}
	}

	public SetString(List l) {
		this.clear();
		this.addAll(l);
	}

	public void rebuild(String s) {
		construct(s, ",");
	}

	private void construct(String s, String split) {
		this.clear();
		if (s == null || s.trim().equals("")) {
			return;
		}
		this.addAll(parseLine(s, split));
	}

	/**
	 * returns a row of values as a list returns null if you are past the end of
	 * the input stream
	 */
	public static Collection<Object> parseLine(String line, String split) {
		int i = 0;
		int ch = line.charAt(i);
		while (ch == '\r') {
			// ignore linefeed chars wherever, particularly just before end of
			// file
			ch = line.charAt(i);
			i++;
		}
		if (ch < 0) {
			return null;
		}
		Collection<Object> store = new ArrayList<Object>();
		StringBuffer curVal = new StringBuffer();
		char inquotes = 0;
		boolean started = false;
		while (ch >= 0 && i < line.length()) {
			ch = line.charAt(i);

			if (inquotes == 0) {
				// We are not yet in a quoted area
				if (ch == '\"' || ch == '\'') {
					// Open up quote
					inquotes = (char)ch;
				} else {
					if (ch == split.charAt(0)) {
						// This is a split
						store.add(curVal.toString());
						curVal = new StringBuffer();
					} else {
						// This is a normal char
						if (ch != ' ' && ch != '\n') {
							curVal.append((char) ch);
						}
					}
				}
			} else {
				// We are in a quoted area
				if ((ch == '\"' || ch == '\'') && ch == inquotes) {
					// this is the corresponding endquote
					inquotes = 0;
				} else {
					curVal.append((char) ch);
				}
			}
			i++;
		}
		if (curVal.toString().toUpperCase().equals("NULL")) {
			store.add(null);
		} else {
			store.add(curVal.toString());
		}
		return store;
	}

	public Collection<Object> getSet() {
		return this;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		int i = 0;
		for (Object e : this) {
			i++;
			if (i > 1) {
				sb.append(", ");
			}
			sb.append(Query.quoteLiteral(e));
		}
		sb.append(")");
		return sb.toString();
	}
}
