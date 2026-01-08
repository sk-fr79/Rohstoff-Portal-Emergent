package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.sql.SQLException;
import java.math.BigDecimal; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.filter.ComparisonExpression;
import panter.gmbh.indep.filter.ExpressionContainsVisitor;
import panter.gmbh.indep.filter.Filter;
import panter.gmbh.indep.filter.INodeElement;
import panter.gmbh.indep.filter.OrExpression;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportBuchung;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportBuchung.Vorgang;
import panter.gmbh.indep.filter.AndExpression;

/**
 * The AccountFinder is used to find accounts for a position on an invoice.
 * The invoice position is usually computed in {@see DBUtil} class, being a
 * join from jt_vkopf_rg with jt_vpos_rg with various other tables joined
 * to it, and passed to an {@see ExportBuchung} for handy usage. In short,
 * the AccountFinder initializes it self with certain rules (actually
 * filters), and takes such an ExportBuchung which is thereinafter assigned
 * an account number.  
 * 
 * @author nils
 *
 */
public class AccountFinder {
	/** All rules to apply to data to be exported, in order to assign account numbers */
	protected ArrayList<AccountRule> accountRules = new ArrayList<AccountRule>();
	
	/** The filter, which will match each export touple against each account rule */
	private Filter filter;
	
	/** Whether or not we debug this */
	private boolean debug = false;

	/**
	 * The AccountFinder will extract the data from JT_FIBU_KONTENREGEL and
	 * produce cached versions for fast and easy hash access.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws myException 
	 */
	public AccountFinder() throws SQLException, myException {
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void init() throws SQLException, myException {
		init(false);
	}
	
	public void init(boolean test) throws SQLException, myException {
		this.accountRules = AccountUtils.getAccountRules();
		this.filter = new Filter();
	}
	

	private ExportBuchung lastBuchung;

	/** The number of Buchungen that changed due to their signum ("Vorzeichen") */
	private int alteredBuchungen = 0;
	
	/**
	 * Finds an {@link AccountRule} to be applied to an entry and returns 
	 * it, assigning it the real FIBU account Number as it is found.
	 * @param entry
	 * @return the matching {@link AccountRule} 
	 * @throws DatevExportException 
	 */
	public AccountRule matchAndAssignAccountNumbers(HashMap<String, Object> dbEntry) throws DatevExportException {
		return matchAndAssignAccountNumbers(new ExportBuchung().fromDBEntry(dbEntry), 0);
	}

	public AccountRule matchAndAssignAccountNumbers(ExportBuchung entry) throws DatevExportException {
		return matchAndAssignAccountNumbers(entry, 0);
	}
	
	private StringBuffer debugBuffer = new StringBuffer();
	public void debug(String s) {
		debugBuffer.append(s);
		debugBuffer.append('\n');
	}
	public void flushDebug() {
		if (debugBuffer != null) {
			debugBuffer = null;
			debugBuffer = new StringBuffer();
		}
	}
	
	public String getDebugBuffer() {
		String answer = debugBuffer.toString();
		flushDebug();
		return answer;
	}

	/**
	 * Finds the rule that is applicable to the provided {@param entry}, and assigns the 
	 * entry the appropriate account numbers
	 */
	public AccountRule matchAndAssignAccountNumbers(ExportBuchung entry, int debugRule) throws DatevExportException {
		lastBuchung = entry;
		
		// Set the Debitor/Kreditor to book this onto
		entry.setKonto(findKreditorOrDebitorAccount(entry));

		// Then find the Gegenkonto!

		// The filter needs the raw dataset
		filter.setData(entry.getDBEntry());
		filter.setDebug(this.debug);

		/** Reference to the applicable accountRule */
		AccountRule found = null;
		
		// Now we loop through all rules 
		Iterator<AccountRule> ari = accountRules.iterator();
		debug("");
		debug("FINDING RULE FOR ========================================================================================================");
		debug(entry.toString());
		
		while (ari.hasNext()) {
			AccountRule rule = ari.next();
			// For debugging a special rule
			if (debugRule != 0 && rule.getRuleId().intValue() != debugRule) {
				continue;
			}
			
			// For better debugging, we filter the output of the rules as such that
			// no debug output for rules dealing with SALES (Verkäufe) are being printed
			// for PURCHAES (Einkäufe) and vice-versa
			ExpressionContainsVisitor ecv = new ExpressionContainsVisitor(new ComparisonExpression("LAGER_VORZEICHEN", "-1"));
			if(ecv.matches(rule.getINodeElement()) == (entry.getVorgang() == Vorgang.EINKAUF)) {
				debug("SKIPPING "+rule.toStringNew()+" (this is "+entry.getVorgang()+")");
				continue;
			}
			
			debug(rule.toStringNew());
			// This calculates the entries matching priority according to the rule
			// If currentMatchPriority is > 0, the rule "in a way" applies. 
			if (filter.matches(rule.getINodeElement())) {
				if (found != null) {
					throw new DatevExportException("Two matching rules (ids: "+found.getRuleId()+" and "+rule.getRuleId()+" found for "+entry.toString()+": RULES "+found.toStringNew()+" AND "+rule.toStringNew()+"", DatevExportException.TYPE.MORE_THAN_ONE_RULE_FOUND);
				}
				found = rule;
			}
			debug(filter.getDebugBuffer());
		}
		// Throw error if no rule applies
		if (found == null) {
			throw new DatevExportException("VPOS_ID "+entry.getId_vpos_rg()+" with VKOPF_ID "+entry.getId_vkopf_rg()+" (Invoice: "+entry.getBuchungsnummer()+") has no matching rule to match an account!", DatevExportException.TYPE.NO_RULE_FOUND);
		}
		
		entry.setGegenkonto(found.getAccountNumber());
		if (found.getAccountVariable() != null) {
			try {
				String variableAccount = (String) entry.getDBEntry().get(found.getAccountVariable());
				entry.setGegenkonto(variableAccount);
			} catch (RuntimeException e) {
				throw new DatevExportException("VPOS_ID "+entry.getId_vpos_rg()+" with VKOPF_ID "+entry.getId_vkopf_rg()+" (Invoice: "+entry.getBuchungsnummer()+") sets accountNumber via variable ("+found.getAccountVariable()+", but threw exception while setting it: ", e, DatevExportException.TYPE.VARIABLE_RULE_EXCEPTION);
			}
		}
		return found;
	}
	
	public String findKreditorOrDebitorAccount(HashMap<String, Object> dbEntry) throws DatevExportException {
		return findKreditorOrDebitorAccount(new ExportBuchung().fromDBEntry(dbEntry));
	}

	public String findKreditorOrDebitorAccount(ExportBuchung entry) {
		if (entry.isRechnung()) {
			return entry.getDebitor_nummer();
		} else {
			return entry.getKreditor_nummer();
		}
	}
	
	/** 
	 * Returns the last {@link ExportBuchung}, which was generated if {@link #findRule}
	 * is called only with a dataset from database
	 */
	public ExportBuchung getBuchung() {
		return lastBuchung;
	}
	public int getAlteredBuchungen() {
		return alteredBuchungen;
	}
}
