package rohstoff.Echo2BusinessLogic.FAHRPLANVARIANTEN;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class FV_ModulContainerList extends Project_BasicModuleContainer
{

	private FV_LIST_ComponentMAP  oFV_LIST_ComponentMAP = null;
	
	/*
	 * in diese column wird immer die gerade geoeffnete Fahrplanliste eingefuegt
	 */
	private MyE2_Column			colFahrplanListe	= new MyE2_Column();
	
	
	public FV_ModulContainerList() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FAHRTENVARIANTEN);
		
		this.oFV_LIST_ComponentMAP = new FV_LIST_ComponentMAP();
		
		E2_NavigationList oNavList = new E2_NavigationList();
		/*
		 * ein komplettes editerpanel dazu laden
		 */
		E2_BASIC_EditListButtonPanel oEditPanel = new E2_BASIC_EditListButtonPanel(oNavList,true,true,true,
														null,null,this.get_MODUL_IDENTIFIER(),
														"", null, null, null);

		this.add(oEditPanel);
		this.add(this.colFahrplanListe);

		/*
		* dann die liste fuellen
		*/
		oNavList.INIT_WITH_ComponentMAP(this.oFV_LIST_ComponentMAP, new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());
		oNavList.Fill_NavigationList("");

		this.colFahrplanListe.add(oNavList);
		
	}
	
	
	
	
}
