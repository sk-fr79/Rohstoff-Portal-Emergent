/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

/**
 * @author martin
 * nicht modales fenster, das einen NaviList-status speichert und beim schliessen wiederherstellt
 */
public class E2_NaviListStatusSaver {


	private int 				actualSiteNumber = 0;
	private Vector<String>   	copyOfSegmentationIds = new Vector<String>();
	private E2_NavigationList   myList = null;
	private Vector<String>      selectedLines = new Vector<String>();
	
	public E2_NaviListStatusSaver(E2_NavigationList navilistToSave) {
		super();
		this.myList = navilistToSave;
		
		this.actualSiteNumber=navilistToSave.get_iActualPage();
		this.copyOfSegmentationIds.addAll(navilistToSave.get_vectorSegmentation());
		this.selectedLines.addAll(navilistToSave.get_vSelectedIDs_Unformated());
	}


	public int getActualSiteNumber() {
		return actualSiteNumber;
	}


	public Vector<String> getCopyOfSegmentationIds() {
		return copyOfSegmentationIds;
	}


	public E2_NavigationList getNavigationList() {
		return myList;
	}


	public Vector<String> getSelectedLines() {
		return selectedLines;
	}

	
	
	
	
	

}
