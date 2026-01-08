package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterForFoundButtons;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterPrepareContentForNew;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_KON_EXT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___MASK_SEARCH_KONTRAKT_POS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_AnzeigeFremdWaehrung;
import rohstoff.Echo2BusinessLogic._4_ALL.KontraktSelector;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FUO_MASK_DB_SEARCH_Kontrakt extends FU___MASK_SEARCH_KONTRAKT_POS
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	public FUO_MASK_DB_SEARCH_Kontrakt(SQLField osqlField, String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(osqlField,cEK_VK);
	
		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		this.set_oMaskActionAfterPrepareContentForNew(new ownActionCleanLieferbed());
		
		this.get_oSeachBlock().set_bAllowEmptySearchField(true);
		
		this.get_oTextForAnzeige().setFont(new E2_FontPlain(-2));
		this.get_oTextFieldForSearchInput().set_iWidthPixel(75);
		this.get_oTextForAnzeige().setWidth(new Extent(400));
		
		boolean useNewKontrakt = ENUM_MANDANT_DECISION.USE_NEW_KONTRAK_TYP.is_YES();

		this.set_AddOnComponent(new E2_ComponentGroupHorizontal(0,new popupButtonMit_Schnell_Selektor(),new lagerButton("ID_ADRESSE"),
				(useNewKontrakt?new openKontraktPosNG(): new openKontraktPos())
				,E2_INSETS.I_0_0_2_0));

		this.set_FormatterForButtons(new manipulator_Markiere_zugeordneteKontrakt_positionen());
		
	}

	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}
	
	
	
	
	//eigener actionagent der die anzeige der lieferbedingung resettet, falls der kontrakt geleert wird
	private class ownActionCleanLieferbed extends  XX_MaskActionAfterPrepareContentForNew  {
		@Override
		public void doMaskSettingsAfterPrepareContentForNew(MyE2_DB_MaskSearchField oDB_SearchField, boolean bSetDefaults) throws myException {
			//2014-06-02: die sonderlieferbedingung wieder entfernen
			FUO_MASK_DB_SEARCH_Kontrakt oThis = FUO_MASK_DB_SEARCH_Kontrakt.this;
			E2_ComponentMAP  oMAPFuhreORT = oThis.EXT().get_oComponentMAP();
			
			//2014-06-05: die sonderlieferbedingung wieder entfernen
			if (S.isFull(oMAPFuhreORT.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV).get_cActualDBValueFormated())) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Die alternative Lieferbedingung im Fuhrenort wird durch das Entfernen des Kontraktes zurückgesetzt !")));
			}
			
			oMAPFuhreORT.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV).set_cActualMaskValue("");
			((FU_AL_Component)oMAPFuhreORT.get__Comp(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV)).clear_Lieferbedingungen();
			((FU_AL_Component)oMAPFuhreORT.get__Comp(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV)).refreshLieferbedingung();
			
			//20180607: waehrungsanzeiger
			new FUO_Set_And_Valid_AnzeigeFremdWaehrung().setManual(FUO_MASK_DB_SEARCH_Kontrakt.this.EXT().get_oComponentMAP());

		}
	}

	
	
	/*
	 * mask-setting-agent fuer das laden der artikelbez in die maske, getrennt nach EK-Bezeichnung oder VK-Bezeichnung
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public ownMaskActionAfterFound()
		{
			super();
		}

		

		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			FUO_MASK_DB_SEARCH_Kontrakt oThis = FUO_MASK_DB_SEARCH_Kontrakt.this;
			
			E2_ComponentMAP   	oMapFuhreZusatzOrt = oThis.EXT().get_oComponentMAP();
			//E2_ComponentMAP  	oMapFuhre = oThis.oFUO_DaughterComponent.EXT().get_oComponentMAP();


			if (bAfterAction)
			{
				
				//zuerst den komplementaeren kontrakt rausziehen und, falls vorhanden, die sorten-Hauptnummer (Stelle 1-2) vergleichen
				String cID_VPOS_KON_Komplement = null;
				String cKomplement = null;
				if (oThis.get_EK_VK().equals("EK"))
				{					
					cID_VPOS_KON_Komplement = ""+oThis.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_VK", true, true, new Long(0)).longValue();
					cKomplement = "VK";
				}
				else
				{
					cID_VPOS_KON_Komplement = ""+oThis.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_EK", true, true, new Long(0)).longValue();
					cKomplement = "EK";
				}

				//2011-02-21: NICHT - pruefung der unterschiedlichen sorte n bei offenem Maskenschalter eingefuegt
				boolean bEK_VK_SorteUnterschiedErlaubt = S.NN(oThis.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_cActualDBValueFormated("EK_VK_SORTE_LOCK")).equals("N");
				
				if (!bEK_VK_SorteUnterschiedErlaubt)
				{
					if (!cID_VPOS_KON_Komplement.trim().equals("0"))
					{
						RECORD_VPOS_KON oMapInput = 		new RECORD_VPOS_KON(cMaskValue);
						RECORD_VPOS_KON oMapKomplement = 	new RECORD_VPOS_KON(cID_VPOS_KON_Komplement);
						
	
						if (!oMapInput.get_ANR1_cUF_NN("--").substring(0,2).equals(oMapKomplement.get_ANR1_cUF_NN("++").substring(0,2)))
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Sorte des Kontrakts passt nicht zum "+cKomplement+"-Kontrakt der Hauptmaske !"));
							oThis.prepare_ContentForNew(false);
							//sicherheitshalber die adresse auch leermachen
							oThis.EXT().get_oComponentMAP().get__DBComp("ID_ADRESSE").prepare_ContentForNew(false);
							
							return;
						}
					}
				}
				
				E2_RecursiveSearch_MaskInfo  oMaskeFuhreZusatzort = new E2_RecursiveSearch_MaskInfo(oThis);
				
				RECORD_VPOS_KON oMAP_VPOS = new RECORD_VPOS_KON(cMaskValue);

				// lieferant laden und folgeaktion durchfuehren
				((MyE2_DB_MaskSearchField)oMaskeFuhreZusatzort.get_Component("ID_ADRESSE")).EXT().SET_Schalter(FU___CONST.SCHALTER_PRUEFE_UND_WARNE_WG_AVV_VERTRAG, false);
				((MyE2_DB_MaskSearchField)oMaskeFuhreZusatzort.get_Component("ID_ADRESSE")).set_cActualMaskValue(oMAP_VPOS.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF(),true,true,false);

				// sortenbez laden und fuellen
				((MyE2_DB_MaskSearchField)oMaskeFuhreZusatzort.get_Component("ID_ARTIKEL_BEZ")).set_cActualMaskValue(oMAP_VPOS.get_ID_ARTIKEL_BEZ_cUF(),true,true,false);

				// die kontraktbezogenen felder fuellen,
				// step 1 die bezeichnungen aus den oHMArtBezEK/oHMArtBezVK
				((MyE2IF__DB_Component)oMaskeFuhreZusatzort.get_Component("BESTELLNUMMER")).set_cActualMaskValue("");

				// dann gegebenenfalls austauschen geben die kontrakteintraege
				((MyE2IF__DB_Component)oMaskeFuhreZusatzort.get_Component("ARTBEZ1")).set_cActualMaskValue(oMAP_VPOS.get_ARTBEZ1_cUF_NN(""));
				((MyE2IF__DB_Component)oMaskeFuhreZusatzort.get_Component("ARTBEZ2")).set_cActualMaskValue(oMAP_VPOS.get_ARTBEZ2_cUF_NN(""));
				((MyE2IF__DB_Component)oMaskeFuhreZusatzort.get_Component("BESTELLNUMMER")).set_cActualMaskValue(oMAP_VPOS.get_BESTELLNUMMER_cUF_NN(""));
				
				
				//die Buchungsinfos leermachen
				oMapFuhreZusatzOrt.get__DBComp("MANUELL_PREIS").set_cActualMaskValue("N");
				oMapFuhreZusatzOrt.get__DBComp("EINZELPREIS").set_cActualMaskValue("");
				oMapFuhreZusatzOrt.get__DBComp("STEUERSATZ").set_cActualMaskValue("");
				oMapFuhreZusatzOrt.get__DBComp("EU_STEUER_VERMERK").set_cActualMaskValue("");
				// ---------------------------

				//2014-06-02: die sonderlieferbedingung wieder entfernen
				//2014-06-05: die sonderlieferbedingung wieder entfernen
				if (S.isFull(oMapFuhreZusatzOrt.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV).get_cActualDBValueFormated())) {
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Die alternative Lieferbedingung im Fuhrenort wird durch das Laden des Kontraktes zurückgesetzt !")));
				}
				
				oMapFuhreZusatzOrt.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV).set_cActualMaskValue("");
				((FU_AL_Component)oMapFuhreZusatzOrt.get__Comp(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV)).clear_Lieferbedingungen();
				((FU_AL_Component)oMapFuhreZusatzOrt.get__Comp(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV)).refreshLieferbedingung();
				//2014-06-02

				
				//jetzt den popup-warner fuer die evtl. nicht vorhandenen avv-kontrakte aufrufen
				new FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden(	(DB_SEARCH_Adress)oMapFuhreZusatzOrt.get__Comp("ID_ADRESSE"),
												oThis,
												oThis.get_EK_VK(),
												oMAP_VPOS.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF(),
												(MyE2_DB_CheckBox)oMapFuhreZusatzOrt.get_Comp("OHNE_AVV_VERTRAG_CHECK"),
												bIS_PrimaryCall, false);
				
				
				//20180607: waehrungsanzeiger
				new FUO_Set_And_Valid_AnzeigeFremdWaehrung().setManual(FUO_MASK_DB_SEARCH_Kontrakt.this.EXT().get_oComponentMAP());

			}
		}
	}

	
	
	
	
	//eine button-klasse definieren, die einen popup-container mit einem KontraktSelector baut
	private class popupButtonMit_Schnell_Selektor extends MyE2_Button
	{
		public popupButtonMit_Schnell_Selektor()
		{
			super(E2_Selection_Row_With_Multi_Cols.ICON_FOR_SEARCHBUTTON);
			
			this.setToolTipText(new MyE2_String("Auswahl des Kontrakts ...").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new ownPopupWindow();
				}
			});
			
		}
		
		private class ownPopupWindow extends E2_BasicModuleContainer
		{

			public ownPopupWindow() throws myException
			{
				super();
				KontraktSelector oSel =  new ownRowSelector(FUO_MASK_DB_SEARCH_Kontrakt.this.get_EK_VK());
				this.add(oSel,E2_INSETS.I_2_2_2_2);
				oSel.START("", true);
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(350), new MyE2_String("Bitte wählen Sie einen Kontrakt aus !"));
			}

			
			private class ownRowSelector extends KontraktSelector
			{
				public ownRowSelector(String cEK_VK) throws myException
				{
					super(cEK_VK);
					FinderErlaubteZuordnungen oFinder = new FinderErlaubteZuordnungen(); 

					this.set_vIDs_zugelassene_kontrakt_Positionen(oFinder.get_vID_Zugelassen());
					//this.set_bDisableNotAllwedIDs(!oFinder.bZusatzOrteBeidSeitig);
				}

				@Override
				public void actionForExit() throws myException
				{
					FUO_MASK_DB_SEARCH_Kontrakt.this.set_cActualMaskValue(this.get_cWertFuerAusstieg(),true,true,true);
					ownPopupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}

				@Override
				public void actionForGotoSecondCol() throws myException
				{
				}
			}

		}
	}
	

	
	
	
	
	//markiert die kontrakte, die zum gegenpart zugeordnet sind (falls ein gegenpart existiert) unnd laesst nur diese zur zuordnung aktiv
	private class manipulator_Markiere_zugeordneteKontrakt_positionen extends XX_FormaterForFoundButtons
	{
		private Vector<String> 			vIDs_Zugeordnet = new Vector<String>();
		private boolean   				bZusatzOrteBeidSeitig = false;
		private __RECORD_VPOS_KON_EXT   recVPPOS_KON = null;

		@Override
		public void DO_Format(XX_Button4SearchResultList button) throws myException
		{
			if (this.recVPPOS_KON==null)
			{
				this.recVPPOS_KON=new __RECORD_VPOS_KON_EXT(button.EXT().get_C_MERKMAL());    //hier steht die ID_VPOS_KON
			}

			
			if (this.vIDs_Zugeordnet.size()==0 || this.vIDs_Zugeordnet.contains(button.EXT().get_C_MERKMAL()))
			{
				if (this.vIDs_Zugeordnet.size()==0)
				{
					button.setFont(new E2_FontPlain());
				}
				else
				{
					button.setFont(new E2_FontBold());
				}
				
			}
			else
			{
				button.setFont(new E2_FontPlain(-2));
				if (!this.bZusatzOrteBeidSeitig)
				{
					//button.set_bEnabled_For_Edit(false);
				}
				
				
			}

			if (button.get_DefSpalteLayout().get_DBNameOfColumn().equals("REST"))
			{
				button.set_Text(MyNumberFormater.formatDez(this.recVPPOS_KON.get_bdRestMenge(), 0, true));
			}
			
		}

		@Override
		public void RESET() throws myException
		{
			FinderErlaubteZuordnungen oFinder = new FinderErlaubteZuordnungen(); 
			vIDs_Zugeordnet.removeAllElements();
			vIDs_Zugeordnet.addAll(oFinder.get_vID_Zugelassen());
			this.bZusatzOrteBeidSeitig = oFinder.get_bZusatzOrteBeidSeitig();
		}


		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException 
		{
			this.recVPPOS_KON=null;
		}
		
		
	}
	
	
	/*
	 * hilfsklasse zum suchen der zuordnungen zum jeweils anderen kontrakte in der fuhre.
	 * Wenn auf beiden Seiten der fuhre zusatzorte sind, dann kann es keine einschraenkung der zuordnung geben.
	 * In diesem Fall wird nur eine markierung hervorgehoben
	 */
	private class FinderErlaubteZuordnungen
	{
		private Vector<String>  vID_Zugelassen = 		new Vector<String>();
		private boolean   		bZusatzOrteBeidSeitig = false;

		public FinderErlaubteZuordnungen() throws myException
		{
			super();
			
			FUO_MASK_DB_SEARCH_Kontrakt oThis = FUO_MASK_DB_SEARCH_Kontrakt.this;
			
			//dann die zusatzorte zaehlen
			PRUEF_RECORD_VPOS_TPA_FUHRE oRecFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(oThis.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),true);

			long[] lAnzahlOrte = oRecFuhre.get_arrayAnzahlZusatzorte();
				
			if (lAnzahlOrte[0]>0 && lAnzahlOrte[1]>0)
			{
				bZusatzOrteBeidSeitig = true;
			}
			
			
			//komplementaerer Wert (falls vorhanden)
			long lAndereSeiteKontrakt = oThis.oFUO_DaughterComponent.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_"+oThis.get_EK_VK_Komplement(), true, true, new Long(0)).longValue();
			
			if (lAndereSeiteKontrakt>=1)
			{
				RECORD_VPOS_KON recVPosKon = new RECORD_VPOS_KON(lAndereSeiteKontrakt);

				//eine von beiden listen gilt (je nach EK oder VK-Typ)
				this.vID_Zugelassen.addAll(bibALL.get_vBuildValueVectorFromHashmap(recVPosKon.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk().get_ID_VPOS_KON_EK_hmString_UnFormated("")));
				this.vID_Zugelassen.addAll(bibALL.get_vBuildValueVectorFromHashmap(recVPosKon.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek().get_ID_VPOS_KON_VK_hmString_UnFormated("")));
			}

			
		}

		public Vector<String> get_vID_Zugelassen()
		{
			return vID_Zugelassen;
		}

		public boolean get_bZusatzOrteBeidSeitig()
		{
			return bZusatzOrteBeidSeitig;
		}
	}
	
	
}
