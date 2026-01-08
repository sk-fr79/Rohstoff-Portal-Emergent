package panter.gmbh.indep.archive;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FileNameCleaner;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myFileCopy;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


/**
 * Klasse, die alles enthaelt, um dateien zu archivieren, und die
 * eintraege in die archivtabelle zu schreiben 
 * 
 */
public class Archiver
{
	
	/**
	 * ENUM für die zeitliche Aufteilung des Archivordners
	 * YEAR  			2013/...
	 * MONTH  			2013-10/...
	 * DAY				2013-10-25/...
	 * YEAR_MONTH		2013/10/...
	 * YEAR_MONTH_DAY	2013/10/25/...
	 * YEAR_CALWEEK		2013/KW51/...
	 * 
	 * @author manfred
	 * @date   27.02.2013
	 */
	public enum ENUM_ARCHIV_AUFTEILUNG{YEAR, MONTH,DAY,YEAR_MONTH,YEAR_MONTH_DAY,YEAR_CALWEEK};
    
	
//    public static String MEDIENKENNER_EMAIL = "EMAIL";
//    public static String MEDIENKENNER_PRINT = "PRINT";
//    public static String MEDIENKENNER_UPLOAD = "UPLOAD";
//    public static String MEDIENKENNER_ORIGBELEG = "ORIGBELEG";   //2015-01-07
//    
//    
//    public static String MEDIENKENNER_INBOX_ANHANG = "INBOX_ANHANG";
//    public static String MEDIENKENNER_INBOX_ORIG   = "INBOX_ORIG";
//    
//    // bildmedien beim image-capturing einer kamera
//    public static String MEDIENKENNER_CAM_CAPTURE   = "CAM_CAPTURE";
//    
//    //spezieller medientyp fuer WEIGERMELDUNGEN
//    public static String MEDIENKENNER_WEIGERMELDUNG   = "WEIGERMELDUNG";
//    
//    public static String MEDIENKENNER_SCANNER_FILE   = "SCANNER_FILE";
    
    
//    public static String[][] MEDIENKENNER_SEL_ARRAY_OHNE_LEER = {{"e-Mail-Anlagen",Archiver.MEDIENKENNER_EMAIL},
//		    													{"Druck-Dateien",Archiver.MEDIENKENNER_PRINT},
//		    													{"Hochgeladene Dateien",Archiver.MEDIENKENNER_UPLOAD},
//		    													{"e-Mail Eingang Anlagen",Archiver.MEDIENKENNER_INBOX_ANHANG},
//		    													{"e-Mail Eingang Originale",Archiver.MEDIENKENNER_INBOX_ORIG},
//		    													{"Kamera-Bilddatei",Archiver.MEDIENKENNER_CAM_CAPTURE},
//		    													{"Scanner-Datei",Archiver.MEDIENKENNER_SCANNER_FILE},
//		    													{"Weigermeldung",Archiver.MEDIENKENNER_WEIGERMELDUNG},
//		    													};
		    
     
    
    private MyDBToolBox 	oDB	= null;
    
    /*
     * pfad zum archiv der webapp + zusatzpfad "MANDANT_0000000001"
     */
    private String			cArchiveBasePath = null;
    
    /*
     * pfad innerhalb des archiv-basis-pfads, der fuer dieses archiv genommen wird
     */
    private String 			cSUB_DIR_IN_Archive = null;

    
    private String 			cTO = null;

    /*
     * uebernimmt den letzten benutzten seq-wert 
     */
    private String 			cLastNew_SEQ_ARCHIVMEDIEN = null;
    
    
    
    /**
     */
    public Archiver(String cSub_DIR_IN_Archive) throws myException
    {
    	this(cSub_DIR_IN_Archive,null,null);
    }
    
