package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;

public class BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE extends MyE2_Button
{
	
	private Vector<String> vID_Wiegekarten = new Vector<String>();
	private E2_ComponentMAP m_compMap  = null;
	
	
	public BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE(E2_ComponentMAP compMap, E2_BasicModuleContainer ContainerToCloseAfterJump) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass.png"));
		
		this.m_compMap = compMap;
		
		this.vID_Wiegekarten.clear();
		this.setToolTipText(new MyE2_String("Zeigt die selektierten Einträge in der Wiegekarten-Liste an.").CTrans());
		
		// dann der zum anzeigen der Werte
		this.add_oActionAgent(new actionAgentJumpToTargetList_dynamic(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE, this.vID_Wiegekarten, "Wiegekarten", ContainerToCloseAfterJump));

	}
	
	
	
	public BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE(Vector<String> v_ID_Fuhren, E2_BasicModuleContainer  ContainerToCloseAfterJump) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass.png"));

		this.vID_Wiegekarten.addAll(v_ID_Fuhren);
		
		if (this.vID_Wiegekarten.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffende Wiegekarte in der Wiegekarten-Liste an: ID: ",true,this.vID_Wiegekarten.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffenden Fuhren aus der Liste in der Wiegekarten-Liste an ...").CTrans());
		}
		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(E2_MODULNAMES.NAME_MODUL_WIEGEKARTE_LISTE, this.vID_Wiegekarten, "Wiegekarten", ContainerToCloseAfterJump));
	}
	
	
	
	
	/**
	 * Privater Action-Agent zum dynamischen übernehmen der Ziele aus der Componenten-Map
	 * Um die selektierten Einträge zu übernehmen
	 * @author manfred
	 *
	 */
	private class actionAgentJumpToTargetList_dynamic extends ActionAgentJumpToTargetList_STD {
		
		public actionAgentJumpToTargetList_dynamic(String ModuleName,
				Vector<String> IDs_Target, String LesbarerModulName,
				E2_BasicModuleContainer ContainerToClose) throws myException {
			super(ModuleName, IDs_Target, LesbarerModulName, ContainerToClose);
			
		}

		public actionAgentJumpToTargetList_dynamic(String ModuleName,
				Vector<String> IDs_Target, String LesbarerModulName,
				Vector<E2_BasicModuleContainer> ContainerToClose)
				throws myException {
			super(ModuleName, IDs_Target, LesbarerModulName, ContainerToClose);
			
		}

		public actionAgentJumpToTargetList_dynamic(String ModuleName,
				Vector<String> IDs_Target, String LesbarerModulName)
				throws myException {
			super(ModuleName, IDs_Target, LesbarerModulName);
			
		}

		
		
		@Override
		public void OVERRIDE_SETTINGS_BEFORE_ACTION() throws myException {
			super.OVERRIDE_SETTINGS_BEFORE_ACTION();
			// löschen der Targetlist 
			clearTargetList();
		}
		
		
		
		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException {
			
			BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE oThis = BTN_BAM_IMPORT__JUMP_TO_WIEGEKARTE.this;
			
			oThis.vID_Wiegekarten.clear();
			
			Vector<E2_ComponentMAP>  maps = oThis.m_compMap.get_oNavigationList_This_Belongs_to().get_vSelected_ComponentMaps();
			for (int i=0; i<maps.size(); i++){
				oThis.vID_Wiegekarten.add(maps.get(i).get_oInternalSQLResultMAP().get_UnFormatedValue("ID_WIEGEKARTE"));
			}
			
			if (oThis.vID_Wiegekarten.size() == 0){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss mindestens ein Eintrag ausgewählt sein."));
			}
			return oThis.vID_Wiegekarten;

		}
	}
	
}

