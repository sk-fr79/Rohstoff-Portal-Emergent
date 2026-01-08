package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.E2_calendar.E2_text_with_date_popup;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public class SF_inputComponentContainer extends MyE2_Grid {
	
	private IF_Field_ext  				field_ext = null;
	private Component  					compInput = null;
	
	private SF_orLine 					or_line = null;
	
	//--- diverse eingaben
	private MyE2_CheckBox        		cb_value = new MyE2_CheckBox();
	private MyE2_TextField        		tf_value = new MyE2_TextField("",280,4000);
	private E2_text_with_date_popup    	date_field = new E2_text_with_date_popup();

	public SF_inputComponentContainer(SF_orLine p_or_line) {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.setColumnWidth(0, new Extent(SF_Filter_CONST.BREITE_INPUTBLOCK-5));
		this.or_line = p_or_line;

		this.add_raw(new MyE2_Label(""));
		this.add_raw(new MyE2_Label(""));
		this.add_raw(new MyE2_Label(""));
	}

	public SF_inputComponentContainer set_field(IF_Field_ext p_field) throws myException {
		this.removeAll();
		
		this.cb_value.setSelected(false);
		this.tf_value.setText("");

		this.field_ext=p_field;
		
		this.removeAll();
		if (this.field_ext==null) {
			this.add_raw(new MyE2_Label(""));
			this.add_raw(new MyE2_Label(""));
			this.add_raw(new MyE2_Label(""));
		} else {
			MyMetaFieldDEF md = this.field_ext.field.generate_MetaFieldDef();
			
			if (md.is_boolean_single_char()) {
				this.compInput = this.cb_value;
			} else if (md.get_bIsDateType()){
				this.compInput = this.date_field;
			} else {
				this.compInput = this.tf_value;
			}
			this.add_raw(this.compInput, MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,0,0)));
		}
		
		
		return this;
	}


	
	public String get_value() throws myException {
		if (this.field_ext!=null) {
			MyMetaFieldDEF md = this.field_ext.field.generate_MetaFieldDef();
			
			if (md.is_boolean_single_char()) {
				return this.cb_value.isSelected()?"Y":"N";
			} else if (md.get_bIsDateType()){
				return S.NN(this.date_field.get_tf_date().getText()).trim();
			} else {
				return this.tf_value.getText().trim();
			}
		} else {
			return null;
		}
	}
	
	
	
	public void set_value(String c_text) throws myException {
		if (this.field_ext !=null) {
			MyMetaFieldDEF md = this.field_ext.field.generate_MetaFieldDef();
			
			if (md.is_boolean_single_char()) {
				 this.cb_value.setSelected(S.NN(c_text).toUpperCase().equals("Y"));
			} else if (md.get_bIsDateType()){
				this.date_field.get_tf_date().setText(c_text);
			} else {
				this.tf_value.setText(c_text);
			}
		}
	}


	

	public SF_orLine get_or_line() {
		return or_line;
	}

	public MyE2_CheckBox get_cb_value() {
		return cb_value;
	}

	public MyE2_TextField get_tf_value() {
		return tf_value;
	}
	
	public E2_text_with_date_popup get_date_field() {
		return date_field;
	}

	public Component get_compInput() {
		return compInput;
	}
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException 	{
		this.cb_value.set_bEnabled_For_Edit(enabled);
		this.tf_value.set_bEnabled_For_Edit(enabled);
		this.date_field.set_bEnabled_For_Edit(enabled);
	}

	public IF_Field_ext get_if_field() {
		return field_ext;
	}


}
