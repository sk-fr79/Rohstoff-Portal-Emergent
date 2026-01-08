package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.io.File;
import java.util.UUID;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.U;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class RECLIST_ARCHIVMEDIEN_ext extends RECLIST_ARCHIVMEDIEN {

	public RECLIST_ARCHIVMEDIEN_ext() throws myException {
		super();
	}

	
	
	public RECLIST_ARCHIVMEDIEN_ext(String QueryString) throws myException {
		super(QueryString);
	}



	/**
	 * relist is sorted by id_archivmedien
	 * @param TableName
	 * @param tableID
	 * @param Medienkenner
	 * @throws myException
	 */
	public RECLIST_ARCHIVMEDIEN_ext(String TableName, String tableID, MEDIENKENNER Medienkenner) throws myException{
		super("TABLENAME = '" + new Archiver_Normalized_Tablename(TableName).get_TableBaseName().trim() + "' AND ID_TABLE = " + tableID + " AND " + _DB.ARCHIVMEDIEN$MEDIENKENNER  + " = '" + Medienkenner.toString() +"'",_DB.ARCHIVMEDIEN$ID_ARCHIVMEDIEN);
	}


	
	/**
	 * 2015-04-23: martin
	 * @param TableName
	 * @param tableID
	 * @param Medienkenner 		Archiver_CONST.MEDIENKENNER can be null
	 * @param Programmkenner  	Archiver_CONST.PROGRAMMKENNER can be null
	 * @throws myException
	 */
	public RECLIST_ARCHIVMEDIEN_ext(String TableName, String tableID, MEDIENKENNER Medienkenner, PROGRAMMKENNER Programmkenner) throws myException{
		super();
		
		String tableName = new Archiver_Normalized_Tablename(TableName).get_TableBaseName();
		SELECT sel = new SELECT("*").from(_DB.ARCHIVMEDIEN).where(_DB.ARCHIVMEDIEN$TABLENAME, tableName).and(_DB.ARCHIVMEDIEN$ID_TABLE, tableID);
		
		if (Medienkenner!=null) {
			sel.and(_DB.ARCHIVMEDIEN$MEDIENKENNER, Medienkenner.get_DB_WERT());
		}
		
		if (Programmkenner !=null) {
			sel.and(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER, Programmkenner.get_DB_WERT());
		}
		
		sel.orderBy(_DB.ARCHIVMEDIEN$ID_ARCHIVMEDIEN);
		
		this.set_Select(sel);
		this.REFRESH();
	}
	
	
	/**
	 * 2015-04-23: martin
	 * @param TableName
	 * @param tableID
	 * @param Medienkenner 		Archiver_CONST.MEDIENKENNER can be null
	 * @param Programmkenner  	Archiver_CONST.PROGRAMMKENNER can be null
	 * @throws myException
	 */
	public RECLIST_ARCHIVMEDIEN_ext(String TableName, String tableID, MEDIENKENNER Medienkenner, PROGRAMMKENNER Programmkenner, String p_orderBy) throws myException{
		super();
		
		String tableName = new Archiver_Normalized_Tablename(TableName).get_TableBaseName();
		SELECT sel = new SELECT("*").from(_DB.ARCHIVMEDIEN).where(_DB.ARCHIVMEDIEN$TABLENAME, tableName).and(_DB.ARCHIVMEDIEN$ID_TABLE, tableID);
		
		if (Medienkenner!=null) {
			sel.and(_DB.ARCHIVMEDIEN$MEDIENKENNER, Medienkenner.get_DB_WERT());
		}
		
		if (Programmkenner !=null) {
			sel.and(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER, Programmkenner.get_DB_WERT());
		}
		if (S.isFull(p_orderBy)) {
			sel.orderBy(p_orderBy);
		} else {
			sel.orderBy(_DB.ARCHIVMEDIEN$ID_ARCHIVMEDIEN);
		}
		
		this.set_Select(sel);
		this.REFRESH();
	}

	
	
	
	/**
	 * 2015-04-24: martin
	 * @param TableName
	 * @param tableID
	 * @param Medienkenner 		Archiver_CONST.MEDIENKENNER can be null
	 * @param Programmkenner  	Archiver_CONST.PROGRAMMKENNER can be null
	 * @param isOriginal        can be null 
	 * @throws myException
	 */
	public RECLIST_ARCHIVMEDIEN_ext(String TableName, String tableID, MEDIENKENNER Medienkenner, PROGRAMMKENNER Programmkenner, Boolean isOriginal) throws myException{
		super();
		
		String tableName = new Archiver_Normalized_Tablename(TableName).get_TableBaseName();
		SELECT sel = new SELECT("*").from(_DB.ARCHIVMEDIEN).where(_DB.ARCHIVMEDIEN$TABLENAME, tableName).and(_DB.ARCHIVMEDIEN$ID_TABLE, tableID);
		
		if (Medienkenner!=null) {
			sel.and(_DB.ARCHIVMEDIEN$MEDIENKENNER, Medienkenner.get_DB_WERT());
		}
		
		if (Programmkenner !=null) {
			sel.and(_DB.ARCHIVMEDIEN$PROGRAMM_KENNER, Programmkenner.get_DB_WERT());
		}
		
		if (isOriginal !=null) {
			if (isOriginal) {
				sel.and("NVL("+_DB.ARCHIVMEDIEN$IST_ORIGINAL+",'N')", "=", new U("'Y'"));
			} else {
				sel.and("NVL("+_DB.ARCHIVMEDIEN$IST_ORIGINAL+",'N')", "=", new U("'N'"));
			}
		}
		
		this.set_Select(sel);
		
		//DEBUG.System_println(sel.toString());
		
		this.REFRESH();
	}
	
	
	
	
	public RECLIST_ARCHIVMEDIEN_ext(RECLIST_ARCHIVMEDIEN rl_arch_orig) throws myException {
		super();
		
		this.ADD(rl_arch_orig, true);
	}

	
	public void ADD(Vector<RECORD_ARCHIVMEDIEN> vArchmed) throws myException {
		for (RECORD_ARCHIVMEDIEN ra: vArchmed) {
			this.ADD(ra, true);
		}
	}
	
	/**
	 * 
	 * @return myTempFile with autodelete, which contains all pdf in reclist concatenated
	 * @throws myException when reclist is emtpy or fist file is no pdf
	 */
	public myTempFile  generate_pdf_tempFile_concatenated() throws myException {
		if (this.get_vKeyValues().size()==0) {
			throw new myException(this,"no file is present in archive !!");
		}
		
		RECORD_ARCHIVMEDIEN_ext ra_ext = new RECORD_ARCHIVMEDIEN_ext(this.get(0));
		
		if (ra_ext.is_PDF()) {
			myTempFile  tf = null;
			tf = new myTempFile(UUID.randomUUID().toString(), ".pdf", true, true, new File(ra_ext.get__cCompletePathAndFileName()));
			for (int i=1; i<this.size();i++) {
				RECORD_ARCHIVMEDIEN_ext ra_ext_innen = new RECORD_ARCHIVMEDIEN_ext(this.get(i));
				if (ra_ext_innen.is_PDF()) {
					tf.append_pdf(bibVECTOR.get_Vector(ra_ext_innen.get__cCompletePathAndFileName()));
				}
			}
			return tf;
		} else {
			throw new myException(this,"First archivfile is not a pdf !!");
		}

	}
	
	
	
	
	
}
