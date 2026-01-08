package panter.gmbh.Echo2.Container;


import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchText_IN_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * eine Containerklasse zum einfachen definieren einer yes / no -abfrage OK-cancel 
 */
public class E2_ConfirmBasicModuleContainer extends E2_BasicModuleContainer     // implements WindowPaneListener, IF_MessagePanel
{

	
	private MyE2_String 			cWindowTitle = null;
	private MyE2_String 			cTextTitle = null;
	private MyE2_String				cLabelOKButton = null;
	private MyE2_String				cLabelCancelButton = null;
	private Extent 					oWidth = new Extent(350);
	private	Extent 					oHeight = new Extent(500);
	
	private MyE2_Button				buttonOK = null;
	private MyE2_Button				buttonCancel = null;

	private MyE2_Row 				oRowForButtons = new MyE2_Row();
	private MyE2_Column	 			oInnerColumn = new MyE2_Column();
	
	private Component			    ADD_ON_Component = null;               // zusatzkomponente z.B. zum eingeben von irgendwas
	
	private XX_BuildAddonComponents_4_Dialog   oAddonDialogBuilder = null;
	

	
	
	
	/**
	 * @param windowtitle
	 * @param texttitle
	 * @param innerTextblock
	 * @param labelOKButton
	 * @param labelCancelButton
	 * @param width
	 * @param height
	 * @param pane
	 */
	public E2_ConfirmBasicModuleContainer(	MyE2_String windowtitle, 
											MyE2_String texttitle, 
											MyE2_String innerTextblock, 
											MyE2_String labelOKButton, 
											MyE2_String labelCancelButton, 
											Extent width, 
											Extent height) throws myException
	{
		super();
		Vector<MyString> vInnerTextblock = new Vector<MyString>();
		if (innerTextblock!=null)
		{
			vInnerTextblock.add(innerTextblock);
		}
		
		this.define_ConfirmWindowContainer(windowtitle,texttitle,null,vInnerTextblock,null,labelOKButton,labelCancelButton,width,height, null,null);
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		
	}
	


	public E2_ConfirmBasicModuleContainer(	MyE2_String windowtitle, 
											MyE2_String texttitle, 
											MyE2_String innerTextblock, 
											XX_BuildAddonComponents_4_Dialog   AddonDialogBuilder,
											MyE2_String labelOKButton, 
											MyE2_String labelCancelButton, 
											Extent width, 
											Extent height) throws myException
	{
		super();
		Vector<MyString> vInnerTextblock = new Vector<MyString>();
		if (innerTextblock!=null)
		{
			vInnerTextblock.add(innerTextblock);
		}
		
		this.define_ConfirmWindowContainer(windowtitle,texttitle,null,vInnerTextblock,null,labelOKButton,labelCancelButton,width,height, null,AddonDialogBuilder);
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		
		this.oAddonDialogBuilder = AddonDialogBuilder;
		
	}

	
	
	
	/**
	 * @param windowtitle
	 * @param texttitle
	 * @param labelOKButton
	 * @param labelCancelButton
	 * @param width
	 * @param height
	 * @param pane
	 * @param innerTextblock
	 */
	public E2_ConfirmBasicModuleContainer(	MyE2_String windowtitle, 
											MyE2_String texttitle, 
											Vector<MyString> vInnerTextblock, 
											MyE2_String labelOKButton, 
											MyE2_String labelCancelButton, 
											Extent width, 
											Extent height) throws myException
	{
		super();
		this.define_ConfirmWindowContainer(windowtitle,texttitle,null,vInnerTextblock,null,labelOKButton,labelCancelButton,width,height, null, null);
	}




	/**
	 * @param windowtitle
	 * @param texttitle
	 * @param vInnerTextblock
	 * @param oInnerComponent
	 * @param oValidatorForOK_Button (kann evtl. eingaben auswerten und verneinen)
	 * @param labelOKButton
	 * @param labelCancelButton
	 * @param width
	 * @param height
	 * @param pane
	 */
	public E2_ConfirmBasicModuleContainer(	MyE2_String 		windowtitle, 
											MyE2_String 		texttitle, 
											Vector<MyString> 	vInnerTextblock, 
											Component 			oInnerComponent,
											XX_ActionValidator 	oValidatorForOK_Button,
											MyE2_String 		labelOKButton, 
											MyE2_String 		labelCancelButton, 
											Extent 				width, 
											Extent 				height
											) throws myException
	{
		super();
		this.define_ConfirmWindowContainer(windowtitle,texttitle,null,vInnerTextblock,oInnerComponent,labelOKButton,labelCancelButton,width,height, oValidatorForOK_Button, null);
	}



	
	
