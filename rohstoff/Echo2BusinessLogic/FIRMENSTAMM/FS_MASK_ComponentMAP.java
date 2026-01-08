package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;
 
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataIndexHashMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF.FS_Component_MASK_DAUGHTER_ARTIKELBEZ;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtGeoCodingAdresse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtShowAdresseandLagerGeopointsOSM;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtShowGeopoint;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN.FS_Component_MASK_DAUGHTER_KONTEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP.FSK_BT_ErmittleKosten_In_Adresse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP.FSK_BT_Ermittle_Kosten_Relationen_In_Adresse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP.FSK__FULL_MASK_DAUGHTER_TP_KOSTEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_HEAD_LIST_BasicModule_Inlay;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN.FS_Component_MASK_DAUGHTER_LIEFERADRESSEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID.FS_SETTING_STEUER_INDIKATOR;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID.FS_VALID_PRIVAT_GESCHAEFTSKUNDEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MASK_VALID.FS_ValidZusatzWaehrungen;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ.FS_Component_MASK_DAUGHTER_MATSPEZ;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER.FS_Component_MASK_DAUGHTER_MITARBEITER;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE.FS_PosList_FullDaughterTable;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.E2_SaveMaskSanktionsController;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.FS_CheckAdresseAfterSaving;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.FS_Component_MASK_DAUGHTER_ZUSATZINFOS;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased.AI_MASK_DaughterListForMotherMaskClassicType;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased.AI__TYP;
 
public class FS_MASK_ComponentMAP extends E2_ComponentMAP implements E2_SaveMaskSanktionsController
{
	private String cSTD_FIELDS1 = "VORNAME|NAME1|NAME2|NAME3|STRASSE|HAUSNUMMER|PLZ|ORT|ORTZUSATZ|PLZ_POB|POB|IS_POB_ACTIVE|RECHNUNGEN_SPERREN|GUTSCHRIFTEN_SPERREN|WARENAUSGANG_SPERREN|WARENEINGANG_SPERREN";
	private String cNameList ="Vorname|Name 1|Name 2|Name 3|Strasse|Hausnummer|PLZ|Ort|Ortszusatz|Fach-PLZ|Postfach|@Postfach aktiv|@Rechnungen Sperrvermerk MWSt.|@Gutschriften Sperrvermerk MWSt.|@Warenausgang sperren|@Wareneingang sperren";
	
	private String cField2 = "BEMERKUNGEN<L700L><H8H>|ERSTKONTAKT|GEBURTSDATUM|AUSWEIS_NUMMER|AUSWEIS_ABLAUF_DATUM|";
	private String cBesch2 = "Bemerkungen|Erstkontakt|Geburtstag|Ausweis-Nummer|Ablaufdatum|";
	
	private String cField3 = "RECHNUNG_PER_FAX<L100L>|EU_BEIBLATT_EMAIL|EU_BEIBLATT_ANSPRECH|EU_BEIBLATT_TEL|EU_BEIBLATT_FAX|EU_BEIBLATT_EK_VERTRAG|EU_BEIBLATT_VK_VERTRAG|BARKUNDE|TRANSFER|BEMERKUNG_FAHRPLAN<L400L><H6H>|";
	private String cBesch3 = "Rechnungen per Fax senden (hier: Faxnummer)|EU-Beiblatt-e-Mail|EU-Beiblatt-Ansprechpartner|EU-Beiblatt-Telefon|EU-Beiblatt-Fax|EU-Beiblatt-EK-Vertrag|EU-Beiblatt-VK-Vertrag|Barkunde|@Waagen-Transfer|Bemerkung Fahrplan|";
	

	// aenderung AVV-Infofelder
	private String cField4 = "|LIEF_NR|ABN_NR|";
	private String cBesch4 = "|Lieferant-Nr|Abnehmer-Nr|";

	
	private E2_ComponentMAP 		oCompMAP_FI = null;
	
	
	
	//validierer-Objekte:
	//private FSMV_PRUEFE_STEUERSCHALTER_INLAND  oPruefe_Schalter_Besteuerung = new FSMV_PRUEFE_STEUERSCHALTER_INLAND();  
	
