package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputAnzahlContainer;

public class _SEARCH_InputAnzahlContainer extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_InputAnzahlContainer()
	{
		super(FUS_InputAnzahlContainer.class.getName());
	}
	
	public FUS_InputAnzahlContainer    get_Found_FUS_InputAnzahlContainer()
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_InputAnzahlContainer)
			{
				vRueck.add((FUS_InputAnzahlContainer)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==1)    //es kann nur einen geben   
		{
			return (FUS_InputAnzahlContainer)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
	
}
