package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;


/*
 * panel, das es erlaubt, bedienelemente zu gruppieren und auszublenden
 */
public class E2_ExpandableRow_NG extends MyE2_Row
{
	
	private		MyE2_Row 				oContent = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private 	MyE2_Button				oButtonOpen = new MyE2_Button(E2_ResourceIcon.get_RI("expandopen.png"));
	private 	MyE2_Button				oButtonClose = new MyE2_Button(E2_ResourceIcon.get_RI("expandclose.png"));
	private 	MyE2_Label				oLabel_InfoWhenClosed = null;
	
	private 	Border					oBorderOpen = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
	private 	Border					oBorderClosed = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
	
	private 	boolean 				bOpen = true;
	
	public E2_ExpandableRow_NG(MyE2_String oInfoWhenClosed,Border oBorderopen, Border oBorderclosed)
	{
		super();
		this._ExpandableRow(oInfoWhenClosed, oBorderopen, oBorderclosed);
	}

	public E2_ExpandableRow_NG(MyE2_String oInfoWhenClosed,Border oBorderopen, Border oBorderclosed, Color ColorExpandableSection)
	{
		super();
		this._ExpandableRow(oInfoWhenClosed, oBorderopen, oBorderclosed);
		this.setBackground(ColorExpandableSection);
		
	}

	
	
	private void _ExpandableRow(MyE2_String oInfoWhenClosed,Border oBorderopen, Border oBorderclosed)
	{

		if (oBorderopen !=null) 	this.oBorderOpen = oBorderopen;
		if (oBorderclosed !=null) 	this.oBorderClosed  = oBorderclosed;
		
		this.setStyle(new ownStyleBorder(this.oBorderOpen));
		
		
		if (oInfoWhenClosed != null)
		{
			this.oLabel_InfoWhenClosed=new MyE2_Label(oInfoWhenClosed);
			this.oLabel_InfoWhenClosed.setLayoutData(new RowLayoutForLabel());
		}
		
		this.oButtonClose.add_oActionAgent(new ActionClose());
		this.oButtonOpen.add_oActionAgent(new ActionOpen());
		this.oButtonClose.setLayoutData(new RowLayoutForButton());
		this.oButtonOpen.setLayoutData(new RowLayoutForButton());
		this.oButtonClose.setStyle(new Style_Button_Image_NoBorder());
		this.oButtonOpen.setStyle(new Style_Button_Image_NoBorder());
		
		
		
//		/*
//		 * starteinstellung
//		 */
//		super.add(oButtonClose);
//		super.add(oContent);
		
		this.set_statusOpen();
		
	}


	private void set_statusOpen()
	{
		super.removeAll();
		super.add(oButtonClose);
		super.add(oContent);
		super.setStyle(new ownStyleBorder(E2_ExpandableRow_NG.this.oBorderOpen));
		this.bOpen = true;
	}
	
	private void set_statusClosed()
	{
		super.removeAll();
		super.add(oButtonOpen);
		super.add(this.oLabel_InfoWhenClosed==null?new MyE2_Label(""):this.oLabel_InfoWhenClosed);
		super.setStyle(new ownStyleBorder(E2_ExpandableRow_NG.this.oBorderClosed));
		this.bOpen = true;
	}
	
	
	public void set_TO_InnerComponent(Component oComp, Insets  oIN)
	{
		this.oContent.removeAll();
		this.oContent.add(oComp, oIN);
	}
	
	public void remove_All_FromInnerComponent()
	{
		this.oContent.removeAll();
	}

	

	private class ownStyleBorder extends MutableStyle
	{

		public ownStyleBorder(Border oBorder)
		{
			super();
			
			if (oBorder != null) this.setProperty( Row.PROPERTY_BORDER, oBorder); 
			this.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		}
		
	}
	
	
	

	
	private class ActionClose extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ExpandableRow_NG.this.set_statusClosed();
			
//			E2_ExpandableRow_NG.this.bOpen = false;
//			
//			E2_ExpandableRow_NG.this.removeAll();
//			E2_ExpandableRow_NG.this.add(E2_ExpandableRow_NG.this.oButtonOpen);
//			if (E2_ExpandableRow_NG.this.oLabel_InfoWhenClosed != null)
//				E2_ExpandableRow_NG.this.add(E2_ExpandableRow_NG.this.oLabel_InfoWhenClosed);
//			
//			E2_ExpandableRow_NG.this.setStyle(new ownStyleBorder(E2_ExpandableRow_NG.this.oBorderClosed));
		}
	}
	

	private class ActionOpen extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ExpandableRow_NG.this.set_statusOpen();
//			
//			E2_ExpandableRow_NG.this.bOpen = true;
//
//			E2_ExpandableRow_NG.this.removeAll();
//			E2_ExpandableRow_NG.this.add(E2_ExpandableRow_NG.this.oButtonClose);
//			E2_ExpandableRow_NG.this.add(E2_ExpandableRow_NG.this.oContent);
//			E2_ExpandableRow_NG.this.setStyle(new ownStyleBorder(E2_ExpandableRow_NG.this.oBorderOpen));
		}
	}

	
	private class RowLayoutForButton extends RowLayoutData
	{

		public RowLayoutForButton()
		{
			super();
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		}
		
	}
	
	private class RowLayoutForLabel extends RowLayoutData
	{

		public RowLayoutForLabel()
		{
			super();
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setInsets(new Insets(5,0,0,0));
		}
		
	}



	public boolean get_bOpen() 
	{
		return bOpen;
	}




	public MyE2_Button get_oButtonOpen() 
	{
		return oButtonOpen;
	}

	public MyE2_Button get_oButtonClose() 
	{
		return oButtonClose;
	}
	
	
	
	private class Style_Button_Image_NoBorder extends MutableStyle
	{

		public Style_Button_Image_NoBorder()
		{
			super();
			this.setProperty( AbstractButton.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-30), Border.STYLE_NONE));
			this.setProperty( AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(false));
			this.setProperty( AbstractButton.PROPERTY_PRESSED_BORDER, new Border(0, Color.WHITE, Border.STYLE_NONE));

		}
		
	}

	
	public Row get_oInnerContainer()
	{
		return oContent;
	}

	
	public void add(Component oComp)
	{
		this.oContent.add(oComp);
	}

	public void add(Component oComp, Insets oInsets)
	{
		this.oContent.add(oComp);
		oComp.setLayoutData(new ownLayoutForInnerComponent(oInsets));
	}

	public void ADD(Component oComp)
	{
		super.add(oComp);
	}

	public void ADD(Component oComp, Insets oInsets)
	{
		super.add(oComp);
		oComp.setLayoutData(new ownLayoutForInnerComponent(oInsets));
	}

	
	
	private class ownLayoutForInnerComponent extends RowLayoutData
	{
		public ownLayoutForInnerComponent(Insets oInsets)
		{
			super();
			this.setInsets(oInsets);
		}
		
	}

	
}
