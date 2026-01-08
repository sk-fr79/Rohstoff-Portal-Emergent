package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughter;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_masklist_aenderung extends RB_MaskDaughter {

	public KFIX_P_M_masklist_aenderung() {
		super();
		this._set_width_of_mask_inlay_container_ex(new Extent(800));
		this._set_height_of_mask_inlay_container_ex(new Extent(300));
	}

	@Override
	public RB_MaskDaughter _mark_components_in_innergrid_neutral() throws myException {
		return this;
	}

	@Override
	public RB_MaskDaughter _mark_components_in_innergrid_disabled() throws myException {
		return this;
	}

	@Override
	public RB_MaskDaughter _set_components_in_innergrid_enabled_for_edit(boolean b_enabled_4_edit) throws myException {
		return this;
	}

	@Override
	public RB_MaskDaughter _rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (((KFIX_P_M__DataObject)dataObject).rb_MASK_STATUS().isStatusNew()) {
			this._rb_set_db_value_manual("");
		} else {
			this._rb_set_db_value_manual(((KFIX_P_M__DataObject)dataObject).get_rec20().get_ufs_dbVal(VPOS_KON.id_vpos_kon));
		}
		return this;
	}

	@Override
	public RB_MaskDaughter _rb_set_db_value_manual(String id_vpos_kon) throws myException {
		
		if (S.isEmpty(id_vpos_kon)) {
			this.get_inner_grid_4_data()._clear();
			this.get_inner_grid_4_data()._setSize(400);
			this.get_inner_grid_4_data()._a(new RB_lab("<kein Druckprotokoll vorhanden>"));
		
		} else {

			MyLong l_id_vpos_kon = new MyLong(id_vpos_kon);

			if (!l_id_vpos_kon.isOK()) {

				this.get_inner_grid_4_data()._clear();
				this.get_inner_grid_4_data()._setSize(400);
				this.get_inner_grid_4_data()._a(new RB_lab("<Fehler beim Aufbau des Aenderungtabelle>"));
				
			}else{
					this.get_inner_grid_4_data()._clear();
					this.get_inner_grid_4_data()._setSize(400)._bo_dd();
					
					RB_gld ld_l_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._left_mid();
					RB_gld ld_l_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._left_mid();
					RB_gld ld_r_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._right_mid();
					RB_gld ld_r_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._right_mid();
					
					
					RecList20 reclist_aenderung = new Rec20(_TAB.vpos_kon)._fill_id(id_vpos_kon).get_down_reclist20(VPOS_KON_AENDERUNGEN.id_vpos_kon, "", VPOS_KON_AENDERUNGEN.id_vpos_kon_aenderungen.fn());

					this.get_inner_grid_4_data()._setSize(110,110,110,110,110)
					._gld(new RB_gld()._col(new E2_ColorBase())._ins(2,2,2,2))
					._a(new RB_lab()._tr("Anzahl"),			ld_r_t)
					._a(new RB_lab()._tr("Einzelpreis"),	ld_r_t)
					._a(new RB_lab()._tr("Geändert am"),	ld_l_t)
					._a(new RB_lab()._tr("Uhrzeit"),		ld_l_t)
					._a(new RB_lab()._tr("Kürzel?"),		ld_l_t);

					for(Rec20 rec_aenderung : reclist_aenderung){
						
						this.get_inner_grid_4_data()._gld(new RB_gld()._col(new E2_ColorBase())._ins(2,2,2,2))
						._a(new RB_lab(rec_aenderung.get_fs_dbVal(VPOS_KON_AENDERUNGEN.anzahl)),			ld_r_d)
						._a(new RB_lab(rec_aenderung.get_fs_dbVal(VPOS_KON_AENDERUNGEN.einzelpreis)),		ld_r_d)
						._a(new RB_lab(rec_aenderung.get_fs_dbVal(VPOS_KON_AENDERUNGEN.letzte_aenderung)),	ld_l_d)
						._a(new RB_lab(rec_aenderung.get_fs_dbVal(VPOS_KON_AENDERUNGEN.uhrzeit_aenderung)), ld_l_d)
						._a(new RB_lab(rec_aenderung.get_fs_dbVal(VPOS_KON_AENDERUNGEN.kuerzel_aenderung)), ld_l_d)
						;		
					}
					
				}
			}
		
		return this;
		
	}

}
