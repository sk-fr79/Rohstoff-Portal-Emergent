package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_WiegeMengeTextField;

public class _SEARCH_WiegeMengeTextField extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_WiegeMengeTextField()
	{
		super(FUS_WiegeMengeTextField.class.getName());
	}
	
	public FUS_WiegeMengeTextField    get_Found_FUS_WiegeMengeTextField()
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_WiegeMengeTextField)
			{
				vRueck.add((FUS_WiegeMengeTextField)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==1)    //es kann nur einen geben   
		{
			return (FUS_WiegeMengeTextField)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
	
}
