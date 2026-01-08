package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.BETA.ENUM_ALIGNEMENT;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_COMP_text_ausrichtung extends E2_Grid implements IF_RB_Component_Savable {

	private ENUM_ALIGNEMENT align = null;

	private HashMap<String, E2_Button> bt_map= new HashMap<String, E2_Button>();

	private RB_KF key;

	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();

	public MC_DES_COMP_text_ausrichtung() {
		super();
		this._setSize(20,20,20)._bo_dd();

		bt_map.put(ENUM_ALIGNEMENT.LEFT_TOP.db_val(), 		new E2_Button()._image("cell_left_top.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.LEFT_TOP)));
		bt_map.put(ENUM_ALIGNEMENT.CENTER_TOP.db_val(), 	new E2_Button()._image("cell_center_top.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.CENTER_TOP)));
		bt_map.put(ENUM_ALIGNEMENT.RIGHT_TOP.db_val(), 		new E2_Button()._image("cell_right_top.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.RIGHT_TOP)));
		bt_map.put(ENUM_ALIGNEMENT.LEFT_MID.db_val(), 		new E2_Button()._image("cell_left_mid.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.LEFT_MID)));
		bt_map.put(ENUM_ALIGNEMENT.CENTER_MID.db_val(), 	new E2_Button()._image("cell_center_mid.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.CENTER_MID)));
		bt_map.put(ENUM_ALIGNEMENT.RIGHT_MID.db_val(), 		new E2_Button()._image("cell_right_mid.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.RIGHT_MID)));
		bt_map.put(ENUM_ALIGNEMENT.LEFT_BOTTOM.db_val(), 	new E2_Button()._image("cell_left_bottom.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.LEFT_BOTTOM)));
		bt_map.put(ENUM_ALIGNEMENT.CENTER_BOTTOM.db_val(), 	new E2_Button()._image("cell_center_bottom.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.CENTER_BOTTOM)));
		bt_map.put(ENUM_ALIGNEMENT.RIGHT_BOTTOM.db_val(), 	new E2_Button()._image("cell_right_bottom.png")._standard_text_button()._aaa(new ownActionAgent(ENUM_ALIGNEMENT.RIGHT_BOTTOM)));

		RB_gld gld = new RB_gld()._ins(2); 

		this
		._a(bt_map.get(ENUM_ALIGNEMENT.LEFT_TOP.db_val())	,gld._left_top())		
		._a(bt_map.get(ENUM_ALIGNEMENT.CENTER_TOP.db_val())	,gld._center_top())		
		._a(bt_map.get(ENUM_ALIGNEMENT.RIGHT_TOP.db_val())	,gld._right_top())

		._a(bt_map.get(ENUM_ALIGNEMENT.LEFT_MID.db_val())	,gld._left_mid())		
		._a(bt_map.get(ENUM_ALIGNEMENT.CENTER_MID.db_val())	,gld._center_mid())		
		._a(bt_map.get(ENUM_ALIGNEMENT.RIGHT_MID.db_val())	,gld._right_mid())

		._a(bt_map.get(ENUM_ALIGNEMENT.LEFT_BOTTOM.db_val())	,gld._left_bottom())		
		._a(bt_map.get(ENUM_ALIGNEMENT.CENTER_BOTTOM.db_val())	,gld._center_bottom())		
		._a(bt_map.get(ENUM_ALIGNEMENT.RIGHT_BOTTOM.db_val())	,gld._right_bottom());

	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(dataObject != null) {
			String align_value = dataObject.rec20().get_fs_dbVal(MASK_DEF_CELL.alignment,"");
			rb_set_db_value_manual(align_value);
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		if(S.isFull(valueFormated)) {
			this.bt_map.get(valueFormated)._bc(new E2_ColorLLight())._bord_black();
			this.align = ENUM_ALIGNEMENT.valueOf(valueFormated);
		}
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
		return new Vector<RB_Validator_Component>();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		if(this.align != null) {
			return this.align.db_val();
		}
		return "";
	}


	@Override
	public void mark_Neutral() throws myException {
		for(String keybt_2_enabled : this.bt_map.keySet()) {
			bt_map.get(keybt_2_enabled).mark_Neutral();
		}
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		for(String keybt_2_enabled : this.bt_map.keySet()) {
			bt_map.get(keybt_2_enabled)._align(align);
		}
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		for(String keybt_2_enabled : this.bt_map.keySet()) {
			bt_map.get(keybt_2_enabled).set_bEnabled_For_Edit(enabled);
		}
	}

	private class ownActionAgent extends XX_ActionAgent{
		private ENUM_ALIGNEMENT align;

		public ownActionAgent(ENUM_ALIGNEMENT p_align) {
			super();
			align = p_align;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MC_DES_COMP_text_ausrichtung.this.align = this.align;
			E2_Button bt = (E2_Button)oExecInfo.get_MyActionEvent().getSource();
			for(Component cmp_  : MC_DES_COMP_text_ausrichtung.this.getComponents()) {
				if(((E2_Button)cmp_).equals(bt)) {
					((E2_Button)cmp_)._bc(new E2_ColorLLight())._bord_black();
				}else {
					((E2_Button)cmp_)._backDark()._bordDDDark();
				}
			}
		}
	}

}
