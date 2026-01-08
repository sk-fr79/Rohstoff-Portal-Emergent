package panter.gmbh.indep.filter;

/**
 * Expression to implement boolean NOT(.), having one child
 * expression to which it applies.
 * @author nils
 * 
 */
public class NotExpression extends UnaryExpression implements IExpression, INodeElement {
	public NotExpression(INodeElement e) {
		super(_FilterConstants.OP.NOT.toString(), e);
	}
}
