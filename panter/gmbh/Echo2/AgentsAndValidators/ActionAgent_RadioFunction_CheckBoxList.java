package panter.gmbh.Echo2.AgentsAndValidators;

import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.exceptions.myException;





/*
 * actionagent macht aus einer gruppe von Checkboxen ein
 * radio-button-feld
 */
public class ActionAgent_RadioFunction_CheckBoxList extends XX_ActionAgent
{
	private Vector<MyE2_CheckBox> 	vCheckBoxes = new Vector<MyE2_CheckBox>();
	private boolean 				bAllUnselectedAllowed = false;
	private boolean 				bAllDisabled = false;
	
	
	
	public ActionAgent_RadioFunction_CheckBoxList(boolean bAllUnselectedAllowed)
	{
		super();
		this.bAllUnselectedAllowed= bAllUnselectedAllowed;
	}
	
	public void add_CheckBox(MyE2_CheckBox oCB)
	{
		this.vCheckBoxes.add(oCB);
		
		if (!oCB.get_vActionAgents().contains(this))
		{
			oCB.add_oActionAgent(this);
		}
		
		if (this.bAllDisabled)
			try {
				oCB.set_bEnabled_For_Edit(false);
			} catch (myException e) {
				oCB.setEnabled(false);
			}
	}

	public void add_CheckBox(Vector<MyE2_CheckBox> vCB)
	{
		for (MyE2_CheckBox oCB: vCB)
		{
			this.add_CheckBox(oCB);
		}
	}
	
	
	
	public void add_v_cb(Vector<? extends MyE2_CheckBox> vCB)
	{
		for (MyE2_CheckBox oCB: vCB)
		{
			this.add_CheckBox(oCB);
		}
	}
	

	public void executeAgentCode(ExecINFO oExecInfo)
	{
		MyE2_CheckBox oCB = (MyE2_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource();
		
		for (int i=0;i<this.vCheckBoxes.size();i++)
		{
			MyE2_CheckBox oCB1 = (MyE2_CheckBox)this.vCheckBoxes.get(i);
			
			if (oCB!=oCB1)
			{
				oCB1.setSelected(false);
			}
			else
			{
				if (!this.bAllUnselectedAllowed)
					oCB.setSelected(true);               // dann darf das geklickte nicht ausgeschaltet werden
			}
		}
	}
	
	/**
	 * 
	 * Möglichkeit den Eventhandler passiv auszulösen
	 * @param oExecInfo
	 * @param bPassiv
	 */
	public void executeAgentCodePassiv(ExecINFO oExecInfo){
		
		MyE2_CheckBox oCB = null;
		try {
			oCB = (MyE2_CheckBox) oExecInfo.get_MyActionEvent().getSource();
		} catch (Exception e) {
			return;
		}
		
		for (int i=0;i<this.vCheckBoxes.size();i++)
		{
			MyE2_CheckBox oCB1 = (MyE2_CheckBox)this.vCheckBoxes.get(i);
			
			if (oCB!=oCB1)
			{
				oCB1.setSelected(false);
			}
			else
			{
				if (!this.bAllUnselectedAllowed)
					oCB.setSelected(true);               // dann darf das geklickte nicht ausgeschaltet werden
			}
		}
	}


	/*
	 * schaltet alle elemente disabled
	 */
	public void set_AllDisabled()
	{
		for (int i=0;i<this.vCheckBoxes.size();i++)
		{
			MyE2_CheckBox oCB = (MyE2_CheckBox)this.vCheckBoxes.get(i);
			try {
				oCB.set_bEnabled_For_Edit(false);
			} catch (myException e) {
				oCB.setEnabled(false);
			}
		}
		this.bAllDisabled = true;
	}

	/*
	 * schaltet alle elemente enabled
	 */
	public void set_AllEnabled()
	{
		for (int i=0;i<this.vCheckBoxes.size();i++)
		{
			MyE2_CheckBox oCB = (MyE2_CheckBox)this.vCheckBoxes.get(i);
			try {
				oCB.set_bEnabled_For_Edit(true);
			} catch (myException e) {
				oCB.setEnabled(true);
			}
		}
		this.bAllDisabled = false;
	}

	/**
	 * gibt zurück ob alle Checkboxen mit der set_AllDisabled()-Funktion ausgeschaltet wurden. 
	 * 
	 * @author manfred
	 * @date   11.11.2015
	 *
	 * @return
	 */
	public boolean get_AllDisabled(){
		return this.bAllDisabled;
	}
	
	
	
	/*
	 * schaltet alle elemente disabled
	 */
	public void set_AllUnselected()
	{
		for (int i=0;i<this.vCheckBoxes.size();i++)
		{
			MyE2_CheckBox oCB = (MyE2_CheckBox)this.vCheckBoxes.get(i);
			oCB.setSelected(false);
		}
	}

	
	public Vector<MyE2_CheckBox> get_vSammler()
	{
		return this.vCheckBoxes;
	}
	

	
	/**
	 * setzt den Wert der Checkbox des Watchdogs
	 * @author manfred
	 * @date 19.07.2016
	 *
	 * @param cb
	 * @param checked
	 */
	public void setCheckbox( MyE2_CheckBox cb, boolean checked){
		this.executeAgentCodePassiv(new ExecINFO(new MyActionEvent(	new ActionEvent(cb, null)), checked));
	}

}
