/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;



import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode_For_TabChangeAction;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_ExpandableRow_SAVEABLE;
import panter.gmbh.Echo2.components.E2_ExpandableRow_SAVEABLE.SAVE_KEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.DAUGHTER_INTERFACE.IF_MaskDaughter_Activater;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF.FS_Component_MASK_DAUGHTER_ARTIKELBEZ;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskGridShowGpsComponents;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN.FS_Component_MASK_DAUGHTER_KONTEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP.FSK__FULL_MASK_DAUGHTER_TP_KOSTEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN.FS_Component_MASK_DAUGHTER_LIEFERADRESSEN;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ.FS_Component_MASK_DAUGHTER_MATSPEZ;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER.FS_Component_MASK_DAUGHTER_MITARBEITER;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE.FS_PosList_FullDaughterTable;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.FS_Component_MASK_DAUGHTER_ZUSATZINFOS;


public class FS_Mask extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	public static RowLayoutData ownRowLayout = null;
	public static MutableStyle	ownRowStyle = null;
	static
	{
		RowLayoutData oRowLayout = new RowLayoutData();
		oRowLayout.setInsets(new Insets(0,0,10,0));
		ownRowLayout = oRowLayout;
		
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Row.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( Row.PROPERTY_INSETS,  new Insets(0)); 
		oStyle.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		ownRowStyle = oStyle;
	}
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	private static String cTab1OrigText = "Adresse";
	
	
	public FS_Mask(	FS_MASK_ComponentMAP  oE2_MAP_ADRESSE, MyE2_TabbedPane oTabbedPane) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		oTabbedPane.set_bAutoHeight(true);

		
		E2_ComponentMAP oE2_MAP_FIRMENINFO = oE2_MAP_ADRESSE.get_E2_ComponentMAP_Firmeninfo();
		
		ownGrid		oGridPage000	= new ownGrid(6);
		
		
		ownGrid		oGridPage001	= new ownGrid(2);

		
		//2012-08-03: neugestaltung der seite
		int[] iSpalten = {200,300,300,300};
		ownGrid     oGridPage002  = new ownGrid(iSpalten); 

		
		ownGrid		oGridPage003 	= new ownGrid(2);
		ownGrid		oGridPage004	= new ownGrid(2);
		// ownGrid		oGridPage005	= new ownGrid(2);
		int[] iSpalten3 = {170,140,140,140,140,140,140};
		ownGrid     oGridPage005  = new ownGrid(iSpalten3);
		
		MyE2_Grid	oGridPage006	= new MyE2_Grid(7); oGridPage006._setSize(170,150,150,150,150,150,250)._nB();
		ownGrid		oGridPage007	= new ownGrid(2);
		ownGrid		oGridPage008	= new ownGrid(2);
		ownGrid		oGridPage009	= new ownGrid(2);
		ownGrid		oGridPage009a	= new ownGrid(2);    //2010-12-27: meldungen in die maske
		ownGrid		oGridPage010	= new ownGrid(1);
		ownGrid		oGridPage011	= new ownGrid(1);
		ownGrid		oGridPage012	= new ownGrid(1);
		ownGrid		oGridPage013	= new ownGrid(1);
		ownGrid		oGridPage014	= new ownGrid(1);
		int[] iSpalten14a = {600,60,60};
		ownGrid     oGridPage014a	= new ownGrid(iSpalten14a);    //2013-05-31: zusatztabeller fuer AVV-Codes
		ownGrid     oGridPage015	= new ownGrid(1);
		ownGrid     oGridPage016	= new ownGrid(2);
		ownGrid     oGridPage017	= new ownGrid(1);              //2014-01-17: neue Tochtertabelle: Rechnungs- und Gutschriftspositionen

		//2018-12-14: casten fuer tabactionagent zu interface IF_MaskDaughter_Activater statt zu XX_FullDaughter
		oTabbedPane.add_Tabb(new MyE2_String(FS_Mask.cTab1OrigText),		oGridPage000,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Mail/ WWW"),					oGridPage001,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Status"),						oGridPage002,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Ausstatt."),					oGridPage003,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Gesch. bez"),					oGridPage004,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Stammdat"),					oGridPage005,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String(FS_CONST.TABTEXT_FINANZEN_IN_ADRESSMASK),	oGridPage006,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Art. EK"),					oGridPage007,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Art. VK"),					oGridPage008,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String(FS_CONST.TABTEXT_INFOS_IN_ADRESSMASK),		oGridPage009,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Meld."),						oGridPage009a,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Mitarb."),					oGridPage010,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Konten"),						oGridPage011,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_KONTEN),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Lager./ Niederlass."),		oGridPage012,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Mat. Spez."),					oGridPage013,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ),oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Cont."),						oGridPage014,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("AVV- Codes"),					oGridPage014a,new tabbActionAgent(null,oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Ford. Status"), 				oGridPage015,new tabbActionAgent((IF_MaskDaughter_Activater)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_INFO_KUNDENSTATUS),oE2_MAP_ADRESSE));

		//2013-11-19
		oTabbedPane.add_Tabb(new MyE2_String("Transport Kosten"),			oGridPage016,new tabbActionAgent((FSK__FULL_MASK_DAUGHTER_TP_KOSTEN)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE),oE2_MAP_ADRESSE));
	
		//2014-01-17: 
		oTabbedPane.add_Tabb(new MyE2_String("Rech./ Gut."),				oGridPage017,new tabbActionAgent((XX_FULL_DAUGHTER)oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_RECH_GUT_POS),oE2_MAP_ADRESSE));
		
		
		//this.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_ADRESS_INFO));
		this.add(oTabbedPane,E2_INSETS.I_0_0_0_0);
		
		
		Insets oIN = new Insets(5,2,0,1);

		E2_MaskFiller oFiller = new E2_MaskFiller(oE2_MAP_ADRESSE,oE2_MAP_ADRESSE.get_E2_ComponentMAP_Firmeninfo(),null,oIN,oIN, new Alignment(Alignment.LEADING, Alignment.CENTER));
		
		//alignment fuer abeichende zeilen (vom schema left/center)
		Alignment oAlignLeftTop = new Alignment(Alignment.LEFT, Alignment.TOP);
		
	
		//hilfsgrid fuer die einstufung des kunden
		int[] iGridSonderspalte1 = {110,110,65,65};
		MyE2_Grid oGridSonderspalte1 = new MyE2_Grid(iGridSonderspalte1, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());

		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("Einstufung als steuerlicher Unternehmer"),new E2_FontBoldItalic(-2),false), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,1,2,1),null,2,null));
		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String(""),new E2_FontBoldItalic(-2),true), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,1,2,1),null,2,null));
		
		
