package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorE2String;

public class E2_MessageBoxYesNo extends E2_BasicModuleContainer 
{

	private MyE2_String  		cTextTitelZeile = 	new MyE2_String("Bitte wählen ...");
	private MyE2_String  		cTextYes = 			new MyE2_String("Ja");
	private MyE2_String  		cTextNo = 			new MyE2_String("Nein");
	private XX_ActionAgent      oActionAgentStart=  null;
	
	private Vector<MyString>  	vInfos = 			new Vector<MyString>();

	private MyE2_Button         oButtonYES =        null;
	private MyE2_Button         oButtonNO =	        null;
	
	public E2_MessageBoxYesNo(	MyE2_String 		TextTitelZeile,	
								MyE2_String 		TextYes, 
								MyE2_String 		TextNo, 
								Vector<MyString> 	Infos,
								XX_ActionAgent      ActionAgentStart) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		this.oActionAgentStart = 	ActionAgentStart;
		
		MyE2_Grid  oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid  oGridHelp2 = new MyE2_Grid_100_percent(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridHelp.setWidth(new Extent(100,Extent.PERCENT));
		
		
		for (MyString oText: this.vInfos)
		{
			oGridHelp.add(new MyE2_Label(oText),2,E2_INSETS.I_2_2_2_2);
		}
		
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(0,this.oButtonYES = new buttonYes(),this.oButtonNO  = new buttonNO(),new Insets(0, 0, 20, 0));
		oGridHelp2.add(oCompGroup, MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_10_2_0,2,new Alignment(Alignment.LEFT, Alignment.BOTTOM)));
		
