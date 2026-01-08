package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_RECORDS._DB;


/**
 * positives selektionsfeld fuer status aktive, inaktiv
 * @author martin
 *
 */
public class MS_LIST_Selector_AKTIV_INAKTIV extends E2_ListSelektorMultiselektionStatusFeld_STD {
	
	private static int[]  iActiveSelektor = {50,50};


	public MS_LIST_Selector_AKTIV_INAKTIV() {
		super(iActiveSelektor, true, new MyE2_String("Aktiv"), new Extent(50));
		
		this.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.MASCHINEN+"."+_DB.MASCHINEN$AKTIV+",0)='Y')", new MyE2_String("Ja"), new MyE2_String("Aktive Maschinen in die Selektion aufnehmen"));
		this.ADD_STATUS_TO_Selector(false, "(NVL("+_DB.MASCHINEN+"."+_DB.MASCHINEN$AKTIV+",0)='N')", new MyE2_String("Nein"), new MyE2_String("Inaktive Maschinen in die Selektion aufnehmen"));
		
	}
	
}
