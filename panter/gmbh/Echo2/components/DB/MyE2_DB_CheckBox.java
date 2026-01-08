package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MyE2_DB_CheckBox extends MyE2_CheckBox  implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;

	
	public MyE2_DB_CheckBox(SQLField osqlField) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	
	public MyE2_DB_CheckBox(SQLField osqlField, boolean bDisableFromBasic) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.EXT().set_bDisabledFromBasic(bDisableFromBasic);
	}


	public MyE2_DB_CheckBox(SQLField osqlField, MyE2_String cTooltips) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.setToolTipText(cTooltips.CTrans());
	}

	
	public MyE2_DB_CheckBox(SQLField osqlField,MyE2_String cText, MyE2_String cTooltips) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		if (cTooltips!=null)
		{
			this.setToolTipText(cTooltips.CTrans());
		}
		this.__setText(cText);
		
	}



//	public MyE2_DB_CheckBox(SQLField osqlField,MyE2_String cText, MyE2_String cTooltips, Alignment oTextPosition) throws myException
//	{
//		super();
//		
//		if (osqlField == null)
//			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
//
//		this.oEXTDB.set_bGivesBackValueToDB(true);
//		this.oEXTDB.set_oSQLField(osqlField);
//		if (cTooltips!=null)
//		{
//			this.setToolTipText(cTooltips.CTrans());
//		}
//		this.__setText(cText);
//		
//		this.setTextPosition(oTextPosition);
//
//	}


	
	
	public MyE2_DB_CheckBox(SQLField osqlField, boolean bDisableFromBasic, MyE2_String cTooltips) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.EXT().set_bDisabledFromBasic(bDisableFromBasic);
		this.setToolTipText(cTooltips.CTrans());
	}


	
	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		String cText = "";
		if (!bSetDefault)
		{
			this.setSelected(false);
			return;
		}
		
		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();
		
		if (cText.equals("") || cText.equals("N"))
		{
			this.setSelected(false);
		}
		else if (cText.equals("Y"))
		{
			this.setSelected(true);
		}
		else
			throw new myException("MyE2_DB_CheckBox:PrepareForNew:Not allowed default-value:"+cText);
		
		
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

		
	}

	public String get_cActualMaskValue() throws myException
	{
		String cRueck = "N";
		if (this.isSelected())
			cRueck = "Y";
		
		return cRueck;
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return this.get_cActualMaskValue();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		if (cText.equals("") || cText.equals("N"))
		{
			this.setSelected(false);
		}
		else if (cText.equals("Y"))
		{
			this.setSelected(true);
		}
		else
			throw new myException("MyE2_DB_CheckBox:set_cActualMaskValue:Not allowed value:"+cText);

	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_CheckBox:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		
		if (cText.equals("") || cText.equals("N"))
		{
			this.setSelected(false);
		}
		else if (cText.equals("Y"))
		{
			this.setSelected(true);
		}
		else
			throw new myException("MyE2_DB_CheckBox:set_cActualDBValueFormated:Not allowed value:"+cText);
		
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);

		
	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return this.bIsComplexObject;
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

	public void set_EXT_DB(MyE2EXT__DB_Component oeXT_DB)
	{
		this.oEXTDB = oeXT_DB;
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_CheckBox oCheckCopy = null;
		try
		{
			oCheckCopy = new MyE2_DB_CheckBox(this.oEXTDB.get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_CheckBox:get_Copy:copy-error!");
		}
		
		oCheckCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCheckCopy));
		oCheckCopy.__setText(this.get_oText());
		
		oCheckCopy.setFont(this.getFont());
		
		if (this.getIcon() != null)
			oCheckCopy.setIcon(this.getIcon());
		
		oCheckCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oCheckCopy));

		//NEU_09
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++)
			oCheckCopy.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		
		Vector<XX_ActionValidator> vGlobalValidators = this.get_vGlobalValidators();
		for (int i=0;i<vGlobalValidators.size();i++)
			oCheckCopy.add_GlobalValidator((XX_ActionValidator)vGlobalValidators.get(i));
		
		oCheckCopy.setToolTipText(this.getToolTipText());
		//NEU_09
		
		return oCheckCopy;
	}

	
	public void set_DescriptionAsCheckboxText(boolean bTranslated)
	{
		this.setText(new MyE2_String(this.EXT_DB().get_oSQLField().get_cFieldLabelForUser().COrig(),bTranslated).CTrans());
	}
	
	
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.setEnabled(bVoraussetzung);
		
		this.set_Icons(this.isEnabled());
	}



	
}
