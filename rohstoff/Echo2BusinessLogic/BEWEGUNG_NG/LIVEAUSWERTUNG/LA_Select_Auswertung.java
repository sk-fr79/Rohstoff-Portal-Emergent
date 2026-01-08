package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LIVEAUSWERTUNG;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class LA_Select_Auswertung extends MyE2_SelectField
{

	private HashMap<String, XX_ClassSammler4AuswerteZentrale>  	hmAuswertungen = new HashMap<String, XX_ClassSammler4AuswerteZentrale>(); 
	
	private LA_BasicModuleContainer   							oContainerThisBelongsTo =  null;
	
	public LA_Select_Auswertung(LA_BasicModuleContainer ContainerThisBelongsTo) throws myException
	{
		super();
		this.setWidth(new Extent(400));
		
		this.oContainerThisBelongsTo = 	ContainerThisBelongsTo;
		
		this.fill_dropDown();	
		this.fill_hmAuswertungen();
		
		this.add_oActionAgent(new ownActionAgent());
		
	}

	
	private void fill_dropDown() throws myException
	{
		String[][] cListenInhalt = new String[3][2];
		
		cListenInhalt[0][0] = "<keine Auswertung>";  			cListenInhalt[0][1] = "";
		cListenInhalt[1][0] = "Alle Atome anzeigen";  			cListenInhalt[1][1] = "ALLE_ATOME";
		cListenInhalt[2][0] = "Lagerbewegungen";  				cListenInhalt[2][1] = "LAGER_BEWEGUNGEN";
		
		
		this.set_ListenInhalt(cListenInhalt, false);
		
	}
	
	
	private void fill_hmAuswertungen() throws myException
	{
		this.hmAuswertungen.put("ALLE_ATOME", new LA_Auswertung_ALLE_ATOME());
		this.hmAuswertungen.put("LAGER_BEWEGUNGEN", new LA_Auswertung_BEWEGUNGEN_AUF_LAGER());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			String cActualValue = LA_Select_Auswertung.this.get_ActualWert();
			
			if (S.isEmpty(cActualValue))
			{
				LA_Select_Auswertung.this.oContainerThisBelongsTo.ClearModulToBasic();
			}
			else
			{
				XX_ClassSammler4AuswerteZentrale oZielModul = LA_Select_Auswertung.this.hmAuswertungen.get(cActualValue);
				LA_Select_Auswertung.this.oContainerThisBelongsTo.__fuelle_Ansicht(oZielModul);
			}
			
		}
	}
	
	
}
