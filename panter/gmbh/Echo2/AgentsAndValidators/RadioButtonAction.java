package panter.gmbh.Echo2.AgentsAndValidators;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;





/*
 * actionagent macht aus einer gruppe von Checkboxen ein
 * radio-button-feld
 */
public class RadioButtonAction extends XX_ActionAgent {

	private VEK<CheckBox> 	vCheckBoxes = new VEK<CheckBox>();
	private boolean 		bAllUnselectedAllowed = false;
	
	
	
	public RadioButtonAction(boolean bAllUnselectedAllowed)	{
		super();
		this.bAllUnselectedAllowed= bAllUnselectedAllowed;
	}
	
	public RadioButtonAction _addCheckBox(CheckBox oCB) throws myException 	{
		if (oCB instanceof E2_IF_Handles_ActionAgents) {
			this.vCheckBoxes.add(oCB);
			E2_IF_Handles_ActionAgents cbAct = (E2_IF_Handles_ActionAgents)oCB;
			
			if (!cbAct.get_vActionAgents().contains(this)) {
				cbAct.add_oActionAgent(this);
			}
		} else {
			throw new myException(this, "only objects from type E2_IF_Handles_ActionAgents are allowed !");
		}
		return this;
	}


	public void executeAgentCode(ExecINFO oExecInfo) 	{
		CheckBox oCB = (CheckBox)oExecInfo.get_MyActionEvent().getSource();
		
		for (int i=0;i<this.vCheckBoxes.size();i++) {
			CheckBox oCB1 = (CheckBox)this.vCheckBoxes.get(i);
			
			if (oCB!=oCB1){
				oCB1.setSelected(false);
			} else {
				if (!this.bAllUnselectedAllowed) {
					oCB.setSelected(true);               // dann darf das geklickte nicht ausgeschaltet werden
				}
			}
		}
	}
	
	
	
	/*
	 * schaltet alle elemente disabled
	 */
	public void set_AllUnselected()	{
		for (int i=0;i<this.vCheckBoxes.size();i++)		{
			((CheckBox)this.vCheckBoxes.get(i)).setSelected(false);
		}
	}


	
	/**
	 * setzt den Wert der Checkbox des Watchdogs
	 * @author manfred
	 * @date 19.07.2016
	 *
	 * @param cb
	 * @param checked
	 */
	public void setCheckbox( CheckBox cb, boolean checked){
		this.executeAgentCode(new ExecINFO(new MyActionEvent(	new ActionEvent(cb, null)), checked));
	}

	public VEK<CheckBox> getvCheckBoxes() {
		return vCheckBoxes;
	}

}
