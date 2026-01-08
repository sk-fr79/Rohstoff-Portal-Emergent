package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterForFoundButtons;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MapValidatorBeforeSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Sel_Zahlungsbedingungen;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;
import rohstoff.utils.My_MWST;
import rohstoff.utils.My_MWSTSaetze;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class BSA_P_MASK__ComponentMAP extends BS_ComponentMAP {

	private BSA_PA_MASK_ComponentMAP oComponentMAP_ZusatzfelderAngebot = null;
	
	private boolean    				bEK = true;

	public BSA_P_MASK__ComponentMAP(BS__SETTING oSetting) throws myException 
	{
		super(new BSA_P_MASK_SQLFieldMAP());
		
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_ANGEBOT)) {
			this.bEK=false;
		}
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		this.oComponentMAP_ZusatzfelderAngebot = new BSA_PA_MASK_ComponentMAP(oFM); 
		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VKOPF_STD")),	new MyE2_String("ID Kopf"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_STD")),	new MyE2_String("ID Pos"));
		this.add_Component(new BS_SelectField_POSITIONSTYP(oFM.get_("POSITION_TYP"),oFM,true),	new MyE2_String("Pos.Typ"));
		this.add_Component(new DB_SEARCH_ArtikelBez(oFM.get_("ID_ARTIKEL_BEZ"),null, null, null, null, null,true,null),	new MyE2_String("ID-Art-Bez."));
		
		
		
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
		
		//2014-11-14: klemmbrett fuer artbez 2
		String cKlemmbrettKenner = "AANGEBOT";
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_ANGEBOT))
		{
			cKlemmbrettKenner = "VANGEBOT";
		}
		
		BS__CompMap_FieldMAP_Gemeinsamkeiten.add_Basic_POSITION_Fields_To_ComponentMap(this, oFM, cKlemmbrettKenner,"STD");
		
		this.add_Component(new BS_ComboBox_EINHEIT(oFM),new MyE2_String("Mg.-EH"));
		this.add_Component(new BS_ComboBox_EINHEIT_PREIS(oFM),new MyE2_String("Pr.-EH"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGENDIVISOR")),new MyE2_String("Divisor"));
		
		
		/*
		 * Feldgroessen
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.format_BasicPositionFields(this);

		((MyE2_DB_TextField)this.get("MENGENDIVISOR")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("MENGENDIVISOR")).set_iWidthPixel(60);
		
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VKOPF_STD")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VKOPF_STD")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VPOS_STD")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VPOS_STD")).set_iWidthPixel(80);
		
		/*
		 * bei suchfeld wird die anzeige ausgeblenden
		 */
		((DB_SEARCH_ArtikelBez)this.get("ID_ARTIKEL_BEZ")).set_bTextForAnzeigeVisible(false);
		((DB_SEARCH_ArtikelBez)this.get("ID_ARTIKEL_BEZ")).set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFoundArtBez());
		
		
		//2013-05-14: die gefundenen, kundenspezifischen sorten hervorheben
		DB_SEARCH_ArtikelBez oSearchArtBez = (DB_SEARCH_ArtikelBez)this.get("ID_ARTIKEL_BEZ");
		oSearchArtBez.set_FormatterForButtons(new ownFormatter());
		//----
		
		
		/*
		 * abgeschaltete felder
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_READ_ONLY_Fields(this);
		
		//((MyE2_DB_TextField)this.get("GESAMTPREIS")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ANR1")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ANR2")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VKOPF_STD")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VPOS_STD")).EXT().set_bDisabledFromBasic(true);

		
		//2012-10-16: feste einstellung: felder EINHEIT,EINHEIT_PREIS,MENGENDIVISOR sind INAKTIV
		this.get__DBComp("EINHEITKURZ").EXT().set_bDisabledFromBasic(true);
		this.get__DBComp("EINHEIT_PREIS_KURZ").EXT().set_bDisabledFromBasic(true);
		this.get__DBComp("MENGENDIVISOR").EXT().set_bDisabledFromBasic(true);

		
		this.set_oMAPSettingAgent(new BSA_P_MASK_MAP_SETTING_AGENT());
		
		this.add_oMAPValidator(new BS_P_MapValidatorBeforeSave(false));
		this.add_oMAPValidator(new BSA_P_MASK_MapValidator_No_Doubles(oSetting));
		this.add_oMAPValidator(new BSA_P_MASK_MapValidator_Dienstleitung_positiv_negativ(oSetting));
		
	}

	
	
	private class ownFormatter extends XX_FormaterForFoundButtons {

		private Vector<String>  vID_ARTBEZ = null;
		
		@Override
		public void DO_Format(XX_Button4SearchResultList oButton) 	throws myException {
			//beim ersten durchlauf die zu markierenden ids sammeln
			if (this.vID_ARTBEZ==null) {
				//zuerst die ID_kopf besorgen
				this.vID_ARTBEZ = new Vector<String>();
				BSA_P_MASK__ComponentMAP oThis = BSA_P_MASK__ComponentMAP.this;
				String cID_VKOPF_STD = oThis.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.VPOS_STD$ID_VKOPF_STD);
				if (!S.isEmpty(cID_VKOPF_STD)) {
					RECORD_VKOPF_STD 			recKOPF = new RECORD_VKOPF_STD(cID_VKOPF_STD);
					RECORD_ADRESSE   			recAdresse = recKOPF.get_UP_RECORD_ADRESSE_id_adresse();
					
					RECLIST_ARTIKELBEZ_LIEF 	rlArtBezLief = recAdresse.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();
					for (RECORD_ARTIKELBEZ_LIEF recArtBezLief: rlArtBezLief.values()) {
						if ((recArtBezLief.get_ARTBEZ_TYP_cUF_NN("").equals("EK") && oThis.bEK) || 
							(recArtBezLief.get_ARTBEZ_TYP_cUF_NN("").equals("VK") && (!oThis.bEK))) {
							this.vID_ARTBEZ.add(recArtBezLief.get_ID_ARTIKEL_BEZ_cUF_NN(""));
						}
					}
				} 
			}
			
			/*
			 * jetzt nachschauen, welche in der artbez-liste vorhanden sind
			 */
			if (this.vID_ARTBEZ.contains(S.NN(oButton.EXT().get_C_MERKMAL()))) {
				oButton.setStyle(new ownStyle());
			}
			
			
		}

		@Override
		public void RESET() throws myException {
			this.vID_ARTBEZ = null;
		}

		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException {
		}
		
	}
	
	
	private class ownStyle extends MutableStyle {

		public ownStyle() {
			super();
			this.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
			this.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
			this.setProperty( AbstractButton.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
			this.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE); 
			this.setProperty( AbstractButton.PROPERTY_FOREGROUND, Color.BLACK);
			this.setProperty( AbstractButton.PROPERTY_INSETS, E2_INSETS.I_2_2_2_2); 
			this.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontBold());
			this.setProperty( AbstractButton.PROPERTY_LINE_WRAP,new Boolean(true));
			this.setProperty( AbstractButton.PROPERTY_BACKGROUND,new E2_ColorLLLight());
		}
		
	}
	
	
	
	/*
	 * mask-setting-agent fuer das laden der adress-werte in die maske
	 */
	private class ownMaskActionAfterFoundArtBez extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cID_Artikel_Bez, MyE2_DB_MaskSearchField oSearch_ID_Artikel_bez, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException 
		{
			// BSAA_ComponentMAP_Mask oThis = BSAA_ComponentMAP_Mask.this;
			if (bAfterAction)
			{
				
				E2_ComponentMAP 			oMap = 				oSearch_ID_Artikel_bez.EXT().get_oComponentMAP();
				
				HashMap<String, MyE2IF__Component> oHMReal = oMap.get_REAL_ComponentHashMap();
					
					
				//NEU_09  ---  falls eine spezielle ARTIKELBEZ_LIEF vorhanden ist, dann die dortigen felder laden 
				BSA_P_MASK_SQLFieldMAP oSQLFieldMAP = (BSA_P_MASK_SQLFieldMAP)BSA_P_MASK__ComponentMAP.this.get_oSQLFieldMAP();
				String cID_VKOPF_STD = ((SQLFieldForRestrictTableRange)oSQLFieldMAP.get_("ID_VKOPF_STD")).get_cRestictionValue_IN_DB_FORMAT();

				RECORD_ARTIKEL_BEZ recArtBez = new RECORD_ARTIKEL_BEZ(cID_Artikel_Bez);
				RECORD_ARTIKEL     recArtikel = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel();
				RECORD_VKOPF_STD  recKopfAngebot = new RECORD_VKOPF_STD(cID_VKOPF_STD);
				RECORD_ADRESSE    recAdresse = recKopfAngebot.get_UP_RECORD_ADRESSE_id_adresse();
				
				
				String cEK_VK = recKopfAngebot.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_ABNAHMEANGEBOT)?"EK":"VK";
				
				//jetzt nachsehen, ob es eine ARTIKEL_BEZ_LIEF - eintragung dazu gibt
				RECLIST_ARTIKELBEZ_LIEF  rlArtbezLief = new RECLIST_ARTIKELBEZ_LIEF(
						"SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+cID_Artikel_Bez+
																		" AND ID_ADRESSE="+recAdresse.get_ID_ADRESSE_cUF()+
																		" AND ARTBEZ_TYP="+bibALL.MakeSql(cEK_VK));
				
				//falls einer oder mehrere gefunden, dann den ersten
				RECORD_ARTIKELBEZ_LIEF recArtbez_lief = null;
				if (rlArtbezLief.get_vKeyValues().size()>0) 	recArtbez_lief = rlArtbezLief.get(0);
					
					
				// wenn  nur ein gueltiger MWST-Satz fuer diese Art-Bez existiert, dann 
				// wird dieser 
				My_MWSTSaetze oBSMW = new My_MWSTSaetze(null,cID_Artikel_Bez);
				((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue("");
				if (oBSMW.get_vMWSTArtBez().size()==1)
				{
					My_MWST oBSMWST = (My_MWST)oBSMW.get_vMWSTArtBez().get(0);
					((MyE2IF__DB_Component)oHMReal.get("STEUERSATZ")).set_cActualMaskValue(oBSMWST.get_cMWST_Formated());
				}
				
				((MyE2IF__DB_Component)oHMReal.get("ANR1")).set_cActualMaskValue(recArtikel.get_ANR1_cUF_NN(""));
				((MyE2IF__DB_Component)oHMReal.get("ANR2")).set_cActualMaskValue(recArtBez.get_ANR2_cUF_NN(""));

				((MyE2IF__DB_Component)oHMReal.get("ARTBEZ1")).set_cActualMaskValue(recArtBez.get_ARTBEZ1_cUF_NN(""));
				
				if (recArtbez_lief !=null)
				{
	                String cArtbez2 = new RECORD_ARTBEZ_LIEF_extend(recArtbez_lief).get_ARTBEZ_2_Incl_Specials();
					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2")).set_cActualMaskValue(cArtbez2);
				}
				else
				{
					((MyE2IF__DB_Component)oHMReal.get("ARTBEZ2")).set_cActualMaskValue(recArtBez.get_ARTBEZ2_cUF_NN(""));
				}
				
				//2012-05-16: die zahlungsbedinung wird nur noch aus der id_zahlungsbedinung, nicht mehr aus dem textfeld gelesen
				//alt// if (recArtbez_lief!=null && S.isFull(recArtbez_lief.get_ZAHLUNGSBEDINGUNGEN_cUF_NN("")))
				//alt// 	((MyE2IF__DB_Component)oHMReal.get("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recArtbez_lief.get_ZAHLUNGSBEDINGUNGEN_cUF_NN(""));
				
				if (recArtbez_lief!=null)
				{
					if (recArtbez_lief.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null)
					{
						//den drop-down-wert setzen 
						((MyE2IF__DB_Component)oHMReal.get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recArtbez_lief.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(""));
						//und die werte nachladen
						BS_Sel_Zahlungsbedingungen.LeseID_Zahlungsbedingung_und_setze_korrellierendeFelder(oMap);
					}
				}
				// -----
				
				
				if (recArtbez_lief!=null && S.isFull(recArtbez_lief.get_LIEFERBEDINGUNGEN_cUF_NN("")))
					((MyE2IF__DB_Component)oHMReal.get("LIEFERBEDINGUNGEN")).set_cActualMaskValue(recArtbez_lief.get_LIEFERBEDINGUNGEN_cUF_NN(""));

				
				((MyE2IF__DB_Component)oHMReal.get("ID_ARTIKEL")).set_cActualMaskValue(recArtBez.get_ID_ARTIKEL_cUF());
				
				((MyE2IF__DB_Component)oHMReal.get("EINHEITKURZ")).set_cActualMaskValue(recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF());
				((MyE2IF__DB_Component)oHMReal.get("EINHEIT_PREIS_KURZ")).set_cActualMaskValue(recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis()!=null?
																								recArtikel.get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cUF_NN(""):
																								recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF());
				((MyE2IF__DB_Component)oHMReal.get("MENGENDIVISOR")).set_cActualMaskValue(recArtikel.get_MENGENDIVISOR_cUF_NN(""));
					
			}
		}
		
	}


	public BSA_PA_MASK_ComponentMAP get_oComponentMAP_ZusatzfelderAngebot()
	{
		return oComponentMAP_ZusatzfelderAngebot;
	}

		
	
	
}
