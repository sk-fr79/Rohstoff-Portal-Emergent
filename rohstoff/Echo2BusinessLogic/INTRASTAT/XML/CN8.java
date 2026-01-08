/**

 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 21.10.2020
 * 
 * 
 * 	<xsd:element name = "CN8">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "CN8Code"/>
				<xsd:element ref = "SUCode" minOccurs = "0"/>
				<xsd:element ref = "additionalGoodsCode" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "CN8Code" type = "xsd:string"/>
	<xsd:element name = "SUCode" type = "xsd:string"/>
	<xsd:element name = "additionalGoodsCode" type = "xsd:string"/>
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;


public class CN8 extends xml_element {

	xml_element _CN8Code 				= new xml_element("CN8Code");
	xml_element _SUCode 				= new xml_element("SUCode");
	xml_element _additionalGoodsCode 	= new xml_element("additionalGoodsCode").isOptional(true);
	

	public CN8(String node) {
		super(node);
	}
	
	@Override
	protected void init() {
		super.init();
		addElement(_CN8Code);
		addElement(_SUCode);
		addElement(_additionalGoodsCode);
	}

}
