package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.indep.exceptions.myException;

public class EMS_bt_ViewListToMask extends RB_bt_List2Mask {

	private E2_NavigationList  NaviList = null;
			
	public EMS_bt_ViewListToMask(E2_NavigationList  naviList) {
		super(false);
		this.NaviList = naviList;
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new EMS__PopUpContainer(this.NaviList);
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
		Vector<String> vIDs = this.NaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only();
		
		RB_hm_multi_DataobjectsCollector hmContainer = new RB_hm_multi_DataobjectsCollector();
		
		if (vIDs.size()==0) {
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens einen Datensatz auswählen !")));
		} else {
			for (String id: vIDs) {
				hmContainer.put(id, new EMS_DataObjectContainer(id, false));
			}
		}
		return hmContainer;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("Anzeige eMail-Schablone");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("eMail-Schablone wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
		return null;
	}

}
