package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_SelectAllNoneInvert_New extends MyE2_Grid
{

	private static 		int[] 					iSpalten  = {10,10};
	
	/**
	 * abstracte klasse um die non-all-invert-selektierung beliebiger sammlungen von checkboxen zu handeln 
	 */
	public E2_SelectAllNoneInvert_New()
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		//super();
		this.set_Spalten(iSpalten);
		this.setBorder(null);
		this.setInsets(E2_INSETS.I_0_0_0_0);
		this.add(new ownButtonUnCheckAll(), E2_INSETS.I_0_0_0_0);
		this.add(new ownButtonCheckAll(), E2_INSETS.I_0_0_0_0);
		this.add(new ownButtonCheckInvert(), E2_INSETS.I_0_0_0_0);

	}
	
	public abstract Vector<CheckBox>  	get_vAllCheckboxes() throws myException; 
	public abstract void  				do_after_action() throws myException;
	
	
	private class ownButtonUnCheckAll extends MyE2_Button
	{

		public ownButtonUnCheckAll()
		{
			super(E2_ResourceIcon.get_RI("checkbox_quartett_uncheck.png"),true);
			this.setInsets(new Insets(1,1,1,1));
			this.setToolTipText(new MyE2_String("Die Auswahlschalter aller Listenzeilen entfernen").CTrans());
			this.add_oActionAgent(new ActionAgentForChangeSelection("KEINE"));
		}
		
	}
	
	private class ownButtonCheckAll extends MyE2_Button
	{

		public ownButtonCheckAll()
		{
			super(E2_ResourceIcon.get_RI("checkbox_quartett_check.png"),true);
			this.setInsets(new Insets(1,1,1,1));
			this.setToolTipText(new MyE2_String("Alle Elemente der Liste auswählen").CTrans());
			this.add_oActionAgent(new ActionAgentForChangeSelection("ALLE"));
		}
		
	}

	private class ownButtonCheckInvert extends MyE2_Button
	{

		public ownButtonCheckInvert()
		{
			super(E2_ResourceIcon.get_RI("checkbox_quartett_invert.png"),true);
			this.setInsets(new Insets(1,1,1,1));
			this.setToolTipText(new MyE2_String("Die Listenauswahl invertieren ...").CTrans());
			this.add_oActionAgent(new ActionAgentForChangeSelection("INVERT"));
		}
		
	}

	
	private class ActionAgentForChangeSelection extends XX_ActionAgent
	{
		private String cType = ""; 

		public ActionAgentForChangeSelection(String type)
		{
			super();
			cType = type;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<CheckBox>  vCB = E2_SelectAllNoneInvert_New.this.get_vAllCheckboxes();
			
			
			if (vCB==null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Systemfehler: Das Listenobjekt ist unbekannt !")));
				return;
			}
			
			
			for (int i=0;i<vCB.size();i++)
			{
				if (this.cType.equals("ALLE")) (vCB.get(i)).setSelected(true);
				if (this.cType.equals("KEINE")) (vCB.get(i)).setSelected(false);
				if (this.cType.equals("INVERT")) (vCB.get(i)).setSelected(!(vCB.get(i)).isSelected());
			}
			
			E2_SelectAllNoneInvert_New.this.do_after_action();
		}

	}
	
}
