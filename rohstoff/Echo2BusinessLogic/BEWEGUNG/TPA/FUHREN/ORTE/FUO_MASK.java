package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;

/*
* maskenvariante mit TabbedPane 
* ... MUSS Umbenannt werden in FUO_MASK
*/
public class FUO_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FUO_MASK(FUO_MASK_ComponentMAP oMap, String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		
		boolean bEK = (cEK_VK.equals("EK"));
		
		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		oTabbedPaneMaske.set_bAutoHeight(true);

		
		int[] iBreite = {150,100,100,100,100,100,100,100,100,100};
		
		MyE2_Grid oGrid0 = new MyE2_Grid(iBreite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid oGrid0a = new MyE2_Grid(7,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(7,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(7,0);
		MyE2_Grid oGrid3 = new MyE2_Grid(7,0);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Fuhrenort"), 		oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Adresse/Artikel"), 	oGrid0a);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Nummerncodes"), 		oGrid1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Abzüge"), 			oGrid2);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Sonderregeln"),		oGrid3);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);

		Insets oIN = 	new Insets(0,2,5,0);
		Insets oINL = 	new Insets(0,2,10,0);    // linke spalte mit beschriftung wird weiter abgesetzt
		Insets oINL2 = 	new Insets(0,2,10,5);    // linke spalte mit beschriftung wird weiter abgesetzt
	
		E2_MaskFiller oFiller = new E2_MaskFiller(oMap,null,null,oINL,oIN, new Alignment(Alignment.LEFT,Alignment.CENTER)); 
		

		//oFiller.add_Line(oGrid0,new MyE2_String("ID"),1,"ID_VPOS_TPA_FUHRE_ORT|#  |#Zusatz in Fuhrenbuchungsnummer: |BUCHUNGSNUMMER_ZUSATZ|# |"+FUO__CONST.HASHKEY_MASK_SHOW_DELETED+"|# |ZEITSTEMPEL",6);
//		oFiller.add_Line(oGrid0,FUO__CONST.HASHKEY_MASK_SHOW_DELETED,1,"ID_VPOS_TPA_FUHRE_ORT",1,"#Zusatz BuchNr:",1,"BUCHUNGSNUMMER_ZUSATZ",1,"ZEITSTEMPEL",1,"#Wiegekarte",1,"WIEGEKARTENKENNER",1,"ID_WIEGEKARTE",3);
		oFiller.add_Line(oGrid0,FUO__CONST.HASHKEY_MASK_SHOW_DELETED,1,"ID_VPOS_TPA_FUHRE_ORT|#Zusatz BuchNr:|BUCHUNGSNUMMER_ZUSATZ|#Wiegekarte|WIEGEKARTENKENNER|ID_WIEGEKARTE|#Intrastat: |INTRASTAT_MELD|#Transit|"+_DB.VPOS_TPA_FUHRE_ORT$TRANSIT,9);

		oFiller.add_Trenner(oGrid0, E2_INSETS.I(0,2,0,2));

		oFiller.add_Line(oGrid0,new MyE2_Label(new MyE2_String(bEK?"Ladeort":"Abladeort"), MyE2_Label.STYLE_TITEL_BIG()),10);
		oFiller.add_Line(oGrid0,new MyE2_String(bEK?"EK-Kontrakt":"VK-Kontrakt"),	1,"ID_VPOS_KON",		9);
		
		//2018-06-07: anzeige der fremdwaehrung einfuegen (ueber 2 zeilen mit rowspan)
		oGrid0.		_add_r(new RB_lab(S.ms("Firma")), new RB_gld()._ins(0,2,5,0))
					._add_r(oMap.get__CompEcho(VPOS_TPA_FUHRE_ORT.id_adresse), new RB_gld()._ins(0,2,5,0)._span(8))
					._add_r(oMap.get__CompEcho(FUO__CONST.FIELDNAME_INFOBOCK_WAEHRUNGEN), new RB_gld()._ins(0,2,5,0)._span(1)._span_r(2));   //infoblock ueber 2 zeilen    
					;
		oGrid0.		_add_r(new E2_Grid()._a(S.ms("Lager"))._a(oMap.get__CompEcho(FUO__CONST.HASH_MASK_COMP_FREMDWARENLAGER), new RB_gld()._ins(0,2,5,0)))
					._add_r(oMap.get__CompEcho(VPOS_TPA_FUHRE_ORT.id_adresse_lager), new RB_gld()._ins(0,2,5,0)._span(8))
					;

		//vor 2018-06-07: 
