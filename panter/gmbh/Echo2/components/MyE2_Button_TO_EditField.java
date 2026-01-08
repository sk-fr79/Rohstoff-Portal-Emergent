package panter.gmbh.Echo2.components;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyE2_Button_TO_EditField extends MyE2_Button  implements E2_IF_Copy
{
    private int iPopupWidth = 400;
    private int iPopupHeight = 400;
	
	public MyE2_Button_TO_EditField() throws myException
	{
		super();
		this.__init();
	}

	public MyE2_Button_TO_EditField(ImageReference oImg, boolean bAutoDisabled)  throws myException
	{
		super(oImg, bAutoDisabled);
		this.__init();
	}

	public MyE2_Button_TO_EditField(ImageReference oImg,ImageReference oimgDisabled)  throws myException
	{
		super(oImg, oimgDisabled);
		this.__init();
	}

	public MyE2_Button_TO_EditField(ImageReference oImg)  throws myException
	{
		super(oImg);
		this.__init();
	}

	public MyE2_Button_TO_EditField(MyString cText)  throws myException
	{
		super(cText);
		this.__init();
	}

	public MyE2_Button_TO_EditField(Object cText, ImageReference oImg,	ImageReference oimgDisabled)  throws myException
	{
		super(cText, oImg, oimgDisabled);
		this.__init();
	}

	public MyE2_Button_TO_EditField(String cText, E2_MutableStyle oStyle)  throws myException
	{
		super(cText, oStyle);
		this.__init();
	}

	public MyE2_Button_TO_EditField(String cText)  throws myException
	{
		super(cText);
		this.__init();
	}

	
	private void __init() throws myException
	{
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	public void set_PopupWidthAndHeight(int iWidth, int iHeight)
	{
		this.iPopupWidth = 	iWidth;
		this.iPopupHeight = iHeight;
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_Button_TO_EditField.this.CreateAndShowPopupWindow(MyE2_Button_TO_EditField.this.iPopupWidth,MyE2_Button_TO_EditField.this.iPopupHeight);
		}
	}
	
	
	
	public abstract void CreateAndShowPopupWindow(int iWidth, int iHeight) throws myException;
	public abstract  MyE2_Button        get_ButtonToSave() throws myException;
	public abstract  MyE2_Button        get_ButtonToCancel() throws myException;

	
}
