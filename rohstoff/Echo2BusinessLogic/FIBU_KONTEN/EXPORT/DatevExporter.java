package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DATEV_PROFILE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DRUCKER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.ZIP.ZIP_NG_Creator;
import panter.gmbh.indep.ZIP.ZIP_NG_NamePair;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.AccountRule;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.AddressLine;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.DatevExportException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.EntryHash;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.BuchungLine;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.HeaderLine;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.AccountFinder;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVColumn;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv.DatevCSVText;
import rohstoff.Echo2BusinessLogic._4_ALL.Generate_Concatenated_InvoiceArchiveLists;
import rohstoff.Echo2BusinessLogic._4_ALL.Print_via_DirectPrinterDefinition;

/**
 * Main class to do the Datev Export mug. Is is usually provided an
 * {@link ExportProperties} instance, which specifies which data is to be
 * exported (by a date range) or which export set is to be written. In the first
 * case (date range), data is written to the "jt_export_log" and it's child
 * relations, generating an export id, and in the second case - an export ID is
 * provided to identify an export set - this export set is materialized to a CSV
 * file.
 * 
 * Additionally, all adresses are also exported into a second CSV file for
 * import into Datev. The process is as follows: - One chooses a date range (or
 * an old export run), see {@see ExportProperties} via GUI - The dataset is
 * gathered as a set of touples from {jt_vpos_rg} x {jt_vkopf_rg}, written to
 * the export log tables and given to the {@see AccountFinder}. This yields an
 * EXPORT ID, a number to identify the export with. - The {@see AccountFinder}
 * initializes a set of rules (filters), against every of them every dataset
 * touples is matched. A filter matching a dataset touple determines it's
 * account number, which is updated in the jt_export_vpos_rg table. - Finally,
 * all touples associated with the EXPORT ID are written to a CSV file (and
 * hence, marked as "festgeschieben" in the jt_vpos_export_rg.
 * 
 * @author nils
 * 
 */
public class DatevExporter {
	private static final String NEWLINE = "\r\n";

	/** The input data from Database, to be accounted */
	ArrayList<HashMap<String, Object>> exportData;

	/** The output for Buchungen: errornous and ambigous */
	ArrayList<ExportBuchung> errornousBuchungen;
	ArrayList<ExportBuchung> ambigousBuchungen;

	/** The exported and evaluated output data from Database */
	ArrayList<ExportBuchung> buchungData;
	/** The input data from Database */
	ArrayList<ExportAddress> addressData;
	
	/** EU Tax ID Codes */
	ArrayList<String> euTaxIdCodes; 

	/** Where protocols are saved */
	String basePath;
	/** The basename for all saved files */
	String baseName;

	/**
	 * The output lines for "Buchungen" grouped by account-nuber, invoice-number
	 */
	HashMap<EntryHash, ExportBuchung> buchungOutput = new HashMap<EntryHash, ExportBuchung>();
	/** The account finder for this instance */
	AccountFinder accountFinder = null;

	/** ExportProperties field */
	private ExportProperties ep;

	/** The DATEV export profile from the Rohstoff Portal */
	private RECORD_DATEV_PROFILE rdp;

	/** For the protocol */
	private StringBuffer sbGood = new StringBuffer();
	private StringBuffer sbFailed = new StringBuffer();

	/** Whether we directly print output to StdOut (only for tests) */
	private boolean verboseOutput = false;

	public DatevExporter() throws SQLException, myException {
		accountFinder = new AccountFinder();
		// Important to call init(), since AccountFinder would not have any
		// rules loaded
		Config.instance().reload();

		basePath = (Config.instance().getExportDir() != null ? Config
				.instance().getExportDir() : ".") + "/";

		accountFinder.init();
		
		euTaxIdCodes = DBUtil.getEUTaxIdCodes();
	}

	public void setVerboseOutput(boolean verboseOutput) {
		this.verboseOutput = verboseOutput;
	}

	public void start() throws FileNotFoundException,
			UnsupportedEncodingException, ClassNotFoundException, SQLException,
			DatevCSVException, myException, DatevExportException {
		doExport();
	}

	public DatevExporter setExportProperties(ExportProperties ep)
			throws myException {
		this.ep = ep;
		
		

		String idExportProfile = bibALL.get_RECORD_USER()
				.get_ID_DATEV_PROFILE_cUF_NN("");
		rdp = new RECORD_DATEV_PROFILE(idExportProfile);
		System.out.println("Datev-Beraternummer für aktuellen User: "
				+ rdp.get_DATEV_BERATERNUMMER_cUF_NN(""));

		return this;
	}

