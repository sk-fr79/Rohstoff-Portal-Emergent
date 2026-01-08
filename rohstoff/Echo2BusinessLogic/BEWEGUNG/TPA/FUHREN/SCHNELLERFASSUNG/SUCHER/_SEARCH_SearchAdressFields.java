package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_VectorStandardElement;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchAdresse;

public class _SEARCH_SearchAdressFields extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_SearchAdressFields()
	{
		super(FUS_SearchAdresse.class.getName());
	}
	
	public FUS_SearchAdresse    get_Found_AdressField(boolean bEK) throws myException
	{
		if (bEK)
		{
			return this.get_Found_EK_AdressField();
		}
		else
		{
			return this.get_Found_VK_AdressField();
		}
	}
	
	
	public FUS_SearchAdresse    get_Found_EK_AdressField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchAdresse)
			{
				vRueck.add((FUS_SearchAdresse)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchAdresse)vRueck.get_EK_Element();
		}
		else
		{
			return null;
		}
	}
	
	
	public FUS_SearchAdresse    get_Found_VK_AdressField() throws myException
	{
		FUS_VectorStandardElement vRueck = new FUS_VectorStandardElement();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_SearchAdresse)
			{
				vRueck.add((FUS_SearchAdresse)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==2)
		{
			return (FUS_SearchAdresse)vRueck.get_VK_Element();
		}
		else
		{
			return null;
		}
	}

	
}
