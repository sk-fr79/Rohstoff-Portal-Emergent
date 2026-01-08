package panter.gmbh.Echo2.RB.BASICS;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec20_field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class RB_MaskControllerMap extends HashMap<RB_KM, HashMap<RB_KF,RB_MaskControllerField>>  {

	
	private RB_ComponentMapCollector  componentMapCollector  = null;
	
	private Object                    starter = null;
	
	//abstracte methode, die das setting auf der maske ausfuehrt
	@Deprecated
	public abstract MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal,  boolean primaryCall) throws myException ;
	
	
	
	/**
	 * doppelte hashmap, der eine komplette maske mit allen komponenten abbildet
	 */
	public RB_MaskControllerMap(RB_ComponentMapCollector  p_componentMapCollector) throws myException {
		super();
		
		this.componentMapCollector = p_componentMapCollector;
		this._refresh();
	}
	
	
	public RB_MaskControllerMap(IF_RB_Component  p_component) throws myException {
		super();
		
		this.componentMapCollector = p_component._find_componentMapCollector_i_belong_to();
		this._refresh();
	}
	
	public RB_MaskControllerMap(RB_ComponentMap  p_componentMap) throws myException {
		super();
		
		this.componentMapCollector = p_componentMap.rb_get_belongs_to();
		this._refresh();
	}
	
	
	
	/**
	 * 
	 * @param p_componentMapCollector
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskControllerMap(RB_ComponentMapCollector  p_componentMapCollector, boolean immediateBuild) throws myException {
		super();
		
		this.starter = p_componentMapCollector;
		if (immediateBuild) {
			this._refresh();
		}
	}
	

	/**
	 * 
	 * @param p_component
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskControllerMap(IF_RB_Component  p_component, boolean immediateBuild) throws myException {
		super();
		
		this.starter = p_component;
		if (immediateBuild) {
			this._refresh();
		}
	}

	
	/**
	 * 
	 * @param p_componentMap
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskControllerMap(RB_ComponentMap  p_componentMap, boolean immediateBuild) throws myException {
		super();
		
		this.starter = p_componentMap;
		if (immediateBuild) {
			this._refresh();
		}
	}

	



	/**
	 * 
	 * @author martin
	 * @date 11.03.2020
	 * leerer konstructor, kann benutzt werden, um eine eingelagerte fehlerbehandlung zu ermoeglichen
	 */
	public RB_MaskControllerMap() {
		super();
	}

	
	/**
	 * 
	 * @author martin
	 * @date 11.03.2020
	 * 
	 * neue init-methode mit der moeglichkeit leere konstructoren zu bauen
	 *
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public RB_MaskControllerMap _init(Object object) throws Exception {
		
		if (object instanceof RB_ComponentMapCollector) {
			this.componentMapCollector = (RB_ComponentMapCollector)object;
			this._refresh();
		} else if (object instanceof IF_RB_Component) {
			this.componentMapCollector = ((IF_RB_Component)object)._find_componentMapCollector_i_belong_to();			this._refresh();
		} else if (object instanceof RB_ComponentMap) {
			this.componentMapCollector = ((RB_ComponentMap)object).rb_get_belongs_to();
			this._refresh();
		} else {
			throw new Exception("Construction of RB_MaskControllerMap only possible with: <RB_ComponentMapCollector>,<IF_RB_Component> or <RB_ComponentMap>");
		}
		
		return this;
	}
	
	


	
	
	public RB_MaskControllerMap _refresh() throws myException {
		this.clear();
		
		//die lazyload-varianten
		if (starter != null) {
			if 			(starter instanceof RB_ComponentMapCollector) {
				this.componentMapCollector = (RB_ComponentMapCollector)starter;
			} else if 	(starter instanceof IF_RB_Component) {
				this.componentMapCollector = ((IF_RB_Component)starter)._find_componentMapCollector_i_belong_to();
			} else if 	(starter instanceof RB_ComponentMap) {
				this.componentMapCollector = ((RB_ComponentMap)starter).rb_get_belongs_to();
			} else {
				throw new myException(this, "undefined starter - object !");
			}
		}
		
		RB_DataobjectsCollector  do_collector = componentMapCollector.rb_Actual_DataobjectCollector();
		
		if (do_collector==null) {
			throw new myException(this, "No actual Dataobject-Collector is present !!");
		}
		
		if (!(  (do_collector instanceof RB_DataobjectsCollector_V2) || (do_collector instanceof RB_DataobjectsCollector_V21) || (do_collector instanceof RB_DataobjectsCollector_V22))) {
			throw new myException(this, "Only possible with Dataobject-Collectors typ RB_DataobjectsCollector_V2/V21/V22 is present ");
		}
		
		//RB_DataobjectsCollector_V2  do_collector2 = (RB_DataobjectsCollector_V2)  do_collector;
		
		for (RB_KM maskKey: componentMapCollector.rb_hm_RB_ComponentMaps().keySet()) {
			HashMap<RB_KF,RB_MaskControllerField> hash = new HashMap<>();
			
			this.put(maskKey, hash);

			//hier die hash fuellen
			RB_ComponentMap 	map = componentMapCollector.get(maskKey) ;
			
			//hier die neuen RB_Dataobject_V21 beruecksichtigen
			Rec20 r20 = null;
			if (do_collector.get(maskKey) instanceof RB_Dataobject_V2) {
				RB_Dataobject_V2 	dob = (RB_Dataobject_V2)do_collector.get(maskKey);
				r20 = dob.get_rec20();
			}
			if (do_collector.get(maskKey) instanceof RB_Dataobject_V21) {
				r20 = (RB_Dataobject_V21)do_collector.get(maskKey);
			}
			if (do_collector.get(maskKey) instanceof RB_Dataobject_V22) {
				r20 = (RB_Dataobject_V22)do_collector.get(maskKey);
			}
			
			if (r20 == null) {
				throw new myException("System-Error:  4029800a-f194-11e8-8eb2-f2801f1b9fd1");
			}
			
			for (RB_KF fieldKey: map.getInnerComponentMap().keySet()) {
				

				
				RB_MaskControllerField mfc = new RB_MaskControllerField();
				
				mfc._set_maskKey(maskKey)._set_fieldKey(fieldKey);
				
				
				MyE2IF__Component comp = map.getInnerComponentMap().get(fieldKey);
				mfc._set_component(comp);
				
				//aenderung: 2018-11-26: Rec20_field     rf2 =   dob.get_rec20().get_field(fieldKey.FIELDNAME());
				Rec20_field     rf2 =  r20.get_field(fieldKey.FIELDNAME());
				mfc._set_rec20Field(rf2);
					
				if (rf2 != null) {
					mfc._set_loaded_value_formated(rf2.get_database_value_f());
				}
				//jetzt die moeglichen typen durchsehen
				if (comp instanceof IF_RB_Component_Savable) {
					mfc._set_actual_value_formated(((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject());
				} else if (comp instanceof IF_RB_Component) {
					mfc._set_actual_value_formated(mfc.get_loaded_value_formated());
				} else if (comp instanceof MyE2IF__DB_Component) {
					mfc._set_actual_value_formated(((MyE2IF__DB_Component)comp).get_cActualDBValueFormated());
				} 
				
				hash.put(fieldKey, mfc);
				
//				DEBUG.System_println("Tabelle:"+maskKey.get_REALNAME()+"  -> Field: "+fieldKey.get_REALNAME());
			}
		}
		return this;
	}
	
	
	
	public RB_MaskControllerField get_MaskControllerField(IF_RB_Component comp) throws myException {
		
		for (HashMap<RB_KF,RB_MaskControllerField> level1: this.values()) {
			for (RB_MaskControllerField field: level1.values()) {
				if (field.get_component()==comp) {
					return field;
				}
			}
		}
		return null;
	}
	
	
	public RB_MaskControllerField get_MaskControllerField(RB_KM mask, RB_KF field) {
		if (this.get(mask)!=null) {
			return this.get(mask).get(field);
		}
		return null;
	}

	
	public IF_RB_Component  getComponent(RB_KM mask, IF_Field field) {
		if (this.get(mask)!=null) {
			RB_KF fieldKey;
			try {
				fieldKey = field.fk();
				if (this.get(mask).get(fieldKey)!=null && this.get(mask).get(fieldKey).get_component() instanceof IF_RB_Component) {
					return (IF_RB_Component)this.get(mask).get(fieldKey).get_component();
				}
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public IF_RB_Component  getComponent(RB_KM mask, RB_KF fieldKey) {
		if (this.get(mask)!=null) {
			if (this.get(mask).get(fieldKey)!=null && this.get(mask).get(fieldKey).get_component() instanceof IF_RB_Component) {
				return (IF_RB_Component)this.get(mask).get(fieldKey).get_component();
			}
		}
		return null;
	}
	
	
	
	
	public MyLong get_MyLong_dbVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		
		return sti.get_MyLong_dbVal();
	}
	
	
	public MyLong get_MyLong_dbVal(IF_Field field)  throws myException {
		return this.get_MyLong_dbVal(field._t().rb_km(),field);
	}
	
	
	
	
	public MyLong get_MyLong_maskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		
		return sti.get_MyLong_maskVal();
	}
	
	
	public MyDate get_MyDate_dbVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		
		return sti.get_MyDate_dbVal();
	}
	
	public MyDate get_MyDate_dbVal(IF_Field field)  throws myException {
		return get_MyDate_dbVal(field._t().rb_km(),field);
	}	
	
	
	public MyDate get_MyDate_maskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		
		return sti.get_MyDate_maskVal();
		
	}

	public String get_maskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		return sti.get_actual_value_formated();
	}

	public String get_maskVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti == null) {return null;}
		return sti.get_actual_value_formated();
	}


	
	
	public String get_dbVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		return sti.get_loaded_value_formated();
	}
	
	public String get_dbVal(IF_Field field)  throws myException {
		return this.get_dbVal(field._t().rb_km(),field);
	}
	
	
	public MyBigDecimal get_MyBigDecimal_dbVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		
		return sti.get_MyBigDecimal_dbVal();
	}

	public MyBigDecimal get_MyBigDecimal_dbVal(IF_Field field)  throws myException {
		return this.get_MyBigDecimal_dbVal(field._t().rb_km(),field);
	}
	
	
	public MyBigDecimal get_MyBigDecimal_maskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return null;}
		
		return sti.get_MyBigDecimal_maskVal();
	}
	
	
	
	//die gleichen getters mit IF_Fields
	public MyLong get_MyLong_dbVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		
		return sti.get_MyLong_dbVal();
		
	}
	
	public MyLong get_MyLong_maskVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		
		return sti.get_MyLong_maskVal();
	}
	
	
	public MyDate get_MyDate_dbVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		
		return sti.get_MyDate_dbVal();
	}
	
	public MyDate get_MyDate_maskVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		
		return sti.get_MyDate_maskVal();
		
	}

	public String get_maskVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		return sti.get_actual_value_formated();
	}

	public String get_dbVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		return sti.get_loaded_value_formated();
	}
	
	
	public MyBigDecimal get_MyBigDecimal_dbVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		
		return sti.get_MyBigDecimal_dbVal();
	}
	
	public MyBigDecimal get_MyBigDecimal_maskVal(RB_KM mask, IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {return null;}
		
		return sti.get_MyBigDecimal_maskVal();
	}

	
	public void set_maskVal(RB_FieldCoordinate adress, String c_val, MyE2_MessageVector mv) {
		RB_MaskControllerField sti = this.get_MaskControllerField(adress.getMaskKey(), adress.getFieldKey());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(adress.getMaskKey().get_REALNAME())+"."+adress.getFieldKey().get_REALNAME()));
		} else {
			sti.set_maskVal(c_val, mv);
		}
	}

	
	
	public void set_maskVal(RB_KM mask, RB_KF field, String c_val, MyE2_MessageVector mv) {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.get_REALNAME()));
		} else {
			sti.set_maskVal(c_val, mv);
		}
	}
	
	
	
	public void set_maskVal(IF_Field field, String c_val, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(field._t().rb_km().get_REALNAME())+"."+field.fk().get_REALNAME()));
		} else {
			sti.set_maskVal(c_val, mv);
		}
	}
	

	
	public void set_maskVal(RB_KM mask, IF_Field field, String c_val, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.fk().get_REALNAME()));
		} else {
			sti.set_maskVal(c_val, mv);
		}
	}

	
	//20180105: neuen wert fuer viele maskenfelder eines maskkeys setzen
	public MyE2_MessageVector set_maskVal(RB_KM mask, String c_val, IF_Field... fields) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		for (IF_Field field: fields) { 
			RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
			if (sti == null) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.fk().get_REALNAME()));
			} else {
				sti.set_maskVal(c_val, mv);
			}
		}
		return mv;
	}
	
	
	//20180105: neuen wert fuer viele maskenfelder eines maskkeys setzen
	public void set_maskVal(RB_KM mask, String c_val, MyE2_MessageVector mv, IF_Field... fields) throws myException {
		for (IF_Field field: fields) { 
			RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
			if (sti == null) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.fk().get_REALNAME()));
			} else {
				sti.set_maskVal(c_val, mv);
			}
		}
	}
	
	
	public MyE2IF__Component  get_comp(RB_KM mask, IF_Field field, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.fk().get_REALNAME()));
		} else {
			return sti.get_component();
		}
		return null;
	}
	
	public MyE2IF__Component  get_comp(RB_KM mask, RB_KF field, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.get_REALNAME()));
		} else {
			return sti.get_component();
		}
		return null;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 31.10.2018
	 *
	 * @param mask
	 * @param field
	 * @return component or null when not found 
	 * @throws myException
	 */
	public MyE2IF__Component  get_comp(RB_KM mask, RB_KF field) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {
			return null;
		} else {
			return sti.get_component();
		}
	}

	
	public MyE2IF__Component  get_comp(RB_KM mask, IF_Field field) throws myException {
		return this.get_comp(mask, field.fk());
	}
	
	public MyE2IF__Component  get_comp(IF_Field field, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(field._t().rb_km().get_REALNAME())+"."+field.fk().get_REALNAME()));
		} else {
			return sti.get_component();
		}
		return null;
	}


	/**
	 * 
	 * @author martin
	 * @date 31.10.2018
	 *
	 * @param field
	 * @return s component or null when not found 
	 * @throws myException
	 */
	public MyE2IF__Component  get_comp(IF_Field field) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti == null) {
			return null;
		} else {
			return sti.get_component();
		}
	}

	
	

	public RB_ComponentMapCollector get_ComponentMapCollector() {
		return this.componentMapCollector;
	}

	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(RB_KM mask, RB_KF field, MyLong l_when_null, MyLong l_when_not_in_mask_found)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return l_when_not_in_mask_found;}
		
		Component comp = (Component)sti.get_component();
		String    l_string = null;
		//	jetzt die moeglichen typen durchsehen
		if (comp instanceof IF_RB_Component_Savable) {
			l_string = ((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject();
		} else if (comp instanceof IF_RB_Component) {
			l_string = ((IF_RB_Component)comp).get__actual_maskstring_in_view();
		} else if (comp instanceof MyE2IF__DB_Component) {
			l_string= ((MyE2IF__DB_Component)comp).get_cActualDBValueFormated();
		} else {
			return l_when_null;
		}
		
		MyLong l = new MyLong(l_string);
		if (l.isOK()) {
			return l;
		}
		return l_when_null;
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(RB_KM mask, RB_KF field, MyBigDecimal bd_when_null, MyBigDecimal bd_when_not_in_mask_found)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return bd_when_not_in_mask_found;}
		
		Component comp = (Component)sti.get_component();
		String    bd_string = null;
		//	jetzt die moeglichen typen durchsehen
		if (comp instanceof IF_RB_Component_Savable) {
			bd_string = ((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject();
		} else if (comp instanceof IF_RB_Component) {
			bd_string = ((IF_RB_Component)comp).get__actual_maskstring_in_view();
		} else if (comp instanceof MyE2IF__DB_Component) {
			bd_string= ((MyE2IF__DB_Component)comp).get_cActualDBValueFormated();
		} else {
			return bd_when_null;
		}
		
		MyBigDecimal l = new MyBigDecimal(bd_string);
		if (l.isOK()) {
			return l;
		}
		return bd_when_null;
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_String_liveVal(RB_KM mask, RB_KF field, String s_when_null, String s_when_not_in_mask_found)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return s_when_not_in_mask_found;}
		
		Component comp = (Component)sti.get_component();
		//	jetzt die moeglichen typen durchsehen
		if (comp instanceof IF_RB_Component_Savable) {
			return ((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject();
		} else if (comp instanceof IF_RB_Component) {
			return ((IF_RB_Component)comp).get__actual_maskstring_in_view();
		} else if (comp instanceof MyE2IF__DB_Component) {
			return ((MyE2IF__DB_Component)comp).get_cActualDBValueFormated();
		} else {
			return s_when_null;
		}
	}

	
	
	/**
	 * 2017-10-04: neue methode: holt aktuelle werte aus der maske (Datum)
	 */
	public MyDate get_MyDate_liveVal(RB_KM mask, RB_KF field, MyDate s_when_null, MyDate s_when_not_in_mask_found)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return s_when_not_in_mask_found;}
		
		Component comp = (Component)sti.get_component();
		String    dateString = null;

		
		//	jetzt die moeglichen typen durchsehen
		if (comp instanceof IF_RB_Component_Savable) {
			dateString= ((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject();
		} else if (comp instanceof IF_RB_Component) {
			dateString= ((IF_RB_Component)comp).get__actual_maskstring_in_view();
		} else if (comp instanceof MyE2IF__DB_Component) {
			dateString= ((MyE2IF__DB_Component)comp).get_cActualDBValueFormated();
		} else {
			return s_when_null;
		}
		
		MyDate l = new MyDate(dateString);
		if (l.isOK()) {
			return l;
		}
		
		return s_when_null;
		
	}

	

	
	
	
	/**
	 * 2017-10-04: neue methode: holt aktuelle werte aus der maske (Datum)
	 */
	public MyDate get_MyDate_liveVal(RB_KM mask, RB_KF field)  throws myException {
		return this.get_MyDate_liveVal(mask, field, null,null);
	}
	
	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(RB_KM mask, RB_KF field)  throws myException {
		return this.get_MyLong_liveVal(mask, field,null,null);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(RB_KM mask, RB_KF field)  throws myException {
		return this.get_MyBigDecimal_liveVal(mask, field,null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(RB_KM mask, RB_KF field)  throws myException {
		return this.get_String_liveVal(mask, field,null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(RB_KM mask, IF_Field field)  throws myException {
		return this.get_MyLong_liveVal(mask, field.fk(),null,null);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(RB_KM mask, IF_Field field)  throws myException {
		return this.get_MyBigDecimal_liveVal(mask, field.fk(),null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(RB_KM mask, IF_Field field)  throws myException {
		return this.get_String_liveVal(mask, field.fk(),null,null);
	}

	

	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(RB_KM mask, IF_Field field, MyLong l_when_null, MyLong l_when_not_in_mask_found)  throws myException {
		return this.get_MyLong_liveVal(mask, field.fk(),l_when_null,l_when_not_in_mask_found);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(RB_KM mask, IF_Field field, MyBigDecimal bd_when_null, MyBigDecimal bd_when_not_in_mask_found)  throws myException {
		return this.get_MyBigDecimal_liveVal(mask, field.fk(),bd_when_null,bd_when_not_in_mask_found);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(RB_KM mask, IF_Field field, String s_when_null, String s_when_not_in_mask_found)  throws myException {
		return this.get_String_liveVal(mask, field.fk(),s_when_null,s_when_not_in_mask_found);
	}

	///////////////////////////////////////////////////////////////////
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(_TAB mask, RB_KF field)  throws myException {
		return this.get_MyLong_liveVal(mask.rb_km(), field,null,null);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(_TAB mask, RB_KF field)  throws myException {
		return this.get_MyBigDecimal_liveVal(mask.rb_km(), field,null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(_TAB mask, RB_KF field)  throws myException {
		return this.get_String_liveVal(mask.rb_km(), field,null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(_TAB mask, IF_Field field)  throws myException {
		return this.get_MyLong_liveVal(mask.rb_km(), field.fk(),null,null);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(_TAB mask, IF_Field field)  throws myException {
		return this.get_MyBigDecimal_liveVal(mask.rb_km(), field.fk(),null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(_TAB mask, IF_Field field)  throws myException {
		return this.get_String_liveVal(mask.rb_km(), field.fk(),null,null);
	}

	

	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(_TAB mask, IF_Field field, MyLong l_when_null, MyLong l_when_not_in_mask_found)  throws myException {
		return this.get_MyLong_liveVal(mask.rb_km(), field.fk(),l_when_null,l_when_not_in_mask_found);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(_TAB mask, IF_Field field, MyBigDecimal bd_when_null, MyBigDecimal bd_when_not_in_mask_found)  throws myException {
		return this.get_MyBigDecimal_liveVal(mask.rb_km(), field.fk(),bd_when_null,bd_when_not_in_mask_found);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(_TAB mask, IF_Field field, String s_when_null, String s_when_not_in_mask_found)  throws myException {
		return this.get_String_liveVal(mask.rb_km(), field.fk(),s_when_null,s_when_not_in_mask_found);
	}

	
	
	///--------------------------- 20171025 neue methoden
	///////////////////////////////////////////////////////////////////
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyLong get_MyLong_liveVal(IF_Field field)  throws myException {
		return this.get_MyLong_liveVal(field._t(), field,null,null);
	}

	
	
	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public MyBigDecimal get_MyBigDecimal_liveVal(IF_Field field)  throws myException {
		return this.get_MyBigDecimal_liveVal(field._t(), field,null,null);
	}


	/**
	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
	 */
	public String get_liveVal(IF_Field field)  throws myException {
		return this.get_liveVal(field._t(), field,null,null);
	}

	/**
	 * 2017-10-04: neue methode: holt aktuelle werte aus der maske (Datum)
	 */
	public MyDate get_MyDate_liveVal(IF_Field field)  throws myException {
		return this.get_MyDate_liveVal(field._t().rb_km(), field.fk(), null,null);
	}

	/**
	 * 2017-10-04: neue methode: holt aktuelle werte aus der maske (Datum)
	 */
	public Boolean isYes_LiveVal(IF_Field field)  throws myException {
		return this.isYes_liveVal(field._t().rb_km(), field.fk(), null,null);
	}
	
	
	/**
	 * 2017-10-04: neue methode: holt aktuelle werte aus der maske (Datum)
	 */
	public Boolean isNo_liveVal(IF_Field field)  throws myException {
		return this.isNo_liveVal(field._t().rb_km(), field.fk(), null,null);
	}
	

	/**
	 * 
	 * @param mask
	 * @param field
	 * @param b_when_null_or_falseField
	 * @param b_when_not_in_mask_found
	 * @return
	 * @throws myException
	 */
	public Boolean isYes_liveVal(RB_KM mask, RB_KF field, Boolean b_when_null_or_falseField, Boolean b_when_not_in_mask_found)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return b_when_not_in_mask_found;}
		
		Component comp = (Component)sti.get_component();
		String    yesNo = null;

		
		//	jetzt die moeglichen typen durchsehen
		if (comp instanceof IF_RB_Component_Savable) {
			yesNo= ((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject();
		} else if (comp instanceof IF_RB_Component) {
			yesNo= ((IF_RB_Component)comp).get__actual_maskstring_in_view();
		} else if (comp instanceof MyE2IF__DB_Component) {
			yesNo= ((MyE2IF__DB_Component)comp).get_cActualDBValueFormated();
		} else {
			return b_when_null_or_falseField;
		}
		
		if (yesNo.toUpperCase().equals("Y")) {
			return true;
		} else if (yesNo==null || yesNo.equals("") || yesNo.toUpperCase().equals("N")) {
			return false;
		}
			
		
		return b_when_null_or_falseField;
		
	}


	/**
	 * 
	 * @param mask
	 * @param field
	 * @param b_when_null_or_falseField
	 * @param b_when_not_in_mask_found
	 * @return
	 * @throws myException
	 */
	public Boolean isNo_liveVal(RB_KM mask, RB_KF field, Boolean b_when_null_or_falseField, Boolean b_when_not_in_mask_found)  throws myException {

		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {return b_when_not_in_mask_found;}
		
		Component comp = (Component)sti.get_component();
		String    yesNo = null;

		
		//	jetzt die moeglichen typen durchsehen
		if (comp instanceof IF_RB_Component_Savable) {
			yesNo= ((IF_RB_Component_Savable)comp).rb_readValue_4_dataobject();
		} else if (comp instanceof IF_RB_Component) {
			yesNo= ((IF_RB_Component)comp).get__actual_maskstring_in_view();
		} else if (comp instanceof MyE2IF__DB_Component) {
			yesNo= ((MyE2IF__DB_Component)comp).get_cActualDBValueFormated();
		} else {
			return b_when_null_or_falseField;
		}
		
		if (yesNo.toUpperCase().equals("Y")) {
			return false;
		} else if (yesNo==null || yesNo.equals("") || yesNo.toUpperCase().equals("N")) {
			return true;
		}
		return b_when_null_or_falseField;
	}

	
	
	
	
	
	
	
	
	
	//2017-11-09: neue getter, die immer eine My--- object liefern, im fehlerfall ein fehlerhaftes, falls nicht gefunden, eine exception
	public MyBigDecimal getMyBigDecimalMaskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return sti.getMyBigDecimalMaskVal();
		} else {
			throw new myException(this,"getMyBigDecimalMaskVal:Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyLong getMyLongMaskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return sti.getMyLongMaskVal();
		} else {
			throw new myException(this,"getMyLongMaskVal:Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyDate getMyDateMaskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return sti.getMyDateMaskVal();
		} else {
			throw new myException(this,"getMyDateMaskVal:Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public String getStringMaskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return S.NN(sti.get_actual_value_formated());
		} else {
			throw new myException(this,"getStringMaskVal :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}

	public boolean getYNMaskVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return S.NN(sti.get_actual_value_formated()).equals("Y");
		} else {
			throw new myException(this,"getYNMaskVal :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	//2017-11-09: neue getter, die immer eine My--- object liefern, im fehlerfall ein fehlerhaftes
	public MyBigDecimal getMyBigDecimalMaskVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return sti.getMyBigDecimalMaskVal();
		} else {
			throw new myException(this,"getMyBigDecimalMaskVal:Component :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyLong getMyLongMaskVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return sti.getMyLongMaskVal();
		} else {
			throw new myException(this,"getMyLongMaskVal:Component :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyDate getMyDateMaskVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return sti.getMyDateMaskVal();
		} else {
			throw new myException(this,"getMyDateMaskVal:Component :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}

	public String getStringMaskVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return S.NN(sti.get_actual_value_formated());
		} else {
			throw new myException(this,"getStringMaskVal :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}

	public boolean getYNMaskVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return S.NN(sti.get_actual_value_formated()).equals("Y");
		} else {
			throw new myException(this,"getYNMaskVal :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	
   //gleiches mit dbVals
	public MyBigDecimal getMyBigDecimalDBVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return sti.getMyBigDecimalDbVal();
		} else {
			throw new myException(this,"getMyBigDecimalDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyLong getMyLongDBVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return sti.getMyLongDbVal();
		} else {
			throw new myException(this,"getMyLongDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyDate getMyDateDBVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return sti.getMyDateDbVal();
		} else {
			throw new myException(this,"getMyDateDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public String getStringDbVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return S.NN(sti.get_loaded_value_formated());
		} else {
			throw new myException(this,"getStringDbVal :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}

	public boolean getYNDbVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			return S.NN(sti.get_loaded_value_formated()).equals("Y");
		} else {
			throw new myException(this,"getYNDBVal :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyBigDecimal getMyBigDecimalDbVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return sti.getMyBigDecimalDbVal();
		} else {
			throw new myException(this,"getMyBigDecimalDBVal: Component :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyLong getMyLongDbVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return sti.getMyLongDbVal();
		} else {
			throw new myException(this,"getMyLongDBVal: Component :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public MyDate getMyDateDbVal(IF_Field field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti != null) {
			return sti.getMyDateDbVal();
		} else {
			throw new myException(this,"getMyDateDBVal: Component :"+field._t().rb_km().get_HASHKEY()+"/"+field.fk().get_HASHKEY()+" was not found in mask !!");
		}
	}

	public String getStringDbVal(IF_Field field)  throws myException {
		return this.getStringDbVal(field._t().rb_km(), field.fk());
	}

	
	
	public boolean getYNDbVal(IF_Field field)  throws myException {
		return this.getYNDbVal(field._t().rb_km(), field.fk());
	}

	
	
	/**
	 * 
	 * @param fields
	 * @return anzahl nicht leerer maskwerte in maske
	 * @throws myException
	 */
	public int getCountNotEmptyInActualMask(IF_Field...fields) throws myException {
		int i=0;
		for (IF_Field f: fields) {
			if (S.isFull(this.get_maskVal(f))) {
				i++;
			}
		}
		return i;
	}
	
	
	
	/*
	 * 20171227: neue setter mit der massgabe, dass nur leere felder gefuellt werden
	 */
	public void set_maskValIfEmpty(RB_FieldCoordinate adress, String c_val, MyE2_MessageVector mv) {
		RB_MaskControllerField sti = this.get_MaskControllerField(adress.getMaskKey(), adress.getFieldKey());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(adress.getMaskKey().get_REALNAME())+"."+adress.getFieldKey().get_REALNAME()));
		} else {
			sti.set_maskValIfEmpty(c_val, mv);
		}
	}

	
	
	public void set_maskValIfEmpty(RB_KM mask, RB_KF field, String c_val, MyE2_MessageVector mv) {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.get_REALNAME()));
		} else {
			sti.set_maskValIfEmpty(c_val, mv);
		}
	}
	
	
	
	public void set_maskValIfEmpty(IF_Field field, String c_val, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(field._t().rb_km(), field.fk());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(field._t().rb_km().get_REALNAME())+"."+field.fk().get_REALNAME()));
		} else {
			sti.set_maskValIfEmpty(c_val, mv);
		}
	}
	

	
	public void set_maskValIfEmpty(RB_KM mask, IF_Field field, String c_val, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field.fk());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(mask.get_REALNAME())+"."+field.fk().get_REALNAME()));
		} else {
			sti.set_maskValIfEmpty(c_val, mv);
		}
	}

	
	
	public Component  getComp(RB_FieldCoordinate coo, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(coo.getMaskKey(), coo.getFieldKey());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(coo.getInfo4User())));
		} else {
			return (Component)sti.get_component();
		}
		return null;
	}

	
	public Component  getComp(RB_KM maskKey,RB_KF fieldKey) {
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti == null) {
			return null;
		} else {
			return (Component)sti.get_component();
		}
	}

	public Component  getComp(RB_KM maskKey,IF_Field field) {
		try {
			RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, field.fk());
			if (sti == null) {
				return null;
			} else {
				return (Component)sti.get_component();
			}
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public IF_RB_Component  getRbComp(RB_FieldCoordinate coo, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(coo.getMaskKey(), coo.getFieldKey());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(coo.getInfo4User())));
		} else {
			MyE2IF__Component c= sti.get_component();
			if (c!=null && c instanceof IF_RB_Component) {
				return (IF_RB_Component)c;
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld hat falschen Typ:").ut(coo.getInfo4User())));
			}
		}
		return null;
	}

	public IF_RB_Component  getRbComp(RB_KM maskKey, RB_KF fieldKey, MyE2_MessageVector mv) throws myException {
		RB_FieldCoordinate coo = new RB_FieldCoordinate(maskKey,fieldKey);
		return this.getRbComp(coo, mv);
	}

	public IF_RB_Component  getRbComp(RB_KM maskKey, IF_Field field, MyE2_MessageVector mv) throws myException {
		RB_FieldCoordinate coo = new RB_FieldCoordinate(maskKey,field);
		return this.getRbComp(coo, mv);
	}

	
	public IF_RB_Component_Savable  IF_RB_Component_Savable(RB_FieldCoordinate coo, MyE2_MessageVector mv) throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(coo.getMaskKey(), coo.getFieldKey());
		if (sti == null) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld nicht gefunden:").ut(coo.getInfo4User())));
		} else {
			MyE2IF__Component c= sti.get_component();
			if (c!=null && c instanceof IF_RB_Component_Savable) {
				return (IF_RB_Component_Savable)c;
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feld hat falschen Typ:").ut(coo.getInfo4User())));
			}
		}
		return null;
	}

	public IF_RB_Component_Savable  getRbCompSavable(RB_KM maskKey, RB_KF fieldKey, MyE2_MessageVector mv) throws myException {
		RB_FieldCoordinate coo = new RB_FieldCoordinate(maskKey,fieldKey);
		return this.IF_RB_Component_Savable(coo, mv);
	}

	public IF_RB_Component_Savable  getRbCompSavable(RB_KM maskKey, IF_Field field, MyE2_MessageVector mv) throws myException {
		RB_FieldCoordinate coo = new RB_FieldCoordinate(maskKey,field);
		return this.IF_RB_Component_Savable(coo, mv);
	}


	
	/**
	 * 	/**
	 * 2018-02-18: transfer maskval  
	 * @param maskSource
	 * @param fieldSource
	 * @param maskTarget
	 * @param fieldTarget
	 * @param forceOverride (ueberschreibt, auch wenn ziel voll ist)
	 * @param mv
	 */
	public void transferMaskVal(RB_KM maskSource, RB_KF fieldSource,RB_KM maskTarget, RB_KF fieldTarget,boolean forceOverride,  MyE2_MessageVector mv) {
		RB_MaskControllerField fSource = this.get_MaskControllerField(maskSource, fieldSource);
		RB_MaskControllerField fTarget = this.get_MaskControllerField(maskTarget, fieldTarget);
		if (fSource == null || fTarget == null ) {
			if (fSource == null ) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Quell-Feld nicht gefunden:").ut(maskSource.get_REALNAME())+"."+fieldSource.get_REALNAME()));
			}
			if (fTarget == null ) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ziel-Feld nicht gefunden:").ut(maskSource.get_REALNAME())+"."+fieldSource.get_REALNAME()));
			}
		} else {
			String cSource = fSource.get_actual_value_formated();
			String cTarget = fTarget.get_actual_value_formated();
			
			if (S.isEmpty(cTarget) || forceOverride) {
				fTarget.set_maskVal(cSource, mv);
			}
		}
	}
	
	/**
	 * 	/**
	 * 2018-02-18: transfer maskval  
	 * @param maskSource
	 * @param fieldSource
	 * @param maskTarget
	 * @param fieldTarget
	 * @param forceOverride (ueberschreibt, auch wenn ziel voll ist)
	 * @param mv
	 */
	public void transferMaskVal(RB_KM maskSource, IF_Field fieldSource,RB_KM maskTarget, IF_Field fieldTarget,boolean forceOverride,  MyE2_MessageVector mv) throws myException {
		this.transferMaskVal(maskSource, fieldSource.fk(), maskTarget, fieldTarget.fk(), forceOverride, mv);
	}

	
	
//	/**
//	 * 2017-01-17: neue methode: holt aktuelle werte aus der maske
//	 */
//	public MyLong get_MyLong_liveVal(_TAB mask, IF_Field field, MyLong l_when_null, MyLong l_when_not_in_mask_found)  throws myException {
//		return this.get_MyLong_liveVal(mask.rb_km(), field.fk(),l_when_null,l_when_not_in_mask_found);
//	}
//

	
	
	/**
	 * 2018-05-09: neue getter-methoden, geben basis-typen zurück
	 */
	
	
	/**
	 * 2018-05-09: neue getter-methoden, geben basis-typen zurück
	 * @param mask
	 * @param field
	 * @return returns Long or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public Long getLongDBVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			try {
				MyLong val = sti.getMyLongDbVal();
				if (val.isNotOK()) {
					return null;
				} else {
					return val.get_oLong();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			throw new myException(this,"getLongDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public Long getLongDBVal(IF_Field field)  throws myException {
		return this.getLongDBVal(field._t().rb_km(), field.fk());
	}
	
	public Long getLongDBVal(RB_KM mask, IF_Field field)  throws myException {
		return this.getLongDBVal(mask, field.fk());
	}
	
	
	
	
	
	/**
	 * 2018-05-09: neue getter-methoden, geben basis-typen zurück
	 * @param mask
	 * @param field
	 * @return returns Long or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public Long getLongLiveVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			try {
				MyLong val = sti.getMyLongMaskVal();
				if (val.isNotOK()) {
					return null;
				} else {
					return val.get_oLong();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			throw new myException(this,"getLongDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public Long getLongLiveVal(IF_Field field)  throws myException {
		return this.getLongLiveVal(field._t().rb_km(), field.fk());
	}
	

	
	public Long getLongLiveVal(RB_KM mask, IF_Field field)  throws myException {
		return this.getLongLiveVal(mask, field.fk());
	}
	
	
	/**
	 * 2018-05-09: neue getter-methoden, geben basis-typen zurück
	 * @param mask
	 * @param field
	 * @return returns Long or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public BigDecimal getBigDecimalDBVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			try {
				MyBigDecimal val = sti.getMyBigDecimalDbVal();
				if (val.isNotOK()) {
					return null;
				} else {
					return val.get_bdWert();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			throw new myException(this,"getLongDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	/**
	 * 2018-05-09: neue getter-methoden, geben basis-typen zurück
	 * @param field
	 * @return returns Long or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public BigDecimal getDecimalDBVal(IF_Field field)  throws myException {
		return this.getBigDecimalDBVal(field._t().rb_km(), field.fk());
	}
	
	
	
	/**
	 * *2018-05-09: neue getter-methoden, geben basis-typen zurück
	 * 
	 * @param mask
	 * @param field
	 * @return returns BigDecimal or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public BigDecimal getBigDecimalLiveVal(RB_KM mask, RB_KF field)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(mask, field);
		if (sti != null) {
			try {
				MyBigDecimal val = sti.getMyBigDecimalMaskVal();
				if (val.isNotOK()) {
					return null;
				} else {
					return val.get_bdWert();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			throw new myException(this,"getBigDecimalDBVal: Component :"+mask.get_HASHKEY()+"/"+field.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	/**
	 * *2018-05-09: neue getter-methoden, geben basis-typen zurück
	 * 
	 * @param field
	 * @return returns BigDecimal or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public BigDecimal getBigDecimalLiveVal(IF_Field field)  throws myException {
		return this.getBigDecimalLiveVal(field._t().rb_km(), field.fk());
	}

	
	
	/**
	 * 2019-01-02: neue getter-methoden, geben basis-typen zurück
	 * @param maskKey
	 * @param fieldKey
	 * @return returns Date or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public Date getDateDBVal(RB_KM maskKey, RB_KF fieldKey)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			try {
				MyDate val = sti.get_MyDate_dbVal();
				if (val.isNotOK()) {
					return null;
				} else {
					return val.get_Calendar().getTime();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new myException(this,"getDateDBVal: Component :"+maskKey.get_HASHKEY()+"/"+fieldKey.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public Date getDateDBVal(IF_Field field)  throws myException {
		return this.getDateDBVal(field._t().rb_km(), field.fk());
	}

	
	/**
	 * 2019-01-02: neue getter-methoden, geben basis-typen zurück
	 * @param maskKey
	 * @param fieldKey
	 * @return returns Date or null (when empty, or not correct type), throws myException when not found
	 * @throws myException
	 */
	public Date getDateLiveVal(RB_KM maskKey, RB_KF fieldKey)  throws myException {
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			try {
				MyDate val = sti.get_MyDate_maskVal();
				if (val==null || val.isNotOK()) {
					return null;
				} else {
					return val.get_Calendar().getTime();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new myException(this,"getDateLiveVal: Component :"+maskKey.get_HASHKEY()+"/"+fieldKey.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	public Date getDateLiveVal(IF_Field field)  throws myException {
		return this.getDateLiveVal(field._t().rb_km(), field.fk());
	}



	/**
	 * neue setter methode. Throws exception, when field not found, sets field empty when not fitting 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param maskKey
	 * @param fieldKey
	 * @param value
	 * @param onlyIfEmpty
	 */
	public void setMaskVal(RB_KM maskKey, RB_KF fieldKey, String value, boolean onlyIfEmpty)   throws myException{
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			if (onlyIfEmpty && S.isFull(sti.get_actual_value_formated())) {
				return;
			}
			try {
				sti.get_rec2_field().get_field().generate_MetaFieldDef().getRaw(value);   //fall der wert nicht passt, exception
				sti.set_maskVal(value, bibMSG.getNewMV());
			} catch (myException e) {
				//fehler bei der konvertierung
				sti.set_maskVal("", bibMSG.getNewMV());
			}
			//sti._set_actual_value_formated(p_actual_mask_value_formated)
		} else {
			throw new myException(this,"setMaskVal: Component :"+maskKey.get_HASHKEY()+"/"+fieldKey.get_HASHKEY()+" was not found in mask !!");
		}
	}
	
	/**
	 * neue setter methode. Throws exception, when field not found, sets field empty when not fitting 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param field
	 * @param value
	 */
	public void setMaskVal(IF_Field field, String value, boolean onlyIfEmpty)   throws myException{
		this.setMaskVal(field._t().rb_km(), field.fk(), value, onlyIfEmpty);
	}

	
	
	
	
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @param maskKey
	 * @return  RB_ComponentMap
	 * @throws myException
	 */
	public RB_ComponentMap getRbComponentMap(RB_KM maskKey) throws myException {
		if (maskKey==null) {
			throw new myException("Error <3c400bac-41a9-11e9-b210-d663bd873d93>: Empty maskkey is not allowed!");
		}
		if (this.componentMapCollector == null) {
			throw new myException("Error<3c400bac-41a9-11e9-b210-d663bd873d93>: Not ComponentMapCollector present !");
		}
		
		if (this.componentMapCollector != null && this.componentMapCollector.get(maskKey)!=null) {
			return this.componentMapCollector.get(maskKey);
		} else {
			throw new myException("Error<3c400bac-41a9-11e9-b210-d663bd873d93>: ComponentMap with key "+maskKey.getKeyAndName()+" is not in this Collection !");
		}
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @param maskKey
	 * @return maskStatus of RB_ComponentMap
	 * @throws myException
	 */
	public MASK_STATUS getRbComponentMapStatus(RB_KM maskKey)  throws myException{
		return this.getRbComponentMap(maskKey).getStatus();
	}
	
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 11.03.2019
	 *
	 * @param mask
	 * @param field
	 * @param enabled
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _setEnabledForEdit(RB_KM mask, IF_Field field, boolean enabled) throws myException {
		
		this.get_comp(mask,field.fk()).set_bEnabled_For_Edit(enabled);
			
		return this;
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 26.02.2020
	 *
	 * @param mask
	 * @param field
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _setEnabled(RB_KM mask, IF_Field field) throws myException {
		this.get_comp(mask,field.fk()).set_bEnabled_For_Edit(true);
		return this;
	}

	/**
	 * 
	 * @author martin
	 * @date 26.02.2020
	 *
	 * @param mask
	 * @param field
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _setDisabled(RB_KM mask, IF_Field field) throws myException {
		this.get_comp(mask,field.fk()).set_bEnabled_For_Edit(false);
		return this;
	}

	/**
	 * 
	 * @author martin
	 * @date 26.02.2020
	 *
	 * @param mask
	 * @param fieldKey
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _setEnabled(RB_KM mask, RB_KF fieldKey) throws myException {
		this.get_comp(mask,fieldKey).set_bEnabled_For_Edit(true);
		return this;
	}

	/**
	 * 
	 * @author martin
	 * @date 26.02.2020
	 *
	 * @param mask
	 * @param fieldKey
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _setDisabled(RB_KM mask, RB_KF fieldKey) throws myException {
		this.get_comp(mask,fieldKey).set_bEnabled_For_Edit(false);
		return this;
	}

	
	/**
	 * macht ein feld leer und gibt, falls es einen aktuellen wert enthalten hat, eine info message aus (wenn message und mv beide notnull)
	 * @author martin
	 * @date 03.04.2019
	 *
	 * @param mask
	 * @param field
	 * @param message
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _setClearWhenNotEmpty(RB_KM mask, RB_KF field, MyE2_String message, MyE2_MessageVector mv) throws myException {
		String liveVal = S.NN(this.get_liveVal(mask, field));

		if (S.isFull(liveVal) && message!=null && mv!=null) {
			mv._addInfo(message);
		}
		this.setMaskVal(mask,field, "", false);
		
		
		return this;
	}
	
	
	public RB_MaskControllerMap _setClearWhenNotEmpty(RB_KM mask, IF_Field field, MyE2_String message, MyE2_MessageVector mv) throws myException {
		return this._setClearWhenNotEmpty(mask, field.fk(), message, mv);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param mv
	 * @param mask
	 * @param fields
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _clearFields( RB_KM mask, RB_KF ... fields) throws Exception {
		for (RB_KF fk: fields) {
			this.setMaskVal(mask,fk, "", false);

		}
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param mv
	 * @param mask
	 * @param fields
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _clearFields( RB_KM mask, IF_Field ... fields) throws Exception {
		for (IF_Field f: fields) {
			this.setMaskVal(mask,f.fk(), "", false);
		}
		return this;
	}
	

	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param mv
	 * @param mask
	 * @param fields
	 * @return
	 * @throws myException
	 */
	public RB_MaskControllerMap _clearAllFields( RB_KM mask, IF_Field ... excludeFromClearing) throws Exception {
		
		//zuerst den table identifizieren
		_TAB table = mask.get_db_table();
		if (table == null) {
			table = _TAB.find_Table(mask.get_REALNAME());
		}

		VEK<IF_Field> fieldsNotToClear = new VEK<>();
		if (excludeFromClearing!=null) {
			fieldsNotToClear._a(excludeFromClearing);
		}
		
		
		
		if (table!=null) {
			//dann alle felder scannen in diesem maskcontroller, ob die kombi maskKey und if_field vorhanden sind
			for (IF_Field field: table.get_fields()) {
				if (this.get_MaskControllerField(mask, field.fk())!=null) {
					if (!fieldsNotToClear.contains(field)) {
						this._clearFields(mask, field);
					}
				}
			}
		}
		
		return this;
	}
	
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 08.04.2019
	 *
	 * @param maskKey
	 * @param fieldKey
	 * @return value of type: Long, BigDecimal, String or date, depending on datatype or null when not correct or emtpy
	 * @throws myException when field not found
	 */
	public Object getRawLiveVal(RB_KM maskKey, RB_KF fieldKey)  throws myException {
		if (maskKey==null || fieldKey == null) {
			throw new myException("Error<7ad0e47b-1aa1-41ce-84f6-9ac9e1673432>: null is not allowed!");
		}
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			return sti.getRawLiveVal();
		} else {
			throw new myException("Error<7ad0e47b-1aa1-41ce-84f6-9ac9e1673432>: Component with coordinates "+maskKey.getKeyAndName()+"/"+fieldKey.getKeyAndName()+" is not in this Collection !");
		}
	}
	
	/**
	 * 
	 * @author martin
	 * @date 08.04.2019
	 *
	 * @param maskKey
	 * @param fieldKey
	 * @return value of type: Long, BigDecimal, String or date, depending on datatype or null when not correct or emtpy
	 * @throws myException when field not found
	 */
	public Object getRawLiveVal(RB_KM maskKey, IF_Field field)  throws myException {
		return this.getRawLiveVal(maskKey, field.fk());
	}
	
	
	

	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param maskKey
	 * @param fieldKey
	 * @return
	 * @throws Exception
	 */
	public Object getRawLiveValThrowsEx(RB_KM maskKey, RB_KF fieldKey)  throws Exception {
		if (maskKey==null || fieldKey == null) {
			throw new myException("Error<7ad0e47b-1aa1-41ce-84f6-9ac9e1673432>: null is not allowed!");
		}
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			return sti.getRawLiveValThrowsEx();
		} else {
			throw new myException("Error<7ad0e47b-1aa1-41ce-84f6-9ac9e1673432>: Component with coordinates "+maskKey.getKeyAndName()+"/"+fieldKey.getKeyAndName()+" is not in this Collection !");
		}
	}
	


	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param maskKey
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public Object getRawLiveValThrowsEx(RB_KM maskKey, IF_Field field)  throws Exception {
		return this.getRawLiveValThrowsEx(maskKey, field.fk());
	}
	
	
	


	/**
	 * neue methode, um aenderungen an maskeneintraegen (auch nach dem Laden) festzustellen 
	 * @author martin
	 * @date 02.03.2020
	 *
	 * @param maskKey
	 * @param fieldKey
	 * @return
	 * @throws myException
	 */
	public Object getValueJustInTime(RB_KM maskKey, RB_KF fieldKey)  throws myException {
		if (maskKey==null || fieldKey == null) {
			throw new myException("Error<e18313dc-5c6c-11ea-bc55-0242ac130003>: null is not allowed!");
		}
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			return sti.getRawValueJustInTime();
		} else {
			throw new myException("Error<e18313dc-5c6c-11ea-bc55-0242ac130003>: Component with coordinates "+maskKey.getKeyAndName()+"/"+fieldKey.getKeyAndName()+" is not in this Collection !");
		}
	}
	

	/**
	 * 
	 * @author martin
	 * @date 02.03.2020
	 *
	 * @param maskKey
	 * @param field
	 * @return
	 * @throws myException
	 */
	public Object getValueJustInTime(RB_KM maskKey, IF_Field field)  throws myException {
		return this.getValueJustInTime(maskKey, field.fk());
	}
	
	
	
	

	public Object getValueFromScreen(RB_KM maskKey, RB_KF fieldKey)  throws myException {
		return this.getValueJustInTime(maskKey, fieldKey);
	}
	

	public Object getValueFromScreen(RB_KM maskKey, IF_Field field)  throws myException {
		return this.getValueJustInTime(maskKey, field.fk());
	}
	

	
	public Boolean getBooleanValueFromScreen(RB_KM maskKey, RB_KF fieldKey)  throws myException {
		Object valRaw =  this.getValueJustInTime(maskKey, fieldKey);
		if (valRaw==null) {
			return false;
		} else {
			if (valRaw instanceof String && ((String)valRaw).length()==1) {
				return valRaw.equals("Y");
			} else {
				throw new myException("Cannot be a boolean value");
			}
		}
	}
	
	public Boolean getBooleanValueFromScreen(RB_KM maskKey, IF_Field field)  throws myException {
		return this.getBooleanValueFromScreen(maskKey, field.fk());
	}

	

	/**
	 * 
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param mask
	 * @param field
	 * @return
	 */
	public Optional<Component>  getCompOpt(RB_KM mask, IF_Field field) {
		return Optional.ofNullable(this.getComp(mask, field));
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param mask
	 * @param field
	 * @return
	 */
	public Optional<IF_RB_Component>  getCompIfRbComponentOpt(RB_KM mask, IF_Field field) {
		return Optional.ofNullable(this.getComponent(mask, field));
	}
	
	public Optional<IF_RB_Component>  getCompIfRbComponentOpt(RB_KM mask, RB_KF field) {
		return Optional.ofNullable(this.getComponent(mask, field));
	}
	
}
