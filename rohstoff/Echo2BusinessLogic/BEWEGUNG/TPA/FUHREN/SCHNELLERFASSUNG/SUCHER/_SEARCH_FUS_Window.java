package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import java.util.Vector;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_Window;

public class _SEARCH_FUS_Window extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_FUS_Window()
	{
		super(FUS_Window.class.getName());
	}
	
	public FUS_Window    get_Found_FUS_Window() throws myException
	{
		Vector<FUS_Window> vRueck = new Vector<FUS_Window>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_Window)
			{
				vRueck.add((FUS_Window)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()==1)
		{
			return (FUS_Window)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}

	
}
