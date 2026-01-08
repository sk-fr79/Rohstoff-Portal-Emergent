package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchKontrakte;

public class _SEARCH_SearchKontraktFields extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_SearchKontraktFields()
	{
		super(FUS_SearchKontrakte.class.getName());
	}
	
	
	public FUS_SearchKontrakte    get_Found_KontraktField(boolean bEK) throws myException
	{
		if (bEK)
		{
			return this.get_Found_EK_KontraktField();
		}
		else
		{
			return this.get_Found_VK_KontraktField();
		}
	}

	
	
	public FUS_SearchKontrakte    get_Found_EK_KontraktField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchKontrakte)
			{
				vRueck.add((FUS_SearchKontrakte)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchKontrakte)vRueck.get_EK_Element();
		}
		else
		{
			return null;
		}
	}
	
	
	public FUS_SearchKontrakte    get_Found_VK_KontraktField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchKontrakte)
			{
				vRueck.add((FUS_SearchKontrakte)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchKontrakte)vRueck.get_VK_Element();
		}
		else
		{
			return null;
		}
	}

	
}
