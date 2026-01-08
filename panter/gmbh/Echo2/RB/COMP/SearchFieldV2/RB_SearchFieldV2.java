package panter.gmbh.Echo2.RB.COMP.SearchFieldV2;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_Executer;
import panter.gmbh.BasicInterfaces.IF_Executer3Parts;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.IF_RB_SetAndValidBinder;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SIMPLE;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;


/**
 * suchfeld ohne datenrueckgabe beim speichern
 * @author martin
 *
 */
public abstract class RB_SearchFieldV2 extends E2_Grid implements MyE2IF__Component, IF_RB_Component, IF_RB_Component_Savable, IF_RB_SetAndValidBinder {

	
	private E2_BasicModuleContainer   			containerToShowResultButtons = null;

	/**
	 * aufbau der ergebnis-componente fuer die darstellung eine lesbaren suchergebnisses (einfach: ein label oder komplexer)
	 * @param unformatedIdFound
	 * @return
	 * @throws myException
	 */
	public abstract void  						renderResultOnMask(E2_Grid gridForResults, Long id) throws myException;
	
	//hier wird aus dem suchergebnis das resultButtonsArray
	public abstract ResultButtons				createResultButtons(RecList21 rlSearchResult, RB_SearchFieldV2 searchField);
	
	
	/**
	 * am besten mit innere ableitung eines E2_BasicModuleContainer wegen des speicherns der fensterzustaende
	 * @return
	 * @throws myException
	 */
	public abstract E2_BasicModuleContainer    			generatePopupContainer() throws myException;
	
	public abstract SEL 								generateSELWithoutWhere() throws myException;
	
	
	private 		E2_Grid  					gridForResults = new E2_Grid();

	private RB_KF Key = null;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();


	private RB_TextField    		tf_search_input = new RB_TextField()._w(100);

	private E2_Button				buttonStartSearch	= 	new E2_Button().__setImages(E2_ResourceIcon.get_RI("suche.png"),E2_ResourceIcon.get_RI("suche__.png"));


	private E2_Button				buttonErase	= 			new E2_Button().__setImages(E2_ResourceIcon.get_RI("eraser.png"),E2_ResourceIcon.get_RI("eraser__.png"));

	

	private boolean   				showEraser = false;
	
	private int 					maxResults = 0;
	
	private And                     andStatementCollectorForBasic = 		new And();   // basis-bedingungen, z.b. aktiv=true
	private And                     andStatementCollectorForSearchInputs = 	new And();   // bedingungen aufrund der sucheingabe
	private And                     andStatementCollectorScreenSetters = 	new And();   // bedingungen, die aufgrund einer momentanen situation  n
	private And                     andStatementCollectorOneTimeCondition = new And();   // bedingungen, werden nach einmaliger suche wieder gelöscht (z.B. basierend auf momentaner maskensituation 
	private And                     andStatementCollectorHandlerStartInsteadOfSearch = new And();   // bedingungen, die von einem vorherigen startmodul kommen, werden nach einmaliger suche geloescht


	private Extent                  widthPopupWindow = new Extent(600);
	private Extent                  heightPopupWindow = new Extent(600);
	private MyE2_String             popupTitle = new MyE2_String("Bitte wählen ...");
	
	
	//dieses grid wird gefuellt, wenn das popup-suchergebnis angezeigt wird
	private ResultButtons   		resultButtons = null;
	private VEK<String>					sortButtonTextsHeaders = new VEK<>();
	private VEK<E2_MutableStyle>		sortButtonStyles = new VEK<>();
	private VEK<SortButtonForResults>	sortButtonsForResults = new VEK<>();

	private E2_Grid   				gridForResultPopups = new E2_Grid();
	
	/*
	 * eine variable haelt gefundene und geladene Datenfeldwerte fest und vergleicht diese beim speichern
	 * mit dem inhalt des sucheingabefeldes. Damit wird erzwungen, dass eine suche stattfindet,
	 * einfaches eintippen geht dann nicht mehr
	 */
	private String 				    c_vergleichswert_dbfeld = "";
	private boolean   				allowEmptySearchfield = false;



