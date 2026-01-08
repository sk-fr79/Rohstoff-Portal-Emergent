package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
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
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimpleV2;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_PROGRAMM_WIDE;
import panter.gmbh.Echo2.AgentsAndValidators.IF_simpleValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.BasicInterfaces.IF_Align;
import panter.gmbh.Echo2.BasicInterfaces.IF_Border;
import panter.gmbh.Echo2.BasicInterfaces.IF_ButtonStyling;
import panter.gmbh.Echo2.BasicInterfaces.IF_Dimension;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.BasicInterfaces.IF_LayoutData;
import panter.gmbh.Echo2.BasicInterfaces.IF_Others;
import panter.gmbh.Echo2.BasicInterfaces.IF_addons4Actionables;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;


public class E2_Button extends Button implements 	MyE2IF__Component, 
													E2_IF_Handles_ActionAgents, 
													IF_FontandText<E2_Button>,
													Comparable<E2_Button>,
													IF_RB_Component,
													IF_Dimension<E2_Button>,
													IF_Border<E2_Button>,
													IF_LayoutData<E2_Button>,
													IF_Others<E2_Button>,
													IF_addons4Actionables<E2_Button>,
													IF_Align<E2_Button>,
													E2_IF_Copy,
													IF_ButtonStyling<E2_Button>
													{
														
	private RB_KF Key = null;

														
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
	
	
	/*
	 * innerer action-listener, der benutzt wird, um die ActionAgents, 
	 * die dem button zugewiesen wurden, abzuarbeiten
	 */
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();
			
	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;
	

	private Object 					addOnObject = null;
	


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
	
	
	
	
	
	public E2_Button() {
		super();
		this.setFont(new E2_FontPlain());
	}

	
	/**
	 * 
	 * @param oImg  image, used as enabled adnd disable
	 * @return
	 */
	public E2_Button _image(ImageReference oImg) {
		this.__setImages(oImg,oImg);
		return this;
	}
	

	/**
	 * 
	 * @param oImg  image
	 * @param oImg  oImgDisabled
	 * @return
	 */
	public E2_Button _image(ImageReference oImg, ImageReference oImgDisabled) {
		this.__setImages(oImg,oImgDisabled);
		return this;
	}
	
	/**
	 * 
	 * @param oImg  image
	 * @param oImg  oImgDisabled
	 * @return
	 */
	public E2_Button _image(ImageReference oImg, boolean b_autoDisabled) {
		if (oImg instanceof E2_ResourceIcon) {
			E2_ResourceIcon oRes = (E2_ResourceIcon)oImg;
			this.__setImages(oImg,oRes.get_GrayVersion());	
		} else {
			this.__setImages(oImg,oImg);
		}
		return this;
	}
	
	public E2_Button __setImages(ImageReference  oImg, ImageReference oimgDisabled)
	{
		this.setIcon(oImg);
		this.oImgEnabled = oImg;
		this.oImgDisabled = oimgDisabled;
		return this;
	}

	
	
	
	//  20170804-image als bildnamen ------
	
	/**
	 * 
	 * @param c_img  image, used as enabled adnd disable
	 * @return
	 */
	public E2_Button _image(String c_img) {
		this.__setImages(E2_ResourceIcon.get_RI(c_img),E2_ResourceIcon.get_RI(c_img));
		return this;
	}
	

	/**
	 * 
	 * @param c_img  image
	 * @param c_img  oImgDisabled
	 * @return
	 */
	public E2_Button _image(String c_img, String cmgDisabled) {
		this.__setImages(E2_ResourceIcon.get_RI(c_img),E2_ResourceIcon.get_RI(cmgDisabled));
		return this;
	}
	
	/**
	 * 
	 * @param c_img  image
	 * @param c_img  oImgDisabled
	 * @return
	 */
	public E2_Button _image(String c_img, boolean b_autoDisabled) {
		E2_ResourceIcon oRes = E2_ResourceIcon.get_RI(c_img);
		this.__setImages(oRes,oRes.get_GrayVersion());	
		return this;
	}
	
	
	//  -----------------------------------
	
	
	

	/**
	 * 
	 * @param iinsets
	 * @return
	 */
	public E2_Button _i(Insets i) {
		this.setInsets(i);
		return this;
	}
	
	/**
	 * 
	 * @param c_text (untranslated) 
	 * @return
	 */
	public E2_Button _t(String c_text) {
		this.setText(c_text);
		return this;
	}

	
	/**
	 * sets style as standardstyle for image-buttons 
	 * @return
	 */
	public E2_Button _s_Image() {
		this.setStyle(E2_Button.StyleImageButton());
		return this;
	}

	
	public E2_Button _style(MutableStyle s) {
		this.setStyle(s);
		return this;
	}
	
	/**
	 * sets style as standardstyle for Textbuttons
	 * @return
	 */
	public E2_Button _s_BorderText() {
		this.setStyle(E2_Button.StyleTextButton());
		return this;
	}

	/**
	 * sets style as standardstyle for Textbuttons
	 * @return
	 */
	public E2_Button _s_BorderTextCentered() {
		this.setStyle(E2_Button.StyleTextButtonCentered());
		return this;
	}


	/**
	 * sets style as standardstyle for Textbuttons linksbündig
	 * @return
	 */
	public E2_Button _s_BorderTextLeft() {
		this.setStyle(E2_Button.StyleTextButtonLeft());
		return this;
	}

	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public E2_Button _t(MyE2_String text) {
		this.setText(text.CTrans());
		return this;
	}
	
	
	

	/**
	 * 
	 * @param c when null, disables clickBorder
	 * @return itself
	 */
	public E2_Button _setBorderPressed(Color c) {
		if (c!=null) {
			this.setPressedBorder(new Border(1, c, Border.STYLE_SOLID));
			this.setPressedEnabled(true);
		} else {
			this.setPressedBorder(null);
			this.setPressedEnabled(false);
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param c when null, disables border
	 * @return itself
	 */
	public E2_Button _setBorder(Color c) {
		if (c!=null) {
			this.setBorder(new Border(1, c, Border.STYLE_SOLID));
		} else {
			this.setBorder(null);
		}
		return this;
	}
	
	/**
	 * 
	 * @param c when null, disables border
	 * @return itself
	 */
	public E2_Button _setRolloverBorder(Color c) {
		if (c!=null) {
			this.setRolloverBorder(new Border(1, c, Border.STYLE_SOLID));
			this.setRolloverEnabled(true);
		} else {
			this.setBorder(null);
			this.setRolloverEnabled(false);
		}
		return this;
	}

	
	
	/**
	 * 
	 * @param text (is translated)
	 * @return
	 */
	public E2_Button _tr(String text) {
		this.setText(new MyE2_String(text).CTrans());
		return this;
	}

	
	/**
	 * 
	 * @param f  (font)
	 * @return
	 */
	public E2_Button _f(Font f) {
		this.setFont(f);
		return this;
	}
	

	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public E2_Button _fsa(int iSizeAdd) {
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
	public E2_Button _fs(int iSize) {
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
	public E2_Button _b() {
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
	public E2_Button _i() {
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
	public E2_Button _ul() {
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
	 * 
	 * @param tooltips
	 * @return
	 * @throws myException
	 */
	public E2_Button _ttt(MyE2_String tooltips){
		if (tooltips!=null) {
			this.setToolTipText(tooltips.CTrans());
		} else {
			this.setToolTipText("");
		}
		return this;
	}

	@Override
	public E2_Button _ttt(String tooltips) {
		
		this.setToolTipText(new MyE2_String(S.NN(tooltips)).CTrans());
		return this;
	}


	

	@Override
	public  E2_Button  _aaa(XX_ActionAgent agent) {
		this.add_oActionAgent(agent);
		return this;
	}

	
	@Override
	public E2_Button  _aaa(XX_ActionAgent agent, boolean bInFront) {
		this.add_oActionAgent(agent, bInFront);
		return this;
	}

	
	@Override
	public  E2_Button  _aaaV(Vector<XX_ActionAgent> v_agents) {
		for (XX_ActionAgent agent: v_agents) {
			this.add_oActionAgent(agent);
		}
		return this;
	}

	
	
	
	@Override
	public E2_Button  _aaa(IF_agentSimple agent) {
		this.add_oActionAgent(agent.genActionAgent());
		return this;
	}
	
	@Override
	public E2_Button  _aaaInFront(IF_agentSimple agent, boolean inFront) {
		this.add_oActionAgent(agent.genActionAgent(), inFront);
		return this;
	}

	
	@Override
	public E2_Button _addValidator(IF_simpleValidator validator) {
		this.add_GlobalValidator(validator.getValidator());
		return this;
	}
	
	
	
	public E2_Button  _aaa(IF_agentSimpleV2 agent) {
		this.add_oActionAgent(agent.genActionAgent());
		return this;
	}
	
	
	@Override
	public E2_Button  _aaaV(Vector<XX_ActionAgent> v_agents, boolean inFront) {
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
	public void setStyle(Style oStyle) 	{
		super.setStyle(oStyle);
		if (oStyle instanceof E2_MutableStyle) {
			this.EXT().set_oE2_Style((E2_MutableStyle)oStyle);
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
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled());
		
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null) {
			if (enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled()) {
				this.setIcon(this.oImgEnabled);
			} else {
				this.setIcon(this.oImgDisabled);
			}
		}

		if (this.EXT().get_oE2_Style()!=null) {
			if (this.isEnabled()) {
				this.setStyle(this.EXT().get_oE2_Style());
			} else {
				this.setStyle(this.EXT().get_oE2_Style().get_oStyleDisabled());
			}
		}
	}

	

	
	@Override
	public void add_GlobalValidator(XX_ActionValidator oValid) {
		this.vGlobalValidators.add(oValid);
	}
	
	
	/**
	 * 2013-05-16: martin panter
	 * @param oValid
	 */
	public void add_GlobalValidator_removeOthers(XX_ActionValidator oValid) {
		this.vGlobalValidators.removeAllElements();
		this.vGlobalValidators.add(oValid);
	}
	
	
	
	
	//2012-10-15: falls nicht der liste
	public void add_GlobalValidator_only_if_ClassNotInList(XX_ActionValidator oValid) {
		boolean bValidatorIstSchonDa = false;
		
		if (oValid!=null) {
			for (XX_ActionValidator oValidVorhanden: this.vGlobalValidators) {
				if (oValidVorhanden.getClass().getName().equals(oValid.getClass().getName())) {
					bValidatorIstSchonDa=true;
				}
			} if (!bValidatorIstSchonDa) {
				this.vGlobalValidators.add(oValid);
			}
		}
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


	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cModuleKenner
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator(String cModuleKenner,String cButtonKenner)	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator(cModuleKenner,cButtonKenner));
	}
	
	
	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_AUTO(String cButtonKenner){
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_AUTO(cButtonKenner));
	}
	

	/**
	 * Hilfsmethode um eine auth-validierung einzufuegen
	 * @param cButtonKenner
	 */
	public void add_GlobalAUTHValidator_PROGRAMM_WIDE(String cButtonKenner)	{
		this.vGlobalValidators.add(new E2_ButtonAUTHValidator_PROGRAMM_WIDE(cButtonKenner));
	}
	
	

	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
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

	
	
	public MyE2_MessageVector  valid_SingleGlobalValidator(XX_ActionValidator oValidator) throws myException {
		MyE2_MessageVector vRueck = new MyE2_MessageVector();
		vRueck.add_MESSAGE(oValidator.isValid(this));
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
		for (int i=0;i<this.vIDValidators.size();i++)
		{
			XX_ActionValidator oValid = (XX_ActionValidator)this.vIDValidators.get(i);
			vRueck.add_MESSAGE(oValid.isValid(vID_Unformated,false));
		}
		return vRueck;
	}


	
	public ImageReference get_oImgDisabled()					{		return oImgDisabled;	}
	public ImageReference get_oImgEnabled()						{		return oImgEnabled;		}


	@Override
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;		}

	@Override
	public MyE2EXT__Component EXT()								{		return this.oEXT;		}

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
	 * 2015-11-25: geaendert: wenn leer oder null, dann darf kein listener zugefuegt werden
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
	@Override
	public void add_oActionAgent(Vector<XX_ActionAgent> vActionAgent, boolean bInFront){
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


	
	
	
	
	
	
	@Override
	public void remove_oActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vActionAgents.size();i++) {
			if (this.vActionAgents.get(i)==actionAgent)	{
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


	public Vector<ActionListener> get_vExternalActionListeners() {
		return vExternalActionListeners;
	}


	@Override
	public void addActionListener(ActionListener oActionListener)	{
		this.vExternalActionListeners.add(oActionListener);
		super.addActionListener(oActionListener);
	}
	
	@Override
	public void removeActionListener(ActionListener oActionListener) {
		for (int i=0;i<this.vExternalActionListeners.size();i++)
		{
			if (this.vExternalActionListeners.get(i) == oActionListener)
			{
				this.vExternalActionListeners.remove(i);
			}
		}
		super.removeActionListener(oActionListener);
	}


	@Override
	public void show_InputStatus(boolean bInputIsOK) {
	}


	public Vector<XX_ActionValidator> get_vGlobalValidators()	{		return vGlobalValidators;	}
	public Vector<XX_ActionValidator> get_vIDValidators()		{		return vIDValidators;	}
	

	
	/**
	 * 
	 * @param c = foregroundColor
	 * @return
	 */
	public E2_Button  _fc(Color c) {
		this.setForeground(c);
		return this;
	}
	
	/**
	 * 
	 * @param c = backgroundColor
	 * @return
	 */
	public E2_Button  _bc(Color c) {
		this.setBackground(c);
		return this;
	}

	/**
	 * 
	 * @param c = backgroundColor-Disabled
	 * @return
	 */
	public E2_Button  _bcd(Color c) {
		this.setDisabledBackground(c);
		return this;
	}

	/**
	 * 
	 * @param c = foregroundColor-Disabled
	 * @return
	 */
	public E2_Button  _fcd(Color c) {
		this.setDisabledForeground(c);
		return this;
	}
	

	//2012-02-02: buttons geaendert, damit beim Druck kein huepfen des layouts
	public static E2_MutableStyle StyleImageButton()	{
		E2_MutableStyle ostyleImageButton = new E2_MutableStyle();
		
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, null);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BORDER, null);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_PRESSED_BACKGROUND, Color.WHITE);
		ostyleImageButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, null);
		return ostyleImageButton;
	}

	

	public E2_Button _set_pressed_color(Color col) {
		this.setPressedBackground(col);
		return this;
	}

	public E2_Button _set_rollover_color(Color col) {
		this.setRolloverBackground(col);
		return this;
	}

	public E2_Button _set_pressed_border(Border border) {
		this.setPressedBorder(border);
		this.setPressedEnabled(true);
		return this;
	}
	
	
	public E2_Button  _standard_text_button() {
		this._set_pressed_border(new Border(1,Color.WHITE,Border.STYLE_SOLID))
			._backDark()
			._bordDDDark()
			;
		
		return this;
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


	//2018-06-05: neutraler Style
	public static E2_MutableStyle baseStyle(Font f)	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColor = new E2_ColorBase();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColor,oColor); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, new Border(1, Color.BLACK, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT,f);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		
		return ostyleTextButton;
	}

	public static E2_MutableStyle styleLikeLabel()	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColor = new E2_ColorBase();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColor,oColor); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, Boolean.FALSE);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDark(), Border.STYLE_NONE));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, new Border(1, Color.BLACK, Border.STYLE_NONE));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		
		return ostyleTextButton;
	}

	

	/**
	 * 
	 * @author martin
	 * @date 15.01.2020
	 *
	 * @param f
	 * @param align (can be null)
	 * @return
	 */
	public static E2_MutableStyle baseStyle(Font f, Alignment align)	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		
		Color					oColor = new E2_ColorBase();
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BACKGROUND, oColor,oColor); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.DARKGRAY);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.DARKGRAY, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDark(), Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ROLLOVER_BORDER, new Border(1, Color.BLACK, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_FONT,f);
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
		
		if (align!=null) {
			ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,align);
		}

		
		return ostyleTextButton;
	}

	
	
	public static E2_MutableStyle baseStyle()	{
		return E2_Button.baseStyle(new E2_FontPlain());
	}
	public static E2_MutableStyle baseStyleRight()	{
		return E2_Button.baseStyle(new E2_FontPlain(), Alignment.ALIGN_RIGHT);
	}
	
	public static E2_MutableStyle baseStyleBold()	{
		return E2_Button.baseStyle(new E2_FontBold());
	}
	public static E2_MutableStyle baseStyleItalic()	{
		return E2_Button.baseStyle(new E2_FontItalic());
	}
	public static E2_MutableStyle baseStyleBoldItalic()	{
		return E2_Button.baseStyle(new E2_FontBoldItalic());
	}

	


	public static E2_MutableStyle baseStyleRight(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontPlain(), Alignment.ALIGN_RIGHT);
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));

		return s;
	}
	
	public static E2_MutableStyle baseStyleRightBold(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontBold(), Alignment.ALIGN_RIGHT);
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}
	
	public static E2_MutableStyle baseStyleRightItalic(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontItalic(), Alignment.ALIGN_RIGHT);
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}
	
	public static E2_MutableStyle baseStyleRightBoldItalic(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontBoldItalic(), Alignment.ALIGN_RIGHT);
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}
	
	
	
	
	public static E2_MutableStyle baseStyle(Color fore, Color back)	{
		E2_MutableStyle s= E2_Button.baseStyle(new E2_FontPlain());
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}
	
	public static E2_MutableStyle baseStyleBold(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontBold());
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}
	
	public static E2_MutableStyle baseStyleItalic(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontItalic());
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}
	
	public static E2_MutableStyle baseStyleBoldItalic(Color fore, Color back)	{
		E2_MutableStyle s = E2_Button.baseStyle(new E2_FontBoldItalic());
		s.setProperty(AbstractButton.PROPERTY_FOREGROUND, O.NN(fore, Color.BLACK));
		s.setProperty(AbstractButton.PROPERTY_BACKGROUND, O.NN(back, new E2_ColorBase()));
		return s;
	}

	


	public static E2_MutableStyle StyleTextButtonCentered()
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
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,Alignment.ALIGN_CENTER);
		
		return ostyleTextButton;
	}

	public static E2_MutableStyle StyleTextButtonLeft()
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
		
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.LEFT, Alignment.CENTER));
		
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

	


	/**
	 * Simpler Style mit Dunklem Border und Wite-Pressed Border
	 */
	public static E2_MutableStyle StyleTransparentTextButtonWithDDDBorder()	{
		E2_MutableStyle ostyleTextButton = new E2_MutableStyle();
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, Color.WHITE, Border.STYLE_SOLID));
		ostyleTextButton.setProperty( AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-40), Border.STYLE_SOLID));
		return ostyleTextButton;
	}

	



	public static E2_MutableStyle Style_SortButtonStandard() {
		return E2_Button.StyleTextButton(new E2_ColorBase(-10),new E2_ColorBase(-10),Color.BLACK,  Color.DARKGRAY);
	}
	
	
	
	public ActionListener get_oInnerActionListener() {
		return oInnerActionListener;
	}


	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	@Override
	public void doActionPassiv() {
		//boolean bTemp = bActionEventIsPassive;
		bActionEventIsPassive =true;
		this.doAction();
		bActionEventIsPassive =false;
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
	public int compareTo(E2_Button arg0) {
		return this.getText().compareTo(arg0.getText());
	}



	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	@Override
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
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
		if (actionAgent != null) {
			this.vInternalActionAgents.add(actionAgent);
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}

	
	@Override
	public void add_oInternalActionAgent(XX_ActionAgent actionAgent, boolean bInFront)	{
		if (actionAgent != null){
			if (bInFront) {
				this.vInternalActionAgents.add(0,actionAgent);
			} else {
				this.vInternalActionAgents.add(actionAgent);
			}
		}
		
		/*
		 * der action-listener fuer diesen button wird nur beim ersten zugefuegt. 
		 * dieser handelt alle actionAgents ab
		 */
		if (this.vInternalActionAgents.size()==1) {
			super.addActionListener(this.oInnerActionListenerInternalAction);
		}
	}
	
	@Override
	public void remove_oInternalActionAgent(XX_ActionAgent actionAgent) {
		for (int i=0;i<this.vInternalActionAgents.size();i++) {
			if (this.vInternalActionAgents.get(i)==actionAgent) 	{
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
	public void remove_AllInternalActionAgents()  {
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



	public E2_Button _bordBlack() {
		this.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
		return this;
	}
	
	public E2_Button _bordDark() {
		this.setBorder(new Border(1,new E2_ColorDark(),Border.STYLE_SOLID));
		return this;
	}

	public E2_Button _bordDDark() {
		this.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
		return this;
	}
	public E2_Button _bordDDDark() {
		this.setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		return this;
	}

	
	public E2_Button _bord(Border bord) {
		this.setBorder(bord);
		return this;
	}

	
	public E2_Button _backGreen() {
		this.setBackground(Color.GREEN);
		return this;
	}
	
	public E2_Button _backDDark() {
		this.setBackground(new E2_ColorDDark());
		return this;
	}

	public E2_Button _backDark() {
		this.setBackground(new E2_ColorDark());
		return this;
	}

	public E2_Button _backDDDark() {
		this.setBackground(new E2_ColorDDDark());
		return this;
	}
	
	public E2_Button _font(Font f) {
		this.setFont(f);
		return this;
	}

	public E2_Button _center() {
		this.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
		return this;
	}

	public E2_Button _left() {
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.DEFAULT));
		return this;
	}
	public E2_Button _right() {
		this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.DEFAULT));
		return this;
	}
	
	
	/**
	 * 
	 * @param v  (global validator)
	 * @return
	 */
	public E2_Button _v(XX_ActionValidator v) {
		this.add_GlobalValidator(v);
		return this;
	}
	
	
	/**
	 * 
	 * @param wrap
	 * @return
	 */
	public E2_Button _lw(boolean wrap) {
		this.setLineWrap(wrap);
		return this;
	}
	
	
	/**
	 * 
	 * @param GridLayoutData (special RB_gld)
	 * @return
	 */
	public E2_Button _gld(RB_gld gld) {
		this.setLayoutData(gld);
		return this;
	}
	
	
	
	//implementierung des interfaces IF_RB_Component
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
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
//		this.setText();
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) {
		this.setAlignment(align);
	}

	public E2_Button _setAlignment(Alignment align)  {
		this.setAlignment(align);
		return this;
	}
	
	public E2_Button _setAlignmentCenterCenter()  {
		return this._setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
	}
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		//wird hier nicht verwendet, ist als passives steuerelement gedacht
	}


	@Override
	public E2_Button setNeutralBorder(Border border) {
		return this._bord(new Border(0, new E2_ColorBase(), Border.STYLE_NONE));
	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		throw new myExceptionCopy(".getCopy(): Must be implemented !");
	}
	
	

	public Object getAddOnObject() {
		return addOnObject;
	}


	public E2_Button _setAddOnObject(Object addOn) {
		this.addOnObject = addOn;
		return this;
	}


	
	
}