//		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("Einstufung: Privat = Adresse IST KEIN steuerlicher Unternehmer"),new E2_FontBoldItalic(-2),true), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,1,2,1)));
//		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("Einstufung: Firma = ADRESSE IST steuerlicher Unternehmer"),new E2_FontBoldItalic(-2),true), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,1,2,1)));
		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("JA (\"FIRMA\")"),new E2_FontBoldItalic(-2),true), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,1,2,1)));
		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("NEIN (\"PRIVAT\")"),new E2_FontBoldItalic(-2),true), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,1,2,1)));
		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("Barkunde"),new E2_FontItalic(-2),false), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(1,1,1,1)));
		oGridSonderspalte1.add(new MyE2_Label(new MyE2_String("Scheckdruck"),new E2_FontItalic(-2),false), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(1,1,1,1)));
		oGridSonderspalte1.add(oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$FIRMA), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(1,1,1,1)));
		oGridSonderspalte1.add(oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$PRIVAT), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(1,1,1,1)));
		oGridSonderspalte1.add(oE2_MAP_ADRESSE.get_Comp(_DB.ADRESSE$BARKUNDE), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(1,1,1,1)));
		oGridSonderspalte1.add(oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$SCHECKDRUCK_JN), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(1,1,1,1)));
		
		
		//hilfsgrid fuer die Anzeige des Kundenstatus bezuegl. Steuerbewertung
		
		
		//die esten 5 Zeilen manuell eintragen, um die Sonderanzeigen unterzubringen (mehrzeilig) auf den Spalten 5 und 6
		oFiller.add_Line_OA(oGridPage000, new MyE2_String("ID-Adresse"),1, "ID_ADRESSE|# |#(ID-Firmeninfo|ID_FIRMENINFO|#)|",3);
		oGridPage000.add_RAW(oGridSonderspalte1,2,2,oIN,null);   //ueber 2 zeilen
		oFiller.add_Line_OA(oGridPage000,"#ID-Anrede",1,"ID_ANREDE|#|",3);
//		oFiller.add_Line(oGridPage000,"#ID-Anrede",1,"ID_ANREDE|#    |#    |#    |BARKUNDE|#   |SCHECKDRUCK_JN",5);
		oFiller.add_Line_OA(oGridPage000,"#Vorname",1,"VORNAME",3);
		oGridPage000.add_RAW(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMP_ANZEIGE_STEUER_STATUS),2,6,oIN,null);   //ueber 5 zeilen
		oFiller.add_Line_OA(oGridPage000,"#Name 1",1,"NAME1",3);
		oFiller.add_Line_OA(oGridPage000,"#Name 2",1,"NAME2",3);
		oFiller.add_Line_OA(oGridPage000,"#Name 3",1,"NAME3",3);
		oFiller.add_Line_OA(oGridPage000,"#Strasse - Hnr",1,"STRASSE|# |HAUSNUMMER",3);
		oFiller.add_Line_OA(oGridPage000,"#Land - PLZ - Ort",1,"ID_LAND|PLZ|ORT",3);
		oGridPage000.set_SpaltenZaehler(0);
		
//		oFiller.add_Line(oGridPage000,"#Ortzusatz",1,"ORTZUSATZ",5);
//		
//		oFiller.add_Line(oGridPage000,"#PLZ - Fach",1,"PLZ_POB|POB|IS_POB_ACTIVE",5);
//		oFiller.add_Line(oGridPage000,"#Rechtsform",1,"ID_RECHTSFORM|# ",5);

		//20180129: gps-block
		FS_MaskGridShowGpsComponents gpsGrid = new FS_MaskGridShowGpsComponents(	oE2_MAP_ADRESSE.get_Comp(ADRESSE.longitude.fn())
																,oE2_MAP_ADRESSE.get_Comp(ADRESSE.latitude.fn())
																, oE2_MAP_ADRESSE.get_Comp(EN_FS_Fields.GPS_BUTTON_SEARCH.name())
																, oE2_MAP_ADRESSE.get_Comp(EN_FS_Fields.GPS_BUTTON_VIEW_IN_MAP.name())
																, oE2_MAP_ADRESSE.get_Comp(EN_FS_Fields.GPS_BUTTON_VIEW_ALL_IN_MAP.name() )
																);
		oFiller.add_Line_OA(oGridPage000,"#Ortzusatz",1,"ORTZUSATZ",3);
		oGridPage000.add_RAW(gpsGrid,2,3,oIN,null);   //ueber 3 zeilen

		oFiller.add_Line_OA(oGridPage000,"#PLZ - Fach",1,"PLZ_POB|POB|IS_POB_ACTIVE",3);
		oFiller.add_Line_OA(oGridPage000,"#Rechtsform",1,"ID_RECHTSFORM|# ",3);

		

		
		
		oFiller.add_Line(oGridPage000,"#Sprache",1,"ID_SPRACHE|# |#Betreuer|ID_USER<W150W>|# |#Betreuer (2)|ID_USER_ERSATZ<W150W>|# |#Sachbearbeiter|ID_USER_SACHBEARBEITER<W150W>",5,new Alignment(Alignment.LEFT,Alignment.CENTER));
		
		oGridPage000.add_sep();
	
		oFiller.add_Line(oGridPage000,"#Tel-/Fax-Nummern",1,FS_CONST.MASK_FIELD_DAUGHTER_TELEFON,5,oAlignLeftTop);
		
		// Tabreiter Mail/Internet
		oGridPage001.add(new MyE2_Label(" "),2, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		oGridPage001.add(new MyE2_Label("Mail-Adressen der Firma"),2,oIN);
		oGridPage001.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_EMAIL),2,1,oIN, new Alignment(Alignment.LEFT,Alignment.TOP));
		oGridPage001.add_sep();
		oGridPage001.add(new MyE2_Label("Internet-Adressen der Firma"),2,oIN);
		oGridPage001.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_INTERNET),2,1,oIN, new Alignment(Alignment.LEFT,Alignment.TOP));
		
		
		
		// Tabreiter Status
		
		oFiller.add_Line(oGridPage002,"#Adress-Art/Klasse",1,				FS_CONST.MASK_FIELD_CROSS_CONNECT_TYP,3,oAlignLeftTop);
		oGridPage002.add_sep();
		//subgrid fuer die branchen-komponenten
		FS_Component_MASK_WeitereBranchen weitereBranchen = (FS_Component_MASK_WeitereBranchen)oE2_MAP_ADRESSE.get__Comp(FS_CONST.MASK_FIELD_CROSS_CONNECT_ZUSATZBRANCHE);
		E2_Grid4MaskSimple helpGrid = new E2_Grid4MaskSimple()
											.def_(oE2_MAP_ADRESSE.get_E2_ComponentMAP_Firmeninfo())
											.add_(FIRMENINFO.id_branche)
											.add_(new MyE2_String("Weitere Branchen: "))
											.def_(20).add_(weitereBranchen.get_popupAddBranche())
											.def_(1000).add_(weitereBranchen);
		oFiller.add_Line(oGridPage002,"#Branche",1,							helpGrid,3,oAlignLeftTop);
		oFiller.add_Line(oGridPage002,"#Branche (AVV-Code)",1,				"ID_EAK_BRANCHE",1,"# ",1);
		
		//2016-04-28: kundenmerkmale einblendbar machen
		
		oGridPage002.add_sep();
