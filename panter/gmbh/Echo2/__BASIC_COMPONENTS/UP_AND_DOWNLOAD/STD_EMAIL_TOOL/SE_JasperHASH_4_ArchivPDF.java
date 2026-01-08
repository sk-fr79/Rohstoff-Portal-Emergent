package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.ArrayList;

import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.exceptions.myException;

public abstract class SE_JasperHASH_4_ArchivPDF extends E2_JasperHASH {

	private _TAB 		table = null;
	private String  	id_tab = null;
	
	private SE_JASPER_EXEC_ARCHIVIERE_PDF  jasper_executer = null;
	
	public SE_JasperHASH_4_ArchivPDF(String name_of_jrxml, _TAB p_tab, String id) throws myException {
		super(name_of_jrxml, new JasperFileDef_PDF());
		this.table = p_tab;
		this.id_tab = id;
		this.jasper_executer = new SE_JASPER_EXEC_ARCHIVIERE_PDF();
		this.get_vExecuters().addElement(this.jasper_executer);
	}


	public SE_JasperHASH_4_ArchivPDF(String name_of_jrxml, _TAB p_tab) throws myException {
		super(name_of_jrxml, new JasperFileDef_PDF());
		this.table = p_tab;
		this.get_vExecuters().addElement(new SE_JASPER_EXEC_ARCHIVIERE_PDF());
	}

	
	/**
	 * 
	 * @return RECORD_ARCHIVMEDIEN from print-process !
	 *         null when error or preview
	 * @throws myException
	 */
	public RECORD_ARCHIVMEDIEN get_rec_Archivmedien() throws myException {
		if (this.jasper_executer==null) {
			throw new myException(this, "SE_JASPER_EXEC_ARCHIVIERE_PDF-representation is null");
		}
		
		return this.jasper_executer.get_record_archivmedien_written();
	}
	
	public abstract boolean check_if_archive_must_be_done() throws myException;


	public _TAB get_table() {
		return table;
	}


	public String get_id_tab() {
		return id_tab;
	}
	
	public abstract String generate_filename_4_archiv_without_ending() throws myException;
	
	public abstract ArrayList<String>  get_v_email_targets() throws myException;
	
	public abstract MANDANT_CONST.MAILPROFILE get_used_send_profile() throws myException;


	// den vecktor mit den jaspertempfile durchsuchen und pruefen, ob einzelne tempfiles gebraucht werden
	public abstract void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale) throws myException;
	
	public abstract PROGRAMMKENNER 	generate_programmkenner_for_archiv() throws myException;
	public abstract MEDIENKENNER 	generate_medienkenner_for_archiv() throws myException;
	
	public void set_table(_TAB table) {
		this.table = table;
	}


	public void set_id_tab(String p_id_tab) {
		this.id_tab = p_id_tab;
	}
}
