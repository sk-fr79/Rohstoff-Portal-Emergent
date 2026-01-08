package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class FU_SONDER_MASK extends MyE2_Grid 
{

	
	public FU_SONDER_MASK(FU_SONDER_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		oFiller.add_Trenner(this, E2_INSETS.I_1_1_1_1);
		
		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("ID:"), 1, "ID_VPOS_TPA_FUHRE_SONDER|#  |",3);
		
		oFiller.add_Line(this, new MyE2_String("Titel:"), 1, "AUSNAHME|#  |",3);
		oFiller.add_Trenner(this, E2_INSETS.I_1_1_1_1);
		oFiller.add_Line(this, new MyE2_String("Ohne VK-Kontrakt:"), 1, "KEIN_KONTRAKT_NOETIG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Ohne Abrechnung:"), 1, "OHNE_ABRECHNUNG|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Ohne AVV-Vertrags-Check:"), 1, "OHNE_AVV_VERTRAG_CHECK|#  |",3);

		oFiller.add_Trenner(this, E2_INSETS.I_1_1_1_1);
		oFiller.add_Line(this, new MyE2_String("Sonderpositionen"), 1, FU_SONDER__CONST.KEY_DAUGHTER_VPOS_VL,3);
		
	}

	
	
}
