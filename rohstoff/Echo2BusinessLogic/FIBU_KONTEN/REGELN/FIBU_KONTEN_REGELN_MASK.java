package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_KONTEN_REGELN_MASK extends MyE2_Grid 
{

	
	public FIBU_KONTEN_REGELN_MASK(FIBU_KONTEN_REGELN_MASK_ComponentMAP oMap) throws myException
	{
//		super(4, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		// Grid with four columns
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		
		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("Regelnummer:"), 1, "ID_FIBU_KONTENREGEL_NEU|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));
		oFiller.add_Line(this, new MyE2_String("Resultierendes Buchungskonto:"), 1, _DB.FIBU_KONTENREGEL$ID_FIBU_KONTO+"|#  (auf dieses Konto wird gebucht)|",3);
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));

		//////////////////////////// 2014-12-17: nils: die componentmaps (START)
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));
		oFiller.add_Line(this, new MyE2_String("Regeln"),4);
//		oFiller.add_Line(this, FIBU_KONTEN_REGELN__CONST.MASK_COMPONENT_CONDITION_DAUGHTER, 4);
		
		
		oFiller.add_Separator(this, E2_INSETS.I(0,2,0,3));
		////////////////////////////2014-12-17: nils: die componentmaps (END)
	}

	
	
}
