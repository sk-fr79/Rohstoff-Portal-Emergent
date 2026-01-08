package rohstoff.Echo2BusinessLogic._TAX;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.query.INSERT;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.U;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;

/**
 * Checks a tax id for validity and writes some protocol (which id, 
 * who, when checked...) to the database. It either delegates to a
 * {@link ForeignTaxIDCheckerService} from the German Bundesamt für 
 * Steuern for any non DE-prefixed tax ids ("foreign ones"), and that 
 * checker provides a detailed status code upon checks, or to the 
 * {@link EUTaxIDCheckerService}, namely VIES from EU, where one can check
 * against all EU's countries tax IDs, but the web service only 
 * returns a boolean wheter they are valid not not.
 * @author nils
 */
public class TaxIdChecker {
	private TaxId toCheck;

	private Boolean lastResult;
	private String lastMessage;
	
	private BigDecimal prueflaufId = null;

	/** Constructor. Provide tax. */
	public TaxIdChecker(String taxId) {
		toCheck = new TaxId(taxId);
	}

	public TaxIdChecker(TaxId taxId) {
		toCheck = taxId;
	}

	public TaxIdChecker() {
	}

	
	public BigDecimal getPrueflaufId() {
		return prueflaufId;
	}

	public void setPrueflaufId(BigDecimal id) {
		prueflaufId = id;
	}
	
	
	private void doIt(){
		try {
			if (toCheck.getCountryCode().equals("DE")) {
				EUTaxIDCheckerService ch = new EUTaxIDCheckerService();
				lastResult = ch.isValid(toCheck);
				lastMessage = "";	// No messages from the EU, jus
				ch = null;
			} else {
				ForeignTaxIDCheckerService ch = new ForeignTaxIDCheckerService();
				// The foreign checker needs a vaid id from germany to check against
				setOwnGermanTaxIfForForeignChecker(ch);
				
				lastResult = ch.isValid(toCheck);
				// Only the foreign checker gives back messages
				lastMessage = "" + ch.getCode() + " - "+ch.getMessage();
				ch = null;
			}
		} catch (WebServiceException e) {
			lastMessage = "WebServiceException: "+e.getMessage();
			lastResult = Boolean.FALSE;
			return;
		}


		// This sets a new prueflaufId, if the current checker's instances' prueflaufId
		// is null. That means for every retrieved instance of {@link TaxIdChecker}, 
		// every check the instance does is stamped with the same prueflaufId.
		if (this.prueflaufId == null) {
			SELECT s = new SELECT(new U("MAX(ID_USTID_PRUEFUNG)", "MAX_ID")).from(_DB.USTID_PRUEFUNG);
			try {
				prueflaufId = (BigDecimal) DBUtil.selectOne(s).get("MAX_ID");
				if (prueflaufId == null) {
					prueflaufId = BigDecimal.ONE;
				} else {
					prueflaufId = prueflaufId.add(BigDecimal.ONE);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Testlauf id = "+prueflaufId);
		}
		
		// Write the protocol.
		INSERT i = new INSERT().into(_DB.USTID_PRUEFUNG)
				.set(_DB.USTID_PRUEFUNG$ID_USTID_PRUEFUNG, new U(_DB.USTID_PRUEFUNG$$SEQ_NEXT))
				.set(_DB.USTID_PRUEFUNG$PRUEFDURCHLAUF, prueflaufId)
				.set(_DB.USTID_PRUEFUNG$USTID, toCheck.toString())
				.set(_DB.USTID_PRUEFUNG$IST_OKAY, lastResult.equals(Boolean.TRUE) ? "Y" : "N")
				.set(_DB.USTID_PRUEFUNG$MELDUNG, lastMessage)
				// Curiously, we don't have the field LETZTE_AENDERUNG available in XML lists,
				// so we need to set that manually here
				.set(_DB.USTID_PRUEFUNG$ERZEUGT_AM, new U("SYSDATE"))
		;
		try {
			DBUtil.query(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMessage() {
		return lastMessage;
	}

	private void setOwnGermanTaxIfForForeignChecker(
			ForeignTaxIDCheckerService ch) {
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
			ch.setOwnTaxId(tid);
			//System.out.println("found out: "+tid);
		} catch (Exception e) {
			// Leber Offenburg DE
			// nein, leere ID
//			ch.setOwnTaxId("DE142532305");
			ch.setOwnTaxId("");
			
		}
	}
	
	/**
	 * Check a foreign (non-DE) tax ID for validity.
	 * @param taxId The tax ID to be checked.
	 * @return 
	 */
	public boolean isValid(TaxId taxId) {
		toCheck = taxId;
		doIt();
		return lastResult.equals(Boolean.TRUE);
	}

	public boolean isValid(String taxId) {
		return isValid(new TaxId(taxId));
	}	

	public boolean isValid() {
		if (toCheck == null) {
			throw new IllegalStateException("A tax id must be suppplied constructing TaxIdChecker, if isValid() is called without id.");
		}
		return isValid(toCheck);
	}	
}
