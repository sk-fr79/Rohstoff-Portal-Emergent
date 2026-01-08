package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_PROFILES_MASK extends MyE2_Grid 
{

	
	public FIBU_EXPORT_PROFILES_MASK(FIBU_EXPORT_PROFILES_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("Profil-ID:"), 1, "ID_DATEV_PROFILE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Profilname:"), 1, "DESCRIPTION|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Datev-Geschäftsjahresbeginn:"), 1, "DATEV_GESCHAEFTSJAHRESBEGINN|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Datev-Beraternummer:"), 1, "DATEV_BERATERNUMMER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Datev-Mandantnummer:"), 1, "DATEV_MANDANTNUMMER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Drucker-ID:"), 1, "ID_DRUCKER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Protokolldrucker-ID:"), 1, "ID_DRUCKER_PROTOKOLLE|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID_USER:"), 1, "ID_USER|#  |",3);

		
		oFiller.add_Line(this, new MyE2_String("Server-Exportpfad"), 1, "EXPORT_DIR|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Startdatum für Exporte:"), 1, "EXPORTS_STARTING_DATE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Start-Export-ID:"), 1, "EXPORTS_STARTING_ID|#  |",3);
		
		oFiller.add_Line(this, new MyE2_String("Export-Tabellen bei Export leeren:"), 1, "FLUSH_TABLES|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Text-Protokolle schreiben:"), 1, "PRINT_PROTOCOLS|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Buchhaltungs-Datum korrigieren:"), 1, "CORRECT_DATES|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Exporte mit Debugflag stampen:"), 1, "STAMP_IMPORTED_WITH_DEBUGFLAGS|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_FIBU_EXPORT_PROFILES",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_FIBU_EXPORT_PROFILES_WICHTIGKEIT|# ",3);   
		oFiller.add_Line(this, new MyE2_String("Erstellt am:"), 1, "GENERIERUNGSDATUM|#   |#Zu erledigen bis|ABLAUFDATUM|#  |#Abgeschlossen |ERLEDIGT|#  am |ABSCHLUSSDATUM",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Aufgabe:"), 1, "AUFGABEKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "AUFGABENTEXT",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Ergebnis:"), 1, "ANTWORTKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "ANTWORTTEXT",3);
		*/

	}

	
	
}
