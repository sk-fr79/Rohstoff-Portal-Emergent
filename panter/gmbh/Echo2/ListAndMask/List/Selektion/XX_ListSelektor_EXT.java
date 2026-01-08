package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;

public abstract class XX_ListSelektor_EXT extends XX_ListSelektor {

	/**
	 * zwei vektoren mit Agenten, die der jeweiligen Selektion voran- und nachgestellt werden
	 * Sie koennen benutzt werden, um die Selektoren bei Auswahl gegenseitig zu beeinflussen
	 */
	private Vector<XX_ActionAgent>  vAgents4ActiveComponentsBeforeSelection = new Vector<XX_ActionAgent>();
	private Vector<XX_ActionAgent>  vAgents4ActiveComponentsAfterSelection = new Vector<XX_ActionAgent>();
	
	
	public Vector<XX_ActionAgent> get_vAgents4ActiveComponentsBeforeSelection() {
		return vAgents4ActiveComponentsBeforeSelection;
	}
	public Vector<XX_ActionAgent> get_vAgents4ActiveComponentsAfterSelection() {
		return vAgents4ActiveComponentsAfterSelection;
	}
	
	
	
}
