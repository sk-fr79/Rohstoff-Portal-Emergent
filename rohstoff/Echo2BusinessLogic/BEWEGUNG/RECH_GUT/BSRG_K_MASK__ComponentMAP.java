package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class BSRG_K_MASK__ComponentMAP extends E2_ComponentMAP {

	
	private BS__SETTING oBS_Setting = null;
	
	public BSRG_K_MASK__ComponentMAP(BSRG_K_MASK__ModulContainer oModulContainerMask) throws myException 
	{
		super(new BSRG_K_MASK_SQLFieldMAP(oModulContainerMask.get_SETTING()));
		
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
		hmInputfields.put("BELEG_IST_INTERN","Beleg ist intern");
		hmInputfields.put("UMSATZSTEUERLKZ","Umsatzsteuer-LKZ");
		hmInputfields.put("UMSATZSTEUERID","Umsatzsteuer-ID");
		hmInputfields.put("UMSATZSTEUERLKZ","Umsatzsteuer-LKZ");
		hmInputfields.put("UMSATZSTEUERID","Umsatzsteuer-ID");
		hmInputfields.put("UMSATZSTEUERLKZ_MANDANT","Umsatzsteuer-LKZ (Mandant)");
		hmInputfields.put("UMSATZSTEUERID_MANDANT","Umsatzsteuer-ID (Mandant)");
		
		
//		 hilfs-Hashmaps mit Anzeige-Feld-Namen
		HashMap<String,String> hmViewfields = new HashMap<String,String>();
		hmViewfields.put("ID_VKOPF_RG","ID-Kopf");
		hmViewfields.put("VORGANG_TYP","Vorgangstyp");
		hmViewfields.put("VORGANGSGRUPPE","Vorgangsgruppe");
		hmViewfields.put("LETZTER_DRUCK","Datum letzter Druck");
		hmViewfields.put("ZAEHLER_ENTSPERRUNG","Anzahl Ensperrungen");
		hmViewfields.put("DRUCKZAEHLER","Druckzähler");
		hmViewfields.put("BUCHUNGSNUMMER","Buchungsnummer");
		hmViewfields.put("ID_VKOPF_RG_STORNO_VORGAENGER","ID Storno-Original");
		hmViewfields.put("ID_VKOPF_RG_STORNO_NACHFOLGER","ID Storno-Gegenbuchungssatz");
		hmViewfields.put("KDNR","KD-Nr");


		// aus den hashmaps werden nun die noetigen listen
		Vector<String> vFields1 = bibALL.get_vBuildKeyVectorFromHashmap(hmInputfields);
		Vector<String> vNames1 = bibALL.get_vBuildValueVectorFromHashmap(hmInputfields);
		
		// aus den hashmaps werden nun die noetigen listen
		Vector<String> vFields2 = bibALL.get_vBuildKeyVectorFromHashmap(hmViewfields);
		Vector<String> vNames2 = bibALL.get_vBuildValueVectorFromHashmap(hmViewfields);
		
		
		MaskComponentsFAB.addStandardComponentsToMAP("|"+bibALL.Concatenate(vFields1,"|", null)+"|",
				 									"|"+bibALL.Concatenate(vNames1,"|", null)+"|",
				 									oFM,
				 									false,
				 									false,
				 									this,
				 									400);
		
		MaskComponentsFAB.addStandardComponentsToMAP("|"+bibALL.Concatenate(vFields2,"|", null)+"|",
													"|"+bibALL.Concatenate(vNames2,"|", null)+"|",
													oFM,
													false,
													false,
													this,
													400);

		
		this.add_Component(MaskComponentsFAB.createStandardComponent(oFM.get_("ABGESCHLOSSEN"),false,true,400, false),new MyE2_String("Abgeschlossen"));
		
		//HttpSession	oSES = bibE2.get_CurrSession();
		
		
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

		
		//die sonderlabels waehrungsymbole fuer den druck werden nur angezeigt
		this.add_Component(new MyE2_DB_Label(oFM.get_("WAEHRUNG_BASIS")), new MyE2_String("Basiswaehrung"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("WAEHRUNG_FREMD")), new MyE2_String("Fremdwaehrung"));
		
		
		
		/*
		 * jetzt den ladebutton fuer neueingabe/aendern der adresse laden
		 */
		this.add_Component(BSRG__CONST.HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS,new BSRG_K_MASK_BT_EDIT_ADRESS(oModulContainerMask),new MyE2_String("Adressbearbeitung"));
		this.add_Component(BSRG__CONST.HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS,new BSRG_K_MASK_BT_NEW_ADRESS(oModulContainerMask),new MyE2_String("Adressbearbeitung"));
		
		
		/*
		 * druckprotokoll-anzeige
		 */
		this.add_Component(BSRG__CONST.HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL,
				new BSRG_K_MASK_COMP_Daughter_PrintProtokoll(oFM,this,this.oBS_Setting),new MyE2_String("Druckprotokoll"));
		
		
		
		/*
		 * positions-tochter (full-daughter)
		 */
		this.add_Component(BSRG__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS,new BSRG_K_MASK_COMP_KP_FullDaughterPositions(
									oFM.get_oSQLFieldPKMainTable(),
									oModulContainerMask),new MyE2_String("Druckprotokoll"));
	
		
		//aenderung: 2010-11-11: neue Felder fuer ust-id
		this.add_Component(BSRG__CONST.HASH_KEY_SELECT_USTID,			new BSRG_K_MASK_ButtonSelect_USTID(false),new MyE2_String("UST-ID-Auswahl"));
		this.add_Component(BSRG__CONST.HASH_KEY_SELECT_USTID_MANDANT,	new BSRG_K_MASK_ButtonSelect_USTID(true),new MyE2_String("UST-ID-Auswahl"));


		this.add_Component(new MyE2_DB_TextArea_WithSelektorEASY(oFM.get_("TEXT_AUSLANDSVERTRETUNG"), "RECH_GUT_TEXTE_AUSLANDVERTRETUNGEN"), new MyE2_String("Texthinweis Auslandvertreter"));
		
		hmInputfields.put("TEXT_AUSLANDSVERTRETUNG","Texthinweis Auslandvertreter");

		
		//2012-01-18: schalter: wird nicht gemahnt
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("KEINE_MAHNUNGEN"),new MyE2_String("Wird nicht gemahnt"),new MyE2_String("Beleg wird von Mahnungen ausgeschlossen !")),new MyE2_String("Wird nicht gemahnt"));
		((MyE2_DB_CheckBox)this.get_Comp("KEINE_MAHNUNGEN")).add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("KEINE_MAHNUNGEN"));
		((MyE2_DB_CheckBox)this.get_Comp("KEINE_MAHNUNGEN")).add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
		});


		
		
		/*
		 * jetzt spezielle groessen-einstellungen
		 */
		((MyE2_DB_TextField) this.get__Comp("ID_VKOPF_RG")).set_iWidthPixel(100);
		((MyE2_DB_TextField) this.get__Comp("BUCHUNGSNUMMER")).set_iWidthPixel(100);
		((MyE2_DB_TextField) this.get__Comp("ID_VKOPF_RG_STORNO_VORGAENGER")).set_iWidthPixel(100);
		((MyE2_DB_TextField) this.get__Comp("ID_VKOPF_RG_STORNO_NACHFOLGER")).set_iWidthPixel(100);
		
		((MyE2_DB_TextField) this.get__Comp("ZAEHLER_ENTSPERRUNG")).set_iWidthPixel(100);
		((MyE2_DB_TextArea) this.get__Comp("BEMERKUNGEN_INTERN")).set_iWidthPixel(600);
		((MyE2_DB_TextArea) this.get__Comp("BEMERKUNGEN_INTERN")).set_iRows(10);
		
		
		((MyE2_DB_TextField) this.get__Comp("UMSATZSTEUERLKZ")).set_iWidthPixel(30);
		((MyE2_DB_TextField) this.get__Comp("UMSATZSTEUERID")).set_iWidthPixel(90);

		((MyE2_DB_TextField) this.get__Comp("UMSATZSTEUERLKZ_MANDANT")).set_iWidthPixel(30);
		((MyE2_DB_TextField) this.get__Comp("UMSATZSTEUERID_MANDANT")).set_iWidthPixel(90);

		((MyE2_DB_TextArea_WithSelektorEASY) this.get__Comp("TEXT_AUSLANDSVERTRETUNG")).get_oTextArea().set_iWidthPixel(600);
		((MyE2_DB_TextArea_WithSelektorEASY) this.get__Comp("TEXT_AUSLANDSVERTRETUNG")).get_oTextArea().set_iRows(10);
		//((MyE2_DB_TextArea) this.get__Comp("TEXT_AUSLANDSVERTRETUNG")).setFont(new E2_FontPlain(-2));
		
		/*
		 * jetzt spezielle disabled-einstellungen
		 */
		((MyE2_DB_TextField) this.get__Comp("BUCHUNGSNUMMER")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_CheckBox) this.get__Comp("ABGESCHLOSSEN")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField) this.get__Comp("ZAEHLER_ENTSPERRUNG")).EXT().set_bDisabledFromBasic(true);
			
		this.get__Comp("ID_VKOPF_RG_STORNO_NACHFOLGER").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("ID_VKOPF_RG_STORNO_VORGAENGER").EXT().set_bDisabledFromBasic(true);
		
		this.get__Comp("UMSATZSTEUERLKZ").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("UMSATZSTEUERID").EXT().set_bDisabledFromBasic(true);

		this.get__Comp("UMSATZSTEUERLKZ_MANDANT").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("UMSATZSTEUERID_MANDANT").EXT().set_bDisabledFromBasic(true);
		
		
		this.set_oMAPSettingAgent(new BSRG_K_MASK_MAP_SETTING_AGENT());
		//this.add_oMAPValidator(new ownMapValidator());

		//dafuer sorgen, dass in allen positionen die richtige zahlungsbedingung vom kopf steht
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add( new  builder_AddOnSQL_STATEMENTS()
		{
			@Override
			public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,   MyE2_MessageVector omv) throws myException
			{
				return new Vector<String>();
			}

			@Override
			public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,MyE2_MessageVector omv) throws myException
			{
				String cID_ZahlungsBed = 			inputMAP.get_InputString("ID_ZAHLUNGSBEDINGUNGEN", "");
				String cID_FixMonat =    			inputMAP.get_InputString("FIXMONAT", "");
				String cID_FixTag     =  			inputMAP.get_InputString("FIXTAG","");
				String cID_Zahltage = 				inputMAP.get_InputString("ZAHLTAGE","");
				String cSkonto = 					inputMAP.get_InputString("SKONTO_PROZENT","");
				String cZahlungsBed = 				inputMAP.get_InputString("ZAHLUNGSBEDINGUNGEN", "");
				
				
				Vector<String>  vSQL = new Vector<String>();
				
				RECORD_VKOPF_RG  recVkopf = 	new RECORD_VKOPF_RG(componentMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				RECLIST_VPOS_RG  reclistVPos = 	recVkopf.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'", null, true);
				
				for (int i=0;i<reclistVPos.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_RG recVPos = reclistVPos.get(i);
					
					recVPos.set_NEW_VALUE_ID_ZAHLUNGSBEDINGUNGEN(cID_ZahlungsBed);
					recVPos.set_NEW_VALUE_FIXMONAT(cID_FixMonat);
					recVPos.set_NEW_VALUE_FIXTAG(cID_FixTag);
					recVPos.set_NEW_VALUE_ZAHLTAGE(cID_Zahltage);
					recVPos.set_NEW_VALUE_SKONTO_PROZENT(cSkonto);
					recVPos.set_NEW_VALUE_ZAHLUNGSBEDINGUNGEN(cZahlungsBed);
					
					vSQL.add(recVPos.get_SQL_UPDATE_STATEMENT(null, true));
					
				}
				
				return vSQL;
			}
			
		});
		
		//2012-08-07: Suche nur in adressen ohne sperrvermerke (ausser barkunden)
		DB_SEARCH_Adress  oSearchAdress = (DB_SEARCH_Adress)this.get__DBComp("ID_ADRESSE");
		oSearchAdress.get_oSeachBlock().get_vZusatzWhereBedingungen().add(
				this.oBS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_GUTSCHRIFT)
				?
				"(NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N')"
				:
				"(NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,  'N')='N')");
		//2012-08-07

		
		//2013-05-14: neuer mask-setter, der dafuer sorgt, dass eine maske mit einer S2-Position nicht mehr komplett offen ist
		this.get_hmInteractiv_settings_validation().put_(_DB.VKOPF_RG$ID_ADRESSE, new BSRG_K_MASK_MAP_SET_AND_VALID_WhenContainsStorno_then_HeadClosed());
		
		
		
	}

	public BS__SETTING get_oBS_Setting()
	{
		return oBS_Setting;
	}

	
	
	
}
