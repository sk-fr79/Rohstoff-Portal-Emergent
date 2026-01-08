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
import nextapp.echo2.app.Label;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlanCourier;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.Echo2.staticStyles.Style_Label_Basic;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_Label extends Label  implements 	MyE2IF__Component, 
													E2_IF_Copy, 
													IF_GetInBorder,
													MyE2IF__CanGetStampInfo
{


	
	private MyE2EXT__Component 	oEXT = new MyE2EXT__Component(this);
	private Object 				oTextObject = null;
	
	/*
	 * falls MyE2_Label  in multicolum/multirow-elementen eingeblendet werden, werden u.U. leeranzeign
	 * mit hoehe 0 angezeigt. dafuer kann ein ersatz eingegeben werden
	 */
	private String 				cErsatzFuerLeeranzeige = null;

	
	/*
	 * der label merkt sich den ursprungs-String
	 * der uebergebene Text wird in einen Roh-Text uebergeben und kann dann ausgetauscht werden
	 */
	private Object 				StringRohMitPlatzhalter = null; 
	

	//falls IF_GetInBorder zum zug kommt
	private 					MyE2_Grid_InLIST  oGridRueck = null;
	
	
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	public MyE2_Label()
	{
		super();
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(false);
	}

	
	/**
	 * mit null, false,false ist der label voellig nackt
	 * @param oText
	 * @param bSetBasicStyle
	 * @param bLineWrap
	 */
	public MyE2_Label(Object oText, boolean bSetBasicStyle, boolean bLineWrap)
	{
		super();
		if (oText != null)      this.set_Text(oText);
		if (bSetBasicStyle) 	this.setStyle(new Style_Label_Basic());
		if (bLineWrap) 			this.setLineWrap(bLineWrap);
	}

	
	
	public MyE2_Label(ImageReference arg0)
	{
		super(arg0);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(false);
	}
	public MyE2_Label(Object cText, ImageReference arg1)
	{
		super();
		this.set_Text(cText);
		this.setIcon(arg1);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(false);
	}
	public MyE2_Label(Object cText)
	{
		super();
		this.set_Text(cText);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(false);
	}


	public MyE2_Label(Object cText, MyE2_String cToolTipText)
	{
		super();
		this.set_Text(cText);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(false);
		this.setToolTipText(cToolTipText.CTrans());
		
	}
	
	
	
	public MyE2_Label(Object cText, Font oFont)
	{
		super();
		this.set_Text(cText);
		this.setStyle(new Style_Label_Basic(oFont));
		this.setLineWrap(false);
	}


	
	public MyE2_Label(MutableStyle oStyle)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(false);
	}
	public MyE2_Label(ImageReference arg0,MutableStyle oStyle)
	{
		super(arg0);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(false);
	}
	public MyE2_Label(Object cText, ImageReference arg1,MutableStyle oStyle)
	{
		super();
		this.set_Text(cText);
		this.setIcon(arg1);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(false);
	}
	public MyE2_Label(Object cText,MutableStyle oStyle)
	{
		super();
		this.set_Text(cText);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(false);
	}


	public MyE2_Label(Object cText,MutableStyle oStyle, LayoutData oLayout)
	{
		super();
		this.set_Text(cText);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(false);
		
		this.setLayoutData(oLayout);
	}


	// 2011-01-12: eine neue satz kontruktoren mit linewrap-moeglichkeit
	public MyE2_Label(boolean bLineWrap)
	{
		super();
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(bLineWrap);
	}
	public MyE2_Label(ImageReference arg0, boolean bLineWrap)
	{
		super(arg0);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(bLineWrap);
	}
	public MyE2_Label(Object cText, ImageReference arg1, boolean bLineWrap)
	{
		super();
		this.set_Text(cText);
		this.setIcon(arg1);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(bLineWrap);
	}
	public MyE2_Label(Object cText, boolean bLineWrap)
	{
		super();
		this.set_Text(cText);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(bLineWrap);
	}


	public MyE2_Label(Object cText, Font oFont, boolean bLineWrap)
	{
		super();
		this.set_Text(cText);
		this.setStyle(new Style_Label_Basic(oFont));
		this.setLineWrap(bLineWrap);
	}


	
	
	public MyE2_Label(MutableStyle oStyle, boolean bLineWrap)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(bLineWrap);
	}
	public MyE2_Label(ImageReference arg0,MutableStyle oStyle, boolean bLineWrap)
	{
		super(arg0);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(bLineWrap);
	}
	public MyE2_Label(Object cText, ImageReference arg1,MutableStyle oStyle, boolean bLineWrap)
	{
		super();
		this.set_Text(cText);
		this.setIcon(arg1);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(bLineWrap);
	}
	public MyE2_Label(Object cText,MutableStyle oStyle, boolean bLineWrap)
	{
		super();
		this.set_Text(cText);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(bLineWrap);
	}


	public MyE2_Label(Object cText,MutableStyle oStyle, LayoutData oLayout, boolean bLineWrap)
	{
		super();
		this.set_Text(cText);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(bLineWrap);
		
		this.setLayoutData(oLayout);
	}
	/////////////7777

	
	
	public void setStyle(Style oStyle)
	{
		super.setStyle(oStyle);
		if (oStyle instanceof E2_MutableStyle)
		{
			this.EXT().set_oE2_Style((E2_MutableStyle)oStyle);
		}
	}
	

	//2011-02-10: Grid in Rahmen
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt, Insets oInsets)
	{
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		//oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		oStyle.setProperty(Grid.PROPERTY_INSETS, E2_INSETS.I(0,0,0,0));
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		this.oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this,oInsets!=null?oInsets:E2_INSETS.I(0,0,0,0));
		
		this.oGridRueck.setVisible(this.isVisible());
		
		return oGridRueck;
	}
	

	public void setVisible(boolean bVisible)
	{
		super.setVisible(bVisible);
		
		if (this.oGridRueck!=null)
		{
			this.oGridRueck.setVisible(bVisible);
		}
	}
	
	
	public void set_Text(Object cText)
	{
		
		
		this.oTextObject = cText;
		this.StringRohMitPlatzhalter = cText;
		
		if (cText == null)
		{
			this.setText(null);
		}
		else if (cText instanceof String)
		{
			this.setText((String)cText);
		} 
		else if (cText instanceof MyString)
		{
			this.setText(((MyString)cText).CTrans());
		}
		else
		{
			this.setText("@@@ERROR@@@");
		}
		
		if (bibALL.isEmpty(this.getText()) && this.cErsatzFuerLeeranzeige != null)
			this.setText(this.cErsatzFuerLeeranzeige);
			
		
	}

	
	
	public Object get_oTextObject()
	{
		return oTextObject;
	}


	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}



	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Label oLabCopy = new MyE2_Label();
		oLabCopy.setFont(this.getFont());
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		if (this.getIcon() != null)
			oLabCopy.setIcon(this.getIcon());
		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());
		
		if (this.getStyle() != null)
			oLabCopy.setStyle(this.getStyle());
		
		if (this.getLayoutData() != null)
			oLabCopy.setLayoutData(this.getLayoutData());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.cErsatzFuerLeeranzeige);
		oLabCopy.set_oStringRohMitPlatzhalter(this.get_oStringRohMitPlatzhalter());

		oLabCopy.setLineWrap(this.isLineWrap());

		
		return oLabCopy;
		
	}

	public String get_cErsatzFuerLeeranzeige()
	{
		return cErsatzFuerLeeranzeige;
	}

	public void set_cErsatzFuerLeeranzeige(String ersatzFuerLeeranzeige)
	{
		cErsatzFuerLeeranzeige = ersatzFuerLeeranzeige;
	}

	/**
	 * @param cPlatzHalter
	 * @param cErsatz (wid nicht uebersetzt)
	 */
	public void set_ReplaceText(String cPlatzHalter, String cErsatz)
	{
		if (StringRohMitPlatzhalter == null)
		{
			this.setText(null);
		}
		else if (StringRohMitPlatzhalter instanceof String)
		{
			this.setText(bibALL.ReplaceTeilString(bibALL.null2leer((String)StringRohMitPlatzhalter), cPlatzHalter, cErsatz));
		} 
		else if (StringRohMitPlatzhalter instanceof MyString)
		{
			String cHelp = bibALL.ReplaceTeilString(bibALL.null2leer(  ((MyString)StringRohMitPlatzhalter).COrig()), cPlatzHalter, cErsatz);
			this.setText(new MyE2_String(cHelp).CTrans());
		}
		else
		{
			this.setText("@@@ERROR@@@");
		}

		
		
	}

	public Object get_oStringRohMitPlatzhalter()
	{
		return StringRohMitPlatzhalter;
	}

	public void set_oStringRohMitPlatzhalter(Object stringRohMitPlatzhalter)
	{
		StringRohMitPlatzhalter = stringRohMitPlatzhalter;
	}

	
	
	
	
	
	public static E2_MutableStyle STYLE_SMALL_PLAIN()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_PLAIN");
		if (oStyle==null)
		{
			oStyle= new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain(-2));
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_PLAIN", oStyle);
		}
		return oStyle;
	}

	

	public static E2_MutableStyle STYLE_SMALL_PLAIN_Wrap()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_PLAIN_WRAP");
		if (oStyle==null)
		{
			oStyle= new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain(-2));
			oStyle.setProperty( Label.PROPERTY_LINE_WRAP, new Boolean(true));
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_PLAIN_WRAP", oStyle);
		}
		return oStyle;
	}

	
	
	public static E2_MutableStyle STYLE_SMALL_ITALIC()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_ITALIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontItalic(-2)); 
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_ITALIC", oStyle);
		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_SMALL_ITALIC_WRAP()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_ITALIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontItalic(-2)); 
			oStyle.setProperty( Label.PROPERTY_LINE_WRAP, new Boolean(true));
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_ITALIC", oStyle);
		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_SMALL_ITALIC_GREY()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_ITALIC_GREY");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontItalic(-2)); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_ITALIC_GREY", oStyle);
		}
		return oStyle;
	}


	public static E2_MutableStyle STYLE_NORMAL_GREY()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_GREY");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain()); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_GREY", oStyle);
		}
		return oStyle;
	}


	public static E2_MutableStyle STYLE_NORMAL_RED()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_RED");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain()); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.RED);
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_RED", oStyle);
		}
		return oStyle;
	}
	
	public static E2_MutableStyle STYLE_BOLD_RED()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_BOLD_RED");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold()); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.RED);
			bibE2.set_MutableStyle4Labels("STYLE_BOLD_RED", oStyle);
		}
		return oStyle;
	}
	
	public static E2_MutableStyle STYLE_SUPERSMALL_ITALIC()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SUPERSMALL_ITALIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontItalic(-4)); 
			bibE2.set_MutableStyle4Labels("STYLE_SUPERSMALL_ITALIC", oStyle);
		}
		return oStyle;
	}

	
	public static E2_MutableStyle STYLE_SMALL_ITALIC_LINETHROUGH()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_ITALIC_LINETHROUGH");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2)); 
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_ITALIC_LINETHROUGH", oStyle);
		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_SMALL_BOLD()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_BOLD");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold(-2)); 
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_BOLD", oStyle);

			
		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_SMALL_BOLD_ITALIC()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_BOLD_ITALIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBoldItalic(-2)); 
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_BOLD_ITALIC", oStyle);

		}
		return oStyle;
	}

	
	public static E2_MutableStyle STYLE_SMALL_BOLD_ITALIC_LINETHROUGH()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_SMALL_BOLD_ITALIC_LINETHROUGH");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_Font(Font.BOLD+Font.ITALIC+Font.LINE_THROUGH,-2)); 
			bibE2.set_MutableStyle4Labels("STYLE_SMALL_BOLD_ITALIC_LINETHROUGH", oStyle);

		}
		return oStyle;
	}
	
	///
	public static E2_MutableStyle STYLE_NORMAL_PLAIN()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_PLAIN");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain()); 
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_PLAIN", oStyle);

		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_NORMAL_ITALLIC()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_ITALLIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontItalic()); 
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_ITALLIC", oStyle);

		}
		return oStyle;
	}


	public static E2_MutableStyle STYLE_NORMAL_ITALIC_GREY()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_ITALLIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontItalic()); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_ITALLIC", oStyle);

			
		}
		return oStyle;
	}

	
	
	public static E2_MutableStyle STYLE_NORMAL_BOLD()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_BOLD");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold()); 
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_BOLD", oStyle);

		}
		return oStyle;
	}

	
	public static E2_MutableStyle STYLE_NORMAL_BOLD_GREY()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_BOLD_GREY");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold()); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_BOLD_GREY", oStyle);

		}
		return oStyle;
	}

	
	
	public static E2_MutableStyle STYLE_NORMAL_BOLD_ITALIC()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_NORMAL_BOLD_ITALIC");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBoldItalic()); 
			bibE2.set_MutableStyle4Labels("STYLE_NORMAL_BOLD_ITALIC", oStyle);

		}
		return oStyle;
	}
