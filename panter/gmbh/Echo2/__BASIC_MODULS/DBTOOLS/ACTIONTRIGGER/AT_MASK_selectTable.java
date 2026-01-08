package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_generate_single_array;
import panter.gmbh.Echo2.RB.COMP.BETA.RB_selField_pop;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class AT_MASK_selectTable extends RB_selField_pop implements IF_RB_Component_Savable {

	private AT_MASK_selectField  selectField  = null; 
	/**
	 * @throws myException 
	 * 
	 */
	public AT_MASK_selectTable(AT_MASK_selectField  p_selectField) throws myException {
		super();
		this.selectField = p_selectField;
		IF_generate_single_array ar = () -> {	
												String[] c=new String[_TAB.values().length]; 
												int i=0; 
												for (_TAB t: _TAB.values()) {
													c[i++]=t.baseTableName();
												} 
												return c;};
	
		this._setShowSearchField(true);
												
		this._populate(ar.generate_single_array());
		this._addAddonActionAgent(new ownActionAgentPopulateFields());
		
	}
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.get_hm_content().get(this.get_tf_actual_keyToShow().getText());
	}

    private class ownActionAgentPopulateFields extends XX_ActionAgent {

		/**
		 * @param p_tableSelector
		 */
		public ownActionAgentPopulateFields() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AT_MASK_selectTable oThis = AT_MASK_selectTable.this;
			
			String baseTableName = oThis.rb_readValue_4_dataobject();
			oThis.selectField._resetComponent();
			
			Vector<String> v_fields = new Vector<>();
			
			if (!S.isEmpty(baseTableName)) {
				_TAB help = _TAB.find_TableFromBasename(baseTableName);
				if (help!=null) {
					IF_Field[] fields = help.get_fields();
					for (IF_Field f: fields) {
						v_fields.add(f.fn());
					}
				}
			}
			
			oThis.selectField._populate(v_fields)._put_empty_in_front();
			oThis.selectField.get_tf_actual_keyToShow().setText(oThis.selectField.get_empty_keyToShow());
		}
    
		
    	
    }

	
}
