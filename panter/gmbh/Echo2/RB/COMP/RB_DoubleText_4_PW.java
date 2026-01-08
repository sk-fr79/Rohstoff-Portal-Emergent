package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_PasswordField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_DoubleText_4_PW extends MyE2_Grid implements IF_RB_Component_Savable{

	private RB_KF Key = null;

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	private TextField          tf_field1 = null;
	private TextField          tf_field2 = null;
	
	private MyE2_PasswordField mskPasswordField1 = new MyE2_PasswordField();
	private MyE2_PasswordField mskPasswordField2 = new MyE2_PasswordField();

	private MyE2_TextField unmskPasswordField1 = new MyE2_TextField();
	private MyE2_TextField unmskPasswordField2 = new MyE2_TextField();
	
	private int passwordLength;

	private boolean maskedField = true;

	private RB_DoubleText_4_PW_complexity.SecurityLevel securityLevel;

	
	public abstract void build_component_4_mask(TextField tf1, TextField tf2) throws myException; 
	
	
	/**
	 * Constructor with fixed password lenght at 7;
	 * @param field
	 * @param iWidthInPixel
	 * @throws myException
	 */
	public RB_DoubleText_4_PW(IF_Field field, RB_DoubleText_4_PW_complexity.SecurityLevel e_securityLevel, int iWidthInPixel) throws myException {
		super(MyE2_Grid.STYLE_GRID_NO_BORDER());
		this._init(field, 7, e_securityLevel, iWidthInPixel, true);
	}


	/**
	 * 
	 * @param field
	 * @param minPasswordLength
	 * @param e_securityLevel
	 * @param iWidthInPixel
	 * @throws myException 
	 */
	public RB_DoubleText_4_PW(IF_Field field, int minPasswordLength, RB_DoubleText_4_PW_complexity.SecurityLevel e_securityLevel, int iWidthInPixel) throws myException{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER());
		this._init(field, minPasswordLength, e_securityLevel, iWidthInPixel, true);
	}

	/**
	 * Constructor with variable password lenght
	 * @param field
	 * @param minPasswordLength
	 * @param iWidthInPixel
	 * @throws myException
	 */
	public RB_DoubleText_4_PW(IF_Field field, int minPasswordLength, RB_DoubleText_4_PW_complexity.SecurityLevel e_securityLevel, int iWidthInPixel, boolean b_isMasked) throws myException{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER());
		this._init(field, minPasswordLength, e_securityLevel, iWidthInPixel, b_isMasked);
	}

	
	private void _init(IF_Field field, int minPasswordLength, RB_DoubleText_4_PW_complexity.SecurityLevel e_securityLevel, int iWidthInPixel, boolean b_isMasked) throws myException {
		this.setWidth(new Extent(iWidthInPixel));
		this.passwordLength = minPasswordLength;
		this.securityLevel = e_securityLevel;
		
		setMaskedField(b_isMasked);
		
		mskPasswordField1.setHeight(new Extent(18));
		mskPasswordField1.setWidth(new Extent(80));
		
		mskPasswordField2.setWidth(new Extent(80));
		mskPasswordField2.setHeight(new Extent(18));

		unmskPasswordField1.setHeight(new Extent(18));
		unmskPasswordField1.setWidth(new Extent(80));
		
		unmskPasswordField2.setHeight(new Extent(18));
		unmskPasswordField2.setWidth(new Extent(80));
		
		rebuildFields();
	}
	
	
	public void rebuildFields() throws myException{
		removeAll();
		
		if(maskedField){
			this.tf_field1 = mskPasswordField1;
			this.tf_field2 = mskPasswordField2;
		}else{
			this.tf_field1 = unmskPasswordField1;
			this.tf_field2 = unmskPasswordField2;
		}
		
		this.build_component_4_mask(this.tf_field1, this.tf_field2);
		
		
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject)
			throws myException {
		String passwordOnComponentLoad = ((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME());

		if (passwordOnComponentLoad==null) {
			mskPasswordField1.setText("");
			unmskPasswordField1.setText("");
		} else {
			mskPasswordField1.setText(passwordOnComponentLoad);
			mskPasswordField2.setText(passwordOnComponentLoad);
			unmskPasswordField1.setText(passwordOnComponentLoad);
			unmskPasswordField2.setText(passwordOnComponentLoad);
		}

	}

	@Override
	public void rb_set_db_value_manual(String valueFormated)	throws myException {

	}

	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;

	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		vVALIDATORS_4_INPUT.add(new LengthValidator());
		vVALIDATORS_4_INPUT.add(new SeizureValidator());
		vVALIDATORS_4_INPUT.add(new ComplexityValidator());
		return vVALIDATORS_4_INPUT;
	}

	@Override
	public void mark_Neutral() throws myException {
		this.mskPasswordField1.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.mskPasswordField2.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.unmskPasswordField1.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.unmskPasswordField2.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.mskPasswordField1.setBackground(new E2_ColorEditBackground());
		this.mskPasswordField2.setBackground(new E2_ColorEditBackground());
		this.unmskPasswordField1.setBackground(new E2_ColorEditBackground());
		this.unmskPasswordField2.setBackground(new E2_ColorEditBackground());

	}

	@Override
	public void mark_MustField() throws myException {
		this.mskPasswordField1.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		this.mskPasswordField2.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		this.unmskPasswordField1.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		this.unmskPasswordField2.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException {
		this.mskPasswordField1.setBackground(new E2_ColorGray(230));
		this.mskPasswordField2.setBackground(new E2_ColorGray(230));
		this.unmskPasswordField1.setBackground(new E2_ColorGray(230));
		this.unmskPasswordField2.setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.mskPasswordField1.setBackground(new E2_ColorHelpBackground());
		this.mskPasswordField2.setBackground(new E2_ColorHelpBackground());
		this.unmskPasswordField1.setBackground(new E2_ColorHelpBackground());
		this.unmskPasswordField2.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.mskPasswordField1.setAlignment(align);
		this.mskPasswordField2.setAlignment(align);
		this.unmskPasswordField1.setAlignment(align);
		this.unmskPasswordField2.setAlignment(align);
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return getPassword();
	}

	public void setPassword(String p_password){
		if(isMaskedField()){

		}
	}

	public String getPassword(){
		if(isMaskedField()){
			return mskPasswordField1.getText();
		}else 
			return unmskPasswordField1.getText();
	}

	public int getPasswordLength() {
		return passwordLength;
	}

	public void setPasswordLength(int passwordLenght) {
		this.passwordLength = passwordLenght;
	}

	public boolean isMaskedField() {
		return maskedField;
	}

	public void setMaskedField(boolean maskedField) throws myException {
		this.maskedField = maskedField;
		rebuildFields();
	}



	private class ComplexityValidator extends RB_Validator_Component{

		@Override
		public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
			String pwd = getPassword();


			boolean isConditionTrue = false;
			if(!pwd.isEmpty()){
				String msgPart1 = "Das Passwort entspricht nicht den Komplexit‰tsvorgaben. Bitte geben Sie ein Passwort ein mit: ";
				String msgPart2 = "";
				switch(securityLevel){
				case LOW:
					isConditionTrue = RB_DoubleText_4_PW_complexity.isNotSecure(pwd);
					msgPart2 = "einer L‰nge von" + passwordLength +" Zeichen";
					break;
				case MEDIUM:
					isConditionTrue = RB_DoubleText_4_PW_complexity.isMediumSecure(pwd);
					msgPart2 = "mindestens einem Groﬂbuchstaben oder einer Zahl";
					break;
				case STRONG:
					isConditionTrue = RB_DoubleText_4_PW_complexity.isStrongSecured(pwd);
					msgPart2 = "mindestens einem Groﬂbuchstaben und einer Zahl.";
					break;
				case VERY_STRONG:
					isConditionTrue = RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwd);
					msgPart2 = "mindestens einem Groﬂbuchstaben, einer Zahl und einem Sonderzeichen ( &~#|`-_)('/?,;:. )";
					break;
				default:
					return new MyE2_MessageVector();
				}
				if(! isConditionTrue){
					return new MyE2_MessageVector(new MyE2_Alarm_Message(msgPart1 + msgPart2));
				}
			}

			return new MyE2_MessageVector();
		}
	}


	private class LengthValidator extends RB_Validator_Component{

		@Override
		public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
			String password = getPassword();
			if(password.length()<passwordLength && password.length()>0){

				return new MyE2_MessageVector(new MyE2_Alarm_Message("Passwort ist zu kurz"));
			}
			return new MyE2_MessageVector();
		}

	}

	private class SeizureValidator extends RB_Validator_Component{

		@Override
		public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
			String pwd1 = "";
			String pwd2 = "";

			pwd1 = tf_field1.getText();
			pwd2 = tf_field2.getText();

			if(pwd1.equals(pwd2)){
				return new MyE2_MessageVector();
			}else 
				return new MyE2_MessageVector(new MyE2_Alarm_Message("Passwort und Passwortwiederholung sind unterschiedlich. Bitte korrigieren!"));
		}
	}

	public MyE2_PasswordField get_mskPasswordField1() {
		return mskPasswordField1;
	}


	public MyE2_PasswordField get_mskPasswordField2() {
		return mskPasswordField2;
	}


	public MyE2_TextField get_unmskPasswordField1() {
		return unmskPasswordField1;
	}


	public MyE2_TextField get_unmskPasswordField2() {
		return unmskPasswordField2;
	}

	public RB_DoubleText_4_PW set_input_field_length( int i_width_pixel) throws myException {
		this.mskPasswordField1.set_iWidthPixel(i_width_pixel);
		this.mskPasswordField2.set_iWidthPixel(i_width_pixel);

		this.unmskPasswordField1.set_iWidthPixel(i_width_pixel);
		this.unmskPasswordField2.set_iWidthPixel(i_width_pixel);
		
		return this;
	}
	
}


