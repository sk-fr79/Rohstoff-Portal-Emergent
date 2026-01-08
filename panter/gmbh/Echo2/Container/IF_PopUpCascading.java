/**
 * 
 */
package panter.gmbh.Echo2.Container;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.WindowPane;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingWindowSize;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * interface erlaubt, popup-container zu implementieren, die sich kaskadierend aufbauen 
 */
public interface IF_PopUpCascading {
	
	/**
	 * 
	 * @param title
	 * @param diffX
	 * @param diffY
	 * @throws myException
	 */
	public default void popupCascading(MyE2_String title, int diffX, int diffY) throws myException {
		if (this instanceof E2_BasicModuleContainer) {
			E2_BasicModuleContainer cont = (E2_BasicModuleContainer)this;
			
			Component[] arr = bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().getComponents();
			
			WindowPane last = null;
			for (Component c: arr) {
				if (c instanceof WindowPane) {
					last= (WindowPane)c;
				}
			}
			if (last!=null) {
				//dann die position auf die des letzten fensters + 80 pixel setzen
				new E2_UserSettingWindowSize().storeContainer(cont, new Extent(last.getPositionX().getValue()+diffX), new Extent(last.getPositionY().getValue()+diffY), null, null);
			}
			
			cont.CREATE_AND_SHOW_POPUPWINDOW(title);
		}
	}
	
}
