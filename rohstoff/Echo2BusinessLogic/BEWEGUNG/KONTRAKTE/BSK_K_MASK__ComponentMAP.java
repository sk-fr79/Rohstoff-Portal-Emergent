package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class BSK_K_MASK__ComponentMAP extends E2_ComponentMAP {

	
	private BS__SETTING oBS_Setting = null;
	
	public BSK_K_MASK__ComponentMAP(BSK_K_MASK__ModulContainer oModulContainerMask) throws myException 
	{
		super(new BSK_K_MASK_SQLFieldMAP(oModulContainerMask.get_SETTING()));
		
		this.oBS_Setting = oModulContainerMask.get_SETTING();
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		// hilfs-Hashmaps mit Eingabe-Feld-Namen
		HashMap<String,String> hmInputfields = new HashMap<String,String>();
		hmInputfields.put("VORNAME",				"Vorname");
		hmInputfields.put("NAME1",				"Name 1");
		hmInputfields.put("NAME2",				"Name 2");
		hmInputfields.put("NAME3",				"Name 3");
		hmInputfields.put("STRASSE",			"Strasse");
		hmInputfields.put("HAUSNUMMER",			"Hausnummer");
		hmInputfields.put("PLZ",				"PLZ");
		hmInputfields.put("ORT",				"Ort");
		hmInputfields.put("ORTZUSATZ",			"Ortszusatz");
		hmInputfields.put("BEMERKUNGEN_INTERN",	"Interne Bemerkungen");
		hmInputfields.put("PLZ_POB",			"Fach-PLZ");
		hmInputfields.put("POB",				"Postfach");
		hmInputfields.put("IS_POB_ACTIVE",		"Postfach aktiv");
		hmInputfields.put("L_VORNAME",			"L-Vorname");
		hmInputfields.put("L_NAME1",			"L-Name 1");
		hmInputfields.put("L_NAME2",			"L-Name 2");
		hmInputfields.put("L_NAME3",			"L-Name 3");
		hmInputfields.put("L_STRASSE",			"L-Strasse");
		hmInputfields.put("L_HAUSNUMMER",		"L-Hausnummer");
		hmInputfields.put("L_PLZ",				"L-PLZ");
		hmInputfields.put("L_ORT",				"L-Ort");
		hmInputfields.put("L_ORTZUSATZ",		"L-Ortszusatz");
		hmInputfields.put("ERSTELLUNGSDATUM",	"Erstellungsdatum");
		hmInputfields.put("DRUCKDATUM",			"Druckdatum");
		hmInputfields.put("NAME_ANSPRECHPARTNER","Name Ansprechpartner");
		hmInputfields.put("BEZUG",				"Bezug");
		hmInputfields.put("OEFFNUNGSZEITEN",	"Öffnungszeiten");
		hmInputfields.put("L_LAENDERCODE",		"L-Ländercode");
		hmInputfields.put("TELEFON_AUF_FORMULAR","Telefon auf Formular");
		hmInputfields.put("TELEFAX_AUF_FORMULAR","Telefax auf Formular");
		hmInputfields.put("EMAIL_AUF_FORMULAR",	"Email auf Formular");


		
//		 hilfs-Hashmaps mit Anzeige-Feld-Namen
		HashMap<String,String> hmViewfields = new HashMap<String,String>();
		hmViewfields.put("ID_VKOPF_KON",		"ID-Kopf");
		hmViewfields.put("VORGANG_TYP",			"Vorgangstyp");
		hmViewfields.put("VORGANGSGRUPPE",		"Vorgangsgruppe");
		hmViewfields.put("LETZTER_DRUCK",		"Datum letzter Druck");
		hmViewfields.put("ZAEHLER_ENTSPERRUNG",	"Anzahl Ensperrungen");
		hmViewfields.put("DRUCKZAEHLER",		"Druckzähler");
		hmViewfields.put("BUCHUNGSNUMMER",		"Buchungsnummer");
		hmViewfields.put("KDNR",				"KD-Nr");


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
		
		
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_VON")),					new MyE2_String("gültig von"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_BIS")),					new MyE2_String("gültig bis"));

		
//		// zwei buttons um in der maske die zeitraeume zu setzen
//		this.add_Component(BSK__CONST.HASH_KEY_KOPF_MASK_SET_ACTUAL_MONTH,
//				new E2_Button_SetDateRange(true,(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_VON"),(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_BIS")),new MyE2_String("Aktuellen Monat besetzen"));
//		this.add_Component(BSK__CONST.HASH_KEY_KOPF_MASK_SET_NEXT_MONTH,
//				new E2_Button_SetDateRange(false,(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_VON"),(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_BIS")),new MyE2_String("Nächsten Monat besetzen"));


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
		this.add_Component(BSK__CONST.HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS,new BSK_K_MASK_BT_EDIT_ADRESS(oModulContainerMask),new MyE2_String("Adressbearbeitung"));
		this.add_Component(BSK__CONST.HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS,new BSK_K_MASK_BT_NEW_ADRESS(oModulContainerMask),new MyE2_String("Adressbearbeitung"));
		
		
		/*
		 * druckprotokoll-anzeige
		 */
		this.add_Component(BSK__CONST.HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL,
				new BSK_K_MASK_COMP_Daughter_PrintProtokoll(oFM,this,this.oBS_Setting),new MyE2_String("Druckprotokoll"));
		
		
		
		/*
		 * positions-tochter (full-daughter)
		 */
		this.add_Component(			BSK__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS,
									new BSK_K_MASK_COMP_FullDaughterPositions(oFM.get_oSQLFieldPKMainTable(),oModulContainerMask),new MyE2_String("Druckprotokoll"));
	
		
		//2020-02-19: text-liste fuer druck
		this.add_Component(new BSK_K_MASK_CompDaugherTextListe(this.oBS_Setting));
		
		
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
		
		this.set_oMAPSettingAgent(new BSK_K_MASK_MAP_SETTING_AGENT());
		
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().removeAllElements();
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new ownAddonStatementBuilder());
		
		
		//2012-08-07: Suche nur in adressen ohne sperrvermerke (ausser barkunden)
		DB_SEARCH_Adress  oSearchAdress = (DB_SEARCH_Adress)this.get__DBComp("ID_ADRESSE");
		oSearchAdress.get_oSeachBlock().get_vZusatzWhereBedingungen().add(
				this.oBS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT)
				?
				"(NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N')"
				:
				"(NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,'N')='N')");
		//2012-08-07

		
	}


	
	private class ownAddonStatementBuilder implements builder_AddOnSQL_STATEMENTS
	{

		@Override
		public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,MyE2_MessageVector omv) throws myException
		{
			Vector<String> vRueck = new Vector<String>();
			
			BSK_K_MASK__ComponentMAP oThis = BSK_K_MASK__ComponentMAP.this;
			
//			String cVorsatz = oThis.oBS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT)?
//										bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_EKK_cF_NN(""):
//										bibALL.get_RECORD_MANDANT().get_BUCHUNGSPREFIX_VKK_cF_NN("");
									
			
			String cID_VKOPF_KON = ""+inputMAP.get_LActualValue("ID_VKOPF_KON", true, true, new Long(0)).longValue();
			if (cID_VKOPF_KON.trim().equals("0"))
			{
				throw new myException("Error finding the current ID_VKOPF_KON - Value !");
			}
				
			//2010-12-29: fehler bei buchungsnummer korrigiert
			String cUpdate = "UPDATE JT_VKOPF_KON SET "+oThis.oBS_Setting.get_SQL_UPDATE_Block_Fuer_Buchungsnummer()+" WHERE BUCHUNGSNUMMER IS NULL AND ID_VKOPF_KON="+cID_VKOPF_KON;
			
//			vRueck.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_KON SET BUCHUNGSNUMMER='"+cVorsatz+"'||TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+oThis.oBS_Setting.get_cBELEGTYP()+".NEXTVAL) " +
//											" WHERE ID_VKOPF_KON="+cID_VKOPF_KON);
			vRueck.add(cUpdate);
			
			return vRueck;
		}

		@Override
		public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,MyE2_MessageVector omv) throws myException
		{
			return new Vector<String>();
		}
		
	}
	
	
	
}