///
	
	
	public static E2_MutableStyle STYLE_TITEL_NORMAL()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_TITEL_NORMAL");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold(0));
			bibE2.set_MutableStyle4Labels("STYLE_TITEL_NORMAL", oStyle);

		}
		return oStyle;
	}

	/**
	 * Bold
	 * Font + 2
	 * 
	 * @author manfred
	 * @date   16.04.2013
	 * @return
	 */
	public static E2_MutableStyle STYLE_TITEL_BIG()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_TITEL_BIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold(2)); 
			bibE2.set_MutableStyle4Labels("STYLE_TITEL_BIG", oStyle);

		}
		return oStyle;
	}

	
	/**
	 * Normal
	 * Font +2
	 * Rot
	 * @author manfred
	 * @date   16.04.2013
	 * @return
	 */
	public static E2_MutableStyle STYLE_TITEL_BIG_BOLD_RED()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_TITEL_BIG_RED");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold(2)); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.RED);
			bibE2.set_MutableStyle4Labels("STYLE_TITEL_BIG_RED", oStyle);

		}
		return oStyle;
	}
	
	
	
	/**
	 * Plain
	 * Font +2
	 * Rot
	 * @author manfred
	 * @date   16.04.2013
	 * @return
	 */
	public static E2_MutableStyle STYLE_TITEL_BIG_PLAIN_RED()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_TITEL_BIG_RED");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain(2)); 
			oStyle.setProperty( Label.PROPERTY_FOREGROUND, Color.RED);
			bibE2.set_MutableStyle4Labels("STYLE_TITEL_BIG_RED", oStyle);

		}
		return oStyle;
	}
	
	/**
	 * Plain
	 * Font +2
	 * Rot
	 * @author manfred
	 * @date   16.04.2013
	 * @return
	 */
	public static E2_MutableStyle STYLE_TITEL_BIG_PLAIN()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_TITEL_BIG_PLAIN");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain(2)); 
			bibE2.set_MutableStyle4Labels("STYLE_TITEL_BIG_PLAIN", oStyle);

		}
		return oStyle;
	}
	
	
	
	public static E2_MutableStyle STYLE_COURIER()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_COURIER");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlanCourier(0)); 
			bibE2.set_MutableStyle4Labels("STYLE_COURIER", oStyle);

		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_COURIER_SMALL()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_COURIER_SMALL");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlanCourier(-2)); 
			bibE2.set_MutableStyle4Labels("STYLE_COURIER_SMALL", oStyle);

		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_ERROR_NORMAL()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ERROR_NORMAL");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontPlain()); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, new E2_ColorAlarm());
			bibE2.set_MutableStyle4Labels("STYLE_ERROR_NORMAL", oStyle);

		}
		return oStyle;
	}


	public static E2_MutableStyle STYLE_ERROR_NORMAL_WRAP()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ERROR_NORMAL_WRAP");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT, new E2_FontBold()); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, new E2_ColorAlarm());
			oStyle.setProperty(Label.PROPERTY_LINE_WRAP,  new Boolean(true));
			bibE2.set_MutableStyle4Labels("STYLE_ERROR_NORMAL_WRAP", oStyle);
		}
		return oStyle;
	}

	
	
	public static E2_MutableStyle STYLE_ERROR_BIG()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ERROR_BIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(2)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, new E2_ColorAlarm());
			bibE2.set_MutableStyle4Labels("STYLE_ERROR_BIG", oStyle);

		}
		return oStyle;
	}
	
	
	public static E2_MutableStyle STYLE_ERROR_BIG_WRAP()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ERROR_BIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(2)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, new E2_ColorAlarm());
			oStyle.setProperty(Label.PROPERTY_LINE_WRAP,  new Boolean(true));
			bibE2.set_MutableStyle4Labels("STYLE_ERROR_BIG", oStyle);

		}
		return oStyle;
	}

	
	public static E2_MutableStyle STYLE_ERROR_BIGBIG()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ERROR_BIGBIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(4)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, new E2_ColorAlarm());
			bibE2.set_MutableStyle4Labels("STYLE_ERROR_BIGBIG", oStyle);

		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_ERROR_BIGBIGBIG()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ERROR_BIGBIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(4)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, new E2_ColorAlarm());
			bibE2.set_MutableStyle4Labels("STYLE_ERROR_BIGBIG", oStyle);

		}
		return oStyle;
	}

	
	public static E2_MutableStyle STYLE_GREEN_BIG()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_GREEN_BIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(2)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.GREEN);
			bibE2.set_MutableStyle4Labels("STYLE_GREEN_BIG", oStyle);

		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_GREEN_BIG_WRAP()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_GREEN_BIG_WRAP");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(2)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.GREEN);
			oStyle.setProperty(Label.PROPERTY_LINE_WRAP,  new Boolean(true));
			bibE2.set_MutableStyle4Labels("STYLE_GREEN_BIG_WRAP", oStyle);
			

		}
		return oStyle;
	}

	public static E2_MutableStyle STYLE_GREEN_BIGBIG()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_GREEN_BIGBIG");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(4)); 
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.GREEN);
			bibE2.set_MutableStyle4Labels("STYLE_GREEN_BIGBIG", oStyle);

		}
		return oStyle;
	}

	
	
	
	public static E2_MutableStyle STYLE_ALARM_LABEL()
	{
		E2_MutableStyle oStyle = bibE2.get_MutableStyle4Labels("STYLE_ALARM_LABEL");
		if (oStyle==null)
		{
			oStyle = new E2_MutableStyle();
			oStyle.setProperty( Label.PROPERTY_FONT,new E2_FontBold(2)); 
			oStyle.setProperty(Label.PROPERTY_BACKGROUND, new E2_ColorAlarm());
			bibE2.set_MutableStyle4Labels("STYLE_ALARM_LABEL", oStyle);

		}
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