//		oFiller.add_Line(oGridPage002,"#Kundenmerkmale",1, "ID_ADRESSE_MERKMAL1|# (M1)|",1,"ID_ADRESSE_MERKMAL2|# (M2)",1,"ID_ADRESSE_MERKMAL3|# (M3)",1);
//		oFiller.add_Line(oGridPage002,"# ", 			1, "ID_ADRESSE_MERKMAL4|# (M4)|",1,"ID_ADRESSE_MERKMAL5|# (M5)",1,"# ",1);


		E2_Grid4MaskSimple  subGrid = new E2_Grid4MaskSimple();
		subGrid._add(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.id_adresse_merkmal1), new RB_gld()._ins(E2_INSETS.I(0,1,0,1)))
				._add(new MyE2_Label("(M1)"), new RB_gld()._ins(E2_INSETS.I(5,1,104,1)))
				._add(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.id_adresse_merkmal2), new RB_gld()._ins(E2_INSETS.I(0,1,0,1)))		
				._add(new MyE2_Label("(M2)"), new RB_gld()._ins(E2_INSETS.I(5,1,104,1)))
				._add(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.id_adresse_merkmal3), new RB_gld()._ins(E2_INSETS.I(0,1,0,1)))		
				._add(new MyE2_Label("(M3)"), new RB_gld()._ins(E2_INSETS.I(5,1,20,1)))
				._add(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.id_adresse_merkmal4), new RB_gld()._ins(E2_INSETS.I(0,1,0,1)))		
				._add(new MyE2_Label("(M4)"), new RB_gld()._ins(E2_INSETS.I(5,1,104,1)))
				._add(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.id_adresse_merkmal5), new RB_gld()._ins(E2_INSETS.I(0,1,0,1)))		
				._add(new MyE2_Label("(M5)"), new RB_gld()._ins(E2_INSETS.I(5,1,15,1)))
				._setSize(200,30,200,30,200,30);

	//	E2_ExpandableRow_SAVEABLE saveRow = new E2_ExpandableRow_SAVEABLE(new MyE2_String("save"), new Border(new Extent(1),new E2_ColorBase(),Border.STYLE_SOLID), new Border(new Extent(1),new E2_ColorBase(),Border.STYLE_SOLID), SAVE_KEYS.ADRESS_MASK_KUNDENMERKMALE_BLOCK_OPEN_CLOSE);
		E2_ExpandableRow_SAVEABLE saveRow = new E2_ExpandableRow_SAVEABLE(	new MyE2_Label(new MyE2_String("Kundenmerkmale sind geschlossen")), 
																			subGrid,
																			null,
																			null,
																			true,
																			false,
																			bibARR.ia(820,15),SAVE_KEY.ADRESS_MASK_KUNDENMERKMALE_BLOCK_OPEN_CLOSE);
		saveRow.get_oButtonClose().setToolTipText(new MyE2_String("Schliessen des Blocks <Kundenmerkmale>").CTrans());
		saveRow.get_oButtonOpen().setToolTipText(new MyE2_String("Öffnen des Blocks <Kundenmerkmale>").CTrans());
		//saveRow.get_oInnerContainer().add(new MyE2_Label("TEST"));
		
		oFiller.add_Line(oGridPage002, "#Kundenmerkmale",1,saveRow,3,oAlignLeftTop);
		

		oFiller.add_Line(oGridPage002,"#Liefmenge/Monat",1,	"LIEFERMENGE_SCHNITT|#(Durchsch.)",1,"LIEFERMENGE_MAX|#(Max.)",1,"# ",1);
		
		
		int[] iSpalten2 = {250,250};
		MyE2_Grid oGridHelp = new MyE2_Grid(iSpalten2,0);
		Insets iField = new Insets(0,2,0,2);
		
		//zeile 1
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp("AKTIV"),iField);
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp("TRANSFER"),iField);
		
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp("GUTSCHRIFTEN_SPERREN"),iField);
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp("RECHNUNGEN_SPERREN"),iField);
		
		//2012-08-09: neue sperrvermerke
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp("WARENEINGANG_SPERREN"),iField);
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp("WARENAUSGANG_SPERREN"),iField);
		
		
		//zeile 2
		oGridHelp.add(oE2_MAP_FIRMENINFO.get_Comp("ABLADEMENGE_FUER_GUTSCHRIFT"),iField);
		oGridHelp.add(oE2_MAP_FIRMENINFO.get_Comp("LADEMENGE_FUER_RECHNUNG"),iField);
		
		//20170926: 
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp(ADRESSE.ah7_quelle_sicher.fn()),iField);
		oGridHelp.add(oE2_MAP_ADRESSE.get_Comp(ADRESSE.ah7_ziel_sicher.fn()),iField);
		
		
		oFiller.add_Line_WithComponents(oGridPage002, new MyE2_Label(new MyE2_String("Einstellungen")), oGridHelp, 3, null, null, 1, null, null, 1, null,oAlignLeftTop);


		oGridPage002.add_sep();
		oFiller.add_Line(oGridPage002,"#Potential",1,"ID_ADRESSE_POTENTIAL|#  ",3);
		oGridPage002.add_sep();
		oFiller.add_Line(oGridPage002,"#Öffnungszeiten",1,	"OEFFNUNGSZEITEN",3);
		oGridPage002.add_sep();
		
		
		oFiller.add_Line(oGridPage002,"#Telefonfeld EU-Blatt:",1, "EU_BEIBLATT_TEL",1,"#Telefaxfeld EU-Blatt:",1,"EU_BEIBLATT_FAX",1);
		oFiller.add_Line(oGridPage002,"#Ansprechpa. EU-Blatt:",1, "EU_BEIBLATT_ANSPRECH",1,"#e-Mail EU-Blatt:",1,"EU_BEIBLATT_EMAIL",1);
		oFiller.add_Line(oGridPage002,"#EU-Vertrag (EK) bis Datum:",1, "EU_BEIBLATT_EK_VERTRAG",1,"#EU-Vertrag (VK) bis Datum:",1,"EU_BEIBLATT_VK_VERTRAG",1);
		//2014-07-10: neues Info-Feld zu EU-Vertraegen
		oFiller.add_Line(oGridPage002,"#Informationen zu EU-Verträgen",	1,RECORD_ADRESSE.FIELD__EU_BEIBLATT_INFOFELD,3);
		
		//2014-11-17: definition, welches vertragsformular gilt
		oFiller.add_Line(oGridPage002,"#Gültiges EU-Vertragsformular",	1,_DB.ADRESSE$ID_ADRESSE_EU_VERTR_FORM,3);
		
		

		//2012-03-05: neue felder Lager-felder
		oGridPage002.add_sep();
		oFiller.add_Line(oGridPage002,"#Hauptlager/Firmensitz:",	4);
		oFiller.add_Line(oGridPage002,"#Sachbearbeiter:",			1,RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB+"|#|#Zuständ.Händler:|"+_DB.ADRESSE$ID_USER_LAGER_HAENDLER,3);