    /**
     * initialisiert den Archiver, prüft das Unterverzeichnis und legt es ggf. an.
     * Falls dateOfObject und aufteilung vorhanden sind, wird an das vorgegebenen Unterverzeichnis 
     * noch eine Unterverzeichnisstruktur nach vorgegebener Aufteilung angehängt
     * 
     * Bsp:
     * Archiver("/INBOX/,datum(2013-10-23),ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY)  
     * erzeugt das Unterverzeichns:  INBOX/2013/10/23
     * 
     * Aufteilung siehe ENUM_ARCHIV_AUFTEILUNG
     * 
     * @author manfred
     * @date   27.02.2013
     * @param cSub_DIR_IN_Archive
     * @param dateOfObject
     * @param aufteilung
     * @throws myException
     */
    public Archiver(String cSub_DIR_IN_Archive, Date dateOfObject, ENUM_ARCHIV_AUFTEILUNG aufteilung ) throws myException{
    	super();
    	
    	String sTime = getDateSubdir(dateOfObject, aufteilung);
    	
        this.oDB = bibALL.get_myDBToolBox();

      
       this.cArchiveBasePath = Archiver.get_ARCHIV_BASE_PATH(true, false);
      
       this.cTO	= bibALL.get_TABLEOWNER();
       this.cSUB_DIR_IN_Archive = Archiver.truncate_FileSeparatorsFromPath( Archiver.truncate_FileSeparatorsFromPath(cSub_DIR_IN_Archive) + sTime );
      
       File oFile = new File(this.cArchiveBasePath+File.separator+this.cSUB_DIR_IN_Archive);
      
       if (oFile.exists())
          if (!oFile.isDirectory())
              throw new myException("Archiver:buildPathInArchivPath:"+this.cArchiveBasePath+File.separator+this.cSUB_DIR_IN_Archive+" exists, but NO DIRECTORY !!!");
          
       if (!oFile.exists())
         if (!oFile.mkdirs())
             throw new myException("Archiver:buildPathInArchivPath:"+this.cArchiveBasePath+File.separator+this.cSUB_DIR_IN_Archive+" not existing, but CANNOT CREATE !!!");
    	
    	
    }
    
    
    /**
     * Erzeugt den String für die zeitliche Trennung der Archivdateien in Unterverzeichnissen
     * MIT führendem Trenner
     * @author manfred
     * @date   27.02.2013
     * @param dateOfObject
     * @param aufteilung
     * @return
     */
    private String getDateSubdir( Date dateOfObject, ENUM_ARCHIV_AUFTEILUNG aufteilung){
    	String sTime = "";
    	
    	// Wenn ein Datum angegeben ist und die Aufteilung auch, dann die Unterverzeichnisse generieren
    	if (dateOfObject != null && aufteilung != null){
    		SimpleDateFormat df;
    		switch (aufteilung) {
    		case YEAR:
    			df = new SimpleDateFormat("yyyy");
    			sTime = File.separator + df.format(dateOfObject);
    			break;
    		case MONTH:
    			df = new SimpleDateFormat("yyyy-MM");
    			sTime = File.separator + df.format(dateOfObject);
    			break;
    		case DAY:
    			df = new SimpleDateFormat("yyyy-MM-dd");
    			sTime = File.separator + df.format(dateOfObject);
    			break;
    		case YEAR_MONTH:
    			df = new SimpleDateFormat("yyyy");
    			sTime = File.separator + df.format(dateOfObject);
    			
    			df = new SimpleDateFormat("MM");
    			sTime += File.separator + df.format(dateOfObject);
    			break;
    		case YEAR_MONTH_DAY:
    			df = new SimpleDateFormat("yyyy");
    			sTime = File.separator + df.format(dateOfObject);
    			
    			df = new SimpleDateFormat("MM");
    			sTime += File.separator + df.format(dateOfObject);
    			
    			df = new SimpleDateFormat("dd");
    			sTime += File.separator + df.format(dateOfObject);
    			
    			break;
    		case YEAR_CALWEEK:
    			df = new SimpleDateFormat("yyyy");
    			sTime = File.separator + df.format(dateOfObject);
    			
    			// week in year
    			df = new SimpleDateFormat("ww");
    			sTime += File.separator + "KW" + df.format(dateOfObject);
    			break;
    			
    		default:
    			sTime = "";
    			break;
    		}
    	}
    	return sTime;
    }
    
    
    /**
     * 2011-12-09:  neue methode
     * @param bFileSeparatorAnfang
     * @param bFileSeparatorAmEnde
     * @return
     */
    public static String get_ARCHIV_BASE_PATH(boolean bFileSeparatorAnfang, boolean bFileSeparatorAmEnde)
    {
        String cString_Mandant = "MANDANT_"+bibALL.fillString(bibALL.get_ID_MANDANT(),10,'0',true);
        String cRueck = bibALL.get_WEBROOTPATH()+File.separator+bibALL.get_ARCHIVPATH()+File.separator+cString_Mandant;
        
        if (bFileSeparatorAnfang)
        {
        	cRueck = File.separator+cRueck;
        }
        
        if (bFileSeparatorAmEnde)
        {
        	cRueck = cRueck+File.separator;
        }
        
        return cRueck;
        
    }
    
    
    /**
     * @param cQuellFile = 		kompletter dateiname mit pfad der ausgangsdatei
     * @param cNameFuerZielDatei=	nur der dateiname, ohne pfad, der Archiver kennt den richtigen pfad 	
     * Sucht eine freie namensnummer nach dem typ cNameFuerZielDatei_1  ...
     * und kopiert die quelldatei dorthin
     * @Return den erzeugten dateinamen
     */
    public String copyFilenameToNextFree(String cQuellFile,String cNameFuerZielDatei) throws myException
    {
        String cRueck = "";
        
        //2016-01-14: filename fuer zieldatei saeubern
        cNameFuerZielDatei = new FileNameCleaner(cNameFuerZielDatei).get_filename_clean();
        
        /*
         * zuerst suchen, ob es schon dateien mit diesem basis-namen gegeben hat (immer hinter die
         * bereits bestehenden anordnen
         */
        String cPathPlusName = 		this.get_cCompleteArchivePath()+File.separator+cNameFuerZielDatei;
        
        File 	fDir	= new File(this.get_cCompleteArchivePath());
        if (!fDir.isDirectory())
            throw new myException("Archiver:copyFilenameToNextFree:Error identifiying ");
        
        String[] cList = fDir.list(new FileSelector(cNameFuerZielDatei));
        
        /*
         * jetzt die nummer raussuchen 
         */
        int iNextNummer = 0;
        try
        {
	        for (int i=0;i<cList.length;i++)
	        {
	            String cNum = cList[i].substring(cNameFuerZielDatei.length()+1).trim();
	            if (!cNum.equals(""))
	            {
	                Integer intNext = new Integer(cNum);
	                if (intNext.intValue()>=iNextNummer)
	                {
	                    iNextNummer =intNext.intValue()+1; 
	                }
	            }
	        }
        }
        catch (Exception ex)
        {
            throw new myException("Archiver:copyFilenameToNextFree:Error checking next free fileplace: "+ex.getLocalizedMessage());
        }
        
        File oFileTest = new File(cPathPlusName+"_"+iNextNummer);
        if (! oFileTest.exists())
        {
            new myFileCopy(cQuellFile,cPathPlusName+"_"+iNextNummer);
            cRueck = cNameFuerZielDatei+"_"+iNextNummer;
        }
        else
            throw new myException("Archiver:copyFilenameToNextFree:Error finding next free fileplace ");

        
        
        return cRueck;
    }
    
    
    public static String getFileNameFrom_CompletePathName(String cNameWithPath)
    {
        String cRueck = "";
        
        int iPos = cNameWithPath.lastIndexOf(File.separator);
        
        if (iPos > 0)
        {
            cRueck = cNameWithPath.substring(iPos+1);
        }
        return cRueck;
    }
    
