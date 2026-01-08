package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * 2015-01-30: 	spezielle factory-klasse, die eine komponente erzeugt, die an der stelle
 * 				des normalen Labels in der liste beim Feld ID_EMAIL_SEND erzeugt. Die Komponente
 *              wird bei der uebergabe des datenbank-werts erzeugt.
 * @author martin
 *
 */
public abstract class UP_DOWN_AddOn_COL_ComponentFactory
{
	
	public abstract String get_MODULKENNER_CALLING_LIST_MODULE();
	
	/**
	 * 
	 * @return Tablename (substr(name,0,3)
	 */
	public abstract String get_TABLENAME_CORRELATED_TO_ARCHIVE();
	
	public abstract Component  generate_Component(E2_ComponentMAP oMAPofRow, String cID_Archivmedien_f) throws myException; 
	
}
