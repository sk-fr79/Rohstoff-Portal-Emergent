package rohstoff.Echo2BusinessLogic._TAX;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Detail;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This class implements XML-RPC on the Service "Bestätigung von ausländischen
 * Umsatzsteuer-Identifikationsnummern" {@link https://evatr.bff-online.de/eVatR/xmlrpc/http}
 * 
 * Info: TaxID DE142532305 is August Leber Offenburg,
 * 
 * @author nils
 *
 */
public class ForeignTaxIDCheckerService {
	private static String SERVICE_URL = "https://evatr.bff-online.de/";
	private static String SERVICE_FUNCTION = "evatrRPC";
	
	/** The fields provided in the return XML */
	public static final String USTID_1 = "UstId_1";
	public static final String ERRORCODE = "ErrorCode";
	public static final String USTID_2 = "UstId_2";
	public static final String DRUCK = "Druck";
	public static final String ERG_PLZ = "Erg_PLZ";
	public static final String ORT = "Ort";
	public static final String DATUM = "Datum";
	public static final String PLZ = "PLZ";
	public static final String ERG_ORT = "Erg_Ort";
	public static final String UHRZEIT = "Uhrzeit";
	public static final String ERG_NAME = "Erg_Name";
	public static final String GUELTIG_AB = "Gueltig_ab";
	public static final String GUELTIG_BIS = "Gueltig_bis";
	public static final String STRASSE = "Strasse";
	public static final String FIRMENNAME = "Firmenname";
	public static final String ERG_STR = "Erg_Str";

	static HashMap<String, String> resultCodesWithMessages = new HashMap<String, String>();
	
	/**
	 * Initialize the result codes with the result "statements", taken from
	 * {@link https://evatr.bff-online.de/eVatR/xmlrpc/codes}
	 */
	static {
		resultCodesWithMessages.put("200", "Die angefragte USt-IdNr. ist gültig.");
		resultCodesWithMessages.put("201", "Die angefragte USt-IdNr. ist ungültig.");
		resultCodesWithMessages.put("202", "Die angefragte USt-IdNr. ist ungültig. Sie ist nicht in der Unternehmerdatei des betreffenden EU-Mitgliedstaates registriert.\nHinweis:\nIhr Geschäftspartner kann seine gültige USt-IdNr. bei der für ihn zuständigen Finanzbehörde in Erfahrung bringen. Möglicherweise muss er einen Antrag stellen, damit seine USt-IdNr. in die Datenbank aufgenommen wird.");
		resultCodesWithMessages.put("203", "Die angefragte USt-IdNr. ist ungültig. Sie ist erst ab dem ... gültig (siehe Feld 'Gueltig_ab').");
		resultCodesWithMessages.put("204", "Die angefragte USt-IdNr. ist ungültig. Sie war im Zeitraum von ... bis ... gültig (siehe Feld 'Gueltig_ab' und 'Gueltig_bis').");
		resultCodesWithMessages.put("205", "Ihre Anfrage kann derzeit durch den angefragten EU-Mitgliedstaat oder aus anderen Gründen nicht beantwortet werden. Bitte versuchen Sie es später noch einmal. Bei wiederholten Problemen wenden Sie sich bitte an das Bundeszentralamt für Steuern - Dienstsitz Saarlouis.");
		resultCodesWithMessages.put("206", "Ihre deutsche USt-IdNr. ist ungültig. Eine Bestätigungsanfrage ist daher nicht möglich. Den Grund hierfür können Sie beim Bundeszentralamt für Steuern - Dienstsitz Saarlouis - erfragen.");
		resultCodesWithMessages.put("207", "Ihnen wurde die deutsche USt-IdNr. ausschliesslich zu Zwecken der Besteuerung des innergemeinschaftlichen Erwerbs erteilt. Sie sind somit nicht berechtigt, Bestätigungsanfragen zu stellen.");
		resultCodesWithMessages.put("208", "Für die von Ihnen angefragte USt-IdNr. läuft gerade eine Anfrage von einem anderen Nutzer. Eine Bearbeitung ist daher nicht möglich. Bitte versuchen Sie es später noch einmal.");
		resultCodesWithMessages.put("209", "Die angefragte USt-IdNr. ist ungültig. Sie entspricht nicht dem Aufbau der für diesen EU-Mitgliedstaat gilt. ( Aufbau der USt-IdNr. aller EU-Länder)");
		resultCodesWithMessages.put("210", "Die angefragte USt-IdNr. ist ungültig. Sie entspricht nicht den Prüfziffernregeln die für diesen EU-Mitgliedstaat gelten.");
		resultCodesWithMessages.put("211", "Die angefragte USt-IdNr. ist ungültig. Sie enthält unzulässige Zeichen (wie z.B. Leerzeichen oder Punkt oder Bindestrich usw.).");
		resultCodesWithMessages.put("212", "Die angefragte USt-IdNr. ist ungültig. Sie enthält ein unzulässiges Länderkennzeichen.");
		resultCodesWithMessages.put("213", "Die Abfrage einer deutschen USt-IdNr. ist nicht möglich.");
		resultCodesWithMessages.put("214", "Ihre deutsche USt-IdNr. ist fehlerhaft. Sie beginnt mit 'DE' gefolgt von 9 Ziffern.");
		resultCodesWithMessages.put("215", "Ihre Anfrage enthält nicht alle notwendigen Angaben für eine einfache Bestätigungsanfrage (Ihre deutsche USt-IdNr. und die ausl. USt-IdNr.).\nIhre Anfrage kann deshalb nicht bearbeitet werden.");
		resultCodesWithMessages.put("216", "Ihre Anfrage enthält nicht alle notwendigen Angaben für eine qualifizierte Bestätigungsanfrage (Ihre deutsche USt-IdNr., die ausl. USt-IdNr., Firmenname einschl. Rechtsform und Ort)\nEs wurde eine einfache Bestätigungsanfrage durchgeführt mit folgenden Ergebnis:\nDie angefragte USt-IdNr. ist gültig.");
		resultCodesWithMessages.put("217", "Bei der Verarbeitung der Daten aus dem angefragten EU-Mitgliedstaat ist ein Fehler aufgetreten. Ihre Anfrage kann deshalb nicht bearbeitet werden.");
		resultCodesWithMessages.put("218", "Eine qualifizierte Bestätigung ist zur Zeit nicht möglich. Es wurde eine einfache Bestätigungsanfrage mit folgendem Ergebnis durchgeführt:\nDie angefragte USt-IdNr. ist gültig.");
		resultCodesWithMessages.put("219", "Bei der Durchführung der qualifizierten Bestätigungsanfrage ist ein Fehler aufgetreten. Es wurde eine einfache Bestätigungsanfrage mit folgendem Ergebnis durchgeführt:\nDie angefragte USt-IdNr. ist gültig.");
		resultCodesWithMessages.put("220", "Bei der Anforderung der amtlichen Bestätigungsmitteilung ist ein Fehler aufgetreten. Sie werden kein Schreiben erhalten.");
		resultCodesWithMessages.put("221", "Die Anfragedaten enthalten nicht alle notwendigen Parameter oder einen ungültigen Datentyp. Weitere Informationen erhalten Sie bei den Hinweisen zum Schnittstelle - Aufruf.");
		resultCodesWithMessages.put("999", "Eine Bearbeitung Ihrer Anfrage ist zurzeit nicht möglich. Bitte versuchen Sie es später noch einmal. ");
	}


