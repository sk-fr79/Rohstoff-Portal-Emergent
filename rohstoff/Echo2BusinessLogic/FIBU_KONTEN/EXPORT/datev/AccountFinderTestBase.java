package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.easymock.EasyMock;
import org.junit.Test;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.TestSession;
import panter.gmbh.basics4project.createSession;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DATEV_PROFILE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DRUCKER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.ZIP.ZIP_NG_Creator;
import panter.gmbh.indep.ZIP.ZIP_NG_NamePair;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_LIST_BT_Mail_and_Print;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL.__JASPER_EXEC_Pruefe_Original_per_Mail;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM.PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM.PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_STD;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.Config;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DatevExporter;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportAddress;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportBuchung;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportProperties;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVException;
import rohstoff.Echo2BusinessLogic._4_ALL.Generate_Concatenated_InvoiceArchiveLists;
import rohstoff.Echo2BusinessLogic._4_ALL.Print_via_DirectPrinterDefinition;

/** 
 * Base class for tests related to the AccountFinder. Instantiates
 * the session and provides nice methods for testing.
 * @author nils
 *
 */
public class AccountFinderTestBase extends TestCase {
	
	protected static AccountFinder af;
	protected static StringBuffer sb;
	protected static DatevExporter de;
	
	protected static boolean printPDFs = true;
	protected static boolean verboseOutput = false;
	