    public static String getPathNameFrom_CompletePathName(String cNameWithPath)
    {
        String cRueck = "";
        
        int iPos = cNameWithPath.lastIndexOf(File.separator);
        
        if (iPos > 0)
        {
            cRueck = cNameWithPath.substring(0,iPos);
        }
        return cRueck;
    }
   
    
    

    /**
     * @param cPFAD ist der relative pfad innerhalb des archivs fuer den mandanten
     * @param cDATEINAME
     * @param cDATEINAME_FUER_DOWNLOAD
     * @param cDATEIBESCHREIBUNG
     * @param cMAILADRESSE
     * @param bMailOK
     * @param cREF_TABLE
     * 	 
	 * Wichtig! Der Name der Tabelle (z.B. JT_ADRESSE wird von dem JT_ befreit,
	 * 			da sonst die select - substitution der Tabellennamen zu den Views 
	 * 			auf auf die Tabellen-Werte der Abfragen angewendet wird - damit kriegt man keine
	 *          rueckgabe !!!
	 *
     * @param cREF_TABLE_ID
     * @param cMEDIENTYP_DateiEndung  falls nicht null, wird versucht, den id_medien_typ zu finden
     * @param cMEDIENKENNER ist als statische String in Archiver hinterlegt: EMAIL,PRINT,UPLOAD
     * @param cVORGANG_TYP wird (falls es ein Archivierter Vorgang ist, auch mitgeschriebenm sonst null
     * @param cSonderFeld kann beliebige infos enthalten
     * @param cAKTIONSPATTERN ist ein beliebiges pattern, was eine gruppe von eintraegen definiert
     * 							(z.b. fuer eine e-Mail-sendeaktion oder so was)
     * 
     * @param cPROGRAMM_KENNER ist ein String, der bestimmte programmgesteuerte bilder definiert (z.B. unterschrift oder passfoto)
     * 
     * @throws myException
     */
    public void WriteFileInfoToArchiveTable(   	String cPFAD,
                    							String cDATEINAME,
                    							String cDATEINAME_FUER_DOWNLOAD,
                    							String cDATEIBESCHREIBUNG,
                    							String cMAILADRESSE,
                    							String cMailOK,
                    							String REF_TABLE,
                    							String cREF_TABLE_ID,
                    							String cMEDIENTYP_DateiEndung,
                    							String cMEDIENKENNER,
                    							String cVORGANG_TYP,
                    							String cSonderFeld,
                    							String cAKTIONSPATTERN,
                    							String cPROGRAMM_KENNER) throws myException          //2011-12-06: neues feld: programmkenner
                    							 
