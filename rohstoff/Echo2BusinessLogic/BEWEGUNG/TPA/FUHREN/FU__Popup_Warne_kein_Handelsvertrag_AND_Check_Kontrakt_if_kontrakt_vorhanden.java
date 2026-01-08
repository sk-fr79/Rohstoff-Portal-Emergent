package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingleMyString;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_KON_EXT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING.BSKC__AUSLASTUNG;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING.BSKC__CLEARING_BUTTON;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;


public class FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden extends E2_BasicModuleContainer_MASK
{
	private DB_SEARCH_Adress 			oSearchAdress = null;
	private MyE2_DB_MaskSearchField		oSearchKontrakt = null;
	private MyE2_Button             	oButtonAbbruch = new MyE2_Button(new MyE2_String("Abbrechen"));
	private MyE2_Button             	oButtonUebernehmen = new MyE2_Button(new MyE2_String("Trotzdem übernehmen"));
	private boolean      				bLeerenWennSchliessknopf = true;
	private MyE2_DB_CheckBox 			oCBOhneAVV_VertragsCheck = null;
	private String  					cID_ADRESSE_UF = null;
	private boolean    					bIsPrimaryCall = false;
	private boolean 					bCallAtAdresseSearch = false;
	private String      				c_EK_VK = null;
	
	/**
	 * 
	 * @param SearchAdress
	 * @param cEK_VK
	 * @param cID_ADRESSE_Unformated
	 * @param CBOhneAVV_VertragsCheck
	 * @param bIsFromPrimaryCall
	 * @param bAdressSearch
	 * @throws myException
	 */
	public FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden(	
										DB_SEARCH_Adress 			SearchAdress, 
										MyE2_DB_MaskSearchField		SearchKontrakt,
										String 						cEK_VK, 		
										String  					cID_ADRESSE_Unformated, 
										MyE2_DB_CheckBox 			CBOhneAVV_VertragsCheck,
										boolean            			bIsFromPrimaryCall, 
										boolean 					CallAtAdressSearch) throws myException
	{
		super();
		this.oSearchAdress = SearchAdress;
		this.oSearchKontrakt = SearchKontrakt;
		this.oCBOhneAVV_VertragsCheck = CBOhneAVV_VertragsCheck;
		this.bIsPrimaryCall = bIsFromPrimaryCall;
		this.bCallAtAdresseSearch = CallAtAdressSearch;
		this.cID_ADRESSE_UF = cID_ADRESSE_Unformated;
		this.c_EK_VK = cEK_VK;
		
		
		//2011-12-16: optionales verhindern einer fuhre, wenn es keinen gueltigen handelsvertrag gibt
		boolean bVerbieteUeberstimmen = __RECORD_MANDANT_ZUSATZ.IS__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG.toString(), "N", "N");
		this.oButtonUebernehmen.setVisible(!bVerbieteUeberstimmen);
		//dazu einfach den ueberstimmen-schalter inaktiv setzen
		
		boolean bWarningSchalter = SearchAdress.EXT().GET_Schalter_und_setze_TRUE(FU___CONST.SCHALTER_PRUEFE_UND_WARNE_WG_AVV_VERTRAG);
		if (!bWarningSchalter)
		{
			return;
		}
		
		this.set_bVisible_Row_For_Messages(true);
		
