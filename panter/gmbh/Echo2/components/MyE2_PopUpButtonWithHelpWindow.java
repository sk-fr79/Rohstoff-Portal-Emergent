/*
 * Created on 16.10.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;




public abstract class MyE2_PopUpButtonWithHelpWindow extends MyE2_Button 
{
	
    private MyE2_Column 		oColInnen = 	new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));

    private Extent				extWidth = new Extent(400);
    private Extent				extHeight = new Extent(400);
    
    private Font				oFontInfo = new E2_FontPlain(-2);
    
    private Vector<MyString> 	vHelpText = new Vector<MyString>();
    
    private MyE2_String			cWindowTitle = new MyE2_String("Hilfe und Information ...");
    
    
    //2011-11-18: abstrakte klasse zum definieren einer eigenen E2_BasicModuleContainer-klasse (zum speichern der groesse)
    public abstract E2_BasicModuleContainer   createOwnBasicModuleContainer() throws myException;
    
    XX_ActionAgent	oActionAgent = new XX_ActionAgent()
    {

		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			// new ownModuleContainer();
			E2_BasicModuleContainer oHelpContainer = MyE2_PopUpButtonWithHelpWindow.this.createOwnBasicModuleContainer();
			
			oHelpContainer.set_bVisible_Row_For_Messages(true);
			MyE2_PopUpButtonWithHelpWindow.this.fillColumnInnen(MyE2_PopUpButtonWithHelpWindow.this.oColInnen);
			oHelpContainer.add(MyE2_PopUpButtonWithHelpWindow.this.oColInnen, E2_INSETS.I_5_5_5_5);
			MyE2_PopUpButtonWithHelpWindow.this.oColInnen.setBackground(new E2_ColorHelpBackground());
			
			oHelpContainer.get_oContentPaneAussen().setBackground(new E2_ColorHelpBackground());
			oHelpContainer.get_oContentPaneInnen().setBackground(new E2_ColorHelpBackground());

			oHelpContainer.CREATE_AND_SHOW_POPUPWINDOW(	MyE2_PopUpButtonWithHelpWindow.this.extWidth, 	
					MyE2_PopUpButtonWithHelpWindow.this.extHeight, 
					MyE2_PopUpButtonWithHelpWindow.this.cWindowTitle);
			
			
		}
    	
    };
    
    
    
    

    /**
     * 
     * @param vHelpStrings
     * @param ExtWidth
     * @param ExtHeight
     * @param oFontForHelpText
     */
    public MyE2_PopUpButtonWithHelpWindow(Vector<MyString> vHelpStrings, Extent ExtWidth, Extent ExtHeight, Font oFontForHelpText)
    {
    	super(E2_ResourceIcon.get_RI("help.png"),true);
        this.add_oActionAgent(this.oActionAgent);
        this.vHelpText.addAll(vHelpStrings);
        if (ExtWidth != null) this.extWidth = ExtWidth;
        if (ExtHeight != null) this.extHeight = ExtHeight;
        if (oFontForHelpText != null) this.oFontInfo = oFontForHelpText;
    }


    
    public void fillColumnInnen(MyE2_Column ColInnen)
    {
    	ColInnen.removeAll();
    	for (int i=0;i<this.vHelpText.size();i++)
    	{
    		MyE2_Label oLab = new MyE2_Label(this.vHelpText.get(i));
    		oLab.setFont(this.oFontInfo);
    		ColInnen.add(oLab);
    	}
    }
 	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return null;
	}



	public Font get_oFontInfo()
	{
		return oFontInfo;
	}



	public Vector<MyString> get_vHelpText()
	{
		return vHelpText;
	}



	public MyE2_String get_cWindowTitle()
	{
		return cWindowTitle;
	}



	public void set_oFontInfo(Font oFontInfo)
	{
		this.oFontInfo = oFontInfo;
	}



	public void set_vHelpText(Vector<MyString> vHelpText)
	{
		this.vHelpText = vHelpText;
	}



	public void set_cWindowTitle(MyE2_String cWindowTitle)
	{
		this.cWindowTitle = cWindowTitle;
	}

	
	
	

}
