package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	public MESSAGE_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE);
		
		
//		// DEBUG
//		// reminder überprüfen und ggf neu schreiben
//		try {
//			REMINDER_Handler oReminder = new REMINDER_Handler(bibALL.get_ID_USER());
//			oReminder.updateReminders();
//		} catch (Exception e) {
//			// ??
//		}		
		
		
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new MESSAGE_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		MESSAGE_LIST_BedienPanel oPanel = new MESSAGE_LIST_BedienPanel(oNaviList,new MESSAGE_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oMESSAGE_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
