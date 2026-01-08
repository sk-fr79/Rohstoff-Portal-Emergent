package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.Vector;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_Caller;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE.DP__CONST;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PIPELINE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PIPELINE_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.VectorString;



/**
 * eine spezielle hashmap, die den gesamten erzeugungsvorgang von jasper-reports begleitet.
 * Fuer jeden ausdruck gibt es eine solche hashmap, die auch dem report uebergeben wird.
 */
public abstract class E2_JasperHASH extends HashMap<String,Object>
{


	/*
	 * stellt eine reihe von statischen konstanten fuer die eintragung von Hash-werten in
	 * die jeweiligen hashmaps fuer die jasper-drucke
	 * !!! wichtig ! Diese namen duerfen nicht fuer "RICHTIGE" hash-werte benutzt werden, da sie nur
	 * fuer die verwaltung in die map geschrieben werden "!!!
	 */
	public static String HM_JASPERREPORT_BASE_NAME = 				"__HM_JASPERREPORT_BASE_NAME";
	public static String HM_JASPERREPORT_REPORT_PATH = 				"__HM_JASPERREPORT_REPORT_PATH";
	public static String HM_JASPERREPORT_NAME_WITH_PATH =  			"__HM_JASPERREPORT_NAME_WITH_PATH";


	public static String JASPERREPORT_REPORT_TARGET_PRINT = 		"PRINT";
	public static String JASPERREPORT_REPORT_TARGET_MAIL =  		"MAIL";


	/*
	 * hash-Name-Konstanten fuer standard-keys
	 */
	public static String HASHKEY_SYS_USERNAME=			"SYS_USERNAME";
	public static String HASHKEY_SYS_KUERZEL=			"SYS_KUERZEL";
	public static String HASHKEY_SYS_USERID=			"SYS_USERID";
	public static String HASHKEY_SYS_MANDANT_ID=		"SYS_MANDANT_ID";
	public static String HASHKEY_SYS_ID_ADRESSE_MANDANT="SYS_ID_ADRESSE_MANDANT";
	public static String HASHKEY_SYS_REPORTDATE=		"SYS_REPORTDATE";
	public static String HASHKEY_SYS_REPORTTARGET=		"SYS_REPORTTARGET";
	public static String HASHKEY_SYS_COPYNUMBER=		"SYS_COPYNUMBER";
	public static String HASHKEY_SYS_ACTUAL_COPY=		"SYS_ACTUAL_COPY";
	public static String HASHKEY_SYS_ACTUAL_COPY_TEXT=	"SYS_ACTUAL_COPY_TEXT";
	public static String HASHKEY_SYS_HASHMAP_4_RETURN=	"SYS_HASHMAP_4_RETURN";      //kann werte uebernehmen fuer die rufenden einheit


	//2011-06-14: neue standard-haskeys
	public static String HASHKEY_SYS_USER_IS_ADMIN=					"SYS_USER_IS_ADMIN";
	public static String HASHKEY_SYS_USER_IS_GESCHAEFTSFUEHRER=		"SYS_USER_IS_GESCHAEFTSFUEHRER";


	//2011-12-09: neuer standard-Hashkey:
	public static String HASHKEY_SYS_ARCHIVBASEPATH=				 "SYS_ARCHIVBASEPATH";

	//konstanten fuer die rueckgabewerte in der hashmap SYS_HASHMAP_4_RETURN
	public static String RETURNKEY_PRINT_ADDONS=		"PRINT_ADDONS";      //kann werte uebernehmen fuer die rufenden einheit




	public static String HM_PREVIEW_YN = 			"PREVIEW_YN";


	//2012-08-03: kennstring fuer batch-druck-job
	public static final String HASHKEY_SYS_KENNSTRING_BATCHDRUCK = "SYS_KENNSTRING_BATCHDRUCK";


	/*
	 * aenderung 2010-10-19: Hashkey DRUCKART =  /PREVIEW/PRINT/EMAIL  (Standard=Print)
	 */
	public static String HASH_TYPE = 				"___TYPE";
	public static String VALUE_PRINT = 				"PRINT";
	public static String VALUE_MAIL = 				"MAIL";
	public static String VALUE_PREVIEW =			"PREVIEW";



	/*
	 * eintragungen fuer die archivierung in JT_ARCHIVMEDIEN
	 */
	public static String HM_ARCHIV_DATEINAME = 		"__HM_ARCHIV_DATEINAME";




	public static String HM_TEMP_FILE_CREATED	= 	"__HM_TEMP_FILE_CREATED";


	//aenderung; 2010-11-17: Archiv-Hash fuer archivierung aus pipeline. nimmt den wert auf, den das jeweilige ID_XXXX - Druckprotokoll-liste enthaelt,
	//                       um diese in der record-pipeline aus dem jasperHash zu lesen
	public static String  HASH_ID_DRUCKTABLE   = 	"HASH_ID_DRUCKTABLE"; 


	//2013-06-12: weiterer schluessel, um bestimmte jasper-generierungen auf wunsch inaktiv zu setzen
	//            muss bei benutzung separat ausgewertet werden
	public static String  HASH_SET_JASPER_INAKTIV = "HASH_SET_JASPER_INAKTIV";


	//2013-09-06: weiterer key, der bei eingehaengten Listen den mime-type der zielausgabe uebergibt. dies kann als steuerung beim reporting dienen
	public static String HASHKEY_LIST_TARGET_TYPE = 	"LIST_TARGET_TYPE";



	private Vector<String>      		vSQL_STATEMENTS_BEFORE_REPORT = new Vector<String>();
	private Vector<String>     		 	vSQL_STATEMENTS_AFTER_REPORT = new Vector<String>();

	//sql-statements, die ausgefuehrt werden, wenn ein fehler auftritt (z.B. im eMail nicht gesendet)
	private Vector<String>     		 	vSQL_STATEMENTS_AFTER_REPORT_ERROR = new Vector<String>();


	/*
	 * 2014-05-21: neue variante fuer uebergabe des reportnames:
	 *             wird ein name wie z.b. LIST_Adresse#nn.jasper (nn steht fuer alles zwischen 0 und 99) uebergeben, dann sucht die klasse in der 
	 *             Report-pipeline nach dem ausdruck: LIST_Adresse#nn, als report-file wird aber LIST_Adresse.jasper gesucht
	 *             Damit kann eine Report-Datei in verschiedenen Pipelines benutzt werden
	 */
	private String     					cReportName4SearchInPipelineDB = null;

	private String   					cReportBaseName = null;
	private String   					cReportPath = null;
	private String   					cAddonPath = null;
	private String   					cImagePath = null;

