package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST_SEL_AnzeigeAltNeu extends E2_ListSelektorMultiselektionStatusFeld_STD	{
	
	private static int[] CheckBoxSelektorSpaltenZeigeNeuAlt = {85,85};

	public FZ_LIST_SEL_AnzeigeAltNeu() throws myException
	{
		super(FZ_LIST_SEL_AnzeigeAltNeu.CheckBoxSelektorSpaltenZeigeNeuAlt,true,new MyE2_String(""),new Extent(20));
		
		this.ADD_STATUS_TO_Selector(false,	"JT_BEWEGUNG.ID_VPOS_TPA_FUHRE IS NOT NULL",	
											new MyE2_String("Fuhren"),   new MyE2_String("Zeige Bewegungssätze, die aus der alten Fuhre kommen"));
		
		this.ADD_STATUS_TO_Selector(true,	"JT_BEWEGUNG.ID_VPOS_TPA_FUHRE IS NULL",	
											new MyE2_String("Atome"),   new MyE2_String("Zeige Bewegungssätze, die aus dem Atom-basierten Bewegungssatz kommen"));

	}

}
