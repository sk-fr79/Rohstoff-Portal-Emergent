package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * SPECIAL button for lists, shows the content of the db in the list as button
 * @author martin
 *
 */
public class MyE2_DB_Button extends MyE2_Button implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private String 					cActualRowID = null;
	
	private MyString  				cSetTextWhenEmpty = null;
	
	private boolean     			bNoTextOnButton = false;
	
	

	public MyE2_DB_Button(SQLField osqlField) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
		this.setStyle(MyE2_Button.Style_Button_DB());
	}

	public MyE2_DB_Button(SQLField osqlField, MutableStyle oStyle) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
		this.setStyle(oStyle);
	}


	public boolean get_bNoTextOnButton() 
	{
		return bNoTextOnButton;
	}

	public void set_bNoTextOnButton(boolean bNoTextOnButton) 
	{
		this.bNoTextOnButton = bNoTextOnButton;
	}

	/*
	 * diese funktion wird hier auser gefecht gesetzt 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		if (this.EXT().get_oComponentMAP() != null)
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
				this.setEnabled(false);
	}

	
	public void prepare_ContentForNew(boolean bSetDefault)
	{
		this.setText("");
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		this.EXT_DB().set_cLASTActualMaskValue("");
	}

	public String get_cActualMaskValue() throws myException
	{
		return null;
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		if (!this.bNoTextOnButton)
		{
			this.set_Text(cText);
		}
		this.EXT_DB().set_cLASTActualMaskValue(cText);

		//jetzt den (falls vorhanden) platzhalter einfuegen
		if (bibALL.isEmpty(cText) && this.cSetTextWhenEmpty!=null)
		{
			this.set_Text(this.cSetTextWhenEmpty);
		}

		//falls kein ersatztext und das feld ist leer, dann evtl borders loeschen, damit nicht ein haesslicher strich in der landschaft steht
		if (S.isEmpty(this.getText()))
		{
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
		}
		
	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Button:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");
								
		this.set_cActualMaskValue(cText);
		this.cActualRowID = oResultMAP.get_cUNFormatedROW_ID();
		this.EXT_DB().set_cLASTActualDBValueFormated(this.cActualRowID);
		
	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return false;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
		this.oEXTDB = oEXT_DB;
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_Button oButtCopy = null;
		
		try
		{
			oButtCopy = new MyE2_DB_Button(this.oEXTDB.get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Button:get_Copy:Copy-Error!");
		}
		
		oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
		if (this.getIcon() != null)
			oButtCopy.setIcon(this.getIcon());
		
		if (this.get_oText() != null)
			oButtCopy.set_Text(this.get_oText());
		
		oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
		
		oButtCopy.setStyle(this.getStyle());
		oButtCopy.setInsets(this.getInsets());
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oButtCopy.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oButtCopy.addActionListener((ActionListener)vActionListeners.get(i));

		
		for (int i=0;i<this.get_vGlobalValidators().size();i++)
			oButtCopy.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++)
			oButtCopy.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));

		oButtCopy.setFont(this.getFont());
		oButtCopy.setAlignment(this.getAlignment());
		
		oButtCopy.set_cSetTextWhenEmpty(this.cSetTextWhenEmpty);
		
		oButtCopy.set_bNoTextOnButton(this.get_bNoTextOnButton());
		
		return oButtCopy;
	}

	public String get_cActualRowID()
	{
		return cActualRowID;
	}

	public void set_cActualRowID(String actualRowID)
	{
		this.cActualRowID = actualRowID;
	}

	public MyString get_cSetTextWhenEmpty() 
	{
		return cSetTextWhenEmpty;
	}

	public void set_cSetTextWhenEmpty(MyString setTextWhenEmpty) 
	{
		cSetTextWhenEmpty = setTextWhenEmpty;
	}



	
	

}
