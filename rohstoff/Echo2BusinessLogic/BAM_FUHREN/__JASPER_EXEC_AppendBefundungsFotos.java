package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.ArrayList;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import panter.gmbh.indep.pdf.PDF_GeneratePdf_With_Images;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * 
 * @author martin
 * Jasper-Executer-Klasse, baut original-Belege auf und archiviert diese fuer die Versendung per eMail
 *
 */
public class __JASPER_EXEC_AppendBefundungsFotos extends Jasper_Exe_ROOT {

	private boolean bInaktiv = false;

	public __JASPER_EXEC_AppendBefundungsFotos(boolean pruefung_First_Print_is_Abgeschlossen) {
		super();
	}


	@Override
	public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector oMV, Object oSammlerRueckgaben) throws myException {
		//zuerst beschaffen einiger informationen
		
		if (this.bInaktiv) {
			return;
		}
		
//		this.Collect_Original_Document_Parts(oJasperHash);
		
		boolean bIsBam = oJasperHash.get_cReportBaseName().startsWith("fbam_fuhren_meldung");
		
		
		//String 		id_fbam = 	(String)oJasperHash.get("id_fbam");
		RECORD_FBAM 				rec_fbam = 			new RECORD_FBAM((String)oJasperHash.get("id_fbam")); 
		RECLIST_ARCHIVMEDIEN_BAM  	rlArchivmedien = 	new RECLIST_ARCHIVMEDIEN_BAM(rec_fbam,bIsBam);
		
		ArrayList<myTempFile>    	alPdfFiles = new ArrayList<myTempFile>();
		
		//jetzt alle durchsuchen und falls es bitmap-anlagen sind, diese sammeln
		for (String id_archivmedien: rlArchivmedien.get_vKeyValues()) {
			RECORD_ARCHIVMEDIEN_ext ra = new RECORD_ARCHIVMEDIEN_ext( rlArchivmedien.get(id_archivmedien));
			
			DEBUG.System_println(ra.get_UP_RECORD_MEDIENTYP_id_medientyp().get_DATEIENDUNG_cUF(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
			//pro bild ein pdf
			if (ra.get_UP_RECORD_MEDIENTYP_id_medientyp().is_IST_PIXELIMAGE_YES()) {
				alPdfFiles.add(new PDF_GeneratePdf_With_Images(bibVECTOR.get_Vector(ra.get__cCompletePathAndFileName()), ra.get__Base_Filename_Orig(""), false, 500).generate_pdf_with_images());
			}
		}

		for (myTempFile tf: alPdfFiles) {
			DEBUG.System_println("foto-pdf: "+tf.getFileName(), DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
		}
		
		
		myTempFile fileCollector = null; 
		if (alPdfFiles.size()>0) {
			fileCollector = alPdfFiles.get(0);
			//alle folgenden an den ersten anhaengen
			for (int i=1;i<alPdfFiles.size();i++) {
				fileCollector.append_pdf(bibVECTOR.get_Vector(alPdfFiles.get(i).getFileName()));
				alPdfFiles.get(i).delete_File(false);
			}
		}
		
		//im preview oder mehrfachdruck-fall werden die anhaenge an das letzte pdf der erzeugten pipeline angehaengt
		if (fileCollector != null) {
			this.attach_pdfs_at_end(oJasperHash, fileCollector);
		}
	}

	
	
	
	
	/**
	 * and das jeweilig letzte tempfile in den gesammelten pdf-vectoren die verkettete liste aller anzuhaengenden pdf-dokumente dranhaengen
	 * (ausser bei der Archivierungs-pipeline, dort waere das datenvolumen dann zu groß
	 * @param oJasperHash
	 * @param downFile
	 * @throws myException
	 */
	private void attach_pdfs_at_end(E2_JasperHASH oJasperHash, myTempFile downFile) throws myException {
		//hier die verkettete temp-anlagen mit an den beleg haengen
		//an jede 1. temp-datei innerhalb der sammelvectoren die verketteten anhaenge einbauen (ausser ins archiv)
		if (oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().size()>0) {
			E2_JasperTempFile_and_pipePos part = oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().get(oJasperHash.get_jasperTempFile_and_pipePos_VECT_standard().size()-1);
			part.get_oJasperTempFile().append_pdf(bibVECTOR.get_Vector(downFile.getFileName()));
		}
		if (oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().size()>0) {
			E2_JasperTempFile_and_pipePos part = oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().get(oJasperHash.get_jasperTempFile_and_pipePos_VECT_DirektDruck().size()-1);
			part.get_oJasperTempFile().append_pdf(bibVECTOR.get_Vector(downFile.getFileName()));
		}
		if (oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail().size()>0) {
			E2_JasperTempFile_and_pipePos part = oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail().get(oJasperHash.get_jasperTempFile_and_pipePos_VECT_KontrollMail().size()-1);
			part.get_oJasperTempFile().append_pdf(bibVECTOR.get_Vector(downFile.getFileName()));
		}
	}

	
	
	@Override
	public EXECUTER_JUMPPOINTS get_JUMPMarker() {
		return Jasper_Exe_CONST.EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PROCESS_ALL_TEMPFILES;
	}

	
	
	private class RECLIST_ARCHIVMEDIEN_BAM extends RECLIST_ARCHIVMEDIEN_ext {

		public RECLIST_ARCHIVMEDIEN_BAM(RECORD_FBAM rec_fbam, boolean bIsBam) throws myException {
			super();
			
			String cSelect = "SELECT * FROM "+bibE2.cTO()+"."+
								_DB.ARCHIVMEDIEN +" WHERE "+
									_DB.ARCHIVMEDIEN$TABLENAME+"="+S.hk(_DB.FBAM.substring(3))+" AND "+
									_DB.ARCHIVMEDIEN$ID_TABLE+"="+rec_fbam.get_ID_FBAM_cUF()+" AND ";
			
			if (bIsBam) {
			    cSelect+= "("+_DB.ARCHIVMEDIEN$PROGRAMM_KENNER+"="+S.hk(Archiver_CONST.PROGRAMMKENNER.BAM_UND_WEIGER_DRUCK_ANHANG.get_DB_WERT())+" OR "+
									_DB.ARCHIVMEDIEN$PROGRAMM_KENNER+"="+S.hk(Archiver_CONST.PROGRAMMKENNER.BAM_DRUCK_ANHANG.get_DB_WERT())+")";
			} else {
			    cSelect+= "("+_DB.ARCHIVMEDIEN$PROGRAMM_KENNER+"="+S.hk(Archiver_CONST.PROGRAMMKENNER.BAM_UND_WEIGER_DRUCK_ANHANG.get_DB_WERT())+" OR "+
						_DB.ARCHIVMEDIEN$PROGRAMM_KENNER+"="+S.hk(Archiver_CONST.PROGRAMMKENNER.WEIGER_DRUCK_ANHANG.get_DB_WERT())+")";
			}
			
			this.set_cQueryString(cSelect);
			this.REFRESH();
		}
		
	}
	
	
	
}
