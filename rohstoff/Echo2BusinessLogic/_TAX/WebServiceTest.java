package rohstoff.Echo2BusinessLogic._TAX;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import org.junit.Test;

import junit.framework.TestCase;
import panter.gmbh.basics4project.TestSession;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.query.INSERT;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;
import rohstoff.Echo2BusinessLogic._TAX.checkvat.CheckVatPortType;
import rohstoff.Echo2BusinessLogic._TAX.checkvat.CheckVatService;

/*
 * 
 * 
 * 5.525
 * 
 * 
 * Leber-og: DE142532305
 * FR17441135761
 * 
 */

public class WebServiceTest extends TestCase {
	static {
		
		try {

			TestSession ts = new TestSession("nils", "nilsandre");
		} catch (myException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testEUWS() throws DatatypeConfigurationException {
		CheckVatService checkVatService = new CheckVatService ();
		
//		System.out.println(checkVatService.getWSDLDocumentLocation());
		
		
		CheckVatPortType checkVatPortType = checkVatService.getCheckVatPort();
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

		Holder<String> holderCountryCode = new Holder<String> ("DE");
		Holder<String> holderVatNumber = new Holder<String>("142532305");
		Holder<XMLGregorianCalendar> holderRequestDate = new Holder<XMLGregorianCalendar>(xmlGregorianCalendar);
		Holder<Boolean> holderValid = new Holder<Boolean>(new Boolean(true));
		Holder<String> holderName = new Holder<String>(new String());
		Holder<String> holderAddress = new Holder<String>(new String());
		
		checkVatPortType.checkVat(holderCountryCode, holderVatNumber, holderRequestDate, holderValid, holderName, holderAddress);
		
		
		System.out.println("Valid?"+holderValid.value);
			

	
	
	}
	
	@Test
	public void testWS() throws myException, SQLException {
		ForeignTaxIDCheckerService s = new ForeignTaxIDCheckerService();
		s.setOwnTaxId("DE142532305");
		
		
		
		try {
			String idAdresseMandant = bibALL.get_RECORD_MANDANT()
					.get_EIGENE_ADRESS_ID_cUF();
			HashMap<String, Object> result = DBUtil.selectOne(new SELECT(
					 _DB.FIRMENINFO$UMSATZSTEUERID, 
					 _DB.FIRMENINFO$UMSATZSTEUERLKZ
			).from(_DB.FIRMENINFO).where(_DB.FIRMENINFO$ID_ADRESSE, idAdresseMandant));
			TaxId tid = new TaxId(
					(String)result.get(_DB.FIRMENINFO$UMSATZSTEUERLKZ), 
					(String)result.get(_DB.FIRMENINFO$UMSATZSTEUERID)
				);
			s.setOwnTaxId(tid);
			System.out.println("found out: "+tid);
		} catch (NullPointerException e) {
			// Leber Offenburg DE
			s.setOwnTaxId("");
		}
		
		
		
		
		
		s.isValid("FR17441135761");
		
		s.isValid("FR17441135761", "Mega GmbH", "Sarreguemines");
		System.out.println("Code = "+s.getCode());
		System.out.println("Valid? "+s.isValid());
		System.out.println("Message = "+s.getMessage());
		

		
		
		s.isValid("XYA910", "Mega GmbH", "Sarreguemines");
		System.out.println("Code = "+s.getCode());
		System.out.println("Valid? "+s.isValid());
		System.out.println("Message = "+s.getMessage());

		
		//TaxId t = new TaxId("DE142532305");
		//System.out.println(t.toString());
		
		
		
	}

	
	
	
	
	
	@Test
	public void testWSCombined() throws myException, SQLException {
		TaxIdChecker tic = new TaxIdChecker();
		
		
		
		System.out.println("isValid?"+tic.isValid("FR17441135761"));
		
		
		
		
	}
	
	@Test
	public void testInsert() {
		INSERT i = new INSERT().into(_DB.USTID_PRUEFUNG)
//				.set(_DB.USTID_PRUEFUNG$ID_USTID_PRUEFUNG, _DB.USTID_PRUEFUNG$$SEQ_NEXT)
				.set(_DB.USTID_PRUEFUNG$PRUEFDURCHLAUF, "1")
				.set(_DB.USTID_PRUEFUNG$USTID, "DE123456")
				.set(_DB.USTID_PRUEFUNG$IST_OKAY, "Y")
				.set(_DB.USTID_PRUEFUNG$MELDUNG, "OK")
		;
		System.out.println(i.toString());
	}

}
