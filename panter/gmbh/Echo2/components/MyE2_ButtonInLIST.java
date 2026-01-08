package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * Fuer das einfuegen in die E2_NavigationList.
 * Unterschied zum normalen button ist, er wird nur disabled
 * wenn der validierer dies sagt. Die sonst ueblich listendarstellung
 * wuerden sonst die buttons ausschalten
 *
 */
public class MyE2_ButtonInLIST extends MyE2_Button
{

	public MyE2_ButtonInLIST()
	{
		super();
	}

	public MyE2_ButtonInLIST(ImageReference oImg, boolean bAutoDisabled)
	{
		super(oImg, bAutoDisabled);
	}

	public MyE2_ButtonInLIST(ImageReference oImg, ImageReference oimgDisabled)
	{
		super(oImg, oimgDisabled);
	}

	public MyE2_ButtonInLIST(ImageReference oImg)
	{
		super(oImg);
	}

	
	public MyE2_ButtonInLIST(ImageReference oImg, MyE2_String cToolTips)
	{
		super(oImg);
		this.setToolTipText(cToolTips.CTrans());
	}

	
	public MyE2_ButtonInLIST(MyString cText)
	{
		super(cText);
	}

	public MyE2_ButtonInLIST(Object cText, ImageReference oImg, ImageReference oimgDisabled)
	{
		super(cText, oImg, oimgDisabled);
	}

	public MyE2_ButtonInLIST(String cText)
	{
		super(cText);
	}


	public MyE2_ButtonInLIST(String cText, E2_MutableStyle oStyle)
	{
		super(cText,oStyle);
	}

	
	public MyE2_ButtonInLIST(MyE2_String cText, E2_MutableStyle oStyle)
	{
		super(cText,oStyle);
	}

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_ButtonInLIST oButton = new MyE2_ButtonInLIST();

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		if (this.get_oText()!=null) oButton.set_Text(this.get_oText());
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		try
		{
			oButton.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
		oButton.setStyle(this.getStyle());
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oButton.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oButton.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oButton.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oButton.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));
		
		//aenderung 20101020: tooltiptext wird nicht mitkopiert
		if (this.getToolTipText() != null)
		{
			oButton.setToolTipText(this.getToolTipText());
		}
		
		
		
		return oButton;
	}
	
	/*
	 * enabled wird nur nach der vailiderung entschieden, normalerweise ist der button immer enabled
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		boolean enabled = this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;
		
		if (this.EXT().get_oComponentMAP() != null && this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP() != null)
		{
			String cROWID = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			enabled = enabled && (this.valid_IDValidation(bibALL.get_Vector(cROWID)).size()==0);
		}
		
		this.setEnabled(enabled);
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
		{
			if (enabled)
				this.setIcon(this.get_oImgEnabled());
			else
				this.setIcon(this.get_oImgDisabled());
		}
			
	}

	
	
	/*
	 * hier wird die sentenabled-methode ueberschrieben, da sonst bei direkten aufrufen
	 * die buttons nicht grau werden
	 * (non-Javadoc)
	 * @see nextapp.echo2.app.Component#setEnabled(boolean)
	 */
	public void setEnabled(boolean bEnabled) {
		super.setEnabled(bEnabled);
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
		{
			if (bEnabled)
				this.setIcon(this.get_oImgEnabled());
			else
				this.setIcon(this.get_oImgDisabled());
		}
	}

	
	
}
