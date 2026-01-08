package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchArtikelBez;

public class _SEARCH_SearchSortenFields extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_SearchSortenFields()
	{
		super(FUS_SearchArtikelBez.class.getName());
	}
	
	public FUS_SearchArtikelBez    get_Found_SortenField(boolean bEK) throws myException
	{
		if (bEK)
		{
			return this.get_Found_EK_SortenField();
		}
		else
		{
			return this.get_Found_VK_SortenField();
		}
	}


	public FUS_SearchArtikelBez    get_Found_EK_SortenField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchArtikelBez)
			{
				vRueck.add((FUS_SearchArtikelBez)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchArtikelBez)vRueck.get_EK_Element();
		}
		else
		{
			return null;
		}
	}
	
	
	public FUS_SearchArtikelBez    get_Found_VK_SortenField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchArtikelBez)
			{
				vRueck.add((FUS_SearchArtikelBez)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchArtikelBez)vRueck.get_VK_Element();
		}
		else
		{
			return null;
		}
	}

	
}
