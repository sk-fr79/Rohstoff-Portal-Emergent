package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.BasicInterfaces.IF_seek_and_find_first;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL;
import panter.gmbh.basics4project.DB_ENUMS.MITARBEITER;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


public class KFIX_K_M_BT_Select_Fremd_Ansprechpartner extends E2_Button {

	public KFIX_K_M_BT_Select_Fremd_Ansprechpartner() {
		super();
		this._image(E2_ResourceIcon.get_RI("person_popup.png"), true);
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException 	{
			new ownBasicContainer4PopUP();
		}
	}
	
	
	private class ownBasicContainer4PopUP extends E2_BasicModuleContainer {
		
		private Vector<ownCheckBoxMitarbeiter>	vCB_Mitarbeiter = 			new Vector<ownCheckBoxMitarbeiter>();
		private Vector<ownCheckBoxEmail>		vCB_MailAdressFirma = 		new Vector<ownCheckBoxEmail>();
		private Vector<ownCheckBoxEmail>		vCB_MailAdressMitarbeiter = new Vector<ownCheckBoxEmail>();
		
		private E2_Grid    	gridAussen = new E2_Grid()._setSize(200,200);
		private E2_Grid     gridMitarbeiter = new E2_Grid()._setSize(200);
		private E2_Grid     gridMailFirma = new E2_Grid()._setSize(200);
		private E2_Grid     gridMailMitarbeiter = new E2_Grid()._setSize(200);

		private ActionAgent_RadioFunction_CheckBoxList oRadioMitarbeiter = new ActionAgent_RadioFunction_CheckBoxList(true);
		private ActionAgent_RadioFunction_CheckBoxList oRadioMail = new ActionAgent_RadioFunction_CheckBoxList(true);

		
		private RecList20	recListMitarbeiter2 = new RecList20(_TAB.adresse);
		
		public ownBasicContainer4PopUP() throws myException 	{
			super();
			KFIX_K_M_BT_Select_Fremd_Ansprechpartner 	oThis = KFIX_K_M_BT_Select_Fremd_Ansprechpartner.this;
			KFIX_K_M_Controller 					controller = new KFIX_K_M_Controller(oThis._find_componentMapCollector_i_belong_to());
			
			this.add(this.gridAussen, E2_INSETS.I(4));
			
			MyLong l_id_adresse = controller.get_MyLong_liveVal(_TAB.vkopf_kon, VKOPF_KON.id_adresse);
			
			if (l_id_adresse==null || l_id_adresse.isNotOK()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst die Kontrakt-Firmenadresse festlegen !")));
			} else {
			
				/*
				 * 2 bereiche aufbauen: 1. Bereich: Alle Mitarbeiter der Firma
				 * 						2. Bereich: Alle Mailadresse der Firma/des zuletzt gewaehlten Mitarbeiters
				 */
				
				Rec20 		recAdressFirma1 		= new Rec20(_TAB.adresse)._fill_id(l_id_adresse.get_iValue());
				RecList20 	recListAdresseMitarbeiter 	= recAdressFirma1.get_down_reclist20(MITARBEITER.id_adresse_basis, "", MITARBEITER.id_mitarbeiter.fieldName());
				
				for(Rec20 mitarbeiter: recListAdresseMitarbeiter){
					String akt = mitarbeiter.get_up_Rec20(MITARBEITER.id_adresse_mitarbeiter, ADRESSE.id_adresse, false).get_fs_dbVal(ADRESSE.aktiv, "N");
					if(akt.equals("Y")){
						recListMitarbeiter2._add(mitarbeiter.get_up_Rec20(MITARBEITER.id_adresse_mitarbeiter, ADRESSE.id_adresse, false));
					}
				}
				
				if(recListMitarbeiter2.size()>0){
					this.vCB_Mitarbeiter.removeAllElements();
					this.vCB_MailAdressFirma.removeAllElements();
					this.vCB_MailAdressMitarbeiter.removeAllElements();
					
					for (int i=0;i<this.recListMitarbeiter2.size();i++)		{
						this.vCB_Mitarbeiter.add(new ownCheckBoxMitarbeiter(this.recListMitarbeiter2.get(i)));
					}
					
					for (int i=0;i<recAdressFirma1.get_down_reclist20(EMAIL.id_adresse, "", EMAIL.id_email.fieldName()).size();i++) 	{
						this.vCB_MailAdressFirma.add(new ownCheckBoxEmail(recAdressFirma1.get_down_reclist20(EMAIL.id_adresse, "", EMAIL.id_email.fieldName()).get(i),true));
					}
					this.build_Content();
					
					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("Mitarbeiter auswählen"));
				} else {
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Keinen aktiven Mitarbeiter gefunden !")));
				}
			}
		}

		
		
