package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.indep.bibALL;

public class FU_SelektorMitUndOhneFuhrenOrt extends E2_ListSelektorMultiselektionStatusFeld_STD
{
    private static int[] iBreiteSpalten = {135,135};
	
	public FU_SelektorMitUndOhneFuhrenOrt()
	{
		super(iBreiteSpalten,false,null,new Extent(95));
		
		String cQuery = "(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE #PARAM# " +
				  		"(SELECT FUO.ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE_ORT  FUO WHERE NVL(FUO.DELETED,'N')='N'))";

		this.ADD_STATUS_TO_Selector(true, bibALL.ReplaceTeilString(cQuery, "#PARAM#", " IN "),     new MyE2_String("Mit Zusatzort"),  	 new MyE2_String("Zeige Fuhren, die mindestens einen ungelöschten Fuhrenort besitzen"));		
		this.ADD_STATUS_TO_Selector(true, bibALL.ReplaceTeilString(cQuery, "#PARAM#", " NOT IN "), new MyE2_String("Ohne Zusatzort"),   new MyE2_String("Zeige Fuhren ohne Zusatzorte"));
		
		this.set_cConditionWhenAllIsSelected("");
		
	}

}
