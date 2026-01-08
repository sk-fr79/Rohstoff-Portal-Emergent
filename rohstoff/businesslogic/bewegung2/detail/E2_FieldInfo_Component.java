/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 27.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 27.03.2019
 *
 */
public class E2_FieldInfo_Component {

	private String 					beschriftung 	= "";
	private IF_Field 				dbField 		= null;
	private IF_FieldInfo_Component 	component1 		= null;
	private IF_FieldInfo_Component 	component2 		= null;
	
	public E2_FieldInfo_Component(String str_beschriftung, IF_Field p_field, IF_FieldInfo_Component c1, IF_FieldInfo_Component c2) throws myException{
		super();
		this.beschriftung = str_beschriftung;
		this.dbField = p_field;
		this.component1 = c1;
		this.component2 = c2;
	}

	public E2_FieldInfo_Component(String str_beschriftung, IF_Field p_field, IF_FieldInfo_Component c2) throws myException{
		super();
		this.beschriftung = str_beschriftung;
		this.dbField = p_field;
		this.component2 = c2;
	}
	
	public E2_FieldInfo_Component(String str_beschriftung, IF_Field p_field) throws myException{
		super();
		this.beschriftung = str_beschriftung;
		this.dbField = p_field;
	}
	
	public E2_FieldInfo_Component(IF_Field p_field) throws myException{
		super();
		this.beschriftung = p_field.fieldName().toLowerCase();
		this.dbField = p_field;
	}
	
	public String getBeschriftung() {
		return beschriftung;
	}

	public IF_Field getIFField() {
		return dbField;
	}

	public IF_FieldInfo_Component getComponent1() {
		return component1;
	}

	public IF_FieldInfo_Component getComponent2() {
		return component2;
	}
	
	
}
