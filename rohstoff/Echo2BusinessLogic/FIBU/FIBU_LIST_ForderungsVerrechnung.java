package rohstoff.Echo2BusinessLogic.FIBU;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_LIST_ForderungsVerrechnung extends MyE2_DB_SelectField
{

	public FIBU_LIST_ForderungsVerrechnung(SQLField osqlField) throws myException
	{
		super(osqlField	,myCONST.FORDERUNGSVERRECHNUNG,false);
		
		this.setFont(new E2_FontItalic(-2));
		this.setWidth(new Extent(100));

		
	}

}
