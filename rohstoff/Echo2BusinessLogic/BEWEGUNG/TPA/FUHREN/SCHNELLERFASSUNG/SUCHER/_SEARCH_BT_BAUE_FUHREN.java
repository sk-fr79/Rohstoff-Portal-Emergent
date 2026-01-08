package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.FUS_BT_BAUE_FUHREN;

public class _SEARCH_BT_BAUE_FUHREN extends E2_RecursiveSearch_AB_Basis
{
	public _SEARCH_BT_BAUE_FUHREN()
	{
		super(FUS_BT_BAUE_FUHREN.class.getName());
	}
	 
	public FUS_BT_BAUE_FUHREN    get_Found_FUS_BT_BAUE_FUHREN()
	{
		Vector<Component> vRueck = new Vector<Component>();
		
		for (int i=0;i<this.get_vAllComponents().size();i++)
		{
			if (this.get_vAllComponents().get(i) instanceof FUS_BT_BAUE_FUHREN)
			{
				vRueck.add((FUS_BT_BAUE_FUHREN)this.get_vAllComponents().get(i));
			}
		}
		if (vRueck.size()>0)
		{
			return (FUS_BT_BAUE_FUHREN)vRueck.get(0);
		}
		else
		{
			return null;
		}
	}
	
	
}
