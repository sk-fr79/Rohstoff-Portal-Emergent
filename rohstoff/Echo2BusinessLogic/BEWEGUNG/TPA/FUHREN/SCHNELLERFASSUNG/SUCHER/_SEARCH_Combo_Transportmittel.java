package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_Combo_Transportmittel;

public class _SEARCH_Combo_Transportmittel extends E2_RecursiveSearch_ComponentExt<FUS_Combo_Transportmittel>
{

	public _SEARCH_Combo_Transportmittel()
	{
		super(FUS_Combo_Transportmittel.class);
	}

	public FUS_Combo_Transportmittel get_found_ComboBox()
	{
		FUS_Combo_Transportmittel oCombo = null;
		
		if (this.get_vAllComponents().size()==1)
		{
			oCombo = this.get_vAllComponents().get(0);
		}
		return oCombo;
	}
	
}
