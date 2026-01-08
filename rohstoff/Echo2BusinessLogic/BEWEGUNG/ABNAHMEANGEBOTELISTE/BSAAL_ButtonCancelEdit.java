package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class BSAAL_ButtonCancelEdit extends MyE2_Button 
{

	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	
	
	
	public BSAAL_ButtonCancelEdit(BSAAL__ModulContainerLIST	oModulContainer) 
	{
		super(E2_ResourceIcon.get_RI("cancel.png"), true);
		
		this.oModulContainerList = oModulContainer;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Abbrechen der Eingabe ...").CTrans());
		
		//2015-12-15: die infobutton-jump-funktion einschalten
		this.add_oActionAgent(new BSAAL_ActionSetInfoButtonsInListe_Status(this.oModulContainerList.get_oNaviList(), true));

	}
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSAAL_ButtonCancelEdit oThis = BSAAL_ButtonCancelEdit.this;
			
			// welche IDs 
			Vector<E2_ComponentMAP> vComponentMaps = oThis.oModulContainerList.get_oNaviList().get_vComponentMAPS();
			
			for (int i=0;i<vComponentMaps.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vComponentMaps.get(i);
				if (((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).valid_IDValidation(
						bibALL.get_Vector(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID())
						).size()==0)
				{
					try
					{
						oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,new Boolean(false));
					}
					catch (myException ex)
					{
					}
				}
			}

			if (oThis.oModulContainerList.get_oDisabler() != null)
			{
				oThis.oModulContainerList.get_oDisabler().setEnabled();
				oThis.oModulContainerList.set_oDisabler(null);
			}

			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Eingabe wurde abgebrochen !"));
		}
	}
	
	
	
}