	private FS_VALID_PRIVAT_GESCHAEFTSKUNDEN   oVALID_PRIVAT_GESCHAEFTSKUNDEN = new FS_VALID_PRIVAT_GESCHAEFTSKUNDEN();
	private FS_SETTING_STEUER_INDIKATOR  		oSETTING_STEUER_INDIKATOR = new FS_SETTING_STEUER_INDIKATOR();
	
	
	public FS_MASK_ComponentMAP(FS_ModulContainer_MASK	oOwnE2_ModulContainerMASK) throws myException
	{
		super(new FS_MASK_SQLFieldMap_ADRESSE());
		
		FS_MASK_SQLFieldMap_ADRESSE oSQLFieldMAP = (FS_MASK_SQLFieldMap_ADRESSE)this.get_oSQLFieldMAP();
		
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_ADRESSE")),new MyE2_String("ID"));

		MaskComponentsFAB.addStandardComponentsToMAP(cSTD_FIELDS1,cNameList,this.get_oSQLFieldMAP(),false,false,this,400);
		MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(cField2,cBesch2,this.get_oSQLFieldMAP(),false,false,this,400);
		MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(cField3,cBesch3,this.get_oSQLFieldMAP(),false,false,this,250);
		MaskComponentsFAB.addStandardComponentsToMAP(cField4,cBesch4,this.get_oSQLFieldMAP(),false,false,this,100);

		
		E2_DropDownSettings ddSprache = new E2_DropDownSettings( "JD_SPRACHE", "BEZEICHNUNG", "ID_SPRACHE", "ISTSTANDARD", true);
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
		E2_DropDownSettings ddMoney = new E2_DropDownSettings( "JD_WAEHRUNG", "KURZBEZEICHNUNG", "ID_WAEHRUNG", "IST_STANDARD", true);
		E2_DropDownSettings ddZahlBed = new E2_DropDownSettings( "JT_ZAHLUNGSBEDINGUNGEN", "KURZBEZEICHNUNG", "ID_ZAHLUNGSBEDINGUNGEN", "IST_STANDARD", true);
		E2_DropDownSettings ddAnrede = new E2_DropDownSettings("JT_ANREDE", "ANREDE", "ID_ANREDE", null, true);

		
		E2_DropDownSettings ddAdress_Potential = new E2_DropDownSettings("JT_ADRESSE_POTENTIAL", "KURZBESCHREIBUNG", "ID_ADRESSE_POTENTIAL", "STANDARD", true);
		E2_DropDownSettings ddAdress_Merkmal1 = new E2_DropDownSettings("JT_ADRESSE_MERKMAL1", "KURZBESCHREIBUNG", "ID_ADRESSE_MERKMAL1", "STANDARD", true);
		E2_DropDownSettings ddAdress_Merkmal2 = new E2_DropDownSettings("JT_ADRESSE_MERKMAL2", "KURZBESCHREIBUNG", "ID_ADRESSE_MERKMAL2", "STANDARD", true);
		E2_DropDownSettings ddAdress_Merkmal3 = new E2_DropDownSettings("JT_ADRESSE_MERKMAL3", "KURZBESCHREIBUNG", "ID_ADRESSE_MERKMAL3", "STANDARD", true);
		E2_DropDownSettings ddAdress_Merkmal4 = new E2_DropDownSettings("JT_ADRESSE_MERKMAL4", "KURZBESCHREIBUNG", "ID_ADRESSE_MERKMAL4", "STANDARD", true);
		E2_DropDownSettings ddAdress_Merkmal5 = new E2_DropDownSettings("JT_ADRESSE_MERKMAL5", "KURZBESCHREIBUNG", "ID_ADRESSE_MERKMAL5", "STANDARD", true);

		E2_DropDownSettings ddLiefBed = new E2_DropDownSettings( "JT_LIEFERBEDINGUNGEN", "KURZBEZEICHNUNG", "ID_LIEFERBEDINGUNGEN", "IST_STANDARD", true);
		
		/*
		 * defaults der dropdowns setzen
		 */
		oSQLFieldMAP.get_("ID_SPRACHE").set_cDefaultValueFormated(ddSprache.getDefault());
		
		oSQLFieldMAP.get_("ID_LAND").set_cDefaultValueFormated(ddLand.getDefault());
		oSQLFieldMAP.get_("ID_WAEHRUNG").set_cDefaultValueFormated(ddMoney.getDefault());
		oSQLFieldMAP.get_("ID_ZAHLUNGSBEDINGUNGEN").set_cDefaultValueFormated(ddZahlBed.getDefault());
		oSQLFieldMAP.get_("ID_LIEFERBEDINGUNGEN").set_cDefaultValueFormated(ddLiefBed.getDefault());

		//aenderung 2010-11-26: zahlungs- und lieferbedinungen fuer EK- und VK getrennt
		oSQLFieldMAP.get_("ID_ZAHLUNGSBEDINGUNGEN_VK").set_cDefaultValueFormated(ddZahlBed.getDefault());
		oSQLFieldMAP.get_("ID_LIEFERBEDINGUNGEN_VK").set_cDefaultValueFormated(ddLiefBed.getDefault());
		
		oSQLFieldMAP.get_("ID_ANREDE").set_cDefaultValueFormated(ddAnrede.getDefault());
		oSQLFieldMAP.get_("ID_ADRESSE_POTENTIAL").set_cDefaultValueFormated(ddAdress_Potential.getDefault());
		oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL1").set_cDefaultValueFormated(ddAdress_Merkmal1.getDefault());
		oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL2").set_cDefaultValueFormated(ddAdress_Merkmal2.getDefault());
		oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL3").set_cDefaultValueFormated(ddAdress_Merkmal3.getDefault());
		oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL4").set_cDefaultValueFormated(ddAdress_Merkmal4.getDefault());
		oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL5").set_cDefaultValueFormated(ddAdress_Merkmal5.getDefault());
		/*
		 *  fertig
		 */
		
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP.get_("ID_USER"),true),new MyE2_String("Betreuer"));
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP.get_("ID_USER_ERSATZ"),true),new MyE2_String("Betreuer (2)"));
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP.get_("ID_USER_SACHBEARBEITER"),true),new MyE2_String("Sachbearbeiter"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_SPRACHE"),ddSprache.getDD(),false),new MyE2_String("Sprache"));
//		MyE2_DB_SelectField oSelLand = 
//			(MyE2_DB_SelectField)this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_LAND"),ddLand.getDD(),false),new MyE2_String("Land"));
//		
		this.add_Component(new DB_Component_LAND_DROPDOWN(oSQLFieldMAP.get_("ID_LAND"),100,true,new FS__ActionAgent_SUCHE_DEB_KRED_NUMMER(this,true)),new MyE2_String("Land"));
		
		MyE2_DB_SelectField selWaehrung = new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_WAEHRUNG"),ddMoney.getDD(),false);
		selWaehrung.setWidth(new Extent(65));
		
		this.add_Component(selWaehrung,new MyE2_String("Währung"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ZAHLUNGSBEDINGUNGEN"),ddZahlBed.getDD(),false),new MyE2_String("Zahlungsbedingungen"));
		//this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_LIEFERBEDINGUNGEN"),ddLiefBed.getDD(),false),new MyE2_String("Lieferbedingungen"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ZAHLUNGSBEDINGUNGEN_VK"),ddZahlBed.getDD(),false),new MyE2_String("Zahlungsbedingungen"));
		//this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_LIEFERBEDINGUNGEN_VK"),ddLiefBed.getDD(),false),new MyE2_String("Lieferbedingungen"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ANREDE"),ddAnrede.getDD(),false),new MyE2_String("Anrede"));
		
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ADRESSE_POTENTIAL"),ddAdress_Potential.getDD(),false),new MyE2_String("Adresspotential"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL1"),ddAdress_Merkmal1.getDD(),false),new MyE2_String("Merkmal 1"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL2"),ddAdress_Merkmal2.getDD(),false),new MyE2_String("Merkmal 2"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL3"),ddAdress_Merkmal3.getDD(),false),new MyE2_String("Merkmal 3"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL4"),ddAdress_Merkmal4.getDD(),false),new MyE2_String("Merkmal 4"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ADRESSE_MERKMAL5"),ddAdress_Merkmal5.getDD(),false),new MyE2_String("Merkmal 5"));
	
		//2013-07-24: lieferbedingungen mit schalter aktiv
		this.add_Component(new FS_Component_MASK_Select_Lieferbedingungen(oSQLFieldMAP.get_(_DB.ADRESSE$ID_LIEFERBEDINGUNGEN)),new MyE2_String("Lieferbedingungen Einkauf"));
		this.add_Component(new FS_Component_MASK_Select_Lieferbedingungen(oSQLFieldMAP.get_(_DB.ADRESSE$ID_LIEFERBEDINGUNGEN_VK)),new MyE2_String("Lieferbedingungen Verkauf"));
		

