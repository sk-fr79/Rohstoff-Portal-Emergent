package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class FS_Selektor_STEUER_FIRMA_nicht_FIRMA extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	public FS_Selektor_STEUER_FIRMA_nicht_FIRMA()
	{
		super(FS_ListSelector.CheckBoxSelektorSpalten,true,new MyE2_String("St. FIRMA"),new Extent(80));
		
		String cPRIVAT_FELD = _DB.FIRMENINFO$FIRMA;
		
		this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.FIRMENINFO+".#FELD#,'N')='Y')".replace("#FELD#",cPRIVAT_FELD),	new MyE2_String("Ja"),   new MyE2_String("Zeige Adressen, die den steuerlichen Status FIRMA tragen"));		
		this.ADD_STATUS_TO_Selector(true,	"(NVL("+_DB.FIRMENINFO+".#FELD#,'N')='N')".replace("#FELD#",cPRIVAT_FELD),	new MyE2_String("Nein"), new MyE2_String("Zeige Adressen, die NICHT den steuerlichen Status FIRMA tragen"));		
	}
}