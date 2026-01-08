package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Component;
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
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public abstract class RB_btGetLagerAuswahl extends E2_Button {

	private RB_HL_SearchAdressStation21 	searchField 					= null;

	private E2_BasicModuleContainer popUp;

	private String OrderBy = " ";
	
	int status =0;
	private ownTitelButton 	titelBt_adresse; 
	private ownTitelButton 	titelBt_Strasse;
	private ownTitelButton 	titelBt_id;
	private ownTitelButton 	titelBt_ort_plz;
	private E2_Grid 		ownResultGrid;
	
	public RB_btGetLagerAuswahl(RB_HL_SearchAdressStation21 search_field) {
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
				this.searchField.get_width_popup_window(), 
				this.searchField.get_height_popup_window(),
				this.searchField.get_title_of_popup()
				);

		popUp.save_focusable_components_outside(this.searchField.get_tf_search_input());
	}

	private void _fill_popup() throws myException{

		SEL lageradresseQuery = new SEL(_TAB.adresse).FROM(_TAB.adresse).INNERJOIN(_TAB.lieferadresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_liefer)
							.WHERE(new vglParam(LIEFERADRESSE.id_adresse_basis))
							.AND(new vglParam(ADRESSE.aktiv))
							.AND(new vglParam(ADRESSE.id_adresse, COMP.GT))
							.ORDER(this.OrderBy)
							;
		
				
		VEK<ParamDataObject> v1 = new VEK<ParamDataObject>()
							._a( new Param_Long(new MyLong(get_id_main_adress()).get_oLong()))
							._a( new Param_String("","Y"))
							._a( new Param_Long(1000l))
							;		
				

		SEL hauptadresseQuery = new SEL(_TAB.adresse).FROM(_TAB.adresse).WHERE(new vglParam(ADRESSE.id_adresse))
							.AND(new vglParam(ADRESSE.aktiv))
							.AND(new vglParam(ADRESSE.id_adresse, COMP.GT))
							.ORDERUP(ADRESSE.ort);

		VEK<ParamDataObject> v2 = new VEK<ParamDataObject>()
							._a( new Param_Long(new MyLong(get_id_main_adress()).get_oLong()))
							._a( new Param_String("","Y"))
							._a( new Param_Long(1000l))
							;		

		RecList21 reclist_hauptAdresse = new RecList21(_TAB.adresse)._fill(new SqlStringExtended(hauptadresseQuery.s())._addParameters(v1));
		RecList21 reclist_lagerAdresse = new RecList21(_TAB.adresse)._fill(new SqlStringExtended(lageradresseQuery.s())._addParameters(v2));
		
		RB_ResultButtonArrays rba = new RB_ResultButtonArrays();

		ownResultGrid._clear()
					._a(this.titelBt_adresse, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.LEFT_TOP))
					._a(this.titelBt_Strasse, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.LEFT_TOP))
					._a(this.titelBt_ort_plz, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.LEFT_TOP))
					._a(this.titelBt_id		, new RB_gld()._col(new E2_ColorDark())._al(E2_ALIGN.RIGHT_TOP));

		for(Rec21 ra: reclist_hauptAdresse){
			rba.add(this.getResults(ra,true));
		}


		for(Rec21 ra: reclist_lagerAdresse){
			rba.add(this.getResults(ra,false));
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

	
	private RB_ResultButton[] getResults(Rec21 r, boolean bold) throws myException {
		RB_ResultButton[] rb_bts = new RB_ResultButton[4];

		Rec21_adresse ra = new Rec21_adresse(r);
		
		rb_bts[0] = new ownResultButton(this.searchField,ra,ra.get__FullNameAndAdress_Typ2(),"");
		rb_bts[1] = new ownResultButton(this.searchField,ra,ra.get__strasseHausnummer(" "),	""	);
		rb_bts[2] = new ownResultButton(this.searchField,ra,ra.getUfs(ADRESSE.ort,"") + "(" + ra.getUfs(ADRESSE.plz,"") + ")" ,"");
		rb_bts[3] = new ownResultButton(this.searchField,ra,""+ra.getId(),"");
		
		if (bold) {
			for (RB_ResultButton b: rb_bts) {
				b._b();
			}
		}
		
		return rb_bts;

	}
	
	
	private class ownResultButton extends RB_ResultButton{

		private String sort_string = null;

		public ownResultButton(RB_SearchField calling_searchField, MyRECORD_IF_RECORDS p_result_record, String cText, String sort_string) throws myException {
			super(calling_searchField, p_result_record, cText, E2_Button.StyleTextButton());
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

	}

	private class ownSortingAction extends XX_ActionAgent{


		String titelString = null;
		String last_selected_field = "";


		public ownSortingAction(String field_to_sort){
			super();
			this.titelString = field_to_sort.toUpperCase();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			OrderBy = "";
			
			RB_btGetLagerAuswahl.this.titelBt_adresse.update_status("empty10.png");
			RB_btGetLagerAuswahl.this.titelBt_id.update_status("empty10.png");
			RB_btGetLagerAuswahl.this.titelBt_ort_plz.update_status("empty10.png");
			RB_btGetLagerAuswahl.this.titelBt_Strasse.update_status("empty10.png");
			
			E2_Button sourceBt = (E2_Button) oExecInfo.get_MyActionEvent().getSource();
			ownTitelButton oParent = (ownTitelButton)sourceBt.getParent();
			
			if(last_selected_field.equals(sourceBt.getText().toUpperCase())){
				status++;
				
			}else{
				status = 0;
			}
			
			if(titelString.equals("ADRESSE")){
				OrderBy = " VORNAME || ' ' || NAME1|| ' ' ||NAME2|| ' ' ||ORT";
			}else if(titelString.equals("STRASSE")){
				OrderBy = " STRASSE || ' ' || HAUSNUMMER";
			}else if(titelString.equals("ID")){
				OrderBy += " ID_ADRESSE";
			}else if(titelString.equals("ORT")){
				OrderBy = " ORT ";
			}
			if(status%2==0){
				oParent.update_status("sortup.png");
				OrderBy += " ASC";
			}else{
				oParent.update_status("sortdown.png");
				OrderBy += " DESC";
			}

			_fill_popup();
			
			last_selected_field = titelString;
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
