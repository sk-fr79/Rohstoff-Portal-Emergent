package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;

public class FSI_Label_NG extends MyE2_Label
{
	
	private int   i_left_mit_right__0_1_2 = 0;
	
	private int   iNumberOfCharToLineBreak=30;
	
	public FSI_Label_NG(String cText, int left_center_right__0_1_2)
	{
		super(cText, false,false);
		
		this.i_left_mit_right__0_1_2 = left_center_right__0_1_2;
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Label.PROPERTY_FONT, new E2_FontPlain(-2));
		
		if (S.NN(cText).length()>this.iNumberOfCharToLineBreak)
		{
			oStyle.setProperty(Label.PROPERTY_LINE_WRAP, Boolean.TRUE);
		}
		
		if (cText.trim().startsWith("<") && cText.trim().endsWith(">"))
		{
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.LIGHTGRAY);
		}
		else
		{
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);
		}
		this.setStyle(oStyle);
		
		GridLayoutData  oGL = this.baueLayoutData(this.i_left_mit_right__0_1_2);
		this.setLayoutData(oGL);
	}

	public FSI_Label_NG(String cText)
	{
		super(cText, false,false);
		
		this.i_left_mit_right__0_1_2 = 0;
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Label.PROPERTY_FONT, new E2_FontPlain(-2));
		
		if (S.NN(cText).length()>this.iNumberOfCharToLineBreak)
		{
			oStyle.setProperty(Label.PROPERTY_LINE_WRAP, Boolean.TRUE);
		}
		
		if (cText.trim().startsWith("<") && cText.trim().endsWith(">"))
		{
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.LIGHTGRAY);
		}
		else
		{
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);
		}
		this.setStyle(oStyle);
		
		GridLayoutData  oGL = this.baueLayoutData(this.i_left_mit_right__0_1_2);
		this.setLayoutData(oGL);
	}
	
	
	public FSI_Label_NG(String cText, int left_center_right__0_1_2, boolean bLineWrap)
	{
		super(cText, false,false);
		
		this.i_left_mit_right__0_1_2 = left_center_right__0_1_2;
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Label.PROPERTY_FONT, new E2_FontPlain(-2));
		
		if (S.NN(cText).length()>this.iNumberOfCharToLineBreak)
		{
			oStyle.setProperty(Label.PROPERTY_LINE_WRAP, Boolean.TRUE);
		}
		
		if (cText.trim().startsWith("<") && cText.trim().endsWith(">"))
		{
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.LIGHTGRAY);
		}
		else
		{
			oStyle.setProperty(Label.PROPERTY_FOREGROUND, Color.BLACK);
		}
		this.setStyle(oStyle);
		
		GridLayoutData  oGL = this.baueLayoutData(this.i_left_mit_right__0_1_2);
		
		this.setLineWrap(bLineWrap);
		
		this.setLayoutData(oGL);
	}

	public FSI_Label_NG _left(){
		GridLayoutData  oGL = this.baueLayoutData(0);
		this.setLayoutData(oGL);
		return this;
	}
	
	public FSI_Label_NG _right(){
		GridLayoutData  oGL = this.baueLayoutData(2);
		this.setLayoutData(oGL);
		return this;
	}
	
	private GridLayoutData baueLayoutData(int left_mit_right__0_1_2)
	{
		GridLayoutData oGL = new GridLayoutData();
				
		switch (left_mit_right__0_1_2)
		{
			case (0):
			{
				oGL.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
				break;
			}
			case (1):
			{
				oGL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
				break;
			}
			case (2):
			{
				oGL.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
				break;
			}
		}
		oGL.setInsets(new Insets(1,2,2,2));
		
		return oGL;
	}
	
	
	
	
}
