package rohstoff.Echo2BusinessLogic.FIBU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_StandardLayoutFactory;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class FIBU_BT_LIST_OPEN_FIRMA extends E2_DB_BUTTON_OPEN_MASK_FromID
{
	 
	private FIBU_LIST_BasicModuleContainer 	oFIBU_ModulContainerLIST = null;

	public FIBU_BT_LIST_OPEN_FIRMA(		SQLField 							osqlField, 
										FS_ModulContainer_MASK 				ModulContainerMASK, 
										FIBU_LIST_BasicModuleContainer 		FIBU_ModulContainerLIST) throws myException
	{
		super(	osqlField, 
				ModulContainerMASK,
				new MyE2_String("Adresse"), 
				null,
				"ADRESSE_BEARBEITEN", 
				"ADRESSE_ANSICHT");
		
		this.get_vActionAgentsAfterSave().add(new actionAfterSave());
		this.get_vActionAgentsAfterCancel().add(new actionAfterSave());
		this.oFIBU_ModulContainerLIST = FIBU_ModulContainerLIST;
		
		this.EXT().set_oLayout_ListTitelElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOPHeader());
		this.EXT().set_oLayout_ListElement(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOP());

		this.setLineWrap(false);
		
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FIBU_BT_LIST_OPEN_FIRMA oButtCopy = null;
		
		try
		{
			oButtCopy = new FIBU_BT_LIST_OPEN_FIRMA(this.EXT_DB().get_oSQLField(),
													(FS_ModulContainer_MASK)this.get_oBasicModulContainer_MASK(),
													this.oFIBU_ModulContainerLIST);
			
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
			FIBU_BT_LIST_OPEN_FIRMA oThis = FIBU_BT_LIST_OPEN_FIRMA.this;
			oThis.oFIBU_ModulContainerLIST.get_oNaviListFirstAdded()._REBUILD_ACTUAL_SITE("");
		}
		
	}
	
}
