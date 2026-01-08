package panter.gmbh.Echo2.ActionEventTools;

import java.util.Vector;

public class ExecINFO_doAction extends ExecINFO 
{

	public ExecINFO_doAction(MyActionEvent oActionEvent, boolean eventWasPassive, int iNummerImActionAgentVector, Vector<XX_ActionAgent> vRestActionAgentsImStack) 
	{
		super(oActionEvent, eventWasPassive);
		this.set_iNummerInActionAgentStack(iNummerImActionAgentVector);
		this.get_vActionAgentsREST().addAll(vRestActionAgentsImStack);
	}

}
