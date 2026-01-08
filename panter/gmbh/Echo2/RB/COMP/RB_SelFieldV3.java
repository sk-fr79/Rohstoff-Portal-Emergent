/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 02.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import echopointng.ContainerEx;
import echopointng.PopUp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.BasicInterfaces.IF_ExecuterWithObject;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.IF_simpleValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 02.05.2019
 *
 */
public class RB_SelFieldV3 extends E2_Grid implements E2_IF_Handles_ActionAgents, IF_RB_Component_Savable {

	
	//basiskomponenten im grid
	private RB_TextField 			tfShowSelection = 	new RB_TextField();     //textfeld, enthaelt die lesbare darstellung
	private PopUp        			popUp = 			new PopUp();	      	//popup
	
	
	private E2_Grid   				grid4ContainerEx   = new E2_Grid()._s(1);   // kann eine zelle enthalten (ohne suche) oder 2 zellen (mit suche)
	private ContainerEx 			popupContainerEx   = new ContainerEx();
	
	//zusatzgrid fuer suche
	private E2_Grid    				grid4Search  = new E2_Grid();
	private RB_TextField 			tfSearch =     new RB_TextField()._fo(new E2_FontPlain(-3));
	private E2_Button 				btStartSearch =   new E2_Button()
																.__setImages(E2_ResourceIcon.get_RI("suche_mini.png"), E2_ResourceIcon.get_RI("suche_mini.png"))
																._aaa(()->{_render();})
																._ttt(S.ms("Suche in den Menüs nach einem Wort"))
																;
	private E2_Button 				btClearSearch =   new E2_Button()
																.__setImages(E2_ResourceIcon.get_RI("eraser.png"), E2_ResourceIcon.get_RI("eraser.png"))
																._aaa(new ActionCleanSearch())
																._aaa(()->{_render();})
																._ttt(S.ms("Suchbegriff leeren"))
																;
	
	private int   		 			iWidth     = 200;
	
	private int          			iWidthOfDropDownBlock = 200;
	private int          			iHeightOfDropDownBlock = 200;
	
	private Color   	 			colorDropDownBack = new E2_ColorDark();
	private Color        			colorDropDownBorder = Color.DARKGRAY;
	
	private HMAP<String, String>   	hmVisibleValues = new HMAP<>();
	private HMAP<String, String>   	hmShadowValues = new HMAP<>();    //verdeckte, die aber geladen werden koennen
	
	//temporaere hashs, werden nach jedem rb_set_db_value_manual() wieder auf anfang gesetzt 
	private HMAP<String, String>   	hmVisibleValuesTemp = new HMAP<>();
	private HMAP<String, String>   	hmShadowValuesTemp = new HMAP<>();    //verdeckte, die aber geladen werden koennen
	
	
	private VEK<IF_ExecuterWithObject<MenueButton>>  executersAfterCreatingDropDownButton = new VEK<>();
	
	private VEK<MenueButton>        actualButtons = new VEK<>();
	
	
	private String   				actualValue = null;
	
	private boolean  				showSearchField = true;
	
	
	private boolean 				showEmptyInFront = true;
	
	
	public RB_SelFieldV3() {
		super();
	}

	

	/**
	 * 
	 * @author martin
	 * @date 03.02.2020
	 *
	 * in den HashMaps: key = anzeigetext, value = datenbank-wert
	 * @param valuesVisible
	 * @param valuesShadow
	 * @return
	 * @throws myException
	 */
	public RB_SelFieldV3 _populate(HMAP<String, String> valuesVisible,HMAP<String, String> valuesShadow) throws myException {
	
		this.hmVisibleValues.clear();
		this.hmShadowValues.clear();

		
		
		
		//dafuer sorgen, dass immer ein nullwert an bord ist 
		if (this.showEmptyInFront) {
			if (!valuesVisible.getValues().contains("")) {
				this.hmVisibleValues._put("", "");
			}
		}
		
		this.hmVisibleValues.putAll(valuesVisible);
		
		if (valuesShadow!=null) {
			this.hmShadowValues.putAll(valuesShadow);
		}
		
		this.hmVisibleValuesTemp.clear();
		this.hmShadowValuesTemp.clear();
		this.hmVisibleValuesTemp.putAll(this.hmVisibleValues);
		this.hmShadowValuesTemp.putAll(this.hmShadowValues);
	
		return this;
	}

	
	
