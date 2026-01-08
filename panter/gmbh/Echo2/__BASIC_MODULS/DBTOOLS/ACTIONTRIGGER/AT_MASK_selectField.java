package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import java.util.Vector;

import panter.gmbh.Echo2.RB.COMP.BETA.RB_selField_pop;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class AT_MASK_selectField extends RB_selField_pop implements IF_RB_Component_Savable {

	/**
	 * @throws myException 
	 * 
	 */
	public AT_MASK_selectField() throws myException {
		super();
		this._setShowSearchField(true);

	}
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.get_hm_content().get(this.get_tf_actual_keyToShow().getText());
	}

	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		//hier nachsehen, was in der tabelle steht und dann den dropdown fuellen
		super.rb_Datacontent_to_Component(dataObject);
		
		String loadedValue = this.get_tf_actual_keyToShow().getText();
		
		if (!dataObject.rec20().is_newRecordSet()) {
			String table = dataObject.rec20().get_fs_dbVal(TRIGGER_ACTION_DEF.table_basename, "");
			if (S.isFull(table)) {
				_TAB tab = _TAB.find_TableFromBasename(table);
				if (tab!=null) {
					this._fill_Field_from_table(tab);
					this.get_tf_actual_keyToShow().setText(loadedValue);
					this._renderButtons();
				}
			}
		}
	}

	
	
	
	public AT_MASK_selectField _fill_Field_from_table(_TAB tab) throws myException {
		this._resetComponent();
		
		IF_Field[] fields = tab.get_fields();
		
		Vector<String>  v_fields = new Vector<>();
		for (IF_Field f: fields) {
			v_fields.add(f.fn());
		}
		
		this._populate(v_fields)._put_empty_in_front();
		this.get_tf_actual_keyToShow().setText(this.get_empty_keyToShow());
		
		return this;
	}
	
	
}
