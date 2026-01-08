package rohstoff.Echo2BusinessLogic.BAM_FUHREN;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class BAMF_MASK_ModulContainer extends Project_BasicModuleContainer_MASK {

	private E2_NavigationList   oNaviList = null;
	
	private String  			idVPOS_TPA_FUHRE = null;
	private String  			idVPOS_TPA_FUHRE_ORT = null;

	
	public BAMF_MASK_ModulContainer(E2_NavigationList NaviList, BAMF_SQLFieldMAP oBAMF_FieldMAP) throws myException 	{
		super(E2_MODULNAMES.NAME_FUHRE_FBAM_MASK);
		
		this.oNaviList = NaviList;
		
		this.idVPOS_TPA_FUHRE=		oBAMF_FieldMAP.getIdVPOS_TPA_FUHRE();
		this.idVPOS_TPA_FUHRE_ORT=	oBAMF_FieldMAP.getIdVPOS_TPA_FUHRE_ORT();
		
		BAMF_MASK_ComponentMAP 		oE2_ComponentMAP = 	new BAMF_MASK_ComponentMAP(oBAMF_FieldMAP,this,this.get_MODUL_IDENTIFIER());
		BAMF_Mask 					oMaskComponent	=	new BAMF_Mask(oE2_ComponentMAP);
		
		this.INIT(oE2_ComponentMAP,oMaskComponent,new Extent(1000),new Extent(700));
		this.add_CloseActions(new ownAction4Close());
	}

	
	
	private class ownAction4Close extends XX_ActionAgentWhenCloseWindow	{

		public ownAction4Close()	{
			super(BAMF_MASK_ModulContainer.this);
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException	{
			if (BAMF_MASK_ModulContainer.this.oNaviList != null)		{
				BAMF_MASK_ModulContainer.this.oNaviList._REBUILD_ACTUAL_SITE(null);
			}
		}
		
	}
	
	
	public String getIdVPOS_TPA_FUHRE() {
		return idVPOS_TPA_FUHRE;
	}


	public String getIdVPOS_TPA_FUHRE_ORT() {
		return idVPOS_TPA_FUHRE_ORT;
	}

}
