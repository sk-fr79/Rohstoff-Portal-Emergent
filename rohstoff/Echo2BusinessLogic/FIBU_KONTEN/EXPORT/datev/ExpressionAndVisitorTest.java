package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.AndExpression;
import panter.gmbh.indep.filter.EqualsExpression;
import panter.gmbh.indep.filter.ExpressionPrintVisitor;
import panter.gmbh.indep.filter.ExpressionValidatorVisitor;
import panter.gmbh.indep.filter.Filter;
import panter.gmbh.indep.filter.GreaterThanExpression;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.InExpression;
import panter.gmbh.indep.filter.NotExpression;
import panter.gmbh.indep.filter.OrExpression;
import panter.gmbh.indep.filter.PrintVisitResult;
import panter.gmbh.indep.filter.RuleFactory;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;

public class ExpressionAndVisitorTest {
	/* @Test */
	public void visitorTest() {

		INodeElement rule = new OrExpression(new EqualsExpression("a", "1"),
				new EqualsExpression("b", "2"), new AndExpression(
						new EqualsExpression("c", "3"), new EqualsExpression(
								"d", "4")));

		PrintVisitResult r = (PrintVisitResult) rule
				.accept(new ExpressionPrintVisitor());
		System.out.println(r.getText());
		System.out.println("-------------------------");

	}

	@Test
	public void expressionFromDBTest() throws SQLException, myException {
		INodeElement rule = RuleFactory.fromDB(1);
		PrintVisitResult r = (PrintVisitResult) rule
				.accept(new ExpressionPrintVisitor());
		System.out.println(r.getText());
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		ArrayList<HashMap<String, Object>> dataset = DBUtil.returnEntriesRaw(
				null, 0);
		for (HashMap<String, Object> d : dataset) {
			System.out.println(d);
			//d.put("LAENDERCODE", null);
			Filter f = new Filter(d);
			f.setDebug(true);
			System.out.println(f.matches(rule));

			return;
		}
	}

	/* @Test */
	public void visitorTest2() throws SQLException, myException {
		ArrayList<String> l = new ArrayList<String>();
		l.add("DE");
		l.add("EN");
		l.add("D");

		BigDecimal x = BigDecimal.valueOf(1010921);
		System.out.println("BIGDEC=" + x.toString());
		// System.out.println("STR="+("1".compareTo("1")));

		INodeElement rule = new AndExpression(new OrExpression(
				new EqualsExpression("DIENSTLEISTUNG", "Y"), new InExpression(
						"LAENDERCODE", l)), new GreaterThanExpression(
				"EINZELPREIS_RESULT", "100"));

		INodeElement rule2 = new AndExpression(new EqualsExpression(
				"DIENSTLEISTUNG", "N"), new OrExpression(new NotExpression(
				new EqualsExpression("LAENDERCODE", "DE")),
				new EqualsExpression("LAENDERCODE", "D")));

		PrintVisitResult r = (PrintVisitResult) rule2
				.accept(new ExpressionPrintVisitor());
		System.out.println(r.getText());
		System.out.println("================================");

		ArrayList<HashMap<String, Object>> dataset = DBUtil.returnEntriesRaw(
				null, 0);
		for (HashMap<String, Object> d : dataset) {
			System.out.println(d);
			Filter f = new Filter(d);
			f.setDebug(true);
			System.out.println(f.matches(rule));

			return;
		}

	}
}