	/**
	 * @param windowtitle
	 * @param texttitle
	 * @param vInnerTextblock
	 * @param oInnerComponent
	 * @param oValidatorForOK_Button (kann evtl. eingaben auswerten und verneinen)
	 * @param labelOKButton
	 * @param labelCancelButton
	 * @param width
	 * @param height
	 * @param pane
	 */
	public E2_ConfirmBasicModuleContainer(	MyE2_String 		windowtitle, 
											MyE2_String 		texttitle, 
											MyE2_MessageVector 	vMessageBlock, 
											Vector<MyString> 	vInnerTextblock, 
											Component 			oInnerComponent,
											XX_ActionValidator 	oValidatorForOK_Button,
											MyE2_String 		labelOKButton, 
											MyE2_String 		labelCancelButton, 
											Extent 				width, 
											Extent 				height
											) throws myException
	{
		super();
		this.define_ConfirmWindowContainer(windowtitle,texttitle,vMessageBlock,vInnerTextblock,oInnerComponent,labelOKButton,labelCancelButton,width,height, oValidatorForOK_Button, null);
	}


	
	
	
	
	private void  define_ConfirmWindowContainer(	MyE2_String windowtitle, 
													MyE2_String texttitle, 
													MyE2_MessageVector oMV,
													Vector<MyString> vInnerTextblock, 
													Component AddOnComponent, 
													MyE2_String labelOKButton, 
													MyE2_String labelCancelButton, 
													Extent width, 
													Extent height, 
													XX_ActionValidator oValidatorForOK_Button,
													XX_BuildAddonComponents_4_Dialog   oAddonDialogBuilder) throws myException
	{
	
		
		
		cWindowTitle = windowtitle;
		cTextTitle = texttitle;
		cLabelOKButton = labelOKButton;
		cLabelCancelButton = labelCancelButton;
		
		if (width != null) this.oWidth = width;
		if (height != null) this.oHeight = height;
		
		if (this.cTextTitle != null)
		{
			MyE2_LabelWrap labelTitle = new MyE2_LabelWrap(this.cTextTitle);
			labelTitle.setFont(new E2_FontBold(2));
			oInnerColumn.add(labelTitle,E2_INSETS.I_10_10_10_10);
		}
		
		//zuerst die messages
		if (oMV != null && oMV.size()>0)
		{
			for (int i=0;i<oMV.size();i++)
			{
				oInnerColumn.add(oMV.get(i).get_MessageLabel(),E2_INSETS.I_10_2_10_2);
			}
		}

		
		//dann die normalen texte
		if (vInnerTextblock != null && vInnerTextblock.size()>0)
		{
			for (int i=0;i<vInnerTextblock.size();i++)
			{
				String cTrans = vInnerTextblock.get(i).CTrans();

				//in den text wird das zeichen <br> als trenner angeben (java-konvention)
				// die trenner-methode nimmt aber nur einzeilige trenner, deshalb zuerst ersetzen dieser <br> gegen sonderzeichen
				cTrans = bibALL.ReplaceTeilString(cTrans, "<br>", "µ");
				
				Vector<String> vZeilen = bibALL.TrenneZeile(cTrans, "µ");
				for (String cHelp: vZeilen)
				{
					MyE2_Label labelblock = new MyE2_Label(cHelp);
					labelblock.setLineWrap(true);
					labelblock.setFont(new E2_FontPlain(0));
					oInnerColumn.add(labelblock,E2_INSETS.I_10_2_10_2);
				}
			}
		}

		this.ADD_ON_Component = AddOnComponent;
		if (AddOnComponent != null)
			oInnerColumn.add(AddOnComponent,E2_INSETS.I_10_2_10_2);
		
		if (oAddonDialogBuilder!=null)
		{
			Component  oComp = oAddonDialogBuilder.build_AddonComponent(this);
			if (oComp!=null)
			{
				oInnerColumn.add(oComp);
			}
		}

		
		this.oRowForButtons = new MyE2_Row();
		if (this.cLabelOKButton != null)
		{
			this.buttonOK = 	new MyE2_Button(this.cLabelOKButton);
			this.oRowForButtons.add(buttonOK, E2_INSETS.I_10_0_10_0);
			buttonOK.add_oActionAgent(new ownActionAgentForClose());
			if (oValidatorForOK_Button != null) buttonOK.add_GlobalValidator(oValidatorForOK_Button);
		}
		
		if (this.cLabelCancelButton != null)
		{
			this.buttonCancel = new MyE2_Button(this.cLabelCancelButton);
			this.oRowForButtons.add(buttonCancel, E2_INSETS.I_10_0_10_0);
			buttonCancel.add_oActionAgent(new ownActionAgentForClose());
		}
			
		
	}

	
	
	
	public void set_ActionAgentForOK(XX_ActionAgent oAgent)
	{
		if (this.buttonOK != null)
		{
			this.buttonOK.remove_AllActionAgents();
			this.buttonOK.add_oActionAgent(oAgent);
			this.buttonOK.add_oActionAgent(new ownActionAgentForClose());
		}
	}
	