	private MailBlock   				oMailBlock = null;	

	private String   					cDownloadAndSendeNameStaticPart = null;             				// hiermit sollte man den beleg benennen
	private String   					cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART = null;			// sqlQuery fuer den dynamischen Teil des beleg-downloads




	//2012-02-08: der vector wird auf VectorSingle umgestellt, damit gleiche adressen  nur einmal behandelt werden
	//private Vector<String>  			vID_ADRESSE_FOR_MailLoad = new Vector<String>();				
	private VectorSingle  				vID_ADRESSE_FOR_MailLoad = new VectorSingle();


	//	/*
	//	 * falls mit der .jasper-datei eine Textdatei mit .maildef gefunden wird, dann wird der inhalt des Textes 
	//	 * in cMailInformationFromFile eingelesen
	//	 */
	//	private String    					cMailInformationFromFile = null;


	/*
	 * das pdf-dokument wird direkt nach bauen in eine E2_FileWithSendName umgewandelt
	 */
	private FileWithSendName   			oTempFileWithSendeName = null;


	private JasperFileDef          		oJasperFileDef = null;

	//hier werden alle tempfiles generiert bevor sie in die jeweilige verarbeitung gesteckt werden
	private HashMap<String, E2_JasperTempFile>  	hmAllePdfs = null ; //new HashMap<String, E2_JasperTempFile>();

	private E2_JasperTempFile_and_pipePos_VECT  	vTempfiles_and_pipePos_Standard = null; //			new E2_JasperFileProcessor_PART_VECTOR();   //landet im download oder im eMail-Versender
	private E2_JasperTempFile_and_pipePos_VECT  	vTempfiles_and_pipePos_DirektDruck = null; //		new E2_JasperFileProcessor_PART_VECTOR();   //wird direkt gedruckt
	private E2_JasperTempFile_and_pipePos_VECT  	vTempfiles_and_pipePos_Archiv = 	null ; //			new E2_JasperFileProcessor_PART_VECTOR();   //wird archiviert
	private E2_JasperTempFile_and_pipePos_VECT  	vTempfiles_and_pipePos_KontrollMail = null; //		new E2_JasperFileProcessor_PART_VECTOR();   //wird an fixe mailadressen verschickt

	//allgemeiner container, damit die E2_JasperTempfiles nicht versehentlich vom system.gc() zu frueh aufgeraeumt werden
	private Vector<myTempFile>               		vFileContainer = null ;  //new Vector<myTempFile>();

	// JasperFileProcessor für den Standard-Druck, damit die Tempfiles nicht sofort gelöscht werden
//	private E2_JasperFileProcessor		oJasperFileProcessor = null;
	//2018-09-17: fuer jeden ein eigener processor 
	private E2_JasperFileProcessor		oJasperFileProcessorStd = null;
	private E2_JasperFileProcessor		oJasperFileProcessorDirect = null;
	private E2_JasperFileProcessor		oJasperFileProcessorArchiv = null;
	private E2_JasperFileProcessor		oJasperFileProcessorControllEmail = null;



	//	//2012-02-13: moeglichkeit zur manipulation der jasperhash nach erstellen der temp-files
	//	//            konkret verwendet im mahnungsruck um auf wunsch die rechnungsarchive anzuhaenge
	//	private Vector<XX_ActionAfterCreateTempFiles>  vActionsAfterCreateTempFiles = new Vector<XX_ActionAfterCreateTempFiles>();



	//2014-01-15: pruefen, ob ein E2_JasperTempFile die seitenzahl 0 hat (leer!!)
	//            dann werden, wenn der folgende parameter=true ist, diese Files aus der hmTempfiles geschmissen 
	private boolean bEliminateEmptyJasperTempFiles = false;
	private boolean bResultIsEmpty = false;    						//kann nur true werden, wenn die bEliminateEmptyJasperTempFiles = true
	private boolean bShowErrorMessageWhenResultIsEmpty = false;    	//wird nur ausgewertet wenn bEliminateEmptyJasperTempFiles = true



	//2014-09-24: executer-objekte, die an verschiedenen stellen der jasper-report-verarbeitung ausgefuehrt werden koennen
	private Vector<Jasper_Exe_ROOT>  vExecuters = new Vector<Jasper_Exe_ROOT>(); 


	//2017.07.11: UUID fuer report_log
	private String report_uuid = "";

	

	//20190529: sammler fuer pipeline-pos-klassen (wenn keine drucksteuerung, dann leer)
	private VEK<RECORD_REPORT_PIPELINE_POS_EXT>   pipelinePositions = new VEK<>();


	/**
	 * 2021-06-01  Wenn kein Pipeline-Eintrag der Druckpipeline gedruckt wird, nur eine WARNING in die bibMSG eintragen, und keinen ERROR
	 */
	private boolean bWarningOnEmptyPipelineResult = false;
	
	
	/**
	 * 
	 * @param cNameOfReport   (ohne pfad und ohne .jasper)
	 * @param jasperFileDef
	 * @throws myException
	 */
	public E2_JasperHASH(String cNameOfReport, JasperFileDef jasperFileDef) throws myException
	{
		super();

		this.report_uuid = UUID.randomUUID().toString();
	
		// Initialisieren der Objekte
		hmAllePdfs = 					new HashMap<String, E2_JasperTempFile>();
		vTempfiles_and_pipePos_Standard = 			new E2_JasperTempFile_and_pipePos_VECT();
		vTempfiles_and_pipePos_DirektDruck = 		new E2_JasperTempFile_and_pipePos_VECT();
		vTempfiles_and_pipePos_Archiv = 				new E2_JasperTempFile_and_pipePos_VECT();
		vTempfiles_and_pipePos_KontrollMail = 		new E2_JasperTempFile_and_pipePos_VECT();
		vFileContainer 	= 				new Vector<myTempFile>();

		this.oJasperFileDef = jasperFileDef;

		if (this.oJasperFileDef==null)
			throw new myException(this,"JasperFileDef MUST be defined !!");

		this.set_Basics(cNameOfReport);


	}



	/**
	 * 2017-02-09: martin
	 * leerer Konstruktor, UNBEDINGT Call __init( String cNameOfReport, JasperFileDef jasperFileDef)
	 * @throws myException
	 */
	public E2_JasperHASH() throws myException {
		super();
		
		this.report_uuid = UUID.randomUUID().toString();

	}