	/**
	 * Does the actual export on the system level. Requires that this instance
	 * has a valid {@see ExportProperties} instance, either specifying an Export
	 * ID (re-run an old export) or a date range (a time interval for which a
	 * new export is to be generated).
	 * 
	 * @return The export file (with full path)
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DatevCSVException
	 * @throws myException
	 * @throws DatevExportException
	 */
	public String doExport() throws FileNotFoundException,
			UnsupportedEncodingException, ClassNotFoundException, SQLException,
			DatevCSVException, myException, DatevExportException {

 		if (ep == null) {
			throw new NullPointerException(
					"ExportProperties must not be null upon start of doExport()");
		}

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		Date now = new Date();

		// Set time of export to now
		ep.setExportDate(now);
		String idUser = bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("");
		ep.setUserName(idUser);

		// Build filename of export file
		String strDate = sdfDate.format(now);

		// Append username to file
		String dateRangeFromName = ep.getName();
		ep.setName(ep.getName() + ", von " + ep.getUserName());

		if (ep.getId() == 0) {
			// No ID, so the ExportProperties contain a date range

			SimpleDateFormat formatter = new SimpleDateFormat(
					"dd.MM.yyyy, HH:mm:ss");
			String description = formatter.format(ep.getStart()) + " bis "
					+ formatter.format(ep.getEnd());

			// Generate Export => Copy data from jt_vpos_rg, jt_vkopf_rg to
			// jt_vpos_export_rg
			// and jt_vkopf_export_rg
			int exportId = DBUtil.generateExport(ep.getStart(), ep.getEnd(),
					description);

			// Set ID (we need that for the filename generation)
			ep.setId(exportId);

			// Get all the exported data
			exportData = DBUtil.returnEntriesRaw(ep, 0);

			// Find out Account numbers
			bookEntriesFromDatabase(exportData);

			// Update export table "jt_vkopf_export_rg" to "delete"
			// addresses that are already materialized
			DBUtil.removeAlreadyExportedAdressesFromExportSet(ep.getId());

		} else {
			// Run export with given ID and write it to file
			// First, obtain relevant data
			System.out.println("DatevExporter: Running with EXPORT_ID = "+ep.getId());


			this.buchungData = DBUtil.returnDataForWrite(ep);
			this.addressData = DBUtil.returnAddressDataForWrite(ep);

			if (this.buchungData.size() > 0) {
				String fileNameData = "", fileNameAddr = "", fileNamePDF = "", fileNameDL = "";

				baseName = "gutschriften-rechnungen_" + setBaseNameFromExportProperties();
				
				fileNameData = "EXTF_"+baseName+"_"+strDate+".csv";
				fileNamePDF = "PDF_"+baseName+".pdf";
				fileNameDL = "ZIP_"+baseName+"_"+strDate+".zip";
				
				String protocolGoodFileName = "GOOD_PROTOCOL_" + baseName + ".txt";
				String protocolFailedFileName = "FAILED_" + baseName + ".txt";

				
				// Write CSV (Buchungen)
				int maxAccountNumberLength = writeBuchungsDataToFile(basePath + fileNameData, now);
				ep.setFileName(basePath + fileNameData);
				DBUtil.updateExportAsMaterialized(ep.getId());
				
				// Write PDF
				String pdf = generatePDF("PDF", getAllVkopfFrom(buchungData));

				// Write Adressen
				baseName = "addressen_" + setBaseNameFromExportProperties();
				fileNameAddr =  "EXTF_"+baseName+"_"+strDate+".csv";
				writeAddressesToFile(basePath + fileNameAddr, now, maxAccountNumberLength);

				
				// Print or ZIP everything
				
				if (!rdp.get_ID_DRUCKER_cF_NN("").equals("")) {
					// There is a printer defined in the profile => print
					System.out.println("=> [PRINT-GEN] Printing export");
					RECORD_DRUCKER rd = new RECORD_DRUCKER(
							rdp.get_ID_DRUCKER_cUF_NN(""));
					
					new Print_via_DirectPrinterDefinition(basePath + fileNameData, rd);
					new Print_via_DirectPrinterDefinition(basePath + fileNameAddr, rd);

					// Nutze Protokolldrucker wenn dieser definiert ist
					if (!rdp.get_ID_DRUCKER_PROTOKOLLE_cF_NN("").equals("")) {
						System.out.println("=> [PRINT-GEN] using specified Protokolldrucker!");
						rd = new RECORD_DRUCKER(rdp.get_ID_DRUCKER_PROTOKOLLE_cUF_NN(""));
					}

					
					if (pdf != null) {
						File concatPDF = new File(pdf);
						if (concatPDF.exists() && Config.instance().printProtocols()) {
							System.out.println("=> [PRINT-GEN] Printing pdf "+pdf);
							new Print_via_DirectPrinterDefinition(pdf, rd);
						}
					} else {
						System.out.println("=> [PRINT-GEN] No concatenated pdf printed (pdf = null)");
					}
				
					File protocolGood = new File(basePath + protocolGoodFileName);
					if (protocolGood.exists() && Config.instance().printProtocols()) {
						System.out.println("=> [PRINT-GEN] Printing protocol (good)");
						new Print_via_DirectPrinterDefinition(basePath + protocolGoodFileName, rd);
					}

					File protocolFailed = new File(basePath + protocolFailedFileName);
					if (protocolFailed.exists() && Config.instance().printProtocols()) {
						System.out.println("=> [PRINT-GEN] Printing protocol (failed)");
						new Print_via_DirectPrinterDefinition(basePath + protocolFailedFileName, rd);
					}

				} else {
					// The is no printer defined => download a ZIP
					System.out.println("=> [ZIP-GEN] Creating ZIP for export download");

					Vector<ZIP_NG_NamePair> oPairs = new Vector<ZIP_NG_NamePair>();
					oPairs.add(new ZIP_NG_NamePair(fileNameData, basePath + fileNameData));
					oPairs.add(new ZIP_NG_NamePair(fileNameAddr, basePath + fileNameAddr));

					
					if (pdf != null) {
						File concatPDF = new File(pdf);
						if (concatPDF.exists()) {
							System.out.println("=> [ZIP-GEN] Adding pdf "+pdf);
							oPairs.add(new ZIP_NG_NamePair(fileNamePDF, pdf));
						}
					} else {
						System.out.println("=> [ZIP-GEN] No concatenated pdf added (pdf = null)");
					}
					
					File protocolGood = new File(basePath + protocolGoodFileName);
					if (protocolGood.exists() && Config.instance().printProtocols()) {
						System.out.println("=> [ZIP-GEN] Adding "+protocolGoodFileName);
						oPairs.add(new ZIP_NG_NamePair(protocolGoodFileName, basePath+protocolGoodFileName));
					}

					File protocolFailed = new File(basePath + protocolFailedFileName);
					if (protocolFailed.exists() && Config.instance().printProtocols()) {
						System.out.println("=> [ZIP-GEN] Adding "+protocolFailedFileName);
						oPairs.add(new ZIP_NG_NamePair(protocolFailedFileName, basePath+protocolFailedFileName));
					}

					System.out.println("=> [ZIP-GEN] creating final "+fileNameDL);
					ZIP_NG_Creator oZip = new ZIP_NG_Creator(oPairs, fileNameDL);
					System.out.println("=> [ZIP-GEN] Starting to download "+fileNameDL);
					oZip.startDownload();
				}
				return fileNameData;

			} else {
				// fileNameData = "(none)";
			}
		}
		return null;
	}

