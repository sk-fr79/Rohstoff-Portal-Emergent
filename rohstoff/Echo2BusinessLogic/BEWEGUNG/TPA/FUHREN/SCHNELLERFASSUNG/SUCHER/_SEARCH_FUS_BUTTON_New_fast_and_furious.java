package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_BUTTON_New_fast_and_furious;

public class _SEARCH_FUS_BUTTON_New_fast_and_furious extends E2_RecursiveSearch_ComponentExt<FUS_BUTTON_New_fast_and_furious>
{



	public _SEARCH_FUS_BUTTON_New_fast_and_furious()
	{
		super(FUS_BUTTON_New_fast_and_furious.class);
	}

	public FUS_BUTTON_New_fast_and_furious get_FoundButton() throws myException
	{
		if (this.get_vAllComponents().size()==1)
		{
			return this.get_vAllComponents().get(0);
		}
		return null;
	}
	
}
