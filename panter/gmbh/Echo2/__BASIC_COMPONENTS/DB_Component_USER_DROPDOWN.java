package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_USER_DROPDOWN extends MyE2_DB_SelectField 
{

	public DB_Component_USER_DROPDOWN(SQLField osqlField) throws myException 
	{
		super(	osqlField, 
		    	new E2_DropDownSettings( "JD_USER", " NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')'", "ID_USER","ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(AKTIV,'N')='Y' ", null, true, null).getDD(), false);

		String[][] cInaktiv = bibDB.EinzelAbfrageInArrayFormatiert("SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||' *)',ID_USER FROM "+bibE2.cTO()+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND NVL(AKTIV,'N')='N' ", "");

		if (cInaktiv != null && cInaktiv.length>0)
		{
			dataToView oShadow = new dataToView(cInaktiv,false, bibE2.get_CurrSession());
			this.set_odataToViewShadow(oShadow);
		}
	}

}