	/**
	 * Fills the instance variables and updates the database with account
	 * numbers for a given "raw" dataset, which means:
	 * (1) the Buchungen are assigned account numbers (with {@see AccountFinder}
	 * (2) the {@see #ambigousBuchungen} are filled: this happens 
	 *   when more than one rule applies to them (so they
	 *   would be assigned two account numbers, which would be invalid).
	 * (3) the {@see #errornousBuchungen} are filled: those which are not matched by
	 *   any rule (they are assigned no account number)
	 * (4) The successful dataset is returned.
	 * For everything that happens, the protocols are also saved accordingly.
	 * 
	 * @param b
	 * @return
	 * @throws myException
	 * @throws SQLException
	 */
	public ArrayList<ExportBuchung> bookEntriesFromDatabase(
			ArrayList<HashMap<String, Object>> b) throws myException,
			SQLException {

		ambigousBuchungen = new ArrayList<ExportBuchung>();
		errornousBuchungen = new ArrayList<ExportBuchung>();

		if (ep == null) {
			ep = new ExportProperties();
		}

		accountFinder.setDebug(true);
		ArrayList<ExportBuchung> bookedResult = null;

		baseName = "gutschriften-rechnungen_"
				+ setBaseNameFromExportProperties();

		int errorCount = 0, ambigousCount = 0;
		printBoth("====> testing " + b.size() + " records (" + baseName + ")");
		for (HashMap<String, Object> bn : b) {
			ExportBuchung buchung;
			try {
				accountFinder.flushDebug();

				AccountRule rule = accountFinder
						.matchAndAssignAccountNumbers(bn);

				buchung = accountFinder.getBuchung();

				String buf = accountFinder.getDebugBuffer();
				printGood(buf);

				if (verboseOutput) {
					System.out.println(buf);
				}

//				saveBuchungForOutput(buchung, false); //UPDATE nils, 8.6. livegang
				saveBuchungForOutput(buchung);
				if (ep.getId() != 0) {
					updateBuchungWithAccountNumbersInExportTable(buchung, rule);
				}

			} catch (DatevExportException e) {
				buchung = accountFinder.getBuchung();
				// This is for "failed" vpos. We give Datev a syntactically correct,
				// but semantically incorrect account number to let the CSV be 
				// imported correctly.
				buchung.setGegenkonto("0000");
				if (checkForKnownStupidVPos(buchung)) {
					continue;
				}

				printFailed(accountFinder.getDebugBuffer());
				printFailed(e.getMessage());
				errorCount++;

				if (e.getType() == DatevExportException.TYPE.MORE_THAN_ONE_RULE_FOUND) {
					ambigousCount++;
					buchung.appendKommentar(e.getMessage());
					ambigousBuchungen.add(buchung);
				} else {
					errornousBuchungen.add(accountFinder.getBuchung());
				}

//				saveBuchungForOutput(buchung, false); //UPDATE nils, 8.6. livegang
				saveBuchungForOutput(buchung);
				if (ep.getId() != 0) {
					updateBuchungWithAccountNumbersInExportTable(buchung, null);
				}
				// e.printStackTrace();
				continue;
			}
		}
		printBoth("");
		printBoth("============================================================================================");
		printBoth("Overall result: " + errorCount + " errors (" + ambigousCount
				+ " ambigous)");


		bookedResult = getBuchungOutputOrderedByInvoiceNumber();
		ArrayList<String> goodVKopf = getAllVkopfFrom(bookedResult);
		if (bookedResult.size() > 0) {
			printGood("");
			printGood("============================================================================================");
			printGood("OKAY: ");
		}
		Date former = null;

		for (ExportBuchung entry : bookedResult) {
			if (former == null
					|| former.getMonth() != entry.getDatum().getMonth()) {
				printGood("");
				printGood("MONAT " + (entry.getDatum().getMonth() + 1) + "/"
						+ (entry.getDatum().getYear() + 1900));
				printGood("==============================");
			}
			printGood(entry.toString());
			former = entry.getDatum();
		}

		ArrayList<String> failedVKopf = getAllVkopfFrom(errornousBuchungen);
		if (errornousBuchungen.size() > 0) {
			printFailed("");
			printFailed("============================================================================================");
			printFailed("ERRORS (" + errornousBuchungen.size() + "): ");

			for (ExportBuchung entry : errornousBuchungen) {
				printFailed(entry.toString());
				String vpos = entry.getId_vkopf_rg().toPlainString();
			}
		}

		if (ambigousBuchungen.size() > 0) {
			printFailed("");
			printFailed("============================================================================================");
			printFailed("AMBIGUITIES (" + ambigousBuchungen.size() + "): ");
			for (ExportBuchung entry : ambigousBuchungen) {
				printFailed(entry.toString());
				String vpos = entry.getId_vkopf_rg().toPlainString();
				if (!failedVKopf.contains(vpos)) {
					failedVKopf.add(vpos);
				}
			}
		}

		printBoth("");

		if (errorCount > 0) {
			flushProtocol(sbFailed, basePath + "FAILED_PROTOCOL_" + baseName
					+ ".txt");
			generatePDF("FAILED", failedVKopf);
		}
		flushProtocol(sbGood, basePath + "GOOD_PROTOCOL_" + baseName + ".txt");
		generatePDF("GOOD", goodVKopf);
		return bookedResult;
	}

