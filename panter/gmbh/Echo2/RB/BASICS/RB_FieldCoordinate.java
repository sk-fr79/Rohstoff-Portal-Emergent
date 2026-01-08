/**
 * 
 */
package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * moeglichkeit, in einer komplexen struktur felder mit der angabe des mask- und -fieldkeys zu beschreiben
 */
public class RB_FieldCoordinate {
	private RB_KM  maskKey = null;
	private RB_KF  fieldKey = null;
	
	private String info4User = null;
	
	/**
	 * @param p_maskKey
	 * @param p_fieldKey
	 */
	public RB_FieldCoordinate(RB_KM p_maskKey, RB_KF p_fieldKey) throws myException {
		super();
		this.maskKey = p_maskKey;
		this.fieldKey = p_fieldKey;
		
		if (this.maskKey==null || this.fieldKey==null) {
			throw new myException(this, "Null is not allowed !");
		}
	}
	
	
	/**
	 * @param p_maskKey
	 * @param p_fieldKey
	 */
	public RB_FieldCoordinate(RB_KM p_maskKey, IF_Field p_field) throws myException {
		super();
		this.maskKey = p_maskKey;
		this.fieldKey = p_field.fk();
		
		if (this.maskKey==null || this.fieldKey==null) {
			throw new myException(this, "Null is not allowed !");
		}
	}


	public RB_KM getMaskKey() {
		return maskKey;
	}
	public RB_KF getFieldKey() {
		return fieldKey;
	}
	public void setMaskKey(RB_KM maskKey)  throws myException {
		this.maskKey = maskKey;
		if (this.maskKey==null || this.fieldKey==null) {
			throw new myException(this, "Null is not allowed !");
		}
	}
	public void setFieldKey(RB_KF fieldKey)  throws myException {
		this.fieldKey = fieldKey;
		if (this.maskKey==null || this.fieldKey==null) {
			throw new myException(this, "Null is not allowed !");
		}
	}
	
	private String getHashKey() {
		return this.maskKey.get_HASHKEY()+"@"+this.fieldKey.get_HASHKEY();
	}
	
	@Override	
	public int hashCode() {
		return this.getHashKey().hashCode();
	}
	
	
	@Override
	public boolean equals( Object obj ) {
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof RB_FieldCoordinate)) {
			return false;
		}
		
		if (obj==this) {
			return true;
		}
		
		RB_FieldCoordinate rbFieldAdress = (RB_FieldCoordinate)obj;
		
		if (this.getHashKey().equals(rbFieldAdress.getHashKey())) {
			return true;
		}
		
		return false;
	}


	public String getInfo4User() {
		if (S.isEmpty(this.info4User)) {
			return this.getHashKey();
		}
		return info4User;
	}


	public RB_FieldCoordinate setInfo4User(String info4User) {
		this.info4User = info4User;
		return this;
	}

	
}