	/**
	 * definition von name und filedef
	 * @param cNameOfReport
	 * @param jasperFileDef
	 * @throws myException
	 */
	public E2_JasperHASH _init(String cNameOfReport, JasperFileDef jasperFileDef) throws myException {
		// Initialisieren der Objekte
		hmAllePdfs = 					new HashMap<String, E2_JasperTempFile>();
		vTempfiles_and_pipePos_Standard = 			new E2_JasperTempFile_and_pipePos_VECT();
		vTempfiles_and_pipePos_DirektDruck = 		new E2_JasperTempFile_and_pipePos_VECT();
		vTempfiles_and_pipePos_Archiv = 				new E2_JasperTempFile_and_pipePos_VECT();
		vTempfiles_and_pipePos_KontrollMail = 		new E2_JasperTempFile_and_pipePos_VECT();
		vFileContainer 	= 				new Vector<myTempFile>();

		this.oJasperFileDef = jasperFileDef;

		if (this.oJasperFileDef==null)
			throw new myException(this,"JasperFileDef MUST be defined !!");

		this.set_Basics(cNameOfReport);
		return this;
	}







	@SuppressWarnings("rawtypes")
	public void set_Basics(String NameOfReport) throws myException
	{
		this.put(E2_JasperHASH.HASHKEY_SYS_USERNAME,				bibALL.get_USERNAME());
		this.put(E2_JasperHASH.HASHKEY_SYS_KUERZEL,					bibALL.get_KUERZEL());
		this.put(E2_JasperHASH.HASHKEY_SYS_USERID,					bibALL.get_ID_USER());
		this.put(E2_JasperHASH.HASHKEY_SYS_MANDANT_ID,				bibALL.get_ID_MANDANT());
		this.put(E2_JasperHASH.HASHKEY_SYS_ID_ADRESSE_MANDANT,		bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));
		this.put(E2_JasperHASH.HASHKEY_SYS_REPORTDATE,				bibALL.get_cDateNOW());
		this.put(E2_JasperHASH.HASHKEY_SYS_REPORTTARGET,			E2_JasperHASH.JASPERREPORT_REPORT_TARGET_PRINT);    //standard-target = print
		this.put(E2_JasperHASH.HASHKEY_SYS_COPYNUMBER,				new Integer(1));   									//standard-kopien-zahl
		this.put(E2_JasperHASH.HASHKEY_SYS_ACTUAL_COPY,				new Integer(1));   									//fortlaufender wert
		this.put(E2_JasperHASH.HASHKEY_SYS_ACTUAL_COPY_TEXT,		"");   									    		//String fuer jeweilige Kopie (Text zur Beschriftung)

		this.put(E2_JasperHASH.HASHKEY_SYS_HASHMAP_4_RETURN, 		new HashMap());   					    			//Rueckgabe aus dem report

		//2011-06-14: neue hashkeys
		this.put(E2_JasperHASH.HASHKEY_SYS_USER_IS_ADMIN, 		    	bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES()?"Y":"N");
		this.put(E2_JasperHASH.HASHKEY_SYS_USER_IS_GESCHAEFTSFUEHRER, 	bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES()?"Y":"N");


		//2011-12-09: neuer hashkey
		this.put(E2_JasperHASH.HASHKEY_SYS_ARCHIVBASEPATH, 	Archiver.get_ARCHIV_BASE_PATH(true, true));			

		//2012-08-03: neuer hashkey fuer batch-job
		this.put(E2_JasperHASH.HASHKEY_SYS_KENNSTRING_BATCHDRUCK, 	"N");       //wird  nur beim batchdruck Y


		String cNameOfReport = NameOfReport;

		E2_JasperReportPathFinder repPath = new E2_JasperReportPathFinder(cNameOfReport);

		//2014-05-21:
		this.cReportName4SearchInPipelineDB = repPath.c_ReportName4SearchInPipelineDB;

		this.cReportBaseName = 	repPath.c_ReportBaseName;
		this.cReportPath = 		repPath.c_ReportPath;
		this.cAddonPath = 		repPath.c_AddonPath;
		this.cImagePath = 		repPath.c_ImagePath;



		//die SYS_Variable reportpath und reportname in die map schreiben
		this.put("SYS_USERNAME",			bibALL.get_USERNAME());
		this.put("SYS_KUERZEL",				bibALL.get_KUERZEL());
		this.put("SYS_USERID",				bibALL.get_ID_USER());
		this.put("SYS_MANDANT_ID",			bibALL.get_ID_MANDANT());

		//speziellen mandantenid fuer das report-testing (speziell im ordner /ALLE/)
		if (S.isFull(bibALL.get_TEST_REPORT_MANDANT()))
		{
			this.put("SYS_MANDANT_ID_4_REPORTPATH",	bibALL.get_TEST_REPORT_MANDANT().trim());
		}
		else
		{
			this.put("SYS_MANDANT_ID_4_REPORTPATH",	bibALL.get_ID_MANDANT());
		}

		this.put("SYS_REPORTDATE",			bibALL.get_cDateNOW());

		this.put("SYS_REPORTBASEPATH", 	bibALL.get_REPORTPATH_BASE());

		//20180502: zwei neue pfad-variable
		this.put("SYS_REPORTPATH_ALL", 		bibALL.get_REPORTPATH_BASE()+"ALLE"+File.separator);
		this.put("SYS_REPORTPATH_MANDANT", 	bibALL.get_REPORTPATH_BASE()+bibALL.get_ID_MANDANT()+File.separator);
		if (S.isFull(bibALL.get_TEST_REPORT_MANDANT())) {
			this.put("SYS_REPORTPATH_MANDANT",bibALL.get_REPORTPATH_BASE()+bibALL.get_TEST_REPORT_MANDANT().trim()+File.separator);
		}



		this.put("SYS_REPORTNAME", 		this.cReportBaseName);
		this.put("SYS_REPORTPATH", 		this.cReportPath);
		this.put("SYS_REPORTADDONPATH", this.cAddonPath);
		this.put("SYS_REPORTIMAGEPATH", this.cImagePath);
		this.put("SYS_REPORTFILE", 		this.cReportPath+this.cReportBaseName+".jasper");