	/**
	 * Returns true if the passed Buchung is known to be false, stupid
	 * or invalid, and must not be taken into consideration for export.
	 * @param buchung
	 * @return
	 */
	private boolean checkForKnownStupidVPos(ExportBuchung buchung) {
		int vpos = buchung.getId_vpos_rg().intValue();

		if (vpos == 206507 || vpos == 206512) {
			// Test Nils
			return true;
		}
		if (vpos == 176266 || vpos == 177426) {
			// 2014-02-28: Tax == 1 (das ist sicherlich falsch)
			// [ExportBuchung vpos_id: 176266, vkopf_id: 37800 
			// [G4206021887 / 14 / -19.8 EUR] {EK DIENSTL PRIV, ustids:
			// DE->null, tax%: 1, anr: KO01-00, artId: 1634, adrId:
			// 27188}]
			// [ExportBuchung vpos_id: 177426, vkopf_id: 37984
			// [G4206022006 / 15 / 19.8 EUR] {EK DIENSTL PRIV, ustids:
			// DE->null, tax%: 1, anr: KO01-00, artId: 1634, adrId:
			// 27188}])
			return true;
		}
		if (vpos == 103948) {
			// 2012: Buchungsnummer fehlt
			// [ExBu vpos_id: 103948, vkopf_id: 21915 [null / 2012-05-31
			// / 4 / 108,00 EUR] {EK , DE->NL, tax%: 19, anr: 0401-01,
			// artId: 20, adrId: 3537}]
			return true;
		}

		if (vpos == 191059 || vpos == 192959) {
			// Storno-Buchungen
			return true;
		}		
		return false;
	}

	/**
	 * Extracts all VKopf-Ids as String from an ArrayList of Buchungen.
	 * Used to be passed to PDF generator.
	 * @param bookings
	 * @return
	 */
	private ArrayList<String> getAllVkopfFrom(ArrayList<ExportBuchung> bookings) {
		ArrayList<String> answer = new ArrayList<String>();
		for (ExportBuchung entry : bookings) {
			if (!answer.contains(entry.getId_vkopf_rg().toPlainString())) {
				answer.add(entry.getId_vkopf_rg().toPlainString());
			}
		}
		return answer;	
	}

