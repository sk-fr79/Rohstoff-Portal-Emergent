package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;



public class DRUCK_LIST_Selektor_Aktiv extends E2_ListSelektorMultiselektionStatusFeld_STD {

	private static int[] CheckBoxSelektorSpalten = {40,55};

	public DRUCK_LIST_Selektor_Aktiv()
	{
		super(CheckBoxSelektorSpalten,true,new MyE2_String("Drucker status:"),new Extent(0));

		String cAktiv_FELD = DRUCKER.aktiv.tnfn() ;
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='Y')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Aktiv"),   new MyE2_String("Aktive Zolltarifnummern"));		
		this.ADD_STATUS_TO_Selector(false,	"(NVL(#FELD#,'N')='N')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Inaktiv"),   new MyE2_String("Inaktive Zolltarifnummern"));		
	}

}


