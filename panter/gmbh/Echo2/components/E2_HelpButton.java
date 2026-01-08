package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.indep.exceptions.myException;

public class E2_HelpButton extends MyE2_Button
{
	
	private MyE2_String 		cFensterTitel = null;
	private boolean 			Translate = false;	
	private Vector<String>	 	vInfos = null;

	private int  				iWidth = 400;
	private int  				iHeight = 400;
	
	
	
	public E2_HelpButton(MyE2_String HilfsFensterTitel, Vector<String> infos, boolean bTranslate)
	{
		super(E2_ResourceIcon.get_RI("help.png"),E2_ResourceIcon.get_RI("leer.png"));
		
		E2_HelpButton.this.cFensterTitel = 	HilfsFensterTitel;
		E2_HelpButton.this.Translate = 		bTranslate;
		E2_HelpButton.this.vInfos = 		infos;
		
		this.add_oActionAgent(new ownInfoAction());
	}


	/*
	 * neue variante, 2 spaltig, gelber hintergrund
	 */
	public E2_HelpButton(MyE2_String HilfsFensterTitel, Vector<String> Ueberschriften, Vector<String> Spalte1_Stichwort, Vector<String> Spalte2_Erlaeuterung, boolean bTranslate, Extent iWidth, Extent iHeight)
	{
		super(E2_ResourceIcon.get_RI("help.png"),E2_ResourceIcon.get_RI("leer.png"));
		
		this.add_oActionAgent(new ownActionHelp2Spaltig(HilfsFensterTitel, Ueberschriften, Spalte1_Stichwort, Spalte2_Erlaeuterung, bTranslate, iWidth, iHeight));
	}


	
	
	private class ownInfoAction extends XX_ActionAgent
	{
		
		public ownInfoAction()
		{
			super();
		}
		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new ownBasicContainerToShowInfos(E2_HelpButton.this.cFensterTitel,E2_HelpButton.this.vInfos, E2_HelpButton.this.Translate);
		}
	}
	
	private class ownBasicContainerToShowInfos extends E2_BasicContainerToShowInfos
	{
		public ownBasicContainerToShowInfos(MyE2_String cFensterTitel, Vector<String> infos, boolean translate) throws myException
		{
			super(cFensterTitel, infos, new Extent(E2_HelpButton.this.iWidth), new Extent(E2_HelpButton.this.iHeight), translate);
		}
	}
	

	public Vector<String> get_vHelpInfos() 
	{
		return vInfos;
	}



	private class ownActionHelp2Spaltig extends XX_ActionAgent
	{
		private MyE2_String cHilfsFensterTitel;
		private Vector<String> 	vUeberschriften;
		private Vector<String> vSpalte1_Stichwort;
		private Vector<String> vSpalte2_Erlaeuterung;
		private boolean bTranslate;
		private Extent iWidth;
		private Extent iHeight;
		
		public ownActionHelp2Spaltig(MyE2_String hilfsFensterTitel,Vector<String> ueberschriften, Vector<String> spalte1_Stichwort, Vector<String> spalte2_Erlaeuterung, boolean translate, Extent width, Extent height)
		{
			super();
			this.cHilfsFensterTitel = 		hilfsFensterTitel;
			this.vUeberschriften 	= 		ueberschriften;
			this.vSpalte1_Stichwort = 		spalte1_Stichwort;
			this.vSpalte2_Erlaeuterung = 	spalte2_Erlaeuterung;
			this.bTranslate = 				translate;
			this.iWidth = 					width;
			this.iHeight = 					height;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new ownContainer4Help(this.cHilfsFensterTitel,	this.vUeberschriften, this.vSpalte1_Stichwort, this.vSpalte2_Erlaeuterung, this.bTranslate, this.iWidth, this.iHeight);
		}
	}
	
	private class ownContainer4Help extends E2_BasicModuleContainer
	{
		public ownContainer4Help(	MyE2_String 	hilfsFensterTitel, 
									Vector<String> 	ueberschriften,
									Vector<String> 	spalte1_Stichwort, 
									Vector<String> 	spalte2_Erlaeuterung, 
									boolean 		Translate, 
									Extent 			Width, 
									Extent 			Height) throws myException
		{
			super();
			
			MyE2_Grid  oGridInnen = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			this.setBackground(new E2_ColorHelpBackground());
			this.get_oContentPaneAussen().setBackground(new E2_ColorHelpBackground());
			this.get_oContentPaneInnen().setBackground(new E2_ColorHelpBackground());

			
			
			GridLayoutData  GLHelp = new GridLayoutData();
			GLHelp.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
			GLHelp.setInsets(new Insets(0,2,20,2));
			
			if (spalte1_Stichwort.size()==spalte2_Erlaeuterung.size())
			{
				for (int i=0;i<ueberschriften.size();i++)
				{
					oGridInnen.add(new MyE2_Label(new MyE2_String(ueberschriften.get(i),Translate),false),2,E2_INSETS.I_0_2_5_2);
				}
				
				for (int i=0;i<spalte1_Stichwort.size();i++)
				{
					oGridInnen.add(new MyE2_Label(new MyE2_String(spalte1_Stichwort.get(i),Translate),false),GLHelp);
					oGridInnen.add(new MyE2_Label(new MyE2_String(spalte2_Erlaeuterung.get(i),Translate),true),GLHelp);
				}
			}
			
			this.add(oGridInnen, E2_INSETS.I_5_5_5_5);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(Width, Height, hilfsFensterTitel);
			
		}

	}


	public void set_iWidth(int width) {
		this.iWidth = width;
	}


	public void set_iHeight(int height) {
		this.iHeight = height;
	}
	
	
	public E2_HelpButton _setWidthPopup(int width) {
		this.iWidth = width;
		return this;
	}


	public E2_HelpButton _setHeightPopup(int height) {
		this.iHeight = height;
		return this;
	}

	
	
}
