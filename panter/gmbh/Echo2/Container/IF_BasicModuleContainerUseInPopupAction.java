package panter.gmbh.Echo2.Container;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.exceptions.myException;

public interface IF_BasicModuleContainerUseInPopupAction {
	
	// die navilist, damit in die referenz-componentMap die spalte mit den rueckgabe-buttons eingefuegt werden kann
	public E2_NavigationList 		getNavigationList() throws myException;
	
	//das fuellen nach dem einfuegen
	public void 					fillList() throws myException;

	//das fuellen mit einer uebergabe
	public void 					fillList(Object obj) throws myException;

	
	//der key fuer die neuen Rueckgabebutton innerhalb der Listem-E2_ComponentMap
	public String    				getMapKey4ReturnButton();
	
	//popup-titel
	public MyE2_String   			getTitle4Popup();
	
	//startBreite in pixel
	public default int 				getPopupWidth() {
		return 1000;
	}
	
	public default int 			 	getPopupHeight() {
		return 800;
	}

}
