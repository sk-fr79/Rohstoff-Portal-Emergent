package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.math.BigDecimal;
import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.utils.MyArtikelbezeichung_NG;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class FU_MASK_DB_SEARCH_SORTE_BEZ extends DB_SEARCH_ArtikelBez
{
	private String EK_VK = null;
	
	public FU_MASK_DB_SEARCH_SORTE_BEZ(SQLField osqlField) throws myException
	{
		super(osqlField,  null, null, null, null, null);
		
		
		if (osqlField.get_cFieldLabel().equals("ID_ARTIKEL_BEZ_EK"))
		{
			this.EK_VK = "EK";
			//this.set_oTell_Adress_ID(new ownTell_Me_The_adresse_id());
		}
		else
		{
			this.EK_VK = "VK";
		}
			
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound(this.EK_VK));
		this.add_ValidatorStartSearch(new VALID_ADRESS_UND_SORTE_VORHANDEN(this.EK_VK));
		this.get_oSeachBlock().set_bAllowEmptySearchField(true);
		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		this.set_bTextForAnzeigeVisible(false);
		this.get_oTextFieldForSearchInput().set_iWidthPixel(50);

	}

	
	
	
	
	/*
	 * mask-setting-agent fuer das laden der artikelbez in die maske, getrennt nach EK-Bezeichnung oder VK-Bezeichnung
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		private String cEK_ODER_VK = "";
		
		public ownMaskActionAfterFound(String cek_oder_vk)
		{
			super();
			cEK_ODER_VK = cek_oder_vk;
		}



		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			FU_MASK_DB_SEARCH_SORTE_BEZ oThis = FU_MASK_DB_SEARCH_SORTE_BEZ.this;

			if (bAfterAction)
			{
				E2_ComponentMAP 			oMapFuhre = 				oSearchField.EXT().get_oComponentMAP();
			
				String cEK_VK = FU_MASK_DB_SEARCH_SORTE_BEZ.this.EK_VK;
				
				String cID_ADRESSE_START = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMapFuhre.get__Comp("ID_ADRESSE_START")).get_cActualMaskValue(),".","");

				boolean bLieferungExMandant = cID_ADRESSE_START.equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""));
				
				//NEU_09
				String cID_ADRESSE_ZIEL = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMapFuhre.get__Comp("ID_ADRESSE_ZIEL")).get_cActualMaskValue(),".","");

				// kann eigentlich nicht sein
				if (cEK_VK.equals("EK") && !bibALL.isInteger(cID_ADRESSE_START))
					throw new myException("FU_MASK_DB_SEARCH_SORTE_BEZ:doMaskSettingsAfterValueWrittenInMaskField:Error: no adressid in ID_ADRESSE_START-Field");
				
				//NEU_09
				if (cEK_VK.equals("VK") && !bibALL.isInteger(cID_ADRESSE_ZIEL))
					throw new myException("FU_MASK_DB_SEARCH_SORTE_BEZ:doMaskSettingsAfterValueWrittenInMaskField:Error: no adressid in ID_ADRESSE_ZIEL-Field");

				

				// block NEU_09
				// lieferanten-Sorte muss den EAK_COD mitabfragen,
				// zielsorte nicht
				MyArtikelbezeichung_NG oArtBez = null;
				
				if (oThis.EK_VK.equals("EK"))
				{
					oArtBez = new MyArtikelbezeichung_NG(cMaskValue,cID_ADRESSE_START,"EK",false);
				}
				else
				{
					oArtBez = new MyArtikelbezeichung_NG(cMaskValue,cID_ADRESSE_ZIEL,"VK",false);
				}

				HashMap<String,MyE2IF__Component> oHMReal = oMapFuhre.get_REAL_ComponentHashMap();
					
				((MyE2IF__DB_Component)oHMReal.get("ANR1_"+this.cEK_ODER_VK)).set_cActualMaskValue(oArtBez.get_ANR1());
				((MyE2IF__DB_Component)oHMReal.get("ANR2_"+this.cEK_ODER_VK)).set_cActualMaskValue(oArtBez.get_ANR2());

				((MyE2IF__DB_Component)oHMReal.get("ARTBEZ1_"+this.cEK_ODER_VK)).set_cActualMaskValue(oArtBez.get_ARTBEZ1());
				((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2_"+this.cEK_ODER_VK)).set_cActualMaskValue(oArtBez.get_ARTBEZ2());
				
				// allgemeine werte, unabhaengig von lieferant/abnehmer
				((MyE2IF__DB_Component)oHMReal.get("EUCODE")).set_cActualMaskValue(oArtBez.get_EUCODE());
				((MyE2IF__DB_Component)oHMReal.get("EUNOTIZ")).set_cActualMaskValue(oArtBez.get_EUNOTIZ());
				((MyE2IF__DB_Component)oHMReal.get("ZOLLTARIFNR")).set_cActualMaskValue(oArtBez.get_ZOLLTARIFNR());

				((MyE2IF__DB_Component)oHMReal.get("BASEL_CODE")).set_cActualMaskValue(oArtBez.get_BASEL_CODE());
				((MyE2IF__DB_Component)oHMReal.get("BASEL_NOTIZ")).set_cActualMaskValue(oArtBez.get_BASEL_NOTIZ());

				((FU_MASK_DB_COMBO_MengenEinheit)oHMReal.get("EINHEIT_MENGEN")).set_cActualMaskValue(oArtBez.get_cEINHEIT());

				//2013-09-26: wegen steuerfindung werden die steuerfelder auf beiden seiten der fuhre geloescht
				
				if (oThis.EK_VK.equals("EK"))	{
					oMapFuhre.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_EK).set_cActualMaskValue("");
					oMapFuhre.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_EK).set_cActualMaskValue("");
					oMapFuhre.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_EK).set_cActualMaskValue("");
				} else {
					oMapFuhre.get__DBComp(_DB.VPOS_TPA_FUHRE$STEUERSATZ_VK).set_cActualMaskValue("");
					oMapFuhre.get__DBComp(_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue("");
					oMapFuhre.get__DBComp(_DB.VPOS_TPA_FUHRE$EU_STEUER_VERMERK_VK).set_cActualMaskValue("");
				}

				
				
				
				// falls EK-Sortenbezeichnung neu geladen wird, dann muss der EAK-kenner neu geladen werden
				MASK_COMPONENT_SEARCH_EAK_CODES  oFieldEAK_Code = (MASK_COMPONENT_SEARCH_EAK_CODES)oMapFuhre.get_Comp("ID_EAK_CODE");
				if (this.cEK_ODER_VK.equals("EK"))
				{
					//laden des AVV-codes
					new FU___Fill_EAK_Code(oArtBez,oFieldEAK_Code,bLieferungExMandant);
				}
				
				if (bIS_PrimaryCall)
				{
					new FU__MaskSettingsAfterInput__Plausibilitaet(oThis);
				}
			}
		}
	}

	
	



	
	
	/**
	 * @author martin
	 * Validator hat folgende aufgabe:
	 * 1. muss sicherstellen, die hauptsorte vorhanden ist
	 * 2. fuegt zusatz-bedingung bei der suche ein, die nur noch sorten zulaesst, die in der kundenspezifischen
	 *    artikelbezeichnung vorhanden sind.
	 */
	private class VALID_ADRESS_UND_SORTE_VORHANDEN extends XX_ActionValidator
	{
		private MyE2_String cError = new MyE2_String("");
	
		private String cEK_ODER_VK = "";
		
		public VALID_ADRESS_UND_SORTE_VORHANDEN(String cek_oder_vk)
		{
			super();
			cEK_ODER_VK = cek_oder_vk;
		}


		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			boolean bRueck = false;
			
			FU_MASK_DB_SEARCH_SORTE_BEZ oThis = FU_MASK_DB_SEARCH_SORTE_BEZ.this;
			
			E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();
			
			try
			{
				String cID_ADRESSE_START = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMap.get__Comp("ID_ADRESSE_START")).get_cActualMaskValue(),".","");
				String cID_ADRESSE_ZIEL = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMap.get__Comp("ID_ADRESSE_ZIEL")).get_cActualMaskValue(),".","");
				String cID_ARTIKEL = bibALL.ReplaceTeilString(((MyE2IF__DB_Component)oMap.get__Comp("ID_ARTIKEL")).get_cActualMaskValue(),".","");

				boolean bEK_EQUAL_VK = true;
				
				if (((MyE2IF__DB_Component)oMap.get__Comp("EK_VK_SORTE_LOCK")).get_cActualMaskValue().equals("N"))
					bEK_EQUAL_VK = false;
				
				
				
				if (!bibALL.isInteger(cID_ADRESSE_START)  || !bibALL.isInteger(cID_ADRESSE_ZIEL) || !bibALL.isInteger(cID_ARTIKEL))
				{
					this.cError = new MyE2_String("Bitte geben Sie zuerst die Adressen (START und ZIEL) ein, sowie die gefahrene Sorte!!");
				}
				else
				{
					bRueck = true;

					if (this.cEK_ODER_VK.equals("EK") && cID_ADRESSE_START.equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cF_NN(""))) 
					{
						String cWhereZusatz1 = "JT_ARTIKEL_BEZ.ID_ARTIKEL="+cID_ARTIKEL;
						String cWhereZusatz2 = 
								"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (SELECT DISTINCT JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ FROM "+
								bibE2.cTO()+".JT_ARTIKELBEZ_LIEF " +
								" WHERE "+
								" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE_START+")";
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cWhereZusatz1);
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cWhereZusatz2);
					}
					else
					{
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
						if (bEK_EQUAL_VK)
						{
							//die ersten 2 ziffern der artikel-hauptnummer rausziehen
							//MAP_ARTIKEL oMapArt = new MAP_ARTIKEL(cID_ARTIKEL);
							int iZahlStellenPruefung = bibALL.get_RECORD_MANDANT().get_ANR1_GLEICHHEIT_FUHRE_STELLEN_bdValue(new BigDecimal(2)).intValue();
							String cHNR = new RECORD_ARTIKEL(cID_ARTIKEL).get_ANR1_cUF_NN("  ").substring(0,iZahlStellenPruefung);
							
							String cWhereZusatz = "JT_ARTIKEL_BEZ.ID_ARTIKEL IN (SELECT ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE SUBSTR(ANR1,1,"+iZahlStellenPruefung+")="+bibALL.MakeSql(cHNR)+")";
							oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cWhereZusatz);
						}
						
					}
				}
					
			}
			catch (myException ex)
			{
				this.cError = new MyE2_String("Fehler beim Pruefen der Adressenfelder !!");
			}
			
			if (!bRueck)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
			}
			return oMV;
		}

		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			return new MyE2_MessageVector();
		}
		
	}


}
