package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_AnzeigeMengenEinheit;

public class _SEARCH_AnzeigeMengenEinheit extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_AnzeigeMengenEinheit()
	{
		super(FUS_AnzeigeMengenEinheit.class.getName());
	}
	 
	public FUS_AnzeigeMengenEinheit    get_Found_FUS_AnzeigeMengenEinheit()
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_AnzeigeMengenEinheit)
			{
				vRueck.add((FUS_AnzeigeMengenEinheit)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()>0)
		{
			return (FUS_AnzeigeMengenEinheit)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
}
