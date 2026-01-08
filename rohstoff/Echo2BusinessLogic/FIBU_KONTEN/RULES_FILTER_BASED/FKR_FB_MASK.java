package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FKR_FB_MASK extends MyE2_Grid 
{

	
	public FKR_FB_MASK(FKR_FB_MASK_ComponentMAP_FILTER oMap_Filter, FKR_FB_MASK_ComponentMAP_KONTENREGEL_NEU oMAP_KR ) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap_Filter,oMAP_KR,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID-Filter:"), 1, 				_DB.FILTER$ID_FILTER+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("ID-Kontenregel:"), 1, 			_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,10,0,10));
		//oFiller.add_Line(this, new MyE2_String("Beschreibung (Filter)"), 1, 	_DB.FILTER$BESCHREIBUNG+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Konto:"), 1, 					_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTO+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Kommentar (Kontenregel)"), 1, 	_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR+"|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Aktiv?"), 1, 					_DB.FIBU_KONTENREGEL_NEU$AKTIV+"|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,10,0,10));

		oFiller.add_Line(this, new MyE2_String("Alle nachfolgenden Einzelbedingungen werden mit UND verknüpft, deren Blöcke mit ODER:"),4 );
		
		oFiller.add_Line(this, FKR_FB__CONST.FELD_FULL_DAUGHTER_FILTER_AND,4 );
		
	}

	
	
}