		//zuerst pruefen, ob der popup-warner ueberhaupt noetig/gewuenscht ist
		// nachsehen, ob ein Vertrag besteht (wenn nicht Mandant selbst)
		if (! cID_ADRESSE_Unformated.equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			FU__CHECK_MustWarn_AVV_Kontrakt  oPruefeVertrag = new FU__CHECK_MustWarn_AVV_Kontrakt();
			
			//2014-08-04: umstellung auf die neue pruefmimik
			VectorSingleMyString vMeldungen = new VectorSingleMyString();
			//sowohl haupt, als auch lageradresse pruefen, weil hier die hauptadresse 2x vorkommt und bei beiden pruefeinstellungen greift
			RECORD_ADRESSE recADRESSE = new RECORD_ADRESSE(cID_ADRESSE_Unformated);
			vMeldungen.addAll(oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE, cEK_VK, bibALL.get_cDateNOW(), true));
			vMeldungen.addAll(oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE, cEK_VK, bibALL.get_cDateNOW(), false));
			
			boolean bShowWarning = (vMeldungen.size()>0);
			
			//2012-03-27: das pruefen auf fehlenden handelsvertrag ist bei gesetztem Schalter <VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG>
			//            in den daemon FUHREN_PRUEFUNG_FEHLENDER_HANDELSVERTRAG gewandert, somit ist das hide des ueberstimm-buttons wirkungslos 
			bShowWarning = bShowWarning && (!bVerbieteUeberstimmen);
			
			
			//wenn in der maske der schalter OHNE_AVV_VERTRAG_CHECK gesetzt ist, dann kein popup anzeigen
			boolean bMustCheck = !oCBOhneAVV_VertragsCheck.isSelected();

			if (bMustCheck && bShowWarning && this.bIsPrimaryCall)
			{
				// den uebergeordneten E2_BasicContainer suchen
				E2_BasicModuleContainer oMask = new E2_RecursiveSearchParent_BasicModuleContainer(this.oSearchAdress).get_First_FoundContainer();
				
				String cMASK_KENNER = oMask.get_MODUL_IDENTIFIER();
				this.oButtonUebernehmen.add_GlobalValidator(new E2_ButtonAUTHValidator(cMASK_KENNER,FU___CONST.HASH_UEBERSTIMMEN_FEHLENDEN_KONTRAKT));
				
//				MyE2_Label oLabelError = new MyE2_Label(new MyE2_String("Es existiert mit dem Abnehmer KEIN EU-HANDELSVERTRAG !"), MyE2_Label.STYLE_ERROR_BIG());
				MyE2_Row   oRowHelp = new MyE2_Row();
//				oRowHelp.setBackground(new E2_ColorHelpBackground());
				oRowHelp.add(oPruefeVertrag.get_ColumnWithMeldungen(vMeldungen), E2_INSETS.I_0_0_0_0);
				
				this.add(oRowHelp, E2_INSETS.I_10_10_10_10);
				this.add(this.oButtonAbbruch,  E2_INSETS.I_10_10_10_10);
				this.add(this.oButtonUebernehmen,  E2_INSETS.I_10_10_10_10);
				
				this.oButtonAbbruch.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				});
		
				this.oButtonUebernehmen.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)  throws myException
					{
						FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.bLeerenWennSchliessknopf = false;     // inhalt bleibt stehen
						FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						if (FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.bIsPrimaryCall)
						{
							new FU__MaskSettingsAfterInput__Plausibilitaet(oCBOhneAVV_VertragsCheck);
							new popupAndShowContracts();
						}
					}
				});
				
				this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
					{
						if (FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.bLeerenWennSchliessknopf)
						{
							// adresse unbrauchbar machen, neu suchen erzwingen
							FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.oSearchAdress.get_oTextForAnzeige().setText("");
							FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.oSearchAdress.get_oTextFieldForSearchInput().setText("");
						}
					}
				});
	
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300),new MyE2_String("WARNUNG ! Kein gültiger EU-Handelsvertrag vorhanden !!!"));
			}
			else
			{
				if (this.bIsPrimaryCall)
				{
					new FU__MaskSettingsAfterInput__Plausibilitaet(oCBOhneAVV_VertragsCheck);
					new popupAndShowContracts();
				}
			}
		}
		else
		{
			if (this.bIsPrimaryCall)
			{
				new FU__MaskSettingsAfterInput__Plausibilitaet(oCBOhneAVV_VertragsCheck);
			}
		}
	}
	
	
	
	
	/**
	 * 
	 * @author martin
	 * falls der aufruf aus der suche nach adressen erfolgt, dann muss nach bestaetigung noch nachgeschaut werden, ob fuer diesen kunden
	 * bereits offene kontrakte bestehen und diese zur auswahl stellen
	 */
	private class popupAndShowContracts extends E2_BasicModuleContainer
	{

		public popupAndShowContracts() throws myException
		{
			super();
			
			
			Insets   oIN = new Insets(2,2,2,0);
			GridLayoutData  GLHead = LayoutDataFactory.get_GL_Copy(MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
			GridLayoutData  GLHeadRight = LayoutDataFactory.get_GL_Copy(MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
//			GridLayoutData  GLNormal = LayoutDataFactory.get_GL_Copy(MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
			
			GLHead.setBackground(new E2_ColorDDark());
			GLHeadRight.setBackground(new E2_ColorDDark());
			
			GLHead.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
			GLHeadRight.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));

			
			
			FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden oThis = FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this;
			
			//wird nur bei aufruf aus der adress-suche gestartet
			if (oThis.bCallAtAdresseSearch)
			{
				String cQuery = "SELECT"+ 
								"    JT_VPOS_KON.*"+ 
								" FROM"+ 
								"    "+bibE2.cTO()+".JT_VPOS_KON"+ 
								" INNER JOIN"+ 
								"    "+bibE2.cTO()+".JT_VKOPF_KON"+ 
								"    ON"+ 
								"    ("+
								"        "+bibE2.cTO()+".JT_VPOS_KON.ID_VKOPF_KON = "+bibE2.cTO()+".JT_VKOPF_KON.ID_VKOPF_KON"+
								"    )"+ 
								" INNER JOIN"+ 
								"    "+bibE2.cTO()+".JT_VPOS_KON_TRAKT"+ 
								"    ON"+ 
								"    ("+
								"        "+bibE2.cTO()+".JT_VPOS_KON.ID_VPOS_KON = "+bibE2.cTO()+".JT_VPOS_KON_TRAKT.ID_VPOS_KON"+
								"    )"+ 
								" WHERE NVL(JT_VPOS_KON.DELETED,'N')='N'"+
								" AND   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N'"+
								" AND   JT_VKOPF_KON.VORGANG_TYP='"+oThis.c_EK_VK+"_KONTRAKT'"+
								" AND   JT_VKOPF_KON.ID_ADRESSE="+oThis.cID_ADRESSE_UF+
								" ORDER BY JT_VPOS_KON.ARTBEZ1";
				
				RECLIST_VPOS_KON  vPosKon = new RECLIST_VPOS_KON(cQuery);
				
				if (vPosKon.get_vKeyValues().size()>0)
				{
					MyE2_Grid oGrid = new MyE2_Grid(10,0);
					
					this.add(oGrid,E2_INSETS.I_5_5_5_5);
					
					//ueberschrift
					//spaltennamen: ID,BUCHNR,FIRMA,MENGE,REST,SORTE,ARTBEZ,PREIS,GUELTIG
					oGrid.add(new MyE2_Label(new MyE2_String("-")),				GLHead);
					oGrid.add(new MyE2_Label(new MyE2_String("Buchungs-Nr")),	GLHead);
					oGrid.add(new MyE2_Label(new MyE2_String("Datum")),			GLHead);
					oGrid.add(new MyE2_Label(new MyE2_String("Firma")),			GLHead);
					oGrid.add(new MyE2_Label(new MyE2_String("Menge")),			GLHeadRight);
					oGrid.add(new MyE2_Label(new MyE2_String("Offen")),			GLHeadRight);
					oGrid.add(new MyE2_Label(new MyE2_String("Sorte")),			GLHead);
					oGrid.add(new MyE2_Label(new MyE2_String("Artikelbez.")),	GLHead);
					oGrid.add(new MyE2_Label(new MyE2_String("Preis")),			GLHeadRight);
					oGrid.add(new MyE2_Label(new MyE2_String("Gültig")),		GLHead);

					for (int i=0;i<vPosKon.get_vKeyValues().size();i++)
					{
						__RECORD_VPOS_KON_EXT oVPos = new __RECORD_VPOS_KON_EXT(vPosKon.get(i));
						
						//aenderung 2010-11-26: Beim anzeigen muss auch die kontrakt-nummer auftauchen
						String cBuchungsNUMMER = oVPos.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_BUCHUNGSNUMMER_cF_NN("("+oVPos.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_VKOPF_KON_cF()+")");
						cBuchungsNUMMER += ("  -  "+oVPos.get_POSITIONSNUMMER_cF_NN("??"));
						String cGueltig =  myDateHelper.ChangeFormatStringToDateWithoutYear(oVPos.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_VON_cF_NN("--.--.-----"))+" - "+myDateHelper.ChangeFormatStringToDateWithoutYear(oVPos.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_GUELTIG_BIS_cF_NN("--.--.----"));
						
						oGrid.add(new ownClearingButton(oVPos), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(cBuchungsNUMMER, 																					oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(oVPos.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_DRUCKDATUM_cF_NN("<datum>"), 						oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(oVPos.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get___KETTE(bibALL.get_Vector("NAME1", "ORT")), 		oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(MyNumberFormater.formatDez(oVPos.get_ANZAHL_bdValue(BigDecimal.ZERO),0,true),																		oVPos.get_ID_VPOS_KON_cUF_NN(""),false), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(MyNumberFormater.formatDez(oVPos.get_bdRestMenge(), 0, true),										oVPos.get_ID_VPOS_KON_cUF_NN(""),false), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(oVPos.get___KETTE(bibALL.get_Vector("ANR1", "ANR2"),"","","","-"),									oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(oVPos.get_ARTBEZ1_cUF_NN("")+" // "+oVPos.get_ARTBEZ2_cUF_NN(""),									oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(oVPos.get_EINZELPREIS_cF_NN("-")+" "+
								(oVPos.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd()!=null
								?
								oVPos.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd().get_WAEHRUNGSSYMBOL_cUF_NN("??")
								:
								"")	,																														oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
						oGrid.add(new ButtonHoleKontrakt(cGueltig,																							oVPos.get_ID_VPOS_KON_cUF_NN(""),true), E2_INSETS.I_5_1_5_1);
					}
					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(500), new MyE2_String("Vorhandene Kontrakte "+(oThis.c_EK_VK.equals("EK")?" dieses Lieferanten: ":" dieses Kunden:")));
				}
			}
		}
		
		
		private class ownClearingButton extends  BSKC__CLEARING_BUTTON
		{

			public ownClearingButton(RECORD_VPOS_KON recVposKon) throws myException 
			{
				super(new BSKC__AUSLASTUNG(recVposKon, false));
				this.setToolTipText(new MyE2_String("Clearing-Informationen zu dieser Kontraktposition ...").CTrans());
			}

			@Override
			public E2_BasicModuleContainer get_ownBasicModuleContainer() throws myException 
			{
				return new ownBasicContainer();
			}
			
			private class ownBasicContainer extends E2_BasicModuleContainer
			{
				public ownBasicContainer() 
				{
					super();
				}
			}
		}
		
		
		
		private class ButtonHoleKontrakt extends MyE2_Button
		{

			public ButtonHoleKontrakt(String text, String cID_VPOS_KON,boolean bLinks)
			{
				super(text);
				this.EXT().set_C_MERKMAL(cID_VPOS_KON);
				if (bLinks)
				{
					this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
				}
				else
				{
					this.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
				}
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo)	throws myException
					{
						/*
						 * dann wird die kontraktsuche angeworfen und die adresse sowieso neu geschrieben
						 */
						FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden.this.oSearchKontrakt.set_cActualMaskValue(
												ButtonHoleKontrakt.this.EXT().get_C_MERKMAL(), 
												true, 
												true, 
												false);
						
						popupAndShowContracts.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						
					}
				});
						
			}
			
		}

	}
	
	
	
	
	
}
