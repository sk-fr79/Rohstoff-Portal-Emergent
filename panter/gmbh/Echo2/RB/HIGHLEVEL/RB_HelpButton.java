package panter.gmbh.Echo2.RB.HIGHLEVEL;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;

public abstract class RB_HelpButton extends E2_Button implements IF_RB_Component {
	
	private MyE2_String 		cFensterTitel = 			new MyE2_String("Informationen:");
	private Color 				oColorFensterBackground = 	new E2_ColorHelpBackground();
	private Extent              oPopupWidth = 				new Extent(800);
	private Extent              oPopupHeight = 				new Extent(500);
	
	public RB_HelpButton() {
		super();
		
		this.__setImages(E2_ResourceIcon.get_RI("help.png"),E2_ResourceIcon.get_RI("leer.png"));
		this._aaa(new ownInfoAction());
	}


	
	public abstract E2_BasicModuleContainer 	generate_E2_BasicModuleContainer() throws myException;
	public abstract E2_Grid   					generate_InfoGrid() throws myException;
	
	
	
	private class ownInfoAction extends XX_ActionAgent  {
		
		public ownInfoAction()	{
			super();
		}
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
			RB_HelpButton oThis = RB_HelpButton.this;
			
			E2_BasicModuleContainer  oContainerPOPUP = oThis.generate_E2_BasicModuleContainer();
						
			oContainerPOPUP.add(oThis.generate_InfoGrid(), E2_INSETS.I(2,2,2,2));
			oContainerPOPUP.setBackground(oThis.oColorFensterBackground);
			oContainerPOPUP.get_oContentPaneAussen().setBackground(new E2_ColorHelpBackground());
			oContainerPOPUP.get_oContentPaneInnen().setBackground(new E2_ColorHelpBackground());
			oContainerPOPUP.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_HELP(oThis.oColorFensterBackground));
			oContainerPOPUP.CREATE_AND_SHOW_POPUPWINDOW(oThis.oPopupWidth, oThis.oPopupHeight, oThis.cFensterTitel);
		}
	}
	
	


	public MyE2_String get_cFensterTitel() {
		return cFensterTitel;
	}




	public RB_HelpButton set_cFensterTitel(MyE2_String cFensterTitel) {
		this.cFensterTitel = cFensterTitel;
		return this;
	}




	public Color get_oColorFensterBackground() {
		return oColorFensterBackground;
	}




	public RB_HelpButton set_oColorFensterBackground(Color oColorFensterBackground) {
		this.oColorFensterBackground = oColorFensterBackground;
		return this;
	}




	public Extent get_oPopupWidth() {
		return oPopupWidth;
	}




	public RB_HelpButton set_oPopupWidth(Extent oPopupWidth) {
		this.oPopupWidth = oPopupWidth;
		return this;
	}




	public Extent get_oPopupHeight() {
		return oPopupHeight;
	}




	public RB_HelpButton set_oPopupHeight(Extent oPopupHeight) {
		this.oPopupHeight = oPopupHeight;
		return this;
	}
	
	
	
}
