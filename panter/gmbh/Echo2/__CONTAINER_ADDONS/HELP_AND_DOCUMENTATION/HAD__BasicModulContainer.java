package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AllMembersOfClass;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.exceptions.myException;

public class HAD__BasicModulContainer extends E2_BasicModuleContainer {

	private HAD__GridWithData 	gridWithData = null;
	private int    				iDiffHeight = 150;
	
	public HAD__BasicModulContainer(String cMODULE_KENNER ) throws myException {
		super();
		
		this.gridWithData = new HAD__GridWithData(cMODULE_KENNER);
		
		this.add(this.gridWithData, E2_INSETS.I(2,2,2,2));
		
		this.gridWithData._build();
		
		this.set_MODUL_IDENTIFIER(MODUL.POPUP_WINDOW_HELPTEXT_LIST.get_callKey());
		
		this.set_oResizeHelper(new ownResizeHelper());
	}

	
	   private class ownResizeHelper extends XX_BasicContainerResizeHelper {
			@Override
			public void do_actionAfterResizeWindow(	E2_BasicModuleContainer ownContainer) throws myException {
				Extent  oHeight = HAD__BasicModulContainer.this.get_oExtHeight();
				if (oHeight.getUnits()==Extent.PX)	{
					HAD__BasicModulContainer.this.gridWithData.set_height_of_ContainerEx(new Extent(oHeight.getValue()-HAD__BasicModulContainer.this.iDiffHeight));
				}
				
				Extent  oWidth = HAD__BasicModulContainer.this.get_oExtWidth();
				if (oWidth.getUnits()==Extent.PX)	{ 
					//jetzt alle bilder-elemente suchen und anpassen (breite)
					bilderSearcher bs = new bilderSearcher();
					for (Component comp: bs.get_vAllComponents()) {
						if (comp instanceof HAD_comp_ButtonImage) {
							((HAD_comp_ButtonImage)comp).set_ImageMaxSize(oWidth.getValue()-300);
						}
					}
				}
			}
	    	
	    }
	   
	   
	   
	   private class bilderSearcher extends E2_RecursiveSearch_AllMembersOfClass {

		   public bilderSearcher() {
			   super(HAD_comp_ButtonImage.class.getName());
		   }
		   
	   }
	   

}
