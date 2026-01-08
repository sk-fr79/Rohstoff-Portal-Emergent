package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;


/**
 * <xsd:element name = "Party">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "partyId"/>
				<xsd:element ref = "partyName" minOccurs = "0"/>
				<xsd:element ref = "interchangeAgreementId" minOccurs = "0"/>
				<xsd:element ref = "password" minOccurs = "0"/>
				<xsd:element ref = "Address" minOccurs = "0"/>
				<xsd:element ref = "ContactPerson" minOccurs = "0"/>
			</xsd:sequence>
			<xsd:attribute name = "partyType" use = "required">
				<xsd:simpleType>
					<xsd:restriction base = "xsd:NMTOKEN">
						<xsd:enumeration value = "PSI"/>
						<xsd:enumeration value = "TDP"/>
						<xsd:enumeration value = "CC"/>
					</xsd:restriction>
				</xsd:simpleType>
			</xsd:attribute>
			<xsd:attribute name = "partyRole" use = "required">
				<xsd:simpleType>
					<xsd:restriction base = "xsd:NMTOKEN">
						<xsd:enumeration value = "sender"/>
						<xsd:enumeration value = "receiver"/>
						<xsd:enumeration value = "PSI"/>
					</xsd:restriction>
				</xsd:simpleType>
			</xsd:attribute>
		</xsd:complexType>
	</xsd:element>
 * @author manfred
 * @date 21.10.2020
 *
 */
public class Party extends xml_element {
    
	
	
	xml_element _partyID= new xml_element("partyId");
	xml_element _partyName= new xml_element("partyName");
	xml_element _interchangeAgreementId= new xml_element("interchangeAgreementId");
	xml_element _password =new xml_element("password");
	Address _Address= new Address();
	
	ContactPerson _ContactPerson = new ContactPerson();
	
	String      _attribute_partyRole = "";
	String 		_attribute_partyType = "";
	
	public Party() {

		super("Party");
		
		
	}
	
	/* (non-Javadoc)
		 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
		 */
		@Override
		protected void init() {
			super.init();
			setAttribut("partyType", _attribute_partyType);
			setAttribut("partyRole", _attribute_partyRole);
			addElement(_partyID);
			addElement(_partyName);
			addElement(_interchangeAgreementId );
			addElement(_password );
			addElement(_Address);
			addElement(_ContactPerson);
		}

	public xml_element get_partyID() {
		return _partyID;
	}

	public Party set_partyID(xml_element _partyID) {
		this._partyID = _partyID;
		return this;
	}

	public xml_element get_partyName() {
		return _partyName;
	}

	public Party set_partyName(xml_element _partyName) {
		this._partyName = _partyName;
		return this;
	}

	public xml_element get_interchangeAgreementId() {
		return _interchangeAgreementId;
	}

	public Party set_interchangeAgreementId(xml_element _interchangeAgreementId) {
		this._interchangeAgreementId = _interchangeAgreementId;
		return this;
	}

	public xml_element get_password() {
		return _password;
	}

	public Party set_password(xml_element _password) {
		this._password = _password;
		return this;
	}

	public Address get_Address() {
		return _Address;
	}

	public Party set_Address(Address _Address) {
		this._Address = _Address;
		return this;
	}

	public xml_element get_ContactPerson() {
		return _ContactPerson;
	}

	public Party set_ContactPerson(ContactPerson _ContactPerson) {
		this._ContactPerson =  _ContactPerson;
		return this;
	}

	/**
	 * @param _attribute_partyRole the _attribute_partyRole to set
	 */
	public Party set_attribute_partyRole(String _attribute_partyRole) {
		this._attribute_partyRole = _attribute_partyRole;
		return this;
	}

	/**
	 * @param _attribute_partyType the _attribute_partyType to set
	 */
	public Party set_attribute_partyType(String _attribute_partyType) {
		this._attribute_partyType = _attribute_partyType;
		return this;
	}
	
    
    

}
