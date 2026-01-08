package panter.gmbh.Echo2.RB.BASICS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import panter.gmbh.BasicInterfaces.IF_ExecuterReturnsMV;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_Container4Visualisation;
import panter.gmbh.Echo2.RB.IF.IF_RB_Collector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.IF.IF_RB_Part;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid_Container;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.QUAD;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * container-klasse um LinkedHashMap
 * @author martin
 * 
 */
public class RB_ComponentMapCollector  implements Iterable<RB_ComponentMap>, IF_RB_Collector<RB_ComponentMap>, IF_RB_Part<IF_Container4Visualisation>{

	private LinkedHashMap<RB_KM,RB_ComponentMap> 	hm_rb_ComponentMap = new LinkedHashMap<RB_KM, RB_ComponentMap>(); 
	
	private IF_Container4Visualisation 				rb_ModuleContainerMASK_this_belongs_to = null;
	private RB_DataobjectsCollector    				rb_DataobjectsCollector = null;
	
	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  										my_key_in_collection = null;
	
	private VEK<IF_ExecuterReturnsMV<RB_ComponentMapCollector>>   beforeMaskSaveCycleAndValidationListener = new VEK<>();

	
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	public RB_ComponentMapCollector() {
		super();
	}

	
	public RB_ComponentMap get(RB_KM k) {
		return this.hm_rb_ComponentMap.get(k);
	}

	
	
	public RB_ComponentMap rb_get_ComponentMAP(RB_KM k) {
		return this.hm_rb_ComponentMap.get(k);
	}




	@Override
	public RB_ComponentMap registerComponent(RB_K maskKey, RB_ComponentMap map) throws myException {
		if (maskKey instanceof RB_KM) {
			RB_KM key = (RB_KM)maskKey;
			RB_ComponentMap maskAdded= this.hm_rb_ComponentMap.put(key,map);
			map.rb_set_belongs_to(this);
			map.setOwnMaskKey(key);
			
			//2016-07-19: einheitliche registrierung
			map.setMyKeyInCollection(key);

			
			return maskAdded;
		} else {
			throw new myException(this,"rb_register: MUST USER RB_KM-object");
		}
	}

	
	
