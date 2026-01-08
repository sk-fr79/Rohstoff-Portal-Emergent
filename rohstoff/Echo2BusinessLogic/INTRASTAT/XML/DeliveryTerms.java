/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 21.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * 
 * @author manfred
 * @date 21.10.2020
 * 
 * 	<xsd:element name = "DeliveryTerms">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "TODCode"/>
				<xsd:element ref = "locationCode" minOccurs = "0"/>
				<xsd:element ref = "TODPlace" minOccurs = "0"/>
				<xsd:element ref = "TODDetails" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	
	<xsd:element name = "TODCode" type = "xsd:string"/>
	<xsd:element name = "locationCode" type = "xsd:string"/>
	<xsd:element name = "TODPlace" type = "xsd:string"/>
	<xsd:element name = "TODDetails" type = "xsd:string"/>
 *
 */
public class DeliveryTerms extends xml_element {

	xml_element _TODCode 		= new xml_element("TODCode");
	xml_element _locationCode 	= new xml_element("locationCode").isOptional(true);
	xml_element _TODPlace 		= new xml_element("TODPlace").isOptional(true);
	xml_element _TODDetails	 	= new xml_element("TODDetails").isOptional(true);

	
	public DeliveryTerms() {
		super("DeliveryTerms");
		addElement(_TODCode);
		addElement(_TODDetails);
		addElement(_TODPlace);
		addElement(_locationCode);
	}


	public xml_element get_TODCode() {
		return _TODCode;
	}


	public xml_element get_locationCode() {
		return _locationCode;
	}


	public xml_element get_TODPlace() {
		return _TODPlace;
	}


	public xml_element get_TODDetails() {
		return _TODDetails;
	}

	
	
	
	
}
