package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.io.File;
import java.util.UUID;

import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class RecList22_Archivmedien extends RecList22 {

	public RecList22_Archivmedien() {
		super(_TAB.archivmedien);
	}
	
	
	public RecList22_Archivmedien(SqlStringExtended QueryString) throws myException {
		this();
		this._fill(QueryString);
	}
	
	/**
	 * Liest alle Archivmedien die an die Tabelle TableName mit der Tableid tableID angehängt sind
	 * @author manfred
	 * @date 22.02.2021
	 *
	 * @param TableName
	 * @param tableID
	 * @throws myException
	 */
	public RecList22_Archivmedien(String TableName, Long tableID) throws myException{
		this(TableName,tableID,null);
	}

	
	
	/**
	 * Liest alle Archivmedien die an die Tabelle TableName mit der Tableid tableID angehängt sind und den Medienkenner gesetzt haben
	 * @author manfred
	 * @date 22.02.2021
	 *
	 * @param TableName
	 * @param tableID
	 * @throws myException
	 */
	public RecList22_Archivmedien(String TableName, Long tableID, MEDIENKENNER Medienkenner) throws myException{
		this();
		
		SEL sel = new SEL("*")
				.FROM(this.get_tab())
				.WHERE(new vglParam(ARCHIVMEDIEN.tablename))
				.AND(new vglParam(ARCHIVMEDIEN.id_table))
				.ORDERDOWN(ARCHIVMEDIEN.erstellungsdatum);
		
		if (Medienkenner != null) {
			sel.AND(new vglParam(ARCHIVMEDIEN.medienkenner));
		}
	
		SqlStringExtended  sqlExt = new SqlStringExtended(sel.s())
					._addParameters(new VEK<ParamDataObject>()
					._a(new Param_String("",TableName))
					._a(new Param_Long(tableID))
					);
		
		if (Medienkenner != null) {
			sqlExt._addParameter(new Param_String("",Medienkenner.get_DB_WERT()));
			
		}
			
		this._fill(sqlExt);
	
	}

	

	/**
	 * erzeugt ein temporäres PDF mit Autodelete mit allen PDFs die in der Liste vorhanden sind
	 * @author manfred
	 * @date 19.02.2021
	 *
	 * @return
	 * @throws myException
	 */
	public myTempFile  generate_pdf_tempFile_concatenated() throws myException {
		if (this.getVEK().size() == 0) {
			throw new myException(this,"no file is present in archive !!");
		}

		boolean bHasPDF = false;
		myTempFile tf = null;
		for (int i = 0; i< this.size(); i++) {
			Rec22Archivmedien recArchiv = new Rec22Archivmedien(this.get(i));
			if (recArchiv.is_PDF()) {
				bHasPDF = true;
				// runterladen, nur wenn die datei auch existiert!!
				if (new File(recArchiv.get__cCompletePathAndFileName()).isFile()) {
					if (tf == null) {
						tf = new myTempFile(UUID.randomUUID().toString(), ".pdf", true, true, new File(recArchiv.get__cCompletePathAndFileName()));
					} else {
						tf.append_pdf(bibVECTOR.get_Vector(recArchiv.get__cCompletePathAndFileName()));
					}
				}
				
			}
		}
		if (tf != null) {
			return tf;
		} else {
			throw new myException(this,"No PDF in Archive !!");
		}
	}
	
	
	
	

}
