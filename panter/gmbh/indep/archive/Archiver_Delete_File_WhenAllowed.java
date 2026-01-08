package panter.gmbh.indep.archive;

import java.io.File;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * prueft, ob eine datei hinter einem Archivbeleg nur in dem aktuelle RECORD verwendet wird.
 * wenn ja, dann darf geloescht werden
 * @author martin
 *
 */
public class Archiver_Delete_File_WhenAllowed {

	/**
	 * 
	 * @param cPFAD
	 * @param cDATEINAME
	 * @param bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN  (vor dem loeschen des RECORDs oder nachher)
	 * @throws myException
	 */
	public Archiver_Delete_File_WhenAllowed(String cPFAD, String cDATEINAME, boolean bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN) throws myException {
		this(cPFAD, cDATEINAME, bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN,bibMSG.MV());
//		
//		
//		RECLIST_ARCHIVMEDIEN  rlMedien = new RECLIST_ARCHIVMEDIEN("PFAD = " + bibALL.MakeSql(cPFAD) + " AND DATEINAME = " + bibALL.MakeSql(cDATEINAME) ,"");
//
////		RECORD_ARCHIVMEDIEN_ext recE = new RECORD_ARCHIVMEDIEN_ext();
////		recE.get__cCompletePathAndFileName()
//		
//		// nur löschen wenn keine Referenz auf die Datei besteht
//		int iNumberAllowedToDelete = bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN?0:1;
//		
//		if (rlMedien.size() <= iNumberAllowedToDelete ){
//			
//			String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
//			String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(cPFAD);
//			String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(cDATEINAME);
//			String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
//			
//			File oFile = new File(cCompletePath);
//			
//			if (oFile.delete()) {
//				// Manfred:  2013-03-07: nie den kompletten Pfad nach aussen rausgeben...
//				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(new MyE2_String("Folgende Datei wurde gelöscht: ").CTrans()+" -> " + cDocPath+File.separator+cDocName,false)));
//			} else {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(new MyE2_String("Folgende Datei konnte nicht gelöscht werden: ").CTrans()+" -> " + cDocPath+File.separator+cDocName,false)));
//			}
//		}

		
	}

	
	/**
	 * 
	 * @param cPFAD
	 * @param cDATEINAME
	 * @param bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN  (vor dem loeschen des RECORDs oder nachher)
	 * @throws myException
	 */
	public Archiver_Delete_File_WhenAllowed(String cPFAD, String cDATEINAME, boolean bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN, MyE2_MessageVector mv) throws myException {
		super();
		
		
		RECLIST_ARCHIVMEDIEN  rlMedien = new RECLIST_ARCHIVMEDIEN("PFAD = " + bibALL.MakeSql(cPFAD) + " AND DATEINAME = " + bibALL.MakeSql(cDATEINAME) ,"");

//		RECORD_ARCHIVMEDIEN_ext recE = new RECORD_ARCHIVMEDIEN_ext();
//		recE.get__cCompletePathAndFileName()
		
		// nur löschen wenn keine Referenz auf die Datei besteht
		int iNumberAllowedToDelete = bCheckIsAfterDelete_RECORD_ARCHIVMEDIEN?0:1;
		
		if (rlMedien.size() <= iNumberAllowedToDelete ){
			
			String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(new Archiver("").get_cArchiveBasePath());
			String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(cPFAD);
			String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(cDATEINAME);
			String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
			
			File oFile = new File(cCompletePath);
			
			if (oFile.delete()) {
				// Manfred:  2013-03-07: nie den kompletten Pfad nach aussen rausgeben...
				mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(new MyE2_String("Folgende Datei wurde gelöscht: ").CTrans()+" -> " + cDocPath+File.separator+cDocName,false)));
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(new MyE2_String("Folgende Datei konnte nicht gelöscht werden: ").CTrans()+" -> " + cDocPath+File.separator+cDocName,false)));
			}
		}

		
	}

	
}