//		oFiller.add_Line(oGridPage002,"#Sachbearbeiter:",			1,RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB,3);
//		oFiller.add_Line(oGridPage002,"#Zuständ.Händler",			1,RECORD_ADRESSE.FIELD__ID_USER_LAGER_HAENDLER,3);
		oFiller.add_Line(oGridPage002,"#Verwaltungsinfo",			1,RECORD_ADRESSE.FIELD__LAGER_KONTROLLINFO,3);
		

		//2014-11-27: neue felder, einblenden der aenderungdaten
		E2_Subgrid_4_Mask  gridSub = new E2_Subgrid_4_Mask();
		gridSub.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		gridSub.FILL_WITH(oE2_MAP_ADRESSE, "Erstellt von|Erstellungsdatum|Letzte Änderung von|Zeitstempel letzte Änderung", 
											_DB.ADRESSE$ERZEUGT_VON+"|"+
											_DB.ADRESSE$ERZEUGT_AM+"|"+
											_DB.ADRESSE$GEAENDERT_VON+"|"+
											_DB.ADRESSE$LETZTE_AENDERUNG+"|"
											, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(1,1,10,1), new E2_ColorDDark(), 1, 1) 
											, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(1,1,10,1), new E2_ColorBase(), 1, 1));
		
		oFiller.add_Line(oGridPage002,"#Entstehung", 1,gridSub,3);
		
		
		// Tabreiter Ausstattung
		oGridPage003.add(new MyE2_Label(" "),2, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		oGridPage003.add(new MyE2_Label(new MyE2_String("Firmen-Ausstattung")),2,oIN);
		oGridPage003.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_CROSS_CONNECT_AUSTATTUNG),2,oIN);
		
		// Tabreiter Beziehungen
		oGridPage004.add(new MyE2_Label("Geschäftsbeziehungen"),2,oIN);
		oGridPage004.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_BEZIEHUNG),2,oIN);

		
		
		
		
		// 2012-08-03: Besser lesbar tabreiter Stammdaten 
		oFiller.add_Line(oGridPage005,"#Bem.1 (700 Zeichen)",1,			"BEMERKUNGEN",6);
		oFiller.add_Line(oGridPage005,"#Bem.2 (250 Zeichen)",1,			"BESCHREIBUNG",6);
		oGridPage005.add_sep();