//		oFiller.add_Line(oGrid0,new MyE2_String("Firma"),							1,"ID_ADRESSE",			9);
//		oFiller.add_Line(oGrid0,"#Lager|"+FUO__CONST.HASH_MASK_COMP_FREMDWARENLAGER,1,"ID_ADRESSE_LAGER",	9);
		
		oFiller.add_Line(oGrid0,new MyE2_String("Öffnungszeiten"),					1,"OEFFNUNGSZEITEN"	,	9);
		oFiller.add_Line(oGrid0,new MyE2_String("Bestell-Nummer"),					1,"BESTELLNUMMER",		2,"#Posten-Nummer",1,"POSTENNUMMER",2,"#",4);
		
		oFiller.add_Trenner(oGrid0, E2_INSETS.I(0,2,0,2));

		
		oFiller.add_Line(oGrid0,new MyE2_String(""),		1,		new MyE2_Label(new MyE2_String("Planmenge(1)"),new E2_FontItalic(-2)),	1,
																	new MyE2_Label(new MyE2_String("Lademenge(2)"),new E2_FontItalic(-2)), 1,
																	new MyE2_Label(new MyE2_String("Ablademenge(3)"),new E2_FontItalic(-2)),1,
																	new MyE2_Label(new MyE2_String("Mengeneinheit"),new E2_FontItalic(-2)),1,
																	new MyE2_Label(new MyE2_String(" "),new E2_FontItalic(-2)),1,
																	new MyE2_Label(new MyE2_String((bEK?"Ladedatum":"Abladedatum")),new E2_FontItalic(-2)),2,
																	"#",2);
		oFiller.add_Line(oGrid0,new MyE2_String("Mengen (Teile)"),	1,	"ANTEIL_PLANMENGE",	1,
																		"ANTEIL_LADEMENGE", 1,
																		"ANTEIL_ABLADEMENGE",1,
																		"EINHEIT_MENGEN",1,
																		"#",1,
																		"DATUM_LADE_ABLADE",2,"#",2);
		
		oFiller.add_Trenner(oGrid0, E2_INSETS.I(0,2,0,2));

		oFiller.add_Line(oGrid0,new MyE2_String("Gefahrene Sorte"),	1,"ID_ARTIKEL_BEZ",	4,		"#(|ANR1|#/|ANR2|#)|# ID-Artikel:|ID_ARTIKEL",5);

		oFiller.add_Trenner(oGrid0, E2_INSETS.I(0,2,0,2));

		
		
		oFiller.add_Line(oGrid0,new MyE2_String("Transitländer"),1,		"LAENDERCODE_TRANSIT1",1,"LAENDERCODE_TRANSIT2",1,"LAENDERCODE_TRANSIT3",1,"|#Drucke EU-Blatt",2,"PRINT_EU_AMTSBLATT",4);
		oFiller.add_Line(oGrid0,new MyE2_String("Nationaler Code"),1,	"NATIONALER_ABFALL_CODE",											 3,"|#EU-Blatt abweich. Menge",2,"EU_BLATT_MENGE|#Volumen|EU_BLATT_VOLUMEN",4);
		oFiller.add_Line(oGrid0,new MyE2_String("Ausstelldatum AVV"),1,	"AVV_AUSSTELLUNG_DATUM", 											 3,"|#Notifi./Begleitformular-Nr:",2,"NOTIFIKATION_NR",4);
		oFiller.add_Trenner(oGrid0, E2_INSETS.I(0,2,0,2));
		
		oGrid0.add(new MyE2_Label(new MyE2_String("Informationen zur Erzeugen von freien Positionen"),MyE2_Label.STYLE_NORMAL_BOLD()),10, oINL2);;
		
		//oFiller.add_Line(oGrid0,new MyE2_String("Preis manuell"),	1,	"MANUELL_PREIS",1,FUO__CONST.HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER,1,"#",7);
		oFiller.add_Line(oGrid0,new MyE2_String("Abnahme-Ang."),	1,	"ID_VPOS_STD",9);
		oFiller.add_Line(oGrid0,	new MyE2_String(bEK?"Einkaufspreis":"Verkaufspreis"),			1,	
									"EINZELPREIS",													1, 
									"MANUELL_PREIS", 												1,
									"#MWSt.|STEUERSATZ|"+FUO__CONST.HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER,	2,
									"#",5);
		
		//2014-06-01: neue lieferbedingungs-erfassung
		oFiller.add_Line(oGrid0,new MyE2_String("Lieferbedingung"),	1,	FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV,9);

		
		
		//20181127: handelsdef-komponente einblende
		E2_Grid gHandeldef = new E2_Grid()._bo_dd()._setSize(60)._a(new RB_lab("Steuer-Rg.")._fsa(-2)._i(), new RB_gld()._center_mid());
		gHandeldef._a(oMap.get__CompEcho(VPOS_TPA_FUHRE_ORT.id_handelsdef), new RB_gld()._center_mid());
		gHandeldef._a(new E2_Grid()._setSize(30,30)
							._a(oMap.get__CompEcho(FUO__CONST.HASH_MASK_EDIT_ID_HANDELSDEF_FUO), new RB_gld()._center_mid())
							._a(oMap.get__CompEcho(FUO__CONST.HASH_MASK_ERASE_ID_HANDELSDEF_FUO), new RB_gld()._center_mid())
					 );

		
		
		MyE2_Grid oGridSteuer = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridSteuer.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__EU_STEUER_VERMERK),1,2,E2_INSETS.I_0_0_2_0, new Alignment(Alignment.LEFT, Alignment.CENTER));
		oGridSteuer.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ID_TAX),E2_INSETS.I_0_0_0_0);
		if (ENUM_MANDANT_DECISION.FUHRENMASKE_ALTE_STEUER_AUSBLENDEN.is_YES_FromSession()) {
			oGridSteuer.add(new RB_lab(),E2_INSETS.I_0_0_0_0);
		} else {
			oGridSteuer.add(oMap.get_Comp(FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT),E2_INSETS.I_0_0_0_0);
		}
		//vor 20181127: neue steuerdef-komponente// oFiller.add_Line(oGrid0,new MyE2_String("Steuervermerk"),			1,	oGridSteuer,9);
		oFiller.add_Line(oGrid0,new MyE2_String("Steuervermerk"),			1,	oGridSteuer,5,gHandeldef,4);
		
		
		GridLayoutData  oGL_LeftCenter = 	MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 0, 2, 0));
		GridLayoutData  oGL_LeftCenterW =	MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(0, 0, 5, 0));
		GridLayoutData  oGL_LeftCenterB =	MyE2_Grid.LAYOUT_CENTER_CENTER(new Insets(10, 0, 0, 0));
		
		MyE2_Grid_InLIST gridFreigabeRechts = new MyE2_Grid_InLIST(7, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("Menge:")), oGL_LeftCenterW);
		gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE), oGL_LeftCenterW);
		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("von")), oGL_LeftCenter);
		gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE_VON),oGL_LeftCenterW);
		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
		gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE_AM), oGL_LeftCenterW);
		gridFreigabeRechts.add(oMap.get_Comp(bEK?RECORD_VPOS_TPA_FUHRE_ORT.FIELD__LADEMENGE_GUTSCHRIFT:RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ABLADEMENGE_RECHNUNG), oGL_LeftCenterB);
		
		//z2
		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("Preis:")), oGL_LeftCenterW);
		gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS), oGL_LeftCenterW);
		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("von")),oGL_LeftCenter);
		gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS_VON), oGL_LeftCenterW);
		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
		gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_PREIS_AM), oGL_LeftCenterW);
		//gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(bEK?"Lademenge":"Ablademenge")+"=Buch.Mge."), oGL_LeftCenterB);
		gridFreigabeRechts.add(oMap.get_Comp(FUO__CONST.ACTIVLABEL_LADE_ABLADE_MENGE_IST_BUCHUNGSMENGE), oGL_LeftCenterB);