	public void set_ActionAgentForOK(Vector<XX_ActionAgent> vAgent)
	{
		if (this.buttonOK != null)
		{
			this.buttonOK.remove_AllActionAgents();
			for (int i=0;i<vAgent.size();i++)
			{
				this.buttonOK.add_oActionAgent(vAgent.get(i));
			}
			this.buttonOK.add_oActionAgent(new ownActionAgentForClose());
		}
	}

	
	public void add_ActionAgentForOK_AfterCloseEvent(XX_ActionAgent oAgent)
	{
		if (this.buttonOK != null)
		{
			this.buttonOK.add_oActionAgent(oAgent);
		}
	}


	
	public void add_ActionAgentForOK_Before_CloseEvent(XX_ActionAgent oAgent)
	{
		if (this.buttonOK != null)
		{
			this.buttonOK.get_vActionAgents().add(this.buttonOK.get_vActionAgents().size()-1, oAgent);
		}
	}

	

	public void show_POPUP_BOX()
	{
		
		this.add(this.oInnerColumn);
		this.set_Component_To_ButtonPane(this.oRowForButtons);
		try
		{
			this.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(this.oWidth,this.oHeight,this.cWindowTitle);
			/*
			 * jetzt nachschauen, ob es in der (falls vorhanden) uebergebenen sonderkomponente ein TextObjekt gibt,
			 * wenn ja den focus drauf setzen
			 */
			if (this.ADD_ON_Component != null)
			{
				Vector<MyE2IF__Component> vTextFields = (new E2_RecursiveSearchText_IN_Component(this.ADD_ON_Component)).get_vTextObjects();
				if (vTextFields.size() >0 )
				{
					if (vTextFields.get(0) instanceof TextField)
					{
						if (((TextField)vTextFields.get(0)).isEnabled())
							bibE2.get_ActiveInstance().setFocusedComponent((TextField)vTextFields.get(0));
					}
					if (vTextFields.get(0) instanceof TextArea)
					{
						if (((TextArea)vTextFields.get(0)).isEnabled())
							bibE2.get_ActiveInstance().setFocusedComponent((TextArea)vTextFields.get(0));
					}
					
				}
			}
			
			//jetzt ganz am schluss (falls ein oAddonDialogBuilder vorhanden ist, den zugehoerigen action-agent ein das ok einbauen)
			if (this.oAddonDialogBuilder!=null)
			{
				Vector<XX_ActionAgent> vAgents = this.oAddonDialogBuilder.get_vActionAgents_from_AddonComponent(this);
				for (int i=0;i<vAgents.size();i++)
				{
					this.get_oButtonOK().add_oActionAgent(vAgents.get(i));
				}
			}

		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}

		
	}
	
	
	

		
	
	private class ownActionAgentForClose extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ConfirmBasicModuleContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	

	public void set_SplitPixelsFromBottom(int Pixels)
	{
		int iPixels = Pixels;
		
		if (iPixels >= this.oHeight.getValue())
			iPixels = this.oHeight.getValue()/2;
		
		// this.oWindowSplitPane.get_oSplitPane().setSeparatorPosition(new Extent(this.oHeight.getValue()-iPixels));
		this.get_oSplitPane().setSeparatorPosition(new Extent(this.oHeight.getValue()-iPixels));
	}


	public MyE2_Button get_oButtonOK()
	{
		return buttonOK;
	}


	public MyE2_Button get_oButtonCancel()
	{
		return buttonCancel;
	}
	
	
	public XX_BuildAddonComponents_4_Dialog get_oAddonDialogBuilder()
	{
		return oAddonDialogBuilder;
	}

	
	
	

//	public void set_oAddonDialogBuilder(XX_BuildAddonComponents_4_Dialog oAddonDialogBuilder)
//	{
//		this.oAddonDialogBuilder = oAddonDialogBuilder;
//	}


	

	public static abstract class XX_BuildAddonComponents_4_Dialog
	{
		public abstract Component                build_AddonComponent(E2_ConfirmBasicModuleContainer oConfirmContainer) throws myException;
		public abstract Vector<XX_ActionAgent>   get_vActionAgents_from_AddonComponent(E2_ConfirmBasicModuleContainer oConfirmContainer)  throws myException;
	}


	//2012-04-11: die zusatzkomponente veroeffentlichen
	public Component get_ADD_ON_Component()
	{
		return ADD_ON_Component;
	}
	

	
}
