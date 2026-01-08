package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES_ENUM;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;


/**
 * suchfeld ohne datenrueckgabe beim speichern
 * @author martin
 *
 */
public abstract class SearchDialog_Base extends E2_BasicModuleContainer implements MyE2IF__Component /*, IF_RB_Component */{

	
	
	// Grid für die Selektions-Auswahl innerhalb des Dialogs
	//private ownButtonEditAdresse  	bt_editAdresse = new ownButtonEditAdresse();

	protected E2_MutableStyle style_normal 			= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
	protected E2_MutableStyle style_bold_normal 	= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontBold());
	protected E2_MutableStyle style_kursive 		= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontItalic());
	protected E2_MutableStyle style_bold_kursive 	= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontBoldItalic());
	
	public enum SORTSTATUS {
		 UP("sortup.png")
		,DOWN("sortdown.png")
		,NEUTRAL("empty10.png")
		;
		private String 		name_icon = 	null;
		
		private SORTSTATUS(String p_name_icon) {
			this.name_icon = p_name_icon;
		}
		
		public SORTSTATUS get_next() {
			SORTSTATUS s_next  = null;
			switch(this) {
				case UP:
					s_next=DOWN;
					break;
				case DOWN:
					s_next = UP;
					break;
				case NEUTRAL:
					s_next = UP;
					break;
				default:
					s_next = UP;
			}
			return s_next;
		}

		public MyE2_Label icon() {
			return new MyE2_Label(E2_ResourceIcon.get_RI(this.name_icon));
		}
		
		
		public boolean is_up() {
			boolean b_rueck  = true;
			switch(this) {
				case DOWN:
					b_rueck  = false;
					break;
				default:
					b_rueck  = true;
			}
			return b_rueck;
		}

		public static SORTSTATUS get_status(String name) {
			for (SORTSTATUS s: SORTSTATUS.values()) {
				if (s.name().equals(name)) {
					return s;
				}
			}
			return null;
		}
	}

	

	// Abstrakte Methoden zum parametrisieren der konkreten Suche
	protected abstract MyE2_MessageVector		  		execute_searchquery_and_fill_resultbutton_array() throws myException;
	public abstract void 							    fill_grid_ResultSet(MyE2_Grid grid_4_popup, SearchDialog_vecResultButtons vektor_buttons) throws myException;
	
	public abstract void 								fill_grid_Selector(E2_Grid grid_4_searchParameter) throws myException;
	
	public abstract String 								get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException;
	public abstract MyRECORD_IF_RECORDS 				get_result_record_from_string(String unformated_MaskValue) throws myException;
	
	
	
	protected SearchDialog_ResultColumnList  	vecColumnList 	= new SearchDialog_ResultColumnList();
	protected SearchDialog_SelectorList 		selections_list = new SearchDialog_SelectorList();	
	
	private bibSES_ENUM             key_to_store_resultbutton_array_in_session = null;
	
	private int 					m_iMaxResults = 0;

	private And                     and_statement_collector_4_basic = new And();
	private And                     and_statement_collector_4_search = new And();
	
	private MyE2_Row				row_headerArea = new MyE2_Row();
	private E2_Grid					grid_container_4_searchParameter 	= new E2_Grid();
	
	private Component				_extra_headerArea = null;  // zusatz-Komponente, das rechts neben dem Selektions-Grid angezeigt werden kann, z.B. für Erklärungen.
	
	private MyE2_Grid               grid_container_4_searchResult 		= new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	private Extent                  width_popup_window = new Extent(600);
	private Extent                  height_popup_window = new Extent(600);
	private MyE2_String             title_of_popup = new MyE2_String("Bitte wählen ...");
	
	private MyE2_Button				buttonRefreshSearch  = new MyE2_Button("Suche aktualisieren",E2_ResourceIcon.get_RI("suche.png"),E2_ResourceIcon.get_RI("leer.png"));
	private XX_ActionAgent			oAgentRefreshSearch ;
	
	private SearchDialog_vecResultButtons     rb_vecResultButtons = new SearchDialog_vecResultButtons();
	
	// Action, die aufgerufen wird, bei Auswahl eines Eintrags
	private XX_ActionAgent			oAgentAfterFound;
	

	//key, um die sortierung zu speichern. ist er vorhanden, dann kann gespeichert werden
	private	ENUM_USER_SAVEKEY  		key_4_save_sorting = null;

	private String					resultValue = null;
	

	
	/**
	 * Konstruktor für das Suchfenster
	 * @author manfred
	 * @date 08.08.2017
	 *
	 * 
	 * @throws myException
	 */
	public SearchDialog_Base(  ) throws myException {
		super();
		 this.oAgentRefreshSearch = new cAgentRefreshResult();
		 this.selections_list.set_oDefaultSelectionAction(oAgentRefreshSearch);
	}


	
	
	/**
	 *  
	 * @author manfred
	 * @date 08.08.2017
	 *
	 * @throws myException
	 */
	protected void initResultWindow() throws myException{
		row_headerArea.add(grid_container_4_searchParameter);
		this.add(row_headerArea);
		
		if(_extra_headerArea != null){
			row_headerArea.add(_extra_headerArea,MyE2_Row.LAYOUT_LEFT_TOP(E2_INSETS.I_10_0_0_0));
		}
		
		
//		this.add(grid_container_4_searchParameter);
		this.add(grid_container_4_searchResult);

		// Selektor-Bereich nur einmal initialisieren
		grid_container_4_searchParameter.removeAll();		

		fill_grid_Selector(grid_container_4_searchParameter);
		grid_container_4_searchParameter._endLine(grid_container_4_searchParameter.get_gridLayoutData());

	}
	
	
	public SearchDialog_SelectorList getSelections_list() {
		return selections_list;
	}

	
	public SearchDialog_Base setSelections_list(SearchDialog_SelectorList selections_list) {
		this.selections_list = selections_list;
		return this;
	}
	
	
	
	/**
	 * Zeigt das Fenster an, mit einer initalen Befüllung 
	 * @author manfred
	 * @date 09.08.2017
	 *
	 * @throws myException
	 */
	public void show() throws myException{
		
		initResultWindow();
		
		// initial suchen und füllen 
		this.refreshResultSet();
		this.fillResultWindow();
		
		this.CREATE_AND_SHOW_POPUPWINDOW(	
				this.get_width_popup_window(), 
				this.get_height_popup_window(),
				this.get_title_of_popup());
	}

	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 08.08.2017
	 *
	 * @throws myException
	 */
	protected void refreshResultSet() throws myException{
		execute_searchquery_and_fill_resultbutton_array();
	}
	

	/**
	 * füllen des Result-Windows
	 * @author manfred
	 * @date 08.08.2017
	 *
	 * @throws myException
	 */
	protected void fillResultWindow() throws myException{
		
		grid_container_4_searchResult.removeAll();
		
		// sortieren, falls eine Spalte als Sortierung definiert ist
		rb_vecResultButtons.sort();
		
		// füllen des Resultgrids
		fill_grid_ResultSet( grid_container_4_searchResult, rb_vecResultButtons );
		
		this.get_rb_vecResultButtons().set_1st_column_focusable();
		MyE2IF__Component  focusable = this.get_rb_vecResultButtons().get_focus_component();
		bibALL.setFocus((Component) focusable);
	}
	
	
	
	/**
	 * Generiert das Grid und baut den Header des Grids auf
	 * @author manfred
	 * @date 14.08.2017
	 *
	 * @return
	 * @throws myException
	 */
	protected E2_Grid4MaskSimple initResultGrid() throws myException{

		Integer     i_actual_sort_col = this.get_rb_vecResultButtons().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_vecResultButtons().get_actual_sort_status();
		
		// Spaltenbreiten festlegen.
		int[] arrWitdth = new int[vecColumnList.size()]; 
		
		
		E2_Grid4MaskSimple gs = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		gs.setSize(vecColumnList.size());
		
		gs  .def_(new E2_ColorDark()).def_(new Alignment(Alignment.LEFT,Alignment.TOP));
		
		// Headerbuttons erzeugen
		for(int i=0; i < vecColumnList.size(); i++){
			SearchDialog_ResultColumn col =  vecColumnList.get(i);
			SearchDialog_ResultList_HeaderButton btnHeader = new SearchDialog_ResultList_HeaderButton(this,i,col.get_Heading(),col.get_StyleHeader());
					
			if (i_actual_sort_col != null && i_actual_sort_col == i){
				btnHeader.set_sortstatus_actual(status_actual);
			}
			gs.def_(col._AlignmentHeader);
			gs.add_(btnHeader);

			// Spaltenbreite merken
			arrWitdth[i]=col._width;
		}
		
		//Spaltenbreite setzen, geht nur hier, nicht oben???
		gs._setSize(arrWitdth);
		
		return gs;
	}
	
	


	public int get_iMaxResults() {
		return m_iMaxResults;
	}

	public SearchDialog_Base set_iMaxResults(int iMaxResults) {
		m_iMaxResults = iMaxResults;
		return this;
	}


	public Extent get_width_popup_window() {
		return width_popup_window;
	}

	public SearchDialog_Base set_width_popup_window(Extent p_width_popup_window) {
		this.width_popup_window = p_width_popup_window;
		return this;
	}

	public Extent get_height_popup_window() {
		return height_popup_window;
	}

	public SearchDialog_Base set_height_popup_window(Extent p_height_popup_window) {
		this.height_popup_window = p_height_popup_window;
		return this;
	}

	public MyE2_String get_title_of_popup() {
		return title_of_popup;
	}

	public SearchDialog_Base set_title_of_popup(MyE2_String p_title_of_popup) {
		this.title_of_popup = p_title_of_popup;
		return this;
	}

	public Component get_extra_headerArea() {
		return _extra_headerArea;
	}
	
	public SearchDialog_Base set_extra_headerArea(Component _extra_headerArea) {
		this._extra_headerArea = _extra_headerArea;
		return this;
	}
	
	
	
	public And and_statement_collector_4_basic() {
		return and_statement_collector_4_basic;
	}

	public And and_statement_collector_4_search() {
		return and_statement_collector_4_search;
	}

	public MyE2_Grid get_grid_container_4_searchResult() {
		return grid_container_4_searchResult;
	}


	public SearchDialog_vecResultButtons get_rb_vecResultButtons() {
		return rb_vecResultButtons;
	}


	public ENUM_USER_SAVEKEY get_key_4_save_sorting() {
		return key_4_save_sorting;
	}

	public void set_key_4_save_sorting(ENUM_USER_SAVEKEY p_key_4_save_sorting) {
		this.key_4_save_sorting = p_key_4_save_sorting;
		
		
		//evtl. vorhandene gespeicherte sortierung laden
		if (get_key_4_save_sorting()!=null) {
			try {
				new SearchDialog_UserSetting_Sort(this).RESTORE();
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}
	

	public bibSES_ENUM get_key_to_store_resultbutton_array_in_session() {
		return key_to_store_resultbutton_array_in_session;
	}

	public void set_key_to_store_resultbutton_array_in_session(bibSES_ENUM p_key_to_store_resultbutton_array_in_session) {
		this.key_to_store_resultbutton_array_in_session = p_key_to_store_resultbutton_array_in_session;
	}


	public void setAgentAfterFound(XX_ActionAgent oAgentAfterFound) {
		this.oAgentAfterFound = oAgentAfterFound;
	}
	
	public XX_ActionAgent getAgentAfterFound(){
		return this.oAgentAfterFound;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	

	
	
	/**
	 * Default Action Agent für den Refresh der Suche
	 * @author manfred
	 * @date 13.09.2017
	 *
	 */
	private class cAgentRefreshResult extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SearchDialog_Base.this.refreshResultSet();
			SearchDialog_Base.this.fillResultWindow();
		}
		
	}





	
	

}