//		//z3
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
//		gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("=Buch.Mge")), oGL_LeftCenterB);
		
		Border oBorder = new Border(2,Color.BLACK,Border.STYLE_SOLID);
		Extent  extRahmenbreite = new Extent(405);

		
		oFiller.add_Line(oGrid0,new MyE2_String("Abschluss"),1,gridFreigabeRechts.get_InBorderGrid_inList(oBorder,extRahmenbreite,null),9);


		
		
		
		//ende seite 1
		
		
		
		
		
		
		oFiller.add_Line(oGrid0a,new MyE2_String("Name "),1,"NAME1",6);
		oFiller.add_Line(oGrid0a,new MyE2_String(" "),1,"NAME2",6);
		oFiller.add_Line(oGrid0a,new MyE2_String(" "),1,"NAME3",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Strasse"),1,"STRASSE|HAUSNUMMER",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Land-Plz-Ort"),1,"LAENDERCODE|PLZ|ORT",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Ortzusatz"),1,"ORTZUSATZ",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Tel"),1,"TEL",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Fax"),1,"FAX",6);
		oFiller.add_Trenner(oGrid0a, E2_INSETS.I(0,2,0,2));
		oFiller.add_Line(oGrid0a,new MyE2_String("Art.Bez.1"),		1,"ARTBEZ1|#  |"+FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE,	6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Art.Bez.2"),		1,"ARTBEZ2",	6);

		// -------------------- seite 2
		
		


		
		oFiller.add_Line(oGrid1,new MyE2_String("Zeitstempel"),1,		_DB.VPOS_TPA_FUHRE_ORT$ZEITSTEMPEL+"|#  |",6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I(0,2,0,2));
		oFiller.add_Line(oGrid1,new MyE2_String("AVV-Code"),1,			"ID_EAK_CODE|#  |"+FUO__CONST.HASHKEY_MASK_LADE_AVV_VON_FUHRE,6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I(0,2,0,2));
		oFiller.add_Line(oGrid1,new MyE2_String("EU-Code/Notiz"),1,		"EUCODE",6);
		oFiller.add_Line(oGrid1,new MyE2_String("EU-Code/Notiz"),1,		"EUNOTIZ",6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I(0,2,0,2));
		oFiller.add_Line(oGrid1,new MyE2_String("Basel-Code/Notiz"),1,	"BASEL_CODE",6);
		oFiller.add_Line(oGrid1,new MyE2_String("Basel-Code/Notiz"),1,	"BASEL_NOTIZ",6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I(0,2,0,2));
		oFiller.add_Line(oGrid1,new MyE2_String("Zolltarifnummer"),1,	"ZOLLTARIFNR",6);
		
		oFiller.add_Trenner(oGrid1, E2_INSETS.I(0,2,0,2));
		oFiller.add_Line(oGrid1,new MyE2_String("Info Zusatzort"),1,"BEMERKUNG",6);

		//20181127: handelsdef auf die erste seite
//		//2013-09-30: neues feld fuer die handelsdefinition
//		oFiller.add_Trenner(oGrid1, E2_INSETS.I(0,2,0,2));
//		oFiller.add_Line(oGrid1,new MyE2_String("USt./Steuertext-Def."),1,	_DB.VPOS_TPA_FUHRE_ORT$ID_HANDELSDEF+"|"+FUO__CONST.HASH_MASK_ERASE_ID_HANDELSDEF_FUO,6);

		
		//2014-02-20: Gelangensbestaetigung
		if (!bEK) {
			oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_10_5);
			oFiller.add_Line(oGrid1,new MyE2_String("Gelangensbestätigung"),1,	_DB.VPOS_TPA_FUHRE_ORT$GELANGENSBESTAETIGUNG_ERHALTEN,6);
		}
		
