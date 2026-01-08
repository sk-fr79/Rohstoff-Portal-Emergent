package panter.gmbh.Echo2.components;

import java.util.Vector;

import echopointng.KeyStrokeListener;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.XX_MessageManipulator;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_InnerActionListenerForInternalActionAgents;
import panter.gmbh.Echo2.ActionEventTools.E2_PassiveActionExecuter;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_KeyStrokeListener extends KeyStrokeListener implements MyE2IF__Component, E2_IF_Handles_ActionAgents
{
	
	private Vector<XX_ActionAgent>   vActionAgents = new Vector<XX_ActionAgent>();
	private MyE2EXT__Component 		oEXT = 			new MyE2EXT__Component(this);


	/*
	 * innerer action-listener, der benutzt wird, um die ActionAgents, 
	 * die dem button zugewiesen wurden, abzuarbeiten
	 */
	
	private E2_InnerActionListenerForActionAgents		oInnerActionListener = new E2_InnerActionListenerForActionAgents();



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
	

	
	private boolean 				Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = true;

	public boolean get_bMustSet_MILLISECONDSTAMP_TO_Session() 
	{
		return this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER;
	}


	public void set_bMustSet_MILLISECONDSTAMP_TO_Session(boolean bMustSet_MILLISECONDSTAMP) 
	{
		this.Must_Set_MILLISECONDSTAMP_TO_STARTCONTAINER = bMustSet_MILLISECONDSTAMP;
	}


	public MyE2_KeyStrokeListener(int KeyAction)
	{
		super();
		this.addKeyCombination(KeyAction);
		this.addActionListener(this.oInnerActionListener);
	}

	public MyE2_KeyStrokeListener(int KeyAction, String KeyCode)
	{
		super();
		this.addKeyCombination(KeyAction,KeyCode);
		this.addActionListener(this.oInnerActionListener);
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

	
	
	/*
	 * 2012-02-07: weitere methode hinzugefuegt, martin 
	 * @see panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents#add_oActionAgent(java.util.Vector, boolean)
	 */
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

	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}


	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		//bEnabled = bEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	public MyE2_MessageVector valid_GlobalValidation() throws myException 
	{
		return new MyE2_MessageVector();
	}

	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException 
	{
		return new MyE2_MessageVector();
	}

	/*
	 * schalter, der benutzt wird, um "passive", d.h. aus dem programmcode abgeleitete event-aufrufe zu starten
	 */
	private boolean bActionEventIsPassive = false;
	

	public void doActionPassiv() 
	{
		bActionEventIsPassive =true;
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
	public void add_GlobalValidator(XX_ActionValidator valid)
	{
		
	}


	@Override
	public void add_IDValidator(XX_ActionValidator valid)
	{
	}


	@Override
	public void add_GlobalValidator(Vector<XX_ActionValidator> valid)
	{
	}


	@Override
	public void add_IDValidator(Vector<XX_ActionValidator> valid)
	{
	}
	
	
	
	//2011-09-30: neue methode im interface fuer das passive abarbeiten eine Button-codes innerhalb eines 
	//            anderen actionagenten
	public void do_OnlyCode_from_OtherActionAgent(MyActionEvent oActionEventOtherActionAgent) throws myException 
	{
		new E2_PassiveActionExecuter(this, oActionEventOtherActionAgent);
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

	
	
}
