package rohstoff.Echo2BusinessLogic._TAX;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import rohstoff.Echo2BusinessLogic._TAX.checkvat.CheckVatPortType;
import rohstoff.Echo2BusinessLogic._TAX.checkvat.CheckVatService;

/**
 * Command used to generate classes:
 * wsimport -p rohstoff.Echo2BusinessLogic._TAX -keep -s /home/nils/generated -d /home/nils/generated http://ec.europa.eu/taxation_customs/vies/checkVatService.wsdl
 * @author nils
 *
 */
public class EUTaxIDCheckerService {
	private Boolean lastResult;
	
	private void doIt(TaxId taxId) {
		CheckVatService checkVatService = new CheckVatService ();

		CheckVatPortType checkVatPortType = checkVatService.getCheckVatPort();
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar xmlGregorianCalendar = null;
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Holder<String> holderCountryCode = new Holder<String> (taxId.getCountryCode());
		Holder<String> holderVatNumber = new Holder<String>(taxId.getNumber());
		Holder<XMLGregorianCalendar> holderRequestDate = new Holder<XMLGregorianCalendar>(xmlGregorianCalendar);
		Holder<Boolean> holderValid = new Holder<Boolean>(new Boolean(true));
		Holder<String> holderName = new Holder<String>(new String());
		Holder<String> holderAddress = new Holder<String>(new String());
		checkVatPortType.checkVat(holderCountryCode, holderVatNumber, holderRequestDate, holderValid, holderName, holderAddress);
		lastResult = holderValid.value;
	}
	
	/**
	 * Check a foreign (non-DE) tax ID for validity.
	 * @param taxId The tax ID to be checked.
	 * @return 
	 */
	public boolean isValid(TaxId taxId) {
		doIt(taxId);
		return lastResult.equals(Boolean.TRUE);
	}

	public boolean isValid(String taxId) {
		return isValid(new TaxId(taxId));
	}	

}
