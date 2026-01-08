package panter.gmbh.Echo2.Container;

import panter.gmbh.indep.exceptions.myException;

public interface IF_BasicModuleContainer_ADD_ON_Component
{

	/*
	 * methode implementiert die abfrage, ob die komponente angezeigt wird oder nicht
	 * (z.B. fuer admins wirds angezeigt, sonst nicht)
	 */
	public abstract boolean get_bIsShown() throws myException;

	public abstract void set_cMODULKENNER(String cModulKenner);

	public abstract String get_cMODULKENNER();

	
	public abstract void add_cZusatzMODULKENNER(String cModulKenner);
	
	
}