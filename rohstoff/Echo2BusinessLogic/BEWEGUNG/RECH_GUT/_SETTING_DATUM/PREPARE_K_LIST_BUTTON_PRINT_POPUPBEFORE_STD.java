package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForDateFields;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;
import rohstoff.Echo2BusinessLogic._4_ALL.BL_CalcZahlungsDatum;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_RG_ext;



/*
 * popup-klasse, sorgt dafuer, dass die Felder ZAHLUNGSBED_CALC_DATUM 
 *   - ueberall gleich sind (dann ist der Druck moeglich)
 *   - ueberall leer sind (dann wird zuerst ein datum abgefragt
 *   - oder verhindert das Drucken 
 *   
 *   diese struktur wird eingesetzt bei Gutschriften immer und bei Rechnungen, wenn nicht die alternative Methode im Mandantenstamm aktiv ist
 */
public class PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_STD extends E2_BasicModuleContainer_PopUp_BeforeExecute
{
	
	private Vector<String>   			vID_VKOPF_RG = null;
	private UB_TextFieldForDateFields   oDatumRechnungsdruck = null;
	private UB_TextFieldForDateFields   oDatumKorrekturRechnungsDruck = null;
	
	private MyE2_Grid  							oGridWithContent = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
	private BS_K_LIST_ActionAgent_Mail_Print  	oActionAgentPrintOrMail = null;

	private MyE2_Label                          oLabelWarnung = new MyE2_Label(new MyE2_String("Bei mindestens 1 Beleg ist das Zahlungsziel VOR dem Belegdatum !"),
																		MyE2_Label.STYLE_NORMAL_BOLD());
	private MyE2_CheckBox    					oCB_DruckenTrotzZahlungszielZuAlt = new MyE2_CheckBox(new MyE2_String("Zahlungsziel in Vergangenheit: IGNORIEREN"));
	private MyE2_Button    						oBtPruefeObDatumZualt = new MyE2_Button(new MyE2_String("Neu prüfen"));
	
	private boolean                             bDruckIstVorschau = false;
	
