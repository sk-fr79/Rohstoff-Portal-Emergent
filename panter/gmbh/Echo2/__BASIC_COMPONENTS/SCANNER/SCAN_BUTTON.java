package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_BUTTON extends SCAN_BUTTON_ABSTRACT1 {

	private SCAN_DESCRIPTION_IF  scanDescriptor = null;
	
	
//	public SCAN_BUTTON_scan2archiv(SCAN_DESCRIPTION_IF  popup4Upload) throws myException {
//		super();
//		this.scanDescriptor = popup4Upload;
//	}


	public SCAN_BUTTON(RECORD_SCANNER_SETTINGS_SPECIAL rec_Scan4ThisButton,SCAN_DESCRIPTION_IF  p_scanDescriptor) throws myException {
		super(rec_Scan4ThisButton);
		this.scanDescriptor = p_scanDescriptor;
	}

	public SCAN_BUTTON(RECORD_SCANNER_SETTINGS_SPECIAL rec_Scan4ThisButton,SCAN_DESCRIPTION_IF  p_scanDescriptor, MyE2_String cText4Button) throws myException {
		this(rec_Scan4ThisButton,p_scanDescriptor);
		this.setIcon(null);
		this.set_Text(cText4Button);
	}

	
	
	@Override
	public MyE2_MessageVector do_Process_Scan_ResultFiles(Collection<String> v_ResultsFileNamesWithPath) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		String cTABLENAME = 		this.scanDescriptor.get_ArchivBaseTable();
		String cTABLE_ID = 			this.scanDescriptor.get_ArchiveIdTable();
		String CARCHIV_START_PFAD = cTABLENAME+"_SCAN";
		
		String cPROGRAMM_KENNER  =  this.get_recScan4ThisButton().get_PROGRAMM_KENNER_cUF_NN("");
		
		myTempFile  oTFComplete = null;
		
		
		ArrayList<String> vResultsFileNamesWithPath = new ArrayList<String>(v_ResultsFileNamesWithPath);
		
		//ausgangsdatei fuer die archivierung
		
		//nur pdf-typen koennen concateniert werden
		if (vResultsFileNamesWithPath.size()>0 && this.get_recScan4ThisButton().get_FILETYPE_cUF_NN("").toUpperCase().equals("PDF")) {
			oTFComplete = this.get_ConcatenatedTempFilePdf(vResultsFileNamesWithPath, "scanner-temp-file");
			if (oTFComplete!=null) {
				oTFComplete.set_bDeleteAtFinalize(true);
			}
		} else if (vResultsFileNamesWithPath.size()>0) {
			throw new myException(this,"Handling of other Filetypes than pdf is not implemented yet !!");
		}
		
		
		if (oTFComplete==null) {
			oMV.add(new MyE2_Alarm_Message(new MyE2_String("Der Scanvorgang hat keine Datei erzeugt !")));
		} else {
			
			
			if (scanDescriptor.get_ScanIs4Download()) {
				oTFComplete.starteDownLoad(
						"SCAN_"+bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("--")+"_"+bibALL.get_cDateTimeNOWInverse()+".pdf", 
						JasperFileDef.MIMETYP_PDF);
			} else {
			
				//hier muessen tablename und tableid vorhanden sein, sonst exception
				if (S.isEmpty(this.scanDescriptor.get_ArchivBaseTable()) || S.isEmpty(this.scanDescriptor.get_ArchiveIdTable()) ) {
					throw new myException(this, "Archiv-Scan only possible with known Table-Name and ID");
				}
			
				//hier ist die archivierung
				//String cARCHIV_FileName_Without_counter = cTABLENAME+"_"+cTABLE_ID+"_SCAN_"+bibALL.get_cDateNOWInverse("")+"."+cENDING;
				String cARCHIV_FileName_Without_counter = SCAN__ENUM.translate(cTABLENAME)+"_"+cTABLE_ID+"_SCAN_"+myDateHelper.Zeitstempel(new Date())+"."+this.get_FileEnding();
				
				
				//archive wird jahr/monat eingereiht
				Archiver  oArchiver = new Archiver(CARCHIV_START_PFAD,new Date(), Archiver.ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH);
				
				//file an die 
				String cFileNameInArchiv = oArchiver.copyFilenameToNextFree(oTFComplete.getFileName(), cARCHIV_FileName_Without_counter );
				
				
	            oArchiver.WriteFileInfoToArchiveTable(	oArchiver.get_cSUB_DIR_IN_Archiv(),
	            										cFileNameInArchiv,
	            										cARCHIV_FileName_Without_counter,
	            											"Eingescannte Datei, "+bibALL.get_cDateNOW()+", "+
	            											bibALL.get_RECORD_USER().get_VORNAME_cUF_NN("-")+
	            											bibALL.get_RECORD_USER().get_NAME1_cUF_NN(bibALL.get_RECORD_USER().get_ID_USER_cUF()),
	            										null,   //mailadresse
	            										null,   //null
	            										this.scanDescriptor.get_ArchivBaseTable(),
	            										this.scanDescriptor.get_ArchiveIdTable(),
	            										this.get_FileEnding(),
	            										Archiver_CONST.MEDIENKENNER.SCANNER_FILE.get_DB_WERT(),
	            										"",   //vorgang-typ
	            										"",   //sonder-feld
	            										"",   //aktionspattern
	            										cPROGRAMM_KENNER  //PROGRAMMKENNER
	            										);
	 			
	            
	            //2015-05-18: gescanntes file an die liste anhaengen
	            String id_new_file = oArchiver.get_cLastNew_SEQ_ARCHIVMEDIEN();
	            if (S.isFull(id_new_file) && this.scanDescriptor!=null && this.scanDescriptor.get_ArchivNaviList() !=null) {
	            	
	            	this.scanDescriptor.get_ArchivNaviList().ADD_NEW_ID_TO_ALL_VECTORS(id_new_file);
	            	this.scanDescriptor.get_ArchivNaviList()._REBUILD_ACTUAL_SITE(null);
	            	
	            }
			}			
            
		}
		
		//aufraeumen
		this.cleanTempFiles(vResultsFileNamesWithPath);
//		if (oTFComplete!=null) {
//			//falls verkettet wurde, die verkettete Datei auch loeschen
//			oTFComplete.getFile().delete();
//			oTFComplete.close();
//		}

		return oMV;
	}

}
