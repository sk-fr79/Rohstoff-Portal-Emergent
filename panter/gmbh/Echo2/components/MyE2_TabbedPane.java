package panter.gmbh.Echo2.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode_For_TabChangeAction;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.E2_MessageHandler;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;
import echopointng.BorderEx;
import echopointng.ExtentEx;
import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;

public class MyE2_TabbedPane extends TabbedPane implements MyE2IF__Component, E2_IF_Copy 
{
	private MyE2EXT__Component 		oEXT = 						new MyE2EXT__Component(this);
	private DefaultTabModel			oTabModel = 				null;
	
	private HashMap<String,Button_TabComponent_ActionAgent> 	hmAgentsAndButtons = 	new HashMap<String,Button_TabComponent_ActionAgent>();
	
	/*
	 * der sortvector enthaelt die texte(Original, nicht uebersetzt) der uebergebenen tabs 
	 */
	private Vector<String>	        vSortVector = 			new Vector<String>();
	
	
	/*
	 * der Vergleichsvector enthaelt die texte(Original, nicht uebersetzt) der uebergebenen tabs 
	 */
	private Vector<String>	        vBasisVector = 			new Vector<String>();
	
	

	/*
	 * ein parameter, der tabbed-panes in masken auf die fensterhoehe mitskaliert
	 */
	private boolean       	       bAutoHeight = false;
	
	
	//2011-02-01:  boolescher wert zum verhindern des tab-abspeicherns (der sonst immer bei abgeleiteten klassen  ... extends MyE2_TabbedPane kommt)
	private boolean   			   bAllowSaveTabReihenfolge = true;
	
	

	/**
	 * innerer action-listener, der benutzt wird, um die ActionAgents, 
	 * die dem button zugewiesen wurden, abzuarbeiten
	 */
	private ActionListener			oInnerActionListener = 
		new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// hier wird die ganze message-agent zurodnung automatisiert
					E2_MessageHandler oMessageHandler = new E2_MessageHandler(e);

					if (e.getSource() instanceof Button)
					{
						HashMap<String,Button_TabComponent_ActionAgent> hm_AgentsAndButtons = MyE2_TabbedPane.this.hmAgentsAndButtons;
						
						for (Map.Entry<String,Button_TabComponent_ActionAgent> oMapEntry: hm_AgentsAndButtons.entrySet())
						{
							Button_TabComponent_ActionAgent oAB = oMapEntry.getValue();
							
							if (oAB.oTabComponent==e.getSource())
							{
								XX_ActionAgent oAgent = oAB.oActionAgent;
								try
								{
									oAgent.ExecuteAgentCode(new ExecINFO_OnlyCode_For_TabChangeAction(oAB));
								}
								catch (myException ex)
								{
									ex.printStackTrace();
									bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
								}
							}
						}
					}
					
