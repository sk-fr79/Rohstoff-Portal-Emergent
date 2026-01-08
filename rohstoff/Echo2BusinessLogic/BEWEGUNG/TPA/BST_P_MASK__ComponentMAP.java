package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.math.BigDecimal;
import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MapValidatorBeforeSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Sel_Zahlungsbedingungen;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_P_MASK_DB_SEARCH_ArtikelBez;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_SQLFieldMAP;
import rohstoff.utils.MyArtikel;
import rohstoff.utils.My_MWST;
import rohstoff.utils.My_MWSTSaetze;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class BST_P_MASK__ComponentMAP extends BS_ComponentMAP {

	private FU_MASK_ComponentMAP   oFU_MASK_ComponentMAP = null;
	
	public BST_P_MASK__ComponentMAP(boolean  bAddOpenTPA_Button) throws myException 
	{
		super(new BST_P_MASK_SQLFieldMAP());
		
		BST_P_MASK_SQLFieldMAP oFM = (BST_P_MASK_SQLFieldMAP)this.get_oSQLFieldMAP();

		this.oFU_MASK_ComponentMAP = new FU_MASK_ComponentMAP(new FU_MASK_SQLFieldMAP(oFM,false),false,bAddOpenTPA_Button,E2_MODULNAMES.NAME_MODUL_TPA_POS_MASK);

		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VKOPF_TPA")),					new MyE2_String("ID Kopf"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA")),					new MyE2_String("ID Pos"));
		
		
		
		this.add_Component(new DB_SEARCH_ArtikelBez(	oFM.get_("ID_ARTIKEL_BEZ"),	
														null, null, null, null, null,true,null),	new MyE2_String("ID-Art-Bez."));

		this.add_Component(new BS_SelectField_POSITIONSTYP(oFM.get_("POSITION_TYP"),oFM,false),	new MyE2_String("Pos.Typ"));
		
		
		/*
		 * 	
		 * haengt folgende felder an:
		 * "ANZAHL"
		 * "ARTBEZ1"
		 * "ARTBEZ2"
		 * "EINZELPREIS"
		 * "GESAMTPREIS"
		 * "EINZELPREIS_ABZUG"
		 * "EINZELPREIS_RESULT"
		 * "GESAMTPREIS_ABZUG"
		 * "EINZELPREIS_FW"
		 * "GESAMTPREIS_FW"
		 * "EINZELPREIS_ABZUG_FW"
		 * "EINZELPREIS_RESULT_FW"
		 * "GESAMTPREIS_ABZUG_FW"
		 * "WAEHRUNGSKURS"
		 * ANR1
		 * ANR2
		 * ID_ARTIKEL
		 * POSITIONSNUMMER
		 * MWST
		 * LIEFERBEDINGUNGEN
		 * ZAHLUNGSBEDINGUNGEN
		 * ID_ZAHLUNGSBEDINGUNGEN
		 * ZAHLTAGE
		 * FIXMONAT
		 * FIXTAG
		 * SKONTO_PROZENT
		 */ 
		BS__CompMap_FieldMAP_Gemeinsamkeiten.add_Basic_POSITION_Fields_To_ComponentMap(this, oFM, "TPA","TPA");

		
		this.add_Component(new BS_ComboBox_EINHEIT(oFM),new MyE2_String("Mg.-EH"));
		this.add_Component(new BS_ComboBox_EINHEIT_PREIS(oFM),new MyE2_String("Pr.-EH"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGENDIVISOR")),new MyE2_String("Divisor"));
		
		//neues feld fuer bemerkungen in der fuhren  -- ENZ_ALARM
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG_INTERN")),new MyE2_String("Bemerkung intern"));
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).set_iWidthPixel(400);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).set_iRows(4);

		
		
