package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.exceptions.myException;

public class MS_MASK extends MyE2_Grid
{

	
	public MS_MASK(MS_MASK_ComponentMAP oMap) throws myException
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_W100());

		MyE2_TabbedPane oTabbedPane =  new MyE2_TabbedPane(new Integer(700));
		oTabbedPane.set_bAutoHeight(true);
		
		this.add(oTabbedPane);
		
		MyE2_Grid oGrid1 = new MyE2_Grid(6, MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
		MyE2_Grid oGrid2 = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
		MyE2_Grid oGrid3 = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
		
		
		Insets oINL = new Insets(3,2,5,0);    // linke spalte mit beschriftung wird weiter abgesetzt
		Insets oIN = new Insets(3,2,5,0);


		E2_MaskFiller oFiller = new E2_MaskFiller(oMap,null,null,oINL,oIN, new Alignment(Alignment.LEFT,Alignment.CENTER)); 

		//hier kommen die Felder	
		oFiller.add_Line(oGrid1, new MyE2_String("ID:"), 1, 					"ID_MASCHINEN|#  |",3,"#Aktiv ?|#|AKTIV",2);
		oFiller.add_Line(oGrid1, new MyE2_String("Kennzeichen:"), 1, 			"KFZKENNZEICHEN",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Standort:"), 	1, 			"ID_ADRESSE_STANDORT|#  |",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Fahrgestellnummer:"), 1, 	"FAHRGESTELLNUMMER|#  |",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Briefnummer:"), 1, 			"BRIEFNUMMER|#  |",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Anschaffung am:"), 1, 		"DATUM_ANSCHAFFUNG|",3,	"#Gewährleistung bis: ",1,"GEWAEHRLEISTUNG_BIS",1);
		oFiller.add_Line(oGrid1, new MyE2_String("Leasing bis:"), 1, 			"LEASING_BIS|",3,		"#gekauft ab: ",1,"GEKAUFT_AB",1);
		oFiller.add_Line(oGrid1, new MyE2_String("Maschinentyp:"), 1, 		"ID_MASCHINENTYP",3,"#Hersteller",1,"HERSTELLER",1);
		oFiller.add_Line(oGrid1, new MyE2_String("Bediener:"), 1, 			"ID_USER_BEDIENER1",3,"#Bediener 2",1,"ID_USER_BEDIENER2",1);
		oFiller.add_Line(oGrid1, new MyE2_String("Anschaffungskosten:"), 1, 	"KOSTEN_ANSCHAFFUNG|#  |",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Kostenstelle:"), 1, 		"KOSTENSTELLE1|#.|KOSTENSTELLE2",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Typenbezeichnung:"), 1, 	"TYPENBEZ|#  |",5);

		oFiller.add_Line(oGrid1, new MyE2_String("Bemerkung:"), 1, 			"BEMERKUNG|#  |",5);
		oFiller.add_Line(oGrid1, new MyE2_String("Beschreibung:"), 1, 		"BESCHREIBUNG|#  |",5);

		
		oGrid2.add(oMap.get_Comp(MS__CONST.HASHKEY_MASK_INLAY_AUFGABEN));
		oGrid3.add(oMap.get_Comp(MS__CONST.HASHKEY_MASK_INLAY_KOSTEN));
		
		
		oTabbedPane.add_Tabb(new MyE2_String("Maschine"), 	oGrid1);
		oTabbedPane.add_Tabb(new MyE2_String("Aufgaben"), 	oGrid2);
		oTabbedPane.add_Tabb(new MyE2_String("Kosten"),	 	oGrid3);

	}

	
	
}
