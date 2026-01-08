package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_Column extends Column implements MyE2IF__Component,E2_IF_Copy ,IF_ADDING_Allowed
{

	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	
	public MyE2_Column()
	{
		super();
		this.setStyle(new Style_Column_Normal(0, new Insets(2,2)));
	}
	
	public MyE2_Column(MutableStyle oStyle)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
	}


	
	
	public void add(Component oComp, Insets oInsets)
	{
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets));
	}
	
	public void add(Component oComp, ColumnLayoutData oLayout)
	{
		super.add(oComp);
		oComp.setLayoutData(oLayout);
	}
	
	public void addAfterRemoveAll(Component oComp, Insets oInsets)
	{
		super.removeAll();
		super.add(oComp);
		oComp.setLayoutData(new ownLayout(oInsets));
	}
	
	public void addAfterRemoveAll(Component oComp, ColumnLayoutData oLayout)
	{
		super.removeAll();
		super.add(oComp);
		oComp.setLayoutData(oLayout);
	}


	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Column  oColCopy = new MyE2_Column();
		oColCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oColCopy));

		oColCopy.setStyle(this.getStyle());
		
		return oColCopy;
	}

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}


	private class ownLayout extends ColumnLayoutData
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
		
		oStyleNoB.setProperty( Column.PROPERTY_BORDER, null); 
		oStyleNoB.setProperty( Column.PROPERTY_INSETS, new Insets(2)); 
		oStyleNoB.setProperty( Column.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyleNoB;
		
	}


	
	public static  MutableStyle STYLE_NO_BORDER_NO_INSETS()
	{
		MutableStyle oStyleNoB = new MutableStyle();
		
		oStyleNoB.setProperty( Column.PROPERTY_BORDER, null); 
		oStyleNoB.setProperty( Column.PROPERTY_INSETS, new Insets(0)); 
		oStyleNoB.setProperty( Column.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyleNoB;
		
	}

	public static  MutableStyle STYLE_THIN_BORDER_NO_INSETS()
	{
		MutableStyle oStyleNoB = new MutableStyle();
		
		oStyleNoB.setProperty( Column.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		oStyleNoB.setProperty( Column.PROPERTY_INSETS, new Insets(0)); 
		oStyleNoB.setProperty( Column.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyleNoB;
		
	}
	
	
	
	public static  MutableStyle STYLE_THIN_BORDER()
	{
		MutableStyle oStyleThinB = new MutableStyle();
		
		oStyleThinB.setProperty( Column.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		oStyleThinB.setProperty( Column.PROPERTY_INSETS, new Insets(2)); 
		oStyleThinB.setProperty( Column.PROPERTY_FONT, new E2_FontPlain());

		return oStyleThinB;
	}

	
	public static  MutableStyle STYLE_3D_BORDER()
	{
		MutableStyle oStyle3DB = new MutableStyle();
		
		oStyle3DB.setProperty( Column.PROPERTY_BORDER,  new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		oStyle3DB.setProperty( Column.PROPERTY_INSETS, new Insets(2)); 
		oStyle3DB.setProperty( Column.PROPERTY_FONT, new E2_FontPlain());
		
		return oStyle3DB;
		
	}

	
	
	public static ColumnLayoutData LAYOUT_LEFT(Insets oInsets)
	{
		ColumnLayoutData oLayout = new ColumnLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static ColumnLayoutData LAYOUT_CENTER(Insets oInsets)
	{
		ColumnLayoutData oLayout = new ColumnLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	public static ColumnLayoutData LAYOUT_RIGHT(Insets oInsets)
	{
		ColumnLayoutData oLayout = new ColumnLayoutData();
		oLayout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		if (oInsets != null)
			oLayout.setInsets(oInsets);
		return oLayout;
	}
	
	
	
	public Vector<Component>  get_vComponents(Vector<String> vClassTypes, Vector<String> vClassesExclude)
	{
		E2_RecursiveSearch_Component recSearch = new E2_RecursiveSearch_Component(this, vClassTypes, vClassesExclude);
		return recSearch.get_vAllComponents();
	}
	
	
	public void add_raw(Component oComp)
	{
		this.add(oComp);
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
