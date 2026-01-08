package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_ContentPane_NUMBER_ONE;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public class CAL_ModuleContainer_MASK extends E2_BasicModuleContainer_MASK 
{
	private String 					cSTATUS_MASKE = null;
	private CAL_BasicModuleContainer	oCalendarBasicRow = null;              // die tagesansicht muss bei jedem speichern neu aufgebaut werden
	private maskButtonDelete 		oButtonDelete = new maskButtonDelete();
	
	
	public CAL_ModuleContainer_MASK(String STATUS_MASKE,	CAL_BasicModuleContainer  oCalendarBasicRow)  throws myException
	{
		super();
		
		this.cSTATUS_MASKE = STATUS_MASKE;
		this.oCalendarBasicRow = oCalendarBasicRow;
		
		if (!(	cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ))
			throw new myException("MASK_ModuleContainer:set_STATUS:Status is not allowed !!");
		
		
		
		MyE2_Grid oGridWithFields = new  MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		
		
		CAL_ComponentMAP_MASK_TERMIN 		oComponentMapTermin = new CAL_ComponentMAP_MASK_TERMIN(this);
		CAL_ComponentMAP_MASK_TERMIN_USER 	oComponentMapTerminUser = oComponentMapTermin.get_E2_ComponentMAP_TerminUser();

		E2_MaskFiller oFiller = new E2_MaskFiller(	oComponentMapTermin,
													oComponentMapTerminUser,null,new Insets(2,2,10,2),new Insets(2,2,2,2),null);
		
		oFiller.add_Line(oGridWithFields,new MyE2_String("Tag"),1,"DATUM",2);
		oFiller.add_Line(oGridWithFields,new MyE2_String("Zeit von"),1,"ZEIT_VON",2);
		oFiller.add_Line(oGridWithFields,new MyE2_String("Zeit bis"),1,"ZEIT_BIS",2);
		
		oFiller.add_Line(oGridWithFields,new MyE2_String("Kurztext"),1,"KURZTEXT",2);
		oFiller.add_Line(oGridWithFields,new MyE2_String("Langtext"),1,"LANGTEXT",2);
		oFiller.add_Line(oGridWithFields,new MyE2_String("Langtext privat"),1,"LANGTEXT_PRIVATE",2);
		
		oFiller.add_Line(oGridWithFields,new MyE2_String("Verteiler"),1,CAL_ComponentMAP_MASK_TERMIN_USER.HASHKEY_GRID_VERTEILER,2);
		
		this.INIT(oComponentMapTermin,oGridWithFields,new Extent(800),new Extent(600));
		this.add_SubTableComponentMAP(oComponentMapTerminUser);
		
		// this.get_vCombinedComponentMAPs().set_oTransactionTrigger(new ownTransActionTrigger());
		
		if (this.cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
		{
			this.get_oRowForButtons().add(new maskButtonSaveEdit(),E2_INSETS.I_2_2_2_2);
			this.get_oRowForButtons().add(new maskButtonCancel(),E2_INSETS.I_2_2_2_2);
		}
		else
		{
			this.get_oRowForButtons().add(new maskButtonSaveEdit(),E2_INSETS.I_2_2_2_2);
			this.get_oRowForButtons().add(new maskButtonCancel(),E2_INSETS.I_2_2_2_2);
			
			this.get_oRowForButtons().add(this.oButtonDelete,E2_INSETS.I_2_2_2_2);
		}
		
	}
	
//	
//	private class resfresh_TerminWarner 
//	{
//
//		public resfresh_TerminWarner() 
//		{
//			super();
//			
//			E2_ContentPane oPaneBase= 
//				new E2_RecursiveSearchParent_ContentPane_NUMBER_ONE().get_FoundPane();
//			
//			Vector<Component> vComponents = new E2_RecursiveSearch_Component(oPaneBase,bibALL.get_Vector(ContainerAddon_TerminUndTodos.class.getName()),null).get_vAllComponents();
//			
//			if (vComponents.size()>0)
//			{
//				for (int i=0;i<vComponents.size();i++)
//				{
//					((ContainerAddon_TerminUndTodos)vComponents.get(i)).RefreshInfo();
//				}
//			}
//			
//			DEBUG.System_println(""+vComponents.size(), "");
//			
//		}
//		
//	}
//	
//	
	/*
	 * der save-button fuer die maske
	 */
	private class maskButtonSaveEdit extends MyE2_ButtonWithKey
	{
		
		public maskButtonSaveEdit()
		{
			super(E2_ResourceIcon.get_RI("save.png"), true,KeyStrokeListener.SHIFT_MASK | KeyStrokeListener.VK_RETURN);
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{
				
				CAL_ModuleContainer_MASK oThis = CAL_ModuleContainer_MASK.this;

				E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	CAL_ModuleContainer_MASK.this.get_vCombinedComponentMAPs();

				try
				{
					E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(CAL_ModuleContainer_MASK.this,null);
					
					Vector<String> vSQL_Statements = new Vector<String>();
					
					bibMSG.add_MESSAGE(oSaveMask.doSaveMask_DUMMY(vSQL_Statements, false));
					
					if (bibMSG.get_bIsOK())
					{
						// jetzt den Benutzer-verteiler aussuchen
						E2_ComponentMAP oMapTERMIN_USER = (E2_ComponentMAP)vCombined_E2_ComponentMaps.get(1);
						CAL_COMPONENT_GridVerteiler   oGridUsers = (CAL_COMPONENT_GridVerteiler) oMapTERMIN_USER.get__Comp(CAL_ComponentMAP_MASK_TERMIN_USER.HASHKEY_GRID_VERTEILER);
						
						if (oThis.cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
						{
							vSQL_Statements.addAll(oGridUsers.get_vInsertStatements_NEW());
						}
						else
						{
							vSQL_Statements.addAll(oGridUsers.get_vUpdateStatements_EDIT(oSaveMask.get_cActualMaskID_Unformated()));
						}

						
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Statements,true));
					}
					
					if (bibMSG.get_bIsOK())
					{

//					    // den terminanzeige in der oberen zeile refreshen
//						new resfresh_TerminWarner();
						
						oSaveMask.doCancelMask_AND_RefreshNaviList();
					    CAL_ModuleContainer_MASK.this.oCalendarBasicRow.get_oColumnDay().buildDay(null);
					    CAL_ModuleContainer_MASK.this.oCalendarBasicRow.get_oCalendarGrid().baue_calender();
					    
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Der aktuelle Datensatz wurde gespeichert: "+oSaveMask.get_cActualMaskID_Unformated()));
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}
	

	
	private class maskButtonCancel extends MyE2_ButtonWithKey
	{
		
		public maskButtonCancel()
		{
			super(E2_ResourceIcon.get_RI("cancel.png"), true,KeyStrokeListener.VK_ESCAPE);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(CAL_ModuleContainer_MASK.this,null);
				oSaveMask.doCancelMask_AND_RefreshNaviList();
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Editieren abgebrochen ..."));
			}
		}
	}

	

	private class maskButtonDelete extends MyE2_Button
	{
		
		public maskButtonDelete()
		{
			super(E2_ResourceIcon.get_RI("delete.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		
		private class ownActionAgent extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				E2_BasicModuleContainer_MASK oMask = (E2_BasicModuleContainer_MASK)(new E2_RecursiveSearchParent_BasicModuleContainer().get_First_FoundContainer());
				
				String cID_TERMIN = oMask.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				try
				{
					bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(
														bibALL.get_Vector("DELETE FROM "+bibE2.cTO()+".JT_TERMIN WHERE ID_TERMIN="+cID_TERMIN),
														true));
				
					if (bibMSG.get_bIsOK())
					{
						
//					    // den terminanzeige in der oberen zeile refreshen
//						new resfresh_TerminWarner();

						bibMSG.add_MESSAGE(new MyE2_Info_Message("Termin ist gelöscht !"));
						E2_SaveMaskStandard oSaveMask = new E2_SaveMaskStandard(CAL_ModuleContainer_MASK.this,null);
						oSaveMask.doCancelMask_AND_RefreshNaviList();
						CAL_ModuleContainer_MASK.this.oCalendarBasicRow.get_oColumnDay().buildDay(null);
					    CAL_ModuleContainer_MASK.this.oCalendarBasicRow.get_oCalendarGrid().baue_calender();
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Löschen des Termins !"));
					}
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}



	public maskButtonDelete get_oButtonDelete() 
	{
		return oButtonDelete;
	}


	

	
}
