package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;


import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceSetTabSequence;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EN_FS_Fields;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_EMAIL;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_TELEFON;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_BT_EXPORT_EAK_LIST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_BT_IMPORT_EAK_LIST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Component_MASK_DAUGHTER_EAK_CODES;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtGeoCodingAdresse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtShowAdresseOSM;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.E2_SaveMaskSanktionsController;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.FS_CheckAdresseAfterSaving;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FSL_MASK_ComponentMAP extends E2_ComponentMAP implements E2_SaveMaskSanktionsController
{
	private String cSTD_FIELDS1 = "NAME1|NAME2|NAME3|STRASSE|HAUSNUMMER|PLZ|ORT|ORTZUSATZ|BEMERKUNGEN|BEMERKUNG_FAHRPLAN|";
	private String cNameList ="Name 1|Name 2|Name 3|Strasse|Hausnummer|PLZ|Ort|Ortszusatz|Bemerkungen|Bemerkung Fahrplan|";
	
	// aenderung AVV-Infofelder
	// 2014-08-01: auch bei den Lieferadressen die Vertragsdaten und vertragsbemerkungen
//	private String cFieldAVV = "EU_BEIBLATT_EMAIL|EU_BEIBLATT_ANSPRECH|EU_BEIBLATT_TEL|EU_BEIBLATT_FAX";
//	private String cBeschAVV = "EU-Beiblatt-e-Mail|EU-Beiblatt-Ansprechpartner|EU-Beiblatt-Telefon|EU-Beiblatt-Fax";
	private String cFieldAVV = "EU_BEIBLATT_EMAIL|EU_BEIBLATT_ANSPRECH|EU_BEIBLATT_TEL|EU_BEIBLATT_FAX|EU_BEIBLATT_EK_VERTRAG|EU_BEIBLATT_VK_VERTRAG";
	private String cBeschAVV = "EU-Beiblatt-e-Mail|EU-Beiblatt-Ansprechpartner|EU-Beiblatt-Telefon|EU-Beiblatt-Fax|EU-Beiblatt-EK-Vertrag|EU-Beiblatt-VK-Vertrag";

	
	private E2_ComponentMAP 		oComponentMAP_Lieferadresse = null;
	
	
	public FSL_MASK_ComponentMAP() throws myException
	{
		super(new FSL_MASK_SQLFieldMap_ADRESSE());
		
		FSL_MASK_SQLFieldMap_ADRESSE oSQLFieldMAP_AdressenTeil = (FSL_MASK_SQLFieldMap_ADRESSE)this.get_oSQLFieldMAP();
		
		
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERNAME", "ID_LAND", "ISTSTANDARD", true);

		/*
		 * defaults der dropdowns setzen
		 */
		oSQLFieldMAP_AdressenTeil.get_("ID_LAND").set_cDefaultValueFormated(ddLand.getDefault());
		/*
		 *  fertig
		 */
		
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP_AdressenTeil.get_("ID_ADRESSE")),new MyE2_String("ID-Adresse"));
		MaskComponentsFAB.addStandardComponentsToMAP(cSTD_FIELDS1,cNameList,this.get_oSQLFieldMAP(),false,false,this,400);
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP_AdressenTeil.get_("ID_LAND"),ddLand.getDD(),false, new Extent(120)),new MyE2_String("Land"));

		
//		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP_AdressenTeil.get_("AKTIV"),oSQLFieldMAP_AdressenTeil,bibE2.get_YN_Ary_WithLeer(),false),new MyE2_String("Aktiv"));
	
		//NEU_09
		MyE2_DB_CheckBox oCB = new MyE2_DB_CheckBox(oSQLFieldMAP_AdressenTeil.get_("AKTIV"));									//NEU_09
		oCB.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"AKTIVIEREN_LIEFER_ADRESSE"));
		oCB.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
		});
		this.add_Component(oCB,new MyE2_String("Aktiv"));       													//NEU_09
		//NEU_09

		//20170926: schalter aktiv
		this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP_AdressenTeil.get_(ADRESSE.ah7_quelle_sicher), 	
												new MyE2_String("Sichere Anhang7-Quelle: Lieferungen von dieser Station sind Anhang7-unkritisch, was Offenlegung des Warenwegs angeht.")
												),new MyE2_String("Sichere Anhang7-Quelle"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP_AdressenTeil.get_(ADRESSE.ah7_ziel_sicher), 	
												new MyE2_String("Sicheres Anhang7-Ziel: Lieferungen zu dieser Station sind Anhang7-unkritisch, was Offenlegung des Warenwegs angeht.")
												),new MyE2_String("Sicheres Anhang7-Ziel"));
		//20170926

		
		// aenderung AVV
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldAVV,cBeschAVV,this.get_oSQLFieldMAP(),false,false,this,400);

		
		//NEU_09 - email fuer lieferadressen
		/*
		 * komplexe feldelemente
		 */
		FS_Component_MASK_DAUGHTER_TELEFON oTelField = new FS_Component_MASK_DAUGHTER_TELEFON(oSQLFieldMAP_AdressenTeil,this);
		FS_Component_MASK_DAUGHTER_EMAIL 	oMailField = new FS_Component_MASK_DAUGHTER_EMAIL(oSQLFieldMAP_AdressenTeil,this, true, false);
		((MyE2_DB_TextField)oMailField.get_oOwnComponentMAP_ForList().get__Comp("EMAIL")).set_iWidthPixel(250);
		((MyE2_DB_TextField)oMailField.get_oOwnComponentMAP_ForList().get__Comp("BESCHREIBUNG")).set_iWidthPixel(250);
		
		((MyE2_DB_SelectField)oMailField.get_oOwnComponentMAP_ForList().get__Comp("TYPE")).setWidth(new Extent(100));
		
		oTelField.set_oContainerExScrollHeight(new Extent(150));
		oMailField.set_oContainerExScrollHeight(new Extent(150));
		
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_TELEFON_LIEF,oTelField,new MyE2_String("Kommunikationsangaben"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_EMAIL_LIEF,oMailField,new MyE2_String("E-Mail-Adressen"));
		//NEU_09 -- Ende eMail Lieferadressen
		

		//2012-03-05: neue Felder: ID_USER_LAGER_HAENDLER, ID_USER_LAGER_SACHBEARB, LAGER_KONTROLLINFO
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP_AdressenTeil.get_(RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB), true, 300), new MyE2_String("Sachbearbeiter für das Lager"));
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFieldMAP_AdressenTeil.get_(RECORD_ADRESSE.FIELD__ID_USER_LAGER_HAENDLER), true, 300), new MyE2_String("Händler für das Lager"));
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP_AdressenTeil.get_(RECORD_ADRESSE.FIELD__LAGER_KONTROLLINFO), true, 600), new MyE2_String("Lager-Kontroll-Information"));

		//2014-01-16: neues feld
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP_AdressenTeil.get_(_DB.ADRESSE$LAGER_ZUSTAENDIG_EXTERN), true, 600), new MyE2_String("Lager-Zuständigkeit vor Ort"));

		//2013-06-03: zusatz-tabelle: zertifizierte avv-codes fuer die adresse
		__FS_Component_MASK_DAUGHTER_EAK_CODES oDaughterAVV = new __FS_Component_MASK_DAUGHTER_EAK_CODES(oSQLFieldMAP_AdressenTeil,this);
		this.add_Component(FSL__CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_LA,
				oDaughterAVV,new MyE2_String("Erlaubte AVV-Codes bei dieser Adresse"));
		this.add_Component(FSL__CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_EXPORT_LA,
				new __FS_BT_EXPORT_EAK_LIST(oDaughterAVV),new MyE2_String("Exportiere AVV-ID-Liste"));
		this.add_Component(FSL__CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_IMPORT_LA,
				new __FS_BT_IMPORT_EAK_LIST(oDaughterAVV),new MyE2_String("Importiere AVV-ID-Liste"));

		
