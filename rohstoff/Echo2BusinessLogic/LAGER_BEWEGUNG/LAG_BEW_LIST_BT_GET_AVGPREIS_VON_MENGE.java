package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;


import java.math.BigDecimal;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerBewegungHandler;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerPreisHandler;

public class LAG_BEW_LIST_BT_GET_AVGPREIS_VON_MENGE extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long 						serialVersionUID = 405872297902299499L;
	private E2_NavigationList  						oNaviList = null;
	private LAG_BEW_LIST_Selector 					oListSelector = null;
	private LAG_BEW_LIST_Panel_MengenErmittlung 	oPanelParent = null;
	private boolean 								bRecalculatePrices = false;
	 
	public LAG_BEW_LIST_BT_GET_AVGPREIS_VON_MENGE(	E2_NavigationList onavigationList, 
											LAG_BEW_LIST_Selector ListSelector, 
											LAG_BEW_LIST_Panel_MengenErmittlung oPanel)
	{
		//super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		super("Ermitteln");
		this.oNaviList = onavigationList;
		this.oListSelector =  ListSelector;
		this.oPanelParent = oPanel;
		
		
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_BEW_CALC_AVG_PRICE_FOR_QUANTITY"));
		
		this.setToolTipText(new MyE2_String("Ermitteln des Durchschnittspreises für eine gegebene Menge.").CTrans());
	}
	
	

	public void setRecalculatePrices(boolean bRecalculatePrices) {
		this.bRecalculatePrices = bRecalculatePrices;
	}

	public boolean isRecalculatePrices() {
		return bRecalculatePrices;
	}



	private class ownActionAgent extends XX_ActionAgent
	{
		public ownActionAgent() {
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {

			LAG_BEW_LIST_BT_GET_AVGPREIS_VON_MENGE oThis = LAG_BEW_LIST_BT_GET_AVGPREIS_VON_MENGE.this;
			
			String cID_Artikel = bibALL.ReplaceTeilString(oThis.oListSelector.get_oSelArtikel().get_ActualWert(),".","");
			String cID_Hauptartikel = bibALL.ReplaceTeilString(oThis.oListSelector.get_oSelHauptartikel().get_ActualWert(), ".", "");
			String cID_Lager = bibALL.ReplaceTeilString(oThis.oListSelector.get_oSelLager().get_ActualWert(),".","");
			
			//löschen der zuvor gefundenen Daten
			oThis.oPanelParent.get_oMengenErmittlung().clearAuswahlListe();
			
			//oThis.oPanelParent.setErgebnis("");
			//|| bibALL.isEmpty(cID_Lager)
			if (bibALL.isEmpty(cID_Artikel) && bibALL.isEmpty(cID_Hauptartikel) ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Zur Preisbestimmung muß eine Sorte oder Hauptsorte ausgewählt sein!")));
				oThis.oNaviList._REBUILD_ACTUAL_SITE("");
				return;
			}
			
			Double dblMengeDurchschnitt = bibALL.makeDoublefromNumberString(oThis.oPanelParent.getMengeDurchschnittspreis(), ",", ".");
			if (dblMengeDurchschnitt == null){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie eine Zahl im Feld für die Menge ein!")));
				oThis.oNaviList._REBUILD_ACTUAL_SITE("");
				return;
			}
			
			BigDecimal bdAvgBetrag = new BigDecimal(dblMengeDurchschnitt.doubleValue() );
			bdAvgBetrag = bdAvgBetrag.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			
			// zuerst die Lagerverteilung automatisch durchführen

			// NEUE PREISE BERECHNEN
			if (oThis.oPanelParent.getAutoCalc()){
				LAG_LagerPreisHandler oPreise = new LAG_LagerPreisHandler(null);
				oPreise.ReorganisePriceEntries(bibALL.get_ID_MANDANT());
				//oPreise.executeSqlStatements(true);
			
				// DIE MENGEN VERTEILEN
				LAG_LagerBewegungHandler oBewegung = new LAG_LagerBewegungHandler(null);
				oBewegung.ReorganiseLagerEntries(bibALL.get_ID_MANDANT());
			}
			
			oThis.oPanelParent.get_oMengenErmittlung().ermittleDurchschnittspreisVonMenge(cID_Lager,cID_Hauptartikel, cID_Artikel, bdAvgBetrag);
			if (bibMSG.get_bIsOK())
			{
				BigDecimal bdValue = oThis.oPanelParent.get_oMengenErmittlung().get_ErmittelteMenge();
				if (bdValue == null){
					bdValue = BigDecimal.ZERO;
				}
				String sValue = bibALL.makeDez(bdValue.doubleValue(), 3, true);
				
				
				BigDecimal bdPreis = oThis.oPanelParent.get_oMengenErmittlung().get_ErmittelterPreis();
				if (bdPreis == null) {
					bdPreis = BigDecimal.ZERO;
				}
				String sValuePreis = bibALL.makeDez(bdPreis.doubleValue(), 2, true);
								
				oThis.oPanelParent.setErgebnisPreisDurchnittspreis(sValuePreis);
				oThis.oPanelParent.setErgebnisMengeDurchschnittspreis(sValue);
				oThis.oPanelParent.setMengenEinheit(oThis.oPanelParent.get_oMengenErmittlung().get_Mengeneinheit());
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Ermittlung des Durchschnittspreises wurde abgeschlossen.")));
			}
			
			
			//oThis.vAuswahlHaufen.add("1010");
			oThis.oNaviList._REBUILD_ACTUAL_SITE("");
			
			
		}

	}
	


	
	
}
