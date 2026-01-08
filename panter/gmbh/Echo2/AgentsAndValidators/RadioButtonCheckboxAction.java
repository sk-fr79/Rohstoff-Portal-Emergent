package panter.gmbh.Echo2.AgentsAndValidators;


import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.indep.myVectors.VEK;





/**
 * 
 * @author martin
 * @date 08.07.2019
 *
 */
public class RadioButtonCheckboxAction 
{
	private VEK<CheckBox> 			checkBoxes = new VEK<CheckBox>();
	private boolean 				allowAllUnselected = false;
	
	public RadioButtonCheckboxAction(boolean p_allowAllUnselected)
	{
		super();
		this.allowAllUnselected= p_allowAllUnselected;
	}
	
	public RadioButtonCheckboxAction _addCheckBox(CheckBox cb) {
		this.checkBoxes._a(cb);
		
		cb.addActionListener((event)->{
			if (!cb.isSelected() && !isOneChecked()) {   //falls abgewaehlt und kein anderer an ist, dann bleibt die geklickte checkbox selektiert
				if (!allowAllUnselected) {
					cb.setSelected(true);
				}
			} else {
				for (CheckBox c: checkBoxes) {
					c.setSelected(false);
				}
				cb.setSelected(true);
			}
		});
		if (!this.isOneChecked()) {		//beim aufbau dann ist der erste uebergebene standard-auswahl
			cb.setSelected(true);
		}
		
		return this;
	}

	
	
	public void _addCheckBox(Vector<CheckBox> vCB) {
		for (CheckBox oCB: vCB) {
			this._addCheckBox(oCB);
		}
	}

	public void _addCheckBoxes(Vector<RB_cb> vCB) {
		for (CheckBox oCB: vCB) {
			this._addCheckBox(oCB);
		}
	}

	
	public boolean isAllowAllUnselected() {
		return allowAllUnselected;
	}

	public RadioButtonCheckboxAction _setAllowAllUnselected(boolean p_allowAllUnselected) {
		this.allowAllUnselected = p_allowAllUnselected;
		return this;
	}
	
	public boolean isOneChecked() {
		for (CheckBox c: checkBoxes) {
			if (c.isSelected()) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * alle ausschalten nur die uebergebene checkbox einschalten
	 * @author martin
	 * @date 08.07.2019
	 *
	 * @param cb
	 * @return
	 */
	public RadioButtonCheckboxAction _select(CheckBox cb) {
		for (CheckBox c: checkBoxes ) {
			c.setSelected(cb==c);
		}
		return this;
	}

	public VEK<CheckBox> getCheckBoxes() {
		return checkBoxes;
	}

}
