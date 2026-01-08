package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_Wiegeschein;

public class _SEARCH_Wiegeschein extends E2_RecursiveSearch_ComponentExt<FUS_Wiegeschein>
{

	public _SEARCH_Wiegeschein()
	{
		super(FUS_Wiegeschein.class);
	}

	public FUS_Wiegeschein    get_found_Wiegeschein(boolean bEK) throws myException
	{
		if (bEK)
		{
			return this.get_found_Wiegeschein_EK();
		}
		else
		{
			return this.get_found_Wiegeschein_VK();
		}
	}

	
	public FUS_Wiegeschein get_found_Wiegeschein_EK() throws myException
	{
		FUS_Wiegeschein oField = null;
		
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
				throw new myException(this,"FUS_Wiegeschein EK was not found !!");
			}
		}
		
		return oField;
	}
	
	public FUS_Wiegeschein get_found_Wiegeschein_VK() throws myException
	{
		FUS_Wiegeschein oField = null;
		
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
				throw new myException(this,"FUS_Wiegeschein VK was not found !!");
			}
		}
		
		return oField;
	}

	
}
