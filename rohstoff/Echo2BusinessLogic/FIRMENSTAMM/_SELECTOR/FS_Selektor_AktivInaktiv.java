package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class FS_Selektor_AktivInaktiv extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	public FS_Selektor_AktivInaktiv()
	{
		super(FS_ListSelector.CheckBoxSelektorSpalten,true,new MyE2_String("Aktiv"),new Extent(80));
		
		String cAktiv_FELD = RECORD_ADRESSE.FIELD__AKTIV;
		
		this.ADD_STATUS_TO_Selector(true,	"(NVL(JT_ADRESSE.#FELD#,'N')='Y')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Ja"),   new MyE2_String("Zeige Adressen, die das AKTIV sind"));		
		this.ADD_STATUS_TO_Selector(false,	"(NVL(JT_ADRESSE.#FELD#,'N')='N')".replace("#FELD#",cAktiv_FELD),	new MyE2_String("Nein"),   new MyE2_String("Zeige Adressen, die INAKTIV sind"));		
	}
}