    {
    	String cREF_TABLE = REF_TABLE;
    	
    	
        /*
         * zuerst versuchen, den id_medientyp zu finden
         */
        String cIDMedienTyp = "NULL";
        
        //aenderung 20101116: medientyp kann auch mit punkt uebergeben werden  (z.B. endung: .pdf)
        String cMedienTyp = cMEDIENTYP_DateiEndung;
        if (cMedienTyp.startsWith("."))
        {
        	cMedienTyp = cMedienTyp.substring(1);
        }
        if (!bibALL.null2leer(cMedienTyp).trim().equals("")) 
        {
            String cHelp = bibALL.MakeSql(cMedienTyp.trim().toUpperCase());
            
            cIDMedienTyp = this.oDB.EinzelAbfrage("SELECT ID_MEDIENTYP FROM "+this.cTO+".JT_MEDIENTYP WHERE UPPER(DATEIENDUNG)="+cHelp,"","","@@@");
        }
        if (cIDMedienTyp.equals("@@@"))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT query ID_MEDIENTYP");
        
        
        //aenderung:2010-11-16: Medientyp -ID MUSS gefunden werden !! Sonst kann kein download-typ definiert werden
        if (S.isEmpty(cIDMedienTyp))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT query ID_MEDIENTYP");
        
        
        if (!(cMailOK==null || cMailOK.equals("")||cMailOK.equals("Y")||cMailOK.equals("N")))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: Parameter cMailOK only emtpy String or Y or N allowed !!");

        
        
        
        /*
         * jetzt die ref-table (falls vorhanden) von JT_ und JD_ befreien
         */
        if (cREF_TABLE.toUpperCase().startsWith("JT_") || cREF_TABLE.toUpperCase().startsWith("JD_"))
            cREF_TABLE = cREF_TABLE.substring(3).toUpperCase();
        
        
        String cSQL = "INSERT INTO "+this.cTO+".JT_ARCHIVMEDIEN " +
        							"(ID_ARCHIVMEDIEN,ERSTELLUNGSDATUM,AENDERUNGSDATUM,PFAD,DATEINAME,DOWNLOADNAME,DATEIBESCHREIBUNG,MAILADRESSE,MAILERFOLGREICH," +
        							"TABLENAME,ID_TABLE,ID_MEDIENTYP,MEDIENKENNER, VORGANG_TYP, AKTIONSPATTERN,SONDERFELD, PROGRAMM_KENNER)  VALUES (" +
        								"SEQ_ARCHIVMEDIEN.NEXTVAL,"+
										"SYSDATE,"+
										"SYSDATE,"+
										this.MakeSql(cPFAD)+","+
										this.MakeSql(cDATEINAME)+","+
										this.MakeSql(cDATEINAME_FUER_DOWNLOAD)+","+
										this.MakeSql(cDATEIBESCHREIBUNG)+","+
										this.MakeSql(cMAILADRESSE)+","+
										this.MakeSql(cMailOK)+","+
										this.MakeSql(cREF_TABLE)+","+
										cREF_TABLE_ID.trim()+","+
										cIDMedienTyp+","+
										this.MakeSql(cMEDIENKENNER)+","+
										this.MakeSql(cVORGANG_TYP)+","+
										this.MakeSql(cAKTIONSPATTERN)+","+
										this.MakeSql(cSonderFeld)+","+
										this.MakeSql(cPROGRAMM_KENNER)+
										")";
        
        this.cLastNew_SEQ_ARCHIVMEDIEN = null;
        
        if (! this.oDB.ExecSQL(cSQL,true))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT write JT_ARCHIVMEDIEN-dataset !!");
        else
        {
        	this.cLastNew_SEQ_ARCHIVMEDIEN=oDB.EinzelAbfrage("SELECT "+this.cTO+".SEQ_ARCHIVMEDIEN.CURRVAL FROM DUAL");
        	if (!bibALL.isInteger(this.cLastNew_SEQ_ARCHIVMEDIEN))
        		this.cLastNew_SEQ_ARCHIVMEDIEN=null;
        	
        }
        
        
    }
    
    
    
//    //2015-01-08: neue methode, liefert sql-statement zurueck, statt den eintrag direkt zu schreiben
//    /**
//     * 
//     * @param cPFAD
//     * @param cDATEINAME
//     * @param cDATEINAME_FUER_DOWNLOAD
//     * @param cDATEIBESCHREIBUNG
//     * @param cMAILADRESSE
//     * @param cMailOK
//     * @param REF_TABLE
//     * @param cREF_TABLE_ID
//     * @param cMEDIENTYP_DateiEndung
//     * @param cMEDIENKENNER
//     * @param cVORGANG_TYP
//     * @param cSonderFeld
//     * @param cAKTIONSPATTERN
//     * @param cPROGRAMM_KENNER
//     * @return String[2], [0]=erstellte seq_archivmedien, [1]=sql-statement
//     * @throws myException
//     */
//    public String[] get_sql_4_WriteFileInfoToArchiveTable(String cPFAD,
//                    							String cDATEINAME,
//                    							String cDATEINAME_FUER_DOWNLOAD,
//                    							String cDATEIBESCHREIBUNG,
//                    							String cMAILADRESSE,
//                    							String cMailOK,
//                    							String REF_TABLE,
//                    							String cREF_TABLE_ID,
//                    							String cMEDIENTYP_DateiEndung,
//                    							String cMEDIENKENNER,
//                    							String cVORGANG_TYP,
//                    							String cSonderFeld,
//                    							String cAKTIONSPATTERN,
//                    							String cPROGRAMM_KENNER,
//                    							boolean bIstOriginalBeleg) throws myException          //2011-12-06: neues feld: programmkenner
//                    							 
//    {
//    	String[] cRueck = new String[2];
//    	
//    	String cREF_TABLE = REF_TABLE;
//    	
//    	
//        /*
//         * zuerst versuchen, den id_medientyp zu finden
//         */
//        String cIDMedienTyp = "NULL";
//        
//        //aenderung 20101116: medientyp kann auch mit punkt uebergeben werden  (z.B. endung: .pdf)
//        String cMedienTyp = cMEDIENTYP_DateiEndung;
//        if (cMedienTyp.startsWith("."))
//        {
//        	cMedienTyp = cMedienTyp.substring(1);
//        }
//        if (!bibALL.null2leer(cMedienTyp).trim().equals("")) 
//        {
//            String cHelp = bibALL.MakeSql(cMedienTyp.trim().toUpperCase());
//            
//            cIDMedienTyp = this.oDB.EinzelAbfrage("SELECT ID_MEDIENTYP FROM "+this.cTO+".JT_MEDIENTYP WHERE UPPER(DATEIENDUNG)="+cHelp,"","","@@@");
//        }
//        if (cIDMedienTyp.equals("@@@"))
//            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT query ID_MEDIENTYP");
//        
//        
//        //aenderung:2010-11-16: Medientyp -ID MUSS gefunden werden !! Sonst kann kein download-typ definiert werden
//        if (S.isEmpty(cIDMedienTyp))
//            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT query ID_MEDIENTYP");
//        
//        
//        if (!(cMailOK==null || cMailOK.equals("")||cMailOK.equals("Y")||cMailOK.equals("N")))
//            throw new myException("Archiver:WriteFileInfoToArchiveTable: Parameter cMailOK only emtpy String or Y or N allowed !!");
//
//        
//        //hier wird der folgende seq-wert abgefragt und zurueckgegeben
//        String cID_ARCHIVMEDIEN_NEXT = bibDB.get_NextSequenceValueOfTable(_DB.ARCHIVMEDIEN);
//        
//        /*
//         * jetzt die ref-table (falls vorhanden) von JT_ und JD_ befreien
//         */
//        if (cREF_TABLE.toUpperCase().startsWith("JT_") || cREF_TABLE.toUpperCase().startsWith("JD_"))
//            cREF_TABLE = cREF_TABLE.substring(3).toUpperCase();
//        
//        
//        String cSQL = "INSERT INTO "+this.cTO+".JT_ARCHIVMEDIEN " +
//        							"(ID_ARCHIVMEDIEN,ERSTELLUNGSDATUM,AENDERUNGSDATUM,PFAD,DATEINAME,DOWNLOADNAME,DATEIBESCHREIBUNG,MAILADRESSE,MAILERFOLGREICH," +
//        							"TABLENAME,ID_TABLE,ID_MEDIENTYP,MEDIENKENNER, VORGANG_TYP, AKTIONSPATTERN,SONDERFELD, PROGRAMM_KENNER, IST_ORIGINAL)  VALUES (" +
//        								cID_ARCHIVMEDIEN_NEXT+","+
//										"SYSDATE,"+
//										"SYSDATE,"+
//										this.MakeSql(cPFAD)+","+
//										this.MakeSql(cDATEINAME)+","+
//										this.MakeSql(cDATEINAME_FUER_DOWNLOAD)+","+
//										this.MakeSql(cDATEIBESCHREIBUNG)+","+
//										this.MakeSql(cMAILADRESSE)+","+
//										this.MakeSql(cMailOK)+","+
//										this.MakeSql(cREF_TABLE)+","+
//										cREF_TABLE_ID.trim()+","+
//										cIDMedienTyp+","+
//										this.MakeSql(cMEDIENKENNER)+","+
//										this.MakeSql(cVORGANG_TYP)+","+
//										this.MakeSql(cAKTIONSPATTERN)+","+
//										this.MakeSql(cSonderFeld)+","+
//										this.MakeSql(cPROGRAMM_KENNER)+","+
//										(bIstOriginalBeleg?"'Y'":"'N'")+
//										")";
//        
//        this.cLastNew_SEQ_ARCHIVMEDIEN = cID_ARCHIVMEDIEN_NEXT;
// 
//        cRueck[0]=cID_ARCHIVMEDIEN_NEXT;
//        cRueck[1]=cSQL;
//        
//        return cRueck;
//        
//    }

    
    //2015-01-22: neue methode, liefert RECORDNEW zurueck
    /**
     * 
     * @param cPFAD
     * @param cDATEINAME
     * @param cDATEINAME_FUER_DOWNLOAD
     * @param cDATEIBESCHREIBUNG
     * @param cMAILADRESSE
     * @param cMailOK
     * @param REF_TABLE
     * @param cREF_TABLE_ID
     * @param cMEDIENTYP_DateiEndung
     * @param cMEDIENKENNER
     * @param cVORGANG_TYP
     * @param cSonderFeld
     * @param cAKTIONSPATTERN
     * @param cPROGRAMM_KENNER
     * @param bIstOriginalbeleg
     * @return RECORDNEW-class
     * @throws myException
     */
    public RECORDNEW_ARCHIVMEDIEN get_RECNEW_ARCHIVMEDIEN(String cPFAD,
                    							String cDATEINAME,
                    							String cDATEINAME_FUER_DOWNLOAD,
                    							String cDATEIBESCHREIBUNG,
                    							String cMAILADRESSE,
                    							String cMailOK,
                    							String REF_TABLE,
                    							String cREF_TABLE_ID,
                    							String cMEDIENTYP_DateiEndung,
                    							String cMEDIENKENNER,
                    							String cVORGANG_TYP,
                    							String cSonderFeld,
                    							String cAKTIONSPATTERN,
                    							String cPROGRAMM_KENNER,
                    							boolean bIstOriginalBeleg,
                    							MyE2_MessageVector oMV4InsertChecks) throws myException          //2011-12-06: neues feld: programmkenner
                    							 
