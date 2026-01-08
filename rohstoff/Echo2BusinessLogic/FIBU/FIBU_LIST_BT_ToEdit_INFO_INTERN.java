package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Button_TO_EditField_DirektEditAndSave;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FIBU_LIST_BT_ToEdit_INFO_INTERN extends MyE2_Button_TO_EditField_DirektEditAndSave
{

	private UB_TextArea	   					oTA_ZusatzInfo = null;
	private String         					cID_FIBU_UF = null;
	private FIBU_LIST_BasicModuleContainer  oFIBU_LIST_BasicModuleContainer = null;
	private E2_BasicModuleContainer         oContainerForThis = null;
	private MyE2_Button 					oButtonSave = new MyE2_Button(new MyE2_String("Speichern"));
	private MyE2_Button   					oButtonCancel = new MyE2_Button(new MyE2_String("Abbruch"));
	
	public FIBU_LIST_BT_ToEdit_INFO_INTERN(FIBU_LIST_BasicModuleContainer  LIST_BasicModuleContainer) throws myException
	{
		super("--");
		this.setLineWrap(true);
		this.oTA_ZusatzInfo = 	new UB_TextArea("INTERN_INFO",true,"",400,8,800);
		this.oFIBU_LIST_BasicModuleContainer = LIST_BasicModuleContainer;
		
		this.oButtonSave.add_oActionAgent(new ownActionAgentSave());
		this.oButtonSave.add_GlobalAUTHValidator_AUTO("AENDERE_BUCHUNGS_INFO_INTERN");
		this.oButtonSave.add_oActionAgent(new ownActionCloseWindow());
		
		this.oButtonCancel.add_oActionAgent(new ownActionCloseWindow());
		
		this.EXT().set_cList_or_Mask_Titel(new MyE2_String("Buchungsinfos (intern)"));
		
		this.setToolTipText(new MyE2_String("Buchungsinfo INTERN dieser Buchungszeile ändern").CTrans());
		
		
	}
	
	//in der liste immer enabled
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}

	@Override
	public MyE2_Grid get_GridWithComponents() throws myException
	{
		MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.add(this.oTA_ZusatzInfo);
		return oGridRueck;
	}

	@Override
	public E2_BasicModuleContainer get_BasicModuleContainer() throws myException
	{
		this.oContainerForThis=new ownContainer();
		return this.oContainerForThis;
	}

	@Override
	public boolean check_StatusBeforePopup(E2_BasicModuleContainer oContainer, Vector<MyE2_String> vInfoTexts, MyE2_Button oBtSave, MyE2_Button oBtCancel) throws myException
	{
		return true;
	}

	@Override
	public MyE2_Button get_ButtonToSave() throws myException
	{
		return this.oButtonSave;
	}

	@Override
	public MyE2_Button get_ButtonToCancel() throws myException
	{
		
		return this.oButtonCancel;
		
	}

	
	
	private class ownContainer extends E2_BasicModuleContainer
	{
		public ownContainer()
		{
			super();
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		
		try
		{
			return new FIBU_LIST_BT_ToEdit_INFO_INTERN(this.oFIBU_LIST_BasicModuleContainer);
		}
		catch (myException e)
		{
			throw new myExceptionCopy(e.ErrorMessage);
		}
		
	}

	
	
	private class ownActionAgentSave extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FIBU_LIST_BT_ToEdit_INFO_INTERN oThis = FIBU_LIST_BT_ToEdit_INFO_INTERN.this;
			
			if (oThis.cID_FIBU_UF!=null)
			{
				RECORD_FIBU  recFibu = new RECORD_FIBU(oThis.cID_FIBU_UF);
				
				String cNeu = S.NN(oThis.oTA_ZusatzInfo.get_cText());
				
				recFibu.set_NEW_VALUE_INTERN_INFO(cNeu);
				
				recFibu.UPDATE(null, true);

				//liste aktualisieren
				oThis.oFIBU_LIST_BasicModuleContainer.get_oNaviListFirstAdded().get_ComponentMAP(oThis.cID_FIBU_UF)._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, null);
			}
			else
			{
				throw new myException("Error: ID_FIBU was not set ....");
			}
			
		}
	}
	
	
	
	private class ownActionCloseWindow extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FIBU_LIST_BT_ToEdit_INFO_INTERN.this.oContainerForThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
		
	}
	
	
	public void set_cID_FIBU_UF(String ID_FIBU_UF)
	{
		this.cID_FIBU_UF = ID_FIBU_UF;
	}

	public void set_cINTER_INFO(String cINFO)
	{
		this.oTA_ZusatzInfo.setText(S.NN(cINFO));
	}
	
}
