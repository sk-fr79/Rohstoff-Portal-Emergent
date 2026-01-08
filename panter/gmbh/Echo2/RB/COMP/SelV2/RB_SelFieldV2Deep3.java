/**
 * panter.gmbh.Echo2.RB.COMP
 * @author martin
 * @date 02.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SelV2;

import java.util.Vector;

import echopointng.ContainerEx;
import echopointng.PopUp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForInternalActionAgents;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
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
public class RB_SelFieldV2Deep3 extends E2_Grid implements E2_IF_Handles_ActionAgents, IF_RB_Component {

	public enum  SELFIELD_ALIGN {
		LEFTBOUND
		,RIGHTBOUND
		;
		
		private SELFIELD_ALIGN() {
		}
		
		public int getLeftOffsetInPixel(int iWidhtTextField, int iWidhtPopupButton, int widthOfPopDownBox) {
			int offset = 0;
			switch (this) {
			case LEFTBOUND:
				offset = -1*(iWidhtTextField+iWidhtPopupButton+2);
				break;
			case RIGHTBOUND:
				offset = -1*(widthOfPopDownBox+2);
				break;
			default:
				break;
			}
			
			
			return offset;
		}
		
	}
	
	
	
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
																._aaa(new ActionSucheInDropdown())
																._ttt(S.ms("Suche in den Menüs nach einem Wort"))
																;
	private E2_Button 				btClearSearch =   new E2_Button()
																.__setImages(E2_ResourceIcon.get_RI("eraser.png"), E2_ResourceIcon.get_RI("eraser.png"))
																._aaa(new ActionCleanSearch())
																._ttt(S.ms("Suchbegriff leeren"))
																;
	
	//abmessungen
	private int   		 			iWidhtTextField     = 100;
	private int   		 			iWidhtPopupButton   = 20;
	
	private int          			iWidthOfDropDownBlock = 200;
	private int          			iHeightOfDropDownBlock = 200;
	
	private Color   	 			colorDropDownBack = new E2_ColorDark();
	private Color        			colorDropDownBorder = Color.DARKGRAY;
	
	
	private HMAP<String, HMAP<String, HMAP<String, String>>>   	hmSubMenuesDeep3 = new HMAP<>(); 
	private HMAP<String, HMAP<String, String>>   				hmSubMenues = new HMAP<>(); 
	
	private HMAP<String, String>   								hmVisibleValues = new HMAP<>();
	private HMAP<String, String>   								hmShadowValues = new HMAP<>();    //verdeckte, die aber geladen werden koennen
	
	private String   				actualValue = null;
	
	//wenn die dropdown-box linksbuendig aufpoppen soll, sonst rechtsbuendig
	private SELFIELD_ALIGN   		horizontalAlign = SELFIELD_ALIGN.LEFTBOUND;
	
	private boolean  				showSearchField = true;
	
	private OwnRB_SelectCascadingWithString 	selectCascadingWithString = null;
	
	/**
	 * hier koennen den buttons beim aufbau unter dem buttonstring noch tooltips hinterlegt werden
	 */
	private HMAP<String, String>    hmButtonAndTooltips = new HMAP<>();
	
	
	public RB_SelFieldV2Deep3() {
		super();
	}


	public RB_SelFieldV2Deep3 _render() throws myException {
		
		this._clear();
		this.grid4ContainerEx._clear();
		this.grid4Search._clear();
		
		
		//suchgrid oberhalb der container-Ex-Zelle
		int breiteSuchText = iWidthOfDropDownBlock-70;
		this.grid4Search._setSize(breiteSuchText-6,20,20);
		this.grid4Search._setRowH(0, 15);
		tfSearch.setBackground(new E2_ColorLLight());
		tfSearch._bord_dgrey();
		tfSearch._w(breiteSuchText);
		RB_gld ld = new RB_gld()._col(colorDropDownBack)._ins(0,0,4,0);
		this.grid4Search._a(tfSearch,ld)._a(btClearSearch,ld)._a(btStartSearch,ld);
		RB_gld ld2 = new RB_gld()._col(colorDropDownBack);
		if (this.showSearchField) {
			this.grid4ContainerEx._a(grid4Search,ld2);
		}
		this.grid4ContainerEx._a(popupContainerEx,ld2);
		
		this._width(iWidhtTextField+iWidhtPopupButton+4);
		this._setSize(iWidhtTextField,iWidhtPopupButton);
		this._a(tfShowSelection,new RB_gld()._ins(1))._a(popUp,new RB_gld()._ins(1)._center_mid());
		this.tfShowSelection._w(iWidhtTextField);
		this.tfShowSelection.setEnabled(false);
		
		grid4ContainerEx.setWidth(new Extent(iWidthOfDropDownBlock));
		
		popupContainerEx.setWidth(new Extent(iWidthOfDropDownBlock));
		popupContainerEx.setHeight(new Extent(iHeightOfDropDownBlock));
		popupContainerEx.setScrollBarPolicy(ContainerEx.AUTO);
		popUp.setPopUp(grid4ContainerEx);
		
		popupContainerEx.setBackground(colorDropDownBack);
		
		popUp.setPopUpOnRollover(false);
		popUp.setPopUpAlwaysOnTop(true);
		popUp.setInsets(new Insets(0,0,0,0));
		popUp.setOutsets(new Insets(0,0,0,0));
	    popUp.setPopUpBorder(new Border(1, this.colorDropDownBorder, Border.STYLE_SOLID));
	    //popUp.setPopUpLeftOffset(-1*(iWidhtTextField+iWidhtPopupButton+2));
	    popUp.setPopUpLeftOffset(this.horizontalAlign.getLeftOffsetInPixel(iWidhtTextField, iWidhtPopupButton, iWidthOfDropDownBlock));
	    popUp.setPopUpTopOffset(3);
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
		
		return this;
	}
	
	
	/**
	 * versucht die breite nach den maximalen inhalten zu definieren
	 * @author martin
	 * @date 22.05.2019
	 *
	 * @return
	 */
	public RB_SelFieldV2Deep3 _setAutomaticButtonWidth() {
		int numberOfCharactersFor100pixel = 13;
		
		int maxLenght = 50;  // gibt 300 pixel breite 
		for (RB_SelectCascadingWithString.MenueButton b: this.selectCascadingWithString.getAllMenueButtons()) {
			if (b.getValue()!=null) {
				if (b.getText().length()>maxLenght) {
					maxLenght=b.getText().length();
				}
			}
		}

		if (maxLenght>400) {
			maxLenght = 400;
		}
		
		iWidthOfDropDownBlock = (maxLenght/numberOfCharactersFor100pixel)*100;
		
		
		grid4ContainerEx.setWidth(new Extent(iWidthOfDropDownBlock));
		popupContainerEx.setWidth(new Extent(iWidthOfDropDownBlock));
		
		return this;
	}
	
	
	
	public int getiWidhtTextField() {
		return iWidhtTextField;
	}


	public RB_SelFieldV2Deep3 _setWidthTextField(int widhtTextField) {
		this.iWidhtTextField = widhtTextField;
		return this;
	}


	public int getiWidhtPopupButton() {
		return iWidhtPopupButton;
	}


	public RB_SelFieldV2Deep3 _setWidhtPopupButton(int widhtPopupButton) {
		this.iWidhtPopupButton = widhtPopupButton;
		return this;
	}


	public int getiWidthOfDropDownBlock() {
		return iWidthOfDropDownBlock;
	}


	public RB_SelFieldV2Deep3 _setWidthOfDropDownBlock(int widthOfDropDownBlock) {
		this.iWidthOfDropDownBlock = widthOfDropDownBlock;
		return this;
	}


	public int getiHeightOfDropDownBlock() {
		return iHeightOfDropDownBlock;
	}


	public RB_SelFieldV2Deep3 _setHeightOfDropDownBlock(int heightOfDropDownBlock) {
		this.iHeightOfDropDownBlock = heightOfDropDownBlock;
		return this;
	}


	public Color getColorDropDownBack() {
		return colorDropDownBack;
	}


	public RB_SelFieldV2Deep3 _setColorDropDownBack(Color colorDropDownBack) {
		this.colorDropDownBack = colorDropDownBack;
		this.popupContainerEx.setBackground(this.colorDropDownBack);
		return this;
	}


	public Color getColorDropDownBorder() {
		return colorDropDownBorder;
	}


	public RB_SelFieldV2Deep3 _setColorDropDownBorder(Color colorDropDownBorder) {
		this.colorDropDownBorder = colorDropDownBorder;
		return this;
	}


	public RB_TextField getTfShowSelection() {
		return tfShowSelection;
	}


	public PopUp getPopUp() {
		return popUp;
	}

	/**
	 * innere standard-action (wird im innneractionListener ausgefuehrt) und bilded die gleichnamige methode
	 * des RB_SelectCascading - objectes ab, kann überschrieben werden
	 * @author martin
	 * @date 06.05.2019
	 *
	 * @param key
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector executeClickOnMenueButton(String key, RB_SelectCascadingWithString.MenueButton menueButton) throws myException {
		
		//evtl. suche ausschalten
		tfSearch.setText("");
		selectCascadingWithString._setHidden(null);

		popUp.setExpanded(false);
		String textForUser  = selectCascadingWithString.findTextOfKey(key);
		if (textForUser==null) {
			tfShowSelection._t("@@Error");
			throw new myException("RB_SelFieldV2: undefined state:  15539932-6cfb-11e9-a923-1681be663d3e");
		}
		tfShowSelection._t(S.NN( selectCascadingWithString.findTextOfKey(key)));
		tfShowSelection.setToolTipText(S.NN(textForUser));
		
		if (S.isFull(this.getHmButtonAndTooltips().get(key))) {
			tfShowSelection.setToolTipText(this.getHmButtonAndTooltips().get(key));
		}
		

		actualValue=key;
		selectCascadingWithString._renderStatus(actualValue);
		return bibMSG.newMV();
	}	

	/**
	 * 
	 * @author martin
	 * @date 03.05.2019
	 *
	 * @param valuesVisible
	 * @param valuesShadow  (nullable)
	 * @param valuesSubMenues (nullable)
	 * @return
	 * @throws myException
	 */
	public RB_SelFieldV2Deep3 _populate(		HMAP<String, HMAP<String, HMAP<String, String>>> 	valuesSubMenues3, 
												HMAP<String, HMAP<String, String>>				   	valuesSubMenues2, 
												HMAP<String, String> 								valuesVisible,
												HMAP<String, String> 								valuesShadow) throws myException {
		
	
		this.hmSubMenuesDeep3.clear();
		this.hmSubMenues.clear();
		this.hmVisibleValues.clear();
		this.hmShadowValues.clear();
		
		if (valuesVisible!=null) {
			this.hmVisibleValues.putAll(valuesVisible);
		}
		if (valuesShadow!=null) {
			this.hmShadowValues.putAll(valuesShadow);
		}
		if (valuesSubMenues3!=null) {
			this.hmSubMenuesDeep3.putAll(valuesSubMenues3);
		}
		if (valuesSubMenues2!=null) {
			this.hmSubMenues.putAll(valuesSubMenues2);
		}
		
		this.buildSelector();
		
		return this;
	}
	

	protected void buildSelector() throws myException {
		this.selectCascadingWithString = new OwnRB_SelectCascadingWithString(this.hmSubMenuesDeep3,hmSubMenues, hmVisibleValues, hmShadowValues );
		selectCascadingWithString._setWidthBaseMenue(this.iWidthOfDropDownBlock);
		selectCascadingWithString.setHeight(new Extent(100,Extent.PERCENT));
		selectCascadingWithString.setWidth(new Extent(100,Extent.PERCENT));
		
		//this.popupContainerEx.clear();
		this.popupContainerEx.removeAll();
		this.popupContainerEx.add(selectCascadingWithString);
	}
	
	

	public String getActualValue() {
		return actualValue;
	}

	
	public RB_SelFieldV2Deep3 _setActualValue(String value) throws myException {
		this.actualValue = value;
		
		//eine evtl.reduzierte sicht resetten
		this.selectCascadingWithString._setHidden(null);
		
		RB_SelectCascading<String>.Menue 			menueWithActualValue = 	this.selectCascadingWithString.findMenueOf(value);
		RB_SelectCascading<String>.MenueButton 		buttonWithActualValue = this.selectCascadingWithString.findMenueButtonOf(value);

		if (menueWithActualValue==null || buttonWithActualValue==null) {
			this.hmShadowValues.put(value, "<"+value+">");
			this.buildSelector();
			//nochmal suchen
			this.selectCascadingWithString.findMenueButtonOf(value)._setVisibleInRendering(true);
			menueWithActualValue = this.selectCascadingWithString.findMenueOf(value);
		} else {
			this.selectCascadingWithString.findMenueButtonOf(value)._setVisibleInRendering(true);
		}
		
		if (menueWithActualValue==null) {
			throw new myException("Error!  e8fc078a-6d8f-11e9-a923-1681be6345e: cannot setActualValue!!");
		} else {
			String textForUser  = selectCascadingWithString.findTextOfKey(value);
			if (textForUser==null) {
				tfShowSelection._t("@@Error");
				throw new myException("RB_SelFieldV2: undefined state:  15539932-6cfb-11e9-a923-1681be663d3e");
			}
			tfShowSelection._t(S.NN(textForUser));
			tfShowSelection.setToolTipText(S.NN(textForUser));
			
			if (S.isFull(this.getHmButtonAndTooltips().get(value))) {
				tfShowSelection.setToolTipText(this.getHmButtonAndTooltips().get(value));
			}
			 
			
			
			actualValue=value;
			selectCascadingWithString._renderStatus(value);
		}
		
		return this;
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
	private Vector<ActionListener>	vExternalActionListeners = new Vector<ActionListener>();
	private  E2_Break4PopupController break4PopupController = null;
	private boolean bActionEventIsPassive = false;

	private Vector<XX_ActionValidator>	vGlobalValidators = 	new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>	vIDValidators = 		new Vector<XX_ActionValidator>();
	
	
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();
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
	public  RB_SelFieldV2Deep3  _aaa(XX_ActionAgent agent) {
		this.add_oActionAgent(agent);
		return this;
	}

	
	@Override
	public RB_SelFieldV2Deep3  _aaa(XX_ActionAgent agent, boolean bInFront) {
		this.add_oActionAgent(agent, bInFront);
		return this;
	}

	
	@Override
	public  RB_SelFieldV2Deep3  _aaaV(Vector<XX_ActionAgent> v_agents) {
		for (XX_ActionAgent agent: v_agents) {
			this.add_oActionAgent(agent);
		}
		return this;
	}

	
	@Override
	public RB_SelFieldV2Deep3  _aaaV(Vector<XX_ActionAgent> v_agents, boolean inFront) {
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
		if (this.selectCascadingWithString!=null) {
			this.selectCascadingWithString._delegateActionsAndValidatorsToTargetButtons();
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
		if (this.selectCascadingWithString!=null) {
			this.selectCascadingWithString._delegateActionsAndValidatorsToTargetButtons();
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
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
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
			this._setActualValue("");
		} else {
			this._setActualValue(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this._setActualValue(valueFormated);
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



	/**
	 * methode, um auf auesserem level auf die buttons zugreifen zu koennen
	 * @author martin
	 * @date 03.05.2019
	 *
	 * @param button
	 * @param key
	 */
	public void setShapeOfActionButtonOutside(RB_SelectCascading<String>.MenueButton button, String key) {
	}
	public void setHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton bt) {
	}
	public void resetHighLightStatusOfActualValueButtonOutside(RB_SelectCascading<String>.MenueButton bt) {
	}




	//hier wird jetzt ein element fuer die popup-darstellung erzeugt
	protected class OwnRB_SelectCascadingWithString extends RB_SelectCascadingWithStringDeep3 {

		public OwnRB_SelectCascadingWithString(HMAP<String, HMAP<String, HMAP<String, String>>> 	hmSubMenues3,	HMAP<String, HMAP<String, String>> hmSubMenues , HMAP<String, String> hmVisible, HMAP<String, String> hmShadow) throws myException {
			super(hmSubMenues3, hmSubMenues, hmVisible, hmShadow);
		}

		@Override
		public MyE2_MessageVector executeClickOnMenueButton(String key, RB_SelectCascadingWithString.MenueButton menueButton) throws myException {
			return RB_SelFieldV2Deep3.this.executeClickOnMenueButton(key, menueButton);
		}

		@Override
		public void addActionsAgentsAndValidatorsToTargetButton(RB_SelectCascading<String>.MenueButton button,String target) {
			for (XX_ActionAgent agent: vActionAgents) {
				button._aaa(agent);
			}
			
			for (XX_ActionValidator valid: vGlobalValidators) {
				button.add_GlobalValidator(valid);
			}
		}
		
		
		@Override
		public void setShapeOfActionButton(RB_SelectCascading<String>.MenueButton button, String key) throws myException {
			button.setFont(new E2_FontPlain());
			button.setText(S.NN(findTextOfKey(key)));
			button.setStyle(RB_SelectCascadingWithString.StyleForActionButton());
			if (isInInActiveBlock(key)) {
				button.set_bEnabled_For_Edit(false);
				button._setVisibleInRendering(false);
			}
			setShapeOfActionButtonOutside(button, key);
		}
		
		@Override
		public void setHighLightStatusOfActualValueButton(RB_SelectCascading<String>.MenueButton bt) {
			setHighLightStatusOfActualValueButtonOutside(bt);
		}

		@Override
		public void resetHighLightStatusOfActualValueButton(RB_SelectCascading<String>.MenueButton bt) {
			resetHighLightStatusOfActualValueButtonOutside(bt);
		}

	}




	public RB_SelectCascadingWithStringDeep3 getSelectCascadingWithString() {
		return selectCascadingWithString;
	}


	
	private class ActionSucheInDropdown extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			VEK<String> hidden = new VEK<>();
			
			String suchText = tfSearch.getText().trim();
			if (S.isFull(suchText)) {
				for (OwnRB_SelectCascadingWithString.MenueButton s: selectCascadingWithString.getAllMenueButtons()) {
					if (!s.getText().toUpperCase().contains(suchText.toUpperCase())) {
						hidden._a(s.getValue());
					}
				}
			}
			
			if (hidden.size()==selectCascadingWithString.getAllMenueButtons().size()) {
				bibMSG.MV()._addWarn(S.ms("Keine Treffer gefunden !"));
			} else {
				selectCascadingWithString._setHidden(hidden)._gotoStartMenue();
			}
		}
		
	}

	
	
	private class ActionCleanSearch extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			tfSearch.setText("");
			selectCascadingWithString._setHidden(null)._gotoStartMenue();
			if (S.isFull(actualValue)) {
				selectCascadingWithString._renderStatus(actualValue);
			}
		}
		
	}




	public SELFIELD_ALIGN getHorizontalAlign() {
		return horizontalAlign;
	}


	public RB_SelFieldV2Deep3 _setHorizontalAlign(SELFIELD_ALIGN horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
		return this;
	}

	public RB_SelFieldV2Deep3 _setHorizontalAlignLeft() {
		this.horizontalAlign = SELFIELD_ALIGN.LEFTBOUND;
		return this;
	}
	
	public RB_SelFieldV2Deep3 _setHorizontalAlignRight() {
		this.horizontalAlign = SELFIELD_ALIGN.RIGHTBOUND;
		return this;
	}


	public boolean isShowSearchField() {
		return showSearchField;
	}


	public RB_SelFieldV2Deep3 _setShowSearchField(boolean showSearchField) {
		this.showSearchField = showSearchField;
		return this;
	}
	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.tfShowSelection.setForeground(enabled?Color.BLACK:new E2_ColorGray(100));   //setzt die farbe im hintergrund grau
		this.tfShowSelection.setBackground(enabled?new E2_ColorEditBackground():new E2_ColorGray(230));   //setzt die farbe im hintergrund grau
		this.tfShowSelection.setEnabled(false);     //bearbeiten ist immer verboten
		this.popUp.setEnabled(enabled);
	}


	public HMAP<String, String> getHmButtonAndTooltips() {
		return hmButtonAndTooltips;
	}

}
