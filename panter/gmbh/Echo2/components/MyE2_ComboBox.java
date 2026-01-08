package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.list.DefaultListModel;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Factorys.StyleFactory_ComboBox;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import echopointng.ComboBox;

public class MyE2_ComboBox extends ComboBox  implements 		MyE2IF__Component, 
																E2_IF_Copy,
																MyE2IF__CanGetStampInfo
{
	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	
 	private String[] 		cDefArray = null;
	
 	
 	
	public MyE2_ComboBox()
	{
		super();
        this.__setLookAndFeel();
	}
	
	
	public MyE2_ComboBox(String[] aDefArray, String cdefaultValue) throws myException
	{
		super();
        this.__setLookAndFeel();

		this.set_ContentArray(aDefArray);
        this.setText(cdefaultValue);
        
	}


	private void __setLookAndFeel()
	{
        this.setToggleIcon(E2_ResourceIcon.get_RI("popdownflat.png"));
        this.setTogglePressedIcon(E2_ResourceIcon.get_RI("popdownflat.png"));
        this.setToggleRolloverIcon(E2_ResourceIcon.get_RI("popdownflat.png"));

        this.getTextField().setStyle(new StyleFactory_TextField().get_Style(true,true,false));
        
		this.EXT().set_STYLE_FACTORY(new StyleFactory_ComboBox());
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		this.setAutoRecall(false);    // keine autovervollstaendigung
		this.setPopUpAlwaysOnTop(true);
		
	}

	
	public void set_ContentArray(String[] aDefArray) throws myException
	{
		this.cDefArray = aDefArray;
	    
	    if (aDefArray == null)
	        throw new myException("MyE2_ComboBox: set_ContentArray: error: null-array not allowed");

	    this.setListModel(new DefaultListModel(this.cDefArray));

	}
	
	
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		this.setEnabled( bEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
		this.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),true,!bInputIsOK));
		
		if (this.isEnabled())
		{
			if (!bInputIsOK)
			{
				this.getTextField().setBackground(Color.YELLOW);
			}
			else
			{
				this.getTextField().setBackground(new E2_ColorEditBackground());
			}
		}
		else
		{
			this.getTextField().setBackground(new E2_ColorGray(230)); 
		}
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.cDefArray == null)
			throw new myExceptionCopy("MyE2_ComboBox:get_Copy: Error: Value-Array not initialized !");
		
		MyE2_ComboBox oCombo = null;
		
		try
		{
			oCombo = new MyE2_ComboBox(this.cDefArray,this.getText());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_ComboBox:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oCombo.set_EXT((MyE2EXT__Component)this.oEXT.get_Copy(oCombo));
		oCombo.setStyle(this.getStyle());
		oCombo.setToggleIcon(this.getToggleIcon());
		oCombo.setTogglePressedIcon(this.getTogglePressedIcon());
		oCombo.setToggleRolloverIcon(this.getToggleRolloverIcon());

		
		return oCombo;
	}


	public String[] get_cDefArray()
	{
		return cDefArray;
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