//		//aktion suche debitor/kreditor-nummer
//		oSelLand.add_oActionAgent(new FS__ActionAgent_SUCHE_DEB_KRED_NUMMER(this,true));
		
		
		//ein Zauberstab fuers ermitteln der Debitoren/Kreditoren-Nummer
		MyE2_Button  oButtonDebKred = new MyE2_Button(E2_ResourceIcon.get_RI("wizard.png"),true);
		oButtonDebKred.add_oActionAgent(new FS__ActionAgent_SUCHE_DEB_KRED_NUMMER(this,false));
		this.add_Component(FS_CONST.MASK_FIELD_BUTTON_DEB_KRED_ZAUBERSTAB, oButtonDebKred, new MyE2_String("Die nächsten freien Debitor/Kreditor-Nummern suchen"));
		
		
		//NEU_09
		// aktiv-button wird geschuetzt durch buttonkenner
		MyE2_DB_CheckBox oDBAktiv = new MyE2_DB_CheckBox(oSQLFieldMAP.get_("AKTIV"));
		oDBAktiv.set_DescriptionAsCheckboxText(true);
		oDBAktiv.add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),"AKTIVIEREN_ADRESSE"));
		oDBAktiv.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
		});
		
		this.add_Component(oDBAktiv,new MyE2_String("Aktiv"));
		//this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("AKTIV"),oSQLFieldMAP,bibE2.get_YN_Ary_WithLeer(),false),new MyE2_String("Aktiv"));
		//ENDE NEU_09
		
		//20170926: schalter aktiv
		this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(ADRESSE.ah7_quelle_sicher), 	
												new MyE2_String("Sichere Anhang7-Quelle"),  
												new MyE2_String("Sichere Anhang7-Quelle: Lieferungen von dieser Station sind Anhang7-unkritisch, was Offenlegung des Warenwegs angeht.")
												),new MyE2_String("Sichere Anhang7-Quelle"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(ADRESSE.ah7_ziel_sicher), 	
												new MyE2_String("Sicheres Anhang7-Ziel"),  
												new MyE2_String("Sicheres Anhang7-Ziel: Lieferungen zu dieser Station sind Anhang7-unkritisch, was Offenlegung des Warenwegs angeht.")
												),new MyE2_String("Sicheres Anhang7-Ziel"));
		//20170926
		
		
		
		//2014-11-17: neues dropdown-Feld fuer die Einstellung, welches EU-Vertragsformular zu verwenden ist
		E2_DropDownSettingsNew  oDD_VT = new E2_DropDownSettingsNew(_DB.ADRESSE_EU_VERTR_FORM,
																	_DB.ADRESSE_EU_VERTR_FORM$INFOTEXT,
																	_DB.ADRESSE_EU_VERTR_FORM$ID_ADRESSE_EU_VERTR_FORM,
																	null,
																	true,
																	true);
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_(_DB.ADRESSE$ID_ADRESSE_EU_VERTR_FORM), oDD_VT.getDD(), false, new Extent(400)), new MyE2_String("EU-Vertrags-Formular"));
		//2014-11-17 ---
		
		
		((MyE2_DB_SelectField)this.get__Comp(_DB.ADRESSE$ID_LAND)).setWidth(new Extent(100));
		((MyE2_DB_SelectField)this.get__Comp(_DB.ADRESSE$ID_SPRACHE)).setWidth(new Extent(100));
		((MyE2_DB_TextField)this.get__Comp(_DB.ADRESSE$PLZ)).setWidth(new Extent(95));
		((MyE2_DB_TextField)this.get__Comp(_DB.ADRESSE$PLZ_POB)).setWidth(new Extent(95));
		

		this.add_Component(new MyE2_DB_TextArea(oSQLFieldMAP.get_("LIEFERINFO_TPA"),400,6),new MyE2_String("Lieferinfo TPA"));
		
		
		/*
		 * komplexe feldelemente
		 */
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_TELEFON,new FS_Component_MASK_DAUGHTER_TELEFON(oSQLFieldMAP,this),new MyE2_String("Kommunikationsangaben"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_EMAIL,new FS_Component_MASK_DAUGHTER_EMAIL(oSQLFieldMAP,this, true,true),new MyE2_String("E-Mail-Adressen"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_INTERNET,new FS_Component_MASK_DAUGHTER_INTERNET(oSQLFieldMAP,this),new MyE2_String("Internet-Adressen"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_BEZIEHUNG,new FS_Component_MASK_DAUGHTER_BEZIEHUNGEN(oSQLFieldMAP,this),new MyE2_String("Geschäftsbeziehungen"));
		this.add_Component(FS_CONST.MASK_FIELD_CROSS_CONNECT_TYP,new FS_Component_MASK_CROSS_ADRESSART(oSQLFieldMAP),new MyE2_String("Adressklasse"));
		this.add_Component(FS_CONST.MASK_FIELD_CROSS_CONNECT_AUSTATTUNG,new FS_Component_MASK_CROSS_ADRESSAUSSTATTUNG(oSQLFieldMAP),new MyE2_String("Ausstattung"));
		this.add_Component(FS_CONST.MASK_FIELD_CROSS_CONNECT_MWST,new FS_Component_MASK_CROSS_STEUERSAETZE(oSQLFieldMAP),new MyE2_String("Steuersätze"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_CONTAINERTYPEN,new FS_Component_MASK_DAUGHTER_CONTAINERTYPEN(oSQLFieldMAP,this),new MyE2_String("Erlaubte Container"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_UST_IDS,new FS_Component_MASK_DAUGHTER_UST_IDS(oSQLFieldMAP,this),new MyE2_String("Weitere Umsatzsteuer-IDs"));
		
		
		//neues feld fuer weitere ZUSATZBRANCHEN
		this.add_Component(FS_CONST.MASK_FIELD_CROSS_CONNECT_ZUSATZBRANCHE,new FS_Component_MASK_WeitereBranchen(oSQLFieldMAP.get("ID_ADRESSE")),new MyE2_String("Zusatzbranchen"));
		
		
		
		/*
		 * komplette tochtertabellen mit eigener maske / oder komplett-editierbare liste mit navigation
		 */
		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER,
				new FS_Component_MASK_DAUGHTER_MITARBEITER(oSQLFieldMAP,oOwnE2_ModulContainerMASK),new MyE2_String("Mitarbeiter"));
		
		
		//20181214: neue info und meldungserfassungs-toechter
//		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS,
//				new FS_Component_MASK_DAUGHTER_ZUSATZINFOS(oSQLFieldMAP,oOwnE2_ModulContainerMASK,true),new MyE2_String("Infos"));
//
//		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES,
//				new FS_Component_MASK_DAUGHTER_ZUSATZINFOS(oSQLFieldMAP,oOwnE2_ModulContainerMASK,false),new MyE2_String("Meld."));
		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS,
				new AI_MASK_DaughterListForMotherMaskClassicType(AI__TYP.INFO,true),new MyE2_String("Infos"));

		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES,
				new AI_MASK_DaughterListForMotherMaskClassicType(AI__TYP.MELDUNG,true),new MyE2_String("Meldungen"));
		//20181214: neue info -- ende
		
		
		
		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN,
				new FS_Component_MASK_DAUGHTER_LIEFERADRESSEN(oSQLFieldMAP,oOwnE2_ModulContainerMASK),new MyE2_String("Lieferadressen"));

		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_KONTEN,
				new FS_Component_MASK_DAUGHTER_KONTEN(oSQLFieldMAP,oOwnE2_ModulContainerMASK),new MyE2_String("Konten"));

		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ,
				new FS_Component_MASK_DAUGHTER_MATSPEZ(oSQLFieldMAP,oOwnE2_ModulContainerMASK),new MyE2_String("Materialspezifikationen"));

		//NEU_09
		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK,
				new FS_Component_MASK_DAUGHTER_ARTIKELBEZ(oSQLFieldMAP,oOwnE2_ModulContainerMASK,this,"EK"),new MyE2_String("Kundenspezifische Artikelbezeichnungen EK"));
		this.add_Component(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK,
				new FS_Component_MASK_DAUGHTER_ARTIKELBEZ(oSQLFieldMAP,oOwnE2_ModulContainerMASK,this,"VK"),new MyE2_String("Kundenspezifische Artikelbezeichnungen VK"));
		//NEU_09 --ende

		
		// Manfred:2013-04-15 Tabreiter Kundenstatus Forderungen / Verbindlichkeiten
		FS_Component_MASK_INFO_FIRMENSTATUS oFS_Component_MASK_INFO_FIRMENSTATUS = new FS_Component_MASK_INFO_FIRMENSTATUS(oSQLFieldMAP,oOwnE2_ModulContainerMASK);
		this.add_Component(FS_CONST.MASK_FIELD_INFO_KUNDENSTATUS,
				oFS_Component_MASK_INFO_FIRMENSTATUS,new MyE2_String("Kundenstatus Forderungen/Verbindlichkeiten"));
		//die komponente zum inaktiv setzen wenn das fenster geschlossen wird (damit bei naechten oeffnen diese komponente als aktiver tab nicht zu viel zeit beansprucht
		// 2018-07-11 Manfred: wieder aktiviert, da auch das verlassen durch X nicht inaktiv schaltet.