//		//2013-11-19 Transportkosten
//		FS_Daughter_Kosten_Lieferbed 		oDaughterTransportkosten = new FS_Daughter_Kosten_Lieferbed(oSQLFieldMAP_AdressenTeil,this);
//		this.add_Component(FSL__CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN,	oDaughterTransportkosten,new MyE2_String("Transportkosten dieser Lieferadresse"));
		
		//2014-08-01: neues infofeld fuer die AVV-Kontrakte
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP_AdressenTeil.get_(RECORD_ADRESSE.FIELD__EU_BEIBLATT_INFOFELD), true, 700), new MyE2_String("Infos zu den EU-Verträgen"));

		//2014-08-01: feldlaengen der hauptadresse anpassen
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_TEL)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_FAX)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_ANSPRECH)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_EMAIL)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_EK_VERTRAG)).set_iWidthPixel(200);
		((MyE2_DB_TextField)this.get_Comp(RECORD_ADRESSE.FIELD__EU_BEIBLATT_VK_VERTRAG)).set_iWidthPixel(200);

		((MyE2_DB_TextField)this.get__Comp(ADRESSE.hausnummer)).set_iWidthPixel(30);

		
		
		//2018-01-29: gps-koordinaten
		MyE2_DB_TextField tfLongitude= new MyE2_DB_TextField(oSQLFieldMAP_AdressenTeil.get_(ADRESSE.longitude.fn()),	true,	100);
		MyE2_DB_TextField tfLatitude= new MyE2_DB_TextField(oSQLFieldMAP_AdressenTeil.get_(ADRESSE.latitude.fn()),	true, 	100);
		this.add_Component(tfLongitude, new MyE2_String("Längengrad"));
		this.add_Component(tfLatitude, new MyE2_String("Breitengrad"));
		this.add_Component(EN_FS_Fields.GPS_BUTTON_SEARCH.name(), new FS_MaskBtGeoCodingAdresse(tfLongitude,tfLatitude), new MyE2_String("Geocodieren der Adresse ..."));
