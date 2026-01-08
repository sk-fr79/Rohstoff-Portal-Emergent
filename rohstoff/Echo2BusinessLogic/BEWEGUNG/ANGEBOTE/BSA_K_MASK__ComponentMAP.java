package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA_K_MASK__ComponentMAP extends E2_ComponentMAP {

	
	private BS__SETTING oBS_Setting = null;
	
	public BSA_K_MASK__ComponentMAP(BSA_K_MASK__ModulContainer oModulContainerMask) throws myException 
	{
		super(new BSA_K_MASK_SQLFieldMAP(oModulContainerMask.get_SETTING()));
		
		this.oBS_Setting = oModulContainerMask.get_SETTING();
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		// hilfs-Hashmaps mit Eingabe-Feld-Namen
		HashMap<String,String> hmInputfields = new HashMap<String,String>();
		hmInputfields.put("VORNAME","Vorname");
		hmInputfields.put("NAME1","Name 1");
		hmInputfields.put("NAME2","Name 2");
		hmInputfields.put("NAME3","Name 3");
		hmInputfields.put("STRASSE","Strasse");
		hmInputfields.put("HAUSNUMMER","Hausnummer");
		hmInputfields.put("PLZ","PLZ");
		hmInputfields.put("ORT","Ort");
		hmInputfields.put("ORTZUSATZ","Ortszusatz");
		hmInputfields.put("BEMERKUNGEN_INTERN","Interne Bemerkungen");
		hmInputfields.put("PLZ_POB","Fach-PLZ");
		hmInputfields.put("POB","Postfach");
		hmInputfields.put("IS_POB_ACTIVE","Postfach aktiv");
		hmInputfields.put("L_VORNAME","L-Vorname");
		hmInputfields.put("L_NAME1","L-Name 1");
		hmInputfields.put("L_NAME2","L-Name 2");
		hmInputfields.put("L_NAME3","L-Name 3");
		hmInputfields.put("L_STRASSE","L-Strasse");
		hmInputfields.put("L_HAUSNUMMER","L-Hausnummer");
		hmInputfields.put("L_PLZ","L-PLZ");
		hmInputfields.put("L_ORT","L-Ort");
		hmInputfields.put("L_ORTZUSATZ","L-Ortszusatz");
		hmInputfields.put("ERSTELLUNGSDATUM","Erstellungsdatum");
		hmInputfields.put("DRUCKDATUM","Druckdatum");
		hmInputfields.put("NAME_ANSPRECHPARTNER","Name Ansprechpartner");
		hmInputfields.put("BEZUG","Bezug");
		hmInputfields.put("OEFFNUNGSZEITEN","Öffnungszeiten");
		hmInputfields.put("L_LAENDERCODE","L-Ländercode");
		hmInputfields.put("TELEFON_AUF_FORMULAR","Telefon auf Formular");
		hmInputfields.put("TELEFAX_AUF_FORMULAR","Telefax auf Formular");
		hmInputfields.put("EMAIL_AUF_FORMULAR","Email auf Formular");



		
//		 hilfs-Hashmaps mit Anzeige-Feld-Namen
		HashMap<String,String> hmViewfields = new HashMap<String,String>();
		hmViewfields.put("ID_VKOPF_STD","ID-Kopf");
		hmViewfields.put("VORGANG_TYP","Vorgangstyp");
		hmViewfields.put("VORGANGSGRUPPE","Vorgangsgruppe");
		hmViewfields.put("LETZTER_DRUCK","Datum letzter Druck");
		hmViewfields.put("ZAEHLER_ENTSPERRUNG","Anzahl Ensperrungen");
		hmViewfields.put("BUCHUNGSNUMMER","Buchungsnummer");
		hmViewfields.put("KDNR","KD-Nr");


		// aus den hashmaps werden nun die noetigen listen
		Vector<String> vFields1 = bibALL.get_vBuildKeyVectorFromHashmap(hmInputfields);
		Vector<String> vNames1 = bibALL.get_vBuildValueVectorFromHashmap(hmInputfields);
		
		// aus den hashmaps werden nun die noetigen listen
		Vector<String> vFields2 = bibALL.get_vBuildKeyVectorFromHashmap(hmViewfields);
		Vector<String> vNames2 = bibALL.get_vBuildValueVectorFromHashmap(hmViewfields);
		
		
		MaskComponentsFAB.addStandardComponentsToMAP("|"+bibALL.Concatenate(vFields1,"|", null)+"|",
				 									"|"+bibALL.Concatenate(vNames1,"|", null)+"|",oFM,
				 									false,
				 									false,
				 									this,
				 									400);
		
		MaskComponentsFAB.addStandardComponentsToMAP("|"+bibALL.Concatenate(vFields2,"|", null)+"|",
													"|"+bibALL.Concatenate(vNames2,"|", null)+"|",oFM,
													false,
													false,
													this,
													400);

		
		this.add_Component(MaskComponentsFAB.createStandardComponent(oFM.get_("ABGESCHLOSSEN"),false,true,400, false),new MyE2_String("Abgeschlossen"));
		
				
		
		/*
		 * fuegt folgende feld-komponenten ein:
		 * 	 *  fuegt einige immer gleiche kopf-maskenelemente in die jeweiligen component-MAPS ein
		 *  ID_USER
		 *  ID_SPRACHE
		 *  ID_ZAHLUNGSBEDINGUNGEN
		 *  ZAHLTAGE
		 *  FIXMONAT
		 *  FIXTAG
		 *  SKONTO_PROZENT
		 *  LIEFERBEDINGUNGEN
		 *  LAENDERCODE
		 *  WAEHRUNG_FREMD
		 *  MITARBEITER
		 *  FORMULARTEXT_ANFANG
		 *  FORMULARTEXT_ENDE
		 *  ID_ADRESSE
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.add_Basic_KOPF_FieldsToComponentMAP(this, oFM, this.oBS_Setting.get_cBELEGTYP());
		
		
		/*
		 * jetzt den ladebutton fuer neueingabe/aendern der adresse laden
		 */
		this.add_Component(BSA__CONST.HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS,new BSA_K_MASK_BT_EDIT_ADRESS(oModulContainerMask),new MyE2_String("Adressbearbeitung"));
		this.add_Component(BSA__CONST.HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS,new BSA_K_MASK_BT_NEW_ADRESS(oModulContainerMask),new MyE2_String("Adressbearbeitung"));
		
		
		/*
		 * druckprotokoll-anzeige
		 */
		this.add_Component(BSA__CONST.HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL,
				new BSA_K_MASK_COMP_Daughter_PrintProtokoll(oFM,this,this.oBS_Setting),new MyE2_String("Druckprotokoll"));
		
		
		
		/*
		 * positions-tochter (full-daughter)
		 */
		this.add_Component(BSA__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS,new BSA_K_MASK_COMP_KP_FullDaughterPositions(
									oFM.get_oSQLFieldPKMainTable(),
									oModulContainerMask),new MyE2_String("Druckprotokoll"));
	

		
		/*
		 * jetzt spezielle groessen-einstellungen
		 */
		((MyE2_DB_TextField) this.get__Comp("BUCHUNGSNUMMER")).set_iWidthPixel(150);
		((MyE2_DB_TextField) this.get__Comp("ZAEHLER_ENTSPERRUNG")).set_iWidthPixel(100);
		((MyE2_DB_TextArea) this.get__Comp("BEMERKUNGEN_INTERN")).set_iWidthPixel(600);
		
		
		/*
		 * jetzt spezielle disabled-einstellungen
		 */
		((MyE2_DB_TextField) this.get__Comp("BUCHUNGSNUMMER")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_CheckBox) this.get__Comp("ABGESCHLOSSEN")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField) this.get__Comp("ZAEHLER_ENTSPERRUNG")).EXT().set_bDisabledFromBasic(true);
			

		
		this.set_oMAPSettingAgent(new BSA_K_MASK_MAP_SETTING_AGENT());
		
		
	}


	
}
