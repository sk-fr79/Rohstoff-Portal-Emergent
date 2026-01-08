package panter.gmbh.Echo2.RB.BASICS;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.IF.IF_RB_Part;
import panter.gmbh.Echo2.RB.VALID.RB_DynamicRule;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * meta-informationen zu den einzelnen komponenten auf der maske
 * @author martin
 *
 */
public class RB_SurfaceSettings implements IF_RB_Part<RB_PreSettingsContainer>{

	private RB_KF 		Key = null;

	
	private boolean 		Enabled = 	true;
	private boolean 		MustField = false;
	private boolean 		GetsFocus = true;
	
	private String      	DefaultValue = null;
	
	//parameter von den DynamicRules
	private RB_DynamicRule 	DynamicRule = null;
	
	
	
	
	private RB__CONST.ALIGN_HORIZONTAL AlignHorizontal = RB__CONST.ALIGN_HORIZONTAL.LEFT;
	private RB__CONST.ALIGN_VERTICAL   AlignVertical =   RB__CONST.ALIGN_VERTICAL.MID;
	
	private RB_PreSettingsContainer   	rb_SurfaceSettingsContainerThisBelongsTo = null;
	
	/*
	 * Beschreibung des feldes fuer fehlermeldungen o.ä.
	 */
	private RB__Mask_Description       Description = null;
	
	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  										my_key_in_collection = null;
	
	
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	public RB_SurfaceSettings() {
		super();
	}

	
	
	public boolean is_EnabledComplete() {
		boolean enabledDyn = (this.DynamicRule==null)?true:this.DynamicRule.is_AllowEdit();
		return Enabled&&enabledDyn;
	}
	public void set_Enabled(boolean enabled) {
		Enabled = enabled;
	}
	public boolean is_MustFieldComplete() {
		boolean bMustFieldDyn = (this.DynamicRule==null)?false:(!this.DynamicRule.is_AllowEmpty());
		return MustField||bMustFieldDyn;
	}
	public void set_MustField(boolean mustField) {
		MustField = mustField;
	}
	public boolean is_GetsFocus() {
		return GetsFocus&&this.is_EnabledComplete();
	}
	public void set_GetsFocus(boolean getsFocus) {
		GetsFocus = getsFocus;
	}
	public RB__Mask_Description get_Description() {
		return Description;
	}
	public void set_Description(RB__Mask_Description description) {
		Description = description;
	}
	
	public void set_Description(MyE2_String description, MyE2_String position) throws myException {
		this.Description=new RB__Mask_Description(this.Key, description, position);
	}
	public void set_Description(MyE2_String description) throws myException {
		this.Description=new RB__Mask_Description(this.Key, description, null);
	}
	
	
	public RB__CONST.ALIGN_HORIZONTAL get_AlignHorizontal() {
		return AlignHorizontal;
	}
	public void set_AlignHorizontal(RB__CONST.ALIGN_HORIZONTAL alignHorizontal) {
		AlignHorizontal = alignHorizontal;
	}
	public RB__CONST.ALIGN_VERTICAL get_AlignVertical() {
		return AlignVertical;
	}
	
	public Alignment  get_Alignment()  {
		return new Alignment(this.AlignHorizontal.get_EchoConst(), this.AlignVertical.get_EchoConst());
	}
	
	public void set_AlignVertical(RB__CONST.ALIGN_VERTICAL alignVertical) {
		AlignVertical = alignVertical;
	}
	public RB_KF get_RB_K() {
		return this.Key;
	}
	public void set_RB_K(RB_KF fieldKey) {
		this.Key = fieldKey;
	}


	public RB_DynamicRule get_rb_DynamicRule() {
		return DynamicRule;
	}


	public void set_rb_DynamicRule(RB_DynamicRule dynamicRule) {
		DynamicRule = dynamicRule;
	}


	public String get_rb_DefaultComplete() {
		String defaultDyn = (this.DynamicRule==null)?null:this.DynamicRule.get_Predefined();

		return S.isFull(defaultDyn)?defaultDyn:this.DefaultValue;
	}


	public void set_rb_Default(String c_default) {
		DefaultValue = c_default;
	}


	@Override
	public void rb_set_belongs_to(RB_PreSettingsContainer surfaceSettingsContainer) throws myException {
		this.rb_SurfaceSettingsContainerThisBelongsTo=surfaceSettingsContainer;
	}


	@Override
	public RB_PreSettingsContainer rb_get_belongs_to() throws myException {
		return this.rb_SurfaceSettingsContainerThisBelongsTo;
	}

	
	
	
}