		//mandanten-angaben:
		this.put("SYS_MANDANT_TELEFON", 		bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_TELEFON_cUF_NN(""));
		this.put("SYS_MANDANT_TELEFAX", 		bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_TELEFAX_cUF_NN(""));
		this.put("SYS_MANDANT_EMAIL", 			bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_EMAIL_cUF_NN(""));
		this.put("SYS_MANDANT_WWW", 			bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_WWW_cUF_NN(""));
		this.put("SYS_MANDANT_BANK", 			bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_BANK_cUF_NN(""));
		this.put("SYS_MANDANT_BLZ", 			bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_BLZ_cUF_NN(""));
		this.put("SYS_MANDANT_KONTO", 			bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_KONTO_cUF_NN(""));
		this.put("SYS_MANDANT_REGISTERGERICHT", bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_REGISTERGERICHT_cUF_NN(""));
		this.put("SYS_MANDANT_HANDELSREG_NR", 	bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_HANDELSREG_NR_cUF_NN(""));
		this.put("SYS_MANDANT_USTID", 			bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_USTID_cUF_NN(""));
		this.put("SYS_MANDANT_STEUERNR",		bibALL.get_RECORD_MANDANT().get_BELEGDRUCK_STEUERNR_cUF_NN(""));


		//aenderung 2010-12-15: eine string-hash fuer alle reports
		HashMap<String, String> hmZusaetze = new HashMap<String, String>();
		RECLIST_MANDANT_ZUSATZ  recListZusatz = bibALL.get_RECLIST_MANDANTEN_ZUSATZ();
		Iterator<RECORD_MANDANT_ZUSATZ>  oIter = recListZusatz.values().iterator();

		while (oIter.hasNext())
		{
			RECORD_MANDANT_ZUSATZ recZusatz = oIter.next();
			hmZusaetze.put(recZusatz.get_FIELDNAME_cUF(), recZusatz.get_TEXT_VALUE_cUF_NN("<"+recZusatz.get_FIELDNAME_cUF()+">"));
		}
		this.put("SYS_HASHMAP_MANDANT_ZUSAETZE",		hmZusaetze);



		//im fall einer vorschau wird nicht archiviert
		this.set_bVorschauDruck(false);

		//standard-Typ ist druck
		this.set_TYPE_PRINT();

