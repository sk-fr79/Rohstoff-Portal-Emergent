package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.indep.filter.INodeElement;

public class AccountRule {
	
	/** The id of the rule, from db */
	private BigDecimal ruleId;
	
	/** The final accountId the rule applies to; */ 
	private BigDecimal accountId;
	
	/** The real account Number, "Buchungskonto", e.g. "4120" */
	private String accountNumber;
	
	/** The Description of the underlying account, eg. "4120 - Wareneinkauf 0% Schweiz" */
	private String description;
	
	/** The reference to the underlying filter */
	private INodeElement iNodeElement;

	/** A string containing a field name, which, when applied to data, yields the accountNumber */
	private String variable;

	public AccountRule() {
	}
	
	/**
	 * Fill AccountRule from database recordset (an entry in
	 * JT_FIBU_KONTENREGEL); according to what fields are filled in
	 * this rule, the priority is calculated for later comparison
	 */
	public AccountRule fromDBRule(HashMap<String, Object> ruleFromDB) {

		/** This is the internal rule ID */
		this.ruleId =  (BigDecimal) ruleFromDB.get("ID_FIBU_KONTENREGEL");

		/** This references JT_FIBU_KONTO.ID_FIBU_KONTO and maps to a BigDecimal */
		this.accountId = (BigDecimal) ruleFromDB.get("ID_FIBU_KONTO");
		if (this.accountId == null) {
			throw new RuntimeException("Rule with id "+this.ruleId.intValue()+" from database does not map to an account!");
		}
		System.out.println("Creating rule "+this.toString());
		return this;
	}


	public BigDecimal getAccountId() {
		return this.accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}

	public void setRuleId(BigDecimal ruleId) {
		this.ruleId = ruleId;
	}

	public BigDecimal getRuleId() {
		return this.ruleId;
	}
	

	public INodeElement getINodeElement() {
		return iNodeElement;
	}

	public void setINodeElement(INodeElement ine) {
		this.iNodeElement = ine;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public void setAccountNumber(String string) {
		this.accountNumber = string;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	
	public void setAccountVariable(String variable) {
		this.variable = variable;
	}

	public String getAccountVariable() {
		return this.variable;
	}
	
	public String toStringNew() {
		StringBuilder sb = new StringBuilder();
		sb.append("[AccountRule id = ");
		sb.append(ruleId);
		if (description != null) {
			sb.append(" \"");
			sb.append(description);
			sb.append("\"");
		}
		if (accountNumber != null) {
			sb.append(", buchungskonto = \"");
			sb.append(accountNumber);
			sb.append("\"");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String toString() {
		return toStringNew();
	}
}
