package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperTempFile_and_pipePos_VECT;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_JasperHASH_4_ArchivPDF;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_agent_processing_print_mail_preview;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver_CONST.MEDIENKENNER;
import panter.gmbh.indep.archive.Archiver_CONST.PROGRAMMKENNER;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class FS_BT_EU_Vertrag_DruckMail_button extends MyE2_Button implements FS_Report_konstant_EU_VERTRAG, DS_IF_components4decision{

	private E2_NavigationList navilist;
	private FS_POPUP_PrintMail_EU_VERTRAG parent;

	private processtype typ;

	private ownJasperHash ojh = null;

	private String jrxmlFile = "";

	public FS_BT_EU_Vertrag_DruckMail_button(FS_POPUP_PrintMail_EU_VERTRAG c_euVertrag_POPUP,  processtype e_typ) throws myException {
		super("");

		this.parent = c_euVertrag_POPUP;
		this.navilist = c_euVertrag_POPUP.getNavilist();
		this.typ = e_typ;

		this.add_GlobalValidator(new FS_BT_EU_Vertrag_DruckMail_validator(this.parent));

		//2016-04-29: popup-validator 
		this.add_oActionAgent(new FS_ValAgent_WarnWhenNoStation(this, this.parent));
		
		switch(typ){
		case MAIL:
			this.setText("eMail");
			this._bordBlack();
			this.setToolTipText(new MyE2_String("EU Vertrag per Mail Schicken und Maske schliessen").CTrans());
			this.add_oActionAgent(new ownActionAgent(typ));
			break;
		case PRINT:
			this.setText("Druck");
			this._bordBlack();
			this.setToolTipText(new MyE2_String("EU Vertrag drucken und Maske schliessen").CTrans());
			this.add_oActionAgent(new ownActionAgent(typ));
			break;
		case PREVIEW:
			this.setText("Vorschau");
			this.setToolTipText(new MyE2_String("EU Vertrag vorschauen").CTrans());
			this.add_oActionAgent(new ownActionAgent(typ));
			break;
		default:
			break;
		}

		RECORD_ADRESSE recAdr = new RECORD_ADRESSE(navilist.get_vSelectedIDs_Unformated_Select_the_one_and_only().get(0));

		jrxmlFile = recAdr.get_UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form().get_JASPERFORMULAR_cUF_NN("");

		if (S.isEmpty(recAdr.get_UP_RECORD_ADRESSE_EU_VERTR_FORM_id_adresse_eu_vertr_form().get_JASPERFORMULAR_cUF_NN(""))) {
			//sollte durch die validierung verhindert werden
			throw new myException(this,"Error: Adress: "+recAdr.get_ID_ADRESSE_cUF_NN("<id>")+" has no Contract-Definition !");
		}

		this.add_oActionAgent(new closeActionAgent());
	}

	public String getSelectedId(){
		return navilist.get_vSelectedIDs_Unformated_Select_the_one_and_only().get(0);
	}

	private class ownJasperHash extends SE_JasperHASH_4_ArchivPDF{

		String c_id_adresse = null;
		
		public ownJasperHash(String uf_id_adresse) throws myException {
			super(FS_BT_EU_Vertrag_DruckMail_button.this.jrxmlFile, _TAB.adresse, uf_id_adresse);
			this.c_id_adresse = uf_id_adresse;
		}

		@Override
		public boolean check_if_archive_must_be_done() throws myException {
			return true;
		}

		@Override
		public String generate_filename_4_archiv_without_ending() throws myException {
			return "EU_VERTRAG"+"_"+"ADRESSE_ID_"+S.NN(c_id_adresse);
		}

		@Override
		public ArrayList<String> get_v_email_targets() throws myException {
			return FS_BT_EU_Vertrag_DruckMail_button.this.parent.getSelectedEmails();
		}

		@Override
		public MAILPROFILE get_used_send_profile() throws myException {
			return null;
		}

		@Override
		public void check_all_jasperTempfiles_whether_needed(E2_JasperTempFile_and_pipePos_VECT vAlleOriginale)
				throws myException {/**/}

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
		public void doActionAfterCreatedFile() throws myException {/**/}

	}

	private class ownActionAgent extends SE_agent_processing_print_mail_preview{

		public ownActionAgent(processtype p_typ) {
			super(p_typ);
		}

		@Override
		public boolean is_multi_id_allowed() {
			return false;
		}

		@Override
		public Vector<String> get_ids_to_print() throws myException {
			return bibALL.get_Vector(FS_BT_EU_Vertrag_DruckMail_button.this.getSelectedId());
		}

		@Override
		public SE_JasperHASH_4_ArchivPDF generate_jasperHash(String uf_id_adresse) throws myException {

			ojh = new ownJasperHash(uf_id_adresse);

			ojh.put(ID_ADRESSE, uf_id_adresse);

			ojh.put(VERTRAG_DATUM, FS_BT_EU_Vertrag_DruckMail_button.this.parent.getVertragsDatum());

			HashMap<String, String> hm_empfaengerVeranlasser = FS_BT_EU_Vertrag_DruckMail_button.this.parent.getEmpfaengerVeranlasserGrid();

			String[] verantwortlichePerson = parent.getVerantworlichPerson().split(",");
			
			ArrayList<String> emails = parent.getSelectedEmails();
			
			String faxNummer = parent.getSelectedFaxNummer();
			
			if(verantwortlichePerson[0].equals("NAME")){
				if(verantwortlichePerson.length>1){
					ojh.put(VERANTWORTLICH_PERSON, verantwortlichePerson[1]);
				}
			}else if(verantwortlichePerson[0].equals("ID")){
				ojh.put(VERANTWORTLICH_PERSON_ID , verantwortlichePerson[1]);
			}
			
			
			for(String hmKey: hm_empfaengerVeranlasser.keySet()){
				ojh.put(hmKey, hm_empfaengerVeranlasser.get(hmKey));
			}

			if(! parent.getSelectedLager().isEmpty()){
				ojh.put(LAGER_LIST, convertArrayListInString(parent.getSelectedLager()));
			}
			
			if(emails.size()>0){
				for(int i = 0; i<emails.size(); i++){
					ojh.put(KONTAKT_ + (i + 1), emails.get(i));	
				}
			}
			ojh.put("FAXNUMMER", faxNummer);

			return ojh;
		}

	}
	
	private class closeActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			parent.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			parent.getNavilist().RefreshList();
		}
		
	}

	private static String convertArrayListInString(VEK<String> stringList){
		if(stringList.size()>0){
			StringBuffer buf = new StringBuffer("(");
			for(int i = 0; i<(stringList.size()-1); i++){
				buf.append("'" + stringList.get(i)+"',");
			}
			buf.append("'" + stringList.get(stringList.size()-1) + "')");
			return buf.toString();
		}else return "()";
	}

	
	
	

	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return this.storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return this.storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return this.storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return this.hm_descision_containers;
	}

	
	
	
}
