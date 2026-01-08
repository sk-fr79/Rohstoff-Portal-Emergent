package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextArea;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_TextArea extends TextArea implements MyE2IF__Component, E2_IF_Copy, MyE2IF__CanGetStampInfo
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	//private		int 		iWidthPixel = 20;
	private 	Extent 		m_ExtendWidth = new Extent(20, Extent.PX);
	
	private 	int 		iMaxInputSize = 10;
	private 	int 		iRows = 3;
	
	private 	XXX_StyleFactory  m_StyleFactory = new StyleFactory_TextArea();
	
	public MyE2_TextArea()
	{
		super();
		this.__setBasic();
	}
	
	
	
	public MyE2_TextArea(String cText,int iwidthPixel, int imaxInputSize, int irows)
	{
		super();

		this.__setBasic();
		
//		this.iWidthPixel = 		iwidthPixel;
		this.m_ExtendWidth = new Extent(iwidthPixel, Extent.PX);
		this.iMaxInputSize = 	imaxInputSize;
		this.iRows	= irows;
		
//		this.setWidth(new Extent(this.iWidthPixel,Extent.PX));
		this.setWidth(m_ExtendWidth);
		this.setHeight(new Extent(this.iRows, Extent.PC));   /* alternativ EM */
		this.setText(cText);
		
		this.setHorizontalScroll(new Extent(1));
		this.setVerticalScroll(new Extent(1));
		


		
		
	}
	
	public void __setBasic()
	{
		this.setFont(new E2_FontPlain());
		this.EXT().set_STYLE_FACTORY(m_StyleFactory);
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
	}
	
	/**
	 * Setzen der Stylefactory anderst als die default-Factory "StyleFactory_TextArea"
	 * 
	 * @param styleFactory
	 */
	public void __setStyleFactory(XXX_StyleFactory styleFactory){
		this.m_StyleFactory = styleFactory;
		this.__setBasic();
	}
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,false));
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_TextArea oRueck = new MyE2_TextArea();
		oRueck.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oRueck));
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.set_iMaxInputSize(this.get_iMaxInputSize());
		//oRueck.set_iWidthPixel(this.get_iWidthPixel());
		oRueck.set_WitdhExtent(this.get_WidthExtent());
		
		oRueck.setText(this.getText());
		oRueck.setWidth(this.getWidth());
		oRueck.setHeight(this.getHeight());
		
		return oRueck;
		
	}
	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}


	public int get_iMaxInputSize()								{		return iMaxInputSize;	}
	
	
	public void set_iMaxInputSize(int maxInputSize)			
	{		
		iMaxInputSize = maxInputSize;
		this.setMaximumLength(this.get_iMaxInputSize());
	}

	
	public Extent get_WidthExtent(){
		return this.m_ExtendWidth;
	}
	
	public void set_WitdhExtent(Extent extWidth){
		this.m_ExtendWidth = extWidth;
		this.setWidth(m_ExtendWidth);
	}
	
	
	public int get_iWidthPixel()	{		
		//return iWidthPixel;
		return this.m_ExtendWidth.getValue();
	}
	
	public void set_iWidthPixel(int widthPixel)				
	{		
//		iWidthPixel = widthPixel;
//		this.setWidth(new Extent(this.iWidthPixel,Extent.PX));
		this.m_ExtendWidth = new Extent(widthPixel,Extent.PX);
		this.setWidth(this.m_ExtendWidth);
		
		
		
	}

	public int get_iRows()								{		return iRows;	}
	public void set_iRows(int irows)				
	{		
		this.iRows = irows;
		this.setHeight(new Extent(this.iRows, Extent.PC));
	}
	
	
	public void show_InputStatus(boolean bInputIsOK)
	{
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,bInputIsOK));
	}

	
	/*
	 * setText wird ueberschrieben, weil in Echo2 
	 * die Zeilentrenner chr 13+10 als zusaetzlicher Zeilen-
	 * vorschub definiert ist, deshalb wird die Zeichenfolge Chr(10)und chr(10)
	 * in chr(10) umgewandelt 
	 * 
	 */
	public void setText(String cText)
	{
		
		String chr1310 = "\r\n"; 
		String chr10 = "\n";
		String cText2 = bibALL.ReplaceTeilString(cText,chr1310,chr10);
		super.setText(cText2);
	}

	
	
	
	public static MutableStyle  STYLE_ANZEIGE_FIELD(int iFontSizeDiff) 
	{
		MutableStyle oStyle = new MutableStyle();
		
		oStyle.setProperty(TextArea.PROPERTY_BACKGROUND, new E2_ColorGray(200));
		oStyle.setProperty(TextArea.PROPERTY_DISABLED_BACKGROUND , new E2_ColorGray(200));
		oStyle.setProperty(TextArea.PROPERTY_FONT, new E2_FontPlain(iFontSizeDiff));
		oStyle.setProperty(TextArea.PROPERTY_FOREGROUND, Color.BLACK);
		oStyle.setProperty(TextArea.PROPERTY_HORIZONTAL_SCROLL , new Extent(10));
		oStyle.setProperty(TextArea.PROPERTY_VERTICAL_SCROLL, new Extent(10));
		
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
