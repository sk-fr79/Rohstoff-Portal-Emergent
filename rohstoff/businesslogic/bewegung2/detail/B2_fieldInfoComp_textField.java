/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 25.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;


public class B2_fieldInfoComp_textField extends RB_TextField implements IF_FieldInfo_Component {

	private IF_Field field;
	
	public B2_fieldInfoComp_textField(IF_Field p_field) {
		super();
	}


	public B2_fieldInfoComp_textField(IF_Field p_field, int i_width) {
		super(i_width);
	}

	public B2_fieldInfoComp_textField(IF_Field p_field, int i_width, int i_max_input_size) {
		super(i_width, i_max_input_size);
	}


	@Override
	public Component get_component(Rec21 r) throws myException {
		if(field != null) {
			this.rb_set_db_value_manual(r.get_ufs_dbVal(field, ""));
		}
		return this.c();
	}

}
