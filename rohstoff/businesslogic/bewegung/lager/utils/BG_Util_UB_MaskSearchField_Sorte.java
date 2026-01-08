package rohstoff.businesslogic.bewegung.lager.utils;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.unboundDataFields.UB_MaskSearchField;
import panter.gmbh.indep.exceptions.myException;

public class BG_Util_UB_MaskSearchField_Sorte extends UB_MaskSearchField
{

	/**
	 * 
	 */

	public BG_Util_UB_MaskSearchField_Sorte(String fieldName, boolean EmptyAllowed) throws myException
	{
		super(	fieldName, EmptyAllowed, 
				"JT_ARTIKEL.ID_ARTIKEL, JT_ARTIKEL.ANR1, NVL(JT_ARTIKEL.ARTBEZ1,'') ", 
				bibE2.cTO()+".JT_ARTIKEL", 
				"JT_ARTIKEL.ARTBEZ1",
				"NVL(JT_ARTIKEL.AKTIV,'N')='Y'", 
				"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%') OR TO_CHAR(JT_ARTIKEL.ID_ARTIKEL)='#WERT#'", 
				null,
				null, 
				 "SELECT trim(  NVL(ANR1,'-')) || ' - ' || trim(  NVL(JT_ARTIKEL.ARTBEZ1,'-')) "+
					" from " + 
					bibE2.cTO() + ".JT_ARTIKEL " +
					"WHERE " +
					"ID_ARTIKEL=#WERT#",
				E2_INSETS.I_0_0_2_0, false);
		
		this.get_oTextForAnzeige().setFont(new E2_FontPlain());
	}

	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}

}
