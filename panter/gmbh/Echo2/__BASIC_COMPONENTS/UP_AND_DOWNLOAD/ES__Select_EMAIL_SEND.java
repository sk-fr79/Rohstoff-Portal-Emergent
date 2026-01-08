package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.query.SELECT;

public class ES__Select_EMAIL_SEND extends SELECT {
	public ES__Select_EMAIL_SEND(String cFieldlist, String tableNameBase, String id_Table) {
		//super("DISTINCT "+_DB.EMAIL_SEND+".*");
		super(cFieldlist);
		this.from(_DB.EMAIL_SEND)
			.join(_DB.EMAIL_SEND_ATTACH).on(	_DB.Z_EMAIL_SEND$ID_EMAIL_SEND, 	_DB.Z_EMAIL_SEND_ATTACH$ID_EMAIL_SEND)
			.join(_DB.ARCHIVMEDIEN).on(			_DB.Z_ARCHIVMEDIEN$ID_ARCHIVMEDIEN, _DB.Z_EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN)
			.where(	_DB.Z_ARCHIVMEDIEN$TABLENAME,tableNameBase)
			.and(	_DB.Z_ARCHIVMEDIEN$ID_TABLE, id_Table);
		
		//DEBUG.System_println(this.toString());
	}

}
