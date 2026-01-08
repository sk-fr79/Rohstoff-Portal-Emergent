package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldMonospace;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class FU_MASK_DB_SELECT_INTRASTAT_MELDUNG extends MyE2_DB_SelectField
{

	public FU_MASK_DB_SELECT_INTRASTAT_MELDUNG(SQLField osqlField, int iWitdh) throws myException
	{
		super(osqlField);
		
		this.setFont(new E2_FontBoldMonospace(-2));
		this.setBackground(new E2_ColorBase());
		
		String[][] cWerte = new String[TAX_CONST.INTRASTAT_MELDEN_DD_ARRAY.length][2];
		
		
		for (int i=0;i<TAX_CONST.INTRASTAT_MELDEN_DD_ARRAY.length;i++)
		{
			cWerte[i][0] = TAX_CONST.INTRASTAT_MELDEN_DD_ARRAY[i][0];
			cWerte[i][1] = TAX_CONST.INTRASTAT_MELDEN_DD_ARRAY[i][1];
		}
		
		this.set_ListenInhalt(cWerte, false);
		this.setWidth(new Extent(iWitdh));

		this.set_bSetToolTipsToActiveListValue(true);
	}

}
