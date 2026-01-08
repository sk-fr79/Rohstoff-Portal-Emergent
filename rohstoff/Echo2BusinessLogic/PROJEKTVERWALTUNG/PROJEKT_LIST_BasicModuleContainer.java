package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_TAGE_DIFF_IN_LIST =		"NAME_OF_TAGE_DIFF_IN_LIST";
	
	public static final String NAME_OF_FORTGANG_DAUGHTER =		"NAME_OF_FORTGANG_DAUGHTER";
	public static final String NAME_OF_MITARBEITER_DAUGHTER =	"NAME_OF_MITARBEITER_DAUGHTER";
	public static final String NAME_OF_FIRMEN_DAUGHTER =		"NAME_OF_FIRMEN_DAUGHTER";

	
	public PROJEKT_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_PROJEKT_LISTE);
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_NavigationList oNaviList = new E2_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new PROJEKT_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		PROJEKT_LIST_BedienPanel oPanel = new PROJEKT_LIST_BedienPanel(oNaviList,new PROJEKT_MASK_BasicModuleContainer());
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oPROJEKT_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
}
