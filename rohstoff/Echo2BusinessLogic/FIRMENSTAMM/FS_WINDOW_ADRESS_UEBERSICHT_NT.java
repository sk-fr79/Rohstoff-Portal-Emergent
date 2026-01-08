package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI__Container;

/**
 * hilfsmethode zum schnellen ueberblick ueber adressen-informationen,
 * auch mehrere adressen gleichzeitig
 */

public class FS_WINDOW_ADRESS_UEBERSICHT_NT extends E2_BasicModuleContainer {
    
	private AI__Container ownContainer = null;
	private int   iDiffHeight = 160;
	
    public FS_WINDOW_ADRESS_UEBERSICHT_NT(Vector<String> v_AdressenIDs) throws myException   {
        super();
        
        if (v_AdressenIDs.size()==0) {
        	bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Adresse auswählen !")));
        	return;
        }

       
        this.ownContainer = new AI__Container(v_AdressenIDs);
        this.add(ownContainer);
        this.set_oResizeHelper(new ownResizeHelper());

        this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(740),new MyE2_String("Adressinformationen"));
        
    }

    
       
    private class ownResizeHelper extends XX_BasicContainerResizeHelper {
		@Override
		public void do_actionAfterResizeWindow(	E2_BasicModuleContainer ownContainer) throws myException {
			Extent  oHeight = FS_WINDOW_ADRESS_UEBERSICHT_NT.this.get_oExtHeight();
			if (oHeight.getUnits()==Extent.PX)	{
				FS_WINDOW_ADRESS_UEBERSICHT_NT.this.ownContainer.set_height_of_ContainerEx(new Extent(oHeight.getValue()-FS_WINDOW_ADRESS_UEBERSICHT_NT.this.iDiffHeight));
			}
		}
    	
    }
    
}
