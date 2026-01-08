package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class ZOL_LIST_Selektor_Aktiv extends E2_ListSelektorMultiselektionStatusFeld_STD {

	static int[] CheckBoxSelektorSpalten = {40,55};
	
	public ZOL_LIST_Selektor_Aktiv()
	{
		super(CheckBoxSelektorSpalten,true,new MyE2_String(""),new Extent(0));
		
		String cAktiv_FELD = ZOLLTARIFNUMMER.aktiv.tnfn() ;
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='Y')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Aktiv"),   new MyE2_String("Aktive Zolltarifnummern"));		
		this.ADD_STATUS_TO_Selector(false,	"(NVL(#FELD#,'N')='N')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Inaktiv"),   new MyE2_String("Inaktive Zolltarifnummern"));		
	}
	
}
