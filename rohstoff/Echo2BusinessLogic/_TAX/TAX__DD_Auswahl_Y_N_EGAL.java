package rohstoff.Echo2BusinessLogic._TAX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField_LabelInList;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class TAX__DD_Auswahl_Y_N_EGAL extends MyE2_DB_SelectField_LabelInList
{

	public TAX__DD_Auswahl_Y_N_EGAL(SQLField osqlField, int iWidth, boolean bUseInList) throws myException
	{
		super(osqlField);
		
		boolean bErlaube_Schalter_Egal = bib_Settigs_Mandant.IS__Value("HANDELSDEF_ERLAUBE_VARIANTE_EGAL_IN_DROPDOWN", "N", "N");

		int iZahlDD = bErlaube_Schalter_Egal?4:3;
				
		String[][] cWerte = new String[iZahlDD][2];
		
		String[][] cShadow = new String[1][2];
		cShadow[0][0] = "Ja/Nein"; 	cShadow[0][1] = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__EGAL;
		
		
		cWerte[0][0] = "-"; 	cWerte[0][1] = "";
		cWerte[1][0] = "Ja"; 	cWerte[1][1] = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y;
		cWerte[2][0] = "Nein"; 	cWerte[2][1] = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__N;
		if (bErlaube_Schalter_Egal) {
			cWerte[3][0] = "Ja/Nein"; 	cWerte[3][1] = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__EGAL;
		}
		
		this.set_ListenInhalt(cWerte, false);
		if (!bErlaube_Schalter_Egal) {
			this.set_odataToViewShadow(new dataToView(cShadow, false, bibE2.get_CurrSession()));
		}
		
		this.setWidth(new Extent(iWidth));

		this.set_bUseInList(bUseInList);
	}


	
}
