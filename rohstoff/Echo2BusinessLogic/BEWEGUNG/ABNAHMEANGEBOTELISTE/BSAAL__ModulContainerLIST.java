package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.Container.E2_ComponentDisabler;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL__ModulContainerLIST extends Project_BasicModuleContainer 
{

	private E2_ComponentMAP 					oComponentMap = null;
	private E2_NavigationList					oNaviList = null;
	private BSAAL_ListSelector					oSelector = null;
	private BSAAL_ListBedienPanel				oBedienPanel = null;
		
	private E2_ComponentDisabler 	oDisabler = null;
	

	
	public BSAAL__ModulContainerLIST()  throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ABNAHMEABGEBOT_LISTENEINGABE);

		this.oNaviList = new E2_NavigationList();
		
		// column fuer alle folgenden elemente
		MyE2_Column oCol = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		
		
		this.oSelector = 	new BSAAL_ListSelector(this.oNaviList, this.get_MODUL_IDENTIFIER());
		this.oBedienPanel = new BSAAL_ListBedienPanel(this);

		this.oComponentMap = new BSAAL_ComponentMAP_List(this.oNaviList,this);
		this.oNaviList.INIT_WITH_ComponentMAP(this.oComponentMap,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), this.get_MODUL_IDENTIFIER());
		
		oCol.add(this.oSelector,new Insets(0,0,0,10));
		oCol.add(this.oBedienPanel);
		this.add(oCol);
		this.add(this.oNaviList);
		
		this.oSelector.do_RefreshSelectDateRange();
		this.oSelector.get_oSelVector().doActionPassiv();
		
		this.oBedienPanel.set_Status_ViewMode();
	}



	public E2_NavigationList get_oNaviList() 
	{
		return oNaviList;
	}


	public BSAAL_ListBedienPanel get_oBedienPanel() 
	{
		return oBedienPanel;
	}


	public BSAAL_ListSelector get_oSelector() 
	{
		return oSelector;
	}



	public E2_ComponentDisabler get_oDisabler()
	{
		return oDisabler;
	}


	public void set_oDisabler(E2_ComponentDisabler disabler)
	{
		oDisabler = disabler;
	}
	
	
}
