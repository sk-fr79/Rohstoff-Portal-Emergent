package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputDatum;

public class _SEARCH_InputDatum extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_InputDatum()
	{
		super(FUS_InputDatum.class.getName());
	}
	
	public FUS_InputDatum    get_Found_FUS_InputDatum()
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_InputDatum)
			{
				vRueck.add((FUS_InputDatum)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()>0)
		{
			return (FUS_InputDatum)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
}
