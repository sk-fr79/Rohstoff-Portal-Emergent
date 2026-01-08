/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 21.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * @author manfred
 * @date 21.10.2020
 *
 */
public class Function extends xml_element {

	
	xml_element _functionCode 				= new xml_element("functionCode");
	xml_element _previousDeclarationId		= new xml_element("previousDeclarationId");
	
	
	/**
	 * @author manfred
	 * @date 21.10.2020
	 *
	 * @param node
	 */
	public Function(String node) {
		super(node);
	}

	/**
	 * Default-Constructor
	 * sets Node=Function
	 * @author manfred
	 * @date 05.11.2020
	 *
	 */
	public Function() {
		this("Function");
		// default in D laut Doku: O 
		get_functionCode().setText("O");
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		addElement(_functionCode);
		addElement(_previousDeclarationId);
	}
	
	
	public xml_element get_functionCode() {
		return _functionCode;
	}

	public xml_element get_previousDeclarationId() {
		return _previousDeclarationId;
	}

	
	public void set_functionCode(xml_element _functionCode) {
		this._functionCode = _functionCode;
	}

	public void set_previousDeclarationId(xml_element _previousDeclarationId) {
		this._previousDeclarationId = _previousDeclarationId;
	}

	
	
	
}