					oMessageHandler.showMessages();
				}
			};

	

	
	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	private Integer    iForcedHeight = new Integer(500);
	
	
	public MyE2_TabbedPane(DefaultTabModel oTabModel, Integer ForcedHeight)
	{
		super();
		
		this.oTabModel = oTabModel;
		
		if (ForcedHeight!=null) this.iForcedHeight = ForcedHeight;
		this.setHeight(new Extent(this.iForcedHeight.intValue()));
		
		this.setModel(this.oTabModel);
		this.setTabPlacement(Alignment.TOP);

		this.setInsets(new Insets(4));
		this.setOutsets(new Insets(2));
		this.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
	}
	

	
	public MyE2_TabbedPane(Integer ForcedHeight)
	{
		super();
		
		this.oTabModel = new MyE2_TabbedPane.MyE2_TabModel();

		if (ForcedHeight!=null) 
		{
			this.iForcedHeight = ForcedHeight;
			this.setHeight(new Extent(this.iForcedHeight.intValue()));
		}
		
		this.setModel(this.oTabModel);
		this.setTabPlacement(Alignment.TOP);

		this.setInsets(new Insets(4));
		this.setOutsets(new Insets(2));
		this.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
			
	}
	

	
	public void add_Tabb(MyString cLabelText, Component oComponent) throws myException
	{
		this._add_Tabb(cLabelText, oComponent, null);
	}
	
	public void add_Tabb(MyString cLabelText, Component oComponent, XX_ActionAgent oAgent) throws myException
	{
		this._add_Tabb(cLabelText, oComponent, oAgent);
	}
	
	

	private void _add_Tabb(MyString cLabelText, Component oComponent, XX_ActionAgent oActionAgentForTab) throws myException
	{
		if (this.hmAgentsAndButtons.containsKey(cLabelText.COrig()))
			throw new myException(this,"Key is double"+cLabelText.COrig());
		
		if (oComponent instanceof ContentPane || this.iForcedHeight==null)
		{
			this.oTabModel.addTab(cLabelText.CTrans(),oComponent);
		}
		else
		{
			ContentPane oPane = new ContentPane();
			oPane.add(oComponent);
			this.oTabModel.addTab(cLabelText.CTrans(),oPane);
		}
			
		int 		iActualSelected = 	this.getSelectedIndex();
		Component 	oCompTab = 			this.oTabModel.getTabAt(this,this.size()-1,(iActualSelected==(this.size()-1)));
		Component   oCompInhalt = 		this.oTabModel.getTabContentAt(this.size()-1);

		this.hmAgentsAndButtons.put(cLabelText.COrig(), new Button_TabComponent_ActionAgent(cLabelText,oActionAgentForTab,oCompTab,oCompInhalt));
		
		if (oCompTab instanceof Button && oActionAgentForTab!=null)
		{
			((Button)oCompTab).addActionListener(this.oInnerActionListener);
		}
		
		this.vSortVector.add(cLabelText.COrig());
		this.vBasisVector.add(cLabelText.COrig());
	}
	

	
	
	
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException 
	{
		//bEnabled = bEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;
	}

	public void show_InputStatus(boolean bInputIsOK) 
	{
	}

	
	
	/**
	 * evtl. neue reihenfolge der tabpanes einstellen
	 * @throws myException
	 */
	public void RebuildTabPane(Vector<String> SortVector) throws myException
	{
		int iSize = this.oTabModel.size();
		
		//test
		//DEBUG.System_println("Selected-Index TabbedPane vor sort:"+this.getSelectedIndex(), "");
		//test
		
		for (int i=0;i<iSize;i++)
		{
			this.oTabModel.removeTabAt(0);
		}

		//test
		//DEBUG.System_println("Selected-Index TabbedPane nach remove:"+this.getSelectedIndex(), "");
		//test

		
		// falls der basisvector und der sortvector nicht uebereinstimmen, dann
		this.vSortVector.removeAllElements();
		if (bibVECTOR.VectorsContainsSameStrings(SortVector, this.vBasisVector))
		{
			this.vSortVector.addAll(SortVector);
		}
		else
		{
			this.vSortVector.addAll(this.vBasisVector);
		}
		
		
		for (int i=0;i<this.vSortVector.size();i++)
		{
			Button_TabComponent_ActionAgent oTab = this.hmAgentsAndButtons.get(this.vSortVector.get(i));
			
			this.oTabModel.addTab(oTab.oString.CTrans(),oTab.oTabInhalt);
			int 		iActualSelected = 	this.getSelectedIndex();
			Component 	oCompTab = 			this.oTabModel.getTabAt(this,this.size()-1,(iActualSelected==(this.size()-1)));
			
			//test
			//DEBUG.System_println("Selected-Index TabbedPane nach add "+i+":"+this.getSelectedIndex(), "");
			//test

			
			if (oCompTab instanceof Button && oTab.oActionAgent!=null)
			{
				((Button)oCompTab).addActionListener(this.oInnerActionListener);
				//der Tab-button wird immer automatisch neu gesetzt, deshalb muss er in das Button_TabComponent_ActionAgent-element
				oTab.oTabComponent = oCompTab;
			}
		}
	}
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy 
	{
		throw new myExceptionCopy("Component: MyE2_TabbedPane:get_Copy() not implemented yet !");
	}

	

	/*
	 * schaltet die buttons in allen tabs enabled
	 */
	public void set_enable_All_Tabs() throws myException
	{
		int iActualSelected = this.getSelectedIndex();
		for (int i=0;i<this.oTabModel.size();i++)
		{
		
			/*
			 * getTabAt(TabbedPane tabbedPane, int index, boolean isSelected)
	         * This is called to retrieve a Component that represents the title of the tab at the specified index. 
			 */
			Component oComp = this.oTabModel.getTabAt(this,i,(iActualSelected==i));

			if (oComp instanceof Button)
			{
				((Button)oComp).setEnabled(true);
			}
		}
	}
	
	/*
	 * schaltet die buttons in allen TABs ausser dem eigenen Disabled
	 */
	public void set_disable_All_Tabs_ExceptActualTab() throws myException
	{
		int iActualSelected = this.getSelectedIndex();
		for (int i=0;i<this.oTabModel.size();i++)
		{
			/*
			 * getTabAt(TabbedPane tabbedPane, int index, boolean isSelected)
	         * This is called to retrieve a Component that represents the title of the tab at the specified index. 
			 */
			Component oComp = this.oTabModel.getTabAt(this,i,(iActualSelected==i));

			if (oComp instanceof Button)
			{
				if (i!=iActualSelected)
					((Button)oComp).setEnabled(false);
			}
		}
	}
	

	public void set_Enable_Tab(int iTabIndex, boolean bEnabled) throws myException
	{
		if (iTabIndex>=this.oTabModel.size())
			throw new myException("MyE2_TabbedPane:add_ActionAgent_to_Tab:Index out of bound !!!");
		
		int iActualSelected = this.getSelectedIndex();
		
		/*
		 * getTabAt(TabbedPane tabbedPane, int index, boolean isSelected)
         * This is called to retrieve a Component that represents the title of the tab at the specified index. 
		 */
		Component oComp = this.oTabModel.getTabAt(this,iTabIndex,(iActualSelected==iTabIndex));

		if (oComp instanceof Button)
		{
			((Button)oComp).setEnabled(bEnabled);
		}
	}
	
	
	
	
	
		

	
	
	
	public DefaultTabModel get_oTabModel()
	{
		return oTabModel;
	}

	
	/*
	 * helper-klasse, die eine button-komponente und einen actionagent zusammenfasst
	 */
	public class Button_TabComponent_ActionAgent 
	{
		public MyString       	oString      = null;
		public XX_ActionAgent 	oActionAgent = null;
		public Component		oTabComponent = null;
		public Component     	oTabInhalt = null;
		
		public Button_TabComponent_ActionAgent(MyString cBeschriftung, XX_ActionAgent agent, Component tabcomponent, Component tabInhalt)
		{
			super();
			this.oString = cBeschriftung;
			this.oActionAgent = agent;
			this.oTabComponent = tabcomponent;
			this.oTabInhalt = tabInhalt;
		}
		
		/**
		 * liefert den unuebersetzten text auf der tab-beschriftung
		 * @return
		 */
		public String get_cOriginalTextAufTAB() {
			return oString==null ? null : oString.COrig();
		}
	}
	
	
	
	
	
	
	public static class MyE2_TabModel extends DefaultTabModel
	{
		
		private Font 	oFont = new E2_FontPlain();
		
		
		public MyE2_TabModel(Font font)
		{
			super();
			oFont = font;
		}

		public MyE2_TabModel()
		{
			super();
		}

		protected void paintTabComponent(TabbedPane tabbedPane, Component tabComponent, boolean isSelected, int tabPlacement) 
		{
			super.paintTabComponent(tabbedPane,tabComponent,isSelected,tabPlacement);
		
			int borderStyle = Border.STYLE_SOLID;
			
			Color 		oColBorder = 				new E2_ColorDDDark();
			Color 		oColSelectedBackGround = 	new E2_ColorBase();
			Color 		oColBackGround = 			new E2_ColorBase(-20);
			BorderEx 	newBorder;
			
			if (isSelected) 
			{
				newBorder = new BorderEx(
								/*L*/new ExtentEx(1),oColBorder,					borderStyle,
								/*R*/new ExtentEx(1),oColBorder,					borderStyle,
								/*T*/new ExtentEx(1),oColBorder,					borderStyle,
								/*B*/new ExtentEx(1),oColSelectedBackGround,		borderStyle);
								
				tabComponent.setProperty(Button.PROPERTY_BACKGROUND, oColSelectedBackGround);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BACKGROUND, oColSelectedBackGround);
				tabComponent.setProperty(Button.PROPERTY_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_FONT,this.oFont);
			} 
			else 
			{
				newBorder = new BorderEx(
						/*L*/new ExtentEx(1),oColBorder,					borderStyle,
						/*R*/new ExtentEx(1),oColBorder,					borderStyle,
						/*T*/new ExtentEx(1),oColBorder,					borderStyle,
						/*B*/new ExtentEx(1),oColBackGround,				borderStyle);
						
				tabComponent.setProperty(Button.PROPERTY_BACKGROUND, oColBackGround);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BACKGROUND, oColBackGround);
				tabComponent.setProperty(Button.PROPERTY_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_FONT,this.oFont);
			}
		};
		
		
	}

	
	
	
	
	/**
	 * 2013-02-25
	 * neues tab-model mit zeilenumbruch
	 * @author martin
	 *
	 */
	public static class MyE2_TabModelLineWrap extends DefaultTabModel
	{
		
		private Font 	oFont = new E2_FontPlain();
		private Font 	oFontSelected = new E2_FontPlain();
		private int     iHeightTabs = 30;
		
		
		
		public MyE2_TabModelLineWrap(Font font, int HeightTabs) {
			super();
			this.oFont = font;
			this.oFontSelected=font;
			this.iHeightTabs = HeightTabs;
		}

		/**
		 * 20180504: neuer konstructor
		 * @param font
		 * @param HeightTabs
		 */
		public MyE2_TabModelLineWrap(Font font,Font fontSelected, int HeightTabs) {
			super();
			this.oFont = font;
			this.oFontSelected=fontSelected;
			this.iHeightTabs = HeightTabs;
		}

		
		
		public MyE2_TabModelLineWrap( int HeightTabs) {
			super();
			this.iHeightTabs = HeightTabs;
		}

		protected void paintTabComponent(TabbedPane tabbedPane, Component tabComponent, boolean isSelected, int tabPlacement) 
		{
			super.paintTabComponent(tabbedPane,tabComponent,isSelected,tabPlacement);
		
			int borderStyle = Border.STYLE_SOLID;
			
			Color 		oColBorder = 				new E2_ColorDDDark();
			Color 		oColSelectedBackGround = 	new E2_ColorBase();
			Color 		oColBackGround = 			new E2_ColorBase(-20);
			BorderEx 	newBorder;
			
			if (isSelected) 
			{
				newBorder = new BorderEx(
								/*L*/new ExtentEx(1),oColBorder,					borderStyle,
								/*R*/new ExtentEx(1),oColBorder,					borderStyle,
								/*T*/new ExtentEx(1),oColBorder,					borderStyle,
								/*B*/new ExtentEx(1),oColSelectedBackGround,		borderStyle);
								
				tabComponent.setProperty(Button.PROPERTY_BACKGROUND, oColSelectedBackGround);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BACKGROUND, oColSelectedBackGround);
				tabComponent.setProperty(Button.PROPERTY_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_FONT,this.oFontSelected);
				tabComponent.setProperty(Button.PROPERTY_LINE_WRAP,new Boolean(true));
				tabComponent.setProperty(Button.PROPERTY_HEIGHT,new Extent(this.iHeightTabs));
			} 
			else 
			{
				newBorder = new BorderEx(
						/*L*/new ExtentEx(1),oColBorder,					borderStyle,
						/*R*/new ExtentEx(1),oColBorder,					borderStyle,
						/*T*/new ExtentEx(1),oColBorder,					borderStyle,
						/*B*/new ExtentEx(1),oColBackGround,				borderStyle);
						
				tabComponent.setProperty(Button.PROPERTY_BACKGROUND, oColBackGround);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BACKGROUND, oColBackGround);
				tabComponent.setProperty(Button.PROPERTY_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_ROLLOVER_BORDER,newBorder);
				tabComponent.setProperty(Button.PROPERTY_FONT,this.oFont);
				tabComponent.setProperty(Button.PROPERTY_LINE_WRAP,new Boolean(true));
				tabComponent.setProperty(Button.PROPERTY_HEIGHT,new Extent(this.iHeightTabs));
			}
		};
		
		
	}

	
	
	
	

	public Vector<String> get_SortVector()
	{
		return vSortVector;
	}
	
	public HashMap<String, Button_TabComponent_ActionAgent> get_hmAgentsAndButtons()
	{
		return hmAgentsAndButtons;
	}
	
	
	public boolean get_bAutoHeight()
	{
		return bAutoHeight;
	}
	
	
	public void set_bAutoHeight(boolean autoHeight)
	{
		bAutoHeight = autoHeight;
	}
	
	
	public boolean get_bAllowSaveTabReihenfolge() 
	{
		return bAllowSaveTabReihenfolge;
	}
	
	public void set_bAllowSaveTabReihenfolge(boolean bAllowSaveTabReihenfolge) 
	{
		this.bAllowSaveTabReihenfolge = bAllowSaveTabReihenfolge;
	}
	
	
	
	
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

}