//		oFiller.add_Line(oGrid2,new MyE2_String("Abzüge"),2,"|#   |ABZUG_MENGE|",5);
//		oFiller.add_Trenner(oGrid2, E2_INSETS.I(0,2,0,2));
		
		oFiller.add_Line(oGrid2,FUO__CONST.HASHKEY_MASK_ABZUG_FUHRE_ORT,7);

		
		//sonderregeln und rechnungsvorlagen
		
		oFiller.add_Line_WithComponents(oGrid3, new MyE2_Label(new MyE2_String("Sonderdef:")), 	
							new E2_Subgrid_4_Mask("Sonderdefinition|Fremdware|Kein VK-Kon|Ohne AVV-Vertr.Check",
												"ID_VPOS_TPA_FUHRE_SONDER|OHNE_ABRECHNUNG|KEIN_KONTRAKT_NOETIG|OHNE_AVV_VERTRAG_CHECK",oMap, null, null), 4, E2_INSETS.I_0_0_0_0,
												null,0, null,null,0,null);

		
		oFiller.add_Line(oGrid3,new MyE2_String("Sonderpositionen"),1,FUO__CONST.HASHKEY_DAUGHTER_POS_VORLAGEN,4);


		this.vMaskGrids.add(oGrid0);
		this.vMaskGrids.add(oGrid0a);
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
		
	}

	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}


	
}
