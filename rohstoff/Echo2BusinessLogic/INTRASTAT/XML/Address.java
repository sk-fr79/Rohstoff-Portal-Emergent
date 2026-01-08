package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * 
 * <xsd:element name = "Address">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "streetName" minOccurs = "0"/>
				<xsd:element ref = "streetNumber" minOccurs = "0"/>
				<xsd:element ref = "postalCode" minOccurs = "0"/>
				<xsd:element ref = "cityName" minOccurs = "0"/>
				<xsd:element ref = "countryName" minOccurs = "0"/>
				<xsd:element ref = "phoneNumber" minOccurs = "0"/>
				<xsd:element ref = "faxNumber" minOccurs = "0"/>
				<xsd:element ref = "e-mail" minOccurs = "0"/>
				<xsd:element ref = "URL" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
 * 
 * @author manfred
 * @date 21.10.2020
 *
 */
public class Address extends xml_element{
	
	xml_element _streetName = new xml_element("streetName" );
	xml_element _streetNumber = new xml_element( "streetNumber");
	xml_element _postalCode = new xml_element("postalCode" );
	xml_element _cityName = new xml_element("cityName" );
	xml_element _countryName = new xml_element("countryName" );
	xml_element _phoneNumber = new xml_element("phoneNumber" );
	xml_element _faxNumber = new xml_element("faxNumber" );
	
	xml_element _email = new xml_element("e-mail" );
	xml_element _URL = new xml_element("URL" );

	/**
	 * @author manfred
	 * @date 20.10.2020
	 *
	 * @param node
	 */
	public Address() {
		super("Address");
	
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		addElement(_streetName );
		addElement(_streetNumber );
		addElement(_postalCode );
		addElement(_cityName );
		addElement(_countryName);
		addElement(_phoneNumber );
		addElement(_faxNumber);
		addElement(_email);
		addElement(_URL );
	}	


	public xml_element get_streetName() {
		return _streetName;
	}

	public void set_streetName(xml_element _streetName) {
		this._streetName = _streetName;
	}

	public xml_element get_streetNumber() {
		return _streetNumber;
	}

	public void set_streetNumber(xml_element _streetNumber) {
		this._streetNumber = _streetNumber;
	}

	public xml_element get_postalCode() {
		return _postalCode;
	}

	public void set_postalCode(xml_element _postalCode) {
		this._postalCode = _postalCode;
	}

	public xml_element get_cityName() {
		return _cityName;
	}

	public void set_cityName(xml_element _cityName) {
		this._cityName = _cityName;
	}

	public xml_element get_countryName() {
		return _countryName;
	}

	public void set_countryName(xml_element _countryName) {
		this._countryName = _countryName;
	}

	public xml_element get_phoneNumber() {
		return _phoneNumber;
	}

	public void set_phoneNumber(xml_element _phoneNumber) {
		this._phoneNumber = _phoneNumber;
	}

	public xml_element get_faxNumber() {
		return _faxNumber;
	}

	public void set_faxNumber(xml_element _faxNumber) {
		this._faxNumber = _faxNumber;
	}

	public xml_element get_email() {
		return _email;
	}

	public void set_email(xml_element _email) {
		this._email = _email;
	}

	public xml_element get_URL() {
		return _URL;
	}

	public void set_URL(xml_element _URL) {
		this._URL = _URL;
	}
	
	
	


}
