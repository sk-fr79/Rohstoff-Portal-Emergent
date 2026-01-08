package panter.gmbh.Echo2.DB_RB.BASICS;

import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;


/**
 * interface zur erweiterung der MyE2IF_Components mit den methoden, 
 * die benoetigt werden, um im Datenbank-Ablauf der RECORD-Basierten Masken zu funktionieren
 * @author martin
 *
 */
public interface IF_DBRB_Component extends MyE2IF__DB_Component {

	public void 		set_cActual_Formated_DBContent_To_Mask(MyRECORD  oRec) throws myException;
	public String      get_FIELDNAME() throws myException;
	
}
