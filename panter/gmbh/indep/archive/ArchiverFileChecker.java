package panter.gmbh.indep.archive;

import java.io.File;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * Checks archive files for certain properties
 */
public class ArchiverFileChecker {
	private static String BASEPATH;
	static {
		if (BASEPATH == null) {
			try {
				BASEPATH = (new Archiver("")).get_cArchiveBasePath();
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String path;
	private String fileName;
	private String id;
	private File file;

	public ArchiverFileChecker(String path, String fileName) {
		this.path = path;
		this.fileName = fileName;
		
		//TODO: Should normalize path
		//TODO: Should be parametrized with ID
	}

	public boolean canDelete() {
		RECLIST_ARCHIVMEDIEN rlMedien;
		try {
			rlMedien = new RECLIST_ARCHIVMEDIEN("PFAD = " + bibALL.MakeSql(this.path) + " AND DATEINAME = " + bibALL.MakeSql(this.fileName) ,"");
			if (rlMedien.size() == 1) {
				String cBasePath	= 	Archiver.truncate_FileSeparatorsFromPath(BASEPATH);
				String cDocPath		=  	Archiver.truncate_FileSeparatorsFromPath(this.path);
				String cDocName		=  	Archiver.truncate_FileSeparatorsFromPath(this.fileName);
				String cCompletePath	= File.separator+cBasePath+File.separator+cDocPath+File.separator+cDocName;
				this.file = new File(cCompletePath);
				return true;
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public File getLastFile() {
		return file;
	}
}
