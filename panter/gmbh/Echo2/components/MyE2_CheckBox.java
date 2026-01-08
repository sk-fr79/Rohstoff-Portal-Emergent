package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
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
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_CheckBox extends CheckBox implements  MyE2IF__Component, 
 														E2_IF_Copy, 
 														E2_IF_Handles_ActionAgents, 
 														IF_GetInBorder,
 														MyE2IF__CanGetStampInfo
 														
{

	public static MutableStyle STYLE_NORMAL(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontPlain());
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}
	
	public static MutableStyle STYLE_NORMAL_NO_BORDER(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(0,null,Border.STYLE_NONE)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontPlain());
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}
	
	public static MutableStyle STYLE_NORMAL_NO_BORDER_NO_INSETS(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-20), Border.STYLE_NONE)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(0,0,0,0)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontPlain());
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}
	
	public static MutableStyle STYLE_SMALL(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontPlain(-2));
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}
	
	
	public static MutableStyle STYLE_SMALL_NO_INSETS_NO_BORDER(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-20), Border.STYLE_NONE)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(0,0,0,0)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontPlain(-2));
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}

	
	public static MutableStyle STYLE_SMALL_BOLD(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontBold(-2));
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}
	
	public static MutableStyle STYLE_ITALIC(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontItalic());
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}
	
	public static MutableStyle STYLE_SMALL_ITALIC(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontItalic(-2));
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}

	public static MutableStyle STYLE_BIG_BOLD(){
		MutableStyle ms = new MutableStyle();
		ms.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		ms.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ms.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		ms.setProperty( CheckBox.PROPERTY_FONT, new E2_FontBold(2));
		ms.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		return ms;
	}

	
	public static E2_MutableStyle STYLE_NORMAL_WITH_GREY_DISABLE() {
		E2_MutableStyle oStye = new E2_MutableStyle();
		
		oStye.setProperty( CheckBox.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStye.setProperty( CheckBox.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		oStye.setProperty( CheckBox.PROPERTY_INSETS, new Insets(2,0,0,2)); 
		oStye.setProperty( CheckBox.PROPERTY_FONT, new E2_FontPlain());
		oStye.setProperty( CheckBox.PROPERTY_LINE_WRAP, Boolean.FALSE);
		
		oStye.setProperty( CheckBox.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		
		return oStye;
	}
	
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	private Object 				oText = null;
	private ImageReference 	oImg = null;
	
	/*
	 * ein vector fuer die speicherung der MyE2_ActionAgent - object.
	 * dieser werden dann nach der reihenfolge ihrer zufuegung ausgefuehrt 
	 */
	private Vector<XX_ActionAgent>   vActionAgents = new Vector<XX_ActionAgent>();

	
	// NEU31
	/*
	 * vector fuer validatoren (je einer fuer globale und fuer ID bezogene) 
	 */
	private Vector<XX_ActionValidator>  vGlobalValidators = new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>  vIDValidators = new Vector<XX_ActionValidator>();

	
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();


	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;

	

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
	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() 
	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}


	@Override
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) 
	{
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}


	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		
		this.set_Icons(this.isEnabled());
		
		
		//2014-09-08: E2_MutableStyle ausprogrammieren
		if (this.EXT().get_oE2_Style()!=null) 		{
			if (this.isEnabled())			{
				this.setStyle(this.EXT().get_oE2_Style());
			} else {
				this.setStyle(this.EXT().get_oE2_Style().get_oStyleDisabled());
			}
		}

	}

	
	public MyE2_CheckBox()
	{
		super();

		this.set_Icons(true);
	}

	
	public MyE2_CheckBox(Object cText)
	{
		super();
		this.__setText(cText);
		this.setStyle(MyE2_CheckBox.STYLE_NORMAL());
	}

	public MyE2_CheckBox(Object cText, MyE2_String cToolTipText)
	{
		super();
		this.__setText(cText);
		this.setStyle(MyE2_CheckBox.STYLE_NORMAL());
		this.setToolTipText(cToolTipText.CTrans());
	}

	public MyE2_CheckBox(Object cText, MutableStyle oStyle)
	{
		super();
		this.__setText(cText);
		this.setStyle(oStyle);
	}
	
	
	
	
	
	//2011-01-12: Ein neuer Satz checkboxen mit uebergebenem Schalterzustand
	public MyE2_CheckBox(boolean bIsSelected, boolean bSetDisabledFromBasic)
	{
		super();
		this.set_Icons(true);
		this.setSelected(bIsSelected);
		this.EXT().set_bDisabledFromBasic(bSetDisabledFromBasic);
		try 
		{
			this.set_bEnabled_For_Edit(!bSetDisabledFromBasic);
		} 
		catch (myException e) 
		{
			e.printStackTrace();
		}
		
	}

	
	public MyE2_CheckBox(Object cText, boolean bIsSelected, boolean bSetDisabledFromBasic)
	{
		super();
		this.__setText(cText);
		this.setStyle(MyE2_CheckBox.STYLE_NORMAL());
		this.setSelected(bIsSelected);
		this.EXT().set_bDisabledFromBasic(bSetDisabledFromBasic);
		try 
		{
			this.set_bEnabled_For_Edit(!bSetDisabledFromBasic);
		} 
		catch (myException e) 
		{
			e.printStackTrace();
		}
	}

	public MyE2_CheckBox(Object cText, MyE2_String cToolTipText, boolean bIsSelected, boolean bSetDisabledFromBasic)
	{
		super();
		this.__setText(cText);
		this.setStyle(MyE2_CheckBox.STYLE_NORMAL());
		this.setToolTipText(cToolTipText.CTrans());
		this.setSelected(bIsSelected);
		this.EXT().set_bDisabledFromBasic(bSetDisabledFromBasic);
		try 
		{
			this.set_bEnabled_For_Edit(!bSetDisabledFromBasic);
		} 
		catch (myException e) 
		{
			e.printStackTrace();
		}
	}

	public MyE2_CheckBox(Object cText, MutableStyle oStyle, boolean bIsSelected, boolean bSetDisabledFromBasic)
	{
		super();
		this.__setText(cText);
		this.setStyle(oStyle);
		this.setSelected(bIsSelected);
		this.EXT().set_bDisabledFromBasic(bSetDisabledFromBasic);
		try 
		{
			this.set_bEnabled_For_Edit(!bSetDisabledFromBasic);
		} 
		catch (myException e) 
		{
			e.printStackTrace();
		}
	}
	//2011-01-12: <END> Ein neuer Satz checkboxen mit uebergebenem Schalterzustand

	
	
	
	
	
	
	
	public void set_Icons(boolean bEnabled)
	{
		if (bEnabled)
		{
			this.setStateIcon(E2_ResourceIcon.get_RI("checkboxoff.gif"));
			this.setSelectedStateIcon(E2_ResourceIcon.get_RI("checkboxon.gif"));
		}
		else
		{
			this.setStateIcon(E2_ResourceIcon.get_RI("checkboxoff_disabled.gif"));
			this.setSelectedStateIcon(E2_ResourceIcon.get_RI("checkboxon_disabled.gif"));
		}
	}
	
	public void __setText(Object cText)
	{
		this.oText = cText;
		if (cText == null)
		{
			this.setText(null);
		}
		else if (cText instanceof String)
		{
			this.setText((String)cText);
		} 
		else if (cText instanceof MyString)
		{
			this.setText(((MyString)cText).CTrans());
		}
		else
		{
			this.oText = null;
			this.setText("@@@ERROR@@@");
		}
	}


	

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_CheckBox oButton = new MyE2_CheckBox();
		
		oButton.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oButton));
		oButton.__setText(this.get_oText());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}

		oButton.setStyle(this.getStyle());
		
		//NEU_09
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oButton.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		//2013-01-04: interne actionAgents
		Vector<XX_ActionAgent> vInternalAgents = this.get_vInternalActionAgents();
		for (int i=0;i<vInternalAgents.size();i++)
			oButton.add_oInternalActionAgent((XX_ActionAgent)vInternalAgents.get(i));

		for (int i=0;i<this.vGlobalValidators.size();i++)
			oButton.add_GlobalValidator((XX_ActionValidator)this.vGlobalValidators.get(i));
		//NEU_09

		oButton.setToolTipText(this.getToolTipText());
		
		return oButton;
	}
	
	
	
	public Object get_oText()									{		return oText;	}
	
	public ImageReference get_oImg()							{		return oImg;	}
	public void set_oImg(ImageReference imgEnabled)			{		oImg = imgEnabled;	}

	@Override
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	@Override
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	@Override
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent)
	{
		if (actionAgent != null)
		{
			this.vActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	@Override
	public void add_oActionAgent(XX_ActionAgent actionAgent, boolean bInFront)
	{
		if (actionAgent != null)
		{
			if (bInFront)
			{
				this.vActionAgents.add(0,actionAgent);
			}
			else
			{
				this.vActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	@Override
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront)
	{
		if (vActionAgent != null)
		{
			if (bInFront)
			{
				this.vActionAgents.addAll(0,vActionAgent);
			}
			else
			{
				this.vActionAgents.addAll(vActionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	
	
	
	@Override
	public void remove_oActionAgent(XX_ActionAgent actionAgent)
	{
		for (int i=0;i<this.vActionAgents.size();i++)
		{
			if (this.vActionAgents.get(i)==actionAgent)
			{
				this.vActionAgents.remove(i);
			}
		}
		
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vActionAgents.size()==0)
		{
			super.removeActionListener(this.oInnerActionListener);
		}
	}

	
	
	
	@Override
	public void remove_AllActionAgents() 
	{
		this.vActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListener);
	}


	
	@Override
	public Vector<XX_ActionAgent> get_vActionAgents()
	{
		return this.vActionAgents;
	}

	
	
	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	@Override
	public MyE2_MessageVector valid_GlobalValidation() throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vGlobalValidators.size();i++)
		{
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
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)
		{
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
	public boolean valid_IDValidationAndShowMessages(Vector<String> vID_Unformated) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		for (int i=0;i<this.vIDValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}

		bibMSG.add_MESSAGE(vRueck);
		if (vRueck.get_bHasAlarms())
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	
	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cModuleKenner
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator(String cModuleKenner,String cButtonKenner)
	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator(cModuleKenner,cButtonKenner));
	}
	

	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_AUTO(String cButtonKenner)
	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_AUTO(cButtonKenner));
	}
	

	
	
	@Override
	public void add_GlobalValidator(XX_ActionValidator oValid)
	{
		this.vGlobalValidators.add(oValid);
	}
	@Override
	public void add_IDValidator(XX_ActionValidator oValid)
	{
		this.vIDValidators.add(oValid);
	}

	@Override
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid)
	{
		this.vGlobalValidators.addAll(vValid);
	}

	@Override
	public void add_IDValidator(Vector<XX_ActionValidator> vValid)
	{
		this.vIDValidators.addAll(vValid);
	}


	public Vector<XX_ActionValidator> get_vGlobalValidators()	{		return vGlobalValidators;	}
	public Vector<XX_ActionValidator> get_vIDValidators()		{		return vIDValidators;	}


	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	@Override
	public void doActionPassiv() 
	{
		bActionEventIsPassive =true;
		this.doAction();
		bActionEventIsPassive =false;
	}


	@Override
	public boolean get_bIsPassivAction() 
	{
		return this.bActionEventIsPassive;
	}


	@Override
	public void set_bPassivAction(boolean bPassiv) 
	{
		this.bActionEventIsPassive = bPassiv;
	}

	@Override
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt,	Insets oInsets) throws myException
	{
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		MyE2_Grid_InLIST  oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this);
		
		return oGridRueck;
	}


	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	@Override
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException 
	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}
	

	
	
	
	
	
	
	
	//2013-01-04: codeblock fuer interalActionAgents
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	@Override
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents()
	{
		return this.vInternalActionAgents;
	}

	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent)
	{
		if (actionAgent != null)
		{
			this.vInternalActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	
	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)
	{
		if (actionAgent != null)
		{
			if (bInFront)
			{
				this.vInternalActionAgents.add(0,actionAgent);
			}
			else
			{
				this.vInternalActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1)
		{
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}
	
	@Override
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent)
	{
		for (int i=0;i<this.vInternalActionAgents.size();i++)
		{
			if (this.vInternalActionAgents.get(i)==actionAgent)
			{
				this.vInternalActionAgents.remove(i);
			}
		}
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==0)
		{
			super.removeActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	@Override
	public void remove_AllInternalActionAgents() 
	{
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

	@Override
	public String get_STAMP_INFO() throws myException {
		return this.isSelected()?"Y":"N";
	}

	
	
}
