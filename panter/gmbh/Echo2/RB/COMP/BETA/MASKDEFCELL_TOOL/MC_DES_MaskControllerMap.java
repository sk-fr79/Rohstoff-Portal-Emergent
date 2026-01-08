package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_MaskControllerMap extends RB_MaskControllerMap {
	
	private RB_ComponentMap cmp_map ;
	
	public MC_DES_MaskControllerMap(IF_RB_Component p_component) throws myException {
		super(p_component);
		
		cmp_map = p_component.rb_ComponentMap_this_belongsTo();
	
	}

	public MyE2_MessageVector populate_feld_assistant_popup(IF_RB_Component compCalling, String fieldVal)throws myException {
		MC_DES_COMP_bt_fieldname cmp = (MC_DES_COMP_bt_fieldname) this.cmp_map._find_component_in_neighborhood(MC_DES_CONST.KUSTOM_COMPONENT.BT_FIELD_ASSISTANT.key());
		cmp.populate_field_list(fieldVal);
		return null;
	}
	
	public MyE2_MessageVector set_fieldname( String fieldVal)throws myException {
		this.cmp_map._find_component_in_neighborhood(MASK_DEF_CELL.fieldname).rb_set_db_value_manual(fieldVal);
		return null;
	}
	
	
	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		return null;
	}

	
	
	
}
