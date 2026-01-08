package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.ArrayList;
import java.util.Collections;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung extends MyE2_Button {

	private Rec20 record_kopf = null;

	private ArrayList<Rec20> position_list;

	IF_wrappedMulticomponentsInGrid wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._a_cm(c);} return g._s(comps.length); };

	private int last_abgeschlossenen_position_nummer;

	private E2_NavigationList navilist = null;

	private KFIX_K_M_masklist_position pos_list = null;
	
	@SuppressWarnings("unused")
	private boolean is_fixierung = false;
	
	public KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung(Rec20 recVkopf,E2_NavigationList p_compMap) throws myException {
		super(E2_ResourceIcon.get_RI("fix_pos_sortierung.png"), E2_ResourceIcon.get_RI("leer.png"));

		this.record_kopf = recVkopf;

		this.is_fixierung = record_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung);
		
		this.position_list 		= new ArrayList<>();

		this.navilist = p_compMap;

		this._ttt("Position Reihenfolge verändern");

		this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
		
		this.add_GlobalValidator(new ownValidator());
		
		this._aaa(new own_actionAgent());
	}

	public KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung(Rec20 recVkopf,KFIX_K_M_masklist_position o_parent) throws myException {
		super(E2_ResourceIcon.get_RI("fix_pos_sortierung.png"), E2_ResourceIcon.get_RI("leer.png"));
		this.record_kopf = recVkopf;

		this.is_fixierung = record_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung);

		this.position_list 		= new ArrayList<>();

		this.pos_list = o_parent;

		this.add_GlobalValidator(new ownValidator());
		
		this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
		
		this._aaa(new own_actionAgent());
	}

	private void fill_record_list(boolean ignore_deleted) throws myException{
		this.position_list.clear();

		String ignore_deleted_where_clause = "";
		if(ignore_deleted){
			ignore_deleted_where_clause = "NVL(JT_VPOS_KON.DELETED,'N')='N'";
		}

		RecList20 position_records = this.record_kopf.get_down_reclist20(VPOS_KON.id_vkopf_kon, ignore_deleted_where_clause , VPOS_KON.positionsnummer.fn());	

		for(Rec20 position:position_records){
			this.position_list.add(position);
		}

	}

	private boolean check_if_posnr_equals_pos_in_list() throws myException{
		boolean test_result = true;

		for(int i=0; i<this.position_list.size(); i++){
			int posnr_in_list = this.position_list.get(i).get_myLong_dbVal(VPOS_KON.positionsnummer).get_iValue();
			int real_posnr = i+1;
			if(posnr_in_list != real_posnr){
				test_result = false;
			}

		}
		return test_result;
	}

	private void correct_posnr()throws myException{
		int i_new_pos_nr = 1;
		MyE2_MessageVector mv = new MyE2_MessageVector();
		for(Rec20 position: this.position_list){
			if(position.get_myLong_dbVal(VPOS_KON.positionsnummer).get_iValue() > 0){
				position._nv(VPOS_KON.positionsnummer, ""+i_new_pos_nr, mv);
				i_new_pos_nr ++ ;
			}
		}

		for(Rec20 position: this.position_list){
			position._SAVE(true, mv);
		}

		for(Rec20 position: this.position_list){
			DEBUG.System_println("****\t" + position.get_fs_dbVal(VPOS_KON.id_vpos_kon) + ";\t" + position.get_fs_dbVal(VPOS_KON.positionsnummer) +";\t" + position.get_fs_dbVal(VPOS_KON.deleted, "N"));;
		}

	}

	private void pass_deleted_position_to_posnr_zero() throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		for(Rec20 position: this.position_list){
			if(position.is_yes_db_val(VPOS_KON.deleted) && record_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung)){
				position._nv(VPOS_KON.positionsnummer, "0", mv);
			}
		}

		for(Rec20 position: this.position_list){
			position._SAVE(true, mv);
		}

		bibMSG.add_MESSAGE(mv);
	}

	private void get_last_abgeschlossene_position() throws myException{
		for(int i=0; i<this.position_list.size(); i++){
			if(this.position_list.get(i).get_down_reclist20(VPOS_KON_TRAKT.id_vpos_kon, "", "").get(0).is_yes_db_val(VPOS_KON_TRAKT.abgeschlossen)){
				last_abgeschlossenen_position_nummer 		= i+1;
			}
		}


	}


	private class own_actionAgent extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.fill_record_list(false);

			if(check_if_posnr_equals_pos_in_list()){
				//autocorrection
				KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.pass_deleted_position_to_posnr_zero();

				KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.fill_record_list(false);

				KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.correct_posnr();

			}
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.fill_record_list(true);

			//normal_process
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.get_last_abgeschlossene_position();

			new position_aenderung_popUp();			
		}
	}

	private class position_aenderung_popUp extends E2_BasicModuleContainer{

		private MyLong last_active_id = new MyLong(0);

		private E2_Grid grd ;

		public position_aenderung_popUp() throws myException {
			super();

			grd  = new E2_Grid()._setSize(25, 25,100, 100, 100, 100, 100)._bo_ddd();

			_fill_grid(last_active_id);

			E2_Grid grd_button = new E2_Grid()._setSize(460,100)
					._a(new speichern_button(this)	, new RB_gld()._ins(0,2,0,2)._right_mid())
					._a(new abbruch_button(this)	, new RB_gld()._ins(0,2,0,2)._right_mid());

			MyE2_ContainerEx container = new MyE2_ContainerEx(this.grd);
			container.setHeight(new Extent(240));
			this.add(container	, E2_INSETS.I(2));
			this.add(grd_button	, E2_INSETS.I(2));

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(350), new MyE2_String("Fixierungsposition Reihenfolge verändern"));
		}

		public E2_Grid _fill_grid(MyLong activeId) throws myException{
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung oThis = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this;
			grd._clear();

			grd
			._a(""										, new RB_gld()._left_top()._ins(1)._col(new E2_ColorDDark()))
			._a(""										, new RB_gld()._left_top()._ins(1)._col(new E2_ColorDDark()))
			._a(new RB_lab("ID")				._i()	, new RB_gld()._left_top()._ins(1)._col(new E2_ColorDDark()))
			._a(new RB_lab("Abgeschlossen")		._i()	, new RB_gld()._left_top()._ins(1)._col(new E2_ColorDDark()))
			._a(new RB_lab("Buchungsnummer")	._i()	, new RB_gld()._left_top()._ins(1)._col(new E2_ColorDDark()))
			._a(new RB_lab("Positionnummer")	._i()	, new RB_gld()._left_top()._ins(1)._col(new E2_ColorDDark()))
			._a(new RB_lab("Menge")				._i()	, new RB_gld()._right_top()._ins(1)._col(new E2_ColorDDark()))
			;


			for(int i=0; i<oThis.position_list.size(); i++){
				if(oThis.position_list.get(i).is_no_db_val(VPOS_KON.deleted)){
					Rec20 record_kon_trakt = oThis.position_list.get(i).get_down_reclist20(VPOS_KON_TRAKT.id_vpos_kon, "", "").get(0);

					String buchungsnummer = record_kopf.get_fs_dbVal(VKOPF_KON.buchungsnummer, "<ID:"+ record_kopf.get_fs_dbVal(VKOPF_KON.id_vkopf_kon)+">")+ "-" + oThis.position_list.get(i).get_fs_dbVal(VPOS_KON.positionsnummer);

					String einheit = oThis.position_list.get(i).get_ufs_dbVal(VPOS_KON.einheitkurz, "");
					
					Color bg_col = null;
					if(activeId.get_iValue()==oThis.position_list.get(i).get_myLong_dbVal(VPOS_KON.id_vpos_kon).get_iValue()){
						bg_col = new E2_ColorLight();
					}else{
						bg_col = new E2_ColorBase();
					}

					grd._a(new zuordnung_button(this, oThis.position_list.get(i), true)											, new RB_gld()._center_mid()._ins(1)._col(bg_col))
					._a(new zuordnung_button(this, oThis.position_list.get(i), false)											, new RB_gld()._center_mid()._ins(1)._col(bg_col))
					._a(new RB_lab()._t(oThis.position_list.get(i).get_fs_dbVal(VPOS_KON.id_vpos_kon))							, new RB_gld()._left_top()._ins(1)._col(bg_col))
					._a(new RB_lab()._t(record_kon_trakt.get_fs_dbVal(VPOS_KON_TRAKT.abgeschlossen).equals("Y")?"Ja":"Nein")	, new RB_gld()._left_top()._ins(1)._col(bg_col))
					._a(new RB_lab()._t(buchungsnummer)																			, new RB_gld()._left_top()._ins(1)._col(bg_col))
					._a(new RB_lab()._t(""+(i+1))																				, new RB_gld()._left_top()._ins(1)._col(bg_col))
					._a(new RB_lab()._t(oThis.position_list.get(i).get_fs_dbVal(VPOS_KON.anzahl, "0") + " " + einheit)				, new RB_gld()._right_top()._ins(1)._col(bg_col))
					;
				}
			}
			return grd;
		}

	}

	private class speichern_button extends E2_Button{
		public speichern_button(position_aenderung_popUp popup) throws myException{
			super();
			this._t("Speichern")
			._aaa(new own_speichern_action())
			._aaa(new own_close_action(popup))
			._style(E2_Button.StyleTextButton())
			._width(75)
			._ttt("Die neue Reihenfolge wurde gespeichert")
			;
		}
	}

	private class abbruch_button extends E2_Button{
		public abbruch_button(position_aenderung_popUp popup) throws myException{
			super();
			this._t("Abbruch")
			._style(E2_Button.StyleTextButton())
			._width(75)
			._aaa(new own_close_action(popup))
			;
		}
	}




	private class own_speichern_action extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung oThis = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this;

			MyE2_MessageVector mv = new MyE2_MessageVector();

			for(int i=0; i<oThis.position_list.size(); i++){
				Rec20 position_record = oThis.position_list.get(i);

				//update of the new positionnumber
				position_record._nv(VPOS_KON.positionsnummer, ""+(i+1), mv);

			}
			if(mv.get_bIsOK()){
				for(Rec20 position: oThis.position_list){
					position._SAVE(true, mv);
				}
			}
			if(mv.get_bIsOK()){

			}else{
				bibMSG.add_MESSAGE(mv);
			}
		}

	}
	private class zuordnung_button extends E2_Button{


		private position_aenderung_popUp popup = null;

		private MyLong 					l_posNr			= new MyLong(0l);

		public zuordnung_button(position_aenderung_popUp p_popup, Rec20 record_position, boolean upZuordnung) throws myException {
			super();

			this.l_posNr			= record_position.get_myLong_dbVal(VPOS_KON.positionsnummer);
			this.popup 				= p_popup;

			E2_ResourceIcon icon 	= E2_ResourceIcon.get_RI(upZuordnung?"menge_hoch.png":"menge_runter.png");
			
			if(last_abgeschlossenen_position_nummer == 0){
				this._image(icon);
				this._aaa(new zuordnung_action(popup, upZuordnung, record_position));
			}else{	
				if(l_posNr.get_iValue() > last_abgeschlossenen_position_nummer){
					this._image(icon);
					this._aaa(new zuordnung_action(popup, upZuordnung, record_position));
				}
			}

		}
	}

	private class zuordnung_action extends XX_ActionAgent{

		private boolean b_up = false;

		private KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung oThis = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this;

		private Rec20 position_record;

		private position_aenderung_popUp popup;

		public zuordnung_action(position_aenderung_popUp p_popup, boolean upZuordnung,Rec20 record_position) {
			super();

			this.b_up = upZuordnung;

			this.position_record = record_position;

			this.popup = p_popup;

		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			int position_ = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.position_list.indexOf(position_record);

			MyLong id = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.position_list.get(position_).get_myLong_dbVal(VPOS_KON.id_vpos_kon);

			if(b_up){
				if(position_ !=  KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.last_abgeschlossenen_position_nummer){
					Collections.swap(oThis.position_list, position_, position_-1);
				}
			}else{
				if(position_ !=  (KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.position_list.size()-1)){
					Collections.swap(oThis.position_list, position_, position_+1);
				}
			}

			this.popup.last_active_id = id;

			this.popup._fill_grid(this.popup.last_active_id);
		}
	}


	private class own_close_action extends XX_ActionAgentWhenCloseWindow{

		private E2_BasicModuleContainer container;

		public own_close_action(E2_BasicModuleContainer oContainer) {
			super(oContainer);

			this.container = oContainer;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung oThis = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this;
			
			E2_NavigationList navlist = oThis.navilist;
			KFIX_K_M_masklist_position poslist = oThis.pos_list;
			
			if(navlist != null){
				navlist._REBUILD_ACTUAL_SITE(null);
			}else if(poslist != null){
				poslist.rb_set_db_value_manual(oThis.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
			}
			
			this.container.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			String cID_VKOPF_KON = KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung.this.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon);

			try 
			{
				String cQuery1 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON_EK IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";
				String cQuery2 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON_VK IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";
				String cQuery3 = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FU WHERE NVL(FU.DELETED,'N')='N' AND  FU.ID_VPOS_KON IN (SELECT VP.ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON VP WHERE NVL(VP.DELETED,'N')='N' AND  VP.ID_VKOPF_KON="+cID_VKOPF_KON+")";				

				int i1 = new Integer(bibDB.EinzelAbfrage(cQuery1)).intValue();
				int i2 = new Integer(bibDB.EinzelAbfrage(cQuery2)).intValue();
				int i3 = new Integer(bibDB.EinzelAbfrage(cQuery3)).intValue();

				int iAlle = i1+i2+i3;

				if (iAlle>0)
				{
					oMV.add(new MyE2_Alarm_Message("Umsortieren ist nur solange möglich, solange keine Fuhre auf den Vertrag gebucht ist!"));
				}

			} 
			catch (NumberFormatException e) 
			{
				e.printStackTrace();
				oMV.add(new MyE2_Alarm_Message("Fehler beim Feststellen der Buchungszahlen !"));
			}

			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}

	}
}
