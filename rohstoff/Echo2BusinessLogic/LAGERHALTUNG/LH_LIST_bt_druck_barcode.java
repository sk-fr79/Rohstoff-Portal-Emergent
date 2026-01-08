/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 28.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import java.util.ArrayList;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_agent_processing_print_mail_preview;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public class LH_LIST_bt_druck_barcode extends E2_Button {

	private RB_TransportHashMap m_trpHashMap;

	public LH_LIST_bt_druck_barcode(RB_TransportHashMap otrpHashMap) {
		super();
		this._image(E2_ResourceIcon.get_RI("barcode.png"));
		this._ttt(S.ms("Drucken der Barcodes der ausgewählten Boxen."));
		this._aaa(new ownActionAgent());
		this.add_GlobalValidator(new ownValidator());
		this.m_trpHashMap = otrpHashMap;
	}

	public RB_TransportHashMap getTransportHashMap() {
		return m_trpHashMap;
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}


	private class ownActionAgent extends SE_agent_processing_print_mail_preview{

		public ownActionAgent() {
			super(processtype.PRINT);
		}

		@Override
		public boolean is_multi_id_allowed() {
			return true;
		}

		@Override
		public Vector<String> get_ids_to_print() throws myException {
			return new VEK<String>()._a(m_trpHashMap.getNavigationList().get_vSelectedIDs_Unformated());
		}

		@Override
		public SE_JasperHASH_4_ArchivPDF generate_jasperHash(String id) throws myException {
			return new ownJasperHash(id);
		}	
	}

	private class ownJasperHash extends SE_JasperHASH_4_ArchivPDF {


		public ownJasperHash(String id_box) throws myException {
			super("box_etikett", _TAB.lager_box);

			this.put("ID_LAGER_BOX", id_box);
		}


		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return false;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return "Box_Etikett";
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
		}

		@Override
		public PROGRAMMKENNER generate_programmkenner_for_archiv() throws myException {
			return null;
		}

		@Override
		public MEDIENKENNER generate_medienkenner_for_archiv() throws myException {
			return null;
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

	private class ownValidator extends XX_ActionValidator_NG{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			VEK<String> vSelectedIds = new VEK<String>()._a(m_trpHashMap.getNavigationList().get_vSelectedIDs_Unformated());
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if(vSelectedIds.size()==0) {
				mv._addAlarm("Sie müssen mindestens eine Box auswählen! ");
			}
			return mv;
		}

	}
}

