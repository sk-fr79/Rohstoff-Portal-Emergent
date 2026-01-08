package panter.gmbh.Echo2.ListAndMask.List;


import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Container.E2_ComponentDisabler;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;

public class E2_BASIC_EditListButtonPanel extends MyE2_Row
{
	/*
	 * IDs, die beim anklicken des Editbuttons aktiv waren
	 */
	private Vector<String> vID_EditActive = new Vector<String>();
	
	private MyE2_Button	buttonEditSelected = 	new MyE2_Button(E2_ResourceIcon.get_RI("edit.png"),true);
	private MyE2_Button	buttonSave = 			new MyE2_Button(E2_ResourceIcon.get_RI("save.png"),true);
	private MyE2_Button	buttonNewROW = 			new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
	private MyE2_Button	buttonCancel= 			new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"),true);
	private MyE2_Button buttonDelete=			new MyE2_Button(E2_ResourceIcon.get_RI("delete.png"),true);
	private MyE2_Button buttonRefresh=			new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"),true);
	private MyE2_Button buttonCopy=			   	new MyE2_Button(E2_ResourceIcon.get_RI("copy.png"),true);
		
	
	private E2_NavigationList 	oNaviList = null;
	private boolean 			bEditAllowed = false;
	private boolean 			bAddAllowed = false;
	private boolean 			bDeleteAllowed = false;
	
	
	private XX_ADDON_Buttons_Status_AGENT 	oADDON_BUTTON_STATUS = null;
	
	/*
	 * wenn true, dann wird eine neueingabe automatisch angehakt
	 */
	private boolean				bCheckNewLineAutomatic = false;				


	
	/*
	 * wenn true, dann wird bei neueingabe nur der teil mit leeren ComponentMAPs gezeichnet
	 */
	private boolean				bShowOnlyInputRowsAt_NEW_AND_COPY = false;
	
	
	
	private Vector<String>				vSQLStatementsBeforeDeleteStatement = new Vector<String>();
	private Vector<String>				vSQLStatementsAfterDeleteStatement = new Vector<String>();
	
	
	
	/*
	 * falls ein spezielles MyDBToolBox-objekt verwendet werden soll, muss hier eines uebergeben werden.
	 * Ist das eigene = NULL, dann wird ganz normal verfahren
	 */
	private MyDBToolBox 		oDBToolBox = null;                 
	
	
//	/*
//	 * vector sammelt alle komponenten, die von der disalbe-automatik deaktiviert wurden
//	 */
//	private Vector<Component>   vAllDisabledComponents = new Vector<Component>();
//	
	
	
	/*
	 * hilfsklasse, die das enabled/diabled-spiel beim editieren ueberwacht
	 */
	private E2_ComponentDisabler  oContainerDisabler = null;
	
	
	/*
	 * zusatzfelder fuer datenbankeingabe hier auch individuell machbar, damit datenbankfelder mit eingegebenem ID_MANDANT auch erfassbar sind
	 */
	private String[][]          arrayDBAutomatikFelder = null;
	
	
	//2013-01-15: mehrfaches loeschen auf wunsch ermoeglichen
	private boolean             bAllowDeleteMultiLines = true;



	/**
	 * @param navilist
	 * @param beditAllowed
	 * @param baddAllowed
	 * @param bdeleteAllowed
	 * @param buttonHelp NULL OK
	 * @param oPopUp NULL OK
	 * @param cMODULKENNER NULL OK
	 * @param cBUTTON_KENNER_PREFIX  (INFO vor den BUTTON-Kennern fuer die Validierer)   NULL OK
	 * @param StyleForBasicRow  NULL OK
	 * @param InsetsForButtons NULL OK
	 * @param InsetsForButtonsLeftSpace NULL OK
	 */
	public E2_BASIC_EditListButtonPanel(		E2_NavigationList 	navilist,
												boolean				beditAllowed,
												boolean 			baddAllowed,
												boolean 			bdeleteAllowed,
												MyE2_Button			buttonHelp,
												MyE2_PopUpMenue		oPopUp,
												String 				cMODULKENNER,
												String 				BUTTON_KENNER_PREFIX, 
												MutableStyle 		StyleForBasicRow, 
												Insets 				InsetsForButtons, 
												Insets 				InsetsForButtonsLeftSpace)
	{
		super();
		
		String 				cBUTTON_KENNER_PREFIX = BUTTON_KENNER_PREFIX;
		
		if (StyleForBasicRow != null)
			this.setStyle(StyleForBasicRow);
		
		Insets oInsetsNormal = new Insets(1,2,1,2);
		Insets oInsetsSpaceBefore = new Insets(20,2,1,2);

		if (InsetsForButtons != null)
			oInsetsNormal = InsetsForButtons;
		
		if (InsetsForButtonsLeftSpace != null)
			oInsetsSpaceBefore = InsetsForButtonsLeftSpace;
		
		
			
		this.oNaviList = 		navilist;
		this.bEditAllowed = 	beditAllowed;
		this.bAddAllowed = 		baddAllowed;
		this.bDeleteAllowed = 	bdeleteAllowed;
		
		if (cBUTTON_KENNER_PREFIX == null)
			cBUTTON_KENNER_PREFIX = "";
		
		/*
		 * einige buttons werden mit zugriffvalids gesperrt
		 */
		if (!bibALL.isEmpty(cMODULKENNER))
		{
			this.buttonEditSelected.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,	cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_EDIT_IN_LIST"));
			this.buttonNewROW.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,			cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_NEW_IN_LIST"));
			this.buttonDelete.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,			cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_DELETE_IN_LIST"));
			this.buttonCopy.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,			cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_COPY_IN_LIST"));
		}
		//aenderung der unbestimmten validierung
		else
		{
			this.buttonEditSelected.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_EDIT_IN_LIST"));
			this.buttonNewROW.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_NEW_IN_LIST"));
			this.buttonDelete.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_DELETE_IN_LIST"));
			this.buttonCopy.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO(cBUTTON_KENNER_PREFIX+"STANDARD_BUTTON_COPY_IN_LIST"));
		}
		
