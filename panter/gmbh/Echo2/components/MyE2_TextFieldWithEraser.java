package panter.gmbh.Echo2.components;


import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_TextFieldWithEraser extends MyE2_Grid  implements MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	private		int 		iWidthPixel = 100;
	private 	int 		iMaxInputSize = 100;
	
	private     MyE2_TextField   internalTextField = 	new MyE2_TextField();
	private 	MyE2_Button		 buttonErase	= 		new MyE2_Button(
											E2_ResourceIcon.get_RI("eraser.png"),
											E2_ResourceIcon.get_RI("leer.png"),
											new MyE2_String("Eingabe löschen"),
											new ownActionAgent());
	
	private     Insets           insetBetween_Elements = E2_INSETS.I_0_0_2_0;
	
	private     boolean        	 bSmallEraser = false;
	
	private     Vector<Component>  vAddOns = new Vector<Component>();
	
	
	
	public MyE2_TextFieldWithEraser(boolean bSmallEraser)
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.__setBasic(bSmallEraser);
	}
	
	
	
	public MyE2_TextFieldWithEraser(String cText,int iwidthPixel, int imaxInputSize, boolean bSmallEraser)
	{
		super();
		this.iWidthPixel = 		iwidthPixel;
		this.iMaxInputSize = 	imaxInputSize;
		this.__setBasic(bSmallEraser);
		
		this.setText(cText);
	}
	
	public void setText(String cText)
	{
		this.internalTextField.setText(cText);
	}
	
	public String getText()
	{
		return this.internalTextField.getText();
	}
	
	public void setMaximumLength(int iMaxInputSize)
	{
		this.internalTextField.setMaximumLength(iMaxInputSize);
	}
	
	public void setFont(Font oFont)
	{
		this.internalTextField.setFont(oFont);
	}
	
	
	public void setWidth(Extent  oWidth)
	{
		this.internalTextField.setWidth(oWidth);
	}
	
	public void setEnabled(boolean bEnabled)
	{
		this.internalTextField.setEnabled(bEnabled);
		this.buttonErase.setEnabled(bEnabled);
	}
	
	public void setBackground(Color oColor)
	{
		this.internalTextField.setBackground(oColor);
	}
	
	
	public void setHeight(Extent  oHeight)
	{
		this.internalTextField.setHeight(oHeight);
	}
	
	public Extent getHeight()
	{
		return this.internalTextField.getHeight();
	}

	public void setBorder(Border oBorder)
	{
		this.internalTextField.setBorder(oBorder);
	}
	
	public Border getBorder()
	{
		return this.internalTextField.getBorder();
	}
	
	public void setAlignment(Alignment oAlign)
	{
		this.internalTextField.setAlignment(oAlign);
	}
	
	public Alignment getAlignment()
	{
		return this.internalTextField.getAlignment();
	}
	
	public void setToolTipText(String cToolTip)
	{
		this.internalTextField.setToolTipText(cToolTip);
	}
	
	
	public void __setBasic(boolean SmallEraser)
	{
		this.bSmallEraser = SmallEraser;
		

		this.buttonErase.setFocusTraversalParticipant(true);
		
		this.setWidth(new Extent(this.iWidthPixel,Extent.PX));
		//this.setMaximumLength(10);
		this.setMaximumLength(iMaxInputSize);
		this.setFont(new E2_FontPlain());
		this.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.internalTextField.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		if (this.bSmallEraser)
		{
			this.buttonErase.__setImages(E2_ResourceIcon.get_RI("eraser_xxs.png"), true);
		}

		this.__rebuild();
		
	}
	
	
	public void __rebuild()
	{
		this.removeAll();
		
		this.setSize(this.vAddOns.size()+2);
		
		this.add(this.internalTextField, 	insetBetween_Elements);
		if (this.vAddOns.size()>0)
		{
			this.add(this.buttonErase, 		insetBetween_Elements);
		}
		else
		{
			this.add(this.buttonErase, 		E2_INSETS.I_0_0_0_0);
		}

		for (int i=0;i<this.vAddOns.size();i++)
		{
			if (i==this.vAddOns.size()-1)
			{
				this.add(this.vAddOns.get(i), E2_INSETS.I_0_0_0_0);
			}
			else
			{
				this.add(this.vAddOns.get(i), insetBetween_Elements);
			}
		}
	}
	
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{

		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		
		if (this.internalTextField.isEnabled())
		{
			this.internalTextField.setFocusTraversalParticipant(true);
		}
		else
		{
			this.internalTextField.setFocusTraversalParticipant(false);
		}
		this.internalTextField.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.internalTextField.isEnabled(),true,false));
		this.buttonErase.set_bEnabled_For_Edit(this.internalTextField.isEnabled());
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_TextFieldWithEraser oRueck = new MyE2_TextFieldWithEraser(this.bSmallEraser);
		
		oRueck.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oRueck));
		oRueck.internalTextField.setStyle(this.internalTextField.getStyle());
		oRueck.setFont(this.internalTextField.getFont());
		
		oRueck.set_iMaxInputSize(this.get_iMaxInputSize());
		oRueck.set_iWidthPixel(this.get_iWidthPixel());
		oRueck.setText(this.getText());
		oRueck.setWidth(this.internalTextField.getWidth());
		
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

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_TextFieldWithEraser.this.internalTextField.setText("");
		}
	}


	public void add_ZusatzComponent(Component  oComp)
	{
		this.vAddOns.add(oComp);
		this.__rebuild();
	}
	
	
	public void remove_ZusatzComponents()
	{
		this.vAddOns.removeAllElements();
		this.__rebuild();
	}
	
	
}
