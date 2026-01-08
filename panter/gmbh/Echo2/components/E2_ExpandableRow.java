package panter.gmbh.Echo2.components;

import java.util.Vector;

import org.jfree.ui.Align;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.indep.exceptions.myException;


/*
 * panel, das es erlaubt, bedienelemente zu gruppieren und auszublenden
 */
public class E2_ExpandableRow extends Row  implements MyE2IF__Component
{
	private 	MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
	private		Row 					oContent = new Row();
	private 	MyE2_Button				oButtonOpen = new MyE2_Button(E2_ResourceIcon.get_RI("expandopen.png"));
	private 	MyE2_Button				oButtonClose = new MyE2_Button(E2_ResourceIcon.get_RI("expandclose.png"));
	private 	MyE2_Label				oLabel_InfoWhenClosed = null;
	
	private 	Border					oBorderOpen = null;
	private 	Border					oBorderClosed = new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID);
	
	private 	boolean 				bOpen = true;
	
	public E2_ExpandableRow(MyE2_String oInfoWhenClosed,Border oBorderopen, Border oBorderclosed)
	{
		super();
		this._ExpandableRow(oInfoWhenClosed, oBorderopen, oBorderclosed);
	}

	public E2_ExpandableRow(MyE2_String oInfoWhenClosed,Border oBorderopen, Border oBorderclosed, Color ColorExpandableSection)
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
		
		
		
		/*
		 * starteinstellung
		 */
		super.add(oButtonClose);
		super.add(oContent);
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


	

	private class ownStyleBorder extends MutableStyle
	{

		public ownStyleBorder(Border oBorder)
		{
			super();
			
			if (oBorder != null) this.setProperty( Row.PROPERTY_BORDER, oBorder); 
			this.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		}
		
	}
	
	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		for (int i=0;i<this.getComponentCount();i++)
		{
			if (this.getComponent(i) instanceof MyE2IF__Component)
				((MyE2IF__Component)this.getComponent(i)).set_bEnabled_For_Edit(bEnabled && this.EXT().is_ValidEnableAlowed());
		}
		
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}
	
	
	private class ActionClose extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ExpandableRow.this.bOpen = false;
			
			E2_ExpandableRow.this.removeAll();
			E2_ExpandableRow.super.add(E2_ExpandableRow.this.oButtonOpen);
			if (E2_ExpandableRow.this.oLabel_InfoWhenClosed != null)
				E2_ExpandableRow.super.add(E2_ExpandableRow.this.oLabel_InfoWhenClosed);
			
			E2_ExpandableRow.this.setStyle(new ownStyleBorder(E2_ExpandableRow.this.oBorderClosed));
		}
	}
	

	private class ActionOpen extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			E2_ExpandableRow.this.bOpen = true;

			E2_ExpandableRow.this.removeAll();
			E2_ExpandableRow.super.add(E2_ExpandableRow.this.oButtonClose);
			E2_ExpandableRow.super.add(E2_ExpandableRow.this.oContent);
			E2_ExpandableRow.this.setStyle(new ownStyleBorder(E2_ExpandableRow.this.oBorderOpen));
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

	
	private class ownLayoutForInnerComponent extends RowLayoutData
	{
		public ownLayoutForInnerComponent(Insets oInsets)
		{
			super();
			this.setInsets(oInsets);
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

	
	public void removeAllInnerComponents()
	{
		this.oContent.removeAll();
	}

	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}

	
	
	
}
