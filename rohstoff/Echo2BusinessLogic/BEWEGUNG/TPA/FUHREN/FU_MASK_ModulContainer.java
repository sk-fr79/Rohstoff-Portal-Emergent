package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_ModulContainer extends Project_BasicModuleContainer_MASK
{
	
	private E2_NavigationList    oNaviListFuhre = null;
	
	public FU_MASK_ModulContainer(String cModuleKenner, E2_NavigationList NaviListFuhre, boolean bAusFahrplan, boolean bAddOpenTPA_Button) throws myException
	{
		super(cModuleKenner==null?E2_MODULNAMES.NAME_MODUL_FUHRENMASKE:cModuleKenner);
		FU_MASK_ComponentMAP	oComponentMAP_MASK = 	new FU_MASK_ComponentMAP(new FU_MASK_SQLFieldMAP(null,bAusFahrplan), true, bAddOpenTPA_Button,(cModuleKenner==null?E2_MODULNAMES.NAME_MODUL_FUHRENMASKE:cModuleKenner));
		FU_MASK					oMask	= 				new FU_MASK(oComponentMAP_MASK);
		
		this.oNaviListFuhre = NaviListFuhre;
		
		this.set_bMustBeRefreshed(false);
		
		this.INIT(oComponentMAP_MASK,oMask,new Extent(1000), new Extent(700));
		
		this.set_iVerticalOffsetForTabbedPane(170);
		
		//immer maske refreshen, da evtl. nur ein fuhren-zusatzort geaendert wurde, damit aber der status der fuhre neu geschrieben wurde
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if ( FU_MASK_ModulContainer.this.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oInternalSQLResultMAP() !=null)
				{
					String cID_Fuhre = FU_MASK_ModulContainer.this.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
						
					if (FU_MASK_ModulContainer.this.oNaviListFuhre != null)
					{
						FU_MASK_ModulContainer.this.oNaviListFuhre.Refresh_ComponentMAP(cID_Fuhre, E2_ComponentMAP.STATUS_VIEW);
					}
				}
			}
		});
		
	}

	public E2_NavigationList get_oNaviListFuhre()
	{
		return oNaviListFuhre;
	}


	
}
