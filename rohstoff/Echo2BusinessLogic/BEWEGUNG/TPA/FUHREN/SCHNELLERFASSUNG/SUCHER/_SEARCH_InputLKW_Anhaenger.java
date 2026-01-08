package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputLKW_Anhaenger;

public class _SEARCH_InputLKW_Anhaenger extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_InputLKW_Anhaenger()
	{
		super(FUS_InputLKW_Anhaenger.class.getName());
	}
	
	public FUS_InputLKW_Anhaenger    get_Found_FUS_InputLKW_Anhaenger()
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_InputLKW_Anhaenger)
			{
				vRueck.add((FUS_InputLKW_Anhaenger)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==1)    //es kann nur einen geben   
		{
			return (FUS_InputLKW_Anhaenger)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
	
}
