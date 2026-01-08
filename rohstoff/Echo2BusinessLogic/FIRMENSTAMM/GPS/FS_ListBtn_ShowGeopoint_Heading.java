package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.util.Vector;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceShowAddressLocationsOnMapBean;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceShowAddressLocationsOnMap;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.ActionAgentJumpToTargetList_STD;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.exceptions.myException;

public class FS_ListBtn_ShowGeopoint_Heading extends MyE2_Button
{
	
	private Vector<String> vIDAdressen = new Vector<String>();
	private E2_ComponentMAP m_compMap  = null;
	
	
	
	public FS_ListBtn_ShowGeopoint_Heading(E2_ComponentMAP compMap) throws myException
	{
		super(E2_ResourceIcon.get_RI("geo_punkte_show.png"));
		
		this.m_compMap = compMap;
		
		this.vIDAdressen.clear();
		this.setToolTipText(new MyE2_String("Zeigt die selektierten Adressen auf der Karte an.").CTrans());
		
		// dann der zum anzeigen der Werte
		this.add_oActionAgent(new cActionAgent());
	}
	
	
	private Vector<String> get_vID_Target() throws myException {
		
		FS_ListBtn_ShowGeopoint_Heading oThis = FS_ListBtn_ShowGeopoint_Heading.this;
		
		oThis.vIDAdressen.clear();
		
		Vector<E2_ComponentMAP>  maps = oThis.m_compMap.get_oNavigationList_This_Belongs_to().get_vSelected_ComponentMaps();
		
		for (int i=0; i<maps.size(); i++){
			oThis.vIDAdressen.add(maps.get(i).get_oInternalSQLResultMAP().get_UnFormatedValue(ADRESSE.id_adresse.fn()));
		}
		
		if (oThis.vIDAdressen.size() == 0){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss mindestens ein Eintrag ausgewählt sein."));
		}
		
		return oThis.vIDAdressen;
	}
	
	
	
	private class cActionAgent extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			PdServiceShowAddressLocationsOnMapBean o = new PdServiceShowAddressLocationsOnMapBean();
			// adressen ermitteln
			FS_ListBtn_ShowGeopoint_Heading oThis = FS_ListBtn_ShowGeopoint_Heading.this ;
			
			o.showAddressLocationsOnMap(oThis.get_vID_Target());
		}
	}	
	
	
	
}

