package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public interface MyE2IF_DB_SimpleComponent  extends MyE2IF__Component{
	
	//2015-09-02: methode ausgelagert in das interface MyE2IF_DB_SimpleComponent,  um komponenten, die einen datenbankwert bekommen, zu erzeugen
	public void 		set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException;		// setzt den formatierten wert im maskenfeld

	//2017-08-18: methode ausgelagert in das interface MyE2IF_DB_SimpleComponent
	public void 		prepare_ContentForNew(boolean bSetDefault) throws myException;		// loeschen der anzeige / setzen des defaultwertes

	
	
}