		this.buttonEditSelected.setToolTipText(new MyE2_String("Bearbeiten der markierten Zeilen ...").CTrans());
		this.buttonSave.setToolTipText(new MyE2_String("Speichern ...").CTrans());
		this.buttonNewROW.setToolTipText(new MyE2_String("Neueingabe einer Zeile ...").CTrans());
		this.buttonCopy.setToolTipText(new MyE2_String("Kopieren einer Zeile ...").CTrans());
		this.buttonCancel.setToolTipText(new MyE2_String("Abbruch der Bearbeitung ...").CTrans());
		this.buttonDelete.setToolTipText(new MyE2_String("Löschen einer Zeile ...").CTrans());
		this.buttonRefresh.setToolTipText(new MyE2_String("Neuaufbau der Liste ...").CTrans());


		//den buttonsave von der MILLISECOND-Stamp-funktion ausnehmen
		this.buttonSave.set_bMustSet_MILLISECONDSTAMP_TO_Session(false);
		
		this.buttonEditSelected.add_oActionAgent(new ownActionAgentEditSelected());
		this.buttonSave.add_oActionAgent(new ownActionAgentSave());
		this.buttonNewROW.add_oActionAgent(new ownActionAgentNewLine());
		this.buttonCopy.add_oActionAgent(new ownActionAgentCopyLine());
		this.buttonCancel.add_oActionAgent(new ownActionAgentCancelEdit());
		this.buttonRefresh.add_oActionAgent(new ownActionAgentRefresh());
		this.buttonDelete.add_oActionAgent(new ownActionAgentDelete());
		
		if (this.bEditAllowed)
		{
			this.add(this.buttonEditSelected,oInsetsNormal);
			if (this.bAddAllowed)
			{	
				this.add(this.buttonNewROW,oInsetsNormal);
				this.add(this.buttonCopy,oInsetsNormal);
			}
			this.add(this.buttonSave,oInsetsSpaceBefore);
			this.add(this.buttonCancel,oInsetsNormal);
			if (this.bDeleteAllowed)
				this.add(this.buttonDelete,oInsetsNormal);

		}
		if (oPopUp != null)
			this.add(oPopUp,oInsetsSpaceBefore);
		
		this.add(this.buttonRefresh,oInsetsSpaceBefore);
		
		if (buttonHelp != null)
			this.add(buttonHelp,oInsetsNormal);
		
