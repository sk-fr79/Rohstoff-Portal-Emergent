package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonWithKey;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.KeyStrokeListener;

public abstract class XX_ButtonActionAgent_FromListToMask extends XX_ActionAgent
{
	private E2_NavigationList 				oNavigationList = null;
	private E2_BasicModuleContainer_MASK	oMaskContainer = null;

	
	private MyString 			cNameOfAction = null;
	private boolean 			bMultiOperationAlowed = false;
	private boolean				bShowLeftRightButtons = false;
	private boolean 			bAllowEmptyIdVector	= false;
	
	private String 				cActualID_Unformated = "";
	private Vector<String> 		vIDs_Liste_Unformated = new Vector<String>();
	
	/*
	 * button, der das ganze aus der liste gestartet hat, wird benoetigt fuer validierung z.b. bei
	 * multioperationen
	 */
	private MyE2_Button			oOwnButton	= null;

	

	/*
	 * falls beim springen auf den vorigen/naechsten eine gesonderte meldung erforderlich ist,
	 * z.b. Überspringe Bearbeitung ohne Speichern o.a., dann wird dies hier als MyString hinterlegt
	 */
	private MyString 			cTextJumpToNext = null;
	
	
	
	/**
	 * @param actionName
	 * @param onavigationList
	 * @param omaskContainer
	 * @param oownButton
	 * @param omessageagent
	 * @param bmultioperationAllowed
	 * @param bshowLeftRightButtons
	 * @param ballowEmtyID_Vector
	 */
	public XX_ButtonActionAgent_FromListToMask(	MyString 						actionName,
												E2_NavigationList 				onavigationList,
												E2_BasicModuleContainer_MASK 	omaskContainer,
												MyE2_Button						oownButton,
												boolean 						bmultioperationAllowed,
												boolean 						bshowLeftRightButtons, 
												boolean 						ballowEmtyID_Vector)
	{
		super();
		this.oMaskContainer = omaskContainer;
		this.oNavigationList = onavigationList;

		this.cNameOfAction = actionName;
		this.bMultiOperationAlowed = bmultioperationAllowed;
		this.bAllowEmptyIdVector = ballowEmtyID_Vector;
		this.bShowLeftRightButtons = bshowLeftRightButtons;
		this.oOwnButton = oownButton;
	}

