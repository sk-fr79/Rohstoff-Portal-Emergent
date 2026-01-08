package panter.gmbh.Echo2.ActionEventTools;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;

public abstract class XX_ActionAgentWhenCloseWindow extends XX_ActionAgent 
{
	private E2_BasicModuleContainer 	oContainer = null;
	private boolean					 	bWasDone = false;
	
    
	
	public XX_ActionAgentWhenCloseWindow(E2_BasicModuleContainer container) 
	{
		this.oContainer = container;
	}

	public boolean get_bWasDone() 
	{
		return bWasDone;
	}
	
	public void set_bWasDone(boolean wasDone) 
	{
		bWasDone = wasDone;
	}
	
	public E2_BasicModuleContainer get_oContainer() 
	{
		return oContainer;
	}
	
	public void set_oContainer(E2_BasicModuleContainer container) 
	{
		oContainer = container;
	}
	
	

}
