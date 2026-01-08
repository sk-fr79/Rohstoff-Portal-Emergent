package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public interface MyE2IF__DB_Component extends MyE2IF_DB_SimpleComponent
{
	
	//2017-08-18: methode ausgelagert in das interface MyE2IF_DB_SimpleComponent
//	public void 		prepare_ContentForNew(boolean bSetDefault) throws myException;		// loeschen der anzeige / setzen des defaultwertes
	public String 		get_cActualMaskValue() throws myException;													// wert der anzeige in der maske
	public String 		get_cActualDBValueFormated() throws myException;											// liest den aktuellen datenbankwert aus
	public void 		set_cActualMaskValue(String cText) throws myException;										// setzt den anzeigewert in der maske
	//2015-09-02: methode ausgelagert in das interface MyE2IF_DB_SimpleComponent
	//public void 		set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException;		// setzt den formatierten wert im maskenfeld


	/*
	 * falls das maskenobject ein komplexes tochterobject ist (z.b. eine tochtertabelle beschreibt)
	 * wird ein schalter gesetzt.
	 * dann wird das sqlfield nicht benutzt, ein statement zu fuellen, sondern um 
	 * mit dem wert einen sql-stack zu bauen, der wiederum 
	 * mit der entsprechenden methode zurueckgegeben wird
	 */
	public void 				set_bIsComplexObject(boolean bisComplex);
	public boolean 			get_bIsComplexObject();
	public Vector<String>		get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException;
	public Vector<String>		get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException;
	
	
	public 				MyE2EXT__DB_Component EXT_DB();
	public void 		set_EXT_DB(MyE2EXT__DB_Component oEXT_DB);
	
	//2021-02-09: martin
	public default MyE2IF__DB_Component _setVisibleInList(boolean visible) {
		this.EXT().set_bIsVisibleInList(visible);
		return this;
	}

	
	
}
