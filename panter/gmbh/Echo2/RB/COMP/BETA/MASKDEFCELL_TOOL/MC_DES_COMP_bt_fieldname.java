package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_COMP_bt_fieldname extends MyE2_PopUpMenue implements IF_RB_Component{
	
	private RB_KF key = null;
	
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	
	public MC_DES_COMP_bt_fieldname() throws myException {
		super(E2_ResourceIcon.get_RI("popup.png"), E2_ResourceIcon.get_RI("popup__.png"), true);
	}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(dataObject.get_RecORD() != null) {
			Rec20 record_mask_def_cell = dataObject.rec20();
			if(record_mask_def_cell.get_tab().equals(_TAB.mask_def_cell)) {
				populate_field_list(record_mask_def_cell.get_ufs_dbVal(MASK_DEF_CELL.id_mask_def));
			}
		}else {
			this.addButton(new MyE2_Button("<->"), false);
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		populate_field_list(bibALL.convertID2UnformattedID(valueFormated));	
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
		
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;
	}
	
	private class ownUpdateFieldNameField extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_Button bt = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			new MC_DES_MaskControllerMap(MC_DES_COMP_bt_fieldname.this).set_fieldname(bt.getText());
		}	
	}
	
	public void populate_field_list(String id_mask_def) throws myException{
		Rec20 record_mask_def =new Rec20(_TAB.mask_def)._fill_id(id_mask_def);
		
		IF_Field[] field_list = _TAB.find_TableFromBasename(record_mask_def.get_ufs_dbVal(MASK_DEF.tablename)).get_fields();
		
		for(int i=0; i<field_list.length;i++) {
			MyE2_Button field_bt = new MyE2_Button(field_list[i].fieldName());
			field_bt._aaa(new ownUpdateFieldNameField());
			this.addButton(field_bt,true)	;	
		}
	}
}
