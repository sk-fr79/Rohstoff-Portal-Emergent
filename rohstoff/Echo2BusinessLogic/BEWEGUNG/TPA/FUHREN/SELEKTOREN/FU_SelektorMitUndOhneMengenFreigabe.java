package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.indep.bibALL;

public class FU_SelektorMitUndOhneMengenFreigabe extends E2_ListSelektorMultiselektionStatusFeld_STD
{
    private static int[] iBreiteSpalten = {135,135};
	
	public FU_SelektorMitUndOhneMengenFreigabe()
	{
		super(iBreiteSpalten,false,null,new Extent(95));
		
		
		String cQuery = "(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE #PARAM# (SELECT FU.ID_VPOS_TPA_FUHRE FROM JT_VPOS_TPA_FUHRE  FU "+ 
						" WHERE "+ 
						" NVL(FU.PRUEFUNG_LADEMENGE,'N')='Y' "+ 
						" AND "+ 
						" NVL(FU.PRUEFUNG_ABLADEMENGE,'N')='Y' "+ 
						" AND "+ 
						"   (  SELECT COUNT(*) FROM JT_VPOS_TPA_FUHRE_ORT FUO WHERE NVL(DELETED,'N')='N' " +
						"      AND NVL(FUO.PRUEFUNG_MENGE,'N')='N' AND FUO.ID_VPOS_TPA_FUHRE=FU.ID_VPOS_TPA_FUHRE" +
						"   )=0" +
						"))";

		this.ADD_STATUS_TO_Selector(true, bibALL.ReplaceTeilString(cQuery, "#PARAM#", " IN "),     new MyE2_String("Mengenfreigabe"),  	 new MyE2_String("Zeige Fuhren, die in allen Positionen die Mengenfreigabe erhalten haben"));		
		this.ADD_STATUS_TO_Selector(true, bibALL.ReplaceTeilString(cQuery, "#PARAM#", " NOT IN "), new MyE2_String("Keine Mge.Freig."),   new MyE2_String("Zeige Fuhren, die in mindestens einer Position noch keine Mengenfreigabe erhalten haben"));
		
		this.set_cConditionWhenAllIsSelected("");
		
	}

}
