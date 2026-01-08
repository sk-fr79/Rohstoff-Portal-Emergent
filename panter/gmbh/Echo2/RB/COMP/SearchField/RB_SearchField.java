package panter.gmbh.Echo2.RB.COMP.SearchField;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerFillFieldsAfterAction;
import panter.gmbh.Echo2.RB.COMP.IF_hasChangeActions;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES_ENUM;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;


/**
 * suchfeld ohne datenrueckgabe beim speichern
 * @author martin
 *
 */
public abstract class RB_SearchField extends E2_Grid implements MyE2IF__Component, IF_RB_Component, IF_hasChangeActions {

	/**
	 * kann benutzt werden, um eine fill-after-action aktion zu steuern
	 */
	private VEK<RB_MaskControllerFillFieldsAfterAction>    vFillersAfterAction = new VEK<>();

	
	/**
	 * 
	 * @author martin
	 * @date 28.12.2018
	 * weitere moeglichkeit, actions nach sucherfolg zu definieren
	 */
	private VEK<XX_ActionAgent>  						  changeActions = new VEK<>();
	
	
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

	

	

	/**
	 * aufbau der ergebnis-componente fuer die darstellung eine lesbaren suchergebnisses (einfach: ein label oder komplexer)
	 * @param c_result_value_4_db
	 * @return
	 * @throws myException
	 */
	public abstract void  	render_search_result_visible_on_mask(E2_Grid grid_result_container, String c_result_value_4_db) throws myException;
	