	private boolean  				firstColInResultPopupFocusAble = true;

	


	/*
	 * 2019-01-09: map mit weiteren komponenten, die gegegenenfalls enabled werden
	 */
	private HMAP<String, Component>		addOnComponents = new HMAP<>();

	//ein renderer um die popups mit den resultbuttons zu fuellen
	private  String   									saveSortSettingsKey = null;
	
	private _TAB										table = null;

	//hier kann eine aktion untergebracht werden, wenn eine leere suche geklickt wird
	private IfHandlerStartInsteadOfSearch   				handlerStartInsteadOfSearch = null;

	
	private VEK<XX_ActionAgent>                         actionAgentsForMapSetAndValidators = new VEK<>();

	private VEK<IF_Executer>  							beforeStartSearchEvents = new VEK<>();
	private VEK<IF_Executer>  							afterValueChangeEvents = new VEK<>();
	private VEK<IF_Executer>  							afterFieldEraseEvents = new VEK<>();

	
	//2020-02-20: afterMaskFillListener
	private VEK<IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>>>  beforeSearchResultGridRendererListeners = new VEK<>();

	private VEK<IF_ExecuterOnComponentV2<ResultButton>>                                beforeResultButtonToGridListeners = new VEK<>();

	
	
	public RB_SearchFieldV2() throws myException {
		
		super();
		this._s(5)._bo_no();
	

		this.putActionToEraseButton(buttonErase);
		this.putActionToSearchButton(buttonStartSearch);
		
		this.tf_search_input.setFocusTraversalParticipant(true);
		this.buttonErase.setFocusTraversalParticipant(true);
		this.buttonStartSearch.setFocusTraversalParticipant(true);
		
		this._setShapeResultLeft();
		
		this.rb_VALIDATORS_4_INPUT().addElement(new ownValidator_check_input_field());
	}

	
	
	public RB_SearchFieldV2 _setSearchIconNormalSearch() {
		buttonStartSearch.__setImages(bibE2.getIcon("suche.png"), bibE2.getIcon("suche__.png"));
		return this;
	}

	public RB_SearchFieldV2 _setSearchIconAutomatikSearch() {
		buttonStartSearch.__setImages(bibE2.getIcon("suche_automatik.png"), bibE2.getIcon("suche_automatik__.png"));
		return this;
	}

	

	public RB_SearchFieldV2 _setShapeResultLeft() {
		//standard-aufbau
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		this._clear()
			._a(this.getGridForResults(), gl)
			._a(tf_search_input, gl)
			._a(buttonErase, gl._c()._ins(0, 2, 2, 0))
			._a(buttonStartSearch, gl._c()._ins(0, 2, 2, 0))
			._setSize(200,100,20,20);

		return this;
	}
	
	

	public RB_SearchFieldV2 _setShapeResultToBottom() {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		this._clear()
			._a(tf_search_input, gl)
			._a(buttonErase, gl._c()._ins(0, 2, 2, 0))
			._a(buttonStartSearch, gl._c()._ins(0, 2, 2, 0))
			._a("", gl)
			._a(this.getGridForResults(), gl._c()._span(4))
			._setSize(100,20,20,160);

		return this;
	}
	

	public RB_SearchFieldV2 _setShapeResultToRight() {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		this._clear()
			._a(tf_search_input, gl)
			._a(buttonErase, gl._c()._ins(0, 2, 2, 0))
			._a(buttonStartSearch, gl._c()._ins(0, 2, 2, 0))
			._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
			._setSize(100,20,20,300);

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
		if (dataObject instanceof RB_Dataobject_V2 || dataObject instanceof RB_Dataobject_V21 || dataObject instanceof RB_Dataobject_V22) { 
			Rec20 rec = null;
			if (dataObject instanceof RB_Dataobject_V2 ) {
				rec = ((RB_Dataobject_V2)dataObject).get_rec20();
			} else if (dataObject instanceof RB_Dataobject_V21 ){
				rec = (RB_Dataobject_V21)dataObject;
			} else {
				rec = (RB_Dataobject_V22)dataObject;
			}
			
			if (rec==null || rec.is_newRecordSet()) {
				this.tf_search_input.setText("");
				this.renderResultOnMask(this.getGridForResults(), null);
			} else {
				this.tf_search_input.setText(S.NN(rec.getFs(rec.find_field(this.rb_KF().FIELDNAME()))));
				this.renderResultOnMask(this.getGridForResults(), rec.getLongDbValue(rec.find_field(this.rb_KF().FIELDNAME())));
			}
			this.c_vergleichswert_dbfeld = this.tf_search_input.getText();
		} else {
			throw new myException("Only Records of type RB_Dataobject_V2/RB_Dataobject_V21 are allowed !<eb4b14a2-dac4-11e9-8a34-2a2ae2dbcce4>");
		}
	}


