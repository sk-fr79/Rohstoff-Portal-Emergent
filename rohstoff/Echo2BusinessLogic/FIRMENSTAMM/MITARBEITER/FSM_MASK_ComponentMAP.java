package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EN_FS_Fields;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_EMAIL;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_Component_MASK_DAUGHTER_TELEFON;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtGeoCodingAdresse;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskBtShowAdresseOSM;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.E2_SaveMaskSanktionsController;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.FS_CheckAdresseAfterSaving;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class FSM_MASK_ComponentMAP extends E2_ComponentMAP implements E2_SaveMaskSanktionsController
{
	private String cSTD_FIELDS1 = 	"VORNAME|NAME1|NAME2|NAME3|STRASSE|ORT|ORTZUSATZ|BEMERKUNGEN|";
	private String cNameList1 = 	"Vorname|Name 1|Name 2|Name 3|Strasse|Ort|Ortszusatz|Bemerkungen|";
	private String cSTD_FIELDS2 = 	"HAUSNUMMER|PLZ";
	private String cNameList2 =		"Hausnummer|PLZ";
	
	
	private E2_ComponentMAP 		  oComponentMAP_Mitarbeiter = null;
	
	//private FSM_ModulContainer_MASK   oFSM_Mask_Container = null;
	
	public FSM_MASK_ComponentMAP(FSM_ModulContainer_MASK FSM_Mask_Container) throws myException
	{
		super(new FSM_MASK_SQLFieldMap_ADRESSE());
		
		//this.oFSM_Mask_Container = FSM_Mask_Container;
		
		FSM_MASK_SQLFieldMap_ADRESSE oSQLFieldMAP = (FSM_MASK_SQLFieldMap_ADRESSE)this.get_oSQLFieldMAP();
		
		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_ADRESSE")),new MyE2_String("ID-Adresse"));

		MaskComponentsFAB.addStandardComponentsToMAP(cSTD_FIELDS1,cNameList1,this.get_oSQLFieldMAP(),false,false,this,400);
		MaskComponentsFAB.addStandardComponentsToMAP(cSTD_FIELDS2,cNameList2,this.get_oSQLFieldMAP(),false,false,this,80);
		
		E2_DropDownSettings ddSprache = new E2_DropDownSettings( "JD_SPRACHE", "BEZEICHNUNG", "ID_SPRACHE", "ISTSTANDARD", true);
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERNAME", "ID_LAND", "ISTSTANDARD", true);
		E2_DropDownSettings ddAnrede = new E2_DropDownSettings("JT_ANREDE", "ANREDE", "ID_ANREDE", null, true);

		/*
		 * defaults der dropdowns setzen
		 */
		oSQLFieldMAP.get_("ID_SPRACHE").set_cDefaultValueFormated(ddSprache.getDefault());
		oSQLFieldMAP.get_("ID_LAND").set_cDefaultValueFormated(ddLand.getDefault());
		oSQLFieldMAP.get_("ID_ANREDE").set_cDefaultValueFormated(ddAnrede.getDefault());
		/*
		 *  fertig
		 */
		
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_SPRACHE"),ddSprache.getDD(),false),new MyE2_String("Sprache"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_LAND"),ddLand.getDD(),false),new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_ANREDE"),ddAnrede.getDD(),false),new MyE2_String("Anrede"));
		
		((MyE2_DB_SelectField)this.get__Comp("ID_LAND")).setWidth(new Extent(80));
		
		//NEU_09
		MyE2_DB_CheckBox oCB = new MyE2_DB_CheckBox(oSQLFieldMAP.get_("AKTIV"));									//NEU_09
		oCB.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,"AKTIVIEREN_MITARBEITER"));
		oCB.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
		});
		this.add_Component(oCB,new MyE2_String("Aktiv"));       													//NEU_09
		//NEU_09
	
		//this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("AKTIV"),oSQLFieldMAP,bibE2.get_YN_Ary_WithLeer(),false),new MyE2_String("Aktiv"));
		
		/*
		 * komplexe feldelemente
		 */
		FS_Component_MASK_DAUGHTER_TELEFON oTelField = new FS_Component_MASK_DAUGHTER_TELEFON(oSQLFieldMAP,this);
		FS_Component_MASK_DAUGHTER_EMAIL 	oMailField = new FS_Component_MASK_DAUGHTER_EMAIL(oSQLFieldMAP,this, true, false);
		oTelField.set_oContainerExScrollHeight(new Extent(150));
		oMailField.set_oContainerExScrollHeight(new Extent(150));
		
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_TELEFON,oTelField,new MyE2_String("Kommunikationsangaben"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_EMAIL,oMailField,new MyE2_String("E-Mail-Adressen"));
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_INFOS,new FSM_MASK_Component_DAUGHTER_ZUSATZINFOS(oSQLFieldMAP,this),new MyE2_String("E-Mail-Adressen"));
		
		//NEU_09   --  geschenkliste
		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_GESCHENKE,new FSM_MASK_Component_DAUGHTER_GESCHENKE(oSQLFieldMAP,this),new MyE2_String("Geschenke"));
		
		
		//2018-01-29: gps-koordinaten
		MyE2_DB_TextField tfLongitude= new MyE2_DB_TextField(oSQLFieldMAP.get_(ADRESSE.longitude.fn()),	true,	100);
		MyE2_DB_TextField tfLatitude= new MyE2_DB_TextField(oSQLFieldMAP.get_(ADRESSE.latitude.fn()),	true, 	100);
		this.add_Component(tfLongitude, new MyE2_String("Längengrad"));
		this.add_Component(tfLatitude, new MyE2_String("Breitengrad"));
		
		this.add_Component(EN_FS_Fields.GPS_BUTTON_SEARCH.name(), new FS_MaskBtGeoCodingAdresse(tfLongitude,tfLatitude), new MyE2_String("Geocodieren der Adresse ..."));
		this.add_Component(EN_FS_Fields.GPS_BUTTON_VIEW_OSM_IN_MAP.name(), new FS_MaskBtShowAdresseOSM(), new MyE2_String("Zeige auf Karte ..."));
	
		
