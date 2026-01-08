package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentEDIT;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WF_HEAD_LIST_BT_EDIT extends MyE2_Button
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5290486144842118909L;

	public WF_HEAD_LIST_BT_EDIT(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer)
	{
		super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE,"BEARBEITE_WF_HEAD"));
		
		this.add_IDValidator(new XX_ActionValidator()
		{

			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				return null;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
//				MAP_LAUFZETTEL oMAP_LZ = new MAP_LAUFZETTEL(unformated);
//				if (oMAP_LZ.get_cUF_GELOESCHT_NN("N").equals("Y"))
//				{
//					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Editieren bei gelöschten Laufzetteln verboten!")));
//				}

				RECORD_LAUFZETTEL oMAP_LZ = new RECORD_LAUFZETTEL(unformated);
				if (oMAP_LZ.get_GELOESCHT_cUF_NN("N").equals("Y"))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Editieren bei gelöschten Laufzetteln verboten!")));
				}

				return oMV;
			}
		});
		
		// neuer Validierer, zum prüfen, dass nur der Besitzer oder der Supervisor den Datensatz bearbeiten darf
		this.add_IDValidator(new XX_ActionValidator()
		{

			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				return null;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated)	throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				String cID_USER = bibALL.get_ID_USER();
				RECORD_LAUFZETTEL oMAP_LZ = new RECORD_LAUFZETTEL(unformated);
//				MAP_LAUFZETTEL oMAP_LZ = new MAP_LAUFZETTEL(unformated);
				if (!oMAP_LZ.get_ID_USER_BESITZER_cUF().equals(cID_USER) 
						&& !bibALL.null2leer(oMAP_LZ.get_ID_USER_SUPERVISOR_cUF()).equals(cID_USER)
						&& !bibALL.get_bIST_SUPERVISOR())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Editieren darf nur der Besitzer oder Supervisor!")));
				}
				return oMV;
			}
		});

		
	}
	
	private class ownActionAgent extends ButtonActionAgentEDIT
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton)
		{
			super(new MyE2_String("Bearbeiten eines Laufzettels"), onavigationList, omaskContainer, oownButton, null, null);
		}
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vIDs =  new Vector<String>();
			vIDs.addAll(this.get_oNavigationList().get_vSelectedIDs_Unformated());

			bibMSG.add_MESSAGE(WF_HEAD_LIST_BT_EDIT.this.valid_IDValidation(vIDs));
			if (bibMSG.get_bIsOK())
			{
				super.executeAgentCode(oExecInfo);
			}
		}
	}
	

}
