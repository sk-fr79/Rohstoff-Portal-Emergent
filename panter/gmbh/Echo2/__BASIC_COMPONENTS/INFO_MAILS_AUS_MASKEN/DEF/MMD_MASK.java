package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK extends MyE2_Grid 
{

	
	public MMD_MASK(MMD_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
	
		MyE2_TabbedPane  oTAB = new MyE2_TabbedPane(25);
		oTAB.set_bAutoHeight(true);
		
		this.add(oTAB, 4, E2_INSETS.I(2,2,2,2));

		
		MyE2_Grid oGrid1 = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
		MyE2_Grid oGrid2 = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
		MyE2_Grid oGrid3 = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		oTAB.add_Tabb(new MyE2_String("Basisangaben"), 	oGrid1);
		oTAB.add_Tabb(new MyE2_String("Mailadressen"), 	oGrid2);
		oTAB.add_Tabb(new MyE2_String("Reports"), 		oGrid3);
		
		
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(oGrid1, new MyE2_String("ID:"), 1, 									_DB.MAIL_AUS_MASK$ID_MAIL_AUS_MASK+"|#  |",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Beschriftung:"), 1, 							_DB.MAIL_AUS_MASK$BUTTONBESCHRIFTUNG+"|#  |",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Basistabelle:"), 1, 							_DB.MAIL_AUS_MASK$BASISTABELLE+"|#  |",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Modul:"), 1, 									_DB.MAIL_AUS_MASK$MODULKENNER+"|#  |",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Autenifizierungskennung:"), 1, 				_DB.MAIL_AUS_MASK$BUTTONKENNER+"|#  |",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Betreffzeile (mit Platzhaltern):"), 1, 		_DB.MAIL_AUS_MASK$BETREFF+"|#  |"+MMD__CONST.MASK_KEY_INFO_BUTTON1+"|",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Mailtext (mit Platzhaltern):"), 1, 			_DB.MAIL_AUS_MASK$MAILTEXT+"|#  |"+MMD__CONST.MASK_KEY_INFO_BUTTON2+"|",3);
		oFiller.add_Line(oGrid1, new MyE2_String("Freie Mailadresse erlaubt:"), 1, 				_DB.MAIL_AUS_MASK$ERLAUBE_FREIEMAILADRESSE+
																								"|#  |#Signatur des Benutzers einfügen:|"+_DB.MAIL_AUS_MASK$SIGNATUR_ANHAENGEN,3);
		oFiller.add_Line(oGrid1, new MyE2_String("Bed.für Versand (Groovy-Script):"), 1,		_DB.MAIL_AUS_MASK$GROOVY_BEDINGUNG+"|#  |"+MMD__CONST.MASK_KEY_INFO_BUTTON3+"|",3);
		oFiller.add_Line(oGrid1, new MyE2_Label(new MyE2_String("Meldung/Sicherheitsabfrage (#Ja-Antwort#Nein-Antwort):"),true), 1, 		_DB.MAIL_AUS_MASK$SICHERHEITSABFRAGE_VOR_START+"|#  |"+MMD__CONST.MASK_KEY_INFO_BUTTON4+"|",3);
		
		E2_Subgrid_4_Mask oSubGrid = new E2_Subgrid_4_Mask("Neueingabe|Editieren|Anzeigen (ohne Bearbeitung)|Undefiniert",
														_DB.MAIL_AUS_MASK$ERLAUBT_BEI_NEUEINGABE+"|"+
														_DB.MAIL_AUS_MASK$ERLAUBT_BEI_EDIT+"|"+
														_DB.MAIL_AUS_MASK$ERLAUBT_BEI_VIEW+"|"+
														_DB.MAIL_AUS_MASK$ERLAUBT_BEI_UNDEF,														
														oMap,null, null);
		oSubGrid.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		
		oFiller.add_Line(oGrid1,new MyE2_Label(new MyE2_String("Ausführung erlauben im Status (keine Prüfung wenn alle aktiv):"),true),1,oSubGrid,3);
		
		
		
		oGrid2.add(new MyE2_Label(new MyE2_String("Mailverteiler")), 4, E2_INSETS.I(2,2,2,2));
		oGrid2.add(oMap.get_Comp(MMD__CONST.MASK_KEY_DAUGHTER_EMAIL), 4, E2_INSETS.I(2,2,2,2));
		
		oGrid3.add(new E2_ComponentGroupHorizontal(0, 
							new MyE2_Label(new MyE2_String("Reports (Anhang an die Mail)")), 
							oMap.get_Comp(MMD__CONST.MASK_KEY_INFO_BUTTON5),
							E2_INSETS.I(0,0,10,0)), 4, E2_INSETS.I(2,2,2,10));
		
		oGrid3.add(oMap.get_Comp(MMD__CONST.MASK_KEY_DAUGHTER_JASPER), 4, E2_INSETS.I(2,2,2,2));

	}

	
	
}
