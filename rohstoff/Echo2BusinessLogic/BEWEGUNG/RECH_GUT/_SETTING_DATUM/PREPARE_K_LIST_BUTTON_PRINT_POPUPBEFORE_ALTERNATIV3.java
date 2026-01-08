package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_IdKvPosUndFristTage;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VkopfRG;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposRg;

//alternative berechnungsmethode des Rechnungsdatums
public class PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV3 extends E2_BasicModuleContainer_PopUp_BeforeExecute {

	private Vector<String>   					vID_VKOPF_RG = null;
	private BS_K_LIST_ActionAgent_Mail_Print  	oActionAgentPrintOrMail = null;
	private MyE2_Grid  							oGridBase = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	private HMAP<String, RechnungsZeile>  		hmRechnungsZeilen =  new HMAP<String, RechnungsZeile>();
	
	private int 								i_BaseWidth_Window = 800;
	private int 								i_BaseHeight_Window = 450;
	
	private boolean b_Preview = false;
	
	public PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV3(BS_K_LIST_ActionAgent_Mail_Print  ActionAgentPrintOrMail, boolean bPreview) throws myException
	{
		super();
		this.b_Preview = bPreview;
		
		this.oActionAgentPrintOrMail = ActionAgentPrintOrMail;
		this.add_In_ContainerEX(this.oGridBase, new Extent(this.i_BaseWidth_Window-20), new Extent(this.i_BaseHeight_Window-150), E2_INSETS.I(2,2,2,2));
		
		this.set_oExtWidth(new Extent(this.i_BaseWidth_Window));
		this.set_oExtHeight(new Extent(this.i_BaseHeight_Window));
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_ComponentGroupHorizontal oButtonLeiste = new E2_ComponentGroupHorizontal(0,this.get_oButtonForOK(),this.get_oButtonForAbbruch(),E2_INSETS.I_10_2_10_2);
		this.add(oButtonLeiste,  E2_INSETS.I_5_5_5_5);
		this.set_bShowWindowAsSplitpane(false);
		
		//2011-07-05: individuelles resizing
		this.set_oResizeHelper(new ownResizer());

	}
	
	
	@Override
	protected void doOwnCode_in_ok_button() throws myException {
		MyE2_MessageVector  oMVSammler = new MyE2_MessageVector();
		
		for (String cID_VKOPF_RG: this.hmRechnungsZeilen.keySet()) {
			oMVSammler.add_MESSAGE(this.hmRechnungsZeilen.get(cID_VKOPF_RG).pruefe_DatumsFelder());
		}
		
		if (oMVSammler.get_bIsOK()) {
			
			Vector<String>  vSQL = new Vector<String>();
			for (String cID_VKOPF_RG: this.hmRechnungsZeilen.keySet()) {
				RECORD_VKOPF_RG_SETTING_DATUM recRG = new RECORD_VKOPF_RG_SETTING_DATUM(cID_VKOPF_RG);
				HashMap<String, MyDate> hmErgebnisse = new HashMap<String, MyDate>();
				hmErgebnisse.put(_DB.VKOPF_RG$DRUCKDATUM,  				this.hmRechnungsZeilen.get(cID_VKOPF_RG).getMyDateRECHUNGSDATUM());
				hmErgebnisse.put(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM,	this.hmRechnungsZeilen.get(cID_VKOPF_RG).getMyDateZAHLUNGSDATUM());

				oMVSammler.add_MESSAGE(recRG.ErzeugeStatements_fuer_RechnungsDatum_AND_ZahlungsZiel_Alternativ(vSQL,hmErgebnisse));
			}
			
			DEBUG.System_print(vSQL);
			
			if (oMVSammler.get_bIsOK()) {
				oMVSammler.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			}
			
		}

		bibMSG.add_MESSAGE(oMVSammler);

	}

	@Override
	protected void doOwnCode_in_cancel_button() throws myException {
		
	}