	public PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_STD(BS_K_LIST_ActionAgent_Mail_Print  ActionAgentPrintOrMail, boolean bVorschau) throws myException
	{
		super();
		this.bDruckIstVorschau = bVorschau;
		this.oActionAgentPrintOrMail = ActionAgentPrintOrMail;

		//der "fehlerblock"
		this.oLabelWarnung.setVisible(false);
		this.oLabelWarnung.setLineWrap(true);
		this.oCB_DruckenTrotzZahlungszielZuAlt.setVisible(false);
		this.oBtPruefeObDatumZualt.setVisible(false);
		this.oBtPruefeObDatumZualt.add_oActionAgent(new XX_ActionAgent() 
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_STD.this.pruefe_ZahlungsZielZuAlt();
			}
		});
		
		
		this.oDatumRechnungsdruck = new UB_TextFieldForDateFields("DRUCKDATUM",false,bibALL.get_cDateNOW());
		
		this.oDatumKorrekturRechnungsDruck = 		new UB_TextFieldForDateFields("--ERSATZDATUM--",false,"");
		this.oDatumKorrekturRechnungsDruck.set_bEmptyAllowd(true);
		
		
		this.set_oExtWidth(new Extent(630));
		this.set_oExtHeight(new Extent(300));
		
		this.set_oExtMINIMALWidth(new Extent(630));
		this.set_oExtMINIMALHeight(new Extent(300));

		this.set_bVisible_Row_For_Messages(true);

		this.add_In_ContainerEX(this.oGridWithContent, new Extent(630), new Extent(250), E2_INSETS.I_5_5_5_5);
		
		E2_ComponentGroupHorizontal oButtonLeiste = new E2_ComponentGroupHorizontal(0,this.get_oButtonForOK(),this.get_oButtonForAbbruch(),E2_INSETS.I_10_2_10_2);
		this.add(oButtonLeiste,  E2_INSETS.I_5_5_5_5);
		this.set_bShowWindowAsSplitpane(false);

		
		//2011-07-05: individuelles resizing
		this.set_oResizeHelper(new ownResizer());
		
	}


	//2011-07-05: individuelles resizing
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
	

	public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) throws myException
	{
		boolean bRueck = false;
		
		this.oDatumRechnungsdruck.set_StartValue(bibALL.get_cDateNOW());
		
		this.oDatumKorrekturRechnungsDruck.setText("");
		this.oCB_DruckenTrotzZahlungszielZuAlt.setSelected(false);
		
		this.vID_VKOPF_RG = new Vector<String>();
		this.vID_VKOPF_RG.addAll(this.oActionAgentPrintOrMail.fill_IDs_To_Print());
		
		if (this.vID_VKOPF_RG.size()==0) {
			return false;
		}
		
		//step 1: rechnungspositionen lueckenlos schreiben
		MyE2_MessageVector  oMV = 	new MyE2_MessageVector();
		KORR_PosNummern 	oKorr = new KORR_PosNummern(this.vID_VKOPF_RG) ;
		
		if (!oKorr.MakeKorrection(oMV)) {
			bibMSG.add_MESSAGE(oMV);
			return false;
		}
		
//		/*
//		 * hier die Reihenfolge der positionen festelegen, damit keine sortierzufaelle moeglich sind 
//		 */
//		Vector<String> vSQLs = new Vector<String>();
//		for (int i=0;i<this.vID_VKOPF_RG.size();i++)
//		{
//			RECLIST_VPOS_RG  recListVPOSRG = new RECLIST_VPOS_RG("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_RG="+this.vID_VKOPF_RG.get(i)+" ORDER BY POSITIONSNUMMER");
//			
//			for (int k=0;k<recListVPOSRG.get_vKeyValues().size();k++)
//			{
//				bibMSG.add_MESSAGE(recListVPOSRG.get(k).set_NEW_VALUE_POSITIONSNUMMER(""+(k+1)));
//				vSQLs.add(recListVPOSRG.get(k).get_SQL_UPDATE_STATEMENT(null, true));
//			}
//		}
//		
//		bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLs, true));
//		if (bibMSG.get_bHasAlarms())
//		{
//			return false;
//		}
		

		//dann pruefen, ob gemischte RG-Positionen (Abgeschlossen/nicht abgeschlossen) vorliegen
		CHECK_NurFertigeOderOffene  oCheck = new CHECK_NurFertigeOderOffene(this.vID_VKOPF_RG);
		if (oCheck.get_iAnzahlSelektiert()>0) {
			if (oCheck.get_bGemischteAuswahl()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es dürfen nur mehrere NICHT-Abgeschlossene oder mehrere ABGESCHLOSSENE gleichzeitig gedruckt werden !!"));
				bRueck = false;
			} else if (oCheck.get_bNurNeue()) {
				bRueck = true;
			} else {
				bRueck = false;
			}
		}
		
		
//		
//		if (this.vID_VKOPF_RG.size() > 0)
//		{
//			String cInString = "("+bibALL.Concatenate(this.vID_VKOPF_RG, ",", "0")+")";
//			
//			String cAnzahl_N = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN "+cInString+" AND NVL(ABGESCHLOSSEN,'N')='N' AND NVL(DELETED,'N')='N'");
//			String cAnzahl_Y = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN "+cInString+" AND NVL(ABGESCHLOSSEN,'N')='Y' AND NVL(DELETED,'N')='N'");
//			
//			if (bibALL.isLong(cAnzahl_N) && bibALL.isLong(cAnzahl_Y))
//			{
//				long lAnzahl_N = new Long(cAnzahl_N).longValue();
//				long lAnzahl_Y = new Long(cAnzahl_Y).longValue();
//				
//				if (lAnzahl_Y==0)
//				{
//					bRueck = true;   // keine abgeschlossenen, d.h. es muss nach druckdatum gefragt werden und auch die zahlungsziele festgelegt werden
//				}
//				else if (lAnzahl_N>0 && lAnzahl_Y>0)
//				{
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es dürfen nur mehrere NICHT-Abgeschlossene oder mehrere ABGESCHLOSSENE gleichzeitig gedruckt werden !!"));
//					bRueck = false;
//				}
//				// andere faelle heisen: alle bereits abgeschlossen
//			}
//			else
//			{
//				throw new myException(this,"Error quering ABGESCHLOSSEN-Status !");
//			}
//		}
		
		return bRueck;
	}

	
	
	public void doBuildContent(ExecINFO oExecInfo) throws myException
	{
		this.oGridWithContent.removeAll();
		
		if (!this.bDruckIstVorschau)
		{
			this.oGridWithContent.add(new MyE2_Label(new MyE2_String("ACHTUNG! Entgültiger Druck! Beleg(e) werden geschlossen !!"),MyE2_Label.STYLE_ERROR_BIGBIG()).get_InBorderGrid(new Border(2, Color.BLACK, Border.STYLE_SOLID),null,E2_INSETS.I_5_5_5_5),
																															LayoutDataFactory.get_GridLayoutGridCenter(new Insets(5,10,10,10),this.oGridWithContent.getSize()));
		}
		
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Bitte geben Sie das Rechnungsdatum an .....................")),3,new Insets(5,10,10,10));
		this.oGridWithContent.add(this.oDatumRechnungsdruck,2,new Insets(10,10,10,10));
		
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("Bitte nur ausfüllen, wenn das Leistungsdatum ALLER Positionen")),5,new Insets(5,10,10,5));
		
		this.oGridWithContent.add(new MyE2_Label(new MyE2_String("für die Berechnung des Zahlungsziels überstimmt werden soll: ")),3,new Insets(5,0,10,2));
		this.oGridWithContent.add(this.oDatumKorrekturRechnungsDruck,1,new Insets(10,0,10,2));
		this.oGridWithContent.add(this.oBtPruefeObDatumZualt,1,new Insets(5,0,10,2));
		
		
		//den korrektur-schalter einblenden (falls ein fehler auftritt)
		
		this.oGridWithContent.add(this.oLabelWarnung.get_InBorderGrid(new Border(2, Color.BLACK, Border.STYLE_SOLID), new Extent(100,Extent.PERCENT), E2_INSETS.I_2_2_2_2),this.oGridWithContent.getSize(),new Insets(5,10,10,2));
		this.oGridWithContent.add(this.oCB_DruckenTrotzZahlungszielZuAlt,this.oGridWithContent.getSize(),new Insets(5,10,10,2));
		
		
		//erste pruefung auf zahlungsziel im Verhaeltnis zum rechnungsdatum
		this.pruefe_ZahlungsZielZuAlt();
		
	}

	
	/*
	 * gibt den ersten gefundenen, falsch datierten VKOPF zurueck oder null
	 */
	private RECORD_VKOPF_RG_ext pruefe_ZahlungsZielZuAlt() throws myException
	{
		RECORD_VKOPF_RG_ext recRueck = null;
		
		MyE2_MessageVector oMV = this.oDatumRechnungsdruck.get_MV_InputOK();
		oMV.add_MESSAGE(this.oDatumKorrekturRechnungsDruck.get_MV_InputOK());
		if (oMV.get_bHasAlarms())   //bei falschem datum raus
		{
			bibMSG.add_MESSAGE(oMV);
			return null;			
		}
		
		this.oLabelWarnung.setVisible(false);
		this.oCB_DruckenTrotzZahlungszielZuAlt.setVisible(false);
		this.oBtPruefeObDatumZualt.setVisible(false);

		if (S.isEmpty(this.oDatumRechnungsdruck.get_cText()))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Leeres Druckdatum ist nicht erlaubt!!")));
			return null;
		}

		
		MyDate oDateDruckDatum = new MyDate(this.oDatumRechnungsdruck.get_cText());

		if (!oDateDruckDatum.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehlerhaftes Druckdatum!!")));
			return null;
		}
		
		
		for (int i=0;i<this.vID_VKOPF_RG.size();i++)
		{
			RECORD_VKOPF_RG_ext  recVkopfRG = new RECORD_VKOPF_RG_ext(this.vID_VKOPF_RG.get(i));

			MyDate oDateZahlungsZiel = recVkopfRG.CalcZahlungsZiel(this.oDatumRechnungsdruck.get_cText(), this.oDatumKorrekturRechnungsDruck.get_cText(),oMV);
			
			if (!oMV.get_bIsOK())
			{
				bibMSG.add_MESSAGE(oMV);
				return null;
			}
			
			if (oDateZahlungsZiel==null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Ermitteln des Zahlungsziels!!")));
				return null;
			}

			if (oMV.get_bIsOK())
			{
				if (oDateDruckDatum.get_cDBFormatErgebnis().compareTo(oDateZahlungsZiel.get_cDBFormatErgebnis())>0)
				{
					this.oLabelWarnung.setVisible(true);
					this.oCB_DruckenTrotzZahlungszielZuAlt.setVisible(true);
					this.oBtPruefeObDatumZualt.setVisible(true);
					recRueck = recVkopfRG;
					
					this.oLabelWarnung.set_Text(new MyE2_String("Bei mindestens 1 Beleg, ist das Zahlungsziel VOR dem Belegdatum ",true,
									"("+recRueck.get___KETTE(bibALL.get_Vector("NAME1", "ORT"))+" //  Beleg-ID ....   "+recRueck.get_ID_VKOPF_RG_cF()+",    Zahlungsziel ....   "+oDateZahlungsZiel.get_cUmwandlungsergebnis()+")",false));
					
					break;       //einer reicht fuer die warnung
				}
			}
			else
			{
				bibMSG.add_MESSAGE(oMV);
				break;
			}
		}

		return recRueck;
		
	}
	
	
	protected void doOwnCode_in_ok_button() throws myException
	{
		if (this.oDatumRechnungsdruck.get_MV_InputOK().get_bIsOK() && this.oDatumKorrekturRechnungsDruck.get_MV_InputOK().get_bIsOK() )
		{
			RECORD_VKOPF_RG_ext recTest = this.pruefe_ZahlungsZielZuAlt();
			
			if (bibMSG.get_bHasAlarms())
			{
				return;
			}
			
			if (recTest!=null && !this.oCB_DruckenTrotzZahlungszielZuAlt.isSelected())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das errechnete Zahlungsziel liegt VOR dem Rechnungsdatum! Bitte ein passendes Korrekturdatum eingeben oder IGNORIEREN auswählen!")));
				return;
			}
			
			
			String cDBDate = this.oDatumRechnungsdruck.get_cUpdatePart();
			
			Vector<String>   vSQLStatements = new Vector<String>();
			
			// sql-statements fuer die aenderung der Druckdatumsfelder 
//			Vector<String> vSelIDs = this.oNaviList.get_vSelectedIDs_Unformated();
			String cInString = "("+bibALL.Concatenate(this.vID_VKOPF_RG, ",", "0")+")";
			
			RECLIST_VKOPF_RG recListVKOPF = new RECLIST_VKOPF_RG("SELECT * FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG IN "+cInString);
			
			for (int i=0;i<recListVKOPF.get_vKeyValues().size();i++)
			{
				RECORD_VKOPF_RG_ext recVKOPF = new RECORD_VKOPF_RG_ext(recListVKOPF.get(i));
				
				vSQLStatements.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_RG SET "+cDBDate+" WHERE ID_VKOPF_RG="+recVKOPF.get_ID_VKOPF_RG_cUF());
				
				//waehrungszeichen in die datenbank schreiben
				//beim ersten Drucken die waehrungssymbole in den kopfsatz schreiben
				String cBasisWaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
				String cFremdWaehrung = bibE2.get_cBASISWAEHRUNG_SYMBOL();
				
				if (recVKOPF.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd()!=null)
				{
					cFremdWaehrung = recVKOPF.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd().get_WAEHRUNGSSYMBOL_cUF_NN(bibE2.get_cBASISWAEHRUNG_SYMBOL());
				}
				
				recVKOPF.set_NEW_VALUE_WAEHRUNG_BASIS(cBasisWaehrung);
				recVKOPF.set_NEW_VALUE_WAEHRUNG_FREMD(cFremdWaehrung);
				
				vSQLStatements.add(recVKOPF.get_SQL_UPDATE_STATEMENT(null, true));
				
				for (Entry<String, RECORD_VPOS_RG> oEntry: recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().entrySet())
				{
					if (oEntry.getValue().is_DELETED_NO())
					{
						RECORD_VPOS_RG recVPOS_RG = oEntry.getValue();
						
						//leistungs- und rechnungsdatum besorgen
						String cDatumRech = 	this.oDatumRechnungsdruck.getText();    //rechnungsdatum

						//datum, was benutzt wird, um den zahlungstag zu berechnen
						String cDatumFuerBerechnung = recVPOS_RG.get_AUSFUEHRUNGSDATUM_cF(); 

						if (bibALL.isEmpty(cDatumFuerBerechnung))
						{
							cDatumFuerBerechnung = cDatumRech;
						}

						//nachsehen, ob eine ID_Zahlungsbed. vorhanden ist
						if (!bibALL.isEmpty(recVPOS_RG.get_ID_ZAHLUNGSBEDINGUNGEN_cUF()))
						{
							if (recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().is_ZAHLDAT_CALC_RECHDAT_YES())
							{
								cDatumFuerBerechnung = cDatumRech;    //dann gibt es in dieser Zahlungsbedingung die vorgabe, dass die Berechung des zahlungsziels ab dem rechnungsdatum erfolgen soll
							}
						}

						//wenn in das textfeld this.oDatumLeistung ein ueberstimmendes leistungsdatum eingetragen wurde,
						//dann wird das genommen
						if (S.isFull(this.oDatumKorrekturRechnungsDruck.get_cText()))
						{
							cDatumFuerBerechnung = this.oDatumKorrekturRechnungsDruck.get_cText();
						}
						
						
						//jetzt die inneren positionen mit zahlungsdatum durchkalkulieren
						BL_CalcZahlungsDatum oCalZDat = new BL_CalcZahlungsDatum(
												cDatumFuerBerechnung,
												recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_ZAHLTAGE_lValue(null),
												recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXMONAT_lValue(null),
												recVPOS_RG.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXTAG_lValue(null));

						recVPOS_RG.set_NEW_VALUE_ZAHLUNGSBED_CALC_DATUM(oCalZDat.get_cDateForMask());
						vSQLStatements.add(recVPOS_RG.get_SQL_UPDATE_STATEMENT(null,true));
					}
				}
				
				//2011-04-05: aenderung der kalkulation des zahlungsziels fuer alle positionen
				MyE2_MessageVector oMV2 = new MyE2_MessageVector();
				MyDate oDateZahlungsZiel = recVKOPF.CalcZahlungsZiel(this.oDatumRechnungsdruck.get_cText(), this.oDatumKorrekturRechnungsDruck.get_cText(), oMV2);
				if (oMV2.get_bHasAlarms())
				{
					bibMSG.add_MESSAGE(oMV2);
					return;
				}
				
				//bei einer leeren rechnung kann er kein leistungsdatum haben, fehler
				if (oDateZahlungsZiel==null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ich konnte kein Zahlungsziel einstellen, vermutlich Rechnung ohne Position ??")));
					return ;
				}
				
				vSQLStatements.add("UPDATE "+bibE2.cTO()+".JT_VPOS_RG SET ZAHLUNGSBED_CALC_DATUM ="+ oDateZahlungsZiel.get_cDBFormatErgebnis_4_SQLString()+
									"WHERE JT_VPOS_RG.ID_VKOPF_RG="+recVKOPF.get_ID_VKOPF_RG_cUF());
			}
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLStatements, true));
			
			DEBUG.System_print(vSQLStatements);
		}
		else
		{
			bibMSG.add_MESSAGE(this.oDatumRechnungsdruck.get_MV_InputOK());
			bibMSG.add_MESSAGE(this.oDatumKorrekturRechnungsDruck.get_MV_InputOK());
		}
	}



	@Override
	protected void doOwnCode_in_cancel_button() throws myException
	{
	}

}
