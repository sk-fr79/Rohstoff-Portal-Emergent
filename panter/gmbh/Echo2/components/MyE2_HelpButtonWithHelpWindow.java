/*
 * To change the template for this generated file go to
 *  Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;




public abstract class MyE2_HelpButtonWithHelpWindow extends MyE2_Button 
{
	
    private MyE2_Column 		oColInnen = 	new MyE2_Column(new Style_Column_Normal(0,new Insets(0)));

    private Extent				extWidth = new Extent(400);
    private Extent				extHeight = new Extent(400);
    
   
    private MyE2_String			cWindowTitle = new MyE2_String("Hilfe und Information ...");
    
    
    //2011-11-18: abstrakte klasse zum definieren einer eigenen E2_BasicModuleContainer-klasse (zum speichern der groesse)
    public abstract E2_BasicModuleContainer   	createOwnBasicModuleContainer() throws myException;
    public abstract void          				fillInternalColumn(MyE2_Column oColumn) throws myException;
    
    
    
    XX_ActionAgent	oActionAgent = new XX_ActionAgent()
    {

		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			// new ownModuleContainer();
			E2_BasicModuleContainer oHelpContainer = MyE2_HelpButtonWithHelpWindow.this.createOwnBasicModuleContainer();
			
			oHelpContainer.set_bVisible_Row_For_Messages(true);
			MyE2_HelpButtonWithHelpWindow.this.fillInternalColumn(MyE2_HelpButtonWithHelpWindow.this.oColInnen);
			oHelpContainer.add(MyE2_HelpButtonWithHelpWindow.this.oColInnen, E2_INSETS.I_5_5_5_5);
			MyE2_HelpButtonWithHelpWindow.this.oColInnen.setBackground(new E2_ColorHelpBackground());
			
			oHelpContainer.get_oContentPaneAussen().setBackground(new E2_ColorHelpBackground());
			oHelpContainer.get_oContentPaneInnen().setBackground(new E2_ColorHelpBackground());

			oHelpContainer.CREATE_AND_SHOW_POPUPWINDOW(	MyE2_HelpButtonWithHelpWindow.this.extWidth, 	
					MyE2_HelpButtonWithHelpWindow.this.extHeight, 
					MyE2_HelpButtonWithHelpWindow.this.cWindowTitle);
			
			
		}
    	
    };
    
    
    /**
     * hilfsmethode, fuellt eine liste von strings als labels in einen container
     * @param oCol
     * @param vTextline
     * @param bTranslate
     * @param oFont
     */
    public static void add_StringsToColumn(MyE2_Column oCol, Vector<String>  vTextline, boolean bTranslate, Font oFont)
    {
    	for (int i=0;i<vTextline.size();i++)
    	{
    		MyE2_Label oLab = new MyE2_Label(new MyE2_String(vTextline.get(i),bTranslate));
    		oLab.setFont(oFont);
    		oCol.add(oLab);
    	}

    }
    
    /**
     * hilfsmethode, fuellt eine liste von strings als labels in einen container
     * @param oCol
     * @param vTextline
     * @param bTranslate
     * @param oFont
     */
    public static void add_StringsToColumn(Vector<MyString>  vTextline, MyE2_Column oCol , boolean bTranslate, Font oFont)
    {
    	for (int i=0;i<vTextline.size();i++)
    	{
    		MyE2_Label oLab = new MyE2_Label(vTextline.get(i));
    		oLab.setFont(oFont);
    		oCol.add(oLab);
    	}

    }
  
    
    /**
     * 
     * @param vHelpStrings
     * @param ExtWidth
     * @param ExtHeight
     * @param oFontForHelpText
     */
    public MyE2_HelpButtonWithHelpWindow()
    {
    	super(E2_ResourceIcon.get_RI("help.png"),true);
        this.add_oActionAgent(this.oActionAgent);
    }


    /**
     * 
     * @param vHelpStrings
     * @param ExtWidth
     * @param ExtHeight
     * @param oFontForHelpText
     */
    public MyE2_HelpButtonWithHelpWindow(Extent ExtWidth, Extent ExtHeight)
    {
    	super(E2_ResourceIcon.get_RI("help.png"),true);
        this.add_oActionAgent(this.oActionAgent);
        if (ExtWidth != null) this.extWidth = ExtWidth;
        if (ExtHeight != null) this.extHeight = ExtHeight;
    }



    
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return null;
	}




	public MyE2_String get_cWindowTitle()
	{
		return cWindowTitle;
	}




	public void set_cWindowTitle(MyE2_String cWindowTitle)
	{
		this.cWindowTitle = cWindowTitle;
	}
	public Extent get_extWidth() {
		return extWidth;
	}
	public void set_extWidth(Extent ext_Width) {
		this.extWidth = ext_Width;
	}
	public Extent get_extHeight() {
		return extHeight;
	}
	public void set_extHeight(Extent ext_Height) {
		this.extHeight = ext_Height;
	}

	
	
	

}
