package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldV2;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BS_SELECTFIELD_WAEHRUNG_FREMD extends MyE2_DB_SelectFieldV2 
{

	public BS_SELECTFIELD_WAEHRUNG_FREMD(SQLFieldMAP oFM, boolean bSmall) throws myException 
	{
		super(oFM.get_("ID_WAEHRUNG_FREMD"), "SELECT KURZBEZEICHNUNG||' ('||WAEHRUNGSSYMBOL||')',ID_WAEHRUNG FROM "+bibE2.cTO()+".JD_WAEHRUNG ORDER BY KURZBEZEICHNUNG", false,	false);
		this.setWidth(new Extent(100));
		if (bSmall) this.setFont(new E2_FontPlain(-2));
	}

}
