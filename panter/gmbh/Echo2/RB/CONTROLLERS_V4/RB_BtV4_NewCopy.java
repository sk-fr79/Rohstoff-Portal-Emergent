package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * button, um eine neu maske zu oeffnen, in der die werte eines ausgewaehlten satzes drinstehen
 * @author martin
 *
 */
public abstract class RB_BtV4_NewCopy extends E2_Button  {

	public abstract E2_Break4PopupController    			getBreak4PopupController4Save() throws myException;
	public abstract E2_Break4PopupController    			getBreak4PopupController4Cancel() throws myException;
	public abstract RB_ModuleContainerMASK 					generateMaskContainer() throws myException;
	public abstract RB_DataobjectsCollector 				generateDataObjects4MaskCopy(Long idtoCopy) throws myException;
	public abstract RB_DataobjectsCollector 				generateDataObjects4Copy(String idToCopyUf) throws myException;

	private E2_NavigationList  	NaviList = null;
	private MyE2_String  		Text4MaskTitel = new MyE2_String("Kopie ...");
	private MyE2_String  		ToolTip4Button = new MyE2_String("Kopie...");
	private E2_Grid  			grid4Mask = 			new E2_Grid()._s(3);
	private E2_Grid  			grid4MaskInternal = 	new E2_Grid()._s(3);
	private E2_Grid  			grid4MaskExternal = 	new E2_Grid()._s(3);

	private RB_ModuleContainerMASK  mask_container = null;
	
	private E2_Button 			btSave = 			null;
	private E2_Button 			btSaveAndReopen = 	null;
	private E2_Button 			btCancel = 			null;

//	private IF_BtV4_StandardButtonAccess objFormatter = null;
	private Vector<IF_ObjectAccessor<RB_BtV4_NewCopy>> vObjAccessor = new Vector<IF_ObjectAccessor<RB_BtV4_NewCopy>>();

	
	private RB_TransportHashMap  		m_transportHashMap = null;
	
	public RB_TransportHashMap getTtransportHashMap() {
		return m_transportHashMap;
	}
	
