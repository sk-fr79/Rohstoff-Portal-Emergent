package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_agent_processing_print_mail_preview;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.exceptions.myException;

public class BGL_LIST_jasperhash_BelegGrenzUbertritt extends SE_agent_processing_print_mail_preview {

	private BGL_LIST_popup_BelegGrenzUbertritt parent_popup = null;
	
	private ownJasperHash oJh;
	
	private final static String JRXML_FILE = "beleg_eu_verbringung";
	
	public BGL_LIST_jasperhash_BelegGrenzUbertritt(BGL_LIST_popup_BelegGrenzUbertritt parent_popup, processtype p_typ) {
		super(p_typ);
		this.parent_popup = parent_popup;
	}
	@Override
	public boolean is_multi_id_allowed() {
		return true;
	}

	@Override
	public Vector<String> get_ids_to_print() throws myException {
		return parent_popup.get_naviList().get_vSelectedIDs_Unformated();
	}

	@Override
	public SE_JasperHASH_4_ArchivPDF generate_jasperHash(String id) throws myException {
		
		oJh = new ownJasperHash(id);

		RB_ComponentMap valueMap = parent_popup.get_componentMap();
		
		String ePreis 				= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.E_PREIS.name())).getText();
		String name_ansprech 		= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.name())).getText();
		String tel_ansprech 		= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.name())).getText();
		String fax_ansprech 		= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.name())).getText();

		String name_bearbeiter 		= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.name())).getText();
		String tel_bearbeiter 		= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.name())).getText();
		String fax_bearbeiter 		= ((RB_TextField) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.name())).getText();
		
		String formular_start 		= ((RB_TextArea) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.name())).getText();
		String formular_ende 		= ((RB_TextArea) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.name())).getText();
		String ausland_vertretung 	= ((RB_TextArea) valueMap.get_Comp(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.name())).getText();
		
		oJh.put("id_vpos_tpa_fuhre", 										id);
		
		oJh.put("id_zahlungsbedingungen",									S.NN(valueMap.get_cActualDBValueFormated(BGL_ENUM_REPORT_PARAMETER.ID_ZAHLUNGSBEDINGUNGEN.name())).replace(".", ""));
		oJh.put("e_preis", 													ePreis);
		oJh.put("id_tax_rechnung",											S.NN(valueMap.get_cActualDBValueFormated(BGL_ENUM_REPORT_PARAMETER.ID_TAX_RECHNUNG.name())).replace(".", ""));	
		oJh.put("id_tax_gutschrift",										S.NN(valueMap.get_cActualDBValueFormated(BGL_ENUM_REPORT_PARAMETER.ID_TAX_GUTSCHRIFT.name())).replace(".", ""));			
		oJh.put(BGL_ENUM_REPORT_PARAMETER.NAME_ANSPRECH_INTERN.name(),		name_ansprech);			
		oJh.put(BGL_ENUM_REPORT_PARAMETER.TEL_ANSPRECH_INTERN.name(),		tel_ansprech);
		oJh.put(BGL_ENUM_REPORT_PARAMETER.FAX_ANSPRECH_INTERN.name(),		fax_ansprech);			
		oJh.put(BGL_ENUM_REPORT_PARAMETER.NAME_BEARBEITER_INTERN.name(),	name_bearbeiter);			
		oJh.put(BGL_ENUM_REPORT_PARAMETER.TEL_BEARBEITER_INTERN.name(),		tel_bearbeiter);
		oJh.put(BGL_ENUM_REPORT_PARAMETER.FAX_BEARBEITER_INTERN.name(),		fax_bearbeiter);
		oJh.put(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ANFANG.name(),		formular_start);			
		oJh.put(BGL_ENUM_REPORT_PARAMETER.FORMULARTEXT_ENDE.name(),			formular_ende);
		oJh.put(BGL_ENUM_REPORT_PARAMETER.TEXT_AUSLANDSVERTRETUNG.name(),	ausland_vertretung);

		return oJh;
	}

	
	
	private class ownJasperHash extends SE_JasperHASH_4_ArchivPDF{

		public ownJasperHash(String id__vpos_tpa_fuhre) throws myException {
			super(JRXML_FILE, _TAB.vpos_tpa_fuhre, id__vpos_tpa_fuhre);
		}

		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return true;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return JRXML_FILE.toUpperCase();
		}

		@Override
		public ArrayList<String> get_v_email_targets() throws myException {
			return parent_popup.get_selected_mail();
		}

		@Override
		public MAILPROFILE get_used_send_profile() throws myException {
			return MAILPROFILE.BELEG_GRENZUBERTRITT;
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
	
	
}
