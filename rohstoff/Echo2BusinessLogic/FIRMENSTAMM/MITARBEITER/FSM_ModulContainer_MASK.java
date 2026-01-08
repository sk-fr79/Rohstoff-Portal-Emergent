package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FSM_ModulContainer_MASK extends E2_BasicModuleContainer_MASK
{

	private FSM_MASK_ComponentMAP 	oComponentMAP_MASK = null;
	private String 					ID_ADRESSE_BASIS_UF = null;
	
	
	

	public FSM_ModulContainer_MASK() throws myException
	{
		super();

		//this.set_bVisible_Row_For_Messages(false);   // die messagerow ausschalten
		
		this.oComponentMAP_MASK = new FSM_MASK_ComponentMAP(this);
		
		this.INIT(this.oComponentMAP_MASK,new FSM_Mask(this.oComponentMAP_MASK),new Extent(900),new Extent(600));
		
		this.add_SubTableComponentMAP(this.oComponentMAP_MASK.get_E2_ComponentMAP_Mitarbeiter());
	}

	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ADRESS_ID(String cID_Unformated) throws myException
	{
		try
		{
			this.ID_ADRESSE_BASIS_UF = cID_Unformated;
			
			((SQLFieldForRestrictTableRange)this.oComponentMAP_MASK.get_E2_ComponentMAP_Mitarbeiter().get_oSQLFieldMAP().get("ID_ADRESSE_BASIS")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_MASK:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	
	public String get_cID_ADRESSE_BASIS_UF() 
	{
		return ID_ADRESSE_BASIS_UF;
	}

}
