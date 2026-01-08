package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_ContentPane_NUMBER_ONE;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class CAL_BasicModuleContainer extends E2_BasicModuleContainer
{	
	private CAL_GridWithDayButtons 			oColumnDay = 	null;
	private CAL_CalendarGrid  				oCalendarGrid = null;
	
	private MyE2_TabbedPane   				oTabbedPane = new MyE2_TabbedPane(null);
	
	
	
	public CAL_BasicModuleContainer(int Monat, int Jahr) throws myException
	{
		super();
		this.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_CALENDAR_POPUP);
		
		MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER());

		RowLayoutData oRowLayout = new RowLayoutData();
		oRowLayout.setInsets(new Insets(5,0,40,0));
		oRowLayout.setAlignment(new Alignment(Alignment.LEADING, Alignment.TOP));
		
		this.oColumnDay	= 		new CAL_GridWithDayButtons(this);
		this.oCalendarGrid = 	new CAL_CalendarGrid(Monat,Jahr,E2_INSETS.I_2_2_2_2,true,true,this);

		
		oRow.add(oCalendarGrid,oRowLayout);
		oRow.add(this.oColumnDay,oRowLayout);

		
		this.add(oRow, E2_INSETS.I_5_5_5_5);
		
		
//		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
//		{
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
//			{
//				E2_ContentPane oPaneBase= 
//					new E2_RecursiveSearchParent_ContentPane_NUMBER_ONE(CAL_BasicModuleContainer.this).get_FoundPane();
//				
//				Vector<Component> vComponents = new E2_RecursiveSearch_Component(oPaneBase,bibALL.get_Vector(ContainerAddon_TerminUndTodos.class.getName()),null).get_vAllComponents();
//				
//				if (vComponents.size()>0)
//				{
//					for (int i=0;i<vComponents.size();i++)
//					{
//						((ContainerAddon_TerminUndTodos)vComponents.get(i)).Refresh_Warner();
//					}
//				}
//	
//			}
//		});

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(500),new MyE2_String("Termine verwalten ..."));
		
	}

	public CAL_CalendarGrid get_oCalendarGrid() 
	{
		return oCalendarGrid;
	}

	public CAL_GridWithDayButtons get_oColumnDay() 
	{
		return oColumnDay;
	}
	
	
	public MyE2_TabbedPane get_TabbedPane()
	{
		return this.oTabbedPane;
	}
	
	
}
