package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class ZOL_LIST_Selektor_RC extends E2_ListSelektorMultiselektionStatusFeld_STD {

	static int[] CheckBoxSelektorSpalten = {50,50};
	
	public ZOL_LIST_Selektor_RC()
	{
		super(	CheckBoxSelektorSpalten,
				true,
				new MyE2_String("Reverse Charge (RC)"),
				new Extent(150));
		
		this.get_oComponentForSelection().setBackground(Color.GREEN);
		
		String cAktiv_FELD = ZOLLTARIFNUMMER.reverse_charge.tnfn() ;
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='Y')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Ja"),   new MyE2_String("Ist RC"));		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='N')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Nein"),   new MyE2_String("Ist nicht RC"));		
	}
	
}
