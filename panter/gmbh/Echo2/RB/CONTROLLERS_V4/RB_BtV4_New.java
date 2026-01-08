package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_BtV4_New extends E2_Button  {

	public abstract E2_Break4PopupController    			getBreak4PopupController4Save() throws myException;
	public abstract E2_Break4PopupController    			getBreak4PopupController4Cancel() throws myException;
	public abstract RB_ModuleContainerMASK 					generateMaskContainer() throws myException;
	public abstract RB_DataobjectsCollector 				generateDataObjects4New() throws myException;
	public abstract RB_DataobjectsCollector 				generateDataObjects4Edit(String idToCopyUf) throws myException;

	
	private E2_NavigationList  	NaviList = null;
	private MyE2_String  		Text4MaskTitel = new MyE2_String("Neuerfassung ...");
	private MyE2_String  		ToolTip4Button = new MyE2_String("Neuerfassung...");
	private E2_Grid  			grid4Mask = 			new E2_Grid()._s(3);
	private E2_Grid  			grid4MaskInternal = 	new E2_Grid()._s(3);
	private E2_Grid  			grid4MaskExternal = 	new E2_Grid()._s(3);

	private RB_ModuleContainerMASK  mask_container = null;
	
	private E2_Button 			btSave = 			null;
	private E2_Button 			btSaveAndReopen = 	null;
	private E2_Button 			btCancel = 			null;

	private boolean 			showBtnSave 			= true;
	private boolean 			showBtnSaveAndReopen 	= true;
	
	private Vector<IF_ObjectAccessor<RB_BtV4_New>> vObjAccessor = new Vector<IF_ObjectAccessor<RB_BtV4_New>>();
	
//	private IF_BtV4_StandardButtonAccess objFormatter = null;


	private RB_TransportHashMap  		m_transportHashMap = null;
	
	public RB_TransportHashMap getTtransportHashMap() {
		return m_transportHashMap;
	}
	
	
	public RB_BtV4_New _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}


	public RB_BtV4_New() {
		super();
		this._image("new.png",true);
		
		this.grid4Mask._a(this.grid4MaskInternal,new RB_gld()._ins(1));
		this.grid4Mask._a(this.grid4MaskExternal,new RB_gld()._ins(1));
		
		this.add_oActionAgent(new ownActionAgent());
	}





	
	
	private class ownActionAgent extends XX_ActionAgent  {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_BtV4_New 							oThis = RB_BtV4_New.this;
			RB_ModuleContainerMASK 				mask = oThis.generateMaskContainer();
			oThis.mask_container = 				mask;

			if (oThis.m_transportHashMap==null) {
				throw new myException(this,"TransportHashMap was not set !");
			}
			
			
			if (bibMSG.get_bIsOK()) {
				RB_DataobjectsCollector oContainer = oThis.generateDataObjects4New();
				if (oContainer!=null) {
					bibMSG.MV()._add(mask.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(oContainer));
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
																		return RB_BtV4_New.this.generateDataObjects4Edit(id_record);
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
						
						if (showBtnSave) {
							oThis.grid4MaskInternal._a(btSave, 				new RB_gld()._ins(1, 1, 2, 1));
						}
						if (showBtnSaveAndReopen) {
							oThis.grid4MaskInternal._a(btSaveAndReopen, 	new RB_gld()._ins(1, 1, 2, 1));
						}

						oThis.grid4MaskInternal._a(btCancel,			new RB_gld()._ins(1, 1, 2, 1));
						
						m_transportHashMap._setButtonMaskCancel(oThis.btCancel);
						m_transportHashMap._setButtonMaskSave(oThis.btSave);
						m_transportHashMap._setButtonMaskSaveAndReload(oThis.btSaveAndReopen);
						
						
						for (IF_ObjectAccessor<RB_BtV4_New> o: vObjAccessor) {
							o.doAnythingWith(oThis);
						}
						
						
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
	
	public RB_BtV4_New set_Text4MaskTitle(MyE2_String cMaskTitle){
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
		
	public E2_Grid get_grid4MaskExternal() {
		return grid4MaskExternal;
	}

	
	public E2_Grid getGrid4Mask() {
		return grid4Mask;
	}

	public RB_BtV4_New _setShowSaveAndReOpenButton(boolean bShow) {
		showBtnSaveAndReopen = bShow;
		return this;
	}
	
	public RB_BtV4_New setShowBtnSave(boolean bShow) {
		showBtnSave = bShow;
		return this;
	}
	
	public RB_BtV4_New _addObjectAccessor(IF_ObjectAccessor<RB_BtV4_New> accessor ){
		vObjAccessor.add(accessor);
		return this;
	}
	
	/**
	 * @param objFormatter the objFormatter to set
	 */
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
