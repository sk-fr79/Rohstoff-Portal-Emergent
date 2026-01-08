package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.components.E2_ButtonMarker;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class KFIX_P_M_BT_List2Mask extends RB_bt_List2Mask {

	private String belegTyp 					= "";
	private Vector<String>  	vIDsToEdit 	= new Vector<String>();
	private Rec20 record_kopf					= null;

	private Object parent = null;

	private SammleID   sammler = null;
	
	public KFIX_P_M_BT_List2Mask(boolean bEdit, String vposKonId, KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent, String oModul, Rec20 recVkopf) throws myException {
		super(bEdit, null);

		this.record_kopf = recVkopf;

		this.vIDsToEdit.add(vposKonId);

		this.sammler = new SammleIdsManuell(vposKonId);
		
		this.parent = oParent;

		this.belegTyp = this.record_kopf.get_ufs_dbVal(VKOPF_KON.vorgang_typ);
		
		if (bEdit) {
			this._ttt("Kontrakt-Position bearbeiten");
			this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
		} else  {
			this._ttt("Kontrakt-Position anzeigen");
		}

		if(bEdit){
			if(this.belegTyp.equals(VORGANGSART.EK_KONTRAKT.get_DBValue())){
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_EDIT.getValidator());
			}else{
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_EDIT.getValidator());
			}
		}else{
			if(this.belegTyp.equals(VORGANGSART.EK_KONTRAKT.get_DBValue())){
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_VIEW.getValidator());
			}else{
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_VIEW.getValidator());
			}
		}
		
		
//		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit?BSK_M_VK_BUTTONS.EDIT.db_val():BSK_M_VK_BUTTONS.VIEW.db_val()));

	}

	/**
	 * editieren und anzeigen direkt aus der liste 
	 * @param bEdit
	 * @param vposKonId
	 * @param oParent
	 * @throws myException
	 */
	public KFIX_P_M_BT_List2Mask(boolean bEdit, String vposKonId, KFIX_K_M_masklist_position_raster_grid oParent) throws myException {
		super(bEdit, null);

		Rec20 recPos = new Rec20(_TAB.vpos_kon)._fill_id(vposKonId);
		this.record_kopf = recPos.get_up_Rec20(VKOPF_KON.id_vkopf_kon);

		this.vIDsToEdit.add(vposKonId);
		
		this.sammler = new SammleIdsManuell(vposKonId);

		this.parent = oParent;

		this.belegTyp = this.record_kopf.get_ufs_dbVal(VKOPF_KON.vorgang_typ);
		
		if (bEdit) {
			
			this._ttt("Kontrakt-Position bearbeiten");
			this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
			if(this.belegTyp.equals(VORGANGSART.EK_KONTRAKT.get_DBValue())){
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_EDIT.getValidator());
			}else{
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_EDIT.getValidator());
			}
			
		} else  {
			
			this._ttt("Kontrakt-Position anzeigen");
			
			if(this.belegTyp.equals(VORGANGSART.EK_KONTRAKT.get_DBValue())){
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_VIEW.getValidator());
			}else{
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_VIEW.getValidator());
			}
		}		
//		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit?BSK_M_VK_BUTTONS.EDIT.db_val():BSK_M_VK_BUTTONS.VIEW.db_val()));

	}

	
	
	
	public KFIX_P_M_BT_List2Mask(boolean bEdit, KFIX_K_M_masklist_position_raster_grid oParent, String oModul, Rec20 recVkopf) throws myException {
		super(bEdit, null);

		this.record_kopf = recVkopf;

		this.parent = oParent;

		this.sammler = new SammleIDSelected(oParent);

		
		this.belegTyp = this.record_kopf.get_ufs_dbVal(VKOPF_KON.vorgang_typ);
		
		
		if (bEdit) {
			
			this._ttt("Kontrakt-Position bearbeiten");
			this.setDisabledIcon(E2_ResourceIcon.get_RI("leer.png"));
			if(this.belegTyp.equals(VORGANGSART.EK_KONTRAKT.get_DBValue())){
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_EDIT.getValidator());
			}else{
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_EDIT.getValidator());
			}
			
		} else  {
			
			this._ttt("Kontrakt-Position anzeigen");
			
			if(this.belegTyp.equals(VORGANGSART.EK_KONTRAKT.get_DBValue())){
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_EK_VIEW.getValidator());
			}else{
				this.add_GlobalValidator(ENUM_VALIDATION.VPOS_KON_VK_VIEW.getValidator());
			}
		}	

