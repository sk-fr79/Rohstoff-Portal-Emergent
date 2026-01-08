/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 21.10.2020
 * 
 * 
 * <xsd:element name = "NatureOfTransaction">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "natureOfTransactionACode"/>
				<xsd:element ref = "natureOfTransactionBCode" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "natureOfTransactionACode" type = "xsd:string"/>
	<xsd:element name = "natureOfTransactionBCode" type = "xsd:string"/>
	
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * @author manfred
 * @date 21.10.2020
 *
 */
public class NatureOfTransaction extends xml_element {

	private xml_element _natureOfTransactionACode = new xml_element("natureOfTransactionACode");
	private xml_element _natureOfTransactionBCode = new xml_element("natureOfTransactionBCode");
	
	
	/**
	 * @author manfred
	 * @date 21.10.2020
	 *
	 * @param node
	 */
	public NatureOfTransaction() {
		super("NatureOfTransaction");
		
	}
	
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		
		super.init();
		addElement(_natureOfTransactionACode);
		addElement(_natureOfTransactionBCode);
		
	}

	

	/**
	 * @return the _natureOfTransactionACode
	 */
	public xml_element get_natureOfTransactionACode() {
		return _natureOfTransactionACode;
	}


	/**
	 * @return the _natureOfTransactionBCode
	 */
	public xml_element get_natureOfTransactionBCode() {
		return _natureOfTransactionBCode;
	}
	
	
	
	

}
