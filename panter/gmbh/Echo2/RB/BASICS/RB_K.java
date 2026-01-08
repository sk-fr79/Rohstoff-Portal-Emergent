package panter.gmbh.Echo2.RB.BASICS;

import java.util.UUID;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


/**
 * klasse zum kapseln von hashkeys und realen namen
 * Beispiel: wird ein Maskenfeld innerhalb einer Maske 2 mal verwendet, dann kann dem feld er reale name uebermittelt werden und
 *           im hash trotzdem ein unterschiedlicher hashkey erfasst sein
 * @author martin
 *
 */
public abstract class RB_K {
	
	protected String HASHKEY = null;          	
	protected String REALNAME = null;          	
	
	//2018-08-10: leerer konstuctor
	public RB_K() {
		this._setHASHKEY(null);   //vergibt automatisch eine uuid
		this._setREALNAME(null);   //vergibt automatisch eine uuid
	}
	
	/**
	 * @param realName   
	 * @throws myException
	 */
	public RB_K(String realName) throws myException{
		super();
		if (S.isEmpty(realName) ) {
			throw new myException(this,"empty name not allowed !!!");
		}
		this.REALNAME = realName;
		this.HASHKEY = realName;
	}

	
	/**
	 * @param realName  
	 *  @param hashKey
	 * @throws myException
	 */
	public RB_K(String realName, String hashKey) throws myException{
		super();
		if (S.isEmpty(realName) ) {
			throw new myException(this,"empty name not allowed !!!");
		}
		this.REALNAME = realName;
		this.HASHKEY = hashKey;
		if (S.isEmpty(this.HASHKEY)) {
			this.HASHKEY=this.REALNAME;
		}
	}

	@Override	
	public int hashCode() {
		return this.HASHKEY.hashCode();
	}
	
	
	@Override
	public boolean equals( Object obj ) {
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof RB_K)) {
			return false;
		}
		
		if (obj==this) {
			return true;
		}
		
		RB_K rb_k_ext = (RB_K)obj;
		
		if (this.HASHKEY.equals(rb_k_ext.HASHKEY)) {
			return true;
		}
		
		return false;
	}


	public String get_HASHKEY() {
		return HASHKEY;
	}


	public void set_HASHKEY(String hASHKEY) {
		HASHKEY = hASHKEY;
	}


	public String get_REALNAME() {
		return REALNAME;
	}


	public void set_REALNAME(String rEALNAME) {
		REALNAME = rEALNAME;
	}
	
	

	//2018-08-10: selbstretunierende methode 
	public RB_K _setHASHKEY(String hASHKEY) {
		this.HASHKEY = S.NN(hASHKEY,UUID.randomUUID().toString());
		return this;
	}

	//2018-08-10: selbstretunierende methode 
	public RB_K _setREALNAME(String rEALNAME) {
		this.REALNAME = S.NN(rEALNAME,UUID.randomUUID().toString());
		return this;
	}

	//2018-08-10: selbstretunierende methode 
	public RB_K _setKeyAndName(String keyAndName) {
		this.REALNAME = S.NN(keyAndName,UUID.randomUUID().toString());
		this.HASHKEY = S.NN(keyAndName,UUID.randomUUID().toString());
		return this;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 03.01.2019
	 *
	 * @return info for debug:  S.NN(this.HASHKEY,"<hashkey>")+"/"+S.NN(this.REALNAME,"<realname>");
	 */
	public String getKeyAndName() {
		return S.NN(this.HASHKEY,"<hashkey>")+"/"+S.NN(this.REALNAME,"<realname>");
	}
	
}
