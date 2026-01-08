package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_Row extends Row implements MyE2IF__Component,E2_IF_Copy, IF_ADDING_Allowed
{
	
	

	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	
	public MyE2_Row()
	{
		super();
		this.setStyle(new Style_Row_Normal(0, new Insets(2,2)));
	}
	
	public MyE2_Row(MutableStyle oStyle)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
	}

	
	
	public void add(Component oComp, Insets oInsets)
	{
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets));
	}


	public void add(Component oComp, RowLayoutData oRowLayoutData)
	{
		super.add(oComp);
		oComp.setLayoutData(oRowLayoutData);
	}

	

	public void addAfterRemoveAll(Component oComp, Insets oInsets)
	{
		super.removeAll();
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets));
	}


	public void addAfterRemoveAll(Component oComp, RowLayoutData oRowLayoutData)
	{
		super.removeAll();
		super.add(oComp);
		oComp.setLayoutData(oRowLayoutData);
	}


	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Row oRowRueck = new MyE2_Row();
		oRowRueck.setStyle(this.getStyle());
		oRowRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRowRueck));
		return oRowRueck;
	}

	
	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	private class ownLayout extends RowLayoutData
	{
		public ownLayout(Insets oInsets)
		{
			super();
			this.setInsets(oInsets);
		}
		
	}

	

	
	public static  MutableStyle STYLE_NO_BORDER()
	{
		MutableStyle oStyleNoB = new MutableStyle();
		
		oStyleNoB.setProperty( Row.PROPERTY_BORDER, null); 
		oStyleNoB.setProperty( Row.PROPERTY_INSETS, new Insets(2)); 
		oStyleNoB.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyleNoB;
	}
	
	
	public static  MutableStyle STYLE_NO_BORDER_NO_INSETS()
	{
		MutableStyle oStyleNoB = new MutableStyle();
		oStyleNoB.setProperty(PROPERTY_BORDER, null); 
		oStyleNoB.setProperty(PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		return oStyleNoB;
	}



	public static  MutableStyle STYLE_NO_BORDER_NO_INSETS_LEFT_TOP()
	{
		MutableStyle oStyleNoB = new MutableStyle();
		oStyleNoB.setProperty(PROPERTY_BORDER, null); 
		oStyleNoB.setProperty(PROPERTY_ALIGNMENT, new Alignment(Alignment.LEFT,Alignment.TOP));
		return oStyleNoB;
	}

	public static  MutableStyle STYLE_NO_BORDER_NO_INSETS_LEFT_CENTER()
	{
		MutableStyle oStyleNoB = new MutableStyle();
		oStyleNoB.setProperty(PROPERTY_BORDER, null); 
		oStyleNoB.setProperty(PROPERTY_ALIGNMENT, new Alignment(Alignment.LEFT,Alignment.CENTER));
		return oStyleNoB;
	}

	
	
	public static  MutableStyle STYLE_THIN_BORDER()
	{
		MutableStyle oStyleThinB = new MutableStyle();
		
		oStyleThinB.setProperty( Row.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		oStyleThinB.setProperty( Row.PROPERTY_INSETS, new Insets(2)); 
		oStyleThinB.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyleThinB;
	}
	
	public static  MutableStyle STYLE_3D_BORDER()
	{
		MutableStyle oStyle3DB = new MutableStyle();
		oStyle3DB.setProperty( Row.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_OUTSET));
		oStyle3DB.setProperty( Row.PROPERTY_INSETS, new Insets(2)); 
		oStyle3DB.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyle3DB;
	}
	
	
	public static RowLayoutData LAYOUT_LEFT(Insets oInsets)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static RowLayoutData LAYOUT_CENTER(Insets oInsets)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static RowLayoutData LAYOUT_RIGHT(Insets oInsets)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	

	
	public static RowLayoutData LAYOUT_LEFT_TOP(Insets oInsets)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static RowLayoutData LAYOUT_CENTER_TOP(Insets oInsets)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static RowLayoutData LAYOUT_RIGHT_TOP(Insets oInsets)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}

	
	public void add_raw(Component oComp)
	{
		this.add(oComp);
	}

	
	
	public static RowLayoutData LAYOUT_LEFT_TOP(Insets oInsets, Color oColorBackground)
	{
		RowLayoutData oLayout = new RowLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		
		if (oColorBackground != null)
			oLayout.setBackground(oColorBackground);


		return oLayout;
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