//2014-03-11: ausweisnummer zu den finanzdaten		
//		oFiller.add_Line(oGridPage005,"#Geburtstag",1,					"GEBURTSDATUM",1,"#Ausweis-Nr.",1,"AUSWEIS_NUMMER",2,"#gültig bis",1,"AUSWEIS_ABLAUF_DATUM", 1);
//		oFiller.add_Line(oGridPage005,"#Geburtstag",1,					"GEBURTSDATUM",6);
//		oGridPage005.add_sep();
		oFiller.add_Line(oGridPage005,"#Erstkontakt",1,					"ERSTKONTAKT",1,"#Zahl Mitarbeiter",1,"ZAHL_ANGESTELLTE|# am|",1,"ZAHLANGESTELLTE_AM",1,"#Aquisitionskosten",1,"AQUISITIONSKOSTEN",1);
		oFiller.add_Line(oGridPage005,"#Gründungsdatum",1,				"GRUENDUNGSDATUM",1,"#Stammkapital",1,"STAMMKAPITAL",1,"# ",3);
		oFiller.add_Line(oGridPage005,"#Dokument-Kopien",1,				"DOKUMENTKOPIEN",1,"#Besuchsrythmus",1,"BESUCHSRYTHMUS",1,"# ",3);
		oGridPage005.add_sep();
		
		oFiller.add_Line(oGridPage005,"# ",1, "#Bemerkung Fahrplan",3,"#Lieferinfo TPA",3);
		oFiller.add_Line(oGridPage005,"#Beleginfos",1, "BEMERKUNG_FAHRPLAN",3,"LIEFERINFO_TPA",3);
	
		
		
//		
//		// tabreiter Finanzen
//		
//		//20171123: feldbreiten angleichen (auf gridpage 006)
		oE2_MAP_ADRESSE._setWidth(300, 			/**ADRESSE.id_waehrung,**/
												ADRESSE.id_lieferbedingungen
												,ADRESSE.id_lieferbedingungen_vk
												,ADRESSE.id_zahlungsbedingungen
												,ADRESSE.id_zahlungsbedingungen_vk
												,ADRESSE.rechnung_per_fax
												,ADRESSE.ausweis_nummer
												,ADRESSE.ausweis_ablauf_datum
												,ADRESSE.geburtsdatum

												); 
		oE2_MAP_FIRMENINFO._setWidth(300, 	 FIRMENINFO.kreditbetrag_intern 
											,FIRMENINFO.kreditbetrag_intern_valid_to 
											,FIRMENINFO.verlaengert_eigentum_vorbehalt
											,FIRMENINFO.forderungsverrechnung
											,FIRMENINFO.id_zahlungsmedium
											,FIRMENINFO.handelsregister
											,FIRMENINFO.steuernummer
											);
		
		oGridPage006.addSeparator();
		FS_MASK_FullDaughter_Kreditversicherung oDaughter = (FS_MASK_FullDaughter_Kreditversicherung)oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_DAUGHTER_KREDITVERSICHERUNG);
		
		//oFiller.add_Line(oGridPage006,"#Kreditversicherung (NEU)",		1,	FS_CONST.MASK_FIELD_DAUGHTER_KREDITVERSICHERUNG,6,oAlignLeftTop);

		
		//2011-09-20: kreditversicherungsinlay
		oFiller.add_Line(oGridPage006,"#Kreditversicherung",			1,	oDaughter.get_InBorderGrid(new Border(0,new E2_ColorDDark(), Border.STYLE_SOLID), new Extent(99,Extent.PERCENT), E2_INSETS.I_0_0_0_0),6,oAlignLeftTop);
		oFiller.add_Line(oGridPage006,"#Zusätz.interner Kreditrahmen",1,"KREDITBETRAG_INTERN",			3, "#gültig bis:",								1,"KREDITBETRAG_INTERN_VALID_TO",			2);
		
		oGridPage006.addSeparator();
		oFiller.add_Line(oGridPage006,"#Verläng.Eigent.Vorbeh.",		1, "VERLAENGERT_EIGENTUM_VORBEHALT",	3,"#Forderungsverrechnungsvereinbarung",	1,"FORDERUNGSVERRECHNUNG",			2);
		
		
		oGridPage006.addSeparator();
		//2018-05-28: zusatzwaehrungen
		E2_Grid gWaehrungen = new E2_Grid()._setSize(65,65,200);
		gWaehrungen._a(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.id_waehrung), new RB_gld()._ins(0,0,2,0)._left_mid());
		gWaehrungen._a(new RB_lab()._t(S.ms("Weitere: ")), new RB_gld()._ins(0,0,4,0)._right_mid());
		gWaehrungen._a(oE2_MAP_ADRESSE.get__CompEcho(EN_FS_Fields.MASK_FIELD_ZUSATZWAEHRUNGEN.name()), new RB_gld()._ins(0,0,2,0)._left_mid());
		
//		oFiller.add_Line(oGridPage006,"#Währung",						1,	"ID_WAEHRUNG",				3,"#Zahlungsmedium:",		 	1,"ID_ZAHLUNGSMEDIUM",			2);
		oFiller.add_Line(oGridPage006,"#Währung",						1,	gWaehrungen,				3,"#Zahlungsmedium:",		 	1,"ID_ZAHLUNGSMEDIUM",			2);
		oFiller.add_Line(oGridPage006,"#Lieferbedingungen (EK)",		1,	"ID_LIEFERBEDINGUNGEN",		3,"#Zahlungsbedingungen (EK) ",	1,"ID_ZAHLUNGSBEDINGUNGEN",		2);
		oFiller.add_Line(oGridPage006,"#Lieferbedingungen (VK)",		1,	"ID_LIEFERBEDINGUNGEN_VK",	3,"#Zahlungsbedingungen (VK) ",	1,"ID_ZAHLUNGSBEDINGUNGEN_VK",	2);
		//2013-12-11: felder "OHNE_STEUER_WARENEINGANG","OHNE_STEUER_WARENAUSGANG" weg
//		oFiller.add_Line(oGridPage006,"#Steuerdefinition",				1,	"OHNE_STEUER_WARENEINGANG",			1,"OHNE_STEUER_WARENAUSGANG",	1,"#Rechnung per Fax:",2,"RECHNUNG_PER_FAX<W300W>",2);
		
		oFiller.add_Line(oGridPage006,"#Rechnung per Fax:",				1,  "RECHNUNG_PER_FAX",			3,"#Beanstandungsmeldungen intern ",1,FIRMENINFO.fbam_nur_intern.fn(),  2 );
		
		oGridPage006.addSeparator();
