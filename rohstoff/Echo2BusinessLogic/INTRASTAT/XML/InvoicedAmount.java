/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 06.11.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * 
 * @author manfred
 * @date 06.11.2020
 * 
 * 
 * 	<xsd:element name = "invoicedAmount">
	<xsd:complexType>
		<xsd:simpleContent>
			<xsd:extension base = "xsd:decimal">
				<xsd:attribute name = "currencyCode" use = "optional" type = "xsd:string"/>
			</xsd:extension>
		</xsd:simpleContent>
	</xsd:complexType>
 </xsd:element>
 *
 */
public class InvoicedAmount extends xml_element {

	private String _Attribut_currencyCode = "";
	
	public InvoicedAmount() {
		super("invoicedAmount");
	}
	
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		setAttribut("currencyCode", _Attribut_currencyCode);
	}
	
	
	/**
	 * @param _Attribut_currencyCode the _Attribut_currencyCode to set
	 */
	public InvoicedAmount set_Attribut_currencyCode(String _Attribut_currencyCode) {
		this._Attribut_currencyCode = _Attribut_currencyCode;
		return this;
		
	}
	

	
	

}