		try 
		{
			this.set_BUTTON_STATUS_VIEW();
		} 
		catch (myException e) 
		{
			e.printStackTrace();
			this.setVisible(false);
			bibMSG.add_MESSAGE(e);
		}
	}
	
	
	
	/**
	 * einstellung fuer programmteile, wo nur Ansicht moeglich ist 
	 */
	public void set_ALL_ButtonsEnabled_Without_Refresh(boolean enabled) throws myException
	{
		this.buttonRefresh.set_bEnabled_For_Edit(true);
		this.buttonEditSelected.set_bEnabled_For_Edit(enabled);
		this.buttonSave.set_bEnabled_For_Edit(enabled);
		this.buttonNewROW.set_bEnabled_For_Edit(enabled);
		this.buttonCancel.set_bEnabled_For_Edit(enabled);
		this.buttonDelete.set_bEnabled_For_Edit(enabled);
		this.buttonCopy.set_bEnabled_For_Edit(enabled);
	}
	
	
	
	public void set_ALL_ButtonsDisabled()  throws myException
	{
		try
		{
			this.buttonEditSelected.set_bEnabled_For_Edit(false);
			this.buttonSave.set_bEnabled_For_Edit(false);
			this.buttonNewROW.set_bEnabled_For_Edit(false);
			this.buttonCancel.set_bEnabled_For_Edit(false);
			this.buttonDelete.set_bEnabled_For_Edit(false);
			this.buttonRefresh.set_bEnabled_For_Edit(false);
			this.buttonCopy.set_bEnabled_For_Edit(false);
		}
		catch (myException ex) 
		{
			this.setVisible(false);
			throw ex;
		}
	}
	
	public void set_BUTTON_STATUS_VIEW() throws myException
	{
		try
		{
			this.oNaviList.set_EnabledNavigationElements(true);
			this.oNaviList.set_bSortIsAllowed(true);

			this.set_ALL_ButtonsDisabled();
			this.buttonEditSelected.set_bEnabled_For_Edit(true);
			this.buttonNewROW.set_bEnabled_For_Edit(true);
			this.buttonCopy.set_bEnabled_For_Edit(true);
			this.buttonDelete.set_bEnabled_For_Edit(true);
			this.buttonRefresh.set_bEnabled_For_Edit(true);
			if (this.oADDON_BUTTON_STATUS != null)
				this.oADDON_BUTTON_STATUS.set_Status(XX_ADDON_Buttons_Status_AGENT.STATUS_LISTVIEW);
			
			if (this.oContainerDisabler != null)   // beim ersten aufruf
			{
				this.oContainerDisabler.setEnabled();
			}
			
		}
		catch (myException ex) 
		{
			this.setVisible(false);
			throw ex;
		}
	}
	

	public void set_BUTTON_STATUS_INPUTNEW()  throws myException
	{
		try
		{
			this.oNaviList.set_EnabledNavigationElements(false);
			this.oNaviList.set_bSortIsAllowed(false);

			this.set_ALL_ButtonsDisabled();
			this.buttonSave.set_bEnabled_For_Edit(true);
			this.buttonCancel.set_bEnabled_For_Edit(true);
			this.buttonRefresh.set_bEnabled_For_Edit(false);
			if (this.oADDON_BUTTON_STATUS != null)
				this.oADDON_BUTTON_STATUS.set_Status(XX_ADDON_Buttons_Status_AGENT.STATUS_INPUT_NEW);
			
			// die restilichen buttons auch noch fangen 
			Vector<Component> vNotTouch = new Vector<Component>();
			vNotTouch.add(this.buttonSave);
			vNotTouch.add(this.buttonCancel);
			
			Vector<E2_ComponentMAP> vNewRows = this.oNaviList.get_vComponentMAPS_NEW();
			for (E2_ComponentMAP oMap: vNewRows)
			{
				Vector<MyE2IF__Component> vMapComps =  oMap.get_REAL_ComponentVector();
				for (MyE2IF__Component ocomp:vMapComps)
				{
					vNotTouch.add((Component)ocomp);
				}
			}
 
			this.oContainerDisabler = new E2_ComponentDisabler(null);
			this.oContainerDisabler.INIT(vNotTouch);
			this.oContainerDisabler.setDisabled();
			
		}
		catch (myException ex) 
		{
			this.setVisible(false);
			throw ex;
		}
	}
	
	public void set_BUTTON_STATUS_EDIT() throws myException
	{
		try
		{
			this.oNaviList.set_EnabledNavigationElements(false);
			this.oNaviList.set_bSortIsAllowed(false);

			this.set_ALL_ButtonsDisabled();
			this.buttonSave.set_bEnabled_For_Edit(true);
			this.buttonCancel.set_bEnabled_For_Edit(true);
			this.buttonRefresh.set_bEnabled_For_Edit(false);
			if (this.oADDON_BUTTON_STATUS != null)
				this.oADDON_BUTTON_STATUS.set_Status(XX_ADDON_Buttons_Status_AGENT.STATUS_EDIT);

			// die restilichen buttons auch noch fangen 
			Vector<Component> vNotTouch = new Vector<Component>();
			vNotTouch.add(this.buttonSave);
			vNotTouch.add(this.buttonCancel);
			
			Vector<E2_ComponentMAP> vSelectedRows = this.oNaviList.get_vSelected_ComponentMaps();
			for (E2_ComponentMAP oMap: vSelectedRows)
			{
				Vector<MyE2IF__Component> vMapComps =  oMap.get_REAL_ComponentVector();
				for (MyE2IF__Component ocomp:vMapComps)
				{
					vNotTouch.add((Component)ocomp);
				}
			}
			
			this.oContainerDisabler = new E2_ComponentDisabler(null);
			this.oContainerDisabler.INIT(vNotTouch);
			this.oContainerDisabler.setDisabled();
			
		}
		catch (myException ex) 
		{
			this.setVisible(false);
			throw ex;
		}
	}
	
	
	public 	XX_ADDON_Buttons_Status_AGENT 	get_oADDON_BUTTON_STATUS()													{		return oADDON_BUTTON_STATUS;	}
	
	
	public boolean get_bCheckNewLineAutomatic()
	{
		return bCheckNewLineAutomatic;
	}



	public void set_bCheckNewLineAutomatic(boolean checkNewLineAutomatic)
	{
		bCheckNewLineAutomatic = checkNewLineAutomatic;
	}


	/*
	 * demarkiert alle und markiert den letzten neuen datensatz
	 */
	public void UncheckALL_Check_LastNewLine()
	{
		/*
		 * dann alle entmarkieren, ausser dem neuen
		 */
		Vector<E2_ComponentMAP> vComponentMAPs = new Vector<E2_ComponentMAP>();
		vComponentMAPs.addAll(this.oNaviList.get_vComponentMAPS());
		vComponentMAPs.addAll(this.oNaviList.get_vComponentMAPS_NEW());
		for (int i=0;i<vComponentMAPs.size();i++)
			((E2_ComponentMAP)vComponentMAPs.get(i)).setChecked_CheckBoxForList(false);
		
		Vector<E2_ComponentMAP> vNews = this.oNaviList.get_vComponentMAPS_NEW();
		if (vNews.size()>0)
			((E2_ComponentMAP)vNews.get(vNews.size()-1)).setChecked_CheckBoxForList(true);
	
	}
	

	
	
	/*
	 * beim anfuegen eines Statusagenten gleich auf standard stellen
	 */
	public 	void  set_oADDON_BUTTON_STATUS(	XX_ADDON_Buttons_Status_AGENT oaddon_button_status)		
	{	
		
		oADDON_BUTTON_STATUS = oaddon_button_status;
		try
		{
			oADDON_BUTTON_STATUS.set_Status(E2_BASIC_EditListButtonPanel.XX_ADDON_Buttons_Status_AGENT.STATUS_LISTVIEW);
		}
		catch (myException ex)
		{
			
		}
	}

	public MyE2_Button get_ButtonCancel()		{		return buttonCancel;		}
	public MyE2_Button get_ButtonCopy()			{		return buttonCopy;			}
	public MyE2_Button get_ButtonDelete()		{		return buttonDelete;		}
	public MyE2_Button get_ButtonEditSelected()	{		return buttonEditSelected;	}
	public MyE2_Button get_ButtonNewROW()		{		return buttonNewROW;		}
	public MyE2_Button get_ButtonRefresh()		{		return buttonRefresh;		}
	public MyE2_Button get_ButtonSave()			{		return buttonSave;		}



	public boolean  get_bShowOnlyInputRowsAt_NEW_AND_COPY() 	{		return bShowOnlyInputRowsAt_NEW_AND_COPY;	}
	public void  	set_bShowOnlyInputRowsAt_NEW_AND_COPY(		boolean showOnlyInputRowsAt_NEW_AND_COPY)	
	{
		bShowOnlyInputRowsAt_NEW_AND_COPY = showOnlyInputRowsAt_NEW_AND_COPY;
	}



	
	
	
	
	/*
	 * die action-agents
	 */
	
	
	/*
	 * editieren
	 */
	private class ownActionAgentEditSelected extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;

			MyE2_Button oButton = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();

			/*
			 * klassen-vector merkt sich die zu editierenden ids
			 */
			Vector<String> vID_Edit = E2_BASIC_EditListButtonPanel.this.vID_EditActive;
			vID_Edit.removeAllElements();
			
			Vector<String> vSelected = oNavList.get_vSelectedIDs_Unformated();
			
			if (vSelected.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind keine Zeilen ausgewählt zum Bearbeiten !",true),true);
			}
			else
			{
				try
				{
				
					int iOffen = 0;
					int iLocked = 0;
					if (bibMSG.get_bIsOK())
					{
						for (int i=0;i<oNavList.get_vComponentMAPS().size();i++)
						{
							E2_ComponentMAP oMap = (E2_ComponentMAP)oNavList.get_vComponentMAPS().get(i);
							
							String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
							if (vSelected.contains(cID))
							{
								bibMSG.add_MESSAGE(oButton.valid_IDValidation(bibALL.get_Vector(cID)));
								if (bibMSG.get_bIsOK())
								{
								
									/*
									 * zuerst alle anderen, evtl. offenen komponenten schliessen und neu einlesen
									 */
									oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_EDIT,true,new Boolean(true));
									vID_Edit.add(cID);
									iOffen++;
								}
								else
								{
									iLocked++;
								}
							}
						}
						if (iOffen>0)
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl Zeilen zum Editieren geöffnet: "+iOffen,true),true);
							E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_EDIT();
						}
						if (iLocked>0)
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzahl Zeilen für Editieren gesperrt: "+iLocked,true),false);
						}
					}
					
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(),true);
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("EditListButtonPanel:ownActionAgentEditSelected:Error:",false),true);
					for (int i=0;i<oNavList.get_vComponentMAPS().size();i++)
					{
						E2_ComponentMAP oMap = (E2_ComponentMAP)oNavList.get_vComponentMAPS().get(i);
						try
						{
							oMap.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_UNDEFINED);
						}
						catch (myException exx) {}
					}
				}
			}
		}
	}

	
	
