package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class MESSAGE_LIST_Selektor_Bestaetigt extends E2_ListSelektorMultiselektionStatusFeld_STD {

	static int[] CheckBoxSelektorSpalten = {40,55};
	
	public MESSAGE_LIST_Selektor_Bestaetigt()
	{
		super(CheckBoxSelektorSpalten,false,new MyE2_String(""),new Extent(0));
		
		String cAktiv_FELD = RECORD_NACHRICHT.TABLENAME + "." +  RECORD_NACHRICHT.FIELD__BESTAETIGT;
		this.ADD_STATUS_TO_Selector(true,	"(NVL(#FELD#,'N')='N')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Unbestätigte"),   new MyE2_String("Zeige unbestätigte Nachrichten"));		
		this.ADD_STATUS_TO_Selector(false,	"(NVL(#FELD#,'N')='Y')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Bestätigte"),   new MyE2_String("Zeige bestätigte Nachrichten"));		
	}
	
}