	@Override
	public void rb_set_db_value_manual(String idUnformated) throws myException {
		if (S.isFull(idUnformated)) {
			//als standard ist von einer id auszugehen
			MyLong l = new MyLong(idUnformated);
			if (l.isOK()) {
				this.getTextFieldSearchInput().setText(l.get_cF_LongString());
				this.renderResultOnMask(this.getGridForResults(), l.get_oLong());
				this.set_c_vergleichswert_dbfeld(l.get_cF_LongString());
			} else {
				this.getTextFieldSearchInput().setText("");
				this.set_c_vergleichswert_dbfeld("");
				this.renderResultOnMask(this.getGridForResults(), null);
			}
		} else {
			this.getTextFieldSearchInput().setText("");
			this.getGridForResults()._clear();
			this.set_c_vergleichswert_dbfeld("");
			this.renderResultOnMask(this.getGridForResults(), null);
		}
		this.c_vergleichswert_dbfeld = this.tf_search_input.getText();
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
		return maxResults;
	}

	public void set_iMaxResults(int m_iMaxResults) {
		this.maxResults = m_iMaxResults;
	}

	
	
	public RB_SearchFieldV2 _setMaxResults(int maxResults) {
		this.maxResults = maxResults;
		return this;
	}



	public boolean isShowEraser() {
		return showEraser;
	}

	public RB_TextField getTextFieldSearchInput() {
		return tf_search_input;
	}

	public Extent get_width_popup_window() {
		return widthPopupWindow;
	}

	public RB_SearchFieldV2 set_width_popup_window(Extent p_width_popup_window) {
		this.widthPopupWindow = p_width_popup_window;
		return this;
	}

	public Extent get_height_popup_window() {
		return heightPopupWindow;
	}

	public RB_SearchFieldV2 set_height_popup_window(Extent p_height_popup_window) {
		this.heightPopupWindow = p_height_popup_window;
		return this;
	}

	public MyE2_String get_title_of_popup() {
		return popupTitle;
	}

	public RB_SearchFieldV2 set_title_of_popup(MyE2_String p_title_of_popup) {
		this.popupTitle = p_title_of_popup;
		return this;
	}

	public And getAndStatementCollectorForBasic() {
		return andStatementCollectorForBasic;
	}

	public And getAndStatementCollectorForSearchInputs() {
		return andStatementCollectorForSearchInputs;
	}

	public And getAndStatementCollectorScreenSetters() {
		return andStatementCollectorScreenSetters;
	}

