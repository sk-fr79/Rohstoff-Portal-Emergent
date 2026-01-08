package rohstoff.utils.ECHO2.GROUP_COLLECTOR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class SELECTOR_GROUPS_MultiSelection extends E2_ListSelectorMultiDropDown
{
	
	/*
	 * selektor-kompoente fuer die gruppen
	 */
	public SELECTOR_GROUPS_MultiSelection(String NAME_OF_TABLE ,String PRIMARY_KEY_OF_TABLE, String MODULE_KENNER) throws myException
	{
		super(new MyE2_SelectField("SELECT MENUETEXT,ID_COLLECTION_DEF FROM "+bibE2.cTO()+".JT_COLLECTION_DEF WHERE MODULE_KENNER="+bibALL.MakeSql(MODULE_KENNER)+" ORDER BY UPPER(MENUETEXT)",
				false,true,false,true),
				NAME_OF_TABLE+"."+PRIMARY_KEY_OF_TABLE+" IN (SELECT ID_TABLE FROM "+bibE2.cTO()+".JT_COLLECTIONS WHERE ID_COLLECTION_DEF=#WERT#)");
		
		
		this.get_oSelFieldBasis().setToolTipText(new MyE2_String("Auswahl der benutzerdefinierten Gruppen").CTrans());
		
	}


}
