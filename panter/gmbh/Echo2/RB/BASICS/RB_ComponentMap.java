package panter.gmbh.Echo2.RB.BASICS;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.IF_ExecuterReturnsMV;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_Container4Visualisation;
import panter.gmbh.Echo2.RB.IF.IF_Formatter;
import panter.gmbh.Echo2.RB.IF.IF_RB_Collector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Complex;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.IF.IF_RB_Part;
import panter.gmbh.Echo2.RB.VALID.IF_RB_SetAndValidBinder;
import panter.gmbh.Echo2.RB.VALID.IF_Simple_Mask_Set_And_Valid;
import panter.gmbh.Echo2.RB.VALID.RB_DynamicRules;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid_Container;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class RB_ComponentMap extends E2_ComponentMAP  	implements 	IF_RB_Part<RB_ComponentMapCollector>, 
																			IF_RB_Collector<MyE2IF__Component>,
																			Iterable<IF_RB_Component> {

	public abstract MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException;
	public abstract MyE2_MessageVector maskSettings_do_After_Load() throws myException;
	public abstract MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, RB__CONST.MASK_STATUS status) throws myException;

	public abstract void     user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) ;
	
	/*
	 * diese hashmap wird vor jedem zyklus aus standardwerten gebaut und kann mit einer abstrakten methode manipuliert werden
	 */
	private RB_PreSettingsContainer     				rb_pre_SettingsContainer = new RB_PreSettingsContainer();
	
	private String    									cTABLENAME = null;

	private RB_ComponentMapCollector 					rb_componentMapCollector_this_BELONGS_TO = null;

	private RB_Dataobject 		 						rb_dataobject_actual_for_mask = null;
	
	private RB_KM  										own_rb_mask_key = null;      //wird beim register-vorgang gesetzt
		
	private RB_Mask_Set_And_Valid_Container   			interactive_Settings_Validations = new RB_Mask_Set_And_Valid_Container(); 
	
	private RB_COMP_Container      						rb_inner_componentMap = new RB_COMP_Container();
	
	
	//weitere hashmap nur fuer RB_Components   **2018-05-16: aenderung von HashMap in linkedHashMap
	private LinkedHashMap<RB_KF, IF_RB_Component> 		rb_hm_only_rb_components = new LinkedHashMap<>();
	

	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  										my_key_in_collection = null;
	
	
	//2020-02-20: afterMaskFillListener
	private VEK<IF_ExecuterReturnsMV<RB_ComponentMap>>   				afterMaskPopulateListeners = new VEK<>();
			
	private VEK<IF_ExecuterReturnsMV<RB_ComponentMap>>   				beforeMaskToRecordListener = new VEK<>();
	
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}

	
	
	public RB_ComponentMap() {
		super();
	}

	
	/**
	 * @param tablename
	 * @throws myException
	 */
	public void rb_INIT_4_DB(String tablename) throws myException {
		this.set_rbTABLENAME(tablename);
	}

	/**
	 * @param tablename
	 * @throws myException
	 */
	public void rb_INIT_4_DB(_TAB table) throws myException {
		this.set_rbTABLENAME(table.fullTableName());
	}

	
	
	@Override
	public void rb_set_belongs_to(RB_ComponentMapCollector componentMapCollector_this_belongs_to) throws myException {
		this.rb_componentMapCollector_this_BELONGS_TO=componentMapCollector_this_belongs_to;
	}

	@Override
	public RB_ComponentMapCollector rb_get_belongs_to() throws myException {
		return this.rb_componentMapCollector_this_BELONGS_TO;
	}
	
	
	/**
	 * 
	 * @param hashKey
	 * @return s MyMetaFieldDEF if database-field else null
	 * @throws myException
	 */
	public MyMetaFieldDEF getMetaFieldDef(RB_KF hashKey) throws myException {
		
		
		if (this.rb_dataobject_actual_for_mask!=null) {
			if (this.rb_dataobject_actual_for_mask.get_RecNEW().get(hashKey.FIELDNAME()) != null)  {
				return this.rb_dataobject_actual_for_mask.get_RecNEW().get(hashKey.FIELDNAME());
			} else {
				return null;
			}
		} else {
			throw new myException(this, "get_MetaFieldDef: RB_MASK : no RB_MASK_DATA present!");
		}
		
		
	}

	
	

	
	/**
	 * @param KEY
	 * @param comp
	 * @throws myException
	 */
	@Override
	public MyE2IF__Component registerComponent(RB_K KEY, MyE2IF__Component comp) throws myException {
		
		if (!(KEY instanceof RB_KF)) {
			throw new myException(this,"Key MUST be a typof RB_KF");
		}
		
		RB_KF key = (RB_KF)KEY;
		if (this.containsKey(key.HASHKEY())) {
			throw new myException(this,"Hashkey "+key.HASHKEY()+" is already used !");
		}
		
		if (comp instanceof IF_RB_Component) {
			this.rb_hm_only_rb_components.put(key, (IF_RB_Component)comp);
		}
		
		//2016-07-19: einheitliche registrierung
		if (comp instanceof IF_RB_Component) {
			((IF_RB_Component)comp).setMyKeyInCollection(KEY);
		}

		comp.EXT().rb_set_belongs_to(this);
		
		this.put(key.HASHKEY(),comp);
		this.get_vComponentHashKeys().add(key.HASHKEY());

		comp.EXT().set_oComponentMAP(this);
		comp.EXT().set_cList_or_Mask_Titel(null);
		comp.EXT().set_C_HASHKEY(key.HASHKEY());
		comp.EXT().set_RB_K(key);
		
		this.get_hmRealComponents().put(key.HASHKEY(), comp);
		
		if (comp instanceof IF_RB_Component) {
			((IF_RB_Component)comp).set_rb_RB_K(key);
		}
	
		this.rb_inner_componentMap.put(key, comp);
		
		return comp;
	}
	
	
	/**
	 * @param KEY
	 * @param comp
	 * @throws myException
	 */
	public MyE2IF__Component registerComponent(IF_Field field, MyE2IF__Component comp) throws myException {
		return this.registerComponent(field.fk(), comp);
	}
	

	
	//20171220: neue register-methoden mit der moeglichkeit eines replaceings
	/**
	 * @param KEY
	 * @param comp
	 * @throws myException
	 */
	public MyE2IF__Component registerReplaceComponent(RB_K KEY, MyE2IF__Component comp) throws myException {
		if (!(KEY instanceof RB_KF)) {
			throw new myException(this,"Key MUST be a typof RB_KF");
		}
		RB_KF key = (RB_KF)KEY;
		
		if (this.containsKey(key.get_HASHKEY())) {
			this.remove(key.get_HASHKEY());
		}
		return this.registerComponent(KEY, comp);
	}

	
	/**
	 * @param KEY
	 * @param comp
	 * @throws myException
	 */
	public MyE2IF__Component registerReplaceComponent(IF_Field field, MyE2IF__Component comp) throws myException {
		return this.registerReplaceComponent(field.fk(), comp);
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 24.02.2020
	 *
	 * @param componentWithKey
	 * @return
	 * @throws myException
	 */
	public IF_RbComponentWithOwnKey registerComponent(IF_RbComponentWithOwnKey componentWithKey) throws myException {
		this.registerComponent(componentWithKey.getFieldKey(), componentWithKey);
		return componentWithKey;
	}
	
	
	
	/**
	 * 
	 * @param key
	 * @return found IF_RB_Component-class or null
	 */
	public IF_RB_Component  getRbComponent(RB_KF key) {
		return this.rb_inner_componentMap.get_(key);
	}

	/**
	 * 
	 * @param key
	 * @return found IF_RB_Component-class or null
	 * @throws myException 
	 */
	public IF_RB_Component  getRbComponent(IF_Field field) throws myException {
		return this.rb_inner_componentMap.get_(field.fk());
	}


	
	/**
	 * 
	 * @param key
	 * @return found IF_RB_Component_Savable-class or null
	 * @throws myException 
	 */
	public IF_RB_Component_Savable  getRbComponentSavable(IF_Field field) throws myException {
		IF_RB_Component comp = this.rb_inner_componentMap.get_(field.fk());
		if (comp instanceof IF_RB_Component_Savable) {
			return (IF_RB_Component_Savable)comp;
		}
		return null;
	}


	
	/**
	 * 
	 * @param key
	 * @return found Component-class or null
	 */
	public Component  getComp(RB_KF key) {
		return (Component)this.rb_inner_componentMap.get_(key);
	}

	
	/**
	 * 
	 * @param key
	 * @return found Component-class or null
	 * @throws myException 
	 */
	public Component  getComp(IF_Field field) throws myException {
		return this.getComp(field.fk());
	}


	public Component[]  getComps(IF_Field... field) throws myException {
		Component[] ret = new Component[field.length];
		for (int i=0;i<field.length;i++) {
			ret[i]=this.getComp(field[i]);
		}
		return ret;
	}

	
	
	
	
	/**
	 * 
	 * @param key
	 * @return found MyE2IF_Component-class or null
	 */
	public MyE2IF__Component  getIfComp(RB_KF key) {
		return (MyE2IF__Component)this.rb_inner_componentMap.get_(key);
	}

	/**
	 * 
	 * @param key
	 * @return found MyE2IF_Component-class or null
	 * @throws myException 
	 */
	public MyE2IF__Component  getIfComp(IF_Field field) throws myException {
		return (MyE2IF__Component)this.rb_inner_componentMap.get_(field.fk());
	}

	
	/**
	 * 
	 * @param key
	 * @return found MyE2IF_Component-class or null
	 */
	public IF_RB_Component  getIfRbComp(RB_KF key) {
		return this.rb_inner_componentMap.get_(key);
	}

	/**
	 * 
	 * @param key
	 * @return found MyE2IF_Component-class or null
	 * @throws myException 
	 */
	public IF_RB_Component  getIfRbComp(IF_Field field) throws myException {
		return this.rb_inner_componentMap.get_(field.fk());
	}


	
	
	/**
	 * 
	 * @param key
	 * @return found Component-class or null
	 */
	public Component  getComponentEchoBase(RB_KF key) {
		IF_RB_Component c = this.getRbComponent(key);
		if (c!=null && (c instanceof Component)) {
			return (Component)c;
		}
		return null;
	}

	
	
	/**
	 * extrahieren aller IF_RB_Component - Components
	 * @return
	 * @throws myException
	 */
	public HashMap<String, IF_RB_Component> getHmRbComponents() throws myException {
		
		HashMap<String, IF_RB_Component>  hmRB_Components = new HashMap<String, IF_RB_Component>();
		
		for (String cHASH: this.keySet()) {
			if (this.get(cHASH) instanceof IF_RB_Component) {
				hmRB_Components.put(cHASH, (IF_RB_Component) this.get(cHASH));
			}
		}
		
		return hmRB_Components;
	}
	
	
	
	/**
	 * extrahieren aller IF_RB_Component_Savable - Components
	 * @return
	 * @throws myException
	 */
	public HashMap<String, IF_RB_Component_Savable> getHmComponentsSavable() throws myException {
		
		HashMap<String, IF_RB_Component_Savable>  hmRB_Components = new HashMap<String, IF_RB_Component_Savable>();
		
		for (String cHASH: this.keySet()) {
			if (this.get(cHASH) instanceof IF_RB_Component_Savable) {
				hmRB_Components.put(cHASH, (IF_RB_Component_Savable) this.get(cHASH));
			}
		}
		
		return hmRB_Components;
	}
	
	
	
	/**
	 * extrahieren aller IF_RB_Component - Components, die NICHT saveable sind
	 * @return
	 * @throws myException
	 */
	public HashMap<String, IF_RB_Component> getHmComponentsNotSavable() throws myException {
		
		HashMap<String, IF_RB_Component>  hmRB_Components = new HashMap<String, IF_RB_Component>();
		
		for (String cHASH: this.keySet()) {
			if (this.get(cHASH) instanceof IF_RB_Component) {
				if (!(this.get(cHASH) instanceof IF_RB_Component_Savable)) {
					hmRB_Components.put(cHASH, (IF_RB_Component) this.get(cHASH));
				}
			}
		}
		
		return hmRB_Components;
	}
	
	
	

	
	/**
	 * extrahieren aller IF_RB_Component_Complex - Components
	 * @return
	 * @throws myException
	 */
	public HashMap<String, IF_RB_Component_Complex> getComplexComponents() throws myException {
		
		HashMap<String, IF_RB_Component_Complex>  hmRB_Components = new HashMap<String, IF_RB_Component_Complex>();
		
		for (String cHASH: this.keySet()) {
			if (this.get(cHASH) instanceof IF_RB_Component_Complex) {
				hmRB_Components.put(cHASH, (IF_RB_Component_Complex) this.get(cHASH));
			}
		}
		
		return hmRB_Components;
	}
	
	
	
	
	public String rb_TABLENAME() {
		return cTABLENAME;
	}
	
	public void set_rbTABLENAME(String tablename) throws myException {
		this.cTABLENAME = tablename.trim().toUpperCase();
	}

	

	public HashMap<String, MyE2IF__Component> getHmComponents() {
		return this;
	}


	
	public RB_PreSettingsContainer getPreSettingsContainer() {
		return this.rb_pre_SettingsContainer;
	}
	
	

	/**
	 * erstellen der aktuellen Component_METADEF aus den standardwerten der componenten und sql-metadefs 
	 * @param statusToSet
	 * @throws myException
	 */
	public void getNewGeneratedStandardMaskSettings(RB__CONST.MASK_STATUS statusToSet) throws myException {
		
		if (this.rb_dataobject_actual_for_mask==null || this.rb_dataobject_actual_for_mask.rb_MASK_STATUS()==null) {
			throw new myException(this,"FILL_MASK_COMPONENT_METADEF_WITH_STANDARD: Undefined RB_MASK-Status!");
		}
		
		//maskeneinstellungen
		RB_DynamicRules dyn_Rules = new RB_DynamicRules(this, this.generate_KennerString4Validators());
		
		
		this.rb_pre_SettingsContainer.rb_clearMap();
		
		
		//die regulaeren feldnamen sammeln
		Vector<String> vRegularFields = new Vector<String>();
		
		for (MyMetaFieldDEF meta: this.rb_dataobject_actual_for_mask.get_RecNEW().values()) {
			vRegularFields.add(meta.get_FieldName());
		}
		
		for (Map.Entry<String, MyE2IF__Component> entry: this.entrySet()) {
			RB_KF 			HASHKEY = entry.getValue().EXT().get_RB_K();
			MyE2IF__Component	comp 	= entry.getValue();
			
			
//			DEBUG.System_println("MAP:"+this.cTABLENAME+" >>@@>> "+"Field:" +HASHKEY.get_REALNAME());
			
			RB_SurfaceSettings surfaceSetting = new RB_SurfaceSettings();
			surfaceSetting.set_RB_K(HASHKEY);
			
//			//ist es ein datenbank-gebundenes objekt
//			MyMetaFieldDEF  metadef = null;
//			if (this.rb_dataobject_actual_for_mask.get_RecNEW().keySet().contains(HASHKEY.FIELDNAME())) {
//				metadef = this.rb_dataobject_actual_for_mask.get_RecNEW().get(HASHKEY.FIELDNAME());
//			} else {
//				
//				DEBUG.System_print(new Vector<String>(this.rb_dataobject_actual_for_mask.get_RecNEW().keySet()), true);
//				
//				throw new myException("Field:"+HASHKEY.FIELDNAME()+" is not present in RECORD "+this.rb_dataobject_actual_for_mask.get_RecNEW().get_TABLENAME());
//			}
//			
//			if (comp instanceof IF_RB_Component_Savable) {
//				//id muss nicht auf MUST gesetzt werden, da automatisch erzeugt
//				if (metadef.get_bMUST_Field_Basic() && !(HASHKEY.FIELDNAME().equals(this.rb_dataobject_actual_for_mask.get_RecNEW().get_PRIMARY_KEY_NAME()))) {
//					surfaceSetting.set_MustField(true);
//				}
//			}

			//20180105: martin: aenderung: alte konstruktion verhindert NICHT auf die datenbanktabellen gemappete komponenten in der RB_ComponentMAP
			//ist es ein datenbank-gebundenes objekt
			MyMetaFieldDEF  metadef = null;
			if (this.rb_dataobject_actual_for_mask.get_RecNEW().keySet().contains(HASHKEY.FIELDNAME())) {
				metadef = this.rb_dataobject_actual_for_mask.get_RecNEW().get(HASHKEY.FIELDNAME());

				if (comp instanceof IF_RB_Component_Savable) {
					//id muss nicht auf MUST gesetzt werden, da automatisch erzeugt
					if (metadef.get_bMUST_Field_Basic() && !(HASHKEY.FIELDNAME().equals(this.rb_dataobject_actual_for_mask.get_RecNEW().get_PRIMARY_KEY_NAME()))) {
						surfaceSetting.set_MustField(true);
					}
				}
			}
			//ende aenderung 
			
			boolean bEnabled = true;
			
			if (comp.EXT().get_bDisabledFromBasic() || statusToSet==RB__CONST.MASK_STATUS.VIEW) {
				bEnabled = false;
			}
			
			
			if (comp instanceof MyE2IF__DB_Component && !(comp instanceof IF_RB_Component_Complex)) {
				MyE2IF__DB_Component comp_1= (MyE2IF__DB_Component)comp;
				
				if (comp_1.EXT_DB().get_oSQLField()==null ||  (!vRegularFields.contains(comp_1.EXT_DB().get_oSQLField().get_cFieldName()))) {
					bEnabled = false;
				} else if (!comp_1.EXT_DB().get_bGivesBackValueToDB()) {
					//wenn der status this.oEXTDB.set_bGivesBackValueToDB(...) auf false steht, dann ist die komponenten auch disabled
					bEnabled = false;
				}
			} 
			
			if (comp instanceof IF_RB_Component_Savable) {
				//ein savable objekt auf den primary-key ist immer disabled
				if (HASHKEY.FIELDNAME().equals(this.rb_dataobject_actual_for_mask.get_RecNEW().get_PRIMARY_KEY_NAME())) {
					bEnabled = false;
				}
			}
			
			surfaceSetting.set_Enabled(bEnabled);
			
			
			MyMetaFieldDEF meta = this.getMetaFieldDef(HASHKEY);
			if (meta!=null) {
				if (meta.get_bIsNumberTypeWithDecimals() || meta.get_bIsNumberTypeWithOutDecimals()) {
					surfaceSetting.set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.RIGHT);
				} else if (meta.get_bIsDateType()) {
					surfaceSetting.set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.CENTER);
				} else {
					surfaceSetting.set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL.LEFT);
				}
			} 
			
			if (dyn_Rules.get(HASHKEY)!=null) {
				surfaceSetting.set_rb_DynamicRule(dyn_Rules.get(HASHKEY));
			}
			
			this.rb_pre_SettingsContainer.registerComponent(HASHKEY, surfaceSetting);
		}
		
		
		//jetzt ein set von RB_ForcedDBValues aus der tabelle erzeugen
		String table_name = this.rb_dataobject_actual_for_mask.rb_relevant_record_to_fill().get_TABLENAME();
		if (S.isFull(table_name)) {
			_TAB table = _TAB.find_Table(table_name);
			if (table != null) {
				IF_Field[] fields = table.get_fields();
				for (IF_Field field: fields) {
					this.rb_pre_SettingsContainer.rb_register(field, new RB_ForcedDBValue());   //jedes datenbankfeld bekommt einen leeren wert
				}
			}else {
				throw new myException(this,"Cannot identify table "+table_name);
			}
		} else {
			throw new myException(this,"NO TABLE is PRESENT");
		}
		
		
	}

	
	
	/**
	 * complete cycle to fill a mask from a record
	 * @param dataObject
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector setDataToMask(RB_Dataobject dataObject) throws myException {
		
		this.rb_dataobject_actual_for_mask = dataObject;
		
		if (this.rb_dataobject_actual_for_mask!=null) {
			
			
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			oMV.add_MESSAGE(this.setSurfaceSettingsNeutral());
			oMV.add_MESSAGE(this.setUserdefinedMaskSettingBeforeLoad());
				
			HashMap<String, IF_RB_Component> hmRealComponents = this.getHmRbComponents();
			for (Map.Entry<String, IF_RB_Component> entry: hmRealComponents.entrySet()) {
				entry.getValue().rb_Datacontent_to_Component(dataObject);
			}

			//falls der status NEW_COPY ist, wird zuerst ein record uebergeben, die maske gefuellt und danach das quellrecord auf null gestellt
			if (dataObject.rb_MASK_STATUS().isStatusNew())  {
				this.rb_dataobject_actual_for_mask.set_implicit_status_new();
			}
				
			oMV.add_MESSAGE(this.maskSettings_do_After_Load());
				
			this.getNewGeneratedStandardMaskSettings(this.rb_dataobject_actual_for_mask.rb_MASK_STATUS());
			oMV.add_MESSAGE(this.maskSettings_define_own_pre_settings(this.rb_pre_SettingsContainer,this.rb_dataobject_actual_for_mask.rb_MASK_STATUS()));
			this.setSurfaceSettingsActive();
			
			//jetzt die preset-values fuellen
			this.setSurfaceSettingPresetValuesToMask();
			
			//2020-02-20: die afterMaskPopulateListeners abarbeiten
			if (afterMaskPopulateListeners.size()>0) {
				for (IF_ExecuterReturnsMV<RB_ComponentMap> exe: afterMaskPopulateListeners) {
					oMV._add(exe.execute(this));
					if (oMV.hasAlarms()) {
						break;
					}
				}
			}
			
			
			return oMV;

		} else {
			throw new myException(this,"MaskContent_COMPLETE_FILL_CYCLE: no Rb_Mask_data_actual present!");
		}
	}
	
	
	
	

	/**
	 * 
	 * @return 's MyE2_MessageVector
	 * @throws myException
	 */
	public MyE2_MessageVector  rb_Mask_to_Dataobject_SIMULATE() throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (this.rb_dataobject_actual_for_mask!=null) {
		
			HashMap<String, IF_RB_Component_Savable> hm_RB_ComponentsSave = 	this.getHmComponentsSavable();
			HashMap<String, IF_RB_Component_Complex> hm_RB_ComponentsComplex = 	this.getComplexComponents();
	
			HashMap<String, MyMetaFieldDEF>		hmFieldMetaDefs = new HashMap<String, MyMetaFieldDEF>();
			hmFieldMetaDefs.putAll(this.rb_dataobject_actual_for_mask.get_RecNEW());
			
			//2016-12-08: pruefung der felddefinierten validatoren auch auf nicht-saveable RB_Components
			oMV.add_MESSAGE(this.check_field_validators_of_not_saving_Fields());
			//2016-12-08-ende
			
			
			for (Map.Entry<String, IF_RB_Component_Savable> entry : hm_RB_ComponentsSave.entrySet()) {
				IF_RB_Component_Savable  	comp = 		entry.getValue();
				RB_KF                  key = comp.EXT().get_RB_K();
				
				RB_SurfaceSettings  		surfaceSettings = this.rb_pre_SettingsContainer.rb_get(key);

				if (surfaceSettings != null) {
					
					Vector<String>  v_excludeFields = this.get_EXCLUDE_HashKeys();
					
					if (hmFieldMetaDefs.keySet().contains(key.FIELDNAME()) && !(v_excludeFields.contains(key.FIELDNAME()))) {    //sicherstellen, dass nur maskenkomponenten, die an die datenbanktabelle genkuepft sind, auch uebergeben werden
	
						String cNewValue = comp.rb_readValue_4_dataobject();
						
						MyE2_MessageVector  o_mv = this.rb_dataobject_actual_for_mask.get_RecNEW().check_NewValueForDatabase(	key.FIELDNAME(), 
																													cNewValue,
																													surfaceSettings.is_MustFieldComplete());
						
						//jetzt die eigenen validators pruefen
						for (RB_Validator_Component vali: entry.getValue().rb_VALIDATORS_4_INPUT()) {
							o_mv.add_MESSAGE(vali.do_Validate(entry.getValue()));
						}
						
						if (o_mv.get_bHasAlarms()) {
							entry.getValue().mark_FalseInput();
						}
						
						//jetzt die messages nach dem vorkommen von <HASH> durchsuchen und durch eine evtl. vorhandene description ersetzen
						if (surfaceSettings.get_Description()!=null && o_mv.size()>0) {
							String c_replacename = surfaceSettings.get_Description().get_DescriptionAndPosition().CTrans();
							
							for (MyE2_Message mess: o_mv) {
								String cMessage = mess.get_cMessage().CTrans();
								mess.set_Message(new MyE2_String(cMessage.replace("<"+key.FIELDNAME()+">", c_replacename),false));
							}
						}
						oMV.add_MESSAGE(o_mv);
						
						
						//hier noch die DynamicSetter-validierer durchpruefen
						if (surfaceSettings.get_rb_DynamicRule()==null) {
							throw new myException(this,"rb_Mask_to_Dataobject_SIMULATE: No DynamicRule for <"+key.HASHKEY()+">");
						}
						
						if (surfaceSettings.get_rb_DynamicRule().get_vCompVALIDATOR_4_INPUT().size()>0) {
							for (RB_Validator_Component valid: surfaceSettings.get_rb_DynamicRule().get_vCompVALIDATOR_4_INPUT()) {
								oMV.add_MESSAGE(valid.do_Validate(comp));
							}
						}
					}
				} else {
					throw new myException(this,"rb_Mask_to_Dataobject_SIMULATE: No surface-setting for <"+key.HASHKEY()+">");
				}
				
			}
			
			
			//2015-11-26: die vordefinierten datenfeld-werte iterieren
			String table_name = this.rb_dataobject_actual_for_mask.rb_relevant_record_to_fill().get_TABLENAME();
			if (S.isFull(table_name)) {
				MyE2_MessageVector  o_mv = new MyE2_MessageVector();
				_TAB table = _TAB.find_Table(table_name);
				if (table != null) {
					IF_Field[] fields = table.get_fields();
					for (IF_Field field: fields) {
						RB_ForcedDBValue db_val = this.rb_pre_SettingsContainer.rb_get_forcedDB(field);
						if (db_val.rb_get_formated_value_forced_at_save()!=null) {
							o_mv.add_MESSAGE(this.rb_dataobject_actual_for_mask.get_RecNEW().check_NewValueForDatabase(field.fieldName(), 
												db_val.rb_get_formated_value_forced_at_save(),
												false));
							if (o_mv.get_bHasAlarms()) {
								o_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.t("Fehlerinfo: Vorgabe-Wert für DB-Feld: "),
																							S.ut(field.fn()),
																							S.t(":"),
																							S.t(" unpassend ! ... Wert: "),
																							S.ut(db_val.rb_get_formated_value_forced_at_save()))));
							}
						}
					}
				}else {
					throw new myException(this,"Cannot identify table "+table_name);
				}
				
				oMV.add_MESSAGE(o_mv);
				
			} else {
				throw new myException(this,"NO TABLE is PRESENT");
			}
			
			
			
			for (Map.Entry<String, IF_RB_Component_Complex> entry : hm_RB_ComponentsComplex.entrySet()) {
				IF_RB_Component_Complex compComplex = entry.getValue();
				
				//jetzt die eigenen validators pruefen
				for (RB_Validator_Component vali: compComplex.rb_VALIDATORS_4_INPUT()) {
					oMV.add_MESSAGE(vali.do_Validate(entry.getValue()));
				}
			}
			
			
			
			
			
		} else {
			throw new myException(this,"maskContent_check_Values_and_Mark_Errors: No  RB_MASK_DATA present!");
		}
		return oMV;
	}


	
	
	
	/**
	 * 
	 * @return 's Record with new values MyRECORD_IF_FILLABLE is implemented in RECORD_<ABC> and MyRecord_NEW
	 * @throws myException
	 */
	public MyE2_MessageVector  rb_Mask_to_Dataobject() throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		
		//2020-02-27: hier die beforeMaskToRecordListeners
		if (beforeMaskToRecordListener.size()>0) {
			for (IF_ExecuterReturnsMV<RB_ComponentMap> exe: beforeMaskToRecordListener) {
				oMV._add(exe.execute(this));
				if (oMV.hasAlarms()) {
					break;
				}
			}
		}

		if (oMV.isOK()) {
		
			Vector<String>  v_excludeFields = this.get_EXCLUDE_HashKeys();
	
			MyRECORD_IF_FILLABLE  				recToFill = 		this.rb_dataobject_actual_for_mask.rb_relevant_record_to_fill();
			HashMap<String, MyMetaFieldDEF>		hmFieldMetaDefs = 	new HashMap<String, MyMetaFieldDEF>();
	
			if (this.rb_dataobject_actual_for_mask==null) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("RB_MASK:",false,"Schwerer Fehler: ",true,"mask_to_data: ",false,"besitzt kein RB_MASK_DATA!",true)));
			} else {
				
				HashMap<String, IF_RB_Component_Savable> hm_RB_ComponentsSave = 	this.getHmComponentsSavable();
				HashMap<String, IF_RB_Component_Complex> hm_RB_ComponentsComplex = 	this.getComplexComponents();
				
				//die zusatzvectoren in der Rb_Mask_data_actual leeren
				this.rb_dataobject_actual_for_mask.get_v_RECORD_IF_FILLABLE().removeAllElements();
				this.rb_dataobject_actual_for_mask.get_v_STATEMENTBUILDERS().removeAllElements();
				
				//referenz der felder immer aus der recordnew
				hmFieldMetaDefs.putAll(this.rb_dataobject_actual_for_mask.get_RecNEW());
				
				//zuerst die "normalen" felder
				for (Map.Entry<String, IF_RB_Component_Savable> entry : hm_RB_ComponentsSave.entrySet()) {
					String 						cHashKey =  entry.getKey();
					IF_RB_Component_Savable  	comp = 		entry.getValue();
					
					String cNewValue = comp.rb_readValue_4_dataobject();
					
					if (hmFieldMetaDefs.keySet().contains(cHashKey) && !(v_excludeFields.contains(cHashKey))) {
						oMV.add_MESSAGE(recToFill.set_NewValueForDatabase(cHashKey, cNewValue));
					}
				}
				
				
				
				//2015-11-26: die vordefinierten datenfeld-werte iterieren/ einfuegen
				String table_name = this.rb_dataobject_actual_for_mask.rb_relevant_record_to_fill().get_TABLENAME();
				if (S.isFull(table_name)) {
					_TAB table = _TAB.find_Table(table_name);
					if (table != null) {
						IF_Field[] fields = table.get_fields();
						for (IF_Field field: fields) {
							RB_ForcedDBValue db_val = this.rb_pre_SettingsContainer.rb_get_forcedDB(field);
							if (db_val.rb_get_formated_value_forced_at_save()!=null) {
								String cNewValue = db_val.rb_get_formated_value_forced_at_save();
								if (cNewValue != null) {
									oMV.add_MESSAGE(recToFill.set_NewValueForDatabase(field.fn(), cNewValue));
								}
							}
						}
					}else {
						throw new myException(this,"Cannot identify table "+table_name);
					}
				} else {
					throw new myException(this,"NO TABLE is PRESENT");
				}
				//2015-11-26: die vordefinierten datenfeld-werte iterieren
	
				
				
				//dann die komplexen
				for (Map.Entry<String, IF_RB_Component_Complex> entry : hm_RB_ComponentsComplex.entrySet()) {
					IF_RB_Component_Complex complexComp = entry.getValue();
					
					Vector<MyRECORD_IF_FILLABLE>  			vDaughterRecs = 			complexComp.maskContents_Transfer_To_Record_And_Prepare_Save(oMV,this);
					Vector<MySqlStatementBuilder>  	vDaughterSimpleStatements = complexComp.get_vSQL_StatementBuilders_Others(oMV,this);
					if (oMV.get_bIsOK()) {
						if (vDaughterRecs!=null) {
							oMV.add_MESSAGE(complexComp.makeBindingDaughterRecords_to_MotherTable(this.rb_dataobject_actual_for_mask, vDaughterRecs));
							this.rb_dataobject_actual_for_mask.get_v_RECORD_IF_FILLABLE().addAll(vDaughterRecs);
						}
						if (vDaughterSimpleStatements!=null) {
							this.rb_dataobject_actual_for_mask.get_v_STATEMENTBUILDERS().addAll(vDaughterSimpleStatements);
						}						
					}
				}
			}
		}
		return oMV;
	}


	
	/**
	 * martin, 2016-12-08
	 * die validierung der feldeigenen inputvalidators wird hier erweitert auf IF_RB_Component ohne saveable-status
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector check_field_validators_of_not_saving_Fields() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		HashMap<String, IF_RB_Component> hm_RB_Components = 	this.getHmComponentsNotSavable();

		for (Map.Entry<String, IF_RB_Component> entry : hm_RB_Components.entrySet()) {
			MyE2_MessageVector mv_i = new MyE2_MessageVector();
			
			//jetzt die eigenen validators pruefen
			//20190320: falls rb_VALIDATORS_4_INPUT keinen leeren vektor sondern null liefert
			if (entry.getValue().rb_VALIDATORS_4_INPUT()!=null) {
				for (RB_Validator_Component vali: entry.getValue().rb_VALIDATORS_4_INPUT()) {
					mv_i.add_MESSAGE(vali.do_Validate(entry.getValue()));
				}
			}
			if (mv_i.get_bHasAlarms()) {
				entry.getValue().mark_FalseInput();
			}
			mv.add_MESSAGE(mv_i);
		}
		
		return mv;
	}
	
	


	public void  setSurfaceSettingsActive()  throws myException {
		
		for (Map.Entry<String, MyE2IF__Component> entry: this.entrySet()) {
			MyE2IF__Component	comp 	= entry.getValue();
			RB_KF    		key = comp.EXT().get_RB_K();
			
			RB_SurfaceSettings  surfaceSet = this.rb_pre_SettingsContainer.rb_get(key);
			
			//zuruecksetzen
			if (comp instanceof IF_Formatter) {
				((IF_Formatter)comp).mark_Neutral();
			}
			
			if (surfaceSet != null) {
				//alignment
				if (comp instanceof IF_Formatter) {
					
//					//##debug
//					if (comp instanceof RB_TextField_old) {
//						if (comp.EXT().get_RB_K().equals(BEWEGUNG_ATOM.menge.fk())) {
//							DEBUG.System_println("hier");
//						}
//					}
//					//##debug ende
					
					
					((IF_Formatter)comp).set_Alignment(surfaceSet.get_Alignment());
					((IF_Formatter)comp).setFocusTraversalParticipant(surfaceSet.is_GetsFocus());
				}

				
				//enabled/disabled
				comp.set_bEnabled_For_Edit(surfaceSet.is_EnabledComplete());
				if (!surfaceSet.is_EnabledComplete()) {
					if (comp instanceof IF_Formatter) {
						((IF_Formatter)comp).mark_Disabled();
					}
				}
				
				if (surfaceSet.is_MustFieldComplete()) {
					if (comp instanceof IF_Formatter) {
						((IF_Formatter)comp).mark_MustField();
					}
				}
				
				
			} else {
				throw new myException(this,"rb_maskSettings_render_SurfaceSettings: No MASK_COMPONENT_METADEF for <"+key.HASHKEY()+">");
			}
		}
		
	}

	
	/**
	 * die vordefinierten datenbankinhalte in die felder fuellen
	 * @throws myException
	 */
	public void  setSurfaceSettingPresetValuesToMask()  throws myException {
		
		for (Map.Entry<String, MyE2IF__Component> entry: this.entrySet()) {
			MyE2IF__Component	comp 	= entry.getValue();
			RB_KF    		key = comp.EXT().get_RB_K();
			
			RB_SurfaceSettings  surfaceSet = this.rb_pre_SettingsContainer.rb_get(key);
			
			if (surfaceSet != null) {
				if (comp instanceof IF_RB_Component_Savable) {
					if (this.rb_dataobject_actual_for_mask.rb_MASK_STATUS().equals(RB__CONST.MASK_STATUS.NEW)) {
						IF_RB_Component_Savable oCompSave = (IF_RB_Component_Savable)comp;
						if (S.isFull(surfaceSet.get_rb_DefaultComplete())) {
							oCompSave.rb_set_db_value_manual(surfaceSet.get_rb_DefaultComplete());
						}
					}
				}
			} else {
				throw new myException(this,"rb_maskSettings_render_PresetValues: No RB_SurfaceSettings for <"+key.HASHKEY()+">");
			}
		}
	}

	
	
	
	/**
	 * alle speziellen maskeneinstellungen wieder auf anfang
	 */
	public MyE2_MessageVector setSurfaceSettingsNeutral() 	throws myException {
		
		//die interactiv-anteile resetten
		for (Map.Entry<String, MyE2IF__Component> entry: this.entrySet()) {
			if (entry.getValue() instanceof IF_Formatter) {
				((IF_Formatter)entry.getValue()).mark_Neutral();
			}
		}
		
		return new MyE2_MessageVector();
	}

	
	
	public RB_Dataobject getRbDataObjectActual()
	{
		return rb_dataobject_actual_for_mask;
	}

	

	
	/**
	 * 
	 * @author martin
	 * @date 08.01.2019
	 *
	 * @return s actual status of loaded dataobject or null
	 */
	public RB__CONST.MASK_STATUS getStatus() {
		RB__CONST.MASK_STATUS ret = null;
		
		if (this.getRbDataObjectActual()!=null) {
			ret = this.getRbDataObjectActual().rb_MASK_STATUS();
		}
		
		return ret;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 24.01.2019
	 *
	 * @return
	 * @throws myException
	 */
	public boolean isSaveable() {
		boolean ret = false;
		
		if (this.getRbDataObjectActual()!=null) {
			ret = this.getRbDataObjectActual().rb_MASK_STATUS().isStatusCanBeSaved();
		}
		
		return ret;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 05.03.2020
	 *
	 * @return s on new and newCopy true, otherwise false, on error NULL
	 */
	public Boolean isNew() {
		Boolean ret = null;   //falls null wirde new zurueckgegeben
		
		if (this.getRbDataObjectActual()!=null) {
			ret = this.getRbDataObjectActual().rb_MASK_STATUS().isStatusNew();
		}
		
		return ret;
	}
	
	
	
	

	public Vector<String> get_EXCLUDE_HashKeys() throws myException {
		
		if (this.rb_dataobject_actual_for_mask!=null) {
			Vector<String> v_excl = new Vector<String>();
			v_excl.addAll(this.rb_dataobject_actual_for_mask.get_RecNEW().get_vSonderFelder());
			v_excl.add(this.rb_dataobject_actual_for_mask.get_RecNEW().get_PRIMARY_KEY_NAME());
			this.user_setting_change_EXCLUDE_HASHKEYS(v_excl);
			return v_excl;
		} else {
			throw new myException(this,"Method: get_EXCLUDE_FIELD() is only possible with defined Rb_Mask_data!");
		}
	}
	
	
	public RB_KM getOwnMaskKey() {
		return own_rb_mask_key;
	}
	public void setOwnMaskKey(RB_KM own_Mask_Key) {
		this.own_rb_mask_key = own_Mask_Key;
	}


	/**
	 * 2019-02-15: methoden fuer functionales interface
	 * @author martin
	 * @date 15.02.2019
	 *
	 * @param f_key
	 * @param simpleSetter
	 * @throws myException
	 */
	public void registerSetterValidators(RB_KF f_key, IF_Simple_Mask_Set_And_Valid simpleSetter) throws myException {
		this.registerSetterValidators(f_key, simpleSetter.get());
	}
	public void registerSetterValidators(IF_Field field, IF_Simple_Mask_Set_And_Valid simpleSetter) throws myException {
		this.registerSetterValidators(field.fk(), simpleSetter.get());
	}
	
	
	
	/**
	 * 20180508: weitere methode, machts einfacher
	 * @param field
	 * @param validator
	 * @throws myException
	 */
	public void registerSetterValidators(IF_Field field, RB_Mask_Set_And_Valid validator) throws myException {
		this.registerSetterValidators(field.fk(),validator);
	}
	
	
	
	/**
	 * validierer anmelden, bei ersten annmelden wird 
	 * hash-key zugeordneten objekten wird ein actionagent hinterlegt
	 * @param cHASH4Component, oValidator
	 */
	public void registerSetterValidators(RB_KF f_key, RB_Mask_Set_And_Valid validator) throws myException {
		
		if (this.keySet().contains(f_key.HASHKEY())) {
			
			MyE2IF__Component oComp = this.get(f_key.HASHKEY());
			
			if (oComp != null) {
				this.getMaskSetAndValidContainer().registerComponent(f_key, validator);
			} else {
				throw new myException(this,"Validation-Component not found!");
			}
			
			//2017-02-03: neues spezielles interface fuer zusammengesetzte komponenten
			//interface fuer spezielles binding
			if (oComp instanceof IF_RB_SetAndValidBinder) {
				VEK<XX_ActionAgent> actions = ((IF_RB_SetAndValidBinder)oComp).getActionAgentsForMapSetAndValidators();
				boolean bIstBereitsDa = false;
				for (XX_ActionAgent oAgent: actions) {
					if (oAgent instanceof ownClickActionAgent) {
						bIstBereitsDa = true;
					}
				}
				if (!bIstBereitsDa) {
					actions._a(new ownClickActionAgent(f_key));
				}
				
			} else if (oComp instanceof E2_IF_Handles_ActionAgents) {

				//dafuer sorgen, dass bei mehrfacher zuordnung eines validier-objects der actionagent nur einemal zugeordnet wird
				Vector<XX_ActionAgent>  vActionAgents = ((E2_IF_Handles_ActionAgents)oComp).get_vActionAgents();
				boolean bIstBereitsDa = false;
				for (XX_ActionAgent oAgent: vActionAgents) {
					if (oAgent instanceof ownClickActionAgent) {
						bIstBereitsDa = true;
					}
				}
				
				if (!bIstBereitsDa) {
					((E2_IF_Handles_ActionAgents)oComp).add_oActionAgent(new ownClickActionAgent());
				}
			}
			
		} else {
			throw new myException(this,"Validation-Component not found: Mask:"+this.own_rb_mask_key.HASHKEY()+", Field:"+f_key.HASHKEY());
		}
		
	}

	
	
	
	
	
	//2019-09-23: erweiterung des actionagents
	protected class ownClickActionAgent extends XX_ActionAgent {
		
		private RB_KF 				rb_keyCompoent = 	null;
		
		public ownClickActionAgent() {
			super();
		}
		
		//2019-09-23: constructor fuer IF_RB_SetAndValidBinder, wo die agents nicht direkt an die eigentliche maskenkomponente gebunden sind
		public ownClickActionAgent(RB_KF p_rb_keyCompoent) {
			super();
			rb_keyCompoent = p_rb_keyCompoent;
		}
		

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			
			if (oExecInfo!=null && rb_keyCompoent==null) {
				rb_keyCompoent = oExecInfo.get_RB_K_of_KLICK_COMPONENT();
			}

			/**
			 * nur auf den fuer eine komponente registrierte validators ausfuehren
			 */
			for (RB_Mask_Set_And_Valid valid:  RB_ComponentMap.this.interactive_Settings_Validations.get(rb_keyCompoent)) {
				bibMSG.add_MESSAGE(valid.make_Interactive_Set_and_Valid(RB_ComponentMap.this,VALID_TYPE.USE_IN_MASK_KLICK_ACTION,oExecInfo));
			}
			
		}
		
	}
	
	
//	private class ownClickActionAgent extends XX_ActionAgent {
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//
//			RB_KF 				rb_keyCompoent = 	null;
//			
//			if (oExecInfo!=null) {
//				rb_keyCompoent = oExecInfo.get_RB_K_of_KLICK_COMPONENT();
//			}
//
//			/**
//			 * nur auf den fuer eine komponente registrierte validators ausfuehren
//			 */
//			for (RB_Mask_Set_And_Valid valid:  RB_ComponentMap.this.interactive_Settings_Validations.get(rb_keyCompoent)) {
//				bibMSG.add_MESSAGE(valid.make_Interactive_Set_and_Valid(RB_ComponentMap.this,VALID_TYPE.USE_IN_MASK_KLICK_ACTION,oExecInfo));
//			}
//			
//		}
//		
//	}
//	

	
	
	public RB_Mask_Set_And_Valid_Container getMaskSetAndValidContainer() {
		return interactive_Settings_Validations;
	}


	private String generate_KennerString4Validators() throws myException {
		String kenner4validators = null;
		
		RB_ComponentMapCollector  rb_mC = this.rb_componentMapCollector_this_BELONGS_TO;
		if (rb_mC!=null) {
			IF_Container4Visualisation  rb_modCon = rb_mC.rb_get_belongs_to();
			if (rb_modCon==null) {
				throw new myException(this,"!!Registration-Chain is not correct!! First add RB_ComponentMAP to collector and then to IF_Container4Visualisation");
			}
			if (rb_mC != null) {
				//kennung wird zusammengesetzt aus : Kennung des MaskContainers @ HashKey des RB_MASK-objekts innerhalb des RB_Mask_Containers
				if (rb_modCon.rb_get_OwnKENNER()!=null && rb_modCon.rb_get_OwnKENNER().get_callKey()!=null) {
					kenner4validators=rb_modCon.rb_get_OwnKENNER().get_callKey(); //+"@"+this.own_rb_mask_key.HASHKEY();
				}
			}
		}

		if (kenner4validators!=null) {
			return kenner4validators;
		} else {
			throw new myException(this,"!!Registration-Chain is not correct!!");
		}
		
	}
	
	
	public RB_ComponentMap _setValue(RB_KF field, String new_val) throws myException{
		if (this.getRbComponent(field)!=null ) {
			this.getRbComponent(field).rb_set_db_value_manual(new_val);
		} else {
			throw new myException(this,"Error: Field "+field.FIELDNAME()+" is not in this RB_ComponentMAP!");
		}
		return this;
	}
	
	public RB_ComponentMap _setValue(IF_Field field, String new_val) throws myException{
		if (this.getRbComponent(field.fk())!=null ) {
			this.getRbComponent(field.fk()).rb_set_db_value_manual(new_val);
		} else {
			throw new myException(this,"Error: Field "+field.fn()+" is not in this RB_ComponentMAP!");
		}
		return this;
	}

	
	
	
	
	@Override
	public Iterator<IF_RB_Component> iterator() {
		return this.rb_hm_only_rb_components.values().iterator();
	}

	
	
	
	/**
	 * findet in der componentmap-struktur alle komponenten, die mit einem feldnamen registriert sind
	 * @param field_key
	 * @return
	 * @throws myException
	 */
	public  HashMap<RB_KM,IF_RB_Component>  _find_components_in_neighborhood(RB_KF field_key) throws myException {
		HashMap<RB_KM,IF_RB_Component> v_c = new HashMap<>();
		RB_ComponentMap map = this;

		if (map != null) {
			IF_RB_Component c = map.getIfRbComp(field_key);
			if (c!=null) {
				v_c.put(map.getOwnMaskKey(),c);
			}
			
			//jetzt die anderen componentmaps
			RB_ComponentMapCollector map_c = map.rb_get_belongs_to();
			
			for (RB_ComponentMap m: map_c) {
				IF_RB_Component c1 = m.getIfRbComp(field_key);
				if (c1!=null) {
					v_c.put(m.getOwnMaskKey(),c1);
				}
			}
		}
		return v_c;
	}

	/**
	 * findet in der componentmap-struktur alle komponenten, die mit einem feldnamen registriert sind
	 * @param field
	 * @return
	 * @throws myException
	 */
	public  HashMap<RB_KM,IF_RB_Component>  _find_components_in_neighborhood(IF_Field field) throws myException {
		return this._find_components_in_neighborhood(field.fk());
	}

	
	/**
	 * findet, falls es ein eindeutiges feld des namen im Umfeld gibt, dieses ansonsten null (auch bei mehrdeutigkeit)
	 * @param field
	 * @return
	 * @throws myException
	 */
	public  IF_RB_Component  _find_component_in_neighborhood(RB_KF field) throws myException {
		HashMap<RB_KM,IF_RB_Component> hm = this._find_components_in_neighborhood(field);
		
		if (hm.values().size()==1) {
			return new Vector<IF_RB_Component>(hm.values()).get(0);
		}
		
		return null;
	}

	/**
	 * findet, falls es ein eindeutiges feld des namen im Umfeld gibt, dieses ansonsten null (auch bei mehrdeutigkeit)
	 * @param field
	 * @return
	 * @throws myException
	 */
	public  IF_RB_Component  _find_component_in_neighborhood(IF_Field field) throws myException {
		return this._find_component_in_neighborhood(field.fk());
	}

	/**
	 * findet, feld aus der maskenCollection
	 * @param mask
	 * @param field
	 * @return
	 * @throws myException
	 */
	public  IF_RB_Component  _find_component_in_neighborhood(RB_KM mask, RB_KF field) throws myException {
		HashMap<RB_KM,IF_RB_Component> hm = this._find_components_in_neighborhood(field);
		
		return hm.get(mask);
	}
	
	
	/**
	 * 
	 * @return HashMap<RB_KF,IF_RB_Component>, d.h. alle komponenten der maske, die das interface IF_RB_Component erfuellen
	 */
	public HashMap<RB_KF, IF_RB_Component> getHmOnlyRbComponents() {
		return rb_hm_only_rb_components;
	}
	
	
	/**
	 * 
	 * @return HashMap<RB_KF,MyE2IF__Component>, d.h. alle komponenten der maske, die das interface MyE2IF__Component erfuellen (enthalten alle von rb_hm_only_rb_components());
	 */
	public RB_COMP_Container getInnerComponentMap() {
		return rb_inner_componentMap;
	}


	
	/**
	 * 2018-01-18: nuer speicherbare components 
	 * @return
	 */
	public HashMap<RB_KF, IF_RB_Component_Savable> getHmOnlyRbComponentsSaveable() {
		
		HashMap<RB_KF, IF_RB_Component_Savable> hm = new HashMap<RB_KF, IF_RB_Component_Savable>();
		for (RB_KF f: this.rb_hm_only_rb_components.keySet()) {
			if (this.rb_hm_only_rb_components.get(f) instanceof IF_RB_Component_Savable) {
				hm.put(f, (IF_RB_Component_Savable)this.rb_hm_only_rb_components.get(f));
			}
		}
		
		return hm;
	}
	
	

	/**
	 * 
	 * @return s first component from type IF_RB_Component
	 */
	public IF_RB_Component getFirstRbComponent() {
		for (IF_RB_Component c: this.rb_hm_only_rb_components.values()) {
			return c;
		}
		return null;
	}
	
	
	
	
	//20190925: erweiterungen mit intrinsischem maskcontroller
	public String getStringLiveVal(IF_Field field)  throws myException {
		return new RB_MaskController(this).get_liveVal(field);
	}

	public boolean getYNLiveVal(IF_Field field)  throws myException {
		return new RB_MaskController(this).getYNMaskVal(field._t().rb_km(), field.fk());
	}

	public Long getLongLiveVal(IF_Field field)  throws myException {
		return new RB_MaskController(this).getLongLiveVal(field);
	}

	public BigDecimal getBigDecimalLiveVal(IF_Field field)  throws myException {
		return new RB_MaskController(this).getBigDecimalLiveVal(field);
	}


	public Date getDateLiveVal(IF_Field field)  throws myException {
		return new RB_MaskController(this).getDateLiveVal(field);
	}

	
	public RB_ComponentMap _setClearWhenNotEmpty(RB_KM mask, IF_Field field, MyE2_String message, MyE2_MessageVector mv) throws myException {
		new RB_MaskController(this)._setClearWhenNotEmpty(mask, field.fk(), message, mv);
		return this;
	}
	
	public RB_ComponentMap setMaskVal(IF_Field field, String value, boolean onlyIfEmpty)   throws myException{
		new RB_MaskController(this).setMaskVal(field._t().rb_km(), field.fk(), value, onlyIfEmpty);
		return this;
	}

	
	public RB_ComponentMap _addListenersAfterMaskWasPopulated(IF_ExecuterReturnsMV<RB_ComponentMap> executer) {
		this.afterMaskPopulateListeners._a(executer);
		return this;
	}
	
	public RB_ComponentMap _clearListenersAfterMaskWasPopulated() {
		this.afterMaskPopulateListeners._clear();
		return this;
	}
	
	
	public RB_ComponentMap _addListenersBeforeMaskToRecord(IF_ExecuterReturnsMV<RB_ComponentMap> executer) {
		this.beforeMaskToRecordListener._a(executer);
		return this;
	}
	
	public RB_ComponentMap _clearListenersBeforeMaskToRecord() {
		this.beforeMaskToRecordListener._clear();
		return this;
	}
	
	
}
 