package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class FS_Selektor_Scheck_kein_Scheck extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	public FS_Selektor_Scheck_kein_Scheck()
	{
		super(FS_ListSelector.CheckBoxSelektorSpalten,true,new MyE2_String("Scheck-K."),new Extent(80));
		
		String cScheck_FELD = RECORD_FIRMENINFO.FIELD__SCHECKDRUCK_JN;
		
		this.ADD_STATUS_TO_Selector(true,	"(JT_ADRESSE.ID_ADRESSE IN (SELECT FIN.ID_ADRESSE FROM JT_FIRMENINFO FIN WHERE NVL(FIN.#FELD#,'N')='Y'))".replace("#FELD#",cScheck_FELD),	
											new MyE2_String("Ja"),   new MyE2_String("Zeige Adressen, die für Scheckdruck vorgesehen sind"));		
		this.ADD_STATUS_TO_Selector(true,	"(JT_ADRESSE.ID_ADRESSE IN (SELECT FIN.ID_ADRESSE FROM JT_FIRMENINFO FIN WHERE NVL(FIN.#FELD#,'N')='N'))".replace("#FELD#",cScheck_FELD),	
											new MyE2_String("Nein"),   new MyE2_String("Zeige Adressen, die NICHT für Scheckdruck vorgesehen sind"));		
	}
}