//		oOwnE2_ModulContainerMASK.get_vActionsOnWindowClose().add(oFS_Component_MASK_INFO_FIRMENSTATUS);
		
		//2011-09-20: inlay fuer kreditversicherung
		KV_HEAD_LIST_BasicModule_Inlay oInlay_VersicherungsKopf = new KV_HEAD_LIST_BasicModule_Inlay(oOwnE2_ModulContainerMASK);
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_KREDITVERSICHERUNG, 
						new FS_MASK_FullDaughter_Kreditversicherung(oSQLFieldMAP.get_oSQLFieldPKMainTable(), oInlay_VersicherungsKopf), new MyE2_String("Kreditversicherung"));
		
		
		//2013-05-31: tochtertabelle fuer die erfassung der erlaubten avv-codes
		__FS_Component_MASK_DAUGHTER_EAK_CODES oDaughterAVV = new __FS_Component_MASK_DAUGHTER_EAK_CODES(oSQLFieldMAP,this);
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT,
				oDaughterAVV,new MyE2_String("Erlaubte AVV-Codes bei dieser Adresse"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_EXPORT,
				new __FS_BT_EXPORT_EAK_LIST(oDaughterAVV),new MyE2_String("Exportiere AVV-ID-Liste"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_IMPORT,
				new __FS_BT_IMPORT_EAK_LIST(oDaughterAVV),new MyE2_String("Importiere AVV-ID-Liste"));
	
		 
//		//2013-11-19 Transportkosten 
		//FSK_Daughter_Kosten_Lieferbed 		oDaughterTransportkosten = new FSK_Daughter_Kosten_Lieferbed(oSQLFieldMAP,this);
		FSK__FULL_MASK_DAUGHTER_TP_KOSTEN   oDaughterTransportkosten = new FSK__FULL_MASK_DAUGHTER_TP_KOSTEN(oSQLFieldMAP,oOwnE2_ModulContainerMASK); 
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE,	oDaughterTransportkosten,new MyE2_String("Transportkosten dieser Adresse"));
		//2014-03-14: button, der alle moeglichen kostenkobinationen sucht und einbaut
		FSK_BT_Ermittle_Kosten_Relationen_In_Adresse    		oButtonSearchKost = new FSK_BT_Ermittle_Kosten_Relationen_In_Adresse();
		this.add_Component(FS_CONST.MASK_BUTTON_SUCHE_ALLE_KOSTEN_KOMBIS,	oButtonSearchKost,new MyE2_String("Suchen der vorhandenen Kombinationen von Warenbewegungen"));
		FSK_BT_ErmittleKosten_In_Adresse oButtonSearchPreiseFuerRelationen = new FSK_BT_ErmittleKosten_In_Adresse();
		this.add_Component(FS_CONST.MASK_BUTTON_SUCHE_PREISE_FUER_KOSTEN_KOMBIS,	oButtonSearchPreiseFuerRelationen,new MyE2_String("Ermittle die Transportkosten dieser Adresse"));
		
		
		
		//2014-01-17: Positionen, Erfassung von Zahlungen auf Positionsebene
		FS_PosList_FullDaughterTable   oDaughter_RECH_GUT_Positionen = new FS_PosList_FullDaughterTable(oSQLFieldMAP);
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_RECH_GUT_POS,	oDaughter_RECH_GUT_Positionen,new MyE2_String("Rechnungs- und Gutschriftspositionen"));		
		
		
		//2012-03-05: neue Felder: ID_USER_LAGER_HAENDLER, ID_USER_LAGER_SACHBEARB, LAGER_KONTROLLINFO
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP.get_(RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB), true, 300), new MyE2_String("Sachbearbeiter für das Lager"));
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP.get_(RECORD_ADRESSE.FIELD__ID_USER_LAGER_HAENDLER), true, 300), new MyE2_String("Händler für das Lager"));
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_(RECORD_ADRESSE.FIELD__LAGER_KONTROLLINFO), true, 600), new MyE2_String("Lager-Kontroll-Information"));

		//2014-07-10: neues infofeld fuer die AVV-Kontrakte
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_(RECORD_ADRESSE.FIELD__EU_BEIBLATT_INFOFELD), true, 600), new MyE2_String("Infos zu den EU-Verträgen"));
		
		
		//2013-11-19: komponente fuer die anzeige des status /steuer
		this.add_Component(FS_CONST.MASK_FIELD_COMP_ANZEIGE_STEUER_STATUS, new __FS_MASKEN_INDIKATOR(),new MyE2_String("Anzeige Status im Bezug auf die Steuer"));

		
		//2014-11-27: neuer anzeigen-block: erzeugt_von/am, geaendert von/am
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_(_DB.ADRESSE$ERZEUGT_VON)), new MyE2_String("Erzeugt von"));
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_(_DB.ADRESSE$ERZEUGT_AM)), new MyE2_String("Erzeugt am"));
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_(_DB.ADRESSE$GEAENDERT_VON)), new MyE2_String("Geändert von"));
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_(_DB.ADRESSE$LETZTE_AENDERUNG)), new MyE2_String("Geändert am"));
		
		
		
		//2018-01-29: gps-koordinaten
		MyE2_DB_TextField tfLongitude= new MyE2_DB_TextField(oSQLFieldMAP.get_(ADRESSE.longitude.fn()),	true,	100);
		MyE2_DB_TextField tfLatitude= new MyE2_DB_TextField(oSQLFieldMAP.get_(ADRESSE.latitude.fn()),	true, 	100);
		this.add_Component(tfLongitude, new MyE2_String("Längengrad"));
		this.add_Component(tfLatitude, new MyE2_String("Breitengrad"));
		this.add_Component(EN_FS_Fields.GPS_BUTTON_SEARCH.name(), new FS_MaskBtGeoCodingAdresse(tfLongitude,tfLatitude), new MyE2_String("Geocodieren der Adresse ..."));
		this.add_Component(EN_FS_Fields.GPS_BUTTON_VIEW_IN_MAP.name(), new FS_MaskBtShowGeopoint(), new MyE2_String("Zeige die Adresse auf Karte ..."));
		this.add_Component(EN_FS_Fields.GPS_BUTTON_VIEW_ALL_IN_MAP.name(), new FS_MaskBtShowAdresseandLagerGeopointsOSM()._ttt("Zeigt Adresse und alle zugehörigen Lager auf der Karte ..."), new MyE2_String("Zeigt Adresse und alle zugehörigen Lager auf der Karte ..."));
		
		
		this.add_Component(EN_FS_Fields.MASK_FIELD_ZUSATZWAEHRUNGEN.name(), new FS_Component_MASK_WeitereWaehrungen(oSQLFieldMAP.get_(ADRESSE.id_adresse)), S.ms("Zusatzwährungen"));
		
		
		this.oCompMAP_FI = new FS_ComponentMAP_MASK_FIRMENINFO((FS_MASK_SQLFieldMap_ADRESSE)this.get_oSQLFieldMAP(),oOwnE2_ModulContainerMASK);
		
		this.set_oSubQueryAgent(new SubQueryAgentToDisplayInfoLine());
		this.add_oSubQueryAgent(new ownSubQueryAgent());
		this.add_oSubQueryAgent(new ownSubQueryAgentSetDateTime_In_LetzteAenderung());

		
		
		//eine methode, die die XX_MAP_Set_And_Validator setzt und den komponenten den actionagent zuteilt 
		this.setze_XX_MAP_Set_And_Valid_objects(this, this.oCompMAP_FI);
		
		
		
		

		
		
		//2012-08-03: bestimmte feldlaengen definieren
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_TEL)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_FAX)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_ANSPRECH)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_EMAIL)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_EK_VERTRAG)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_VK_VERTRAG)).set_iWidthPixel(200);

		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__LAGER_KONTROLLINFO)).set_iWidthPixel(700);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_INFOFELD)).set_iWidthPixel(700);
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB)).setWidth(new Extent(140));
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_USER_LAGER_HAENDLER)).setWidth(new Extent(140));

		
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_ADRESSE_POTENTIAL)).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_ADRESSE_MERKMAL1)).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_ADRESSE_MERKMAL2)).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_ADRESSE_MERKMAL3)).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_ADRESSE_MERKMAL4)).setWidth(new Extent(150));
		((MyE2_DB_SelectField)this.get_Comp(RECORD_ADRESSE.FIELD__ID_ADRESSE_MERKMAL5)).setWidth(new Extent(150));
		
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__AUSWEIS_NUMMER)).set_iWidthPixel(200);
		
		((MyE2_DB_TextArea)this.get_Comp(RECORD_ADRESSE.FIELD__BEMERKUNGEN)).set_iWidthPixel(810);
		
		
		((MyE2_DB_TextField)this.get_Comp(ADRESSE.lief_nr.fn())).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get_Comp(ADRESSE.abn_nr.fn())).set_iWidthPixel(80);
		
		
		//dem feld Barkunde wird ein actionagent definiert, der automatisch das feld Sckeckdruck einschaltet, wenn es ausgeschaltet war
		((E2_IF_Handles_ActionAgents)this.get__Comp("BARKUNDE")).add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (((MyE2_DB_CheckBox)FS_MASK_ComponentMAP.this.get__Comp("BARKUNDE")).isSelected())
				{
					((MyE2_DB_CheckBox)FS_MASK_ComponentMAP.this.get_E2_ComponentMAP_Firmeninfo().get__Comp("SCHECKDRUCK_JN")).setSelected(true);
				}
			}
		});
		
       
		//dem Feld privatkunde bei neuerfassung automatisch die Bar- und scheckdruck setzen
		((E2_IF_Handles_ActionAgents)this.oCompMAP_FI.get__Comp(_DB.FIRMENINFO$PRIVAT)).add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (FS_MASK_ComponentMAP.this.get_bIs_Neueingabe()) {
					((MyE2_DB_CheckBox)FS_MASK_ComponentMAP.this.get__Comp(_DB.ADRESSE$BARKUNDE)).setSelected(true);
					((MyE2_DB_CheckBox)FS_MASK_ComponentMAP.this.oCompMAP_FI.get__Comp(_DB.FIRMENINFO$SCHECKDRUCK_JN)).setSelected(true);
				}
			}
		});
		
		
		
		
	}

	
	
	
	
	
	
	/*
	 * schaltet das Feld Auslandsvertretungstext bei der UST_ID_Tochter frei
	 */
	private class ownSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT
	{

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException 
		{
			((FS_Component_MASK_DAUGHTER_UST_IDS)oMAP.get__Comp(FS_CONST.MASK_FIELD_DAUGHTER_UST_IDS)).set_enableAuslandsText(false);
			
			
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException 
		{
			boolean bIsMandant = (oUsedResultMAP.get_LActualDBValue("ID_ADRESSE", false).longValue()==
												bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(new Long(-1)).longValue());
			
			((FS_Component_MASK_DAUGHTER_UST_IDS)oMAP.get__Comp(FS_CONST.MASK_FIELD_DAUGHTER_UST_IDS)).set_enableAuslandsText(bIsMandant);
			
		}
		
	}
	
	
	private class ownSubQueryAgentSetDateTime_In_LetzteAenderung extends XX_ComponentMAP_SubqueryAGENT {

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {
			
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
			((MyE2_DB_Label)oMAP.get__Comp(_DB.ADRESSE$LETZTE_AENDERUNG)).set_cActualMaskValue(oUsedResultMAP.get(_DB.ADRESSE$LETZTE_AENDERUNG).get_cDateTimeValueFormated());			
		}
		
	}
	
	
	
	public E2_ComponentMAP get_E2_ComponentMAP_Firmeninfo()
	{
		return this.oCompMAP_FI;
	}
	
	
	
	
	public class FS_ComponentMAP_MASK_FIRMENINFO extends E2_ComponentMAP
	{
		String cFields1 = "|BESCHREIBUNG<L700L><H8H>|ZAHLANGESTELLTE_AM|GRUENDUNGSDATUM|";
		String cFields2 = "|OEFFNUNGSZEITEN<L700L>|STEUERNUMMER|";
		String cFields3 = "|ABLADEMENGE_FUER_GUTSCHRIFT|LADEMENGE_FUER_RECHNUNG|SCHECKDRUCK_JN|OHNE_STEUER_WARENAUSGANG|OHNE_STEUER_WARENEINGANG";
		
		String cBeschr1 = "|Beschreibung (FI)|Zahl Angestellte am|Gründungsdatum|";
		String cBeschr2 = "|Öffnungszeiten|Steuernummer|";
		String cBeschr3 = "|@Ablademenge für Gutschriften|@Lademenge für Rechnungen|Scheckdruck|@VK steuerfrei|@EK steuerfrei";
		
		String cFieldsMax100 = "|ZAHL_ANGESTELLTE|AQUISITIONSKOSTEN|DOKUMENTKOPIEN|STAMMKAPITAL|BESUCHSRYTHMUS|KREDITOR_NUMMER|DEBITOR_NUMMER|BETRIEBSNUMMER_SAA|KREDITSTAND|";
		String cBeschrMax100 = "|Zahl Angestellte|Aquisitionskosten|Dokumentkopien|Stammkapital|Besuchsrythmus|Kreditor-Nummer|Debitor-Nummer|Betriebsnummer-SAA|Kreditstand|";

		String cFieldsMax200 = "|LIEFERMENGE_SCHNITT|LIEFERMENGE_MAX|UMSATZSTEUERID|HANDELSREGISTER|KREDITVERS_NR|";
		String cBeschrMax200 = "|Liefermenge (Schnitt)|Liefermenge (Max)|Umsatzsteuer-ID|Handelsregister|Kreditversicherung-Nr|";

		String cFieldsMax50 = "|UMSATZSTEUERLKZ|";
		String cBeschrMax50 = "|Umsatzsteuer-Kennzeichen|";

		
		String cFieldsKreditLimit = "|KREDITLIMIT|KREDITLIMIT2|KREDITLIMIT3|KREDITLIMIT_VON|KREDITLIMIT_BIS|KREDITLIMIT2_VON|KREDITLIMIT2_BIS|KREDITLIMIT3_VON|KREDITLIMIT3_BIS|";
		String cBeschrKreditLimit = "|Kreditlimit|Kreditlimit2|Kreditlimit3|Kreditlimit (von)|Kreditlimit (bis)|Kreditlimit2 (von)|Kreditlimit2 (bis)|Kreditlimit3 (von)|Kreditlimit3 (bis)|";
		
		
		//aenderung 2010-12-08: Kreditversicherungsanfragen
		String cFieldsKreditVers = "|VERSICHANFR_SUMME|VERSICHANFR_DAT|VERSICH_MELDEFRIST_TAGE|";
		String cBeschrKreditVers = "|Versicherungsanfrage Summe|Versicherungsanfrage Datum|Versicherungsanfrage Meldefrist|";
		
		
		
		//2011-12-02: alte kreditlimit-felder deaktivieren
		String[]  cFelderInaktiv = {"KREDITLIMIT",
				"KREDITLIMIT2",
				"KREDITLIMIT3",
				"KREDITSTAND",
				"KREDITLIMIT_VON",
				"KREDITLIMIT2_VON",
				"KREDITLIMIT3_VON",
				"KREDITVERS_NR",
				"KREDITLIMIT_BIS",
				"KREDITLIMIT2_BIS",
				"KREDITLIMIT3_BIS",
				"ID_KREDITLIMIT_BEDINGUNG1",
				"ID_KREDITLIMIT2_BEDINGUNG1",
				"ID_KREDITLIMIT3_BEDINGUNG1",
				"ID_KREDITLIMIT_BEDINGUNG2",
				"ID_KREDITLIMIT2_BEDINGUNG2",
				"ID_KREDITLIMIT3_BEDINGUNG2",
				"ID_KREDITLIMIT_BEDINGUNG3",
				"ID_KREDITLIMIT2_BEDINGUNG3",
				"ID_KREDITLIMIT3_BEDINGUNG3",
				"VERSICHANFR_SUMME",
				"VERSICHANFR_DAT",
				"VERSICH_MELDEFRIST_TAGE"};
		
		
		public FS_ComponentMAP_MASK_FIRMENINFO(FS_MASK_SQLFieldMap_ADRESSE sqlfieldMAP_Adresse,FS_ModulContainer_MASK	oOwnE2_ModulContainerMASK) throws myException
		{
			super(new FS_MASK_SQLFieldMap_FIRMENINFO(sqlfieldMAP_Adresse));
			
			FS_MASK_SQLFieldMap_FIRMENINFO oSQLFieldMAP = (FS_MASK_SQLFieldMap_FIRMENINFO)this.get_oSQLFieldMAP();
			
			E2_DropDownSettings ddBranche = new E2_DropDownSettings("JT_BRANCHE", "KURZBEZEICHNUNG", "ID_BRANCHE", "IST_STANDARD", true);

			EAK_BrancheDDSettings dd_EAK_BRANCHE = new EAK_BrancheDDSettings();
			
			E2_DropDownSettings ddRechtsform = new E2_DropDownSettings("JT_RECHTSFORM", "KURZBEZEICHNUNG", "ID_RECHTSFORM", "IST_STANDARD", true);
			E2_DropDownSettings ddZahlungsmed = new E2_DropDownSettings("JT_ZAHLUNGSMEDIUM", "KURZBEZEICHNUNG", "ID_ZAHLUNGSMEDIUM", "IST_STANDARD", true);

			E2_DropDownSettings ddKreditbedingungen = new E2_DropDownSettings( "JT_KREDITLIMIT_BEDINGUNG", "BEDINGUNG", "ID_KREDITLIMIT_BEDINGUNG",null, null, true, null);
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT_BEDINGUNG1"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 11"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT_BEDINGUNG2"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 12"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT_BEDINGUNG3"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 13"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT2_BEDINGUNG1"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 21"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT2_BEDINGUNG2"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 22"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT2_BEDINGUNG3"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 23"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT3_BEDINGUNG1"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 31"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT3_BEDINGUNG2"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 32"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_KREDITLIMIT3_BEDINGUNG3"),ddKreditbedingungen.getDD(),false), new MyE2_String("Kreditbedingung 33"));

			//2015-04-21: UST-Check-Button
			this.add_Component(FS_CONST.MASK_BT_CHECK_USTID, new FS_BT_CheckUStID(	_DB.FIRMENINFO$UMSATZSTEUERLKZ, _DB.FIRMENINFO$UMSATZSTEUERID), new MyE2_String("Prüfe Umsatzsteuer-ID..."));

			
			MyE2_DB_SelectField oSelEAK_Branche = new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_EAK_BRANCHE"),dd_EAK_BRANCHE.getDD(),false);
			oSelEAK_Branche.setFont(new E2_FontItalic(-2));
			
			
			MyE2_DB_SelectField oSelVerlaengerterEigentumsvorbehalt = new MyE2_DB_SelectField(oSQLFieldMAP.get_("VERLAENGERT_EIGENTUM_VORBEHALT"),
																					myCONST.VERLAENGERTER_EIGENTUMSVORBEHALT,false);

			MyE2_DB_SelectField oSelFORDERUNGSVERRECHNUNG = new MyE2_DB_SelectField(oSQLFieldMAP.get_("FORDERUNGSVERRECHNUNG"),
																						myCONST.FORDERUNGSVERRECHNUNG,false);

			oSelVerlaengerterEigentumsvorbehalt.setFont(new E2_FontItalic(-2));
			oSelFORDERUNGSVERRECHNUNG.setFont(new E2_FontItalic(-2));
			oSelFORDERUNGSVERRECHNUNG.setWidth(new Extent(150));
			
			
			this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_FIRMENINFO")),new MyE2_String("ID-Firmeninfo"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_BRANCHE"),ddBranche.getDD(),false),new MyE2_String("Branche"));
			this.add_Component(oSelEAK_Branche,new MyE2_String("AVV-Branche"));
			this.add_Component(oSelVerlaengerterEigentumsvorbehalt,new MyE2_String("Verlängerter Eigentumsvorbehalt"));
			this.add_Component(oSelFORDERUNGSVERRECHNUNG,new MyE2_String("Forderungsverrechungsvereinbarung"));
			
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_RECHTSFORM"),ddRechtsform.getDD(),false),new MyE2_String("Rechtsform"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ZAHLUNGSMEDIUM"),ddZahlungsmed.getDD(),false),new MyE2_String("Zahlungsmedium"));
			
