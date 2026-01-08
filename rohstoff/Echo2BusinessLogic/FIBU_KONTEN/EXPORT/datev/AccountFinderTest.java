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


public class AccountFinderTest extends AccountFinderTestBase {
	
	
	//@Test
	public void test206512() throws SQLException, myException, DatevExportException {
		int bn = 206512;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
		//String accountMain = naf.findKreditorOrDebitorAccount(buchung);
	
	}
	
	//@Test
	public void test206507() throws SQLException, myException, DatevExportException {
		int bn = 206507;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
		//String accountMain = naf.findKreditorOrDebitorAccount(buchung);
	
	}
	
//	@Test
	public void test203066() throws SQLException, myException, DatevExportException {
		int bn = 203066;


//		AccountRule r = naf.matchAndAssignAccountNumbers(getBuchungRaw(bn), 1128);
		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
		//String accountMain = naf.findKreditorOrDebitorAccount(buchung);
	
	}

//	@Test
	// Einkauf mit 19% Ust, ohne Kunden-UST
	public void test203071() throws SQLException, myException, DatevExportException {
		int bn = 203071;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}

//	@Test
	public void test187852() throws SQLException, myException, DatevExportException {
		int bn = 187852;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}
	
	//@Test
	// Hydrauliköl => Regel 1126 nun auch mit Produkten und Dienstleistungen
	// RE4106015196
	public void test205330() throws SQLException, myException, DatevExportException {
		int bn = 205330;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}
	
	//@Test
	// RE4106015261
	// Versicherung
	public void test206069() throws SQLException, myException, DatevExportException {
		int bn = 206069;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}

//	@Test
	// RE4106014848
	public void test200470() throws SQLException, myException, DatevExportException {
		int bn = 200470;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}

	//@Test
	// G4206025507
	//Privateinkauf von Kleinfirma (privat = true) in A
	public void test202316() throws SQLException, myException, DatevExportException {
		int bn = 202316;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}

//	@Test
	// G4206025221
	// Einkauf im EU-Ausland
	public void test200238() throws SQLException, myException, DatevExportException {
		int bn = 200238;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}

	//@Test
	public void testAllRecentX() throws SQLException, myException, DatevExportException {
		int bn = 200238;


		AccountRule r = af.matchAndAssignAccountNumbers(getBuchungRaw(bn));
		String accountCounter = r.getAccountNumber();
	}


//	@Test
	public void testAll() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2012-02-01";
		debugTimespan(start, null);
	}
	
//	publi
	

//	@Test
	public void testAllRecent2012Feb() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2012-02-01";
		String end = "2012-03-31";
		
		debugTimespan(start, end);
	}

//	@Test
	public void testAllRecent2012() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2012-01-01";
		String end = "2012-12-31";
		
		debugTimespan(start, end);
	}
	
	//@Test
	public void testAllRecent2013() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2013-01-01";
		String end = "2014-12-31";
		
		debugTimespan(start, end);
	}
	

//	@Test
	public void testAllRecent2014() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2014-01-01";
		String end = "2014-12-31";
		
		debugTimespan(start, end);
	}
	
	//@Test
	public void testAllRecent2014b() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2013-01-01";
//		String start = "2014-12-01";
		
		String end = null; //"2014-07-01";
		debugTimespan(start, end);

	}

//	@Test
	public void testAllRecent02() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2014-07-01";
		String end = "2014-10-01";
		debugTimespan(start, end);
	}
//	@Test
	public void testAllRecent01() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2014-10-01";
		debugTimespan(start, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//@Test
	public void testSessionHecktor02vom1803() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2014-02-01";
		String end = "2014-03-01";
		debugTimespan(start, end);
		//debugEntry("G4206021596"), null, null); 
	}

	@Test
	public void testAllFromJan2013() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2014-05-01";
		String end = "2014-05-31";
		debugTimespan(start, end);
		//debugEntry("G4206021596"), null, null); 

	}

	//@Test
	public void testEdcasesHecktorSession() throws SQLException, myException, DatevExportException {
		// Das waren die MwSt-Fehler
		//		debugEntry("RE4106012785", "RE4106012555", "RE4106012786", "RE4106012856"), null, null); 
		
		// Das sind die Autokäufe
		debugEntry("G4206021342", "G4206022007", "G4206021412"); 
//		debugEntry("G4206022007"), null, null); 
//		debugEntry("G4206021342"), null, null); 
		
		// debugEntry("RE4106012555"), null, null); 
		
	}
	

	//@Test
	public void testEdcase1() throws SQLException, myException, DatevExportException {
		debugEntry("G4206021596"); 
	}

	//@Test
	public void testEdcase2() throws SQLException, myException, DatevExportException {
		debugEntry("G4206021596"); 
	}

	//@Test
	public void testWeiterbelastungKosten() throws SQLException, myException, DatevExportException {
		verboseOutput = true;
		debugEntry("RE4106012824"); 
	}
	
	@Test
	public void testAllFromJan2014() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2014-01-01";
//		String end = "2014-05-31";
		debugTimespan(start, null);
		//debugEntry("G4206021596"), null, null); 

	}

	@Test
	public void testAllFromJan2013mew() throws SQLException, myException, DatevExportException, ParseException {
		String start = "2013-01-01";
		String end = null; // "2013-12-31";
		debugTimespan(start, end);
		//debugEntry("G4206021596"), null, null); 

	}

	@Test
	public void testExport1289() throws SQLException, myException, DatevExportException, ParseException {
		debugExport(1289);
	}
	
	
	public void testEntryInExportSet() throws SQLException, myException, DatevExportException, ParseException {
		debugBelegInExportSet("RE4106009576", 1289);

	}
	
	@Test
	public void testMehrwertsteuerHecktor20150729a() throws SQLException, myException, DatevExportException {
		verboseOutput = true;
		debugEntry("RE4106017660"); 
	}


	//@Test
	public void testMentorShalaEK01b() throws SQLException, myException, DatevExportException {
		verboseOutput = true;
		debugEntry("G4206032020"); 
	}

	//@Test
	public void testKellerGbrAltfahrzeuge() throws SQLException, myException, DatevExportException {
		verboseOutput = true;
		debugEntry("G4206032026"); 
	}

}