//	private void Enable_Active_Elements_in_EditStatus() throws myException
//	{
//		for (int i=0;i<this.vAllDisabledComponents.size();i++)
//		{
//			((MyE2IF__Component)vAllDisabledComponents.get(i)).set_bEnabled_For_Edit(true);
//		}
//		
//	}
//	
	
	
	
	/*
	 * editieren abbrechen
	 */
	private class ownActionAgentCancelEdit extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;

			
			// die nachbearbeitung der masken-setter
			Vector<String> vID_Edit = E2_BASIC_EditListButtonPanel.this.vID_EditActive;
			
			try
			{
				for (int i=0;i<oNavList.get_vComponentMAPS().size();i++)
				{
					E2_ComponentMAP oMap = (E2_ComponentMAP)oNavList.get_vComponentMAPS().get(i);
					String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					if (vID_Edit.contains(cID))
					{
						oMap.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
					}
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("E2_BASIC_EditListButtonPanel:ownActionAgentCancelEdit:doAction:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			// fertig
			
			
			vID_Edit.removeAllElements();

			try
			{
				oNavList.get_vComponentMAPS_NEW().removeAllElements();
				Vector<String> vOldCheckedRows = oNavList.get_vSelectedIDs_Unformated();   // letzte markierungen
				oNavList.BUILD_ComponentMAP_Vector_from_ActualSegment();
				oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
				oNavList.set_SelectIDs(vOldCheckedRows);
				E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_VIEW();
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Editieren abgebrochen !"));
//				E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_VIEW();

			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("EditListButtonPanel:ownActionAgentCancelEdit:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}

		}
	}

	
	
	/*
	 * editieren speichern
	 */
	private class ownActionAgentSave extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			if (E2_BASIC_EditListButtonPanel.this.oNaviList.get_vComponentMAPS_NEW().size()==0)
				this.save_edit();
			else
				this.save_new();
			
		}
		
		
		private void save_edit()
		{
			
			E2_BASIC_EditListButtonPanel oThis = E2_BASIC_EditListButtonPanel.this;
			
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;
			Vector<String> vID_Edit = E2_BASIC_EditListButtonPanel.this.vID_EditActive;
			String cLastSavedID = null;
			
			if (vID_Edit.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es sind keine Zeilen ausgewählt zum Speichern !"));
				return;
			}

			try
			{
				Vector<String>  vSQLStack	= new Vector<String> ();
				Vector<String> 	vChanges 	= new Vector<String> ();
				
			
				for (int i=0;i<oNavList.get_vComponentMAPS().size();i++)
				{
					E2_ComponentMAP oMap = (E2_ComponentMAP)oNavList.get_vComponentMAPS().get(i);
					String cID = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					if (vID_Edit.contains(cID))
					{
						
						
						SQLResultMAP oResult = oMap.get_oInternalSQLResultMAP();			// vorige resultmap

						SQLMaskInputMAP oInputMap = oMap.MakeCompleteCycle_of_Validation_After_Input(oResult,bibMSG.MV(),E2_ComponentMAP.STATUS_EDIT);
						
						/*
						 * zuerst pruefen, ob der satz im netz geaendert wurde,
						 */
						vChanges.addAll(oMap.get_ChangedFieldResults());

						
						if (vChanges.size()>0)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurden Daten während der Bearbeitung von einem anderen Benutzer verändert !"), false);
							
							for (int k=0;k<vChanges.size();k++)
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message((String)vChanges.get(k),false), false);
						}
						
						if (bibMSG.get_bHasAlarms())
						{
							break;
						}
						
						vSQLStack.addAll(oMap.get_oSQLFieldMAP().get_SQL_UPDATESTACK(oResult,oInputMap));
						vSQLStack.addAll(oMap.get_UpdateStackFromComplexFields(oMap,oInputMap));

						
						cLastSavedID = cID;
						
						oMap.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
						
						// evtl. daughterbuttons/daughter-objekt schliessen
						oMap.set_OPEN_DaughterObjectForList_With_ToggleButton(false);
					}
				}
				

				if (bibMSG.get_bIsOK())
				{
					if (oThis.oDBToolBox == null)    //standard-fall
					{
						if (oThis.arrayDBAutomatikFelder==null)            //dann wird mit den standard-feldern gearbeitet
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLStack,true));
						}
						else
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQLStack,true,oThis.arrayDBAutomatikFelder));
						}
					}
					else
					{
						bibMSG.add_MESSAGE(oThis.oDBToolBox.ExecMultiSQLVector(vSQLStack,true));
					}
	
					if (bibMSG.get_bIsOK())
					{
						try
						{
							Vector<String> vOldCheckedRows = oNavList.get_vSelectedIDs_Unformated();   // letzte markierungen
							oNavList.BUILD_ComponentMAP_Vector_from_ActualSegment();
							oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
							oNavList.set_SelectIDs(vOldCheckedRows);
//							E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_VIEW();
							
							bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String("Anzahl Zeilen gespeichert: "+vID_Edit.size()),MyE2_Message.TYP_INFO,false),true);
							
							E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_VIEW();
							E2_BASIC_EditListButtonPanel.this.vID_EditActive.removeAllElements();
							
							if (cLastSavedID != null)
								oNavList.Mark_ID_IF_IN_Page(cLastSavedID);
							
						}
						catch (myException ex)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("EditListButtonPanel:ownActionAgentSave:Error:"));
							bibMSG.add_MESSAGE(ex.get_ErrorMessage());
						}
					}
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("EditListButtonPanel:ownActionAgentEditSelected:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}//save_edit()
		
		
		
		
		private void save_new()
		{
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;
			E2_BASIC_EditListButtonPanel oThis = E2_BASIC_EditListButtonPanel.this;

			try
			{
				
				E2_ComponentMAP oMap = (E2_ComponentMAP)oNavList.get_vComponentMAPS_NEW().get(0);
				String cCreated_Unformated_KeyOfLeadingTable = null;
				
				Vector<String> vInserts   = new Vector<String>();

				SQLMaskInputMAP oInputMap = oMap.MakeCompleteCycle_of_Validation_After_Input(null,bibMSG.MV(),E2_ComponentMAP.STATUS_NEW_EMPTY);
				
				if (bibMSG.get_bIsOK())
				{
					vInserts.addAll(oMap.get_oSQLFieldMAP().get_SQL_INSERTSTACK(oInputMap));
					vInserts.addAll(oMap.get_InsertStackFromComplexFields(oMap,oInputMap));
					
					if (oThis.oDBToolBox == null)   //normal-fall
					{
						if (oThis.arrayDBAutomatikFelder==null)            //dann wird mit den standard-feldern gearbeitet
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vInserts,true));
						}
						else
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vInserts,true,oThis.arrayDBAutomatikFelder));
						}
					}
					else
					{
						bibMSG.add_MESSAGE(oThis.oDBToolBox.ExecMultiSQLVector(vInserts,true));
					}
					
					if (bibMSG.get_bIsOK())
					{
						cCreated_Unformated_KeyOfLeadingTable = oMap.get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cLastCreatedPrimaryKEY_Unformated();
						
						/*
						 * jetzt alle internen resultmaps loeschen
						 */
						oMap.set_InternResultMAP_TO_NULL();
						Vector<String> vOldCheckedRows = oNavList.get_vSelectedIDs_Unformated();   // letzte markierungen
						oNaviList.get_vComponentMAPS_NEW().removeAllElements();   						// neu-block wieder loeschen
						oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(cCreated_Unformated_KeyOfLeadingTable);
						oNaviList.set_SelectIDs(vOldCheckedRows);
						
						E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_VIEW();
						E2_BASIC_EditListButtonPanel.this.vID_EditActive.removeAllElements();
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensatz gespeichert. ID: "+cCreated_Unformated_KeyOfLeadingTable));
						
						oNavList.Mark_ID_IF_IN_Page(cCreated_Unformated_KeyOfLeadingTable);
						
						oMap.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
					}
				}
			
			}
			catch (myExceptionDataValueNotFittingToField ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String("Vermutlich ist ein Feld der Datenbanktabelle nicht in der Liste vorhanden"),MyE2_Message.TYP_ALARM,false), false);
				bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String("das eingegeben werden MUSS. Bitte geben Sie Info an den Administrator."),MyE2_Message.TYP_ALARM,false), false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String("EditListButtonPanel:ownActionAgentNewLine:Error:",false),MyE2_Message.TYP_ALARM,false), false);
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}//save_new()
	}

	
	
	
	
	/*
	 * neue Zeile
	 */
	private class ownActionAgentNewLine extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;
			E2_BASIC_EditListButtonPanel.this.vID_EditActive.removeAllElements();
			try
			{
				oNavList.add_Row_MAP_FOR_NEW_INPUT(true,false, false);
				E2_ComponentMAP oMapNew = (E2_ComponentMAP)oNavList.get_vComponentMAPS_NEW().get(oNavList.get_vComponentMAPS_NEW().size()-1);
				oMapNew.do_MapSettings_BEFORE(E2_ComponentMAP.STATUS_NEW_EMPTY);
				oMapNew.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_NEW_EMPTY);
				
				if (E2_BASIC_EditListButtonPanel.this.bShowOnlyInputRowsAt_NEW_AND_COPY)
				{
					oNavList.FILL_GRID_From_InternalComponentMAPs(false, true);
				}
				else
				{
					oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
				}
				E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_INPUTNEW();
				
				/*
				 * dann alle entmarkieren, ausser dem neuen
				 */
				if (E2_BASIC_EditListButtonPanel.this.bCheckNewLineAutomatic)
					E2_BASIC_EditListButtonPanel.this.UncheckALL_Check_LastNewLine();
				
				//2014-04-08: hier auch die validierer-hashmap aufrufen
				oMapNew.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMapNew);

				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neuen Datensatz erfassen ..."));
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("EditListButtonPanel:ownActionAgentNewLine:Error:"));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}

		}
	}


	/*
	 * neue Zeile kopiert
	 */
	private class ownActionAgentCopyLine extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;

			Vector<String> vIDs = oNavList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Zum Kopieren einer Zeile bitte EXACT einen Datensatz auswählen !"));
			}
			else
			{
				String cID_TO_Copy = (String)vIDs.get(0);
				
				E2_BASIC_EditListButtonPanel.this.vID_EditActive.removeAllElements();
				try
				{
					oNavList.add_Row_MAP_FOR_NEW_INPUT(true,false, false);
					
					if (E2_BASIC_EditListButtonPanel.this.bShowOnlyInputRowsAt_NEW_AND_COPY)
					{
						oNavList.FILL_GRID_From_InternalComponentMAPs(false, true);
					}
					else
					{
						oNavList.FILL_GRID_From_InternalComponentMAPs(true, true);
					}
					
					E2_ComponentMAP oMapNew = (E2_ComponentMAP)oNavList.get_vComponentMAPS_NEW().get(0);
					
					oMapNew.fill_ComponentMapFromDB(null, cID_TO_Copy, E2_ComponentMAP.STATUS_NEW_COPY,true,new Boolean(true));
					oMapNew.set_InternResultMAP_TO_NULL();
					
					/*
					 * dann alle entmarkieren, ausser dem neuen
					 */
					if (E2_BASIC_EditListButtonPanel.this.bCheckNewLineAutomatic)
						E2_BASIC_EditListButtonPanel.this.UncheckALL_Check_LastNewLine();

					
					E2_BASIC_EditListButtonPanel.this.set_BUTTON_STATUS_INPUTNEW();
					
					//2012-12-13: settings-collection anwenden
					oMapNew.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMapNew);
					
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Den vorher ausgewählten Datensatz kopieren ..."));

				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("EditListButtonPanel:ownActionAgentNewLine:Error:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
				
			}
		}
	}

	

		
	

	
	private class ownActionAgentRefresh extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;
			E2_BASIC_EditListButtonPanel.this.vID_EditActive.removeAllElements();
			try
			{
				String cID_Marked = oNavList.get_cID_Unformated_Of_LastActive_Row();
				
				int iActualPage = oNavList.get_iActualPage();
				oNaviList._REBUILD_COMPLETE_LIST("");
				oNaviList.goToPage(null,iActualPage);
				/*
				 * falls die aktuelle seite leer ist, dann auf die erste seite gehen
				 */
				if (oNaviList.get_vActualID_Segment().size()==0)
					oNaviList.goToPage(null,0);
				
				if (cID_Marked != null)
					oNavList.Mark_ID_IF_IN_Page(cID_Marked);
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
		
	}
	

	
	private class ownActionAgentDelete extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_BASIC_EditListButtonPanel oThis = E2_BASIC_EditListButtonPanel.this;
			E2_NavigationList oNavList = E2_BASIC_EditListButtonPanel.this.oNaviList;
			Vector<String> vIDsToDelete = E2_BASIC_EditListButtonPanel.this.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDsToDelete.size() == 0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte "+(oThis.bAllowDeleteMultiLines?"genau einen":"mindestens einen")+" Datensatz zum Löschen auswählen !!"));
			}
			else if (vIDsToDelete.size() > 1 && !oThis.bAllowDeleteMultiLines)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte nur einen Datensatz zum Löschen auswählen !!"));
			}
			else
			{
				//String cID_To_Delete = (String)vIDsToDelete.get(0);
				
				E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
						new MyE2_String("Sicherheitsabfrage"),
						vIDsToDelete.size()==1?new MyE2_String("Datensatz löschen ?"):new MyE2_String(""+vIDsToDelete.size(),false," Datensätze löschen ?",true),
						new MyE2_String(""),
						new MyE2_String("JA"),
						new MyE2_String("NEIN"),
						new Extent(300),
						new Extent(200)
						);
				oConfirm.set_ActionAgentForOK(new ownActionAgentConfirmDelete(vIDsToDelete,oNavList));
				oConfirm.show_POPUP_BOX();
			}
				
		}
		
	}


	
	private class ownActionAgentConfirmDelete extends XX_ActionAgent
	{
		
		private Vector<String> 		vId_to_delete = null;
		private E2_NavigationList 	oNavigationList = null;
		
		
		public ownActionAgentConfirmDelete(Vector<String> v_Id_to_delete, E2_NavigationList onaviList)
		{
			super();
			this.vId_to_delete = v_Id_to_delete;
			this.oNavigationList = onaviList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_BASIC_EditListButtonPanel 	oThis = 	E2_BASIC_EditListButtonPanel.this;
			Vector<String>  				vAllSQLs = 	new Vector<String>();
			
			for (int k=0;k<this.vId_to_delete.size();k++)
			{
				String cID_To_Delete = this.vId_to_delete.get(k);
			
				Vector<String>  vStatementsVor = new Vector<String> ();
				for (int i=0;i<oThis.vSQLStatementsBeforeDeleteStatement.size();i++)
				{
					vStatementsVor.add(new String(bibALL.ReplaceTeilString( 
							    (String)oThis.vSQLStatementsBeforeDeleteStatement.get(i),
							    "#ID#",
							    cID_To_Delete
							    )));
				}
				
				Vector<String>  vStatementsNach = new Vector<String> ();
				for (int i=0;i<oThis.vSQLStatementsAfterDeleteStatement.size();i++)
				{
					vStatementsNach.add(new String(bibALL.ReplaceTeilString( 
							    (String)oThis.vSQLStatementsAfterDeleteStatement.get(i),
							    "#ID#",
							    cID_To_Delete
							    )));
				}
				
				
				Vector<String>  vSQL = new Vector<String> ();
				vSQL.addAll(vStatementsVor);
				vSQL.add("DELETE FROM "+bibE2.cTO()+"."+this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cTableName()+
						" WHERE "+this.oNavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_oSQLFieldPKMainTable().get_cFieldName()+
						" = "+cID_To_Delete);
				vSQL.addAll(vStatementsNach);
				
				vAllSQLs.addAll(vSQL);
				
			}
			
			
			if (oThis.oDBToolBox == null)
			{
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vAllSQLs,true));
			}
			else
			{
				bibMSG.add_MESSAGE(oThis.oDBToolBox.ExecMultiSQLVector(vAllSQLs,true));
			}

			if (bibMSG.get_bIsOK())
			{
				MyE2_String cMeldungsKern = null;
				if (this.vId_to_delete.size()==1)
				{
					cMeldungsKern = new MyE2_String("Datensatz mit der ID: ",true,this.vId_to_delete.get(0),false," wurde gelöscht!",true);
				}
				else
				{
					cMeldungsKern = new MyE2_String("Datensätze mit IDs: ",true,this.vId_to_delete.get(0)+", "+this.vId_to_delete.get(0)+", ...",false," wurden gelöscht!",true);
				}
				bibMSG.add_MESSAGE(new MyE2_Message(cMeldungsKern,MyE2_Message.TYP_INFO,false), false);
				try
				{
					oNavigationList.get_vActualID_Segment().removeAll(this.vId_to_delete);
					oNavigationList.BUILD_ComponentMAP_Vector_from_ActualSegment();						// liste bauen
					oNavigationList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String("EditListButtonPanel:ownActionAgentDelete:Error:"+ex.get_ErrorMessage(),false),MyE2_Message.TYP_INFO,false), false);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Message(new MyE2_String("Error Deleting Record(s): "+this.vId_to_delete.get(0)+" ...",false),MyE2_Message.TYP_ALARM,false),true);
			}
		}
	}

	
	
	
	
	
	/*
	 * ein abstracte klasse, die es ermoegliche, die schaltzustaender der buttons auf extern zudefinierte buttons zu erweitern
	 */
	public static abstract class XX_ADDON_Buttons_Status_AGENT
	{
		public static String STATUS_LISTVIEW = 		"STATUS_LISTVIEW";
		public static String STATUS_INPUT_NEW = 	"STATUS_INPUT_NEW";
		public static String STATUS_EDIT = 			"STATUS_EDIT";
		
		public abstract boolean set_Status(String cActualStatus) throws myException; 
		
	}




	public Vector<String> get_vID_EditActive()
	{
		return vID_EditActive;
	}



	/**
	 * Vector kann sql-statements aufnehmen, die in der loeschtransaktion
	 * vor dem eigentlichen loeschstatement ausgefuehrt werden.
	 * Durch ein eingefuegtes #ID# kann die row-id des zu loeschenden satzes eingefuegt werden
	 * @return Vector with Statements
	 */
	public Vector<String> get_vSQLStatementsBeforeDeleteStatement()
	{
		return vSQLStatementsBeforeDeleteStatement;
	}


	/**
	 * Vector kann sql-statements aufnehmen, die in der loeschtransaktion
	 * vor dem eigentlichen loeschstatement ausgefuehrt werden
	 * Durch ein eingefuegtes #ID# kann die row-id des zu loeschenden satzes eingefuegt werden
	 * @return Vector with Statements
	 */
	public Vector<String> get_vSQLStatementsAfterDeleteStatement()
	{
		return vSQLStatementsAfterDeleteStatement;
	}



	public void set_oDBToolBox(MyDBToolBox toolBox) 
	{
		oDBToolBox = toolBox;
	}






	public String[][] get_arrayDBAutomatikFelder() 
	{
		return arrayDBAutomatikFelder;
	}


	public void set_arrayDBAutomatikFelder(String[][] arrayDBAutomatikFelder) 
	{
		this.arrayDBAutomatikFelder = arrayDBAutomatikFelder;
	}



	public boolean get_bAllowDeleteMultiLines()
	{
		return bAllowDeleteMultiLines;
	}



	public void set_bAllowDeleteMultiLines(boolean AllowDeleteMultiLines)
	{
		this.bAllowDeleteMultiLines = AllowDeleteMultiLines;
	}



	
	
	
	
}
