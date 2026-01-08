package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.RECORD2.Rec20_field;
import panter.gmbh.indep.exceptions.myException;

/**
 * ermoeglicht den leichten zugriff auf die maskenobjekte im moment der maskenerfassung,
 * pro feld wird ein objekt erzeugt
 * @author martin
 *
 */
public class RB_MaskControllerField {
	
	//der formated-string, der beim laden an die maske uebergeben wurde
	private String  loaded_value_formated = null;
	
	//der momentane string in der maske
	private String  actual_value_formated = null;
	
	
	private MyE2IF__Component component = null;
	
	private Rec20_field   rec2_field = null; 
	
	private RB_KM maskKey = null;
	private RB_KF fieldKey = null;
	
	/**
	 * @param p_loaded_value_formated
	 * @param p_actual_value_formated
	 * @param p_metaDef
	 */
	public RB_MaskControllerField() {
		super();
	}

	public RB_MaskControllerField _set_loaded_value_formated(String p_loaded_db_value_formated) {
		this.loaded_value_formated=p_loaded_db_value_formated;
		return this;
	}
	public RB_MaskControllerField _set_actual_value_formated(String p_actual_mask_value_formated) {
		this.actual_value_formated=p_actual_mask_value_formated;
		return this;
	}
	public RB_MaskControllerField _set_rec20Field(Rec20_field   p_rec2_field) {
		this.rec2_field=p_rec2_field;
		return this;
	}
	public RB_MaskControllerField _set_component(MyE2IF__Component p_component) {
		this.component=p_component;
		return this;
	}
	


	public RB_KM get_maskKey() {
		return maskKey;
	}

	public RB_KF get_fieldKey() {
		return fieldKey;
	}

	public RB_MaskControllerField _set_maskKey(RB_KM maskKey) {
		this.maskKey = maskKey;
		return this;
	}

	public RB_MaskControllerField _set_fieldKey(RB_KF fieldKey) {
		this.fieldKey = fieldKey;
		return this;
	}
	
	
	public String get_loaded_value_formated() {
		return loaded_value_formated;
	}


	public String get_actual_value_formated() {
		return actual_value_formated;
	}


	public MyE2IF__Component get_component() {
		return component;
	}

	public Rec20_field get_rec2_field() {
		return rec2_field;
	} 
	
	
	
	public IF_BasicTypeWrapper get_object_dbVal() throws myException {
		if (this.rec2_field!=null) {
			MyMetaFieldDEF md = this.rec2_field.get_metadef();
			return md.get_object(this.loaded_value_formated);
		}		
		return null;
	}
	
	
	public IF_BasicTypeWrapper get_object_maskVal() throws myException {
		if (this.rec2_field!=null) {
			MyMetaFieldDEF md = this.rec2_field.get_metadef();
			return md.get_object(this.actual_value_formated);
		}
		return null;
	}

	
	
	
	
