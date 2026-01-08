package panter.gmbh.Echo2.RB.CONTROLLERS;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class RB_bt_delete_single extends RB_bt_delete {

	private String id_to_delete = null;
	
	public RB_bt_delete_single(String p_id_to_delete) throws myException {
		super();
		this.id_to_delete = p_id_to_delete;
	}

	@Override
	public Vector<String> get_ids_to_delete() throws myException {
		return new VEK<String>()._a(this.id_to_delete);
	}

	@Override
	public String get_message_text_mindestens_eine_irgendwas_markieren() {
		return "";
	}


}
