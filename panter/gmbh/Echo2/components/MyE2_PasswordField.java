package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.PasswordField;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_PasswordField extends PasswordField implements MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	
	private		int 		iWidthPixel = 20;
	private 	int 		iMaxInputSize = 10;
	
	
	
	public MyE2_PasswordField()
	{
		super();
		this.__setBasic();
	}
	
	public MyE2_PasswordField(String cText,int iwidthPixel, int imaxInputSize)
	{
		super();
		this.__setBasic();
		this.iWidthPixel = 		iwidthPixel;
		this.iMaxInputSize = 	imaxInputSize;
		
		this.setWidth(new Extent(this.iWidthPixel,Extent.PX));
		this.setText(cText);
		
	}
	
	public void __setBasic()
	{
		this.setFont(new E2_FontPlain());
		this.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));

	}
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled(),true,false));
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_PasswordField oRueck = new MyE2_PasswordField();
		
		oRueck.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oRueck));
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.set_iMaxInputSize(this.get_iMaxInputSize());
		oRueck.set_iWidthPixel(this.get_iWidthPixel());
		oRueck.setText(this.getText());
		oRueck.setWidth(this.getWidth());
		
		return oRueck;
		
	}
	
	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public int get_iMaxInputSize()								{		return iMaxInputSize;	}
	public void set_iMaxInputSize(int maxInputSize)			{		iMaxInputSize = maxInputSize;	}
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
