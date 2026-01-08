package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_TextField extends TextField implements MyE2IF__Component, E2_IF_Copy, MyE2IF__CanGetStampInfo
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	private		int 		iWidthPixel = 100;
	private 	int 		iMaxInputSize = 100;
	
	//2011-11-22: textfeld mit automatischer verkleinerung ab einer bestimmten laenge
	private     int         iLengthFontDownsizingStart = 0;        //wenn > 0, dann wird der text ab dieser laenge automatisch mit 2 verkleinert
	private     Font        oFontNormal = null;
	private     Font        oFontSmall  = null;
	
	
	//2011-11-22: textfeld mit automatischer verkleinerung ab einer bestimmten laenge
	public void setText(String cText)
	{
		super.setText(cText);
		
		try
		{
			if (this.iLengthFontDownsizingStart>0 && this.oFontNormal!=null && this.oFontSmall!=null)
			{
				this.setFont(this.oFontNormal);
				if (cText.length()>this.iLengthFontDownsizingStart)
				{
					this.setFont(this.oFontSmall);
				}
			}
		}
		catch (Exception ex)
		{
			
		}
		
		//wenn der font kleiner als standard, dann sitzt der text zu weit oben
		if (this.getFont() != null)
		{
			this.setInsets(E2_INSETS.I_0_0_0_0);
			if (this.getFont().getSize().getValue() < bibALL.get_FONT_SIZE())
			{
				this.setInsets(E2_INSETS.I_0_1_0_0);
			}
		}
		
		
	}
	
	/**
	 * 
	 * @param LengthFontDownsizingStart (wirkt nur >0)
	 * @param FontNormal  				wirkt nur != null
	 * @param FontSmall   				wirkt nur != null
	 */
	public void set_AutoFontDownSize(int LengthFontDownsizingStart, Font FontNormal, Font FontSmall)
	{
		this.iLengthFontDownsizingStart=LengthFontDownsizingStart;
		this.oFontNormal=FontNormal;
		this.oFontSmall=FontSmall;
	}
	
	
	
	
	public MyE2_TextField()
	{
		super();
		this.__setBasic();
		
	}
	
	
	public MyE2_TextField(String cText,int iwidthPixel, int imaxInputSize)
	{
		super();
		this.iWidthPixel = 		iwidthPixel;
		this.iMaxInputSize = 	imaxInputSize;
		this.__setBasic();
		
		this.setText(cText);
		
	}
	
	
	public MyE2_TextField(String cText,int iwidthPixel, int imaxInputSize, MyE2_String cToolTips)
	{
		super();
		this.iWidthPixel = 		iwidthPixel;
		this.iMaxInputSize = 	imaxInputSize;
		this.__setBasic();
		
		this.setText(cText);
		
		if (cToolTips!=null)
		{
			this.setToolTipText(cToolTips.CTrans());
		}
		
	}

	
	
	public void __setBasic()
	{
		this.setWidth(new Extent(this.iWidthPixel,Extent.PX));
		//this.setMaximumLength(10);
		this.setMaximumLength(iMaxInputSize);
		
		//2013-01-29: wird ein font vergeben, dann funktionieren die style nicht mehr (was die fonts angeht)
		//this.setFont(new E2_FontPlain());
		this.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.DEFAULT));
		

	}
	
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{

		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		
		MutableStyle style = this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,false);
		
		this.setStyle(style);
		
		if (this.isEnabled())  {
			this.setFocusTraversalParticipant(true);
		} else {
			this.setFocusTraversalParticipant(false);
		}
	
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_TextField oRueck = new MyE2_TextField();
		
		oRueck.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oRueck));
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.set_iMaxInputSize(this.get_iMaxInputSize());
		oRueck.set_iWidthPixel(this.get_iWidthPixel());
		oRueck.setText(this.getText());
		oRueck.setWidth(this.getWidth());
		
		//2011-11-22: textfeld mit automatischer verkleinerung ab einer bestimmten laenge
		oRueck.set_AutoFontDownSize(this.get_iLengthFontDownsizingStart(), this.get_oFontNormal_4_FontDownsizing(), this.get_oFontSmall_4_FontDownsizing());
		
		return oRueck;
		
	}
	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public int get_iMaxInputSize()								{		return iMaxInputSize;	}
	
	public void set_iMaxInputSize(int maxInputSize)			
	{		
		iMaxInputSize = maxInputSize;
		this.setMaximumLength(this.iMaxInputSize);
	}
	
	public int get_iWidthPixel()								{		return iWidthPixel;	}
	public void set_iWidthPixel(int widthPixel)				
	{		
		iWidthPixel = widthPixel;
		this.setWidth(new Extent(this.iWidthPixel,Extent.PX));
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,bInputIsOK));
	}

	
	//2011-11-22: textfeld mit automatischer verkleinerung ab einer bestimmten laenge
	public int get_iLengthFontDownsizingStart()
	{
		return iLengthFontDownsizingStart;
	}

	public Font get_oFontNormal_4_FontDownsizing()
	{
		return oFontNormal;
	}

	public Font get_oFontSmall_4_FontDownsizing()
	{
		return oFontSmall;
	}

	
	public static MutableStyle STYLE_SMALL_KURSIV()
	{
		MutableStyle oStyle = new MutableStyle();
		
		oStyle.setProperty(TextField.PROPERTY_DISABLED_FONT, new E2_FontItalic(-4));
		return oStyle;
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
		return S.NN(this.getText());
	}

	
}