		//2013-09-06: unter dem key: LIST_TARGET_TYPE wird der mimetype der liste uebergeben
		if (this.oJasperFileDef != null) {
			this.set_Target_Type(this.oJasperFileDef.MimeType);
		}


	}







	public String get_cReportBaseName()
	{
		return cReportBaseName;
	}


	//2014-05-21: 
	public String get_cReportName4SearchInPipelineDB()
	{
		return cReportName4SearchInPipelineDB;
	}



	public String get_cReportPath()
	{
		return cReportPath;
	}

	/**
	 * 
	 * @return s complete name of jasperfile   /../../REPORTS/1/test.jasper
	 */
	public String get_cCompleteReportPathAndFileName()
	{
		return cReportPath+this.cReportBaseName+".jasper";
	}




	public String 			get_HM_FILENAME_OF_TEMP_FILE () 		
	{
		return (String)this.get(E2_JasperHASH.HM_TEMP_FILE_CREATED);
	}


	/*
	 * eintragungen fuer die archivierung in JT_ARCHIVMEDIEN
	 */
	public String 	get_HM_ARCHIV_DATEINAME () 							{ return (String) this.get(E2_JasperHASH.HM_ARCHIV_DATEINAME );}
	public void 	set_HM_JASPERREPORT_BASE_NAME (String cValue) 		{ this.put(E2_JasperHASH.HM_JASPERREPORT_BASE_NAME,cValue);}
	public void 	set_HM_JASPERREPORT_REPORT_PATH (String cValue) 	{ this.put(E2_JasperHASH.HM_JASPERREPORT_REPORT_PATH,cValue);}
	public void 	set_HM_JASPERREPORT_NAME_WITH_PATH (String cValue) 	{ this.put(E2_JasperHASH.HM_JASPERREPORT_NAME_WITH_PATH,cValue);}


	public void 	set_HM_FILENAME_OF_TEMP_FILE (String cTempFilePath) 	
	{
		this.put(E2_JasperHASH.HM_TEMP_FILE_CREATED,cTempFilePath);
	}


	/*
	 * eintragungen fuer die archivierung in JT_ARCHIVMEDIEN
	 */
	public void set_HM_ARCHIV_DATEINAME (String cValue) 			{ this.put(E2_JasperHASH.HM_ARCHIV_DATEINAME ,cValue);}


	public Vector<String> get_vSQL_STATEMENTS_AFTER_REPORT() 
	{
		return this.vSQL_STATEMENTS_AFTER_REPORT;
	}


	public void set_vSQL_STATEMENTS_AFTER_REPORT(Vector<String> vsql_statements_after_report) 
	{
		this.vSQL_STATEMENTS_AFTER_REPORT.removeAllElements();
		this.vSQL_STATEMENTS_AFTER_REPORT.addAll(vsql_statements_after_report);
	}


	public Vector<String> get_vSQL_STATEMENTS_BEFORE_REPORT() 
	{
		return this.vSQL_STATEMENTS_BEFORE_REPORT;
	}


	public void set_vSQL_STATEMENTS_BEFORE_REPORT(Vector<String> vsql_statements_before_report) 
	{
		this.vSQL_STATEMENTS_BEFORE_REPORT.removeAllElements();
		this.vSQL_STATEMENTS_BEFORE_REPORT.addAll(vsql_statements_before_report);
	}

	public Vector<String> get_vSQL_STATEMENTS_AFTER_REPORT_ERROR() 
	{
		return this.vSQL_STATEMENTS_AFTER_REPORT_ERROR;
	}


	public void set_vSQL_STATEMENTS_AFTER_REPORT_ERROR(Vector<String> vsql_statements_after_report) 
	{
		this.vSQL_STATEMENTS_AFTER_REPORT_ERROR.removeAllElements();
		this.vSQL_STATEMENTS_AFTER_REPORT_ERROR.addAll(vsql_statements_after_report);
	}



	public String get_cDownloadAndSendeNameStaticPart()
	{
		return cDownloadAndSendeNameStaticPart;
	}



	public void set_cDownloadAndSendeNameStaticPart(String downloadAndSendeName)
	{
		cDownloadAndSendeNameStaticPart = downloadAndSendeName;
	}


	public String get_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART()
	{
		return cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART;
	}



	public void set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART(String sql_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART)
	{
		cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART = sql_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART;
	}



	public Vector<String> get_vID_ADRESSE_FOR_MailLoad()
	{
		return vID_ADRESSE_FOR_MailLoad;
	}




	public boolean get_bHasMAILBLOCK_With_Minimum_One_MailAdresse() throws myException
	{
		boolean bRueck = false;

		if (this.get_BUILD_AND_GET_MAILBLOCK() != null)
		{
			if (this.get_BUILD_AND_GET_MAILBLOCK().get_v_MailAdress4MailBlock().size()>0)
			{
				bRueck = true;
			}
		}

		return bRueck;
	}

	/*
	 * generiert aus dem statischen teil des sendenamens und aus dem dynamischen SQL-Query-Block
	 * den kompletten download-namen
	 */
	public String get_completeDownloadAndSendeName(boolean bMitEndung)
	{

		String cRueck = "druckdatei";

		//aenderung: 20101116: als namensbasis immer mindestens den reportname nehmen
		if (S.isFull(this.cReportBaseName))
		{
			cRueck = this.cReportBaseName;
		}

		if (S.isFull(this.cDownloadAndSendeNameStaticPart))
		{
			cRueck = this.cDownloadAndSendeNameStaticPart.trim();

			if (S.isFull(this.cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART))
			{
				try
				{
					String cAntwort = bibDB.EinzelAbfrage(this.cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART, "", "", "");
					cRueck += ("_"+cAntwort);
				}
				catch (Exception ex)
				{

				}
			}
		}

		if (bMitEndung)
		{
			return cRueck+this.oJasperFileDef.Endung;
		}
		else
		{
			return cRueck;
		}
	}




	public MailBlock get_BUILD_AND_GET_MAILBLOCK() throws myException
	{
		if (this.oMailBlock == null)
		{
			this.oMailBlock = this.create_MailBlock();
			if (this.oMailBlock != null)
			{
				this.oMailBlock.set_JasperHash_this_comes_From(this);
			}
		}

		return oMailBlock;
	}




	/*
	 * aenderung: 20101019: print-pipe
	 * Vorgelagerte Methode, die nachschaut, ob nach der alten oder neuen Methode gedruckt wird
	 * 
	 */
	public void Build_tempFile(boolean bPrintOrMail) throws myException
	{

		//2014-05-21: hier wird jetzt unterschieden, ob der uebergebene name mit #0 bis #99 endet. wenn ja wird nach dem orignalen namen gesucht, 
		// die jasperfiles bleiben aber gleich
		//		  RECLIST_REPORT_PIPELINE  recListPipeLine = new RECLIST_REPORT_PIPELINE(
		//				  "SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PIPELINE WHERE UPPER(REPORTFILEBASENAME)="+bibALL.MakeSql(this.cReportBaseName.toUpperCase()));

		RECLIST_REPORT_PIPELINE  recListPipeLine = new RECLIST_REPORT_PIPELINE(
				"SELECT * FROM "+bibE2.cTO()+".JT_REPORT_PIPELINE WHERE UPPER(REPORTFILEBASENAME)="+bibALL.MakeSql(this.cReportName4SearchInPipelineDB.toUpperCase()));

		//falls in der pipeline-tabelle was drinsteht, und es pipeline-positionen dazu gibt, dann das neue Verfahren, sonst auch das alte
		if (recListPipeLine.get_vKeyValues().size()>0)
		{
			this.Build_tempFile_NG(bPrintOrMail, recListPipeLine);
		}
		else
		{
			this.Build_tempFile_NG(bPrintOrMail,null);
		}



		//	  	//2012-02-13: wenn der Vector this.vActionsAfterCreateTempFiles etwas enthaelt, dann werden die methoden angewendet 
		//		//            um erzeugte tempfiles manipulieren zu koennen (oder aehnliches) hier eine schnittstelle 
		//		if (this.vActionsAfterCreateTempFiles.size()>0)
		//		{
		//			MyE2_MessageVector  oMV = new MyE2_MessageVector();
		//			for (int i=0;i<this.vActionsAfterCreateTempFiles.size();i++)
		//			{
		//				oMV.add_MESSAGE(this.vActionsAfterCreateTempFiles.get(i).doActionAfterCreatingTempFiles(this));
		//			}
		//			bibMSG.add_MESSAGE(oMV);
		//		}

		/*
		 * 2014-09-25: umstellung auf die jasperExecute - methode
		 * Ausfuehrung aller JasperExecute-klassen, die der Sammlung Jasper_Executer_ROOT.EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_CREATE_TEMPFILE
		 * angehoeren
		 */
		new Jasper_Exe_Caller().ExecuterStart(this, null, EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_CREATE_TEMPFILE, null, null);

	}



	/**
	 * 
	 * @param bPrintOrMail (aus kompatibilitaet mitziehen, falls ein report diesen parameter benutzt
	 * @param recListPipeLine
	 * @throws myException
	 */
	private void Build_tempFile_NG(boolean bPrintOrMail, RECLIST_REPORT_PIPELINE  recListPipeLine) throws myException
	{
		DEBUG.System_println("Build_tempFile_NG", DEBUG.DEBUG_FLAG_DIVERS1);



		/*
		 * jetzt eine sammel-hash fuer jede verarbeitungs-moeglichkeit
		 */
		DEBUG.System_println("E2_JasperHASH.Build_tempFile_NG::loeschen aller Vectoren", DEBUG.DEBUG_FLAG_DIVERS1);
		this.hmAllePdfs.clear();
		this.vTempfiles_and_pipePos_Standard.removeAllElements();   				//landet im download oder im eMail-Versender
		this.vTempfiles_and_pipePos_DirektDruck.removeAllElements();   			//wird direkt gedruckt
		this.vTempfiles_and_pipePos_Archiv.removeAllElements();   				//wird archiviert
		this.vTempfiles_and_pipePos_KontrollMail.removeAllElements();   			//wird an fixe mailadressen verschickt


		if (recListPipeLine==null)
		{
			DEBUG.System_println("reclistPipeLine == null", DEBUG.DEBUG_FLAG_DIVERS1);
			//standard-fall, keine pipeline

			vTempfiles_and_pipePos_Standard.add(new E2_JasperTempFile_and_pipePos(new E2_JasperTempFile(this,null,null, null,null,null),null));

		}
		else
		{

			DEBUG.System_println("reclistPipeLine != null", DEBUG.DEBUG_FLAG_DIVERS1);

			//zuerst ein paar fehlerzustaende pruefen
			if (recListPipeLine.size()>1)	{	throw new myException(this,"Record-Pipeline ist nicht eindeutig !!");}

			//jetzt nachsehen, ob es eine zughoerige list mit pipeline-positionen gibt, wenn ja, diese aufrufen
			RECLIST_REPORT_PIPELINE_POS  reclistPipelinePos = recListPipeLine.get(0).get_DOWN_RECORD_LIST_REPORT_PIPELINE_POS_id_report_pipeline(null,"JT_REPORT_PIPELINE_POS.ID_REPORT_PIPELINE_POS",true);

			if (reclistPipelinePos.size()==0) 	{	throw new myException(this,"Record-Pipeline hat keine definierten Verarbeitungschritte !!");}

			if (bPrintOrMail)					
			{	

				DEBUG.System_println("Target = " + E2_JasperHASH.JASPERREPORT_REPORT_TARGET_PRINT, DEBUG.DEBUG_FLAG_DIVERS1);
				this.put(E2_JasperHASH.HASHKEY_SYS_REPORTTARGET, E2_JasperHASH.JASPERREPORT_REPORT_TARGET_PRINT);
			}
			else
			{
				DEBUG.System_println("Target = " + E2_JasperHASH.JASPERREPORT_REPORT_TARGET_MAIL, DEBUG.DEBUG_FLAG_DIVERS1);
				this.put(E2_JasperHASH.HASHKEY_SYS_REPORTTARGET, E2_JasperHASH.JASPERREPORT_REPORT_TARGET_MAIL);
			}


			/*
			 * jetzt die unterschiedlichen reports bauen, jede variante fuer jeden report (incl. unterschiedliche eindruck-saetze)
			 */
			for (int i=0;i<reclistPipelinePos.size();i++)
			{
				RECORD_REPORT_PIPELINE_POS_EXT recPipePos = new RECORD_REPORT_PIPELINE_POS_EXT(reclistPipelinePos.get(i));
				
				this.pipelinePositions._a(recPipePos);
				
				
				//jetzt die relevanz pruefen
				if (recPipePos.get_bIsRelevant(this))
				{
					if ( !this.hmAllePdfs.containsKey(recPipePos.get_HashKey_4_FileList()))
					{
						this.hmAllePdfs.put(recPipePos.get_HashKey_4_FileList(), 
								new E2_JasperTempFile(this,  
										recPipePos.get_ZUSATZSTRING4JASPERHASH1_cUF(),
										recPipePos.get_ZUSATZSTRING4JASPERHASH2_cUF(),
										recPipePos.get_ZUSATZSTRING4JASPERHASH3_cUF(),
										null,recPipePos.get_REPORTFILEBASENAME_cUF()));
					}
				}
			}

			if (this.hmAllePdfs.size()==0)
			{
				String cVerarbeitung = "Druck";
				if (this.get_IS_TYP_MAIL()) 		cVerarbeitung="eMail";
				if (this.get_IS_TYP_PREVIEW()) 		cVerarbeitung="Vorschau";
				
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Report: ",true,this.get_cReportBaseName(),false," hat in der Verarbeitung: ",true,cVerarbeitung,false, " keine Verarbeitungspositionen !",true).CTrans()));
				String sInfo =  new MyE2_String("Der Report: ",true,this.get_cReportBaseName(),false," hat in der Verarbeitung: ",true,cVerarbeitung,false, " keine Verarbeitungspositionen !",true).CTrans();
				if (bWarningOnEmptyPipelineResult)
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(sInfo));
				else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(sInfo));
				}
			}
			else
			{

				for (int i=0;i<reclistPipelinePos.size();i++)
				{
					RECORD_REPORT_PIPELINE_POS_EXT recPipePos = new RECORD_REPORT_PIPELINE_POS_EXT(reclistPipelinePos.get(i));

					//jetzt die relevanz pruefen
					if (recPipePos.get_bIsRelevant(this))
					{
						if (recPipePos.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_DIREKTDRUCK))
						{
							vTempfiles_and_pipePos_DirektDruck.add(new E2_JasperTempFile_and_pipePos(this.hmAllePdfs.get(recPipePos.get_HashKey_4_FileList()),recPipePos));
						}
						else if (recPipePos.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_ARCHIV))
						{
							vTempfiles_and_pipePos_Archiv.add(new E2_JasperTempFile_and_pipePos(this.hmAllePdfs.get(recPipePos.get_HashKey_4_FileList()),recPipePos));
						}
						else if (recPipePos.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_EMAIL))
						{
							vTempfiles_and_pipePos_KontrollMail.add(new E2_JasperTempFile_and_pipePos(this.hmAllePdfs.get(recPipePos.get_HashKey_4_FileList()),recPipePos));
						}
						else if (recPipePos.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_STANDARD))
						{
							vTempfiles_and_pipePos_Standard.add(new E2_JasperTempFile_and_pipePos(this.hmAllePdfs.get(recPipePos.get_HashKey_4_FileList()),recPipePos));
						} 
					}
				}		
			}
		}

		//2014-01-15: hier pruefen, ob teilreports leer sind (wenn gewuenscht)
		if (this.bEliminateEmptyJasperTempFiles) {
			vTempfiles_and_pipePos_Standard = 		this.eliminate_empty_tempfiles(vTempfiles_and_pipePos_Standard);
			vTempfiles_and_pipePos_DirektDruck = 	this.eliminate_empty_tempfiles(vTempfiles_and_pipePos_DirektDruck);
			vTempfiles_and_pipePos_Archiv = 			this.eliminate_empty_tempfiles(vTempfiles_and_pipePos_Archiv);
			vTempfiles_and_pipePos_KontrollMail = 	this.eliminate_empty_tempfiles(vTempfiles_and_pipePos_KontrollMail);

			if ((this.vTempfiles_and_pipePos_Archiv.size()+this.vTempfiles_and_pipePos_DirektDruck.size()+this.vTempfiles_and_pipePos_KontrollMail.size()+this.vTempfiles_and_pipePos_Standard.size())==0) {
				this.bResultIsEmpty=true;
				if (this.bResultIsEmpty && this.bShowErrorMessageWhenResultIsEmpty) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Report: ",true,this.get_cReportName4SearchInPipelineDB(),false," hat in nur leere Seiten produziert!",true).CTrans()));
				}
			}

		}



		//hier einen verarbeitungsstep, um die generierten pdfs zu beinflussen
		Jasper_Exe_Caller oJasper_Exe_Caller = new Jasper_Exe_Caller();
		//------------------------------------



		//2014-12-16: weiterer excuter fuer das wegspeichern der originale bei rechnugen
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		oJasper_Exe_Caller.ExecuterStart(this, null, EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES, oMV, null);
		bibMSG.add_MESSAGE(oMV);


		if (vTempfiles_and_pipePos_Standard.size()>0)
		{
			oJasperFileProcessorStd = new E2_JasperFileProcessor(vTempfiles_and_pipePos_Standard, this);
			bibMSG.add_MESSAGE(oJasperFileProcessorStd.make_File4StandardProcessing());
		}

		if (vTempfiles_and_pipePos_DirektDruck.size()>0)
		{
			//mehrfache meldung erlauben, weil diese meldung im stapelverlauf mehrfach auflaufen kann
			if (recListPipeLine.get(0).is_DIREKTDRUCKE_ZUSAMMENFASSEN_NO())
			{
				oJasperFileProcessorDirect = new E2_JasperFileProcessor(vTempfiles_and_pipePos_DirektDruck, this);
				bibMSG.add_MESSAGE_AllowDoubles(oJasperFileProcessorDirect.make_DirectPrinting());
			}
			else
			{
				oJasperFileProcessorDirect = new E2_JasperFileProcessor(vTempfiles_and_pipePos_DirektDruck, this);
				bibMSG.add_MESSAGE_AllowDoubles(oJasperFileProcessorDirect.make_DirectPrinting_Zusammengefasst());				
			}
		}

		if (vTempfiles_and_pipePos_Archiv.size()>0)
		{
			oJasperFileProcessorArchiv = new E2_JasperFileProcessor(vTempfiles_and_pipePos_Archiv, this);
			bibMSG.add_MESSAGE_AllowDoubles(oJasperFileProcessorArchiv.make_Archiving());
		}

		if (vTempfiles_and_pipePos_KontrollMail.size()>0)
		{
			oJasperFileProcessorControllEmail = new E2_JasperFileProcessor(vTempfiles_and_pipePos_KontrollMail, this);
			bibMSG.add_MESSAGE_AllowDoubles(oJasperFileProcessorControllEmail.make_ControllMailing());
		}

		//jetzt die (evtl.) aktion nach dem erzeugen des files
		this.doActionAfterCreatedFile();

		DEBUG.System_println("Verlasse  E2_JasperHash.Build_tempFile_NG", DEBUG.DEBUG_FLAG_DIVERS1);
	}



	/**
	 * 2014-01-15: bei so definiertem parameter sollten leere seiten aus den reports entfernt werden
	 */
	private E2_JasperTempFile_and_pipePos_VECT  eliminate_empty_tempfiles(E2_JasperTempFile_and_pipePos_VECT vTempfiles) {

		E2_JasperTempFile_and_pipePos_VECT vRueck = new E2_JasperTempFile_and_pipePos_VECT();

		for (int i=0;i<vTempfiles.size();i++) {
			if (vTempfiles.get(i).get_oJasperTempFile().get_intAnzahlSeitenNachGenerierung().intValue()!=0) {
				vRueck.add(vTempfiles.get(i));
			}
		}

		return vRueck;
	}



	/**
	 * Vorversion zum signieren von PDF-Dokumenten
	 * 
	 * @param filename
	 */
	public void signDocument(String filename){

		if (1==1){
			return;
		}



		KeyStore ks;
		String sTempfile = filename + ".ori";
		boolean bOK = false;
		try {
			// original-Datei
			File fOri = new File(filename);
			if (fOri.exists()){
				// fileobjekt erzeugen
				File temp = new File(sTempfile);
				// prüfen, ob es da ist, wenn ja, alte Datei löschen
				if(temp.exists()){
					temp.delete();
				}

				// datei verschieben
				bOK = fOri.renameTo(temp);
			}
			if (!bOK){
				return;
			}





			ks = KeyStore.getInstance(KeyStore.getDefaultType());

			ks.load(new FileInputStream(bibALL.get_REPORTPATH_MANDANT() + "keystore.ks"), "panter".toCharArray());
			String alias = (String)ks.aliases().nextElement();
			PrivateKey key = (PrivateKey)ks.getKey(alias, "panter".toCharArray());
			Certificate[] chain = ks.getCertificateChain(alias);
			PdfReader reader = new PdfReader(sTempfile);

			// das signierte Dokument wird an den original-Platz geschrieben
			FileOutputStream fout = new FileOutputStream(filename);
			PdfStamper stp = PdfStamper.createSignature(reader, fout, '\0');
			PdfSignatureAppearance sap = stp.getSignatureAppearance();
			sap.setCrypto(key, chain, null, PdfSignatureAppearance.SELF_SIGNED);
			sap.setReason("I'm the author");
			sap.setLocation("OFFENBURG");
			// comment next line to have an invisible signature
			sap.setVisibleSignature(new Rectangle(0, 0, 100, 10), 1, null);
			stp.close();


			// jetzt das tempfile löschen
			File temp = new File(sTempfile);
			if(temp.exists()){
				temp.delete();
			}


		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}


	}




	public FileWithSendName get_oTempFileWithSendeName()
	{
		return oTempFileWithSendeName;
	}


	public void set_oTempFileWithSendeName(FileWithSendName ofile)
	{
		this.oTempFileWithSendeName=ofile;
	}




	//	public String get_cMailInformationFromFile()
	//	{
	//		return cMailInformationFromFile;
	//	}


	public JasperFileDef get_oJasperFileDef()
	{
		return oJasperFileDef;
	}

	public void set_oJasperFileDef(JasperFileDef jasperFileDef)
	{
		oJasperFileDef = jasperFileDef;
	}








	/**
	 * 
	 * @param cShellCommand (can be null  - when null system has a look to JT_REPORT_SETTING, whether there is as print-command
	 * @throws myException
	 */
	public void sendPdfFile_to_Printer(String cShellCommand) throws myException
	{
		E2_JasperFileProcessor.sendPdfFile_to_Printer(cShellCommand, this);
	}







	protected abstract MailBlock 	create_MailBlock() throws myException;
	public	  abstract boolean   	get_bIsDesignedForMail() throws myException;
	public    abstract void     	doActionAfterCreatedFile() throws myException;





	public void set_bVorschauDruck(boolean bVorschau)
	{
		this.put(E2_JasperHASH.HM_PREVIEW_YN,	bVorschau?"Y":"N");
	}

	public boolean get_bVorschauDruck()
	{
		String cWert = "N";
		if (this.get(E2_JasperHASH.HM_PREVIEW_YN)!=null && this.get(E2_JasperHASH.HM_PREVIEW_YN) instanceof String)
		{
			cWert=(String)this.get(E2_JasperHASH.HM_PREVIEW_YN);
		}

		return cWert.equals("Y")?true:false;

	}



	/*
	 * aenderung 2010-10-19: Hashkey DRUCKART =  /PREVIEW/PRINT/EMAIL  (Standard=Print)
	 */
	public void set_TYPE_PRINT()
	{
		this.put(E2_JasperHASH.HASH_TYPE, E2_JasperHASH.VALUE_PRINT);
	}
	public void set_TYPE_MAIL()
	{
		this.put(E2_JasperHASH.HASH_TYPE, E2_JasperHASH.VALUE_MAIL);
	}
	public void set_TYPE_PREVIEW()
	{
		this.put(E2_JasperHASH.HASH_TYPE, E2_JasperHASH.VALUE_PREVIEW);
	}


	public boolean get_IS_TYP_PRINT()
	{
		return ((String)this.get(E2_JasperHASH.HASH_TYPE)).equals(E2_JasperHASH.VALUE_PRINT);
	}

	public boolean get_IS_TYP_MAIL()
	{
		return ((String)this.get(E2_JasperHASH.HASH_TYPE)).equals(E2_JasperHASH.VALUE_MAIL);
	}

	public boolean get_IS_TYP_PREVIEW()
	{
		return ((String)this.get(E2_JasperHASH.HASH_TYPE)).equals(E2_JasperHASH.VALUE_PREVIEW);
	}



	public Vector<myTempFile> get_vFileContainer() 
	{
		return vFileContainer;
	}


	//	//aenderung 2010-11-17: druck-table-id fuer recordpipeline 
	//	public void set_HASH_ID_DRUCKTABLE(String ID_DRUCKTABLE)
	//	{
	//		this.put(E2_JasperHASH.HASH_ID_DRUCKTABLE, ID_DRUCKTABLE);
	//	}


	/*
	 * aenderung 2014-09-22: der Key ID_DRUCKTABLE speichert keinen String mehr, sondern einen Vector<String>
	 *                       damit es moeglich ist, z.b. mehreren fuhren den gleichen mehrfachlieferschein ins druckprotokoll zu haengen (
	 *                       mehrere eintraege in der tabelle JT_ARCHIVMEDIEN zeigen auf die gleiche archivdatei)
	 *                       ACHTUNG: die werte hinter HASH_ID_DRUCKTABLE werden intern bei der zuordnung der Archiv-dateien genutzt, sind
	 *                                NICHT zur Verwendung in anderen Prozessen gedacht!!!
	 */
	public void add_HASH_ID_DRUCKTABLE(String ID_DRUCKTABLE)
	{
		VectorString vID_DRUCKTABLE = new VectorString();
		if (this.get(E2_JasperHASH.HASH_ID_DRUCKTABLE)==null) {
			this.put(E2_JasperHASH.HASH_ID_DRUCKTABLE, vID_DRUCKTABLE);
		} else {
			vID_DRUCKTABLE = (VectorString)this.get(E2_JasperHASH.HASH_ID_DRUCKTABLE);
		}

		vID_DRUCKTABLE.add(ID_DRUCKTABLE);
	}



	/**
	 * 2014-10-22: rueckgabe des Vectors in HashID-Drucktable 
	 * @return s VectorString with size()=0 or size()>0 when id is preset
	 */
	public VectorString get_HASH_ID_DRUCKTABLE() {
		VectorString vRueck = new VectorString();

		if (this.get(E2_JasperHASH.HASH_ID_DRUCKTABLE) != null) {
			vRueck.addAll((VectorString)this.get(E2_JasperHASH.HASH_ID_DRUCKTABLE));
		}

		return vRueck;
	}



	public void set_bInactiv() {
		this.put(E2_JasperHASH.HASH_SET_JASPER_INAKTIV, "Y");
	}

	public void set_bActiv() {
		this.put(E2_JasperHASH.HASH_SET_JASPER_INAKTIV, "N");
	}


	public boolean get_bInactiv() {
		return ( (this.get(E2_JasperHASH.HASH_SET_JASPER_INAKTIV) != null) && 
				(this.get(E2_JasperHASH.HASH_SET_JASPER_INAKTIV) instanceof String) &&
				(S.NN((String)this.get(E2_JasperHASH.HASH_SET_JASPER_INAKTIV)).equals("Y"))
				);
	}

	public boolean get_bActive() {
		return !this.get_bInactiv();
	}


	public void set_Target_Type(String cMimeType) {
		this.put(E2_JasperHASH.HASHKEY_LIST_TARGET_TYPE, cMimeType);
	}


	public boolean get_bEliminateEmptyJasperTempFiles()
	{
		return bEliminateEmptyJasperTempFiles;
	}


	public void set_bEliminateEmptyJasperTempFiles(boolean bEliminateEmptyJasperTempFiles)
	{
		this.bEliminateEmptyJasperTempFiles = bEliminateEmptyJasperTempFiles;
	}

	public boolean get_bResultIsEmpty()
	{
		return bResultIsEmpty;
	}


	public boolean get_bShowErrorMessageWhenResultIsEmpty()
	{
		return bShowErrorMessageWhenResultIsEmpty;
	}


	public void set_bShowErrorMessageWhenResultIsEmpty(boolean bShowErrorMessageWhenResultIsEmpty)
	{
		this.bShowErrorMessageWhenResultIsEmpty = bShowErrorMessageWhenResultIsEmpty;
	}


	public Vector<Jasper_Exe_ROOT> get_vExecuters() {
		return vExecuters;
	}

	
	/**
	 * True: Es wird bei leerer Pipeline nur eine Warning ausgegeben, bei false (default) eine Alarm-Meldung
	 * @author manfred
	 * @date 01.06.2021
	 *
	 * @param bMessageAsWarning
	 */
	public void set_bWarningOnEmptyPipelineResult(boolean bMessageAsWarning) {
		this.bWarningOnEmptyPipelineResult = bMessageAsWarning;
	}
	
	public boolean get_bWarningOnEmptyPipelineResult() {
		return this.bWarningOnEmptyPipelineResult;
	}
	
	
	
	/**
	 * @since 16.08.2018
	 * @author sebastien
	 * @return report UUID
	 */
	public String get_reportUUID() {
		return report_uuid;
	}


	public E2_JasperTempFile_and_pipePos_VECT get_jasperTempFile_and_pipePos_VECT_standard() {
		return vTempfiles_and_pipePos_Standard;
	}

	public E2_JasperTempFile_and_pipePos_VECT get_jasperTempFile_and_pipePos_VECT_DirektDruck() {
		return vTempfiles_and_pipePos_DirektDruck;
	}

	public E2_JasperTempFile_and_pipePos_VECT get_jasperTempFile_and_pipePos_VECT_Archiv() {
		return vTempfiles_and_pipePos_Archiv;
	}

	public E2_JasperTempFile_and_pipePos_VECT get_jasperTempFile_and_pipePos_VECT_KontrollMail() {
		return vTempfiles_and_pipePos_KontrollMail;
	}



	public VEK<RECORD_REPORT_PIPELINE_POS_EXT> getPipelinePositions() {
		return pipelinePositions;
	}



}
