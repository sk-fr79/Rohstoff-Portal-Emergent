package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import java.util.ArrayList;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.ArchiverConcatenated_PDF_and_PIXELFiles;
import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


/**
 * allgemeiner button um anlagen zu einem datensatz einzusammeln und als gesamt-pdf downzuloaden
 * @author martin
 *
 */
public class UP_DOWN_GenericDownloadCollector extends MyE2_Button {
	//uebergeben werden kann entweder die id_table oder eine ComponentMAP wo dann live nachgesucht wird, welche ID der basistabelle vorliegt
	private String 				id_table = null;
	private E2_ComponentMAP    	maskMap = null;
	private String 				baseTableName = null;
	
	//2015-10-29: tempfile wird in der komponente gehalten, damit ein nebenlaeufiger download noch die daten findet
	private myTempFile          tf_alles_zusammen = null;
	
	public UP_DOWN_GenericDownloadCollector(String p_id_table, String  p_tablename, XX_ActionValidator authValidator) {
		super(E2_ResourceIcon.get_RI("down_mini.png"));
		this.init(p_id_table, null, p_tablename, authValidator);
	}

	public UP_DOWN_GenericDownloadCollector(E2_ComponentMAP    p_maskMap , String  p_tablename, XX_ActionValidator authValidator) {
		super(E2_ResourceIcon.get_RI("down_mini.png"));
		this.init(null, p_maskMap,  p_tablename, authValidator);
	}

	
	public void init(String p_id_table, E2_ComponentMAP    p_maskMap , String  p_tablename, XX_ActionValidator authvalidator)  {
		this.baseTableName =new Archiver_Normalized_Tablename(p_tablename).get_TableBaseName();
		this.id_table = p_id_table;
		this.maskMap = p_maskMap;
		
		if (authvalidator == null) {
			this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("SAMMELDOWNLOAD"));
		} else {
			this.add_GlobalValidator(authvalidator);
		}
		
		this.setToolTipText(new MyE2_String("Downloaden von allen Anlagen vom Typ pdf oder Bild (pixelbasierte)").CTrans());
		
		this.add_oActionAgent(new ownActionAgentDownload());

	}
	
	
	
	private class ownActionAgentDownload extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String basetable = 	UP_DOWN_GenericDownloadCollector.this.baseTableName;
			String id    = 		UP_DOWN_GenericDownloadCollector.this.id_table;
			
			if (S.isEmpty(id)) {
				if (UP_DOWN_GenericDownloadCollector.this.maskMap!=null) {
					if (UP_DOWN_GenericDownloadCollector.this.maskMap.get_oInternalSQLResultMAP()!=null) {
						baseTableName = UP_DOWN_GenericDownloadCollector.this.maskMap.get_oInternalSQLResultMAP().get_oSQLFieldMAP().get_cMAIN_TABLE();
						baseTableName = new Archiver_Normalized_Tablename(basetable).get_TableBaseName();
						id = UP_DOWN_GenericDownloadCollector.this.maskMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					}
				}
			}

			if (S.isEmpty(id)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei Neueingabe sind keine Dateien zum Download vorhanden !")));
				return;
			}
			
			//alle archivmedien, die an dem jeweiligen datensatz haengen
			RECLIST_ARCHIVMEDIEN_ext  ra_ext = new RECLIST_ARCHIVMEDIEN_ext(basetable, id, null,null);
			
			
			//zuerst zaehlen was da ist
			int i=0;
			for (RECORD_ARCHIVMEDIEN ra: ra_ext) {
				RECORD_ARCHIVMEDIEN_ext rae = new RECORD_ARCHIVMEDIEN_ext(ra);
				
				if (rae.is_pixel_image() || rae.is_PDF()) {
					i++;
				}
			}
			
			if (i==0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt keine PDF oder Pixel-Anlagen !")));
			} else {
				ArchiverConcatenated_PDF_and_PIXELFiles concatenater = new ArchiverConcatenated_PDF_and_PIXELFiles();
				ArrayList<RECORD_ARCHIVMEDIEN> v_ra = new ArrayList<RECORD_ARCHIVMEDIEN>();
				v_ra.addAll(ra_ext.values());
				
				String baseName = basetable+"_"+id+"_";
				
				MyE2_MessageVector  mv = new MyE2_MessageVector();
				
				UP_DOWN_GenericDownloadCollector.this.tf_alles_zusammen = concatenater.generate_ConcatenatedFile(v_ra,  mv, baseName);
				
				if (mv.get_bIsOK()) {
					UP_DOWN_GenericDownloadCollector.this.tf_alles_zusammen.starteDownLoad(baseName, JasperFileDef.MIMETYP_PDF);
				}
			}
			
			
			
		}
		
	}

}
