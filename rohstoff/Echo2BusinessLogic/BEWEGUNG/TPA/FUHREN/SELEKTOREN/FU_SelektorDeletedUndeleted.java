package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;

public class FU_SelektorDeletedUndeleted extends E2_ListSelektorMultiselektionStatusFeld_STD
{

    private static int[] iBreiteSpalten = {135,135};

	public FU_SelektorDeletedUndeleted()
	{
		super(iBreiteSpalten,false,new MyE2_String("Zeige: "),new Extent(95));
		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N')",		new MyE2_String("Ungelöschte"),   new MyE2_String("Zeige Fuhren, die NICHT als gelöscht markiert sind"));		
		this.ADD_STATUS_TO_Selector(false,	"(NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='Y')",		new MyE2_String("Gelöschte"),     new MyE2_String("Zeige Fuhren, die gelöscht wurden"));
		
		this.set_cConditionWhenAllIsSelected("");

	}

	
}
