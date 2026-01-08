package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyE2_Button_TO_EditField_DirektEditAndSave extends 	MyE2_Button_TO_EditField implements  E2_IF_Copy 
{
    private VECTOR_UB_FIELDS  		vUnboundDataFields = new VECTOR_UB_FIELDS();
	
    private MyE2_String            	cTitleForPopup = 	null;
 

	private Vector<MyE2_String>    	vInfoTexts = 		new Vector<MyE2_String>();
	

	public MyE2_Button_TO_EditField_DirektEditAndSave() throws myException 
	{
		super();
	}

	public MyE2_Button_TO_EditField_DirektEditAndSave(ImageReference oImg,boolean bAutoDisabled) throws myException 
	{
		super(oImg, bAutoDisabled);
	}

	public MyE2_Button_TO_EditField_DirektEditAndSave(ImageReference oImg,	ImageReference oimgDisabled) throws myException 
	{
		super(oImg, oimgDisabled);
	}

	public MyE2_Button_TO_EditField_DirektEditAndSave(ImageReference oImg)	throws myException 
	{
		super(oImg);
	}

	public MyE2_Button_TO_EditField_DirektEditAndSave(MyString cText) throws myException 
	{
		super(cText);
	}

	public MyE2_Button_TO_EditField_DirektEditAndSave(Object cText,	ImageReference oImg, ImageReference oimgDisabled)	throws myException 
	{
		super(cText, oImg, oimgDisabled);
	}


	public MyE2_Button_TO_EditField_DirektEditAndSave(String cText,E2_MutableStyle oStyle) throws myException 
	{
		super(cText, oStyle);
	}

	
	public MyE2_Button_TO_EditField_DirektEditAndSave(String cText)	throws myException 
	{
		super(cText);
	}

	public VECTOR_UB_FIELDS get_vUnboundDataFields() 
	{
		return vUnboundDataFields;
	}

	
	public Vector<MyE2_String> get_vInfoTexts() 
	{
		return vInfoTexts;
	}

	public void set_cTitleForPopup(MyE2_String TitleForPopup) 
	{
		this.cTitleForPopup = TitleForPopup;
	}

	
	@Override
	public  void 				CreateAndShowPopupWindow(int iWidth, int iHeight) throws myException
	{
		E2_BasicModuleContainer  oContainer = this.get_BasicModuleContainer();

		MyE2_Grid oGrid1 = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		for (int i=0;i<this.vInfoTexts.size();i++)
		{
			oGrid1.add(new MyE2_Label(this.vInfoTexts.get(i)), E2_INSETS.I_0_2_0_2);
		}
		
		
		MyE2_Button oBtSave = this.get_ButtonToSave();
		MyE2_Button oBtCancel = this.get_ButtonToCancel();
		
		MyE2_Grid gridButtons = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		if (oBtSave!=null)
		{
			gridButtons.add(oBtSave, E2_INSETS.I_0_2_5_2);
		}
		
		if (oBtCancel!=null)
		{
			gridButtons.add(oBtCancel, E2_INSETS.I_0_2_5_2);
		}
		
		
		oContainer.add(oGrid1,  E2_INSETS.I_4_2_2_4);
		oContainer.add(this.get_GridWithComponents(),  E2_INSETS.I_4_2_2_4);
		oContainer.add(gridButtons,  E2_INSETS.I_4_2_2_2);
		
		if (this.check_StatusBeforePopup(oContainer, vInfoTexts, oBtSave, oBtCancel))
		{
			oContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iWidth), new Extent(iHeight), (this.cTitleForPopup!=null?this.cTitleForPopup:new MyE2_String("")));
		}
		
		//wenn es einen gefuellten Vector mit datafields gibt, dann bekommt der erste den focus
		if (vUnboundDataFields.size()>0 && vUnboundDataFields.get(0) instanceof Component)
		{
			ApplicationInstance.getActive().setFocusedComponent((Component)vUnboundDataFields.get(0));
		}
	}

	
	public abstract  MyE2_Grid   		       get_GridWithComponents()  	throws myException;
	public abstract  E2_BasicModuleContainer   get_BasicModuleContainer()   throws myException;
	public abstract  boolean                   check_StatusBeforePopup(E2_BasicModuleContainer  oContainer, Vector<MyE2_String> vInfoTexts, MyE2_Button oBtSave, MyE2_Button oBtCancel) 	throws myException;
	
}
