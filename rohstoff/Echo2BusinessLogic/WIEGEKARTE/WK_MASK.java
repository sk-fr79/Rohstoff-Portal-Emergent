package rohstoff.Echo2BusinessLogic.WIEGEKARTE;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class WK_MASK extends MyE2_Grid 
{

	
	public WK_MASK(WK_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("BEFUND:"), 1, "BEFUND|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_ADRESSE_LAGER:"), 1, "ID_ADRESSE_LAGER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_ADRESSE_LIEFERANT:"), 1, "ID_ADRESSE_LIEFERANT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_ARTIKEL_SORTE:"), 1, "ID_ARTIKEL_SORTE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_VPOS_TPA_FUHRE:"), 1, "ID_VPOS_TPA_FUHRE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_VPOS_TPA_FUHRE_ORT:"), 1, "ID_VPOS_TPA_FUHRE_ORT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_WIEGEKARTE:"), 1, "ID_WIEGEKARTE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("IST_LIEFERANT:"), 1, "IST_LIEFERANT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("KENNZEICHEN:"), 1, "KENNZEICHEN|#  |",3);
		oFiller.add_Line(this, new MyE2_String("LFD_NR:"), 1, "LFD_NR|#  |",3);
		oFiller.add_Line(this, new MyE2_String("NETTOGEWICHT:"), 1, "NETTOGEWICHT|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_WK",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_WK_WICHTIGKEIT|# ",3);   
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