//		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(bEdit?BSK_M_VK_BUTTONS.EDIT.db_val():BSK_M_VK_BUTTONS.VIEW.db_val()));

	}

	
	
	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new KFIX_P_M__MaskModulContainer(this.belegTyp, this.record_kopf);
	}

	@Override
	public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {

		RB_hm_multi_DataobjectsCollector hmDataContainer = new RB_hm_multi_DataobjectsCollector();

		this.vIDsToEdit.clear();
		this.vIDsToEdit.addAll(this.sammler.getIdVposKon());
		
		if (this.vIDsToEdit.size()==0) {
			mv_sammler._addAlarm("Bitte mindestens eine Position auswählen!");
		}
		
		for (String id: this.vIDsToEdit) {

			boolean is_position_geschlossen = 
					new Rec20(_TAB.vpos_kon_trakt)
					._fill_sql(new SEL().FROM(_TAB.vpos_kon_trakt).WHERE(new vgl(VPOS_KON_TRAKT.id_vpos_kon,id )).s())
					.is_yes_db_val(VPOS_KON_TRAKT.abgeschlossen);

			boolean is_deleted = new Rec20(_TAB.vpos_kon)
					._fill_sql(new SEL().FROM(_TAB.vpos_kon).WHERE(new vgl(VPOS_KON.id_vpos_kon,id )).s())
					.is_yes_db_val(VPOS_KON.deleted);

			MASK_STATUS mask_status = null;
			if(is_position_geschlossen || is_deleted ){
				mask_status = MASK_STATUS.VIEW;
			}else{
				mask_status =  this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
			}

			hmDataContainer.put(id, new KFIX_P_M__DataObjectCollector(id,mask_status,this.belegTyp));
		}
		return hmDataContainer;
	}

	@Override
	public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten von Fixierungsposition":"Anzeige von Fixierungsposition");
	}

	@Override
	public MyE2_String generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
		return new MyE2_String("Fixierungsposition wurde gespeichert");
	}

	@Override
	public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList navilist) throws myException {
		Vector<XX_ActionAgentWhenCloseWindow> oVekt = new Vector<XX_ActionAgentWhenCloseWindow>();
		oVekt.add(new ownActionRefreshNavilist(rb_ModulContainerMask));
		oVekt.add(new ownActionRefreshMarkLastUsed(rb_ModulContainerMask));
		return oVekt;
	}


	private class ownActionRefreshNavilist extends XX_ActionAgentWhenCloseWindow {

		public ownActionRefreshNavilist(RB_ModuleContainerMASK p_maskPopup) {
			super(p_maskPopup);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			KFIX_P_M_BT_List2Mask oThis = KFIX_P_M_BT_List2Mask.this;

			if(KFIX_P_M_BT_List2Mask.this.parent instanceof KFIX_K_L_EXPANDER_4_ComponentMAP_NG){

				KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent = (KFIX_K_L_EXPANDER_4_ComponentMAP_NG) KFIX_P_M_BT_List2Mask.this.parent;

				oParent.get_oNavigationList().Refresh_ComponentMAP(oThis.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon), E2_ComponentMAP.STATUS_VIEW);
			}
			else if(KFIX_P_M_BT_List2Mask.this.parent instanceof KFIX_K_M_masklist_position_raster_grid){
				KFIX_K_M_masklist_position_raster_grid oParent = (KFIX_K_M_masklist_position_raster_grid)KFIX_P_M_BT_List2Mask.this.parent;
				
				oParent.completeBuildAndRender();
				oParent._clearCheckboxes()._markCheckboxes(KFIX_P_M_BT_List2Mask.this.vIDsToEdit);
				
			
				
				
			//	oParent.get_parent_container().rb_set_db_value_manual(oThis.record_kopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
//				oParent.rb_ComponentMap_this_belongsTo().rb_get_belongs_to().rb_COMPLETE_MASK_RELOAD();
//				oThis.rb_modulContainerMASK().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_MASK_RELOAD();
			}

		}
	}



	private class ownActionRefreshMarkLastUsed extends XX_ActionAgentWhenCloseWindow {

		public ownActionRefreshMarkLastUsed(RB_ModuleContainerMASK p_maskPopup) {
			super(p_maskPopup);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			KFIX_P_M_BT_List2Mask oThis = KFIX_P_M_BT_List2Mask.this;
			if(KFIX_P_M_BT_List2Mask.this.parent instanceof KFIX_K_L_EXPANDER_4_ComponentMAP_NG){
				
				KFIX_K_L_EXPANDER_4_ComponentMAP_NG oParent = (KFIX_K_L_EXPANDER_4_ComponentMAP_NG) KFIX_P_M_BT_List2Mask.this.parent;
				
				VEK<E2_ButtonMarker<Rec20>>  v_markers = oParent.get_v_markers();
				
				if (v_markers!=null) {
					for (E2_ButtonMarker<Rec20> m: v_markers) {
						if (oThis.vIDsToEdit.contains(m.get_reference().get_ufs_dbVal(VPOS_KON.id_vpos_kon)) || 
								oThis.vIDsToEdit.contains(m.get_reference().get_fs_dbVal(VPOS_KON.id_vpos_kon))) {
							m._mark();
						}
					}
				}
			}

		}
	}
	
	
	
	
	//sammler-klassen, die unter verschiedene scenarien die zu bearbeitenden ids raussuchen
	private abstract class SammleID {
		public abstract Vector<String> getIdVposKon();
	}
	
	
	private class SammleIDSelected extends SammleID {
		private KFIX_K_M_masklist_position_raster_grid motherGrid = null;

		/**
		 * @param p_motherGrid
		 */
		public SammleIDSelected(KFIX_K_M_masklist_position_raster_grid p_motherGrid) {
			super();
			this.motherGrid = p_motherGrid;
		}

		public Vector<String> getIdVposKon() {
			return (motherGrid.get_selected_ids());
			
		}
	}
	
	
	private class SammleIdsManuell extends SammleID {

		private String id_vpos_kon = null;
		
		/**
		 * @param p_id_vpos_kon
		 */
		public SammleIdsManuell(String p_id_vpos_kon) {
			super();
			this.id_vpos_kon = p_id_vpos_kon;
		}

		@Override
		public Vector<String> getIdVposKon() {
			return bibVECTOR.get_Vector(this.id_vpos_kon);
		}
		
	}
	
}
