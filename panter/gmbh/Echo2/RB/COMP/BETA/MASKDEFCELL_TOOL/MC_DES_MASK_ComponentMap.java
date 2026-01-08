package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF;

public class MC_DES_MASK_ComponentMap extends RB_ComponentMap {

	private int x_coor, y_coor = 0;

	private Rec20_MASK_DEF record_mask_def;
	
	public MC_DES_MASK_ComponentMap(Rec20 record_mask_definition) throws myException {
		super();

		this.record_mask_def = new Rec20_MASK_DEF(record_mask_definition);
		
		this.registerComponent(MASK_DEF_CELL.id_mask_def_cell,	new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.id_mask_def,    		new MC_DES_COMP_maskdefinition_info_grid());
		this.registerComponent(MASK_DEF_CELL.fieldname,    		new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.classname,    		new MC_DES_COMP_Selfield_externalComp(MASK_DEF_CELL.classname, 220));
		this.registerComponent(MASK_DEF_CELL.usertext,    		new RB_TextArea()._width(458)._rows(5));
		this.registerComponent(MASK_DEF_CELL.field_heigth,    	new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.field_length,    	new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.colspan,    			new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.rowspan,    			new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.start_col_in_mask,	new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.start_row_in_mask,	new RB_TextField()._width(220));
		this.registerComponent(MASK_DEF_CELL.text_italic,    		new RB_CheckBox(MASK_DEF_CELL.text_italic));
		this.registerComponent(MASK_DEF_CELL.text_bold,    		new RB_CheckBox(MASK_DEF_CELL.text_bold));
		this.registerComponent(MASK_DEF_CELL.text_size,    		new RB_TextField()._width(100));
		this.registerComponent(MASK_DEF_CELL.left_insets,    		new RB_TextField()._width(100));
		this.registerComponent(MASK_DEF_CELL.top_insets,    		new RB_TextField()._width(100));
		this.registerComponent(MASK_DEF_CELL.right_insets,    	new RB_TextField()._width(100));
		this.registerComponent(MASK_DEF_CELL.bottom_insets,    	new RB_TextField()._width(100));
		
		this.registerComponent(MASK_DEF_CELL.alignment, 			new MC_DES_COMP_text_ausrichtung());
		
		this.registerComponent(MC_DES_CONST.KUSTOM_COMPONENT.BT_FIELD_ASSISTANT.key(), new MC_DES_COMP_bt_fieldname());
		
		this.registerSetterValidators(MASK_DEF_CELL.id_mask_def.fk(), new MC_DES_SetAndValidator());
	}


	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		String id_mask_definition = this.record_mask_def.get_fs_dbVal(MASK_DEF.id_mask_def);
		
		this.getRbComponent(MC_DES_CONST.KUSTOM_COMPONENT.BT_FIELD_ASSISTANT.key()).rb_set_db_value_manual(id_mask_definition);
		
		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		String id_mask_definition = this.record_mask_def.get_fs_dbVal(MASK_DEF.id_mask_def);
		
		preSettingsContainer.rb_get(MASK_DEF_CELL.text_bold)	.set_MustField(false);
		preSettingsContainer.rb_get(MASK_DEF_CELL.text_italic)	.set_MustField(false);
		preSettingsContainer.rb_get(MASK_DEF_CELL.rowspan)		.set_MustField(true);
		preSettingsContainer.rb_get(MASK_DEF_CELL.colspan)		.set_MustField(true);
		preSettingsContainer.rb_get(MASK_DEF_CELL.classname)	.set_MustField(true);

//		preSettingsContainer.rb_set_defaultMaskValue(MASK_DEF_CELL.id_mask_def, id_mask_definition);

		if(status == MASK_STATUS.NEW) {
			
			this.getRbComponent(MASK_DEF_CELL.id_mask_def).rb_set_db_value_manual(id_mask_definition);
			
//			preSettingsContainer.rb_get(MASK_DEF_CELL.id_mask_def).set_rb_Default(id_mask_definition);
			preSettingsContainer.rb_set_defaultMaskValue(MASK_DEF_CELL.rowspan, "1");
			if(this.x_coor>0) {
				preSettingsContainer.rb_get(MASK_DEF_CELL.start_col_in_mask).set_rb_Default(""+x_coor);
				preSettingsContainer.rb_get(MASK_DEF_CELL.start_col_in_mask).set_Enabled(false);
			}
			if(this.y_coor>0) {
				preSettingsContainer.rb_get(MASK_DEF_CELL.start_row_in_mask).set_rb_Default(""+y_coor);
				preSettingsContainer.rb_get(MASK_DEF_CELL.start_row_in_mask).set_Enabled(false);
			}
			preSettingsContainer.rb_set_defaultMaskValue(MASK_DEF_CELL.left_insets, 	"2");
			preSettingsContainer.rb_set_defaultMaskValue(MASK_DEF_CELL.right_insets, 	"2");
			preSettingsContainer.rb_set_defaultMaskValue(MASK_DEF_CELL.top_insets, 		"2");
			preSettingsContainer.rb_set_defaultMaskValue(MASK_DEF_CELL.bottom_insets, 	"2");
		}

		return mv;
	}


	public void set_column_coordinate(int x_coor) {
		this.x_coor = x_coor;
	}


	public void set_row_coordinate(int y_coor) {
		this.y_coor = y_coor;
	}
}

