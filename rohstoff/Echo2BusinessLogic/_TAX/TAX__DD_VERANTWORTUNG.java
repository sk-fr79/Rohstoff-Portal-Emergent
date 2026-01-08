package rohstoff.Echo2BusinessLogic._TAX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField_LabelInList;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class TAX__DD_VERANTWORTUNG extends MyE2_DB_SelectField_LabelInList
{

	public TAX__DD_VERANTWORTUNG(SQLField osqlField, int iWidth, boolean bUseInList, boolean inclusiveEgal) throws myException
	{
		super(osqlField);
		
		String[][] cWerte = new String[inclusiveEgal?5:4][2];

		
		cWerte[0][0] = "-";
		cWerte[0][1] = "";

		cWerte[1][0] = bibALL.get_RECORD_MANDANT().get___KETTE(bibVECTOR.get_Vector("NAME1", "NAME2"), "<Mandant>", "", "", " ");
		cWerte[1][1] = TAX_CONST.TP_VERANTWORTUNG_MANDANT;
		
		cWerte[2][0] = "Lieferant";
		cWerte[2][1] = TAX_CONST.TP_VERANTWORTUNG_QUELLE;
		
		cWerte[3][0] = "Abnehmer";
		cWerte[3][1] = TAX_CONST.TP_VERANTWORTUNG_ZIEL;

		//der wert "egal" darf nur in der maske der handelsdefinition vorhanden sein 
		if (inclusiveEgal)
		{
			cWerte[4][0] = "Egal";
			cWerte[4][1] = TAX_CONST.TP_VERANTWORTUNG_EGAL;
		}

		
		this.set_ListenInhalt(cWerte, false);
		this.setWidth(new Extent(iWidth));
		
		this.set_bUseInList(bUseInList);
		
	}

}
