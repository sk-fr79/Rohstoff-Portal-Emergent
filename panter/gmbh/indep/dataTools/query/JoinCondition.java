package panter.gmbh.indep.dataTools.query;

import panter.gmbh.indep.dataTools.MyDBToolboxQueryUtils;
import panter.gmbh.indep.dataTools.query.Query.ToStringMode;
import panter.gmbh.indep.filter.AndExpression;
import panter.gmbh.indep.filter.ExpressionPrintSQLVisitor;
import panter.gmbh.indep.filter.PrintVisitResult;

/** 
 * A join condition: The class extends {@link AndExpression}, because it also
 * holds conditions (which determine the type of the join, which is usually 
 * provided by an ON clause in an SQL statement: Theta-Join, equi-join), and
 * it also holds additional information on the type of the join determined by
 * the SQL syntax: Left/Right [Full/Outer] Join etc.
 * @author nils
 */
class JoinCondition extends AndExpression implements SQLPart {
	private String table;
	private String correlationName;
	private Join type;
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getCorrelationName() {
		return correlationName;
	}
	public void setCorrelationName(String correlationName) {
		this.correlationName = correlationName;
	}
	public Join getType() {
		return type;
	}
	public void setType(Join type) {
		this.type = type;
	}
	
	public String toString(ToStringMode mode) {
		ExpressionPrintSQLVisitor epsv = new ExpressionPrintSQLVisitor(mode);
		// In the ON clauses, the right handsides are not deemed to be literals
		// rather than also identifiers, because there are plenty more equi-
		// joins on two or more tables than joins based on actual values.

		epsv.setQuoteRightHandsidesAsLiterals(false);
		PrintVisitResult r = (PrintVisitResult) this.accept(epsv);
		StringBuffer sb = new StringBuffer();
		sb.append(" ");
		if (this.type == Join.LEFT) {
			sb.append("LEFT ");
		}
		if (this.type == Join.RIGHT) {
			sb.append("RIGHT ");
		}
		if (this.type == Join.FULL) {
			sb.append("FULL ");
		}
		TID tmpTID = new TID(correlationName, table);
		sb.append("JOIN ");
		sb.append(tmpTID.toStringWithAlias(mode));
		//TODO: This is very dependent on the JOIN type
		sb.append(" ON ");
		sb.append(r.getText());
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return toString(ToStringMode.REGULAR);
	}
	@Override
	public String toStringSubstituted() {
		return toString(ToStringMode.SUBSTITE);
	}
}
