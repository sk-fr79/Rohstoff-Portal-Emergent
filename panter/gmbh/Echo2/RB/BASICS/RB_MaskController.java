package panter.gmbh.Echo2.RB.BASICS;

import java.util.Optional;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.AgentsAndValidators.IF_simpleValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RB_MaskController extends RB_MaskControllerMap {

	//hier koennen actionen hinterlegt werden, die maskeninhalte als folge einer aktion auf der maske aendern,
	// beispiel: adresse wurde gesucht und die nachfolgenden felder wurden gesetzt
	private VEK<IF_agentSimple>		actionsMaskValueSetters = new VEK<>();
	

	//WICHTIG: werden in einem zusammenhang mehrere Controller hintereinander benoetigt, dann muessen zuerst alle actionsMaskPreSetters am stueck
	//         durchgefuehrt werden, erst danach die actionsMaskSetters. sonst kann es sein, dass sich die einzelnen Controller die felder nach deaktiveren wieder einschalten !!
	// hier koennen maskenfelder deaktiviert werden, z.b. fuer lager-lager-fuhren gibt es keinen grund , die 
	// felder "suche nach kontrakt" oder "suche nach angebot" aktiv zu lassen
	private VEK<IF_agentSimple>		actionsMaskAppearenceSetters = new VEK<>();
	
	// werden auf in einem controller bestimmte settings vorgenommen, sollten diese vorher resettet werden.
	// hier koennen diese resets durchgefuehrt werden
	private VEK<IF_agentSimple>		actionsMaskAppearencePreSetters = new VEK<>();
	
	
	private VEK<IF_simpleValidator>  validators = new VEK<>();
	
	public RB_MaskController _addValidationInfoMsg(MyString s) {
		this.validators._a(()-> {
			return bibMSG.getNewMV()._add(new MyE2_Info_Message(s));
		});
		return this;
	}

	public RB_MaskController _addValidationAlarmMsg(MyString s) {
		this.validators._a(()-> {
			return bibMSG.getNewMV()._add(new MyE2_Alarm_Message(s));
		});
		return this;
	}
	
	public RB_MaskController _addValidationWarningMsg(MyString s) {
		this.validators._a(()-> {
			return bibMSG.getNewMV()._add(new MyE2_Warning_Message(s));
		});
		return this;
	}
	
	
	
	
	
	/**
	 * @param p_component
	 * @throws myException
	 */
	public RB_MaskController(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	/**
	 * @param p_componentMap
	 * @throws myException
	 */
	public RB_MaskController(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}

	/**
	 * @param p_componentMapCollector
	 * @throws myException
	 */
	public RB_MaskController(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}


	/**
	 * 
	 * @param p_dataObjectsCollector
	 * @throws myException
	 */
	public RB_MaskController(RB_DataobjectsCollector p_dataObjectsCollector) throws myException {
		super(p_dataObjectsCollector.rb_ComponentMapCollector_ThisBelongsTo());
	}
	
	
	
	

	

	/**
	 * Neuer satz con konstructoren, die fehler beim init intrinsch behandeln
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public RB_MaskController(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
		super();
		try {
			this._init(p_component);
		} catch ( Exception e) {
			mvForErrors._add(e);
		}
	}

	/**
	 * Neuer satz con konstructoren, die fehler beim init intrinsch behandeln
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public RB_MaskController(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super();
		try {
			this._init(p_componentMap);
		} catch ( Exception e) {
			mvForErrors._add(e);
		}
	}

	/**
	 * Neuer satz con konstructoren, die fehler beim init intrinsch behandeln
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public RB_MaskController(RB_ComponentMapCollector p_componentMapCollector, MyE2_MessageVector mvForErrors) {
		super();
		try {
			this._init(p_componentMapCollector);
		} catch ( Exception e) {
			mvForErrors._add(e);
		}
	}


	/**
	 * Neuer satz con konstructoren, die fehler beim init intrinsch behandeln
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param p_component
	 * @param mvForErrors
	 */
	public RB_MaskController(RB_DataobjectsCollector p_dataObjectsCollector, MyE2_MessageVector mvForErrors) {
		super();
		try {
			this._init(p_dataObjectsCollector.rb_ComponentMapCollector_ThisBelongsTo());
		} catch ( Exception e) {
			mvForErrors._add(e);
		}
	}
	

	
	
	//------------
	
	
	
	
	
	
	
	
	/**
	 * @param p_component
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskController(IF_RB_Component p_component, boolean immediateBuild) throws myException {
		super(p_component, immediateBuild);
	}

	/**
	 * @param p_componentMap
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskController(RB_ComponentMap p_componentMap, boolean immediateBuild) throws myException {
		super(p_componentMap, immediateBuild);
	}

	/**
	 * @param p_componentMapCollector
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskController(RB_ComponentMapCollector p_componentMapCollector, boolean immediateBuild) throws myException {
		super(p_componentMapCollector, immediateBuild);
	}


	/**
	 * 
	 * @param p_dataObjectsCollector
	 * @param immediateBuild (wenn false, muss vor benutzung noch ._refresh() erfolgen)
	 * @throws myException
	 */
	public RB_MaskController(RB_DataobjectsCollector p_dataObjectsCollector, boolean immediateBuild) throws myException {
		super(p_dataObjectsCollector.rb_ComponentMapCollector_ThisBelongsTo(), immediateBuild);
	}
	

	
	
	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall) 	throws myException {
		return null;
	}

	

	/**
	 * 
	 * @author martin
	 * @date 12.03.2019
	 * fuehrt aenderungen innerhalb der maskenwerte aus
	 * @return  
	 * @throws myException
	 */
	public RB_MaskController _executeMaskValueSetters() throws myException {
		if (this.isEditable()) {
			for (IF_agentSimple a: this.actionsMaskValueSetters) {
				a.genActionAgent().executeAgentCode(null);
			}
		}
		return this;
	}
	
	
	public RB_MaskController _executeMaskValueSetters(MyE2_MessageVector mv) throws myException {
		if (mv==null) {
			throw new myException("MessageVector MUST NOT BE null: < 048cc50e-7e37-11e9-8f9e-2a86e4085a59>");
		}
		
		MyE2_MessageVector mvSave=bibMSG.newMV()._add(bibMSG.MV());
		bibMSG.MV().clear();

		if (this.isEditable()) {
			for (IF_agentSimple a: this.actionsMaskValueSetters) {
				a.genActionAgent().executeAgentCode(null);
			}
		}
		
		mv._add(bibMSG.MV());
		bibMSG.MV()._clear()._add(mvSave);

		return this;
	}
	

	
	
	/**
	 * 
	 * @author martin
	 * @date 12.03.2019
	 * fuehrt aenderungen innerhalb der maskenwerte aus (hier auch bei maskenanzeigen, nicht editierbar)
	 * @return  
	 * @throws myException
	 */
	public RB_MaskController _executeMaskValueSettersForce() throws myException {
		for (IF_agentSimple a: this.actionsMaskValueSetters) {
			a.genActionAgent().executeAgentCode(null);
		}
		return this;
	}
	
	
	public RB_MaskController _executeMaskValueSettersForce(MyE2_MessageVector mv) throws myException {
		if (mv==null) {
			throw new myException("MessageVector MUST NOT BE null: < 048cc50e-7e37-11e9-8f9e-2a86e4085a59>");
		}
		
		MyE2_MessageVector mvSave=bibMSG.newMV()._add(bibMSG.MV());
		bibMSG.MV().clear();

		
		for (IF_agentSimple a: this.actionsMaskValueSetters) {
			a.genActionAgent().executeAgentCode(null);
		}
		
		mv._add(bibMSG.MV());
		bibMSG.MV()._clear()._add(mvSave);

		return this;
	}
	

	
	
	/**
	 * 
	 * @author martin
	 * @date 12.03.2019
	 * fuehrt maskeneinstellungen au, aber keine aenderungen an den feldwerten
	 * @return  
	 * @throws myException
	 */
	public RB_MaskController _executeMaskAppearenceSetters() throws myException {
		if (this.isEditable()) {
			for (IF_agentSimple a: this.actionsMaskAppearenceSetters) {
				a.genActionAgent().executeAgentCode(null);
			}
		}
		return this;
	}
	
	public RB_MaskController _executeMaskAppearenceSetters(MyE2_MessageVector mv) throws myException {
		if (mv==null) {
			throw new myException("MessageVector MUST NOT BE null: < 048cc50e-7e37-11e9-8f9e-2a86e4085a59>");
		}
		
		MyE2_MessageVector mvSave=bibMSG.newMV()._add(bibMSG.MV());
		bibMSG.MV().clear();
		if (this.isEditable()) {
			for (IF_agentSimple a: this.actionsMaskAppearenceSetters) {
				a.genActionAgent().executeAgentCode(null);
			}
		}
		
		mv._add(bibMSG.MV());
		bibMSG.MV()._clear()._add(mvSave);
		
		return this;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 12.03.2019
	 * fuehrt maskeneinstellungen au, aber keine aenderungen an den feldwerten
	 * @return  
	 * @throws myException
	 */
	public RB_MaskController _executeMaskAppearencePreSetters() throws myException {
		if (this.isEditable()) {
			for (IF_agentSimple a: this.actionsMaskAppearencePreSetters) {
				a.genActionAgent().executeAgentCode(null);
			}
		}
		return this;
	}
	
	public RB_MaskController _executeMaskAppearencePreSetters(MyE2_MessageVector mv) throws myException {
		
		if (mv==null) {
			throw new myException("MessageVector MUST NOT BE null: < 048cc50e-7e37-11e9-8f9e-2a86e4085a59>");
		}
		
		MyE2_MessageVector mvSave=bibMSG.newMV()._add(bibMSG.MV());
		bibMSG.MV().clear();

		if (this.isEditable()) {
			for (IF_agentSimple a: this.actionsMaskAppearencePreSetters) {
				a.genActionAgent().executeAgentCode(null);
			}
		}
		
		mv._add(bibMSG.MV());
		bibMSG.MV()._clear()._add(mvSave);
	
		return this;
	}
	

	
	/**
	 * 
	 * @author martin
	 * @date 12.03.2019
	 * fuehrt maskeneinstellungen au, aber keine aenderungen an den feldwerten (hier auch bei maskenanzeigen, nicht editierbar)
	 * @return  
	 * @throws myException
	 */
	public RB_MaskController _executeMaskAppearenceSettersForce() throws myException {
		for (IF_agentSimple a: this.actionsMaskAppearenceSetters) {
			a.genActionAgent().executeAgentCode(null);
		}
		return this;
	}

	
	public RB_MaskController _executeMaskAppearenceSettersForce(MyE2_MessageVector mv) throws myException {
		if (mv==null) {
			throw new myException("MessageVector MUST NOT BE null: < 048cc50e-7e37-11e9-8f9e-2a86e4085a59>");
		}
		
		MyE2_MessageVector mvSave=bibMSG.newMV()._add(bibMSG.MV());
		bibMSG.MV().clear();
		
		for (IF_agentSimple a: this.actionsMaskAppearenceSetters) {
			a.genActionAgent().executeAgentCode(null);
		}
		
		mv._add(bibMSG.MV());
		bibMSG.MV()._clear()._add(mvSave);
		
		return this;
	}

	
	
	
	public RB_MaskController _executeValidators(MyE2_MessageVector mv) {
		if (mv == null) {
			mv = bibMSG.getNewMV();
		}
		
		for (IF_simpleValidator v: this.validators) {
			try {
				mv._add(v.simpleValid());
			} catch (myException e) {
				mv._add(e.get_ErrorMessage());
			}
		}
		
		return this;
	}
	
	
	public RB_MaskController _executeValidators(MyE2_MessageVector mv, boolean onlyWarning) {
		if (mv == null) {
			mv = bibMSG.getNewMV();
		}
		
		for (IF_simpleValidator v: this.validators) {
			try {
				mv._add(v.simpleValid()._changeErrorsToWarnings());
			} catch (myException e) {
				mv._add(e.get_ErrorMessage());
			}
		}
		
		return this;
	}
	
	
	public RB_MaskController _executeValidators() {
		for (IF_simpleValidator v: this.validators) {
			try {
				bibMSG.MV()._add(v.simpleValid());
			} catch (myException e) {
				bibMSG.MV()._add(e.get_ErrorMessage());
			}
		}
		return this;
	}
	
	
	
	
	
	public VEK<IF_agentSimple> getActionsFieldValueChangers() {
		return actionsMaskValueSetters;
	}

	
	public VEK<IF_agentSimple> getActionsMaskSetters() {
		return actionsMaskAppearenceSetters;
	}

	public VEK<IF_agentSimple> getActionsMaskPreSetters() {
		return actionsMaskAppearencePreSetters;
	}

	public VEK<IF_simpleValidator> getValidators() {
		return validators;
	}
	
	
	/**
	 * can be overwritten
	 * @author martin
	 * @date 03.04.2019
	 *
	 * @return
	 */
	public boolean isEditable() {
		return this.get_ComponentMapCollector().getFirstComponentMap().getStatus().isStatusCanBeSaved();
	}
	
	public boolean isNew() {
		return this.get_ComponentMapCollector().getFirstComponentMap().getStatus().isStatusNew();
	}
	
	public MASK_STATUS getMaskStatus() {
		return this.get_ComponentMapCollector().getFirstComponentMap().getStatus();
	}
	
	
	/**
	 * kurzschreibweise fuer einfuegen eines paares getActionsMaskPreSetters,getActionsMaskSetters fuer eine feldinaktivierung
	 * @author martin
	 * @date 27.03.2019
	 *
	 * @param mask
	 * @param field
	 * @return
	 */
	public RB_MaskController _registerDisabler(RB_KM mask, RB_KF ... fields) {
		this.getActionsMaskSetters()._a(()	->	{
			for (RB_KF f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(false);
			}
		});
		return this;
	}

	
	public RB_MaskController _registerClearer(RB_KM mask, MyE2_String meldungWhenClear, IF_Field... fields) throws myException {
		this.getActionsFieldValueChangers()._a(()	->	{
			for (IF_Field f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(false);
				if (S.isFull(this.get_liveVal(mask, f))) {
					this.setMaskVal(mask, f.fk(), "", false);
					if (S.isFull(meldungWhenClear)) {
						bibMSG.MV()._addInfo(meldungWhenClear);
					}
				}
			}
		});
		return this;
	}
	
	public RB_MaskController _registerClearer(RB_KM mask, MyE2_String meldungWhenClear, RB_KF... fields) {
		this.getActionsFieldValueChangers()._a(()	->	{
			for (RB_KF f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(false);
				if (S.isFull(this.get_liveVal(mask, f))) {
					this.setMaskVal(mask, f, "", false);
					if (S.isFull(meldungWhenClear)) {
						bibMSG.MV()._addInfo(meldungWhenClear);
					}
				}
			}
		});
		return this;
	}

	
	
	public RB_MaskController _registerDisabler(RB_KM mask, IF_Field ... fields) throws myException {
		this.getActionsMaskSetters()._a(()	->	{
			for (IF_Field f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(false);
			}
		});
		return this;
	}	
	
	public RB_MaskController _registerEnabler(RB_KM mask, RB_KF ... fields) {
		this.getActionsMaskPreSetters()._a(()->	{	
			for (RB_KF f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(true);
			}
		});
		return this;
	}

	public RB_MaskController _registerEnabler(RB_KM mask, IF_Field ... fields) throws myException {
		this.getActionsMaskPreSetters()._a(()->	{	
			for (IF_Field f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(true);
			}
		});
		return this;
	}	
	
	public RB_MaskController _registerValueChangers(IF_agentSimple e) {
		this.getActionsFieldValueChangers()._a(e);
		return this;
	}

	
	public RB_MaskController  _registerValidator(IF_simpleValidator v) {
		this.getValidators()._a(v);
		return this;
	}


	/**
	 * 
	 * @author martin
	 * @date 24.05.2019
	 *
	 * @return validator which wraps alle registered IF_simpleValidators
	 */
	public XX_ActionValidator_NG getAsValidator() {
		return new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				MyE2_MessageVector mv = bibMSG.getNewMV();
				for (IF_simpleValidator sv: getValidators()) {
					mv._add(sv.simpleValid());
				}
				return mv;
			}
		};
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 24.05.2019
	 *
	 * @return validator which wraps alle registered IF_simpleValidators
	 */
	public XX_ActionValidator_NG getAsValidatorMaxWarnings() {
		return new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				MyE2_MessageVector mv = bibMSG.getNewMV();
				for (IF_simpleValidator sv: getValidators()) {
					mv._add(sv.simpleValid());
				}
				
				return mv._changeErrorsToWarnings();
			}
		};
	}
	
	
	
	
	/**
	 * eine reihe von feldern eine maske enabled/disabled setzen
	 * @author martin
	 * @date 13.01.2020
	 *
	 * @param enabled
	 * @param mask
	 * @param fields
	 * @return
	 * @throws myException
	 */
	public RB_MaskController _setEnabledForEdit(boolean enabled, RB_KM mask, IF_Field ... fields) throws myException {
		try {
			for (IF_Field f: fields) {
				getComponent(mask, f).set_bEnabled_For_Edit(enabled);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage()+"<66f81f28-3619-11ea-978f-2e728ce88125>");
		}
		return this;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param maskKey
	 * @param fieldKey
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public RB_MaskController _setMaskValue(RB_KM maskKey, RB_KF fieldKey, String value) throws Exception {
		RB_MaskControllerField sti = this.get_MaskControllerField(maskKey, fieldKey);
		if (sti != null) {
			try {
				sti.get_rec2_field().get_field().generate_MetaFieldDef().getRaw(value);   //fall der wert nicht passt, exception
				sti.set_maskVal(value, bibMSG.getNewMV());
			} catch (myException e) {
				//fehler bei der konvertierung
				sti.set_maskVal("", bibMSG.getNewMV());
			}
		} else {
			throw new myException(this,"setMaskVal: Component :"+maskKey.get_HASHKEY()+"/"+fieldKey.get_HASHKEY()+" was not found in mask !!");
		}
		return this;
	}

	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param maskKey
	 * @param field
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public RB_MaskController _setMaskValue(RB_KM maskKey, IF_Field field, String value) throws Exception {
		return this._setMaskValue(maskKey, field.fk(), value);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 19.05.2020
	 *
	 * @param maskKeySource
	 * @param fieldSource
	 * @param maskKeyTarget
	 * @param fieldTarget
	 * @return
	 * @throws Exception
	 */
	public RB_MaskController _copyMaskValue(RB_KM maskKeySource, RB_KF fieldSource, RB_KM maskKeyTarget, RB_KF fieldTarget) throws Exception {
		return this._setMaskValue(maskKeyTarget, fieldTarget, this.get_liveVal(maskKeySource, fieldSource));
	}
	
	/**
	 * 
	 * @author martin
	 * @date 19.05.2020
	 *
	 * @param maskKeySource
	 * @param fieldSource
	 * @param maskKeyTarget
	 * @param fieldTarget
	 * @return
	 * @throws Exception
	 */
	public RB_MaskController _copyMaskValue(RB_KM maskKeySource, IF_Field fieldSource, RB_KM maskKeyTarget, IF_Field fieldTarget) throws Exception {
		return this._setMaskValue(maskKeyTarget, fieldTarget, this.get_liveVal(maskKeySource, fieldSource));
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 11.03.2020
	 *
	 * @param maskKey
	 * @param field
	 * @return
	 */
	public RB_MaskController _setFocus(RB_KM maskKey, IF_Field field) {
		Optional<IF_RB_Component> opt = this.getCompIfRbComponentOpt(maskKey,field);
		opt.ifPresent((e)-> {
			e._setFocus();
		});

		return this;
	}
	
	
	
	public RB_MaskController _setHighlightColorInGridWhenNull(RB_KM maskKey,  Color higlightColor, IF_Field ... fields) {
		if (fields!=null) {
			for (IF_Field field: fields) {
				try {
					this._setHighlightColorInGridWhenNull(maskKey,higlightColor,field.fk());
				} catch (myException e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}
	
	
	
	public RB_MaskController _setHighlightColorInGridWhenNull(RB_KM maskKey, Color higlightColor, RB_KF ... fields) {
		if (fields!=null) {
			for (RB_KF field: fields) {
				Optional<IF_RB_Component> opt = this.getCompIfRbComponentOpt(maskKey,field);
				opt.ifPresent((e)-> {
					try {
						if (this.getValueJustInTime(maskKey, field)==null) {
							e.setHighlightColorInGrid(higlightColor,RB_Grid.class);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
			}
		}
		return this;
	}

	
	public RB_MaskController _setHighlightColorInGrid(RB_KM maskKey, Color higlightColor, IF_Field ... fields) {
		if (fields!=null) {
			for (IF_Field field: fields) {
				try {
					this._setHighlightColorInGrid(maskKey,higlightColor,field.fk());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}
	
	
	
	public RB_MaskController _setHighlightColorInGrid(RB_KM maskKey, Color higlightColor, RB_KF ... fields) {
		if (fields!=null) {
			for (RB_KF field: fields) {
				Optional<IF_RB_Component> opt = this.getCompIfRbComponentOpt(maskKey,field);
				opt.ifPresent((e)-> {
					try {
						e.setHighlightColorInGrid(higlightColor,RB_Grid.class);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
			}
		}
		return this;
	}

	
	
	public RB_MaskController _setNormalColorInGrid(RB_KM maskKey, IF_Field ... fields) {
		if (fields!=null) {

			for (IF_Field field: fields) {
				try {
					this._setNormalColorInGrid(maskKey,field.fk());
				} catch (myException e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}
	
	
	
	public RB_MaskController _setNormalColorInGrid(RB_KM maskKey, RB_KF ... fields) {
		if (fields!=null) {
			for (RB_KF field: fields) {
				Optional<IF_RB_Component> opt = this.getCompIfRbComponentOpt(maskKey,field);
				opt.ifPresent((e)-> {
					try {
						if (this.getValueJustInTime(maskKey, field)==null) {
							e.setNormalColorInGrid(RB_Grid.class);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
			}
		}
		return this;
	}

	

	
	public RB_MaskController _setNormalColorInGridAllComponents() {
		
		try {
			RB_ComponentMapCollector collector = this.get_ComponentMapCollector();
			
			for (RB_ComponentMap map: collector) {
				for (IF_RB_Component c: map.getHmRbComponents().values()) {
					c.setNormalColorInGrid(RB_Grid.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}

	
}
