package panter.gmbh.Echo2.RB.CONTROLLERS;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_bt_List2Mask_V3 extends E2_Button implements IF_generating_mask_container {

	private E2_NavigationList  									NaviList = 				null;
	private MyE2_String  										Text4MaskTitel = 		new MyE2_String("Maskenbearbeitung...");
	private MyE2_String  										ToolTip4Button = 		new MyE2_String("Maskenbearbeitung...");
	private boolean    											IsUsedToEdit = 			false;
	private MyE2_Grid  											grid4Mask = 			new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  											grid4MaskInternal = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_Grid  											grid4MaskExternal = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	//alle buttons generieren
	private bt_saveMaskAndNext 									bt_save = null;
	private bt_CloseMask										bt_exit = null;
	private bt_navigateNext    	 								bt_next = null;
	private bt_navigateBack     								bt_back = null;

	
	/*
	 * 2016-04-08: liste refreshen, wenn eine vorhanden ist
	 */
	private boolean    											autoRefreshList = false;


	private RB_ModuleContainerMASK 								o_ModulContainerMASK = 	null;
	
	//key: ids aus der navilist
	private RB_hm_multi_DataobjectsCollector 					hm_RB_Dataobjects_Container = null;
	
	public abstract E2_Break4PopupController    				getBreak4PopupController4Save() throws myException;
	public abstract E2_Break4PopupController    				getBreak4PopupController4Cancel() throws myException;
	
	
	public RB_hm_multi_DataobjectsCollector get_hm_RB_Dataobjects_Container() {
		return hm_RB_Dataobjects_Container;
	}

	//komponente fuer die maske (anzeige der save/cancel-buttons und der fortschrittsanzeige)
	public RB_bt_List2Mask_V3(boolean bEdit) {
		super();
		this._image(E2_ResourceIcon.get_RI(bEdit?"edit.png":"view.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.IsUsedToEdit = bEdit;
		
		this.grid4Mask.add(this.grid4MaskInternal,E2_INSETS.I(0,0,0,0));
		this.grid4Mask.add(this.grid4MaskExternal,E2_INSETS.I(0,0,0,0));
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	//komponente fuer die maske (anzeige der save/cancel-buttons und der fortschrittsanzeige)
	public RB_bt_List2Mask_V3(boolean bEdit, E2_NavigationList p_naviList) {
		this(bEdit);
		this._setNaviList(p_naviList);
	}

	
	
	public abstract RB_ModuleContainerMASK 				generate_MaskContainer() throws myException;
	public abstract RB_hm_multi_DataobjectsCollector  	generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException;
	public abstract MyE2_String   						generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException;
	public abstract MyE2_String   						generate_Meldung4SaveRecord(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException;
	public abstract Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask, E2_NavigationList  navilist) throws myException;
	
	private class ownActionAgent extends XX_ActionAgent  {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_bt_List2Mask_V3 						oThis = RB_bt_List2Mask_V3.this;
			
			oThis.o_ModulContainerMASK = 			oThis.generate_MaskContainer();

			//alle buttons generieren
			oThis.bt_save = new bt_saveMaskAndNext();
			oThis.bt_exit = new bt_CloseMask(); 
			oThis.bt_next = new bt_navigateNext();
			oThis.bt_back = new bt_navigateBack();

			oThis.o_ModulContainerMASK.setButtonForClosingWindow(oThis.bt_exit);
			
			E2_Break4PopupController controllerSave = 	getBreak4PopupController4Save();
			E2_Break4PopupController controllerCancel = getBreak4PopupController4Cancel();
			if (controllerSave!=null) {
				oThis.bt_save.setBreak4PopupController(controllerSave);
			}
			if (controllerCancel!=null) {
				oThis.bt_exit.setBreak4PopupController(controllerCancel);
				oThis.bt_next.setBreak4PopupController(controllerCancel);
				oThis.bt_back.setBreak4PopupController(controllerCancel);
			}

			
			if (bibMSG.get_bIsOK()) {
				oThis.hm_RB_Dataobjects_Container = oThis.generate_DataObjects4Mask(bibMSG.MV());
				if (bibMSG.get_bIsOK()) {
					bibMSG.add_MESSAGE(oThis.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(oThis.hm_RB_Dataobjects_Container.get_first_DataObjects_container()));
					oThis.o_ModulContainerMASK.get_oRowForButtons().add(oThis.grid4Mask);
					if (bibMSG.get_bIsOK()) {
						oThis.o_ModulContainerMASK.CREATE_AND_SHOW_POPUPWINDOW(oThis.Text4MaskTitel);
						Vector<XX_ActionAgentWhenCloseWindow> vClosers = oThis.generate_WindowCloseActions(oThis.o_ModulContainerMASK, oThis.NaviList) ;
						if (vClosers!=null) {
							for (XX_ActionAgentWhenCloseWindow closer: vClosers) {
								oThis.o_ModulContainerMASK.add_CloseActions(closer);
							}
						}
					}
					oThis.refresh_MaskBedienPanel();
				}
			}
		}
	}

	
	
	
	
	
	private void  refresh_MaskBedienPanel() throws myException {
		int iCount = 1;
		
		
		
		this.grid4MaskInternal.removeAll();
		
		if (this.o_ModulContainerMASK == null) 							{throw new myException(this,"No RB_ModuleContainerMASK registered!");}
		if (this.hm_RB_Dataobjects_Container == null
			|| this.hm_RB_Dataobjects_Container.keySet().size()==0) 	{throw new myException(this,"No Vector<RB_Dataobjects_Container> in maskContainer registered!");}
		
		RB_DataobjectsCollector 	dataObjActual = this.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector();
		if (dataObjActual == null) 										{throw new myException(this,"No Dataobject-container in maskContainer registered!");}
	
		int iNumberDataSets = this.hm_RB_Dataobjects_Container.size();
		String keyOfActual = this.hm_RB_Dataobjects_Container.key_of(dataObjActual);
		int iPositionOfActual = this.hm_RB_Dataobjects_Container.get_positionNumber(keyOfActual);
		
		
		
		bt_back.set_bEnabled_For_Edit(true);
		bt_next.set_bEnabled_For_Edit(true);
		if (this.hm_RB_Dataobjects_Container.is_first(dataObjActual)) {
			bt_back.set_bEnabled_For_Edit(false);
		}
		if (this.hm_RB_Dataobjects_Container.is_last(dataObjActual)) {
			bt_next.set_bEnabled_For_Edit(false);
		}
		
		if (this.IsUsedToEdit) {
			this.grid4MaskInternal.add(bt_save, E2_INSETS.I(2,2,2,2));
			iCount++;
		}
		this.grid4MaskInternal.add(bt_exit, E2_INSETS.I(2,2,2,2));
		if (iNumberDataSets>1) {
			this.grid4MaskInternal.add(bt_back, E2_INSETS.I(2,2,2,2));
			
			//die labels
			MyE2_Label  		lb_pos = new MyE2_Label(""+iPositionOfActual, MyE2_Label.STYLE_SMALL_BOLD());
			MyE2_Label  		lb_von = new MyE2_Label(""+iNumberDataSets, MyE2_Label.STYLE_SMALL_BOLD());
			MyE2_Label  		lb_mitte = new MyE2_Label("von", MyE2_Label.STYLE_SMALL_BOLD());
			
			this.grid4MaskInternal.add(lb_pos, E2_INSETS.I(2,2,2,2));
			this.grid4MaskInternal.add(lb_mitte, E2_INSETS.I(2,2,2,2));
			this.grid4MaskInternal.add(lb_von, E2_INSETS.I(2,2,2,2));
			
			this.grid4MaskInternal.add(bt_next, E2_INSETS.I(2,2,2,2));
			iCount+=5;
		}
		
		this.grid4MaskInternal.setSize(iCount);
		
		MyE2_String  titelText4ModulMaskWindow = this.generate_TitelInfo4MaskWindow(this.o_ModulContainerMASK);
		
		if (titelText4ModulMaskWindow!=null && S.isFull(titelText4ModulMaskWindow)) {
			this.o_ModulContainerMASK.get_oWindowPane().set_oTitle(titelText4ModulMaskWindow);
		}
	}

	
	
	
	public E2_NavigationList get_NaviList() {
		return NaviList;
	}


	public RB_bt_List2Mask_V3 _setNaviList(E2_NavigationList naviList) {
		this.NaviList = naviList;
		return this;
	}
	
	

	public MyE2_String get_ToolTip4Button() {
		return ToolTip4Button;
	}


	public void set_ToolTip4Button(MyE2_String toolTip4Button) {
		ToolTip4Button = toolTip4Button;
		this.setToolTipText(ToolTip4Button.CTrans());
	}
		
	
	
	private class bt_saveMaskAndNext extends E2_Button{

		public bt_saveMaskAndNext() throws myException {
			super();
			this.__setImages(E2_ResourceIcon.get_RI("save.png"), E2_ResourceIcon.get_RI("save__.png"));
			this._style(E2_Button.StyleImageButton());
			this.add_oActionAgent(new ownActionSaveMask());
		}
		
		private class ownActionSaveMask extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_bt_List2Mask_V3 oThis = RB_bt_List2Mask_V3.this;
 
				//actualkey holen und dataset neu bauen
				String cActualKey = oThis.hm_RB_Dataobjects_Container.key_of(oThis.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector());

				MyE2_MessageVector  oMV = oThis.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);
				
				if (oMV.get_bIsOK()) {
					MyE2_String s_message4Save = oThis.generate_Meldung4SaveRecord(oThis.o_ModulContainerMASK);
					if (S.isFull(s_message4Save)) {
						oMV.add_MESSAGE(new MyE2_Info_Message(s_message4Save));
					} else {
						oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(S.t("Der Datensatz mit der ID "),S.ut(cActualKey),S.t(" wurde gespeichtert!"))));
					}
					//2016-04-08: wenn eine liste vorhanden ist und der schalter gesetzt ist, diese auch aktualisieren
					if (oThis.autoRefreshList && oThis.NaviList!=null) {
						oThis.NaviList.Refresh_ComponentMAP(cActualKey, E2_ComponentMAP.STATUS_VIEW);
					}
					
					if (oThis.hm_RB_Dataobjects_Container.get_lastKey().equals(cActualKey)) {
						oThis.o_ModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						bibMSG.add_MESSAGE(oMV);
					} else {
						//daten neu aufbauen und weiterspringen
						oThis.hm_RB_Dataobjects_Container = oThis.generate_DataObjects4Mask(oMV);
						if (oMV.get_bIsOK()) {
							RB_DataobjectsCollector do_next = oThis.hm_RB_Dataobjects_Container.get(oThis.hm_RB_Dataobjects_Container.get_nextKey(cActualKey));
							if (do_next!=null) {
								oMV.add_MESSAGE(oThis.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(do_next));
								if (oMV.get_bIsOK()) {
									oThis.refresh_MaskBedienPanel();
								} 
							} else {
								oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in der Datenintegrität, Änderung von aussen !")));
							}
						}
						if (oMV.get_bHasAlarms()) {
							//wenn beim neuaufbau und blaettern was schieflaeuft, dann meldung und raus
							oThis.o_ModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						}
						
						bibMSG.add_MESSAGE(oMV);
					}
					
				} else {
					bibMSG.add_MESSAGE(oMV);
				}
			}
		}
	}
	
	private class bt_CloseMask extends MyE2_Button {
		public bt_CloseMask() throws myException {
			super(E2_ResourceIcon.get_RI("cancel.png"));
			this.add_oActionAgent(new ownActionCloseMask());
		}

		private class ownActionCloseMask extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_bt_List2Mask_V3.this.o_ModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
	}

	
	private class bt_navigateNext extends MyE2_Button {
		public bt_navigateNext() {
			super(E2_ResourceIcon.get_RI("mask_right.png"), true);
			this.add_oActionAgent(new actionNavigate(true));
		}
	}
	
	
	private class bt_navigateBack extends MyE2_Button {
		public bt_navigateBack() {
			super(E2_ResourceIcon.get_RI("mask_left.png"), true);
			this.add_oActionAgent(new actionNavigate(false));
		}
	}

	
	private class actionNavigate extends XX_ActionAgent {

		boolean vor = true;
		
		public actionNavigate(boolean Vor) {
			super();
			this.vor = Vor;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_bt_List2Mask_V3 oThis = RB_bt_List2Mask_V3.this;
			
			//actualkey holen
			String cActualKey = oThis.hm_RB_Dataobjects_Container.key_of(oThis.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector());
			//dataset neu bauen
			oThis.hm_RB_Dataobjects_Container = oThis.generate_DataObjects4Mask(bibMSG.MV());
			
			if (bibMSG.get_bIsOK()) {
				String newKey = this.vor?oThis.hm_RB_Dataobjects_Container.get_nextKey(cActualKey):oThis.hm_RB_Dataobjects_Container.get_previousKey(cActualKey);
				
				RB_DataobjectsCollector contNew = oThis.hm_RB_Dataobjects_Container.get(newKey);
				
				if (contNew!=null) {
					bibMSG.add_MESSAGE(oThis.o_ModulContainerMASK.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(contNew));
					oThis.refresh_MaskBedienPanel();
				}
			}
			if (bibMSG.get_bHasAlarms()) {
				//abbruch
				oThis.o_ModulContainerMASK.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
	}






	public RB_ModuleContainerMASK rb_modulContainerMASK() {
		return o_ModulContainerMASK;
	}

	public MyE2_Grid get_grid4MaskExternal() {
		return grid4MaskExternal;
	}

	public void set_hm_RB_Dataobjects_Container(RB_hm_multi_DataobjectsCollector hm_rb_Dataobjects_Container) {
		this.hm_RB_Dataobjects_Container = hm_rb_Dataobjects_Container;
	}

	public boolean is_UsedToEdit() {
		return this.IsUsedToEdit;
	}

	
	public boolean get_bAutoRefreshList() {
		return autoRefreshList;
	}

	
	public void set_bAutoRefreshList(boolean autoRefreshList) {
		this.autoRefreshList = autoRefreshList;
	}
	
	@Override
	public RB_ModuleContainerMASK get_MaskContainer() throws myException {
		return this.o_ModulContainerMASK;
	}


	public RB_bt_List2Mask_V3 _setStandardMessageWhenNothingSelected() {
		this.add_GlobalValidator(new XX_ActionValidator_NG() {
			
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				if (RB_bt_List2Mask_V3.this.NaviList.get_vSelectedIDs_Unformated().size()==0) {
					return new MyE2_MessageVector()._add(new MyE2_Alarm_Message(S.ms("Bitte mindestens eine Zeile auswählen !")));
				}
				return null;
			}
		});
		return this;
	}
	
	public MyE2_Grid getGrid4Mask() {
		return grid4Mask;
	}

}
