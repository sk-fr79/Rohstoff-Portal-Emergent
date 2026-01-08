package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;

public class ES_RB_DataObject extends RB_Dataobject_V1 {

	/**
	 * bei neuerfassung eines eMail-Objekts stehen die gewuenschten id_archivmedien zur auswahl fest
	 * (von aussen)
	 */
	private Vector<String>  v_id_archivmedien_at_new = new Vector<String>();
	
	private Vector<String>  v_id_archivmedien_possible = new Vector<String>();
	
	
	public ES_RB_DataObject(MyRECORD_IF_RECORDS recORD, MASK_STATUS status) throws myException {
		super(recORD, status);
	}

	public ES_RB_DataObject(MyRECORD_NEW recNEW, MyRECORD_IF_RECORDS recORD, MASK_STATUS status) throws myException {
		super(recNEW, recORD, status);
	}

	public ES_RB_DataObject(MyRECORD_NEW recNEW) throws myException {
		super(recNEW);
	}

	public ES_RB_DataObject(String tablename) throws myException {
		super(tablename);
	}

	public Vector<String> get_v__id_archivmedien_vorschlag() {
		return this.v_id_archivmedien_at_new;
	}

	public Vector<String> get_v_id_archivmedien_possible() {
		return this.v_id_archivmedien_possible;
	}


}
