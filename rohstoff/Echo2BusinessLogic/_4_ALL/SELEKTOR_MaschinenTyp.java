package rohstoff.Echo2BusinessLogic._4_ALL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettingsNew;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

public class SELEKTOR_MaschinenTyp extends MyE2_SelectField 
{
	public SELEKTOR_MaschinenTyp(int iWidth, boolean bMitBeschreibung, boolean bFormated) throws myException 
	{
		super();
		E2_DropDownSettingsNew ddLand = new E2_DropDownSettingsNew(
							"JT_MASCHINENTYP", 
							"MASCHINENTYP"+(bMitBeschreibung?"||' ('||  NVL(BESCHREIBUNG,'-')||')'":""), 
							"ID_MASCHINENTYP","", null, true,"MASCHINENTYP", bFormated);
		this.set_ListenInhalt(ddLand.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
		
	}

}
