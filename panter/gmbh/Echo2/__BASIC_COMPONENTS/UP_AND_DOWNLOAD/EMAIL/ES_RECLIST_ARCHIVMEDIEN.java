package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;

public class ES_RECLIST_ARCHIVMEDIEN extends RECLIST_ARCHIVMEDIEN {

	public ES_RECLIST_ARCHIVMEDIEN(String tableName, String tableID) throws myException {
		super(new SELECT("*").
				from(_DB.ARCHIVMEDIEN).
				where(_DB.ARCHIVMEDIEN$ID_TABLE,tableID).
				and(_DB.ARCHIVMEDIEN$TABLENAME, new Archiver_Normalized_Tablename(tableName).get_TableBaseName()));
		
		
//		DEBUG.System_println(new SELECT("*").
//				from(_DB.ARCHIVMEDIEN).
//				where(_DB.ARCHIVMEDIEN$ID_TABLE,tableID).
//				and(_DB.ARCHIVMEDIEN$TABLENAME, new Archiver_Normalized_Tablename(tableName).get_TableBaseName()).toString());
		
		
	}

}