//		//test qualifier
//		this.add_Component(FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_QUALIFIER,new FSM_MASK_LIST_Qualifier(oSQLFieldMAP.get_("ID_ADRESSE"),false),new MyE2_String("Qualifizierer"));
		
		
		// einige einstellungen
		((MyE2_DB_TextArea)this.get_Comp("BEMERKUNGEN")).set_iWidthPixel(700);

		
		this.add_oSubQueryAgent(new ownSubQueryAgent());
		
		this.oComponentMAP_Mitarbeiter = new FS_ComponentMAP_MASK_MITARBEITER((FSM_MASK_SQLFieldMap_ADRESSE)this.get_oSQLFieldMAP());
		
	}
 	
	
	
	private class ownSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {
			FSM_ModulContainer_MASK oMaskContainer = (FSM_ModulContainer_MASK)oMAP.get_oModulContainerMASK_This_BelongsTo();
			
			if (S.isFull(oMaskContainer.get_cID_ADRESSE_BASIS_UF())) {
				RECORD_ADRESSE_extend recAdresse = new RECORD_ADRESSE_extend(new  RECORD_ADRESSE(oMaskContainer.get_cID_ADRESSE_BASIS_UF()));
				
				MyE2_Grid oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
				
				oGrid.add(new MyE2_Label(new MyE2_String("Erfassen eines neuen Mitarbeiters zur Firma: "),new E2_FontItalic(-2)),E2_INSETS.I(1,1,10,1));
				oGrid.add(new MyE2_Label(recAdresse.get_Signatur_String_kurz(),new E2_FontBoldItalic(-2)),E2_INSETS.I(1,1,10,1));
				
				oMaskContainer.add_Headline(oGrid);
				
			}
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException {
			
			FSM_ModulContainer_MASK oMaskContainer = (FSM_ModulContainer_MASK)oMAP.get_oModulContainerMASK_This_BelongsTo();
			
			if (S.isFull(oMaskContainer.get_cID_ADRESSE_BASIS_UF())) {
				RECORD_ADRESSE_extend recAdresse = new RECORD_ADRESSE_extend(new  RECORD_ADRESSE(oMaskContainer.get_cID_ADRESSE_BASIS_UF()));
				
				MyE2_Grid oGrid = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
				
				MyE2_Label oLabFirma1 = new MyE2_Label(new MyE2_String("Firma: "),new E2_FontItalic(-2));
				MyE2_Label oLabFirma2 = new MyE2_Label(recAdresse.get_Signatur_String_kurz(),new E2_FontBoldItalic(-2));
				oLabFirma1.setForeground(recAdresse.is_AKTIV_YES()?Color.BLACK:Color.DARKGRAY);
				oLabFirma2.setForeground(recAdresse.is_AKTIV_YES()?Color.BLACK:Color.DARKGRAY);
				oGrid.add(oLabFirma1,E2_INSETS.I(1,1,10,1));
				oGrid.add(oLabFirma2,E2_INSETS.I(1,1,10,1));
				
				
				boolean bAktiv = 		oMAP.get_bActualDBValue(_DB.ADRESSE$AKTIV);
				String cHelpVorname = 	oMAP.get_cActualDBValueFormated(_DB.ADRESSE$VORNAME);
				String cHelpName1 = 	oMAP.get_cActualDBValueFormated(_DB.ADRESSE$NAME1);
				String cName = "";
				if (S.isFull(cHelpVorname)) { cName  += cHelpVorname; }
				if (S.isFull(cHelpName1)) 	{ cName  += (" "+cHelpName1); }

				MyE2_Label oLabMitarbeiter1 = new MyE2_Label(new MyE2_String("Mitarbeiter:"),new E2_FontItalic(-2));
				MyE2_Label oLabMitarbeiter2 = new MyE2_Label(new MyE2_String(cName.trim(),false),new E2_FontBoldItalic(-2));
				oLabMitarbeiter1.setForeground(bAktiv?Color.BLACK:Color.DARKGRAY);
				oLabMitarbeiter2.setForeground(bAktiv?Color.BLACK:Color.DARKGRAY);
				
				oGrid.add(oLabMitarbeiter1,E2_INSETS.I(1,1,10,1));
				oGrid.add(oLabMitarbeiter2,E2_INSETS.I(1,1,10,1));
				oMaskContainer.add_Headline(oGrid);
			}
		}
		
	}
	
	
	
	
	public E2_ComponentMAP get_E2_ComponentMAP_Mitarbeiter()
	{
		return this.oComponentMAP_Mitarbeiter;
	}
	
	
	
	
	public class FS_ComponentMAP_MASK_MITARBEITER extends E2_ComponentMAP
	{
		
		public FS_ComponentMAP_MASK_MITARBEITER(FSM_MASK_SQLFieldMap_ADRESSE sqlfieldMAP_Adresse) throws myException
		{
			super(new FSM_MASK_SQLFieldMap_MITARBEITER(sqlfieldMAP_Adresse));
			
			FSM_MASK_SQLFieldMap_MITARBEITER oSQLFieldMAP = (FSM_MASK_SQLFieldMap_MITARBEITER)this.get_oSQLFieldMAP();
			
			E2_DropDownSettings ddMitarbeiterTyp = new E2_DropDownSettings("JT_MITARBEITERTYP", "KURZBEZEICHNUNG", "ID_MITARBEITERTYP", null, true);

			this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_MITARBEITER")),new MyE2_String("ID-Mitarbeiter"));
			this.add_Component(new MyE2_DB_Label(oSQLFieldMAP.get_("ID_ADRESSE_MITARBEITER")),new MyE2_String("ID-Adresse (Mitarbeiter)"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_MITARBEITERTYP"),ddMitarbeiterTyp.getDD(),false),new MyE2_String("Mitarbeitertyp"));
			//2014-04-25: weitere mitarbeiter-typ-felder
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_MITARBEITERTYP2"),ddMitarbeiterTyp.getDD(),false),new MyE2_String("Mitarbeitertyp2"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_MITARBEITERTYP3"),ddMitarbeiterTyp.getDD(),false),new MyE2_String("Mitarbeitertyp3"));
			this.add_Component(new MyE2_DB_SelectField(oSQLFieldMAP.get_("ID_MITARBEITERTYP4"),ddMitarbeiterTyp.getDD(),false),new MyE2_String("Mitarbeitertyp4"));
			
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("IST_ANSPRECHPARTNER")),new MyE2_String("Ansprechpartner ?"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("WEIHNACHTSGESCHENK")),new MyE2_String("Weihnachtsgeschenk ?"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("SOMMERGESCHENK")),new MyE2_String("Geschenk im Sommer ?"));
			
			
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.MITARBEITER$GESCHENK_WEIN)),new MyE2_String("Wein"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.MITARBEITER$GESCHENK_SEKT)),new MyE2_String("Sekt"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.MITARBEITER$GESCHENK_KALENDER)),new MyE2_String("Kalender"));
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_(_DB.MITARBEITER$GESCHENK_SPARGEL)),new MyE2_String("Spargel"));
			this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP.get_(_DB.MITARBEITER$ROHSTOFF_GESCHENK_INFO),true,300),new MyE2_String("Info"));
			
			
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_ABNAHMEANGEBOT")),new MyE2_String("Abnahmeanbot/Preisinfo"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_ANGEBOT")),new MyE2_String("Angebot"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_EK_KONTRAKT")),new MyE2_String("EK-Kontrakt"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_VK_KONTRAKT")),new MyE2_String("VK-Kontrakt"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_RECHNUNG")),new MyE2_String("Rechnung"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_GUTSCHRIFT")),new MyE2_String("Gutschrift"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_TRANSPORT")),new MyE2_String("Transportauftrag"));     
			this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP.get_("ASP_FIBU")),new MyE2_String("Fibu/Mahnwesen"));
			
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
