package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class B2_fieldInfoComp_upDetail extends RB_lab implements IF_FieldInfo_Component{

	private IF_Field link_field;
	private IF_Field field;

	public B2_fieldInfoComp_upDetail(IF_Field pLink_field, IF_Field pField) {
		super();

		this.link_field = pLink_field;
		this.field = pField;
	}

	@Override
	public Component get_component(Rec21 r) throws myException {
		Rec21 zielRec = r.get_up_Rec21(link_field);
		if(zielRec != null) {
			this._lwn()._t(zielRec.getUfs(field));
		}
		return this;
	}
}