//		this.add_Component(EN_FS_Fields.GPS_BUTTON_VIEW_IN_MAP.name(), new FS_MaskBtShowAdresseandLagerGeopointsOSM(), new MyE2_String("Zeige auf Karte ..."));
		this.add_Component(EN_FS_Fields.GPS_BUTTON_VIEW_IN_MAP.name(), new FS_MaskBtShowAdresseOSM(), new MyE2_String("Zeige auf Karte ..."));

		
		
		// einige einstellungen
		((MyE2_DB_TextArea)this.get_Comp("BEMERKUNGEN")).set_iWidthPixel(700);

		this.add_Component(new MyE2_DB_TextArea(oSQLFieldMAP_AdressenTeil.get_("LIEFERINFO_TPA")),new MyE2_String("Lieferinfo TPA"));
		((MyE2_DB_TextArea)this.get__Comp("LIEFERINFO_TPA")).set_iWidthPixel(400);
		((MyE2_DB_TextArea)this.get__Comp("LIEFERINFO_TPA")).set_iRows(4);

		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_FAHRPLAN")).set_iWidthPixel(400);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_FAHRPLAN")).set_iRows(4);

		
		this.oComponentMAP_Lieferadresse = new FS_ComponentMAP_MASK_LIEFERADRESSE(oSQLFieldMAP_AdressenTeil);
		
		this._setWidth(350, ADRESSE.id_land, ADRESSE.plz, ADRESSE.id_user_lager_sachbearb, ADRESSE.id_user_lager_haendler);
		
		new PdServiceSetTabSequence()._addMap(this)._addMap(this.oComponentMAP_Lieferadresse)
				._setSeq(1
						,ADRESSE.name1
						,ADRESSE.name2
						,ADRESSE.name3
						,ADRESSE.strasse
						,ADRESSE.hausnummer
						,ADRESSE.id_land
						,ADRESSE.plz
						,ADRESSE.ort
						,ADRESSE.ortzusatz
						,LIEFERADRESSE.beschreibung
						,LIEFERADRESSE.oeffnungszeiten
						,ADRESSE.ah7_quelle_sicher
						,ADRESSE.ah7_ziel_sicher
						,LIEFERADRESSE.registerfuehrung
						,LIEFERADRESSE.hauptadresse_ersetzt_lager
						,LIEFERADRESSE.ist_standard
						,ADRESSE.id_user_lager_sachbearb
						,ADRESSE.id_user_lager_haendler
						,ADRESSE.lager_zustaendig_extern
						,ADRESSE.lager_kontrollinfo
						,LIEFERADRESSE.id_adresse_besitz_lager
						);
	}

	
	
	public E2_ComponentMAP get_E2_ComponentMAP_Lieferadresse()
	{
		return this.oComponentMAP_Lieferadresse;
	}
	
	
	
	
	public class FS_ComponentMAP_MASK_LIEFERADRESSE extends E2_ComponentMAP
	{
		
		public FS_ComponentMAP_MASK_LIEFERADRESSE(FSL_MASK_SQLFieldMap_ADRESSE sqlfieldMAP_Adresse) throws myException
		{
			super(new FSL_MASK_SQLFieldMap_LIEFERADRESSE(sqlfieldMAP_Adresse));
			
			FSL_MASK_SQLFieldMap_LIEFERADRESSE oSQLFieldMAP = (FSL_MASK_SQLFieldMap_LIEFERADRESSE)this.get_oSQLFieldMAP();

			this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_LIEFERADRESSE")),new MyE2_String("ID-Lieferadresse"));
			this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_ADRESSE_LIEFER")),new MyE2_String("ID-Adresse (Lieferort)"));
			this.add_Component(new MyE2_DB_TextArea(oSQLFieldMAP.get_("BESCHREIBUNG"),600,5),new MyE2_String("Beschreibung"));
			this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_("OEFFNUNGSZEITEN"),true,600),new MyE2_String("Öffnungszeiten"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("IST_STANDARD")),new MyE2_String("Standard ?"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("REGISTERFUEHRUNG")),new MyE2_String("Register ?"));
			
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(LIEFERADRESSE.hauptadresse_ersetzt_lager)),new MyE2_String("Hauptadresse in Formularen andrucken ?"));
			
			
			MyE2_Label lblEigentuemerWare = new MyE2_Label(new MyE2_String("Eigentümer (Fremdware):").CTrans());
			lblEigentuemerWare.setLineWrap(true);
			lblEigentuemerWare.setToolTipText(new MyString("Eigentümer der Ware im juristischen Sinn. Benutzung für Fremdwaren-Transporte (Quelle oder Ziel)").CTrans());
			this.add_Component(FSL__CONST.LABEL_ID_ADRESSE_FREMDWARE,lblEigentuemerWare, new MyE2_String("Eigentümer Fremdware"));
			
			MyE2_Label lblBesitzerWare = new MyE2_Label(new MyE2_String("Besitzer (Ware):").CTrans());
			lblBesitzerWare.setLineWrap(true);
			lblBesitzerWare.setToolTipText(new MyString("Besitzer der Ware auf dem Lager im juristischen Sinn").CTrans());
			this.add_Component(FSL__CONST.LABEL_ID_ADRESSE_BESITZER_WARE,lblBesitzerWare, new MyE2_String("Besitzer Ware"));

			MyE2_Label lblBesitzerWareLager = new MyE2_Label(new MyE2_String("...dazu gehörendes Lager:").CTrans());
			lblBesitzerWareLager.setLineWrap(true);
			lblBesitzerWareLager.setToolTipText(new MyString("Besitzer der Ware auf dem Lager im juristischen Sinn/Lager dieses Besitzers").CTrans());
			this.add_Component(FSL__CONST.LABEL_ID_ADRESSE_BESITZER_WARE_LAGER,lblBesitzerWareLager, new MyE2_String("Lager/Besitzer (Ware)"));

			
			MyE2_Label lblBesitzerLagerGrundstueck = new MyE2_Label(new MyE2_String("Besitzer (Grundstück - für AH7):").CTrans());
			lblBesitzerLagerGrundstueck.setLineWrap(true);
			lblBesitzerLagerGrundstueck.setToolTipText(new MyString("Besitzer des Grundstücks, auf dem das Lager angelegt ist. Für die Definition, ob ein Lager im AH7 als vertrauenswürdig angesehen wird.").CTrans());
			this.add_Component(FSL__CONST.LABEL_ID_ADRESSE_BESITZ_LAGER,lblBesitzerLagerGrundstueck, new MyE2_String("Besitzer Grundstück"));

			
			
			//2020-10-05: neues suchfeld
