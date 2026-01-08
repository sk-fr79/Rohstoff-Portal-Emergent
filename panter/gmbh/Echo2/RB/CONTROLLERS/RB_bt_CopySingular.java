package panter.gmbh.Echo2.RB.CONTROLLERS;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

/**
 * button, um eine neu maske zu oeffnen, in der die werte eines ausgewaehlten satzes drinstehen
 * @author martin
 *
 */
public abstract class RB_bt_CopySingular extends MyE2_Button  implements IF_generating_mask_container {

	private E2_NavigationList  	NaviList = null;
	private Extent  			ExtWidth = new Extent(800);
	private Extent  			ExtHeight = new Extent(600);
	private MyE2_String  		Text4MaskTitel = new MyE2_String("Kopie ...");
	private MyE2_String  		ToolTip4Button = new MyE2_String("Kopie...");
	private MyE2_Grid  			grid4Mask = 			new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskInternal = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  			grid4MaskExternal = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	private RB_ModuleContainerMASK  mask_container = null;
	
	public RB_bt_CopySingular() {
		super(E2_ResourceIcon.get_RI("copy.png") , true);
		
		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(1,1,1,1));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(1,1,1,1));
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	
	public RB_bt_CopySingular(ImageReference oImg, boolean bAutoDisabled) {
		super(oImg, bAutoDisabled);
		
		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(1,1,1,1));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(1,1,1,1));
		
		this.add_oActionAgent(new ownActionAgent());
	}



	public RB_bt_CopySingular(ImageReference oImg, ImageReference oimgDisabled) {
		super(oImg, oimgDisabled);
		
		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(1,1,1,1));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(1,1,1,1));
		
		this.add_oActionAgent(new ownActionAgent());
	}



	public abstract RB_ModuleContainerMASK 					generate_MaskContainer() throws myException;
	public abstract RB_DataobjectsCollector 				generate_DataObjects4Edit() throws myException;
	
	//hier werden die actions festgelegt
	public abstract void define_Actions_4_saveButton(RB_bt_CopySingular btEditSingular, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException;
	public abstract void define_Actions_4_CloseButton(RB_bt_CopySingular btEditSingular, RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup) throws myException;
	
	
	private class ownActionAgent extends XX_ActionAgent  {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_bt_CopySingular 							oThis = RB_bt_CopySingular.this;
			RB_ModuleContainerMASK 						mask = oThis.generate_MaskContainer();
			oThis.mask_container = mask;
			if (bibMSG.get_bIsOK()) {
				RB_DataobjectsCollector oCollector = oThis.generate_DataObjects4Edit();
				if (oCollector!=null) {
					mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(oCollector);
					if (bibMSG.get_bIsOK()) {
						mask.get_oRowForButtons().removeAll();
						mask.get_oRowForButtons().add(oThis.grid4Mask);
						oThis.grid4MaskInternal.removeAll();
						RB_bt_maskSaveAndClose 	btSave = 	new RB_bt_maskSaveAndClose(mask);
						RB_bt_maskClose 		btCancel = 	new RB_bt_maskClose(mask);

						oThis.define_Actions_4_saveButton(oThis,btSave,mask);
						oThis.define_Actions_4_CloseButton(oThis,btCancel,mask);
						
						oThis.grid4MaskInternal.add(btSave, E2_INSETS.I(1,1,2,1));
						oThis.grid4MaskInternal.add(btCancel, E2_INSETS.I(1,1,2,1));
						mask.CREATE_AND_SHOW_POPUPWINDOW(oThis.ExtWidth, oThis.ExtHeight, oThis.Text4MaskTitel);
						
						//hier werden jetzt die rec20-objecte gegen leere ausgetausch
						for (RB_Dataobject dob: oCollector) {
							_TAB t = ((RB_Dataobject_V2)dob).get_rec20().get_tab();
							((RB_Dataobject_V2)dob).set_implicit_status_new();
							((RB_Dataobject_V2)dob).set_actualMASK_STATUS(MASK_STATUS.NEW_COPY);
						}
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
	
	


	public Extent get_ExtWidth() {
		return ExtWidth;
	}


	public void set_ExtWidth(Extent extWidth) {
		ExtWidth = extWidth;
	}


	public Extent get_ExtHeight() {
		return ExtHeight;
	}


	public void set_ExtHeight(Extent extHeight) {
		ExtHeight = extHeight;
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



	public void set_text4MaskTitel(MyE2_String text4MaskTitel) {
		Text4MaskTitel = text4MaskTitel;
	}

	@Override
	public RB_ModuleContainerMASK get_MaskContainer() throws myException {
		return this.mask_container;
	}

}
