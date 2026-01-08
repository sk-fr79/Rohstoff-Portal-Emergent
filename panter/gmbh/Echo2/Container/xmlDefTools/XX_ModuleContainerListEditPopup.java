package panter.gmbh.Echo2.Container.xmlDefTools;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


/*
 * abstrakte klasse zum bau von popup-modulen, die in xml-definierte listen
 * eingebaut werden koennen
 */
public abstract class XX_ModuleContainerListEditPopup extends E2_BasicModuleContainer
{
	
	private 	E2_NavigationList 	oNavigationListFromListModule = null;
	private 	String 				cMODULKENNER_FROM_LISTMODULE = null;
	private		MyE2_Column 		oContainer = new MyE2_Column();

	
	
	public XX_ModuleContainerListEditPopup(	)
	{
		super();
		this.add(this.oContainer);
	}
	
	

	public String 				get_cMODULKENNER_FROM_LISTMODULE()				{		return cMODULKENNER_FROM_LISTMODULE;	}
	public E2_NavigationList 	get_oNavigationListFromListModule()				{		return oNavigationListFromListModule;	}

	public void START(	E2_NavigationList 	onavigationList,
						String 				cmODULKENNER_FROM_LISTMODULE) throws myException
	{
		this.cMODULKENNER_FROM_LISTMODULE=cmODULKENNER_FROM_LISTMODULE;
		this.oNavigationListFromListModule=onavigationList;
		this.build_Content();
	}

	public MyE2_Column get_oContainer()
	{
		return oContainer;
	}
	
	
	public abstract MyString get_Check_CanBeShown();
	public abstract void build_Content() throws myException;




	
	
	
}
