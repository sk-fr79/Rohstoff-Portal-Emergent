package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

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

public class BTN_NAVIGATOR__JUMP_TO_FUHRE extends MyE2_Button
{
	
	private Vector<String> vID_Fuhren = new Vector<String>();
	private E2_ComponentMAP m_compMap  = null;
	
	
	public BTN_NAVIGATOR__JUMP_TO_FUHRE(E2_ComponentMAP compMap, E2_BasicModuleContainer ContainerToCloseAfterJump) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));
		
		this.m_compMap = compMap;
		
		this.vID_Fuhren.clear();
		this.setToolTipText(new MyE2_String("Zeigt die selektierten Einträge in der Fuhrenzentrale an.").CTrans());
		
		// dann der zum anzeigen der Werte
		this.add_oActionAgent(new actionAgentJumpToTargetList_dynamic(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, this.vID_Fuhren, "Fuhrenzentrale", ContainerToCloseAfterJump));

	}
	
	
	
	public BTN_NAVIGATOR__JUMP_TO_FUHRE(Vector<String> v_ID_Fuhren, E2_BasicModuleContainer  ContainerToCloseAfterJump) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_fuhre.png"));

		this.vID_Fuhren.addAll(v_ID_Fuhren);
		
		if (this.vID_Fuhren.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffende Fuhre in der Fuhrenzentrale an: ID: ",true,this.vID_Fuhren.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffenden Fuhren aus der Liste in der Fuhrenzentrale an ...").CTrans());
		}
		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, this.vID_Fuhren, "Fuhrenzentrale", ContainerToCloseAfterJump));
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
			
			BTN_NAVIGATOR__JUMP_TO_FUHRE oThis = BTN_NAVIGATOR__JUMP_TO_FUHRE.this;
			
			oThis.vID_Fuhren.clear();
			
			Vector<E2_ComponentMAP>  maps = oThis.m_compMap.get_oNavigationList_This_Belongs_to().get_vSelected_ComponentMaps();
			for (int i=0; i<maps.size(); i++){
				oThis.vID_Fuhren.add(maps.get(i).get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE"));
			}
			
			if (oThis.vID_Fuhren.size() == 0){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss mindestens ein Eintrag ausgewählt sein."));
			}
			return oThis.vID_Fuhren;

		}
	}
	
}