	/**
	 * ExportProperties usually determine the naming of file names,
	 * depending on whether these properties specify a new exporting
	 * date range, a re-export of an already existant export
	 * or only a single invoice, for debugging purposes. This sets
	 * the baseName accordingly.
	 * @return
	 */
	private String setBaseNameFromExportProperties() {
		// Basename for protocols and debug PDFs
		String answer;
		if (ep.getStart() == null && ep.getEnd() == null
				&& ep.getDebugBelegNummer() != null) {
			this.printBoth("====> booking belegnummern: "
					+ ep.getDebugBelegNummer());
			answer = ep.getDebugBelegNummer();
		} else if (ep.getId() != 0) {
			this.printBoth("====> booking belegnummern: "
					+ ep.getDebugBelegNummer());
			answer = "export_" + ep.getId();
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			this.printBoth("====> booking recent timespan "
					+ (ep.getStart() != null ? "from: " + ep.getStart() : "")
					+ (ep.getEnd() != null ? " until: " + ep.getEnd() : ""));

			answer = (ep.getStart() != null ? formatter.format(ep.getStart())
					: "null")
					+ "-"
					+ (ep.getEnd() != null ? formatter.format(ep.getEnd())
							: "null");
		}
		return answer;
	}

	private void updateBuchungWithAccountNumbersInExportTable(
			ExportBuchung buchung, AccountRule rule) throws SQLException {
		DBUtil.updateVPOS(buchung, rule);
	}

	/**
	 * Generates a pdf with prefix + baseName in basepath, containing all belege
	 * for the given vkopf list.
	 * 
	 * @param prefix
	 * @param vkopf
	 * @param baseName
	 * @param basePath
	 * @throws myException
	 */
	private String generatePDF(String prefix, ArrayList<String> vkopf) throws myException {
		return generatePDF(prefix, vkopf, null);
	}
	
	private String generatePDF(String prefix, ArrayList<String> vkopf, String copyTo) throws myException {
		myTempFile r = getGeneratedPDFFile(prefix, vkopf);
		System.out.println("generatePDF: Would generate "+ basePath + prefix + "_" + baseName + ".pdf");

		if (r != null) {
			try {
				if (copyTo == null) {
					copyTo = basePath + prefix + "_" + baseName + ".pdf";
				}
				FileUtils.copyFile(new File(r.getFileName()), new File(copyTo));
				System.out.println("=> PDF generated: " + copyTo);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("=> [PDF-EX] " + e.getMessage());
				System.out.println(Helpers.getExceptionMessage(e));
				copyTo = null;
			}
		}
		return copyTo;
	}
	
	private myTempFile getGeneratedPDFFile(String prefix, ArrayList<String> vkopf) throws myException {
		System.out.println("Generating PDF for " + prefix + ": "+ vkopf);
		Generate_Concatenated_InvoiceArchiveLists generator = new Generate_Concatenated_InvoiceArchiveLists(
				vkopf, false);
		generator.setFileNamePrefix(prefix + "-");
		MyE2_MessageVector mv = generator.DO_Concatenate();
		for (MyE2_Message m : mv) {
			System.out.println("=> [PDF-MSG] " + m.get_cMessage().COrig());
		}
		return generator.get_oTempFile();
	}

