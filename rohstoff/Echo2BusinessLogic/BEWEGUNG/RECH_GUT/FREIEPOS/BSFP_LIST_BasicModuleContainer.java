package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class BSFP_LIST_BasicModuleContainer extends Project_BasicModuleContainer
{
	
	private E2_NavigationList   		oNaviList = null;
	private BSFP_MASK_ModulContainer    oModulContainerMASK =  null;

	
	/**
	 * @param opane
	 * @param cID_VPOS_TPA_FUHRE == null, dann das freie fuhren-modul, sonst die ansicht bei der umbuchung
	 * @throws myException
	 */
	public BSFP_LIST_BasicModuleContainer(String cID_VPOS_TPA_FUHRE) throws myException 
	{
		super(E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN);
		
		if(cID_VPOS_TPA_FUHRE!=null) this.set_bVisible_Row_For_Messages(false);
		
		this.oModulContainerMASK  = new BSFP_MASK_ModulContainer();
		this.oNaviList = new E2_NavigationList();
		
		this.oNaviList.INIT_WITH_ComponentMAP(new BSFP_LIST_ComponentMAP(cID_VPOS_TPA_FUHRE),null, this.get_MODUL_IDENTIFIER());

		BSFP_ListSelector oListSelektor = new BSFP_ListSelector(this.oNaviList, this.get_MODUL_IDENTIFIER());
		if(cID_VPOS_TPA_FUHRE==null) this.add(oListSelektor);
		if(cID_VPOS_TPA_FUHRE==null) this.add(new BSFP_LIST_BedienPanel(this));

		this.add(this.oNaviList);
		
		if (cID_VPOS_TPA_FUHRE==null) 
		{
			oListSelektor.get_oSelVector().doActionPassiv();
		}
		else
		{
			// die sortiermoeglichkeiten abschalten
			this.oNaviList.get_oComponentMAP__REF().set_allDBComponents_Sortable(false);
			this.oNaviList._REBUILD_COMPLETE_LIST("");   // im fuhren-buchungsmodul eingebettet
		}
		
	}


	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}



	public BSFP_MASK_ModulContainer get_oModulContainerMASK()
	{
		return oModulContainerMASK;
	}
	
	
	
	/*
	 * falls der container in der definition der fuhrenbuchungen benutzt wird,
	 * muss hier das restrict-tablerange-feld gesetzt werden
	 */
	public void set_ID_VPOS_TPA_FUHRE(String cID_VPOS_TPA_FUHRE_Unformated) throws myException
	{
		
		try
		{
			// zuerst die sqlfieldmap der componentmap der liste beschaffen
			E2_ComponentMAP oMapList = this.oNaviList.get_oComponentMAP__REF();
			SQLFieldMAP     oSQLFieldMAP_List = oMapList.get_oSQLFieldMAP();
			
			// zuerst die sqlfieldmap der componentmap der Maske beschaffen
			E2_ComponentMAP oMapMask = this.oModulContainerMASK.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
			SQLFieldMAP     oSQLFieldMAP_Mask = oMapMask.get_oSQLFieldMAP();
			
			// jetzt die beschraenkung setzen
			((SQLFieldForRestrictTableRange)oSQLFieldMAP_List.get_("ID_VPOS_TPA_FUHRE")).set_cRestrictionValue_IN_DB_FORMAT(cID_VPOS_TPA_FUHRE_Unformated);
			((SQLFieldForRestrictTableRange)oSQLFieldMAP_Mask.get_("ID_VPOS_TPA_FUHRE")).set_cRestrictionValue_IN_DB_FORMAT(cID_VPOS_TPA_FUHRE_Unformated);
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("BSFP_LIST_BasicModuleContainer:set_ID_VPOS_TPA_FUHRE:Error :"+ex.getLocalizedMessage());
		}
		
	}
	
	
	
}
