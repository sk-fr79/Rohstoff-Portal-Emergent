package panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER;

import java.util.Vector;

import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_ButtonMarkForDelete extends MyE2_Button  implements MyE2IF__Component, E2_IF_Copy
{
	
	private boolean bMarkedToDelete = false;
	

	
	public MyE2_ButtonMarkForDelete()
	{
		super(E2_ResourceIcon.get_RI("delete.png"),true);
		this.add_oActionAgent(new ownActionAgent());
	}



	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_ButtonMarkForDelete oButton = new MyE2_ButtonMarkForDelete();

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		if (this.get_oText()!=null) oButton.set_Text(this.get_oText());
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_ButtonMarkForDelete:get_Copy: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oButton.setStyle(this.getStyle());
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oButton.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oButton.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oButton.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));
		
		
		return oButton;
	}

	
	
	
	public boolean get_bMarkedToDelete()					{		return bMarkedToDelete;	}
	public void set_bMarkToDelete(boolean bmarkToDelete)	{		this.bMarkedToDelete = bmarkToDelete;	}

	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_ButtonMarkForDelete oButt = (MyE2_ButtonMarkForDelete)bibE2.get_LAST_ACTIONEVENT().getSource();
			boolean bNewStatusMarkForDelete = !oButt.get_bMarkedToDelete();
			
			oButt.set_bMarkToDelete(bNewStatusMarkForDelete);
			
			E2_ComponentMAP oMap = MyE2_ButtonMarkForDelete.this.EXT().get_oComponentMAP();
			
			oMap.set_AllComponentsEnabled_For_Edit(!bNewStatusMarkForDelete,E2_ComponentMAP.STATUS_UNDEFINED);
			
			if (bNewStatusMarkForDelete)
			{
				oMap.set_AllComponentsAsDeleted();
			}
			else
			{
				oMap.set_AllComponentsAsNormal();
			}
			
			
			/*
			 * dann muss der button hier wieder aktiviert werden, damit er nicht disabled wird
			 */
			oButt.set_bEnabled_For_Edit(true);
		}
	}

	
}
