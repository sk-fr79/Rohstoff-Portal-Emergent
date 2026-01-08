package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_EXT;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;

public abstract class SF__Filter extends XX_ListSelektor_EXT{

	//haelt die tables fuer die selektion
	private Vector<_TAB_ext> 		v_tables = 	new Vector<_TAB_ext>();
	private Vector<IF_Field_ext> 	v_fields = new Vector<IF_Field_ext>();
	private Vector<SF_andBlock> 	v_ands_4_popup = new Vector<SF_andBlock>();
	private Vector<SF_andBlock>	 	v_ands_4_select = new Vector<SF_andBlock>();

	//buttons fuer den popup-container
	private SF_bt_save_and_start_selection  bt_closePopupAndStartSelection = null;
	private MyE2_Button  bt_closePopupAndCancel = new MyE2_Button(new MyE2_String("Abbruch"),new MyE2_String("Fenster schließen, alles belassen"),null);
	private MyE2_Button  bt_clearPopupSelection = new MyE2_Button(new MyE2_String("Leeren"),new MyE2_String("Auswahl leeren"),null);
	private MyE2_Button  bt_add_andBlock = new MyE2_Button(new MyE2_String("Weiteren Bedingungsblock anhängen"),new MyE2_String("Weiteren, ausschließenden Bedingungsblock hinzufügen"),null);
	
	
	//components fuer anzeige im selektor
	//private ownButtonToShowCompleteSelection   labelShowActualSelection = new ownButtonToShowCompleteSelection("");
	private MyE2_Button  bt_clear_anzeige_in_base = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),
																	new MyE2_String("Filterbedingungen leeren"),null);
	private MyE2_Button  bt_start_filterselectdefinition = new MyE2_Button(new MyE2_String("Filter definieren"));
	
	private E2_BasicModuleContainer container4select_popup = null;
	
	private MyE2_Grid  				gridContainer4ands = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

	
	private E2_Grid4MaskSimple   	grid4selektor = new E2_Grid4MaskSimple();
	
	//hier wird der actionagent des selektors gespeichert
	private XX_ActionAgent 			selectAction = null;
	
	public SF__Filter() throws myException {
		super();
		this.bt_closePopupAndStartSelection = new SF_bt_save_and_start_selection(this, new MyE2_String("Aktivieren, schliessen"));
		
		this.bt_start_filterselectdefinition.add_oActionAgent(new ownActionStartFilterPopUp());
		this.v_ands_4_select.add(new SF_andBlock(this));
		
		this.bt_add_andBlock.add_oActionAgent(new ownActionAdd_And_Block());
		
		this.bt_closePopupAndStartSelection.add_oActionAgent(new ownActionAgentcloseWindow(false));
		//die popup-bedingungen in die kompoente-bedingungen uebernehmen
		this.bt_closePopupAndStartSelection.add_oActionAgent(new ownActionTransferBedingungen());
		this.bt_closePopupAndStartSelection.add_oActionAgent(new ownActionRefreshMaskComponent());
		

		//die maske immer auffrischen
		this.bt_clear_anzeige_in_base.add_oActionAgent(new ownActionCleanSelectionInBase());
		this.bt_clear_anzeige_in_base.add_oActionAgent(new ownActionCleanSelectionInPopup());
		this.bt_clear_anzeige_in_base.add_oActionAgent(new ownActionRefreshMaskComponent());

		this.bt_closePopupAndCancel.add_oActionAgent(new ownActionAgentcloseWindow(true));
		this.bt_closePopupAndCancel.add_oActionAgent(new ownActionRefreshMaskComponent());

		this.bt_clearPopupSelection.add_oActionAgent(new ownActionCleanSelectionInPopup());
		this.bt_clearPopupSelection.add_oActionAgent(new ownActionRefreshMaskComponent());
		
	}
	
	
	
	
	
	
	public abstract void fill_components_4_selector(	E2_Grid4MaskSimple   	grid4selektor,
														MyE2_Button 			showActualSelection, 
														MyE2_Button  			bt_clear_anzeige,
														MyE2_Button 			bt_start_filterselectdefinition) throws myException;
	
	
	public Component 	get_oComponentForSelection()  throws myException {
		this.fill_components_4_selector(	this.grid4selektor, 
											new ownButtonToShowCompleteSelection(this.get_WhereBlock()),
											this.bt_clear_anzeige_in_base,
											this.bt_start_filterselectdefinition);
		return this.grid4selektor;
	}
	public Component 	get_oComponentWithoutText()  throws myException {
		return this.get_oComponentForSelection();
	}

	
	public SF__Filter add_table(_TAB tab) throws myException {
		return this.add_table(new _TAB_ext(tab));
	}
	
	
	public SF__Filter add_table(_TAB_ext tab) throws myException {
		this.v_tables.addElement(tab);
		this.v_fields.removeAllElements();
		
		for (_TAB_ext t: this.v_tables) {
			for (IF_Field_ext f: t.get_fields()) {
				this.v_fields.add(f);
			}
		}
		
		return this;
	}
	
	
	
	
	public void    	add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		this.bt_closePopupAndStartSelection.add_oActionAgent(oAgent);
		this.bt_clear_anzeige_in_base.add_oActionAgent(oAgent);
		this.selectAction = oAgent;
	}

	
	
	private E2_BasicModuleContainer  generate_container() {
		this.container4select_popup = new E2_BasicModuleContainer();
		
		String c_key = "";
		for (_TAB_ext t: this.v_tables) {
			c_key+=t.getAddonKey();
		}
		this.container4select_popup.set_cADDON_TO_CLASSNAME(c_key);   //speicherschluessel wird durch die Tabellen definiert
		
		return this.container4select_popup;
	}


	public Vector<_TAB_ext> get_tables() {
		return this.v_tables;
	}


	public Vector<IF_Field_ext> get_fields() {
		return this.v_fields;
	}


	public Vector<SF_andBlock> get_ands_4_popup() {
		return this.v_ands_4_popup;
	}

	public Vector<SF_andBlock> get_ands_4_select() {
		return this.v_ands_4_select;
	}

	
	
	public SF__Filter create_popup_4_select() throws myException {
		E2_BasicModuleContainer container = this.generate_container();
		
		SF_Filter_CONST.transfer_one_into_two(this.v_ands_4_select, this.v_ands_4_popup);
		
		this.fill_gridContainer4Popup();
		container.add(gridContainer4ands, E2_INSETS.I(2,2,2,2));
		container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(900), new Extent(500), new MyE2_String("Filter-Selektion"));
		return this;
	}
	
	

	
	public void fill_gridContainer4Popup() throws myException {
		this.gridContainer4ands.removeAll();
		
		//fuer jeden and-block ein grid in das popup-fenster packen
		for (SF_andBlock andblock: this.v_ands_4_popup) {
			andblock.fill_grid_with_components();
			gridContainer4ands.add(andblock.get_grid_with_and_block());
			if (andblock!=this.v_ands_4_popup.get(this.v_ands_4_popup.size()-1)) {
				this.gridContainer4ands.add(new MyE2_Label(new MyE2_String(" ..... UND ......"),new E2_FontBoldItalic()), E2_INSETS.I(0,5,0,5));
			}
		}

		E2_Grid4MaskSimple grid_help = new E2_Grid4MaskSimple()
													.add_(this.bt_closePopupAndStartSelection)
													.add_(this.bt_closePopupAndCancel);
		gridContainer4ands.add(
					new E2_Grid4MaskSimple().def_(E2_INSETS.I(3,1,3,1))
											.add_(new MyE2_Label(""))
											.add_(grid_help)
											.add_(this.bt_clearPopupSelection)
											.add_(this.bt_add_andBlock)
											.add_(new MyE2_Label(""))
											.add_(new MyE2_Label(""))
											.setSize_(SF_Filter_CONST.get_i_breiten())
											, E2_INSETS.I(0,20,0,5));

	}
	

	
	@Override
	public String get_WhereBlock() {
		String and_const = " AND ";
		String c_rueck = "";
		for (SF_andBlock andblock: this.v_ands_4_select) {
			String and_block =  andblock.get_sql_where_block();
			if (S.isFull(and_block.trim())) {
				c_rueck = c_rueck +" "+and_block+and_const;
			}
		}
		
		if (c_rueck.endsWith(and_const)) {
			c_rueck="("+c_rueck.substring(0, c_rueck.length()-and_const.length())+")";
		}
		
//		DEBUG.System_println("Where: "+c_rueck);

		
		return c_rueck;
	}

	
	public class ownActionStartFilterPopUp extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter.this.create_popup_4_select();
		}
	}

	
	private class ownActionAgentcloseWindow extends XX_ActionAgent  {
		private boolean b_ok = true;
		
		public ownActionAgentcloseWindow(boolean ok) {
			super();
			this.b_ok = ok;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter.this.container4select_popup.CLOSE_AND_DESTROY_POPUPWINDOW(this.b_ok);	
		}
	}

	
	private class ownActionCleanSelectionInBase extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter oThis = SF__Filter.this;
			oThis.v_ands_4_select.removeAllElements();
			oThis.v_ands_4_select.add(new SF_andBlock(oThis));
			oThis.fill_gridContainer4Popup();
		}
	}

	private class ownActionCleanSelectionInPopup extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter oThis = SF__Filter.this;
			oThis.v_ands_4_popup.removeAllElements();
			oThis.v_ands_4_popup.add(new SF_andBlock(oThis));
			oThis.fill_gridContainer4Popup();
		}
	}

	
	
	/**
	 * alles leermachen
	 * @return
	 * @throws myException
	 */
	public SF__Filter clearFilter() throws myException {
		this.v_ands_4_select.removeAllElements();
		this.v_ands_4_select.add(new SF_andBlock(this));
		this.fill_gridContainer4Popup();
		return this;
	}
	

	
	
	private class ownActionAdd_And_Block extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter oThis = SF__Filter.this;
			oThis.v_ands_4_popup.add(new SF_andBlock(oThis));
			oThis.fill_gridContainer4Popup();
		}
	}
	
	private class ownActionRefreshMaskComponent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter.this.fill_components_4_selector(	SF__Filter.this.grid4selektor,
														new ownButtonToShowCompleteSelection(SF__Filter.this.get_WhereBlock()),
														SF__Filter.this.bt_clear_anzeige_in_base,
														SF__Filter.this.bt_start_filterselectdefinition);
			
		}
	}
	
	
	private class ownActionTransferBedingungen extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SF__Filter oThis = SF__Filter.this;
			SF_Filter_CONST.transfer_one_into_two(oThis.v_ands_4_popup, oThis.v_ands_4_select);
		}
	}
	
	
	public SF__Filter add_orLine_from_external(IF_Field field, COMP comp, String input_string, boolean clean_filter_before, boolean startSelectionAtEnd) throws myException {
		return this.add_orLine_from_external(new IF_Field_ext(field), comp, input_string,clean_filter_before,startSelectionAtEnd);
	}
	
	
	/**
	 * haengt eine bedingung an den letzten and-block (einer ist immer da)
	 * @param field
	 * @param comp
	 * @param input_string
	 * @return
	 * @throws myException 
	 */
	public SF__Filter add_orLine_from_external(IF_Field_ext field, COMP comp, String input_string, boolean clean_filter_before, boolean startSelectionAtEnd) throws myException {
		//zuerst refreshen und basis-status festlegen
		if (clean_filter_before) {
			this.clearFilter();
		}
		
		//zuerst den letzten and-block suchen
		SF_andBlock  and_block_last = this.v_ands_4_select.get(v_ands_4_select.size()-1);

		//dann eine leere zeile anhaengen
		SF_orLine    or_line_last = new SF_orLine(this, and_block_last);
		and_block_last.add_orLine(or_line_last);
		
		//fehlerpruefung
		if (field == null || comp == null) {
			throw new myException(this,"System-error: field and compare-operator MUST NOT BE NULL!");
		}
		
		or_line_last.get_selfield().set_ActiveValue_OR_FirstValue(field.tnfn());
		if (or_line_last.get_selfield().getSelectedIndex()==0) {
			throw new myException(this,"System-error: Field <"+field.tnfn()+"> was not found !");
		}
		
		
		or_line_last.get_selfield().do_actionPassivSimulated(); //stellt die richtigen compare-werte fuer das feld ein
		
		or_line_last.get_selComps().set_ActiveValue_OR_FirstValue(comp.ausdruck());
		if (or_line_last.get_selComps().getSelectedIndex()==0) {
			throw new myException(this,"System-error: operator <"+field.tnfn()+"> was not found !");
		}

		or_line_last.get_containerInput().set_value(input_string);
		or_line_last.get_bt_save().doActionPassiv();
		
		//damit ist die zeile geprueft und geschlossen
		if (this.selectAction!=null && startSelectionAtEnd) {
			this.selectAction.ExecuteAgentCode(new ExecINFO(new MyActionEvent(new ActionEvent(this.bt_closePopupAndStartSelection, null)),true));
		}
		
		this.fill_components_4_selector(	this.grid4selektor, 
											new ownButtonToShowCompleteSelection(this.get_WhereBlock()),
											this.bt_clear_anzeige_in_base,
											this.bt_start_filterselectdefinition);

		
		return this;
	}

	
	/**
	 * fuegt einen neuen and-block zu den bestehenden and-bloecken hinzu
	 * @return
	 * @throws myException
	 */
	public SF__Filter add_andBlock_from_external() throws myException {
		this.v_ands_4_select.add(new SF_andBlock(this));
		return this;
	}
	
	
	
	private class ownButtonToShowCompleteSelection extends MyE2_Button {

		private String completeStatement = null;
		
		public ownButtonToShowCompleteSelection(String p_completeStatement) {
			super(p_completeStatement.length()>50?p_completeStatement.substring(0,50)+"...":p_completeStatement);
			
			E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
			
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, new E2_ColorBase(),new E2_ColorBase()); 
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(0,0,0,0)); 
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT,new E2_FontItalic(-3));
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_FONT,new E2_FontPlain(-3));
			
			this.setStyle(ostyleTextButton);
			
			this.completeStatement = p_completeStatement;
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new ownPopup();
			}
		}
		
		private class ownPopup extends E2_BasicModuleContainer {
			public ownPopup() throws myException {
				super();
				MyE2_TextArea  ta = new MyE2_TextArea(ownButtonToShowCompleteSelection.this.completeStatement,300,4000,30);
				ta.setFont(new E2_FontPlain(-2));
				this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());
				this.set_bVisible_Row_For_Messages(false);
				this.add(ta,E2_INSETS.I(3,5,3,3));
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(330), new Extent(700), new MyE2_String("Erzeugtes Statement..."));
			}
		}
	}
	
}
