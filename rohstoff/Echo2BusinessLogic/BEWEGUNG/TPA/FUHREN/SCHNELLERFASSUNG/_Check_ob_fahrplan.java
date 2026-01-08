package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_Grid_InnereErfassungsMaske;

public class _Check_ob_fahrplan
{

	private boolean bIsFahrPlan = false;
	
	public _Check_ob_fahrplan()
	{
		super();
		
		this.bIsFahrPlan = new _SEARCH_FUS_Grid_InnereErfassungsMaske().get_Found_FUS_Grid_InnereErfassungsMaske().get_bVarianteFahrplan();
		
	}

	public boolean get_bIsFahrPlan()
	{
		return bIsFahrPlan;
	}
	
	
	
}