	public RB_SelFieldV3 _render() throws myException {
		String suchText = this.tfSearch.getText();
		
		tfSearch.setBackground(new E2_ColorLLight());
		tfSearch._bord_dgrey();

		RB_gld ld = new RB_gld()._col(colorDropDownBack)._ins(0,0,4,0);
		RB_gld ld2 = new RB_gld()._col(colorDropDownBack);
		
		this.popupContainerEx.removeAll();
		this.grid4ContainerEx._clear();
		this.grid4Search._clear();
		this.grid4Search._a(tfSearch,ld)._a(btClearSearch,ld)._a(btStartSearch,ld);
		
		if (this.showSearchField) {
			this.grid4ContainerEx._a(grid4Search,ld2);
		}
		E2_Grid gridWithSelections = new E2_Grid()._w100()._s(1);

		actualButtons._clear();
		for (String btText: this.hmVisibleValuesTemp.keySet()) {
			if (S.isFull(suchText) && ! btText.toUpperCase().contains(suchText.toUpperCase())) {
				continue;
			}
			String buttonValue = this.hmVisibleValuesTemp.get(btText);
			MenueButton bt = new MenueButton(btText, buttonValue);
			if (buttonValue.equals(S.NN(this.actualValue))) {
				bt._bord_black();
				bt._b();
				bt._backDDark();
			}
			bt._aaaV(this.vActionAgents);
			gridWithSelections._a(actualButtons._ar(bt));
			
			for (IF_ExecuterWithObject<MenueButton> ex: executersAfterCreatingDropDownButton) {
				ex.execute(bt);
			}
			
		}

		this.grid4ContainerEx._a(gridWithSelections);
		
		
		this._a(tfShowSelection,new RB_gld()._ins(1))._a(popUp,new RB_gld()._ins(1)._center_mid());
		this.tfShowSelection.setEnabled(false);
		
		
		popupContainerEx.add(grid4ContainerEx);
		popupContainerEx.setScrollBarPolicy(ContainerEx.AUTO);
		popUp.setPopUp(popupContainerEx);
		
		popupContainerEx.setBackground(colorDropDownBack);
		
		popUp.setPopUpOnRollover(false);
		popUp.setPopUpAlwaysOnTop(true);
		popUp.setInsets(new Insets(0,0,0,0));
		popUp.setOutsets(new Insets(0,0,0,0));
	    popUp.setPopUpBorder(new Border(1, this.colorDropDownBorder, Border.STYLE_SOLID));
		popUp.setPopUpAlignment(new Alignment(Alignment.RIGHT,Alignment.BOTTOM));
		popUp.setToggleIcon(E2_ResourceIcon.get_RI("popdownflat2.png"));
		popUp.setToggleRolloverIcon(E2_ResourceIcon.get_RI("popdownflat2_highlight.png"));
		popUp.setTogglePressedIcon(E2_ResourceIcon.get_RI("popdownflat2_pressed.png"));
		popUp.setToggleBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		popUp.setToggleRolloverBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		popUp.setTogglePressedBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		
		popUp.setTargetBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		popUp.setTargetRolloverBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		popUp.setTargetBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		popUp.setBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		popUp.setRolloverBorder(new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));
		
		this._setSizes(this.iWidth, this.iWidthOfDropDownBlock, this.iHeightOfDropDownBlock);
		