	public RB_BtV4_NewCopy _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}

	
	public RB_BtV4_NewCopy() {
		super();
		this._image(E2_ResourceIcon.get_RI("copy.png") , true);
		
		this.grid4Mask._a(this.grid4MaskInternal,new RB_gld()._ins(1));
		this.grid4Mask._a(this.grid4MaskExternal,new RB_gld()._ins(1));
		
		this.add_oActionAgent(new ownActionAgent());
	}



	
	private class ownActionAgent extends XX_ActionAgent  {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_BtV4_NewCopy 							oThis = RB_BtV4_NewCopy.this;
			RB_ModuleContainerMASK 						mask = oThis.generateMaskContainer();
			oThis.mask_container = mask;
			
			if (oThis.m_transportHashMap==null) {
				throw new myException(this,"TransportHashMap was not set !");
			}

			
			if (bibMSG.get_bIsOK()) {
				
				Long idToCopy = oThis.getIdToCopy(bibMSG.MV());
				
				if (idToCopy != null && bibMSG.MV().isOK()) {
					
					RB_DataobjectsCollector oCollector = oThis.generateDataObjects4MaskCopy(idToCopy);
					
					oThis.getTtransportHashMap().getNavigationList().Mark_ID_IF_IN_Page(idToCopy.toString());
					
					if (oCollector!=null) {
						mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(oCollector);
						if (bibMSG.get_bIsOK()) {
							mask.get_oRowForButtons().removeAll();
							mask.get_oRowForButtons().add(oThis.grid4Mask);
							oThis.grid4MaskInternal.removeAll();
							
							btSave = 			new E2_Button()	._setShapeSaveAndClose()
																._aaa(new RB_BtV4_ActionStandardSave()._setTransportHashMap(oThis.m_transportHashMap))
																._aaa(new RB_BtV4_ActionStandardCloseMask()._setTransportHashMap(oThis.m_transportHashMap))
																._aaa(new RB_BtV4_ActionStandardRefreshList()._setTransportHashMap(oThis.m_transportHashMap))
																;
	
							btSaveAndReopen = 	new E2_Button()	._setShapeSaveAndReOpen()
																._aaa(new RB_BtV4_ActionStandardSaveAndReopen() {
																	@Override
																	public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record,RB_ComponentMapCollector componentmapCollectorActual) throws myException {
																		return RB_BtV4_NewCopy.this.generateDataObjects4Copy(id_record);
																	}
																}._setTransportHashMap(oThis.m_transportHashMap))
																;
	
							btCancel = 			new E2_Button()	._setShapeCancelAndClose()
																._aaa(new RB_BtV4_ActionStandardCloseMask()._setTransportHashMap(oThis.m_transportHashMap))
																;
							
							oThis.mask_container.setButtonForClosingWindow(btCancel);
							
							E2_Break4PopupController conSave = getBreak4PopupController4Save();
							E2_Break4PopupController conCancel = getBreak4PopupController4Cancel();
							
							if (conSave!=null) {
								btSave.setBreak4PopupController(conSave);
								//20200206 breakcontroller auch bei saveandreopen
								btSaveAndReopen.setBreak4PopupController(conSave);
							}
							if (conCancel!=null) {
								btCancel.setBreak4PopupController(conCancel);
							}
							
							oThis.grid4MaskInternal._a(btSave, 				new RB_gld()._ins(1, 1, 2, 1));
							oThis.grid4MaskInternal._a(btSaveAndReopen, 	new RB_gld()._ins(1, 1, 2, 1));
							oThis.grid4MaskInternal._a(btCancel,			new RB_gld()._ins(1, 1, 2, 1));
							
							//20181105: aktuelle maskbuttons in die Transporthashmap schreiben
							m_transportHashMap._setButtonMaskCancel(btCancel);
							m_transportHashMap._setButtonMaskSave(btSave);
							m_transportHashMap._setButtonMaskSaveAndReload(btSaveAndReopen);
							
							
//							if (objFormatter != null) {
//								objFormatter.doAnythingWith(oThis);
//							}
							for (IF_ObjectAccessor<RB_BtV4_NewCopy> o: vObjAccessor) {
								o.doAnythingWith(oThis);
							}
							
							mask.CREATE_AND_SHOW_POPUPWINDOW(oThis.Text4MaskTitel);
							
							//hier werden jetzt die rec20-objecte gegen leere ausgetausch
							for (RB_Dataobject dob: oCollector) {
								if (dob instanceof RB_Dataobject_V2) {
									((RB_Dataobject_V2)dob).set_implicit_status_new();
									((RB_Dataobject_V2)dob).set_actualMASK_STATUS(MASK_STATUS.NEW_COPY);
								} else if (dob instanceof RB_Dataobject_V21) {
									((RB_Dataobject_V21)dob).set_implicit_status_new();
									((RB_Dataobject_V21)dob).set_actualMASK_STATUS(MASK_STATUS.NEW_COPY);
								} else if (dob instanceof RB_Dataobject_V22) {
									((RB_Dataobject_V22)dob).set_implicit_status_new();
									((RB_Dataobject_V22)dob).set_actualMASK_STATUS(MASK_STATUS.NEW_COPY);
								}
							}
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
	
	
	public MyE2_String get_ToolTip4Button() {
		return ToolTip4Button;
	}


	public void set_ToolTip4Button(MyE2_String toolTip4Button) {
		ToolTip4Button = toolTip4Button;
		this.setToolTipText(ToolTip4Button.CTrans());
	}
		
	public E2_Grid get_grid4MaskExternal() {
		return grid4MaskExternal;
	}

	public E2_Grid get_grid4MaskInternal() {
		return grid4MaskExternal;
	}


	public void set_text4MaskTitel(MyE2_String text4MaskTitel) {
		Text4MaskTitel = text4MaskTitel;
	}

	
	/**
	 * holt die id zum kopieren, kann ueberschrieben werden
	 * @return
	 */
	public Long getIdToCopy(MyE2_MessageVector mv) throws myException {
		
		if (this.m_transportHashMap.getNavigationList()!=null) {

			Long id = null;
			
			Vector<String> v = this.m_transportHashMap.getNavigationList().get_vSelectedIDs_Unformated();
			
			if (v.size()==0) {
				mv._addAlarm(S.ms("Sie müssen genau eine Zeile zum Kopieren auswählen !"));
			} else if (v.size()==1) {
				MyLong l = new MyLong(v.get(0));
				if (l.isOK()) {
					id = l.get_oLong();
				} else {
					mv._addAlarm(S.ms("Ich konnte keine ID finden !"));
				}
			} else {
				mv._addAlarm(S.ms("Sie dürfen nur eine Zeile zum Kopieren auswählen !"));
			}
			return id;
		}
		
		throw new myException(this,"NavigationList is not in the transport-hashmap");
		
	}
	
	/**
	 * @param objFormatter the objFormatter to set
	 */
	public RB_BtV4_NewCopy _addObjectAccessor(IF_ObjectAccessor<RB_BtV4_NewCopy> accessor ){
		vObjAccessor.add(accessor);
		return this;
	}
	
	
	//	public void setObjFormatter(IF_BtV4_StandardButtonAccess objFormatter) {
//		this.objFormatter = objFormatter;
//	}
	public E2_Button getBtSave() {
		return btSave;
	}
	public E2_Button getBtSaveAndReopen() {
		return btSaveAndReopen;
	}
	public E2_Button getBtCancel() {
		return btCancel;
	}
	
}
