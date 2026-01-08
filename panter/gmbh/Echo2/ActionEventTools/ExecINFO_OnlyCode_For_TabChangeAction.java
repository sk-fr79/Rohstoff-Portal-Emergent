package panter.gmbh.Echo2.ActionEventTools;

import panter.gmbh.Echo2.components.MyE2_TabbedPane.Button_TabComponent_ActionAgent;

public class ExecINFO_OnlyCode_For_TabChangeAction extends ExecINFO 
{
	private Button_TabComponent_ActionAgent oAB = null;
	/*
	 * wird benutzt, um in tabwechsel-events die infos ueber den tab zu transportieren
	 */
	public ExecINFO_OnlyCode_For_TabChangeAction(Button_TabComponent_ActionAgent AB) 
	{
		super(null, true);
		this.oAB = AB;
		
	}

	public Button_TabComponent_ActionAgent get_oAB() {
		return oAB;
	}

}
