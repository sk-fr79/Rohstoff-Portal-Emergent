package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final	String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";

	private MyE2_Grid 					grid4Progress = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100()); 
	//private RECLIST_BEWEGUNG_VEKTOR  	rlVector = null;
	private String[][] ids_to_korr = null;
	
	public FZ_LIST_BasicModuleContainer() throws myException
	{
		super(E2_MODULNAME_ENUM.MODUL.FUZE_ATOM_BASED_LIST.get_callKey());
		
		this.set_bVisible_Row_For_Messages(true);
		
		FZ_NavigationList oNaviList = new FZ_NavigationList();
		oNaviList.INIT_WITH_ComponentMAP(new FZ_LIST_ComponentMap(oNaviList),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		FZ_LIST_BedienPanel oPanel = new FZ_LIST_BedienPanel(oNaviList);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

		//standard-sortierung, neue zuerst
		oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
		oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn()+" DESC");


		
		
		oPanel.get_oFZ_LIST_Selector().get_oSelVector().doActionPassiv();
	}
		
	
	



	
}
