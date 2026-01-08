package panter.gmbh.indep.filter;

/**
 * The basic node element
 * 
 * @author nils
 * 
 */
public interface INodeElement {
	IVisitResult accept(INodeVisitor visitor); // elements have to provide
												// accept().
}
