package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class RB_bt_get_lager extends E2_Button {

	private RB_HL_SearchAdressStation 	searchField 					= null;

	private E2_BasicModuleContainer popUp;

	private String OrderBy = " ";
	
	int status =0;
	private ownTitelButton 	titelBt_adresse; 
	private ownTitelButton 	titelBt_Strasse;
	private ownTitelButton 	titelBt_id;
	private ownTitelButton 	titelBt_ort_plz;
	private E2_Grid 		ownResultGrid;
	
	public RB_bt_get_lager(RB_HL_SearchAdressStation search_field) {
		super();

		this.__setImages(E2_ResourceIcon.get_RI("firma.png"), E2_ResourceIcon.get_RI("firma__.png"));
		this.searchField = search_field;

		this._aaa(new ownSearchAction());

		this.titelBt_adresse = new ownTitelButton("Adresse");
		this.titelBt_Strasse = new ownTitelButton("Strasse");
		this.titelBt_ort_plz = new ownTitelButton("Ort");
		this.titelBt_id = new ownTitelButton("ID");

		this.ownResultGrid = new E2_Grid()._setSize(300,300,300,50);
		
	}

	public abstract void do_after_select_lager(Rec20 lager_record);

	public abstract String get_id_main_adress();

	private class ownSearchAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_search_lager();
		}	
	}



	private void _search_lager() throws myException{

		popUp = searchField.get_container_4_popupWindow();
		popUp.RESET_Content(true);
		
		this._fill_popup();

		popUp.CREATE_AND_SHOW_POPUPWINDOW(
				this.searchField.get_height_popup_window(), 
				this.searchField.get_width_popup_window(),
				this.searchField.get_title_of_popup()
				);

		popUp.save_focusable_components_outside(this.searchField.get_tf_search_input());
	}

	private void _fill_popup() throws myException{

		SEL lageradresseQuery = new SEL()
				.ADDFIELD(ADRESSE.id_adresse)
				.FROM(_TAB.adresse)
				.INNERJOIN(_TAB.lieferadresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_liefer)
				.WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, get_id_main_adress()))
				.AND(new vgl(ADRESSE.aktiv, "Y"))
				.AND(new vgl(ADRESSE.id_adresse, COMP.GT,"1000"));
				

		SEL hauptadresseQuery = new SEL()
				.ADDFIELD(ADRESSE.id_adresse)
				.FROM(_TAB.adresse)
				.WHERE(new vgl(ADRESSE.id_adresse, get_id_main_adress()))
				.AND(new vgl(ADRESSE.aktiv, "Y"))
				.AND(new vgl(ADRESSE.id_adresse, COMP.GT,"1000"))
				.ORDERUP(ADRESSE.ort);

		RecList20 reclist_hauptAdresse = new RecList20(_TAB.adresse)._fill(hauptadresseQuery.s());
		RecList20 reclist_lagerAdresse = new RecList20(_TAB.adresse)._fill(lageradresseQuery.s()+OrderBy);
		
		RB_ResultButtonArrays rba = new RB_ResultButtonArrays();

		ownResultGrid._clear()
		._a(this.titelBt_adresse, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.LEFT_TOP))
		._a(this.titelBt_Strasse, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.LEFT_TOP))
		._a(this.titelBt_ort_plz, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.LEFT_TOP))
		._a(this.titelBt_id		, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.RIGHT_TOP));

		for(Rec20 record_lager: reclist_hauptAdresse){
			RB_ResultButton[] rb_bts = new RB_ResultButton[4];

			String uf_id = record_lager.get_ufs_dbVal(ADRESSE.id_adresse);

			RECORD_ADRESSE_extend old_record_adresse = new RECORD_ADRESSE_extend(uf_id);

			rb_bts[0] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get__FullNameAndAdress_Typ2(),
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					).set_bold();

			rb_bts[1] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get__strasse_hausnummer(),
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					).set_bold();

			rb_bts[2] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get_ORT_cUF_NN("") + "(" + old_record_adresse.get_PLZ_cUF_NN("") + ")" ,
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					).set_bold();

			rb_bts[3] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get_ID_ADRESSE_cF(),
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					).set_bold();

			rba.add(rb_bts);
		}


		for(Rec20 record_lager: reclist_lagerAdresse){

			RB_ResultButton[] rb_bts = new RB_ResultButton[4];

			String uf_id = record_lager.get_ufs_dbVal(ADRESSE.id_adresse);

			RECORD_ADRESSE_extend old_record_adresse = new RECORD_ADRESSE_extend(uf_id);

			rb_bts[0] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get__FullNameAndAdress_Typ2(),
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					);

			rb_bts[1] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get__strasse_hausnummer(),
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					);

			
			rb_bts[2] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get_ORT_cUF_NN("") + "  (" + old_record_adresse.get_PLZ_cUF_NN("") + ")" ,
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					);

			rb_bts[3] = new ownResultButton(
					this.searchField,
					old_record_adresse,
					old_record_adresse.get_ID_ADRESSE_cF(),
					MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(),
					""
					);
			
			rba.add(rb_bts);

		}
		
		for(RB_ResultButton_IF[] bt:rba){
			ownResultGrid
			._a(bt[0].me(), new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(0,0,1,0))
			._a(bt[1].me(), new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(0,0,1,0))
			._a(bt[2].me(), new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(0,0,1,0))
			._a(bt[3].me(), new RB_gld()._al(E2_ALIGN.RIGHT_TOP)._ins(0,0,1,0));
		}

		popUp.add(ownResultGrid, E2_INSETS.I(2));
	}

	private class ownResultButton extends RB_ResultButton{

		private String sort_string = null;

		public ownResultButton(RB_SearchField calling_searchField, MyRECORD_IF_RECORDS p_result_record, String cText,
				MutableStyle oStyle, String sort_string) throws myException {

			super(calling_searchField, p_result_record, cText, oStyle);
			this.sort_string = sort_string;
			this.add_oActionAgent(new RB_ResultButton_action(p_result_record, calling_searchField));
		
		}	

		@Override
		public Component me() throws myException {
			return this;
		}

		@Override
		public String get_sort_string() throws myException {
			return sort_string;
		}
		
		public ownResultButton set_bold(){
			this._b();
			return this;
		}
	}

	private class ownSortingAction extends XX_ActionAgent{


		String field = null;
		String last_selected_field = "";


		public ownSortingAction(String field_to_sort){
			super();
			this.field = field_to_sort.toUpperCase();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			OrderBy = "";
			
			RB_bt_get_lager.this.titelBt_adresse.update_status("empty10.png");
			RB_bt_get_lager.this.titelBt_id.update_status("empty10.png");
			RB_bt_get_lager.this.titelBt_ort_plz.update_status("empty10.png");
			RB_bt_get_lager.this.titelBt_Strasse.update_status("empty10.png");
			
			E2_Button sourceBt = (E2_Button) oExecInfo.get_MyActionEvent().getSource();
			ownTitelButton oParent = (ownTitelButton)sourceBt.getParent();
			
			if(last_selected_field.equals(sourceBt.getText().toUpperCase())){
				status++;
				
			}else{
				status = 0;
			}
			
			if(field.equals("ADRESSE")){
				OrderBy = " ORDER BY VORNAME || ' ' || NAME1|| ' ' ||NAME2|| ' ' ||ORT";
			}else if(field.equals("STRASSE")){
				OrderBy = " ORDER BY STRASSE || ' ' || HAUSNUMMER";
			}else if(field.equals("ID")){
				OrderBy += " ORDER BY ID_ADRESSE";
			}else if(field.equals("ORT")){
				OrderBy = " ORDER BY ORT ";
			}
			if(status%2==0){
				oParent.update_status("sortup.png");
				OrderBy += " ASC";
			}else{
				oParent.update_status("sortdown.png");
				OrderBy += " DESC";
			}

			_fill_popup();
			
			last_selected_field = field;
		}

	}

	private class ownTitelButton extends E2_Grid{
		private E2_Button bt= null;
		private RB_lab status_lbl = null;
		
		public ownTitelButton(String oText) {
			super();
			
			this.bt 		= new E2_Button()._t(oText)._aaa(new ownSortingAction(oText));
			this.status_lbl = new RB_lab();
			
			this._s(2)._a(bt,new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(0,0,2,0))._a(status_lbl, new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,0,0,0));
		}
		
		public ownTitelButton update_status(String ikon_name_with_extension){
			if(S.isFull(ikon_name_with_extension)){
				this.status_lbl.setIcon(E2_ResourceIcon.get_RI(ikon_name_with_extension));
			}
			return this;
		}
	}
	
	
}
