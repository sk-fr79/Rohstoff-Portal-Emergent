package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import echopointng.Separator;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;



/*
 * allgemeiner info-button zu kontrakten,
 * der alle clearinginfos auch in anderen listen anzeigt
 */
public abstract class BSKC__CLEARING_BUTTON extends MyE2_Button 
{

	private BSKC__AUSLASTUNG  oAuslastung = null;
	
	public BSKC__CLEARING_BUTTON(BSKC__AUSLASTUNG  Auslastung) 
	{	
		super(E2_ResourceIcon.get_RI("inforound.png"), true);
		
		this.oAuslastung = Auslastung;
		
		this.add_oActionAgent(new ownActionAgent());
				
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSKC__CLEARING_BUTTON oThis = BSKC__CLEARING_BUTTON.this;
			
			E2_BasicModuleContainer  oContainer = oThis.get_ownBasicModuleContainer();
			
		
			
			MyE2_Grid  oGrid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			if (!oThis.oAuslastung.get_bAktiv())
			{
				oThis.oAuslastung.MakeAktiv();
			}
			 
			oGrid.add(new MyE2_Label(new MyE2_String((oThis.oAuslastung.get_cEK_VK().equals("EK")?"Einkauf":"Verkauf")+"-Kontraktpositions-Infos:"),new E2_FontBold()),2,E2_INSETS.I_2_2_2_2);
			
			oGrid.add(new Separator(),2,E2_INSETS.I_2_2_2_2);
			
			oGrid.add(new MyE2_Label(new MyE2_String("Menge:")),1,E2_INSETS.I_2_2_2_2);
			oGrid.add(new MyE2_Label(oThis.oAuslastung.get_cMengeKontrakt(0)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_20_2_2_2));

			oGrid.add(new MyE2_Label(new MyE2_String("Zuordnung zu "+(oThis.oAuslastung.get_cEK_VK().equals("EK")?"Verkauf:":"Einkauf:"))),1,E2_INSETS.I_2_2_2_2);
			oGrid.add(new MyE2_Label(oThis.oAuslastung.get_cMengeGegenSeite(0)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_20_2_2_2));
			
			oGrid.add(new MyE2_Label(new MyE2_String("Zuordnung zu Lager:")),1,E2_INSETS.I_2_2_2_2);
			oGrid.add(new MyE2_Label(oThis.oAuslastung.get_cMengeLager(0)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_20_2_2_2));
			
			oGrid.add(new MyE2_Label(new MyE2_String("Bereits in Fuhren (Plan/Echt):")),1,E2_INSETS.I_2_2_2_2);
			oGrid.add(new MyE2_Label(oThis.oAuslastung.get_cMengeFuhreGesamtPlan_oder_Echt(0)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_20_2_2_2));
			
			oGrid.add(new MyE2_Label(new MyE2_String("Bereits in Fuhren Echt:")),1,E2_INSETS.I_2_2_2_2);
			oGrid.add(new MyE2_Label(oThis.oAuslastung.get_cMengeFuhreGesamt_Echt(0)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_20_2_2_2));
			
			oGrid.add(new Separator(),2,E2_INSETS.I_2_2_2_2);
			
			
			double dRest = oThis.oAuslastung.get_dMengeKontrakt()-oThis.oAuslastung.get_dMengeFuhreGesamtPlan_oder_Echt();
			if (dRest > 0)
			{
				oGrid.add(new MyE2_Label(new MyE2_String("Planungs-Restmenge: "), new E2_FontBold(2)),1,E2_INSETS.I_2_2_2_2);
				oGrid.add(new MyE2_Label(MyNumberFormater.formatDez(dRest, 0,true), new E2_FontBold(2)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_20_2_2_2));
			}
			else
			{
				oGrid.add(new MyE2_Label(new MyE2_String("Planungs-Restmenge: "), new E2_FontBold(2)),   MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, Color.RED, 1));
				oGrid.add(new MyE2_Label(MyNumberFormater.formatDez(dRest, 0,true), new E2_FontBold(2)),MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2, Color.RED, 1));
				if (oThis.oAuslastung.get_oVPOS_KON().get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).is_UEBERLIEFER_OK_YES())
				{
					oGrid.add(new MyE2_Label(new MyE2_String("Überlieferung ERLAUBT"), new E2_FontBold(2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, Color.GREEN, 2));
				}
				else
				{
					oGrid.add(new MyE2_Label(new MyE2_String("Überlieferung VERBOTEN"), new E2_FontBold(2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, Color.RED, 2));
				}
			}
			
			oContainer.add(oGrid, E2_INSETS.I_5_5_5_5);
			
			oContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(400), new MyE2_String("Clearing-Infos"));
		}
	}
	
	
	
	public abstract E2_BasicModuleContainer get_ownBasicModuleContainer() throws myException;
	
	
	
}
