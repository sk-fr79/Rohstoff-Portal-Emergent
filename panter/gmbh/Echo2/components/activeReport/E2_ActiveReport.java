package panter.gmbh.Echo2.components.activeReport;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class E2_ActiveReport extends MyE2_Grid 
{

	private Vector<E2_ActiveReportBlock> vFirstLevelReportBlocks = new Vector<E2_ActiveReportBlock>();

	public E2_ActiveReport(int iNumCols, MutableStyle oStyle) 
	{
		super(iNumCols, oStyle);
	}

	public E2_ActiveReport(int[] iSpalten, MutableStyle oStyle) 
	{
		super(iSpalten, oStyle);
	}

	public void fill_Report() throws myException 
	{
		this.removeAll();

		for (int i = 0; i < this.vFirstLevelReportBlocks.size(); i++) 
		{
			E2_ActiveReportBlock oReportBlock = this.vFirstLevelReportBlocks.get(i);
			oReportBlock.built(this, null);
		}
	}

	public Vector<E2_ActiveReportBlock> get_vFirstLevelReportBlocks() {
		return vFirstLevelReportBlocks;
	}

	public static GridLayoutData LAYOUT_LEFT_DARK(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorDDark(),
						new Alignment(Alignment.LEFT, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_CENTER_DARK(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorDDark(),
						new Alignment(Alignment.CENTER, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_RIGHT_DARK(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorDDark(),
						new Alignment(Alignment.RIGHT, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_LEFT(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorBase(),
						new Alignment(Alignment.LEFT, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_CENTER(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorBase(),
						new Alignment(Alignment.CENTER, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_RIGHT(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorBase(),
						new Alignment(Alignment.RIGHT, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_LEFT_LIGHT(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorLight(),
						new Alignment(Alignment.LEFT, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_CENTER_LIGHT(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorLight(),
						new Alignment(Alignment.CENTER, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static GridLayoutData LAYOUT_RIGHT_LIGHT(Insets In, int iColSpan) {
		GridLayoutData oLayoutRueck = LayoutDataFactory
				.get_GridLayoutGridCenter(In, new E2_ColorLight(),
						new Alignment(Alignment.RIGHT, Alignment.CENTER));
		oLayoutRueck.setColumnSpan(iColSpan);
		return oLayoutRueck;
	}

	public static MyE2_Label get_LabelTitel(Object cText) 
	{
		MyE2_Label oLabelRueck = new MyE2_Label(cText);
		oLabelRueck.setStyle(MyE2_Label.STYLE_TITEL_BIG());

		return oLabelRueck;
	}

	public static MyE2_Label get_Label_SUB_Ueberschrift(Object cText) 
	{
		MyE2_Label oLabelRueck = new MyE2_Label(cText);
		MutableStyle oStyle = MyE2_Label.STYLE_SMALL_ITALIC();
		oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
		oLabelRueck.setStyle(oStyle);

		return oLabelRueck;
	}

	public static MyE2_Label get_LabelStd(Object cText)
	{
		MyE2_Label  oLabelRueck = new MyE2_Label(cText);
		MutableStyle  oStyle = MyE2_Label.STYLE_NORMAL_PLAIN();
		
		String cTestString = "";
		if (cText instanceof MyString)
		{
			cTestString = ((MyString)cText).COrig().trim();
		}
		else if (cText instanceof String)
		{
			cTestString = ((String)cText).trim();
		}
		
	    if (cTestString.startsWith("<") && cTestString.endsWith(">"))
	    {
	    	oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
	    }
		oLabelRueck.setStyle(oStyle);
		
		return oLabelRueck;
	}


	
	public static MyE2_Label get_LabelStd_LineWrap(Object cText)
	{
		MyE2_Label  oLabelRueck = new MyE2_Label(cText);
		MutableStyle  oStyle = MyE2_Label.STYLE_NORMAL_PLAIN();
		
		String cTestString = "";
		if (cText instanceof MyString)
		{
			cTestString = ((MyString)cText).COrig().trim();
		}
		else if (cText instanceof String)
		{
			cTestString = ((String)cText).trim();
		}
		
	    if (cTestString.startsWith("<") && cTestString.endsWith(">"))
	    {
	    	oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
	    }
	    
	    oStyle.setProperty(Label.PROPERTY_LINE_WRAP, new Boolean(true));
	    
		oLabelRueck.setStyle(oStyle);
		
		oLabelRueck.setLineWrap(true);
		
		return oLabelRueck;
	}

	
	
	public static MyE2_Label get_LabelStd_Bold(Object cText)
	{
		MyE2_Label  oLabelRueck = new MyE2_Label(cText);
		MutableStyle  oStyle = MyE2_Label.STYLE_NORMAL_BOLD();
		
		String cTestString = "";
		if (cText instanceof MyString)
		{
			cTestString = ((MyString)cText).COrig().trim();
		}
		else if (cText instanceof String)
		{
			cTestString = ((String)cText).trim();
		}
		
	    if (cTestString.startsWith("<") && cTestString.endsWith(">"))
	    {
	    	oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.DARKGRAY);
	    }
	    
		oLabelRueck.setStyle(oStyle);
		
		return oLabelRueck;
	}



}
