package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;

/**
 * abstract klasse, kann dem Upload-fenster zur verfuegung gestellt werden und 
 * sucht unter bestimmten umstaenden attachments, die zu mail-sendeaktionen zugefuegt werden koennen
 * @author martin
 *
 */
public abstract class ES__AttachementSeeker {
	
	public abstract void init_with_archivInfos(String tableBaseName, String id_table) throws myException;
	public abstract Vector<String>  get_vPossible_id_Archivmedien_to_send(RB_Dataobject dataObject) throws myException;
}
