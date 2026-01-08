package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchAngebote;

public class _SEARCH_SearchAngebotsFields extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_SearchAngebotsFields()
	{
		super(FUS_SearchAngebote.class.getName());
	}
	
	
	public FUS_SearchAngebote    get_Found_AngebotField(boolean bEK) throws myException
	{
		if (bEK)
		{
			return this.get_Found_EK_AngebotField();
		}
		else
		{
			return this.get_Found_VK_AngebotField();
		}
	}
	

	
	
	public FUS_SearchAngebote    get_Found_EK_AngebotField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchAngebote)
			{
				vRueck.add((FUS_SearchAngebote)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchAngebote)vRueck.get_EK_Element();
		}
		else
		{
			return null;
		}
	}
	
	
	public FUS_SearchAngebote    get_Found_VK_AngebotField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchAngebote)
			{
				vRueck.add((FUS_SearchAngebote)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchAngebote)vRueck.get_VK_Element();
		}
		else
		{
			return null;
		}
	}
	
	

	
}