	/**
	 * 
	 * @return 's MyE2_MessageVector
	 * @throws myException
	 */
	public MyE2_MessageVector  rb_InputValues_to_Dataobjects_SIMULATE() throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		for (RB_ComponentMap mask: this.hm_rb_ComponentMap.values()) {
			oMV.add_MESSAGE(mask.rb_Mask_to_Dataobject_SIMULATE());
		}
		return oMV;
	}

	
	/**
	 * 
	 * @return 's HashMap<String, MyRECORD_IF_FILLABLE> filled with maskValues
	 * @throws myException
	 */
	public MyE2_MessageVector  rb_InputValues_to_Dataobjects() throws myException {
		
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		if (this.rb_DataobjectsCollector==null) {
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("RB_MASK_HM ",false," Schwerer Fehler: ",true," mask_to_data: ",false, " kein Daten-objekt vorhanden",true)));
		} else {
			for (Map.Entry<RB_KM, RB_ComponentMap> entry: this.hm_rb_ComponentMap.entrySet()) {
				oMV.add_MESSAGE(entry.getValue().rb_Mask_to_Dataobject());
			}
		}
		return oMV;
	}



	
	
	public MyE2_MessageVector rb_COMPLETE_SAVE_CYCLE(boolean bForceSaveEvenWhenNothingWasChanged) throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//2020-03-06: hier die beforeMaskToRecordListeners
		if (beforeMaskSaveCycleAndValidationListener.size()>0) {
			for (IF_ExecuterReturnsMV<RB_ComponentMapCollector> exe: beforeMaskSaveCycleAndValidationListener) {
				oMV._add(exe.execute(this));
				if (oMV.hasAlarms()) {
					break;
				}
			}
		}
        
		if (oMV.isOK()) {
		
			//2016-08-04: aktion vor der ersten pruefung (um felder zu besetzen z.B.)
			oMV.add_MESSAGE(this.rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION));
			if (oMV.get_bIsOK()) {
				oMV.add_MESSAGE(this.rb_InputValues_to_Dataobjects_SIMULATE());
				oMV.add_MESSAGE(this.rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE.USE_IN_MASK_VALID_ACTION));
				if (oMV.get_bIsOK()) {
					oMV.add_MESSAGE(this.rb_InputValues_to_Dataobjects());
					if (oMV.get_bIsOK()) {
						oMV.add_MESSAGE(this.rb_DataobjectsCollector.rb_Dataobjects_to_Database(bForceSaveEvenWhenNothingWasChanged));
					}
				}
			}
			
		}
		return oMV;
	}
	

	/**
	 * 2018-05-22: martin
	 * @param bForceSaveEvenWhenNothingWasChanged
	 * @return messages, which are generated on validation of mask
	 * @throws myException
	 */
	public MyE2_MessageVector rb_SIMULATE_SAVE_VALIDATION_CYCLE() throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//2020-03-06: hier die beforeMaskToRecordListeners
		if (beforeMaskSaveCycleAndValidationListener.size()>0) {
			for (IF_ExecuterReturnsMV<RB_ComponentMapCollector> exe: beforeMaskSaveCycleAndValidationListener) {
				oMV._add(exe.execute(this));
			}
		}
		
		//2016-08-04: aktion vor der ersten pruefung (um felder zu besetzen z.B.)
		oMV.add_MESSAGE(this.rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION));
		oMV.add_MESSAGE(this.rb_InputValues_to_Dataobjects_SIMULATE());
		oMV.add_MESSAGE(this.rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE.USE_IN_MASK_VALID_ACTION));
		return oMV;
	}
	
	
	
	
	
	
	/**
	 * @deprecated use rb_COMPLETE_MASK_RELOAD(
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector rb_COMPLETE_MASK_RELOAD() throws myException{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		this.rb_Actual_DataobjectCollector().rb_Rebuild_ALL_RECORD(true);
		
		oMV.add_MESSAGE(this.rb_COMPLETE_FILL_CYCLE(this.rb_Actual_DataobjectCollector()));
		
		return oMV;
	}
	

	
	/**
	 * 20180105: martin
	 * komplettes reload mit neuem maskenstatus (nur mit v2 sinnvoll)
	 * @param statusAfterReload
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector rb_CompleteMaskReload(MASK_STATUS statusAfterReload) throws myException{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		this.rb_Actual_DataobjectCollector().rb_RebuildAllRecords();
		
		for (RB_Dataobject d: this.rb_Actual_DataobjectCollector().rb_hm_DataObjects().values()) {
			d.set_actualMASK_STATUS(statusAfterReload);
		}
		oMV.add_MESSAGE(this.rb_COMPLETE_FILL_CYCLE(this.rb_Actual_DataobjectCollector()));
		return oMV;
	}

	
	
	/**
	 * 20180809: martin
	 * @param newStatus
	 * @return
	 */
	public RB_ComponentMapCollector _setCompleteMaskStatusInAllMembers(MASK_STATUS newStatus) {
		for (RB_Dataobject d: this.rb_Actual_DataobjectCollector().rb_hm_DataObjects().values()) {
			d.set_actualMASK_STATUS(newStatus);
		}
		return this;
	}

	
	
	
	/**
	 * global ausgefuehte  set_and_valid-laeufe (Typen: USE_IN_MASK_LOAD_ACTION,USE_IN_MASK_VALID_ACTION,USE_IN_MASK_UNBOUND_KLICK_ACTION)
     * @param usingContext
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE usingContext) throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		Vector<RB_Mask_Set_And_Valid>  vDublettencheck = new Vector<RB_Mask_Set_And_Valid>();
		
		for (RB_ComponentMap  mask: this.hm_rb_ComponentMap.values()) {
			RB_Mask_Set_And_Valid_Container  maskValidContainer = mask.getMaskSetAndValidContainer();
			for (RB_KF key: maskValidContainer.keySet()) {
				for (RB_Mask_Set_And_Valid valid: maskValidContainer.get(key)) {
					if (!vDublettencheck.contains(valid)) {
						oMV.add_MESSAGE(valid.make_Interactive_Set_and_Valid(mask, usingContext, null));
						vDublettencheck.add(valid);
					}
				}
			}
		}
		return oMV;
	}
	
	
	

	/**
	 * 
	 * @param rb_Dataobjects_Container
	 * @return
	 * @throws myException
	 */
	public MyE2_MessageVector  rb_COMPLETE_FILL_CYCLE(RB_DataobjectsCollector rb_Dataobjects_Container) throws myException {
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		this.rb_DataobjectsCollector = rb_Dataobjects_Container;
		
		
		if (this.rb_DataobjectsCollector!=null) {
			this.rb_DataobjectsCollector.set_rb_ComponentMapCollector_ThisBelongsTo(this);
		
			for (RB_KM key: this.hm_rb_ComponentMap.keySet()) {
				oMV.add_MESSAGE(this.get(key).setDataToMask(this.rb_DataobjectsCollector.get(key)));
			}
			
			oMV.add_MESSAGE(this.rb_EXECUTE_Mask_Set_And_Validation_all_MASKS_Global(VALID_TYPE.USE_IN_MASK_LOAD_ACTION));
			return oMV;
		} else {
			throw new myException(this,"data_to_mask():  No RB_Dataobject_Container was set !");
		}
	}
	


	@Override
	public Iterator<RB_ComponentMap> iterator() {
		return this.hm_rb_ComponentMap.values().iterator();
	}


	@Override
	public void rb_set_belongs_to(IF_Container4Visualisation moduleContainerMask) throws myException {
		this.rb_ModuleContainerMASK_this_belongs_to=moduleContainerMask;
	}


	@Override
	public IF_Container4Visualisation rb_get_belongs_to() throws myException {
		return this.rb_ModuleContainerMASK_this_belongs_to;
	}

	/**
	 * besser lesbar
	 */
	public IF_Container4Visualisation rb_get_maskcontainer_i_belong_to() throws myException {
		return this.rb_ModuleContainerMASK_this_belongs_to;
	}
	
	

	public RB_DataobjectsCollector rb_Actual_DataobjectCollector() {
		return rb_DataobjectsCollector;
	}


	
	public LinkedHashMap<RB_KM, RB_ComponentMap> rb_hm_RB_ComponentMaps() {
		return hm_rb_ComponentMap;
	}


	
	/**
	 * 
	 * @param fieldAdress (bestehend aus mask- und field-key
	 * @return found Component or null
	 */
	public Component  rb_Comp(RB_FieldCoordinate fieldAdress) {
		if (fieldAdress!=null) {
			RB_ComponentMap map = this.get(fieldAdress.getMaskKey());
			if (map != null) {
				return map.getComp(fieldAdress.getFieldKey());
			}
		}
		return null;
	}


	/**
	 * 
	 * @param maskKey
	 * @param field
	 * @return Component from ComponentMap with maskKey and Field=field
	 * @throws myException
	 */
	public Component  rb_Comp(RB_KM maskKey, IF_Field field) throws myException {
		return this.rb_Comp(new RB_FieldCoordinate(maskKey, field));
	}
	
	
	/**
	 * 
	 * @param maskKey
	 * @param field
	 * @return Component from ComponentMap with maskKey and Field=field
	 * @throws myException
	 */
	public Component  rb_Comp(RB_KM maskKey, RB_KF field) throws myException {
		return this.rb_Comp(new RB_FieldCoordinate(maskKey, field));
	}


	
	/**
	 * prueft nach, ob ein maskenwert veraendert wurde
	 * @param onlyRegisteredComponents    wenn true, dann nur komponenten pruefen, die das registrierungsflag zeigen 
	 * @return
	 * @throws myException
	 */
	public int getNumberOfChangedFields(boolean onlyRegisteredComponents) throws myException {
		int i=0;
		
		RB_MaskController c = new RB_MaskController(this);
		
		for (RB_KM mk: this.hm_rb_ComponentMap.keySet()) {
			
			//if (map.rb_Mask_Dataobject_actual())
			HashMap<RB_KF, IF_RB_Component_Savable> hmComponents = this.get(mk).getHmOnlyRbComponentsSaveable();
			for (RB_KF f: hmComponents.keySet()) {
				if (hmComponents.get(f).rb_ComponentMap_this_belongsTo()!=null)  {      //ohne componentmap wird nix gespeichert
					if (hmComponents.get(f).isRegisteredToMask() || (!onlyRegisteredComponents)) {
						String formatedDbVal = S.NN(c.get_dbVal(mk, f));
						String formatedLiveVal = S.NN(c.get_liveVal(mk, f));
						
						//sonderfall: wenn f ein String(1) - d.h. YESNO ist, dann gilt ein leerer datenbank-wert wie N
						if (hmComponents.get(f) instanceof CheckBox) {
							if (S.isEmpty(formatedDbVal)) {
								formatedDbVal="N";
							}
						}
						
						if (!(formatedDbVal.equals(formatedLiveVal))) {
							i++;
						}
					}
				}
			}
				
		}
		
		
		return i;
	}

	
	
	/**
	 * 2018-01-18: schaut nach, ob mindestens einer der in der collection vorhandenen componentmaps gespeichert werden kann 
	 * @return
	 * @throws myException 
	 */
	public boolean isComponentMapCollectorSaveable() throws myException {
		boolean b = false;
		
		for (RB_ComponentMap m: this.hm_rb_ComponentMap.values()) {
			b = b || m.getRbDataObjectActual().isStatusCanBeSaved();
		}
		return b;
	}
	
	
	
	
	/**
	 * prueft nach, ob ein maskenwert veraendert wurde
	 * @param exclude   falls bestimmte mask/field-key-kombination ausgeschlossen werden sollen
	 * @return
	 * @throws myException
	 */
	public int getNumberOfChangedFields(Vector<Pair<RB_K>> exclude) throws myException {
		int i=0;
		
		RB_MaskController c = new RB_MaskController(this);
		
		for (RB_KM mk: this.hm_rb_ComponentMap.keySet()) {
			
			//if (map.rb_Mask_Dataobject_actual())
			HashMap<RB_KF, IF_RB_Component_Savable> hmComponents = this.get(mk).getHmOnlyRbComponentsSaveable();
			for (RB_KF f: hmComponents.keySet()) {
				boolean isExcluded = false;
				if (exclude!=null && exclude.size()>0) {
					for (Pair<RB_K> p: exclude) {
						if (p.getVal1().equals(mk) && p.getVal2().equals(f)) {
							isExcluded = true;
							break;
						}
					}
				}
				if (!isExcluded) {
					if (hmComponents.get(f).rb_ComponentMap_this_belongsTo()!=null)  {      //ohne componentmap wird nix gespeichert
						String formatedDbVal = S.NN(c.get_dbVal(mk, f));
						String formatedLiveVal = S.NN(c.get_liveVal(mk, f));
						
						//sonderfall: wenn f ein String(1) - d.h. YESNO ist, dann gilt ein leerer datenbank-wert wie N
						if (hmComponents.get(f) instanceof CheckBox) {
							if (S.isEmpty(formatedDbVal)) {
								formatedDbVal="N";
							}
						}
						
						if (!(formatedDbVal.equals(formatedLiveVal))) {
							i++;
						}
					}
				}
			}
		}
		
		return i;
	}

	
	/**
	 * holt die veraenderten maskenwerte in einem VEK<Quad> ab
	 * @param exclude   falls bestimmte mask/field-key-kombination ausgeschlossen werden sollen
	 * @return VEK<QUAD> with <maskKey,fieldKey,loaded,mask-val>
	 * @throws myException
	 */
	public VEK<QUAD<RB_K,RB_K,String,String>> getChangedFields(Vector<Pair<RB_K>> exclude) throws myException {
		
		VEK<QUAD<RB_K,RB_K,String,String>> ret = new VEK<QUAD<RB_K,RB_K,String,String>>();
		
		RB_MaskController c = new RB_MaskController(this);
		
		for (RB_KM mk: this.hm_rb_ComponentMap.keySet()) {
			
			//if (map.rb_Mask_Dataobject_actual())
			HashMap<RB_KF, IF_RB_Component_Savable> hmComponents = this.get(mk).getHmOnlyRbComponentsSaveable();
			for (RB_KF f: hmComponents.keySet()) {
				boolean isExcluded = false;
				if (exclude!=null && exclude.size()>0) {
					for (Pair<RB_K> p: exclude) {
						if (p.getVal1().equals(mk) && p.getVal2().equals(f)) {
							isExcluded = true;
							break;
						}
					}
				}
				if (!isExcluded) {
					if (hmComponents.get(f).rb_ComponentMap_this_belongsTo()!=null)  {      //ohne componentmap wird nix gespeichert
						String formatedDbVal = S.NN(c.get_dbVal(mk, f));
						String formatedLiveVal = S.NN(c.get_liveVal(mk, f));
						
						//sonderfall: wenn f ein String(1) - d.h. YESNO ist, dann gilt ein leerer datenbank-wert wie N
						if (hmComponents.get(f) instanceof CheckBox) {
							if (S.isEmpty(formatedDbVal)) {
								formatedDbVal="N";
							}
						}
						
						if (!(formatedDbVal.equals(formatedLiveVal))) {
							ret._a(new QUAD<RB_K,RB_K,String,String>(mk, f,formatedDbVal,formatedLiveVal ));
						}
					}
				}
			}
		}
		
		return ret;
	}

	
	/**
	 * 
	 * @return first found RB_ComponentMAP or null
	 */
	public RB_ComponentMap getFirstComponentMap() {
		for (RB_ComponentMap m: this) {
			return m;
		}
		return null;
	}
	
	/**
	 * 
	 * @author martin
	 * @date 06.03.2020
	 *
	 * @param executer
	 * @return
	 * @throws myException
	 */
	public RB_ComponentMapCollector _addBeforeMaskSaveCycleAndValidationListener(IF_ExecuterReturnsMV<RB_ComponentMapCollector> executer) throws myException {
		this.beforeMaskSaveCycleAndValidationListener._a(executer);
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 06.03.2020
	 *
	 * @param executer
	 * @return
	 * @throws myException
	 */
	public RB_ComponentMapCollector _clearBeforeMaskSaveCycleAndValidationListener() throws myException {
		this.beforeMaskSaveCycleAndValidationListener._clear();
		return this;
	}
	
}
