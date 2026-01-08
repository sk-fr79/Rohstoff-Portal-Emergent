package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.ArrayList;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.exceptions.myException;

public class WK_Print_JasperHash_Archiv extends SE_JasperHASH_4_ArchivPDF {

	public WK_Print_JasperHash_Archiv(String name_of_jrxml,String id_Wiegekarte) throws myException {
		super(name_of_jrxml, _TAB.wiegekarte, id_Wiegekarte);
	}
	
	
	@Override
	public boolean check_if_archive_must_be_done() throws myException {
			return true;
	}

	@Override
	public String generate_filename_4_archiv_without_ending() throws myException {
		return "WIEGEKARTE_ARCHIV";
	}

	@Override
	public ArrayList<String> get_v_email_targets() throws myException {
		return null;
	}

	@Override
	public MAILPROFILE get_used_send_profile() throws myException {
		return null;
	}

	@Override
	public void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale)
			throws myException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PROGRAMMKENNER generate_programmkenner_for_archiv() throws myException {
		// TODO: AKTUELL kein Programmkenner setzen.. vielleicht 
		return null;
	}

	@Override
	public MEDIENKENNER generate_medienkenner_for_archiv() throws myException {
		return MEDIENKENNER.AUTOARCHIV;
	}

	@Override
	protected MailBlock create_MailBlock() throws myException {
		return null;
	}

	@Override
	public boolean get_bIsDesignedForMail() throws myException {
		return false;
	}

	@Override
	public void doActionAfterCreatedFile() throws myException {
	}

}


