package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

/*
* maskenvariante mit TabbedPane 
*/
public class USER__MASK extends MyE2_Column   implements IF_BaseComponent4Mask	
{
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public USER__MASK(USER__MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(500);
		oTabbedPaneMaske.set_bAutoHeight(true);
		
		// 5 Grids
		MyE2_Grid oGrid0 = new MyE2_Grid(4,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(4,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(4,0);
		MyE2_Grid oGrid2b = new MyE2_Grid(4,0);
		
		//2011-12-06: signatur
		MyE2_Grid oGrid3 = new MyE2_Grid(4,0);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Benutzer"), oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("eMail"), oGrid1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Sonderrechte"), oGrid2);
		//2014-02-17: neue Felder fuer die benutzereinstellungen der Masken-Warnhervorhebungen
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Erscheinungsbild"), oGrid2b);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Signatur"), oGrid3);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
		
		//hier kommen die Felder	
		oFiller.add_Line(oGrid0, new MyE2_String("ID"), 1, "ID_USER|#  |#Mandant:<L5L>|ID_MANDANT",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Anrede"), 1, "ANREDE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Vorname"), 1, "VORNAME|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Name 1"), 1, "NAME1|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Name 2"), 1, "NAME2|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Name 3"), 1, "NAME3|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Telefon"), 1, "TELEFON|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Telefax"), 1, "TELEFAX|#  |",3);

		oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_0_0_0);
		
		oFiller.add_Line(oGrid0, new MyE2_String("Benutzername"), 1, "NAME",1,"#Passwort|PASSWORT",1,"#Benutzergruppe|ID_USERGROUP",2);
//		oFiller.add_Line(oGrid0, new MyE2_String("Passwort"), 1, "PASSWORT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Benutzerkürzel"), 1, "KUERZEL",			1,"#",1,"#",1);
		oFiller.add_Line(oGrid0, new MyE2_String(""),				1, "AKTIV",				1,"IST_VERWALTUNG",1,_DB.USER$MELDUNG_KREDITVERS_ABLAUF,1);
		oFiller.add_Line(oGrid0, new MyE2_String(""), 				1, "GESCHAEFTSFUEHRER",	1,"IST_FAHRER",1,_DB.USER$MELDUNG_KREDITVERS_BETRAG,1);
		oFiller.add_Line(oGrid0, new MyE2_String(""),				1, "IST_SUPERVISOR",	1,"HAT_FAHRPLAN_BUTTON",1,"MENGENABSCHLUSS_FUHRE_OK",1);
		oFiller.add_Line(oGrid0, new MyE2_String(""), 				1, "FENSTER_MIT_SCHATTEN",1,"TEXTCOLLECTOR",1,"PREISABSCHLUSS_FUHRE_OK",1);
		
		
		
		oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_0_0_0);

		oFiller.add_Line(oGrid0, new MyE2_String("To-Do-Verwalter"), 1,	"TODO_SUPERVISOR|",1,"#",1,"#Sprache|ID_SPRACHE",1);
//		oFiller.add_Line(oGrid0, new MyE2_String(" "), 1, 				"IST_FAHRER",1,"",2);
		
		oFiller.add_Line(oGrid0, new MyE2_String("Schriftgrösse"), 1, "EIGENDEF_SCHRIFTGROESSE",1,"#",1,"#Offset<R15R>|VERTICAL_OFFSET_MASKTABS|#(Vertikaler Tab-Offset)",1);
		oFiller.add_Line(oGrid0, new MyE2_String("Laufzeit Sitzung"), 1, "LAUFZEIT_SESSION|#  |",3);
		
		oFiller.add_Line(oGrid1, new MyE2_String("EMAIL:"), 1, "EMAIL|#  |",3);
		oFiller.add_Line(oGrid1, new MyE2_String("MAIL_SIGNATUR:"), 1, "MAIL_SIGNATUR|#  |",3);
		
		
		
		//Tab: Sonderrechte
		oFiller.add_Line(oGrid2, new MyE2_String("Anzeige OP-Saldo:"), 1, "SONDERRECH_ZEIGE_OPLISTE_SALDO|#  |",3);
		
		//2014-10-13: developer-feld
		oFiller.add_Separator(oGrid2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid2, new MyE2_String("Entwicklermodus"), 1, _DB.USER$DEVELOPER+"|#  |",3);
		
		oFiller.add_Separator(oGrid2, E2_INSETS.I_0_0_0_0);
		oFiller.add_Line(oGrid2, new MyE2_String("Datev-Profil"), 1, _DB.USER$ID_DATEV_PROFILE+"|#  |",3);

		
		
		
		//2014-02-17:  erscheinungsbild
		oFiller.add_Line(oGrid2b, new MyE2_String("Maskenschriften Anzeige <OK>"), 1, 		_DB.USER$COLOR_MASK_OK_RED+"|"+
																							_DB.USER$COLOR_MASK_OK_GREEN+"|"+
																							_DB.USER$COLOR_MASK_OK_BLUE+"|"+
																							USER__MASK_ComponentMAP.HASHKEY_SELECT_OK_COLOR
																							,3);		
		oFiller.add_Line(oGrid2b, new MyE2_String("Maskenschriften Anzeige <WARNUNG>"), 1,	_DB.USER$COLOR_MASK_WARN_RED+"|"+
																							_DB.USER$COLOR_MASK_WARN_GREEN+"|"+
																							_DB.USER$COLOR_MASK_WARN_BLUE+"|"+
																							USER__MASK_ComponentMAP.HASHKEY_SELECT_WARN_COLOR
																							,3);		
		oFiller.add_Line(oGrid2b, new MyE2_String("Maskenschriften Anzeige <FEHLER>"), 1, 	_DB.USER$COLOR_MASK_ERROR_RED+"|"+
																							_DB.USER$COLOR_MASK_ERROR_GREEN+"|"+
																							_DB.USER$COLOR_MASK_ERROR_BLUE+"|"+
																							USER__MASK_ComponentMAP.HASHKEY_SELECT_ERROR_COLOR
																							,3);		
		
		//2011-12-06: signatur
		oFiller.add_Line(oGrid3, new MyE2_String("Signatur:"), 1,"ID_USER_SIGNATUR" ,3);
		
		this.vMaskGrids.add(oGrid0);
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}

	
	

	
}