	public MyLong get_MyLong_dbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyLong) {
			return (MyLong)bo;
		} else {
			return  null;
		}
	}
	
	public MyLong get_MyLong_maskVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_maskVal();
		if (bo instanceof MyLong) {
			return (MyLong)bo;
		} else {
			return  null;
		}
	}
	
	
	public MyDate get_MyDate_dbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyDate) {
			return (MyDate)bo;
		} else {
			return  null;
		}
	}
	
	public MyDate get_MyDate_maskVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_maskVal();
		if (bo instanceof MyDate) {
			return (MyDate)bo;
		} else {
			return  null;
		}
	}

	
	
	
	public MyBigDecimal get_MyBigDecimal_dbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo instanceof MyBigDecimal) {
			return (MyBigDecimal)bo;
		} else if (bo != null && bo instanceof MyLong){
			MyLong l = (MyLong) bo;

			return new MyBigDecimal(l.get_lValue());
		} else {
			return  null;
		}
	}
	
	public MyBigDecimal get_MyBigDecimal_maskVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_maskVal();
		if (bo instanceof MyBigDecimal) {
			return (MyBigDecimal)bo;
		} else if (bo != null && bo instanceof MyLong){
			MyLong l = (MyLong) bo;

			return new MyBigDecimal(l.get_lValue());
		} else {
			return  null;
		}
	}
	
	
	/**
	 * maskenwert neu setzen (nur bei {@link IF_RB_Component}
	 * @param c_val
	 */
	public void set_maskVal(String c_val, MyE2_MessageVector mv) {
		if (this.component!=null && this.component instanceof IF_RB_Component) {
			try {
				((IF_RB_Component)this.component).rb_set_db_value_manual(c_val);
			} catch (myException e) {
				String feldname = "";
				if (this.rec2_field!=null) {
					feldname = this.rec2_field.get_field().tnfn();
				}
				
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Setzen des Werts: ").ut(feldname).ut(S.NN(c_val)).t(" ergibt einen Fehler! ").ut(e.getMessage())));
				e.printStackTrace();
			}
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Setzen des Werts: ").ut(S.NN(c_val)).t(" ergibt einen Fehler! - die Komponente wurde nicht gefunden !")));
		}
	}

	
	/**
	 * maskenwert neu setzen (nur bei {@link IF_RB_Component_Savable}
	 * @param c_val
	 */
	public void set_maskValIfEmpty(String c_val, MyE2_MessageVector mv) {
		if (this.component!=null && this.component instanceof IF_RB_Component_Savable) {
			try {
				if (S.isEmpty(((IF_RB_Component_Savable)this.component).rb_readValue_4_dataobject())) {
					((IF_RB_Component)this.component).rb_set_db_value_manual(c_val);
				}
			} catch (myException e) {
				String feldname = "";
				if (this.rec2_field!=null) {
					feldname = this.rec2_field.get_field().tnfn();
				}
				
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Setzen des Werts: ").ut(feldname).ut(S.NN(c_val)).t(" ergibt einen Fehler! ").ut(e.getMessage())));
				e.printStackTrace();
			}
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Setzen des Werts: ").ut(S.NN(c_val)).t(" ergibt einen Fehler! - es wurde keine solche speicherbare komponenten gefunden !")));
		}
	}


	
	
	//2017-11-09: neue getter, liefern immer My-Object object, im null-Fall eines not ok
	
	/**
	 * liefert immer MyLong, falls etwas schiefgeht ein fehlerhaftes
	 * @return
	 * @throws myException
	 */
	public MyLong getMyLongDbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		if (bo==null) {
			return new MyLong(null);
		} else {
			if (bo instanceof MyLong) {
				return (MyLong)bo;
			} else {
				return new MyLong(null);
			}
		}
	}
	
	/**
	 * liefert immer MyLong, falls etwas schiefgeht ein fehlerhaftes
	 * @return
	 * @throws myException
	 */
	public MyLong getMyLongMaskVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_maskVal();
		if (bo==null) {
			return new MyLong(null);
		} else {
			if (bo instanceof MyLong) {
				return (MyLong)bo;
			} else {
				return new MyLong(null);
			}
		}
	}
	
	/**
	 * liefert immer MyDate, falls etwas schiefgeht ein fehlerhaftes
	 * @return
	 * @throws myException
	 */
	public MyDate getMyDateDbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();

		if (bo==null) {
			return new MyDate("");
		} else {
			if (bo instanceof MyDate) {
				return (MyDate)bo;
			} else {
				return new MyDate("");
			}
		}
	}

	
	/**
	 * liefert immer MyDate, falls etwas schiefgeht ein fehlerhaftes
	 * @return
	 * @throws myException
	 */
	public MyDate getMyDateMaskVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_maskVal();
		
		if (bo==null) {
			return new MyDate("");
		} else {
			if (bo instanceof MyDate) {
				return (MyDate)bo;
			} else {
				return new MyDate("");
			}
		}
	}

	/**
	 * liefert immer MyBigDecimal, falls etwas schiefgeht ein fehlerhaftes
	 * @return
	 * @throws myException
	 */
	public MyBigDecimal getMyBigDecimalDbVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_dbVal();
		
		if (bo == null) {
			return new MyBigDecimal("");
		} else {
			if (bo instanceof MyBigDecimal) {
				return (MyBigDecimal)bo;
			} else if (bo != null && bo instanceof MyLong){
				MyLong l = (MyLong) bo;
	
				return new MyBigDecimal(l.get_lValue());
			} else {
				return  new MyBigDecimal("");
			}
		}
	}
	
	/**
	 * liefert immer MyBigDecimal, falls etwas schiefgeht ein fehlerhaftes
	 * @return
	 * @throws myException
	 */
	public MyBigDecimal getMyBigDecimalMaskVal()  throws myException {
		IF_BasicTypeWrapper bo = this.get_object_maskVal();
		if (bo == null) {
			return new MyBigDecimal("");
		} else {
			if (bo instanceof MyBigDecimal) {
				return (MyBigDecimal)bo;
			} else if (bo != null && bo instanceof MyLong){
				MyLong l = (MyLong) bo;
	
				return new MyBigDecimal(l.get_lValue());
			} else {
				return  new MyBigDecimal("");
			}
		}
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 08.04.2019
	 *
	 * @return s raw-object value from actual mask-content
	 */
	public Object getRawLiveVal() {
		Object ret = null;
		try {
			ret = this.get_rec2_field().get_metadef().getRaw(this.get_actual_value_formated());
		} catch (myException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 
	 * @author martin
	 * @date 08.04.2019
	 *
	 * @return s raw-object value from actual mask-content
	 */
	public Object getRawLiveValThrowsEx() throws Exception {
		Object ret = null;
		ret = this.get_rec2_field().get_metadef().getRaw(this.get_actual_value_formated());
		return ret;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 02.03.2020
	 *
	 * @return s acutal value on mask without refreshing this field
	 */
	public Object getRawValueJustInTime() throws myException {
		Object ret = null;
		boolean falseObjectTyp = false;
		try {
			if (this.get_component() instanceof IF_RB_Component_Savable) {
				ret = this.get_rec2_field().get_metadef().getRawWithoutDatabaseCheck( ((IF_RB_Component_Savable)this.get_component()).rb_readValue_4_dataobject());
			} else {
				falseObjectTyp = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (falseObjectTyp) {
			throw new myException("Function getRawValueJustInTime only available on IF_RB_Component_Savable - Types ! <9c6c47d2-5c6c-11ea-bc55-0242ac130003>");
		}
		
		return ret;

	}
	
	
}
