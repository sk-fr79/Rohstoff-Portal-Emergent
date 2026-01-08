package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_Auswahl_Y_N;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_Auswahl_Y_N_EGAL;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_VERANTWORTUNG;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_MapSetAndValid_SortenTypKombi;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_MapSetting_Steuerinfos;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_SETTING_AKTIV;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_SETTING_EIGENE_STATION;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_SETTING_PseudoFieldSchrott;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_SETTING_RESET_DUBETTE;
import rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS.__TR_SETTING_TYP_USERINFO;


public class TR__MASK_ComponentMAP extends E2_ComponentMAP 
{

	public TR__MASK_ComponentMAP() throws myException
	{
		super(new TR__MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		


		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_(_DB.HANDELSDEF$ID_HANDELSDEF)), 								new MyE2_String("ID"));
//		this.add_Component(new TAX__DD_ART_GESCHAEFT(oFM.get_(_DB.HANDELSDEF$ART_GESCHAEFT),200,false),		new MyE2_String("Art Geschäft"));
		this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR),300), 		new MyE2_String("Quelle JUR"));
		this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO),300), 		new MyE2_String("Quelle GEO"));
		this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR),300), 			new MyE2_String("Ziel JUR"));
		this.add_Component(new DB_Component_LAND_DROPDOWN(oFM.get_(_DB.HANDELSDEF$ID_LAND_ZIEL_GEO),300), 			new MyE2_String("Ziel GEO"));
		
		if (ENUM_MANDANT_DECISION.USE_GROUPED_TAX_SELECTOR.is_YES_FromSession()) {
			TR_MASK_SelectTaxViaPopup idTaxQuelle = new TR_MASK_SelectTaxViaPopup(oFM.get_(_DB.HANDELSDEF$ID_TAX_QUELLE));
			TR_MASK_SelectTaxViaPopup idTaxZiel   = new TR_MASK_SelectTaxViaPopup(oFM.get_(_DB.HANDELSDEF$ID_TAX_ZIEL));
			
			TR_MASK_SelectTaxViaPopup idTaxNegativQuelle = new TR_MASK_SelectTaxViaPopup(oFM.get_(_DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE));
			TR_MASK_SelectTaxViaPopup idTaxNegativZiel   = new TR_MASK_SelectTaxViaPopup(oFM.get_(_DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL));
			
			this.add_Component(idTaxQuelle, 			new MyE2_String("Steuer Quelle"));
			this.add_Component(idTaxZiel, 				new MyE2_String("Steuer Ziel"));
			
			this.add_Component(idTaxNegativQuelle, 		new MyE2_String("Steuer Quelle (bei negativen Preisen)"));
			this.add_Component(idTaxNegativZiel, 		new MyE2_String("Steuer Ziel (bei negativen Preisen)"));
			
			
			idTaxQuelle._setOwnHshKeyAndComponentMapToAllButtonEndpointIds();
			idTaxZiel._setOwnHshKeyAndComponentMapToAllButtonEndpointIds();
			
			idTaxNegativQuelle._setOwnHshKeyAndComponentMapToAllButtonEndpointIds();
			idTaxNegativZiel._setOwnHshKeyAndComponentMapToAllButtonEndpointIds();
			
		} else {
			this.add_Component(new TR__MASK_CompSteuerDropDown(oFM.get_(_DB.HANDELSDEF$ID_TAX_QUELLE),300,false), 		new MyE2_String("Steuer Quelle"));
			this.add_Component(new TR__MASK_CompSteuerDropDown(oFM.get_(_DB.HANDELSDEF$ID_TAX_ZIEL),300,false), 		new MyE2_String("Steuer Ziel"));
			
			//2013-09-30: steuersaetze (optional) fuer negative einzelpreise
			this.add_Component(new TR__MASK_CompSteuerDropDown(oFM.get_(_DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE),300,false),new MyE2_String("Steuer Quelle (bei negativen Preisen)"));
			this.add_Component(new TR__MASK_CompSteuerDropDown(oFM.get_(_DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL),300,false), new MyE2_String("Steuer Ziel (bei negativen Preisen)"));
		}
		
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$INTRASTAT_MELD_IN)), 						new MyE2_String("Intrastat Einfuhr"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$INTRASTAT_MELD_OUT)), 						new MyE2_String("Intrastat Ausfuhr"));
			
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$QUELLE_IST_MANDANT)), 						new MyE2_String("Quelle ist Mandant"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$ZIEL_IST_MANDANT)), 						new MyE2_String("Ziel ist Mandant"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$TRANSIT_EK)), 								new MyE2_String("Transit melden (Einkauf)"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$TRANSIT_VK)), 								new MyE2_String("Transit melden (Verkauf)"));

		
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE),300,false),	new MyE2_String("Quelle UST-Teiln."));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL),300,false), 	new MyE2_String("Ziel UST-Teiln."));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_RC_QUELLE),300,false), 		new MyE2_String("Quelle: Sorte ist Reverse-Charge"));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_RC_ZIEL),300,false), 			new MyE2_String("Ziel: Sorte ist Reverse-Charge"));
		
		
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE),60,false), 	new MyE2_String("Quelle: Sorte ist ein Produkt"));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL),60,false), 	new MyE2_String("Ziel: Sorte ist ein Produkt"));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE),60,false),	new MyE2_String("Quelle: Sorte ist eine Dienstleistung"));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL),60,false),	new MyE2_String("Ziel: Sorte ist eine Dienstleistung"));
		//2014-02-24: EOW
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_EOW_QUELLE),60,false),		new MyE2_String("Quelle: Sorte ist klassifiziert als End-of-Waste"));
		this.add_Component(new TAX__DD_Auswahl_Y_N_EGAL(oFM.get_(_DB.HANDELSDEF$SORTE_EOW_ZIEL),60,false),			new MyE2_String("Ziel: Sorte ist klassifiziert als End-of-Waste"));

		//2018-08-27: zusatz-schalter fuer schrott-sorte, ist immer dann Y, wenn die drei produkt,eow, dienstl. = N
		this.add_Component(TR__CONST.KEY_MASK_SCHROTTSELEKTOR_EK, new TAX__DD_Auswahl_Y_N(60), 					S.ms("Schrott-Sorte (EK)"));
		this.add_Component(TR__CONST.KEY_MASK_SCHROTTSELEKTOR_VK, new TAX__DD_Auswahl_Y_N(60), 					S.ms("Schrott-Sorte (VK)"));
		
		
		this.add_Component(new TAX__DD_VERANTWORTUNG(oFM.get_(_DB.HANDELSDEF$TP_VERANTWORTUNG),660,false,true), 	new MyE2_String("TPA-Verantwortung"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.HANDELSDEF$MELDUNG_FUER_USER),660,5), 					new MyE2_String("Benutzerinformation (BI)"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_(_DB.HANDELSDEF$TYP_MELDUNG),							TAX_CONST.MELDUNG_DD_ARRAY, true, new Extent(300)), 	new MyE2_String("BI-Typ"));

		
		//2013-12-18: neues feld fuer die erlaubnis, dubletten zu erzeugen
		this.add_Component(new TR__MASK_CompDublette(oFM.get_(_DB.HANDELSDEF$VERSIONSZAEHLER)), 					new MyE2_String("Erlaube mehrfach die gleiche Ausgangsbasis"));
		
		
		//2013-10-01: neues feld: aktiv mit validierung
		MyE2_DB_CheckBox  o_CB_Aktiv = new MyE2_DB_CheckBox(oFM.get_(_DB.HANDELSDEF$AKTIV),new MyE2_String("Aktiv"),new MyE2_String("Ist eine Handelsdefinition passiv, dann wird diese bei der Steuerfindung ignoriert!"));
		o_CB_Aktiv.add_GlobalAUTHValidator_AUTO(TR__CONST.VALID_KEY_ERLAUBE_AKTIV_PASSIV_STEUER_IN_TR_MASKE);
		o_CB_Aktiv.add_oActionAgent(new XX_ActionAgent()    //actionagent, damit das validieren klappt
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
			}
		});
		this.add_Component(o_CB_Aktiv, new MyE2_String("Aktiv"));

		
		
		//2014-06-11: fuer die markierung der eigenen lagerseiten die hilfskomponenten auch in die ComponentMAP
		this.add_Component(TR__CONST.KEY_MASK_TITEL_EK, new MyE2_Label(new MyE2_String("Gutschrift-/Ladeseite"),new E2_FontBold()), new MyE2_String("Titel EK") );
		this.add_Component(TR__CONST.KEY_MASK_TITEL_VK, new MyE2_Label(new MyE2_String("Rechnung-/Abladeseite"),new E2_FontBold()), new MyE2_String("Titel VK") );
		
		this.add_Component(TR__CONST.KEY_MASK_SORTENTYPBLOCK_EK, new E2_Subgrid_4_Mask(), new MyE2_String("Titel EK") );
		this.add_Component(TR__CONST.KEY_MASK_SORTENTYPBLOCK_VK, new E2_Subgrid_4_Mask(), new MyE2_String("Titel VK") );
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new TR__MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new TR__MASK_FORMATING_Agent());
		
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$TYP_MELDUNG, 				new __TR_SETTING_TYP_USERINFO());
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$QUELLE_IST_MANDANT, 		new __TR_SETTING_EIGENE_STATION("EK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ZIEL_IST_MANDANT, 			new __TR_SETTING_EIGENE_STATION("VK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR, 		new __TR_SETTING_EIGENE_STATION("EK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR, 			new __TR_SETTING_EIGENE_STATION("VK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_TAX_QUELLE, 				new __TR_SETTING_EIGENE_STATION("EK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE, 		new __TR_SETTING_EIGENE_STATION("EK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_TAX_ZIEL, 				new __TR_SETTING_EIGENE_STATION("VK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL, 		new __TR_SETTING_EIGENE_STATION("VK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE,		new __TR_SETTING_EIGENE_STATION("EK",this));
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL, 		new __TR_SETTING_EIGENE_STATION("VK",this));

		
		__TR_SETTING_RESET_DUBETTE  oDubletteReset = new __TR_SETTING_RESET_DUBETTE();
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_LAND_ZIEL_GEO, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_RC_QUELLE, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_RC_ZIEL, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL, oDubletteReset);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$TP_VERANTWORTUNG, oDubletteReset);
		
		
		__TR_MapSetAndValid_SortenTypKombi  oUeberwacheSortenKombiEK = new __TR_MapSetAndValid_SortenTypKombi(true);
		__TR_MapSetAndValid_SortenTypKombi  oUeberwacheSortenKombiVK = new __TR_MapSetAndValid_SortenTypKombi(false);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE, oUeberwacheSortenKombiEK);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_EOW_QUELLE, oUeberwacheSortenKombiEK);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE, oUeberwacheSortenKombiEK);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL, oUeberwacheSortenKombiVK);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_EOW_ZIEL, oUeberwacheSortenKombiVK);
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL, oUeberwacheSortenKombiVK);


		//2015-09-18: neuer setterValidator um die gewaehlten steuersaetze naeher anzuzeigen
		__TR_MapSetting_Steuerinfos   steuerinfoSetter = new __TR_MapSetting_Steuerinfos();
		this.register_Interactiv_settings_validation(HANDELSDEF.id_tax_quelle.fn(), steuerinfoSetter);
		this.register_Interactiv_settings_validation(HANDELSDEF.id_tax_ziel.fn(), steuerinfoSetter);
		this.register_Interactiv_settings_validation(HANDELSDEF.id_tax_negativ_quelle.fn(), steuerinfoSetter);
		this.register_Interactiv_settings_validation(HANDELSDEF.id_tax_negativ_ziel.fn(), steuerinfoSetter);
		
		//2016-04-06: maskenhervorhebung bei aktiven saetzen
		this.register_Interactiv_settings_validation(_DB.HANDELSDEF$AKTIV, 				new __TR_SETTING_AKTIV());

		
		//2018-08-27: registrierung der settings fuer das pseudo-dropdown schrottEk und schrottVk
		__TR_SETTING_PseudoFieldSchrott settingPseudo = new __TR_SETTING_PseudoFieldSchrott();
		this.register_Interactiv_settings_validation(HANDELSDEF.sorte_produkt_quelle.fn(), 		settingPseudo);
		this.register_Interactiv_settings_validation(HANDELSDEF.sorte_produkt_ziel.fn(), 		settingPseudo);
		this.register_Interactiv_settings_validation(HANDELSDEF.sorte_eow_quelle.fn(), 			settingPseudo);
		this.register_Interactiv_settings_validation(HANDELSDEF.sorte_eow_ziel.fn(), 			settingPseudo);
		this.register_Interactiv_settings_validation(HANDELSDEF.sorte_dienstleist_quelle.fn(), 	settingPseudo);
		this.register_Interactiv_settings_validation(HANDELSDEF.sorte_dienstleist_ziel.fn(), 	settingPseudo);
		
		this.register_Interactiv_settings_validation(TR__CONST.KEY_MASK_SCHROTTSELEKTOR_EK, 	settingPseudo);
		this.register_Interactiv_settings_validation(TR__CONST.KEY_MASK_SCHROTTSELEKTOR_VK, 	settingPseudo);
	}
	

	
	
}
