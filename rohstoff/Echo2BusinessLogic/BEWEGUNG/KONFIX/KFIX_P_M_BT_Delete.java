package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_DELETER;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;

public class KFIX_P_M_BT_Delete extends MyE2_Button {

	private String             	id_vpos_kon = null;

	private GridLayoutData    	gl = MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I(0, 0, 0, 0));
	private MyE2_TextArea      	oTFDelGrund = new MyE2_TextArea( "",200,200,6);


	private MyE2_MessageVector mv = new MyE2_MessageVector();

	private Object parent = null;

	private boolean is_ek;

	public KFIX_P_M_BT_Delete(String p_id_vpos_kon, KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent) throws myException {

		super(E2_ResourceIcon.get_RI("delete_mini.png"), E2_ResourceIcon.get_RI("leer.png"));
		
		this.id_vpos_kon=bibALL.convertID2UnformattedID(p_id_vpos_kon);

		this.parent = oParent;

		this.gl.setColumnSpan(1);

		this.setLayoutData(this.gl);

		this.initialize_validator();

		this._ttt("Kontrakt-Position löschen");
		
		this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
		
		this.setLayoutData(new RB_gld()._center_mid());
		
		this.is_ek = VORGANGSART.EK_KONTRAKT.get_DBValue().equals(new Rec20_VPOS_KON()._fill_id(this.id_vpos_kon).get_fs_dbVal(VPOS_KON.vorgang_typ))?true:false;
	}

	public KFIX_P_M_BT_Delete(String p_id_vpos_kon, KFIX_K_M_masklist_position oParent) throws myException {

		super(E2_ResourceIcon.get_RI("delete_mini.png"), E2_ResourceIcon.get_RI("leer__.png"));
		
		this.id_vpos_kon=bibALL.convertID2UnformattedID(p_id_vpos_kon);

		this.parent = oParent;

		this.gl.setColumnSpan(1);

		this.setLayoutData(this.gl);

		this.initialize_validator();

		this._ttt("Kontrakt-Position löschen");
		
		this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
		
		this.setLayoutData(new RB_gld()._center_mid());
		
		this.is_ek = VORGANGSART.EK_KONTRAKT.get_DBValue().equals(new Rec20_VPOS_KON()._fill_id(this.id_vpos_kon).get_fs_dbVal(VPOS_KON.vorgang_typ))?true:false;
	}

	private void initialize_validator(){
		this.add_GlobalValidator(new ownValid_with_query(
				"SELECT    NVL(JT_VKOPF_KON.ABGESCHLOSSEN,'N') " +
						" FROM "+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VKOPF_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT   " +
						" WHERE " +
						" JT_VPOS_KON_TRAKT.ID_VPOS_KON = JT_VPOS_KON.ID_VPOS_KON AND " +
						" JT_VPOS_KON.ID_VKOPF_KON  = JT_VKOPF_KON.ID_VKOPF_KON AND " +
						" JT_VPOS_KON.ID_VPOS_KON="+this.id_vpos_kon
						,"N"
						,new MyE2_String("Aktion ist verboten, da der Kontrakt-Kopf bereits geschlossen ist!")));

		this.add_GlobalValidator(new ownValid_with_query(
				"SELECT   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N') " +
						" FROM "+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT   " +
						" WHERE " +
						" JT_VPOS_KON_TRAKT.ID_VPOS_KON = JT_VPOS_KON.ID_VPOS_KON AND " +
						" JT_VPOS_KON.ID_VPOS_KON="+this.id_vpos_kon
						,"N"
						, new MyE2_String("Aktion ist verboten, da Position bereits geschlossen ist!")));

		this.add_GlobalValidator(new ownValid_with_query(
				"SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE "+
						"ID_VPOS_KON_EK="+this.id_vpos_kon+" OR ID_VPOS_KON_VK="+this.id_vpos_kon
						,"0"
						, new MyE2_String("Aktion ist verboten, die Kontraktposition bereits EK-VK-Zuordnungen hat!")));


		this.add_GlobalValidator(new ownFuhreDeletePruefung());
		this.add_oActionAgent(new ownAction());	
		
		if(this.is_ek){
			this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_DELETE.getValidator());
		}else{
			this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_DELETE.getValidator());
		}
	}

	private class ownValid_with_query extends XX_ActionValidator_NG {
		private String 			sql_query = null;
		private VEK<String>  	v_allowed_answer = new VEK<>();
		private MyE2_String     message_when_not_allowed = null;

		/**
		 * @param p_sql_query
		 * @param p_v_allowed_answer
		 * @param p_message_when_not_allowed
		 */
		public ownValid_with_query(String p_sql_query, String p_allowed_answer,	MyE2_String p_message_when_not_allowed) {
			super();
			this.sql_query = p_sql_query;
			this.v_allowed_answer.add(p_allowed_answer);
			this.message_when_not_allowed = p_message_when_not_allowed;
		}



		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			String c = bibDB.EinzelAbfrage(this.sql_query);
			if (!this.v_allowed_answer.contains(c)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(this.message_when_not_allowed));
			}
			return mv;
		}

	}


	private class ownFuhreDeletePruefung extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			KFIX_P_M_BT_Delete.this.mv.clear();

			RECORD_VPOS_KON  recVpos = new RECORD_VPOS_KON(KFIX_P_M_BT_Delete.this.id_vpos_kon);

			RECLIST_VPOS_TPA_FUHRE reclistFuhren = null;

			if (recVpos.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_EK_KONTRAKT))
			{
				reclistFuhren = recVpos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_ek("NVL("+VPOS_TPA_FUHRE.ist_storniert.tnfn()+",'N')='N' " +
						" AND NVL("+VPOS_TPA_FUHRE.deleted.tnfn()+",'N')='N'",null,true);
			}
			else
			{
				reclistFuhren = recVpos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_kon_vk("NVL("+VPOS_TPA_FUHRE.ist_storniert.tnfn()+",'N')='N' " +
						" AND NVL("+VPOS_TPA_FUHRE.deleted.tnfn()+",'N')='N'",null,true);
			}

			//fuhrenorte noch checken
			RECLIST_VPOS_TPA_FUHRE_ORT  rl_ort = recVpos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_kon("NVL("+VPOS_TPA_FUHRE_ORT.deleted.tnfn()+",'N')='N'", null, true);

			if (reclistFuhren.get_vKeyValues().size()>0 || rl_ort.size()>0) {
				KFIX_P_M_BT_Delete.this.mv.add(new MyE2_Alarm_Message("Die Kontraktposition hat zugeordnete, unstornierte Fuhren/Fuhrenorte-> Löschen ist verboten !!"));
			}

			return KFIX_P_M_BT_Delete.this.mv;
		}

	}


	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentGroupHorizontal oGroup = new E2_ComponentGroupHorizontal(
					0,
					new MyE2_Label(new MyE2_String("Lösch-Grund: ")),
					oTFDelGrund, E2_INSETS.I_0_10_10_10
					);

			E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(	new MyE2_String("Sicherheitsabfrage"),
					new MyE2_String("Löschen ?"),
					null,
					oGroup,
					new ValidInputText(),
					new MyE2_String("JA"),
					new MyE2_String("NEIN"),
					new Extent(400),
					new Extent(300));

			oConfirm.set_oExtMINIMALHeight(new Extent(300));
			oConfirm.set_oExtMINIMALWidth(new Extent(450));

			oConfirm.set_ActionAgentForOK(new ownActionDelete());

			oConfirm.show_POPUP_BOX();
		}
	}



	private class ownActionDelete extends XX_ActionAgent {
		public ownActionDelete() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String> vSQL_4_delete = new Vector<>();

			BS_DELETER oDel = new BS_DELETER(	
					_TAB.vpos_kon.fullTableName(),
					id_vpos_kon,
					oTFDelGrund.getText());
			vSQL_4_delete.addAll(oDel.get_vDeleteStatements());

			KFIX_P_M_BT_Delete.this.mv._add(bibDB.ExecMultiSQLVector(vSQL_4_delete, false));

			if(KFIX_P_M_BT_Delete.this.mv.get_bIsOK()){
				bibDB.Commit();

				if(KFIX_P_M_BT_Delete.this.parent instanceof KFIX_K_L_EXPANDER_4_ComponentMAP_NG){

					
					Vector<String> selected_kopf_id = ((XX_List_EXPANDER_4_ComponentMAP) KFIX_P_M_BT_Delete.this.parent).get_oNavigationList().get_vSelectedIDs_Unformated_Select_the_one_and_only();

					((XX_List_EXPANDER_4_ComponentMAP) KFIX_P_M_BT_Delete.this.parent).get_oNavigationList()._REBUILD_ACTUAL_SITE(true, true, selected_kopf_id);
				}
				else if(KFIX_P_M_BT_Delete.this.parent instanceof KFIX_K_M_masklist_position){

					
					KFIX_K_M_masklist_position oParent = (KFIX_K_M_masklist_position)KFIX_P_M_BT_Delete.this.parent;

					String kopf_id = new Rec20(_TAB.vpos_kon)._fill_id(KFIX_P_M_BT_Delete.this.id_vpos_kon).get_ufs_dbVal(VPOS_KON.id_vkopf_kon, "");

					oParent.rb_set_db_value_manual(kopf_id);
				}
			}
		}

	}

	private class ValidInputText extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector  vRueck = new MyE2_MessageVector();
			String cText = bibALL.null2leer(oTFDelGrund.getText()).trim();
			if (cText.equals("") || cText.length()>200)
				vRueck.add_MESSAGE(new MyE2_Alarm_Message("Bitte einen Grund angeben (<= 200 Zeichen) !!!"), false);

			return vRueck;
		}

		public MyE2_MessageVector isValid(String cID)
		{
			return new MyE2_MessageVector();
		}

	}

}
