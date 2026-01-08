/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import oracle.sql.DATE;

/**
 * @author manfred
 * @date 19.10.2020
 *
 */
public class INSTAT extends xml_element{

	private String root;
	
	
	private Vector<xml_element> _elements = new Vector<xml_element>();
	
	
	private Envelope   _Envelope = new Envelope();

	
	
	
	/**
	 * Root-Knoten für den XML-Export Intrastat
	 * @author manfred
	 * @date 19.10.2020
	 *
	 */
	public INSTAT() {
		
		super("INSTAT");
		
		
	
		
		
		
		
	}


	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		addElement(_Envelope);
	}


	public Envelope get_Envelope() {
		return _Envelope;
	}




	public void set_Envelope(Envelope _Envelope) {
		this._Envelope = _Envelope;
	}

	
	
	/**
	 * erzeugt ein Test mit den einzelnen INSTAT-Elementen
	 * @author manfred
	 * @date 05.11.2020
	 *
	 */
	public void runTest() {
		SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
		
		Date dt = new Date();
		String sNachrichtId = "XGTEST-202001-20200210-1125";
		
		//
		// Envelope
		//
		get_Envelope().get_envelopeId().setText(sNachrichtId);
		this.get_Envelope().get_DateTime().get_Date().setText(dfDate.format(dt));
		this.get_Envelope().get_DateTime().get_Time().setText(dfTime.format(dt));
		
		
		
		Address adrRec = new Address();
		adrRec._cityName.setText("Wiesbaden");
		adrRec._postalCode.setText("65189");
		adrRec._streetName.setText("Gustav-Stresemann-Ring 11");
		this.get_Envelope().get_Party_receiver().set_Address(adrRec);

		
		Party   partySender = new Party();
		partySender.get_interchangeAgreementId().setText("XGTEST");
		partySender.set_attribute_partyRole("sender").set_attribute_partyType("PSI");
		partySender.get_partyID().setText("FA-nr- usw...");
		partySender.get_partyName().setText("MV Gottenheim");
		Address adrSend = new Address();
		adrSend._cityName.setText("Gottenheim");
		adrSend._streetName.setText("strassennamen ");
		partySender.set_Address(adrSend);
		this.get_Envelope().set_Party_sender(partySender);
		
		
		
		get_Envelope().get_testIndicator().setText("true");
		get_Envelope().get_softwareUsed().setText("RohstoffApp");
		
//		get_Envelope().get_Declaration().
		get_Envelope().get_Declaration().get_DateTime().get_Date().setText("2020-01-01");
		get_Envelope().get_Declaration().get_flowCode().setText("A");
		get_Envelope().get_Declaration().get_referencePeriod().setText("2020-01");
		
//		(new xml_element("ITEM"));
		get_Envelope().get_Declaration().addItem(new Item());
		get_Envelope().get_Declaration().addItem(new Item());
		get_Envelope().get_Declaration().addItem(new Item());
		get_Envelope().get_Declaration().addItem(new Item());
		get_Envelope().get_Declaration().addItem(new Item());
		
		get_Envelope().get_numberOfDeclarations().setText("1");
		
		
//		
		
//		
//		Envelope envelope = new Envelope();
//		xml_element envelopeID = new xml_element("envelopeID").setText("*envelopeID*");
//		DateTime dateTime 	= new DateTime();
//		
//		Party party_CC = (Party) new Party()
//					.setAttribut("partyType","CC")
//					.setAttribut("partyRole","receiver");
//		
//		Party party_Sender = (Party) new Party()
//				.setAttribut("partyType","PSI")
//				.setAttribut("partyRole","sender");
//		xml_element testIndicator 	= new xml_element("test_indicator").setText("true");
//		xml_element softwareUsed 	= new xml_element("softwareUsed").setText("Rohstoff-Portal");
//		
//		
//		
//		
//		envelope.addElement("envelopeId", envelopeID) ;
//		envelope.addElement("DateTime",dateTime);
//		envelope.addElement("PartyCC", party_CC);
//		envelope.addElement("PartySender", party_Sender);
//		envelope.addElement("testindicator", testIndicator);
//		envelope.addElement("softwareUsed", softwareUsed);
//		
//		this.addElement("Envelope", envelope);
		
		
		
		DocumentHelper docHelper;
		Document doc;
		Element element;
		doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("ISO-8859-1");
		doc.add(this.getElement());
		
		OutputFormat outputFormat = OutputFormat.createPrettyPrint();
		outputFormat.setEncoding("ISO-8859-1");
		XMLWriter writer;
		try {
			writer = new XMLWriter(System.out,outputFormat);
			writer.write(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		DEBUG._print(doc.asXML());
	}
	
	
}