//			DB_SEARCH_Adress oSearch = new DB_SEARCH_Adress(oSQLFieldMAP.get_("ID_ADRESSE_FREMDWARE"),true);
//			oSearch.get_oTextForAnzeige().setFont(new E2_FontPlain());
//			oSearch.get_oTextForAnzeige().setWidth(new Extent(450));
			
			//Eigentuemer der Ware im juristischen sinn (analogie: Vermieter = Eigentümer der Wohnung)
			FSL_MASK_SearchAdresse_JuristischEigentuemerWare searchEigentuemer = new FSL_MASK_SearchAdresse_JuristischEigentuemerWare(oSQLFieldMAP.get_(LIEFERADRESSE.id_adresse_fremdware));
			this.add_Component(searchEigentuemer, new MyE2_String("Eigentümer Fremdware"));
			
			//Besitzer der Ware im juristischen sinn (analogie: Mieter = Besitzer der Wohnung)
			FSL_MASK_SearchAdresse_JuristischBesitzerWare searchBesitzer = new FSL_MASK_SearchAdresse_JuristischBesitzerWare(oSQLFieldMAP.get_(LIEFERADRESSE.id_adresse_besitzer_ware));
			this.add_Component(searchBesitzer, new MyE2_String("Besitzer Fremdware"));
			//hier ein lager des besitzers
			FSL_MASK_SearchAdresse_JuristischBesitzerWareLager searchBesitzerLager = new FSL_MASK_SearchAdresse_JuristischBesitzerWareLager(oSQLFieldMAP.get_(LIEFERADRESSE.id_adresse_besitzer_ware_lager));
			this.add_Component(searchBesitzerLager, new MyE2_String("Besitzer Fremdware (Lager)"));
			
			searchBesitzer._setReferenzAdresseLager(searchBesitzerLager);
			searchBesitzerLager._setReferenzAdresse(searchBesitzer);
			
			
			//20180323: suche nach grundbuch-adresse
			FSL_MASK_SearchAdresse_BesitzGrundstueck searchAdresseBesitzerLagerGrundstueck = 	new FSL_MASK_SearchAdresse_BesitzGrundstueck(oSQLFieldMAP.get_(LIEFERADRESSE.id_adresse_besitz_lager));
			this.add_Component(searchAdresseBesitzerLagerGrundstueck,new MyE2_String("Grundstücksbesitzer des Lagers"));
			
			
			// mapsetting-Agent
			this.set_oMAPSettingAgent(new Lieferadresse_MapSettingAgent());
			
		}
		
		
		
		private class Lieferadresse_MapSettingAgent extends XX_MAP_SettingAgent{

			@Override
			public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException {

				// die zugrundeliegende Adress-ID ermitteln
				String sBaseAdressID = bibALL.null2leer(((FSL_ModulContainer_MASK) oMap.get_oModulContainerMASK_This_BelongsTo()).get_BASE_ADRESS_ID());
				
				
				// FELDER verstecken, wenn es nicht der Mandant ist
				oMap.get__Comp(LIEFERADRESSE.id_adresse_fremdware.fn()).EXT().set_bDisabledFromInteractive(!sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__CompEcho(LIEFERADRESSE.id_adresse_fremdware).setVisible(sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_FREMDWARE).setVisible(sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				
				oMap.get__Comp(LIEFERADRESSE.id_adresse_besitzer_ware.fn()).EXT().set_bDisabledFromInteractive(!sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__CompEcho(LIEFERADRESSE.id_adresse_besitzer_ware).setVisible(sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_BESITZER_WARE).setVisible(sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));

				oMap.get__Comp(LIEFERADRESSE.id_adresse_besitzer_ware_lager.fn()).EXT().set_bDisabledFromInteractive(!sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__CompEcho(LIEFERADRESSE.id_adresse_besitzer_ware_lager).setVisible(sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_BESITZER_WARE_LAGER).setVisible(sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));

				
				//2016-05-06: die felder REGISTERFUEHRUNG und HAUPTADRESSE_ERSETZT_LAGER ebernfalls nur anzeigen, wenn die adresse der mandant ist
				oMap.get__Comp(LIEFERADRESSE.registerfuehrung.fn()).EXT().set_bDisabledFromInteractive(!sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				oMap.get__Comp(LIEFERADRESSE.hauptadresse_ersetzt_lager.fn()).EXT().set_bDisabledFromInteractive(!sBaseAdressID.equals(bibALL.get_ID_ADRESS_MANDANT()));
				
			}

			@Override
			public void __doSettings_AFTER(E2_ComponentMAP oMap,
					String STATUS_MASKE) throws myException {
				
			}
			
		}
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
