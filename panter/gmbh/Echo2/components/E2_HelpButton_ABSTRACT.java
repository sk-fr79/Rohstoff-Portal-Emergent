package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_HelpButton_ABSTRACT extends MyE2_Button
{
	
	private MyE2_String 		cFensterTitel = 			new MyE2_String("Informationen:");
	private Color 				oColorFensterBackground = 	new E2_ColorHelpBackground();
	private Extent              oPopupWidth = 				new Extent(800);
	private Extent              oPopupHeight = 				new Extent(500);

	
	
	public E2_HelpButton_ABSTRACT()
	{
		super(E2_ResourceIcon.get_RI("help.png"),E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownInfoAction());
	}



	
	public abstract E2_BasicModuleContainer 	get_Container() throws myException;
	public abstract MyE2_Grid   				get_GridWithInfos() throws myException;
	
	
	
	private class ownInfoAction extends XX_ActionAgent  {
		
		public ownInfoAction()	{
			super();
		}
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
			E2_HelpButton_ABSTRACT oThis = E2_HelpButton_ABSTRACT.this;
			
			E2_BasicModuleContainer  oContainerPOPUP = oThis.get_Container();
						
			oContainerPOPUP.add(oThis.get_GridWithInfos(), E2_INSETS.I(2,2,2,2));
			oContainerPOPUP.setBackground(oThis.oColorFensterBackground);
			oContainerPOPUP.get_oContentPaneAussen().setBackground(new E2_ColorHelpBackground());
			oContainerPOPUP.get_oContentPaneInnen().setBackground(new E2_ColorHelpBackground());

			
			
			oContainerPOPUP.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_HELP(oThis.oColorFensterBackground));
			oContainerPOPUP.CREATE_AND_SHOW_POPUPWINDOW(oThis.oPopupWidth, oThis.oPopupHeight, oThis.cFensterTitel);
		}
	}
	
	
	
	public void fill_GridWithText_Without_Translation(MyE2_Grid oGrid, Vector<String> vTextZeilen, Font  oFont) {
		
		oGrid.setSize(1);
		oGrid.setBackground(this.oColorFensterBackground);
		for (int i=0;i<vTextZeilen.size();i++) {
			oGrid.add(new MyE2_Label(S.NN(vTextZeilen.get(i)), oFont), E2_INSETS.I(2,1,1,2));
		}
		
	}

	
	public void fill_GridWithText_Without_Translation(MyE2_Grid oGrid, Vector<E2_HelpButton_ABSTRACT.TextInsets> vTextZeilen) {
		
		oGrid.setSize(1);
		oGrid.setBackground(this.oColorFensterBackground);
		for (int i=0;i<vTextZeilen.size();i++) {
			oGrid.add(new MyE2_Label(vTextZeilen.get(i).cText, vTextZeilen.get(i).oFont), vTextZeilen.get(i).oIN);
		}
	}





	public MyE2_String get_cFensterTitel() {
		return cFensterTitel;
	}




	public void set_cFensterTitel(MyE2_String cFensterTitel) {
		this.cFensterTitel = cFensterTitel;
	}




	public Color get_oColorFensterBackground() {
		return oColorFensterBackground;
	}




	public void set_oColorFensterBackground(Color oColorFensterBackground) {
		this.oColorFensterBackground = oColorFensterBackground;
	}




	public Extent get_oPopupWidth() {
		return oPopupWidth;
	}




	public void set_oPopupWidth(Extent oPopupWidth) {
		this.oPopupWidth = oPopupWidth;
	}




	public Extent get_oPopupHeight() {
		return oPopupHeight;
	}




	public void set_oPopupHeight(Extent oPopupHeight) {
		this.oPopupHeight = oPopupHeight;
	}
	
	
	
	
	public class TextInsets {
		public MyE2_String cText = null;
		public Insets      oIN = E2_INSETS.I(2,1,2,1);
		public Font        oFont = new E2_FontPlain();
		
		public TextInsets(String Text) {
			this.cText=new MyE2_String(Text,false);
		}
		public TextInsets(String Text, Insets IN) {
			this.cText=new MyE2_String(Text,false);
			this.oIN = IN;
		}
		public TextInsets(String Text, Insets IN, Font font) {
			this.cText=new MyE2_String(Text,false);
			this.oIN = IN;
			this.oFont = font;
		}
	
	}
	
	
}
