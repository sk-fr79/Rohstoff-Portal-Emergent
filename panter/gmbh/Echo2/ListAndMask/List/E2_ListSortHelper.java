package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Row;

public class E2_ListSortHelper {

	
	public static void reset_all_sorters(E2_NavigationList oNaviList)
	{
		//----------------------
		
		/*
		 * die tabelle nach anderen sortbuttons durchsuchen und diese resetten,
		 * es koennen auch kombinationen von titelbuttons in der titelzeile stehen
		 */
		Grid oTable = oNaviList.get_oDataGrid();
		Component oComponent[] = oTable.getComponents();
		
		for (int i=0;i<oComponent.length;i++)
		{
			if (oComponent[i] instanceof IF_ListSorter) {
				((IF_ListSorter)oComponent[i]).Reset();
			}
			else if (oComponent[i] instanceof Row)   // dann evtl.geschachtelte titel-buttons
			{
				Component[] oComp = ((Row) oComponent[i]).getComponents();
				for (int k=0;k<oComp.length;k++)
				{
					if (oComp[k] instanceof IF_ListSorter) {
						((IF_ListSorter)oComp[k]).Reset();
					}
				}
			}
			else if (oComponent[i] instanceof Column)   // dann evtl.geschachtelte titel-buttons
			{
				Component[] oComp = ((Column) oComponent[i]).getComponents();
				for (int k=0;k<oComp.length;k++)
				{
					if (oComp[k] instanceof IF_ListSorter) {
						((IF_ListSorter)oComp[k]).Reset();
					}
				}
			}
			//aenderung 20101020: grids wurden beim resetten vergessen
			else if (oComponent[i] instanceof Grid)   // dann evtl.geschachtelte titel-buttons
			{
				Component[] oComp = ((Grid) oComponent[i]).getComponents();
				for (int k=0;k<oComp.length;k++)
				{
					if (oComp[k] instanceof IF_ListSorter) {
						((IF_ListSorter)oComp[k]).Reset();
					}
				}
			}

		}
		//----------------------

	}

	
	
	//2013-10-10: anzeiger, der den sortierungsstatus der sortierungsbuttons anzeigt
	public static void ShowSortStatusSortButtons(E2_NavigationList oNaviList) {
		
		//als erstes alle sortbuttons der liste beschaffen
		Vector<IF_ListSorter> vSortButtons = E2_ListSortHelper.get_vAllSortButtonsInList(oNaviList);
		
		Vector<String> vOrderSegment = oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_vOrderFields();
		
		if (vSortButtons!=null && vOrderSegment!=null) {
			for (IF_ListSorter oSortButton: vSortButtons) {
				for (String cSortString: vOrderSegment) {
					if (oSortButton.get_cSORT_STATEMENT_UP().equals(cSortString)) {
						oSortButton.set_SortedUP();
					} else if (oSortButton.get_cSORT_STATEMENT_DOWN().equals(cSortString)) {
						oSortButton.set_SortedDown();
					} else {
						oSortButton.set_Unsorted();
					}
				}
			}
		}
		
	}
	
	
	public static Vector<IF_ListSorter>  get_vAllSortButtonsInList(E2_NavigationList oNaviList) {
		
		Vector<IF_ListSorter>  vRueck = new Vector<IF_ListSorter>();
		
		/*
		 * die tabelle nach anderen sortbuttons durchsuchen und diese resetten,
		 * es koennen auch kombinationen von titelbuttons in der titelzeile stehen
		 */
		Grid oTable = oNaviList.get_oDataGrid();
		Component oComponent[] = oTable.getComponents();
		
		for (int i=0;i<oComponent.length;i++)
		{
			if (oComponent[i] instanceof IF_ListSorter)
				vRueck.add((IF_ListSorter)oComponent[i]);
			else if (oComponent[i] instanceof Row)   // dann evtl.geschachtelte titel-buttons
			{
				Component[] oComp = ((Row) oComponent[i]).getComponents();
				for (int k=0;k<oComp.length;k++)
				{
					if (oComp[k] instanceof IF_ListSorter)
						vRueck.add(((IF_ListSorter)oComp[k]));
				}
			}
			else if (oComponent[i] instanceof Column)   // dann evtl.geschachtelte titel-buttons
			{
				Component[] oComp = ((Column) oComponent[i]).getComponents();
				for (int k=0;k<oComp.length;k++)
				{
					if (oComp[k] instanceof IF_ListSorter)
						vRueck.add(((IF_ListSorter)oComp[k]));
				}
			}
			//aenderung 20101020: grids wurden beim resetten vergessen
			else if (oComponent[i] instanceof Grid)   // dann evtl.geschachtelte titel-buttons
			{
				Component[] oComp = ((Grid) oComponent[i]).getComponents();
				for (int k=0;k<oComp.length;k++)
				{
					if (oComp[k] instanceof IF_ListSorter)
						vRueck.add(((IF_ListSorter)oComp[k]));
				}
			}

		}
		//----------------------
		
		return vRueck;
	}
	
}