	public void executeAgentCode(ExecINFO oExecInfo) throws myException
	{
		
		this.oMaskContainer.set_oNavigationListWhichBelongsToTheMask(this.oNavigationList);
		
		/*
		 * nachschauen, ob das fenster aus derm E2_MaskContainer null ist, wenn ja, dann wird 
		 * initialisiert. 
		 */
		boolean bWindowIsThere = true;
		
		if (this.oMaskContainer.get_oWindowPane() == null)
			bWindowIsThere = false;

		this.vIDs_Liste_Unformated.removeAllElements();
		//2013-05-13: wenn eine Selektion nur eine Zeile enthaelt, diese aber nicht markiert ist, dann wird diese markiert und benutzt 
		//ALT:  this.vIDs_Liste_Unformated.addAll(this.oNavigationList.get_vSelectedIDs_Unformated());
		this.vIDs_Liste_Unformated.addAll(this.oNavigationList.get_vSelectedIDs_Unformated_Select_the_one_and_only());
		 
		
		if (bibMSG.get_bIsOK())
		{
		
			/*
			 * an der stelle pruefen, ob ein validator vorhanden ist, damit, falls der erste datensatz
			 * gesperrt ist, gar kein fenster auf geht. Falls erste der zweite datensatz betroffen ist,
			 * geht dann das fenster zu
			 */
			if (!bWindowIsThere && this.vIDs_Liste_Unformated.size()>0)
			{
				String cFirstID = (String)this.vIDs_Liste_Unformated.get(0);
				bibMSG.add_MESSAGE(this.get_oOwnButton().valid_IDValidation(bibALL.get_Vector(cFirstID)));
			}

			if (bibMSG.get_bIsOK())
			{
			
				if (this.vIDs_Liste_Unformated.size()==0 && ! this.bAllowEmptyIdVector)
				{
					MyE2_String oInfo = new MyE2_String("Bitte wählen Sie MINDESTENS EINE Zeile aus !!!");
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+" : "+oInfo.CTrans(),false),true);
				}
				else if (this.vIDs_Liste_Unformated.size()>1 && !this.bMultiOperationAlowed)
				{
					MyE2_String oInfo = new MyE2_String("Bitte wählen Sie GENAU EINE Zeile aus !!!");
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+" : "+oInfo.CTrans(),false),true);
				}
				else
				{
					if (!bWindowIsThere)
					{
						this.cActualID_Unformated = null;
						if (this.vIDs_Liste_Unformated.size()>0) this.cActualID_Unformated = (String)this.vIDs_Liste_Unformated.get(0);
						this.get_oMaskContainer().get_oRowForButtons().removeAll();
	
						/*
						 * jetzt nachschauen, ob es eine mehrfachoperation mit mehreren IDs ist, wenn ja, blaetterbuttons in der maske einfuegen
						 */
						if (this.bMultiOperationAlowed && this.bShowLeftRightButtons && this.vIDs_Liste_Unformated.size()>1)
						{
							E2_ComponentGroupHorizontal oRow = new E2_ComponentGroupHorizontal(0,new maskButtonActionForPrevious(),new maskButtonActionForNext(),E2_INSETS.I_2_0_2_0);
							this.get_oMaskContainer().get_oRowForButtons().add(oRow,new Insets(1,0,0,5));
						}
						
						this.get_oMaskContainer().get_oRowForButtons().add(this.build_ComponentWithMaskButtons(),new Insets(1,0,0,5));
						if (this.do_prepareMaskForActualID())
						{
							try
							{
								this.do_innerAction();
								if (bibMSG.get_bIsOK())
								{
									this.showPopupWindow();
								}
							}
							catch (myException ex)
							{
								ex.printStackTrace();
								bibMSG.add_MESSAGE(ex.get_ErrorMessage(),false);
							}
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+" : "+new MyE2_String("Error preparing MASK !!!").CTrans(),false),true);
						}
					}
					else
					{
						if (this.do_prepareMaskForActualID())
						{
							try
							{
								this.do_innerAction();
							}
							catch (myException ex)
							{
								ex.printStackTrace();
								XX_ButtonActionAgent_FromListToMask.this.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
								bibMSG.add_MESSAGE(ex.get_ErrorMessage(),false);
							}
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(this.cNameOfAction.CTrans()+" : "+new MyE2_String("Error preparing MASK !!!").CTrans(),false),true);
						}
					}
				}
			}
		}
	}

	public MyString 			get_cNameOfAction()							{		return cNameOfAction;	}
	public String 				get_cActualID_Unformated()					{		return cActualID_Unformated;	}
	public boolean 				get_bMultiOperationAlowed()					{		return bMultiOperationAlowed;	}
	public MyE2_Button 			get_oOwnButton()							{		return oOwnButton;	}
	public Vector<String> 		get_vIDs_Liste()							{		return vIDs_Liste_Unformated;	}
	public E2_BasicModuleContainer_MASK 	get_oMaskContainer()			{		return oMaskContainer;	}
	public E2_NavigationList 	get_oNavigationList()						{		return oNavigationList;	}

	public MyString 			get_cTextJumpToNext()						{		return cTextJumpToNext;	}
	public void 				set_cTextJumpToNext(MyString textJumpToNext)	{		this.cTextJumpToNext = textJumpToNext;	}
	

	
	public abstract boolean		do_prepareMaskForActualID()  throws myException; 	
	public abstract void		do_innerAction() throws myException;
	public abstract Component 	build_ComponentWithMaskButtons() throws myException;
	public abstract void 		showPopupWindow() throws myException;

	
	
	
	
	
	
	public boolean activate_Next_ID()
	{
		/*
		 * die naechste id im vector als actual setzen
		 */
		for (int i=0;i<this.vIDs_Liste_Unformated.size();i++)
		{
			String cTest = (String)this.vIDs_Liste_Unformated.get(i);
			if (cTest.equals(this.cActualID_Unformated))
				if (i<(this.vIDs_Liste_Unformated.size()-1))
				{
					this.cActualID_Unformated = (String)this.vIDs_Liste_Unformated.get(i+1);
					return true;
				}
		}
		return false;
		
	}
	
	public boolean activate_Previous_ID()
	{
		/*
		 * die vorige id im vector als actual setzen
		 */
		for (int i=this.vIDs_Liste_Unformated.size()-1;i>=0;i--)
		{
			String cTest = (String)this.vIDs_Liste_Unformated.get(i);
			if (cTest.equals(this.cActualID_Unformated))
				if (i>0)
				{
					this.cActualID_Unformated = (String)this.vIDs_Liste_Unformated.get(i-1);
					return true;
				}
		}
		return false;
	}

	
	
	
	
	/*
	 * jetzt die blaetter-buttons hier erzeugen als private klassen
	 */
	private class maskButtonActionForNext extends MyE2_ButtonWithKey
	{
		
		public maskButtonActionForNext()
		{
			super(E2_ResourceIcon.get_RI("right.png"), true,KeyStrokeListener.VK_RIGHT|KeyStrokeListener.SHIFT_MASK|KeyStrokeListener.CONTROL_MASK);    //NEU_09
			this.add_oActionAgent(new ownActionAgentNext());
			//this.EXT().set_oMessageAgent(XX_ButtonActionAgent_FromListToMask.this.oMessageAgent);
			this.setToolTipText(new MyE2_String("Nächster Datensatz (Shift+Pfeil rechts)").CTrans());                 //NEU_09
		}

		
		private class ownActionAgentNext extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				if (!XX_ButtonActionAgent_FromListToMask.this.activate_Next_ID())
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind am letzten Datensatz angelangt !"), false);
				else
				{
					if (XX_ButtonActionAgent_FromListToMask.this.get_cTextJumpToNext()!=null)
						bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(XX_ButtonActionAgent_FromListToMask.this.get_cTextJumpToNext().CTrans()));
					
					try
					{
						XX_ButtonActionAgent_FromListToMask.this.do_innerAction();
					}
					catch (myException ex)
					{
						XX_ButtonActionAgent_FromListToMask.this.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("XX_ButtonActionAgent_FromListToMask:maskButtonActionForNext:"));
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					}
				}
			}
		}
		
	}
	
	
	/*
	 * jetzt die blaetter-buttons hier erzeugen als private klassen
	 */
	private class maskButtonActionForPrevious extends MyE2_ButtonWithKey
	{
		
		public maskButtonActionForPrevious()
		{
			super(E2_ResourceIcon.get_RI("left.png"), true, KeyStrokeListener.VK_LEFT|KeyStrokeListener.SHIFT_MASK|KeyStrokeListener.SHIFT_MASK|KeyStrokeListener.CONTROL_MASK);  //NEU_09
			this.add_oActionAgent(new ownActionAgentPrevious());
			//this.EXT().set_oMessageAgent(XX_ButtonActionAgent_FromListToMask.this.oMessageAgent);
			this.setToolTipText(new MyE2_String("Voriger Datensatz (Shift+Pfeil links)").CTrans());                 //NEU_09
		}

		
		private class ownActionAgentPrevious extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				if (!XX_ButtonActionAgent_FromListToMask.this.activate_Previous_ID())
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Sie sind am ersten Datensatz angelangt !"), false);
				else
					if (XX_ButtonActionAgent_FromListToMask.this.get_cTextJumpToNext()!=null)
						bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(XX_ButtonActionAgent_FromListToMask.this.get_cTextJumpToNext().CTrans()));

				try
				{
					XX_ButtonActionAgent_FromListToMask.this.do_innerAction();
				}
				catch (myException ex)
				{
					XX_ButtonActionAgent_FromListToMask.this.oMaskContainer.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("XX_ButtonActionAgent_FromListToMask:maskButtonActionForNext:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
	}




}
