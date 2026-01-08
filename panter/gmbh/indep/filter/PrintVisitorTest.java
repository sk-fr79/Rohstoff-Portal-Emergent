package panter.gmbh.indep.filter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;
import junit.framework.TestCase;

public class PrintVisitorTest extends TestCase {
	public void testVisitor() throws SQLException, myException {
		ArrayList<HashMap<String, Object>> r = DBUtil.getFilter(1004);
		for (HashMap<String, Object> o : r) {
			System.out.println(o);
		}
		
		INodeElement i = RuleFactory.fromResult(r);
		System.out.println(i.toString());
	}
}