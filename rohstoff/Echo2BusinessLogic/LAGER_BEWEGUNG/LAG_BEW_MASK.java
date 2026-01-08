package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class LAG_BEW_MASK extends MyE2_Grid 
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7828238883091487535L;

	public LAG_BEW_MASK(LAG_BEW_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("BUCHUNGSDATUM:"), 1, "BUCHUNGSDATUM|#  |",3);
		oFiller.add_Line(this, new MyE2_String("BUCHUNGSTYP:"), 1, "BUCHUNGSTYP|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_ADRESSE_LAGER:"), 1, "ID_ADRESSE_LAGER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_ARTIKEL_SORTE:"), 1, "ID_ARTIKEL_SORTE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_LAGER_KONTO:"), 1, "ID_LAGER_KONTO|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_LAGER_KONTO_PARENT:"), 1, "ID_LAGER_KONTO_PARENT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_VPOS_RG:"), 1, "ID_VPOS_RG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_VPOS_TPA_FUHRE:"), 1, "ID_VPOS_TPA_FUHRE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID_VPOS_TPA_FUHRE_ORT:"), 1, "ID_VPOS_TPA_FUHRE_ORT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("IST_KOMPLETT:"), 1, "IST_KOMPLETT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("MENGE:"), 1, "MENGE|#  |",3);
		oFiller.add_Line(this, new MyE2_String("PREIS:"), 1, "PREIS|#  |",3);
		oFiller.add_Line(this, new MyE2_String("SALDO:"), 1, "SALDO|#  |",3);
		oFiller.add_Line(this, new MyE2_String("STORNO:"), 1, "STORNO|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_LAG_BEW",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_LAG_BEW_WICHTIGKEIT|# ",3);   
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
