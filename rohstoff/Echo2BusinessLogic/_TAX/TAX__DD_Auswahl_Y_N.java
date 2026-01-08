package rohstoff.Echo2BusinessLogic._TAX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

/**
 * 2018-08-27: selektor fuer die pseudo-typisierung der sorten in "schrott" -> d.h. kein prodult, eow, dienstleistung
 * @author martin
 *
 */
public class TAX__DD_Auswahl_Y_N extends MyE2_SelectField {

	public TAX__DD_Auswahl_Y_N(int iWidth) throws myException 	{
		super();
		
				
		String[][] cWerte = new String[3][2];
		
		
		cWerte[0][0] = "-"; 	cWerte[0][1] = "";
		cWerte[1][0] = "Ja"; 	cWerte[1][1] = "Y";
		cWerte[2][0] = "Nein"; 	cWerte[2][1] = "N";

		
		this.set_ListenInhalt(cWerte, false);
		this.setWidth(new Extent(iWidth));
	}


}
