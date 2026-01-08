package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BSKC_BT_LIST_OPEN_FIRMA extends E2_DB_BUTTON_OPEN_MASK_FromID
{
	 
	private BSKC_ModulContainerLIST oBSKC_ModulContainerLIST = null;
	private String 				    cEK_VK = null;

	public BSKC_BT_LIST_OPEN_FIRMA(		SQLFieldMAP 					osqlFieldGroup, 
										E2_BasicModuleContainer_MASK 	ModulContainerMASK, 
										BSKC_ModulContainerLIST 		BSKC_ModulContainerLIST,
										String EK_VK) throws myException
	{
		super(	osqlFieldGroup.get_("AD_ID_ADRESSE"), 
				ModulContainerMASK,
				new MyE2_String("Adresse"), 
				null,
				"ADRESSE_BEARBEITEN", 
				"ADRESSE_ANSICHT");
		
		this.cEK_VK = EK_VK;
		
		this.get_vActionAgentsAfterSave().add(new actionAfterSave());
		this.get_vActionAgentsAfterCancel().add(new actionAfterSave());
		
		this.oBSKC_ModulContainerLIST = BSKC_ModulContainerLIST;
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		BSKC_BT_LIST_OPEN_FIRMA oButtCopy = null;
		
		try
		{
			oButtCopy = new BSKC_BT_LIST_OPEN_FIRMA(this.EXT_DB().get_oSQLFieldMAP(),
													this.get_oBasicModulContainer_MASK(),
													this.oBSKC_ModulContainerLIST,
													this.cEK_VK);
			
			oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
			if (this.getIcon() != null)
				oButtCopy.setIcon(this.getIcon());
			
			if (this.get_oText() != null)
				oButtCopy.set_Text(this.get_oText());
			
			oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
			
			oButtCopy.setStyle(this.getStyle());
			oButtCopy.setInsets(this.getInsets());
			oButtCopy.get_vActionAgentsAfterCancel().addAll(this.get_vActionAgentsAfterCancel());
			oButtCopy.get_vActionAgentsAfterSave().addAll(this.get_vActionAgentsAfterSave());

			
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("BSKC_BT_LIST_OPEN_FIRMA:get_Copy:Copy-Error!");
		}
		return oButtCopy;
	}
	
	
	private class actionAfterSave extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSKC_BT_LIST_OPEN_FIRMA oThis = BSKC_BT_LIST_OPEN_FIRMA.this;
			oThis.oBSKC_ModulContainerLIST.get_oNavigationList()._REBUILD_ACTUAL_SITE("");
		}
		
	}
	
}
