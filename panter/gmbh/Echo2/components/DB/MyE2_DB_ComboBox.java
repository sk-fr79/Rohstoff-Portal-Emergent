package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_ComboBox;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MyE2_DB_ComboBox extends MyE2_ComboBox implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;

	
	
	public MyE2_DB_ComboBox(SQLField osqlField)
	{
		super();
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
	}


	public MyE2_DB_ComboBox(SQLField osqlField, String[] aDefArray) throws myException
	{
		super(aDefArray, "");
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
	}

	

	public MyE2_DB_ComboBox(SQLField osqlField, String cSQLQuery) throws myException
	{
		super();

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		this.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cRueck=oDB.EinzelAbfrageInArray(cSQLQuery,"");
		bibALL.destroy_myDBToolBox(oDB);
		
		if (cRueck == null)
			throw new myException("MyE2_DB_ComboBoxSQLQuery:Constructor:Error in Query: "+cSQLQuery);
		
		String[] cWerte = new String[cRueck.length];
		for (int i=0;i<cRueck.length;i++)
		{
			cWerte[i]=cRueck[i][0];
		}
		this.set_ContentArray(cWerte);
		
	}

	
	
	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		if (!bSetDefault)
		{
			this.setText("");
			return;
		}
		
		
		String cText = "";
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		this.setText(cText);
		
		
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

	}


	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();

		this.setEnabled(bVoraussetzung);
		this.getTextField().setEnabled(bVoraussetzung);
		
		this.getTextField().setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
	}

	
	public void show_InputStatus(boolean bInputIsOK)
	{
		this.getTextField().setStyle(this.EXT().get_STYLE_FACTORY().get_Style(this.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK));
	}

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_cDefArray() == null)
			throw new myExceptionCopy("MyE2_ComboBox:get_Copy: Error: Value-Array not initialized !");
		
		MyE2_DB_ComboBox oCombo = null;
		
		try
		{
			oCombo = new MyE2_DB_ComboBox(this.EXT_DB().get_oSQLField(),this.get_cDefArray());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_ComboBox:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oCombo.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCombo));
		oCombo.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oCombo));
		oCombo.setStyle(this.getStyle());
		oCombo.setToggleIcon(this.getToggleIcon());
		oCombo.setTogglePressedIcon(this.getTogglePressedIcon());
		oCombo.setToggleRolloverIcon(this.getToggleRolloverIcon());
		oCombo.setWidth(this.getWidth());
		oCombo.getTextField().setWidth(this.getTextField().getWidth());
		return oCombo;
	}

	
	
	
	public String get_cActualMaskValue() throws myException
	{
		return this.getText();
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return this.getText();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		this.setText(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_ComboBox:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		
		this.set_cActualMaskValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);

	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
		this.bIsComplexObject = bisComplex;
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

	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
		this.oEXTDB=oEXT_DB;
	}

}
