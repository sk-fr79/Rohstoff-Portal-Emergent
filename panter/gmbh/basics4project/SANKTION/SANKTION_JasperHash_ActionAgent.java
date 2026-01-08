package panter.gmbh.basics4project.SANKTION;

import java.util.ArrayList;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.exceptions.myException;

public class SANKTION_JasperHash_ActionAgent extends ACTIONAGENT_MAIL_AND_REPORT{


	private final static String JASPER_FILE 		= "LIST_adresse_sanktion_pruefung";

	public SANKTION_JasperHash_ActionAgent() {
		super(S.ms(""), "SANKTION_PRUEFUNG", "SANKTION_PRUEFUNG",null, false);
	}

	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException {
		V_JasperHASH vReck = new V_JasperHASH();
		vReck.add(new  own_jasper_hash());
		return vReck;
	}

	@Override
	public E2_MassMailer get_MassMailer() throws myException {
		return null;
	}


	private class own_jasper_hash extends SE_JasperHASH_4_ArchivPDF { 

		public own_jasper_hash() throws myException {
			super(JASPER_FILE,_TAB.sanktion_pruefung);
		}

		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return false;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return JASPER_FILE.toUpperCase();
		}

		@Override
		public ArrayList<String> get_v_email_targets() throws myException {
			return new ArrayList<String>();
		}

		@Override
		public MAILPROFILE get_used_send_profile() throws myException {
			return MAILPROFILE.ALLGEMEIN;
		}

		@Override
		public void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale)
				throws myException {}

		@Override
		public PROGRAMMKENNER generate_programmkenner_for_archiv() throws myException {return null;}

		@Override
		public MEDIENKENNER generate_medienkenner_for_archiv() throws myException {
			return MEDIENKENNER.AUTOARCHIV;
			}

		@Override
		protected MailBlock create_MailBlock() throws myException {return null;}

		@Override
		public boolean get_bIsDesignedForMail() throws myException {return false;}

		@Override
		public void doActionAfterCreatedFile() throws myException {}


	}
}