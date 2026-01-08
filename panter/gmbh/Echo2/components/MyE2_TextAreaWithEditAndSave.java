package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_TextAreaWithEditAndSave extends MyE2_Grid
{
	public static int iButtonWidth = 14;
	
	private MyE2_TextArea oTextArea = null;
	
	private MyE2_Button    oButtonEdit = 	new MyE2_Button(E2_ResourceIcon.get_RI("edit_mini2.png"),true);
	private MyE2_Button    oButtonSave = 	new MyE2_Button(E2_ResourceIcon.get_RI("save_mini.png"),true);
	private MyE2_Button    oButtonCancel = 	new MyE2_Button(E2_ResourceIcon.get_RI("cancel_mini.png"),true);
	
	
	public MyE2_TextAreaWithEditAndSave(String cText,int iwidthPixel, int imaxInputSize, int irows) throws myException
	{
		super();
		
		this.oTextArea = new MyE2_TextArea(cText, iwidthPixel-2*MyE2_TextAreaWithEditAndSave.iButtonWidth, imaxInputSize, irows);
	
		this.oButtonEdit.setToolTipText(new MyE2_String("Feldbeschreibung des Feldes bearbeiten").CTrans());
		this.oButtonSave.setToolTipText(new MyE2_String("Feldbeschreibung des Feldes speichern").CTrans());
		this.oButtonCancel.setToolTipText(new MyE2_String("Bearbeitung abbrechen").CTrans());
	
		
		int[] iBreite = new int[2];
		iBreite[0]= iwidthPixel-2*MyE2_TextAreaWithEditAndSave.iButtonWidth;
		iBreite[1]= 2*MyE2_TextAreaWithEditAndSave.iButtonWidth;
		
		this.setWidth(new Extent(iwidthPixel));
		
		
		MyE2_Grid oGrid4Buttons = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid4Buttons.setColumnWidth(0, new Extent(MyE2_TextAreaWithEditAndSave.iButtonWidth));
		oGrid4Buttons.setColumnWidth(1, new Extent(MyE2_TextAreaWithEditAndSave.iButtonWidth));
		
		oGrid4Buttons.add(this.oButtonEdit, E2_INSETS.I_0_0_0_0);
		oGrid4Buttons.add(new MyE2_Label(""), E2_INSETS.I_0_0_0_0);
		oGrid4Buttons.add(this.oButtonSave, E2_INSETS.I_0_0_0_0);
		oGrid4Buttons.add(this.oButtonCancel, E2_INSETS.I_0_0_0_0);

		this.add(this.oTextArea,E2_INSETS.I_0_0_0_0);
		this.add(oGrid4Buttons,E2_INSETS.I_0_0_0_0);
		
		this.setStatusWait4Edit();
	}
	
	
	
	public void setStatusWait4Edit() throws myException
	{
		this.oTextArea.set_bEnabled_For_Edit(false);

		this.oButtonEdit.set_bEnabled_For_Edit(true);
		this.oButtonSave.set_bEnabled_For_Edit(false);
		this.oButtonCancel.set_bEnabled_For_Edit(false);
	}

	public void setStatusEdit() throws myException
	{
		this.oTextArea.set_bEnabled_For_Edit(true);
		
		this.oButtonEdit.set_bEnabled_For_Edit(false);
		this.oButtonSave.set_bEnabled_For_Edit(true);
		this.oButtonCancel.set_bEnabled_For_Edit(true);
	}

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setStatusWait4Edit();
		this.oButtonEdit.set_bEnabled_For_Edit(false);
	}


	public void setText(String cText)
	{
		this.oTextArea.setText(cText);
	}
	
	public String getText()
	{
		return this.oTextArea.getText();
	}



	public MyE2_TextArea get_oTextArea()
	{
		return oTextArea;
	}



	public MyE2_Button get_oButtonEdit()
	{
		return oButtonEdit;
	}



	public MyE2_Button get_oButtonSave()
	{
		return oButtonSave;
	}



	public MyE2_Button get_oButtonCancel()
	{
		return oButtonCancel;
	}
	
	
	


}