    {
    	String cREF_TABLE = REF_TABLE;
        if (cREF_TABLE.toUpperCase().startsWith("JT_") || cREF_TABLE.toUpperCase().startsWith("JD_")) {
            cREF_TABLE = cREF_TABLE.substring(3).toUpperCase();
        }

    	
        RECORDNEW_ARCHIVMEDIEN  oRECNEW = new RECORDNEW_ARCHIVMEDIEN();
    	
        /*
         * zuerst versuchen, den id_medientyp zu finden
         */
        String cIDMedienTyp = "NULL";
        
        //aenderung 20101116: medientyp kann auch mit punkt uebergeben werden  (z.B. endung: .pdf)
        String cMedienTyp = cMEDIENTYP_DateiEndung;
        if (cMedienTyp.startsWith("."))
        {
        	cMedienTyp = cMedienTyp.substring(1);
        }
        if (!bibALL.null2leer(cMedienTyp).trim().equals("")) 
        {
            String cHelp = bibALL.MakeSql(cMedienTyp.trim().toUpperCase());
            
            cIDMedienTyp = this.oDB.EinzelAbfrage("SELECT ID_MEDIENTYP FROM "+this.cTO+".JT_MEDIENTYP WHERE UPPER(DATEIENDUNG)="+cHelp,"","","@@@");
        }
        if (cIDMedienTyp.equals("@@@"))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT query ID_MEDIENTYP");
        
        
        //aenderung:2010-11-16: Medientyp -ID MUSS gefunden werden !! Sonst kann kein download-typ definiert werden
        if (S.isEmpty(cIDMedienTyp))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: CANNOT query ID_MEDIENTYP");
        
        
        if (!(cMailOK==null || cMailOK.equals("")||cMailOK.equals("Y")||cMailOK.equals("N")))
            throw new myException("Archiver:WriteFileInfoToArchiveTable: Parameter cMailOK only emtpy String or Y or N allowed !!");

 
        oRECNEW.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(_DB.ARCHIVMEDIEN$ERSTELLUNGSDATUM,"SYSDATE");
        oRECNEW.set_NewValueForDatabase_RAW_AS_STRING_IN_STATEMENT(_DB.ARCHIVMEDIEN$AENDERUNGSDATUM, "SYSDATE");
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_PFAD(cPFAD));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_DATEINAME(cDATEINAME));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_DOWNLOADNAME(cDATEINAME_FUER_DOWNLOAD));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_DATEIBESCHREIBUNG(cDATEIBESCHREIBUNG));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_MAILADRESSE(cMAILADRESSE));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_MAILERFOLGREICH(cMailOK));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_TABLENAME(cREF_TABLE));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_ID_TABLE(cREF_TABLE_ID.trim()));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_ID_MEDIENTYP(cIDMedienTyp));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_MEDIENKENNER(cMEDIENKENNER));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_VORGANG_TYP(cVORGANG_TYP));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_AKTIONSPATTERN(cAKTIONSPATTERN));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_SONDERFELD(cSonderFeld));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_PROGRAMM_KENNER(cPROGRAMM_KENNER));
        oMV4InsertChecks.add_MESSAGE(oRECNEW.set_NEW_VALUE_IST_ORIGINAL((bIstOriginalBeleg?"Y":"N")));
        
        return oRECNEW;
        
    }

    
    
    
    // fügt in einem sql-string für ein vorhandenes ' ein zweites ein  
    private String MakeSql(String pAusgangsString)
    {
        if (pAusgangsString == null || pAusgangsString.equals(""))
            return "NULL";
        
        String RueckString = "";

        RueckString = "'" +bibALL.MakeSqlInnerString(pAusgangsString)  + "'";

        if (RueckString.equals("''"))
        {
            RueckString = "null";
        }

        return (RueckString);
    }

    
    
    
    
    
    
    
    /**
     * @returns basepath of archivedirectory in web-application (with File.separator on left, not on right)
     */
    public String get_cArchiveBasePath()
    {
        return cArchiveBasePath;
    }    
    

    /**
     * @returns path in basepath of archivedirectory  (without File.separator)
     */
   public String get_cSUB_DIR_IN_Archiv()
    {
        return cSUB_DIR_IN_Archive;
    }
    
    /**
     * @return complete path of this archive (with File.separator on left, not on right)
     */
    public String get_cCompleteArchivePath()
    {
        return this.cArchiveBasePath+File.separator+this.cSUB_DIR_IN_Archive;
    }
    
    
    
    
    public static String truncate_FileSeparatorsFromPath(String cPath)
    {
        String cRueck = cPath.trim();
        
        if (cRueck.startsWith(File.separator))
            cRueck = cRueck.substring(1);
        
        if (cRueck.endsWith(File.separator))
            cRueck = cRueck.substring(0,cRueck.length()-1);
        
        return cRueck;
    }
    
    
    
    
    public static class FileSelector implements FilenameFilter
    {

        private String cBaseName = null;

        public FileSelector(String baseName)
        {
            super();
            cBaseName = baseName;
        }
        
        
        public boolean accept(File dir, String name)
        {
            if (name.startsWith(this.cBaseName))
                return true;
            else
                return false;
        }
        
    }




	public String get_cLastNew_SEQ_ARCHIVMEDIEN()
	{
		return cLastNew_SEQ_ARCHIVMEDIEN;
	}
    
    
    
    /**
     * @param cARCHIVE_BASE_NAME
     * @param cZUSATZINFO
     * @return s Standard-conform archivname for zip-archives
     */
    public static String CreateArchiver_Name_ohneEndung(String cARCHIVE_BASE_NAME,String cZUSATZINFO)
    {
    	String cBaseName = bibALL.null2leer(cARCHIVE_BASE_NAME);
    	cBaseName = bibALL.fillString(cBaseName,20,'_',false);
    	
       	String cZUSATZ = bibALL.null2leer(cZUSATZINFO);
       	cZUSATZ = bibALL.fillString(cZUSATZINFO,20,'_',false);
  	
    	String cMandant = "M"+bibALL.fillString(bibALL.get_ID_MANDANT(),2,'0',true);
    	String cKuerzel = "K"+bibALL.fillString(bibALL.get_KUERZEL(),4,'_',true);
    	
    	SimpleDateFormat oSD = new SimpleDateFormat("yyyyMMdd-HHmmss");
    	String cDATUM = oSD.format(new GregorianCalendar().getTime());
    	
    	return cBaseName+"_"+cZUSATZ+"_"+cDATUM+"_"+cKuerzel+"_"+cMandant;
    }
	
	
    
    
    public static File get_File(RECORD_ARCHIVMEDIEN  recArchivMedien) throws myException
    {
    	
    	if (recArchivMedien==null)
    	{
    		throw new myException("Archiver:get_File():Empty Record is not allowed !");
    	}

    	if (S.isEmpty(recArchivMedien.get_PFAD_cUF_NN("")) || S.isEmpty(recArchivMedien.get_DATEINAME_cUF_NN("")))
    	{
    		throw new myException("Archiver:get_File():Record has no Path or Filename");
    	}

    	
		String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
		String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(recArchivMedien.get_PFAD_cUF());
		String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(recArchivMedien.get_DATEINAME_cUF());
		String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
		
		return new File(cCompletePath);
		
		
    }
    

    /**
     * 2018-08-29: neue methode
     * @param recArchivMedien
     * @return
     * @throws myException
     */
    public static File getFile(Rec20  recArchivMedien) throws myException   {
    	
    	if (recArchivMedien==null) {
    		throw new myException("Archiver:get_File():Empty Record is not allowed !");
    	}

    	if (S.isEmpty(recArchivMedien.getUfs(ARCHIVMEDIEN.pfad, ""))  || S.isEmpty(recArchivMedien.getUfs(ARCHIVMEDIEN.dateiname, ""))) 	{
    		throw new myException("Archiver:get_File():Record has no Path or Filename");
    	}

    	
		String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
		String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(recArchivMedien.getUfs(ARCHIVMEDIEN.pfad));
		String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(recArchivMedien.getUfs(ARCHIVMEDIEN.dateiname));
		String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
		
		return new File(cCompletePath);
		
		
    }
    

    

    /**
     * 
     * @param recArchivMedien
     * @return compete path to archiv-file
     * @throws myException
     */
    public static String get_FileName(RECORD_ARCHIVMEDIEN  recArchivMedien) throws myException
    {
    	
    	if (recArchivMedien==null)
    	{
    		throw new myException("Archiver:get_File():Empty Record is not allowed !");
    	}

    	if (S.isEmpty(recArchivMedien.get_PFAD_cUF_NN("")) || S.isEmpty(recArchivMedien.get_DATEINAME_cUF_NN("")))
    	{
    		throw new myException("Archiver:get_File():Record has no Path or Filename");
    	}

    	
		String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
		String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(recArchivMedien.get_PFAD_cUF());
		String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(recArchivMedien.get_DATEINAME_cUF());
		String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
		
		return cCompletePath;
    }
    
    
    private String tableName;
    public void setTableName(String tableName) {
    	this.tableName = tableName;
    }
    
    
    private Archiver_CONST.MEDIENKENNER medienKenner;
    public void setMedienKenner(Archiver_CONST.MEDIENKENNER mk) {
    	this.medienKenner = mk;
    }

    
    /**
     * Copies the file tempFileName residing in tempFilePath to the specified archive directory,
     * associates it with the tableIdValue in tableName, describes it with fileDescription (in the
     * JT_ARCHIVMEDIEN table) and sets the Medienkenner. If the file system operation succeeds, 
     * the database entry is written; otherwise falls will be returned or an exception be thrown.
     * @author nilsandre
     * 		  -- returnwert auf tempfilename	
     * @param tempFilePath
     * @param tempFileName
     * @param tableIdValue
     * @param fileDescription
     * @return filename wenn ok, sonst null
     * @throws myException
     */
    public String writeTempFileToArchive(String tempFilePath, String tempFileName, String tableIdValue, String fileDescription) throws myException {
    	String sRet = null;
    	String fullTempFileName = tempFilePath + File.separator + tempFileName;
    	String extension =  tempFileName.substring(tempFileName.lastIndexOf(".")+1);
    	
		String targetFileName = this.copyFilenameToNextFree(fullTempFileName, tempFileName);
		
		if (targetFileName != null && !targetFileName.equals("")) {
			this.WriteFileInfoToArchiveTable(this.get_cSUB_DIR_IN_Archiv(), targetFileName, tempFileName, 
					fileDescription, null, null, tableName, tableIdValue, extension,
					medienKenner.get_DB_WERT(), null, null, null, null);
			sRet = targetFileName;
		}
		
		return sRet;
    }
    
    
    public String writeTempFileToArchive(String tempFilePath, String tempFileName, String tableIdValue, String fileDescription, String archive_file_name) throws myException {
    	String sRet = null;
    	String fullTempFileName = tempFilePath + File.separator + tempFileName;
    	String extension =  tempFileName.substring(tempFileName.lastIndexOf(".")+1);
    	
		String targetFileName = this.copyFilenameToNextFree(fullTempFileName, archive_file_name);
		
		if (targetFileName != null && !targetFileName.equals("")) {
			this.WriteFileInfoToArchiveTable(this.get_cSUB_DIR_IN_Archiv(), targetFileName, archive_file_name, 
					fileDescription, null, null, tableName, tableIdValue, extension,
					medienKenner.get_DB_WERT(), null, null, null, null);
			sRet = targetFileName;
		}
		
		return sRet;
    }
    

    
    
    /**
     * Beispiel eines public "Builders": Die eigentlich "zu bauende" klasse ist die äußere, 
     * deren Konstruktor privat sein sollte (das geht in dem Fall vom "Archiver" natürlich
     * nicht). 
     * @author nils
     *
     */
    public static class Builder {
        // mandatory parameter
        private final String archiveDir;
        private final Archiver_CONST.MEDIENKENNER mk;
        private String tableName = null; 
             
        // optional
        private String description = "";
                 
        public Builder(String archiveDir, Archiver_CONST.MEDIENKENNER mk) {
           this.archiveDir = archiveDir;
           this.mk = mk;
        }
        
        private Date date = null;
        private ENUM_ARCHIV_AUFTEILUNG aufteilung = null;
        
        public Builder setAufteilung(Date date, ENUM_ARCHIV_AUFTEILUNG aufteilung) {
        	this.date = date;
        	this.aufteilung = aufteilung;
			return this;
        }
        
        public Builder setTable(String tableName) {
           this.tableName = tableName; 
           return this;
        }
        public Builder approved(String description) {
           this.description = description;
           return this;
        } 
        public Archiver build() throws myException {
           Archiver answer = new Archiver(this.archiveDir, this.date, this.aufteilung);
           if (this.tableName != null) {
        	   answer.setTableName(tableName);
           }
           if (this.mk != null) {
        	   answer.setMedienKenner(mk);
           }
           return answer;
        }
     }
   
}
