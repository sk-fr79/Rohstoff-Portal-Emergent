package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;


import java.math.BigDecimal;
import java.util.Vector;

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

public class ATOM_LAG_BEW_LIST_BT_GET_RESTMENGEN extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long 						serialVersionUID = 405872297902299499L;
	private E2_NavigationList  						oNaviList = null;
	private ATOM_LAG_BEW_LIST_Selector 					oListSelector = null;
	private ATOM_LAG_BEW_LIST_Panel_MengenErmittlung 	oPanelParent = null;
	private boolean 								bRecalculatePrices = false;
	 
	public ATOM_LAG_BEW_LIST_BT_GET_RESTMENGEN(	E2_NavigationList onavigationList, 
											ATOM_LAG_BEW_LIST_Selector ListSelector, 
											ATOM_LAG_BEW_LIST_Panel_MengenErmittlung oPanel)
	{
		//super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		super("Ermitteln");
		this.oNaviList = onavigationList;
		this.oListSelector =  ListSelector;
		this.oPanelParent = oPanel;
		
		
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LAG_BEW_CALC_MENGEN_BELOW_PRICE"));
		
		this.setToolTipText(new MyE2_String("Ermitteln aller Restmengen, die unterhalb des angegebenen Preises liegen.").CTrans());
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
			// TODO Auto-generated constructor stub
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException {

			ATOM_LAG_BEW_LIST_BT_GET_RESTMENGEN oThis = ATOM_LAG_BEW_LIST_BT_GET_RESTMENGEN.this;
			
//			String cID_Artikel = bibALL.ReplaceTeilString(oThis.oListSelector.get_oSelSorte().get_ActualWert(),".","");
//			String cID_Hauptartikel = bibALL.ReplaceTeilString(oThis.oListSelector.get_oSelHauptSorte().get_ActualWert(), ".", "");
//			String cID_Lager = bibALL.ReplaceTeilString(oThis.oListSelector.get_oSelLager().get_ActualWert(),".","");
			
			Vector<String> vIDLager = oThis.oListSelector.get_oSelLager().get_SelectedValues();
			Vector<String> vIDSorte = oThis.oListSelector.get_oSelSorte().get_SelectedValues();
			Vector<String> vIDHautpSorte = oThis.oListSelector.get_oSelHauptSorte().get_SelectedValues();

			//löschen der zuvor gefundenen Daten
			oThis.oPanelParent.get_oMengenErmittlung().clearAuswahlListe();
			
			//oThis.oPanelParent.setErgebnis("");
			//|| bibALL.isEmpty(cID_Lager)
			if ((vIDSorte == null || vIDSorte.size() == 0)
					&& (vIDHautpSorte == null || vIDHautpSorte.size() == 0) ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Zur Mengenbestimmung muß eine Sorte oder Hauptsorte ausgewählt sein!")));
				oThis.oNaviList._REBUILD_ACTUAL_SITE("");
				return;
			}
			
			Double dblMaxPreis = bibALL.makeDoublefromNumberString(oThis.oPanelParent.getPreisMax(), ",", ".");
			if (dblMaxPreis == null){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie eine Zahl im Feld für den Maximalpreis ein!")));
				oThis.oNaviList._REBUILD_ACTUAL_SITE("");
				return;
			}
			
			BigDecimal bdMaxBetrag = new BigDecimal(dblMaxPreis.doubleValue() );
			bdMaxBetrag = bdMaxBetrag.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			
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
			
//			oThis.oPanelParent.get_oMengenErmittlung().ermittleMengeZuMaximalpreis(cID_Lager,cID_Hauptartikel, cID_Artikel, bdMaxBetrag);
			oThis.oPanelParent.get_oMengenErmittlung().initMengenermittlung ( null,vIDLager,null,vIDHautpSorte,null,vIDSorte, null);
			oThis.oPanelParent.get_oMengenErmittlung().ermittleMengeZuMaximalpreis(bdMaxBetrag);
			
			if (bibMSG.get_bIsOK())
			{
				String sValue = bibALL.makeDez(oThis.oPanelParent.get_oMengenErmittlung().get_ErmittelteMenge().doubleValue(), 3, true);
				oThis.oPanelParent.setErgebnis(sValue);
				oThis.oPanelParent.setMengenEinheit(oThis.oPanelParent.get_oMengenErmittlung().get_Mengeneinheit());
				

				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Die Ermittlung der Preise wurde abgeschlossen.")));
			}
			
			//oThis.vAuswahlHaufen.add("1010");
			oThis.oNaviList._REBUILD_ACTUAL_SITE("");
			
		}
		

	}
	


	
	
}