	public And getAndStatementCollectorOneTimeCondition() {
		return andStatementCollectorOneTimeCondition;
	}
	
	
	public And getAndStatementCollectorHandlerStartInsteadOfSearch() {
		return andStatementCollectorHandlerStartInsteadOfSearch;
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
			
			RB_SearchFieldV2 sf = (RB_SearchFieldV2)rb_Component;
			
			if (!sf.is_search_done_correct()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("In Suchfeldern können keine Werte direkt eingetragen werden !")));
			}
			return mv;
		}
	}

	
	public boolean is_allow_empty_searchfield() {
		return allowEmptySearchfield;
	}

	


	public boolean isFirstColInResultPopupFocusAble() {
		return firstColInResultPopupFocusAble;
	}

	public RB_SearchFieldV2 _setFirstColInResultPopupFocusAble(boolean p_firstColInResultPopupFocusAble) {
		this.firstColInResultPopupFocusAble = p_firstColInResultPopupFocusAble;
		return this;
	}

	
	public HMAP<String, Component> getAddOnComponents() {
		return addOnComponents;
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
	protected VEK<String> getWhereStatementParts(String searchText) throws myException {
		
		//suchbedingungen fuer die suche in der artikelbez-tabelle
		Vector<String>      v_wheres = new Vector<String>();
		
		String sqlStatementCollectorForSearchInputs = null;
		String sqlStatementCollectorForBasic = null;
		String sqlStatementCollectorScreenSetters = null;
		String sqlStatementCollectorFormEmptySearchHandler =null;
		String sqlStatementCollectorHandlerStartInsteadOfSearch=null;


		
		if (S.isFull(searchText.trim()) && this.getAndStatementCollectorForSearchInputs().size()>0) {
			StringSeparator 	v_separator = new StringSeparator(searchText," ");
			String 				search_block_with_placeholder = this.getAndStatementCollectorForSearchInputs().s();
			for (String s: v_separator) {
				v_wheres.add(" ("+bibALL.ReplaceTeilString(search_block_with_placeholder, "#WERT#", s.trim())+") ");
			}
			sqlStatementCollectorForSearchInputs = "("+bibALL.Concatenate(v_wheres, " AND ", "1=1")+")";   //nur der suchanteil
		} 
		
		if (this.getAndStatementCollectorForBasic().size()>0) {
			sqlStatementCollectorForBasic = "("+this.getAndStatementCollectorForBasic().s()+")";
		}
		if (this.getAndStatementCollectorScreenSetters().size()>0) {
			sqlStatementCollectorScreenSetters = "("+this.getAndStatementCollectorScreenSetters().s()+")";
		}
		
		if (this.getAndStatementCollectorOneTimeCondition().size()>0) {
			sqlStatementCollectorFormEmptySearchHandler = "("+this.getAndStatementCollectorOneTimeCondition().s()+")";
		}
		
		if (this.getAndStatementCollectorHandlerStartInsteadOfSearch().size()>0) {
			sqlStatementCollectorHandlerStartInsteadOfSearch = "("+this.getAndStatementCollectorHandlerStartInsteadOfSearch().s()+")";
		}
		
		
		return new VEK<String>()
				._addValidated((c)->{return S.isFull(c);},sqlStatementCollectorForSearchInputs)
				._addValidated((c)->{return S.isFull(c);},sqlStatementCollectorForBasic)
				._addValidated((c)->{return S.isFull(c);},sqlStatementCollectorScreenSetters)
				._addValidated((c)->{return S.isFull(c);},sqlStatementCollectorFormEmptySearchHandler)
				._addValidated((c)->{return S.isFull(c);},sqlStatementCollectorHandlerStartInsteadOfSearch)
				;
	}
	
	


	public E2_Grid getGridForResults() {
		return gridForResults;
	}

	
	public String getSaveSortSettingsKey() {
		return saveSortSettingsKey;
	}
	
	public RB_SearchFieldV2 _setSaveSortSettingsKey(String p_saveSortSettingsKey) {
		this.saveSortSettingsKey = p_saveSortSettingsKey;
		return this;
	}
	
	

	/**
	 * kann ueberschrieben werden
	 * @author martin
	 * @date 19.09.2019
	 *
	 * @param eraseButton
	 */
	public void putActionToEraseButton(E2_Button eraseButton) {
		eraseButton._aaa(()-> {
			rb_set_db_value_manual("");
		});
		
		eraseButton._aaa(()->{
			for (IF_Executer ex: afterFieldEraseEvents) {
				ex.execute();
			}
		});
	}

	
	public void putActionToSearchButton(E2_Button searchButton) {
		
		searchButton._aaa(()->{
			for (IF_Executer ex: beforeStartSearchEvents) {
				ex.execute();
			}
		});
		
		
		searchButton._aaa(()-> {
			
			boolean integrityCheck = true;
			if (S.isEmpty(saveSortSettingsKey)) {
				bibMSG.MV()._addAlarm("System-Check Searchfield: Define Error: execute: _setSaveSortSettingsKey(String p_saveSortSettingsKey)");
				integrityCheck=false;
			}

			if (table==null) {
				bibMSG.MV()._addAlarm("System-Check Searchfield: Define Error emtpy table: execute:  _setTable(_TAB table) ");
				integrityCheck=false;
			}
			
			
			if (integrityCheck) {

				if (this.handlerStartInsteadOfSearch!=null && this.andStatementCollectorHandlerStartInsteadOfSearch.size()==0 && this.handlerStartInsteadOfSearch.checkIfNeeded(this)) {
					handlerStartInsteadOfSearch.executeInsteadOfSearch(this, this.andStatementCollectorHandlerStartInsteadOfSearch);
				} else {

					String searchText = S.NN(tf_search_input.getText());
				
					if (  !(  S.isFull(searchText) || allowEmptySearchfield || andStatementCollectorHandlerStartInsteadOfSearch.size()>0)) {
						bibMSG.MV()._addAlarm(S.ms("Bitte geben Sie einen Suchttext ein"));
					} else {
						/*
						 * wenn der text ohne punkt eine integer ist, dann den punkt rauswerfen, damit auch formatierte IDs gefunden werden
						 */
						String sIntTest = bibALL.ReplaceTeilString(searchText,".","");
						if (bibALL.isInteger(sIntTest)) {
							searchText=sIntTest;
						}
								
						try	{
							
							resultButtons = new ResultButtons(getSaveSortSettingsKey());
							
							SEL sel = generateSELWithoutWhere();
							VEK<String> wheres = getWhereStatementParts(searchText);  //3 teile: 1. suchtext, 2. basis, 3. situationsbedingt
							if (wheres.size()>0) {
								sel.WHERE(new TermSimple(wheres.get(0)));
								for (int i=1; i<wheres.size();i++ ) {
									sel.AND(new TermSimple(wheres.get(i)));
								}
							}
							
							RecList21 rl = new RecList21(table)._fill(new SqlStringExtended(sel.s()));
							
							DEBUG._print("Search-query \n"+sel.s()+"\n key:e4fd315c-dbaf-11e9-8a34-2a2ae2dbcce4");
							
							if (rl.size()==0) {
								bibMSG.MV()._addAlarm(S.ms("Nichts gefunden ..."));
							} else if (rl.size()==1) {
								resultButtons = createResultButtons(rl, RB_SearchFieldV2.this);
	
								for (ResultButton[] arr: resultButtons) {
									for (ResultButton b: arr) {
										putActionAgentToResultButton(b, null);    	// eigentliche results-button-aktion
									}
								}
		
								resultButtons.get(0)[0].doActionPassiv();
								bibMSG.MV()._addInfo("Genau ein Datensatz gefunden und geladen !");
							} else {
								
								resultButtons = createResultButtons(rl, RB_SearchFieldV2.this);
								createSortButtons(resultButtons);
								initSortStatus(gridForResultPopups,resultButtons,sortButtonsForResults);
								renderGrid4ResultPopup(gridForResultPopups,resultButtons,sortButtonsForResults);
								formatGrid4ResultPopup(gridForResultPopups,resultButtons,sortButtonsForResults);
								containerToShowResultButtons = generatePopupContainer();
								
								for (ResultButton[] arr: resultButtons) {
									for (ResultButton b: arr) {
										putActionAgentToResultButton(b, containerToShowResultButtons);    	// eigentliche results-button-aktion
									}
								}
								containerToShowResultButtons.add(gridForResultPopups, E2_INSETS.I(5));
								containerToShowResultButtons.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Bitte wählen Sie ..."));
							}
						
						} catch (myException ex) {
							throw ex;
						} catch (Exception ex) {
							ex.printStackTrace();
							throw new myException(ex.getLocalizedMessage());
						}
	
					}

					
				}
				
				//dann den andStatementCollectorFormEmptySearchHandler wieder leer machen fuer den naechsten durchlauf
				andStatementCollectorOneTimeCondition.clear();
				
				//und die bedinung fuer den searchhandler ebenfalls
				andStatementCollectorHandlerStartInsteadOfSearch.clear();
			}
			
		});
	}

	
	
	/**
	 * aufbau der titelbuttons fuer die sortierung
	 */
	public void createSortButtons(ResultButtons buttons) {
		int breite = buttons.get(0).length;

		getSortButtonsForResults()._clear();
		
		//jetzt den sortbutton-vector bauen (falls er noch leer ist
		for (int i=0;i<breite;i++) {
			String text = "Spalte "+i;
			if (getSortButtonTextsHeaders().size()>i) {
				text=getSortButtonTextsHeaders().get(i);
			}
			E2_MutableStyle style = E2_Button.baseStyleBold();
			
			if (getSortButtonStyles().size()>i) {
				style = O.NN(getSortButtonStyles().get(i), E2_Button.baseStyleBold());
			}
			
			getSortButtonsForResults()._a(new SortButtonForResults(RB_SearchFieldV2.this, i, S.msUt(text), style));
		}
	}
	
	
	
	public void initSortStatus(E2_Grid grid, ResultButtons resultButtons, VEK<SortButtonForResults> sortButtons) {
		int breite = resultButtons.get(0).length;

		//die alte sortierung einlesen und aktivieren
		resultButtons._readStoredSortStatus();
		
		//die sort-anzeige beim richtigen sortbutton anzeigen
		if (resultButtons.getActualSortCol()!=null && resultButtons.getActualSortStatus()!=null && resultButtons.getActualSortCol().intValue()<breite && sortButtons.size()==breite) {
			sortButtons.get(resultButtons.getActualSortCol()).set_sortstatus_actual(resultButtons.getActualSortStatus());
			resultButtons.sort();
		}

	}
	
	
	public void renderGrid4ResultPopup() {
		renderGrid4ResultPopup(this.gridForResultPopups,this.resultButtons,this.sortButtonsForResults);
	}
	
	
	public void renderGrid4ResultPopup(E2_Grid grid, ResultButtons buttons, VEK<SortButtonForResults> sortButtons) {
		
		int breite = buttons.get(0).length;
		grid._clear()._s(breite);

		//2020-03-06: moeglichkeit, eine komponente oberhalb der ergebnisse anzuzeigen
		for (IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> executer: this.beforeSearchResultGridRendererListeners) {
			executer.execute(grid, buttons, sortButtons);
		}
		
		
		if (sortButtons.size()==breite) {
			for (int i=0;i<breite;i++) {
				grid._a(sortButtons.get(i));
			}
		}
		for (ResultButton[] arr: buttons) {
			for (ResultButton b: arr) {
				for (IF_ExecuterOnComponentV2<ResultButton> ex: this.beforeResultButtonToGridListeners) {
					ex.execute(b);
				}
				
				
				grid._a(b, new RB_gld()._ins(2, 1, 2, 1));
			}
		}
	}


	
	public void formatGrid4ResultPopup(E2_Grid grid, ResultButtons buttons, VEK<SortButtonForResults> sortButtons) {
	}
	
	
	
	public void putActionAgentToResultButton(ResultButton button, E2_BasicModuleContainer resultsPopup) {
		button._aaa(()-> {
			if (resultsPopup!=null) {
				resultsPopup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
			rb_set_db_value_manual(""+button.getRec21().getId());
		});
		//jetzt evtl. mapsetting-actions uebergeben
		button._aaaV(this.getActionAgentsForMapSetAndValidators());
		
		button._aaa(()->{
			for (IF_Executer ex: afterValueChangeEvents) {
				ex.execute();
			}
		});

	}



	public _TAB getTable() {
		return table;
	}


	public RB_SearchFieldV2 _setTable(_TAB table) {
		this.table = table;
		return this;
	}

	
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		if (this.is_search_done_correct()) {
			return this.getTextFieldSearchInput().getText();
		} else {
			return "";
		}
	}




	public E2_BasicModuleContainer getContainerToShowResultButtons() {
		return containerToShowResultButtons;
	}

	public IfHandlerStartInsteadOfSearch getHandlerStartInsteadOfSearch() {
		return handlerStartInsteadOfSearch;
	}

	public RB_SearchFieldV2 _setHandlerStartInsteadOfSearch(IfHandlerStartInsteadOfSearch handlerForEmptySearch) {
		this.handlerStartInsteadOfSearch = handlerForEmptySearch;
		return this;
	}

	
	@Override
	public VEK<XX_ActionAgent> getActionAgentsForMapSetAndValidators()  {
		return actionAgentsForMapSetAndValidators;
	}

	public RB_SearchFieldV2 _registerBeforeStartSearchEvent(IF_Executer executer) {
		beforeStartSearchEvents._a(executer);
		return this;
	}

	public RB_SearchFieldV2 _registerAfterValueChangeEvents(IF_Executer executer) {
		afterValueChangeEvents._a(executer);
		return this;
	}

	public RB_SearchFieldV2 _registerAfterFieldEraseEvents(IF_Executer executer) {
		afterFieldEraseEvents._a(executer);
		return this;
	}
	
	
	/**
	 * speichern des aktuelle sortierungsstatus des suchfelds
	 * @author martin
	 * @date 24.09.2019
	 *
	 * @return
	 */
	public RB_SearchFieldV2 _saveActualStatus() {
		try {
			E2_UserSetting_SIMPLE setting = new E2_UserSetting_SIMPLE(saveSortSettingsKey);
			String toSave = "0@UP";
			for (int i=0;i< this.getSortButtonsForResults().size();i++) {
				SortButtonForResults sortButton = this.getSortButtonsForResults().get(i);
				if (sortButton.getSortstatus_actual()!=EnSortStatusButtonGrid.NEUTRAL) {
					toSave = ""+sortButton.getColumnNrSort()+"@"+sortButton.getSortstatus_actual().name();	
				}
			}
			setting.STORE(saveSortSettingsKey, toSave);
			
		} catch (myException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
	public ResultButtons getResultButtons() {
		return resultButtons;
	}

	
	public VEK<SortButtonForResults> getSortButtonsForResults() {
		return sortButtonsForResults;
	}


	
	
	public E2_Grid getGridForResultPopups() {
		return gridForResultPopups;
	}

	public VEK<String> getSortButtonTextsHeaders() {
		return sortButtonTextsHeaders;
	}
	
	public boolean isAllowEmptySearchfield() {
		return allowEmptySearchfield;
	}

	public RB_SearchFieldV2 _setAllowEmptySearchfield(boolean allowEmptySearchfield) {
		this.allowEmptySearchfield = allowEmptySearchfield;
		return this;
	}
	
//	public RB_SearchFieldV2 _allowEmptySearchfield(boolean b_allow_empty_searchfield) {
//		this.allowEmptySearchfield = b_allow_empty_searchfield;
//		return this;
//	}

	
	public E2_Button getButtonStartSearch() {
		return buttonStartSearch;
	}

	public E2_Button getButtonErase() {
		return buttonErase;
	}

	public VEK<E2_MutableStyle> getSortButtonStyles() {
		return sortButtonStyles;
	}

	public boolean isSearchFieldEmpty() {
		return S.isEmpty(this.tf_search_input.getText());
	}

	public RB_SearchFieldV2 _addListenerbeforeSearchResultGridRenderer(IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> executer) {
		this.beforeSearchResultGridRendererListeners._a(executer);
		return this;
	}
	
	public RB_SearchFieldV2 _clearListenersbeforeSearchResultGridRenderer()  {
		this.beforeSearchResultGridRendererListeners._clear();
		return this;
	}
	
	public VEK<IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>>> getListenersbeforeSearchResultGridRenderer() {
		return this.beforeSearchResultGridRendererListeners._clear();
	}

	public RB_SearchFieldV2 _addListenerBeforeResultButtonToGrid(IF_ExecuterOnComponentV2<ResultButton> executer) {
		this.beforeResultButtonToGridListeners._a(executer);
		return this;
	}
	
	public RB_SearchFieldV2 _clearListenerBeforeResultButtonToGrid()  {
		this.beforeResultButtonToGridListeners._clear();
		return this;
	}
	
	public VEK<IF_ExecuterOnComponentV2<ResultButton>> getListenerBeforeResultButtonToGrid() {
		return this.beforeResultButtonToGridListeners._clear();
	}


	
	
}
