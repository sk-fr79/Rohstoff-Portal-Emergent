/*
 * Created on 16.10.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import echopointng.PopUp;




public class MyE2_PopUpHelp extends PopUp  implements MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
 
    // grid als Rahmen
    private MyE2_Column 		oColAussen = 		new MyE2_Column(new Style_Column_Normal(1,new Insets(3,3,3,3)));
    private MyE2_Column 		oColInnen = 		new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));
    private E2_ResourceIcon 	oIconAktiv= 		E2_ResourceIcon.get_RI("help.png");
    private E2_ResourceIcon 	oIconInactiv = 		E2_ResourceIcon.get_RI("leer.png");

     
    

    /**
     * @param vHelpStrings = Vector aus MyString
     */
    public MyE2_PopUpHelp(Vector<MyString> vHelpStrings)
    {
        
        this.setToggleIcon(this.oIconAktiv);
        this.setTogglePressedIcon(this.oIconAktiv);
        this.setToggleRolloverIcon(this.oIconAktiv);
        
        this.setPopUp(this.oColAussen);
        this.oColAussen.add(this.oColInnen);
        
        this.setPopUpAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
        
        this.oColAussen.setStyle(MyE2_PopUpHelp.DEFAULT_STYLE_OUTER_COLUMN);
        this.oColInnen.setStyle(MyE2_PopUpHelp.DEFAULT_STYLE_INNER_COLUMN);
        
        this.setStyle(MyE2_PopUpHelp.DEFAULT_STYLE_POPUP);

        this.fillPopUp(vHelpStrings);
        
        this.setPopUpOnRollover(false);
        this.setPopUpAlwaysOnTop(true);

    }


    
    private void fillPopUp(Vector<MyString> vHelpStrings)
    {
    	for (int i=0;i<vHelpStrings.size();i++)
    	{
   			this.oColInnen.add(new MyE2_Label(vHelpStrings.get(i)));
    	}
    }
    
    
    

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}


	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException
	{
		boolean bEnabled = bbEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		if (bEnabled)
		{
	        this.setToggleIcon(this.oIconAktiv);
	        this.setTogglePressedIcon(this.oIconAktiv);
	        this.setToggleRolloverIcon(this.oIconAktiv);
		}
		else
		{
	        this.setToggleIcon(this.oIconInactiv);
	        this.setTogglePressedIcon(this.oIconInactiv);
	        this.setToggleRolloverIcon(this.oIconInactiv);
		}
		
		this.setEnabled(bEnabled);
		this.oColAussen.setEnabled(bEnabled);
		this.oColInnen.setEnabled(bEnabled);
	}



	public E2_ResourceIcon get_oIconAktiv()						{		return oIconAktiv;	}
	public void set_oIconAktiv(E2_ResourceIcon iconAktiv)		{		oIconAktiv = iconAktiv; }
	public E2_ResourceIcon get_oIconInactiv()					{		return oIconInactiv;	}
	public void set_oIconInactiv(E2_ResourceIcon iconInactiv)	{		oIconInactiv = iconInactiv;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return null;
	}

	

	/**
	 * This DEFAULT_STYLE is applied to the popup to give it a series of borders and background colors
	 */
	public static final Style DEFAULT_STYLE_POPUP;
	static {
		MutableStyle style = new MutableStyle();
		
		style.setProperty(PROPERTY_POPUP_INSETS,new Insets(0));
		style.setProperty(PROPERTY_POPUP_OUTSETS,new Insets(0));
		style.setProperty(PopUp.PROPERTY_POPUP_ON_ROLLOVER, new Boolean(false));

		style.setProperty(PROPERTY_INSETS,new Insets(0));
		style.setProperty(PROPERTY_BACKGROUND,new E2_ColorBase());
		style.setProperty(PROPERTY_ROLLOVER_BORDER,new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));

		DEFAULT_STYLE_POPUP = style;
	}	


	
	public static final Style DEFAULT_STYLE_OUTER_COLUMN;
	static {
		MutableStyle style = new MutableStyle();
		
		style.setProperty(Column.PROPERTY_BORDER,new Border(1, Color.BLACK, Border.STYLE_SOLID));
		style.setProperty( Column.PROPERTY_BACKGROUND, new E2_ColorBase(20)); 
		style.setProperty( Column.PROPERTY_INSETS, new Insets(0)); 
		DEFAULT_STYLE_OUTER_COLUMN = style;
	}	

	
	public static final Style DEFAULT_STYLE_INNER_COLUMN;
	static {
		MutableStyle style = new MutableStyle();
	
		style.setProperty(Column.PROPERTY_BORDER,new Border(0, Color.BLACK, Border.STYLE_NONE));
		style.setProperty( Column.PROPERTY_BACKGROUND, new E2_ColorHelpBackground()); 
		style.setProperty( Column.PROPERTY_INSETS, new Insets(2)); 
		
		DEFAULT_STYLE_INNER_COLUMN = style;
	}	


	
	public MyE2_Column get_oColInnen()
	{
		return oColInnen;
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
