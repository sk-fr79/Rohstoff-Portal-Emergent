package rohstoff.Echo2BusinessLogic.FAHRPLANVARIANTEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FV_SEARCH_Sorte extends MyE2_DB_MaskSearchField
{
	
	public FV_SEARCH_Sorte(		SQLField osqlField) throws myException
	{
		super(		osqlField, 
					"JT_ARTIKEL.ID_ARTIKEL,JT_ARTIKEL.ANR1,JT_ARTIKEL.ARTBEZ1", 
					bibE2.cTO()+".JT_ARTIKEL ", 
					"ANR1",
					"", 
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR ANR1 = '#WERT#' OR TO_CHAR(JT_ARTIKEL.ID_ARTIKEL)='#WERT#'", 
					null,
					 null, 
					 "SELECT trim(  NVL(ANR1,'-')) || ' - ' || trim(  NVL(JT_ARTIKEL.ARTBEZ1,'-')) "+
    									" from " + 
    									bibE2.cTO() + ".JT_ARTIKEL " +
    									"WHERE " +
    									"ID_ARTIKEL=#WERT#", null, false);
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(70);
		this.get_oTextForAnzeige().setWidth(new Extent(100));

		
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			return new FV_SEARCH_Sorte(this.EXT_DB().get_oSQLField());
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
