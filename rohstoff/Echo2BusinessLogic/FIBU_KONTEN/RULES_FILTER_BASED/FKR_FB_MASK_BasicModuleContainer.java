package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;


public class FKR_FB_MASK_BasicModuleContainer extends Project_BasicModuleContainer_MASK 
{
	private E2_NavigationList oNaviList; 
	
	public FKR_FB_MASK_BasicModuleContainer(E2_NavigationList oNaviList) throws myException
	{
		super(E2_MODULNAMES.NAME_MASK_FIBU_KONTEN_REGELN_FILTERBASED);
		
		this.set_bVisible_Row_For_Messages(true);
		
		FKR_FB_MASK_SQLFieldMAP_FILTER 				oFM_Filter = new FKR_FB_MASK_SQLFieldMAP_FILTER();
		FKR_FB_MASK_SQLFieldMAP_KONTENREGEL_NEU 	oFM_KR = new FKR_FB_MASK_SQLFieldMAP_KONTENREGEL_NEU(oFM_Filter);
		
		FKR_FB_MASK_ComponentMAP_FILTER 			oFKR_FB_MASK_ComponentMAP_Filter = 	new FKR_FB_MASK_ComponentMAP_FILTER(oFM_Filter,this);
		FKR_FB_MASK_ComponentMAP_KONTENREGEL_NEU	oFKR_FB_MASK_ComponentMAP_KR = 		new FKR_FB_MASK_ComponentMAP_KONTENREGEL_NEU(oFM_KR);
		
		this.INIT(oFKR_FB_MASK_ComponentMAP_Filter, new FKR_FB_MASK(oFKR_FB_MASK_ComponentMAP_Filter,oFKR_FB_MASK_ComponentMAP_KR), new Extent(900), new Extent(650));
		this.add_SubTableComponentMAP(oFKR_FB_MASK_ComponentMAP_KR);
		this.oNaviList = oNaviList;
	}
	
	public E2_NavigationList getModuleNaviList() {
		return oNaviList;
	}
	
	
}
