package rohstoff.utils.ECHO2;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class DB_SEARCH_Sorte extends MyE2_DB_MaskSearchField
{
	
	public DB_SEARCH_Sorte(		SQLField osqlField 
								) throws myException
	{
		super(		osqlField, 
					"JT_ARTIKEL.ID_ARTIKEL,JT_ARTIKEL.ANR1,JT_ARTIKEL.ARTBEZ1", 
					bibE2.cTO()+".JT_ARTIKEL ", 
					"ANR1",
					"  NVL(JT_ARTIKEL.AKTIV,'N')='Y'", 
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%') OR TO_CHAR(JT_ARTIKEL.ID_ARTIKEL)='#WERT#'",                 //NEU_09
					null,
					 null, 
					 "SELECT trim(  NVL(ANR1,'-')) || ' - ' || trim(  NVL(JT_ARTIKEL.ARTBEZ1,'-')) "+
    									" from " + 
    									bibE2.cTO() + ".JT_ARTIKEL " +
    									"WHERE " +
    									"ID_ARTIKEL=#WERT#", E2_INSETS.I_0_0_2_0, false);
		this.set_oPosX(null);
		this.set_oPosY(null);
		
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			DB_SEARCH_Sorte oRueck = new DB_SEARCH_Sorte(this.EXT_DB().get_oSQLField());
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			oRueck.get_oTextFieldForSearchInput().setWidth(this.get_oTextFieldForSearchInput().getWidth());
			oRueck.add_ValidatorStartSearch(this.get_ValidatorStartSearch());
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("Error Copying DB_SEARCH_Sorte: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
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
