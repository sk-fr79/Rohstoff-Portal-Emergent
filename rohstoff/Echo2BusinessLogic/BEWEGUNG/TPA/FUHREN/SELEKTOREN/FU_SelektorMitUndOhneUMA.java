package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;

public class FU_SelektorMitUndOhneUMA extends E2_ListSelektorMultiselektionStatusFeld_STD
{
    private static int[] iBreiteSpalten = {135,135};

	
	public FU_SelektorMitUndOhneUMA()
	{
		super(iBreiteSpalten,false,new MyE2_String("Zeige: "),new Extent(95));
		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_VPOS_TPA_FUHRE.ID_UMA_KONTRAKT,0)>0)",	new MyE2_String("Mit UMA"),   new MyE2_String("Zeige Fuhren, die einem UMA-Kontrakt zugeordnet wurden"));		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_VPOS_TPA_FUHRE.ID_UMA_KONTRAKT,0)<=0)",	new MyE2_String("Ohne UMA"),  new MyE2_String("Zeige Fuhren OHNE UMA-Kontrakt"));
		
		this.set_cConditionWhenAllIsSelected("");
	}

	
	
}
