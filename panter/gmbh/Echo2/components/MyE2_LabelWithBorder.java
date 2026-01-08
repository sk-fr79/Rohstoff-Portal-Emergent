package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.staticStyles.Style_Label_Basic;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_LabelWithBorder extends Grid  implements MyE2IF__Component, E2_IF_Copy, MyE2IF__CanGetStampInfo
{

	public static  MutableStyle 		STYLE_BORDER_BLACK = null;
	public static  GridLayoutData		CONTENT_LAYOUT_LEFT = null;
	public static  GridLayoutData		CONTENT_LAYOUT_CENTER = null;
	public static  GridLayoutData		CONTENT_LAYOUT_RIGHT = null;
	static
	{
		MutableStyle oSTYLE_BORDER_BLACK = new MutableStyle();
		oSTYLE_BORDER_BLACK.setProperty( Grid.PROPERTY_INSETS, new Insets(1,0,1,0));
		oSTYLE_BORDER_BLACK.setProperty( Grid.PROPERTY_BORDER, new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oSTYLE_BORDER_BLACK.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		STYLE_BORDER_BLACK = oSTYLE_BORDER_BLACK;
		
		GridLayoutData		oCONTENT_LAYOUT_LEFT = new GridLayoutData();
		GridLayoutData		oCONTENT_LAYOUT_CENTER = new GridLayoutData();
		GridLayoutData		oCONTENT_LAYOUT_RIGHT = new GridLayoutData();
		
		oCONTENT_LAYOUT_LEFT.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		oCONTENT_LAYOUT_CENTER.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		oCONTENT_LAYOUT_RIGHT.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		
		CONTENT_LAYOUT_LEFT = oCONTENT_LAYOUT_LEFT;
		CONTENT_LAYOUT_CENTER = oCONTENT_LAYOUT_CENTER;
		CONTENT_LAYOUT_RIGHT = oCONTENT_LAYOUT_RIGHT;
	}


	public static MutableStyle STYLE_BORDER_NONE()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(0,new E2_ColorLight(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}
	
	public static MutableStyle STYLE_BORDER_LIGHTCOLOR()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(1,new E2_ColorLight(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}

	
	public static MutableStyle STYLE_BORDER_LLIGHTCOLOR()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(1,new E2_ColorLLight(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}
	public static MutableStyle STYLE_BORDER_LLLIGHTCOLOR()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(1,new E2_ColorLLLight(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}
	public static MutableStyle STYLE_BORDER_DARKCOLOR()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(1,new E2_ColorDark(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}
	public static MutableStyle STYLE_BORDER_DDARKCOLOR()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}
	
	public static MutableStyle STYLE_BORDER_DDDARKCOLOR()
	{
		MutableStyle oRueck = new MutableStyle();
		oRueck.setProperty( Row.PROPERTY_INSETS, new Insets(1,0,1,0));
		oRueck.setProperty( Row.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oRueck.setProperty( Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
		return oRueck;
	}
	
	// wird als label in einer row definiert
	private MyE2_Label				ownLabel = new MyE2_Label();
	
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
	// hier bekommt auch der border einen style
	private MutableStyle			oMutableStyleBorder = null; 		
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}
 
	public MyE2_LabelWithBorder(MutableStyle MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.setStyle(new Style_Label_Basic());
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}
	public MyE2_LabelWithBorder(ImageReference arg0,MutableStyle MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.setIcon(arg0);
		this.ownLabel.setStyle(new Style_Label_Basic());
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}
	public MyE2_LabelWithBorder(Object cText, ImageReference arg1,MutableStyle MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.set_Text(cText);
		this.ownLabel.setIcon(arg1);
		this.ownLabel.setStyle(new Style_Label_Basic());
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}
	public MyE2_LabelWithBorder(Object cText,MutableStyle	MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.set_Text(cText);
		this.ownLabel.setStyle(new Style_Label_Basic());
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}


	public MyE2_LabelWithBorder(MutableStyle oStyle,MutableStyle MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		if (oStyle != null) this.setStyle(oStyle);
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}

	public MyE2_LabelWithBorder(ImageReference arg0,MutableStyle oStyleLabel,MutableStyle	 MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.setIcon(arg0);
		if (oStyleLabel != null) this.ownLabel.setStyle(oStyleLabel);
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}
 
	public MyE2_LabelWithBorder(Object cText, ImageReference arg1,MutableStyle oStyleLabel,MutableStyle	MutableStyleBorder, LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.set_Text(cText);
		this.ownLabel.setIcon(arg1);
		if (oStyleLabel != null) this.setStyle(oStyleLabel);
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}
	
	public MyE2_LabelWithBorder(Object cText,MutableStyle oStyleLabel, MutableStyle MutableStyleBorder , LayoutData oGridLayout)
	{
		super(1);
		this.setStyle(MutableStyleBorder);
		this.ownLabel.setLayoutData(oGridLayout);
		this.oMutableStyleBorder = MutableStyleBorder;
		this.ownLabel.set_Text(cText);
		if (oStyleLabel != null) this.ownLabel.setStyle(oStyleLabel);
		this.ownLabel.setLineWrap(false);
		this.add(this.ownLabel);
	}


	public void set_Text(Object cText)
	{
		this.ownLabel.set_Text(cText);
	}

	public Object get_oTextObject()
	{
		return this.ownLabel.get_oTextObject();
	}

	public void setFont(Font oFont)
	{
		this.ownLabel.setFont(oFont);
	}

	public Font getFont()
	{
		return this.ownLabel.getFont();
	}

	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()							{		return this.oEXT;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}



	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_LabelWithBorder oLabCopy = new MyE2_LabelWithBorder(this.get_MutableStyleBorder(),this.ownLabel.getLayoutData());
		oLabCopy.get_ownLabel().setFont(this.getFont());
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		if (this.get_ownLabel().getIcon() != null)
			oLabCopy.get_ownLabel().setIcon(this.ownLabel.getIcon());
		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());

		if (this.getStyle() != null)
			oLabCopy.setStyle(this.getStyle());
		
		if (this.ownLabel.getLayoutData() != null)
			oLabCopy.get_ownLabel().setLayoutData(this.ownLabel.getLayoutData());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.get_cErsatzFuerLeeranzeige());
		
		return oLabCopy;
		
	}

	public String get_cErsatzFuerLeeranzeige()
	{
		return this.ownLabel.get_cErsatzFuerLeeranzeige();
	}

	public void set_cErsatzFuerLeeranzeige(String ersatzFuerLeeranzeige)
	{
		this.ownLabel.set_cErsatzFuerLeeranzeige(ersatzFuerLeeranzeige);
	}

	public MyE2_Label get_ownLabel()
	{
		return ownLabel;
	}

	public void set_ownLabel(MyE2_Label ownLabel)
	{
		this.ownLabel = ownLabel;
	}

	public MutableStyle get_MutableStyleBorder()
	{
		return this.oMutableStyleBorder;
	}

	public void set_MutableStyleBorder(MutableStyle oStyleBorder)
	{
		this.setStyle(oStyleBorder);
		this.oMutableStyleBorder=oStyleBorder;
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

	@Override
	public String get_STAMP_INFO() throws myException {
		return S.NN(this.ownLabel.getText());
	}

	
}
