package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class FS_Selektor_Bar_Nicht_bar extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	public FS_Selektor_Bar_Nicht_bar()
	{
		super(FS_ListSelector.CheckBoxSelektorSpalten,true,new MyE2_String("Bar-Kunde"),new Extent(80));
		
		String cBAR_FELD = RECORD_ADRESSE.FIELD__BARKUNDE;
		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_ADRESSE.#FELD#,'N')='Y')".replace("#FELD#",cBAR_FELD),	new MyE2_String("Ja"),   		new MyE2_String("Zeige Adressen, die das Merkmal <Bar-Kunde> tragen"));		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_ADRESSE.#FELD#,'N')='N')".replace("#FELD#",cBAR_FELD),	new MyE2_String("Nein"),   new MyE2_String("Zeige Adressen, die NICHT das Merkmal <Bar-Kunde> tragen"));		
	}
}