//		oFiller.add_Line(oGridPage006,	"#Steuernummer",				1,	
//										"STEUERNUMMER<W150W>",			1,
//										"#Handelsreg.",					1,
//										"HANDELSREGISTER<W150W>",		2,	
//										"#BetriebsNr.-SAA",				1,
//										"BETRIEBSNUMMER_SAA",			1);

//		oFiller.add_Line(oGridPage006,	"#Handelsreg.",					1,	"HANDELSREGISTER<W150W>",		3,	"#BetriebsNr.-SAA",				1,		"BETRIEBSNUMMER_SAA",			2);
		
		//20171122: neues maskenlayout in finanz/handel
		//E2_Grid g_help1 = new E2_Grid()._s(2)._a_lm(oE2_MAP_FIRMENINFO.get__CompEcho(FIRMENINFO.debitor_nummer))._a(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_BUTTON_DEB_KRED_ZAUBERSTAB));
//		E2_Grid g_help2 = new E2_Grid()._s(2)._a_lm(new RB_lab()._tr("Abfallbehandlung"))._a(oE2_MAP_FIRMENINFO.get__CompEcho(FIRMENINFO.verwertungsverfahren),new RB_gld()._ins(3,0,0,0)._left_mid());
		
//		oFiller.add_Line(oGridPage006,	"#Kreditor-Nr.",				1,	
//										"KREDITOR_NUMMER",				1,
//										"#Debitor-Nr.",					1,
//										"DEBITOR_NUMMER|#|"+FS_CONST.MASK_FIELD_BUTTON_DEB_KRED_ZAUBERSTAB,		2,
//										"#Abfallbehandlung",			1,
//										_DB.FIRMENINFO$VERWERTUNGSVERFAHREN,1);

//		oFiller.add_Line(oGridPage006,	"#Kreditor-Nr.",				1,	
//										"KREDITOR_NUMMER",				3,
//										"#Debitor-Nr.",					1,
//										g_help1,						1,
//										g_help2,						1
//										);

		RB_gld lt = new RB_gld()._ins(2)._col(new E2_ColorDark());
		RB_gld li = new RB_gld()._ins(2);
		E2_Grid g_nums = new E2_Grid()._setSize(80,20,80,80,80);
		
		g_nums	._a(new RB_lab()._tr("Kreditor-Nr")._fsa(-2)._i(), lt)
				._a(new RB_lab()._tr("")._fsa(-2)._i(), lt)
				._a(new RB_lab()._tr("Debitor-Nr")._fsa(-2)._i(), lt)
				._a(new RB_lab()._tr("Alt.Lief.-Nr")._fsa(-2)._i(), lt)
				._a(new RB_lab()._tr("Alt.Abn.-Nr")._fsa(-2)._i(), lt)
				._a(oE2_MAP_FIRMENINFO.get__CompEcho(FIRMENINFO.kreditor_nummer),li)
				._a(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_BUTTON_DEB_KRED_ZAUBERSTAB))
				._a(oE2_MAP_FIRMENINFO.get__CompEcho(FIRMENINFO.debitor_nummer),li)
				._a(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.lief_nr),li)
				._a(oE2_MAP_ADRESSE.get__CompEcho(ADRESSE.abn_nr),li)
				;
		
//		E2_Grid g_schalter = new E2_Grid()._setSize(200,200)
//								._a(oE2_MAP_FIRMENINFO.get__CompEcho(FIRMENINFO.akonto),li)
//								._a(oE2_MAP_FIRMENINFO.get__CompEcho(FIRMENINFO.keine_mahnungen),li);
//		
		
//		MyE2_DB_CheckBox  cbAKONTO = (MyE2_DB_CheckBox)oE2_MAP_FIRMENINFO.get("AKONTO");
//
//		//2012-01-18: nicht-mahnen-schalter
//		MyE2_DB_CheckBox  cbNICHT_MAHNEN = (MyE2_DB_CheckBox)oE2_MAP_FIRMENINFO.get("KEINE_MAHNUNGEN");
		
//		oFiller.add_Line(oGridPage006,"#ALT:Lief-Nr.",			1,	"LIEF_NR",							1,"#ALT:Abn-Nr.",				1,"ABN_NR",					2,
//																	cbAKONTO.get_InBorderGrid(null, null, E2_INSETS.I_5_5_5_5),1,
//																	cbNICHT_MAHNEN.get_InBorderGrid(null, null, E2_INSETS.I_5_5_5_5),1);
		oFiller.add_Line(oGridPage006,"#Handelsreg.",		1,	"HANDELSREGISTER",	3,	"#BetriebsNr.-SAA",	 1,	"BETRIEBSNUMMER_SAA",					1, FIRMENINFO.akonto.fn(),1);
		oFiller.add_Line(oGridPage006,"#Nummern",			1,	g_nums,				3,	"#Abfallbehandlung", 1,	FIRMENINFO.verwertungsverfahren.fn(),	1, FIRMENINFO.keine_mahnungen.fn(),1);

		
		
		oGridPage006.addSeparator();
		//oFiller.add_Line(oGridPage006,"#Lieferanten-MWSt.",		1,	FS_CONST.MASK_FIELD_CROSS_CONNECT_MWST,3,"#Steuerstatus",1,oGridSteuerSchalter,2,oAlignLeftTop);
		//2013-11-13: neudefinition der steuersachverhalte
		oFiller.add_Line(oGridPage006,"#Lieferanten-MWSt.",		1,	FS_CONST.MASK_FIELD_CROSS_CONNECT_MWST,6,oAlignLeftTop);
		oGridPage006.addSeparator();
		
		
		
		//2014-03-11: steuernummer und ausweisnummer auf dem reiter finanz/handel
		
