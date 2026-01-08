package panter.gmbh.Echo2.RB.BASICS;

import java.util.UUID;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class RB_KF extends RB_K {

	private IF_Field  data_field = null;
	
	
	//2018-08-10: leerer konstuctor
	public RB_KF() {
		super();
	}
	
	
	public RB_KF(String fieldName) throws myException {
		super(fieldName);
	}

	
	public RB_KF(IF_Field field) throws myException {
		super(field.fn());
		this.data_field = field;
	}

	public RB_KF(IF_Field field,String hashkeyName) throws myException {
		super(field.fn(), hashkeyName);
		this.data_field=field;
	}


	
	public RB_KF(String fieldName, String hashkeyName) throws myException {
		super(fieldName, hashkeyName);
	}


	public String FIELDNAME() {
		return this.get_REALNAME();
	}
	public String HASHKEY() {
		return this.get_HASHKEY();
	}


	/**
	 * 
	 * @return falls ein IF_Field im konstruktor genutzt wurde, dann wird dieser hier zurueckgegeben
	 */
	public IF_Field get_data_field() {
		return data_field;
	}
	
	
	//2018-08-10: selbstretunierende methode 
	public RB_KF _setHASHKEY(String hASHKEY) {
		this.HASHKEY = S.NN(hASHKEY,UUID.randomUUID().toString());
		return this;
	}

	//2018-08-10: selbstretunierende methode 
	public RB_KF _setREALNAME(String rEALNAME) {
		this.REALNAME = S.NN(rEALNAME,UUID.randomUUID().toString());
		return this;
	}

	//2018-08-10: selbstretunierende methode 
	public RB_KF _setKeyAndName(String keyAndName) {
		this.REALNAME = S.NN(keyAndName,UUID.randomUUID().toString());
		this.HASHKEY = S.NN(keyAndName,UUID.randomUUID().toString());
		return this;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 12.11.2020
	 *
	 * @param zusatz
	 * @return  s new RB_KF mit zusatztag (z.B. fuer feldbeschriftungen)
	 */
	public RB_KF getCpAdd(String zusatz) {
		RB_KF newKey = new RB_KF();
		
		if (S.isFull(this.HASHKEY)) {
			newKey._setHASHKEY(this.HASHKEY+"@"+zusatz);
		}
		if (S.isFull(this.REALNAME)) {
			newKey._setREALNAME(this.REALNAME+"@"+zusatz);;
		}
		
		
		return newKey;
	}
	
}