		this.add(oGridHelp, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelp2, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(180+(this.vInfos.size()*20)), this.cTextTitelZeile);
		
	}
	
	
	public E2_MessageBoxYesNo(	MyE2_String 		TextTitelZeile,	
								MyE2_String 		TextYes, 
								MyE2_String 		TextNo, 
								boolean 			bShowYes,
								boolean 			bShowNo,
								Vector<MyString> 	Infos,
								XX_ActionAgent      ActionAgentStart) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		this.oActionAgentStart = 	ActionAgentStart;
		
		MyE2_Grid  oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid  oGridHelp2 = new MyE2_Grid_100_percent(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridHelp.setWidth(new Extent(100,Extent.PERCENT));
		
		
		for (MyString oText: this.vInfos)
		{
			oGridHelp.add(new MyE2_Label(oText),2,E2_INSETS.I_2_2_2_2);
		}
		this.oButtonYES = new buttonYes();
		this.oButtonNO  = new buttonNO();
		
		boolean bBeideAusblenden = (!bShowYes) && (!bShowNo);
		
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(0,bShowYes?this.oButtonYES:null,bShowNo?this.oButtonNO:null,(bBeideAusblenden?new MyE2_Label(""):null),new Insets(0, 0, 20, 0));
		oGridHelp2.add(oCompGroup, MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_10_2_0,2,new Alignment(Alignment.LEFT, Alignment.BOTTOM)));
		
		this.add(oGridHelp, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelp2, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(180+(this.vInfos.size()*20)), this.cTextTitelZeile);
	
	}


	
	
	//weitere variante
	public E2_MessageBoxYesNo(	MyE2_String 		TextTitelZeile,	
								MyE2_String 		TextYes, 
								MyE2_String 		TextNo, 
								boolean             bShowYes,
								boolean             bShowNo,
								MyE2IF__Component   ComponentToShow,
								XX_ActionAgent      ActionAgentStart,
								Extent              iWidth,
								Extent              iHeight) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		
		this.oActionAgentStart = 	ActionAgentStart;
		
		MyE2_Grid  oGridHelp = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid  oGridHelp2 = new MyE2_Grid_100_percent(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridHelp.setWidth(new Extent(100,Extent.PERCENT));
		
		if (ComponentToShow != null)
		{
			oGridHelp.add((Component) ComponentToShow, MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I_2_2_2_2));
		}
		
		E2_ComponentGroupHorizontal oCompGroup  = null;
		if (bShowNo && bShowYes)
		{
			oCompGroup = new E2_ComponentGroupHorizontal(0,this.oButtonYES = new buttonYes(),this.oButtonNO  = new buttonNO(),new Insets(0, 0, 20, 0));
		}
		else if (bShowNo)
		{
			oCompGroup = new E2_ComponentGroupHorizontal(0,this.oButtonNO  = new buttonNO(),new Insets(0, 0, 20, 0));
		}
		else if (bShowYes)
		{
			oCompGroup = new E2_ComponentGroupHorizontal(0,this.oButtonYES  = new buttonYes(),new Insets(0, 0, 20, 0));
		}
		else
		{
			oCompGroup = new E2_ComponentGroupHorizontal(new Insets(0, 0, 20, 0));
		}
		
		oGridHelp2.add(oCompGroup, MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_10_2_0,2,new Alignment(Alignment.LEFT, Alignment.BOTTOM)));
		
		this.add(oGridHelp, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelp2, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(
				(iWidth!=null?iWidth:new Extent(400)), 
				(iHeight!=null?iHeight:new Extent(400)), 
				this.cTextTitelZeile);
	
	}

	
	//2014-05-15: noch eine Variante
	public E2_MessageBoxYesNo(	MyE2_String 		TextTitelZeile,	
								MyE2_String 		TextYes, 
								MyE2_String 		TextNo, 
								boolean 			bShowYes,
								boolean 			bShowNo,
								Vector<MyString> 	Infos,
								XX_ActionAgent      ActionAgentStart,
								int 				iWidth,
								int 				iHeight) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		this.oActionAgentStart = 	ActionAgentStart;
		
		MyE2_Grid  oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid  oGridHelp2 = new MyE2_Grid_100_percent(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridHelp.setWidth(new Extent(100,Extent.PERCENT));
		
		
		for (MyString oText: this.vInfos)
		{
		oGridHelp.add(new MyE2_Label(oText),2,E2_INSETS.I_2_2_2_2);
		}
		this.oButtonYES = new buttonYes();
		this.oButtonNO  = new buttonNO();
		
		boolean bBeideAusblenden = (!bShowYes) && (!bShowNo);
		
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(0,bShowYes?this.oButtonYES:null,bShowNo?this.oButtonNO:null,(bBeideAusblenden?new MyE2_Label(""):null),new Insets(0, 0, 20, 0));
		oGridHelp2.add(oCompGroup, MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_10_2_0,2,new Alignment(Alignment.LEFT, Alignment.BOTTOM)));
		
		this.add(oGridHelp, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelp2, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iWidth), new Extent(iHeight), this.cTextTitelZeile);
		
	}

	//2014-05-15: noch eine Variante
	public E2_MessageBoxYesNo(	MyE2_String 		TextTitelZeile,	
								MyE2_String 		TextYes, 
								MyE2_String 		TextNo, 
								Vector<MyString> 	Infos,
								XX_ActionAgent      ActionAgentStart,
								int 				iWidth,
								int 				iHeight) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		this.oActionAgentStart = 	ActionAgentStart;
		
		MyE2_Grid  oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid  oGridHelp2 = new MyE2_Grid_100_percent(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridHelp.setWidth(new Extent(100,Extent.PERCENT));
		
		
		for (MyString oText: this.vInfos)
		{
		oGridHelp.add(new MyE2_Label(oText),2,E2_INSETS.I_2_2_2_2);
		}
		
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(0,this.oButtonYES = new buttonYes(),this.oButtonNO  = new buttonNO(),new Insets(0, 0, 20, 0));
		oGridHelp2.add(oCompGroup, MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_10_2_0,2,new Alignment(Alignment.LEFT, Alignment.BOTTOM)));
		
		this.add(oGridHelp, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelp2, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iWidth), new Extent(iHeight), this.cTextTitelZeile);
	
	}


	
	//2016-03-11: noch eine
	//2014-05-15: noch eine Variante
	public E2_MessageBoxYesNo(	MyE2_String 		TextTitelZeile,	
								MyE2_String 		TextYes, 
								MyE2_String 		TextNo, 
								boolean 			bShowYes,
								boolean 			bShowNo,
								VectorE2String 		Infos,
								XX_ActionAgent      ActionAgentStart,
								int 				iWidth,
								int 				iHeight) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		this.oActionAgentStart = 	ActionAgentStart;
		
		MyE2_Grid  oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		MyE2_Grid  oGridHelp2 = new MyE2_Grid_100_percent(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridHelp.setWidth(new Extent(100,Extent.PERCENT));
		
		
		for (MyString oText: this.vInfos) 		{
			oGridHelp.add(new MyE2_Label(oText),2,E2_INSETS.I_2_2_2_2);
		}
		this.oButtonYES = new buttonYes();
		this.oButtonNO  = new buttonNO();
		
		boolean bBeideAusblenden = (!bShowYes) && (!bShowNo);
		
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(0,bShowYes?this.oButtonYES:null,bShowNo?this.oButtonNO:null,(bBeideAusblenden?new MyE2_Label(""):null),new Insets(0, 0, 20, 0));
		oGridHelp2.add(oCompGroup, MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I_2_10_2_0,2,new Alignment(Alignment.LEFT, Alignment.BOTTOM)));
		
		this.add(oGridHelp, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelp2, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iWidth), new Extent(iHeight), this.cTextTitelZeile);
		
	}

	
	
	
	
	public void add_ActionAgents(Vector<XX_ActionAgent> vAgents) 
	{
		this.oButtonYES.remove_AllActionAgents();
		
		for (XX_ActionAgent oAgent: vAgents)
		{
			this.oButtonYES.add_oActionAgent(oAgent);
		}
		
		this.oButtonYES.add_oActionAgent(new XX_ActionAgent() 
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				E2_MessageBoxYesNo.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});

		
	}
	
	
	public void set_ActionAgent4No(XX_ActionAgent oAgent) {
		this.oButtonNO.remove_AllActionAgents();
		
		this.oButtonNO.add_oActionAgent(oAgent);
		this.oButtonNO.add_oActionAgent(new XX_ActionAgent() 
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				E2_MessageBoxYesNo.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		});
	}

	
	
	public void set_ActionAgent4Yes(XX_ActionAgent oAgent, boolean bAddCloseMessageBoxAgent) 
	{
		this.oButtonYES.remove_AllActionAgents();
		
		this.oButtonYES.add_oActionAgent(oAgent);
		
		if (bAddCloseMessageBoxAgent)
		{
			this.oButtonYES.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					E2_MessageBoxYesNo.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}

	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 26.01.2021
	 *
	 */
	private class buttonNO extends MyE2_Button
	{

		public buttonNO() 
		{
			super(E2_MessageBoxYesNo.this.cTextNo);
			
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					E2_MessageBoxYesNo.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}
	

	private class buttonYes extends MyE2_Button
	{

		public buttonYes() 
		{
			super(E2_MessageBoxYesNo.this.cTextYes);
			
			if (E2_MessageBoxYesNo.this.oActionAgentStart!=null) this.add_oActionAgent(E2_MessageBoxYesNo.this.oActionAgentStart);
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					E2_MessageBoxYesNo.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}


	/**
	 * @return the oButtonYES
	 */
	public MyE2_Button getButtonYES() {
		return oButtonYES;
	}


	/**
	 * @return the oButtonNO
	 */
	public MyE2_Button getButtonNO() {
		return oButtonNO;
	}
	
	
	
	
	
}
