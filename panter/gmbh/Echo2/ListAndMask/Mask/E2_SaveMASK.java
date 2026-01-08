package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskSaveController_IF.ENUM_SAVEMASKCONTROLLERS_POS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;



/**
 * @author martin
 * klasse, die das speichern einer maske durchfuehrt
 *
 */
public abstract class E2_SaveMASK 
{
	private 	E2_BasicModuleContainer_MASK 	oMaskContainer = null;
	private 	String 							cActualMaskID_Unformated = null;
	private 	String 							cActualMaskStatus = null;
	private 	E2_NavigationList				oNaviListToMask = null;
	
	//2018-07-19: mitschreiben der zuletzt gespeicherten id
	private     Long   							lastSavedId = null;
	
	public E2_SaveMASK(E2_BasicModuleContainer_MASK maskContainer, E2_NavigationList oNavigationList) 
	{
		super();
		this.oMaskContainer = maskContainer;
		this.oNaviListToMask = oNavigationList;
	}
	
	
	public void doSaveMask(boolean bReloadForEdit) throws myException
	{
		
		this.lastSavedId = null;
		
		/*
		 * beschaffen der kombinierten component-maps 
		 */
		E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oMaskContainer.get_vCombinedComponentMAPs();
		E2_ComponentMAP 			oLeadingMaskMAP = 				vCombined_E2_ComponentMaps.get_oE2_ComponentMAP_MAIN();
		
		//2018-07-19: einbinden der saveControllers
		bibMSG.MV()._add(this.doExecuteSaveMaskControllers(ENUM_SAVEMASKCONTROLLERS_POS.BEVORE_SAVE));
		
		//status der maske feststellen (es wird nur zwischen edit und new unterschieden (copy=new)
		this.cActualMaskStatus = E2_ComponentMAP.STATUS_NEW_EMPTY;
		if (oLeadingMaskMAP.get_oInternalSQLResultMAP()!=null) {
			cActualMaskStatus = E2_ComponentMAP.STATUS_EDIT;
		}

		if (cActualMaskStatus.equals( E2_ComponentMAP.STATUS_NEW_EMPTY))
		{
			String cNewID = vCombined_E2_ComponentMaps.SaveALL_E2_ComponentMAPS_AFTER_NEW(E2_ComponentMAP.STATUS_NEW_EMPTY);
			if (cNewID == null)
			{
				if (bibMSG.get_bIsOK()) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !!")));
				}
			}
			else
			{
				vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
				vCombined_E2_ComponentMaps.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_VIEW);
				this.cActualMaskID_Unformated = cNewID;
				
				try {
					this.lastSavedId = new MyLong(cNewID).get_oLong();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			if (!vCombined_E2_ComponentMaps.SaveALL_E2_ComponentMAPS_AFTER_EDIT(E2_ComponentMAP.STATUS_EDIT))
			{
				if (bibMSG.get_bIsOK()) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !!")));
				}
			}
			else
			{
				vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
				vCombined_E2_ComponentMaps.Requery_All_ActualResultMAPs(E2_ComponentMAP.STATUS_VIEW);
				vCombined_E2_ComponentMaps.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_VIEW);
				this.cActualMaskID_Unformated=oLeadingMaskMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				try {
					this.lastSavedId = new MyLong(this.cActualMaskID_Unformated).get_oLong();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}			


		if (bibMSG.get_bIsOK())
		{
			/*
			 * jetzt nachsehen, ob die navigationliste != null ist und dann aktualisiert werden muss
			 */
			if (this.oNaviListToMask != null)
			{
				if (cActualMaskStatus.equals( E2_ComponentMAP.STATUS_NEW_EMPTY))
				{
					this.oNaviListToMask.get_vActualID_Segment().add(0,this.cActualMaskID_Unformated);
					this.oNaviListToMask.get_vectorSegmentation().add(0,this.cActualMaskID_Unformated);
					this.oNaviListToMask.BUILD_ComponentMAP_Vector_from_ActualSegment();
					this.oNaviListToMask.FILL_GRID_From_InternalComponentMAPs(true, true);
					this.oNaviListToMask.Mark_ID_IF_IN_Page(this.cActualMaskID_Unformated);
				}
				else
				{
					try
					{
						this.oNaviListToMask.Refresh_ComponentMAP(this.cActualMaskID_Unformated,E2_ComponentMAP.STATUS_VIEW);
						this.oNaviListToMask.Mark_ID_IF_IN_Page(this.cActualMaskID_Unformated);
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("E2_SaveMASK:doSaveMask:Error Refreshing ListRow:",false), false);
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					}
				}
	
			}
		
			if (bibMSG.get_bIsOK()) {
				//2018-07-19: einbinden der saveControllers
				bibMSG.MV()._add(this.doExecuteSaveMaskControllers(ENUM_SAVEMASKCONTROLLERS_POS.AFTER_SAVE));
			}
			
		
			/*
			 * falls bReloadForEdit, dann alle felder neu laden im zustand edit,
			 * wenn es eine neueingabe ware, dann muessen die integritaetspruefer (dynamisch) intialisiert werden,
			 * das sonst evtl.vorhandene weitere innere masken nicht speicherbar sind
			 */
			if (bReloadForEdit)
			{
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,this.cActualMaskID_Unformated);
			}
		}

		if (bibMSG.get_bIsOK())
		{
			this.actionAfterSaveMask();
		}
	}
	
	
	
	
	/*
	 * fuehrt nicht das speichern der maske aus, sondern gibt in dem uebergebenen
	 * Vector die SQL-Statements gesammelt zurueck
	 */
	public MyE2_MessageVector doSaveMask_DUMMY(Vector<String> vReturnSQL_Statments, boolean bReloadForEdit) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
	
		
		/*
		 * beschaffen der kombinierten component-maps 
		 */
		E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oMaskContainer.get_vCombinedComponentMAPs();
		E2_ComponentMAP 				oLeadingMaskMAP = 				vCombined_E2_ComponentMaps.get_oE2_ComponentMAP_MAIN();
		
		
		//status der maske feststellen (es wird nur zwischen edit und new unterschieden (copy=new)
		this.cActualMaskStatus = E2_ComponentMAP.STATUS_NEW_EMPTY;
		
		if (oLeadingMaskMAP.get_oInternalSQLResultMAP()!=null)
			cActualMaskStatus = E2_ComponentMAP.STATUS_EDIT;

		if (cActualMaskStatus.equals( E2_ComponentMAP.STATUS_NEW_EMPTY))
		{
			String cNewID = vCombined_E2_ComponentMaps.SaveALL_E2_ComponentMAPS_AFTER_NEW_DUMMY(vReturnSQL_Statments,E2_ComponentMAP.STATUS_NEW_EMPTY,oMV);
			if (cNewID == null)
			{
				if (oMV.get_bIsOK())
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !!")));
			}
			else
			{
//				vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
//				vCombined_E2_ComponentMaps.set_AllComponentsEnabled_For_Edit(false,bReloadForEdit?E2_ComponentMAP.STATUS_EDIT:E2_ComponentMAP.STATUS_VIEW);
				this.cActualMaskID_Unformated = cNewID;
			}
		}
		else
		{
			if (!vCombined_E2_ComponentMaps.SaveALL_E2_ComponentMAPS_AFTER_EDIT_DUMMY(vReturnSQL_Statments, E2_ComponentMAP.STATUS_EDIT,oMV))
			{
				if (oMV.get_bIsOK())
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !!")));
			}
			else
			{
//				vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
//				
//				vCombined_E2_ComponentMaps.Requery_All_ActualResultMAPs(E2_ComponentMAP.STATUS_VIEW);
//				vCombined_E2_ComponentMaps.set_AllComponentsEnabled_For_Edit(false,bReloadForEdit?E2_ComponentMAP.STATUS_EDIT:E2_ComponentMAP.STATUS_VIEW);
				this.cActualMaskID_Unformated=oLeadingMaskMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			}
		}	
		
		
		return oMV;
	}

	

	
	
	
	

	/*
	 * schliesst die maske mit abbruch, aktualisiert aber, falls vorhanden eine liste
	 */
	public void doCancelMask_AND_RefreshNaviList() throws myException
	{
		/*
		 * beschaffen der kombinierten component-maps 
		 */
		E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oMaskContainer.get_vCombinedComponentMAPs();
		E2_ComponentMAP 			oLeadingMaskMAP = 				vCombined_E2_ComponentMaps.get_oE2_ComponentMAP_MAIN();
		
		//status der maske feststellen (es wird nur zwischen edit und new unterschieden (copy=new)
		this.cActualMaskStatus = E2_ComponentMAP.STATUS_NEW_EMPTY;
		if (oLeadingMaskMAP.get_oInternalSQLResultMAP()!=null)
			cActualMaskStatus = E2_ComponentMAP.STATUS_EDIT;

		
		
		/*
		 * jetzt nachsehen, ob die navigationliste != null ist und dann aktualisiert werden muss
		 */
		if (this.oNaviListToMask != null)
		{
			if (cActualMaskStatus.equals( E2_ComponentMAP.STATUS_EDIT))
			{
				String cID_of_MASK = oLeadingMaskMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				try
				{
					this.oNaviListToMask.Refresh_ComponentMAP(cID_of_MASK,E2_ComponentMAP.STATUS_VIEW);
					this.oNaviListToMask.Mark_ID_IF_IN_Page(cID_of_MASK);
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("E2_SaveMASK:doCancelMask:Error Refreshing ListRow:",false)));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
		this.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		
	}
	
	
	public String get_cActualMaskID_Unformated()		{			return cActualMaskID_Unformated;		}
	public String get_cMASK_STATUS()				{			return this.cActualMaskStatus;			}

	
	public abstract boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK 	MaskContainer,
												E2_vCombinedComponentMAPs 		vCombined_E2_ComponentMaps,
												String							ActualMaskStatus);
	
	
	public abstract void actionAfterSaveMask() throws myException;

	public E2_BasicModuleContainer_MASK get_oMaskContainer() 
	{
		return oMaskContainer;
	}
	
	
	/**
	 * 2018-07-19: controller fuer den speichervorgang. koennen den E2_ComponentMaps zugefuegt werden und 
	 *             werden dann in den E2_SaveMask - Objekten ausgewertet
	 */
	private MyE2_MessageVector doExecuteSaveMaskControllers(ENUM_SAVEMASKCONTROLLERS_POS position) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oMaskContainer.get_vCombinedComponentMAPs();

		for (E2_ComponentMAP map: vCombined_E2_ComponentMaps) {
			if (map instanceof E2_SaveMaskSaveController_IF) {
				E2_SaveMaskSaveController_IF controller = (E2_SaveMaskSaveController_IF) map;
				mv.add_MESSAGE(controller.checkSaveing(this,position));
			}
		}
		
		return mv;
	}


	/**
	 * 
	 * @return zuletzt gespeicherte id, falls fehler null
	 */
	public Long getLastSavedId() {
		return lastSavedId;
	}
	
	
}
