package panter.gmbh.Echo2.Container;

import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import nextapp.echo2.app.Extent;

public class E2_BasicModuleContainer_ExtendTabPaneHightAutomatic
{

	/*
	 * seit firefox 29 zeigen nicht groessen-gespeicherte POPUP-Fenster auf null-Hohe geschrumpfte Tab-Panes
	 * Dieses Modul soll das verhindern
	 */
	
	public E2_BasicModuleContainer_ExtendTabPaneHightAutomatic(E2_BasicModuleContainer oContainer, int iHeightInPixel) throws myException
	{
		super();
		
		
		E2_RecursiveSearch_Component oSearchComp = new E2_RecursiveSearch_Component(
				oContainer,
				bibALL.get_Vector(MyE2_TabbedPane.class.getName(), E2_Selection_Row_With_Multi_Cols.class.getName()),
				null);

		if (oSearchComp.get_vAllComponents().size()>=1) {
			
			if (oSearchComp.get_vAllComponents().get(0) instanceof MyE2_TabbedPane) {
				if ( ((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).get_bAutoHeight())	{
					((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).setHeight(
							new Extent(iHeightInPixel-(int)bibALL.get_RECORD_USER().get_VERTICAL_OFFSET_MASKTABS_lValue(new Long(180)).longValue()));
				}
			}
			else if (oSearchComp.get_vAllComponents().get(0) instanceof E2_Selection_Row_With_Multi_Cols) {
				if ( ((E2_Selection_Row_With_Multi_Cols)oSearchComp.get_vAllComponents().get(0)).get_bAutoHeight()) {
					((E2_Selection_Row_With_Multi_Cols)oSearchComp.get_vAllComponents().get(0)).set_iHeight_in_Pixel(
							iHeightInPixel-(int)bibALL.get_RECORD_USER().get_VERTICAL_OFFSET_MASKTABS_lValue(new Long(180)).longValue());
				}
			}

			
		}
		
		
		
	}

}