	/** The own Tax ID to be submitted with any checks */
	private TaxId ownTaxId = null;
	
	/** The params[] hold the full parameter stack for one check, and are built dynamically */
	private Object[] params;
	
	/** The last result of a check */
	private HashMap<String, String> lastCheckResult;
	
	/** 
	 * Helper to retrieve the {@param index}'th child element named {@param tag} 
	 * from the parent element {@param parent} (and retrieve its contents as string)
	 * @return the String value (contents of that node)
	 */
	private static String getValue(String tag, Element parent, int index) {
		NodeList nodes = parent.getElementsByTagName(tag).item(index)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		if (node == null)
			return "";
		return node.getNodeValue();
	}
	
	//TODO: Specify which exceptions are to be thrown/populated and which are to be suppressed
	/** Actually, perform the check. Needs {@link #params} to be populated. */
	private void doIt() {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		try {
			config.setServerURL(new URL(SERVICE_URL));
		} catch (MalformedURLException e) {
			// This should actually never be reached! Make sure the URL is okay!
			e.printStackTrace();
		}
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		try {
			String xml = (String) client.execute(SERVICE_FUNCTION, params);
		
			HashMap<String, String> result = new HashMap<String, String>();
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("param");	

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String paramName = getValue("string", element, 0);
					String paramValue = getValue("string", element, 1);
					result.put(paramName, paramValue);
					
				}
			}
			lastCheckResult = result;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Check a foreign (non-DE) tax ID for validity.
	 * @param taxId The tax ID to be checked.
	 * @return 
	 */
	public boolean isValid(TaxId taxId) {
		if (ownTaxId == null) {
			throw new IllegalStateException("No own tax id provided.");
		}
		params = new Object[]{this.ownTaxId.toString(), taxId.toString()};
		doIt();
		return isValid();
	}

	public boolean isValid(String taxId) {
		return isValid(new TaxId(taxId));
	}	


	/**
	 * Check for a foreign (non-DE) tax ID for validity.
	 */
	public void isValid(TaxId taxId, String companyName, String companyCity) {
		if (ownTaxId == null) {
			throw new IllegalStateException("No own tax id provided.");
		}
		String companyZip = "79107"; 
		String companyStreet = "Rue de la gare";
		String withOfficialConfirmation = "ja";
		params = new Object[]{this.ownTaxId.toString(), taxId.toString(), companyName, companyCity, companyZip, companyStreet, withOfficialConfirmation};
		doIt();
	}

	public void isValid(String taxId, String companyName, String companyCity) {
		isValid(new TaxId(taxId), companyName, companyCity);
	}	

	private void ensureState() {
		if (this.lastCheckResult == null) {
			throw new IllegalStateException("Must query before asking for a result.");
		}
	}

	
	public int getCode() {
		ensureState();
		try {
			return Integer.parseInt(lastCheckResult.get(ERRORCODE));
		} catch (NumberFormatException e) {
		}
		return 0;
	}


	public String getMessage() {
		ensureState();
		return resultCodesWithMessages.get(lastCheckResult.get(ERRORCODE));
	}
	
	public void setOwnTaxId(TaxId taxId) {
		this.ownTaxId = taxId; 
	}

	public void setOwnTaxId(String taxId) {
		this.ownTaxId = new TaxId(taxId); 
	}

	public boolean isValid() {
		ensureState();
		int code = getCode();
		if (code == 205) {
			throw new WebServiceException(getMessage());
		}
		return code == 200 || code == 218 || code == 219; 
	}
}
