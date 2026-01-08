/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 *  <xsd:sequence>
	<xsd:element ref = "envelopeId"/>
	<xsd:element ref = "DateTime"/>
	<xsd:element ref = "Party" maxOccurs = "unbounded"/>
	<xsd:element ref = "acknowlegementRequest" minOccurs = "0"/>
	<xsd:element ref = "authentication" minOccurs = "0"/>
	<xsd:element ref = "testIndicator" minOccurs = "0"/>
	<xsd:element ref = "applicationReference" minOccurs = "0"/>
	<xsd:element ref = "softwareUsed" minOccurs = "0"/>
	<xsd:element ref = "Declaration" maxOccurs = "unbounded"/>
	<xsd:element ref = "numberOfDeclarations" minOccurs = "0"/>
	</xsd:sequence>
 * @author manfred
 * @date 19.10.2020
 *
 */
public class Envelope extends xml_element{
	
	private xml_element 	_envelopeId				= new xml_element("envelopeId");
	private DateTime 		_DateTime				= (DateTime) new DateTime();
	private Party			_Party_sender 			= new Party();
	private Party			_Party_receiver 		= new Party();
	private xml_element	    _acknowlegementRequest 	= new xml_element("acknowlegementRequest").isOptional(true);
	private xml_element		_authentication 		= new xml_element("authentication").isOptional(true);
	private xml_element		_testIndicator 			= new xml_element("testIndicator").isOptional(true);
	private xml_element		_applicationReference 	= new xml_element("applicationReference").isOptional(true);
	private xml_element		_softwareUsed 			= new xml_element("softwareUsed").isOptional(true);
	private Declaration		_Declaration 			= new Declaration("Declaration");
	private xml_element		_numberOfDeclarations 	= new xml_element("numberOfDeclarations").isOptional(true);
	
	/**
	 * 
	 * 
	 * @author manfred
	 * @date 20.10.2020
	 *
	 * @param node
	 */
	public Envelope() {
		super("Envelope");
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		
		_Party_receiver.setAttribut("partyType", "CC").setAttribut("partyRole", "receiver");
		_Party_sender.setAttribut("partyType", "PSI").setAttribut("partyRole", "sender");
		
		addElement(_envelopeId);
		addElement(_DateTime);
		addElement(_Party_receiver);
		addElement(_Party_sender);
		addElement(_acknowlegementRequest);		
		addElement(_authentication);
		addElement(_testIndicator);
		addElement(_applicationReference);
		addElement(_softwareUsed);
		addElement(_Declaration);
		addElement(_numberOfDeclarations);
	}

	public xml_element get_envelopeId() {
		return _envelopeId;
	}

	public DateTime get_DateTime() {
		return _DateTime;
	}

	public Party get_Party_sender() {
		return _Party_sender;
	}

	public Party get_Party_receiver() {
		return _Party_receiver;
	}

	public xml_element get_acknowlegementRequest() {
		return _acknowlegementRequest;
	}

	public xml_element get_authentication() {
		return _authentication;
	}

	public xml_element get_testIndicator() {
		return _testIndicator;
	}

	public xml_element get_applicationReference() {
		return _applicationReference;
	}

	public xml_element get_softwareUsed() {
		return _softwareUsed;
	}

	public Declaration get_Declaration() {
		return _Declaration;
	}

	public xml_element get_numberOfDeclarations() {
		return _numberOfDeclarations;
	}

	public void set_DateTime(DateTime _DateTime) {
		this._DateTime = _DateTime;
	}

	public void set_Party_sender(Party _Party_sender) {
		this._Party_sender = _Party_sender;
	}

	public void set_Party_receiver(Party _Party_receiver) {
		this._Party_receiver = _Party_receiver;
	}

	public void set_Declaration(Declaration _Declaration) {
		this._Declaration = _Declaration;
	}

	


	
	
}
