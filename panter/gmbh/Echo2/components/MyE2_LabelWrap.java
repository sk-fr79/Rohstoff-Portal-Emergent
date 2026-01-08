package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.staticStyles.Style_Label_Basic;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_LabelWrap extends Label  implements MyE2IF__Component, E2_IF_Copy, MyE2IF__CanGetStampInfo
{

	private MyE2EXT__Component oEXT = new MyE2EXT__Component(this);
	private Object 				oTextObject = null;
	
	/*
	 * falls MyE2_Label  in multicolum/multirow-elementen eingeblendet werden, werden u.U. leeranzeign
	 * mit hoehe 0 angezeigt. dafuer kann ein ersatz eingegeben werden
	 */
	private String 				cErsatzFuerLeeranzeige = null;

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}

	public MyE2_LabelWrap()
	{
		super();
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(true);
	}
	public MyE2_LabelWrap(ImageReference arg0)
	{
		super(arg0);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(true);
	}
	public MyE2_LabelWrap(Object cText, ImageReference arg1)
	{
		super();
		this.set_Text(cText);
		this.setIcon(arg1);
		this.setStyle(new Style_Label_Basic());
		this.setLineWrap(true);
	}
	public MyE2_LabelWrap(Object cText)
	{
		super();
		this.set_Text(cText);
		this.setLineWrap(true);
	}


	public MyE2_LabelWrap(MutableStyle oStyle)
	{
		super();
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(true);
	}
	public MyE2_LabelWrap(ImageReference arg0,MutableStyle oStyle)
	{
		super(arg0);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(true);
	}
	public MyE2_LabelWrap(Object cText, ImageReference arg1,MutableStyle oStyle)
	{
		super();
		this.set_Text(cText);
		this.setIcon(arg1);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(true);
	}
	public MyE2_LabelWrap(Object cText,MutableStyle oStyle)
	{
		super();
		this.set_Text(cText);
		if (oStyle != null) this.setStyle(oStyle);
		this.setLineWrap(true);
	}


	public void set_Text(Object cText)
	{
		
		
		this.oTextObject = cText;
		
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

	public void set_oTextObject(Object textObject)
	{
		oTextObject = textObject;
	}

	
	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}



	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_LabelWrap oLabCopy = new MyE2_LabelWrap();
		oLabCopy.setFont(this.getFont());
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		if (this.getIcon() != null)
			oLabCopy.setIcon(this.getIcon());
		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.cErsatzFuerLeeranzeige);
		
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

	
	public MyE2_LabelWrap _setStyle(MutableStyle style) {
		this.setStyle(style);
		return this;
	}
	

}
