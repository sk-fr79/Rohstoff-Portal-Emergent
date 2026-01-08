package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FSK_ModulContainer_MASK extends E2_BasicModuleContainer_MASK
{

	FSK_MASK_ComponentMAP oComponentMAP_MASK = null;
	
	public FSK_ModulContainer_MASK() throws myException
	{
		super();

		this.oComponentMAP_MASK = new FSK_MASK_ComponentMAP();
		this.INIT(this.oComponentMAP_MASK,new FSK_Mask(this.oComponentMAP_MASK),new Extent(900),new Extent(650));
	}

	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ADRESS_ID(String cID_Unformated) throws myException
	{
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_MASK.get_oSQLFieldMAP().get("ID_ADRESSE")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_MASK:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	
	
	
	
}