	/**
	 * am besten mit innere ableitung eines E2_BasicModuleContainer wegen des speicherns
	 * @return
	 * @throws myException
	 */
	public abstract E2_BasicModuleContainer    			generate_container_4_popup_window() throws myException;
	public abstract MyE2_MessageVector             		do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv) throws myException;
	
	public abstract XX_ActionAgent  					generate_StartSearchAction() throws myException;
	public abstract XX_ActionAgent  					generate_EraseButtonAction() throws myException;
	
	protected abstract MyE2_MessageVector		  		execute_searchquery_and_fill_resultbutton_array(String c_searchtext) throws myException;
	public abstract void 							    fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO oExecInfo) throws myException;

	public abstract String 								get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException;

	//public abstract MyRECORD_IF_RECORDS 				get_result_record_from_string(String unformated_MaskValue) throws myException;
	///////////

	
	
	private bibSES_ENUM                                 key_to_store_resultbutton_array_in_session = null;
	
	
	
	
	
	private RB_KF Key = null;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();


	private RB_TextField    		tf_search_input = new RB_TextField();

	private MyE2_Button				buttonStartSearch	= new MyE2_Button(E2_ResourceIcon.get_RI("suche.png"),E2_ResourceIcon.get_RI("leer.png"));
	private MyE2_Button				buttonErase	= new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),E2_ResourceIcon.get_RI("leer.png"));
	private boolean   				b_showEraser = false;
	
	private E2_Grid       			gridContainer_to_show_searchResult = new E2_Grid()._w100()._bo_no();
	
	private int 					m_iMaxResults = 0;

	private And                     and_statement_collector_4_basic = new And();
	private And                     and_statement_collector_4_search = new And();

	private And                     and_statement_collector_4_action = new And();
	
	private Extent                  width_popup_window = new Extent(600);
	private Extent                  height_popup_window = new Extent(600);
	private MyE2_String             title_of_popup = new MyE2_String("Bitte wählen ...");
	
	//dieses grid wird gefuellt, wenn das popup-suchergebnis angezeigt wird
	private E2_BasicModuleContainer container_4_popupWindow = null;

	private MyE2_Grid               grid_container_4_popupWindow = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

	private RB_ResultButtonArrays   rb_ResultButtonArray = new RB_ResultButtonArrays();
	
	/*
	 * eine variable haelt gefundene und geladene Datenfeldwerte fest und vergleicht diese beim speichern
	 * mit dem inhalt des sucheingabefeldes. Damit wird erzwungen, dass eine suche stattfindet,
	 * einfaches eintippen geht dann nicht mehr
	 */
	private String 				    c_vergleichswert_dbfeld = "";
	

	//key, um die sortierung zu speichern. ist er vorhanden, dann kann gespeichert werden
	private	ENUM_USER_SAVEKEY  		key_4_save_sorting = null;
	
	
	private boolean   				b_allow_empty_searchfield = false;
	
	
	private boolean  				firstColInResultPopupFocusAble = true;

	/*
	 * 2019-01-09: map mit weiteren komponenten, die gegegenenfalls enabled werden
	 */
	private HMAP<String, Component>		addOnComponents = new HMAP<>();

	

	/*
	 * 2019-01-10: korrektur im verhalten. sorgt fuer den aufruf der Ergebnis-Render-Methode auch  
	 *             bei leerer uebergabe der werte
	 */
	private boolean   			      renderSearchResultVisibleOnMaskInEmptyManualSettings = false;
	
	
	
	
	public RB_SearchField() throws myException {
		this(false);
	}

	

	
	public RB_SearchField(boolean bShowEraser ) throws myException {
		
		super();
		this._s(5)._bo_no();
	
		//standard-aufbau
		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_top();
		this._clear()
			._a(this.get_gridContainer_to_show_searchResult(), gl)
			._a(tf_search_input, gl)
			._a(buttonErase, gl)
			._a(buttonStartSearch, gl)
			._setSize(200,120,20,20);

		this.buttonStartSearch.add_oActionAgent(this.generate_StartSearchAction());
		
		XX_ActionAgent  agent4erasor = this.generate_EraseButtonAction();
		if (agent4erasor!=null) {
			this.buttonErase.add_oActionAgent(agent4erasor);
		}
		
		
		this.tf_search_input.setFocusTraversalParticipant(true);
		this.buttonErase.setFocusTraversalParticipant(true);
		this.buttonStartSearch.setFocusTraversalParticipant(true);
		
		this.container_4_popupWindow=this.generate_container_4_popup_window();   //abstrakte methode wegen der groessenspeicherung
		
		this.rb_VALIDATORS_4_INPUT().addElement(new ownValidator_check_input_field());
	}

	
	

	public RB_SearchField result_to_bottom() {
		RB_gld gl = new RB_gld()._ins(0,0,0,2)._left_top();
		this._clear()
			._a(tf_search_input, gl)
			._a(buttonErase, gl)
			._a(buttonStartSearch, gl)
			._a(this.get_gridContainer_to_show_searchResult(), gl._c()._span(3))
			._setSize(120,20,20);

		return this;
	}
	
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException {
		boolean enabled = Enabled && this.EXT().is_ValidEnableAlowed()&&(!this.EXT().get_bDisabledFromBasic());
		this.tf_search_input.set_bEnabled_For_Edit(enabled);
		this.buttonStartSearch.set_bEnabled_For_Edit(enabled);
		this.buttonErase.set_bEnabled_For_Edit(enabled);
		
		for (Component c: this.addOnComponents.values() ) {
			if (c instanceof MyE2IF__Component) {
				((MyE2IF__Component)c).set_bEnabled_For_Edit(Enabled);
			} else {
				c.setEnabled(Enabled);
			}
		}
	}

	
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.tf_search_input.setText("");
			this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), null);
		} else {
			String c_formated_result = ((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME());
			String c_unformated_result = ((MyRECORD)dataObject.get_RecORD()).get_UnFormatedValue(this.rb_KF().FIELDNAME());
			this.tf_search_input.setText(S.NN(c_formated_result));
			this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), c_unformated_result);
		}
		this.c_vergleichswert_dbfeld = this.tf_search_input.getText();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		if (S.isFull(valueFormated)) {
			//als standard ist von einer id auszugehen
			MyLong l = new MyLong(valueFormated);
			if (l.isOK()) {
				this.get_tf_search_input().setText(l.get_cF_LongString());
				this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), l.get_cUF_LongString());
				this.set_c_vergleichswert_dbfeld(l.get_cF_LongString());
			} else {
				this.get_tf_search_input().setText("");
				this.get_gridContainer_to_show_searchResult()._clear();
				this.set_c_vergleichswert_dbfeld("");
				if (this.renderSearchResultVisibleOnMaskInEmptyManualSettings) {
					this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), null);
				}
				
			}
		} else {
			this.get_tf_search_input().setText("");
			this.get_gridContainer_to_show_searchResult()._clear();
			this.set_c_vergleichswert_dbfeld("");
			if (this.renderSearchResultVisibleOnMaskInEmptyManualSettings) {
				this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), null);
			}
		}
		
	}


	/**
	 * abgewandelte set_manual-methode
	 * @param p_valueFormated
	 * @param execute_maskSettings_behind
	 * @param aktiv
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector rb_set_db_value_manual(String p_valueFormated,  boolean execute_maskSettings_behind, boolean aktiv) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		String valueFormated = p_valueFormated;
		this.rb_set_db_value_manual(valueFormated);
		
		if (S.isFull(valueFormated)) {
			MyLong lval = new MyLong(valueFormated);
			if (lval.isOK()) {
				valueFormated=lval.get_cUF_LongString();
			} else {
				valueFormated="";
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Suchfeld-Wert darf nur eine Ganzzahl sein ! Eingabe: "+p_valueFormated)));
			}
		}
		
		if (execute_maskSettings_behind) {
			if (this.vFillersAfterAction!=null && this.vFillersAfterAction.size()>0) {
				for (RB_MaskControllerFillFieldsAfterAction f: this.vFillersAfterAction) {
					mv.add_MESSAGE(f.fillMaskfieldsAfterAction(this, p_valueFormated, aktiv));
				}
			}
			if (mv.isOK()) {
				mv._add(this.doChangeActions());
			}
			
			mv.add_MESSAGE(do_mask_settings_after_search(valueFormated,aktiv));
		}
		
		return mv;
	}
	
	
	
	
	
	@Override
	public void mark_Neutral() throws myException {
		this.tf_search_input.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.tf_search_input.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException	{
		this.tf_search_input.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.tf_search_input.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.tf_search_input.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.tf_search_input.setAlignment(align);
	}


	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}



	public int get_iMaxResults() {
		return m_iMaxResults;
	}

	public void set_iMaxResults(int m_iMaxResults) {
		this.m_iMaxResults = m_iMaxResults;
	}

	
	
	public RB_SearchField setMaxResults(int maxResults) {
		this.m_iMaxResults = maxResults;
		return this;
	}

	public MyE2_Button get_buttonStartSearch() {
		return buttonStartSearch;
	}

	public MyE2_Button get_buttonErase() {
		return buttonErase;
	}

	public boolean is_showEraser() {
		return b_showEraser;
	}

	public E2_Grid get_gridContainer_to_show_searchResult() {
		return gridContainer_to_show_searchResult;
	}


	public RB_TextField get_tf_search_input() {
		return tf_search_input;
	}

	public Extent get_width_popup_window() {
		return width_popup_window;
	}

	public RB_SearchField set_width_popup_window(Extent p_width_popup_window) {
		this.width_popup_window = p_width_popup_window;
		return this;
	}

	public Extent get_height_popup_window() {
		return height_popup_window;
	}

	public RB_SearchField set_height_popup_window(Extent p_height_popup_window) {
		this.height_popup_window = p_height_popup_window;
		return this;
	}

	public MyE2_String get_title_of_popup() {
		return title_of_popup;
	}

	public RB_SearchField set_title_of_popup(MyE2_String p_title_of_popup) {
		this.title_of_popup = p_title_of_popup;
		return this;
	}

	public And and_statement_collector_4_basic() {
		return and_statement_collector_4_basic;
	}

	public And and_statement_collector_4_search() {
		return and_statement_collector_4_search;
	}

	public And and_statement_collector_4_action() {
		return and_statement_collector_4_action;
	}

	
	public MyE2_Grid get_grid_container_4_popupWindow() {
		return grid_container_4_popupWindow;
	}

	public E2_BasicModuleContainer get_container_4_popupWindow() {
		return this.container_4_popupWindow;
	}

	public RB_ResultButtonArrays get_rb_ResultButtonArray() {
		return rb_ResultButtonArray;
	}

	public String get_c_vergleichswert_dbfeld() {
		return c_vergleichswert_dbfeld;
	}

	public void set_c_vergleichswert_dbfeld(String p_c_vergleichswert_dbfeld) {
		this.c_vergleichswert_dbfeld = p_c_vergleichswert_dbfeld;
	}

	/**
	 *
	 * @return true, wenn die suche korrekt durchgefuehrt wurde
	 */
	public boolean is_search_done_correct() {
		if (this.Key!=null) {
			String wahr = (S.NN(this.c_vergleichswert_dbfeld,"").equals(S.NN(this.tf_search_input.getText(),"")))?"TRUE":"FALSE";
			if (wahr.equals("FALSE")) {
				DEBUG.System_println("RB_SearchField: ("+wahr+"):"+this.Key.get_REALNAME()+"/"+this.Key.get_HASHKEY()+":<feld:"+this.tf_search_input.getText()+">"+"  <<->> "+":<vergleich:"+this.c_vergleichswert_dbfeld+">", DEBUG_FLAGS.MARTINS_EIGENER.name());
			}
		}
		
		return (S.NN(this.c_vergleichswert_dbfeld,"").equals(S.NN(this.tf_search_input.getText(),"")));
	}

	
	private class ownValidator_check_input_field extends RB_Validator_Component {
		@Override
		public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RB_SearchField sf = (RB_SearchField)rb_Component;
			
			if (!sf.is_search_done_correct()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("In Suchfeldern können keine Werte direkt eingetragen werden !")));
			}
			return mv;
		}
	}



	public ENUM_USER_SAVEKEY get_key_4_save_sorting() {
		return key_4_save_sorting;
	}

	public void set_key_4_save_sorting(ENUM_USER_SAVEKEY p_key_4_save_sorting) {
		this.key_4_save_sorting = p_key_4_save_sorting;
	}
	
	
	public void make_search_result_visible_on_mask(String c_result_value_4_db) throws myException {
		this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(),c_result_value_4_db);
	}

	
	public boolean is_allow_empty_searchfield() {
		return b_allow_empty_searchfield;
	}

	
	public RB_SearchField _allowEmptySearchfield(boolean b_allow_empty_searchfield) {
		this.b_allow_empty_searchfield = b_allow_empty_searchfield;
		return this;
	}

	

	public bibSES_ENUM get_key_to_store_resultbutton_array_in_session() {
		return key_to_store_resultbutton_array_in_session;
	}

	public void set_key_to_store_resultbutton_array_in_session(bibSES_ENUM p_key_to_store_resultbutton_array_in_session) {
		this.key_to_store_resultbutton_array_in_session = p_key_to_store_resultbutton_array_in_session;
	}

	public VEK<RB_MaskControllerFillFieldsAfterAction> getFillersAfterAction() {
		return this.vFillersAfterAction;
	}

	public RB_SearchField addFillerAfterAction(RB_MaskControllerFillFieldsAfterAction fillerAfterAction) {
		this.vFillersAfterAction.add(fillerAfterAction);
		return this;
	}

	public boolean isFirstColInResultPopupFocusAble() {
		return firstColInResultPopupFocusAble;
	}

	public RB_SearchField _setFirstColInResultPopupFocusAble(boolean p_firstColInResultPopupFocusAble) {
		this.firstColInResultPopupFocusAble = p_firstColInResultPopupFocusAble;
		return this;
	}

	@Override
	public VEK<XX_ActionAgent> getChangeActions() {
		return changeActions;
	}
	

	
	public static E2_MutableStyle styleTitleButtonsLeft() {
		E2_MutableStyle s = E2_Button.baseStyle();
		s.setProperty(AbstractButton.PROPERTY_FONT, 				new E2_FontBoldItalic(),  new E2_FontBoldItalic());
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, 			new E2_ColorDark(),  new E2_ColorDark());
		s.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,  	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_ALIGNMENT,  			Alignment.ALIGN_LEFT);
		return s;
	}

	public static E2_MutableStyle styleTitleButtonsMid() {
		E2_MutableStyle s = E2_Button.baseStyle();
		s.setProperty(AbstractButton.PROPERTY_FONT, 				new E2_FontBoldItalic(),  new E2_FontBoldItalic());
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, 			new E2_ColorDark(),  new E2_ColorDark());
		s.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,  	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_ALIGNMENT,  			Alignment.ALIGN_CENTER);
		return s;
	}

	
	
	public static E2_MutableStyle styleTitleButtonsRight() {
		E2_MutableStyle s = E2_Button.baseStyle();
		s.setProperty(AbstractButton.PROPERTY_FONT, 				new E2_FontBoldItalic(),  new E2_FontBoldItalic());
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, 			new E2_ColorDark(),  new E2_ColorDark());
		s.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,  	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_ALIGNMENT,  			Alignment.ALIGN_RIGHT);
		return s;
	}

	
	public static E2_MutableStyle styleListButtonsLeft() {
		E2_MutableStyle s = E2_Button.baseStyle();
		s.setProperty(AbstractButton.PROPERTY_FONT, 				new E2_FontPlain(),  new E2_FontPlain());
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, 			new E2_ColorDark(),  new E2_ColorDark());
		s.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,  	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_ALIGNMENT,  			Alignment.ALIGN_LEFT);
		return s;
	}

	public static E2_MutableStyle styleListButtonsMid() {
		E2_MutableStyle s = E2_Button.baseStyle();
		s.setProperty(AbstractButton.PROPERTY_FONT, 				new E2_FontPlain(),  new E2_FontPlain());
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, 			new E2_ColorDark(),  new E2_ColorDark());
		s.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,  	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_ALIGNMENT,  			Alignment.ALIGN_CENTER);
		return s;
	}

	
	public static E2_MutableStyle styleListButtonsRight() {
		E2_MutableStyle s = E2_Button.baseStyle();
		s.setProperty(AbstractButton.PROPERTY_FONT, 				new E2_FontPlain(),  new E2_FontPlain());
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, 			new E2_ColorDark(),  new E2_ColorDark());
		s.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, 	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,  	new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		s.setProperty( AbstractButton.PROPERTY_ALIGNMENT,  			Alignment.ALIGN_RIGHT);
		return s;
	}

	
	
	public HMAP<String, Component> getAddOnComponents() {
		return addOnComponents;
	}

	public boolean isRenderSearchResultVisibleOnMaskInEmptyManualSettings() {
		return renderSearchResultVisibleOnMaskInEmptyManualSettings;
	}

	public RB_SearchField _setRenderSearchResultVisibleOnMaskInEmptyManualSettings(boolean p_renderSearchResultVisibleOnMaskInEmptyManualSettings) {
		this.renderSearchResultVisibleOnMaskInEmptyManualSettings = p_renderSearchResultVisibleOnMaskInEmptyManualSettings;
		return this;
	}


	
	/**
	 * baut den where-block fuer die 3 unterschiedlichen bedingungs-sammler auf
	 * @author martin
	 * @date 15.01.2019
	 *
	 * @param searchText
	 * @return s triple: sql_where_from_search, sql_where_from_base, sql_where_from_action
	 * @throws myException
	 */
	public Triple<String> getWhereStatementParts(String searchText) throws myException {
		
		//suchbedingungen fuer die suche in der artikelbez-tabelle
		Vector<String>      v_wheres_artbez = new Vector<String>();
		
		String sql_where_from_search = 	null;
		String sql_where_from_base = 	null;
		String sql_where_from_action = 	null;

		
		if (S.isFull(searchText.trim()) && this.and_statement_collector_4_search().size()>0) {
			StringSeparator 	v_separator = new StringSeparator(searchText," ");
			String 				search_block_with_placeholder = this.and_statement_collector_4_search().s();
			for (String s: v_separator) {
				v_wheres_artbez.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder, "#WERT#", s.trim())+") ");
			}
			sql_where_from_search = "("+bibALL.Concatenate(v_wheres_artbez, " AND ", "1=1")+")";   //nur der suchanteil
		} 
		
		if (this.and_statement_collector_4_basic().size()>0) {
			sql_where_from_base = "("+this.and_statement_collector_4_basic().s()+")";
		}
		if (this.and_statement_collector_4_action().size()>0) {
			sql_where_from_action = "("+this.and_statement_collector_4_action().s()+")";
		}
		return new Triple<String>(sql_where_from_search, sql_where_from_base, sql_where_from_action);
	}
	
	
	
	/**
	 * renders actual id in input-field
	 * @author martin
	 * @date 17.01.2019
	 *
	 * @return
	 */
	public RB_SearchField _renderActualMaskVal() {
		try {
			this.render_search_result_visible_on_mask(this.get_gridContainer_to_show_searchResult(), this.tf_search_input.getText());
		} catch (myException e) {
			this.get_gridContainer_to_show_searchResult()._clear();
			this.tf_search_input.setText("");
			e.printStackTrace();
		}
		
		return this;
	}

	
	/**
	 * writes id in the dbField and renders mask-representation
	 * @author martin
	 * @date 17.01.2019
	 *
	 * @param s_id
	 * @return
	 */
	public RB_SearchField _setManualAndRenderMaskVal(String s_id) {
		try {
			this.rb_set_db_value_manual(s_id);
			this._renderActualMaskVal();
		} catch (myException e) {
			this.get_gridContainer_to_show_searchResult()._clear();
			this.tf_search_input.setText("");
			e.printStackTrace();
		}
		return this;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 11.03.2019
	 *
	 * @param ActionAgent after change
	 * @return
	 */
	public RB_SearchField _aaaAfterChange(XX_ActionAgent agent) {
		this.changeActions._a(agent);
		return this;
	}
	
	public RB_SearchField _aaaAfterChange(IF_agentSimple agent) {
		this.changeActions._a(agent.genActionAgent());
		return this;
	}
	
}