		return this;
	}
	
	
	public RB_SelFieldV3 _setSizes(int width, int widthOfDropDown, int heightOfDropdown) {
		
		this.iWidth=width;
		
		this._setSize(iWidth-24,24);
		this._w(iWidth);
		this.tfShowSelection._w(iWidth-24);
		
		this.iWidthOfDropDownBlock=widthOfDropDown;
		this.iHeightOfDropDownBlock = heightOfDropdown;

		int breiteSuchText = iWidthOfDropDownBlock-70;
		this.grid4Search._setSize(breiteSuchText-6,20,20);
		this.grid4Search._setRowH(0, 15);
		tfSearch._w(breiteSuchText);
		
		popupContainerEx.setWidth(new Extent(iWidthOfDropDownBlock));
		popupContainerEx.setHeight(new Extent(iHeightOfDropDownBlock));
		popupContainerEx.setScrollBarPolicy(ContainerEx.AUTO);
		
	    popUp.setPopUpLeftOffset(-1*iWidth);
	    popUp.setPopUpTopOffset(3);

		return this;
	}
	



	public int getiWidthOfDropDownBlock() {
		return iWidthOfDropDownBlock;
	}



	public int getiHeightOfDropDownBlock() {
		return iHeightOfDropDownBlock;
	}



	public Color getColorDropDownBack() {
		return colorDropDownBack;
	}


	public RB_SelFieldV3 _setColorDropDownBack(Color colorDropDownBack) {
		this.colorDropDownBack = colorDropDownBack;
		this.popupContainerEx.setBackground(this.colorDropDownBack);
		return this;
	}


	public Color getColorDropDownBorder() {
		return colorDropDownBorder;
	}


	public RB_SelFieldV3 _setColorDropDownBorder(Color colorDropDownBorder) {
		this.colorDropDownBorder = colorDropDownBorder;
		return this;
	}


	public RB_TextField getTfShowSelection() {
		return tfShowSelection;
	}


	public PopUp getPopUp() {
		return popUp;
	}

	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.tfShowSelection.setForeground(enabled?Color.BLACK:new E2_ColorGray(100));   //setzt die farbe im hintergrund grau
		this.tfShowSelection.setBackground(enabled?new E2_ColorEditBackground():new E2_ColorGray(230));   //setzt die farbe im hintergrund grau
		this.tfShowSelection.setEnabled(false);     //bearbeiten ist immer verboten
		this.popUp.setEnabled(enabled);
		
	}

	

	public String getActualValue() {
		return actualValue;
	}

	

	
	//  ----------------------------------------------------------------------------
	//  ----------------------------------------------------------------------------
	//  hier beginnt der block fuer die implemtierung von E2_IF_Handles_ActionAgents
	//  wird hier nur benutzt, um eine aehnliche verhaltensweise wie ein selectField zu erreichen
	//  ----------------------------------------------------------------------------
	//  ----------------------------------------------------------------------------
	//  ----------------------------------------------------------------------------
	
	
	/*
	 * ein vector fuer die speicherung der MyE2_ActionAgent - object.
	 * dieser werden dann nach der reihenfolge ihrer zufuegung ausgefuehrt 
	 */
	private Vector<XX_ActionAgent> 	vActionAgents = new Vector<XX_ActionAgent>();
	private  E2_Break4PopupController break4PopupController = null;
	private boolean bActionEventIsPassive = false;

	private Vector<XX_ActionValidator>	vGlobalValidators = 	new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>	vIDValidators = 		new Vector<XX_ActionValidator>();
	
	
	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;
	
	
	private XX_MessageManipulator      oMessageManipulator = null;
	
	
	@Override
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

	
	@Override
	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		this.oMessageManipulator = oManipulator;
	}


	@Override
	public E2_Break4PopupController  getBreak4PopupController() {
		return this.break4PopupController;
	}


	@Override
	public void setBreak4PopupController(E2_Break4PopupController controller) {
		this.break4PopupController = controller;
	}
	
	@Override
	public  RB_SelFieldV3  _aaa(XX_ActionAgent agent) {
		this.add_oActionAgent(agent);
		return this;
	}

	
	@Override
	public RB_SelFieldV3  _aaa(XX_ActionAgent agent, boolean bInFront) {
		this.add_oActionAgent(agent, bInFront);
		return this;
	}

	
	@Override
	public  RB_SelFieldV3  _aaaV(Vector<XX_ActionAgent> v_agents) {
		for (XX_ActionAgent agent: v_agents) {
			this.add_oActionAgent(agent);
		}
		return this;
	}

	
	@Override
	public RB_SelFieldV3  _aaaV(Vector<XX_ActionAgent> v_agents, boolean inFront) {
		//umsortieren
		VEK<XX_ActionAgent> vAgentsReverseOrder = new VEK<XX_ActionAgent>();
		if (inFront) {
			for (XX_ActionAgent agent: v_agents) {
				vAgentsReverseOrder.add(0, agent);
			}
		} else {
			vAgentsReverseOrder.addAll(v_agents);
		}
		for (XX_ActionAgent agent: vAgentsReverseOrder) {
			this.add_oActionAgent(agent,inFront);
		}
		return this;
	}


	@Override
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront) {
		for (XX_ActionAgent agent: vActionAgent) {
			this.add_oActionAgent(agent);
		}
	}
	
	
		
	@Override
	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session()	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}

	
	@Override
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) {
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}



	
	@Override
	public void add_GlobalValidator(XX_ActionValidator oValid) {
		this.vGlobalValidators.add(oValid);
	}
	
	
	
		@Override
	public void add_IDValidator(XX_ActionValidator oValid) {
		this.vIDValidators.add(oValid);
	}
	
	@Override
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid) {
		this.vGlobalValidators.addAll(vValid);
	}

	@Override
	public void add_IDValidator(Vector<XX_ActionValidator> vValid){
		this.vIDValidators.addAll(vValid);
	}


	@Override
	public MyE2_MessageVector valid_GlobalValidation() throws myException {
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vGlobalValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vGlobalValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(this));
		}
		return vRueck;
	}

	@Override
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}
		return vRueck;
	}



	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent) {
		if (actionAgent != null) {
			this.vActionAgents.add(actionAgent);
		}
	}

	
	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront) {
		if (actionAgent != null) {
			if (bInFront) {
				this.vActionAgents.add(0,actionAgent);
			} else {
				this.vActionAgents.add(actionAgent);
			}
		}

	}
		
	@Override
	public void remove_oActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vActionAgents.size();i++) {
			if (this.vActionAgents.get(i)==actionAgent)	{
				this.vActionAgents.remove(i);
			}
		}
	}

	
	@Override
	public void remove_AllActionAgents() {
		this.vActionAgents.removeAllElements();
	}

	
	@Override
	public Vector<XX_ActionAgent> get_vActionAgents() {
		return this.vActionAgents;
	}



	@Override
	public void doActionPassiv() {
	}


	@Override
	public boolean get_bIsPassivAction() {
		return this.bActionEventIsPassive;
	}


	@Override
	public void set_bPassivAction(boolean bPassiv) {
		this.bActionEventIsPassive = bPassiv;
	}



	@Override
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException	{
	}


	//2013-01-04: codeblock fuer interalActionAgents
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	
	@Override
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents() {
		return this.vInternalActionAgents;
	}

	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent) {
	}

	
	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)	{
	}
	
	@Override
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent) {
	}

	@Override
	public void remove_AllInternalActionAgents()  {
	}





	/**
	 * ab hier implemtierung IF_RB_Component
	 */

	private Vector<RB_Validator_Component> validators = new Vector<>();
	private RB_KF key = null;

	@Override
	public void mark_Neutral() throws myException {
	}


	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.rb_set_db_value_manual("");
		} else {
			this.rb_set_db_value_manual(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		
		this.hmVisibleValuesTemp.clear();
		this.hmShadowValuesTemp.clear();
		this.hmVisibleValuesTemp.putAll(this.hmVisibleValues);
		this.hmShadowValuesTemp.putAll(this.hmShadowValues);

		String value = S.NN(valueFormated);
		
		boolean found = false;
		
		
		
		//jetzt den text (=key) fuer den gesuchten wert suchen)
		String btTextFound = null;
		for (String btText: hmVisibleValuesTemp.getKeys()) {
			if (hmVisibleValuesTemp.get(btText).equals(value)) {
				btTextFound = btText;
				found = true;
				break;
			}
		}
		
		if (S.isEmpty(btTextFound)) {
			//das gleiche spiel mit den shadows
			for (String btText: hmShadowValuesTemp.getKeys()) {
				if (hmShadowValuesTemp.get(btText).equals(value)) {
					btTextFound = btText;
					//dann den shadow-wert zu den visibles dazu
					this.hmVisibleValuesTemp._put(btTextFound, hmShadowValuesTemp.get(btText));
					found = true;
					break;
				}
			}
		}
		
		if (!found) {
			//dann den wert in die liste aufnehmen
			this.hmVisibleValuesTemp._put(value, value);
			btTextFound = value;
		}
		this.actualValue = value;
		this.tfShowSelection._t(btTextFound);
		this._render();
	}


	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}


	@Override
	public void set_rb_RB_K(RB_KF p_key) throws myException {
		key = p_key;
	}


	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return validators;
	}





	
	private class ActionCleanSearch extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			tfSearch.setText("");
		}
	}


	public boolean isShowSearchField() {
		return showSearchField;
	}


	public RB_SelFieldV3 _setShowSearchField(boolean showSearchField) throws myException {
		this.showSearchField = showSearchField;
		this._render();
		return this;
	}
	
	
	
	private class MenueButton extends E2_Button {
		public MenueButton(String text, String value) {
			super();
			this._t(text)._setShapeStyleStandard();
			this._height(20);
			this.setTextAlignment(Alignment.ALIGN_LEFT);
			this.setInsets(new Insets(0,2,0,0));
			this.add_oInternalActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					tfSearch._t("");    //suchtext wird leer gemacht
					RB_SelFieldV3.this.rb_set_db_value_manual(value);
					popUp.setExpanded(false);
				}
			});
		}
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return actualValue;
	}
	
	
	public RB_SelFieldV3 _addExecuterAfterCreatingDropDownButton(IF_ExecuterWithObject<MenueButton> executer) {
		this.executersAfterCreatingDropDownButton.add(executer);
		return this;
	}
	
	
	
	public RB_SelFieldV3  _aaa(IF_agentSimple agent) {
		this.add_oActionAgent(agent.genActionAgent());
		return this;
	}
	
	//20180229: moeglichkeit, simple actions zu erzeugen und zu uebergebben
	public RB_SelFieldV3  _aaaInFront(IF_agentSimple agent, boolean inFront) {
		this.add_oActionAgent(agent.genActionAgent(), inFront);
		return this;
	}

	
	//20180406: validierer als lambda zulassen
	public RB_SelFieldV3 _addValidator(IF_simpleValidator validator) {
		this.add_GlobalValidator(validator.getValidator());
		return this;
	}
	
	
}
