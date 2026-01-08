package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V21;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V22;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_BtV4_List2Mask extends E2_Button  {

	public abstract RB_ModuleContainerMASK   					generateMaskContainer() throws myException;
	public abstract E2_Break4PopupController    				getBreak4PopupController4Save() throws myException;
	public abstract E2_Break4PopupController    				getBreak4PopupController4Cancel() throws myException;
	public abstract RB_hm_multi_DataobjectsCollector  			generateDataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException;
	public abstract MyE2_String   								generateTitelInfo4MaskWindow() throws myException;
	public abstract MyE2_String   								generateMessagetextForSaveRecord() throws myException;
	public abstract Vector<XX_ActionAgentWhenCloseWindow> 		generateWindowCloseActions() throws myException;

	
	
	private MyE2_String  				Text4MaskTitel = 			new MyE2_String("Maskenbearbeitung...");
	private MyE2_String  				ToolTip4Button = 			new MyE2_String("Maskenbearbeitung...");
	private boolean    					IsUsedToEdit = 				false;
	private E2_Grid  					gridButtonListContainer = 	new E2_Grid()._s(3);
	private E2_Grid  					gridButtonListBasic = 		new E2_Grid()._s(3);
	private E2_Grid  					gridButtonListAddons = 		new E2_Grid()._s(3);
	
	//alle buttons generieren
	private E2_Button 					btSaveAndNext = 	null;
	private E2_Button 					btSaveAndReopen = 	null;
	private E2_Button 					btCancel = 			null;
	private E2_Button    	 			btNext = 			null;
	private E2_Button     				btBack = 			null;

//	private IF_BtV4_StandardButtonAccess objFormatter = null;
	private Vector<IF_ObjectAccessor<RB_BtV4_List2Mask>> vObjAccessor = new Vector<IF_ObjectAccessor<RB_BtV4_List2Mask>>(); 
	
	
	private RB_TransportHashMap  		m_transportHashMap = null;
	
	public RB_TransportHashMap getTransportHashMap() {
		return m_transportHashMap;
	}
	public RB_BtV4_List2Mask _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}


	
	/*
	 * 2016-04-08: liste refreshen, wenn eine vorhanden ist
	 */
	private boolean    											autoRefreshList = false;
	
	//key: ids aus der navilist
	private RB_hm_multi_DataobjectsCollector 					hm_RB_Dataobjects_Container = null;
	
		
	
	//2010-09-14: option, den save-and-reopen-button auszublenden
	private boolean 		showSaveAndReOpenButton = true;
	
	
	
	public RB_hm_multi_DataobjectsCollector get_hm_RB_Dataobjects_Container() {
		return hm_RB_Dataobjects_Container;
	}

	public RB_BtV4_List2Mask(boolean bEdit) {
		super();
		this._image(E2_ResourceIcon.get_RI(bEdit?"edit.png":"view.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.IsUsedToEdit = bEdit;
		
		this.gridButtonListContainer._a(this.gridButtonListBasic,new RB_gld()._ins(0,0,0,0));
		this.gridButtonListContainer._a(this.gridButtonListAddons,new RB_gld()._ins(0,0,0,0));
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 13.02.2019
	 * kann überschrieben werden, wenn der editbutton eigentlich nicht mehr erlaubt ist (und automatisch zum view werden soll)
	 * @return
	 */
	protected boolean isEditAllowd() {
		return true;
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent  {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_BtV4_List2Mask 						oThis = RB_BtV4_List2Mask.this;
			
			RB_TransportHashMap thm = oThis.getTransportHashMap();
			
			if (oThis.m_transportHashMap==null) {
				throw new myException(this, "No transportHashMap is present !");
			}
			
			oThis.m_transportHashMap._setRBModulContainerMask(oThis.generateMaskContainer());
			oThis.m_transportHashMap._setMaskGrid((E2_Grid)thm.getRBModulContainerMask().get_ComponentWithMaskElements());
			oThis.m_transportHashMap._setMaskComponentMapCollector(thm.getRBModulContainerMask() .rb_FirstAndOnlyComponentMapCollector());
			

			//alle buttons generieren
			oThis.btSaveAndNext = 	new bt_saveMaskAndNext(thm.getRBModulContainerMask());
			oThis.btSaveAndReopen = new E2_Button()		
											._setShapeSaveAndReOpen()
											._aaa(new RB_BtV4_ActionStandardSaveAndReopen() {
													@Override
													public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record,RB_ComponentMapCollector componentmapCollectorActual) throws myException {
														RB_BtV4_List2Mask oThis = RB_BtV4_List2Mask.this;
														MyE2_MessageVector mv = new MyE2_MessageVector();
														
														RB_hm_multi_DataobjectsCollector hm = oThis.generateDataObjects4Mask(mv);
														if (hm.get(id_record)==null) {
															throw new myException("Record of ID: "+id_record+" is not longer existing !");
														}
														
														oThis.m_transportHashMap._setMaskDataObjectsCollector(hm.get(id_record));
														
														return hm.get(id_record);
													}
												}._setTransportHashMap(oThis.m_transportHashMap))
											;
			
			
			oThis.btCancel = 		new E2_Button()
										._setShapeCancelAndClose()
										._aaa(new RB_BtV4_ActionStandardCloseMask()._setTransportHashMap(oThis.m_transportHashMap))
										;
			
			oThis.btNext = 			new E2_Button()
										._setShapeMoveForeward()
										._aaa(new actionNavigate(true))
										;
			
			oThis.btBack =			new E2_Button()
										._setShapeMoveBackward()
										._aaa(new actionNavigate(false))
										;

			thm._setButtonMaskCancel(oThis.btCancel);
			thm._setButtonMaskSave(oThis.btSaveAndNext);
			thm._setButtonMaskSaveAndReload(oThis.btSaveAndReopen);
			
			
			
			thm.getRBModulContainerMask().setButtonForClosingWindow(oThis.btCancel);
			
			E2_Break4PopupController controllerSave = 	getBreak4PopupController4Save();
			E2_Break4PopupController controllerCancel = getBreak4PopupController4Cancel();
			
			if (controllerSave!=null) {
				oThis.btSaveAndNext.setBreak4PopupController(controllerSave);
				//20200206 breakcontroller auch bei saveandreopen
				btSaveAndReopen.setBreak4PopupController(controllerSave);

			}
			
			for (IF_ObjectAccessor<RB_BtV4_List2Mask> o: vObjAccessor) {
				o.doAnythingWith(oThis);
			}
//			if (objFormatter != null) {
//				objFormatter.doAnythingWith(oThis);
//			}
			
			
			if (controllerCancel!=null) {
				oThis.btCancel.setBreak4PopupController(controllerCancel);
				oThis.btNext.setBreak4PopupController(controllerCancel);
				oThis.btBack.setBreak4PopupController(controllerCancel);
			}
			
			
			if (bibMSG.get_bIsOK()) {
				oThis.hm_RB_Dataobjects_Container = oThis.generateDataObjects4Mask(bibMSG.MV());
				if (bibMSG.get_bIsOK()) {
					
					//das ist der einstiegs-collector, wenn mehrere bearbeitet werden
					RB_DataobjectsCollector  collector = oThis.hm_RB_Dataobjects_Container.get_first_DataObjects_container();
					thm._setMaskDataObjectsCollector(collector);
					//String id = ((RB_DataobjectsCollector_V2)collector).get_v2(oThis.m_transportHashMap.getLeadingMaskKey()).get_rec20().get_key_value();
					//2019-01-04: anpassung an RB_DataobjectsCollector_V21
					String id = oThis.findIdOfLeadingMask(collector);
					//2019-01-04: anpassung ende
					thm.getNavigationList().Mark_ID_IF_IN_Page(id);
					
					bibMSG.add_MESSAGE(thm.getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(collector));
					thm.getRBModulContainerMask().get_oRowForButtons().add(oThis.gridButtonListContainer);


					if (bibMSG.get_bIsOK()) {
						
						thm.getRBModulContainerMask().CREATE_AND_SHOW_POPUPWINDOW(oThis.Text4MaskTitel);
						Vector<XX_ActionAgentWhenCloseWindow> vClosers = oThis.generateWindowCloseActions() ;
						if (vClosers!=null) {
							for (XX_ActionAgentWhenCloseWindow closer: vClosers) {
								thm.getRBModulContainerMask().add_CloseActions(closer);
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
		
		this.gridButtonListBasic.removeAll();
		
		if (this.getTransportHashMap().getRBModulContainerMask() == null) 							{throw new myException(this,"No RB_ModuleContainerMASK registered!");}
		if (this.hm_RB_Dataobjects_Container == null
			|| this.hm_RB_Dataobjects_Container.keySet().size()==0) 	{throw new myException(this,"No Vector<RB_Dataobjects_Container> in maskContainer registered!");}
		
		RB_DataobjectsCollector 	dataObjActual = this.getTransportHashMap().getRBModulContainerMask() .rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector();
		if (dataObjActual == null) 										{throw new myException(this,"No Dataobject-container in maskContainer registered!");}
	
		int iNumberDataSets = this.hm_RB_Dataobjects_Container.size();
		String keyOfActual = this.hm_RB_Dataobjects_Container.key_of(dataObjActual);
		int iPositionOfActual = this.hm_RB_Dataobjects_Container.get_positionNumber(keyOfActual);
		
		
		
		btBack.set_bEnabled_For_Edit(true);
		btNext.set_bEnabled_For_Edit(true);
		if (this.hm_RB_Dataobjects_Container.is_first(dataObjActual)) {
			btBack.set_bEnabled_For_Edit(false);
		}
		if (this.hm_RB_Dataobjects_Container.is_last(dataObjActual)) {
			btNext.set_bEnabled_For_Edit(false);
		}
		
		if (this.IsUsedToEdit && this.isEditAllowd()) {
			this.gridButtonListBasic._a(btSaveAndNext, new RB_gld()._ins(2,2,4,2));
			iCount++;
			if (showSaveAndReOpenButton) {
				this.gridButtonListBasic._a(btSaveAndReopen, new RB_gld()._ins(2,2,4,2));
				iCount++;
			}
		}
		this.gridButtonListBasic._a(btCancel, new RB_gld()._ins(2,2,2,2));
		
		if (iNumberDataSets>1) {
			this.gridButtonListBasic._a(btBack, new RB_gld()._ins(2,2,2,2));
			
			//die labels
			MyE2_Label  		lb_pos = new MyE2_Label(""+iPositionOfActual, MyE2_Label.STYLE_SMALL_BOLD());
			MyE2_Label  		lb_von = new MyE2_Label(""+iNumberDataSets, MyE2_Label.STYLE_SMALL_BOLD());
			MyE2_Label  		lb_mitte = new MyE2_Label("von", MyE2_Label.STYLE_SMALL_BOLD());
			
			this.gridButtonListBasic._a(lb_pos, new RB_gld()._ins(2,4,4,2));
			this.gridButtonListBasic._a(lb_mitte, new RB_gld()._ins(2,4,4,2));
			this.gridButtonListBasic._a(lb_von, new RB_gld()._ins(2,4,4,2));
			
			this.gridButtonListBasic._a(btNext, new RB_gld()._ins(2,2,4,2));
			iCount+=5;
		}
		
		this.gridButtonListBasic.setSize(iCount);
		
		MyE2_String  titelText4ModulMaskWindow = this.generateTitelInfo4MaskWindow();
		
		if (titelText4ModulMaskWindow!=null && S.isFull(titelText4ModulMaskWindow)) {
			this.getTransportHashMap().getRBModulContainerMask() .get_oWindowPane().set_oTitle(titelText4ModulMaskWindow);
		}
	}

	

	public MyE2_String get_ToolTip4Button() {
		return ToolTip4Button;
	}


	public void set_ToolTip4Button(MyE2_String toolTip4Button) {
		ToolTip4Button = toolTip4Button;
		this.setToolTipText(ToolTip4Button.CTrans());
	}
		
	
	
	private class bt_saveMaskAndNext extends E2_Button{

		public bt_saveMaskAndNext(RB_ModuleContainerMASK maskContainer) throws myException {
			super();
			
			this.__setImages(E2_ResourceIcon.get_RI("save.png"), E2_ResourceIcon.get_RI("save__.png"));
			this._style(E2_Button.StyleImageButton());
			this.add_oActionAgent(new ownActionSaveMask());
		}
		
		private class ownActionSaveMask extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				RB_BtV4_List2Mask oThis = RB_BtV4_List2Mask.this;
 
				//actualkey holen und dataset neu bauen
				String cActualKey = oThis.hm_RB_Dataobjects_Container.key_of(oThis.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector());

				
				MyE2_MessageVector  oMV = oThis.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true);
				
				if (oMV.get_bIsOK()) {
					MyE2_String s_message4Save = oThis.generateMessagetextForSaveRecord();
					if (S.isFull(s_message4Save)) {
						oMV.add_MESSAGE(new MyE2_Info_Message(s_message4Save));
					} else {
						oMV.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(S.t("Der Datensatz mit der ID "),S.ut(cActualKey),S.t(" wurde gespeichtert!"))));
					}
					//2016-04-08: wenn eine liste vorhanden ist und der schalter gesetzt ist, diese auch aktualisieren
					if (oThis.autoRefreshList && oThis.m_transportHashMap.get(RB_TransportHashMap_ENUM.NAVILIST)!=null) {
						oThis.m_transportHashMap.getNavigationList().Refresh_ComponentMAP(cActualKey, E2_ComponentMAP.STATUS_VIEW);
					}
					
					if (oThis.hm_RB_Dataobjects_Container.get_lastKey().equals(cActualKey)) {
						oThis.getTransportHashMap().getRBModulContainerMask().CLOSE_AND_DESTROY_POPUPWINDOW(true);
						bibMSG.add_MESSAGE(oMV);
					} else {
						//daten neu aufbauen und weiterspringen
						oThis.hm_RB_Dataobjects_Container = oThis.generateDataObjects4Mask(oMV);
						if (oMV.get_bIsOK()) {
							RB_DataobjectsCollector do_next = oThis.hm_RB_Dataobjects_Container.get(oThis.hm_RB_Dataobjects_Container.get_nextKey(cActualKey));
							if (do_next!=null) {
								oMV.add_MESSAGE(oThis.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(do_next));
								if (oMV.get_bIsOK()) {
									
									//dann den aktuellen dataobjectsCollector in die transportmap eintrage
									oThis.m_transportHashMap._setMaskDataObjectsCollector(do_next);
									
									//String id = ((RB_DataobjectsCollector_V2)do_next).get_v2(oThis.m_transportHashMap.getLeadingMaskKey()).get_rec20().get_key_value();
									//2019-01-04: anpassung an RB_DataobjectsCollector_V21
									String id = oThis.findIdOfLeadingMask(do_next);
									//2019-01-04: anpassung ende

									
									oThis.m_transportHashMap.getNavigationList().Mark_ID_IF_IN_Page(id);

									
									oThis.refresh_MaskBedienPanel();
								} 
							} else {
								oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in der Datenintegrität, Änderung von aussen !")));
							}
						}
						if (oMV.get_bHasAlarms()) {
							//wenn beim neuaufbau und blaettern was schieflaeuft, dann meldung und raus
							oThis.getTransportHashMap().getRBModulContainerMask().CLOSE_AND_DESTROY_POPUPWINDOW(false);
						}
						
						bibMSG.add_MESSAGE(oMV);
					}
					
				} else {
					bibMSG.add_MESSAGE(oMV);
				}
			}
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
			RB_BtV4_List2Mask oThis = RB_BtV4_List2Mask.this;
			
			//actualkey holen
			String cActualKey = oThis.hm_RB_Dataobjects_Container.key_of(oThis.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector());
			//dataset neu bauen
			oThis.hm_RB_Dataobjects_Container = oThis.generateDataObjects4Mask(bibMSG.MV());
			
			if (bibMSG.get_bIsOK()) {
				String newKey = this.vor?oThis.hm_RB_Dataobjects_Container.get_nextKey(cActualKey):oThis.hm_RB_Dataobjects_Container.get_previousKey(cActualKey);
				
				RB_DataobjectsCollector contNew = oThis.hm_RB_Dataobjects_Container.get(newKey);
				
				if (contNew!=null) {
					bibMSG.add_MESSAGE(oThis.getTransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(contNew));
					oThis.m_transportHashMap._setMaskDataObjectsCollector(contNew);
					
					//String id = ((RB_DataobjectsCollector_V2)contNew).get_v2(oThis.m_transportHashMap.getLeadingMaskKey()).get_rec20().get_key_value();
					//2019-01-04: anpassung an RB_DataobjectsCollector_V21
					String id = oThis.findIdOfLeadingMask(contNew);
					//2019-01-04: anpassung ende

					
					oThis.m_transportHashMap.getNavigationList().Mark_ID_IF_IN_Page(id);
					
					oThis.refresh_MaskBedienPanel();
				}
			}
			if (bibMSG.get_bHasAlarms()) {
				//abbruch
				oThis.getTransportHashMap().getRBModulContainerMask().CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
	}



	public E2_Grid get_grid4MaskExternal() {
		return gridButtonListAddons;
	}

	public void set_hm_RB_Dataobjects_Container(RB_hm_multi_DataobjectsCollector hm_rb_Dataobjects_Container) {
		this.hm_RB_Dataobjects_Container = hm_rb_Dataobjects_Container;
	}

	public boolean isUsedToEdit() {
		return this.IsUsedToEdit;
	}
	
	// 2020-09-21 Manfred
	public void setIsUsedToEdit(boolean bEdit) {
		this.IsUsedToEdit = bEdit;
		
	}

	
	public boolean get_bAutoRefreshList() {
		return autoRefreshList;
	}

	
	public void set_bAutoRefreshList(boolean autoRefreshList) {
		this.autoRefreshList = autoRefreshList;
	}
	
	public RB_BtV4_List2Mask _setStandardMessageWhenNothingSelected() {
		this.add_GlobalValidator(new XX_ActionValidator_NG() {
			
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				if (RB_BtV4_List2Mask.this.getTransportHashMap().getNavigationList().get_vSelectedIDs_Unformated().size()==0) {
					return new MyE2_MessageVector()._add(new MyE2_Alarm_Message(S.ms("Bitte mindestens eine Zeile auswählen !")));
				}
				return null;
			}
		});
		return this;
	}
	
	public E2_Grid getGrid4Mask() {
		return gridButtonListContainer;
	}

	
	private String findIdOfLeadingMask(RB_DataobjectsCollector collector) throws myException {
		if (collector instanceof RB_DataobjectsCollector_V2) {
			return ((RB_DataobjectsCollector_V2)collector).get_v2(this.m_transportHashMap.getLeadingMaskKey()).get_rec20().get_key_value();
		} else if (collector instanceof RB_DataobjectsCollector_V21) {
			return ((RB_DataobjectsCollector_V21)collector).get_v21(this.m_transportHashMap.getLeadingMaskKey()).get_key_value();
		} else if (collector instanceof RB_DataobjectsCollector_V22) {
			return ((RB_DataobjectsCollector_V22)collector).get_v22(this.m_transportHashMap.getLeadingMaskKey()).get_key_value();
		}
		throw new myException(this, "Systemerror: only RB_DataobjectsCollector_V2/V21/V22 allowed");
	}
	/**
	 * @return the showSaveAndReOpenButton
	 */
	public boolean isShowSaveAndReOpenButton() {
		return showSaveAndReOpenButton;
	}
	/**
	 * @param showSaveAndReOpenButton the showSaveAndReOpenButton to set
	 */
	public RB_BtV4_List2Mask _setShowSaveAndReOpenButton(boolean showSaveAndReOpenButton) {
		this.showSaveAndReOpenButton = showSaveAndReOpenButton;
		return this;
	}
	
	
	
	public RB_BtV4_List2Mask _addObjectAccessor(IF_ObjectAccessor<RB_BtV4_List2Mask> accessor ){
		vObjAccessor.add(accessor);
		return this;
	}
	
	
	
	/**
	 * @param objFormatter the objFormatter to set
	 */
//	public void setObjFormatter(IF_BtV4_StandardButtonAccess objFormatter) {
//		this.objFormatter = objFormatter;
//	}
	public E2_Button getBtSaveAndNext() {
		return btSaveAndNext;
	}
	public E2_Button getBtSaveAndReopen() {
		return btSaveAndReopen;
	}
	public E2_Button getBtCancel() {
		return btCancel;
	}
	public E2_Button getBtNext() {
		return btNext;
	}
	public E2_Button getBtBack() {
		return btBack;
	}
	

///*test*/
//	private void showActualId() {
//		try {
//			/*test*/        RB_KM keyleader = this.m_transportHashMap.getLeadingMaskKey();
//			RB_Dataobject   doB = 			this.m_transportHashMap.getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector().rb_Actual_DataobjectCollector().get(keyleader);
//			RecT test = (RecT)doB;
//			Long l__id = ((RecT)doB).getIdLong();
//
//			String id_edit = ""+doB.get_RecORD().get_PRIMARY_KEY_VALUE();
//			
//			DEBUG._print("ID lsdkfsalkdfkla: > "+id_edit);
//		} catch (Exception e) {
////			e.printStackTrace();
//			DEBUG._print("ID lsdkfsalkdfkla: Fehler ");
//		}
//	}
//	
	
}
