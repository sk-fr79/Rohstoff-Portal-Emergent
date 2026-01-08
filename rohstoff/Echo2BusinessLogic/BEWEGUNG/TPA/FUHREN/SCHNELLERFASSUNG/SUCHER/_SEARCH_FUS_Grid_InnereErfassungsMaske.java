package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import java.util.Vector;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_Grid_ErfassteFuhren;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_Grid_InnereErfassungsMaske;

public class _SEARCH_FUS_Grid_InnereErfassungsMaske extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_FUS_Grid_InnereErfassungsMaske()
	{
		super(FUS_Grid_ErfassteFuhren.class.getName());
	}
	
	public FUS_Grid_InnereErfassungsMaske    get_Found_FUS_Grid_InnereErfassungsMaske()
	{
		Vector<FUS_Grid_InnereErfassungsMaske> 	vRueck = new Vector<FUS_Grid_InnereErfassungsMaske>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_Grid_InnereErfassungsMaske)
			{
				vRueck.add((FUS_Grid_InnereErfassungsMaske)this.get_vAllComponents().get(i));
			}
		}
		
		if (vRueck.size()==1)    //es kann nur einen geben   
		{
			return (FUS_Grid_InnereErfassungsMaske)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
}