	/**
	 * Writes the data to a CSV file
	 * 
	 * @throws DatevCSVException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws myException
	 */
	private int writeBuchungsDataToFile(String fileName, java.util.Date now)
			throws FileNotFoundException, UnsupportedEncodingException,
			DatevCSVException, ClassNotFoundException, SQLException,
			myException {
		/** The youngmost (most recent) date found in current data */
		Date youngestDateFound = null;
		/** The oldest (leastmost recent) date found in current data */
		Date oldestDateFound = null;
		/** Max length of account number */
		int maxAccountNumberLength = 0;

		// Sorting buchungsData according to

		for (ExportBuchung entry : buchungData) {
			String invoiceNumber = entry.getBuchungsnummer();
			String accountMain = entry.getKonto();
			String accountCounter = entry.getGegenkonto();

			if (youngestDateFound == null || (entry.getDatum() != null)
					&& youngestDateFound.before(entry.getDatum())) {
				youngestDateFound = entry.getDatum();
			}
			if (oldestDateFound == null || (entry.getDatum() != null)
					&& oldestDateFound.after(entry.getDatum())) {
				oldestDateFound = entry.getDatum();
			}
			if (accountCounter != null
					&& accountCounter.length() > maxAccountNumberLength) {
				// Sachkontenlänge
				maxAccountNumberLength = accountCounter.length();
			}
			EntryHash h = saveBuchungForOutput(entry, true);
		}

		ArrayList<ExportBuchung> buchungOutputSorted = getBuchungOutputOrderedByInvoiceNumber();

		// Korrektur: Claudia Hecktor möchte von/bis immer manuell eingeben. Daher überschreiben
		// wir hier die gefundenen "jümgsten" und "ältesten" Datumszeitstempel mit denjenigen
		// der ExportProperties (idR 01.Monat.Jahr - 28/29/30/31.Monat.Jahr)
		if (ep.getStart() != null) {
			System.out.println("Overwriting oldest date found to "+ep.getStart());
			oldestDateFound = ep.getStart();
		}
		if (ep.getEnd() != null) {
			System.out.println("Overwriting youngest date found to "+ep.getEnd());
			youngestDateFound = ep.getEnd();
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss000");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
		String exportDate = formatter.format(ep.getExportDate());

		// Iterate over all outputLines and write them to file
		PrintWriter writer = new PrintWriter(fileName, "Cp1252");

		String youngest = formatter2.format(youngestDateFound);
		String oldest = formatter2.format(oldestDateFound);

		String exporterOriginAbbrev = "SV"; //
		Date wjbd = (Date) rdp.get(
				_DB.DATEV_PROFILE$DATEV_GESCHAEFTSJAHRESBEGINN)
				.get_oNativeDataObject();

		wjbd.setYear(youngestDateFound.getYear());

		String exportWirtschaftsjahrbeginn = formatter2.format(wjbd);
		String exportingUser = ep.getUserName();
		// This header specifies version information

		HeaderLine header = new HeaderLine();
		header.addValue("EXTF")
				// immmer
				.addValue(300)
				// Versionsnr
				.addValue(21)
				// "Buchungsstapel"
				.addValue("Buchungsstapel")
				// Beschr
				.addValue(4)
				// Formatversion; 4
				.addValue(exportDate)
				// Erzeugt am
				.addValue(null)
				// Importiert am
				.addValue(exporterOriginAbbrev)
				// Herkunftskz
				.addValue(exportingUser)
				// Deb/Kred
				.addValue(null)
				// Importiert von
				.addValue(rdp.get_DATEV_BERATERNUMMER_cUF_NN(""))
				// Deb/Kred
				.addValue(rdp.get_DATEV_MANDANTNUMMER_cUF_NN(""))
				// Deb/Kred
				.addValue(exportWirtschaftsjahrbeginn)
				.addValue(maxAccountNumberLength)
				.addValue(oldest)
				// Von
				.addValue(youngest)
				// Bis
				.addValue("[ID = "+ep.getId()+"] Buchungsstapel " + ep.getName())
				// Beschreibung
				.addValue("MM")
				//Diktatkürzel
				.addValue(1).addValue(0).addValue(null).addValue("EUR")
				.addValue(null).addValue(null).addValue(null).addValue(null);

		// This prints the file format header
		writer.print(header.toCSVString() + NEWLINE);

		// This is the header which describes the field names; it can be useful
		// for debugging an export CSV file
		writer.print(new BuchungLine().getHeader() + NEWLINE);

		for (ExportBuchung val : buchungOutputSorted) {
			// System.out.println(val.getBuchungsnummer());
			writeBuchungLine(writer, val);
		}
		writer.close();

		return maxAccountNumberLength;
	}

	/**
	 * Method to write the data to a CSV file
	 * 
	 * @throws DatevCSVException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws myException
	 */
	private void writeAddressesToFile(String fileName,
			java.util.Date timestamp, int maxAccountNumberLength)
			throws FileNotFoundException, UnsupportedEncodingException,
			DatevCSVException, ClassNotFoundException, SQLException,
			myException {
		/** Max length of account number */

		// Iterate over all outputLines and write them to file
		PrintWriter writer = new PrintWriter(fileName, "Cp1252");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss000");
		String exportDate = formatter.format(ep.getExportDate());

		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");

		String exporterOriginAbbrev = "SV"; //
		Date wjbd = (Date) rdp.get(
				_DB.DATEV_PROFILE$DATEV_GESCHAEFTSJAHRESBEGINN)
				.get_oNativeDataObject();

		wjbd.setYear(ep.getExportDate().getYear());
		String exportWirtschaftsjahrbeginn = formatter2.format(wjbd);

		String exportingUser = ep.getUserName();
		// This header specifies version information

		HeaderLine header = new HeaderLine();
		header.addValue("EXTF")
				// immmer
				.addValue(300)
				// Versionsnr
				.addValue(16)
				// Deb/Kred
				.addValue("Debitoren/Kreditoren")
				// Beschr
				.addValue(3)
				// Formatversion; 3
				.addValue(exportDate)
				// Erzeugt am
				.addValue(null)
				// Importiert am
				.addValue(exporterOriginAbbrev)
				// Herkunftskz
				.addValue(exportingUser)
				// Deb/Kred
				.addValue(null)
				// Importiert von
				.addValue(rdp.get_DATEV_BERATERNUMMER_cUF_NN(""))
				// Deb/Kred
				.addValue(rdp.get_DATEV_MANDANTNUMMER_cUF_NN(""))
				// Deb/Kred
				.addValue(exportWirtschaftsjahrbeginn)
				.addValue(maxAccountNumberLength)
				.addValue(exportDate)
				// Von
				.addValue(exportDate)
				// Bis
				.addValue("[ID = "+ep.getId()+"] Debitoren/Kreditoren zu " + ep.getName())
				.addValue("MM").addValue(1).addValue(0).addValue(null)
				.addValue("EUR").addValue(null).addValue(null).addValue(null)
				.addValue(null);

		// This prints the file format header
		writer.print(header.toCSVString() + NEWLINE);
		// This is the header which describes the field names; it can be useful
		// for debugging an export CSV file
		writer.print(new AddressLine().getHeader() + NEWLINE);
		// And now: data!
		Iterator<ExportAddress> it = addressData.iterator();
		ExportAddress current;
		while (it.hasNext()) {
			current = it.next();
			// Each "Adresse" must be exported as both Debitor and Kreditor
			if (current.getDebitor_nummer() != null
					&& !current.getDebitor_nummer().equals("")) {
				writeAddressLine(writer, current.getDebitor_nummer(), current);
			}
			if (current.getKreditor_nummer() != null
					&& !current.getKreditor_nummer().equals("")) {
				writeAddressLine(writer, current.getKreditor_nummer(), current);
			}
		}
		writer.close();
	}

	public void printBoth(String s) {
		printGood(s);
		printFailed(s);
	}

	public void printGood(String s) {
		sbGood.append(s);
		sbGood.append("\n");
	}

	public void printFailed(String s) {
		sbFailed.append(s);
		sbFailed.append("\n");
	}

	/** Writes the protocol to a file */
	public void flushProtocol(StringBuffer sb, String fileName) {
		BufferedWriter bwr;
		try {
			bwr = new BufferedWriter(new FileWriter(new File(fileName)));
			bwr.write(sb.toString());
			bwr.flush();
			bwr.close();
			System.out.println("Flushed buffer to file: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates hash for Buchung and adds it to the buchungOutput field,
	 * correcting some date
	 * 
	 * @param buchung
	 * @param correctDate
	 *            set to true if you want dates to be non-bookable at the moment
	 *            to be corrected to the most recent month that's possible to be
	 *            used in datev. Usually, when January is editable until
	 *            (including), March 10; February is editable until April 10 and
	 *            so forth; if it's April 11 or later, February would already be
	 *            closed.
	 * @return the generated hash
	 */
	public EntryHash saveBuchungForOutput(ExportBuchung buchung) {
		return saveBuchungForOutput(buchung, Config.instance().correctDates());
	}

	public EntryHash saveBuchungForOutput(ExportBuchung buchung,
			boolean correctDate) {
		if (correctDate) {
			Calendar today = Calendar.getInstance(); // this defaults to now
			Calendar latestPossible = Calendar.getInstance();

			// Fibu says: Januay is editable until March 10
			// General: Any month (Mon-2) is editable until (10th day of Mon).
			// We check for dates in the output that still fall in 
			// ranges we are technically unable/disallowed by law to book on their 
			// respective dates. So 
			

			latestPossible.set(Calendar.DAY_OF_MONTH, 1);
			latestPossible.set(Calendar.HOUR_OF_DAY, 0);
			latestPossible.set(Calendar.MINUTE, 0);
			latestPossible.set(Calendar.SECOND, 0);

			if (today.get(Calendar.DAY_OF_MONTH) <= 10) {
				// Minus two months
				latestPossible.add(Calendar.MONTH, -2);
			} else {
				// Minus one month only
				latestPossible.add(Calendar.MONTH, -1);
			}

			// If the buchung's date is before the latest possible bookable
			// date...
			if (buchung.getDatum() != null
					&& buchung.getDatum().before(latestPossible.getTime())) {
				// ... then correct date!
				buchung.setDatum(latestPossible.getTime());
			}
		}

		EntryHash h = EntryHash.get(buchung);

		if (buchungOutput.get(h) == null) {
			buchung.setPositionsnummer(BigDecimal.ONE);
			buchungOutput.put(h, buchung);
		} else {
			mergeEntries(h, buchung);
		}
		return h;
	}

	/**
	 * Merges two Entries into one, if appropriate
	 */
	private void mergeEntries(EntryHash h, ExportBuchung entry) {
		ExportBuchung currentLine = buchungOutput.get(h);

		// Count number of positions merged (increment by one)
		currentLine.setPositionsnummer(currentLine.getPositionsnummer().add(
				BigDecimal.ONE));

		// Merge F_GESAMTPREIS, since all other information is already present
		currentLine.setGesamtpreis(currentLine.getGesamtpreis().add(
				entry.getGesamtpreis()));

		currentLine.setId_vpos_parent(h.getParentVPosID());
		entry.setId_vpos_parent(h.getParentVPosID());

	}

	/**
	 * Actually writes a Line to a CSV file; the entries are queried from the
	 * "jt_vpos_export_rg" relation, where they are written in the step before.
	 * 
	 * @param writer
	 * @param current
	 * @throws DatevCSVException
	 */
	private void writeBuchungLine(PrintWriter writer, ExportBuchung val)
			throws DatevCSVException {

		// Here, the formatting for the DATEV takes plase
		BuchungLine line = new BuchungLine();

		// This is already the summed price; we only take the value without sign
		BigDecimal gesamtpreis = val.getGesamtpreis();

		// Keine Nullbuchungen!
		if (gesamtpreis.equals(new BigDecimal(0)))
			return;

		// Setze "gesamtpreis"
		line.get(1).setValue(gesamtpreis.abs());

		// Setze "Währungskennzeichen"
		line.get(3).setValue(val.getWaehrung());

		// The two account numbers
		line.get(7).setValue(val.getKonto());
		line.get(8).setValue(val.getGegenkonto());

		// Belegdatum
		line.get(10).setValue(val.getDatum());
		// line.get(11).setValue(val.get("ID_VKOPF_EXPORT_RG"));

		// Belegnummer, normalized
		String buchungsnummer = val.getBuchungsnummerNormalized();
		line.get(11).setValue(buchungsnummer);

		// Importflag auf "Buchungstext"
		if (Config.instance().stampImportedWithDebugflag()) {
			line.get(14).setValue("I");
		}

		// Soll-Haben-Kennzeichen
		if (val.isGutschrift()) {
			line.get(2).setValue("H");
			if (gesamtpreis.signum() == -1) {
				line.get(2).setValue("S");
			}
		} else {
			line.get(2).setValue("S");
			if (gesamtpreis.signum() == -1) {
				line.get(2).setValue("H");
			}
		}

		writer.print(line.toCSVString() + NEWLINE);
	}

	/** Writes one address to a CSV line */
	private void writeAddressLine(PrintWriter writer, String nummer,
			ExportAddress current) throws DatevCSVException {
		// Here, the formatting for the DATEV takes plase
		AddressLine line = new AddressLine();

		line.get(1).setValue(nummer);

		// Korrektur 2015-10-21 (nilsandre): Alle Namen (Vornamen, Firmennnamensteile) korrekt übernehmen
		String name = (current.getVorname() != null && !current.getVorname().equals("") ? current.getVorname() + " " : "");
		name += (current.getName1() != null && !current.getName1().equals("") ? current.getName1() + " " : "");
		name += (current.getName2() != null && !current.getName2().equals("") ? current.getName2() + " " : "");
		name += (current.getName3() != null && !current.getName3().equals("") ? current.getName3() + " " : "");
		
		name = name.replaceAll("/\\s+/", " ");
		line.get(2).setValue(name);	// Name (Adressattyp Unternehmen)

		//line.get(4) =  Name (Adressattyp natürl. Person)
		//line.get(5) =  Vorname (Adressattyp natürl. Person)
		//line.get(6) =  Name (Adressattyp keine Angabe)

		// Füge UST-Nummer nur hinzu für europäische, nicht-deutsche Klienten (2015-07-29)
		if (current.getUmsatzsteuerLKZ() != null && !(current.getUmsatzsteuerLKZ().toLowerCase().equals("de")) && euTaxIdCodes.contains(current.getUmsatzsteuerLKZ().toUpperCase())) {
			line.get(9).setValue(current.getUmsatzsteuerLKZ());
			line.get(10).setValue(current.getUmsatzsteuerID());
		}

		// Korrektur 2015-10-21 (nilsandre): Hausnummer hinzu 
		line.get(16).setValue(current.getStrasse()+ " "+ current.getHausnummer());
		// line.get(17).setValue(current.getStrasse()); // Postfach
		line.get(18).setValue(current.getPlz());
		line.get(19).setValue(current.getOrt());
		line.get(20).setValue(current.getLaendercode());

		line.get(29).setValue(current.getTel());
		line.get(37).setValue(current.getFax());

		// Importflag auf "Indiv. Feld 1"
		// TODO: Das soll später wieder rausfliegen
		if (Config.instance().stampImportedWithDebugflag()) {
			line.get(137).setValue("I");
		}

		writer.print(line.toCSVString() + NEWLINE);
	}

	/** Returns the output array for the current operation */
	public ArrayList<ExportBuchung> getBuchungOutput() {
		ArrayList<ExportBuchung> answer = new ArrayList<ExportBuchung>();

		for (Map.Entry<EntryHash, ExportBuchung> entry : buchungOutput
				.entrySet()) {
			answer.add(entry.getValue());
		}
		return answer;
	}

	/** Before 2015-June-09, this was the preferred sorting function */
	@Deprecated
	public ArrayList<ExportBuchung> getBuchungOutputOrderedByDate() {
		ArrayList<ExportBuchung> answer = getBuchungOutput();
		// Sort according to date
		Collections.sort(answer, new Comparator<ExportBuchung>() {
			@Override
			public int compare(ExportBuchung o1, ExportBuchung o2) {
				return o1.datum.equals(o2.datum) ? (o1
						.getBuchungsnummerNormalized().compareTo(o2
						.getBuchungsnummerNormalized())) : (o1.datum
						.before(o2.datum) ? -1 : 1);
			}
		});
		return answer;
	}
	
	/** 
	 * Sorts the output to be written to CSV export file according to
	 * the invoice number (and by date secondarily); this is the correct
	 * way according to Claudia Hecktor (in session with nilsandre
	 * on 09-Jun-2015). The date sorting is implied by the invoice Number.
	 * @return the sorted BuchungOutput ArrayList
	 */
	public ArrayList<ExportBuchung> getBuchungOutputOrderedByInvoiceNumber() {
		ArrayList<ExportBuchung> answer = getBuchungOutput();
		Collections.sort(answer, new Comparator<ExportBuchung>() {
			@Override
			public int compare(ExportBuchung o1, ExportBuchung o2) {
				return o1.getBuchungsnummerNormalized().compareTo(o2
						.getBuchungsnummerNormalized());
			}
		});
		return answer;
	}
}
