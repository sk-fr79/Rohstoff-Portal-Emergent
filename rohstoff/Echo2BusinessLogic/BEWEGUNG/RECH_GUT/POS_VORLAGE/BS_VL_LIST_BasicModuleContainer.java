package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;


public class BS_VL_LIST_BasicModuleContainer extends Project_BasicModuleContainer
{
	
	private E2_NavigationList   		    oNaviList = null;
	private BS_VL_MASK_ModulContainer    oModulContainerMASK =  null;

	
	/**
	 * @param opane
	 * @param cID_VPOS_TPA_FUHRE == null, dann das freie fuhren-modul, sonst die ansicht bei der umbuchung
	 * @throws myException
	 */
	public BS_VL_LIST_BasicModuleContainer() throws myException 
	{
		super(E2_MODULNAMES.NAME_MODUL_POSITION_VORLAGEN);
		
		this.oModulContainerMASK  = new BS_VL_MASK_ModulContainer();
		this.oNaviList = new E2_NavigationList();
		
		this.oNaviList.INIT_WITH_ComponentMAP(new BS_VL_LIST_ComponentMAP(),null, this.get_MODUL_IDENTIFIER());
		this.add(new BS_VL_LIST_BedienPanel(this));
		this.add(this.oNaviList);
		this.oNaviList._REBUILD_COMPLETE_LIST("");   // im fuhren-buchungsmodul eingebettet
	}


	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}


	public BS_VL_MASK_ModulContainer get_oModulContainerMASK()
	{
		return oModulContainerMASK;
	}
	
	
	
	
}