//			//2014-01-28: nicht mehr verwendet
//			//2012-12-18: neue felder fuer die steuersatz-validierung
//			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("VORSTEUERABZUG_RC_INLAND")),	new MyE2_String("Vorsteuerabzug im Inland / RC im Inland"));
//			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("STEUERNUMMER_STATT_UST")),		new MyE2_String("Steuernummer plus Steuerberaterauskunft ersetzt UST-ID"));
//			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("INNERGEMEIN_LIEF_EU")),			new MyE2_String("EU-Ausland: Innergemeinschaftliche Lieferungen erlaubt (nur mit UST-ID)"));
			
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFields1,		this.cBeschr1,oSQLFieldMAP,false,false,this,400);
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFields2,		this.cBeschr2,oSQLFieldMAP,false,false,this,400);
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFields3,		this.cBeschr3,oSQLFieldMAP,false,false,this,400);
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFieldsMax50,this.cBeschrMax50,oSQLFieldMAP,false,false,this,50);
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFieldsMax100,this.cBeschrMax100,oSQLFieldMAP,false,false,this,100);
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFieldsMax200,this.cBeschrMax200,oSQLFieldMAP,false,false,this,200);
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFieldsKreditLimit,this.cBeschrKreditLimit,oSQLFieldMAP,false,false,this,100);

			//aenderung 2010-12-08: Kreditversicherungsanfragen
			MaskComponentsFAB.addStandardComponentsToMAP_m_Zusatz(this.cFieldsKreditVers,this.cBeschrKreditVers,oSQLFieldMAP,false,false,this,100);

			
			//2013-03-20: feld zur abwandlung der standard-
			this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_(_DB.FIRMENINFO$VERWERTUNGSVERFAHREN), true, 100), new MyE2_String("Abweichendes Verwertungsverfahren"));
			
			
			//2011-06-06: neues Feld AKONTO
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("AKONTO"),new MyE2_String("Akonto-Lieferungen"),new MyE2_String("Lieferungen nur möglich, wenn eine Fuhre eine verbuchte Rechnung besitzt !")),new MyE2_String("Akonto-Zahlung"));
			((MyE2_DB_CheckBox)this.get_Comp("AKONTO")).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),"AKONTO_DEFINITION"));
			((MyE2_DB_CheckBox)this.get_Comp("AKONTO")).add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
			});

			
			//2012-01-18: schalter: wird nicht gemahnt
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("KEINE_MAHNUNGEN"),new MyE2_String("Wird nicht gemahnt"),new MyE2_String("Firma wird von Mahnungen ausgeschlossen !")),new MyE2_String("Wird nicht gemahnt"));
			((MyE2_DB_CheckBox)this.get_Comp("KEINE_MAHNUNGEN")).add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("KEINE_MAHNUNGEN"));
			((MyE2_DB_CheckBox)this.get_Comp("KEINE_MAHNUNGEN")).add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
			});

			
			
			((MyE2_DB_CheckBox)this.get_Comp("OHNE_STEUER_WARENEINGANG")).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),"DEFINIERE_OHNE_STEUER"));
			((MyE2_DB_CheckBox)this.get_Comp("OHNE_STEUER_WARENEINGANG")).add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
			});
			((MyE2_DB_CheckBox)this.get_Comp("OHNE_STEUER_WARENAUSGANG")).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),"DEFINIERE_OHNE_STEUER"));
			((MyE2_DB_CheckBox)this.get_Comp("OHNE_STEUER_WARENAUSGANG")).add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
			});
			
			
			//2013-12-03: neue felder "PRIVAT" und "FIRMA"
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.FIRMENINFO$PRIVAT)),							new MyE2_String("Private Adresse"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.FIRMENINFO$PRIVAT_MIT_USTID)),				new MyE2_String("Privat trotz UST-ID"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.FIRMENINFO$FIRMA)),							new MyE2_String("Firmen-Adresse"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.FIRMENINFO$FIRMA_OHNE_USTID)),				new MyE2_String("Steuerberaterauskunft (Firma ohne UST-ID)"));
			
			

			((MyE2_DB_CheckBox)this.get_Comp(_DB.FIRMENINFO$PRIVAT)).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),FS_CONST.MASK_FIELD_VALIDATOR_PRIVATADRESSE));
			((MyE2_DB_CheckBox)this.get_Comp(_DB.FIRMENINFO$PRIVAT_MIT_USTID)).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),FS_CONST.MASK_FIELD_VALIDATOR_PRIVAT_MIT_USTID));
			((MyE2_DB_CheckBox)this.get_Comp(_DB.FIRMENINFO$FIRMA)).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),FS_CONST.MASK_FIELD_VALIDATOR_FIRMA));
			((MyE2_DB_CheckBox)this.get_Comp(_DB.FIRMENINFO$FIRMA_OHNE_USTID)).add_GlobalValidator(new E2_ButtonAUTHValidator(oOwnE2_ModulContainerMASK.get_MODUL_IDENTIFIER(),FS_CONST.MASK_FIELD_VALIDATOR_FIRMA_OHNE_USTID));
			
			
			
			//2014-12-11 zusätzlicher interner Kreditrahmen
			this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_(RECORD_FIRMENINFO.FIELD__KREDITBETRAG_INTERN), true, 100), new MyE2_String("Intern gewährter Kreditrahmen, der zusätzlich zu den Beträgen der Kreditversicherungen addiert wird."));
			this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_(RECORD_FIRMENINFO.FIELD__KREDITBETRAG_INTERN_VALID_TO), true, 100), new MyE2_String("Zeitpunkt bis zu dem der intern gewährte Kreditrahmen gültig ist"));

			
			//20171122: weiterer schalter: klassifiziert die kunden / lieferanten ob fbams intern gehalten werden sollen oder verschickt werden
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(FIRMENINFO.fbam_nur_intern.fn())),	new MyE2_String("BAMs zu Fuhren nur intern behandeln"));

			
			//2011-12-02: alter kreditfelder inaktiv schalten
			for (int i=0;i<cFelderInaktiv.length;i++)
			{
				this.get__Comp(cFelderInaktiv[i]).EXT().set_bDisabledFromBasic(true);
			}
			
			
			((MyE2_DB_SelectField)this.get_Comp(_DB.FIRMENINFO$ID_RECHTSFORM)).setWidth(new Extent(100));
			
			
			//2012-08-03: bestimmte feldlaengen definieren
			((MyE2_DB_TextField)this.get_Comp(RECORD_FIRMENINFO.FIELD__LIEFERMENGE_SCHNITT)).set_iWidthPixel(150);
			((MyE2_DB_TextField)this.get_Comp(RECORD_FIRMENINFO.FIELD__LIEFERMENGE_MAX)).set_iWidthPixel(150);
			
			((MyE2_DB_SelectField)this.get_Comp(RECORD_FIRMENINFO.FIELD__ID_BRANCHE)).setWidth(new Extent(150));
			((MyE2_DB_SelectField)this.get_Comp(RECORD_FIRMENINFO.FIELD__ID_EAK_BRANCHE)).setWidth(new Extent(150));
				
			((MyE2_DB_TextField)this.get_Comp(RECORD_FIRMENINFO.FIELD__OEFFNUNGSZEITEN)).set_iWidthPixel(700);
			((MyE2_DB_TextArea)this.get_Comp(RECORD_FIRMENINFO.FIELD__BESCHREIBUNG)).set_iWidthPixel(810);

			((MyE2_DB_TextField)this.get_Comp(FIRMENINFO.debitor_nummer.fn())).set_iWidthPixel(80);
			((MyE2_DB_TextField)this.get_Comp(FIRMENINFO.kreditor_nummer.fn())).set_iWidthPixel(80);

			
			
			//die steuerpruefmethoden der maske uebergeben
