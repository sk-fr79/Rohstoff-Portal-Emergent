package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class FUS_Window extends Project_BasicModuleContainer
{
	private E2_NavigationList   				oNaviList = null;
//	private E2_BasicModuleContainer_MASK 		oMaskContainer = null;
	
	//das Container-Grid enthaelt einen Block fuer schnellerfassung und einen mit ergebniszeilen
	private MyE2_Grid    						oGrid4Content = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	private boolean   					  bVarianteFahrplan = false;
	
	public FUS_Window(		E2_NavigationList              	NaviList, 
							boolean  						VarianteFuerFahrplan) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_FUHREN_SCHNELLERFASSUNG);
		
		this.bVarianteFahrplan = VarianteFuerFahrplan;
		
		this.oNaviList = NaviList;
//		this.oMaskContainer = 	maskContainer;
		
		this.add(this.oGrid4Content);
		
		this.oGrid4Content.add(new FUS_Grid_InnereErfassungsMaske(this.bVarianteFahrplan));
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), new MyE2_String("Fuhren-Schnell/-Mehrfacherfassung ..."));
	}

	
	public E2_NavigationList get_oNaviList()
	{
		return oNaviList;
	}

	
//	public E2_BasicModuleContainer_MASK get_oMaskContainer()
//	{
//		return oMaskContainer;
//	}

}
