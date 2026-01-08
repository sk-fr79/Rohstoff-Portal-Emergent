package rohstoff.Echo2BusinessLogic.FIBU;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_MASK__ModulContainer;

public class FIBU_BT_LIST_OPEN_RECH_GUT extends E2_DB_BUTTON_OPEN_MASK_FromID
{
	 
	private FIBU_LIST_BasicModuleContainer 	oFIBU_ModulContainerLIST = null;
	private BSRG_K_MASK__ModulContainer     oBasicContainerMASK_RG = null;

	public FIBU_BT_LIST_OPEN_RECH_GUT(	SQLField 							osqlField, 
										BSRG_K_MASK__ModulContainer 		ModulContainerMASK, 
										FIBU_LIST_BasicModuleContainer 		FIBU_ModulContainerLIST) throws myException
	{
		super(	osqlField, 
				ModulContainerMASK,
				new MyE2_String("Adresse"), 
				null,
				"RECHNUNG_GUTSCHRIFT_BEARBEITEN", 
				"RECHNUNG_GUTSCHRIFT_ANSICHT");
		
		this.oBasicContainerMASK_RG = ModulContainerMASK;
		this.oFIBU_ModulContainerLIST = FIBU_ModulContainerLIST;
		
		this.get_vActionAgentsAfterSave().add(new actionAfterSave());
		this.get_vActionAgentsAfterCancel().add(new actionAfterSave());
		
		this.add_IDValidator(new XX_ActionValidator()
		{
			@Override
			public MyE2_MessageVector isValid(Component componentWhichIsValidated) throws myException
			{
				return new MyE2_MessageVector();
			}

			@Override
			protected MyE2_MessageVector isValid(String cID_VKOPF_RG)	throws myException
			{
				MyE2_MessageVector vMG = new MyE2_MessageVector();
				RECORD_VKOPF_RG  recTest = new RECORD_VKOPF_RG(cID_VKOPF_RG);
				if (recTest.is_ABGESCHLOSSEN_YES())
				{
					vMG.add_MESSAGE(new MyE2_Alarm_Message("Bearbeiten von geschlossenen Rechnungen/Gutschriften ist nicht erlaubt !"));
				}
				return vMG;
			}
			
		});
		
		this.get_vActionsBeforeExecute().add(new ownActionBeforeButtonAction());
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FIBU_BT_LIST_OPEN_RECH_GUT oButtCopy = null;
		
		try
		{
			oButtCopy = new FIBU_BT_LIST_OPEN_RECH_GUT(this.EXT_DB().get_oSQLField(),
													(BSRG_K_MASK__ModulContainer)this.get_oBasicModulContainer_MASK(),
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
			FIBU_BT_LIST_OPEN_RECH_GUT oThis = FIBU_BT_LIST_OPEN_RECH_GUT.this;
			oThis.oFIBU_ModulContainerLIST.get_oNaviListFirstAdded()._REBUILD_ACTUAL_SITE("");
		}
		
	}
	
	
	
	private class ownActionBeforeButtonAction extends E2_DB_BUTTON_OPEN_MASK_FromID.X_ActionBeforeExecuteButton
	{

		@Override
		public void do_Action(String cid) throws myException
		{
			RECORD_VKOPF_RG recTest = new RECORD_VKOPF_RG(cid);
			
			FIBU_BT_LIST_OPEN_RECH_GUT.this.oBasicContainerMASK_RG.set_SETTING(new BS__SETTING(recTest.get_VORGANG_TYP_cUF()));
		}
		
	}
}