//		/*
//		 * Feldgroessen
//		 */
		((MyE2_DB_TextField)this.get("ANZAHL")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ANZAHL")).set_iWidthPixel(100);
		
		
		BS__CompMap_FieldMAP_Gemeinsamkeiten.format_BasicPositionFields(this);

		((MyE2_DB_TextField)this.get("MENGENDIVISOR")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("MENGENDIVISOR")).set_iWidthPixel(60);
		
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VKOPF_TPA")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VKOPF_TPA")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VPOS_TPA")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VPOS_TPA")).set_iWidthPixel(80);
		
		/*
		 * bei suchfeld wird die anzeige ausgeblenden
		 */
		((DB_SEARCH_ArtikelBez)this.get("ID_ARTIKEL_BEZ")).set_bTextForAnzeigeVisible(false);
		((DB_SEARCH_ArtikelBez)this.get("ID_ARTIKEL_BEZ")).set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		
		
		/*
		 * abgeschaltete felder
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_READ_ONLY_Fields(this);

		((MyE2_DB_TextField)this.get("ANR1")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ANR2")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VKOPF_TPA")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VPOS_TPA")).EXT().set_bDisabledFromBasic(true);

		// wird erstmal totgestellt, nur artikel-positionen erlaubt
		((BS_SelectField_POSITIONSTYP)this.get("POSITION_TYP")).EXT().set_bDisabledFromBasic(true);
		
		//TEST20110325
		this.set_oMAPSettingAgent(new BST_P_MASK_MAP_SETTING_AGENT());
		
		this.add_oMAPValidator(new BS_P_MapValidatorBeforeSave(true));
	}

	
	
	
	
	/*
	 * mask-setting-agent fuer das laden der artikel in die maske
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			if (bAfterAction)
			{
				
				E2_ComponentMAP 			oMap = 				oSearchField.EXT().get_oComponentMAP();
				
				String cQuery = "SELECT  JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ," +				// 0
										"JT_ARTIKEL.ID_ARTIKEL, " +						// 1
										"JT_ARTIKEL_BEZ.ARTBEZ1," +						// 2
										"JT_ARTIKEL_BEZ.ARTBEZ2, " +					// 3
										"JT_ARTIKEL.ANR1, " +							// 4
										"JT_ARTIKEL_BEZ.ANR2 " +						// 5
										"  FROM " +
										bibE2.cTO()+".JT_ARTIKEL_BEZ, "+bibE2.cTO()+".JT_ARTIKEL " +
										"  WHERE  JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL  AND " +
										" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+cMaskValue;
				
				MyDBToolBox oDB = bibALL.get_myDBToolBox();
				String[][] oRueck = oDB.EinzelAbfrageInArray(cQuery,"");
				bibALL.destroy_myDBToolBox(oDB);
				
				
				if (oRueck == null || oRueck.length != 1)
				{
					throw new myException("BST_P_ComponentMAP_MASK:ownMaskActionAfterFound:Error filling mask with search-result");
				}
				else
				{
					HashMap<String, MyE2IF__Component> oHMReal = oMap.get_REAL_ComponentHashMap();
					
					
					//2012-05-16: zahlungsbedingungen genauso laden wie bei anderen bewegungssaetzen
					//jetzt nachsehen, ob es eine ARTIKEL_BEZ_LIEF - eintragung dazu gibt
					//zuerst das beschraenkungsfeld des kopfsatzes suchen
					if (BST_P_MASK__ComponentMAP.this.get_oSQLFieldMAP().get_("ID_VKOPF_TPA") instanceof SQLFieldForRestrictTableRange)
					{
						RECORD_VKOPF_TPA  recTPA = new RECORD_VKOPF_TPA(
								((SQLFieldForRestrictTableRange)BST_P_MASK__ComponentMAP.this.get_oSQLFieldMAP().get_("ID_VKOPF_TPA")).get_cRestictionValue_IN_DB_FORMAT()
								);
						
						RECLIST_ARTIKELBEZ_LIEF  rlArtbezLief = new RECLIST_ARTIKELBEZ_LIEF(
								"SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+cMaskValue+
								" AND ID_ADRESSE="+recTPA.get_ID_ADRESSE_cUF());
						
						RECORD_ARTIKELBEZ_LIEF recArtbez_lief = null;
						if (rlArtbezLief.get_vKeyValues().size()>0) 	recArtbez_lief = rlArtbezLief.get(0);
						
						if (recArtbez_lief!=null)
						{
							if (recArtbez_lief.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null)
							{
								//den drop-down-wert setzen 
								((MyE2IF__DB_Component)oMap.get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recArtbez_lief.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(""));
								//und die werte nachladen
								BS_Sel_Zahlungsbedingungen.LeseID_Zahlungsbedingung_und_setze_korrellierendeFelder(oMap);
							}
						}
					}
					
					
					// wenn  nur ein gueltiger MWST-Satz fuer diese Art-Bez existiert, dann 
					// wird dieser 
					My_MWSTSaetze oBSMW = new My_MWSTSaetze(null,oRueck[0][0]);
					((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue("");
					if (oBSMW.get_vMWSTArtBez().size()==1)
					{
						My_MWST oBSMWST = (My_MWST)oBSMW.get_vMWSTArtBez().get(0);
						((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue(oBSMWST.get_cMWST_Formated());
					}
					
					MyArtikel oArtikel = new MyArtikel(oRueck[0][1]);
					
					
					((MyE2IF__DB_Component)oHMReal.get("ANR1")).set_cActualMaskValue(oRueck[0][4]);
					((MyE2IF__DB_Component)oHMReal.get("ANR2")).set_cActualMaskValue(oRueck[0][5]);

					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ1")).set_cActualMaskValue(oRueck[0][2]);
					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2")).set_cActualMaskValue(oRueck[0][3]);
					
					((MyE2IF__DB_Component)oHMReal.get("ID_ARTIKEL")).set_cActualMaskValue(oRueck[0][1]);
					
					((MyE2IF__DB_Component)oHMReal.get("EINHEITKURZ")).set_cActualMaskValue(oArtikel.get_EINHEIT());
					((MyE2IF__DB_Component)oHMReal.get("EINHEIT_PREIS_KURZ")).set_cActualMaskValue(oArtikel.get_EINHEIT_PREIS());
					((MyE2IF__DB_Component)oHMReal.get("MENGENDIVISOR")).set_cActualMaskValue(oArtikel.get_MENGENDIVISOR());
					
				}
				
			}
		}
		
	}

		
	
	



	public FU_MASK_ComponentMAP get_oFU_MASK_ComponentMAP()
	{
		return this.oFU_MASK_ComponentMAP;
	}
	
	
	
	
	
	
}
