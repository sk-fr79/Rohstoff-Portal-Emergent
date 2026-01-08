package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.ResourceImageReference;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.event.ActionListener;
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
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_PROGRAMM_WIDE;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.BasicInterfaces.IF_Align;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.VALIDATOR_KEY;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MyE2_Button extends Button implements 	MyE2IF__Component, 
													E2_IF_Copy, 
													E2_IF_Handles_ActionAgents, 
													Comparable<MyE2_Button>,
													IF_GetInBorder,
													MyE2IF__CanGetStampInfo,
													IF_Align<MyE2_Button>
{
	
	private Object 					oText = null;
	private ImageReference 			oImgEnabled = null;
	private ImageReference 			oImgDisabled = null;
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
	/*
	 * ein vector fuer die speicherung der MyE2_ActionAgent - object.
	 * dieser werden dann nach der reihenfolge ihrer zufuegung ausgefuehrt 
	 */
	private Vector<XX_ActionAgent> 	vActionAgents = new Vector<XX_ActionAgent>();
	private Vector<ActionListener>	vExternalActionListeners = new Vector<ActionListener>();

	
	/*
	 * vector fuer validatoren (je einer fuer globale und fuer ID bezogene) 
	 */
	private Vector<XX_ActionValidator>	vGlobalValidators = 	new Vector<XX_ActionValidator>();
	private Vector<XX_ActionValidator>	vIDValidators = 		new Vector<XX_ActionValidator>();
	

	private Color					oColorBackground = new E2_ColorBase(-10);
	private Color					oColorBackgroundDisabled = new E2_ColorBase(-10);
	private Color					oColorForeground = Color.BLACK;
	private Color					oColorForegroundDisabled = Color.DARKGRAY;
	
	/*
	 * innerer action-listener, der benutzt wird, um die ActionAgents, 
	 * die dem button zugewiesen wurden, abzuarbeiten
	 */
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();
			
	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;
	

	/**
	 * 2013-12-18: neue methode um meldungen zu manipulieren
	 */
	private XX_MessageManipulator      oMessageManipulator = null;
	public XX_MessageManipulator   get_MessageManipulator() {
		return this.oMessageManipulator;
	}

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
	

	
	public MyE2_Button()
	{
		super();
	}

	
	
	/**
	 * 
	 * @param oImg
	 * @param bAutoDisabled
	 * versucht automatisch eine graue version anhand
	 * der E2_ResourcIcon zu finden und laedt diese zu
	 * 
	 */
	public MyE2_Button(ImageReference oImg, boolean bAutoDisabled)
	{
		super();
		ImageReference oImage2 = oImg;

		if (bAutoDisabled)
			if (oImg instanceof E2_ResourceIcon)
			{
				E2_ResourceIcon oRes = (E2_ResourceIcon)oImg;
				oImage2 = oRes.get_GrayVersion();
			}
	
		this.__setImages(oImg,oImage2);
		this.setStyle(MyE2_Button.StyleImageButton());
	}

	
	
	public MyE2_Button(ImageReference oImg, ImageReference oimgDisabled)
	{
		super();

		this.__setImages(oImg,oimgDisabled);
		this.setStyle(MyE2_Button.StyleImageButton());
		
	}

	public MyE2_Button(ImageReference oImg)
	{
		super();
		this.__setImages(oImg,oImg);
		this.setStyle(MyE2_Button.StyleImageButton());
	}

	public MyE2_Button(Object cText,ImageReference  oImg, ImageReference oimgDisabled)
	{
		super();
		this.set_Text(cText);
		this.__setImages(oImg,oimgDisabled);
		this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
	}

	public MyE2_Button(String cText)
	{
		super();
		this.set_Text(cText);
		this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
	}

	public MyE2_Button(MyString cText)
	{
		super();
		this.set_Text(cText);
		this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
	}

	
	
	public MyE2_Button(MyString cText, Font font)
	{
		super();
		this.set_Text(cText);
		this.setStyle(MyE2_Button.StyleTextButton(
						this.oColorBackground,
						this.oColorBackgroundDisabled,
						this.oColorForeground, 
						this.oColorForegroundDisabled,
						font));
	}


	
	public MyE2_Button(String cText, E2_MutableStyle oStyle)
	{
		super();
		this.set_Text(cText);
		this.setStyle(oStyle);
	}

	public MyE2_Button(MyString cText, E2_MutableStyle oStyle)
	{
		super();
		this.set_Text(cText);
		this.setStyle(oStyle);
	}

	
	
	
	//2011-08-16: neue konstruktoren mit tooltips und actionagent
	public MyE2_Button(ImageReference oImg, boolean bAutoDisabled, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		ImageReference oImage2 = oImg;

		if (bAutoDisabled)
			if (oImg instanceof E2_ResourceIcon)
			{
				E2_ResourceIcon oRes = (E2_ResourceIcon)oImg;
				oImage2 = oRes.get_GrayVersion();
			}
	
		this.__setImages(oImg,oImage2);
		this.setStyle(MyE2_Button.StyleImageButton());
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
		
	}

	
	
	public MyE2_Button(ImageReference oImg, ImageReference oimgDisabled, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();

		this.__setImages(oImg,oimgDisabled);
		this.setStyle(MyE2_Button.StyleImageButton());
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
		
	}

	public MyE2_Button(ImageReference oImg, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.__setImages(oImg,oImg);
		this.setStyle(MyE2_Button.StyleImageButton());
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	public MyE2_Button(Object cText,ImageReference  oImg, ImageReference oimgDisabled, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.__setImages(oImg,oimgDisabled);
		this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	public MyE2_Button(String cText, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	public MyE2_Button(MyString cText, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	
	
	public MyE2_Button(String cText, MyString cToolTips, XX_ActionAgent  oAgent, boolean bCentered)
	{
		super();
		this.set_Text(cText);
		if (bCentered)
		{
			this.setStyle(MyE2_Button.StyleTextButtonCentered(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		}
		else
		{
			this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		}
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	public MyE2_Button(MyString cText, MyString cToolTips, XX_ActionAgent  oAgent, boolean bCentered)
	{
		super();
		this.set_Text(cText);
		if (bCentered)
		{
			this.setStyle(MyE2_Button.StyleTextButtonCentered(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		}
		else
		{
			this.setStyle(MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground, this.oColorForegroundDisabled));
		}
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}


	
	public MyE2_Button(String cText, E2_MutableStyle oStyle, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.setStyle(oStyle);
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}
	
	
	
	public MyE2_Button(String cText, MutableStyle oStyle, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.setStyle(oStyle);
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	
	
	
	public MyE2_Button(MyString cText, E2_MutableStyle oStyle, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.setStyle(oStyle);
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	
	public MyE2_Button(MyString cText, MutableStyle oStyle, MyString cToolTips, XX_ActionAgent  oAgent)
	{
		super();
		this.set_Text(cText);
		this.setStyle(oStyle);
		
		if (cToolTips != null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
		if (oAgent != null)
		{
			this.add_oActionAgent(oAgent);
		}
	}

	
//--------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setStyle(Style oStyle)
	{
		super.setStyle(oStyle);
		if (oStyle instanceof E2_MutableStyle)
		{
			this.EXT().set_oE2_Style((E2_MutableStyle)oStyle);
		}
	}
	
	
	
	public void set_Text(Object cText)
	{
		this.oText = cText;
		if (cText instanceof String)
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

	public void __setImages(ImageReference  oImg, ImageReference oimgDisabled)
	{
		this.setIcon(oImg);
		this.oImgEnabled = oImg;
		this.oImgDisabled = oimgDisabled;
		this.setInsets(new Insets(2));
	}
	
	
	//aenderung 20101020
	public void __setImages(ImageReference  oImg, boolean bAutomaticDisable)
	{
		ImageReference oImage2 = oImg;

		if (bAutomaticDisable)
			if (oImg instanceof E2_ResourceIcon)
			{
				E2_ResourceIcon oRes = (E2_ResourceIcon)oImg;
				oImage2 = oRes.get_GrayVersion();
			}
	
		this.__setImages(oImg,oImage2);
	}

	
	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() 
	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}

	
	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) 
	{
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}

			
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled());
		
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
		{
			if (enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled())
			{
				this.setIcon(this.oImgEnabled);
			}
			else
			{
				this.setIcon(this.oImgDisabled);
			}
		}

		if (this.EXT().get_oE2_Style()!=null)
		{
			if (this.isEnabled())
			{
				this.setStyle(this.EXT().get_oE2_Style());
			}
			else
			{
				this.setStyle(this.EXT().get_oE2_Style().get_oStyleDisabled());
			}
		}
	}

	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Button oButton = new MyE2_Button();

		oButton.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oButton));
		if (this.get_oText()!=null) oButton.set_Text(this.get_oText());
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
		oButton.setStyle(this.getStyle());
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oButton.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
	
		//2013-01-04: interne actionAgents
		Vector<XX_ActionAgent> vInternalAgents = this.get_vInternalActionAgents();
		for (int i=0;i<vInternalAgents.size();i++)
			oButton.add_oInternalActionAgent((XX_ActionAgent)vInternalAgents.get(i));

		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oButton.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.vGlobalValidators.size();i++)
			oButton.add_GlobalValidator((XX_ActionValidator)this.vGlobalValidators.get(i));
				
		
		for (int i=0;i<this.vIDValidators.size();i++)
			oButton.add_IDValidator((XX_ActionValidator)this.vIDValidators.get(i));
		
		oButton.setFont(this.getFont());
		oButton.setAlignment(this.getAlignment());

		oButton.set_bMustSet_MILLISECONDSTAMP_TO_Session(this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER);
		
		return oButton;
	}
	
	
	public void add_GlobalValidator(XX_ActionValidator oValid)
	{
		this.vGlobalValidators.add(oValid);
	}
	
	
	/**
	 * 2013-05-16: martin panter
	 * @param oValid
	 */
	public void add_GlobalValidator_removeOthers(XX_ActionValidator oValid)
	{
		this.vGlobalValidators.removeAllElements();
		this.vGlobalValidators.add(oValid);
	}
	
	
	
	
	//2012-10-15: falls nicht der liste
	public void add_GlobalValidator_only_if_ClassNotInList(XX_ActionValidator oValid)
	{
		boolean bValidatorIstSchonDa = false;
		
		if (oValid!=null)
		{
			for (XX_ActionValidator oValidVorhanden: this.vGlobalValidators)
			{
				if (oValidVorhanden.getClass().getName().equals(oValid.getClass().getName()))
				{
					bValidatorIstSchonDa=true;
				}
			}
			if (!bValidatorIstSchonDa)
			{
				this.vGlobalValidators.add(oValid);
			}
		}
	}


	public void add_IDValidator(XX_ActionValidator oValid)
	{
		this.vIDValidators.add(oValid);
	}
	
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid)
	{
		this.vGlobalValidators.addAll(vValid);
	}

	public void add_IDValidator(Vector<XX_ActionValidator> vValid)
	{
		this.vIDValidators.addAll(vValid);
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
	

	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_PROGRAMM_WIDE(String cButtonKenner)
	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_PROGRAMM_WIDE(cButtonKenner));
	}
	
	

	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
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

	
	
	public MyE2_MessageVector  valid_SingleGlobalValidator(XX_ActionValidator oValidator) throws myException
	{
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		vRueck.add_MESSAGE(oValidator.isValid(this));
		return vRueck;
		
	}
	

	
	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
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


	
	
	public Object get_oText()									{		return oText;			}


	public ImageReference get_oImgDisabled()					{		return oImgDisabled;	}
	public ImageReference get_oImgEnabled()						{		return oImgEnabled;		}


	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;		}
	public MyE2EXT__Component EXT()								{		return this.oEXT;		}

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

	
	
	
//	/*
//	 * 2012-02-07: weitere methode hinzugefuegt, martin 
//	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
//	 */
//	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront)
//	{
//		boolean bHadActionAgentsBefore = (this.vActionAgents.size()>0);   //2012-10-15: fehlerkorrektur
//
//		
//		if (vActionAgent != null)
//		{
//			if (bInFront)
//			{
//				this.vActionAgents.addAll(0,vActionAgent);
//			}
//			else
//			{
//				this.vActionAgents.addAll(vActionAgent);
//			}
//		}
//		
//		/*
//		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
//		 * dieser handelt alle actionAgents ab
//		 */
//		if (!bHadActionAgentsBefore)
//		{
//			super.addActionListener(this.oInnerActionListener);
//		}
//	}

	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * 2015-11-25: geaendert: wenn leer oder null, dann darf kein listener zugefuegt werden
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront)
	{
		boolean bHadActionAgentsBefore = (this.vActionAgents.size()>0);   //2012-10-15: fehlerkorrektur

		if (vActionAgent==null || vActionAgent.size()==0) {
			return;   //nichts tun
		}
		
		if (bInFront) {
			this.vActionAgents.addAll(0,vActionAgent);
		} else {
			this.vActionAgents.addAll(vActionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (!bHadActionAgentsBefore)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}


	
	
	/*
	 * 2012-10-15: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent_only_if_ClassNotInList(XX_ActionAgent ActionAgent, boolean bInFront)
	{
		if (vActionAgents != null)
		{
			boolean bIstSchonDa = false;
			for (XX_ActionAgent oAgentVorhanden:this.vActionAgents)
			{
				if (oAgentVorhanden.getClass().getName().equals(ActionAgent.getClass().getName()))
				{
					bIstSchonDa = true;
				}
			}
			if (!bIstSchonDa)
			{
				if (bInFront)
				{
					this.vActionAgents.add(0,ActionAgent);
				}
				else
				{
					this.vActionAgents.add(ActionAgent);
				}
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
	 * 2012-10-15: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	public void add_oActionAgent_only_if_ClassNotInList(Vector<XX_ActionAgent> ActionAgents, boolean bInFront)
	{
		boolean bHadActionAgentsBefore = (this.vActionAgents.size()>0);
		
		if (vActionAgents != null)
		{
			for (XX_ActionAgent oAgent: ActionAgents)
			{
				boolean bIstSchonDa = false;
				for (XX_ActionAgent oAgentVorhanden:this.vActionAgents)
				{
					if (oAgentVorhanden.getClass().getName().equals(oAgent.getClass().getName()))
					{
						bIstSchonDa = true;
					}
				}
				if (!bIstSchonDa)
				{
					if (bInFront)
					{
						this.vActionAgents.add(0,oAgent);
					}
					else
					{
						this.vActionAgents.add(oAgent);
					}
				}
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (!bHadActionAgentsBefore)
		{
			super.addActionListener(this.oInnerActionListener);
		}
	}

	
	
	
	
	
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

	
	public void remove_AllActionAgents() 
	{
		this.vActionAgents.removeAllElements();
		super.removeActionListener(this.oInnerActionListener);
	}

	
	
	
	public Vector<XX_ActionAgent> get_vActionAgents()
	{
		return this.vActionAgents;
	}


	public Vector<ActionListener> get_vExternalActionListeners()
	{
		return vExternalActionListeners;
	}


	public void addActionListener(ActionListener oActionListener)
	{
		this.vExternalActionListeners.add(oActionListener);
		super.addActionListener(oActionListener);
	}
	
	public void removeActionListener(ActionListener oActionListener)
	{
		for (int i=0;i<this.vExternalActionListeners.size();i++)
		{
			if (this.vExternalActionListeners.get(i) == oActionListener)
			{
				this.vExternalActionListeners.remove(i);
			}
		}
		super.removeActionListener(oActionListener);
	}


	public void show_InputStatus(boolean bInputIsOK)
	{
	}


	public Vector<XX_ActionValidator> get_vGlobalValidators()	{		return vGlobalValidators;	}
	public Vector<XX_ActionValidator> get_vIDValidators()		{		return vIDValidators;	}
	


	public void set_COLORS(	Color oColorBackground, 
							Color oColorForeGround,
							Color oColorBackgroundDisabled, 
							Color oColorForeGroundDiabled)
	{
		this.oColorBackground=			oColorBackground;
		this.oColorBackgroundDisabled = oColorBackgroundDisabled;
		this.oColorForeground= 			oColorForeGround;
		this.oColorForegroundDisabled = oColorForeGroundDiabled;
		
		if (!bibALL.isEmpty(this.getText()))
		{

			E2_MutableStyle oStyle = MyE2_Button.StyleTextButton(this.oColorBackground,this.oColorBackgroundDisabled,this.oColorForeground,this.oColorForegroundDisabled);

			
			
			if (this.isEnabled())
				this.setStyle(oStyle);
			else
				this.setStyle(oStyle.get_oStyleDisabled());
			
		}

	}
	
	
//	public static E2_MutableStyle StyleImageButton()
//	{
//		E2_MutableStyle ostyleImageButton = new E2_MutableStyle();
//		
//
//		//2012-02-02: buttons geaendert, damit beim Druck kein huepfen des layouts
//		// ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-30), Border.STYLE_NONE));
//		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));
//		
//		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
//		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
//		
//		return ostyleImageButton;
//	}
	
	
	//2012-02-02: buttons geaendert, damit beim Druck kein huepfen des layouts
	public static E2_MutableStyle StyleImageButton()
	{
		E2_MutableStyle ostyleImageButton = new E2_MutableStyle();
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));

		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, null);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, null);
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BACKGROUND, Color.WHITE);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, null);
		
		
		
		return ostyleImageButton;
	}

	

	
	
	public static E2_MutableStyle StyleImageButtonBorderLess()
	{
		E2_MutableStyle ostyleImageButton = new E2_MutableStyle();
		
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, null);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER,null);
		return ostyleImageButton;
	}

	
	
	

	public static E2_MutableStyle StyleImageButtonNoBorders()
	{
		E2_MutableStyle ostyleImageButton = new E2_MutableStyle();
		
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-30), Border.STYLE_NONE));
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_NONE));
		
		return ostyleImageButton;
	}

	
	//2012-08-28: neuer style
	public static E2_MutableStyle StyleImageButtonButtonsAsSmallAsPossible()
	{
		E2_MutableStyle ostyleImageButton = new E2_MutableStyle();
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, null);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(false));
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, null);
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		
		return ostyleImageButton;
	}


	
	
	public static E2_MutableStyle StyleTextButton()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColorBackground = new E2_ColorBase(-10);
		Color					oColorBackgroundDisabled = new E2_ColorBase(-10);
		Color					oColorForeground = Color.BLACK;
		Color					oColorForegroundDisabled = Color.DARKGRAY;

		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisabled); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	
	/**
	 * 
	 * @param font
	 * @param p_border
	 * @param colText
	 * @param colBack
	 * @param lineWrap
	 * @return
	 */
	public static E2_MutableStyle StyleTextButton(Font font, Border p_border, Color colText, Color colBack,boolean lineWrap) {
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColorBackground = (colBack==null?new E2_ColorBase(-10):colBack);
		Color					oColorBackgroundDisabled = new E2_ColorBase(-10);
		Color					oColorForeground = (colText==null?Color.BLACK:colText);
		Color					oColorForegroundDisabled = Color.DARKGRAY;
		
		Border 					border = (p_border==null?new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID):p_border);
		Border 					border_pressed = new Border(border.getSize(), Color.WHITE, border.getStyle());
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisabled); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, border_pressed);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, border);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT,font==null?new E2_FontPlain():font);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(lineWrap));
		
		return ostyleTextButton;
	}


	
	public static E2_MutableStyle StyleTextButton(boolean bLineWrap)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColorBackground = new E2_ColorBase(-10);
		Color					oColorBackgroundDisabled = new E2_ColorBase(-10);
		Color					oColorForeground = Color.BLACK;
		Color					oColorForegroundDisabled = Color.DARKGRAY;

		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisabled); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeground, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(bLineWrap));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleTextButton(Color oColorBackground,Color oColorBackgroundDisable, Color oColorForeGround, Color oColorForegroundDisabled)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisable); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeGround, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleTextButton(	Color oColorBackground,
													Color oColorBackgroundDisable, 
													Color oColorForeGround, 
													Color oColorForegroundDisabled,
													Font  font)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisable); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeGround, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, font);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleTextButtonCentered(Color oColorBackground,Color oColorBackgroundDisable, Color oColorForeGround, Color oColorForegroundDisabled)
	{
		return MyE2_Button.StyleTextButtonCentered(oColorBackground,oColorBackgroundDisable, oColorForeGround, oColorForegroundDisabled, null);
	}


	
	/**
	 * 
	 * @param oColorBackground
	 * @param oColorBackgroundDisable
	 * @param oColorForeGround
	 * @param oColorForegroundDisabled
	 * @param oFont
	 * @return
	 */
	public static E2_MutableStyle StyleTextButtonCentered(
									Color oColorBackground,
									Color oColorBackgroundDisable, 
									Color oColorForeGround, 
									Color oColorForegroundDisabled,
									Font  oFont)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisable); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeGround, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, oFont==null?new E2_FontPlain():oFont);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		return ostyleTextButton;
	}

	

	
	public static E2_MutableStyle StyleTextButtonWithClickForeground( boolean bLineWrap)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));

		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, null); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BACKGROUND, null); 
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, null);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, null);
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_FOREGROUND, Color.DARKGRAY);
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(0,0,0,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(bLineWrap));
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleTextButton(	Color oColorBackground,	
													Color oColorBackgroundDisable, 
													Color oColorForeGround, 
													Color oColorForegroundDisabled, 
													boolean bLineWrap)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisable); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeGround, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(bLineWrap));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleTextButton(	Color oColorBackground,	
													Color oColorBackgroundDisable, 
													Color oColorForeGround, 
													Color oColorForegroundDisabled, 
													Alignment  oAlign,
													boolean bLineWrap)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColorBackground,oColorBackgroundDisable); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, oColorForeGround, oColorForegroundDisabled);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(bLineWrap));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,oAlign);
		
		return ostyleTextButton;
	}


	
	public static E2_MutableStyle StyleImageButtonCenteredWithDDBorder()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(4,4,4,4)); 
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		return ostyleTextButton;
	}


	public static E2_MutableStyle StyleImageButtonCenteredWithBlackBorder()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, Color.BLACK, Border.STYLE_SOLID), new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(4,4,4,4)); 
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		return ostyleTextButton;
	}

	public static E2_MutableStyle StyleButtonCenteredWithDDarkBorder()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(1,1,1,1)); 
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleButtonCenteredWithDDarkBorder(Font font)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, font);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(1,1,1,1)); 
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleButtonWithDDarkBorder(Alignment align)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(1,1,1,1)); 
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,align);
		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleTextButtonSTD(Font oFont)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, new E2_ColorBase(-10), new E2_ColorBase(-10)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, oFont);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}



	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL(boolean bLineWrap)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(bLineWrap));
		
		return ostyleTextButton;
	}


	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL(Font oFont)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, oFont);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_WithBorder()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_WithBorder(Font oFont)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, oFont);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_WithBorder_Center(Font oFont)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, oFont);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));
		
		return ostyleTextButton;
	}


	
	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		return ostyleTextButton;
	}

	
	
	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch_black_and_grey()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		return ostyleTextButton;
	}

	
	
	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(Font font)
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, font);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle StyleTextButton_LOOK_like_LABEL_BoldItalic()
	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(0, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-40), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, new E2_ColorGray(170));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontBoldItalic());
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(false));
		
		return ostyleTextButton;
	}



	
	
	

	
	
	public static E2_MutableStyle Style_Button_DB()
	{
		E2_MutableStyle oStyle_Button_DB = new E2_MutableStyle();

		
		oStyle_Button_DB.setProperty( AbstractButton.PROPERTY_BACKGROUND, new E2_ColorBase(-10)); 
		oStyle_Button_DB.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_GROOVE)); 
		oStyle_Button_DB.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		oStyle_Button_DB.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		oStyle_Button_DB.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
	
		return oStyle_Button_DB;
	}


	public static E2_MutableStyle Style_Button_BIG_BLACK_ON_GREEN()
	{
		E2_MutableStyle oStyle_Button_BIG_GREEN_ON_WHITE = new E2_MutableStyle();

		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_BACKGROUND, Color.GREEN, Color.LIGHTGRAY); 
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, Color.BLACK, Border.STYLE_GROOVE)); 
		
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_1_0_1_0);
		
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE); 
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		oStyle_Button_BIG_GREEN_ON_WHITE.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontBold(4));
	
		return oStyle_Button_BIG_GREEN_ON_WHITE;
	}


	public static E2_MutableStyle Style_SortButtonStandard()
	{
		return MyE2_Button.StyleTextButton(new E2_ColorBase(-10),new E2_ColorBase(-10),Color.BLACK,  Color.DARKGRAY);
	}
	
	
	
	public ActionListener get_oInnerActionListener()
	{
		return oInnerActionListener;
	}


	
	
	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	public void doActionPassiv() 
	{
		//boolean bTemp = bActionEventIsPassive;
		bActionEventIsPassive =true;
		this.doAction();
		bActionEventIsPassive =false;
	}


	public boolean get_bIsPassivAction() 
	{
		return this.bActionEventIsPassive;
	}


	public void set_bPassivAction(boolean bPassiv) 
	{
		this.bActionEventIsPassive = bPassiv;
	}


	@Override
	public int compareTo(MyE2_Button arg0)
	{
		return this.getText().compareTo(arg0.getText());
	}



	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException 
	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
	}



	//2011-02-10: Grid in Rahmen
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt, Insets oInsets)
	{
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		MyE2_Grid oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this);
		
		oGridRueck.setVisible(this.isVisible());
		
		return oGridRueck;
	}
	
	

	
	
	
	//2013-01-04: codeblock fuer interalActionAgents
	private E2_InnerActionListenerForInternalActionAgents  oInnerActionListenerInternalAction = new E2_InnerActionListenerForInternalActionAgents();
	private Vector<XX_ActionAgent>  vInternalActionAgents = new Vector<XX_ActionAgent>();
	public Vector<XX_ActionAgent> 	get_vInternalActionAgents()
	{
		return this.vInternalActionAgents;
	}

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
		return S.NN(this.getText());
	}

	public MyE2_Button _bordBlack() {
		this.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
		return this;
	}
	
	public MyE2_Button _bordDDark() {
		this.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
		return this;
	}
	
	public MyE2_Button _bordDDDark() {
		this.setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		return this;
	}

	public MyE2_Button _bord(Border bord) {
		this.setBorder(bord);
		return this;
	}

	
	public MyE2_Button _backGreen() {
		this.setBackground(Color.GREEN);
		return this;
	}
	
	
	public MyE2_Button _backColorBase() {
		this.setBackground(new E2_ColorBase());
		return this;
	}

	
	public MyE2_Button _backDDark() {
		this.setBackground(new E2_ColorDDDark());
		return this;
	}
	
	public MyE2_Button _font(Font f) {
		this.setFont(f);
		return this;
	}

	public MyE2_Button _center() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
		return this;
	}

	public MyE2_Button _left() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.DEFAULT));
		return this;
	}
	public MyE2_Button _right() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.DEFAULT));
		return this;
	}
	
	public MyE2_Button _lw() {
		this.setLineWrap(true);
		return this;
	}
	
	public MyE2_Button _insets(Insets in) {
		this.setInsets(in);
		return this;
	}

	
	/**
	 * 
	 * @param v  (global validator)
	 * @return
	 */
	public MyE2_Button _v(XX_ActionValidator v) {
		this.add_GlobalValidator(v);
		return this;
	}
	
	
	/**
	 * 
	 * @param v  (Global auth-validator)
	 * @return
	 */
	public MyE2_Button _valid_key(VALIDATOR_KEY key) {
		this.add_GlobalValidator(key.get_authValidator());
		return this;
	}
	

	/**
	 * @param tooltips
	 */
	public MyE2_Button _ttt(MyE2_String tooltips) throws myException{
		this.setToolTipText(tooltips.CTrans());
		return this;
	}

	
	
	/// --------------------------------------------------------------------------------
	
	
	
	/**
	 * 
	 * @param text (no translation)
	 * @return
	 */
	public MyE2_Button _t(String text) {
		this.setText(text);
		return this;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public MyE2_Button _t(MyE2_String text) {
		this.setText(text.CTrans());
		return this;
	}
	
	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public MyE2_Button _tr(String text) {
		this.setText(new MyE2_String(text).CTrans());
		return this;
	}

	
	/**
	 * 
	 * @param f  (font)
	 * @return
	 */
	public MyE2_Button _f(Font f) {
		this.setFont(f);
		return this;
	}
	

	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public MyE2_Button _fsa(int iSizeAdd) {
		Font f = this.getFont();
		if (f==null) {
			Style st = this.getStyle();
			if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
				f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
			} else {
				f=new E2_FontPlain();
			}
		}
		
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue()+iSizeAdd,Extent.PT)));
		return this;
	}
	
	
	/**
	 * set fontsize
	 * @param iSize
	 * @return
	 */
	public MyE2_Button _fs(int iSize) {
		Font f = this.getFont();
		if (f==null) {
			Style st = this.getStyle();
			if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
				f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
			} else {
				f=new E2_FontPlain();
			}
		}
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(f.getTypeface(),style, new Extent(iSize,Extent.PT)));
		return this;
	}
	
	
	/**
	 * set font bold
	 * @return
	 */
	public MyE2_Button _b() {
		Font f = this.getFont();
		if (f==null) {
			Style st = this.getStyle();
			if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
				f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
			} else {
				f=new E2_FontPlain();
			}
		}
		int style = Font.BOLD;   //entspricht plain
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}
	
	/**
	 * set font italic
	 * @return
	 */
	public MyE2_Button _i() {
		Font f = this.getFont();
		if (f==null) {
			Style st = this.getStyle();
			if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
				f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
			} else {
				f=new E2_FontPlain();
			}
		}
		int style = Font.ITALIC;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		
		this.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}


	/**
	 * set font underlined
	 * @return
	 */
	public MyE2_Button _ul() {
		Font f = this.getFont();
		if (f==null) {
			Style st = this.getStyle();
			if (st!=null &&st.getProperty(AbstractButton.PROPERTY_FONT)!=null) {
				f= (Font)st.getProperty(AbstractButton.PROPERTY_FONT);
			} else {
				f=new E2_FontPlain();
			}
		}
		int style = Font.UNDERLINE;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		
		this.setFont(new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT)));
		return this;
	}

	/**
	 * set icon
	 * @param icon
	 * @return
	 */
	public MyE2_Button _ri(ResourceImageReference icon) {
		this.setIcon(icon);
		return this;
	}

	public MyE2_Button _setImages(ImageReference  oImg, ImageReference oimgDisabled){
		this.setIcon(oImg);
		this.oImgEnabled = oImg;
		this.oImgDisabled = oimgDisabled;
		return this;
	}
	

}
