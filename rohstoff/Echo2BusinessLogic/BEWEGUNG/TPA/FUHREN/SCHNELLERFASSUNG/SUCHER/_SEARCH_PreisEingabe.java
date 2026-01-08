package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_PreisEingabe;

public class _SEARCH_PreisEingabe extends E2_RecursiveSearch_ComponentExt<FUS_PreisEingabe>
{

	public _SEARCH_PreisEingabe()
	{
		super(FUS_PreisEingabe.class);
	}

	public FUS_PreisEingabe    get_found_PreisFeld(boolean bEK) throws myException
	{
		if (bEK)
		{
			return this.get_found_PreisFeld_EK();
		}
		else
		{
			return this.get_found_PreisFeld_VK();
		}
	}

	
	public FUS_PreisEingabe get_found_PreisFeld_EK() throws myException
	{
		FUS_PreisEingabe oField = null;
		
		if (this.get_vAllComponents().size()==2)
		{
			if (this.get_vAllComponents().get(0).get_bIS_EK())
			{
				return this.get_vAllComponents().get(0);
			}
			else if (this.get_vAllComponents().get(1).get_bIS_EK())
			{
				return this.get_vAllComponents().get(1);
			}
			else
			{
				throw new myException(this,"FUS_PreisEingabe EK was not found !!");
			}
		}
		
		return oField;
	}
	
	public FUS_PreisEingabe get_found_PreisFeld_VK() throws myException
	{
		FUS_PreisEingabe oField = null;
		
		if (this.get_vAllComponents().size()==2)
		{
			if (!this.get_vAllComponents().get(0).get_bIS_EK())
			{
				return this.get_vAllComponents().get(0);
			}
			else if (!this.get_vAllComponents().get(1).get_bIS_EK())
			{
				return this.get_vAllComponents().get(1);
			}
			else
			{
				throw new myException(this,"FUS_PreisEingabe VK was not found !!");
			}
		}
		
		return oField;
	}

	
	
	
}
