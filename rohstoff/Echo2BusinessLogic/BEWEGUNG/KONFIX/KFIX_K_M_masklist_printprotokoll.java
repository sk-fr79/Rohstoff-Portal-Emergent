package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.GregorianCalendar;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughter;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonUpDown_SingularButton;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON_DRUCK;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON_DRUCK_EM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_masklist_printprotokoll extends RB_MaskDaughter {

	
	/**
	 * 
	 */
	public KFIX_K_M_masklist_printprotokoll() {
		super();
		this._set_width_of_mask_inlay_container_ex(new Extent(800));
		this._set_height_of_mask_inlay_container_ex(new Extent(300));
	}

	@Override
	public KFIX_K_M_masklist_printprotokoll _mark_components_in_innergrid_neutral() throws myException {
		return this;
	}

	@Override
	public KFIX_K_M_masklist_printprotokoll _mark_components_in_innergrid_disabled() throws myException {
		return this;
	}

	@Override
	public KFIX_K_M_masklist_printprotokoll _set_components_in_innergrid_enabled_for_edit(boolean b_enabled_4_edit) throws myException {
		return this;
	}

	@Override
	public KFIX_K_M_masklist_printprotokoll _rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (((KFIX_K_M_DataObject)dataObject).rb_MASK_STATUS().isStatusNew()) {
			this._rb_set_db_value_manual("");
		} else {
			this._rb_set_db_value_manual(((KFIX_K_M_DataObject)dataObject).get_rec20().get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
		}
		return this;
	}

	@Override
	public KFIX_K_M_masklist_printprotokoll _rb_set_db_value_manual(String id_vkopf_kon) throws myException {
		
		if (S.isEmpty(id_vkopf_kon)) {
			this.get_inner_grid_4_data()._clear();
			this.get_inner_grid_4_data()._setSize(400);
			this.get_inner_grid_4_data()._a(new RB_lab("<kein Druckprotokoll vorhanden>"));
		} else {
			
			MyLong l_id_vkopf_kon = new MyLong(id_vkopf_kon);
			if (!l_id_vkopf_kon.isOK()) {
				this.get_inner_grid_4_data()._clear();
				this.get_inner_grid_4_data()._setSize(400);
				this.get_inner_grid_4_data()._a(new RB_lab("<Fehler beim Aufbau des Druckprotokolls>"));
			} else {

				//ueberschrift
				this.get_inner_grid_4_data()._clear();
				this.get_inner_grid_4_data()._setSize(400)._bo_dd();
				RB_gld ld_l_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._left_mid();
				RB_gld ld_m_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._center_mid();
				RB_gld ld_r_t = new RB_gld()._col(new E2_ColorDDark())._ins(2,1,2,1)._right_mid();
				RB_gld ld_l_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._left_mid();
				RB_gld ld_m_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._center_mid();
				RB_gld ld_r_d = new RB_gld()._col(new E2_ColorBase())._ins(2,1,2,1)._right_mid();
				
				
				RecList20 reclist_druck = new Rec20(_TAB.vkopf_kon)._fill_id(id_vkopf_kon).get_down_reclist20(VKOPF_KON_DRUCK.id_vkopf_kon, "", VKOPF_KON_DRUCK.id_vkopf_kon_druck.fn());
				
				
				this.get_inner_grid_4_data()._setSize(20,130,60,120,30,60,60,400)
					._gld(new RB_gld()._col(new E2_ColorBase())._ins(2,2,2,2))
					._a(new RB_lab(),						ld_l_t)
					._a(new RB_lab()._tr("Datum"),			ld_l_t)
					._a(new RB_lab()._tr("Pos."),			ld_l_t)
					._a(new RB_lab()._tr("Gesamt.Netto"),	ld_r_t)
					._a(new RB_lab()._tr("Mail"),			ld_l_t)
					._a(new RB_lab()._tr("Kürzel?"),		ld_l_t)
					._a(new RB_lab()._tr("ID?"),			ld_r_t)
					._a(new RB_lab()._tr("Mailadressen"),	ld_m_t);

				
				
				for(Rec20 rec_druck : reclist_druck){
					
					RB_CheckBox mailCB = new RB_CheckBox(VKOPF_KON_DRUCK.mail);
					mailCB.set_bEnabled_For_Edit(false);
					
					String emails = new String();
					
					RecList20 emailRecList = rec_druck.get_down_reclist20(VKOPF_KON_DRUCK_EM.id_vkopf_kon_druck, "", VKOPF_KON_DRUCK_EM.id_vkopf_kon_druck_em.fn());
					for(Rec20 emailRec : emailRecList){
						emails+= "("+emailRec.get_fs_dbVal(VKOPF_KON_DRUCK_EM.erzeugt_von)+") "+  emailRec.get_fs_dbVal(VKOPF_KON_DRUCK_EM.email_receive)+", ";
					}
					
					if(S.isEmpty(emails)){
						emails = "--";
						mailCB.setSelected(false);
					}else {
						emails = emails.substring(0, emails.length()-2);
						mailCB.setSelected(true);
					}
					
					GregorianCalendar  cal = myDateHelper.get_timeStampAsCalendar(rec_druck.get_raw_resultValue_timeStamp(VKOPF_KON_DRUCK.druckdatum));
					String c_datum_zeit = myDateHelper.get_formated_date_time(cal, "dd.MM.yyyy HH:mm", "-");
					
					this.get_inner_grid_4_data()._gld(new RB_gld()._col(new E2_ColorBase())._ins(2,2,2,2))
							._a(new E2_ButtonUpDown_SingularButton(VKOPF_KON_DRUCK.fullTabName(), rec_druck.get_ufs_dbVal(VKOPF_KON_DRUCK.id_vkopf_kon_druck), null, true),	ld_l_d)
							._a(new RB_lab(c_datum_zeit),	ld_l_d)
							._a(new RB_lab(rec_druck.get_fs_dbVal(VKOPF_KON_DRUCK.positionen)),									ld_l_d)
							._a(new RB_lab(rec_druck.get_fs_dbVal(VKOPF_KON_DRUCK.gesamt_netto)),								ld_r_d)
							._a(mailCB,																							ld_m_d)
							._a(new RB_lab(rec_druck.get_fs_dbVal(VKOPF_KON_DRUCK.kuerzel)),									ld_l_d)
							._a(new RB_lab(rec_druck.get_fs_dbVal(VKOPF_KON_DRUCK.id_vkopf_kon_druck)),							ld_r_d)
							._a(new RB_lab(emails)._i()._fsa(-2),																ld_m_d);
		
				}
			}
		}
		
		
		return this;
	}

}
