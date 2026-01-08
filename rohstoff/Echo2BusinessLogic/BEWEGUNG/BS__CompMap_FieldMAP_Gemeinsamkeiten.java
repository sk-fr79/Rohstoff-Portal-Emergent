package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainerWith_HTTP_PANE;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextFieldLABEL;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_P_MASK_BT_SearchPreise;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;

/*
 * klasse, die in allen Positionsmasken-verwendet werden kann, um
 * die Felder, die fuer die Preiseingabe zustaendig ist, zu verwalten
 */
public class BS__CompMap_FieldMAP_Gemeinsamkeiten 
{

	
	
	/**
	 * @param oMAP
	 * @param oFM
	 *  fuegt einige immer gleiche kopf-maskenelemente in die jeweiligen component-MAPS ein
	 *  ID_USER
	 *  ID_SPRACHE
	 *  ID_ZAHLUNGSBEDINGUNGEN
	 *  ZAHLUNGSBEDINGUNGEN
	 *  ZAHLTAGE
	 *  FIXMONAT
	 *  FIXTAG
	 *  LIEFERBEDINGUNGEN
	 *  LAENDERCODE
	 *  WAEHRUNG_FREMD
	 *  MITARBEITER
	 *  FORMULARTEXT_ANFANG
	 *  FORMULARTEXT_ENDE
	 *  ID_ADRESSE
	 */
	public static void add_Basic_KOPF_FieldsToComponentMAP(E2_ComponentMAP oMAP, SQLFieldMAP oFM, String cKennerForFormulartextLookup) throws myException
	{
		
		oMAP.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER"),true),new MyE2_String("Bearbeiter"));
		oMAP.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_ANSPRECH_INTERN"),true),new MyE2_String("Ansprechpartner im Haus"));
		oMAP.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_SACHBEARB_INTERN"),true),new MyE2_String("Sachbearbeiter im Haus"));
		
		oMAP.add_Component(new BS_ComboBox_SPRACHE(oFM),new MyE2_String("Sprache"));
		oMAP.add_Component(new BS_Sel_Zahlungsbedingungen(oFM),new MyE2_String("ID Zahlungsbedingungen"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ZAHLUNGSBEDINGUNGEN")),new MyE2_String("Zahlungsbedingungen"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("ZAHLTAGE")),new MyE2_String("Tage Zahlungsziel"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("FIXMONAT")),new MyE2_String("Fixmonat"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("FIXTAG")),new MyE2_String("Fixtag"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("SKONTO_PROZENT")),new MyE2_String("Skonto in Prozent"));
		
		oMAP.add_Component(new BS_ComboBox_MITARBEITER(oFM,"NAME_BEARBEITER_INTERN"),new MyE2_String("Name Bearbeiter intern"));
		oMAP.add_Component(new BS_ComboBox_MITARBEITER(oFM,"NAME_ANSPRECH_INTERN"),new MyE2_String("Name Ansprechpartner intern"));
		oMAP.add_Component(new BS_ComboBox_MITARBEITER(oFM,"NAME_SACHBEARB_INTERN"),new MyE2_String("Name Sachbearbeiter intern"));
		
		oMAP.add_Component(new BS_ComboBox_LIEFERBEDINGUNGEN(oFM, true),new MyE2_String("Lieferbedingungen"));
		oMAP.add_Component(new BS_ComboBox_LAENDERCODE(oFM),new MyE2_String("Ländercode"));
		oMAP.add_Component(new BS_SELECTFIELD_WAEHRUNG_FREMD_VKOPF(oFM, false),new MyE2_String("Währung"));

		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("TEL_BEARBEITER_INTERN"),true,150),new MyE2_String("Tel. Bearbeiter intern"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("TEL_ANSPRECH_INTERN"),true,150),new MyE2_String("Tel. Ansprechpartner intern"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("TEL_SACHBEARB_INTERN"),true,150),new MyE2_String("Tel. Sachbearbeiter intern"));
		
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("FAX_BEARBEITER_INTERN"),true,150),new MyE2_String("Fax Bearbeiter intern"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("FAX_ANSPRECH_INTERN"),true,150),new MyE2_String("Tel. Ansprechpartner intern"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("FAX_SACHBEARB_INTERN"),true,150),new MyE2_String("Tel. Sachbearbeiter intern"));

		oMAP.add_Component(new MyE2_DB_TextArea_WithSelektorEASY(oFM.get_("FORMULARTEXT_ANFANG"),cKennerForFormulartextLookup+"ANFANG"),new MyE2_String("Formulartext - ANFANG"));
		oMAP.add_Component(new MyE2_DB_TextArea_WithSelektorEASY(oFM.get_("FORMULARTEXT_ENDE"),cKennerForFormulartextLookup+"ENDE"),new MyE2_String("Formulartext - ENDE"));
		
		//2011-11-17: infos aus der adresse ziehe und als sofortnachricht einblenden
		oMAP.add_Component(new BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS(oFM.get_("ID_ADRESSE"),oFM.get_cMAIN_TABLE(),oFM),new MyE2_String("ID-Adresse"));
		
		/*
		 * selektions-popup fuer die mitarbeiter auf der maske
		 */
		oMAP.add_Component(BS__CONST.HASHKEY_FOR_FREMDMITARBEITERPOPUP, new BS_K_BT_Select_Fremd_Ansprechpartner(), new MyE2_String("Selektion Ansprechpartner"));

		
		((MyE2_DB_TextField) oMAP.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_iWidthPixel(300);
		((MyE2_DB_TextField) oMAP.get__Comp("ZAHLTAGE")).set_iWidthPixel(30);
		((MyE2_DB_TextField) oMAP.get__Comp("FIXMONAT")).set_iWidthPixel(30);
		((MyE2_DB_TextField) oMAP.get__Comp("FIXTAG")).set_iWidthPixel(30);
		((MyE2_DB_TextField) oMAP.get__Comp("SKONTO_PROZENT")).set_iWidthPixel(30);
		
		((MyE2_DB_TextArea_WithSelektorEASY) oMAP.get__Comp("FORMULARTEXT_ANFANG")).get_oTextArea().set_iWidthPixel(600);
		((MyE2_DB_TextArea_WithSelektorEASY) oMAP.get__Comp("FORMULARTEXT_ENDE")).get_oTextArea().set_iWidthPixel(600);
		((MyE2_DB_TextArea_WithSelektorEASY) oMAP.get__Comp("FORMULARTEXT_ANFANG")).get_oTextArea().set_iRows(10);
		((MyE2_DB_TextArea_WithSelektorEASY) oMAP.get__Comp("FORMULARTEXT_ENDE")).get_oTextArea().set_iRows(10);

//		MyE2_DB_ComboBoxErsatz  oTF_BenutzerName = 	(MyE2_DB_ComboBoxErsatz)oMap.get__Comp("NAME_BEARBEITER_INTERN");
//		MyE2_DB_TextField  		oTF_Telefon =   	(MyE2_DB_TextField)oMap.get__Comp("TEL_BEARBEITER_INTERN"); 
//		MyE2_DB_TextField  		oTF_FAX =  		 	(MyE2_DB_TextField)oMap.get__Comp("FAX_BEARBEITER_INTERN"); 

		
		((MyE2_DB_SelectField)oMAP.get__Comp(("ID_USER"))).add_oActionAgent(
				new actionLoadMitarbeiterDaten("NAME_BEARBEITER_INTERN","TEL_BEARBEITER_INTERN","FAX_BEARBEITER_INTERN"));
		
		((MyE2_DB_SelectField)oMAP.get__Comp(("ID_USER_ANSPRECH_INTERN"))).add_oActionAgent(
				new actionLoadMitarbeiterDaten("NAME_ANSPRECH_INTERN","TEL_ANSPRECH_INTERN","FAX_ANSPRECH_INTERN"));
		
		((MyE2_DB_SelectField)oMAP.get__Comp(("ID_USER_SACHBEARB_INTERN"))).add_oActionAgent(
				new actionLoadMitarbeiterDaten("NAME_SACHBEARB_INTERN","TEL_SACHBEARB_INTERN","FAX_SACHBEARB_INTERN"));
		
		
		
		
	}

		
	public static MyE2_Grid fill_BS_KOPF_MASK_Bereich_Zusatzinfos(E2_MaskFiller oFill) throws myException
	{
		MyE2_Grid oGrid2 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oFill.add_Line(oGrid2,new MyE2_String("Erfasser Vorgang"),1,			"ID_USER|NAME_BEARBEITER_INTERN|#  Tel:|TEL_BEARBEITER_INTERN|#  Fax:|FAX_BEARBEITER_INTERN",5);
		oFill.add_Line(oGrid2,new MyE2_String("Händler"),1,						"ID_USER_ANSPRECH_INTERN|NAME_ANSPRECH_INTERN|#  Tel:|TEL_ANSPRECH_INTERN|#  Fax:|FAX_ANSPRECH_INTERN",5);
		oFill.add_Line(oGrid2,new MyE2_String("Sachbearbeiter"),1,				"ID_USER_SACHBEARB_INTERN|NAME_SACHBEARB_INTERN|#  Tel:|TEL_SACHBEARB_INTERN|#  Fax:|FAX_SACHBEARB_INTERN",5);

		oFill.add_Trenner(oGrid2, E2_INSETS.I_5_5_5_5);

		oFill.add_Line(oGrid2,new MyE2_String("Lieferbedingungen"),1,		"LIEFERBEDINGUNGEN",5);
		oFill.add_Line(oGrid2,new MyE2_String("Zahlungsbedingungen"),1,		"ID_ZAHLUNGSBEDINGUNGEN|#   |ZAHLUNGSBEDINGUNGEN|# |ZAHLTAGE|# |FIXMONAT|# |FIXTAG|# |SKONTO_PROZENT",5);
		
		oFill.add_Trenner(oGrid2, E2_INSETS.I_5_5_5_5);
		
		oFill.add_Line(oGrid2,new MyE2_String("Name Ansprechpartner"),1,	"NAME_ANSPRECHPARTNER|# |"+BS__CONST.HASHKEY_FOR_FREMDMITARBEITERPOPUP,5);
		oFill.add_Line(oGrid2,new MyE2_String("Telefon auf Formular"),1,	"TELEFON_AUF_FORMULAR",5);
		oFill.add_Line(oGrid2,new MyE2_String("Telefax auf Formular"),1,	"TELEFAX_AUF_FORMULAR",5);
		oFill.add_Line(oGrid2,new MyE2_String("e-Mail auf Formular"),1,		"EMAIL_AUF_FORMULAR",5);

		return oGrid2;
	}
	
	
	
	
	/**
	 * @param oMAP
	 * @param oFM
	 * @param cKENNER_For_Popup
	 * @param cPOS_TYP  (TPA, KON, STD, RG)
	 * @throws myException
	 */
	public static void add_Basic_POSITION_Fields_To_ComponentMap(E2_ComponentMAP oMAP, SQLFieldMAP oFM, String cKENNER_For_Popup,String cPOS_TYP) throws myException
	{
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ANZAHL")),		new MyE2_String("Anzahl"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ARTBEZ1")),new MyE2_String("Artikelbezeichnung 1"));
		
		if (bibALL.isEmpty(cKENNER_For_Popup))
		{
			oMAP.add_Component(new MyE2_DB_TextArea(oFM.get_("ARTBEZ2")),new MyE2_String("Artikelbezeichnung 2"));
		}
		else
		{
			oMAP.add_Component(new MyE2_DB_TextArea_WithSelektorEASY(oFM.get_("ARTBEZ2"),"TEXT_ARTBEZ2_"+cKENNER_For_Popup),new MyE2_String("Artikelbezeichnung 2"));
		}
		
		MyE2_DB_TextField oTF_DB_Einzelpreis = new MyE2_DB_TextField(oFM.get_("EINZELPREIS"));
		oMAP.add_Component(oTF_DB_Einzelpreis,	new MyE2_String("E-Preis"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("GESAMTPREIS")),	new MyE2_String("G-Preis"));

		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("EINZELPREIS_ABZUG")),	new MyE2_String("E-Preis-Abzug"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("EINZELPREIS_RESULT")),	new MyE2_String("E-Preis-Result"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("GESAMTPREIS_ABZUG")),	new MyE2_String("G-Preis-Abzug"));
		
		
		MyE2_DB_TextField oTF_DB_EinzelpreisFW = new MyE2_DB_TextField(oFM.get_("EINZELPREIS_FW"));
		oMAP.add_Component(oTF_DB_EinzelpreisFW,	new MyE2_String("E-Preis-FW"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("GESAMTPREIS_FW")),	new MyE2_String("G-Preis-FW"));
		
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("EINZELPREIS_ABZUG_FW")),		new MyE2_String("E-Preis-Abzug-FW"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("EINZELPREIS_RESULT_FW")),	new MyE2_String("E-Preis-Result.-FW"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("GESAMTPREIS_ABZUG_FW")),		new MyE2_String("G-Preis-Abzug FW"));
		
		MyE2_DB_TextField oTFWaehrungsKurs = new MyE2_DB_TextField(oFM.get_("WAEHRUNGSKURS"));
		oMAP.add_Component(oTFWaehrungsKurs,	new MyE2_String("Währungskurs"));

		
		// die labels mit dem fremdwaehrungssymbol muessen auch in die MAP
		oMAP.add_Component(BS__CONST.HASH_WS_KURS_FELD, 		new MyE2_Label(new MyE2_String("Kurs #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		oMAP.add_Component(BS__CONST.HASH_WS_EPREIS_IN_BLOCK, new MyE2_Label(new MyE2_String("Einzelpreis #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		oMAP.add_Component(BS__CONST.HASH_WS_GPREIS_IN_BLOCK, new MyE2_Label(new MyE2_String("Gesamtpreis #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		
		
		//selectfield loescht den waehrungskurs bei betaetigung 
		BS_SELECTFIELD_WAEHRUNG_FREMD_VPOS oFremdWaehrung = new BS_SELECTFIELD_WAEHRUNG_FREMD_VPOS(oFM, true);
		oFremdWaehrung.add_oActionAgent(new actionForChangeWaehrung(oTFWaehrungsKurs,oFremdWaehrung));
		oMAP.add_Component(oFremdWaehrung, new MyE2_String("Fremdwährung"));
		

		MyE2_Button oButtonNeuberechnen = new MyE2_Button("Neuberechnung");
		oButtonNeuberechnen.add_oActionAgent(new BS__CompMap_FieldMAP_Gemeinsamkeiten.ActionCalculatePosition(oMAP));
		oMAP.add_Component(BS__CONST.HASHKEY_FOR_NEUBERECHNUNGS_BUTTON, oButtonNeuberechnen, new MyE2_String("Neuberechnung"));

		MyE2_Button oButtonWaehrungsInfo = new MyE2_Button("Kurs ?");
		oButtonWaehrungsInfo.add_oActionAgent(new actionHoleWaehrungsInfo(oFremdWaehrung));
		oMAP.add_Component(BS__CONST.HASHKEY_FOR_WAEHRUNG_POPUP, oButtonWaehrungsInfo, new MyE2_String("Kurs holen"));
		
		MyE2_Button oButtonBerechne = new MyE2_Button("Rechner");
		oButtonBerechne.add_oActionAgent(new actionCreateTaschenrechner(oTF_DB_Einzelpreis,oTF_DB_EinzelpreisFW,oTFWaehrungsKurs,oButtonNeuberechnen));
		oMAP.add_Component(BS__CONST.HASHKEY_FOR_CALCULATOR, oButtonBerechne, new MyE2_String("Verhältnis rechnen"));
		
		//falls es eine RG-position ist, dann einen preis-lade-button fuer kontraktpreise einblenden
		if (cPOS_TYP.equals("RG"))
		{
			oMAP.add_Component(BS__CONST.HASH_BUTTON_SEARCH_PREIS, new BSRG_P_MASK_BT_SearchPreise(), new MyE2_String("Preis aus Kontrakt"));
		}
		
		
		// weiterer block gemeinsamer felder
		/*
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
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ANR1"),true,110),new MyE2_String("ANR 1"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ANR2"),true,80),new MyE2_String("ANR 2"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ARTIKEL"),true,60),	new MyE2_String("ID-Art."));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("POSITIONSNUMMER"),true,60),	new MyE2_String("Pos"));
		oMAP.add_Component(new BS_ComboBox_MWST(oFM),	new MyE2_String("MWST."));
		oMAP.add_Component(new BS_ComboBox_LIEFERBEDINGUNGEN(oFM, true),		new MyE2_String("Lieferbed."));
		oMAP.add_Component(new BS_Sel_Zahlungsbedingungen(oFM),new MyE2_String("ID Zahlungsbedingungen"));
		oMAP.add_Component(new MyE2_DB_TextField(oFM.get_("ZAHLUNGSBEDINGUNGEN")),new MyE2_String("Zahlungsbedingungen"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("ZAHLTAGE")),new MyE2_String("Tage Zahlungsziel"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("FIXMONAT")),new MyE2_String("Fixmonat"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("FIXTAG")),new MyE2_String("Fixtag"));
		oMAP.add_Component(new MyE2_DB_TextFieldLABEL(oFM.get_("SKONTO_PROZENT")),new MyE2_String("Skonto in Prozent"));

		
		
		
		
	}
	
	public static void format_BasicPositionFields(E2_ComponentMAP oMAP)
	{
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS")).set_iWidthPixel(100);
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS")).set_iWidthPixel(120);
		
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_ABZUG")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_ABZUG")).set_iWidthPixel(100);

		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_RESULT")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_RESULT")).set_iWidthPixel(100);

		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_ABZUG")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_ABZUG")).set_iWidthPixel(100);

		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_FW")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_FW")).set_iWidthPixel(100);

		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_FW")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_FW")).set_iWidthPixel(120);

		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_ABZUG_FW")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_ABZUG_FW")).set_iWidthPixel(100);

		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_RESULT_FW")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_RESULT_FW")).set_iWidthPixel(100);

		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_ABZUG_FW")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_ABZUG_FW")).set_iWidthPixel(100);
		
		((MyE2_DB_TextField)oMAP.get("WAEHRUNGSKURS")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("WAEHRUNGSKURS")).set_iWidthPixel(80);
	
		((MyE2_DB_TextField)oMAP.get("ARTBEZ1")).set_iWidthPixel(400);

		if (oMAP.get("ARTBEZ2") instanceof MyE2_DB_TextArea)
		{
			((MyE2_DB_TextArea)oMAP.get("ARTBEZ2")).set_iWidthPixel(400);
			((MyE2_DB_TextArea)oMAP.get("ARTBEZ2")).set_iRows(8);
		}
		else if (oMAP.get("ARTBEZ2") instanceof MyE2_DB_TextArea_WithSelektorEASY)
		{
			((MyE2_DB_TextArea_WithSelektorEASY)oMAP.get("ARTBEZ2")).get_oTextArea().set_iWidthPixel(400);
			((MyE2_DB_TextArea_WithSelektorEASY)oMAP.get("ARTBEZ2")).get_oTextArea().set_iRows(8);
			((MyE2_DB_TextArea_WithSelektorEASY)oMAP.get("ARTBEZ2")).set_bAddTextBehind(new Boolean(true));
		}
		
		((MyE2_DB_TextField)oMAP.get("ANZAHL")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)oMAP.get("ANZAHL")).set_iWidthPixel(100);
	
		
		/*
		 * Feldgroessen
		 */
		((MyE2_DB_TextField)oMAP.get("ANR1")).set_iWidthPixel(80);    //2011-01-06: verlaengert
		((MyE2_DB_TextField)oMAP.get("ANR2")).set_iWidthPixel(50);    //2011-01-06: verlaengert
		
		((MyE2_DB_TextField) oMAP.get("ZAHLUNGSBEDINGUNGEN")).set_iWidthPixel(300);
		((MyE2_DB_TextField) oMAP.get("ZAHLTAGE")).set_iWidthPixel(30);
		((MyE2_DB_TextField) oMAP.get("FIXMONAT")).set_iWidthPixel(30);
		((MyE2_DB_TextField) oMAP.get("FIXTAG")).set_iWidthPixel(30);
		((MyE2_DB_TextField) oMAP.get("SKONTO_PROZENT")).set_iWidthPixel(30);
		
		((MyE2_DB_TextField)oMAP.get("POSITIONSNUMMER")).set_iWidthPixel(50);
		((MyE2_DB_TextField)oMAP.get("POSITIONSNUMMER")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));

		
		
		
		
	}
	
	

	public static void set_READ_ONLY_Fields(E2_ComponentMAP oMAP)
	{

		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_ABZUG")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_RESULT")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_ABZUG")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_FW")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_FW")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_ABZUG_FW")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("EINZELPREIS_RESULT_FW")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS_ABZUG_FW")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)oMAP.get("GESAMTPREIS")).EXT().set_bDisabledFromBasic(true);
	}
	
	
	
	
	public static void set_BasicSQLFieldSettings_for_Positions(SQLFieldMAP oFM, String cLAGERVOZEICHEN) throws myException
	{
		
		/*
		 * defaultwerte setzen
		 */
		//oFM.get_("POSITIONSKLASSE").set_cDefaultValueFormated(myCONST.VG_POSITIONSKLASSE_BELEGPOSITION);
		oFM.get_("POSITION_TYP").set_cDefaultValueFormated(myCONST.VG_POSITION_TYP_ARTIKEL);
		oFM.get_("LAGER_VORZEICHEN").set_cDefaultValueFormated(cLAGERVOZEICHEN);
		oFM.get_("MENGENDIVISOR").set_cDefaultValueFormated("1");
		oFM.get_("WAEHRUNGSKURS").set_cDefaultValueFormated("1");
		oFM.get_("DELETED").set_cDefaultValueFormated("N");
		
		//die waehrung wird auf die mandanten-standard-waehrung gesetzt
		oFM.get_("ID_WAEHRUNG_FREMD").set_cDefaultValueFormated(bibALL.get_ID_BASISWAEHRUNG_FORMATED());
		
		/*
		 * 
		 * must-values
		 */
		oFM.get_("POSITION_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_("WAEHRUNGSKURS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_("ID_WAEHRUNG_FREMD").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oFM.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

	}
	
	
	
	
	/**
	 * @param oMAP
	 * @return s Grid mit allen komponenten ab Anzahl bis Beschreibung2
	 * @throws myException
	 */
	public static MyE2_Grid  get_MASK_GRID_Preise(E2_ComponentMAP oMAP,String cPOS_TYP, Integer iWithLeftRow,  Component oComponentLinksVonArtbez2) throws myException
	{
		
		MyE2_Grid gridRueck = new MyE2_Grid(7,0);
		
		if (iWithLeftRow != null)
		{
			gridRueck.setColumnWidth(0, new Extent(iWithLeftRow));
		}
		
		GridLayoutData oLayOutRechts = MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_0_0_5_2, 1, new Alignment(Alignment.RIGHT,Alignment.TOP));
		
		gridRueck.add(new MyE2_Label(new MyE2_String("Anzahl")),1, E2_INSETS.I_0_0_5_2);
		gridRueck.add(new MyE2_Label(new MyE2_String("Beschreibung")),3, E2_INSETS.I_0_0_5_2);
		gridRueck.add(new MyE2_Label(" "),1, E2_INSETS.I_0_0_5_2);
		gridRueck.add(new MyE2_Label(new MyE2_String("Einzelpreis "+bibE2.get_cBASISWAEHRUNG_SYMBOL())),oLayOutRechts);
		gridRueck.add(new MyE2_Label(new MyE2_String("Gesamtpreis "+bibE2.get_cBASISWAEHRUNG_SYMBOL())),oLayOutRechts);
		
		gridRueck.add(oMAP.get_Comp("ANZAHL"), 1, E2_INSETS.I_0_0_5_2);
		gridRueck.add(oMAP.get_Comp("ARTBEZ1"),3, E2_INSETS.I_0_0_5_2);
		
		if (cPOS_TYP.equals("RG"))
		{
			gridRueck.add(oMAP.get_Comp(BS__CONST.HASH_BUTTON_SEARCH_PREIS),oLayOutRechts);
		}
		else
		{
			gridRueck.add(new MyE2_Label(" "),oLayOutRechts);
		}
		gridRueck.add(oMAP.get_Comp("EINZELPREIS"),oLayOutRechts);
		gridRueck.add(oMAP.get_Comp("GESAMTPREIS"),oLayOutRechts);

		
		
		if (oComponentLinksVonArtbez2==null)
		{
			gridRueck.add(new MyE2_Label(new MyE2_String("")),1,E2_INSETS.I_0_0_5_2);
		}
		else
		{
			//gridRueck.add(oComponentLinksVonArtbez2,1,E2_INSETS.I_0_0_5_2);
			gridRueck.add(oComponentLinksVonArtbez2,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 3, 5, 2)));
		}
		gridRueck.add(oMAP.get_Comp("ARTBEZ2"),3, E2_INSETS.I_0_0_5_2);
		gridRueck.add(new MyE2_Label(" "),1, E2_INSETS.I_0_0_5_2);

		
		MyE2_Grid gridRahmen = new MyE2_Grid(1,1);
		
		MyE2_Grid gridHelp = new MyE2_Grid(2,0);
		
		gridHelp.add(oMAP.get_Comp(BS__CONST.HASH_WS_KURS_FELD),oLayOutRechts);
		gridHelp.add(new MyE2_Label(new MyE2_String("Fremdwährung"),MyE2_Label.STYLE_SMALL_ITALIC()),oLayOutRechts);
		gridHelp.add(oMAP.get_Comp("WAEHRUNGSKURS"),oLayOutRechts);
		gridHelp.add(oMAP.get_Comp("ID_WAEHRUNG_FREMD"),oLayOutRechts);
		
		gridHelp.add(oMAP.get_Comp(BS__CONST.HASH_WS_EPREIS_IN_BLOCK),oLayOutRechts);
		gridHelp.add(oMAP.get_Comp(BS__CONST.HASH_WS_GPREIS_IN_BLOCK),oLayOutRechts);
		gridHelp.add(oMAP.get_Comp("EINZELPREIS_FW"), oLayOutRechts);
		gridHelp.add(oMAP.get_Comp("GESAMTPREIS_FW"), oLayOutRechts);
		
		
		MyE2_Row oRowForButtons = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oRowForButtons.add(oMAP.get_Comp(BS__CONST.HASHKEY_FOR_NEUBERECHNUNGS_BUTTON), E2_INSETS.I_0_0_10_0);
		oRowForButtons.add(oMAP.get_Comp(BS__CONST.HASHKEY_FOR_WAEHRUNG_POPUP), E2_INSETS.I_0_0_10_0);
		oRowForButtons.add(oMAP.get_Comp(BS__CONST.HASHKEY_FOR_CALCULATOR), E2_INSETS.I_0_0_10_0);
		
		gridHelp.add(oRowForButtons,2,E2_INSETS.I_10_5_10_5);
		
		gridRahmen.add(gridHelp, E2_INSETS.I_2_2_2_2);
		
		//gridRueck.add(gridRahmen,2, E2_INSETS.I_0_0_5_2);
		gridRueck.add(gridRahmen,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 3, 5, 2),2));
		
		return gridRueck;
	}
	
	
	private static class actionForChangeWaehrung extends XX_ActionAgent
	{
		private MyE2_DB_TextField 						oFieldWaehrungsKurs = null;
		private BS_SELECTFIELD_WAEHRUNG_FREMD_VPOS   oSelectWaehrung = null;

		public actionForChangeWaehrung(MyE2_DB_TextField fieldWaehrungsKurs, BS_SELECTFIELD_WAEHRUNG_FREMD_VPOS selectWaehrung) {
			super();
			oFieldWaehrungsKurs = fieldWaehrungsKurs;
			oSelectWaehrung = selectWaehrung;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			oFieldWaehrungsKurs.setText("");
			if (oSelectWaehrung.get_ActualWert().replace(".", "").equals(bibALL.get_ID_BASISWAEHRUNG()))
			{
				oFieldWaehrungsKurs.setText("1,000");        //falls selektierte waehrung = basis-waehrung, dann kurs = 1
			}

			
			String cFremdWaehrungssymbol = "??";
			String cID = bibALL.ReplaceTeilString(oSelectWaehrung.get_cActualDBValueFormated(),".","");
			if (bibALL.isLong(cID))
			{
				cFremdWaehrungssymbol = new RECORD_WAEHRUNG(cID).get_WAEHRUNGSSYMBOL_cUF_NN("??");
			}
			//jetzt die masken-Componenten mit #FW# mit der waehrung fuellen
			((BS_ComponentMAP)this.oSelectWaehrung.EXT().get_oComponentMAP()).set_FremdWaehrungsSymbol(bibALL.isEmpty(cFremdWaehrungssymbol)?"??":cFremdWaehrungssymbol);
			
			
			//jetzt nachsehen, ob es eine Abzugsliste gibt, wenn ja, dort auch die fremdwaehrungssymbole ersetzen
			if (((BS_ComponentMAP)this.oSelectWaehrung.EXT().get_oComponentMAP()).containsKey(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS))
			{
				E2_ComponentMAP  oMap = (BS_ComponentMAP)this.oSelectWaehrung.EXT().get_oComponentMAP();
				
				Vector<Component>  vLabs = ((MyE2_DBC_DaughterTable)oMap.get__Comp(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS)).get_vComponents(bibALL.get_Vector(MyE2_Label.class.getName()),null);
				
				// dann alle komponenten vom Typ MyE2_Label rausziehen
				for (Component oLab: vLabs)
				{
					if (oLab instanceof MyE2_Label)
					{
						((MyE2_Label)oLab).set_ReplaceText("#FW#", cFremdWaehrungssymbol);
					}
				}

			}
			
		}
	}

	
	
	private static class ActionCalculatePosition extends XX_ActionAgent
	{

		private E2_ComponentMAP oMAP = null;
		

		public ActionCalculatePosition(E2_ComponentMAP omap) 
		{
			super();
			oMAP = omap;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			try 
			{
//				Double D_Anzahl = this.oMAP.get_DActualDBValue("ANZAHL", false, false, new Double(0));
//				Double D_Epreis = this.oMAP.get_DActualDBValue("EINZELPREIS", false, false, new Double(0));
//				Double D_MengenDiv = this.oMAP.get_DActualDBValue("MENGENDIVISOR", false, false, new Double(0));
//				Double D_Waehrungskurs = this.oMAP.get_DActualDBValue("WAEHRUNGSKURS", false, false, new Double(0));
				Double D_Anzahl = this.oMAP.get_DActualDBValue("ANZAHL", 				true, true, new Double(0));
				Double D_Epreis = this.oMAP.get_DActualDBValue("EINZELPREIS", 			true, true, new Double(0));
				Double D_MengenDiv = this.oMAP.get_DActualDBValue("MENGENDIVISOR", 		true, true, new Double(1));
				Double D_Waehrungskurs = this.oMAP.get_DActualDBValue("WAEHRUNGSKURS", 	true, true, new Double(1));
				
				BS_PreisCalculator oPreisCalc = new BS_PreisCalculator(
							MyNumberFormater.formatDez(D_Anzahl.doubleValue(), 2, true),
							MyNumberFormater.formatDez(D_Epreis.doubleValue(), 2, true),
							MyNumberFormater.formatDez(D_MengenDiv.doubleValue(), 2, true),
							MyNumberFormater.formatDez(D_Waehrungskurs.doubleValue(), 4, true),true);
				
				Double D_Gesamtpreis = oPreisCalc.get_dGesamtPreis().doubleValue();
				Double D_EPreis_FW   =  oPreisCalc.get_dEinzelPreis_FW().doubleValue();
				Double D_Gesamtpreis_FW = oPreisCalc.get_dGesamtPreis_FW().doubleValue();
				
				((MyE2_DB_TextField)this.oMAP.get__Comp("GESAMTPREIS")).setText(MyNumberFormater.formatDez(D_Gesamtpreis,2,true));
				((MyE2_DB_TextField)this.oMAP.get__Comp("EINZELPREIS_FW")).setText(MyNumberFormater.formatDez(D_EPreis_FW,2,true));
				((MyE2_DB_TextField)this.oMAP.get__Comp("GESAMTPREIS_FW")).setText(MyNumberFormater.formatDez(D_Gesamtpreis_FW,2,true));
			} 
			catch (myException e) 
			{
				e.printStackTrace();
				if (!oExecInfo.get_bEventWasPassive())   // sonst werden Fehlermeldungen mehrmals angezeigt
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte füllen Sie die Felder Anzahl, Einzelpreis und Fremdwährungskurs korrekt aus !"));
				}
			}
		}
		
	}
	
	
	
	private static class actionHoleWaehrungsInfo extends XX_ActionAgent
	{
		private BS_SELECTFIELD_WAEHRUNG_FREMD_VPOS oSelID_Fremd = null;
		
		public actionHoleWaehrungsInfo(BS_SELECTFIELD_WAEHRUNG_FREMD_VPOS selID_Fremd) 
		{
			super();
			oSelID_Fremd = selID_Fremd;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_BasicModuleContainerWith_HTTP_PANE oPanePopup = new E2_BasicModuleContainerWith_HTTP_PANE(600,500);
			
			String cID_WAEHRUNG_BASIS = bibALL.get_ID_BASISWAEHRUNG();
			String cID_WAEHRUNG_ZIEL  = oSelID_Fremd.get_ActualWert().replace(".", "");
			
			if (cID_WAEHRUNG_BASIS.equals(cID_WAEHRUNG_ZIEL))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Umrechnung unsinnig, da die Währungen gleich sind !"));
				return;
			}
			
			if (bibALL.isEmpty(cID_WAEHRUNG_BASIS) || bibALL.isEmpty(cID_WAEHRUNG_ZIEL))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! die Währungen sind nicht definiert !!"));
				return;
			}
			
			try
			{
				MyDataRecordHashMap oHM = new MyDataRecordHashMap("SELECT INFO_URL FROM "+bibE2.cTO()+".JD_WAEHRUNGSQUERY " +
						     " WHERE " +
						     " ID_WAEHRUNG_BASIS="+cID_WAEHRUNG_BASIS+" AND "+
						     " ID_WAEHRUNG_ZIEL ="+cID_WAEHRUNG_ZIEL);
				
				oPanePopup.showWebsite(oHM.get_UnFormatedValue("INFO_URL"));
			
				oPanePopup.CREATE_AND_SHOW_POPUPWINDOW(null, null, new MyE2_String("Währungsinfo"));
			} 
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Vermutlicht wurde für diese Kombination der Umrechnung keine Webseite hinterlegt !"));
				return;
			}
		}
	}
	
	
	
	/*
	 * rechner fuer die berechnung der preise (rechnet immer mit 2 werten den 3. aus)
	 */
	private static class actionCreateTaschenrechner extends XX_ActionAgent
	{
		private MyE2_DB_TextField  oDB_TEXT_EinzelPreis = null;
		private MyE2_DB_TextField  oDB_TEXT_EinzelPreis_FW = null;
		private MyE2_DB_TextField  oDB_TEXT_Waehrungskurs = null;
		
		private UB_TextFieldForNumbers oTF_EinzelPreis = null;
		private UB_TextFieldForNumbers oTF_EinzelPreisFW = null;
		private UB_TextFieldForNumbers oTF_Waehrungskurs = null;

		private E2_BasicModuleContainer  oContainerRechner = null; 

		private MyE2_Button  			 oButtonNeuBerechnen = null;
		

		public actionCreateTaschenrechner(		MyE2_DB_TextField einzelPreis, 
												MyE2_DB_TextField einzelPreis_FW, 
												MyE2_DB_TextField waehrungskurs, 
												MyE2_Button buttonNeuBerechnen) 
		{
			super();
			oDB_TEXT_EinzelPreis = einzelPreis;
			oDB_TEXT_EinzelPreis_FW = einzelPreis_FW;
			oDB_TEXT_Waehrungskurs = waehrungskurs;
			oButtonNeuBerechnen = buttonNeuBerechnen;
		}


		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			try
			{
				this.oTF_EinzelPreis = 		new UB_TextFieldForNumbers("EINZELPREIS",2,true,this.oDB_TEXT_EinzelPreis.getText());
				this.oTF_EinzelPreisFW = 	new UB_TextFieldForNumbers("EINZELPREIS_FW",2,true,this.oDB_TEXT_EinzelPreis_FW.getText());
				this.oTF_Waehrungskurs = 	new UB_TextFieldForNumbers("WAEHRUNGSKURS",4,true,this.oDB_TEXT_Waehrungskurs.getText());
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte prüfen Sie die Werte in den Feldern Einzelpreis und Währungskurs !"));
				return;
			}
			this.oContainerRechner = new E2_BasicModuleContainer();
			this.oContainerRechner.set_bVisible_Row_For_Messages(true);
		
			MyE2_Button oButtonOK = new MyE2_Button("Berechne");
			oButtonOK.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExec_Info) throws myException 
				{
					actionCreateTaschenrechner oThis = actionCreateTaschenrechner.this;
					
					int iEmpty = 0;
					
					if (oThis.oTF_EinzelPreis.get_bIsEmpty()) iEmpty++;
					if (oThis.oTF_EinzelPreisFW.get_bIsEmpty()) iEmpty++;
					if (oThis.oTF_Waehrungskurs.get_bIsEmpty()) iEmpty++;
					
					if (iEmpty != 1)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte immer 2 Felder ausfüllen, 1 Feld leerlassen!!"));
						return;
					}
					
					try
					{
						if (oThis.oTF_EinzelPreis.get_bIsEmpty())
						{
							if (oThis.oTF_EinzelPreisFW.get_MV_InputOK().get_bIsOK() & oThis.oTF_Waehrungskurs.get_MV_InputOK().get_bIsOK())
							{
								double dEinzelpreisFW = oThis.oTF_EinzelPreisFW.get_ValueAsDOUBLE(true).doubleValue();
								double dWaehrungskurs = oThis.oTF_Waehrungskurs.get_ValueAsDOUBLE(true).doubleValue();
								
								double dEinzelpreis = bibALL.Round(dEinzelpreisFW/dWaehrungskurs, 2);
								
								oThis.oDB_TEXT_EinzelPreis.setText(MyNumberFormater.formatDez(dEinzelpreis, 2, true));
								oThis.oDB_TEXT_EinzelPreis_FW.setText(MyNumberFormater.formatDez(dEinzelpreisFW, 2, true));
								oThis.oDB_TEXT_Waehrungskurs.setText(MyNumberFormater.formatDez(dWaehrungskurs, 4, true));
								
								oThis.oContainerRechner.CLOSE_AND_DESTROY_POPUPWINDOW(true);
								
								oThis.oButtonNeuBerechnen.doActionPassiv();
								
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte korrekte Zahlen eingeben !!"));
								return;
							}
						}
						
						if (oThis.oTF_EinzelPreisFW.get_bIsEmpty())
						{
							if (oThis.oTF_EinzelPreis.get_MV_InputOK().get_bIsOK() & oThis.oTF_Waehrungskurs.get_MV_InputOK().get_bIsOK())
							{
								double dEinzelpreis = oThis.oTF_EinzelPreis.get_ValueAsDOUBLE(true).doubleValue();
								double dWaehrungskurs = oThis.oTF_Waehrungskurs.get_ValueAsDOUBLE(true).doubleValue();
								
								double dEinzelpreisFW = bibALL.Round(dEinzelpreis*dWaehrungskurs, 2);
								
								oThis.oDB_TEXT_EinzelPreis.setText(MyNumberFormater.formatDez(dEinzelpreis, 2, true));
								oThis.oDB_TEXT_EinzelPreis_FW.setText(MyNumberFormater.formatDez(dEinzelpreisFW, 2, true));
								oThis.oDB_TEXT_Waehrungskurs.setText(MyNumberFormater.formatDez(dWaehrungskurs, 4, true));

								
								oThis.oContainerRechner.CLOSE_AND_DESTROY_POPUPWINDOW(true);
								
								oThis.oButtonNeuBerechnen.doActionPassiv();

							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte korrekte Zahlen eingeben !!"));
								return;
							}
						}
						

						if (oThis.oTF_Waehrungskurs.get_bIsEmpty())
						{
							if (oThis.oTF_EinzelPreisFW.get_MV_InputOK().get_bIsOK() & oThis.oTF_EinzelPreis.get_MV_InputOK().get_bIsOK())
							{
								double dEinzelpreis = oThis.oTF_EinzelPreis.get_ValueAsDOUBLE(true).doubleValue();
								double dEinzelpreisFW = oThis.oTF_EinzelPreisFW.get_ValueAsDOUBLE(true).doubleValue();

								double dWaehrungskurs = bibALL.Round(dEinzelpreisFW/dEinzelpreis, 4);
								
								oThis.oDB_TEXT_EinzelPreis.setText(MyNumberFormater.formatDez(dEinzelpreis, 2, true));
								oThis.oDB_TEXT_EinzelPreis_FW.setText(MyNumberFormater.formatDez(dEinzelpreisFW, 2, true));
								oThis.oDB_TEXT_Waehrungskurs.setText(MyNumberFormater.formatDez(dWaehrungskurs, 4, true));
								
								oThis.oContainerRechner.CLOSE_AND_DESTROY_POPUPWINDOW(true);
								
								oThis.oButtonNeuBerechnen.doActionPassiv();

							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte korrekte Zahlen eingeben !!"));
								return;
							}
						}
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Unbekannter Fehler !!"));
					}
				}
			});

			MyE2_Grid oGridForElements = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

			try
			{
				//MAP_WAEHRUNG oMW = new MAP_WAEHRUNG(new Integer(bibALL.get_ID_BASISWAEHRUNG()).intValue());
				
				oGridForElements.add(new MyE2_Label(new MyE2_String("Einzelpreis ",true,
						new RECORD_WAEHRUNG(bibALL.get_ID_BASISWAEHRUNG()).get_WAEHRUNGSSYMBOL_cUF_NN(""),false), 	MyE2_Label.STYLE_SMALL_BOLD()), MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_10_10_10_10, 1, new Alignment(Alignment.RIGHT,Alignment.TOP)));
				oGridForElements.add(new MyE2_Label(new MyE2_String("Währungskurs "),										  	MyE2_Label.STYLE_SMALL_BOLD()), MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_10_10_10_10, 1, new Alignment(Alignment.RIGHT,Alignment.TOP)));
				oGridForElements.add(new MyE2_Label(new MyE2_String("Einzelpreis FW"),											MyE2_Label.STYLE_SMALL_BOLD()), MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_10_10_10_10, 1, new Alignment(Alignment.RIGHT,Alignment.TOP)));
				oGridForElements.add(new MyE2_Label(new MyE2_String("")));
	
				oGridForElements.add(this.oTF_EinzelPreis, E2_INSETS.I_10_10_10_10);
				oGridForElements.add(this.oTF_Waehrungskurs, E2_INSETS.I_10_10_10_10);
				oGridForElements.add(this.oTF_EinzelPreisFW, E2_INSETS.I_10_10_10_10);
				oGridForElements.add(oButtonOK, E2_INSETS.I_10_10_10_10);
				
				this.oContainerRechner.add(oGridForElements, E2_INSETS.I_10_10_10_10);
				
				this.oContainerRechner.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(200), new MyE2_String("Währungsrechner"));
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Unbekannter Fehler !!"));
			}
			
		}
		
	}
	
	
	
	
	private static class actionLoadMitarbeiterDaten extends XX_ActionAgent
	{
		private String FELD_USERNAME =  null;
		private String FELD_TELEFON =  null;
		private String FELD_TELEFAX =  null;
		
		
		public actionLoadMitarbeiterDaten(String fELD_USERNAME,	String fELD_TELEFON, String fELD_TELEFAX) 
		{	super();
			FELD_USERNAME = fELD_USERNAME;
			FELD_TELEFON = fELD_TELEFON;
			FELD_TELEFAX = fELD_TELEFAX;
		}


		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			try
			{
				MyE2_DB_SelectField 	oSelField = 		(MyE2_DB_SelectField) bibE2.get_LAST_ACTIONEVENT().getSource();
				E2_ComponentMAP 		oMap = 				oSelField.EXT().get_oComponentMAP();
//				MyE2_DB_ComboBoxErsatz  oTF_BenutzerName = 	(MyE2_DB_ComboBoxErsatz)oMap.get__Comp("NAME_BEARBEITER_INTERN");
//				MyE2_DB_TextField  		oTF_Telefon =   	(MyE2_DB_TextField)oMap.get__Comp("TEL_BEARBEITER_INTERN"); 
//				MyE2_DB_TextField  		oTF_FAX =  		 	(MyE2_DB_TextField)oMap.get__Comp("FAX_BEARBEITER_INTERN"); 
				MyE2_DB_ComboBoxErsatz  oTF_BenutzerName = 	(MyE2_DB_ComboBoxErsatz)oMap.get__Comp(this.FELD_USERNAME);
				MyE2_DB_TextField  		oTF_Telefon =   	(MyE2_DB_TextField)oMap.get__Comp(this.FELD_TELEFON); 
				MyE2_DB_TextField  		oTF_FAX =  		 	(MyE2_DB_TextField)oMap.get__Comp(this.FELD_TELEFAX); 
				
				if (oSelField.get_ActualWert().trim().equals(""))
				{
					oTF_BenutzerName.get_oTextField().setText("");
					oTF_Telefon.setText("");
					oTF_FAX.setText("");
				}
				else
				{
					String cID_USER = oSelField.get_ActualWert();
					cID_USER = bibALL.ReplaceTeilString(cID_USER,".","");
					RECORD_USER oUser = new RECORD_USER(cID_USER);
					oTF_BenutzerName.get_oTextField().setText(oUser.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "));
					oTF_Telefon.setText(oUser.get_TELEFON_cUF_NN(""));
					oTF_FAX.setText(oUser.get_TELEFAX_cUF_NN(""));
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Laden des Benutzernamens !! "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		} 
	}
	
	
}
