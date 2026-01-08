package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


public class E2_BasicContainerToShowInfos extends E2_BasicModuleContainer
{
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);


	public E2_BasicContainerToShowInfos(MyE2_String ctitle, Vector<String> vInfos, Extent owidth, Extent oheight, boolean bTranslate) throws myException
	{
		super();
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		this.__build(vInfos,bTranslate);

		this.CREATE_AND_SHOW_POPUPWINDOW(owidth, oheight, ctitle);

	}


	
	public E2_BasicContainerToShowInfos(MyE2_String ctitle, Vector<MyString> vInfos, Extent owidth, Extent oheight) throws myException
	{
		super();
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		this.__build(vInfos);

		this.CREATE_AND_SHOW_POPUPWINDOW(owidth, oheight, ctitle);

	}

	
	
	private void __build(Vector<String> vInfos, boolean bTranslate)
	{
		MyE2_Column	oCol = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		MyE2_ContainerEx oContainerEx = new MyE2_ContainerEx(oCol);
		
		for (int i=0;i<vInfos.size();i++)
		{
			String cHelp = vInfos.get(i);
			oCol.add(new MyE2_Label(new MyE2_String(cHelp,bTranslate),MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_1_1_1_1);
		}
		
		this.add(oContainerEx);
	}
	
	
	private void __build(Vector<MyString> vInfos)
	{
		MyE2_Column	oCol = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		MyE2_ContainerEx oContainerEx = new MyE2_ContainerEx(oCol);
		
		for (int i=0;i<vInfos.size();i++)
		{
			oCol.add(new MyE2_Label(vInfos.get(i),MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_1_1_1_1);
		}
		
		this.add(oContainerEx);
	}

	
	
//	private MutableStyle get_ownStyleWindowPane()
//	{
//		MutableStyle oStyle = new MutableStyle();
//		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent()); 
//		try
//		{
//			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
//		} 
//		catch (myException e)
//		{
//			e.printStackTrace();
//		}
//		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose.png"));
//		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1,1,1,1),new Insets(1,1,1,1)));
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(bibALL.get_FONT_SIZE(),Extent.PT));
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,4,2,1));
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBold());
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
//		return oStyle;
//	}
//	

	
	public void set_EXT(MyE2EXT__Component oeXT)
	{
		this.oEXT = oeXT;
	}

	public MyE2EXT__Component EXT()
	{
		return this.oEXT;
	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		this.setEnabled(bEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
	}



	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
}