//		oFiller.add_Line(oGridPage006,"#Ausweisnummer", 						1,	_DB.ADRESSE$AUSWEIS_NUMMER+"|# |#Ausweis-Ablaufdatum:|# |"+_DB.ADRESSE$AUSWEIS_ABLAUF_DATUM+"|#    |#Geburtstag:|#   |"+_DB.ADRESSE$GEBURTSDATUM,6,oAlignLeftTop);
//		oFiller.add_Line(oGridPage006,"#Steuernummer", 							1,	_DB.FIRMENINFO$STEUERNUMMER,6,oAlignLeftTop);

		oFiller.add_Line(oGridPage006,"#Ausweisnummer", 1,	_DB.ADRESSE$AUSWEIS_NUMMER,		3,	"#Ausweis-Ablaufdatum:",1,	_DB.ADRESSE$AUSWEIS_ABLAUF_DATUM,	2);
		oFiller.add_Line(oGridPage006,"#Steuernummer", 	1,	_DB.FIRMENINFO$STEUERNUMMER,	3,	"#Geburtstag:",			1,	_DB.ADRESSE$GEBURTSDATUM,			2);

		oGridPage006.addSeparator();
		
//		oFiller.add_Line(oGridPage006,"#Einstuf.:FIRMA (ohne UST-ID)", 		1,	_DB.FIRMENINFO$FIRMA_OHNE_USTID+"|# |",6,oAlignLeftTop);
//		oFiller.add_Line(oGridPage006,"#Einstuf.:PRIVAT(trotz UST-ID)", 	1,	_DB.FIRMENINFO$PRIVAT_MIT_USTID+"|# |",6,oAlignLeftTop);
		E2_Grid  g_help3 = new E2_Grid()._s(2)._a(oE2_MAP_FIRMENINFO.get_Comp(FIRMENINFO.firma_ohne_ustid.fn()),new RB_gld()._ins(0,1,1,1))._a(S.ms("FIRMA (ohne UST-ID)"),new RB_gld()._ins(3,1,1,1));
		E2_Grid  g_help4 = new E2_Grid()._s(2)._a(oE2_MAP_FIRMENINFO.get_Comp(FIRMENINFO.privat_mit_ustid.fn()),new RB_gld()._ins(0,1,1,1))._a(S.ms("PRIVAT(trotz UST-ID)"),new RB_gld()._ins(3,1,1,1));
		oFiller.add_Line(oGridPage006,"#Einstufung:", 							1,	g_help3,3,g_help4,3);
		
		
		
		// Added: 30 am Ende
		int[] iB_UST = {30,80,80,160,30};
		MyE2_Grid  oGridHelpUST = new MyE2_Grid(iB_UST, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		((MyE2_DB_TextField)oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$UMSATZSTEUERLKZ)).set_iWidthPixel(50);
		((MyE2_DB_TextField)oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$UMSATZSTEUERID)).set_iWidthPixel(150);
		
		oGridHelpUST.add(new MyE2_Label(""));
		oGridHelpUST.add(new MyE2_Label(""));
		oGridHelpUST.add(new MyE2_Label(new MyE2_String("Basis-UST-LKZ"),new E2_FontItalic(-2)), E2_INSETS.I(3,0,5,0));
		oGridHelpUST.add(new MyE2_Label(new MyE2_String("Basis-UST-ID"),new E2_FontItalic(-2)), E2_INSETS.I(3,0,5,0));
		//Added
		oGridHelpUST.add(new MyE2_Label("OK?"));
		oGridHelpUST.add(new MyE2_Label(""));
		oGridHelpUST.add(new MyE2_Label(""));
		oGridHelpUST.add(oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$UMSATZSTEUERLKZ), E2_INSETS.I(3,0,5,0));
		oGridHelpUST.add(oE2_MAP_FIRMENINFO.get_Comp(_DB.FIRMENINFO$UMSATZSTEUERID), E2_INSETS.I(3,0,5,0));
		
		oGridHelpUST.add(oE2_MAP_FIRMENINFO.get_Comp(FS_CONST.MASK_BT_CHECK_USTID));

		oFiller.add_Line(oGridPage006,"#Umsatzsteuer-ID (Firmensitz)",			1,	oGridHelpUST,6,oAlignLeftTop);
		oFiller.add_Line(oGridPage006,"#Umsatzsteuer-ID (weitere)",				1,	FS_CONST.MASK_FIELD_DAUGHTER_UST_IDS,6,oAlignLeftTop);
		
		
		// tabreiter kundenspezifische artikelbezeichnungen
		oGridPage007.add(new MyE2_Label("Kundenspezifische Artikelbezeichungen EK-Artikel"),2,1,oIN, new Alignment(Alignment.LEFT,Alignment.TOP));
		oGridPage007.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK),2,oIN);  //NEU_09
		

		// tabreiter kundenspezifische artikelbezeichnungen
		oGridPage008.add(new MyE2_Label("Kundenspezifische Artikelbezeichungen VK-Artikel"),2,1,oIN, new Alignment(Alignment.LEFT,Alignment.TOP));
		oGridPage008.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK),2,oIN);  //NEU_09

		
		// tabreiter Zusatzinfos
		oGridPage009.add(new MyE2_Label("Kunden-Zusatzinfos"),2,oIN);
		oGridPage009.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS),2,oIN);

		// tabreiter meldungen
		oGridPage009a.add(new MyE2_Label("Kunden-Meldungen für internen Ablauf"),2,oIN);
		oGridPage009a.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES),2,oIN);

		
		// tabreiter Mitarbeiter
		oGridPage010.add(new MyE2_Label("Mitarbeiter"),1,oIN);
		oGridPage010.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER),1,oIN);
		
		// tabreiter konten
		oGridPage011.add(new MyE2_Label("Konten"),1,oIN);
		oGridPage011.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_KONTEN),1,oIN);

		
		// tabreiter Lieferadressen
		oGridPage012.add(new MyE2_Label("Lieferadressen"),1,oIN);
		oGridPage012.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN),1,oIN);

		// tabreiter Lieferadressen
		oGridPage013.add(new MyE2_Label("Materialspezifikation"),1,oIN);
		oGridPage013.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ),1,oIN);
		
		
		// tabreiter erlaubte container
		oGridPage014.add(new MyE2_Label("Erlaubte Containertypen"),1,oIN);
		oGridPage014.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_CONTAINERTYPEN),1,oIN);
		
		
		//2013-05-31: tabreiter erlaubte Avv-Codes fuer die jeweilige Adresse (anlieferungen)
		oGridPage014a.add(new MyE2_Label("Zu dieser Adresse dürfen nur die folgenden AVV-Codes geliefert werden (falls leer, keine Beschränkung)",true),1,oIN);
		oGridPage014a.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_EXPORT),1,oIN);
		oGridPage014a.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_IMPORT),1,oIN);
		oGridPage014a.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT),3,oIN);
		
		// tabreiter Kundenstatus Forderungen / Verbindlichkeiten
		oGridPage015.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_INFO_KUNDENSTATUS),1,oIN);
		
		//2013-11-19 Transportkosten der Adresse
		oGridPage016.add(new E2_ComponentGroupHorizontal(0, 
								new MyE2_Label("Definition der Transportkosten (für Firmenadresse und alle Lager dieser Adresse)",true),
								oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_BUTTON_SUCHE_ALLE_KOSTEN_KOMBIS)
								,oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_BUTTON_SUCHE_PREISE_FUER_KOSTEN_KOMBIS)
								,E2_INSETS.I(0,1,30,1)),2,oIN);
		
		oGridPage016.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE),2,oIN);

		
		//2014-01-17: Rechnungs- / gutschriftspositionen
		oGridPage017.add(new MyE2_Label("Rechnungs- und Gutschriftspositionen zur Kontrolle",true),2,oIN);
		oGridPage017.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_RECH_GUT_POS),2,oIN);
		
		
		
		this.vMaskGrids.add(oGridPage000);
		this.vMaskGrids.add(oGridPage001);
		this.vMaskGrids.add(oGridPage002);
		this.vMaskGrids.add(oGridPage003);
		this.vMaskGrids.add(oGridPage004);
		this.vMaskGrids.add(oGridPage005);
		this.vMaskGrids.add(oGridPage006);
		this.vMaskGrids.add(oGridPage007);
		this.vMaskGrids.add(oGridPage008);
		this.vMaskGrids.add(oGridPage009);
		this.vMaskGrids.add(oGridPage009a);
		this.vMaskGrids.add(oGridPage010);
		this.vMaskGrids.add(oGridPage011);
		this.vMaskGrids.add(oGridPage012);
		this.vMaskGrids.add(oGridPage013);
		this.vMaskGrids.add(oGridPage014);
		this.vMaskGrids.add(oGridPage015);
		
		this.vMaskGrids.add(oGridPage016);
		this.vMaskGrids.add(oGridPage017);
		
		
	}
	
	
	
	private class ownGrid extends MyE2_Grid
	{

		public ownGrid(int iNumCols) 
		{
			super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			//super(MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
			
			this.setSize(iNumCols);
			this.setWidth(new Extent(100,Extent.PERCENT));
			
			this.add(new MyE2_Label(" "),iNumCols, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		}

		public ownGrid(int[] iSpalten)
		{
			super(iSpalten,0);
			//this.setWidth(new Extent(100,Extent.PERCENT));
			this.add(new MyE2_Label(" "),iSpalten.length, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		}
		
		
		public void add_sep()
		{
			this.NewLine();
			this.add(new Separator(),this.getSize(),E2_INSETS.I_2_0_2_0);
		}
		
		
	}
	
	
	
	/**
	 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
	 */
	private class tabbActionAgent extends XX_ActionAgent
	{
		private FS_MASK_ComponentMAP 		oE2_MAP_ADRESSE = null;
		private IF_MaskDaughter_Activater			oDaughterToActivate = null;
		
		public tabbActionAgent(IF_MaskDaughter_Activater activate, FS_MASK_ComponentMAP oe2_map_adresse)
		{
			super();
			this.oDaughterToActivate = activate;
			this.oE2_MAP_ADRESSE = oe2_map_adresse;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				//2018-12-14: casten fuer tabactionagent zu interface IF_MaskDaughter_Activater statt zu XX_FullDaughter

				
				//NEU_09
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK)).set_bIsActive(false);
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK)).set_bIsActive(false);
				
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES)).set_bIsActive(false);
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN)).set_bIsActive(false);
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_KONTEN)).set_bIsActive(false);
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ)).set_bIsActive(false);
				
				((IF_MaskDaughter_Activater) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_INFO_KUNDENSTATUS)).set_bIsActive(false);
				
				//2014-01-17: die fulldaughtermask fuer die Rechnungspositionen aufnehmern
				((IF_MaskDaughter_Activater) 				this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_DAUGHTER_RECH_GUT_POS)).set_bIsActive(false);
				
				//2014-03-12: daughter kostenliste mitaufnehmen, wegen der aufwendigen drop-down-querys
				((IF_MaskDaughter_Activater)  this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_DAUGHTER_TRANSPORTKOSTEN_ADRESSE)).set_bIsActive(false);
				
				
				if (this.oDaughterToActivate!=null) this.oDaughterToActivate.set_bIsActive(true);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("FS_Mask:tabbActionAgent:doAction:Error setting Complex-Objects!",false));
			}
			
			
			//2013-11-20: aktualisierung der Anzeigematrix auf seiten 1
			if (oExecInfo instanceof ExecINFO_OnlyCode_For_TabChangeAction) {
				String cTabTextOrig = ((ExecINFO_OnlyCode_For_TabChangeAction)oExecInfo).get_oAB().get_cOriginalTextAufTAB();
				
				if (cTabTextOrig.equals(FS_Mask.cTab1OrigText)) {
					E2_ComponentMAP  oMAP_ADRESSE = tabbActionAgent.this.oE2_MAP_ADRESSE;
					try {
						((__FS_MASKEN_INDIKATOR) oMAP_ADRESSE.get__Comp(FS_CONST.MASK_FIELD_COMP_ANZEIGE_STEUER_STATUS)).REFRESH_ANZEIGE();
					} catch (myException e) {
						e.printStackTrace();
					}

				}
				
			}
			
			
		}
		
	}




	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
	
	
}