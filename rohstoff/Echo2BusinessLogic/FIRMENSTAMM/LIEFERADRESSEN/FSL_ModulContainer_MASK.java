package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class FSL_ModulContainer_MASK extends E2_BasicModuleContainer_MASK
{

	FSL_MASK_ComponentMAP oComponentMAP_MASK = null;
	String                m_cBASE_ADRESS_ID = null;
	
	public FSL_ModulContainer_MASK() throws myException
	{
		super();

		//2014-12-04: lager-maske bekommt identifer, damit die fieldrules greifen
		this.set_MODUL_IDENTIFIER(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_LAGER_MASKE);
		
		this.oComponentMAP_MASK = new FSL_MASK_ComponentMAP();
		this.INIT(this.oComponentMAP_MASK,new FSL_Mask(this.oComponentMAP_MASK),new Extent(800),new Extent(600));
		this.add_SubTableComponentMAP(this.oComponentMAP_MASK.get_E2_ComponentMAP_Lieferadresse());
	}

	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void set_BASE_ADRESS_ID(String cID_Unformated) throws myException
	{
		m_cBASE_ADRESS_ID = cID_Unformated;
		try
		{
			((SQLFieldForRestrictTableRange)this.oComponentMAP_MASK.get_E2_ComponentMAP_Lieferadresse().get_oSQLFieldMAP().get("ID_ADRESSE_BASIS")).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated);
		}
		catch (Exception ex)
		{
			throw new myException("FSM_ModulContainer_MASK:set_BASE_ADRESS_ID: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	
	/**
	 * gibt die ID der des Lieferortes zugrundeliegenden Basisadresse zurück
	 * @author manfred
	 * @date   25.04.2013
	 * @return
	 */
	public String get_BASE_ADRESS_ID(){
		return m_cBASE_ADRESS_ID;
	}
	
	
}
