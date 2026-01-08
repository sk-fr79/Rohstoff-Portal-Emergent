package panter.gmbh.indep.dataTools.query;

/**
 * An ORDER BY Condition. Nothing more than an Identifier ({@link ID}), but
 * holding an {@link Order} attribute, whether its ASC (ascending) or DESC 
 * (descending) 
 * @author nils
 *
 */
class OrderByCondition extends ID implements SQLPart {
	private Order order;

	public OrderByCondition(final String tableCorrelationName, final String columnName) {
		super(tableCorrelationName, columnName);
	}

	public OrderByCondition(final String columnName) {
		super(columnName);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String toString() {
		String answer = super.toString();
		if (order == Order.ASC) {
			answer += " ASC";
		} else if (order == Order.DESC) {
			answer += " DESC";
		}
		return answer;
	}
}
