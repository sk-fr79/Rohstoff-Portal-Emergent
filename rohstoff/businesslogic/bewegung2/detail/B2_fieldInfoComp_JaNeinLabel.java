/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 25.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 25.03.2019
 *
 */
public class B2_fieldInfoComp_JaNeinLabel extends RB_lab implements IF_FieldInfo_Component {

	private IF_Field field = null;
	
	public B2_fieldInfoComp_JaNeinLabel(IF_Field p_field) {
		super();
		
		this.field = p_field;
	}
	
	
	@Override
	public Component get_component(Rec21 r) throws myException {
		String wert = r.get_ufs_dbVal(this.field, "N");
		if(wert.equals("Y")) {
			this._t("Ja") ;
		}else {
			this._t("Nein");
		}
		return this.c();
	}

}
