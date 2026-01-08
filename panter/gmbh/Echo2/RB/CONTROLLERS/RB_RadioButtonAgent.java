package panter.gmbh.Echo2.RB.CONTROLLERS;

import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;





/*
 * actionagent macht aus einer gruppe von Checkboxen ein
 * radio-button-feld
 */
public class RB_RadioButtonAgent extends XX_ActionAgent
{
	private Vector<CheckBox> 		vCheckBoxes = new Vector<CheckBox>();
	private boolean 				bAllUnselectedAllowed = false;
	private boolean 				bAllDisabled = false;
	
	
	
	public RB_RadioButtonAgent(boolean bAllUnselectedAllowed) {
		super();
		this.bAllUnselectedAllowed= bAllUnselectedAllowed;
	}
	
	public RB_RadioButtonAgent _addCb(CheckBox oCB) throws myException {
		this.vCheckBoxes.add(oCB);
		
		if (oCB instanceof E2_IF_Handles_ActionAgents) {
			if (!((E2_IF_Handles_ActionAgents)oCB).get_vActionAgents().contains(this)) {
				((E2_IF_Handles_ActionAgents)oCB).add_oActionAgent(this);
			}
			
			if (this.bAllDisabled) {
				this.ownSetEnabled(oCB, false);
			}
			return this;
		}
		throw new myException(this,"Type is not allowed !");
	}
	
	

	public RB_RadioButtonAgent _addCb(Vector<CheckBox> vCB) throws myException { 
		for (CheckBox oCB: vCB) {
			this._addCb(oCB);
		}
		return this;
	}
	
	

	public void executeAgentCode(ExecINFO oExecInfo) {
		CheckBox oCB = (CheckBox)oExecInfo.get_MyActionEvent().getSource();
		
		for (CheckBox c: this.vCheckBoxes)	{
			if (c!=oCB)	{
				c.setSelected(false);
			}  else {
				if (!this.bAllUnselectedAllowed) {
					oCB.setSelected(true);               // dann darf das geklickte nicht ausgeschaltet werden
				}
			}
		}
	}
	
	/**
	 * 
	 * Möglichkeit den Eventhandler passiv auszulösen
	 * @param oExecInfo
	 * @param bPassiv
	 */
	public void executeAgentCodePassiv(ExecINFO oExecInfo) throws myException{
		
		CheckBox oCB = null;
		try {
			oCB = (CheckBox) oExecInfo.get_MyActionEvent().getSource();
		} catch (Exception e) {
			throw new myException(this, "Unknown error !");
		}
	
		if (oCB == null) {
			throw new myException(this, "Unknown error !");
		}
		
		
		for (CheckBox c: this.vCheckBoxes)	{
			if (c!=oCB)	{
				c.setSelected(false);
			}  else {
				if (!this.bAllUnselectedAllowed) {
					oCB.setSelected(true);               // dann darf das geklickte nicht ausgeschaltet werden
				}
			}
		}
	}


	/*
	 * schaltet alle elemente disabled
	 */
	public void set_AllDisabled() throws myException 	{
		for (CheckBox oCB: vCheckBoxes) {
			this.ownSetEnabled(oCB, false);
		}
		this.bAllDisabled = true;
	}

	private void ownSetEnabled(Component cb, boolean bEnabled) throws myException {
		if (cb instanceof MyE2IF__Component) {
			((MyE2IF__Component)cb).set_bEnabled_For_Edit(bEnabled);
		} else {
			cb.setEnabled(false);
		}
	}
	
	/*
	 * schaltet alle elemente enabled
	 */
	public void set_AllEnabled() throws myException {
		for (CheckBox oCB: vCheckBoxes) {
			this.ownSetEnabled(oCB, true);
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
	public boolean isAllDisabled(){
		return this.bAllDisabled;
	}
	
	
	
	/*
	 * schaltet alle elemente disabled
	 */
	public RB_RadioButtonAgent _setAllUnselected() 	{
		for (CheckBox cb: this.vCheckBoxes) {
			cb.setSelected(false);
		}
		return this;
	}

	
	public Vector<CheckBox> getCheckboxes(){
		return this.vCheckBoxes;
	}
	

	
	/**
	 * setzt den Wert der Checkbox des Watchdogs
	 * @author manfred
	 * @date 19.07.2016
	 *
	 * @param cb
	 * @param checked
	 * @throws myException 
	 */
	public void setCheckbox( CheckBox cb, boolean checked) throws myException{
		this.executeAgentCodePassiv(new ExecINFO(new MyActionEvent(	new ActionEvent(cb, null)), checked));
	}

}
