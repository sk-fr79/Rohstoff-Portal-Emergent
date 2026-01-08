package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import panter.gmbh.indep.exceptions.myException;

public abstract class XX_FormaterForSearchResultButtons
{
	public abstract void DO_Format(XX_Button4SearchResultList oButton) throws myException;
	
	// die reset-Methode ist noetig, damit (im falle es ist eine Datenbank-basierte validierung, 
	// nicht bei jedem einzelnen button eine abfrage noetig ist
	public abstract void RESET()  throws myException;
	
	
	//2011-02-08: weitere manipulation moeglich
//	public abstract Component  DO_Transform(MyE2_Button oButton) throws myException;
	public abstract void  RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException;

}
