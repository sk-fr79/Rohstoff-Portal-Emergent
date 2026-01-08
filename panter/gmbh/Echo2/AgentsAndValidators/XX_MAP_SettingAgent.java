package panter.gmbh.Echo2.AgentsAndValidators;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;


/*
 * klassen, die es ermoeglichen, eine maske einzustellen bevor ein
 * bestimmter arbeitsgang erfolgt und nachdem ein bestimmter arbeitsgang zuende ist
 */
public abstract class XX_MAP_SettingAgent
{
	
	public void doSettings_BEFORE(E2_ComponentMAP oMap, String cSTATUS_MASKE) throws myException
	{
		if (cSTATUS_MASKE == null)
			throw new myException("E2_MAP_ValidBeforeSAVE:doSettings_BEFORE:Status NULL is not allowed !!");

		
		if (!(	cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW) ||
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY) || 
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_UNDEFINED)
			))
			throw new myException("E2_MAP_SettingAgent:doSettings_BEFORE:Status is not allowed !!");

		this.__doSettings_BEFORE(oMap,cSTATUS_MASKE);
	}

	
	
	public void doSettings_AFTER(E2_ComponentMAP oMap, String cSTATUS_MASKE) throws myException
	{
		if (cSTATUS_MASKE == null)
			throw new myException("E2_MAP_ValidBeforeSAVE:doSettings_AFTER:Status NULL is not allowed !!");

		if (!(	cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW) ||
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY) || 
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_UNDEFINED)
			))
			throw new myException("E2_MAP_SettingAgent:doSettings_BEFORE:Status is not allowed !!");

		 this.__doSettings_AFTER(oMap,cSTATUS_MASKE);
	}

	
	
	public abstract void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException;
	public abstract void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException;
	
}
