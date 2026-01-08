package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterForFoundButtons;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterPrepareContentForNew;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_KON_EXT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_AnzeigeFremdWaehrung;
import rohstoff.Echo2BusinessLogic._4_ALL.KontraktSelector;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FU_MASK_DB_SEARCH_Kontrakt extends FU___MASK_SEARCH_KONTRAKT_POS
{
	

	public FU_MASK_DB_SEARCH_Kontrakt(SQLField osqlField, String cEK_VK) throws myException
	{
		super(		osqlField,cEK_VK);
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		this.set_oMaskActionAfterPrepareContentForNew(new ownActionCleanLieferbed());
		
		this.get_oSeachBlock().set_bAllowEmptySearchField(true);
		
		this.get_oTextForAnzeige().setFont(new E2_FontPlain(-2));
		this.get_oTextForAnzeige().setWidth(new Extent(200));
		this.get_oTextFieldForSearchInput().setWidth(new Extent(70));
		
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain(-2));
		if (cEK_VK.equals("EK"))
		{
			this.get_oLabel4Anzeige().set_iWidth(220);
		}
		else
		{
			this.get_oLabel4Anzeige().set_iWidth(220);
		}
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(60);
		
		String cFELD_ADRESSE = "ID_ADRESSE_START";
		if (cEK_VK.equals("VK"))
		{
			cFELD_ADRESSE = "ID_ADRESSE_ZIEL";
		}

		boolean useNewKontrakt = ENUM_MANDANT_DECISION.USE_NEW_KONTRAK_TYP.is_YES();
		
		this.set_AddOnComponent(new E2_ComponentGroupHorizontal(0,new popupButtonMit_Schnell_Selektor(),new lagerButton(cFELD_ADRESSE),
				(useNewKontrakt?new openKontraktPosNG(): new openKontraktPos()) 
				,E2_INSETS.I_0_0_1_0));
		
		this.set_bShowSearchButtonLeft(true);

		
		this.set_FormatterForButtons(new manipulator_Markiere_zugeordneteKontrakt_positionen());
		
		
	}

	
	//eigener actionagent der die anzeige der lieferbedingung resettet, falls der kontrakt geleert wird
	private class ownActionCleanLieferbed extends  XX_MaskActionAfterPrepareContentForNew  {
		@Override
		public void doMaskSettingsAfterPrepareContentForNew(MyE2_DB_MaskSearchField oDB_SearchField, boolean bSetDefaults) throws myException {
			//2014-06-02: die sonderlieferbedingung wieder entfernen
			FU_MASK_DB_SEARCH_Kontrakt oThis = FU_MASK_DB_SEARCH_Kontrakt.this;
			E2_ComponentMAP  oMAPFuhre = oThis.EXT().get_oComponentMAP();
			String cEK_VK = oThis.get_EK_VK();
			
			//2014-06-05: die sonderlieferbedingung wieder entfernen
			if (S.isFull(oMAPFuhre.get__DBComp(cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).get_cActualDBValueFormated())) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Die alternative Lieferbedingung in der Fuhre wird durch das Entfernen des Kontraktes zurückgesetzt !")));
			}

			
			oMAPFuhre.get__DBComp(cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).set_cActualMaskValue("");
			((FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).clear_Lieferbedingungen();
			((FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).refreshLieferbedingung();
			
			//20180607: waehrung aktualisieren
			new FU_Set_And_Valid_AnzeigeFremdWaehrung().setManual(FU_MASK_DB_SEARCH_Kontrakt.this.EXT().get_oComponentMAP());

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
			
			
			//2011-09-27: neue SearchFieldStruktur
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

//		@Override
//		public Component DO_Transform(MyE2_Button oButton) throws myException {
//			return null;
//		}

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
			
			FU_MASK_DB_SEARCH_Kontrakt oThis = FU_MASK_DB_SEARCH_Kontrakt.this;
			
			//ist die maske im edit-mode
			if (!oThis.EXT().get_oComponentMAP().get_bIs_Neueingabe())
			{
				//dann die zusatzorte zaehlen
				PRUEF_RECORD_VPOS_TPA_FUHRE oRecFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(),true);
	
				long[] lAnzahlOrte = oRecFuhre.get_arrayAnzahlZusatzorte();
					
				if (lAnzahlOrte[0]>0 && lAnzahlOrte[1]>0)
				{
					bZusatzOrteBeidSeitig = true;
				}
			}
			
			
			try
			{
				//komplementaerer Wert (falls vorhanden)
				long lAndereSeiteKontrakt = oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_"+oThis.get_EK_VK_Komplement(), true, true, new Long(0)).longValue();
				
				if (lAndereSeiteKontrakt>=1)
				{
					RECORD_VPOS_KON recVPosKon = new RECORD_VPOS_KON(lAndereSeiteKontrakt);

					//eine von beiden listen gilt (je nach EK oder VK-Typ)
					this.vID_Zugelassen.addAll(bibALL.get_vBuildValueVectorFromHashmap(recVPosKon.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_vk().get_ID_VPOS_KON_EK_hmString_UnFormated("")));
					this.vID_Zugelassen.addAll(bibALL.get_vBuildValueVectorFromHashmap(recVPosKon.get_DOWN_RECORD_LIST_EK_VK_BEZUG_id_vpos_kon_ek().get_ID_VPOS_KON_VK_hmString_UnFormated("")));
				}
			} 
			catch (myException e)
			{
				e.printStackTrace();
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
	
	
	/*
	 * mask-setting-agent fuer das laden der artikelbez in die maske, getrennt nach EK-Bezeichnung oder VK-Bezeichnung
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public ownMaskActionAfterFound()
		{
			super();
		}


		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValueFromField, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			if (bAfterAction)
			{
				String cMaskValue = new MyLong(cMaskValueFromField).get_cUF_LongString();
				
				if (S.isFull(cMaskValue))
				{
					
					FU_MASK_DB_SEARCH_Kontrakt oThis = FU_MASK_DB_SEARCH_Kontrakt.this;
					
					String cEK_VK = oThis.get_EK_VK();
					String cEK_VK_Komplement = "VK";
					String cSTART_ZIEL = 				"START";
					String cSTART_ZIEL_komplement = 	"ZIEL";
					if (cEK_VK.equals("VK"))
					{
						cSTART_ZIEL = 				"ZIEL";
						cSTART_ZIEL_komplement = 	"START";
						cEK_VK_Komplement = "EK";
					}
	
					//zuerst den komplementaeren kontrakt rausziehen und, falls vorhanden, die sorten-Hauptnummer (Stelle 1-2) vergleichen
					String cID_VPOS_KON_Komplement = null;
					cID_VPOS_KON_Komplement = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_VPOS_KON_"+cEK_VK_Komplement, true, true, new Long(0)).longValue();

					boolean bEK_VK_SorteLock = oThis.EXT().get_oComponentMAP().get_cActualDBValueFormated("EK_VK_SORTE_LOCK","").equals("Y");
					
					
					if (!cID_VPOS_KON_Komplement.trim().equals("0"))
					{
						RECORD_VPOS_KON oMapInput = 		new RECORD_VPOS_KON(cMaskValue);
						RECORD_VPOS_KON oMapKomplement = 	new RECORD_VPOS_KON(cID_VPOS_KON_Komplement);
						
						if (!oMapInput.get_ANR1_cUF_NN("--").substring(0,2).equals(oMapKomplement.get_ANR1_cUF_NN("++").substring(0,2)))
						{
							if (bEK_VK_SorteLock)
							{
							
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Sorten der Kontrakte EK zu VK passen nicht zusammen !!!"));
								oThis.prepare_ContentForNew(false);
	
								//sicherheitshalber die adresse auch leermachen
								oThis.EXT().get_oComponentMAP().get__DBComp("ID_ADRESSE_"+cSTART_ZIEL).prepare_ContentForNew(false);
								
								return;
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Warning_Message("Die Sorten der Kontrakte EK zu VK passen nicht zusammen !!!"));
							}
						}
					}
					
					
					E2_ComponentMAP  oMAPFuhre = oSearchField.EXT().get_oComponentMAP();
	
					//zustandvariable der folgeeintraege triggern
					oMAPFuhre.get__Comp("ID_ADRESSE_"+cSTART_ZIEL).EXT().SET_Schalter(FU___CONST.SCHALTER_PRUEFE_UND_WARNE_WG_AVV_VERTRAG, false);
	
					
					RECORD_VPOS_KON oMAP_VPOS = new RECORD_VPOS_KON(cMaskValue);
	
					// lieferant laden und folgeaktion durchfuehren 
					((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ADRESSE_"+cSTART_ZIEL)).set_cActualMaskValue(oMAP_VPOS.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF(),true,true,false);
	
					if (cEK_VK.equals("EK"))  //auf der EK-seite wird die sorte auf jeden fall definiert
					{
						// sorten laden und fuellen / OHNE die fuellaction, da dort ein popup losgeht, das die selektion EK-VK-Bezeichnung startet   
						((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL")).set_cActualMaskValue(oMAP_VPOS.get_ID_ARTIKEL_cUF(),true,false,false);

						((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL_BEZ_"+cEK_VK)).set_cActualMaskValue(oMAP_VPOS.get_ID_ARTIKEL_BEZ_cUF(),true,true,false);
					}
					
					if (cEK_VK.equals("VK"))  //auf der VK-seite wird die sorte dann geladen, wenn auf der ek-seite ein lager steht
					{
						if (oMAPFuhre.get_LActualDBValue("ID_ADRESSE_"+cSTART_ZIEL_komplement, true,true, new Long(-1)).longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-2)))
						{
							// sorten laden und fuellen / OHNE die fuellaction, da dort ein popup losgeht, das die selektion EK-VK-Bezeichnung startet   
							((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL")).set_cActualMaskValue(oMAP_VPOS.get_ID_ARTIKEL_cUF(),true,false,false);
						}

						((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL_BEZ_"+cEK_VK)).set_cActualMaskValue(oMAP_VPOS.get_ID_ARTIKEL_BEZ_cUF(),true,true,false);
					}

					// falls auf der komplemetaerseite ein lager steht (eigen-adresse), dann die erste passende artikelbezeichung rausziehen und
					// auf der komplementaeren seite eintragen
					if (oMAPFuhre.get_LActualDBValue("ID_ADRESSE_"+cSTART_ZIEL_komplement, true,true, new Long(-1)).longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-2)))
					{
						// sorten laden und fuellen / OHNE die fuellaction, da dort ein popup losgeht, das die selektion EK-VK-Bezeichnung startet   
						((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL")).set_cActualMaskValue(oMAP_VPOS.get_ID_ARTIKEL_cUF(),true,false,false);
						
						/*
						 * dann muss noch die artikelbezeichnung auf der EK-seite geladen werden, damit nicht noch extra eine suche durchgefuehrt werden muss
						 * Dazu muss die erste vorhandene artikelbezeichnung, die der eigenen adresse zugeteilt wurde, geladen werden
						 */
						String cQuery = "SELECT JT_ARTIKELBEZ_LIEF.* FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF "+
												"INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ON (JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ) "+
												"INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL= JT_ARTIKEL.ID_ARTIKEL) "+
												"WHERE JT_ARTIKEL.ID_ARTIKEL="+oMAP_VPOS.get_ID_ARTIKEL_cUF();
						
						if (cEK_VK.equals("VK"))   //dann wird auf der EK-seite die artikelbez gesucht und damit muss sie als artikelbez_lief bekannt sein (wegen AVV-Code)
						{
							cQuery += " AND JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1");
						}
						cQuery += " ORDER BY JT_ARTIKEL_BEZ.ANR2";
						
						RECLIST_ARTIKELBEZ_LIEF  reclistArtbezLIEF = new RECLIST_ARTIKELBEZ_LIEF(cQuery);
						
						if (reclistArtbezLIEF.get_vKeyValues().size()>0)
						{
							RECORD_ARTBEZ_LIEF_extend recArtBezLief = new RECORD_ARTBEZ_LIEF_extend(reclistArtbezLIEF.get(0));
							((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL_BEZ_"+cEK_VK_Komplement)).set_cActualMaskValue(recArtBezLief.get_ID_ARTIKEL_BEZ_cUF(),true,true,false);
						}
						else
						{
							((MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_ARTIKEL_BEZ_"+cEK_VK_Komplement)).prepare_ContentForNew(false);
						}
					}

	
					// die kontraktbezogenen felder fuellen,
					// step 1 die bezeichnungen aus den oHMArtBezEK/oHMArtBezVK
					oMAPFuhre.get__DBComp("BESTELLNUMMER_"+cEK_VK).set_cActualMaskValue("");
	
					// dann gegebenenfalls austauschen geben die kontrakteintraege
					oMAPFuhre.get__DBComp("ARTBEZ1_"+cEK_VK).set_cActualMaskValue(oMAP_VPOS.get_ARTBEZ1_cUF_NN(""));
					oMAPFuhre.get__DBComp("ARTBEZ2_"+cEK_VK).set_cActualMaskValue(oMAP_VPOS.get_ARTBEZ2_cUF_NN(""));
					oMAPFuhre.get__DBComp("BESTELLNUMMER_"+cEK_VK).set_cActualMaskValue(oMAP_VPOS.get_BESTELLNUMMER_cUF_NN(""));
					
					//2011-02-10: transportmittel nur ueberschreiben, wenn was im kontrakt steht
					if (S.isFull(oMAP_VPOS.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_TRANSPORTMITTEL_cUF_NN("")))
					{
						oMAPFuhre.get__DBComp("TRANSPORTMITTEL").set_cActualMaskValue(oMAP_VPOS.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0).get_TRANSPORTMITTEL_cUF_NN(""));
					}
					
					
					//die Buchungsinfos leermachen
					oMAPFuhre.get__DBComp("MANUELL_PREIS_"+cEK_VK).set_cActualMaskValue("N");
					oMAPFuhre.get__DBComp("EINZELPREIS_"+cEK_VK).set_cActualMaskValue("");
					oMAPFuhre.get__DBComp("STEUERSATZ_"+cEK_VK).set_cActualMaskValue("");
					oMAPFuhre.get__DBComp("EU_STEUER_VERMERK_"+cEK_VK).set_cActualMaskValue("");
					// ---------------------------
					//2013-09-26: die neue steuerid auch leermachen
					oMAPFuhre.get__DBComp(cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$ID_TAX_EK:_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue("");
				
					
					//2014-06-02: die sonderlieferbedingung wieder entfernen
					//2014-06-05: die sonderlieferbedingung wieder entfernen
					if (S.isFull(oMAPFuhre.get__DBComp(cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).get_cActualDBValueFormated())) {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Die alternative Lieferbedingung in der Fuhre wird durch das Laden des Kontraktes zurückgesetzt !")));
					}
					
					oMAPFuhre.get__DBComp(cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).set_cActualMaskValue("");
					((FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).clear_Lieferbedingungen();
					((FU_AL_Component)oMAPFuhre.get__Comp(cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).refreshLieferbedingung();
					//2014-06-02

					
					//jetzt den popup-warner fuer die evtl. nicht vorhandenen avv-kontrakte aufrufen
					new FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden(	(DB_SEARCH_Adress)oMAPFuhre.get__Comp("ID_ADRESSE_"+cSTART_ZIEL),
													(MyE2_DB_MaskSearchField)oMAPFuhre.get__Comp("ID_VPOS_KON_"+cEK_VK),
													FU_MASK_DB_SEARCH_Kontrakt.this.get_EK_VK(),
													oMAP_VPOS.get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_ID_ADRESSE_cUF(),
													(MyE2_DB_CheckBox)oMAPFuhre.get_Comp("OHNE_AVV_VERTRAG_CHECK"),
													bIS_PrimaryCall, false);

				} 
				
				
				//20180607: waehrung aktualisieren
				new FU_Set_And_Valid_AnzeigeFremdWaehrung().setManual(FU_MASK_DB_SEARCH_Kontrakt.this.EXT().get_oComponentMAP());

			}
		}
	}

	
	
	//eine button-klasse definieren, die einen popup-container mit einem KontraktSelector baut
	private class popupButtonMit_Schnell_Selektor extends MyE2_Button
	{
		public popupButtonMit_Schnell_Selektor()
		{
			//super(E2_Selection_Row_With_Multi_Cols.ICON_FOR_SEARCHBUTTON);
			super(E2_ResourceIcon.get_RI("suchkaskade_mini.png"));
			
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
				KontraktSelector oSel =  new ownRowSelector(FU_MASK_DB_SEARCH_Kontrakt.this.get_EK_VK());
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
					FU_MASK_DB_SEARCH_Kontrakt.this.set_cActualMaskValue(this.get_cWertFuerAusstieg(),true,true,true);
					ownPopupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}

				@Override
				public void actionForGotoSecondCol() throws myException
				{
				}
			}

		}
	}
	


	
	
}
