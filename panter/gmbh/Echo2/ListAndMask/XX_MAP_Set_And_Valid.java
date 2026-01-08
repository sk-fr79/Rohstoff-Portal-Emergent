package panter.gmbh.Echo2.ListAndMask;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


/*
 * klassen werden in die componentmap uebergeben und werden am ende der methode fill_ComponentMapFromDB() ausgefuehrt 
 */
public abstract class XX_MAP_Set_And_Valid
{
	
	public static int USE_IN_MASK_KLICK_ACTION = 1;
	public static int USE_IN_MASK_LOAD_ACTION = 2;
	public static int USE_IN_MASK_VALID_ACTION = 4;
	
	//2014-01-24: weitere status: unbound_click
	public static int USE_IN_MASK_UNBOUND_KLICK_ACTION = 8;
	
	
	/*
	 * bActive: wird interaktiv innerhalb der maske genutzt, sonst beim Aufbau der Maske nach dmem Laden der Daten
	 */
	public abstract MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, 	int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException;
	public abstract MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(	E2_ComponentMAP oMAP, 	int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException;
	public abstract MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(		E2_ComponentMAP oMAP, 	int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException;
	public abstract MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(		E2_ComponentMAP oMAP, 	int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException;
	public abstract MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(	E2_ComponentMAP oMAP, 	int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException;
		
	

	
	/**
	 * 
	 * @param oMAP
	 * @param cHASH_KEY
	 * @return s Vector with Components from all componentmap in masks with this map, that have hashkey cHASH_KEY
	 */
	public Vector<MyE2IF__Component>  get_ComponentsInAllMaskMaps(E2_ComponentMAP oMAP, String cHASH_KEY)
	{
		Vector<MyE2IF__Component>  vRueck = new Vector<MyE2IF__Component>();
		
		//jetzt diesen und alle anderen Componentmaps dieser maske durchsuchen nach componenten dieses hashs 
		if (oMAP.get(cHASH_KEY)!=null)
		{
			vRueck.add(oMAP.get(cHASH_KEY));
		}
		
		for (E2_ComponentMAP MAP: oMAP.get_E2_vCombinedComponentMAPs())
		{
			if (MAP.get(cHASH_KEY)!=null)
			{
				if (!vRueck.contains(MAP.get(cHASH_KEY)))
				{
					vRueck.add(MAP.get(cHASH_KEY));
				}
			}
		}
		return vRueck;
	}

	
	/**
	 * 
	 * @param oMAP
	 * @param cHASH_KEY
	 * @return s            Single found component with used key oder throws exception
	 * @throws myException
	 */
	public MyE2IF__Component  get_ComponentWithKeyInMask(E2_ComponentMAP oMAP, String cHASH_KEY) throws myException
	{
		Vector<MyE2IF__Component> vComp = this.get_ComponentsInAllMaskMaps(oMAP, cHASH_KEY);
		
		if (vComp.size()!=1)
		{
			throw new myException("XX_ComponentMAP_interactiv_settings:Cannot find singular component with hashkey :"+cHASH_KEY);
		}
		else
		{
			return vComp.get(0);
		}
	}
	

	
}
