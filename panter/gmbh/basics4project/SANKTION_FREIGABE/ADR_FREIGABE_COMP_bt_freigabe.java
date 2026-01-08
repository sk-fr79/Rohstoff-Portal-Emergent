package panter.gmbh.basics4project.SANKTION_FREIGABE;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ADR_FREIGABE_COMP_bt_freigabe extends E2_Button implements IF_RB_Component_Savable {

	private RB_KF key = null;
	
	private final static String t_inaktiv 	= "Freigabe";
	private final static String t_aktiv 	= "Freigabe aufheben";
	
	public ADR_FREIGABE_COMP_bt_freigabe() throws myException {
		super();
		this._bordBlack()._standard_text_button()._width(150)._al_center();
		this.EXT().set_C_MERKMAL("Y");
		this.refresh_status();
		this._aaa(()->update_status());
	}
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(!(dataObject == null)) {
			this.EXT().set_C_MERKMAL(dataObject.rec20().get_ufs_dbVal(key.get_data_field(),"N"));
			refresh_status();
		}
	}
	
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		
		return this.EXT().get_C_MERKMAL();
	}
	
	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}
	
	@Override
	public void set_rb_RB_K(RB_KF p_key) throws myException {
		this.key = p_key;

	}
	
	private void update_status() throws myException {
		boolean actual_status =(this.EXT().get_C_MERKMAL().equals("Y"))?true:false;
		if(actual_status) {
			this.EXT().set_C_MERKMAL("N");
			((RB_TextField)this.rb_ComponentMap_this_belongsTo().get__Comp(SANKTION_PRUEFUNG.freigabe_datum)).rb_set_db_value_manual("");
			((RB_HighLevel_SelectFieldUser)this.rb_ComponentMap_this_belongsTo().get__Comp(SANKTION_PRUEFUNG.freigabe_user)).set_ActiveValue("");
		}else {
			this.EXT().set_C_MERKMAL("Y");
			((RB_TextField)this.rb_ComponentMap_this_belongsTo().get__Comp(SANKTION_PRUEFUNG.freigabe_datum)).rb_set_db_value_manual(bibALL.get_cDateNOW());
			((RB_HighLevel_SelectFieldUser)this.rb_ComponentMap_this_belongsTo().get__Comp(SANKTION_PRUEFUNG.freigabe_user)).set_ActiveValue(bibALL.get_ID_USER_FORMATTED());
		}
		refresh_status();
	}
	
	private void refresh_status() {
		boolean status =(this.EXT().get_C_MERKMAL().equals("Y"))?true:false;
		if(status) {
			this.setText(t_aktiv);
			this._bordBlack()._backDark();
		}else {
			this.setText(t_inaktiv);
			this._bordDDDark()._backDark();
		}
	}
	
}
