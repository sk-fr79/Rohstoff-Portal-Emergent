package panter.gmbh.Echo2.ListAndMask.List;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_ButtonListMarker extends MyE2_Button   implements MyE2IF__Component, E2_IF_Copy
{

	private 	boolean 			bIsMarked = false;   // bedeutet der marker wurde gesetzt
	private 	boolean 			bIsActive = false;   // der button wird fuer eine aktion benutzt
	
	private		E2_ResourceIcon 	oIconLabelMarked = E2_ResourceIcon.get_RI("listlabel_mark.png");
	private		E2_ResourceIcon 	oIconLabelUNMarked = E2_ResourceIcon.get_RI("listlabel_trans.png");
	private		E2_ResourceIcon 	oIconButtonMarked = E2_ResourceIcon.get_RI("listbutton_mark.png");
	private		E2_ResourceIcon 	oIconButtonUNMarked = E2_ResourceIcon.get_RI("listbutton_trans.png");
	
	public E2_ButtonListMarker()
	{
		super();
		
		this.EXT().set_oColExtent(new Extent(18));    // bekommt immer den gleichen extent
		

		/*
		 * action-agent, der markiert
		 */
//		this.add_oActionAgent(new XX_ActionAgent()
//			{
//				public void executeAgentCode(ExecINFO oExecInfo)
//				{
//					E2_ComponentMAP oMap = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_oComponentMAP();
//					oMap.set_Marker(true);
//				}
//			
//			});

		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_ComponentMAP oMap = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_oComponentMAP();
				E2_ButtonListMarker oThis = E2_ButtonListMarker.this;
				
				if (oThis.bIsMarked)
				{
					oMap.set_Marker(false);
					//new ownPopupContainer();
				}
				else
				{
					oMap.set_Marker(true);
				}
			}
		
		});

		
		this.__define();
		
		this.setFocusTraversalParticipant(false);
		
	}

	
	private class ownPopupContainer extends E2_BasicModuleContainer
	{
		public ownPopupContainer() throws myException 
		{
			super();
			this.set_bVisible_Row_For_Messages(true);
			this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(200), new Extent(80), null);
			
		}
	}
	
	
	public void __define()
	{
		if (bIsMarked)
		{
			if (bIsActive)  
				this.__setImages(this.oIconButtonMarked,this.oIconButtonMarked);
			else
				this.__setImages(this.oIconLabelMarked,this.oIconLabelMarked);
			
		}
		else
		{
			if (bIsActive)  
				this.__setImages(this.oIconButtonUNMarked,this.oIconButtonUNMarked);
			else
				this.__setImages(this.oIconLabelUNMarked,this.oIconLabelUNMarked);
		
		}

	}
	
	

	/*
	 * diese funktion wird hier auser gefecht gesetzt 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		
		/*
		 * bei neueingabe deaktiviert
		 */
		if (this.EXT().get_oComponentMAP() != null)
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
				this.setEnabled(false);

	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_ButtonListMarker oButton = new E2_ButtonListMarker();

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		if (this.get_oText()!=null) oButton.set_Text(this.get_oText());
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
		oButton.setStyle(this.getStyle());
		oButton.set_oIconButtonMarked(this.get_oIconButtonMarked());
		oButton.set_oIconButtonUNMarked(this.get_oIconButtonUNMarked());
		oButton.set_oIconLabelMarked(this.get_oIconLabelMarked());
		oButton.set_oIconLabelMarked(this.get_oIconLabelMarked());
		
		oButton.set_bIsActive(this.bIsActive);
		oButton.set_bIsMarked(this.get_bIsMarked());
		
		oButton.setLayoutData(this.getLayoutData());
		
		oButton.__define();
		oButton.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
		
		return oButton;
	}


	public boolean get_bIsActive()												{		return bIsActive;	}
	public void set_bIsActive(boolean isActive)								
	{		
		bIsActive = isActive;
		this.__define();
	}
	public boolean get_bIsMarked()												{		return bIsMarked;	}
	public void set_bIsMarked(boolean isMarked)								
	{		
		bIsMarked = isMarked;
		this.__define();
	}
	public E2_ResourceIcon get_oIconButtonMarked()								{		return oIconButtonMarked;	}
	public void set_oIconButtonMarked(E2_ResourceIcon iconButtonMarked)		{		oIconButtonMarked = iconButtonMarked;	}
	public E2_ResourceIcon get_oIconButtonUNMarked()							{		return oIconButtonUNMarked;	}
	public void set_oIconButtonUNMarked(E2_ResourceIcon iconButtonUNMarked)	{		oIconButtonUNMarked = iconButtonUNMarked;	}
	public E2_ResourceIcon get_oIconLabelMarked()								{		return oIconLabelMarked;	}
	public void set_oIconLabelMarked(E2_ResourceIcon iconLabelMarked)			{		oIconLabelMarked = iconLabelMarked;	}
	public E2_ResourceIcon get_oIconLabelUNMarked()							{		return oIconLabelUNMarked;	}
	public void set_oIconLabelUNMarked(E2_ResourceIcon iconLabelUNMarked)		{		oIconLabelUNMarked = iconLabelUNMarked;	}

	
	
}