//			this.get_hmInteractiv_settings_validation().put_(RECORD_FIRMENINFO.FIELD__VORSTEUERABZUG_RC_INLAND, FS_MASK_ComponentMAP.this.oPruefe_Schalter_Besteuerung);
//			this.get_hmInteractiv_settings_validation().put_(RECORD_FIRMENINFO.FIELD__STEUERNUMMER_STATT_UST, FS_MASK_ComponentMAP.this.oPruefe_Schalter_Besteuerung);
//			this.get_hmInteractiv_settings_validation().put_(RECORD_FIRMENINFO.FIELD__INNERGEMEIN_LIEF_EU, FS_MASK_ComponentMAP.this.oPruefe_Schalter_Besteuerung);
			
			
		}
		
		
		

		private class EAK_BrancheDDSettings extends E2_DropDownSettings
		{

			public EAK_BrancheDDSettings() throws myException
			{
				super("SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '||SUBSTR(NVL(JT_EAK_BRANCHE.BRANCHE,'-'),1,40), JT_EAK_BRANCHE.ID_EAK_BRANCHE "+
						" FROM " +
						bibE2.cTO()+".JT_EAK_BRANCHE "+
						" ORDER BY JT_EAK_BRANCHE.KEY_BRANCHE",true);
				
				// jetzt die sprache (gegebenenfalls manipulieren)
				String cIDSprache = bibALL.get_ID_SPRACHE_USER();
				
				String cSQL = "SELECT JT_EAK_BRANCHE.ID_EAK_BRANCHE,JT_EAK_BRANCHE.KEY_BRANCHE||' - '||SUBSTR(JT_EAK_BRANCHE_SP.BRANCHE_UEBERSETZUNG,1,40) FROM "+bibE2.cTO()+".JT_EAK_BRANCHE_SP,"+
										bibE2.cTO()+".JT_EAK_BRANCHE WHERE " +
												" JT_EAK_BRANCHE_SP.ID_EAK_BRANCHE=JT_EAK_BRANCHE.ID_EAK_BRANCHE AND " +
												" JT_EAK_BRANCHE_SP.BRANCHE_UEBERSETZUNG IS NOT NULL AND "+
												" ID_SPRACHE="+cIDSprache;

				MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL,true);
				
				String[][] cDropDownWert = this.getDD();
				for (int i=0;i<cDropDownWert.length;i++)
				{
					String cNeuerWert = oDMI.get_Result_without_Exception(cDropDownWert[i][1], 1)+" "+ oDMI.get_Result_without_Exception(cDropDownWert[i][1], 0) ;
					if (!bibALL.isEmpty(cNeuerWert))
						cDropDownWert[i][0]=cNeuerWert;
				}
			}
		}
		
	}
	
	
	
	private class SubQueryAgentToDisplayInfoLine extends XX_ComponentMAP_SubqueryAGENT
	{

		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException
		{
			SpecialRowForInfo oInfoRow = this.find_SpecialRow();
			oInfoRow.removeAll();
			oInfoRow.add(new MyE2_Label(new MyE2_String("Neueingabe einer Adresse ..."), MyE2_Label.STYLE_SMALL_ITALIC()),E2_INSETS.I_0_2_20_2);
		}

		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException
		{
			SpecialRowForInfo oInfoRow = this.find_SpecialRow();
			oInfoRow.removeAll();
			
			boolean bActive = oUsedResultMAP.get_booleanActualValue(_DB.ADRESSE$AKTIV);
			
			oInfoRow.add(new MyE2_Label(new MyE2_String("Aktuelle Adresse: "), MyE2_Label.STYLE_SMALL_BOLD()),E2_INSETS.I_0_2_20_2);
			if (bActive) {
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("ID_ADRESSE"), MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("NAME1"), MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("NAME2"), MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("STRASSE"), MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("PLZ"), MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("ORT"), MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_2_20_2);
			} else {
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("ID_ADRESSE"), MyE2_Label.STYLE_NORMAL_BOLD_GREY()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("NAME1"), MyE2_Label.STYLE_NORMAL_BOLD_GREY()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("NAME2"), MyE2_Label.STYLE_NORMAL_BOLD_GREY()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("STRASSE"), MyE2_Label.STYLE_NORMAL_BOLD_GREY()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("PLZ"), MyE2_Label.STYLE_NORMAL_BOLD_GREY()),E2_INSETS.I_0_2_20_2);
				oInfoRow.add(new MyE2_Label(oUsedResultMAP.get_FormatedValue("ORT"), MyE2_Label.STYLE_NORMAL_BOLD_GREY()),E2_INSETS.I_0_2_20_2);
			}
		}
	
		
		
		private SpecialRowForInfo find_SpecialRow()
		{
			E2_BasicModuleContainer_MASK oMask = FS_MASK_ComponentMAP.this.get_oModulContainerMASK_This_BelongsTo();
			MyE2_Row  row4Buttons = oMask.get_oRowForHeadline();
			row4Buttons.setVisible(true);
			
			SpecialRowForInfo rowRueck = null;
			
			Component[] Comps = row4Buttons.getComponents();
			for (int i=0;i<Comps.length;i++)
			{
				if (Comps[i] instanceof SpecialRowForInfo)
				{
					rowRueck = (SpecialRowForInfo)Comps[i];
				}
			}

			if (rowRueck == null)
			{
				rowRueck = new SpecialRowForInfo();
				row4Buttons.add(rowRueck, E2_INSETS.I_20_5_10_5);
			}
			return rowRueck;
		}
	}
	
	
	
	private class SpecialRowForInfo extends MyE2_Row
	{

		public SpecialRowForInfo()
		{
			super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		}
		
	}
	
	
	
	//ausgelagerte methode, um die validierer zu setzen (bessere uebersicht)
	private void setze_XX_MAP_Set_And_Valid_objects(E2_ComponentMAP oMAP_Adresse, E2_ComponentMAP oMAP_Firmeninfo) throws myException {
		
		//die steuerpruefmethoden der maske uebergeben
		oMAP_Adresse.register_Interactiv_settings_validation(_DB.ADRESSE$ID_LAND, FS_MASK_ComponentMAP.this.oVALID_PRIVAT_GESCHAEFTSKUNDEN);
		oMAP_Adresse.register_Interactiv_settings_validation(_DB.ADRESSE$ID_LAND, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		oMAP_Adresse.register_Interactiv_settings_validation(_DB.ADRESSE$AKTIV, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		oMAP_Adresse.register_Interactiv_settings_validation(_DB.ADRESSE$WARENEINGANG_SPERREN, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		oMAP_Adresse.register_Interactiv_settings_validation(_DB.ADRESSE$WARENAUSGANG_SPERREN, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		
		//2013-11-15: die neuen pruefmethoden fuer die maskenschalter privat / steuerberaterauskunft uebergeben
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$PRIVAT, FS_MASK_ComponentMAP.this.oVALID_PRIVAT_GESCHAEFTSKUNDEN);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$PRIVAT, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$FIRMA, FS_MASK_ComponentMAP.this.oVALID_PRIVAT_GESCHAEFTSKUNDEN);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$FIRMA, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$FIRMA_OHNE_USTID, FS_MASK_ComponentMAP.this.oVALID_PRIVAT_GESCHAEFTSKUNDEN);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$FIRMA_OHNE_USTID, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$PRIVAT_MIT_USTID, FS_MASK_ComponentMAP.this.oVALID_PRIVAT_GESCHAEFTSKUNDEN);
		oMAP_Firmeninfo.register_Interactiv_settings_validation(_DB.FIRMENINFO$PRIVAT_MIT_USTID, FS_MASK_ComponentMAP.this.oSETTING_STEUER_INDIKATOR);
		
		//2018-05-28: weitere waehrungen validieren (auf gleichheit zwischen haupt- und zusatzwaehrungen
		oMAP_Adresse.register_Interactiv_settings_validation(ADRESSE.id_waehrung.fn(), new FS_ValidZusatzWaehrungen());
	}



	/*
	 * 2018-07-20: implemetierung der pruefung auf sanktionen innerhalb jedes Adress-Speichervorgangs
	 */
	@Override
	public MyE2_MessageVector checkSaveing(E2_SaveMASK saver, ENUM_SAVEMASKCONTROLLERS_POS pos) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		new FS_CheckAdresseAfterSaving()._init(saver, pos, mv);
		return mv;
	}


	
	

	

	
	

}
