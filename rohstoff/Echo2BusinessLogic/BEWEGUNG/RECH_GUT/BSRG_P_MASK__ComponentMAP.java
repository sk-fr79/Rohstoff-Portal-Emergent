package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ServiceVector;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldValidator_InputAllowed;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_AbzugsKalkulator;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_Daughter_Abzuege;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MapValidatorBeforeSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.SET_AND_VALID.BSRG_Set_And_Valid_Steuervermerk;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_STEUERDEF;

public class BSRG_P_MASK__ComponentMAP extends BS_ComponentMAP 
{

	private boolean bUseAusFreiePositionen = false;
	
	private E2_BasicModuleContainer_MASK  oMaskContainer = null;
	 
	/*
	 * benutzung durch: Rechnung-Gutschrift-Modul, 	oBS_Setting != null
	 * freie positionen:							oBS_Setting == null 
	 */
	public BSRG_P_MASK__ComponentMAP(BS__SETTING oBS_Setting, E2_BasicModuleContainer_MASK  MaskContainer ) throws myException 
	{
		super(new BSRG_P_MASK_SQLFieldMAP(oBS_Setting,null));
		
		this.oMaskContainer = MaskContainer;
		
		if (oBS_Setting==null)
			bUseAusFreiePositionen = true;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG, new MyE2_Grid_InLIST(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()),new MyE2_String("Anzeige Zugang-Abgang"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VKOPF_RG"),true,60),	new MyE2_String("ID Kopf"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_RG"),true,60),	new MyE2_String("ID Pos"));
		this.add_Component(new BSRG_P_MASK_DB_SEARCH_Adresse(oFM.get_("ID_ADRESSE")), new MyE2_String("Lade Adresse"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE_ZUGEORD"),true,60),	new MyE2_String("ID-Fuhre"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD"),true,60),	new MyE2_String("ID-Fuhre-Ort"));
		this.add_Component(new BSRG_P_MASK_COMP_LAGERVORZEICHEN(oFM, true),	new MyE2_String("Warenein-/Ausgang"));

		this.add_Component(new BSRG_P_MASK_DB_SEARCH_ArtikelBez(oFM,	null),	new MyE2_String("ID-Art-Bez."));
		this.add_Component(new BS_SelectField_POSITIONSTYP(oFM.get_("POSITION_TYP"),oFM,false),	new MyE2_String("Pos.Typ"));
		
		
		
		
		/*
		 * 	
		 * haengt folgende felder an:
		 * "ANZAHL"                       "ARTBEZ1"
		 * "ARTBEZ2"            		 * "EINZELPREIS"
		 * "GESAMTPREIS"      			 * "EINZELPREIS_ABZUG"
		 * "EINZELPREIS_RESULT"			 * "GESAMTPREIS_ABZUG"
		 * "EINZELPREIS_FW"				 * "GESAMTPREIS_FW"
		 * "EINZELPREIS_ABZUG_FW"		 * "EINZELPREIS_RESULT_FW"
		 * "GESAMTPREIS_ABZUG_FW"		 * "WAEHRUNGSKURS"
		 * ANR1							 * ANR2
		 * ID_ARTIKEL					 * POSITIONSNUMMER
		 * MWST							 * LIEFERBEDINGUNGEN
		 * ZAHLUNGSBEDINGUNGEN			 * ID_ZAHLUNGSBEDINGUNGEN
		 * ID_WAEHRUNG_FREMD			 * ZAHLTAGE
		 * FIXMONAT						 * FIXTAG
		 * SKONTO_PROZENT
		 * STEUERSATZ
		 */ 
		BS__CompMap_FieldMAP_Gemeinsamkeiten.add_Basic_POSITION_Fields_To_ComponentMap(this, oFM, null,"RG");
		//das alte MWST-Combobox-feld wird in den gemeinsamkeiten zugefuegt
		
		
		BS_ComboBox_MWST oMWST = (BS_ComboBox_MWST)this.get__Comp(_DB.VPOS_RG$STEUERSATZ);
		oMWST.get_oTextField().set_iWidthPixel(30);
		
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("AUSFUEHRUNGSDATUM"),"",120),	new MyE2_String("Leistungdatum"));

		this.add_Component(new BS_ComboBox_EINHEIT(oFM),new MyE2_String("Mg.-EH"));
		this.add_Component(new BS_ComboBox_EINHEIT_PREIS(oFM),new MyE2_String("Pr.-EH"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGENDIVISOR"),true,120),new MyE2_String("Divisor"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ZAHLUNGSBED_CALC_DATUM"),true,120),new MyE2_String("Zahldatum"));

		// deaktivierte Anzeigen, was die abzuege betrifft
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANZAHL_ABZUG"),true,120),			new MyE2_String("Abzug Menge"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_SONDERPOSITION")),				new MyE2_String("Sonderposition"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("STRECKENGESCHAEFT")),					new MyE2_String("Streckengeschäft"));
		

		/*
		 * suche nach Kontraktpos
		 */
		this.add_Component(new BSRG_P_MASK_DB_SEARCH_Kontrakt(oFM.get_("ID_VPOS_KON_ZUGEORD")), new MyE2_String("Kontrakt"));
		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BESTELLNUMMER"),true,200,60,false), new MyE2_String("Fremd-Bestellnummer"));

		
		//2012-10-16: feste einstellung: felder EINHEIT,EINHEIT_PREIS,MENGENDIVISOR sind INAKTIV
		this.get__DBComp("EINHEITKURZ").EXT().set_bDisabledFromBasic(true);
		this.get__DBComp("EINHEIT_PREIS_KURZ").EXT().set_bDisabledFromBasic(true);
		this.get__DBComp("MENGENDIVISOR").EXT().set_bDisabledFromBasic(true);
		
		
		//festlegungen
		this.get__Comp("IST_SONDERPOSITION").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("STRECKENGESCHAEFT").EXT().set_bDisabledFromBasic(true);
		
		this.get__Comp("ID_VPOS_TPA_FUHRE_ZUGEORD").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD").EXT().set_bDisabledFromBasic(true);
		
		//feld OHNE_STEUER
		String cMaskIdentifier = E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN;
		if (oBS_Setting != null)
		{
			cMaskIdentifier =oBS_Setting.get_cMODULCONTAINER_MASK_IDENTIFIER();
		}
		MyE2_DB_CheckBox oCB_OhneSteuer = new MyE2_DB_CheckBox(oFM.get_("OHNE_STEUER"));
		oCB_OhneSteuer.add_GlobalValidator(new E2_ButtonAUTHValidator(cMaskIdentifier,"DEFINIERE_OHNE_STEUER"));
		oCB_OhneSteuer.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
		});
		this.add_Component(oCB_OhneSteuer, new MyE2_String("Beleg ohne Steuer"));
		
		
		//preis-felder formatieren
		BS__CompMap_FieldMAP_Gemeinsamkeiten.format_BasicPositionFields(this);

		//preisfelder sperren 
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_READ_ONLY_Fields(this);

		
		//steuervermerk
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("EU_STEUER_VERMERK"),400,4,true,new E2_FontPlain(-2)), new MyE2_String("Steuervermerk "));
		
		this.add_Component(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, 
				new BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK((MyE2_DB_TextArea)this.get__Comp("EU_STEUER_VERMERK"),
				this.get__DBComp("STEUERSATZ")),new MyE2_String("Selektion der Moeglichkeiten des Steuertextes"));

		
		//2014-10-27: neues id_tax-feld
		this.add_Component(new TAX__DD_STEUERDEF(oFM.get_(_DB.VPOS_RG$ID_TAX),200,false),			new MyE2_String("Steuersatz festlegen.."));

		
		
		//2011-04-14: neue Felder fuer aufgedroeselte abzugsdefinition
		String cFieldsNew = "|ANZAHL_ABZUG_LAGER" +
							"|GPREIS_ABZ_MGE" +
							"|GPREIS_ABZ_MGE_FW" +
							"|GPREIS_ABZ_AUF_NETTOMGE" +
							"|GPREIS_ABZ_AUF_NETTOMGE_FW" +
							"|GPREIS_ABZ_VORAUSZAHLUNG" +
							"|GPREIS_ABZ_VORAUSZAHLUNG_FW" +
							"|EPREIS_RESULT_NETTO_MGE" +
							"|EPREIS_RESULT_NETTO_MGE_FW" +
							"|";
		
		String cBeschNew = 	"|Realer Abzug auf Lagermenge" +
							"|Gesamtpreisabzug Anteil Mengenabzug" +
							"|Gesamtpreisabzug Anteil Mengenabzug (FW)" +
							"|Gesamtpreisabzug Anteil Abzug auf Nettomenge" +
							"|Gesamtpreisabzug Anteil Abzug auf Nettomenge (FW)" +
							"|Gesamtpreisabzug Anteil Vorauszahlung" +
							"|Gesamtpreisabzug Anteil Vorauszahlung (FW)" +
							"|Resultierender Einzelpreis auf Nettomenge" +
							"|Resultierender Einzelpreis auf Nettomenge (FW)" +
							"|";
		
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsNew,cBeschNew,this.get_oSQLFieldMAP(),false,true,this,100);

		E2_ServiceVector vServiceVector = new E2_ServiceVector();
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG_FW));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT_FW));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG_FW));
		
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG_LAGER));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE_FW));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE_FW));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG_FW));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE));
		vServiceVector.add(this.get__Comp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE_FW));
		
		
		vServiceVector.set_Font(new E2_FontPlain(-2));
		vServiceVector.set_Width(new Extent(70));
		vServiceVector.set_Height(new Extent(14));

		//abzuege erst hier, da die komponente obenliegende elemente integriert
		this.add_Component(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS,new own_AbzugsListe(oFM),new MyE2_String("Abzüge"));

		
		// hier werden jetzt die masken-labels erzeugt, die ein waehrungssymbol bekommen sollen.
		// damit sie wiedergefunden werden, muessen sie in der E2_ComponentMAP vorhanden sein
		this.add_Component(BS__CONST.HASH_WS_EPREIS_ABZUG, new MyE2_Label(new MyE2_String("Abzug EPreis #FW# "),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		this.add_Component(BS__CONST.HASH_WS_GPREIS_ABZUG, new MyE2_Label(new MyE2_String("Abzug GPreis #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		this.add_Component(BS__CONST.HASH_WS_EPREIS_RESULT, new MyE2_Label(new MyE2_String("Res. EPreis #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		
		
		

		
		
		this.set_oMAPSettingAgent(new BSRG_P_MASK_MAP_SETTING_AGENT(this.bUseAusFreiePositionen));
		this.add_oMAPValidator(new BS_P_MapValidatorBeforeSave(false));
		this.add_oMAPValidator(new BSRG_P_MASK_MapValidatorSettingsBeforeSave());
		this.add_oMAPValidator(new BSRG_P_MASK_MapValidator_Warenausgang_NurKontrakt());
		this.add_oMAPValidator(new BSRG_P_MASK_MapValidator_Check_Status_von_FELDGLEICHHEIT());
		this.add_oMAPValidator(new BSRG_P_MASK_MapValidator_PrufePosTypArtikel());
		
		//2012-08-07: validierung, ob eine adresse einen Einkauf oder Verkauf durchfuehren kann
		this.add_oMAPValidator(new BSRG_P_MASK_MapValidator_PrufeFreigabeFirmaRechnungGutschrift());
		
		this.set_oSubQueryAgent(new BSRG_P_MASK_ComponentMAP_Subquery_Agent());

		//2013-06-27: neuer Validierer: nur schrottsorten als manuelle erfassung zulassen
		this.get_hmInteractiv_settings_validation().put_(_DB.VPOS_RG$ID_ARTIKEL, new BSRG_P_MASK__ComponentMAP_VALID_SET_SCHROTT());
		
		
		//2013-06-27: validierer einschalten
		this.add_oMAPValidator(this.get_hmInteractiv_settings_validation().get_XX_MAP_ValidBeforeSave());
		
		
		//2014-10-27: den masksetter registrieren
		BSRG_Set_And_Valid_Steuervermerk  oSetAndValidSteuer = new BSRG_Set_And_Valid_Steuervermerk(this) ;
		this.register_Interactiv_settings_validation(_DB.VPOS_RG$ID_TAX, oSetAndValidSteuer);
		this.register_Interactiv_settings_validation(BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT, oSetAndValidSteuer);
		
	}


	public boolean get_bUseAusFreiePositionen() 
	{
		return bUseAusFreiePositionen;
	}


	public E2_BasicModuleContainer_MASK get_oMaskContainer()
	{
		return oMaskContainer;
	}


	
	 
	
	
	private class own_AbzugsListe extends BL_Daughter_Abzuege
	{

		public own_AbzugsListe(SQLFieldMAP  oFM)   throws myException
		{
			super(oFM.get_oSQLFieldPKMainTable(),
					oFM,
					"ID_VPOS_RG",
					"JT_VPOS_RG_ABZUG",
					BSRG_P_MASK__ComponentMAP.this,
					(MyE2_Button)BSRG_P_MASK__ComponentMAP.this.get__Comp(BS__CONST.HASHKEY_FOR_NEUBERECHNUNGS_BUTTON));
			
			
			/*
			 * eingabe nur erlaubt, wenn es eine Artikelposition ist
			 * und nur beim bearbeiten, bei neueingabe gesperrt 
			 */
			this.EXT().add_ValidatorsEnabledAllowd(
				new XX_FieldValidator_InputAllowed()
				{
					public boolean isValid()
					{
						boolean bRueck = false;
						own_AbzugsListe oThis = own_AbzugsListe.this;
						
						SQLResultMAP oResult = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
						if (oResult != null)
						{
							try
							{
								// nur beim edit, nicht bei neueingabe und nur bei positionen vom typ belegposition
								if (oResult.get_UnFormatedValue("POSITION_TYP").equals(myCONST.VG_POSITION_TYP_ARTIKEL))
								{
									bRueck =true;
								}
							}
							catch (myException ex)
							{
							}
						}
						return bRueck;
					}
				});
			

			
			//2011-12-02
			this.fuege_titelblock_ein();            //anzeige des titelblocks

			
			
		}

		@Override
		public Component get__TitleComponentWithButtons() throws myException
		{
			BSRG_P_MASK__ComponentMAP  oThis = BSRG_P_MASK__ComponentMAP.this;
			
			Vector<MyString>   	vTexte = 		new Vector<MyString>();
			Vector<Component> 	vComponents = 	new Vector<Component>();

			String cBasisWaehrung = bibALL.get_WAEHRUNG_BASISWAEHRUNG_KURZ();
			
			vTexte.add(new MyE2_String("Neuer Abzug"));
			vTexte.add(new MyE2_String("Neu rechnen"));
			vTexte.add(new MyE2_String("Abzug Mengen"));
			vTexte.add(new MyE2_String("Abzug E-Preis"));
			vTexte.add(new MyE2_String("Abz.Betrag wegen Mengenabzug"));
			vTexte.add(new MyE2_String("Abz.Betrag auf die Nettomenge"));
			vTexte.add(new MyE2_String("Abz.Betrag wegen Vorauszahl."));
			vTexte.add(new MyE2_String("Abz.Betrag gesamt"));
			vTexte.add(new MyE2_String("Resultier.EPreis auf Brutto (alle Abzüge)"));
			vTexte.add(new MyE2_String("Resultier.EPreis NettoMge"));
			
			
			
			vComponents.add(this.get_oButNew());
			vComponents.add(this.get_oButRefresh());
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG),				oThis.get_Comp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG_LAGER),"Gesamt","zu Netto"));
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG),		oThis.get_Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG_FW),cBasisWaehrung,"#FW#"));
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE),			oThis.get_Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE_FW),cBasisWaehrung,"#FW#"));
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE),	oThis.get_Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE_FW),cBasisWaehrung,"#FW#"));
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG),	oThis.get_Comp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG_FW),cBasisWaehrung,"#FW#"));
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG),		oThis.get_Comp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG_FW),cBasisWaehrung,"#FW#"));
			
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT),		oThis.get_Comp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT_FW),cBasisWaehrung,"#FW#"));
			vComponents.add(this.gg(oThis.get_Comp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE),	oThis.get_Comp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE_FW),cBasisWaehrung,"#FW#"));
			
			
			E2_Subgrid_4_Mask oSubgrid = new E2_Subgrid_4_Mask(	vTexte, 
																vComponents,
																LayoutDataFactory.get_GridLayoutGridCenter_DARK(E2_INSETS.I_5_2_5_2),  
																LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_5_2_5_2), 
																true,
																new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID),
																true);
			
			return oSubgrid;
		}

		
		private MyE2_Grid gg(Component a,Component b,String cA, String cB)
		{
			MyE2_Grid oGridRueck = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			oGridRueck.add(a, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_0_0_5_0));
			oGridRueck.add(new MyE2_Label(cA,MyE2_Label.STYLE_SMALL_PLAIN()), LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_0_0_5_0));
			
			oGridRueck.add(b, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_0_0_5_0));
			oGridRueck.add(new MyE2_Label(cB,MyE2_Label.STYLE_SMALL_PLAIN()), LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_0_0_5_0));
			
			return oGridRueck;
		}
		
		
		@Override
		public RECORD_WAEHRUNG get__RECORD_FREMDWAEHRUNG() throws myException
		{
			RECORD_WAEHRUNG  recWaehrung = null;
			long lFremdWaehrung = BSRG_P_MASK__ComponentMAP.this.get_LActualDBValue(RECORD_VPOS_RG.FIELD__ID_WAEHRUNG_FREMD, new Long(-1),  new Long(-1));
			
			if (lFremdWaehrung>0) {	recWaehrung = new RECORD_WAEHRUNG(lFremdWaehrung);}
			return recWaehrung;
		}

		@Override
		public RECORD_ARTIKEL get__RECORD_ARTIKEL() throws myException
		{
			RECORD_ARTIKEL  recArtikel = null;
			long lArtikel = BSRG_P_MASK__ComponentMAP.this.get_LActualDBValue(RECORD_VPOS_RG.FIELD__ID_ARTIKEL, new Long(-1),  new Long(-1));
			
			if (lArtikel>0)	{ recArtikel = new RECORD_ARTIKEL(lArtikel);}
			return recArtikel;
		}

		
		
		
		@Override
		public String get__ActualFormatedValueMenge() throws myException
		{
			return S.NN(BSRG_P_MASK__ComponentMAP.this.get_cActualDBValueFormated(RECORD_VPOS_RG.FIELD__ANZAHL));
		}

		@Override
		public String get__ActualFormatedValuePreis() throws myException
		{
			return S.NN(BSRG_P_MASK__ComponentMAP.this.get_cActualDBValueFormated(RECORD_VPOS_RG.FIELD__EINZELPREIS));
		}

		@Override
		public String get__ActualFormatedValuePreis_FW() throws myException
		{
			return S.NN(BSRG_P_MASK__ComponentMAP.this.get_cActualDBValueFormated(RECORD_VPOS_RG.FIELD__EINZELPREIS_FW));
		}

		@Override
		public String get__ActualFormatedWaehrungsKurs() throws myException
		{
			return S.NN(BSRG_P_MASK__ComponentMAP.this.get_cActualDBValueFormated(RECORD_VPOS_RG.FIELD__WAEHRUNGSKURS));
		}

		@Override
		public void fill__MaskWithCalcResults(BL_Daughter_Abzuege DaughterAbzuege,BL_AbzugsKalkulator oBL_Kalk) throws myException
		{
			BSRG_P_MASK__ComponentMAP  oThis = BSRG_P_MASK__ComponentMAP.this;

			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG).set_cActualMaskValue(					MyNumberFormater.formatDez(oBL_Kalk.get_bdGESAMTER_MENGENABZUG(),3,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdEINZELPREIS_ABZUG(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG_FW).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdEINZELPREIS_ABZUG_FW(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdGESAMTPREIS_ABZUG(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG_FW).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdGESAMTPREIS_ABZUG_FW(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdRESULTIERENDER_EINZELPREIS(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT_FW).set_cActualMaskValue(		MyNumberFormater.formatDez(oBL_Kalk.get_bdRESULTIERENDER_EINZELPREIS_FW(),2,true));
			
			//neue felder
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG_LAGER).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdGESAMTER_MENGENABZUG_Lager(),3,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE).set_cActualMaskValue(				MyNumberFormater.formatDez(oBL_Kalk.get_bdSumme_Geldabzug_wegen_realem_MengenAbzug(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE_FW).set_cActualMaskValue(			MyNumberFormater.formatDez(oBL_Kalk.get_bdSumme_Geldabzug_wegen_realem_MengenAbzug_FW(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE).set_cActualMaskValue(		MyNumberFormater.formatDez(oBL_Kalk.get_bdSumme_Geldabzug_auf_NettoMenge(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE_FW).set_cActualMaskValue(	MyNumberFormater.formatDez(oBL_Kalk.get_bdSumme_Geldabzug_auf_NettoMenge_FW(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG).set_cActualMaskValue(		MyNumberFormater.formatDez(oBL_Kalk.get_bdSumme_Geldabzug_Vorauszahlung(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG_FW).set_cActualMaskValue(	MyNumberFormater.formatDez(oBL_Kalk.get_bdSumme_Geldabzug_Vorauszahlung_FW(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE).set_cActualMaskValue(		MyNumberFormater.formatDez(oBL_Kalk.get_bdRESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE(),2,true));
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE_FW).set_cActualMaskValue(	MyNumberFormater.formatDez(oBL_Kalk.get_bdRESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE_FW(),2,true));
			
		}

		@Override
		public void fill__MaskWithCalcResults_ON_EMPTY_LIST(BL_Daughter_Abzuege DaughterAbzuege)	throws myException
		{
			BSRG_P_MASK__ComponentMAP  oThis = BSRG_P_MASK__ComponentMAP.this;

			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_ABZUG_FW).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GESAMTPREIS_ABZUG_FW).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EINZELPREIS_RESULT_FW).prepare_ContentForNew(false);

			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__ANZAHL_ABZUG_LAGER).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_MGE_FW).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_AUF_NETTOMGE_FW).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__GPREIS_ABZ_VORAUSZAHLUNG_FW).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE).prepare_ContentForNew(false);
			oThis.get__DBComp(RECORD_VPOS_RG.FIELD__EPREIS_RESULT_NETTO_MGE_FW).prepare_ContentForNew(false);
			
			
			
		}

		@Override
		public boolean get_bSperreFremdWaehrung() throws myException
		{
			return false;
		}

	}
	//ende abzugsklasse
	
	
	
	
	
	
}
