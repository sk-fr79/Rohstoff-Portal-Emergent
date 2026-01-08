package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import echopointng.KeyStrokeListener;


/*
 * button with key ist ein button, der einen keycode uebermittelt bekommt und diesen als
 * "Klick" ausfuehrt
 */
public class MyE2_ButtonWithKey extends MyE2_Row implements MyE2IF__Component, E2_IF_Copy
{
	
	private MyE2_Button 			oButton = null;

	
	/*
	 * keyaction-objekt falls ein keystroke-listener aktiv ist 
	 */
	private KeyStrokeListener		oKeyListener = new KeyStrokeListener();

	
	private int 					iKeyCode = -1;
			

			
	/*
	 * innerer action-listener, der benutzt wird, um die ActionAgents, 
	 * die dem button zugewiesen wurden, abzuarbeiten
	 */
	private ActionListener			oInnerActionListenerForKey = 
		new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					MyE2_ButtonWithKey oThis = MyE2_ButtonWithKey.this;
					if (oThis.oButton.isEnabled())
						oThis.oButton.doAction();
				}
			};

			
			
			
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.oButton.set_bEnabled_For_Edit(enabled);
	}

	
	public MyE2_ButtonWithKey(MyE2_Button oButton,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = oButton;
		this.add(this.oButton);
	}

	
	
	public MyE2_ButtonWithKey(int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button();
		this.add(this.oButton);
	}

	
	/**
	 * 
	 * @param oImg
	 * @param bAutoDisabled
	 * versucht automatisch eine graue version anhand
	 * der E2_ResourcIcon zu finden und laedt diese zu
	 * 
	 */
	public MyE2_ButtonWithKey(ImageReference oImg, boolean bAutoDisabled,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button(oImg,bAutoDisabled);
		this.add(this.oButton);
	}

	
	
	public MyE2_ButtonWithKey(ImageReference oImg, ImageReference oimgDisabled,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button(oImg,oimgDisabled);
		this.add(this.oButton);
	}


	public MyE2_ButtonWithKey(ImageReference oImg,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button(oImg,oImg);
		this.add(this.oButton);
	}

	
	public MyE2_ButtonWithKey(Object cText,ImageReference  oImg, ImageReference oimgDisabled,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button(cText,oImg,oimgDisabled);
		this.add(this.oButton);
	}

	
	public MyE2_ButtonWithKey(String cText,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button(cText);
		this.add(this.oButton);
	}

	public MyE2_ButtonWithKey(MyString cText,int KeyCode)
	{
		super(MyE2_Row.STYLE_NO_BORDER());
		this.activate_KeyCode(KeyCode);
		this.oButton = new MyE2_Button(cText);
		this.add(this.oButton);
	}

	
    public void activate_KeyCode(int KeyCode)
    {
        if(this.iKeyCode>=0)
        {
            this.oKeyListener.removeKeyCombination(this.iKeyCode);
            this.oKeyListener.removeActionListener(this.oInnerActionListenerForKey);
            this.remove(this.oKeyListener);
        }
        this.iKeyCode = KeyCode;
        if (KeyCode>0)
        {
            this.oKeyListener.addActionListener(this.oInnerActionListenerForKey);
            this.oKeyListener.addKeyCombination(KeyCode);
            this.add(this.oKeyListener);
        }
    }


	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_ButtonWithKey oButtonWK = new MyE2_ButtonWithKey((MyE2_Button)this.oButton.get_Copy(null),this.iKeyCode);
		return oButtonWK;
	}
	
	
	public void add_GlobalValidator(XX_ActionValidator oValid)
	{
		this.oButton.add_GlobalValidator(oValid);
	}

	public void add_IDValidator(XX_ActionValidator oValid)
	{
		this.oButton.add_IDValidator(oValid);
	}
	
	public void add_GlobalValidator(Vector<XX_ActionValidator> vValid)
	{
		this.oButton.add_GlobalValidator(vValid);
	}

	public void add_IDValidator(Vector<XX_ActionValidator> vValid)
	{
		this.oButton.add_IDValidator(vValid);
	}


	/**
	 * prueft alle vorhandenen globalen validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public MyE2_MessageVector valid_GlobalValidation() throws myException
	{
		return this.oButton.valid_GlobalValidation();
	}
	

	/**
	 * prueft alle vorhandenen ID-basierten validatoren
	 * wenn alles ok, dann wird ein leerer vector zurueckgegeben
	 * @return
	 */
	public MyE2_MessageVector valid_IDValidation(Vector<String> vID_Unformated) throws myException
	{
		return this.oButton.valid_IDValidation(vID_Unformated);
	}

	
	
	public Object get_oText()									{		return this.oButton.get_oText();	}


	public ImageReference get_oImgDisabled()					{		return this.oButton.get_oImgDisabled();	}
	public ImageReference get_oImgEnabled()					{		return this.oButton.get_oImgEnabled();	}


	public void set_EXT(MyE2EXT__Component oext)				{		this.oButton.set_EXT(oext);	}
	public MyE2EXT__Component EXT()							{		return this.oButton.EXT();	}

	public void add_oActionAgent(XX_ActionAgent actionAgent)
	{
		this.oButton.add_oActionAgent(actionAgent);
	}

	
	
	public void remove_oActionAgent(XX_ActionAgent actionAgent)
	{
		this.oButton.remove_oActionAgent(actionAgent);
	}

	
	public void remove_AllActionAgents()
	{
		this.oButton.remove_AllActionAgents();
	}

	
	
	public Vector<XX_ActionAgent> get_vActionAgents()
	{
		return this.oButton.get_vActionAgents();
	}


	public Vector<ActionListener> get_vExternalActionListeners()
	{
		return this.oButton.get_vExternalActionListeners();
	}


	public void addActionListener(ActionListener oActionListener)
	{
		this.oButton.addActionListener(oActionListener);
	}
	
	public void removeActionListener(ActionListener oActionListener)
	{
		this.oButton.removeActionListener(oActionListener);
	}


	public void show_InputStatus(boolean bInputIsOK)
	{
	}


	public Vector<XX_ActionValidator> get_vGlobalValidators()	{		return this.oButton.get_vGlobalValidators();	}
	public Vector<XX_ActionValidator> get_vIDValidators()		{		return this.oButton.get_vIDValidators();	}
	

	


	public MyE2_Button get_oButton()
	{
		return oButton;
	}
	
	
	public void  set_oButton(MyE2_Button Button)
	{
		this.oButton = Button;
	}
	
	
	public void setToolTipText(String cText)
	{
		this.oButton.setToolTipText(cText);
	}

}
