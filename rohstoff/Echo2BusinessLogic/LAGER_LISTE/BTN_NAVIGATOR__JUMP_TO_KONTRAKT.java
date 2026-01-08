package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_manage_attachments_and_mails;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;

public class BTN_NAVIGATOR__JUMP_TO_KONTRAKT extends MyE2_Button
{
	
	private Vector<String> vID_Kontrakte = new Vector<String>();
	private E2_ComponentMAP m_compMap  = null;
	private String          m_Modulname = null;
	
	
	public BTN_NAVIGATOR__JUMP_TO_KONTRAKT(E2_ComponentMAP compMap, E2_BasicModuleContainer ContainerToCloseAfterJump, boolean bIstVK) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_kontrakt.png"));
		
		this.m_compMap = compMap;
		
		if(ENUM_MANDANT_DECISION.USE_NEW_KONTRAK_TYP.is_YES()){
			m_Modulname = (bIstVK ? E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST_NG : E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST_NG);
		}else{
			m_Modulname = (bIstVK ? E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST : E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST);
		}
		
//		// Modulname abhängig ob ek oder vk
//		m_Modulname = (bIstVK ? E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST : E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST);
		
		
		this.vID_Kontrakte.clear();
		this.setToolTipText(new MyE2_String("Zeigt die selektierten Einträge in den Kontrakten an.").CTrans());
		
		// dann der zum anzeigen der Werte
		this.add_oActionAgent(new actionAgentJumpToTargetList_dynamic(m_Modulname, this.vID_Kontrakte, "Kontrakte", ContainerToCloseAfterJump));

	}
	
	
	
	public BTN_NAVIGATOR__JUMP_TO_KONTRAKT(Vector<String> v_ID_Fuhren, E2_BasicModuleContainer  ContainerToCloseAfterJump, boolean bIstVK) throws myException
	{
		super(E2_ResourceIcon.get_RI("kompass_kontrakt.png"));
		
		// Modulname abhängig ob ek oder vk
		
		if(ENUM_MANDANT_DECISION.USE_NEW_KONTRAK_TYP.is_YES()){
			m_Modulname = (bIstVK ? E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST_NG : E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST_NG);
		}else{
			m_Modulname = (bIstVK ? E2_MODULNAMES.NAME_MODUL_VK_KONTRAKT_LIST : E2_MODULNAMES.NAME_MODUL_EK_KONTRAKT_LIST);
		}
		
		
		this.vID_Kontrakte.addAll(v_ID_Fuhren);
		
		if (this.vID_Kontrakte.size()==1)
		{
			this.setToolTipText(new MyE2_String("Zeigt den betreffenden Kontrakte an: ID: ",true,this.vID_Kontrakte.get(0),false).CTrans());
		}
		else
		{
			this.setToolTipText(new MyE2_String("Zeigt die betreffenden Kontrakte an ...").CTrans());
		}
		this.add_oActionAgent(new ActionAgentJumpToTargetList_STD(m_Modulname, this.vID_Kontrakte, "Kontrakte", ContainerToCloseAfterJump));
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
			
			BTN_NAVIGATOR__JUMP_TO_KONTRAKT oThis = BTN_NAVIGATOR__JUMP_TO_KONTRAKT.this;
			
			oThis.vID_Kontrakte.clear();
			
//			Vector<E2_ComponentMAP>  maps = oThis.m_compMap.get_oNavigationList_This_Belongs_to().get_vSelected_ComponentMaps();
//			for (int i=0; i<maps.size(); i++){
//				oThis.vID_Kontrakte.add(maps.get(i).get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE"));
//			}
//			
//			if (oThis.vID_Kontrakte.size() == 0){
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss mindestens ein Eintrag ausgewählt sein."));
//			}
			return oThis.vID_Kontrakte;

		}
	}
	
}