	static {
		
		try {

			TestSession ts = new TestSession("nilsandre", "nilsandre");
		} catch (myException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			af = new AccountFinder();
			af.init(true);
			af.setDebug(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			de = new DatevExporter();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		de.setVerboseOutput(true);
	}

	protected HashMap<String, Object> getBuchungRaw(int id) {
		try {
			return DBUtil.returnEntryRaw(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	


	public void debugTimespan(String start, String end) throws SQLException, myException, DatevExportException, ParseException {
		ExportProperties ep = new ExportProperties();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ep.setStart(formatter.parse(start));
		if (end != null) {
			ep.setEnd(formatter.parse(end));
		}
		de.setExportProperties(ep);
		ArrayList<HashMap<String, Object>> b = DBUtil.returnEntriesRaw(ep, 0);
		doIt(b);
	}
	
	public void debugEntry(String... belegnummer) throws myException, SQLException {
		ExportProperties ep = new ExportProperties();
		ep.setDebugBelegNummer(Arrays.deepToString(belegnummer));
		de.setExportProperties(ep);
		doIt(DBUtil.returnEntry(belegnummer));
	}

	public void debugBelegInExportSet(String belegnummer, int exportSetId) throws myException, SQLException {
		ExportProperties ep = new ExportProperties();
		//ep.setDebugBelegNummer(Arrays.deepToString(belegnummer));
		ep.setId(exportSetId);
		de.setExportProperties(ep);
		doIt(DBUtil.returnEntry(belegnummer));
	}
	
	public void debugExport(int exportSetId) throws SQLException, myException, DatevExportException, ParseException {
		ExportProperties ep = new ExportProperties();
		ep.setId(exportSetId);
		de.setExportProperties(ep);
		ArrayList<HashMap<String, Object>> b = DBUtil.returnEntriesRaw(ep, 0);
		doIt(b);
	}

	protected ArrayList<ExportBuchung> doIt(ArrayList<HashMap<String, Object>> b) throws myException, SQLException {
		de.setVerboseOutput(false);
		return de.bookEntriesFromDatabase(b);
	}
	/*
	
	public ArrayList<ExportBuchung> doIt(ArrayList<HashMap<String, Object>> b, String start, String end) throws myException {
		af.setDebug(true);	
		ArrayList<ExportBuchung> errBu = new ArrayList<ExportBuchung>();
		ArrayList<ExportBuchung> ambigBu = new ArrayList<ExportBuchung>();
		ArrayList<ExportBuchung> okBu = new ArrayList<ExportBuchung>();
		ArrayList<ExportBuchung> bookedResult = null;


		int errors = 0, ambig = 0;
		de.println("====> testing "+b.size()+" records ");
		for (HashMap<String, Object> bn : b) {
			try {
				af.flushDebug();
				//System.out.println(bn);
				
				AccountRule rule = af.matchAndAssignAccountNumbers(bn);
				ExportBuchung buchung = af.getBuchung(); 
				

				if (verboseOutput) {
					// Only one given => must be debug
					String buf = af.getDebugBuffer();
					de.println(buf);
					System.out.println(buf);
				}
						

				de.saveBuchungForOutput(buchung, false);
				okBu.add(buchung);

				
			} catch (DatevExportException e) {
				int vpos = ((BigDecimal)bn.get("ID_VPOS_RG")).intValue();
				if (vpos == 206507 || vpos == 206512) {
					// Test Nils
					continue;
				}
				if (vpos == 176266 || vpos == 177426) {
					//2014-02-28: Tax == 1 (das ist sicherlich falsch)
					// [ExportBuchung vpos_id: 176266, vkopf_id: 37800 [G4206021887 / 14 / -19.8 EUR] {EK DIENSTL PRIV, ustids: DE->null, tax%: 1, anr: KO01-00, artId: 1634, adrId: 27188}]
					// [ExportBuchung vpos_id: 177426, vkopf_id: 37984 [G4206022006 / 15 / 19.8 EUR] {EK DIENSTL PRIV, ustids: DE->null, tax%: 1, anr: KO01-00, artId: 1634, adrId: 27188}])
					continue;
				}
				if (vpos == 103948) {
					// 2012: Buchungsnummer fehlt
					// [ExBu vpos_id:  103948, vkopf_id:  21915 [null / 2012-05-31 / 4 /  108,00 EUR] {EK      , DE->NL, tax%:  19, anr: 0401-01, artId: 20, adrId: 3537}]
					continue;
				}
				
				if (vpos == 191059 || vpos == 192959) {
					// Storno-Buchungen
					continue;
				}

				
//				if (vpos == 202316 || vpos == 200238) continue;
				de.println(af.getDebugBuffer());
				de.println(e.getMessage());
				errors++;
				if (e.getType() == DatevExportException.TYPE.MORE_THAN_ONE_RULE_FOUND) {
					ambig++;
					ExportBuchung bu = af.getBuchung();
					bu.appendKommentar(e.getMessage());
					ambigBu.add(bu);
				} else {
					errBu.add(af.getBuchung());
				}
				//e.printStackTrace();
				continue;
			}
		}
		de.println("");
		de.println("============================================================================================");
		de.println("Overall result: "+errors+" errors ("+ambig+" ambigous) found between "+start+" and "+end);
		if (af.getAlteredBuchungen() > 0) {
			de.println("Changed Buchungen because of their signum : "+af.getAlteredBuchungen());
		}
		ArrayList<String> vKopfExportedOkay = new ArrayList<String>();

		bookedResult = de.getBuchungOutputOrderedByDate();
		if (bookedResult.size() > 0) {
			de.println("");
			de.println("============================================================================================");
			de.println("OKAY: ");
		}
		Date former = null;
		
		for (ExportBuchung entry : bookedResult) {
			if (former == null || former.getMonth() != entry.getDatum().getMonth()) {
				de.println("");
				de.println("MONAT "+(entry.getDatum().getMonth()+1)+"/"+(entry.getDatum().getYear()+1900));
				de.println("==============================");
			}
			de.println(entry.toString());
			former = entry.getDatum();
			if (!vKopfExportedOkay.contains(entry.getId_vkopf_rg()
					.toPlainString())) {
				vKopfExportedOkay.add(entry.getId_vkopf_rg().toPlainString());
			}
		}

		if (errBu.size() > 0) {
			de.println("");
			de.println("============================================================================================");
			de.println("ERRORS ("+errBu.size()+"): ");
		}
		ArrayList<String> vKopfExportedErrornous = new ArrayList<String>();
		for (ExportBuchung entry : errBu) {
			de.println(entry.toString());
			if (!vKopfExportedErrornous.contains(entry.getId_vkopf_rg()
					.toPlainString())) {
				vKopfExportedErrornous.add(entry.getId_vkopf_rg().toPlainString());
			}
		}
		
		if (ambigBu.size() > 0) {
			de.println("");
			de.println("============================================================================================");
			de.println("AMBIGUITIES ("+ambigBu.size()+"): ");
		}
		for (ExportBuchung entry : ambigBu) {
			de.println(entry.toString());
			if (!vKopfExportedErrornous.contains(entry.getId_vkopf_rg()
					.toPlainString())) {
				vKopfExportedErrornous.add(entry.getId_vkopf_rg().toPlainString());
			}
		}
		
		
		de.println("");
		if (!printPDFs) return bookedResult != null ? bookedResult : okBu;
		
		de.println("Generating PDF for errors (contains not-found and ambigious): "+vKopfExportedErrornous);

		Generate_Concatenated_InvoiceArchiveLists generator = new Generate_Concatenated_InvoiceArchiveLists(vKopfExportedErrornous, false);
		generator.setFileNamePrefix("TEST_FAILED");
		MyE2_MessageVector mv = generator.DO_Concatenate();

		//System.out.println(generator.get_oTempFile());
		
		String basePath = (Config.instance().get("export.dir") != null ?
				Config.instance().get("export.dir") : ".") + "/";
		
		
		String fileName = "PDF_gutschriften-rechnungen-failed_"+ start +"-"+end;

		de.flushProtocol(basePath+"PROTOCOL_gutschriften-rechnungen_"+ start +"-"+end+".txt");

		if (generator.get_oTempFile() != null) {
	
			try {
				FileUtils.copyFile(new File(generator.get_oTempFile().getFileName()), new File(basePath + fileName + ".pdf"));
				System.out.println("PDF generiert: "+basePath + fileName + ".pdf");
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Generating PDF for the good ones: "+vKopfExportedOkay);

		Generate_Concatenated_InvoiceArchiveLists generator2 = new Generate_Concatenated_InvoiceArchiveLists(vKopfExportedOkay, false);
		generator2.setFileNamePrefix("TEST_OKAY");
		MyE2_MessageVector mv2 = generator2.DO_Concatenate();
		
		String fileNameOkay = "PDF_gutschriften-rechnungen-okay_"+ start +"-"+end;

		if (generator.get_oTempFile() != null) {
	
			try {
				FileUtils.copyFile(new File(generator2.get_oTempFile().getFileName()), new File(basePath + fileNameOkay + ".pdf"));
				System.out.println("PDF generiert: "+basePath + fileNameOkay + ".pdf");
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bookedResult != null ? bookedResult : okBu;
	}
	*/
	
	
	
}
