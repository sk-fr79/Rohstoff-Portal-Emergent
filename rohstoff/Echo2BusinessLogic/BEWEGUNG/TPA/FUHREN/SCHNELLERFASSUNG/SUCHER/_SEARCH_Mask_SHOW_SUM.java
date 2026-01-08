package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import java.util.Vector;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_Mask_SHOW_SUM;

public class _SEARCH_Mask_SHOW_SUM extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_Mask_SHOW_SUM()
	{
		super(FUS_Mask_SHOW_SUM.class.getName());
	}
	 
	public FUS_Mask_SHOW_SUM    get_Found_FUS_Mask_SHOW_SUM()
	{
		Vector<MyE2IF__Component> vRueck = new Vector<MyE2IF__Component>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_Mask_SHOW_SUM)
			{
				vRueck.add((FUS_Mask_SHOW_SUM)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()>0)
		{
			return (FUS_Mask_SHOW_SUM)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
}
