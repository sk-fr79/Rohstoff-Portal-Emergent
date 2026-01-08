package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForInternalActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_PassiveActionExecuter;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.BasicInterfaces.IF_Align;
import panter.gmbh.Echo2.BasicInterfaces.IF_ComponentTools;
import panter.gmbh.Echo2.BasicInterfaces.IF_Dimension;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.BasicInterfaces.IF_Style;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;


public class RB_cb extends CheckBox  implements IF_RB_Component_Savable 
												,E2_IF_Handles_ActionAgents  
												,IF_FontandText<RB_cb>
												,IF_LayoutData<RB_cb>
												,IF_Dimension<RB_cb>
												,IF_Style<RB_cb>
												,IF_ComponentTools<RB_cb>
												,IF_Align<RB_cb>
												{
											
	public RB_cb() {
		super();
		this.setFont(new E2_FontPlain());
	}

	public RB_cb(String text) {
		super(text);
		this.setFont(new E2_FontPlain());
	}

	public RB_cb(MyE2_String text) {
		super(text.CTrans());
		this.setFont(new E2_FontPlain());
	}

	private MyE2EXT__Component 			oEXT = new MyE2EXT__Component(this);
	private ImageReference 				oImg = null;
	
	private Vector<XX_ActionAgent>   	vActionAgents = new Vector<XX_ActionAgent>();

	
	private Vector<XX_ActionValidator>  vGlobalValidators = new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>  vIDValidators = new Vector<XX_ActionValidator>();

	
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();

	private boolean 					Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;

	
	
	/**
	 * set text untranslated
	 * @param t
	 * @return
	 */
	public RB_cb _t(String t) {
		this.setText(t);
		return this;
	}
	
	
	
	public RB_cb _t(MyE2_String t) {
		this.setText(t.CTrans());
		return this;
	}
	
	
	public RB_cb  _ttt(MyE2_String tooltips) throws myException{
		if (this instanceof AbstractButton) {
			((AbstractButton)this).setToolTipText(tooltips.CTrans());
		}  else {
			throw new myException("methode _ttt() can only be used at class of type AbstractButton");
		}
		return this;
	}


	/**
	 * haekchen an
	 * @return
	 */
	public RB_cb _check() {
		this.setSelected(true);
		return this;
	}

	
	/**
	 * haekchen aus
	 * @return
	 */
	public RB_cb _un_check() {
		this.setSelected(false);
		return this;
	}

	/**
	 * haekchen an
	 * @return
	 */
	public RB_cb _setSelected() {
		this.setSelected(true);
		return this;
	}

	/**
	 * häkchen an / aus
	 * @author manfred
	 * @date 11.04.2019
	 *
	 * @param bSelect
	 * @return
	 */
	public RB_cb _setSelected(boolean bSelect){
		this.setSelected(bSelect);
		return this;
	}
	
	/**
	 * haekchen aus
	 * @return
	 */
	public RB_cb _setUnSelected() {
		this.setSelected(false);
		return this;
	}


	
	/**
	 * haekchen umschalten
	 * @return
	 */
	public RB_cb _toggle() {
		this.setSelected(!this.isSelected());
		return this;
	}

	
	@Override
	public RB_cb _aaa(XX_ActionAgent action) {
		this.add_oActionAgent(action);
		return this;
	}
	
	
	/**
	 * 2013-12-18: neue methode um meldungen zu manipulieren
	 */
	private XX_MessageManipulator      oMessageManipulator = null;
	@Override
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

	@Override
	public void set_MessageManipulator(XX_MessageManipulator oManipulator) {
		this.oMessageManipulator = oManipulator;
	}

	
	//20170906: unterbrechungen mit benutzerinteraktion einfuegen
	private  E2_Break4PopupController break4PopupController = null;
	
	
	@Override
	public E2_Break4PopupController  getBreak4PopupController() {
		return this.break4PopupController;
	}


	/**
	 * 2018-01-16: martin: break4popup-controller setter dazugefuegt
	 * @param controller
	 * @return
	 */
	@Override
	public void setBreak4PopupController(E2_Break4PopupController controller) {
		this.break4PopupController = controller;
	}
	


	
	@Override
	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() 	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}


	@Override
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP)	{
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}


	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		this.set_Icons(this.isEnabled());
		
		//2014-09-08: E2_MutableStyle ausprogrammieren
		if (this.EXT().get_oE2_Style()!=null) {
			if (this.isEnabled()) {
				this.setStyle(this.EXT().get_oE2_Style());
			} else {
				this.setStyle(this.EXT().get_oE2_Style().get_oStyleDisabled());
			}
		}

	}

	

	

	
	
	
	
	public void set_Icons(boolean bEnabled)	{
		if (bEnabled)	{
			this.setStateIcon(E2_ResourceIcon.get_RI("checkboxoff.gif"));
			this.setSelectedStateIcon(E2_ResourceIcon.get_RI("checkboxon.gif"));
		} else	{
			this.setStateIcon(E2_ResourceIcon.get_RI("checkboxoff_disabled.gif"));
			this.setSelectedStateIcon(E2_ResourceIcon.get_RI("checkboxon_disabled.gif"));
		}
	}
	
	

	
	public ImageReference get_oImg()							{		return oImg;	}
	public void set_oImg(ImageReference imgEnabled)				{		oImg = imgEnabled;	}

	@Override
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	@Override
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	@Override
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent) {
		if (actionAgent != null) {
			this.vActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListener);
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
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	@Override
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront)	{
		if (vActionAgent != null) {
			if (bInFront) {
				this.vActionAgents.addAll(0,vActionAgent);
			} else {
				this.vActionAgents.addAll(vActionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	
	
	
	@Override
	public void remove_oActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vActionAgents.size();i++) {
			if (this.vActionAgents.get(i)==actionAgent) {
				this.vActionAgents.remove(i);
			}
		}
		
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==0) {
			super.removeActionListener(this.oInnerActionListener);
		}
	}

	
	
	
	@Override
	public void remove_AllActionAgents() {
		this.vActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListener);
	}


	
	@Override
	public Vector<XX_ActionAgent> get_vActionAgents() {
		return this.vActionAgents;
	}

	
	
	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	@Override
	public MyE2_MessageVector valid_GlobalValidation() throws myException {
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vGlobalValidators.size();i++){
			XX_ActionValidator oValid = (XX_ActionValidator)this.vGlobalValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(this));
		}
		return vRueck;
	}

	
	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	@Override
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)	{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}
		return vRueck;
	}

	
	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public boolean valid_IDValidationAndShowMessages(Vector<String> vID_Unformated) throws myException	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++){
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}

		bibMSG.add_MESSAGE(vRueck);
		if (vRueck.get_bHasAlarms()){
			return false;
		} else {
			return true;
		}
	}


	
	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cModuleKenner
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator(String cModuleKenner,String cButtonKenner) {
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator(cModuleKenner,cButtonKenner));
	}
	

	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_AUTO(String cButtonKenner) {
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_AUTO(cButtonKenner));
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
	public void add_IDValidator(Vector<XX_ActionValidator> vValid) {
		this.vIDValidators.addAll(vValid);
	}


	public Vector<XX_ActionValidator> get_vGlobalValidators()	{		return vGlobalValidators;	}
	public Vector<XX_ActionValidator> get_vIDValidators()		{		return vIDValidators;	}


	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	@Override
	public void doActionPassiv() {
		bActionEventIsPassive =true;
		this.doAction();
		bActionEventIsPassive =false;
	}


	@Override
	public boolean get_bIsPassivAction() {
		return this.bActionEventIsPassive;
	}


	@Override
	public void set_bPassivAction(boolean bPassiv)	{
		this.bActionEventIsPassive = bPassiv;
	}


	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	@Override
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException {
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}
	

	
	
	
	//2013-01-04: codeblock fuer interalActionAgents
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	@Override
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents()	{
		return this.vInternalActionAgents;
	}

	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent){
		if (actionAgent != null){
			this.vInternalActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1)	{
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	
	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront){
		if (actionAgent != null){
			if (bInFront)	{
				this.vInternalActionAgents.add(0,actionAgent);
			} else {
				this.vInternalActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1)	{
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}
	
	@Override
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vInternalActionAgents.size();i++) {
			if (this.vInternalActionAgents.get(i)==actionAgent)	{
				this.vInternalActionAgents.remove(i);
			}
		}
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==0) {
			super.removeActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	@Override
	public void remove_AllInternalActionAgents() {
		this.vInternalActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListenerInternalAction);
	}
	//2013-01-04 -- ende codeblock internalActionAgents
	
	
	
	
	
	
	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}


	private RB_KF Key = null;

	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}


	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		
		if (dataObject instanceof RB_Dataobject_V1) {
			
			if (dataObject.get_RecORD()==null) {
				this.setSelected(false);;
			} else {
				this.setSelected(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME(),"").equals("Y"));
			}
		
		} else if (dataObject instanceof RB_Dataobject_V2) {
			RB_Dataobject_V2 v2 = (RB_Dataobject_V2) dataObject;
			
			if (v2.rec20()==null||v2.rec20().is_newRecordSet()) {
				this.setSelected(false);;
			} else {
				this.setSelected(v2.rec20().is_yes_db_val(this.rb_KF().get_data_field()));
			}
			
		}
		//20190315: neues dataobject hinzu
		else if (dataObject instanceof RB_Dataobject_V21) {
			Rec21 r = (Rec21)dataObject;
			if (r==null||r.is_newRecordSet()) {
				this.setSelected(false);;
			} else {
				this.setSelected(r.is_yes_db_val(this.rb_KF().get_data_field()));
			}
		}
		//20190315: neues dataobject hinzu
		else if (dataObject instanceof RB_Dataobject_V22) {
			Rec22 r = (Rec22)dataObject;
			if (r==null||r.is_newRecordSet()) {
				this.setSelected(false);;
			} else {
				this.setSelected(r.is_yes_db_val(this.rb_KF().get_data_field()));
			}
		}
		
	}

	
	@Override
	public void mark_Neutral() throws myException {
		this.setForeground(Color.BLACK);
		this.setBackground(null);
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException	{
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.setForeground(new E2_ColorGray(50));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.setAlignment(align);
	}

	

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.isSelected()?"Y":"N";
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.setSelected(valueFormated.toUpperCase().trim().equals("Y"));
	}


	
	public RB_cb _fsa(int iSizeAdd) {
		Font f = this.getFont();
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue()+iSizeAdd,Extent.PT)));
		return this;
	}
	
	
	public RB_cb _fs(int iSize) {
		Font f = this.getFont();
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(iSize,Extent.PT)));
		return this;
	}
	
	
	public RB_cb _b() {
		Font f = this.getFont();
		int style = Font.BOLD;   //entspricht plain
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}
	
	public RB_cb _i() {
		Font f = this.getFont();
		int style = Font.ITALIC;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}

	public RB_cb _ul() {
		Font f = this.getFont();
		int style = Font.UNDERLINE;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		
		this.setFont(new Font(this.getFont().getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}


	public RB_cb _disable() throws myException {
		this.set_bEnabled_For_Edit(false);
		return this;
	}


	public RB_cb _enable() throws myException {
		this.set_bEnabled_For_Edit(true);
		return this;
	}

	
}