		public void build_Content() throws myException 	{

			this.gridAussen._clear();
			this.gridMitarbeiter._clear();
			this.gridMailFirma._clear();
			this.gridMailMitarbeiter._clear();
			
			this.gridAussen._a(this.gridMitarbeiter, 		new RB_gld()._ins(0)._span_r(2)._left_top());
			this.gridAussen._a(this.gridMailFirma, 			new RB_gld()._ins(0)._left_top());
			this.gridAussen._a(this.gridMailMitarbeiter, 	new RB_gld()._ins(0)._left_top());

			E2_Button btOK = new E2_Button()._aaa(new ownActionSaveMaskSettings())._tr("OK-Übernehmen")._sBDD();
			this.gridAussen._a(btOK, new RB_gld()._left_top()._ins(2, 10, 2, 2)._span(2));

			
			for (int i=0;i<this.vCB_Mitarbeiter.size();i++) {
				this.gridMitarbeiter.add(this.vCB_Mitarbeiter.get(i));
			}

			for (int i=0;i<this.vCB_MailAdressMitarbeiter.size();i++)	{
				this.gridMailMitarbeiter.add(this.vCB_MailAdressMitarbeiter.get(i));
			}

			for (int i=0;i<this.vCB_MailAdressFirma.size();i++)	{
				this.gridMailFirma.add(this.vCB_MailAdressFirma.get(i));
			}
			
			this.oRadioMail.get_vSammler().removeAllElements();
			this.oRadioMitarbeiter.get_vSammler().removeAllElements();
			
			this.oRadioMail.add_v_cb(this.vCB_MailAdressFirma);
			this.oRadioMail.add_v_cb(this.vCB_MailAdressMitarbeiter);
			this.oRadioMitarbeiter.add_v_cb(this.vCB_Mitarbeiter);
			
		}


		
		public void showMitarbeiterEmail(Rec20 rec_adresse) throws myException {
			ownBasicContainer4PopUP oThis = ownBasicContainer4PopUP.this;
			
			vCB_MailAdressMitarbeiter.clear();
			oThis.gridMailMitarbeiter._clear();
			for (int i=0;i<rec_adresse.get_down_reclist20(EMAIL.id_adresse, "", EMAIL.id_email.fieldName()).size();i++) 	{
				oThis.vCB_MailAdressFirma.add(new ownCheckBoxEmail(rec_adresse.get_down_reclist20(EMAIL.id_adresse, "", EMAIL.id_email.fieldName()).get(i),false));
			}
			oThis.build_Content();
		}
		
		
		
		private class ownCheckBoxMitarbeiter extends MyE2_CheckBox 	{
			public Rec20 r_adresse = null;
			public ownCheckBoxMitarbeiter(Rec20 recAdresseMitarbeiter) throws myException	{
				super();
				this.r_adresse = recAdresseMitarbeiter;
				this.setStyle(MyE2_CheckBox.STYLE_SMALL_ITALIC());
				this.setText(recAdresseMitarbeiter.get_ufs_kette(" ", ADRESSE.vorname,ADRESSE.name1, ADRESSE.name2));
				this.add_oActionAgent(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						ownBasicContainer4PopUP.this.showMitarbeiterEmail(r_adresse);
					}
				});
			}
		}
		
		
		private class ownCheckBoxEmail extends MyE2_CheckBox 	{
			public Rec20 r_eMail = null;
			public ownCheckBoxEmail(Rec20 recEMail, boolean firma) throws myException	{
				super();
				this.r_eMail = recEMail;
				this.setStyle(MyE2_CheckBox.STYLE_SMALL_ITALIC());
				this.setText(this.r_eMail.get_ufs_kette(" ", EMAIL.email)+(firma?" (FA)":" (MA)" ));
			}
		}
		
		
		private class ownActionSaveMaskSettings extends XX_ActionAgent {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				ownBasicContainer4PopUP oThis = ownBasicContainer4PopUP.this;
				VEK<ownCheckBoxEmail>  			v_cbs = new VEK<ownCheckBoxEmail>()._a(oThis.vCB_MailAdressFirma)._a(oThis.vCB_MailAdressMitarbeiter);
				VEK<ownCheckBoxMitarbeiter>  	v_ma = new VEK<ownCheckBoxMitarbeiter>()._a(oThis.vCB_Mitarbeiter);
				
				//lambdas zum raussuchen der selektierten
				IF_seek_and_find_first<ownCheckBoxEmail> 		seeker_cb_mail = 	(VEK<ownCheckBoxEmail> v)-> { for (ownCheckBoxEmail cb: v) { if (cb.isSelected()) {return cb;}}; return null; };
				IF_seek_and_find_first<ownCheckBoxMitarbeiter> 	seeker_cb_adress = (VEK<ownCheckBoxMitarbeiter> v)-> { for (ownCheckBoxMitarbeiter cb: v) { if (cb.isSelected()) {return cb;}}; return null; };

				ownCheckBoxEmail  		cb_mail = 	seeker_cb_mail.get_found(v_cbs);
				ownCheckBoxMitarbeiter  cb_ma = 	seeker_cb_adress.get_found(v_ma);
				
				ownMaskMapController ommk = new ownMaskMapController(KFIX_K_M_BT_Select_Fremd_Ansprechpartner.this);

				if (cb_ma!=null) {
					ommk.set_maskVal(_TAB.vkopf_kon.rb_km(), VKOPF_KON.name_ansprechpartner, cb_ma.r_adresse.get_ufs_kette(" ", ADRESSE.vorname,ADRESSE.name1), bibMSG.MV());
				}
				
				if (cb_mail!=null) {
					ommk.set_maskVal(_TAB.vkopf_kon.rb_km(), VKOPF_KON.email_auf_formular, cb_mail.r_eMail.get_ufs_dbVal(EMAIL.email), bibMSG.MV());
				}

				ownBasicContainer4PopUP.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
		}
		
		
	}
	
	
	private class ownMaskMapController extends RB_MaskControllerMap {

		public ownMaskMapController(IF_RB_Component p_component) throws myException {
			super(p_component);
		}

		@Override
		public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)	throws myException {
			return null;
		}
		
	}
	
}
