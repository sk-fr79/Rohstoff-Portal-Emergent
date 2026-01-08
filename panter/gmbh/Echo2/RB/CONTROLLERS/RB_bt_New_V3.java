package panter.gmbh.Echo2.RB.CONTROLLERS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_bt_New_V3 extends E2_Button  implements IF_generating_mask_container {

	private E2_NavigationList  	NaviList = null;
	private MyE2_String  		Text4MaskTitel = new MyE2_String("Neuerfassung ...");
	private MyE2_String  		ToolTip4Button = new MyE2_String("Neuerfassung...");
	private MyE2_Grid  			grid4Mask = 			new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskInternal = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskExternal = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private RB_ModuleContainerMASK  mask_container = null;

	public abstract E2_Break4PopupController    				getBreak4PopupController4Save() throws myException;
	public abstract E2_Break4PopupController    				getBreak4PopupController4Cancel() throws myException;

//	/*
//	 * fuer eine variante mit presetting von werten bei der neuerfassung
//	 * kann fuer jeden vorhandenen maskkey ein rec20 uebergeben werden, das dann an die maske nach dem laden uebergeben wird
//	 */
//	private LinkedHashMap<RB_KM, Rec20>  hmPresetValues = new LinkedHashMap<>();
	
	
	// damit kann beim popup der maske eine hashmap mit vorgabewerten erzeugt werden (als quasi maskenkopie)
	public static interface InjectValuesForPresetNewMask {
		public void  injectValues(RB_ComponentMap map, MyE2_MessageVector mv) throws myException;
	}
	
	
	private InjectValuesForPresetNewMask injectValuesForPresetNewMask = null;

	
	public RB_bt_New_V3() {
		super();
		this._image(E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(1,1,1,1));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(1,1,1,1));
		
		this.add_oActionAgent(new ownActionAgent());
	}





	public abstract RB_ModuleContainerMASK 					generate_MaskContainer() throws myException;
	public abstract RB_DataobjectsCollector 				generate_DataObjects4New() throws myException;
	
	//hier werden die actions festgelegt
	public abstract void define_Actions_4_saveButton(RB_bt_New_V3 btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException;
	public abstract void define_Actions_4_CloseButton(RB_bt_New_V3 btNewInList, RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup) throws myException;
	
	
	private class ownActionAgent extends XX_ActionAgent  {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_bt_New_V3 							oThis = RB_bt_New_V3.this;
			RB_ModuleContainerMASK 				mask = oThis.generate_MaskContainer();
			oThis.mask_container = 				mask;

			if (oThis.injectValuesForPresetNewMask != null) {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				//das erste verfuegbare IF_RB_Component suchen und daran einen mask-presetter haengen
				RB_ComponentMap map = mask.rb_FirstComponentMapCollector().getFirstComponentMap();
				IF_RB_Component comp = map.getFirstRbComponent();
				RB_KF fieldKey = comp.rb_KF();
				
				map.registerSetterValidators(fieldKey, new ownPresetter(map,oThis.injectValuesForPresetNewMask));
			
				bibMSG.MV()._add(mv);
			}
			
			
			if (bibMSG.get_bIsOK()) {
				RB_DataobjectsCollector oContainer = oThis.generate_DataObjects4New();
				if (oContainer!=null) {
					mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(oContainer);
					if (bibMSG.get_bIsOK()) {
						mask.get_oRowForButtons().removeAll();
						mask.get_oRowForButtons().add(oThis.grid4Mask);
						oThis.grid4MaskInternal.removeAll();
						RB_bt_maskSaveAndClose 	btSave = 	new RB_bt_maskSaveAndClose(mask);
						RB_bt_maskClose 		btCancel = 	new RB_bt_maskClose(mask);

						oThis.mask_container.setButtonForClosingWindow(btCancel);
						
						E2_Break4PopupController conSave = getBreak4PopupController4Save();
						E2_Break4PopupController conCancel = getBreak4PopupController4Cancel();
						
						if (conSave!=null) {
							btSave.setBreak4PopupController(conSave);
						}
						if (conCancel!=null) {
							btCancel.setBreak4PopupController(conCancel);
						}
						
						oThis.define_Actions_4_saveButton(oThis,btSave,mask);
						oThis.define_Actions_4_CloseButton(oThis,btCancel,mask);
						
						
						
						
						oThis.grid4MaskInternal.add(btSave, E2_INSETS.I(1,1,2,1));
						oThis.grid4MaskInternal.add(btCancel, E2_INSETS.I(1,1,2,1));
						mask.CREATE_AND_SHOW_POPUPWINDOW(oThis.Text4MaskTitel);
					}
				}
			}
		}
		
	}


	public E2_NavigationList get_NaviList() {
		return NaviList;
	}


	public void set_NaviList(E2_NavigationList naviList) {
		this.NaviList = naviList;
	}
	
	public RB_bt_New_V3 set_Text4MaskTitle(MyE2_String cMaskTitle){
		this.Text4MaskTitel = cMaskTitle;
		return this;
	}

	public MyE2_String get_ToolTip4Button() {
		return ToolTip4Button;
	}


	public void set_ToolTip4Button(MyE2_String toolTip4Button) {
		ToolTip4Button = toolTip4Button;
		this.setToolTipText(ToolTip4Button.CTrans());
	}
		
	public MyE2_Grid get_grid4MaskExternal() {
		return grid4MaskExternal;
	}

	
	@Override
	public RB_ModuleContainerMASK get_MaskContainer() throws myException {
		return this.mask_container;
	}

	
	
	private class ownPresetter extends RB_Mask_Set_And_Valid {
		private InjectValuesForPresetNewMask 	m_injector;
		private RB_ComponentMap 				m_map;

		/**
		 * @param injectorMaskValues
		 */
		public ownPresetter(RB_ComponentMap map, InjectValuesForPresetNewMask injector) {
			super();
			this.m_map = map;
			this.m_injector = injector;
		}


		@Override
		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
				RB_bt_New_V3 oThis = RB_bt_New_V3.this;
				this.m_injector.injectValues(this.m_map,mv);
			}
			return mv;
		}
		
	}


	
	public InjectValuesForPresetNewMask getInjectValuesForPrestNewMask() {
		return injectValuesForPresetNewMask;
	}
	public RB_bt_New_V3 _setInjectValuesForPresetNewMask(InjectValuesForPresetNewMask injectValuesForPresetNewMask) {
		this.injectValuesForPresetNewMask = injectValuesForPresetNewMask;
		return this;
	}
	
	public MyE2_Grid getGrid4Mask() {
		return grid4Mask;
	}

}