	@Override
	public void doBuildContent(ExecINFO oExecInfo) throws myException {
		
		E2_Grid  oGridEingabeWerte = new E2_Grid()._setSize(100,100,100,100,100);
		
		this.oGridBase.removeAll();
		this.oGridBase.add(oGridEingabeWerte, E2_INSETS.I(0,0,0,0));
		
		GridLayoutData  oGLVorschaudruck = 	MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,10,5,10), new E2_ColorBase(), 	oGridEingabeWerte.getSize(), 1);
		GridLayoutData  oGLFinaldruck = 	MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,10,5,10), new E2_ColorAlarm(), oGridEingabeWerte.getSize(), 1);
		
		if (this.b_Preview) {
			oGridEingabeWerte._a(new RB_lab()._t(S.ms("VORSCHAUDRUCK"))._b()._fsa(2),  oGLVorschaudruck);
		} else {
			oGridEingabeWerte._a(new RB_lab()._t(S.ms("Achtung! Endgültiger Druck! Beleg(e) werden geschlossen !!"))._b()._fsa(2),  oGLFinaldruck);
		}
		
		oGridEingabeWerte._a(new RB_lab(S.ms("Rechnung ID")), 	new RB_gld()._ins(2)._col_back_d());
		oGridEingabeWerte._a(new RB_lab(S.ms("Verl. Fakt.")), 	new RB_gld()._ins(2)._col_back_d());
		oGridEingabeWerte._a(new RB_lab(S.ms("Beschreibung")), 	new RB_gld()._ins(2)._col_back_d());
		oGridEingabeWerte._a(new RB_lab(S.ms("Rechnungsdatum")),new RB_gld()._ins(2)._col_back_d());
		oGridEingabeWerte._a(new RB_lab(S.ms("Zahlungsziel")), 	new RB_gld()._ins(2)._col_back_d());

		for (String idVkopfRg: this.hmRechnungsZeilen.keySet()) {
			oGridEingabeWerte._a(this.hmRechnungsZeilen.get(idVkopfRg).getTfIdKopfRg(), 							new RB_gld()._ins(2)._col_back_d());
			oGridEingabeWerte._a(this.hmRechnungsZeilen.get(idVkopfRg).getComponentShowVerlaengerteFakturierung(), 	new RB_gld()._ins(2)._col_back_d());
			oGridEingabeWerte._a(this.hmRechnungsZeilen.get(idVkopfRg).getTfInfo(), 								new RB_gld()._ins(2)._col_back_d());
			oGridEingabeWerte._a(this.hmRechnungsZeilen.get(idVkopfRg).getTfRechnungsDatum(), 						new RB_gld()._ins(2)._col_back_d());
			oGridEingabeWerte._a(this.hmRechnungsZeilen.get(idVkopfRg).getTfZahlungsDatum(), 						new RB_gld()._ins(2)._col_back_d());
		}
		
	}
	
	
	private class ownResizer extends XX_BasicContainerResizeHelper
	{
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException
		{
			Extent  oWidth = ownContainer.get_oExtWidth();
			Extent  oHeight = ownContainer.get_oExtHeight();
			
			if (oWidth.getUnits()==Extent.PX && oHeight.getUnits()==Extent.PX)
			{
				E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(ownContainer, bibALL.get_Vector(MyE2_ContainerEx.class.getName()), null);
				
				if (oSearch.get_vAllComponents().size()==1)
				{
					MyE2_ContainerEx oContainerEx = (MyE2_ContainerEx)oSearch.get_vAllComponents().get(0);
					
					oContainerEx.setWidth(new Extent(oWidth.getValue()-40));
					
					oContainerEx.setHeight(new Extent(oHeight.getValue()-150));
				}
			}
		}
	}


	@Override
	public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) {
		
		this.hmRechnungsZeilen.clear();
		
		boolean b_PopupAnzeigen = false;

		
		try {
			int iKarenzZeit = bibALL.get_RECORD_MANDANT().get_KARENZ_ZAHLFRIST_AB_HEUTE_lValue(0l).intValue();
			
			

			
			this.vID_VKOPF_RG = new Vector<String>();
			this.vID_VKOPF_RG.addAll(this.oActionAgentPrintOrMail.fill_IDs_To_Print());
			
			if (this.vID_VKOPF_RG.size()==0) {
				return false;    //dann eh nix machen
			}

//			//Step 1: pruefen, ob gemischte RG-Positionen (Abgeschlossen/nicht abgeschlossen) vorliegen
			CHECK_NurFertigeOderOffene  oCheck = new CHECK_NurFertigeOderOffene(this.vID_VKOPF_RG);
//2019-06-25: wird mit einem validierer im button erledit			
//			if (oCheck.get_bGemischteAuswahl()) {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es dürfen nur mehrere NICHT-Abgeschlossene oder mehrere ABGESCHLOSSENE gleichzeitig gedruckt werden !!"));
//				return false;    //dann eh nix machen
//			}
			
			
			//step 2: rechnungspositionen lueckenlos schreiben
			MyE2_MessageVector  oMV = 	new MyE2_MessageVector();
			KORR_PosNummern 	oKorr = new KORR_PosNummern(this.vID_VKOPF_RG) ;
			
			if (!oKorr.MakeKorrection(oMV)) {
				bibMSG.add_MESSAGE(oMV);
				return false;
			}
			
			
			boolean koorZahlungszielAmWochenende = 	bibALL.get_RECORD_MANDANT().is_KORR_ZAHLDAT_WOCHENENDE_YES();

			if (oCheck.get_bNurNeue()) {        //nur dann gehts hier weiter, sonst ist bereits alles geschrieben
				if (oCheck.get_bOhneLeistungsdatumVorhanden()) {
					//sollte nicht vorkommen
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("In der Auswahl existieren Rechnungspositionen ohne Leistungsdatum! Bitte prüfen!"));
				} else {
			
					//hier die anzeigezeilen des popups gesammelt
					for (String cID_VKOPF_RG: this.vID_VKOPF_RG) {
						//RECORD_VKOPF_RG_SETTING_DATUM recRK = new RECORD_VKOPF_RG_SETTING_DATUM(cID_VKOPF_RG);
						
						Rec21_VkopfRG recVKopf = (Rec21_VkopfRG)new Rec21_VkopfRG()._fill_id(cID_VKOPF_RG);
						
						VEK<KV_IdKvPosUndFristTage> v_kvpos_id_kf_frist=  recVKopf.getAllFaktFristIdsAndVals();
						if (v_kvpos_id_kf_frist.size()>1) { 		//erlaubt ist 1 (entweder alle rechnungspositionen haben die gleiche id oder es steht 0 drin, dann sind alle OHNE Vertrag
							oMV.add_MESSAGE(new MyE2_Alarm_Message(
									S.ms("Ein Auftrag besitzt Kreditversicherungsvarianten aus mehreren Verträgen oder unterschiedliche Kontrakteinstellungen (Fakturierungsfrist)!"
											+ " Bitte nur vertragsgleich abrechnen!").ut("ID: "+cID_VKOPF_RG)));
						} else if (v_kvpos_id_kf_frist.size()==0) {
							oMV.add_MESSAGE(new MyE2_Alarm_Message(
									S.ms("Ein Auftrag besitzt keine Positionen!").ut("ID: "+cID_VKOPF_RG)));
						} else if (v_kvpos_id_kf_frist.size()==1) {   //alle anderen faelle sind fehler
							Long kreditVersFaktFrist = 	v_kvpos_id_kf_frist.get(0).anzahlTage;
							
							Date groesstesLeistungsDatum = 			recVKopf.getBiggestLeitungsDatum();
							Date zahlungsZielNachLeistungsDatum = 	recVKopf.getZahlungsZiel(groesstesLeistungsDatum);

							RechnungsZeile zeile = new RechnungsZeile();
							
							boolean kontraktHasFaktFristSetting = new Rec21_VposRg(recVKopf.getRecPositionsUndeleted().getVEK().get(0)).hatVerlaengerteFaktFristSchalterImKontrakt();
							
							if (kreditVersFaktFrist!=0l && new Date().after(zahlungsZielNachLeistungsDatum) && kontraktHasFaktFristSetting) {
								//neue variante unter beruecksichtigung der fakturierungsfrist
								Date druckDat = 						new Date();
								Date relevantesZahlungsZiel = 			MyDate.getDatePlusDays(druckDat, iKarenzZeit);
								if (koorZahlungszielAmWochenende) {
									relevantesZahlungsZiel = MyDate.getMondayWhenDateInWeekend(relevantesZahlungsZiel);
								}
									
								zeile._setFakturierungsFrist(kreditVersFaktFrist);
								zeile._setDruckDatum(druckDat);
								zeile._setRelevantesZahlungsZiel(relevantesZahlungsZiel);
								zeile._setMaxLeistungsDatum(groesstesLeistungsDatum);
								zeile._setZahlungsZielNachLeistungsDatum(zahlungsZielNachLeistungsDatum);
								zeile._setRec21VKopf(recVKopf);
							} else {
								//alte variante wenn es keine fakturierungsfrist gibt oder die zeit nicht ueberschritten ist
								
								if (new Date().after(zahlungsZielNachLeistungsDatum)) {
									//dann auf heute+karenzzeit
									zahlungsZielNachLeistungsDatum = MyDate.getDatePlusDays(new Date(), iKarenzZeit);
								}
								
								if (koorZahlungszielAmWochenende) {
									zahlungsZielNachLeistungsDatum = MyDate.getMondayWhenDateInWeekend(zahlungsZielNachLeistungsDatum);
								}

								zeile._setFakturierungsFrist(null);
								zeile._setDruckDatum(groesstesLeistungsDatum);
								zeile._setRelevantesZahlungsZiel(zahlungsZielNachLeistungsDatum);
								zeile._setMaxLeistungsDatum(groesstesLeistungsDatum);
								zeile._setZahlungsZielNachLeistungsDatum(zahlungsZielNachLeistungsDatum);
								zeile._setRec21VKopf(recVKopf);
							}
							this.hmRechnungsZeilen.put(cID_VKOPF_RG, zeile._render());
						}
						
					}
				
					if (oMV.get_bHasAlarms()) {
						bibMSG.add_MESSAGE(oMV);
					} else {
						b_PopupAnzeigen = true;
					}
				}
			}
		} catch (myException e) {
			bibMSG.MV()._add(e.get_ErrorMessage());
			b_PopupAnzeigen=false;
		} catch (Exception e) {
			bibMSG.MV()._addAlarm("Unknown Error: Code:<52b299d6-973f-11e9-ba23-526af7764f64>");
			b_PopupAnzeigen=false;
		}
		
		return b_PopupAnzeigen;    
	}
	
}
