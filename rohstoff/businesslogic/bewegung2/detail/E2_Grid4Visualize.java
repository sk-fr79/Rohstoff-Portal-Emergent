package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public class E2_Grid4Visualize extends E2_Grid {

	private Rec21 							record_2_visualize 	= null;
	private B2_Abstract_DefinitionVektor 	vDefinition 		= null;

	private boolean isEditable = false;

	public E2_Grid4Visualize() {
		super();
		this.setOrientation(E2_Grid.ORIENTATION_HORIZONTAL);
	}

	public E2_Grid4Visualize setComponentEditable(boolean b_editable) {
		this.isEditable = b_editable;
		return this;
	}

	public E2_Grid4Visualize _setRec21(Rec21 p_record) throws myException{
		if(p_record != null) {
			this.record_2_visualize = p_record;
		}
		return this;
	}

	public E2_Grid4Visualize _setDefinition(B2_Abstract_DefinitionVektor vDefinition) throws myException{
		if((vDefinition != null) && (vDefinition.size()>0)) {
			this.vDefinition = vDefinition;
		}
		return this;
	}

	public E2_Grid4Visualize _render() throws myException{
		if(vDefinition != null && vDefinition.size()>0) {
			IF_Field field_4_pruefung = vDefinition.get(0).getIFField();

			if(record_2_visualize.get_tab() != field_4_pruefung._t()) {
				throw new myException("Error: 5a351090-c480-4581-bdbe-13cb0fc461db : record and definition on different table ");
			}

			fill_grid();

		}else {
			throw new myException("Error: c8a24a16-8bf1-4f9f-a62e-3d0e59f7ca55 : no definition !");
		}

		return this;
	}

	/**
	 * @author sebastien
	 * @date 27.03.2019
	 *
	 * @throws myException
	 * 
	 *</br></br>if component1 and component2 are not null</br>
	 *<table border="1"  style="width:100%">
	 *<tr><th>Beschriftung</th><th>component1</th><th>component2</th>
	 *</table>
	 *
	 *</br>if component1 is null</br>
	 *<table border="1"  style="width:100%">
	 *<tr><th>Beschriftung</th><th>RB_Textfield(200)</th><th>component2</th></br>
	 *</table>
	 *
	 *</br>if component2 is null
	 *<table border="1" style="width:100%">
	 *<tr><th>Beschriftung</th><th>component1</th><th>new RB_lab()</th></br>
	 *</table>
	 *
	 *</br>if component1 and component2 are null
	 *<table border="1" style="width:100%">
	 *<tr><th>Beschriftung</th><th>RB_Textfield(200)</th><th>RB_lab()</th></br>
	 *</table>
	 */
	public void fill_grid() throws myException{
		this._clear();
		
		RB_gld line_gridlayout = new RB_gld()._ins(1)._left_mid();
		
		E2_Grid innerGrid =  new E2_Grid()._setSize(vDefinition.inner_grid_size());
		
		for(E2_FieldInfo_Component fieldDefinition : this.vDefinition) {
			innerGrid._a(new RB_lab()._t(fieldDefinition.getBeschriftung()), line_gridlayout);

			Component ersatzComp1 = null;

			if(fieldDefinition.getComponent1() != null) {
				ersatzComp1 = fieldDefinition.getComponent1().get_component(record_2_visualize);
			}else {
				RB_TextField cmp1 = new RB_TextField(200);
				cmp1.set_bEnabled_For_Edit(this.isEditable);
				cmp1.rb_set_db_value_manual(this.record_2_visualize.get_ufs_dbVal(fieldDefinition.getIFField(),""));
				ersatzComp1 = cmp1.c();
			}
			
			innerGrid._a(ersatzComp1, line_gridlayout);

			if(fieldDefinition.getComponent2() !=null) {
				innerGrid._a(fieldDefinition.getComponent2().get_component(record_2_visualize), line_gridlayout);
			}else {
				innerGrid._a("", line_gridlayout);
			}
			this._a(innerGrid, line_gridlayout._c()._ins(2));
			
		}
	}

//	public boolean
	
}
