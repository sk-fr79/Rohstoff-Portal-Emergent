package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.Rule;
import panter.gmbh.indep.filter.RuleFactory;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;

/** 
 * Static helpers for accounts. Retrieves list from the database and models them
 * into conveniance objects (Maps and Lists).
 * @author nils
 *
 */
public class AccountUtils {
	
	/** The mapping of AccountIDs (BigDecimals, from DB prim. keys) to stringy FIBU accounts */
	private static Map<BigDecimal, String> getAccountNumbers() throws SQLException, myException {
		// Obtain all account numbers
		ArrayList<HashMap<String, Object>> accounts = DBUtil.getAccounts();
		
		/** This maps the accountIds (integer) to 4-digit FIBU account numbers (1000/2001) */
		Map<BigDecimal, String> accountNumbers = new HashMap<BigDecimal, String>(accounts.size()); 

		Iterator<HashMap<String, Object>> ait = accounts.iterator();
		while (ait.hasNext()) {
			HashMap<String, Object> current = ait.next();
			// As of 2015-09-25, account numbers may also be "var", meaning their actual value
			// is determined by the data itself
			String konto = (String)current.get("KONTO");
			accountNumbers.put(((BigDecimal)current.get("ID_FIBU_KONTO")), konto);
		}
		return accountNumbers;
	}

	/** 
	 * The mapping of AccountIDs (BigDecimals, from DB prim. keys) to variables;
	 * that is where the account number (KONTO field) equals "var", which means that
	 * the description field (BESCHREIBUNG) contains the name of the column in which
	 * the actual account number resides in. 
	 */
	private static Map<BigDecimal, String> getAccountVariables() throws SQLException, myException {
		// Obtain all account numbers
		ArrayList<HashMap<String, Object>> accounts = DBUtil.getAccounts();
		
		/** This maps the accountIds (integer) to 4-digit FIBU account numbers (1000/2001) */
		Map<BigDecimal, String> accountVariables = new HashMap<BigDecimal, String>(accounts.size()); 

		Iterator<HashMap<String, Object>> ait = accounts.iterator();
		while (ait.hasNext()) {
			HashMap<String, Object> current = ait.next();
			String konto = (String)current.get("KONTO");
			if (konto.equals("var")) {
				accountVariables.put(((BigDecimal)current.get("ID_FIBU_KONTO")), (String)current.get("BESCHREIBUNG"));
			}
		}
		return accountVariables;
	}
	
	/** Creates and returns all active account Rules (including their filter) */
	public static ArrayList<AccountRule> getAccountRules()
			throws SQLException, myException {
		
		Map<BigDecimal, String> accountNumbers = getAccountNumbers();
		Map<BigDecimal, String> accountVariables = getAccountVariables();

		ArrayList<AccountRule> accountRules = new ArrayList<AccountRule>();

		// Now we loop through all rules 
		ArrayList<HashMap<String, Object>> rules = DBUtil.getAccountRules();
		
		for (HashMap<String, Object> e : rules) {
			int idFilter = ((BigDecimal)e.get("ID_FILTER")).intValue();
			AccountRule ar = new AccountRule();
			ar.setDescription((String)e.get("KOMMENTAR"));
			
			INodeElement r = RuleFactory.getRule(idFilter);
			if (r != null) {
				ar.setINodeElement(r);
				
				BigDecimal accountId = (BigDecimal)e.get("ID_FIBU_KONTO");
				
				// Sets the id of JT_FIBU_KONTO, e.g. the id of the fibu account
				ar.setAccountId(accountId);
				
				// Sets the id of the rule, for debug purposes
				ar.setRuleId((BigDecimal)e.get("ID_FIBU_KONTENREGEL_NEU"));

				// Sets the 'abcd' four digit fibu account number, from the mapping
				ar.setAccountNumber(accountNumbers.get(accountId));

				// Sets the variable to look for the four digit fibu account number, if applicable
				if (accountVariables.get(accountId) != null) {
					ar.setAccountVariable(accountVariables.get(accountId));
				}

				accountRules.add(ar);
			}
		}
		return accountRules;
	}
}
