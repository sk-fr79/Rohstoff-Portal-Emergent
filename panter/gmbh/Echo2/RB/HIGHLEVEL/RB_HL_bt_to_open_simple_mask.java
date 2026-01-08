package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.TOOLS.RB_grid4masks;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_HL_bt_to_open_simple_mask extends E2_Button {

	private MASK_STATUS  actual_mask_status = null;
	private String       id_main_table  = null;

	private int          i_width_popup = 800;
	private int          i_height_popup = 600;
	private MyE2_String  maskTitle = new MyE2_String("");
	
	/**
	 * nur eine neue leere klasse erstellen, damit die groesse eindeutig gespeichert werden kann
	 * @return
	 * @throws myException
	 */
	public abstract  RB_ModuleContainerMASK   		generate_RB_ModuleContainerMASK() throws myException;
	public abstract  RB_DataobjectsCollector_V2 	generate_RB_DataobjectsCollector_V2(MASK_STATUS  actual_mask_status, String id_main_table) throws myException;
	public abstract  RB_ComponentMapCollector    	generate_RB_ComponentMapCollector() throws myException;
	public abstract  RB_grid4masks  				generate_RB_grid4masks(RB_ComponentMapCollector componentMapCollector) throws myException;
	
	
	public abstract  RB_HL_bt_to_open_simple_mask   define_actionsagents_for_mask_save_button(E2_IF_Handles_ActionAgents button_savemask, RB_ModuleContainerMASK mask) throws myException;
	public abstract  RB_HL_bt_to_open_simple_mask   define_actionsagents_for_mask_cancel_button(E2_IF_Handles_ActionAgents button_cancelmask, RB_ModuleContainerMASK mask) throws myException;
	
	
	/**
	 * 
	 */
	public RB_HL_bt_to_open_simple_mask() {
		super();
		this.add_oActionAgent(new ownActionStartMask());
	}
	
	
	/**
	 * 
	 * @param status
	 * @param id (if ID empty, then status gets automatic STATUS.NEW !!!
	 * @return
	 */
	public RB_HL_bt_to_open_simple_mask _set_mask_status_and_id(MASK_STATUS status, String id) {
		this.setImages(status);
		this.actual_mask_status=status;
		this.id_main_table = id;
		if (S.isEmpty(this.id_main_table)) {
			this.actual_mask_status=MASK_STATUS.NEW;
		}
			
		return this;
	}
	
	
	
	
	
	private void setImages(MASK_STATUS status) {
		if (status == MASK_STATUS.NEW) {
			this.__setImages(E2_ResourceIcon.get_RI("new.png"),E2_ResourceIcon.get_RI("new__.png"));
		} else if (status == MASK_STATUS.EDIT){
			this.__setImages(E2_ResourceIcon.get_RI("edit_mini.png"),E2_ResourceIcon.get_RI("edit_mini__.png"));
		} else if (status == MASK_STATUS.NEW_COPY){
			this.__setImages(E2_ResourceIcon.get_RI("copy.png"),E2_ResourceIcon.get_RI("copy__.png"));
		} else if (status == MASK_STATUS.VIEW){
			this.__setImages(E2_ResourceIcon.get_RI("view.png"),E2_ResourceIcon.get_RI("view__.png"));
		}
	}



	public MASK_STATUS get_actual_mask_status() {
		return actual_mask_status;
	}

	
	private class ownActionStartMask extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			RB_HL_bt_to_open_simple_mask 				oThis = RB_HL_bt_to_open_simple_mask.this;
			
			if (oThis.actual_mask_status==null) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Systemerror: method: _set_mask_status_and_id() was not used !!")));
				return;
			}
			
			RB_ModuleContainerMASK 			mask = oThis.generate_RB_ModuleContainerMASK();
			RB_ComponentMapCollector      	componentMapCollector = oThis.generate_RB_ComponentMapCollector();
			RB_grid4masks 					grid4mask = oThis.generate_RB_grid4masks(componentMapCollector);
			
			if (bibMSG.get_bIsOK()) {
				
				mask.rb_register(componentMapCollector);
				mask.rb_INIT(MODUL.VPOS_KON_LIEFERUNG_ZUM_LAGER_MASK, grid4mask, true);
				
				if (bibMSG.get_bIsOK()) {
					RB_DataobjectsCollector dataobjectsCollector = oThis.generate_RB_DataobjectsCollector_V2(oThis.actual_mask_status,oThis.id_main_table);
					if (dataobjectsCollector!=null) {
						mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(dataobjectsCollector);
						if (bibMSG.get_bIsOK()) {
							RB_bt_maskSaveAndClose 	btSave = 	new RB_bt_maskSaveAndClose(mask);
							RB_bt_maskClose 		btCancel = 	new RB_bt_maskClose(mask);
	
							E2_Grid  grid_4_maskButtons = new E2_Grid()._a(btSave, new RB_gld()._ins(0,2,5,2))
																		._a(btCancel, new RB_gld()._ins(0,2,5,2))
																		;
							
							mask.get_oRowForButtons().removeAll();
							mask.get_oRowForButtons().add(grid_4_maskButtons);
							
							oThis.define_actionsagents_for_mask_save_button(btSave, mask);
							oThis.define_actionsagents_for_mask_cancel_button(btCancel, mask);
							
							mask.add(grid4mask,E2_INSETS.I(2,2,2,2));
							
							mask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(oThis.i_width_popup), new Extent(oThis.i_height_popup), oThis.maskTitle);
						}
					}
				}
			
			
		}
		
	}
	}


	public int get_width_popup() {
		return i_width_popup;
	}
	
	public int get_heigth_popup() {
		return i_height_popup;
	}
	
	public RB_HL_bt_to_open_simple_mask _set_width_popup(int i_width_popup) {
		this.i_width_popup = i_width_popup;
		return this;
	}
	
	public RB_HL_bt_to_open_simple_mask _set_height_popup(int i_heigth_popup) {
		this.i_height_popup = i_heigth_popup;
		return this;
	}
	
	public MyE2_String get_maskTitle() {
		return maskTitle;
	}
	
	public RB_HL_bt_to_open_simple_mask _set_maskTitle(MyE2_String maskTitle) {
		this.maskTitle = maskTitle;
		return this;
	}
	
	
	
	
	
	
}
