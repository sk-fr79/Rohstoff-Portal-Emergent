package rohstoff.Echo2BusinessLogic._4_ALL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_SELEKTOR_MaschinenTyp extends MyE2_DB_SelectField
{
	public DB_SELEKTOR_MaschinenTyp(SQLField osqlField, int iWidth, boolean bMitBeschreibung) throws myException 
	{
		super(osqlField);
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JT_MASCHINENTYP", "MASCHINENTYP"+(bMitBeschreibung?"||' ('||  NVL(BESCHREIBUNG,'-')||')'":""), "ID_MASCHINENTYP","", null, true,"MASCHINENTYP");
		this.set_ListenInhalt(ddLand.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
		
	}


}
